package com.example.smartvoting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartvoting.DataBaseHelper.VotingDatabaseSource;
import com.example.smartvoting.Model.EventModel;

import java.util.concurrent.Executor;

public class SubmitVoteActivity extends AppCompatActivity {

    private TextView voterCandidatePostion, voterCandidateName,votercandidateCode;
    private Button voterProceedBtn, voterCancelBtn;


    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    private EventModel eventModel;
    private VotingDatabaseSource votingDatabaseSource;


    private int eventID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_vote);

        voterCandidatePostion = findViewById(R.id.vote_candidatePosition);
        voterCandidateName = findViewById(R.id.vote_candidateName);
        votercandidateCode = findViewById(R.id.vote_candidateCode);
        voterProceedBtn = findViewById(R.id.voterProceedBtn);
        voterCancelBtn = findViewById(R.id.voterCancelBtn);

        Intent intent = getIntent();

        votingDatabaseSource = new VotingDatabaseSource(this);

        final String position = intent.getStringExtra("Positoin");
        String name = intent.getStringExtra("name");
        String code = intent.getStringExtra("votingCode");
        eventID = intent.getIntExtra("eventID",0);

        voterCandidatePostion.setText(position);
        voterCandidateName.setText(name);
        votercandidateCode.setText(code);



        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate()) {
            case BiometricManager.BIOMETRIC_SUCCESS:
                Toast.makeText(this, "App can authenticate using biometrics", Toast.LENGTH_SHORT).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Toast.makeText(this, "No biometric features available on this device.", Toast.LENGTH_SHORT).show();

                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Toast.makeText(this, "Biometric features are currently unavailable.", Toast.LENGTH_SHORT).show();

                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Toast.makeText(this, "Your Device don't have any FingerPrint Saved", Toast.LENGTH_SHORT).show();

                break;
        }



        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(SubmitVoteActivity.this,
                executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);

                try{
                    String candidate_countedVote = "100";
                   /* String pos = "2345";
                    String candidateName = "ABIRRRR";
                    String votingCode = "CODE";
                    String votingStartTime = "24.4.2020";
                    String votingEndTime = "24.4.2020";*/
                   
                    if(eventID > 0){
                       // eventModel = new EventModel(eventID,pos,candidateName,votingCode,votingStartTime,votingEndTime,candidate_countedVote);
                        eventModel = new EventModel(eventID,candidate_countedVote);
                        boolean status = votingDatabaseSource.submitVotes(eventModel);
                        if(status){
                            Toast.makeText(SubmitVoteActivity.this, "Vote submitted", Toast.LENGTH_SHORT).show();
                            startActivity( new Intent(SubmitVoteActivity.this,ViewAllVotingEventsActivity.class));
                        }else{
                            Toast.makeText(SubmitVoteActivity.this, "Vote submission Failed", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(SubmitVoteActivity.this, "No Row", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Log.d("SqliteExceptoin", "Exception:  "+e);
                }



            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });



        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Submit Vote")
                .setSubtitle("Use your Biometrics for Confirm your vote")
                .setNegativeButtonText("Cancel")
                .build();



    }


    public void onSubmitVote(View view) {
        biometricPrompt.authenticate(promptInfo);

    }

    public void onCancelVote(View view) {
        startActivity(new Intent(SubmitVoteActivity.this,ViewAllVotingEventsActivity.class));
    }
}