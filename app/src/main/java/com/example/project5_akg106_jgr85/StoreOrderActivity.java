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
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import projectFiles.Order;
import projectFiles.Pizza;
import projectFiles.StoreOrders;

/**
 * Store order activity to view and interact with the list of orders.
 * @author Andy Giang, Justin Rhodes
 */
public class StoreOrderActivity extends AppCompatActivity {

    private Spinner numSpinner;
    private TextView totalText;
    private ListView orderList;

    public static final boolean NOT_CANCELABLE = false;

    private StoreOrders so;
    private ArrayList<Pizza> pizzas;
    private ArrayAdapter adapter1;
    private ArrayList<String> phones;
    private ArrayAdapter adapter2;
    private int currentSpin = -1; //no item selected

    /**
     * Creates new activity for checking store orders.
     * @param savedInstanceState Saved instance.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_order);
        orderList = (ListView) findViewById(R.id.listView3);
        numSpinner = (Spinner) findViewById(R.id.spinner3);
        totalText = (TextView) findViewById(R.id.textView18);
        Intent data = getIntent();
        so = (StoreOrders) data.getSerializableExtra("store_order");
        spinnerInit();
    }

    /**
     * Initializes the spinner object on the store order activity.
     */
    protected void spinnerInit() {
        phones = so.getNumbers();
        adapter2 = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, phones);
        numSpinner.setAdapter(adapter2);
        numSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Event handler for spinner items being selected.
             * @param parent Parent of spinner object.
             * @param view Activity view.
             * @param position Position of item.
             * @param id Item id.
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentSpin = position;
                Order o = so.getOrder(position);
                pizzas = o.getPizzaList();
                adapter1 = new ArrayAdapter<>(StoreOrderActivity.this, R.layout.activity_listview,pizzas);
                orderList.setAdapter(adapter1);
                totalText.setText((String.format("%,.2f", o.getPrice())));
            }

            /**
             * Do nothing method for spinner object.
             * @param parent Parent of object.
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    /**
     * Listener to remove an order from the spinner list.
     * @param v Object view for button.
     */
    public void removeOrder(View v) {
        so.remove(phones.get(currentSpin));
        phones.remove(currentSpin);
        adapter2.notifyDataSetChanged();
        if (phones.size() == 0) {
            orderList.clearChoices();
            totalText.setText("");
            new AlertDialog.Builder(this)
                    .setTitle("Empty Store Order")
                    .setMessage("Store orders empty, returning to main screen.")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setCancelable(NOT_CANCELABLE)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                        /**
                         * Event handler for spinner list when said list is empty.
                         * @param dialog Dialog to show.
                         * @param whichButton Buttons to show.
                         */
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Intent data = new Intent(StoreOrderActivity.this, MainActivity.class);
                            data.putExtra("store_order",so);
                            setResult(RESULT_CANCELED, data);
                            finish();
                        }
                    }).show();
        }
    }

    /**
     * Method override to pass order data back on back pressed.
     */
    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent data = new Intent(StoreOrderActivity.this, MainActivity.class);
        data.putExtra("store_order", so);
        setResult(RESULT_CANCELED, data);
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