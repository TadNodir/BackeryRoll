package edu.kit.informatik.elements;

import edu.kit.informatik.exceptions.SemanticException;

/**
 * This class represents the money margin of the player
 *
 * @author Nodirjon Tadjiev
 * @version 1.0
 */
public class MoneyMargin {
    /**
     * Instant amount of gold of each player
     */
    private final int instantGold = 20;
    // player balance representation
    private int goldAmount;

    /**
     * Initialization of the money balance for each player at the start of the game
     */
    public MoneyMargin() {
        this.goldAmount = instantGold;
    }

    /**
     * @return returns the string representation of the money balance
     */
    @Override
    public String toString() {
        return String.valueOf(goldAmount);
    }

    /**
     * @return returns the integer representation of the money balance
     * @throws SemanticException when the money amount is under 0
     */
    public int getGoldAmount() throws SemanticException {
        if (goldAmount < 0) {
            throw new SemanticException("negative amount of gold is not allowed. In this case,"
                    + " player should DECLARE BANKRUPTCY");
        }
        return goldAmount;
    }

    /**
     * @param goldAmount sets the player money balance
     */
    public void setGoldAmount(int goldAmount) {
        this.goldAmount = goldAmount;
    }
}
