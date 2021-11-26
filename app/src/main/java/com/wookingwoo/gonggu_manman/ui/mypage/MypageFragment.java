package com.wookingwoo.gonggu_manman.ui.mypage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.wookingwoo.gonggu_manman.LoginActivity;
import com.wookingwoo.gonggu_manman.R;
import com.wookingwoo.gonggu_manman.databinding.FragmentMypageBinding;

public class MypageFragment extends Fragment {

    private com.wookingwoo.gonggu_manman.ui.mypage.MypageViewModel MypageViewModel;
    private FragmentMypageBinding binding;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore fStore;

    TextView email, name, phone, address;
    Button btnLogout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mypage, container, false);
        fStore = FirebaseFirestore.getInstance();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        email = (TextView) v.findViewById(R.id.info_email);
        name = (TextView) v.findViewById(R.id.info_name);
        phone = (TextView) v.findViewById(R.id.info_phone);
        address = (TextView) v.findViewById(R.id.info_address);

        fStore.collection("users").document(uid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot != null) {
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
                startActivity(new Intent(getActivity().getApplicationContext(), LoginActivity.class));
            }
        });

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}