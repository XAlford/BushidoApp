package com.alford.bushidoapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button mSignBtn;

    private EditText mSignEmail;

    private EditText mSignPassword;

    private TextView mBackToRegister;

    private ProgressDialog mProgressDialog;

    private FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();

        if (mFirebaseAuth.getCurrentUser() != null) {


            //profile

            finish();

            startActivity(new Intent(getApplication(), ProfileActivity.class));

        }


        mSignBtn = (Button) findViewById(R.id.SIGN_IN_BTN);

        mSignEmail = (EditText) findViewById(R.id.SIGN_EMAIL);

        mSignPassword = (EditText) findViewById(R.id.SIGN_PASSWORD);

        mBackToRegister = (TextView) findViewById(R.id.REG_LINK);

        mSignBtn.setOnClickListener(this);

        mBackToRegister.setOnClickListener(this);

        mProgressDialog = new ProgressDialog(this);




    }

    private void loginUser(){
        String email = mSignEmail.getText().toString().trim();
        String password = mSignPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            //email is empty

            Toast.makeText(this, "PLEASE ENTER AN EMAIL",Toast.LENGTH_SHORT).show();

            return;

        }

        if (TextUtils.isEmpty(password)){
            //password is empty

            Toast.makeText(this, "PLEASE ENTER A PASSWORD",Toast.LENGTH_SHORT).show();

            return;

        }


        mProgressDialog.setMessage("WELCOME ABOARD");
        mProgressDialog.show();

        mFirebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mProgressDialog.dismiss();

                if (task.isSuccessful()) {
                    finish();

                    startActivity(new Intent(getApplication(), ProfileActivity.class));

                }else{

                    Toast.makeText(Login.this, "Something not quite right, Lets try something else", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    @Override
    public void onClick(View v) {

        if (v == mSignBtn) {
            loginUser();
        }

        if (v == mBackToRegister) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

    }


}
