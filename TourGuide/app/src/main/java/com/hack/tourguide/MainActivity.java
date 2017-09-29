package com.hack.tourguide;

import android.app.Activity;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public class MainActivity extends AppCompatActivity implements PlaceSelectionListener {

    FloatingActionButton add, message, video, call;
    Animation floatButOpen, floatButClose, floatButRotateForward, floatButRotateBackward;
    boolean isOpen = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = (FloatingActionButton) findViewById(R.id.add);
        message = (FloatingActionButton) findViewById(R.id.message);
        video = (FloatingActionButton) findViewById(R.id.video);
        call = (FloatingActionButton) findViewById(R.id.call);

        floatButOpen = AnimationUtils.loadAnimation(this, R.anim.floatbutton_open);
        floatButClose = AnimationUtils.loadAnimation(this, R.anim.floatbutton_close);

        floatButRotateForward = AnimationUtils.loadAnimation(this, R.anim.rotate_forward);
        floatButRotateBackward = AnimationUtils.loadAnimation(this, R.anim.rotate_backward);

        // Retrieve the PlaceAutocompleteFragment.
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        // Register a listener to receive callbacks when a place has been selected or an error has
        // occurred.
        autocompleteFragment.setOnPlaceSelectedListener(this);

        autocompleteFragment.setBoundsBias(new LatLngBounds(new LatLng(8.270378, 80.365676),new LatLng(8.373810, 80.423482)));

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFloatBut();
            }
        });
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFloatBut();
                Toast.makeText(MainActivity.this, "Message float clicked", Toast.LENGTH_SHORT).show();
            }
        });
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFloatBut();
                Toast.makeText(MainActivity.this, "Video float clicked", Toast.LENGTH_SHORT).show();
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFloatBut();
                Toast.makeText(MainActivity.this, "Call float clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void animateFloatBut() {
        if (isOpen) {
            add.startAnimation(floatButRotateBackward);
            message.startAnimation(floatButClose);
            video.startAnimation(floatButClose);
            call.startAnimation(floatButClose);
            message.setClickable(false);
            video.setClickable(false);
            call.setClickable(false);
            isOpen = false;
        }else {
            add.startAnimation(floatButRotateForward);
            message.startAnimation(floatButOpen);
            video.startAnimation(floatButOpen);
            call.startAnimation(floatButOpen);
            message.setClickable(true);
            video.setClickable(true);
            call.setClickable(true);
            isOpen = true;
        }
    }

    /**
     * Callback invoked when a place has been selected from the PlaceAutocompleteFragment.
     */
    @Override
    public void onPlaceSelected(Place place) {
        Toast.makeText(this, "Place Selected: " + place.getName(),
                Toast.LENGTH_SHORT).show();

        // Format the returned place's details and display them in the TextView.
        //mPlaceDetailsText.setText(formatPlaceDetails(getResources(), place.getName(), place.getId(),
               // place.getAddress(), place.getPhoneNumber(), place.getWebsiteUri()));

        //CharSequence attributions = place.getAttributions();
        //if (!TextUtils.isEmpty(attributions)) {
            //mPlaceAttribution.setText(Html.fromHtml(attributions.toString()));
        //} else {
        //    mPlaceAttribution.setText("");
        //}
    }

    /**
     * Callback invoked when PlaceAutocompleteFragment encounters an error.
     */
    @Override
    public void onError(Status status) {
        //Log.e(TAG, "onError: Status = " + status.toString());

        Toast.makeText(this, "Place selection failed: " + status.getStatusMessage(),
                Toast.LENGTH_SHORT).show();
    }

    /**
     * Helper method to format information about a place nicely.
     */
    /*private static Spanned formatPlaceDetails(Resources res, CharSequence name, String id,
                                              CharSequence address, CharSequence phoneNumber, Uri websiteUri) {
        Log.e(TAG, res.getString(R.string.place_details, name, id, address, phoneNumber,
               websiteUri));
        return Html.fromHtml(res.getString(R.string.place_details, name, id, address, phoneNumber,
                websiteUri));
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}