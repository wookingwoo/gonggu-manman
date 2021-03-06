package com.wookingwoo.gonggu_manman;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class JoinActivity extends AppCompatActivity {
    private EditText userEmail, password, username, phone, address;
    private RadioButton accept, decline;
    private Button submitBtn;

    private FirebaseAuth firebaseAuth;
    FirebaseFirestore fStore;
    String UID;

    List<String> attendList = new ArrayList<>();
    List<String> likeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        firebaseAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userEmail = (EditText) findViewById(R.id.join_email);
        password = (EditText) findViewById(R.id.join_password);
        username = (EditText) findViewById(R.id.join_name);
        phone = (EditText) findViewById(R.id.join_phone);
        address = (EditText) findViewById(R.id.join_address);
        accept = (RadioButton) findViewById(R.id.join_clauseaccept);
        decline = (RadioButton) findViewById(R.id.join_clausedecline);
        submitBtn = (Button) findViewById(R.id.join_submit);

        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), JoinActivity.class));
            finish();
        }


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEMAIL = userEmail.getText().toString().trim();
                String userPWD = password.getText().toString().trim();
                String userNAME = username.getText().toString();
                String userPHONE = phone.getText().toString().trim();
                String userADDRESS = address.getText().toString();

                if (TextUtils.isEmpty(userEMAIL)) {
                    userEmail.setError("???????????? ??????????????????");
                    userEmail.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(userPWD)) {
                    password.setError("??????????????? ??????????????????");
                    password.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(userNAME)) {
                    username.setError("????????? ??????????????????");
                    username.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(userPHONE)) {
                    phone.setError("????????? ????????? ??????????????????");
                    phone.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(userADDRESS)) {
                    address.setError("????????? ??????????????????");
                    address.requestFocus();
                    return;
                }
                if(!Pattern.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$", userEMAIL)) {
                    userEmail.setError("????????? ???????????? ??????????????????");
                    userEmail.requestFocus();
                    return;
                }
                if (!Pattern.matches("^(?=.*[a-zA-Z0-9])(?=.*[a-zA-Z!@#$%^&*])(?=.*[0-9!@#$%^&*]).{8,15}$", userPWD)) {
                    password.setError("???????????? ????????? ???????????????\n??? ??????, ??????, ???????????? ??? 2?????? ?????? ??????(8~15???)");
                    password.requestFocus();
                    return;
                }
                if (!Pattern.matches("^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$", userPHONE)) {
                    phone.setError("????????? ????????? ????????? ????????????.");
                    phone.requestFocus();
                    return;
                }

                final ProgressDialog mDialog = new ProgressDialog(JoinActivity.this);
                mDialog.setMessage("??????????????????...");
                mDialog.show();

                firebaseAuth.createUserWithEmailAndPassword(userEMAIL, userPWD).addOnCompleteListener((task) -> {
                    if (task.isSuccessful()) {
                        UID = firebaseAuth.getCurrentUser().getUid();
                        DocumentReference documentReference = fStore.collection("users").document(UID);
                        Map<String, Object> user = new HashMap<>();
                        user.put("Name", userNAME);
                        user.put("Email", userEMAIL);
                        user.put("Phone", userPHONE);
                        user.put("Address", userADDRESS);
                        user.put("attend_post",attendList);
                        user.put("like_post",likeList);

                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d("TAG", "???????????? ??????!");
                            }
                        });
                        mDialog.dismiss();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    } else {
                        Toast.makeText(JoinActivity.this, "???????????? ??????!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    }
                });
            }
        });
    }
}
