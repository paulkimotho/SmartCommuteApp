package com.example.test.smartcommuteapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeActivity extends AppCompatActivity {

    TextView ename,eemail,eaddress, randcondition;
    Button save,view;
    List<ListData> list;
    DatabaseReference myref;
    FirebaseDatabase firebaseDatabase;
    RecyclerView recyclerview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        randcondition = (TextView) findViewById(R.id.pconditions);
        recyclerview= (RecyclerView) findViewById(R.id.rview);
        randcondition = (TextView) findViewById(R.id.pconditions);
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        String userid =user.getUid();
        TextView randcondition;

        String[] cond = {"High traffic", "Low Traffic", "Normal Traffic"};
        //randcondition = (TextView)findViewById(R.id.pconditions);
        String word = cond[new Random().nextInt(cond.length)];
        firebaseDatabase = FirebaseDatabase.getInstance();
        myref = firebaseDatabase.getReference("Places");
       // randcondition.setText(word);

        myref.child(userid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            list = new ArrayList<>();
            for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){

                Places places = dataSnapshot1.getValue(Places.class);
                ListData listdata = new ListData();
                String name=places.getLocationName();
                String email=places.getAddress();
                String address=places.getAddress();
                listdata.setLocationName(name);
                listdata.setAddress(email);
                listdata.setAddress(address);
                list.add(listdata);
                }

                RecyclerviewAdapter recycler = new RecyclerviewAdapter(list);
                RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(HomeActivity.this);
                recyclerview.setLayoutManager(layoutmanager);
                recyclerview.setItemAnimator( new DefaultItemAnimator());
                recyclerview.setAdapter(recycler);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);




    }

    //for actionbar items selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch(item.getItemId()){
           case R.id.profile:
               Intent intent = new Intent(HomeActivity.this,ProfileActivity.class);
               startActivity(intent);
               return true;

           case R.id.set:
               Toast.makeText(HomeActivity.this,"Settings", Toast.LENGTH_SHORT).show();
               return true;

           case R.id.addL:
               Intent intent2 = new Intent(HomeActivity.this,MapsActivity.class);
               startActivity(intent2);
               return true;


       }
        return super.onOptionsItemSelected(item);
    }



}



