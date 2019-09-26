package com.example.mobileshop;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.craftman.cardform.Card;
import com.craftman.cardform.CardForm;
import com.craftman.cardform.OnPayBtnClickListner;


public class cCard_Payment extends AppCompatActivity {

    private CardForm cardForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cactivity_card__payment);

        cardForm = (CardForm) findViewById(R.id.cardform);
        TextView textView = (TextView) findViewById(R.id.payment_amount);
        Button button = (Button) findViewById(R.id.btn_pay);


        cardForm.setPayBtnClickListner(new OnPayBtnClickListner() {
            @Override
            public void onClick(Card card) {
                //Your code here!! use card.getXXX() for get any card property
                //for instance card.getName();
            }
        });


        textView.setText("$1999");
        button.setText(String.format("Pay %s",textView.getText()));

        cardForm.setPayBtnClickListner (new OnPayBtnClickListner() {

            public void onClick(Card card) {

                /*DatabaseReference readdb = FirebaseDatabase.getInstance().getReference().child("cCustomer").child("customer");
                readdb.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()){

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });*/

                Toast.makeText(cCard_Payment.this, "Payment Successful", Toast.LENGTH_SHORT).show();

            }


        });
    }

}
