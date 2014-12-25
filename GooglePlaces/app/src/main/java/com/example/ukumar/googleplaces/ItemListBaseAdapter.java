package com.example.ukumar.googleplaces;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ukumar on 12/24/2014.
 */
public class ItemListBaseAdapter extends BaseAdapter {

    Context context;
    ArrayList<Places> placesList;

    public ItemListBaseAdapter(Context context, ArrayList<Places> result) {
        this.placesList = result;
        this.context = context;
    }

    public int getCount() {
        return placesList.size();
    }

    public Object getItem(int position) {
        return placesList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        TextView place_name;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_view_places, parent,
                false);
        place_name = (TextView) convertView.findViewById(R.id.name);
        place_name.setText(placesList.get(position).getName());
        return convertView;
    }

}
