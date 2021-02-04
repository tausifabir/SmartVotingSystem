package com.example.smartvoting.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartvoting.Model.EventModel;
import com.example.smartvoting.R;
import com.example.smartvoting.SubmitVoteActivity;

import java.util.List;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.ViewHolder> {

    private Context context;
    private List<EventModel>votingEventList;


    public EventListAdapter(Context context, List<EventModel> votingEventList) {
        this.context = context;
        this.votingEventList = votingEventList;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_events,parent,false);

        return new EventListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.candidatePosition.setText(votingEventList.get(position).getCandidatePosition());
        holder.candidateName.setText(votingEventList.get(position).getCandidateName());
        holder.votingCode.setText(votingEventList.get(position).getVotingCode());
        holder.votingStartTime.setText(votingEventList.get(position).getVotingStartTime());
        holder.votingEndTime.setText(votingEventList.get(position).getVotingEndTime());
        holder.candidate_countVote.setText(String.valueOf(votingEventList.get(position).getCandidate_countedVote()));


        // storing Candidate & send to submit vote
        final int eventID = votingEventList.get(position).getEventId();
        final String eventPosition = votingEventList.get(position).getCandidatePosition();
        final String name = votingEventList.get(position).getCandidateName();
        final String votingCode = votingEventList.get(position).getVotingCode();
        final String startTime = votingEventList.get(position).getVotingStartTime();
        final String endTime = votingEventList.get(position).getVotingEndTime();
        final int candidate_countVote = votingEventList.get(position).getCandidate_countedVote();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, SubmitVoteActivity.class)
                        .putExtra("eventID",eventID)
                        .putExtra("Positoin",eventPosition)
                        .putExtra("name",name)
                        .putExtra("votingCode",votingCode)
                        .putExtra("startTime",startTime)
                        .putExtra("endTime",endTime)
                        .putExtra("vote",candidate_countVote));

            }
        });


        Toast.makeText(context, "RowID: " +votingEventList.get(position).getEventId()+ " Counted Vote: "+votingEventList.get(position).getCandidate_countedVote(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public int getItemCount() {
        return votingEventList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView candidatePosition,candidateName,votingCode,votingStartTime, votingEndTime,candidate_countVote;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            candidatePosition = itemView.findViewById(R.id.row_candidatePosition);
            candidateName = itemView.findViewById(R.id.row_candidateName);
            votingCode = itemView.findViewById(R.id.row_candidateCode);
            votingStartTime = itemView.findViewById(R.id.row_votingStartTime);
            votingEndTime = itemView.findViewById(R.id.row_votingEndTime);
            candidate_countVote = itemView.findViewById(R.id.row_totalVoteCount);

        }
    }




}

