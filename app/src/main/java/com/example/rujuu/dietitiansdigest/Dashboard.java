package com.example.rujuu.dietitiansdigest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Dashboard extends AppCompatActivity {
    CardView MdCard;
    CardView mdietitian;
    CardView prog_rep;
    CardView cal_count;
    CardView complain;
    CardView abt_us;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        MdCard = (CardView) findViewById(R.id.mps);
        mdietitian=findViewById(R.id.dietitian);
        prog_rep=findViewById(R.id.progress);
        cal_count=findViewById(R.id.calorie);
        complain=findViewById(R.id.complain);
        abt_us=findViewById(R.id.about_us);
        MdCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,medicalDetailsActivity.class);
                startActivity(intent);
            }
        });
        mdietitian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,Dietician_profile.class);
                startActivity(intent);
            }
        });
        cal_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,Calorie_counter.class);
                startActivity(intent);
            }
        });

    }


}
