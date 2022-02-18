package com.example.rujuu.dietitiansdigest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class Sendchart extends AppCompatActivity {

ListView listView;

ArrayList<String> names = new ArrayList<>();
ArrayList<String> emails = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendchart);
        listView = findViewById(R.id.listview);

    }

//    public void adduser(View view) {
//        startActivity(new Intent(Sendchart.this,userSigninActivity.class));
//    }

    @Override
    protected void onResume() {
        super.onResume();

            new CallUrl().execute();
    }

    private class CallUrl extends AsyncTask<Void,Void,String> {

        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(Sendchart.this);
            dialog.show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            String res="";
            try {
                res = run("http://192.168.43.32/Services/register2.php?action=getUsers");

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

                Toast.makeText(Sendchart.this,"Please try again.",Toast.LENGTH_SHORT).show();
            }
            else
            {
                names.clear();
                emails.clear();

                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray array = jsonObject.getJSONArray("data");
                    for (int i = 0; i < array.length() ; i++) {
                        JSONObject object = array.getJSONObject(i);
                        String name = object.getString("name");
                        String email = object.getString("email");
                        names.add(name);
                        emails.add(email);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                listView.setAdapter( new ArrayAdapter<String>(Sendchart.this,android.R.layout.simple_list_item_1,names));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        sendemail(emails.get(i));
                    }
                });
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

    public void sendemail(String to)
    {
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{  to});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Diet Chart");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "");
        emailIntent.setType("message/rfc822");

        try {
            startActivity(Intent.createChooser(emailIntent,
                    "Send email using..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Sendchart.this,
                    "No email clients installed.",
                    Toast.LENGTH_SHORT).show();
        }

}
}