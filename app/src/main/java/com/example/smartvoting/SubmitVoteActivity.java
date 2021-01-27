package com.example.smartvoting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartvoting.Model.EventModel;
import com.example.smartvoting.Model.UserModel;

import java.util.concurrent.Executor;

public class SubmitVoteActivity extends AppCompatActivity {

    private TextView voterCandidatePostion, voterCandidateName,votercandidateCode;
    private Button voterProceedBtn, voterCancelBtn;


    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    EventModel eventModel;

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

        String position = intent.getStringExtra("Positoin");
        String name = intent.getStringExtra("name");
        String code = intent.getStringExtra("votingCode");

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

               /* eventModel = new EventModel(userName,userPhone,userNid);
                boolean status = votingDatabaseSource.createNewUser(eventModel);
                if(status){
                    Toast.makeText(SubmitVoteActivity.this, "You registered successfully", Toast.LENGTH_SHORT).show();
                    startActivity( new Intent(SubmitVoteActivity.this,ParticipatedVoterListActivity.class));
                }else{
                    Toast.makeText(SubmitVoteActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }*/
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