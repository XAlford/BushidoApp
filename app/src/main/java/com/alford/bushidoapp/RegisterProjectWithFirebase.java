package com.alford.bushidoapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterProjectWithFirebase extends AppCompatActivity {

    private DatabaseReference myRef;


    Button mRegisterButton;
    EditText mProjectName;
    EditText mSubmitDateField;
    EditText mProjectDescriptionField;
    EditText mDisciplineField;
    RadioButton mMentorRadioButton;
    RadioButton mApprenticeRadioButton;
    RadioGroup mRadioGroupMentor;



    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mFirebaseInstance;

    private String userId;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_project_with_firebase);

//
//        mFirebaseInstance = FirebaseDatabase.getInstance();
//
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
//        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        mRegisterButton = (Button) findViewById(R.id.REG_PROJ_WITH_FIREBASE);
        mDisciplineField = (EditText) findViewById(R.id.DISCIP_EDITTEXT);
        mRadioGroupMentor = (RadioGroup) findViewById(R.id.radio_group);


        mSubmitDateField = (EditText) findViewById(R.id.SET_DATE);
        mProjectDescriptionField = (EditText) findViewById(R.id.PROJ_DESCRIPT);
        mMentorRadioButton = (RadioButton) findViewById(R.id.MENT_RADIO);
        mApprenticeRadioButton = (RadioButton) findViewById(R.id.PRENTICE_RADIO);
        mProjectName = (EditText) findViewById(R.id.project_name);

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedRadioBtn = ((RadioButton) findViewById(mRadioGroupMentor
                        .getCheckedRadioButtonId()))
                        .getText()
                        .toString();

                writeNewProjectToFirebase(mProjectName.getText().toString(),
                        mProjectDescriptionField.getText().toString(),
                        mSubmitDateField.getText().toString(),
                        mDisciplineField.getText().toString(),
                        selectedRadioBtn);

                finish();








            }
        });



        //String userId = mDatabaseReference.push().getKey();
        //mDatabase.child(userId).setValue();











        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {

                            case R.id.profile:

                                Intent intent = new Intent(RegisterProjectWithFirebase.this, ProfileActivity.class);
                                startActivity(intent);
                                break;

                            case R.id.projects:
                                break;


                            case R.id.nearby:
                                Intent projectView = new Intent(RegisterProjectWithFirebase.this, ListOfProjectsActivity.class);
                                startActivity(projectView);
                                break;

                        }
                        return true;
                    }
                });





    }
    public void writeNewProjectToFirebase(
            String mTitle,
            String mDiscipline,
            String mSelectedRadioBtn,
            String mDescription,
            String mDate) {

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("projects");
//                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());



        DatabaseReference msgRef = mDatabase.push();
        String key = msgRef.getKey();
//                mDatabaseReference.child("projects").child(userId).setValue(project);



        ProjectObject project = new ProjectObject(key,mTitle,mSelectedRadioBtn,mDiscipline,mDescription,mDate);

        msgRef.setValue(project);


        Toast.makeText(this, "YOUR PROJECT IS LIVE",Toast.LENGTH_SHORT).show();


    }
}
