package com.example.mobileshop;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class cDetails_Update extends AppCompatActivity {

    EditText txtName1, txtAddL11, txtAddL21, txtPostal1, txtNIC1, txtMobile1, txtMail1, txtOpt1;
    Button btnupdate, btndelete;
    DatabaseReference dbref;
    cCustomer customer;

    public void clearinfo(View view){
        txtName1.setText("");
        txtAddL11.setText("");
        txtAddL21.setText("");
        txtPostal1.setText("");
        txtNIC1.setText("");
        txtMail1.setText("");
        txtMobile1.setText("");
        txtOpt1.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_details__update);

        txtName1 = findViewById(R.id.txtName1);
        txtAddL11 = findViewById(R.id.txtAddL11);
        txtAddL21 = findViewById(R.id.txtAddL21);
        txtPostal1 = findViewById(R.id.txtPostal1);
        txtNIC1 = findViewById(R.id.txtNIC1);
        txtMobile1 = findViewById(R.id.txtMobile1);
        txtMail1 = findViewById(R.id.txtMail1);
        txtOpt1 = findViewById(R.id.txtOpt1);

        btnupdate = findViewById((R.id.btnupdate));
        btndelete = findViewById(R.id.btndelete);

        customer = new cCustomer();


        //Retrive
        DatabaseReference retrive = FirebaseDatabase.getInstance().getReference().child("cCustomer").child("cus2");
        retrive.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    txtName1.setText(dataSnapshot.child("cusName").getValue().toString());
                    txtAddL11.setText(dataSnapshot.child("addL1").getValue().toString());
                    txtAddL21.setText(dataSnapshot.child("addL2").getValue().toString());
                    txtPostal1.setText(dataSnapshot.child("pCode").getValue().toString());
                    txtNIC1.setText(dataSnapshot.child("cusid").getValue().toString());
                    txtMobile1.setText(dataSnapshot.child("conNo").getValue().toString());
                    txtMail1.setText(dataSnapshot.child("mail").getValue().toString());
                    txtOpt1.setText(dataSnapshot.child("optMsg").getValue().toString());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //Update
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference upref = FirebaseDatabase.getInstance().getReference().child("cCustomer");
                upref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("cus2")){
                            try {
                                customer.setCusName(txtName1.getText().toString().trim());
                                customer.setAddL1(txtAddL11.getText().toString().trim());
                                customer.setAddL2(txtAddL21.getText().toString().trim());
                                customer.setpCode(txtPostal1.getText().toString().trim());
                                customer.setCusid(txtNIC1.getText().toString().trim());
                                customer.setConNo(txtMobile1.getText().toString().trim());
                                customer.setMail(txtMail1.getText().toString().trim());
                                customer.setOptMsg(txtOpt1.getText().toString().trim());

                                dbref = FirebaseDatabase.getInstance().getReference().child("cCustomer").child("cus2");
                                dbref.setValue(customer);

                                Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                            }
                            catch (NumberFormatException e){
                                Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                            Toast.makeText(getApplicationContext(), "No Data to Update", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Intent intent = new Intent(cDetails_Update.this, cCard_Payment.class);
                startActivity(intent);
            }
        });



        //Delete
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                DatabaseReference delref = FirebaseDatabase.getInstance().getReference().child("cCustomer");
                delref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("cus2")){


                            AlertDialog.Builder builder = new AlertDialog.Builder(cDetails_Update.this);

                            builder.setMessage("Are you sure ?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    dbref = FirebaseDatabase.getInstance().getReference().child("cCustomer").child("cus2");
                                    dbref.removeValue();
                                    clearinfo(view);
                                    Toast.makeText(getApplicationContext(), "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                                }
                            }).setNegativeButton("No", null);


                            AlertDialog alert = builder.create();
                            alert.show();

                        }
                        else
                            Toast.makeText(getApplicationContext(), "No Data to Delete", Toast.LENGTH_SHORT).show();
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }

        });
    }
}
