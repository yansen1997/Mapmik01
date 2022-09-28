package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.HashMap;
import java.util.Map;


public class GetAPI extends AppCompatActivity {
    Button btn_GetAll, btn_GetById;
    EditText et_DataInput;
    ListView lv_GetData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_api);

        getSupportActionBar().hide();
        btn_GetAll = findViewById(R.id.button);

        et_DataInput = findViewById(R.id.editTextTextPersonName2);
        lv_GetData = findViewById(R.id.listview);

        btn_GetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue queue = Volley.newRequestQueue(GetAPI.this);
                String url = "https://otis.stratumfive.com/rest/1.0/vessels/?get=9104134,9548249,9555632,9558488,9562051,9562063,9654957,9069944,9547960,9549334,9670274,9671890,9672351,9672363,9672375,9672399,9178549,9488243,9783875,9783887,9791755,9791767,9791779,9791781,9813151,9813175,9813187,9813199,9069956,9164249,9164251,9213961,9228617,9253260,9393503,9429352,9501069,9564061,9586291,9614426,9648740,9672387,8733433,8773196,9077202,9345001,9371933,9413298,9479981,9488267";

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String test = response.toString();
                        try {
                            JSONObject jo = new JSONObject(test);
                            JSONArray ja = jo.getJSONArray("obj");
                            for(int i=0; i< ja.length(); i++){
                                JSONObject j = ja.getJSONObject(i);
                                Toast.makeText(GetAPI.this, "LONGITUDE : " + j.getString("lon") + ",LATITUDE : " + j.getString("lat"), Toast.LENGTH_SHORT).show();

                            }
//                            Toast.makeText(GetAPI.this, "Result : "+ja, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GetAPI.this, "Error: "+error, Toast.LENGTH_SHORT).show();

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

////                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
//                String url = "http://192.168.1.119:8010/identity/getUserProfile?EMAIL=" +et_DataInput.getText().toString();
//               // String url = "http://192.168.1.119:8010/user/getAll";
////                String url = "http://192.168.1.119:8010/company/getAll";
//
//
//                //StringRequest untuk JSONObject
//                StringRequest request = new StringRequest(Request.Method.GET, url,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//
//                                Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
//                            }
//                        }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                MySingleton.getInstance(MainActivity.this  ).addToRequestQueue(request);


//                JsonArrayRequest untuk 1 JSONArray
////
//                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null, new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        String usernameID ="";
//
//                        try {
//                            JSONObject username = response.getJSONObject(0);
//                            usernameID = username.getString("user_id");
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//
//
//                        Toast.makeText(MainActivity.this, "User ID = "+usernameID, Toast.LENGTH_SHORT).show();
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        error.printStackTrace();
//                        Toast.makeText(MainActivity.this, "Error :D ", Toast.LENGTH_SHORT).show();
//                    }
//                });


//
//// Add the request to the RequestQueue.
//                queue.add(request);


                //Toast.makeText(MainActivity.this, "You Clicked", Toast.LENGTH_SHORT).show();
            }
        });


    }

}