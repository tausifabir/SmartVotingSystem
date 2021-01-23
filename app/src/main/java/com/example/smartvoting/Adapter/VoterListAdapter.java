package com.example.smartvoting.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartvoting.Model.UserModel;
import com.example.smartvoting.R;

import java.util.List;

public class VoterListAdapter extends RecyclerView.Adapter<VoterListAdapter.ViewHolder>   {


    private Context context;
    private List<UserModel> userModelList;


    public VoterListAdapter(Context context, List<UserModel> userModelList) {
        this.context = context;
        this.userModelList = userModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_voter,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.voterName.setText(userModelList.get(position).getUserName());
        holder.voterPhone.setText(userModelList.get(position).getUserPhone());
        holder.voterNid.setText(userModelList.get(position).getUserNid());



    }

    @Override
    public int getItemCount() {
        return userModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView voterName,voterPhone,voterNid, voterStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            voterName = itemView.findViewById(R.id.voterName);
            voterPhone = itemView.findViewById(R.id.voterPhone);
            voterNid = itemView.findViewById(R.id.voterNid);
            voterStatus = itemView.findViewById(R.id.voterStatus);

        }
    }

}
