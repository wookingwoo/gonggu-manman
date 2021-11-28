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

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    ArrayAdapter<CharSequence> adspin1, adspin2, adspin3, adspin4, adspin5, adspin6, adspin7, adspin8, adspin9, adspin10, adspin11, adspin12, adspin13, adspin14, adspin15, adspin16, adspin17, adspin18, adspin19;

    private ArrayList<SearchData> arrayList;
    private SearchAdapter mainAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    String selectedRegion1, selectedRegion2, selectedCategory;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_search, container, false);

        super.onCreate(savedInstanceState);

        final Spinner spin1 = (Spinner) v.findViewById(R.id.spinner);
        final Spinner spin2 = (Spinner) v.findViewById(R.id.spinner2);
        final Spinner spin3 = (Spinner) v.findViewById(R.id.spinner3);

        adspin1 = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_region, android.R.layout.simple_spinner_dropdown_item);
        adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin1.setAdapter(adspin1);
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (adspin1.getItem(i).equals("서울특별시")) {
                    adspin2 = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_region_seoul, android.R.layout.simple_spinner_dropdown_item);
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
                } else if (adspin1.getItem(i).equals("부산광역시")) {
                    adspin3 = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_region_busan, android.R.layout.simple_spinner_dropdown_item);
                    adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin3);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("대구광역시")) {
                    adspin4 = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_region_daegu, android.R.layout.simple_spinner_dropdown_item);
                    adspin4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin4);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("인천광역시")) {
                    adspin5 = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_region_incheon, android.R.layout.simple_spinner_dropdown_item);
                    adspin5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin5);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("광주광역시")) {
                    adspin6 = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_region_gwangju, android.R.layout.simple_spinner_dropdown_item);
                    adspin6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin6);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("대전광역시")) {
                    adspin7 = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_region_daejeon, android.R.layout.simple_spinner_dropdown_item);
                    adspin7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin7);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("울산광역시")) {
                    adspin8 = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_region_ulsan, android.R.layout.simple_spinner_dropdown_item);
                    adspin8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin8);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("세종특별자치시")) {
                    adspin9 = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_region_sejong, android.R.layout.simple_spinner_dropdown_item);
                    adspin9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin9);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("경기도")) {
                    adspin10 = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_region_gyeonggi, android.R.layout.simple_spinner_dropdown_item);
                    adspin10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin10);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("강원도")) {
                    adspin11 = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_region_gangwon, android.R.layout.simple_spinner_dropdown_item);
                    adspin11.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin11);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("충청북도")) {
                    adspin12 = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_region_chung_buk, android.R.layout.simple_spinner_dropdown_item);
                    adspin12.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin12);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("충청남도")) {
                    adspin13 = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_region_chung_nam, android.R.layout.simple_spinner_dropdown_item);
                    adspin13.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin13);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("전라북도")) {
                    adspin14 = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_region_jeon_buk, android.R.layout.simple_spinner_dropdown_item);
                    adspin14.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin14);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("전라남도")) {
                    adspin15 = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_region_jeon_nam, android.R.layout.simple_spinner_dropdown_item);
                    adspin15.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin15);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("경상북도")) {
                    adspin16 = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_region_gyeong_buk, android.R.layout.simple_spinner_dropdown_item);
                    adspin16.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin16);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("경상남도")) {
                    adspin17 = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_region_gyeong_nam, android.R.layout.simple_spinner_dropdown_item);
                    adspin17.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin17);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("제주특별자치도")) {
                    adspin18 = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_region_jeju, android.R.layout.simple_spinner_dropdown_item);
                    adspin18.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin18);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }


                spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                        selectedRegion2 = spin2.getSelectedItem().toString();
                        Log.d("get-posts-search", "selectedRegion2: " + selectedRegion2);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        adspin19 = ArrayAdapter.createFromResource(getActivity(), R.array.categories, android.R.layout.simple_spinner_dropdown_item);
        adspin19.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin3.setAdapter(adspin19);
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

                                        List<String> method
                                                = (List<String>) document.get("method");
                                        Log.d("get-posts-search", "method->" + method
                                        );

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

                                        String postsCity = (String) document.get("city");
                                        Log.d("get-posts-search", "postsTitle->" + postsTitle);

                                        String postsSigungu = (String) document.get("sigungu");
                                        Log.d("get-posts-search", "postsSigungu->" + postsSigungu);

//                                        카테고리와, 지역 if문으로 비교해서 조건 추가
                                        if ((postsTitle != null) && (!postsTitle.equals("")) && (postsImage != null) && (!postsImage.equals(""))) {


                                            if ((selectedRegion2 == null) || (postsSigungu.equals(selectedRegion2))) {
                                                SearchData mainData = new SearchData(postsImage, postsTitle, postsCategory, postsSigungu, method.toString(), joinStr + "/" + recruitNum, postsPrice + "원", documentID);
                                                arrayList.add(mainData);
                                                mainAdapter.notifyDataSetChanged();
                                            }


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