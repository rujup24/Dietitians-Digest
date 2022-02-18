package com.example.rujuu.dietitiansdigest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class dietitian_dashboard extends AppCompatActivity {
    CardView MdCard;
    CardView mdietitian;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dietitian_dashboard);
        MdCard = (CardView) findViewById(R.id.mps);
        mdietitian=findViewById(R.id.dietitian);
        MdCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dietitian_dashboard.this,diet_chart.class);
                startActivity(intent);
            }
        });
        mdietitian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dietitian_dashboard.this,Patient_profile.class);
                startActivity(intent);
            }
        });
    }
}

