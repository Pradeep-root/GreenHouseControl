package com.example.firenear.greenhousecontrol.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firenear.greenhousecontrol.R;
import com.example.firenear.greenhousecontrol.ui.MainActivity;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class RegisterActivity extends AppCompatActivity {

    private EditText editRegisterNumber;
    private Button btnRegister;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        SharedPreferences sharedPreferences = getSharedPreferences("LoginPersi",MODE_PRIVATE);
        if(sharedPreferences.getBoolean("LoginStatus", false)){
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent.putExtra("phone", sharedPreferences.getString("Number", "")));
            finish();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Register");
        setCallbacks();
        btnRegister = (Button) findViewById(R.id.btn_request_otp);
        editRegisterNumber = (EditText) findViewById(R.id.edit_register_number);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String phoneNumber = editRegisterNumber.getText().toString();
                if(phoneNumber.length() == 10){
                    makeRequest(phoneNumber);
                }else {
                   Toast.makeText(RegisterActivity.this, "please enter correct number", Toast.LENGTH_SHORT);
                }
            }
        });

    }

    private void makeRequest(String phoneNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91"+phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);
    }

    public void setCallbacks(){
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d(RegisterActivity.this.getPackageName(), "onVerificationCompleted:" + credential);


            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w(RegisterActivity.this.getPackageName(), "onVerificationFailed", e);

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    // ...
                    Toast.makeText(RegisterActivity.this, "Invalid request", Toast.LENGTH_SHORT).show();
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    // ...
                    Toast.makeText(RegisterActivity.this, "The SMS quota for the project has been exceeded", Toast.LENGTH_SHORT).show();
                }

                // Show a message and update the UI
                // ...
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Toast.makeText(RegisterActivity.this, "The SMS verification code has been sent to the provided phone number", Toast.LENGTH_SHORT).show();
                Log.d(RegisterActivity.this.getPackageName(), "onCodeSent:" + verificationId);

                Intent intent = new Intent(RegisterActivity.this,LoinWithOTPActivity.class);
                intent.putExtra("verificationId", verificationId);
                startActivity(intent);
            }
        };
    }

}
