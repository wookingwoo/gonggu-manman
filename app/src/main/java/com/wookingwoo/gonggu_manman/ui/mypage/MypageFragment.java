package com.wookingwoo.gonggu_manman.ui.mypage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.wookingwoo.gonggu_manman.BottomNavigationActivity;
import com.wookingwoo.gonggu_manman.LoginActivity;
import com.wookingwoo.gonggu_manman.R;
import com.wookingwoo.gonggu_manman.databinding.FragmentMypageBinding;
import com.wookingwoo.gonggu_manman.ui.community.CommunityViewModel;

public class MypageFragment extends Fragment {

    private com.wookingwoo.gonggu_manman.ui.mypage.MypageViewModel MypageViewModel;
    private FragmentMypageBinding binding;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore fStore;

    String uid;

    TextView email, name, phone, address;
    Button btnLogout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mypage, container, false);
        fStore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        email = (TextView) v.findViewById(R.id.info_email);
        name = (TextView) v.findViewById(R.id.info_name);
        phone = (TextView) v.findViewById(R.id.info_phone);
        address = (TextView) v.findViewById(R.id.info_address);

        if (firebaseAuth.getCurrentUser()!= null) {
            uid = firebaseAuth.getCurrentUser().getUid();
            fStore.collection("users").document(uid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot != null) {
                        String dbEmail = documentSnapshot.getString("Email");
                        String dbName = documentSnapshot.getString("Name");
                        String dbPhone = documentSnapshot.getString("Phone");
                        String dbAddress = documentSnapshot.getString("Address");

                        email.setText(dbEmail);
                        name.setText(dbName);
                        phone.setText(dbPhone);
                        address.setText(dbAddress);
                    }
                }
            });


            btnLogout = (Button) v.findViewById(R.id.logout_button);
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    firebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getActivity().getApplicationContext(), BottomNavigationActivity.class));
                }
            });
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("로그아웃 상태입니다");
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}