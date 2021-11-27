package com.wookingwoo.gonggu_manman.ui.search;

import android.os.Bundle;
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

import com.wookingwoo.gonggu_manman.R;
import com.wookingwoo.gonggu_manman.SearchAdapter;
import com.wookingwoo.gonggu_manman.SearchData;

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
                SearchData mainData = new SearchData(R.mipmap.ic_launcher, "  제목", "카테고리", "지역", "택배/직거래", "num/num", "num");
                arrayList.add(mainData);
                mainAdapter.notifyDataSetChanged();
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