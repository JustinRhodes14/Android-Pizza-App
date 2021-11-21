package com.example.project5_akg106_jgr85;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import projectFiles.Deluxe;
import projectFiles.Hawaiian;
import projectFiles.Pepperoni;
import projectFiles.Topping;

public class PizzaActivity extends AppCompatActivity {

    String tempArray[] = {"Hello","Happy","Helpful","Handy"};
    private ImageView imageView;
    private ListView listView1;
    private ListView listView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.PIZZA_TYPE);

        imageView = (ImageView)findViewById(R.id.imageView);
        listView1 = (ListView)findViewById(R.id.listView1);
        listView2 = (ListView)findViewById(R.id.listView2);
        ArrayList<Topping> listItems1;
        ArrayList<Topping> listItems2;
        if (message.equals("Deluxe")) {
            imageView.setImageResource(R.drawable.deluxepizza);
            listItems1 = Deluxe.selectedComboBox();
            listItems2 = Deluxe.additionalComboBox();
        } else if (message.equals("Hawaiian")) {
            imageView.setImageResource(R.drawable.hawaiianpizza);
            listItems1 = Hawaiian.selectedComboBox();
            listItems2 = Hawaiian.additionalComboBox();
        } else {
            imageView.setImageResource(R.drawable.pepperonipizza);
            listItems1 = Pepperoni.selectedComboBox();
            listItems2 = Pepperoni.additionalComboBox();
        }
        ArrayAdapter adapter1 = new ArrayAdapter<Topping>(this,R.layout.activity_listview,listItems2);
        ArrayAdapter adapter2 = new ArrayAdapter<Topping>(this,R.layout.activity_listview,listItems1);
        listView2.setAdapter(adapter1);
        listView1.setAdapter(adapter2);
    }
}