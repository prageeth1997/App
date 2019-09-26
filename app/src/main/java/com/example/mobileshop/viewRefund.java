package com.example.mobileshop;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class viewRefund extends AppCompatActivity {

    TextView idHeader, idTxt , reasonHeader , reasonTxt , descriptionHeader , descriptionTxt , statusHeader , mobileHeader , mobileNumber , status , adminNoteHeader , adminNote;
    Button btnDelete , btnUpdate;
    ProgressBar proBar;

    RefundDB refund_DB2;
    DatabaseReference pref2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_refund);

        idHeader = findViewById(R.id.textView1);
        idTxt = findViewById(R.id.idTxt);
        reasonHeader = findViewById(R.id.textView2);
        reasonTxt = findViewById(R.id.textView3);
        descriptionHeader = findViewById(R.id.textView4);
        descriptionTxt = findViewById(R.id.textView5);
        mobileHeader = findViewById(R.id.textView6);
        mobileNumber = findViewById(R.id.textView7);
        statusHeader = findViewById(R.id.textView8);
        status = findViewById(R.id.textView9);
        adminNoteHeader = findViewById(R.id.textView10);
        adminNote = findViewById(R.id.textView11);

        btnDelete = findViewById(R.id.button6);
        btnUpdate = findViewById(R.id.button5);
        proBar = findViewById(R.id.progress);

        DatabaseReference readref = FirebaseDatabase.getInstance().getReference().child("RefundDB").child(idTxt.getText().toString());
        readref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    reasonTxt.setText(dataSnapshot.child("reason").getValue().toString());
                    descriptionTxt.setText(dataSnapshot.child("description").getValue().toString());
                    idHeader.setVisibility(View.VISIBLE);
                    idTxt.setVisibility(View.VISIBLE);
                    reasonHeader.setVisibility(View.VISIBLE);
                    reasonTxt.setVisibility(View.VISIBLE);
                    descriptionHeader.setVisibility(View.VISIBLE);
                    descriptionTxt.setVisibility(View.VISIBLE);
                    mobileHeader.setVisibility(View.VISIBLE);
                    mobileNumber.setVisibility(View.VISIBLE);
                    statusHeader.setVisibility(View.VISIBLE);
                    status.setVisibility(View.VISIBLE);
                    adminNoteHeader.setVisibility(View.VISIBLE);
                    adminNote.setVisibility(View.VISIBLE);
                    btnDelete.setVisibility(View.VISIBLE);
                    btnUpdate.setVisibility(View.VISIBLE);
                    proBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pref2 =  FirebaseDatabase.getInstance().getReference().child("RefundDB").child(idTxt.getText().toString());
                pref2.removeValue();

                Toast.makeText(viewRefund.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();

            }
        });



    }

    public void Update(View view) {
        Intent intent2 = new Intent(viewRefund.this , refundUpdate.class);
        startActivity(intent2);

    }

    public void delete(View view) {
        //Intent intent2 = new Intent(viewRefund.this , viewRefund.class);
       // startActivity(intent2);

    }
}
