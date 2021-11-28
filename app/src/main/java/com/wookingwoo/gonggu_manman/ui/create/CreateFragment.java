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
import com.wookingwoo.gonggu_manman.CreateInfo;
import com.wookingwoo.gonggu_manman.LoginActivity;
import com.wookingwoo.gonggu_manman.PostActivity;
import com.wookingwoo.gonggu_manman.R;
import com.wookingwoo.gonggu_manman.databinding.FragmentCreateBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CreateFragment extends Fragment {

    private CreateViewModel createViewModel;
    private FragmentCreateBinding binding;

    private static final String TAG = PostActivity.class.getSimpleName();

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    TextInputEditText title, recruit, price, detail;
    ChipGroup chipGroup;
    TextView category, wordcount;
    EditText imgUrl;
    Spinner spinnerCate;
    Button saveBtn;
    String userUid;

    List<String> selectedChipList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create, container, false);



        title = (TextInputEditText) v.findViewById(R.id.post_title);
        category = (TextView) v.findViewById(R.id.post_cate_selected);
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

        if(firebaseAuth.getCurrentUser() != null) {
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

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, cateList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerCate.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            spinnerCate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    category.setText(spinnerCate.getSelectedItem().toString());
                    Toast.makeText(getActivity(), cateList.get(i), Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            detail.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    String input = detail.getText().toString();
                    wordcount.setText(input.length() + "/200 자");
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
                    }if (!Pattern.matches("^[0-9]*$", price.getText().toString())) {
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
            stateDlg.show();
        }

        return v;
    }

    private void getChipGroupValues() {
        int chipCount = chipGroup.getChildCount();
        if(chipCount == 0) {
            Log.d(TAG, "선택된 chip 없음!");
        } else {
            int i = 0;
            while(i < chipCount) {
                Chip chip = (Chip) chipGroup.getChildAt(i);
                if(chip.isChecked()) {
                    selectedChipList.add(chip.getText().toString());
                }
                i++;
            };
            Log.d(TAG, selectedChipList.toString());
        }

    }

    private void uploadData() {
        final String titleStr = title.getText().toString();
        final String cateStr = category.getText().toString();
        final String recruitStr = recruit.getText().toString().trim();
        final String priceStr = price.getText().toString().trim();
        final String detailStr = detail.getText().toString();
        final String imgStr = imgUrl.getText().toString();
        final String joinStr = "1";
        final String writer = userUid;
        final List<String> method = selectedChipList;

        CreateInfo createInfo = new CreateInfo(titleStr, cateStr, joinStr, recruitStr, priceStr, detailStr, imgStr, method, writer);
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