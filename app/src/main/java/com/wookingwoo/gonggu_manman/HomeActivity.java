package com.wookingwoo.gonggu_manman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Button searchButton = (Button) findViewById(R.id.search_btn);
        searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent searchIntent = new Intent(getApplicationContext(), SearchActivity.class);
                searchIntent.putExtra("isFocusSearchbar", true);
                startActivity(searchIntent);
            }
        });

        ImageButton filterButton = (ImageButton) findViewById(R.id.filter_btn);
        filterButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent searchIntent = new Intent(getApplicationContext(), SearchActivity.class);
                searchIntent.putExtra("isFocusSearchbar", true);
                startActivity(searchIntent);


            }
        });


    }
}