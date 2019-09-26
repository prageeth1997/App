package com.example.madpaymentfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class cUserProf extends AppCompatActivity {

    Button btnupdate;
    DatabaseReference dbref;
    TextView name, address1, address, postal, id, mobile, mail, opt;
    cCustomer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cactivity_user_prof);

        btnupdate = findViewById(R.id.btnupdate);

        //Passing Values
        name = findViewById(R.id.tv_name);
        address1 = findViewById(R.id.tv_address1);
        address = findViewById(R.id.tv_address);
        postal = findViewById(R.id.tv_postal);
        id = findViewById(R.id.tv_id);
        mobile = findViewById(R.id.tv_mobile);
        mail = findViewById(R.id.tv_mail);
        opt = findViewById(R.id.tv_opt);

        //Retrive
        DatabaseReference retrive = FirebaseDatabase.getInstance().getReference().child("cCustomer").child("cus2");
        retrive.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    name.setText(dataSnapshot.child("cusName").getValue().toString());
                    address1.setText(dataSnapshot.child("addL1").getValue().toString());
                    address.setText(dataSnapshot.child("addL2").getValue().toString());
                    postal.setText(dataSnapshot.child("pCode").getValue().toString());
                    id.setText(dataSnapshot.child("cusid").getValue().toString());
                    mobile.setText(dataSnapshot.child("conNo").getValue().toString());
                    mail.setText(dataSnapshot.child("mail").getValue().toString());
                    opt.setText(dataSnapshot.child("optMsg").getValue().toString());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(cUserProf.this, cDetails_Update.class);
                startActivity(intent);
            }
        });

    }

}
