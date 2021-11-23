package com.wookingwoo.gonggu_manman;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.animation.Animator;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import com.bumptech.glide.Glide;

public class FeatureAttend extends AppCompatActivity {
    ScaleAnimation scaleAnimation;
    BounceInterpolator bounceInterpolator;//애니메이션이 일어나는 동안의 회수, 속도를 조절하거나 시작과 종료시의 효과를 추가 할 수 있다
    CompoundButton button_favorite;

    FirebaseAuth firebaseAuth;
    String documentID="f4dSOwWGa7EQYSB4GwQS";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feature_attend);

        firebaseAuth = FirebaseAuth.getInstance();
        scaleAnimation = new ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f, Animation.RELATIVE_TO_SELF, 0.7f, Animation.RELATIVE_TO_SELF, 0.7f);

        ImageView load=(ImageView)findViewById(R.id.imageView2);

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

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("posts") .get()
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
                                String postsTitle = (String) document.get("title");
                                String postsJoin = (String) document.get("join");
                                String postsImage = (String) document.get("image");
                                String postsRecruit = (String) document.get("recruit");
                                String postsWriter = (String) document.get("writer");
                                String postDocumentID = (String) document.getId();

                                if(postDocumentID.equals(documentID)){
                                    Log.d("get-posts-firestore", "postsTitle->" + postsTitle);
                                    Log.d("get-posts-firestore", "postsImage->" + postsImage);
                                    Log.d("get-posts-firestore", "documentID->" + documentID);
                                    Glide.with(FeatureAttend.this).load(postsImage).into(load);
                                    break;
                                }

                            }
                    }
                });

    }
}
