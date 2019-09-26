package com.example.mobileshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class gAddingItems extends AppCompatActivity {

    EditText editTextItemName,editTextItemNo,editTextitemPrice,editTextDescription,editTextcontactNo,editTextsearch,editTextQuantity,editTextCategory;
    Button buttonAdd,buttonDelete,buttonUpdate,buttonAlllist;
    DatabaseReference dbRef;
    ImageButton buttonSearch;

    gAddItems addItems = new gAddItems();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference table_Item = database.getReference("addItems"); //additems - object name
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gactivity_adding_items);


        editTextItemName = findViewById(R.id.EditTextItemName);
        editTextItemNo  = findViewById(R.id.EditTextItemNo);
        editTextitemPrice = findViewById(R.id.EditTextItemPrice);
        editTextDescription = findViewById(R.id.EditTextItemDescription);
        editTextcontactNo = findViewById(R.id.EditTextContactNo);
        editTextsearch = findViewById(R.id.ItemsSearch_field);
        editTextCategory = findViewById(R.id.EditTextCategory);
        editTextQuantity = findViewById(R.id.EditTextItemQuantity);

        buttonAlllist = findViewById(R.id.ItemsAll_btn);
        buttonSearch = findViewById(R.id.ItemsSearch_btn);
        buttonAdd = findViewById(R.id.InsertButton);
        buttonDelete = findViewById(R.id.DeleteButton);
        buttonUpdate = findViewById(R.id.UpdateButton);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addaddItems();
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateaddItems();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteaddItems();
            }
        });

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showaddItems();
            }
        });

        buttonAlllist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(gAddingItems.this, gtravelItemList.class);
                startActivity(intent);
            }
        });
    }

    public void addaddItems(){

        table_Item.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(validate() ==  true) {
                    try {

                        addItems.setItemName(editTextItemName.getText().toString().trim());
                        addItems.setItemId(editTextItemNo.getText().toString().trim());
                        addItems.setItemDescription(editTextDescription.getText().toString().trim());
                        addItems.setItemPrice(editTextitemPrice.getText().toString().trim());
                        addItems.setContactNumber(editTextcontactNo.getText().toString().trim());
                        addItems.setCategory(editTextCategory.getText().toString().trim());
                        addItems.setQuantity(editTextQuantity.getText().toString().trim());

                        table_Item.child(addItems.getItemId()).setValue(addItems);

                        Toast.makeText(getApplicationContext(), "Data Insert Successfully", Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Invalid Operation", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void deleteaddItems(){
        DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("addItems");
        delRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.hasChild(editTextItemNo.getText().toString())) {

                        dbRef = FirebaseDatabase.getInstance().getReference().child("addItems").child(editTextItemNo.getText().toString());
                        dbRef.removeValue();

                        Toast.makeText(getApplicationContext(), "Data Delete Successfully", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Item Name", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Invalid Operation",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void updateaddItems(){
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("addItems");
        readRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                try {
                    if(validate() ==  true) {
                        addItems.setItemName(editTextItemName.getText().toString().trim());
                        addItems.setItemId(editTextItemNo.getText().toString().trim());
                        addItems.setItemDescription(editTextDescription.getText().toString().trim());
                        addItems.setItemPrice(editTextitemPrice.getText().toString().trim());
                        addItems.setContactNumber(editTextcontactNo.getText().toString().trim());
                        addItems.setCategory(editTextCategory.getText().toString().trim());
                        addItems.setQuantity(editTextQuantity.getText().toString().trim());


                        dbRef = FirebaseDatabase.getInstance().getReference().child("addItems").child(editTextItemNo.getText().toString());
                        dbRef.setValue(addItems);

                        Toast.makeText(getApplicationContext(), "Data Update Successfully", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Invalid Operation",Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void showaddItems(){
        dbRef = FirebaseDatabase.getInstance().getReference().child("addItems").child(editTextsearch.getText().toString());
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.hasChildren()) {

                        editTextItemName.setText(dataSnapshot.child("itemName").getValue().toString());
                        editTextItemNo.setText(dataSnapshot.child("itemId").getValue().toString());
                        editTextitemPrice.setText(dataSnapshot.child("itemPrice").getValue().toString());
                        editTextDescription.setText(dataSnapshot.child("itemDescription").getValue().toString());
                        editTextcontactNo.setText(dataSnapshot.child("contactNumber").getValue().toString());
                        editTextCategory.setText(dataSnapshot.child("category").getValue().toString());
                        editTextQuantity.setText(dataSnapshot.child("quantity").getValue().toString());

                        Toast.makeText(getApplicationContext(), "Data Show Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Item Name", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Invalid Operation",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private boolean validate(){

        String item = editTextItemName.getText().toString().trim();
        String itemno = editTextItemNo.getText().toString().trim();
        String price = editTextitemPrice.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        String contact = editTextcontactNo.getText().toString().trim();
        String quantity = editTextQuantity.getText().toString().trim();
        String category = editTextCategory.getText().toString().trim();


        if(item.isEmpty()){
            editTextItemName.setError("Field can't be empty");
            return false;
        }
        else if (itemno.isEmpty()) {
            editTextItemNo.setError("Field can't be empty");
            return false;
        }
        else if (quantity.isEmpty()) {
            editTextitemPrice.setError("Field can't be empty");
            return false;
        }

        else if (price.isEmpty()) {
            editTextitemPrice.setError("Field can't be empty");
            return false;
        }
        else if (category.isEmpty()) {
            editTextitemPrice.setError("Field can't be empty");
            return false;
        }
        else if (description.isEmpty()) {
            editTextDescription.setError("Field can't be empty");
            return false;
        }
        else if (contact.isEmpty()) {
            editTextcontactNo.setError("Field can't be empty");
            return false;
        }
        else{
            return  true;
        }
    }
}
