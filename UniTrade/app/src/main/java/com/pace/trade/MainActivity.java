package com.pace.trade;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButtonRegister;
    private EditText mEditEmailText;
    private EditText mEditPasswordText;
    private TextView mTextSignIn;


    private FirebaseAuth mFirebaseAuth = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FirebaseApp.initializeApp(this);
        mFirebaseAuth = FirebaseAuth.getInstance();
        if(mFirebaseAuth.getCurrentUser()!=null){

            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));

        }

        mButtonRegister =(Button) findViewById(R.id.registerButton);
        mEditEmailText = (EditText) findViewById(R.id.emailEditText);
        mEditPasswordText = (EditText) findViewById(R.id.passwordEditText);
        mTextSignIn =(TextView) findViewById(R.id.signInTextView);


        mButtonRegister.setOnClickListener(this);
        mTextSignIn.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {

        if(view == mButtonRegister){
            registerUser();
        }
        if(view == mTextSignIn){
            startActivity(new Intent(this,LoginActivity.class));
        }

    }

    private void registerUser() {
        String email = mEditEmailText.getText().toString().trim();
        String password= mEditPasswordText.getText().toString().trim();
        if (email.isEmpty()|| password.isEmpty()){
            Toast.makeText(this,"Email and Password is required",Toast.LENGTH_SHORT).show();
            return;
        }
        mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    Toast.makeText(MainActivity.this,"Registered !!!!!",Toast.LENGTH_LONG).show();

                        finish();
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    }
                }

        });
    }
}
