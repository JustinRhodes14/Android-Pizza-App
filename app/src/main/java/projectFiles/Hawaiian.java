package projectFiles;

import java.util.ArrayList;
import java.util.List;

/**
 * Hawaiian pizza class specifying all attributes of a hawaiian pizza.
 * @author Andy Giang, Justin Rhodes
 */
public class Hawaiian extends Pizza {

    private static final int MIN_HAWAIIAN_TOPPING = 3;

    /**
     * Instantiates a Hawaiian pizza object setting the default size and toppings.
     */
    public Hawaiian() {
        this.size = Size.Small;
        this.toppings.add(Topping.Ham);
        this.toppings.add(Topping.Cheese);
        this.toppings.add(Topping.Pineapple);
    }

    /**
     * Gets the price for a hawaiian pizza.
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
        if (this.toppings.size() > MIN_HAWAIIAN_TOPPING) {
            double additional = this.toppings.size() - MIN_HAWAIIAN_TOPPING;
            return sizePrice + HAWAIIAN_PRICE + additional * ADDITIONAL_TOPPING_PRICE;
        }
        return sizePrice + HAWAIIAN_PRICE;
    }

    /**
     * Instantiates selectedListView for hawaiian pizzas.
     * @return List of toppings by default for hawaiian pizzas.
     */
    public static ArrayList<Topping> selectedComboBox() {
        ArrayList<Topping> toppings = new ArrayList<Topping>();

        toppings.add(Topping.Pineapple);
        toppings.add(Topping.Ham);
        toppings.add(Topping.Cheese);

        return toppings;
    }

    /**
     * Instantiates additionalListView for hawaiian pizzas.
     * @return List of toppings by not included by default for hawaiian pizzas.
     */
    public static ArrayList<Topping> additionalComboBox() {
        ArrayList<Topping> toppings = new ArrayList<Topping>();

        toppings.add(Topping.Chicken);
        toppings.add(Topping.Sausage);
        toppings.add(Topping.GreenPepper);
        toppings.add(Topping.Onion);
        toppings.add(Topping.Mushroom);
        toppings.add(Topping.Beef);
        toppings.add(Topping.BlackOlives);

        return toppings;
    }
}
