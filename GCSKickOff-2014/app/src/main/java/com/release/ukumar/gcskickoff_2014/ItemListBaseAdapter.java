package com.release.ukumar.gcskickoff_2014;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
        ImageView place_image;
        TextView place_open_hours;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_view_places, parent,
                    false);
        }

        place_image = (ImageView) convertView.findViewById(R.id.photo);
        if (placesList.get(position).getPhoto_reference().equals("NA")) {
            place_image.setImageResource(R.drawable.no_preview);
        } else {
            String url_photo = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=75&photoreference=" + placesList.get(position).getPhoto_reference() + "&key=AIzaSyABEP4_tb0irjCJV1dS_6Jkne6J4QcyyvM";
            new AsyncImageDownload().execute(place_image,
                    url_photo);
        }

        place_name = (TextView) convertView.findViewById(R.id.name);
        place_name.setText(placesList.get(position).getName());

        place_open_hours = (TextView) convertView.findViewById(R.id.open_hour);
        if (placesList.get(position).getOpen_hours().equals("true"))
        {
            place_open_hours.setText("Open Now");
        } else if (placesList.get(position).getOpen_hours().equals("false"))
        {
            place_open_hours.setText("Closed");
        }

        return convertView;
    }

}
