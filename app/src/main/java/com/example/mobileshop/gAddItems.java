package com.example.mobileshop;

public class gAddItems {

    String ItemId;
    String ItemName;
    String Quantity;
    String Category;
    String ItemPrice;
    String ItemDescription;
    String ItemImage;
    String ContactNumber;


    public gAddItems(String itemId, String itemName, String quantity, String category, String itemPrice, String itemDescription, String itemImage, String contactNumber) {
        ItemId = itemId;
        ItemName = itemName;
        Quantity = quantity;
        Category = category;
        ItemPrice = itemPrice;
        ItemDescription = itemDescription;
        ItemImage = itemImage;
        ContactNumber = contactNumber;
    }

    public gAddItems() {

    }

    public String getItemId() {
        return ItemId;
    }

    public void setItemId(String itemId) {
        ItemId = itemId;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(String itemPrice) {
        ItemPrice = itemPrice;
    }

    public String getItemDescription() {
        return ItemDescription;
    }

    public void setItemDescription(String itemDescription) {
        ItemDescription = itemDescription;
    }

    public String getItemImage() {
        return ItemImage;
    }

    public void setItemImage(String itemImage) {
        ItemImage = itemImage;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }
}
