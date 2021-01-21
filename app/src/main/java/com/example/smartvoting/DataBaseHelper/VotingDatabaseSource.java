package com.example.smartvoting.DataBaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.smartvoting.Model.UserModel;

public class VotingDatabaseSource {


    private VotingDatabaseHelper votingDatabaseHelper;
    private SQLiteDatabase sqLiteDatabase;
    private UserModel userModel;

    public VotingDatabaseSource(Context context) {
        votingDatabaseHelper = new VotingDatabaseHelper(context);

    }


    public  void open(){

        sqLiteDatabase = votingDatabaseHelper.getWritableDatabase();
    }

    public  void close(){
        sqLiteDatabase.close();
    }


    public  boolean createNewUser(UserModel userModel){
        this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(VotingDatabaseHelper.USER_COL_NAME,userModel.getUserName());
        contentValues.put(VotingDatabaseHelper.USER_COL_PHONE,userModel.getUserPhone());
        contentValues.put(VotingDatabaseHelper.USER_COL_NID,userModel.getUserNid());
        long id = sqLiteDatabase.insert(VotingDatabaseHelper.TABLE_USER,null,contentValues);

        if(id > 0){
            return  true;
        }else {
            return  false;
        }

    }

    public  boolean createVotingPosition(UserModel userModel){
        this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(VotingDatabaseHelper.USER_COL_CANDIDATE_POSITION,userModel.getCandidatePosition());
        contentValues.put(VotingDatabaseHelper.USER_COL_CANDIDATE_NAME,userModel.getCandidateName());
        contentValues.put(VotingDatabaseHelper.USER_COL_VOTING_START_TIME,userModel.getVotingStartTime());
        contentValues.put(VotingDatabaseHelper.USER_COL_VOTING_END_TIME,userModel.getVotingEndTime());
        long id = sqLiteDatabase.insert(VotingDatabaseHelper.TABLE_USER,null,contentValues);

        if(id > 0){
            return  true;
        }else {
            return  false;
        }

    }

}
