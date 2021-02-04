package com.example.smartvoting.DataBaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.smartvoting.Model.EventModel;
import com.example.smartvoting.Model.UserModel;

import java.util.ArrayList;

public class VotingDatabaseSource {


    private VotingDatabaseHelper votingDatabaseHelper;
    private SQLiteDatabase sqLiteDatabase;
    private UserModel userModel;
    private EventModel eventModel;

    public VotingDatabaseSource(Context context) {
        votingDatabaseHelper = new VotingDatabaseHelper(context);

    }


    public  void open(){
        sqLiteDatabase = votingDatabaseHelper.getWritableDatabase();
    }

    public  void close(){
        sqLiteDatabase.close();
    }


    //create new users
    public  boolean createNewUser(UserModel userModel){
        this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(VotingDatabaseHelper.USER_COL_NAME,userModel.getUserName());
        contentValues.put(VotingDatabaseHelper.USER_COL_PHONE,userModel.getUserPhone());
        contentValues.put(VotingDatabaseHelper.USER_COL_NID,userModel.getUserNid());
        long id = sqLiteDatabase.insert(VotingDatabaseHelper.TABLE_USER,null,contentValues);
        this.close();

        if(id > 0){
            return  true;
        }else {
            return  false;
        }

    }


    // retrieve all user's information
    public ArrayList<UserModel> getAllUsers(){
        ArrayList<UserModel> userModelArrayList = new ArrayList<>();
        this.open();

        Cursor cursor = sqLiteDatabase.query(VotingDatabaseHelper.TABLE_USER,null,null,
                null, null,null,null );
        cursor.moveToFirst();
        if(cursor != null && cursor.getCount() > 0){

            for(int i = 0; i < cursor.getCount();i++){

                int id = cursor.getInt(cursor.getColumnIndex(VotingDatabaseHelper.USER_COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(VotingDatabaseHelper.USER_COL_NAME));
                String phone = cursor.getString(cursor.getColumnIndex(VotingDatabaseHelper.USER_COL_PHONE));
                String nid = cursor.getString(cursor.getColumnIndex(VotingDatabaseHelper.USER_COL_NID));
                userModel = new UserModel(id,name,phone,nid);
                userModelArrayList.add(userModel);
                cursor.moveToNext();

            }

        }


        cursor.close();
        this.close();
        return userModelArrayList;
    }

    //creating new Voting events
    public  boolean createVotingEvents(EventModel eventModel){
        this.open();
        ContentValues contentValues1 = new ContentValues();
        contentValues1.put(VotingDatabaseHelper.EVENT_COL_CANDIDATE_POSITION,eventModel.getCandidatePosition());
        contentValues1.put(VotingDatabaseHelper.EVENT_COL_CANDIDATE_NAME,eventModel.getCandidateName());
        contentValues1.put(VotingDatabaseHelper.EVENT_COL_CANDIDATE_CODE,eventModel.getVotingCode());
        contentValues1.put(VotingDatabaseHelper.EVENT_COL_VOTING_START_TIME,eventModel.getVotingStartTime());
        contentValues1.put(VotingDatabaseHelper.EVENT_COL_VOTING_END_TIME,eventModel.getVotingEndTime());
        contentValues1.put(VotingDatabaseHelper.EVENT_COL_TOTAL_COUNT_VOTE,eventModel.getCandidate_countedVote());
        long id = sqLiteDatabase.insert(VotingDatabaseHelper.TABLE_EVENT,null,contentValues1);

        this.close();
        if(id > 0){
            return  true;
        }else {
            return  false;
        }

    }





    // retrieve all voting event information
    public ArrayList<EventModel> getAllVotingEvents(){
        ArrayList<EventModel> eventModelList = new ArrayList<>();
        this.open();

        Cursor cursor = sqLiteDatabase.query(VotingDatabaseHelper.TABLE_EVENT,null,null,
                null, null,null,null );
        cursor.moveToFirst();
        if(cursor != null && cursor.getCount() > 0){

            for(int i = 0; i < cursor.getCount();i++){

                int id = cursor.getInt(cursor.getColumnIndex(VotingDatabaseHelper.EVENT_COL_ID));
                String candidatePosition = cursor.getString(cursor.getColumnIndex(VotingDatabaseHelper.EVENT_COL_CANDIDATE_POSITION));
                String candidateName = cursor.getString(cursor.getColumnIndex(VotingDatabaseHelper.EVENT_COL_CANDIDATE_NAME));
                String votingCode = cursor.getString(cursor.getColumnIndex(VotingDatabaseHelper.EVENT_COL_CANDIDATE_CODE));
                String votingStartTime = cursor.getString(cursor.getColumnIndex(VotingDatabaseHelper.EVENT_COL_VOTING_START_TIME));
                String votingEndTime = cursor.getString(cursor.getColumnIndex(VotingDatabaseHelper.EVENT_COL_VOTING_END_TIME));
                eventModel = new EventModel(id,candidatePosition,candidateName,votingCode,votingStartTime,votingEndTime);
                eventModelList.add(eventModel);
                cursor.moveToNext();

            }

        }


        cursor.close();
        this.close();
        return eventModelList;
    }


    // submit vote
    public boolean submitVotes(EventModel eventModel){
        this.open();
        //String WhereClause = VotingDatabaseHelper.EVENT_COL_ID +" = ?";
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put(VotingDatabaseHelper.EVENT_COL_ID,eventModel.getEventId());
        contentValues2.put(VotingDatabaseHelper.EVENT_COL_TOTAL_COUNT_VOTE,eventModel.getCandidate_countedVote());
        int  rowID = sqLiteDatabase.update(VotingDatabaseHelper.TABLE_EVENT,contentValues2, VotingDatabaseHelper.EVENT_COL_ID+"="+eventModel.getEventId(),null);
        this.close();
        if(rowID > 0){
            return true;
        }else{
            return  false;
        }

    }
}
