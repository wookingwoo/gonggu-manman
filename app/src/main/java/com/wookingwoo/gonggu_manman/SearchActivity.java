package com.wookingwoo.gonggu_manman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.SearchView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity {

    private List<String> items = Arrays.asList("생수", "사과", "바나나", "화장지", "과일", "음료", "육류", "반찬", "의류", "생활용품", "육아용품");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        Intent secondIntent = getIntent();
        boolean isFocusSearchbar = secondIntent.getBooleanExtra("isFocusSearchbar", false);


//        intent로 넘겨받은 isFocusSearchbar 값이 true이면 search_view에 포커스하기
        if (isFocusSearchbar) {
            SearchView searchView = (SearchView) findViewById(R.id.search_view);
            searchView.setIconifiedByDefault(true);
            searchView.setFocusable(true);
            searchView.setIconified(false);
            searchView.requestFocusFromTouch();
        }


        SearchView searchView = findViewById(R.id.search_view);
        TextView resultTextView = findViewById(R.id.search_result);
        resultTextView.setText(getResult());

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                resultTextView.setText(search(s));

                return true;
            }
        });
    }

    private String search(String query) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            String item = items.get(i);

            if (item.toLowerCase().trim().contains(query.toLowerCase().trim())) {
                sb.append(item);
                if (i != items.size() - 1) {
                    sb.append("\n");
                }
            }


        }
        return sb.toString();
    }


    private String getResult() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            String item = items.get(i);
            sb.append(item);

            if (i != items.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}