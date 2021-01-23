package com.example.smartvoting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.smartvoting.Adapter.EventListAdapter;
import com.example.smartvoting.Adapter.VoterListAdapter;
import com.example.smartvoting.DataBaseHelper.VotingDatabaseSource;
import com.example.smartvoting.Model.UserModel;

import java.util.List;

public class ViewAllVotingEventsActivity extends AppCompatActivity {

    private RecyclerView eventListRV;

    private VotingDatabaseSource votingDatabaseSource;
    private EventListAdapter eventListAdapter;
    private List<UserModel> votingEventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_voting_events);

        eventListRV = findViewById(R.id.eventListRV);

        votingDatabaseSource = new VotingDatabaseSource(this);
        votingEventList = votingDatabaseSource.getAllVotingEvents();
        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(ViewAllVotingEventsActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        eventListRV.setLayoutManager(linearLayoutManager);
        eventListAdapter = new EventListAdapter(ViewAllVotingEventsActivity.this,votingEventList);
        eventListRV.setAdapter(eventListAdapter);
    }
}