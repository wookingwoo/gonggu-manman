package com.wookingwoo.gonggu_manman.ui.create;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.wookingwoo.gonggu_manman.BottomNavigationActivity;
import com.wookingwoo.gonggu_manman.LoginActivity;
import com.wookingwoo.gonggu_manman.R;
import com.wookingwoo.gonggu_manman.databinding.FragmentCreateBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CreateFragment extends Fragment {

    private FragmentCreateBinding binding;

    private static final String TAG = CreateFragment.class.getSimpleName();

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    TextInputEditText title, recruit, price, detail;
    ChipGroup chipGroup;
    TextView wordcount;
    EditText imgUrl;
    Spinner spinnerCate;
    Button saveBtn;
    String userUid, category, city, sigungu;

    private Spinner spinnerCity, spinnerSigungu;
    private ArrayAdapter<String> arrayAdapter;
    public static final String EXTRA_ADDRESS = "address";


    List<String> selectedChipList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create, container, false);


        title = (TextInputEditText) v.findViewById(R.id.post_title);
        wordcount = (TextView) v.findViewById(R.id.post_wordcount);
        recruit = (TextInputEditText) v.findViewById(R.id.post_recruit);
        price = (TextInputEditText) v.findViewById(R.id.post_price);
        spinnerCate = (Spinner) v.findViewById(R.id.post_cate_main);
        detail = (TextInputEditText) v.findViewById(R.id.post_detail);
        chipGroup = (ChipGroup) v.findViewById(R.id.chip_group);
        imgUrl = (EditText) v.findViewById(R.id.post_imgUrl);
        saveBtn = (Button) v.findViewById(R.id.post_save);

        List<String> cateList = new ArrayList<>();

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            userUid = firebaseAuth.getCurrentUser().getUid();
            Task<QuerySnapshot> docRef = firebaseFirestore.collection("categories").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            cateList.add(document.getId());
                        }
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }

                }
            });

            ArrayAdapter<CharSequence> adapter;

            adapter = ArrayAdapter.createFromResource(getActivity(), R.array.categories, android.R.layout.simple_spinner_dropdown_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinnerCate.setAdapter(adapter);
            spinnerCate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    category = spinnerCate.getSelectedItem().toString();
                    Log.d(TAG, category + "category selected");
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });

            spinnerCity = (Spinner) v.findViewById(R.id.post_spinner_city);
            arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, (String[]) getResources().getStringArray(R.array.spinner_region));
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerCity.setAdapter(arrayAdapter);
            spinnerCity.setSelection(0);

            spinnerSigungu = (Spinner) v.findViewById(R.id.post_spinner_sigungu);

            initAddressSpinner();


            detail.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    String input = detail.getText().toString();
                    wordcount.setText(input.length() + "/500 자");
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (TextUtils.isEmpty(title.getText().toString())) {
                        title.setError("제목을 입력해주세요");
                        title.requestFocus();
                        return;
                    }
                    if (TextUtils.isEmpty(price.getText().toString())) {
                        price.setError("가격 정보를 입력해주세요");
                        price.requestFocus();
                        return;
                    }
                    if (TextUtils.isEmpty(detail.getText().toString())) {
                        detail.setError("본문 내용을 입려해주세요");
                        detail.requestFocus();
                        return;
                    }
                    if (!Pattern.matches("^[0-9]*$", recruit.getText().toString())) {
                        recruit.setError("숫자로 입력해주세요");
                        recruit.requestFocus();
                        return;
                    }
                    if (!Pattern.matches("^[0-9]*$", price.getText().toString())) {
                        price.setError("숫자로 입력해주세요");
                        price.requestFocus();
                        return;
                    }
                    getChipGroupValues();
                    uploadData();
                }
            });

        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("공구 등록은 회원만 가능합니다!");
            builder.setMessage("로그인이나 회원가입을 해주세요");
            builder.setPositiveButton("로그인이나 회원가입 할래요~", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(new Intent(getActivity().getApplicationContext(), LoginActivity.class));
                    getActivity().finish();
                }
            });
            builder.setNegativeButton("괜찮습니다. 그냥 보기만 할래요!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(new Intent(getActivity().getApplicationContext(), BottomNavigationActivity.class));
                }
            });
            AlertDialog stateDlg = builder.create();
            stateDlg.setCancelable(false);
            stateDlg.setCanceledOnTouchOutside(false);
            stateDlg.show();
        }

        return v;
    }

    private void initAddressSpinner() {
        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 시군구, 동의 스피너를 초기화한다.
                switch (position) {
                    case 0:
                        spinnerSigungu.setAdapter(null);
                        break;
                    case 1:
                        setSigunguSpinnerAdapterItem(R.array.spinner_region_seoul);
                        break;
                    case 2:
                        setSigunguSpinnerAdapterItem(R.array.spinner_region_busan);
                        break;
                    case 3:
                        setSigunguSpinnerAdapterItem(R.array.spinner_region_daegu);
                        break;
                    case 4:
                        setSigunguSpinnerAdapterItem(R.array.spinner_region_incheon);
                        break;
                    case 5:
                        setSigunguSpinnerAdapterItem(R.array.spinner_region_gwangju);
                        break;
                    case 6:
                        setSigunguSpinnerAdapterItem(R.array.spinner_region_daejeon);
                        break;
                    case 7:
                        setSigunguSpinnerAdapterItem(R.array.spinner_region_ulsan);
                        break;
                    case 8:
                        setSigunguSpinnerAdapterItem(R.array.spinner_region_sejong);
                        break;
                    case 9:
                        setSigunguSpinnerAdapterItem(R.array.spinner_region_gyeonggi);
                        break;
                    case 10:
                        setSigunguSpinnerAdapterItem(R.array.spinner_region_gangwon);
                        break;
                    case 11:
                        setSigunguSpinnerAdapterItem(R.array.spinner_region_chung_buk);
                        break;
                    case 12:
                        setSigunguSpinnerAdapterItem(R.array.spinner_region_chung_nam);

                        break;
                    case 13:
                        setSigunguSpinnerAdapterItem(R.array.spinner_region_jeon_buk);
                        break;
                    case 14:
                        setSigunguSpinnerAdapterItem(R.array.spinner_region_jeon_nam);
                        break;
                    case 15:
                        setSigunguSpinnerAdapterItem(R.array.spinner_region_gyeong_buk);
                        break;
                    case 16:
                        setSigunguSpinnerAdapterItem(R.array.spinner_region_gyeong_nam);
                        break;
                    case 17:
                        setSigunguSpinnerAdapterItem(R.array.spinner_region_jeju);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerSigungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void setSigunguSpinnerAdapterItem(int array_resource) {
        if (arrayAdapter != null) {
            spinnerSigungu.setAdapter(null);
            arrayAdapter = null;
        }

        arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, (String[]) getResources().getStringArray(array_resource));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSigungu.setAdapter(arrayAdapter);
    }



    private void getChipGroupValues() {
        int chipCount = chipGroup.getChildCount();
        if (chipCount == 0) {
            Log.d(TAG, "선택된 chip 없음!");
        } else {
            int i = 0;
            while (i < chipCount) {
                Chip chip = (Chip) chipGroup.getChildAt(i);
                if (chip.isChecked()) {
                    selectedChipList.add(chip.getText().toString());
                }
                i++;
            }
            ;
            Log.d(TAG, selectedChipList.toString());
        }

    }

    private void uploadData() {
        final String titleStr = title.getText().toString();
        final String cateStr = spinnerCate.getSelectedItem().toString();
        final String cityStr = spinnerCity.getSelectedItem().toString();
        final String sigunguStr = spinnerSigungu.getSelectedItem().toString();
        final String recruitStr = recruit.getText().toString().trim();
        final String priceStr = price.getText().toString().trim();
        final String detailStr = detail.getText().toString();
        final String imgStr = imgUrl.getText().toString();
        final String joinStr = "1";
        final String writer = userUid;
        final List<String> method = selectedChipList;

        CreateInfo createInfo = new CreateInfo(titleStr, cateStr, cityStr, sigunguStr, joinStr, recruitStr, priceStr, detailStr, imgStr, method, writer);
        uploader(createInfo);

    }

    private void uploader(CreateInfo createInfo) {
        firebaseFirestore.collection("posts").add(createInfo)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getActivity(), "게시물 등록 성공!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "게시물이 등록되었습니다!");
                        startActivity(new Intent(getActivity().getApplicationContext(), BottomNavigationActivity.class));

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "게시물 등록 실패!" + e, Toast.LENGTH_SHORT).show();
                        Log.w(TAG, "게시물 등록 실패!", e);
                    }
                });
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }


}