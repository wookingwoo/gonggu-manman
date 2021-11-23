package com.wookingwoo.gonggu_manman;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    Button login, join;
    ImageButton itemPageBtn;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.login_email);
        password = (EditText) findViewById(R.id.login_password);
        login = (Button) findViewById(R.id.login_button);
        join = (Button) findViewById(R.id.join_button);

        firebaseAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputEmail = email.getText().toString().trim();
                String inputPwd = password.getText().toString().trim();

                if(TextUtils.isEmpty(inputEmail)) {
                    email.setError("이메일을 입력하세요");
                    email.requestFocus();
                    return;
                }

                if(TextUtils.isEmpty(inputPwd)) {
                    password.setError("비밀번호를 입력하세요");
                    password.requestFocus();
                    return;
                }

                if(!Pattern.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$", inputEmail)) {
                    email.setError("이메일 형식으로 입력해주세요");
                    email.requestFocus();
                    return;
                }

                final ProgressDialog mDialog = new ProgressDialog(LoginActivity.this);
                mDialog.setMessage("로그인중...");
                mDialog.show();

                firebaseAuth.signInWithEmailAndPassword(inputEmail, inputPwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            mDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "로그인 성공!", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            startActivity(new Intent(getApplicationContext(), FeatureAttend.class));
                        } else {
                            mDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "로그인 실패" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
            }
        });


    }
}
