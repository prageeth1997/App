package com.example.mobileshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class refundUpdate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund_update);
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
