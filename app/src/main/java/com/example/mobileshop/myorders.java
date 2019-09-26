package com.example.mobileshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class myorders extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myorders);



        LinearLayout layout = (LinearLayout) findViewById(R.id.mainLayout);


        for (int i = 0; i < 3; i++) {
            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.VERTICAL);
            row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            row.setBackgroundResource(R.drawable.order_outline);

                Button btnTag1;
                btnTag1 = new Button(this);
                btnTag1.setId(i);
                btnTag1.setText(""+i);

           // btnTag1.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , 10));
                //btnTag1.setBackgroundResource(R.drawable.radiustext);
                //btnTag1.setWidth();
                row.addView(btnTag1);

               /* Button btnTag2;
                btnTag2 = new Button(this);
                btnTag2.setId(i);
                btnTag2.setText(""+i);
                //  btnTag2.setBackgroundResource(R.drawable.radiustext2);
                row.addView(btnTag2);*/



            layout.addView(row);
        }
    }

    public void refund(View view) {

        Intent intent2 = new Intent(myorders.this , refund.class);
        startActivity(intent2);
    }
    public void refundUpdate(View view) {
        Intent intent2 = new Intent(myorders.this , cDetail_Form.class);
        startActivity(intent2);

    }
}
