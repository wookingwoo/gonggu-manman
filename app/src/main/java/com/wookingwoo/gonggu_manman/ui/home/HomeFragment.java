package com.wookingwoo.gonggu_manman.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.wookingwoo.gonggu_manman.R;
import com.wookingwoo.gonggu_manman.searchTitle.SearchActivity;
import com.wookingwoo.gonggu_manman.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FirebaseAuth mFirebaseAuth;
    private String fb_uid;


    private ArrayList<Category> arrayList;
    private CategoryAdapter categoryAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;


    private ArrayList<Recomendation> recomendationArrayList;
    private RecomendationAdapter recomendationAdapter;
    private RecyclerView recomendationRecyclerView;
    private LinearLayoutManager recomendationLinearLayoutManager;


    private FragmentHomeBinding binding; // binding 삭제 금지

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_home, container, false);


        Button searchButton = (Button) v.findViewById(R.id.search_btn);
        searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent searchIntent = new Intent(getActivity().getApplicationContext(), SearchActivity.class);
                searchIntent.putExtra("isFocusSearchbar", true);
                startActivity(searchIntent);
            }
        });

        ImageButton filterButton = (ImageButton) v.findViewById(R.id.filter_btn);
        filterButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent searchIntent = new Intent(getActivity().getApplicationContext(), SearchActivity.class);
                searchIntent.putExtra("isFocusSearchbar", true);
                startActivity(searchIntent);
            }
        });


// Category View
        recyclerView = (RecyclerView) v.findViewById(R.id.category_view);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();

        categoryAdapter = new CategoryAdapter(arrayList);
        recyclerView.setAdapter(categoryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));    // 가로로 배치


        FirebaseFirestore db = FirebaseFirestore.getInstance();


        db.collection("categories")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("get-categories-fs", document.getId() + " => " + document.getData());


                                String postsImage = (String) document.get("image");
                                Log.d("get-categories-fs", "postsImage->" + postsImage);

                                String categoryTitle = (String) document.getId();
                                Log.d("get-categories-fs", "categoryTitle->" + categoryTitle);


                                if ( (!categoryTitle.equals("")) && (postsImage != null) && (!postsImage.equals(""))) {

                                    Category categories = new Category(R.drawable.daily_necessities, categoryTitle);
                                    arrayList.add(categories);
                                }
                            }
                            categoryAdapter.notifyDataSetChanged();

                        } else {
                            Log.w("get-categories-fs", "Error getting documents.", task.getException());

                            String emptyImage = "https://via.placeholder.com/300";


                            Category category1 = new Category(R.drawable.fruits, "Fruits");
                            Category category2 = new Category(R.drawable.vegetables, "Veggie");
                            Category category3 = new Category(R.drawable.meat, "Meat");
                            Category category4 = new Category(R.drawable.daily_necessities, "daily necessities");

                            arrayList.add(category1);
                            arrayList.add(category2);
                            arrayList.add(category3);
                            arrayList.add(category4);

                            categoryAdapter.notifyDataSetChanged();


                        }
                    }
                });




// Recomendation View
        recomendationRecyclerView = (RecyclerView) v.findViewById(R.id.recommendation_view);
        recomendationLinearLayoutManager = new LinearLayoutManager(getActivity());
        recomendationRecyclerView.setLayoutManager(recomendationLinearLayoutManager);

        recomendationArrayList = new ArrayList<>();

        recomendationAdapter = new RecomendationAdapter(recomendationArrayList);
        recomendationRecyclerView.setAdapter(recomendationAdapter);
        recomendationRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));    // 가로로 배치



        db.collection("posts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("get-posts-firestore", document.getId() + " => " + document.getData());

                                String postsTitle = (String) document.get("title");
                                Log.d("get-posts-firestore", "postsTitle->" + postsTitle);

                                String postsPrice = (String) document.get("price");
                                Log.d("get-posts-firestore", "postsPrice->" + postsPrice);


                                String postsImage = (String) document.get("image");
                                Log.d("get-posts-firestore", "postsImage->" + postsImage);

                                String documentID = (String) document.getId();
                                Log.d("get-posts-firestore", "documentID->" + documentID);


                                if ((postsTitle != null) && (!postsTitle.equals("")) && (postsImage != null) && (!postsImage.equals(""))) {
                                    Recomendation recommendationFS = new Recomendation(postsImage, postsTitle, postsPrice + "원", documentID);
                                    recomendationArrayList.add(recommendationFS);
                                }
                            }
                            recomendationAdapter.notifyDataSetChanged();

                        } else {
                            Log.w("get-posts-firestore", "Error getting documents.", task.getException());

                            String emptyImage = "https://via.placeholder.com/300";


                            Recomendation recommendation1 = new Recomendation(emptyImage, "삼다수 2L 6병", "2600원", "mock-up");
                            Recomendation recommendation2 = new Recomendation(emptyImage, "사과 5알", "1900원", "mock-up");
                            Recomendation recommendation3 = new Recomendation(emptyImage, "화장지 5개", "1100원", "mock-up");
                            Recomendation recommendation4 = new Recomendation(emptyImage, "상추 10장", "360원", "mock-up");

                            recomendationArrayList.add(recommendation1);
                            recomendationArrayList.add(recommendation2);
                            recomendationArrayList.add(recommendation3);
                            recomendationArrayList.add(recommendation4);

                            recomendationAdapter.notifyDataSetChanged();


                        }
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