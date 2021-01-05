package edu.kit.informatik.elements.backery;

/**
 * This class represents raw materials, from which recipe is prepared
 *
 * @author Nodirjon Tadjiev
 * @version 1.0
 */
public class Material {
    // cell to be filled by string representation of milk
    private String milk;
    // cell to be filled by string representation of flour
    private String flour;
    // cell to be filled by string representation of egg
    private String egg;

    /**
     * Initialization of the raw materials
     *
     * @param milk  to be filled by milk string representation
     * @param flour to be filled by flour string representation
     * @param egg   to be filled by egg string representation
     */
    public Material(String milk, String flour, String egg) {
        this.milk = milk;
        this.flour = flour;
        this.egg = egg;
    }

    /**
     * @return returns milk string representation
     */
    public String getMilk() {
        return milk;
    }

    /**
     * @param milk sets the milk string representation
     */
    public void setMilk(String milk) {
        this.milk = milk;
    }

    /**
     * @return returns flour string representation
     */
    public String getFlour() {
        return flour;
    }

    /**
     * @param flour sets the flour string representation
     */
    public void setFlour(String flour) {
        this.flour = flour;
    }

    /**
     * @return returns egg string representation
     */
    public String getEgg() {
        return egg;
    }

    /**
     * @param egg sets the egg string representation
     */
    public void setEgg(String egg) {
        this.egg = egg;
    }
}
