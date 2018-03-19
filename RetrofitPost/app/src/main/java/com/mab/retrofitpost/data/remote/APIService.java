package com.mab.retrofitpost.data.remote;

import com.mab.retrofitpost.data.Truck;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by MonisBana on 3/19/2018.
 */

public interface APIService {
    @POST("signin")
    @FormUrlEncoded
    Call<Truck> post(@Field("trucknum") String trucknum,
                         @Field("password") String password);

}
