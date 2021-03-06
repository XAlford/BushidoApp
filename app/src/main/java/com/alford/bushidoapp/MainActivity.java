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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mRegBtn;
    private EditText mEmailText;
    private EditText mPassword;
    private TextView mSignUp;

    private ProgressDialog mProgressDialog;

    private FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressDialog = new ProgressDialog(this);

        mFirebaseAuth = FirebaseAuth.getInstance();

        if (mFirebaseAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }


        mRegBtn = (Button) findViewById(R.id.REG_BTN);
        mEmailText = (EditText) findViewById(R.id.REG_EMAIL);
        mPassword = (EditText) findViewById(R.id.REG_PASSWORD);
        mSignUp = (TextView) findViewById(R.id.Sign_In_Link);

        mRegBtn.setOnClickListener(this);
        mSignUp.setOnClickListener(this);




    }

    private void registerUser() {
        String email = mEmailText.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

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

        mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mProgressDialog.dismiss();


                if (task.isSuccessful()) {

                    if (mFirebaseAuth.getCurrentUser() != null) {
                        finish();
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    }

                    Toast.makeText(MainActivity.this, "EUREKA, You Registered Successfully!", Toast.LENGTH_SHORT).show();

                }else{

                    Toast.makeText(MainActivity.this, "Something not quite right, Lets try something else", Toast.LENGTH_SHORT).show();



                }

            }
        });



    }

    @Override
    public void onClick(View v) {

        if (v == mRegBtn) {
            registerUser();
        }

        if (v == mSignUp) {
            //open login activity
            startActivity(new Intent(this,Login.class));
        }

    }
}
