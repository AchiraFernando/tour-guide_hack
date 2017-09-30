package com.hack.tourguide;

/**
 * Created by sajidhz on 9/30/2017.
 */

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Attr;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.hack.adapters.CustomListAdapter;
import com.hack.models.*;

public class ListActivity extends AppCompatActivity {
    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();

    // Movies json url
    private static final String url = "https://api.androidhive.info/json/movies.json";
    private ProgressDialog pDialog;
    private List<AttractionsModel> attractionsList = new ArrayList<>();
    private ListView listView;
    private CustomListAdapter adapter;

    FloatingActionButton add, message, video, call;
    Animation floatButOpen, floatButClose, floatButRotateForward, floatButRotateBackward;
    boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_main);

        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this, attractionsList);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        add = (FloatingActionButton) findViewById(R.id.add);
        message = (FloatingActionButton) findViewById(R.id.message);
        video = (FloatingActionButton) findViewById(R.id.video);
        call = (FloatingActionButton) findViewById(R.id.call);

        floatButOpen = AnimationUtils.loadAnimation(this, R.anim.floatbutton_open);
        floatButClose = AnimationUtils.loadAnimation(this, R.anim.floatbutton_close);

        floatButRotateForward = AnimationUtils.loadAnimation(this, R.anim.rotate_forward);
        floatButRotateBackward = AnimationUtils.loadAnimation(this, R.anim.rotate_backward);

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
                Toast.makeText(ListActivity.this, "Message float clicked", Toast.LENGTH_SHORT).show();
            }
        });
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFloatBut();
                Toast.makeText(ListActivity.this, "Video float clicked", Toast.LENGTH_SHORT).show();
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFloatBut();
                Toast.makeText(ListActivity.this, "Call float clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // changing action bar color
//       getSupportActionBar().setBackgroundDrawable(
//                new ColorDrawable(Color.parseColor("#1b1b1b")));

        // Creating volley request obj
        JsonArrayRequest movieReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                AttractionsModel movie = new AttractionsModel();
                                movie.setTitle(obj.getString("name"));
                                movie.setThumbnailUrl(obj.getString("image"));
                                movie.setRating(((Number) obj.get("rating"))
                                        .doubleValue());

                                movie.setDescription(obj.getString("description"));
                                // adding movie to movies array
                                attractionsList.add(movie);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      //  getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}