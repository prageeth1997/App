package com.example.mobileshop;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class cDetail_Form extends AppCompatActivity {

    Button btnsubmit, btnreset;
    EditText txtName, txtAddL1, txtAddL2, txtPostal, txtNIC, txtMobile, txtMail, txtOpt;
    DatabaseReference dbref;
    cCustomer customer;

    public void clearinfo(View view){
        txtName.setText("");
        txtAddL1.setText("");
        txtAddL2.setText("");
        txtPostal.setText("");
        txtNIC.setText("");
        txtMail.setText("");
        txtMobile.setText("");
        txtOpt.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cactivity_detail__form);

        txtName = findViewById(R.id.txtName);
        txtAddL1 = findViewById(R.id.txtAddL1);
        txtAddL2 = findViewById(R.id.txtAddL2);
        txtPostal = findViewById(R.id.txtPostal);
        txtNIC = findViewById(R.id.txtNIC);
        txtMobile = findViewById(R.id.txtMobile);
        txtMail = findViewById(R.id.txtMail);
        txtOpt = findViewById(R.id.txtOpt);

        btnsubmit = findViewById(R.id.btnsubmit);
        btnreset = findViewById(R.id.btnreset);

        customer = new cCustomer();

        //Insert
        btnsubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                dbref = FirebaseDatabase.getInstance().getReference().child("cCustomer");
                try {
                    if(TextUtils.isEmpty(txtName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter Name", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtAddL1.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter Address", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtAddL2.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter Address", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtPostal.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter PostalCode", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtNIC.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter NIC", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtMobile.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtMail.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter Email", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtOpt.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter Optional Message", Toast.LENGTH_SHORT).show();
                    else {
                        customer.setCusName(txtName.getText().toString().trim());
                        customer.setAddL1(txtAddL1.getText().toString().trim());
                        customer.setAddL2(txtAddL2.getText().toString().trim());
                        customer.setpCode(txtPostal.getText().toString().trim());
                        customer.setCusid(txtNIC.getText().toString().trim());
                        customer.setConNo(txtMobile.getText().toString().trim());
                        customer.setMail(txtMail.getText().toString().trim());
                        customer.setOptMsg(txtOpt.getText().toString().trim());

                        //dbref.push();
                        dbref.child("cus2").setValue(customer);
                        //dbref.child("1");


                        Toast.makeText(getApplicationContext(), "Details Saved Successfully", Toast.LENGTH_SHORT).show();
                        clearinfo(view);

                        Intent intent = new Intent(cDetail_Form.this, cUserProf.class);
                        startActivity(intent);

                    }

                }
                catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();

                }


            }
        });

    }

}

