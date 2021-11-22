package com.example.project5_akg106_jgr85;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import projectFiles.Order;
import projectFiles.Pizza;

/**
 * Main activity class for ordering pizzas, looking at orders, and viewing store orders.
 * @author Andy Giang, Justin Rhodes
 */
public class MainActivity extends AppCompatActivity {

    public static final String PIZZA_TYPE = "com.example.project5_akg106_jgr85.MESSAGE";
    public static final int PIZZA_REQ = 1;
    public static final int PHONE_LENGTH = 10;
    public static final int NOT_SET = -1;

    private ImageButton btn1;
    private ImageButton btn2;
    private ImageButton btn3;
    private ImageButton btn4;
    private EditText editTextPhoneNumber;

    private long phoneNumber = -1;
    private Order o;

    /**
     * Creates activity for main pizza view.
     * @param savedInstanceState Saved instance.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextPhoneNumber = (EditText) findViewById(R.id.editTextPhoneNumber);
        buttonInit();
        orderButtonInit();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PIZZA_REQ) {
            if (resultCode == RESULT_OK) {
                Pizza p = (Pizza)data.getSerializableExtra("pizza");
                double price = Double.parseDouble(data.getStringExtra("price"));
                o.addPizza(p,price);
                CharSequence text = o.getPizzaList().toString();
                Toast toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT);
                toast.show(); // temporary for testing, remove this
            }
        }
    }

    public void orderButtonInit() {
        btn4 = (ImageButton)findViewById(R.id.imageButton5);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phoneNumber == NOT_SET) {
                    String s = "Please order something before checking the current order.";
                    Toast toast = Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                Intent intent = new Intent(MainActivity.this,OrderActivity.class);
                String number = phoneNumber + "";
                intent.putExtra("phone_number",number);
                intent.putExtra("order_price",(o.getPrice()+""));
                startActivityForResult(intent,PIZZA_REQ);
            }
        });
    }

    /**
     * Initializes event handlers for buttons.
     */
    public void buttonInit() {
        btn1 = (ImageButton) findViewById(R.id.imageButton);
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
        orderInit();
        startActivityForResult(intent,PIZZA_REQ);
    }

    /**
     * Event handler for hawaiian pizza button click.
     */
    protected void hawaiianClick() {
        Intent intent = new Intent(this, PizzaActivity.class);
        intent.putExtra(PIZZA_TYPE,"Hawaiian");
        orderInit();
        startActivityForResult(intent,PIZZA_REQ);
    }

    /**
     * Event handler for pepperoni pizza button click.
     */
    protected void pepperoniClick() {
        Intent intent = new Intent(this, PizzaActivity.class);
        intent.putExtra(PIZZA_TYPE,"Pepperoni");
        orderInit();
        startActivityForResult(intent,PIZZA_REQ);
    }

    /**
     * Ensures text phone number is a valid entry before proceeding to other activities.
     * @return Whether or not phone number is valid.
     */
    protected boolean validPhoneNumber() {
        return (editTextPhoneNumber.getText().toString().length() == PHONE_LENGTH);
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

    protected void orderInit() {
        long tempPhone = Long.parseLong(editTextPhoneNumber.getText().toString());
        if (phoneNumber != tempPhone) {
            phoneNumber = tempPhone;
            o = new Order(phoneNumber);
        }
    }
}