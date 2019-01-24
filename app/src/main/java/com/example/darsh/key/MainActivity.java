package com.example.darsh.key;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private EditText username,password;
    private Button login;
    public String url="https://clientapi.7dayexch.com/API/AuthenticatedUser";
    ProgressDialog pDialog;
    JSONObject jo = new JSONObject();
    private static final String TAG = "Main";
    String Data;
    Intent intent;
    boolean sec=false;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private String responseData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username =(EditText)findViewById(R.id.EditTextUsername);
        password =(EditText)findViewById(R.id.EditTextPassword);
        login =(Button)findViewById(R.id.buttonlogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    login();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });



    }

    private void login() throws JSONException {

        boolean isValid = true;


        String user=username.getText().toString();
        String pass=password.getText().toString();
        String userstring="hihello";


        if (isValid) {


            jo.put("strUserName",user);
            jo.put("strPassword",pass);
            jo.put("strData",userstring);



            new Loginingup().execute();

           // Toast.makeText(MainActivity.this,"all ok", Toast.LENGTH_SHORT).show();
        }
    }
    private class Loginingup extends AsyncTask<Void, Void, Void> {



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
            intent=new Intent(MainActivity.this,StoreInFile.class);


        }

        @Override
        protected Void doInBackground(Void... arg0) {
            /**This is using okHttp **/
            OkHttpClient client = new OkHttpClient();
            Log.d(TAG, "request: "+jo);

            RequestBody body = RequestBody.create(JSON, String.valueOf(jo));

            Request request = new Request.Builder()
                    .url(url).post(body)
                    .build();

            Log.d(TAG, "body"+body);
            Log.d(TAG, "request "+request);
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d(TAG, "IOException"+e);
                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {


                    Log.e(TAG, "onResponse: "+response.toString() );

                    responseData = response.body().string();
                    JSONObject success= null;
                    try {


                        success = new JSONObject(responseData);

                        sec=success.getBoolean("success");
                        Data = success.getString("Data");
                        intent.putExtra("Data",Data);

                        Log.d(TAG, "onResponse: "+sec+Data);



                    //JSONArray Data = success.getJSONArray("Data");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            });


            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog


            if (pDialog.isShowing()) {
                pDialog.dismiss();
            if(sec==true) {
                Toast.makeText(MainActivity.this, "Successfully login", Toast.LENGTH_SHORT).show();

                startActivity(intent);
            }

            }


        }

    }

}
