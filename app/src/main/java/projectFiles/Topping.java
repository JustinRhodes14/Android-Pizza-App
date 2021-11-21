package projectFiles;

/**
 * Interface for pizza toppings.
 * @author Andy Giang, Justin Rhodes
 */
public enum Topping {
    Cheese(1),
    Beef(2),
    Ham(3),
    Pineapple(4),
    BlackOlives(5),
    Sausage(7),
    GreenPepper(8),
    Onion(9),
    Pepperoni(10),
    Mushroom(11),
    Chicken(12);

    private final int LABEL;

    /**
     * Instantiates a topping object.
     * @param label Number label for a topping.
     */
    Topping(int label) {
        this.LABEL = label;
    }

}
