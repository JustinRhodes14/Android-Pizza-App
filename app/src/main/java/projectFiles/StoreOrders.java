package projectFiles;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Class that keeps the list of orders placed by the user.
 * Includes an export() method that saves the store order to an external text file.
 */
public class StoreOrders implements Serializable {

    private static final ArrayList<Order> ORDERS = new ArrayList<Order>();
    private static final DecimalFormat DF = new DecimalFormat("0.00");

    /**
     * Adds an order to the list of store orders.
     * @param o Order to be added to list.
     */
    public static void add(Order o) {
        ORDERS.add(o);
    }

    /**
     * Removes an order from the list of store orders.
     * @param phoneNumber the customer phone number.
     */
    public static void remove(String phoneNumber) {
        if (phoneNumber == null) {
            return;
        }
        //ORDERS.removeIf(order -> order.getPhoneNumber() == (Long.parseLong(phoneNumber)));

        for (int i = 0; i < ORDERS.size(); i++) {
            if (ORDERS.get(i).getPhoneNumber() == Long.parseLong(phoneNumber)) {
                ORDERS.remove(i);
                return;
            }
        }
    }

    /**
     * Checks the existence of an order in store orders with the current customer phone number.
     * @param phoneNumber the customer phone number.
     * @return true if the store orders already have an order with the current customer phone number,
     * false if otherwise.
     */
    public static boolean hasOrder(long phoneNumber) {
        for (Order o : ORDERS) {
            if (o.getPhoneNumber() == phoneNumber) {
                return true;
            }
        }
        return false;
    }

    /**
     * Getter method for all the orders in the store order.
     * @return a list of orders in the store orders.
     */
    public static ArrayList<Order> getOrders() {
        return ORDERS;
    }

    /**
     * Getter method for all order phone numbers for the store order.
     * @return a list of phone numbers in the store order list.
     */
    public static ArrayList<String> getNumbers() {
        ArrayList<String> phoneNumbers = new ArrayList<>();

        for (Order o : ORDERS) {
            phoneNumbers.add(o.getStringNumber());
        }

        return phoneNumbers;
    }

    /**
     * Returns order at specified index.
     * @param index Index in arraylist of desired order.
     * @return Specified order.
     */
    public Order getOrder(int index) {
        return ORDERS.get(index);
    }
}
