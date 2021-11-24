package com.example.project5_akg106_jgr85;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    private TextView phoneText;
    private TextView subtotal;
    private TextView salesTax;
    private TextView total;

    private static final double TAX_RATE = .06625;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        phoneText = (TextView)findViewById(R.id.textView8);
        subtotal = (TextView)findViewById(R.id.textView12);
        salesTax = (TextView)findViewById(R.id.textView13);
        total = (TextView)findViewById(R.id.textView14);
        Intent data = getIntent();
        double price = Double.parseDouble(data.getStringExtra("order_price"));
        subtotal.setText((price + ""));
        double saleTax = (price * TAX_RATE);
        double orderTotal = price + (price * TAX_RATE);
        salesTax.setText(String.format("%,.2f", saleTax));
        total.setText(String.format("%,.2f", orderTotal));
        phoneText.setText(data.getStringExtra("phone_number"));

    }
}