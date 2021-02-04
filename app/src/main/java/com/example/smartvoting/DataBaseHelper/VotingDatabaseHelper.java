package com.example.smartvoting.DataBaseHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;



public class VotingDatabaseHelper extends SQLiteOpenHelper {




    public static final String DATABASE_NAME = "SmartVoting Database";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_USER = "tbl_user_details";
    public static final String USER_COL_ID = "tbl_user_id";
    public static final String USER_COL_NAME = "tbl_user_name";
    public static final String USER_COL_PHONE = "tbl_user_phone";
    public static final String USER_COL_NID = "tbl_user_nid";
    public static final String USER_COL_CANDIDATE_POSITION = "tbl_voting_can_position";
    public static final String USER_COL_CANDIDATE_NAME = "tbl_voting_can_name";
    public static final String USER_COL_VOTING_START_TIME = "tbl_voting_start_time";
    public static final String USER_COL_VOTING_END_TIME = "tbl_voting_end_time";
    public static final String USER_COL_TOTAL_COUNT_VOTE = "tbl_voting_count_vote";
    public static final String USER_COL_VOTING_Result = "tbl_voting_result";

    public static final String TABLE_EVENT = "tbl_voting_details";
    public static final String USER_COL_USER_ID = "tbl_user_id";
    public static final String EVENT_COL_ID = "tbl_voting_id";
    public static final String EVENT_COL_CANDIDATE_POSITION = "tbl_voting_can_position";
    public static final String EVENT_COL_CANDIDATE_NAME = "tbl_voting_can_name";
    public static final String EVENT_COL_CANDIDATE_CODE = "tbl_voting_code";
    public static final String EVENT_COL_VOTING_START_TIME = "tbl_voting_start_time";
    public static final String EVENT_COL_VOTING_END_TIME = "tbl_voting_end_time";
    public static final String EVENT_COL_TOTAL_COUNT_VOTE = "tbl_voting_count_vote";
    public static final String EVENT_COL_VOTING_Result = "tbl_voting_result";



    // creating user table
    public static final String CREATE_TABLE_USER =
            "CREATE TABLE "+TABLE_USER+"("+
                    USER_COL_USER_ID+" INTEGER PRIMARY KEY, "+
                    USER_COL_NAME+" TEXT,"+
                    USER_COL_PHONE+" TEXT,"+
                    USER_COL_NID+" TEXT,"+
                    USER_COL_CANDIDATE_POSITION+" TEXT,"+
                    USER_COL_CANDIDATE_NAME+" TEXT,"+
                    USER_COL_VOTING_START_TIME+" TEXT,"+
                    USER_COL_VOTING_END_TIME+" TEXT,"+
                    USER_COL_TOTAL_COUNT_VOTE+" TEXT,"+
                    USER_COL_VOTING_Result+" TEXT)";

    //creating voting event table
    public static final String CREATE_TABLE_VOTING =
            "CREATE TABLE "+TABLE_EVENT+"("+
                    EVENT_COL_ID+" INTEGER PRIMARY KEY , "+
                    //USER_COL_USER_ID+" INTEGER ,"+
                    EVENT_COL_CANDIDATE_POSITION+" TEXT,"+
                    EVENT_COL_CANDIDATE_NAME+" TEXT,"+
                    EVENT_COL_CANDIDATE_CODE+" TEXT,"+
                    EVENT_COL_VOTING_START_TIME+" TEXT,"+
                    EVENT_COL_VOTING_END_TIME+" TEXT,"+
                    EVENT_COL_TOTAL_COUNT_VOTE+" INTEGER)";//+
                   //
                    //EVENT_COL_VOTING_Result+"TEXT)";
                   // + "FOREIGN KEY ("+USER_COL_USER_ID+") REFERENCES "+TABLE_USER+"("+USER_COL_USER_ID+"));";










    public VotingDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_USER);
        sqLiteDatabase.execSQL(CREATE_TABLE_VOTING);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
