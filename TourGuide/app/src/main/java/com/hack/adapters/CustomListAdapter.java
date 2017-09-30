package com.hack.adapters;


import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.hack.models.AttractionsModel;
import com.hack.tourguide.AppController;
import com.hack.tourguide.R;

/**
 * Created by sajidhz on 9/30/2017.
 */

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<AttractionsModel> attractionItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter(Activity activity, List<AttractionsModel> attractionItems) {
        this.activity = activity;
        this.attractionItems = attractionItems;
    }

    @Override
    public int getCount() {
        return attractionItems.size();
    }

    @Override
    public Object getItem(int location) {
        return attractionItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView rating = (TextView) convertView.findViewById(R.id.rating);
        TextView description = (TextView) convertView.findViewById(R.id.description);

        // getting movie data for the row
        AttractionsModel m = attractionItems.get(position);

        // thumbnail image
        thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);

        // title
        name.setText(m.getTitle());

        // rating
        rating.setText("Rating: " + String.valueOf(m.getRating()));

        // release year
        description.setText(m.getDescription());

        return convertView;
    }

}