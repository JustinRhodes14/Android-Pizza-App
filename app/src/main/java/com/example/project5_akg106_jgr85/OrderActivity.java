package com.example.project5_akg106_jgr85;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import projectFiles.Order;
import projectFiles.Pizza;

public class OrderActivity extends AppCompatActivity {

    private TextView phoneText;
    private TextView subtotal;
    private TextView salesTax;
    private TextView total;
    private ListView pizzaList;

    public static final double TAX_RATE = .06625;
    public static final double MIN_SUB_TOTAL = 0;
    public static final double FIX_SUB_TOTAL = -1;

    private ArrayList<Pizza> pizzas;
    private ArrayAdapter adapter1;
    private Order o;

    /**
     * Creates order activity and initializes views.
     * @param savedInstanceState Saved instance.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        phoneText = (TextView) findViewById(R.id.textView8);
        subtotal = (TextView) findViewById(R.id.textView12);
        salesTax = (TextView) findViewById(R.id.textView13);
        total = (TextView) findViewById(R.id.textView14);
        pizzaList = (ListView) findViewById(R.id.listView3);
        Intent data = getIntent();
        double price = Double.parseDouble(data.getStringExtra("order_price"));
        subtotal.setText(String.format("%,.2f", price));
        double saleTax = (price * TAX_RATE);
        double orderTotal = price + (price * TAX_RATE);
        salesTax.setText(String.format("%,.2f", saleTax));
        total.setText(String.format("%,.2f", orderTotal));
        phoneText.setText(data.getStringExtra("phone_number"));
        o = (Order) data.getSerializableExtra("order_object");

        pizzas = o.getPizzaList();
        adapter1 = new ArrayAdapter<>(this, R.layout.activity_listview, pizzas);
        pizzaList.setAdapter(adapter1);
        setListListener();
    }

    /**
     * Initializes listview listeners for tapping pizzas.
     */
    protected void setListListener() {
        pizzaList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new AlertDialog.Builder(OrderActivity.this)
                        .setTitle("Remove the following pizza?")
                        .setMessage(pizzas.get(position).toString())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                Toast.makeText(OrderActivity.this, "Pizza removed.", Toast.LENGTH_SHORT).show();
                                o.removePizza(position);
                                calculatePrices();
                                adapter1.notifyDataSetChanged();
                                if (o.getPizzaList().size() == 0) {
                                    emptyOrderToast();
                                }
                            }
                        })
                        .setNegativeButton(android.R.string.no,null).show();
            }
        });
    }

    /**
     * Alert for when user has empty order.
     */
    public void emptyOrderToast() {
        new AlertDialog.Builder(this)
                .setTitle("Empty Order")
                .setMessage("Order list empty, returning to main activity.")
                .setIcon(R.drawable.ic_launcher_foreground)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        Intent data = new Intent(OrderActivity.this, MainActivity.class);
                        data.putExtra("order_object", o);
                        setResult(RESULT_CANCELED, data);
                        finish();
                    }
                }).show();
    }

    /**
     * Method override to pass order data back on back pressed.
     */
    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent data = new Intent(OrderActivity.this, MainActivity.class);
        data.putExtra("order_object", o);
        setResult(RESULT_CANCELED, data);
        finish();
    }

    /**
     * Used to calculate subtotal, tax, and total for fxml view.
     */
    public void calculatePrices() {
        double sub = o.getPrice();
        if (sub < MIN_SUB_TOTAL) {
            sub *= FIX_SUB_TOTAL;
        }
        double tax = sub * TAX_RATE;
        double orderTotal = sub + tax;
        subtotal.setText(String.format("%,.2f", sub));
        salesTax.setText(String.format("%,.2f", tax));
        total.setText(String.format("%,.2f", orderTotal));
    }

    /**
     * Event handler for place order button.
     */
    public void placeOrder(View v) {
        long phoneNum = Long.parseLong(phoneText.getText().toString());
        Order tempO = new Order(phoneNum, this.pizzas, total.getText().toString());
        Intent intent = new Intent();
        intent.putExtra("new_order", tempO);
        setResult(RESULT_OK, intent);
        finish();
    }

    /**
     * Method override to prevent parent activity from resetting.
     * @param item the menu item selected.
     * @return true if back button is pressed, false if otherwise.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}