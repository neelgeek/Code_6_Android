package com.mab.retrofitpost;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mab.retrofitpost.data.Truck;
import com.mab.retrofitpost.data.remote.APIService;
import com.mab.retrofitpost.data.remote.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "POST" ;
    private TextView mResponseTv;
    private APIService mAPIService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText truckno = (EditText) findViewById(R.id.et_truckno);
        final EditText password = (EditText) findViewById(R.id.et_password);
        Button loginBtn = (Button) findViewById(R.id.btn_login);
        mResponseTv = (TextView) findViewById(R.id.tv_response);

        mAPIService = ApiUtils.getAPIService();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Truckno = truckno.getText().toString().trim();
                String Password = password.getText().toString().trim();
                if(!TextUtils.isEmpty(Truckno) && !TextUtils.isEmpty(Password)) {
                    sendPost(Truckno, Password);
                }
            }
        });
    }

    private void sendPost(String title, String body) {
        mAPIService.post(title, body).enqueue(new Callback<Truck>() {
            @Override
            public void onResponse(Call<Truck> call, Response<Truck> response) {

                if(response.isSuccessful()) {
                    showResponse(response.body().toString());
                    Log.i(TAG, "post submitted to API." + response.body().toString());

                }
            }

            @Override
            public void onFailure(Call<Truck> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }
        });
        
    }
    public void showResponse(String response) {
        if(mResponseTv.getVisibility() == View.GONE) {
            mResponseTv.setVisibility(View.VISIBLE);
        }
        mResponseTv.setText(response);
    }
}
