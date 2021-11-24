package com.wookingwoo.gonggu_manman;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import java.util.ArrayList;
import java.util.List;

public class PostActivity extends AppCompatActivity {
    private static final String TAG = PostActivity.class.getSimpleName();

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    TextInputEditText title, recruit, detail;
    TextView category;
    EditText imgUrl;
    Spinner spinnerCate;
    Button saveBtn;
    String userUid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        userUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        title = (TextInputEditText) findViewById(R.id.post_title);
        category = (TextView) findViewById(R.id.post_cate_selected);
        recruit = (TextInputEditText) findViewById(R.id.post_recruit);
        spinnerCate = (Spinner) findViewById(R.id.post_cate_main);
        detail = (TextInputEditText) findViewById(R.id.post_detail);
        imgUrl = (EditText) findViewById(R.id.post_imgUrl);
        saveBtn = (Button) findViewById(R.id.post_save);

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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, cateList);
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
    }



    private void uploadData() {
        final String titleStr = title.getText().toString();
        final String cateStr = category.getText().toString();
        final int recruitStr = Integer.parseInt(recruit.getText().toString().trim());
        final String detailStr = detail.getText().toString();
        final String imgStr = imgUrl.getText().toString();
        final int joinStr = 1;
        final String writer = userUid;

        CreateInfo createInfo = new CreateInfo(titleStr, cateStr, joinStr, recruitStr, detailStr, imgStr, writer);
        uploader(createInfo);

    }

    private void uploader(CreateInfo createInfo) {
        firebaseFirestore.collection("posts").add(createInfo)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(PostActivity.this, "게시물 등록 성공!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "게시물이 등록되었습니다!");
                        startActivity(new Intent(getApplicationContext(), BottomNavigationActivity.class));

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(PostActivity.this, "게시물 등록 실패!" + e, Toast.LENGTH_SHORT).show();
                        Log.w(TAG, "게시물 등록 실패!", e);
                    }
                });
    }
}
