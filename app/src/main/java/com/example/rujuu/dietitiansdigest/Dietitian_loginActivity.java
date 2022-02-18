package com.example.rujuu.dietitiansdigest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Dietitian_loginActivity extends AppCompatActivity {

    EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dietitian_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
    }
    public void redirect(View v){
        Intent intent=new Intent(Dietitian_loginActivity.this,Dietician_reg.class);
        startActivity(intent);

    }

    public void Loginclick(View view) {

        new CallUrl().execute();

    }

    private class CallUrl extends AsyncTask<Void,Void,String> {
        String url="http://192.168.43.32:80/Services/register2.php?action=dologin&username="+username.getText().toString()+"&password="+password.getText().toString();
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(Dietitian_loginActivity.this);
            dialog.show();
            //Log.d("onPreExecute:ends", "onPreExecute: ");
            Log.d("url", url);
        }

        @Override
        protected String doInBackground(Void... voids) {
           // Log.d("dinbg start", "doInBackground: ");

            String res="";
            try {

                res = run(url);
                Log.e("url", url );
                return res;
            } catch (IOException e) {
                e.printStackTrace();
            }
           Log.d("dinbg ends", "doInBackground: ");
            return "error";
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();

            if(s.equals("error"))
            {

                Toast.makeText(Dietitian_loginActivity.this,"Please try again.",Toast.LENGTH_SHORT).show();
            }
            else
            {

                if(s.trim().equalsIgnoreCase("valid"))
                {
                    Toast.makeText(Dietitian_loginActivity.this,"Login successfull",Toast.LENGTH_SHORT).show();

                    Intent intent= new Intent(Dietitian_loginActivity.this, dietitian_dashboard.class);

                    startActivity(intent);

                }
                else
                {
                    Toast.makeText(Dietitian_loginActivity.this,"invalid userid or password",Toast.LENGTH_SHORT).show();

                }

            }
        }
    }


    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
