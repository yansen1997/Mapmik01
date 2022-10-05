package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InsertPort extends AppCompatActivity {

    private EditText edUser, edName, edRemarks, edCode, edLongitude, edLatitude;
    private Button insertPortBtn;
    private TextView responseTV;
    private ProgressBar loadingPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_port);


        edUser = findViewById(R.id.user);
        edName = findViewById(R.id.name);
        edRemarks = findViewById(R.id.remarks);
        edCode = findViewById(R.id.code);
        edLongitude = findViewById(R.id.longitude);
        edLatitude = findViewById(R.id.latitude);
        insertPortBtn = findViewById(R.id.btnSubmit);
        responseTV = findViewById(R.id.idTVResponse);
        loadingPB = findViewById(R.id.idLoadingPB);

        insertPortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            if (edUser.getText().toString().isEmpty() && edName.getText().toString().isEmpty() && edRemarks.getText().toString().isEmpty() &&
                 edCode.getText().toString().isEmpty() && edLongitude.getText().toString().isEmpty() && edLatitude.getText().toString().isEmpty()){
                    Toast.makeText(InsertPort.this, "Please enter both values", Toast.LENGTH_SHORT).show();
                    return;
                }

            PostData(edUser.getText().toString(), edName.getText().toString(), edRemarks.getText().toString(),
                     edCode.getText().toString(), edLongitude.getText().toString(), edLatitude.getText().toString());
            }

        });


    }

    private void PostData(String user, String name, String remarks, String code, String longitude, String latitude){

        loadingPB.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.119:7002/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        // passing data from our text fields to our modal class.
        DataModal modal = new DataModal(user, name, remarks, code, longitude, latitude);

        // calling a method to create a post and passing our modal class.
        Call<DataModal> call = retrofitAPI.createPost(modal);

        // on below line we are executing our method.
        call.enqueue(new Callback<DataModal>() {

            @Override
            public void onResponse(@NonNull Call<DataModal> call, @NonNull Response<DataModal> response) {
                Toast.makeText(InsertPort.this, "Data added to API", Toast.LENGTH_SHORT).show();

                loadingPB.setVisibility(View.GONE);

                edUser.setText("");
                edName.setText("");
                edRemarks.setText("");
                edCode.setText("");
                edLongitude.setText("");
                edLatitude.setText("");

                DataModal responseFromAPI = response.body();


                String responseString = "Response Code:" + response.code()+ responseFromAPI.getUser() +
                      responseFromAPI.getName() + responseFromAPI.getRemarks() + responseFromAPI.getCode() +
                        responseFromAPI.getLongitude() + responseFromAPI.getLatitude();

               responseTV.setText(responseString);
            }

            @Override
            public void onFailure(@NonNull Call<DataModal> call, Throwable t) {
                // setting text to our text view when
                // we get error response from A PI
                responseTV.setText("Error found is : " + t.getMessage());

            }
        });
    }
}