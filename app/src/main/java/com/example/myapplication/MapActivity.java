package com.example.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapActivity extends AppCompatActivity {

    @SuppressWarnings("FieldCanBeLocal")
    private final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;

    private MapView map = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        setContentView(R.layout.activity_map);

        map = (MapView) findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);

        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);

        IMapController mapController = map.getController();
        mapController.setZoom(9.5);
        GeoPoint startPoint = new GeoPoint(-6.138377, 106.866463);
        GeoPoint markerpoint = new GeoPoint(-6.137170, 106.865240);
        mapController.setCenter(startPoint);

        RequestQueue queue = Volley.newRequestQueue(MapActivity.this);
        String url = "https://otis.stratumfive.com/rest/1.0/vessels/?get=9104134,9548249,9555632,9558488,9562051,9562063,9654957,9069944,9547960,9549334,9670274,9671890,9672351,9672363,9672375,9672399,9178549,9488243,9783875,9783887,9791755,9791767,9791779,9791781,9813151,9813175,9813187,9813199,9069956,9164249,9164251,9213961,9228617,9253260,9393503,9429352,9501069,9564061,9586291,9614426,9648740,9672387,8733433,8773196,9077202,9345001,9371933,9413298,9479981,9488267";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String test = response.toString();
                try {
                    JSONObject jo = new JSONObject(test);
                    JSONArray ja = jo.getJSONArray("obj");
                    for(int i=0; i< ja.length() ; i++){
                        JSONObject j = ja.getJSONObject(i);
                        Marker startMarker = new Marker(map);

                        Double lon = j.getDouble("lon");
                        Double lat = j.getDouble("lat");
                        GeoPoint markerpoint = new GeoPoint(lat,lon);
                        try{
                            startMarker.setPosition(markerpoint);
                        }
                        catch(Exception e){
                            Toast.makeText(MapActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                        }
                        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                        map.getOverlays().add(startMarker);

//                        Toast.makeText(MapActivity.this, "LONGITUDE : " + j.getString("lon") + ",LATITUDE : " + j.getString("lat"), Toast.LENGTH_SHORT).show();

                    }
//                            Toast.makeText(GetAPI.this, "Result : "+ja, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    Toast.makeText(MapActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MapActivity.this, "Error: "+error, Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {



                Map<String, String>  headers= new HashMap<String, String>();
                headers.put("Authorization ", "Basic dGVtYXNsaW5lOlhJYTFHak5jU0plbjgzUHRUUFQ2aGFUQ2c=");
                return headers;
            }
        };

        queue.add(request);

        requestPermissionsIfNecessary(new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
        });
//        Marker startMarker = new Marker(map);
//        startMarker.setPosition(markerpoint);
//        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
//        map.getOverlays().add(startMarker);



    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Configuration.getInstance().load(this, prefs);
        map.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Configuration.getInstance().save(this, prefs);
        map.onPause();  //needed for compass, my location overlays, v6.0.0 and up

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, int[] grantResults) {
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            permissionsToRequest.add(permissions[i]);
        }
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }


    private void requestPermissionsIfNecessary(String[] permissions) {
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                permissionsToRequest.add(permission);
            }
        }
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }



}

