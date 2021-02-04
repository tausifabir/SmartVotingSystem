package com.example.smartvoting;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartvoting.DataBaseHelper.VotingDatabaseSource;
import com.example.smartvoting.Model.EventModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateVotingEventActivity extends AppCompatActivity {

    private Button votingStartTimeBtn, votingEndTimeBtn, creatingNewEventBtn;
    private EditText candidatePositionET, candidateNameET, votingCodeET;
    boolean isDateChanged = false;

    private EventModel eventModel;
    private VotingDatabaseSource votingDatabaseSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_voting_event);

        candidatePositionET = findViewById(R.id.candidatePositionET);
        candidateNameET = findViewById(R.id.candidateNameET);
        votingCodeET = findViewById(R.id.votingCodeET);
        votingStartTimeBtn = findViewById(R.id.votingStartTimeBtn);
        votingEndTimeBtn = findViewById(R.id.votingEndTimeBtn);
        creatingNewEventBtn = findViewById(R.id.creatingNewEventBtn);


        votingDatabaseSource = new VotingDatabaseSource(this);

        creatingNewEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String candidatePosition = candidatePositionET.getText().toString();
                String candidateName = candidateNameET.getText().toString();
                String votingCode = votingCodeET.getText().toString();
                String votingStartTime = votingStartTimeBtn.getText().toString();
                String votingEndTime = votingEndTimeBtn.getText().toString();
                String candidateVote = "3";

                eventModel = new EventModel(candidatePosition,candidateName,votingCode,votingStartTime,votingEndTime,candidateVote);
                boolean status = votingDatabaseSource.createVotingEvents(eventModel);
                if(status){
                    Toast.makeText(CreateVotingEventActivity.this, "Event created successfully", Toast.LENGTH_SHORT).show();
                    startActivity( new Intent(CreateVotingEventActivity.this,AdminDashboardActivity.class));
                }else{
                    Toast.makeText(CreateVotingEventActivity.this, "Event failed to create", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }





    public void createVotingStartTime(View view) {

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog =
                new DatePickerDialog(this, listner, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-10000);// disable to take previous dates
        datePickerDialog.show();
    }

    public void createVotingEndTime(View view) {

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog =
                new DatePickerDialog(this, listner1, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-10000);// disable to take previous dates
        datePickerDialog.show();
    }


    // listner for startTime
    private DatePickerDialog.OnDateSetListener listner = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            final  Calendar calendar1 = Calendar.getInstance();
            calendar1.set(dayOfMonth,month,year);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yy");
            String selecteddate = simpleDateFormat.format(calendar1.getTime());

            votingStartTimeBtn.setText(dayOfMonth+"/"+(month+1)+"/"+year);




        }
    };


    //listner for endTime
    private DatePickerDialog.OnDateSetListener listner1 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            final  Calendar calendar1 = Calendar.getInstance();
            calendar1.set(dayOfMonth,month,year);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yy");
            String selecteddate = simpleDateFormat.format(calendar1.getTime());

            votingEndTimeBtn.setText(dayOfMonth+"/"+(month+1)+"/"+year);



        }
    };


    public DatePicker.OnDateChangedListener dateSetListener = new DatePicker.OnDateChangedListener() {
        public void onDateChanged(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
            isDateChanged = true;
        }
    };
}