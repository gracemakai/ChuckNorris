package com.grace.chucknorris;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonHelper {

    @GET("random")
    Call<ValueModel> getRandomToken();

    @GET("random?")
    Call<ValueModel> getTokenForCategory(@Query("category") String category);

    @GET("categories")
    Call<ValueModel> getAllCategories();

}

