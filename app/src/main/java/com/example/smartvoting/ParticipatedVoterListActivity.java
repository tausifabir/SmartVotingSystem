package com.example.smartvoting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.smartvoting.Adapter.VoterListAdapter;
import com.example.smartvoting.DataBaseHelper.VotingDatabaseSource;
import com.example.smartvoting.Model.UserModel;

import java.util.List;

public class ParticipatedVoterListActivity extends AppCompatActivity {

    private RecyclerView voterListRV;

    private VotingDatabaseSource votingDatabaseSource;
    private VoterListAdapter voterListAdapter;
    private List<UserModel> userModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participated_voter_list);

        voterListRV = findViewById(R.id.voterListRV);

        votingDatabaseSource = new VotingDatabaseSource(this);
        userModelList = votingDatabaseSource.getAllUsers();
        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(ParticipatedVoterListActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        voterListRV.setLayoutManager(linearLayoutManager);
        voterListAdapter = new VoterListAdapter(ParticipatedVoterListActivity.this,userModelList);
        voterListRV.setAdapter(voterListAdapter);
    }
}