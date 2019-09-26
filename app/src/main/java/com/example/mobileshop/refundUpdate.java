package com.example.mobileshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class refundUpdate extends AppCompatActivity {

    EditText txt1 , txt2 , txt3 , txt4;
    TextView textV1 , textV2,textV3 , textV4,textV5 , textV6;
    RadioButton rad1 , rad2;
    ProgressBar proBar;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund_update);

        txt1 = findViewById(R.id.editText1);
        txt2 = findViewById(R.id.editText2);
        txt3 = findViewById(R.id.amount1);
        txt4 = findViewById(R.id.editText4);
        textV1 = findViewById(R.id.textView16);
        textV2 = findViewById(R.id.textView18);

        textV1 = findViewById(R.id.textView1);
        textV2 = findViewById(R.id.textView2);
        textV3 = findViewById(R.id.textView3);
        textV4 = findViewById(R.id.textView4);
        textV5 = findViewById(R.id.textView17);
        textV6 = findViewById(R.id.textView21);

        rad1 = findViewById(R.id.radioButton1);
        rad2 = findViewById(R.id.radioButton2);
        proBar = findViewById(R.id.progress);
        btn = findViewById(R.id.button4);

        Intent intent = getIntent();
        textV2.setText(intent.getStringExtra("ID"));

        DatabaseReference readrefUpdate = FirebaseDatabase.getInstance().getReference().child("RefundDB").child(textV2.getText().toString());
        readrefUpdate.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    txt1.setText(dataSnapshot.child("reason").getValue().toString());
                    txt2.setText(dataSnapshot.child("description").getValue().toString());
                    String radio = dataSnapshot.child("type").getValue().toString();
                    if(radio.equals("Half")) {
                        rad2.setChecked(true);
                        rad1.setChecked(false);
                        txt3.setVisibility(View.VISIBLE);
                        txt3.setText(dataSnapshot.child("amount").getValue().toString());

                    }else {
                        rad1.setChecked(true);
                        rad2.setChecked(false);
                    }
                    textV1.setVisibility(View.VISIBLE);
                    textV2.setVisibility(View.VISIBLE);
                    textV3.setVisibility(View.VISIBLE);
                    textV4.setVisibility(View.VISIBLE);
                    textV5.setVisibility(View.VISIBLE);
                    textV6.setVisibility(View.VISIBLE);


                    txt1.setVisibility(View.VISIBLE);
                    txt2.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    rad1.setVisibility(View.VISIBLE);
                    rad2.setVisibility(View.VISIBLE);
                    btn.setVisibility(View.VISIBLE);
                    proBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void fully(View view) {

        TextView txt1 = findViewById(R.id.amount1);
        TextView txt2 = findViewById(R.id.textView17);
        RadioButton rdbtn3 = findViewById(R.id.radioButton1);
        txt1.setVisibility(View.VISIBLE);
        txt2.setVisibility(View.VISIBLE);
        rdbtn3.setChecked(false);

    }
    public void half(View view) {

        TextView txt1 = findViewById(R.id.amount1);
        TextView txt2 = findViewById(R.id.textView17);
        RadioButton rdbtn4 = findViewById(R.id.radioButton2);
        txt1.setVisibility(View.INVISIBLE);
        txt2.setVisibility(View.INVISIBLE);
        rdbtn4.setChecked(false);

    }
    public void refundUpdate(View view) {
        Intent intent2 = new Intent(refundUpdate.this , viewRefund.class);
        startActivity(intent2);

    }
}
