package projectFiles;

/**
 * Interface for pizza sizes.
 * @author Andy Giang, Justin Rhodes
 */
public enum Size {
    Small(1),
    Medium(2),
    Large(3);

    private final int LABEL;

    /**
     * Instantiates a size enum.
     * @param label Number label of size.
     */
    Size(int label) {
        this.LABEL = label;
    }

    /**
     * Getter method for size of pizza.
     * @return the label corresponding to the size of pizza.
     */
    public int getLabel() {
        return this.LABEL;
    }

    /**
     * Converts a size from String to Size type.
     * @param s the size of pizza in String type
     * @return the size of pizza in Size type.
     */
    public static Size toSize(String s) {
        if (s.equals("Small")) {
            return Small;
        }
        else if (s.equals("Medium")) {
            return Medium;
        }
        return Large;
    }
}
