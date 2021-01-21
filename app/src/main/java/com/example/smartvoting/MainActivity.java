package com.example.smartvoting;



import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import java.util.concurrent.Executor;


public class MainActivity extends AppCompatActivity {

    private TextView loginStatus;
    private ImageView imageView;
    private Button loginBtn;

    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginStatus = findViewById(R.id.loginStatus);
        imageView = findViewById(R.id.imageView);
        loginBtn = findViewById(R.id.loginBtn);


        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate()) {
            case BiometricManager.BIOMETRIC_SUCCESS:
                loginStatus.setText("App can authenticate using biometrics");
                imageView.setBackgroundColor(getColor(R.color.colorAccent));
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                loginStatus.setText("No biometric features available on this device.");
                loginStatus.setVisibility(View.VISIBLE);
                imageView.setBackgroundColor(getColor(R.color.colorREd));
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                loginStatus.setText("Biometric features are currently unavailable.");
                loginStatus.setVisibility(View.VISIBLE);
                imageView.setBackgroundColor(getColor(R.color.colorREd));
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                loginStatus.setText("Your Device don't have any FingerPrint Saved");
                loginStatus.setVisibility(View.VISIBLE);
                imageView.setBackgroundColor(getColor(R.color.colorREd));
                break;
        }

        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(MainActivity.this,
                executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(MainActivity.this, "You are login Successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });


        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Login")
                .setSubtitle("Log in using your biometric credential")
                .setNegativeButtonText("Cancel")
                .build();


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                biometricPrompt.authenticate(promptInfo);
            }
        });

    }
}