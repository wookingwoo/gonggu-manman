package com.wookingwoo.gonggu_manman.ui.create;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.wookingwoo.gonggu_manman.BottomNavigationActivity;
import com.wookingwoo.gonggu_manman.CreateInfo;
import com.wookingwoo.gonggu_manman.PostActivity;
import com.wookingwoo.gonggu_manman.R;
import com.wookingwoo.gonggu_manman.databinding.FragmentCreateBinding;

import java.util.ArrayList;
import java.util.List;

public class CreateFragment extends Fragment {

    private CreateViewModel createViewModel;
    private FragmentCreateBinding binding;

    private static final String TAG = PostActivity.class.getSimpleName();

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    TextInputEditText title, recruit, price, detail;
    TextView category;
    EditText imgUrl;
    Spinner spinnerCate;
    Button saveBtn;
    String userUid;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create, container, false);


        userUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        title = (TextInputEditText) v.findViewById(R.id.post_title);
        category = (TextView) v.findViewById(R.id.post_cate_selected);
        recruit = (TextInputEditText) v.findViewById(R.id.post_recruit);
        price = (TextInputEditText) v.findViewById(R.id.post_price);
        spinnerCate = (Spinner) v.findViewById(R.id.post_cate_main);
        detail = (TextInputEditText) v.findViewById(R.id.post_detail);
        imgUrl = (EditText) v.findViewById(R.id.post_imgUrl);
        saveBtn = (Button) v.findViewById(R.id.post_save);

        List<String> cateList = new ArrayList<>();


        firebaseFirestore = FirebaseFirestore.getInstance();
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

        spinnerCate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                category.setText(spinnerCate.getSelectedItem().toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadData();
            }
        });

        return v;
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

        CreateInfo createInfo = new CreateInfo(titleStr, cateStr, joinStr, recruitStr, priceStr, detailStr, imgStr, writer);
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