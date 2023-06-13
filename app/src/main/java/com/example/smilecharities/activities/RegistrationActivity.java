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
import com.example.smilecharities.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {
    Button regsignup;
    EditText regname, regemail, regpass;
    TextView  regsignin;
    FirebaseDatabase database;
    FirebaseAuth auth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        regsignup = findViewById(R.id.regsignup);
        regemail = findViewById(R.id.regemail);
        regname = findViewById(R.id.regname);
        regpass = findViewById(R.id.regpass);
        regsignin = findViewById(R.id.regsignin);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        auth = FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        regsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });
        regsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
                progressBar.setVisibility(View.VISIBLE);

            }
        });
    }

    private void createUser() {
        String username = regname.getText().toString();
        String useremail = regemail.getText().toString();
        String userpassword = regpass.getText().toString();

        if (TextUtils.isEmpty(username)){
            Toast.makeText(this,"Name is Empty",Toast.LENGTH_SHORT).show();
            return;
        }
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
        auth.createUserWithEmailAndPassword(useremail,userpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    UserModel userModel = new UserModel(username,useremail,userpassword);
                    String id = task.getResult().getUser().getUid();
                    database.getReference().child("Admin").child(id).setValue(userModel);
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(RegistrationActivity.this,"Registration Successfull", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(RegistrationActivity.this,"Error:"+task.getException(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}