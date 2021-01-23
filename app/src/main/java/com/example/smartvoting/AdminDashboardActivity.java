package com.example.smartvoting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminDashboardActivity extends AppCompatActivity {

    private Button createEventsBtn, viewEventsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        viewEventsBtn = findViewById(R.id.viewEventsBtn);
        createEventsBtn = findViewById(R.id.createEventsBtn);
    }

    public void onCreateVotingEvent(View view) {
        startActivity(new Intent(AdminDashboardActivity.this,CreateVotingEventActivity.class));
    }

    public void onViewVotingEvents(View view) {
        startActivity(new Intent(AdminDashboardActivity.this,ViewAllVotingEventsActivity.class));

    }
}