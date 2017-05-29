package com.alford.bushidoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ListOfProjectsActivity extends AppCompatActivity {

    private DatabaseReference myRef;

    private RecyclerView mRecyclerView;
    private FirebaseRecyclerAdapter mFirebaseRecyclerAdapter;

    private TextView mListTitleText;
    private TextView mDisciplineText;
    private TextView mListDescriptionText;
    private TextView mMentorTitle;
    private ImageButton mSendRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        setContentView(R.layout.activity_list_of_projects);
        mSendRequest = (ImageButton) findViewById(R.id.req_ment_btn);


        //mRecyclerView = (RecyclerView) findViewById(R.id.RECY_PROJECTS);


        Toolbar mainTool = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mainTool);


        myRef = FirebaseDatabase.getInstance().getReference("projects");


        mFirebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ProjectObject,
                ProjectHolder>(ProjectObject.class,
                R.layout.project_list_view_from_firebase, ProjectHolder.class, myRef) {

            @Override
            protected void populateViewHolder(ProjectHolder viewHolder, final ProjectObject model, final int position) {

                viewHolder.mDate.setText(model.getDate());
                viewHolder.mListTitleText.setText(model.getTitle());
                viewHolder.mDisciplineText.setText(model.getDiscipline());
                viewHolder.mListDescriptionText.setText(model.getDescription());

                //When I populate Views it returns NUll
                viewHolder.mMentorTitle.setText(model.getMentor());


                //Toast.makeText(ListOfProjectsActivity.this, 'v'+ model.getMentor(), Toast.LENGTH_SHORT).show();
                viewHolder.mButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      //  myRef.child()

                    Intent intent = new Intent(getApplicationContext(), MessageActivity.class);
                        intent.putExtra("key",model.getId());

                        startActivity(intent);




                    }
                });

            }

        };
        RecyclerView recycler = (RecyclerView) findViewById(R.id.project_listview);
        recycler.setHasFixedSize(false);
        recycler.setLayoutManager(new LinearLayoutManager(this));


        recycler.setAdapter(mFirebaseRecyclerAdapter);

//        FirebaseListAdapter firebaseListAdapter =
//                new FirebaseListAdapter<ProjectObject>(this,
//                        ProjectObject.class, R.layout.project_list_view_from_firebase, myRef) {
//                    @Override
//                    protected void populateView(View v, ProjectObject model, int position) {
//
//                       TextView title = (TextView)v.findViewById(R.id.title_list);
//                        TextView date = (TextView) v.findViewById(R.id.date_title);
//                        TextView discipline = (TextView)v.findViewById(R.id.discipline_list);
//                        TextView description = (TextView)v.findViewById(R.id.description_list);
//                        TextView mentorTitle = (TextView)v.findViewById(R.id.mentor_title);
//
//
//                        //ImageView imageView = (ImageView)findViewById(R.id.REQ_MENT_BTN);
//
//                        date.setText(model.getDate());
//                        title.setText(model.getTitle());
//                        discipline.setText(model.getDiscipline());
//                        description.setText(model.getDescription());
//                        mentorTitle.setText(model.getSelectedRadioBtn());
//                    }
//                };
//
//        final ListView listView = (ListView) findViewById(R.id.project_listview);
//        listView.setAdapter(firebaseListAdapter);
//
//
//        //TODO REQUEST TO WORK WITH MENTOR
//
//        ImageView imageView = (ImageView) findViewById(R.id.REQ_MENT_BTN);
//        mRequestMentor.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myRef.push().setValue("test123");
//            }
//        });
//
//    }

//        mSendRequest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//                startActivity(new Intent(getApplicationContext(), MessageActivity.class));
//            }
//        });


    }


    public static class ProjectHolder extends RecyclerView.ViewHolder {

        private final TextView mListTitleText;
        private final TextView mDisciplineText;
        private final TextView mDate;
        private final TextView mListDescriptionText;
        private final TextView mMentorTitle;
        private final ImageButton mButton;


        public ProjectHolder(View itemView) {
            super(itemView);

            mListTitleText = (TextView) itemView.findViewById(R.id.title_list);
            mDisciplineText = (TextView) itemView.findViewById(R.id.discipline_list);
            mListDescriptionText = (TextView) itemView.findViewById(R.id.description_list);
            mMentorTitle = (TextView) itemView.findViewById(R.id.mentor_title);
            mDate = (TextView) itemView.findViewById(R.id.date_title);
            mButton = (ImageButton) itemView.findViewById(R.id.req_ment_btn);

        }
    }
}
