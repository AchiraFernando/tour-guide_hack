package com.hack.tourguide;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceDetectionApi;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public class MainActivity extends AppCompatActivity implements PlaceSelectionListener {

    FloatingActionButton add, message, video, call;
    Animation floatButOpen, floatButClose, floatButRotateForward, floatButRotateBackward;
    boolean isOpen = false;

    ImageView attractions_but, transport_but, emergency_but, accommodation_but;

    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
     Button city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = (FloatingActionButton) findViewById(R.id.add);
        message = (FloatingActionButton) findViewById(R.id.message);
        video = (FloatingActionButton) findViewById(R.id.video);
        call = (FloatingActionButton) findViewById(R.id.call);


//        attractions_but = (ImageView) findViewById(R.id.attractions);
//        transport_but = (ImageView) findViewById(R.id.transport);
//        emergency_but = (ImageView) findViewById(R.id.emergencyImg);
//        accommodation_but = (ImageView) findViewById(R.id.accommodationImg);

        city = (Button) findViewById(R.id.city);
        city.setBackground(null);
        city.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openAutocompleteActivity();
            }
        });


//        floatButOpen = AnimationUtils.loadAnimation(this, R.anim.floatbutton_open);
//        floatButClose = AnimationUtils.loadAnimation(this, R.anim.floatbutton_close);
//
//        floatButRotateForward = AnimationUtils.loadAnimation(this, R.anim.rotate_forward);
//        floatButRotateBackward = AnimationUtils.loadAnimation(this, R.anim.rotate_backward);


        // Register a listener to receive callbacks when a place has been selected or an error has
        // occurred.

//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                animateFloatBut();
//            }
//        });
//        message.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                animateFloatBut();
//                Intent myIntent = new Intent(getBaseContext(), ListActivity.class);
//                //myIntent.putExtra("key", value); //Optional parameters
//                MainActivity.this.startActivity(myIntent);
//            }
//        });
//        video.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                animateFloatBut();
//                Toast.makeText(MainActivity.this, "Video float clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
//        call.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                animateFloatBut();
//                Toast.makeText(MainActivity.this, "Call float clicked", Toast.LENGTH_SHORT).show();
//            }
//        });

//        attractions_but.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent attractionIntent = new Intent(MainActivity.this, ListActivity.class);
//                attractionIntent.putExtra("key", "Attractions");
//                MainActivity.this.startActivity(attractionIntent);
//            }
//        });
//        transport_but.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent transportIntent = new Intent(MainActivity.this, ListActivity.class);
//                transportIntent.putExtra("key", "Transport");
//                MainActivity.this.startActivity(transportIntent);
//            }
//        });
//        emergency_but.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent emergencyIntent = new Intent(MainActivity.this, ListActivity.class);
//                emergencyIntent.putExtra("key", "Emergency");
//                MainActivity.this.startActivity(emergencyIntent);
//            }
//        });
//        accommodation_but.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent accommodationIntent = new Intent(MainActivity.this, ListActivity.class);
//                accommodationIntent.putExtra("key", "Accommodation");
//                MainActivity.this.startActivity(accommodationIntent);
//            }
//        });
    }


    private void openAutocompleteActivity() {
        try {
            // The autocomplete activity requires Google Play Services to be available. The intent
            // builder checks this and throws an exception if it is not the case.

            AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                    .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES).setCountry("LK").build();
            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).setFilter(typeFilter)
                    .build(this);
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException e) {
            // Indicates that Google Play Services is either not installed or not up to date. Prompt
            // the user to correct the issue.
            GoogleApiAvailability.getInstance().getErrorDialog(this, e.getConnectionStatusCode(),
                    0 /* requestCode */).show();
        } catch (GooglePlayServicesNotAvailableException e) {
            // Indicates that Google Play Services is not available and the problem is not easily
            // resolvable.
            String message = "Google Play Services is not available: " +
                    GoogleApiAvailability.getInstance().getErrorString(e.errorCode);

            Log.e("", message);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
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

        setPlace(place);

        // Format the returned place's Details and display them in the TextView.
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check that the result was from the autocomplete widget.
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Get the user's selected place from the Intent.
                Place place = PlaceAutocomplete.getPlace(this, data);
                Log.i("", "Place Selected: " + place.getName());
                city.setText(place.getName());
                // Format the place's details and display them in the TextView.
//                mPlaceDetailsText.setText(formatPlaceDetails(getResources(), place.getName(),
//                        place.getId(), place.getAddress(), place.getPhoneNumber(),
//                        place.getWebsiteUri()));
//
//                // Display attributions if required.
//                CharSequence attributions = place.getAttributions();
//                if (!TextUtils.isEmpty(attributions)) {
//                    mPlaceAttribution.setText(Html.fromHtml(attributions.toString()));
//                } else {
//                    mPlaceAttribution.setText("");
//                }
//            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
//                Status status = PlaceAutocomplete.getStatus(this, data);
//                Log.e(TAG, "Error: Status = " + status.toString());
//            } else if (resultCode == RESULT_CANCELED) {
//                // Indicates that the activity closed before a selection was made. For example if
//                // the user pressed the back button.
//            }
            }}
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

    String place;

    public String getPlace() {
        return this.place;
    }
    public void setPlace(Place place) {
        this.place = place.getName().toString();
    }


}
