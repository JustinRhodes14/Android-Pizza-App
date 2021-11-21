package com.example.project5_akg106_jgr85;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Main activity class for ordering pizzas, looking at orders, and viewing store orders.
 * @author Andy Giang, Justin Rhodes
 */
public class MainActivity extends AppCompatActivity {

    public static final String PIZZA_TYPE = "com.example.project5_akg106_jgr85.MESSAGE";

    private ImageButton btn1;
    private ImageButton btn2;
    private ImageButton btn3;
    private EditText editTextPhoneNumber;

    /**
     * Creates activity for main pizza view.
     * @param savedInstanceState Saved instance.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (ImageButton) findViewById(R.id.imageButton);
        editTextPhoneNumber = (EditText) findViewById(R.id.editTextPhoneNumber);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validPhoneNumber()) {
                    deluxeClick();
                } else {
                    phoneToast();
                }
            }
        });
        btn2 = (ImageButton) findViewById(R.id.imageButton3);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validPhoneNumber()) {
                    hawaiianClick();
                } else {
                    phoneToast();
                }
            }
        });
        btn3 = (ImageButton) findViewById(R.id.imageButton4);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validPhoneNumber()) {
                    pepperoniClick();
                } else {
                    phoneToast();
                }
            }
        });
    }

    /**
     * Event handler for deluxe pizza button click.
     */
    protected void deluxeClick() {
        Intent intent = new Intent(this, PizzaActivity.class);
        intent.putExtra(PIZZA_TYPE,"Deluxe");
        startActivity(intent);
    }

    /**
     * Event handler for hawaiian pizza button click.
     */
    protected void hawaiianClick() {
        Intent intent = new Intent(this, PizzaActivity.class);
        intent.putExtra(PIZZA_TYPE,"Hawaiian");
        startActivity(intent);
    }

    /**
     * Event handler for pepperoni pizza button click.
     */
    protected void pepperoniClick() {
        Intent intent = new Intent(this, PizzaActivity.class);
        intent.putExtra(PIZZA_TYPE,"Pepperoni");
        startActivity(intent);
    }

    /**
     * Ensures text phone number is a valid entry before proceeding to other activities.
     * @return
     */
    protected boolean validPhoneNumber() {
        return (editTextPhoneNumber.getText().toString().length() == 10);
    }

    /**
     * Toast for invalid phone number entries.
     */
    protected void phoneToast() {
        Context context = getApplicationContext();
        CharSequence text = "Invalid phone number, must be 10 digits";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context,text,duration);
        toast.show();
    }
}