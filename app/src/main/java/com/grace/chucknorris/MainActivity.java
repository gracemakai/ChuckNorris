package com.grace.chucknorris;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button newButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        newButton = findViewById(R.id.newButton);

        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });
    }

    private void update() {

        Call<ValueModel> authModelCall = genToken().getToken();

        authModelCall.enqueue(new Callback<ValueModel>() {
            @Override
            public void onResponse(Call<ValueModel> call, Response<ValueModel> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Something is not right. Try again" + response.code(), Toast.LENGTH_SHORT).show();
                }

                ValueModel authModelList = response.body();
                textView.setText(authModelList.getValue());
            }

            @Override
            public void onFailure(Call<ValueModel> call, Throwable t) {

            }
        });
    }

    private JsonHelper genToken() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.chucknorris.io/jokes/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();

        return retrofit.create(JsonHelper.class);
    }
}