package com.mshack.mshack;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Search_Activity extends AppCompatActivity {
    public static final String URL_OPEN = "com.mshack.mshack.searchActivity.MESSAGE";
    ListView listViewWeb ;
    ListView listViewStored ;
    List<Title_URL> webList ;
    List<Title_URL> storedList ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_);

        listViewWeb = findViewById(R.id.listView2);
        listViewStored = findViewById(R.id.listView1);
        webList= new ArrayList<>();
        storedList= new ArrayList<>();

        listViewWeb.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Title_URL item = (Title_URL) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(getApplicationContext(),ResultWebView.class);
                intent.putExtra(URL_OPEN,item.getUrl());
                startActivity(intent);
            }
        });

        listViewStored.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Title_URL item = (Title_URL) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(getApplicationContext(),ResultWebView.class);
                intent.putExtra(URL_OPEN,item.getUrl());
                startActivity(intent);
            }
        });

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }
        else{
            String query = intent.getStringExtra(MainActivity.BUTTON_QUERY);
            if(query.equals("local"))
                doMySearch1();
            else
                doMySearch(query);
        }
    }

    void loadTitleUrlList(String query){
        //creating a string request to send request to the url
        String URL = "https://www.googleapis.com/customsearch/v1?key=AIzaSyB8FLZNoP6HI5vfXwinHq0h0MZU7Sps4mA&"+
                "cx=004292046143926136976:wqe7d7biz7q"+"&q="+query+"&sender=app&return=json";
        String JSON_URL = URL.replaceAll(" ","%20");
        Log.d("URL",JSON_URL);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);

                            //we have the array named  inside the object
                            //so here we are getting that json array
                            JSONArray itemArray = obj.getJSONArray("items");

                            //now looping through all the elements of the json array
                            for (int i = 0; i < itemArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject itemObject = itemArray.getJSONObject(i);

                                //creating a hero object and giving them the values from json object
                                Title_URL titleUrl = new Title_URL(itemObject.getString("title"), itemObject.getString("link"));
                                Log.d("Item",titleUrl.toString());
                                webList.add(titleUrl);
                            }

                            //creating custom adapter object
                            ListViewAdapter adapter = new ListViewAdapter(webList, getApplicationContext());

                            //adding the adapter to listview
                            listViewWeb.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);

    }

    void doMySearch(String query) {
        Log.d("Search_Activity", query);
        loadTitleUrlList(query);
    }
    void doMySearch1(){
        String [] storedContent = getResources().getStringArray(R.array.local_resource);
        for (String obj: storedContent){
            Log.d("TEST",obj);
            String[] item= obj.split(",",2);
            Title_URL titleUrl = new Title_URL(item[0], item[1]);
            Log.d("Item", titleUrl.toString());
            storedList.add(titleUrl);
        }
        ListViewAdapter adapter = new ListViewAdapter(storedList, getApplicationContext());
        //adding the adapter to listview
        listViewStored.setAdapter(adapter);
    }

}
