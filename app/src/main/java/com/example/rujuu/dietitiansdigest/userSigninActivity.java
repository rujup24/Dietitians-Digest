package com.example.rujuu.dietitiansdigest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class userSigninActivity extends AppCompatActivity {
private Button Next;
    EditText name;
    EditText Age;
    EditText contact;
    EditText email;
    EditText pwd;
    EditText dob;
    RadioButton male;
    RadioButton female;
    String gen;



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
        setContentView(R.layout.activity_user_signin);

        name = findViewById(R.id.name_et);
        Next=findViewById(R.id.button_next);
        Age = findViewById(R.id.age_et);
        dob=findViewById(R.id.dob);
        contact = findViewById(R.id.phone_et);
        email = findViewById(R.id.mail_et);
        pwd = findViewById(R.id.pwd_et);
        male = findViewById(R.id.male_rb);
        female = findViewById(R.id.female_rb);


            if(male.isChecked())
            {
                gen="male";
            }
            else
            {
                gen="female";
            }


            Next =(Button)findViewById(R.id.button_next);

       Next.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view){

           new CallUrl().execute();

            }

       });

    }
    public void submit(View view) {


    }
    private class CallUrl extends AsyncTask<Void,Void,String> {

        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(userSigninActivity.this);
            dialog.show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            String res="";
            try {
                res = run("http://192.168.43.32/Services/register2.php?action=doRegister&name="+name.getText().toString()+"&age="+Age.getText().toString()+"&dob="+dob.getText().toString()+"&gender="+gen+"&contact="+contact.getText().toString()+"&email="+email.getText().toString()+"&pass="+pwd.getText().toString());

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

                Toast.makeText(userSigninActivity.this,"Please try again.",Toast.LENGTH_SHORT).show();
            }
            else
            {

                Toast.makeText(userSigninActivity.this,"Registration Successfully",Toast.LENGTH_SHORT).show();

                finish();
             //   startActivity(intent);

            }
        }
    }


    public void sendemail()
    {
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{  "serveroverloadofficial@gmail.com"});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Diet Chart");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, email.getText().toString());
        emailIntent.setType("message/rfc822");

        try {
            startActivity(Intent.createChooser(emailIntent,
                    "Send email using..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(userSigninActivity.this,
                    "No email clients installed.",
                    Toast.LENGTH_SHORT).show();
        }


    }

}
