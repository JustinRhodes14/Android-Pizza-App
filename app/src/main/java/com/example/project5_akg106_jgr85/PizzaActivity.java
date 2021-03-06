package com.example.project5_akg106_jgr85;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import projectFiles.Deluxe;
import projectFiles.Hawaiian;
import projectFiles.Pepperoni;
import projectFiles.Pizza;
import projectFiles.PizzaMaker;
import projectFiles.Size;
import projectFiles.Topping;

/**
 * The activity class to handle orders for specific types of pizza, updates element content appropriately for each pizza.
 * @author Andy Giang, Justin Rhodes
 */
public class PizzaActivity extends AppCompatActivity {

    private ImageView imageView;
    private ListView additionalToppings;
    private ListView selectedToppings;
    private TextView priceText;
    private Spinner sizeSpinner;
    private Button addButton;

    public static final int SMALL = 1;
    public static final int MAX_TOPPINGS = 7;
    public static ArrayList<Topping> DEFAULT_TOPPINGS;
    public static final int SETUP = 1;
    public static final int NOT_SETUP = 0;

    private ArrayList<Topping> selItems;
    private ArrayList<Topping> addItems;
    private ArrayAdapter adapter1;
    private ArrayAdapter adapter2;
    private int currSize = SMALL;
    private int initial = SETUP;
    private String message;

    /**
     * Creates new activity for ordering pizzas.
     * @param savedInstanceState Saved instance.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.PIZZA_TYPE);
        this.message = message;
        initViews(message);
        priceText = (TextView) findViewById(R.id.textView7);
        addButton = (Button) findViewById(R.id.button5);
        priceText.setText(setPrice(message));
        initListeners();
        initSpinListen();
    }

    /**
     * Initializes the spinner functionality for changing the price.
     */
    protected void initSpinListen() {
        sizeSpinner = (Spinner) findViewById(R.id.spinner);
        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Event handler for item selection in spinner.
             * @param parent Parent of object.
             * @param view Activity view.
             * @param position Position of item.
             * @param id ID of item.
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                incrementPrice(currSize, Size.toSize((String)(sizeSpinner.getSelectedItem())));
            }

            /**
             * Do nothing method for when no item is selected in spinner.
             * @param parent Parent of object.
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    /**
     * Initializes the listviews and adapters for listviews, as well as priceText.
     */
    protected void initListeners() {
        additionalToppings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * Event handler for item selection in listview.
             * @param parent Parent of object.
             * @param view Activity view.
             * @param position Position of item.
             * @param id ID of item.
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (selItems.size() == MAX_TOPPINGS) {
                    addToast();
                    return;
                }

                selItems.add(addItems.get(position));
                addItems.remove(position);
                adapter1.notifyDataSetChanged();
                adapter2.notifyDataSetChanged();
                priceText.setText(String.format("%,.2f", (Double.parseDouble(priceText.getText().toString()) + Pizza.ADDITIONAL_TOPPING_PRICE)));
            }
        });
        selectedToppings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * Event handler for item selection in listview.
             * @param parent Parent of object.
             * @param view Activity view.
             * @param position Position of item.
             * @param id ID of item.
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (DEFAULT_TOPPINGS.contains(selItems.get(position))) {
                    defaultToast();
                    return;
                }
                addItems.add(selItems.get(position));
                selItems.remove(position);
                adapter1.notifyDataSetChanged();
                adapter2.notifyDataSetChanged();
                priceText.setText(String.format("%,.2f",
                        (Double.parseDouble(priceText.getText().toString()) - Pizza.ADDITIONAL_TOPPING_PRICE)));
            }
        });
    }

    /**
     * Initializes the image views of the activity, as well as the items in the listviews and default toppings.
     * @param message Message from the intent.
     */
    protected void initViews(String message) {
        imageView = (ImageView) findViewById(R.id.imageView);
        additionalToppings = (ListView) findViewById(R.id.listView2);
        selectedToppings = (ListView) findViewById(R.id.listView1);
        if (message.equals("Deluxe")) {
            imageView.setImageResource(R.drawable.deluxepizza);
            selItems = Deluxe.selectedComboBox();
            addItems = Deluxe.additionalComboBox();
            DEFAULT_TOPPINGS = Deluxe.selectedComboBox();
        }
        else if (message.equals("Hawaiian")) {
            imageView.setImageResource(R.drawable.hawaiianpizza);
            selItems = Hawaiian.selectedComboBox();
            addItems = Hawaiian.additionalComboBox();
            DEFAULT_TOPPINGS = Hawaiian.selectedComboBox();
        }
        else {
            imageView.setImageResource(R.drawable.pepperonipizza);
            selItems = Pepperoni.selectedComboBox();
            addItems = Pepperoni.additionalComboBox();
            DEFAULT_TOPPINGS = Pepperoni.selectedComboBox();
        }
        adapter1 = new ArrayAdapter<>(this, R.layout.activity_listview, addItems);
        adapter2 = new ArrayAdapter<>(this, R.layout.activity_listview, selItems);
        selectedToppings.setAdapter(adapter2);
        additionalToppings.setAdapter(adapter1);
    }

    /**
     * Sets the price text of the activity.
     * @param message Message from the intent.
     * @return Price of label.
     */
    protected CharSequence setPrice(String message) {
        if (message.equals("Deluxe")) {
            return "" + Pizza.DELUXE_PRICE;
        }
        else if (message.equals("Hawaiian")) {
            return "" + Pizza.HAWAIIAN_PRICE;
        }
        else {
            return "" + Pizza.PEPPERONI_PRICE;
        }
    }

    /**
     * Toast for invalid addition of toppings.
     */
    protected void addToast() {
        CharSequence text = "You may only have 7 toppings.";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getBaseContext(), text, duration);
        toast.show();
    }

    /**
     * Toast for invalid removal of toppings.
     */
    protected void defaultToast() {
        CharSequence text = "You cannot remove default toppings.";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getBaseContext(), text, duration);
        toast.show();
    }

    /**
     * Increments price for when new size is selected on spinner.
     * @param prevPrice Previous size price.
     * @param s Size to be set to.
     */
    public void incrementPrice(int prevPrice, Size s) {
        if (initial == SETUP) {
            initial = NOT_SETUP;
            return;
        }
        double currPrice = 0.0;
        if (s == Size.Medium) {
            if (prevPrice == Size.Small.getLabel()) {
                currPrice = Double.parseDouble(priceText.getText().toString()) + Pizza.MEDIUM;
            }
            else if (prevPrice == Size.Large.getLabel()) {
                currPrice = Double.parseDouble(priceText.getText().toString()) - Pizza.MEDIUM;
            }
            currSize = Size.Medium.getLabel();
        }
        else if (s == Size.Large) {
            if (prevPrice == Size.Small.getLabel()) {
                currPrice = Double.parseDouble(priceText.getText().toString()) + Pizza.LARGE;
            }
            else if (prevPrice == Size.Medium.getLabel()) {
                currPrice = Double.parseDouble(priceText.getText().toString()) + Pizza.MEDIUM;
            }
            currSize = Size.Large.getLabel();
        }
        else {
            if (prevPrice == Size.Large.getLabel()) {
                currPrice = Double.parseDouble(priceText.getText().toString()) - Pizza.LARGE;
            }
            else if (prevPrice == Size.Medium.getLabel()) {
                currPrice = Double.parseDouble(priceText.getText().toString()) - Pizza.MEDIUM;
            }
            currSize = Size.Small.getLabel();
        }
        priceText.setText(String.format("%,.2f", currPrice));
    }

    /**
     * Adds order to store orders in main activity.
     * @param v View.
     */
    public void addOrder(View v) {
        Pizza p = PizzaMaker.createPizza(message);
        p.addToppings(selItems);
        p.setSize(Size.toSize((String)(sizeSpinner.getSelectedItem())));
        Intent intent = new Intent();
        intent.putExtra("pizza", p);
        intent.putExtra("price", priceText.getText());
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