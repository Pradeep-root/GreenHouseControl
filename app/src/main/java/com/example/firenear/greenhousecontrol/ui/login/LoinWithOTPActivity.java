package com.example.firenear.greenhousecontrol.ui.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.firenear.greenhousecontrol.R;
import com.example.firenear.greenhousecontrol.ui.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class LoinWithOTPActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button btnLogin ;
    private EditText editOtpCode;
    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loin_with_otp);

        mAuth = FirebaseAuth.getInstance();
        editOtpCode = (EditText) findViewById(R.id.edit_otp_number);
        btnLogin = (Button) findViewById(R.id.btn_login);

        Intent intent = getIntent();
        if(intent != null){
            verificationId = intent.getStringExtra("verificationId");
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = editOtpCode.getText().toString();
                if (TextUtils.isEmpty(code)) {
                    editOtpCode.setError("Cannot be empty.");
                    return;
                }
                verifyPhoneNumberWithCode(verificationId, code);
            }
        });
    }

    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuthCredential(credential);
    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                          //  Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = task.getResult().getUser();
                           Intent intent = new Intent(LoinWithOTPActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent.putExtra("phone", user.getPhoneNumber()));
                            finish();
                        } else {
                           // Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                editOtpCode.setError("Invalid code.");
                            }
                        }
                    }
                });
    }
}
