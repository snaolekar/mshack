package com.mshack.mshack;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Belal on 9/5/2017.
 */

public class ListViewAdapter extends ArrayAdapter<Title_URL> {

    //the hero list that will be displayed
    private List<Title_URL> titleUrlList;

    //the context object
    private Context mCtx;

    //so while creating the object of this adapter class we need to give herolist and context
    public ListViewAdapter(List<Title_URL> urlList, Context mCtx) {
        super(mCtx, R.layout.list_items, urlList);
        this.titleUrlList = urlList;
        this.mCtx = mCtx;
    }

    //this method will return the list item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //getting the layoutinflater
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        //creating a view with our xml layout
        View listViewItem = inflater.inflate(R.layout.list_items, null, true);

        //getting text views
        TextView textViewListTitle = listViewItem.findViewById(R.id.list_title);
        TextView textViewListUrl = listViewItem.findViewById(R.id.list_url);

        //Getting the hero for the specified position
        Title_URL urlTitleList = titleUrlList.get(position);

        //setting hero values to textviews
        textViewListTitle.setText((urlTitleList.getTitle()));
        textViewListUrl.setText(urlTitleList.getUrl());

        //returning the listitem
        return listViewItem;
    }

    public Title_URL getItem(int position){
        return titleUrlList.get(position);
    }
}

