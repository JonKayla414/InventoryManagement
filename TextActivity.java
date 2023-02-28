package com.example.inventoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
public class TextActivity extends AppCompatActivity {

    TextView mainText;
    private ImageButton imageButtonNo;
    private ImageButton imageButtonYes;
    EditText editTextNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        mainText = (TextView) findViewById(R.id.textView1);
        editTextNumber= (EditText) findViewById(R.id.editTextNumber);
        imageButtonNo = (ImageButton) findViewById(R.id.imageButtonNo);
        imageButtonYes = (ImageButton) findViewById(R.id.imageButtonYes);
        imageButtonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = editTextNumber.getText().toString();

                if(number.equals("") || number.length() < 9)
                    Toast.makeText(TextActivity.this, "Please enter correct fields", Toast.LENGTH_SHORT).show();
                else {

                    Toast.makeText(TextActivity.this, "Texting is now enabled!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(intent);

                }

                }
        });

        imageButtonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Toast.makeText(TextActivity.this, "Opted Out of Text, Redirecting to Home",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(intent);


            }
        });


    }
}