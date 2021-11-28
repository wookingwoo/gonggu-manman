package com.wookingwoo.gonggu_manman.ui.search;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.wookingwoo.gonggu_manman.R;
import com.wookingwoo.gonggu_manman.SearchAdapter;
import com.wookingwoo.gonggu_manman.SearchData;
import com.wookingwoo.gonggu_manman.ui.home.Recomendation;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

//    private SearchViewModel searchViewModel;
//    private FragmentSearchBinding binding;


    ArrayAdapter<CharSequence> adspin1, adspin2, adspin3;

    private ArrayList<SearchData> arrayList;
    private SearchAdapter mainAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_search, container, false);


        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_search);

        final Spinner spin1 = (Spinner) v.findViewById(R.id.spinner);
        final Spinner spin2 = (Spinner) v.findViewById(R.id.spinner2);
        final Spinner spin3 = (Spinner) v.findViewById(R.id.spinner3);

        adspin1 = ArrayAdapter.createFromResource(getActivity(), R.array.area, android.R.layout.simple_spinner_dropdown_item);
        adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin1.setAdapter(adspin1);
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (adspin1.getItem(i).equals("서울특별시")) {
                    adspin2 = ArrayAdapter.createFromResource(getActivity(), R.array.seoul, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("인천광역시")) {
                    adspin2 = ArrayAdapter.createFromResource(getActivity(), R.array.incheon, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        adspin3 = ArrayAdapter.createFromResource(getActivity(), R.array.categories, android.R.layout.simple_spinner_dropdown_item);
        adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin3.setAdapter(adspin3);
        spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        recyclerView = (RecyclerView) v.findViewById((R.id.rv));
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();

        mainAdapter = new SearchAdapter(arrayList);
        recyclerView.setAdapter(mainAdapter);

        Button btn1 = (Button) v.findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                arrayList.removeAll(arrayList);
                mainAdapter.notifyDataSetChanged();


                FirebaseFirestore db = FirebaseFirestore.getInstance();


                db.collection("posts")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Log.d("get-posts-search", document.getId() + " => " + document.getData());

                                        String postsTitle = (String) document.get("title");
                                        Log.d("get-posts-search", "postsTitle->" + postsTitle);

                                        String postsPrice = (String) document.get("price");
                                        Log.d("get-posts-search", "postsPrice->" + postsPrice);


                                        String postsImage = (String) document.get("image");
                                        Log.d("get-posts-search", "postsImage->" + postsImage);

                                        String postsCategory = (String) document.get("category");
                                        Log.d("get-posts-search", "postsCategory->" + postsCategory);


                                        int joinNum = 0;

                                        String joinStr = (String) document.get("join");
                                        if ((joinStr != null) && (!joinStr.equals(""))) {
                                            joinNum = Integer.parseInt(joinStr);

                                        }
                                        Log.d("get-posts-search", "joinNum->" + joinNum);


                                        int recruitNum = 0;

                                        String recruitStr = (String) document.get("recruit");
                                        if ((recruitStr != null) && (!recruitStr.equals(""))) {
                                            recruitNum = Integer.parseInt(recruitStr);

                                        }
                                        Log.d("get-posts-search", "recruitNum->" + recruitNum);


                                        String documentID = (String) document.getId();
                                        Log.d("get-posts-search", "documentID->" + documentID);


//                                        카테고리와, 지역 if문으로 비교해서 조건 추가
                                        if ((postsTitle != null) && (!postsTitle.equals("")) && (postsImage != null) && (!postsImage.equals("")) && (joinNum < recruitNum)) {


                                            SearchData mainData = new SearchData(postsImage, postsTitle, postsCategory, "지역", "택배/직거래", joinStr + "/" + recruitNum, "num", documentID);
                                            arrayList.add(mainData);
                                            mainAdapter.notifyDataSetChanged();


                                        }
                                    }
                                    mainAdapter.notifyDataSetChanged();

                                } else {
                                    Log.w("get-posts-search", "Error getting documents.", task.getException());

                                    String emptyImage = "https://via.placeholder.com/300";


                                    SearchData mainData = new SearchData(emptyImage, "  제목", "카테고리", "지역", "택배/직거래", "num/num", "num", "mock-up");
                                    arrayList.add(mainData);
                                    mainAdapter.notifyDataSetChanged();


                                }
                            }
                        });


            }
        });

        return v;
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
}