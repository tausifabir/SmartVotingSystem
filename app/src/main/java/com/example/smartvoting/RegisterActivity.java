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
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartvoting.DataBaseHelper.VotingDatabaseSource;
import com.example.smartvoting.Model.UserModel;

import java.util.concurrent.Executor;

public class RegisterActivity extends AppCompatActivity {

    private EditText userNameET,userPhoneET,userNidET;
    private Button registerBtn,loginBtn;


    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    private String userName,userPhone,userNid;

    private UserModel userModel;

    private VotingDatabaseSource votingDatabaseSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        userNameET = findViewById(R.id.userLoginNameET);
        userPhoneET = findViewById(R.id.userLoginPhoneET);
        userNidET = findViewById(R.id.userNidET);
        registerBtn = findViewById(R.id.registerBtn);
        loginBtn = findViewById(R.id.loginBtn);


        votingDatabaseSource = new VotingDatabaseSource(this);

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
        biometricPrompt = new BiometricPrompt(RegisterActivity.this,
                executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);

                userModel = new UserModel(userName,userPhone,userNid);
                boolean status = votingDatabaseSource.createNewUser(userModel);
                if(status){
                    Toast.makeText(RegisterActivity.this, "You registered successfully", Toast.LENGTH_SHORT).show();
                    startActivity( new Intent(RegisterActivity.this,ParticipatedVoterListActivity.class));
                }else{
                    Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });


        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Register")
                .setSubtitle("Please Use Your FingerPrint for registration")
                .setNegativeButtonText("Cancel")
                .build();





        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = userNameET.getText().toString();
                userPhone = userPhoneET.getText().toString();
                userNid = userNidET.getText().toString();
                if(userName.isEmpty()){
                    userNameET.setError("Field must not be empty");
                }else if(userPhone.isEmpty()){
                    userPhoneET.setError("Field must not be empty");
                }else if(userNid.isEmpty()){
                    userNidET.setError("Field must not be empty");
                }else if(userNid.length()<6 ){
                    userNidET.setError("Nid must be 6 characters");
                } else{
                    biometricPrompt.authenticate(promptInfo);
                }
            }
        });


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });

    }

}