package com.example.project5_akg106_jgr85;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    public static final String PIZZA_TYPE = "com.example.project5_akg106_jgr85.MESSAGE";

    private ImageButton btn1;
    private ImageButton btn2;
    private ImageButton btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (ImageButton) findViewById(R.id.imageButton);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deluxeClick();
            }
        });
        btn2 = (ImageButton) findViewById(R.id.imageButton3);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hawaiianClick();
            }
        });
        btn3 = (ImageButton) findViewById(R.id.imageButton4);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pepperoniClick();
            }
        });
    }

    protected void deluxeClick() {
        Intent intent = new Intent(this, PizzaActivity.class);
        intent.putExtra(PIZZA_TYPE,"Deluxe");
        startActivity(intent);
    }

    protected void hawaiianClick() {
        Intent intent = new Intent(this, PizzaActivity.class);
        intent.putExtra(PIZZA_TYPE,"Hawaiian");
        startActivity(intent);
    }

    protected void pepperoniClick() {
        Intent intent = new Intent(this, PizzaActivity.class);
        intent.putExtra(PIZZA_TYPE,"Pepperoni");
        startActivity(intent);
    }
}