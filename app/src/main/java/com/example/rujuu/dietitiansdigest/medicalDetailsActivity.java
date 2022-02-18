package com.example.rujuu.dietitiansdigest;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class medicalDetailsActivity extends AppCompatActivity {

EditText height,weight;
TextView bmi;
TextView bmi_desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_details);
        height=findViewById(R.id.height_et);
        weight=findViewById(R.id.weight_et);
        bmi=findViewById(R.id.bmi_tv);
        bmi_desc=findViewById(R.id.bmi_desc);
        Button btn = findViewById(R.id.calc_btn);
        Spinner spinner_medcon = findViewById(R.id.spinner_medcon);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.medcon_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_medcon.setAdapter(adapter);
        class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
            }

            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        }


        btn.setOnClickListener( new View.OnClickListener() {

            View v;
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String h,w,b;
                h=height.getText().toString();
                double fh=Double.parseDouble(h);
                w=weight.getText().toString();
                double fw=Double.parseDouble(w);
                int fb= (int) (fw/(fh*fh));
                b=String.valueOf(fb);
                bmi.setText(b);
                if(fb<=18){
                    bmi_desc.setText("Underweight");
                    bmi_desc.setTextColor(Color.parseColor("#fff200"));
                }
                else if(fb>18&&fb<25){
                    bmi_desc.setText("Normal");
                    bmi_desc.setTextColor(Color.parseColor("#39b54a"));
                }
                else if(fb>=25&&fb<30){
                    bmi_desc.setText("Obese");
                    bmi_desc.setTextColor(Color.parseColor("#f26522"));
                }
                else{
                    bmi_desc.setText("Morbidly Obese");
                    bmi_desc.setTextColor(Color.parseColor("#ff0000"));
                }

            }
        });

    }



}
