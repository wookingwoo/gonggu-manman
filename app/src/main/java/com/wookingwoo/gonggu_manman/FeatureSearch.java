package com.wookingwoo.gonggu_manman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class FeatureSearch extends AppCompatActivity {

    ArrayAdapter<CharSequence> adspin1, adspin2;

    private ArrayList<SearchData> arrayList;
    private SearchAdapter mainAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search);

        final Spinner spin1 = (Spinner)findViewById(R.id.spinner);
        final Spinner spin2 = (Spinner)findViewById(R.id.spinner2);

        adspin1 = ArrayAdapter.createFromResource(this, R.array.area, android.R.layout.simple_spinner_dropdown_item);
        adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin1.setAdapter(adspin1);
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (adspin1.getItem(i).equals("서울특별시")) {
                    adspin2 = ArrayAdapter.createFromResource(FeatureSearch.this, R.array.seoul, android.R.layout.simple_spinner_dropdown_item);
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

                else if (adspin1.getItem(i).equals("인천광역시")) {
                    adspin2 = ArrayAdapter.createFromResource(FeatureSearch.this, R.array.incheon, android.R.layout.simple_spinner_dropdown_item);
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

        recyclerView = (RecyclerView) findViewById((R.id.rv));
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();

        mainAdapter = new SearchAdapter(arrayList);
        recyclerView.setAdapter(mainAdapter);

        Button btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchData mainData = new SearchData(R.mipmap.ic_launcher, "  제목", "카테고리", "지역", "택배/직거래","num/num", "num");
                arrayList.add(mainData);
                mainAdapter.notifyDataSetChanged();
            }
        });

    }
}
