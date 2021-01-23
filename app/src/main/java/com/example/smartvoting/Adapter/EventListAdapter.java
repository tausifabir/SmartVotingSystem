package com.example.smartvoting.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartvoting.Model.EventModel;
import com.example.smartvoting.Model.UserModel;
import com.example.smartvoting.R;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.candidatePosition.setText(votingEventList.get(position).getCandidatePosition());
        holder.candidateName.setText(votingEventList.get(position).getCandidateName());
        holder.votingStartTime.setText(votingEventList.get(position).getVotingStartTime());
        holder.votingEndTime.setText(votingEventList.get(position).getVotingEndTime());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView candidatePosition,candidateName,votingStartTime, votingEndTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            candidatePosition = itemView.findViewById(R.id.candidatePosition);
            candidateName = itemView.findViewById(R.id.candidateName);
            votingStartTime = itemView.findViewById(R.id.votingStartTime);
            votingEndTime = itemView.findViewById(R.id.votingEndTime);

        }
    }




}

