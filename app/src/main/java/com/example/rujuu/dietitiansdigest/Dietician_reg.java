package com.example.rujuu.dietitiansdigest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Dietician_reg extends AppCompatActivity {


    EditText name;
    EditText  address;
    EditText  qualification;
    EditText  experience;
    EditText  password;
    EditText  email;
    Button btn;


    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dietician_reg);

        name = findViewById(R.id.name);
        address = findViewById(R.id.addr);
        qualification = findViewById(R.id.qualification);
        experience = findViewById(R.id.exp);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        btn = findViewById(R.id.btnreg);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Dietician_reg.CallUrl().execute();

            }

        });
    }

    public void submit(View view) {


    }

    private class CallUrl extends AsyncTask<Void, Void, String> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(Dietician_reg.this);
            dialog.show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            String res = "";
            try {
                res = run("http://192.168.43.32/Services/register2.php?action=dodiereg&name=" + name.getText().toString() + "&address=" + address.getText().toString() + "&qualification=" + qualification.getText().toString() + "&experience=" + experience.getText().toString() + "&email=" + email.getText().toString() + "&password=" + password.getText().toString());

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
            if (s.equals("error")) {

                Toast.makeText(Dietician_reg.this, "Please try again.", Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(Dietician_reg.this, "Registration Successfully", Toast.LENGTH_SHORT).show();

                finish();
                //   startActivity(intent);

            }
        }
    }
}
