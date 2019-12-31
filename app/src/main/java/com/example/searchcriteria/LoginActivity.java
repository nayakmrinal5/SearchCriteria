package com.example.searchcriteria;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;
//import com.example.jsonobjectrequestsample.NewActivity;
//import com.example.jsonobjectrequestsample.MyApplication;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.loopj.android.http.AsyncHttpClient;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;


import org.json.JSONException;
import org.json.JSONObject;

import static com.loopj.android.http.AsyncHttpClient.log;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Gson gson = new Gson();
        Button login_button = findViewById(R.id.btn_login);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://www.greenwaveps.com/bi.api/api/v1/Auth/GetToken";
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();

                params.put("login", "mobile");
                params.put("password", "mobilE@123");

                client.post(url, params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        // Root JSON in response is an dictionary i.e { "data : [ ... ] }

                        log.d("success","Response available");


                            try {
                                // Parsing json object response
                                //boolean error = response.getBoolean("Error");
                                String messager = response.getString("token");
                               String message = gson.toJson(response);
                       //         String token = response.getString("");

                                log.i("response",message);
                                //if (!error) {
// parsing the user profile information
                                //JSONArray resp = response.getJSONArray("");

//do what ever you want to do with your response

                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                                 //else {
                                    //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();


                            } catch (JSONException e) {
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            }

                        }
                        // Handle resulting parsed JSON response here


                    @Override
                    public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                        log.e("error", String.valueOf(t));
                    }
                });

            }
        });
    }
}