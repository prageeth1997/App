package com.example.mobileshop;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class refund extends AppCompatActivity {

    TextView ID , image;
    EditText reasonTxt , descriptionTxt , amountTxt;
    RadioButton rdbtn1 , rdbtn2 ;
    Button btn;

    RefundDB refund_DB;
    DatabaseReference pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund);

        ID = findViewById(R.id.textView13);
        image = findViewById(R.id.textView4);
        reasonTxt = findViewById(R.id.editText1);
        descriptionTxt = findViewById(R.id.editText2);
        amountTxt = findViewById(R.id.editText3);
        rdbtn1 = findViewById(R.id.radioButton1);
        rdbtn2 = findViewById(R.id.radioButton2);
        btn = findViewById(R.id.button3);

        refund_DB = new RefundDB();

      btn.setOnClickListener(new View.OnClickListener(){
          public void onClick(View v){
              pref =  FirebaseDatabase.getInstance().getReference().child("RefundDB");

              refund_DB.setOrderID(ID.getText().toString());
              refund_DB.setImage(image.getText().toString());
              refund_DB.setReason(reasonTxt.getText().toString());
              refund_DB.setDescription(descriptionTxt.getText().toString());



              if(rdbtn1.callOnClick()){
                  refund_DB.setType("Full");
                  refund_DB.setAmount(amountTxt.getText().toString());
              }
              if(rdbtn2.callOnClick()){
                  refund_DB.setType("Half");
                  refund_DB.setAmount(amountTxt.getText().toString());
              }

              pref.push();
              pref.child(refund_DB.getOrderID()).setValue(refund_DB);

              clearAll();

          }
      });


    }

    public void clearAll() {
        reasonTxt.setText("");
        descriptionTxt.setText("");
        amountTxt.setText("");

    }
    public void partialRefund(View view) {

        TextView txt1 = findViewById(R.id.editText3);
        TextView txt2 = findViewById(R.id.textView17);

        txt1.setVisibility(View.VISIBLE);
        txt2.setVisibility(View.VISIBLE);
        rdbtn1.setChecked(false);

    }
    public void fullRefund(View view) {

        TextView txt1 = findViewById(R.id.editText3);
        TextView txt2 = findViewById(R.id.textView17);

        txt1.setVisibility(View.INVISIBLE);
        txt2.setVisibility(View.INVISIBLE);
        rdbtn2.setChecked(false);

    }

}
