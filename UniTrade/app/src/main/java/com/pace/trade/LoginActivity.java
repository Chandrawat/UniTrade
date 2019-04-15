package com.pace.trade;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{


    private Button mButtonSignIn;
    private EditText mEditTextEmailLogin;
    private EditText mEditTextPasswordLogin;
    private TextView mTextViewSingup;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        FirebaseApp.initializeApp(this);
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!=null){

            finish();
            //startActivity(new Intent(getApplicationContext(), ProfileActivity.class));

        }

        mEditTextEmailLogin = findViewById(R.id.emailEditTextLogin);
        mEditTextPasswordLogin = findViewById(R.id.passwordEditTextLogin);
        mButtonSignIn = findViewById(R.id.signUpButton);
        mTextViewSingup = findViewById(R.id.signUpInTextView);


        mButtonSignIn.setOnClickListener(this);
        mTextViewSingup.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if(view == mButtonSignIn){
            userLogin();

        }
        if(view == mTextViewSingup){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }


    }

    private void userLogin() {

        String email = mEditTextEmailLogin.getText().toString().trim();
        String password= mEditTextPasswordLogin.getText().toString().trim();

        if (email.isEmpty()|| password.isEmpty()){
            Toast.makeText(this,"Email and Password is required",Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                        finish();
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                }
            }
        });

    }
}
