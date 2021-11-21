package projectFiles;

/**
 * Factory class to make pizzas.
 * @author Andy Giang, Justin Rhodes
 */
public class PizzaMaker {
    /**
     * Creates a pizza of specified flavor.
     * @param flavor Flavor of pizza (deluxe, pepperoni, hawaiian).
     * @return New instance of a pizza object.
     */
    public static Pizza createPizza(String flavor) {
        if (flavor.equals("Deluxe")) {
            return new Deluxe();
        }
        else if (flavor.equals("Pepperoni")) {
            return new Pepperoni();
        }
        else {
            return new Hawaiian();
        }
    }
}
