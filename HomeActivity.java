package com.example.inventoryapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class HomeActivity extends AppCompatActivity {
    TextView resultText;
    private Button buttonText;
    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;
    EditText itemId;
    EditText itemName;
    EditText itemLoc;
    EditText itemCount;
    InventoryDBHelper itemsDbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        resultText = (TextView) findViewById(R.id.result);
        itemId = (EditText) findViewById(R.id.item_id);
        itemName = (EditText) findViewById(R.id.gen_name);
        itemLoc = (EditText) findViewById(R.id.item_loc);
        itemCount = (EditText) findViewById(R.id.item_count);

        buttonText = (Button) findViewById(R.id.buttonText);
        buttonText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent intent = new Intent(getApplicationContext(),TextActivity.class);
                startActivity(intent);



            }
        });


        resultText.setMovementMethod(new ScrollingMovementMethod());
        itemsDbHandler= new InventoryDBHelper(this);



    }
    public void loadItems(View view) {
        resultText.setText(itemsDbHandler.loadHandler());
        itemId.setText("");
        itemName.setText("");
        itemLoc.setText("");
        itemCount.setText("");
    }
    public void addItem (View view) {
        if(!itemId.getText().toString().isEmpty() && !itemName.getText().toString().isEmpty()
                && !itemLoc.getText().toString().isEmpty() &&  !itemCount.getText().toString().isEmpty() ) {
            int id = Integer.parseInt(itemId.getText().toString());
            int count = Integer.parseInt(itemCount.getText().toString());
            String name = itemName.getText().toString();
            String loc = itemLoc.getText().toString();

            Items items = new Items(id, name, loc, count);
            long insertId=itemsDbHandler.addHandler(items);
            if(insertId==-1){
                resultText.setText("Record already exists");
            }
            else{
                itemId.setText("");
                itemName.setText("");
                itemLoc.setText("");
                itemCount.setText("");
                resultText.setText("Record added");
            }
        }
        else{
            resultText.setText("Please fill in the correct fields!");
        }
    }


    public void updateItem(View view) {
        if( !itemId.getText().toString().isEmpty() && !itemName.getText().toString().isEmpty()
                && !itemLoc.getText().toString().isEmpty() &&  !itemCount.getText().toString().isEmpty()) {

            boolean result = itemsDbHandler.updateHandler(Integer.parseInt(
                    itemId.getText().toString()), itemName.getText().toString(), itemLoc.getText().toString() , Integer.parseInt(
                    itemCount.getText().toString()));
            if (result) {
                itemId.setText("");
                itemName.setText("");
                itemLoc.setText("");
                itemCount.setText("");
                resultText.setText("Record Updated");
            } else {
                resultText.setText("No Record Found");
            }
        }
        else{
            resultText.setText("Please fill in the correct fields!");
        }
    }
    public void deleteItem(View view) {
        if(!itemId.getText().toString().isEmpty()) {
            boolean result = itemsDbHandler.deleteHandler(Integer.parseInt(
                    itemId.getText().toString()));
            if (result) {
                itemId.setText("");
                itemName.setText("");
                resultText.setText("Record Deleted");
            } else {
                resultText.setText("No Record Found");
            }
        } else{
            resultText.setText("Please fill correct Item Stock Number");
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        itemsDbHandler.close();
    }

}