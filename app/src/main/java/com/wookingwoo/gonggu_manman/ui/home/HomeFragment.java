package com.wookingwoo.gonggu_manman.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wookingwoo.gonggu_manman.Category;
import com.wookingwoo.gonggu_manman.CategoryAdapter;
import com.wookingwoo.gonggu_manman.R;
import com.wookingwoo.gonggu_manman.Recomendation;
import com.wookingwoo.gonggu_manman.RecomendationAdapter;
import com.wookingwoo.gonggu_manman.SearchActivity;
import com.wookingwoo.gonggu_manman.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {


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

        Button postButton = (Button) findViewById(R.id.post_btn);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PostActivity.class));
            }
        });

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


        Category category1 = new Category(R.drawable.fruits, "Fruits");
        Category category2 = new Category(R.drawable.vegetables, "Veggie");
        Category category3 = new Category(R.drawable.meat, "Meat");
        Category category4 = new Category(R.drawable.daily_necessities, "daily necessities");

        arrayList.add(category1);
        arrayList.add(category2);
        arrayList.add(category3);
        arrayList.add(category4);

        categoryAdapter.notifyDataSetChanged();

// Recomendation View
        recomendationRecyclerView = (RecyclerView) v.findViewById(R.id.recommendation_view);
        recomendationLinearLayoutManager = new LinearLayoutManager(getActivity());
        recomendationRecyclerView.setLayoutManager(recomendationLinearLayoutManager);

        recomendationArrayList = new ArrayList<>();

        recomendationAdapter = new RecomendationAdapter(recomendationArrayList);
        recomendationRecyclerView.setAdapter(recomendationAdapter);
        recomendationRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));    // 가로로 배치


        Recomendation recommendation1 = new Recomendation(R.drawable.ic_baseline_image_24, "삼다수 2L 6병", "2600원");
        Recomendation recommendation2 = new Recomendation(R.drawable.ic_baseline_image_24, "사과 5알", "1900원");
        Recomendation recommendation3 = new Recomendation(R.drawable.ic_baseline_image_24, "화장지 5개", "1100원");
        Recomendation recommendation4 = new Recomendation(R.drawable.ic_baseline_image_24, "상추 10장", "360원");

        recomendationArrayList.add(recommendation1);
        recomendationArrayList.add(recommendation2);
        recomendationArrayList.add(recommendation3);
        recomendationArrayList.add(recommendation4);


        recomendationAdapter.notifyDataSetChanged();


        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}