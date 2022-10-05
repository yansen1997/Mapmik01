package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetPort extends AppCompatActivity {

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_port);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.119:7002/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<List<DataModal>> call = retrofitAPI.getPost("yansen@temasline.com");
        call.enqueue(new Callback<List<DataModal>>() {
            @Override
            public void onResponse(Call<List<DataModal>> call, Response<List<DataModal>> response) {
            List<DataModal> postList = response.body();
            PostAdapter postAdapter = new PostAdapter(GetPort.this, postList);
            recyclerView.setAdapter(postAdapter);
            }

            @Override
            public void onFailure(Call<List<DataModal>> call, Throwable t) {

            }
        });
    }
}