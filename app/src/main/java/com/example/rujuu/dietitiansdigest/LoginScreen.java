package com.example.rujuu.dietitiansdigest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginScreen extends AppCompatActivity {

    EditText email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        email = findViewById(R.id.login_id_et);
        password = findViewById(R.id.login_pwd_et);
    }

    public void adduser(View view) {
        startActivity(new Intent(LoginScreen.this,userSigninActivity.class));
    }


    public void redirectlogin(View v){
        Intent intent=new Intent(LoginScreen.this,Dietitian_loginActivity.class);
        startActivity(intent);

    }

    public void Loginclick(View view) {

        new LoginScreen.CallUrl().execute();

    }

    private class CallUrl extends AsyncTask<Void,Void,String> {

        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(LoginScreen.this);
            dialog.show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            String res="";
            try {
                res = run("http://192.168.43.32/Services/register2.php?action=getUsers&email="+email.getText().toString()+"&password="+password.getText().toString());

                return res;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "error";
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();

            if(s.equals("error"))
            {

                Toast.makeText(LoginScreen.this,"Please try again.",Toast.LENGTH_SHORT).show();
            }
            else
            {

                if(s.trim().equalsIgnoreCase("valid"))
                {
                    Toast.makeText(LoginScreen.this,"Login successfull",Toast.LENGTH_SHORT).show();

                    Intent intent= new Intent(LoginScreen.this,Dashboard.class);

                    startActivity(intent);

                }
                else
                {
                    Toast.makeText(LoginScreen.this,"invalid userid or password",Toast.LENGTH_SHORT).show();

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
