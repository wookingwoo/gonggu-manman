package com.wookingwoo.gonggu_manman;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class PostActivity extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    TextInputEditText title, recruit, detail;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        title = (TextInputEditText) findViewById(R.id.post_title);
        recruit = (TextInputEditText) findViewById(R.id.post_recruit);
        detail = (TextInputEditText) findViewById(R.id.post_detail);
        saveBtn = (Button) findViewById(R.id.post_save);

        firebaseFirestore = FirebaseFirestore.getInstance();
        DocumentReference docRef = firebaseFirestore.collection("categories").document("name");
        Spinner spinnerMain = (Spinner) findViewById(R.id.post_cate_main);
        Spinner spinnerSub = (Spinner) findViewById(R.id.post_cate_sub);
        List<String> cateMain = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, cateMain);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMain.setAdapter(adapter);

    }

    private void uploadData() {
        final String titleStr = title.getText().toString();
        final String recruitStr = recruit.getText().toString().trim();
        final String detailStr = detail.getText().toString();
        final String joinStr = "0";

        CreateInfo createInfo = new CreateInfo(titleStr, joinStr, recruitStr, detailStr);
        uploader(createInfo);



    }

    private void uploader(CreateInfo createInfo) {
//        firebaseFirestore.collection("products").
    }
}
