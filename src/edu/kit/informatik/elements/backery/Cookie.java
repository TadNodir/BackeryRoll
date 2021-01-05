package edu.kit.informatik.elements.backery;

/**
 * This enum represents the cookie recipes ant their price
 *
 * @author Nodirjon Tadjiev
 * @version 1.0
 */
public enum Cookie {
    /**
     * yoghurt
     */
    YOGHURT("yoghurt", 8),
    /**
     * meringue
     */
    MERINGUE("meringue", 9),
    /**
     * bread
     */
    BREAD("bread", 10),
    /**
     * bun
     */
    BUN("bun", 11),
    /**
     * crepe
     */
    CREPE("crepe", 12),
    /**
     * pudding
     */
    PUDDING("pudding", 13),
    /**
     * cake
     */
    CAKE("cake", 22);

    /**
     * represents the name of the recipe
     */
    private final String name;
    /**
     * represents the price of the recipe
     */
    private final int price;

    /**
     * Initialization of the name and price of the cookies
     *
     * @param name  recipe name
     * @param price recipe price
     */
    Cookie(String name, int price) {
        this.name = name;
        this.price = price;
    }

    /**
     * @return returns the string representation of the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return returns the integer representation of the cookie price
     */
    public int getPrice() {
        return price;
    }
}
