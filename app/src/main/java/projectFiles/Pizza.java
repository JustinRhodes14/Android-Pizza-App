package projectFiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class of a Pizza, used as a skeleton for subtypes of each pizza.
 * @author Andy Giang, Justin Rhodes
 */
public abstract class Pizza implements Serializable {
    protected ArrayList<Topping> toppings = new ArrayList<Topping>();
    protected Size size;

    /**
     * Abstract price method for all instances of pizza.
     * @return Price as a double.
     */
    public abstract double price();

    public static final double DELUXE_PRICE = 12.99;
    public static final double HAWAIIAN_PRICE = 10.99;
    public static final double PEPPERONI_PRICE = 8.99;
    public static final double ADDITIONAL_TOPPING_PRICE = 1.49;
    public static final double SMALL = 0.00;
    public static final double MEDIUM = 2.00;
    public static final double LARGE = 4.00;

    /**
     * Adds toppings to the pizza.
     * @param toppings List of toppings to be added.
     */
    public void addToppings(List<Topping> toppings) {
        for (Topping t: toppings) {
            if (!this.toppings.contains(t)) {
                this.toppings.add(t);
            }
        }
    }

    /**
     * Sets the size of the pizza.
     * @param s Size of pizza.
     */
    public void setSize(Size s) {
        this.size = s;
    }

    /**
     * toString method for pizza.
     * @return String format of a pizza object.
     */
    @Override
    public String toString() {
        String temp = getClass().toString();
        StringBuilder s = new StringBuilder(temp.substring(temp.indexOf(".") + 1) + " pizza, ");

        for (Topping t : toppings) {
            s.append(t.toString()).append(", ");
        }

        return s + "" + this.size + ", $" + String.format("%,.2f", this.price());
    }
}
