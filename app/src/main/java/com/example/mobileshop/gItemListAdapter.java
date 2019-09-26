package com.example.mobileshop;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class gItemListAdapter extends RecyclerView.Adapter<gItemListAdapter.travelViewHolder> {
    List<gAddItems> AddItems;
    Context context;


    public gItemListAdapter(Context context) {
        this.AddItems = new ArrayList<>();
        this.context = context;
    }

    public void travelItemAddAll(List<gAddItems> newTravelItem){
        int initSize = AddItems.size();
        AddItems.addAll(newTravelItem);
        notifyItemChanged(initSize,newTravelItem.size());
    }

    public String getLastTravelItemId() {
        return AddItems.get(AddItems.size()-1).getItemId();
    }

    @NonNull
    @Override
    public travelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View TravelItemView = LayoutInflater.from(context).inflate(R.layout.glayout_adding_items,parent,false);
        return new travelViewHolder(TravelItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull travelViewHolder holder, int position) {
        holder.ItemName.setText(AddItems.get(position).getItemName());
        holder.ItemDescription.setText(AddItems.get(position).getItemDescription());
        holder.ItemPrice.setText(AddItems.get(position).getItemPrice());

    }

    @Override
    public int getItemCount() {
        return AddItems.size();
    }

    public class travelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView ItemName,ItemDescription,ItemPrice;
        ImageView ItemImage;
        public travelViewHolder(@NonNull View itemView) {
            super(itemView);

            ItemName =(TextView)itemView.findViewById(R.id.Itemname);
            ItemDescription = (TextView)itemView.findViewById((R.id.Itemdescription));
            ItemPrice = (TextView)itemView.findViewById((R.id.Itemprice));
            ItemImage = (ImageView)itemView.findViewById(R.id.ItemimageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(),AddItems.get(getAdapterPosition()).getItemId(),Toast.LENGTH_SHORT).show();

            String itemName,itemDescription,itemPrice,itemId,userContactNumber;
            itemName = AddItems.get(getAdapterPosition()).getItemName();
            itemPrice = AddItems.get(getAdapterPosition()).getItemPrice();
            itemDescription = AddItems.get(getAdapterPosition()).getItemDescription();
            itemId = AddItems.get(getAdapterPosition()).getItemId();
            userContactNumber = AddItems.get(getAdapterPosition()).getContactNumber();

            Intent intent = new Intent(view.getContext(), gtravelItemDescription.class);
            intent.putExtra("itemName",itemName);
            intent.putExtra("itemPrice",itemPrice);
            intent.putExtra("itemDescription",itemDescription);
            intent.putExtra("itemId",itemId);
            intent.putExtra("itemUserContactNumber",userContactNumber);

            view.getContext().startActivity(intent);
        }
    }
}
