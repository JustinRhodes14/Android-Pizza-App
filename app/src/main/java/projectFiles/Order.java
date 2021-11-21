package projectFiles;

import java.util.ArrayList;

/**
 * Class to hold a list of pizzas and a users phone number, used to be added to the store order list.
 * @author Andy Giang, Justin Rhodes
 */
public class Order {
    private final long phoneNumber;
    private String stringNumber;
    private final ArrayList<Pizza> pizzaList;
    private double price;

    /**
     * Constructor for Order class, initializes an Order object.
     * @param phoneNumber the customer phone number.
     */
    public Order(long phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.pizzaList = new ArrayList<Pizza>();
        this.price = 0.0;
    }

    /**
     * Constructor for Order class that also takes in a list of pizzas and the order total as inputs.
     * @param phoneNumber the customer phone number.
     * @param order the list of pizzas in the order.
     * @param total the order total.
     */
    public Order(long phoneNumber, ArrayList<Pizza> order, String total) {
        this.phoneNumber = phoneNumber;
        this.pizzaList = order;
        this.price = Double.parseDouble(total);
    }

    /**
     * Adds a pizza to the order list.
     * @param p Pizza to be added.
     * @param price Price of said pizza.
     */
    public void addPizza(Pizza p, double price) {
        this.pizzaList.add(p);
        this.price += price;
    }

    /**
     * Getter method for pizzaList attribute.
     * @return ArrayList of pizzas.
     */
    public ArrayList<Pizza> getPizzaList() {
        return this.pizzaList;
    }

    /**
     * Getter method for price attribute of an order.
     * @return Price of order.
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Getter method for customer phone number.
     * @return customer phone number.
     */
    public long getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Removes specified pizza from pizzaList.
     * @param index Index of pizza to be removed.
     * @return Pizza that was removed.
     */
    public Pizza removePizza(int index) {
        this.price -= pizzaList.get(index).price();
        return this.pizzaList.remove(index);
    }

    /**
     * Sets stringPhoneNumber for comboBox purposes.
     * @param s String for stringNumber to be set to.
     */
    public void setStringNumber(String s) {
        this.stringNumber = s;
    }

    /**
     * Getter method for stringNumber variable.
     * @return The stringNumber.
     */
    public String getStringNumber() {
        return this.stringNumber;
    }
}
