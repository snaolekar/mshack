package com.mshack.mshack;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;

public class MainActivity extends AppCompatActivity {
    public static final String BUTTON_QUERY = "com.mshack.mshack.mainActivity.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = findViewById(R.id.searchView);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
        searchView.setSubmitButtonEnabled(true);

        ImageButton imageButton1= findViewById(R.id.imageButton);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Search_Activity.class);
                i.putExtra(BUTTON_QUERY,"learning disability");
                startActivity(i);
            }
        });

        ImageButton imageButton2= findViewById(R.id.imageButton2);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Search_Activity.class);
                i.putExtra(BUTTON_QUERY,"dyslexia");
                startActivity(i);
            }
        });

        ImageButton imageButton3= findViewById(R.id.imageButton3);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Search_Activity.class);
                i.putExtra(BUTTON_QUERY,"speech disability");
                startActivity(i);
            }
        });

        ImageButton imageButton4= findViewById(R.id.imageButton4);
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Search_Activity.class);
                i.putExtra(BUTTON_QUERY,"motor disability");
                startActivity(i);
            }
        });

        ImageButton imageButton6= findViewById(R.id.imageButton6);
        imageButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Search_Activity.class);
                i.putExtra(BUTTON_QUERY,"local");
                startActivity(i);
            }
        });
    }
}
