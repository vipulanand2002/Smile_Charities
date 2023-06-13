package com.example.smilecharities.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smilecharities.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    Button logsignin;
    EditText  regemail, regpass;
    TextView logsignup;
    FirebaseAuth auth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        regemail = findViewById(R.id.regemail);
        regpass = findViewById(R.id.regpass);
        logsignup = findViewById(R.id.logsignup);
        logsignin = findViewById(R.id.logsignin);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

        auth = FirebaseAuth.getInstance();
        logsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });
        logsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    private void loginUser() {
        String useremail = regemail.getText().toString();
        String userpassword = regpass.getText().toString();


        if (TextUtils.isEmpty(useremail)){
            Toast.makeText(this,"E-Mail is Empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userpassword)){
            Toast.makeText(this,"Password is Empty",Toast.LENGTH_SHORT).show();
            return;
        }

        if(userpassword.length()<6){
            Toast.makeText(this,"Password length must be greater than 6!!",Toast.LENGTH_SHORT).show();
            return;
        }

        //LoginUser
        auth.signInWithEmailAndPassword(useremail, userpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                }
                else{
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this,"Error"+task.isSuccessful(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}