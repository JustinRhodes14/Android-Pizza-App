package projectFiles;

import java.util.ArrayList;
import java.util.List;

/**
 * Pepperoni pizza class specifying all attributes of a pepperoni pizza.
 * @author Andy Giang, Justin Rhodes
 */
public class Pepperoni extends Pizza {

    private static final int MIN_PEPPERONI_TOPPING = 2;

    /**
     * Instantiates a Pepperoni pizza object setting the default size and toppings.
     */
    public Pepperoni() {
        this.size = Size.Small;
        this.toppings.add(Topping.Cheese);
        this.toppings.add(Topping.Pepperoni);
    }

    /**
     * Gets the price for a pepperoni pizza.
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
        if (this.toppings.size() > MIN_PEPPERONI_TOPPING) {
            double additional = this.toppings.size() - MIN_PEPPERONI_TOPPING;
            return sizePrice + PEPPERONI_PRICE + additional * ADDITIONAL_TOPPING_PRICE;
        }
        return sizePrice + PEPPERONI_PRICE;
    }

    /**
     * Instantiates selectedListView for pepperoni pizzas.
     * @return List of toppings by default for pepperoni pizzas.
     */
    public static ArrayList<Topping> selectedComboBox() {
        ArrayList<Topping> toppings = new ArrayList<Topping>();

        toppings.add(Topping.Pepperoni);
        toppings.add(Topping.Cheese);

        return toppings;
    }

    /**
     * Instantiates additionalListView for pepperoni pizzas.
     * @return List of toppings by not included by default for pepperoni pizzas.
     */
    public static ArrayList<Topping> additionalComboBox() {
        ArrayList<Topping> toppings = new ArrayList<Topping>();

        toppings.add(Topping.Chicken);
        toppings.add(Topping.Sausage);
        toppings.add(Topping.GreenPepper);
        toppings.add(Topping.Onion);
        toppings.add(Topping.Mushroom);
        toppings.add(Topping.Beef);
        toppings.add(Topping.Ham);
        toppings.add(Topping.Pineapple);
        toppings.add(Topping.BlackOlives);

        return toppings;
    }
}
