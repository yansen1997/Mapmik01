package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitAPI {

    //@POST("http://192.168.1.119:7002/api/insertNewPort/")
    //Call<DataModal> createPost(@Body DataModal dataModal);
//
//    @POST("http://192.168.1.119:7002/api/insertNewPort/")
//    Call<DataModal> createPost(@Field("user")String user,
//                               @Field("name")String name,
//                               @Field("remarks")String remarks,
//                               @Field("code")String code,
//                               @Field("longitude")String longitude,
//                               @Field("latitude")String latitude);
    @Headers({
            "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI2NiIsImp0aSI6ImUyN2U0NjVjMWUyYzIyZDZhZjA0MDA4ZWUxMjc3YWNkYTBmZWU2NTk3ODBlYTEyMGI0MzgxZTNmMmIxNGNkM2I2MDcxM2JjOWViMDY2ZjUxIiwiaWF0IjoxNjY0OTM5ODkyLjk2MjczNCwibmJmIjoxNjY0OTM5ODkyLjk2Mjc0NCwiZXhwIjoxNjY0OTQzNDkyLjk1NTgzLCJzdWIiOiIiLCJzY29wZXMiOltdfQ.Fdg_L0PfXvHTxvqHhDzOq2P9s0YWtl0EnvLQkhSf5OilVIguHPosT0URJyCKQ9nElBrsdIdNNYA0apPku2HIFAB5e9lhXV3ckalI6ZuZKoc1GsE99PW7W1u53tihwUupv31Xyc3q-xlQ1p1aJS8bY6LE-eNWZd4og40G6XINHKGTVBogL0De-8I7MUJG0gKA6ix65MkH4VbH0phuKnrPcKH5Ng0kK4Xy8Qx6Qw4zXOwXhoBRzl0VmUOXX4MFgk-EudFSgSsm3mt7KTPdtudn51vzLnyR85-MVOhpC04iY7T6c0hPYg2YCxuN7Hx8vbdsuelu5Ov8_MgtGlikewp8BtejEyEdoNjDqnFRVSqTyxORYdXhNPi2y_8osc9JkAL4vJ4n-Qy7ClWXcTi9UFJK7Gjdlgfhu6DoCNcDQzTWTv0h0Xbsqzc8pcVhX7FEgB2Wi9LW0nbBaBhBl18QOmUGTZA_UbwoLvoxCB-zuad9-t8dwoBtmcoQDJq4awmRXkavK2ogl2zQeYmTTSe2ipuQ3hqCetx2pPbWQIJSZCm0LkuIo7uSnWQIsYGAjj0c3hXhA3APrwFVHaZ9e3bhqzM_RUja1UKvPnrYuvKv5B71gbXlCO2_3DCuWXuko5DWzu7g9VHEWGi5fhxVVcHkj5KYEyDe_7--1C36BShbb0TmSEg",
            "Content-Type: application/json"
    })

    @POST("insertNewPort")
    Call<DataModal> createPost( @Body DataModal modal);

    @GET("getAllPort")
    Call<List<DataModal>> getPost(@Query("user") String user);





}