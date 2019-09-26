package com.example.mobileshop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class gtravelItemDescription extends AppCompatActivity {
    String itemName,itemDescription,itemPrice,ItemId,contactnumber;
    TextView userTelNo;
    TextView txtItemName,txtItemPrice,txtItemDesciption,txtItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gactivity_adding_items_description);
        Intent i =getIntent();
        itemName= i.getStringExtra("itemName");
        itemPrice= i.getStringExtra("itemPrice");
        itemDescription= i.getStringExtra("itemDescription");
        ItemId= i.getStringExtra("itemId");
        contactnumber = i.getStringExtra("itemUserContactNumber");

        userTelNo = findViewById(R.id.txtUserTelNo);
        txtItemName =findViewById(R.id.item_name);
        txtItemPrice =findViewById(R.id.item_price);
        txtItemDesciption = findViewById(R.id.item_description);
        txtItemId = findViewById(R.id.item_no);

        userTelNo.setText(contactnumber);
        txtItemName.setText(itemName);
        txtItemPrice.setText("Rs. " + itemPrice + ".00");
        txtItemDesciption.setText(itemDescription);
        txtItemId.setText(ItemId);
    }
}
