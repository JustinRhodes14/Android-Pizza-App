package projectFiles;

import java.util.ArrayList;
import java.util.List;

/**
 * Deluxe pizza class specifying all attributes of a deluxe pizza.
 * @author Andy Giang, Justin Rhodes
 */
public class Deluxe extends Pizza {

    private static final int MIN_DELUXE_TOPPING = 5;

    /**
     * Instantiates a Deluxe pizza object setting the default size and toppings.
     */
    public Deluxe() {
        this.size = Size.Small;
        this.toppings.add(Topping.Sausage);
        this.toppings.add(Topping.GreenPepper);
        this.toppings.add(Topping.Onion);
        this.toppings.add(Topping.Pepperoni);
        this.toppings.add(Topping.Mushroom);
    }

    /**
     * Gets the price for a deluxe pizza.
     * @return Pizza price.
     */
    public double price() {
        double sizePrice = SMALL;
        if (this.size == Size.Medium) {
            sizePrice += MEDIUM;
        }
        else if (this.size == Size.Large) {
            sizePrice += LARGE;
        }
        if (this.toppings.size() > MIN_DELUXE_TOPPING) {
            double additional = this.toppings.size() - MIN_DELUXE_TOPPING;
            return sizePrice + DELUXE_PRICE + additional * ADDITIONAL_TOPPING_PRICE;
        }
        return sizePrice + DELUXE_PRICE;
    }

    /**
     * Instantiates selectedListView for deluxe pizzas.
     * @return List of toppings by default for deluxe pizzas.
     */
    public static ArrayList<Topping> selectedComboBox() {
        ArrayList<Topping> toppings = new ArrayList<Topping>();

        toppings.add(Topping.Sausage);
        toppings.add(Topping.GreenPepper);
        toppings.add(Topping.Onion);
        toppings.add(Topping.Pepperoni);
        toppings.add(Topping.Mushroom);

        return toppings;
    }

    /**
     * Instantiates additionalListView for deluxe pizzas.
     * @return List of toppings by not included by default for deluxe pizzas.
     */
    public static ArrayList<Topping> additionalComboBox() {
        ArrayList<Topping> toppings = new ArrayList<Topping>();

        toppings.add(Topping.Chicken);
        toppings.add(Topping.Beef);
        toppings.add(Topping.Ham);
        toppings.add(Topping.Pineapple);
        toppings.add(Topping.BlackOlives);
        toppings.add(Topping.Cheese);

        return toppings;
    }
}
