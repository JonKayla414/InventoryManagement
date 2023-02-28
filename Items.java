package com.example.inventoryapp;

public class Items {
    private int id;
    private String itemName;
    private String itemLoc;
    private int itemCount;
    Items() {
    }
    Items(int id, String itemName, String itemLoc, int itemCount) {
        this.id = id;
        this.itemName = itemName;
        this.itemLoc = itemLoc;
        this.itemCount = itemCount;

    }
    void setID(int id) {
        this.id = id;
    }
    int getID() {
        return this.id;
    }
    void setItemName(String itemName) {
        this.itemName = itemName;
    }
    String getItemName() {
        return this.itemName;
    }
    void setItemLoc(String ItemLoc){
        this.itemLoc=itemLoc;

    }
    String getItemLoc(){
        return this.itemLoc;
    }
    void setItemCount (int itemCount){
        this.itemCount = itemCount;
    }

    int getItemCount (){
        return this.itemCount;
    }
}

