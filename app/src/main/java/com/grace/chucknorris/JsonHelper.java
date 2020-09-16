package com.grace.chucknorris;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonHelper {

    @GET("random")
    Call<ValueModel> getToken();
}
