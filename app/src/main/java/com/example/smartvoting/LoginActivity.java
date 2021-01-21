package com.example.smartvoting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartvoting.Model.UserModel;

import java.util.concurrent.Executor;

public class LoginActivity extends AppCompatActivity {


    private EditText userLoginNameET, userLoginPhoneET;

    private Button  userLoginBtn;
    private String adminName = "Abir";
    private String adminPhone = "01679409720";

    private String userName,userPhone;



    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        userLoginNameET = findViewById(R.id.userLoginNameET);
        userLoginPhoneET = findViewById(R.id.userLoginPhoneET);
        userLoginBtn = findViewById(R.id.userLoginBtn);







        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(LoginActivity.this,
                executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);



                if(userName.equals(adminName) && userPhone.equals(adminPhone)){
                    Toast.makeText(LoginActivity.this, "You loggedin as Admin", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,AdminDashboardActivity.class));
                }else{
                    Toast.makeText(LoginActivity.this, "You loggedin successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,VoterDashBoardActivity.class));
                    Toast.makeText(LoginActivity.this, "User: "+userName, Toast.LENGTH_SHORT).show();
                    Toast.makeText(LoginActivity.this, "User: "+userPhone, Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });



        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Register")
                .setSubtitle("Please Use Your FingerPrint for login")
                .setNegativeButtonText("Cancel")
                .build();

        userLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = userLoginNameET.getText().toString();
                userPhone = userLoginPhoneET.getText().toString();
                if(userName.isEmpty()){
                    userLoginNameET.setError("Field must not be empty");
                }else if(userPhone.isEmpty()){
                    userLoginPhoneET.setError("Field must not be empty");
                }
                else{
                    biometricPrompt.authenticate(promptInfo);
                }

            }
        });



    }



}