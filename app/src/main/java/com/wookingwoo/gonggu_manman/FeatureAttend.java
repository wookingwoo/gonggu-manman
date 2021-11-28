package com.wookingwoo.gonggu_manman;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeatureAttend extends AppCompatActivity {
    ScaleAnimation scaleAnimation;
    BounceInterpolator bounceInterpolator;//애니메이션이 일어나는 동안의 회수, 속도를 조절하거나 시작과 종료시의 효과를 추가 할 수 있다
    CompoundButton button_favorite;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    String documentID;
    boolean check;
    String postsJoin;
    String postsRecruit;
    String U
    ArrayList<String> attend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feature_attend);

        firebaseAuth = FirebaseAuth.getInstance();
        scaleAnimation = new ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f, Animation.RELATIVE_TO_SELF, 0.7f, Animation.RELATIVE_TO_SELF, 0.7f);

        ImageView load = (ImageView) findViewById(R.id.imageView2);
        TextView title = (TextView) findViewById(R.id.Title);
        TextView context = (TextView) findViewById(R.id.Context);
        TextView recruit = (TextView) findViewById(R.id.Recriut);
        TextView join = (TextView) findViewById(R.id.Join);
        Button join_btn = (Button) findViewById(R.id.button2);

        Intent intent = getIntent(); /*데이터 수신*/
        documentID = intent.getExtras().getString("documentID");


        scaleAnimation.setDuration(500);
        bounceInterpolator = new BounceInterpolator();
        scaleAnimation.setInterpolator(bounceInterpolator);

        button_favorite = findViewById(R.id.button);

        button_favorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                compoundButton.startAnimation(scaleAnimation);
            }
        });

        UID = firebaseAuth.getCurrentUser().getUid();
        db.collection("posts").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d("get-posts-firestore", document.getId() + " => " + document.getData());
//                            }
//                        }
//                        else{
//                            Log.w("get-posts-firestore", "Error getting documents.", task.getException());
//                        }

                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String postDocumentID = (String) document.getId();

                            if (postDocumentID.equals(documentID)) {
                                String postsTitle = (String) document.get("title");
                                postsJoin = (String) document.get("join");
                                String postsImage = (String) document.get("image");
                                postsRecruit = (String) document.get("recruit");
                                String postsWriter = (String) document.get("writer");
                                String postsDetail = (String) document.get("detail");

//                                Log.d("get-posts-firestore", "postsTitle->" + postsTitle);
//                                Log.d("get-posts-firestore", "postsImage->" + postsImage);
//                                Log.d("get-posts-firestore", "documentID->" + documentID);

                                Glide.with(FeatureAttend.this).load(postsImage).into(load);

                                join.setText(postsJoin);
                                recruit.setText(postsRecruit);
                                title.setText(postsTitle);
                                context.setText(postsDetail);
                                break;
                            }

                        }
                    }
                });

        join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", UID);

                if (!check) {
                    postsJoin = String.valueOf(Integer.parseInt(postsJoin) + 1);
                    int total = Integer.parseInt(postsRecruit);
                    int trans = Integer.parseInt(postsJoin);
                    if(trans>total){
                        Toast.makeText(FeatureAttend.this, "참여인원이 꽉 찼습니다. 다음에 만나요~" , Toast.LENGTH_LONG).show();
                    }
                    else{
                        String joinNum = Integer.toString(trans);
                        join.setText(joinNum);
                        join_btn.setText("참가 취소");
                        check = true;
                    }
                } else {
                    postsJoin = String.valueOf(Integer.parseInt(postsJoin) - 1);
                    int trans = Integer.parseInt(postsJoin);
                    String joinNum = Integer.toString(trans);
                    join.setText(joinNum);
                    join_btn.setText("공구 참여");
                    check = false;
                }
                Log.d("FeatureAttend-log", "postsJoin: " + postsJoin);


                // firestore 업데이트
                DocumentReference postsID = db.collection("posts").document(documentID);

                postsID
                        .update("join", postsJoin)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("FeatureAttend-log", "DocumentSnapshot successfully updated!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("FeatureAttend-log", "Error updating document", e);
                            }
                        });

            }
        });
    }
    public void Update(){
        DocumentReference user = db.collection("users").document(UID);
        user
                .update("attend_list", attend)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("FeatureAttend-log", "DocumentSnapshot successfully updated!");
                    }
                });
    }
}
