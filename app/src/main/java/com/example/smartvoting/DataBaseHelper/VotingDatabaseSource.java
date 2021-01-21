package com.example.smartvoting.DataBaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.smartvoting.Model.UserModel;

import java.util.ArrayList;

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
        this.close();

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

}
