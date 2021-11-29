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
import projectFiles.StoreOrders;

/**
 * Main activity class for ordering pizzas, looking at orders, and viewing store orders.
 * @author Andy Giang, Justin Rhodes
 */
public class MainActivity extends AppCompatActivity {

    public static final String PIZZA_TYPE = "com.example.project5_akg106_jgr85.MESSAGE";
    public static final int PIZZA_REQ = 1;
    public static final int ORDER_REQ = 2;
    public static final int STORE_REQ = 3;
    public static final int PHONE_LENGTH = 10;
    public static final int NOT_SET = -1;

    private ImageButton btn1;
    private ImageButton btn2;
    private ImageButton btn3;
    private ImageButton btn4;
    private ImageButton btn5;
    private EditText editTextPhoneNumber;

    private long phoneNumber = -1;
    private Order o;
    private StoreOrders so;

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
        so = new StoreOrders();
        storeButtonInit();
    }

    /**
     * Handles completion of activities for transfer of data.
     * @param requestCode Type of request from activity.
     * @param resultCode Result from activity.
     * @param data Data received from activity, containing various objects.
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PIZZA_REQ) {
            if (resultCode == RESULT_OK) {
                Pizza p = (Pizza) data.getSerializableExtra("pizza");
                double price = Double.parseDouble(data.getStringExtra("price"));
                o.addPizza(p, price);
                successToast();
            }
        }
        else if (requestCode == ORDER_REQ) {
            if (resultCode == RESULT_CANCELED) {
                o = (Order) data.getSerializableExtra("order_object");
            }
            else if (resultCode == RESULT_OK) {
                Order tempO = (Order) data.getSerializableExtra("new_order");
                so.add(tempO);
                o = null;
                editTextPhoneNumber.setText("");
                orderSuccessToast();
            }
        }
        else if (requestCode == STORE_REQ) {
            if (resultCode == RESULT_CANCELED) {
                so = (StoreOrders) data.getSerializableExtra("store_order");
            }
        }
    }

    /**
     * Initializes StoreOrderActivity view.
     */
    public void storeButtonInit() {
        btn5 = (ImageButton) findViewById(R.id.imageButton6);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (so.getOrders().size() == 0) {
                    emptyStoreToast();
                }
                else {
                    Intent intent = new Intent(MainActivity.this, StoreOrderActivity.class);
                    intent.putExtra("store_order", so);
                    startActivityForResult(intent, STORE_REQ);
                }
            }
        });
    }

    /**
     * Initializes OrderActivity view.
     */
    public void orderButtonInit() {
        btn4 = (ImageButton) findViewById(R.id.imageButton5);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phoneNumber == NOT_SET || o == null || o.getPizzaList().size() == 0) {
                    String s = "Please order something before checking the current order.";
                    Toast toast = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                String number = phoneNumber + "";
                intent.putExtra("phone_number", number);
                intent.putExtra("order_price", (o.getPrice() + ""));
                intent.putExtra("order_object", o);
                startActivityForResult(intent, ORDER_REQ);
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
                if (validOrder()) {
                    deluxeClick();
                }
            }
        });
        btn2 = (ImageButton) findViewById(R.id.imageButton3);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validOrder()) {
                    hawaiianClick();
                }
            }
        });
        btn3 = (ImageButton) findViewById(R.id.imageButton4);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validOrder()) {
                    pepperoniClick();
                }
            }
        });
    }

    /**
     * Event handler for deluxe pizza button click.
     */
    protected void deluxeClick() {
        Intent intent = new Intent(this, PizzaActivity.class);
        intent.putExtra(PIZZA_TYPE, "Deluxe");
        orderInit();
        startActivityForResult(intent, PIZZA_REQ);
    }

    /**
     * Event handler for hawaiian pizza button click.
     */
    protected void hawaiianClick() {
        Intent intent = new Intent(this, PizzaActivity.class);
        intent.putExtra(PIZZA_TYPE,"Hawaiian");
        orderInit();
        startActivityForResult(intent, PIZZA_REQ);
    }

    /**
     * Event handler for pepperoni pizza button click.
     */
    protected void pepperoniClick() {
        Intent intent = new Intent(this, PizzaActivity.class);
        intent.putExtra(PIZZA_TYPE,"Pepperoni");
        orderInit();
        startActivityForResult(intent, PIZZA_REQ);
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
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    /**
     * Initializes order for new phone numbers.
     */
    protected void orderInit() {
        long tempPhone = Long.parseLong(editTextPhoneNumber.getText().toString());
        if (phoneNumber != tempPhone) {
            phoneNumber = tempPhone;
            o = new Order(phoneNumber);
        }
    }

    /**
     * Toast for successful pizza addition.
     */
    protected void successToast() {
        Context context = getApplicationContext();
        CharSequence text = "Successfully added pizza.";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    /**
     * Toast for invalid order placement.
     */
    protected void existsToast() {
        Context context = getApplicationContext();
        CharSequence text = "Order has already been placed for given phone number.";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    /**
     * Checks whether or not an order is able to be placed.
     * @return true if order is able to be placed, false if otherwise.
     */
    protected boolean validOrder() {
        if (validPhoneNumber() == false) {
            phoneToast();
            return false;
        }
        long phoneNum = Long.parseLong(editTextPhoneNumber.getText().toString());
        if (so.hasOrder(phoneNum)) {
            existsToast();
            return false;
        }
        return true;
    }

    /**
     *  Toast for a successful order placement.
     */
    protected void orderSuccessToast() {
        Context context = getApplicationContext();
        CharSequence text = "Successfully placed order.";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    /**
     *  Toast for an empty store order list.
     */
    protected void emptyStoreToast() {
        Context context = getApplicationContext();
        CharSequence text = "Store order list is empty.";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}