package edu.kit.informatik.elements.field;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.elements.Market;
import edu.kit.informatik.elements.Players;
import edu.kit.informatik.exceptions.SemanticException;

/**
 * This class represents the game board and all possible functions, the player can do on it
 *
 * @author Nodirjon Tadjiev
 * @version 1.0
 */
public class Board {
    // index of the current active player, which is initialized
    private int currentActivePlayerIndex = 0;
    // players array, embodying each player and its functions
    private Players[] players;
    // market, where all raw materials ara placed
    private Market market;

    /**
     * Initialization of the game on the board and its necessities
     *
     * @param players playing players array
     * @param market  market of the raw materials
     */
    public Board(Players[] players, Market market) {
        this.players = players;
        this.market = market;
    }

    /**
     * @return returns the current active player
     */
    public Players getCurrentActivePlayer() {
        return this.players[this.currentActivePlayerIndex];
    }

    /**
     * Changes the current active player
     */
    private void changeCurrentActivePlayer() {
        this.currentActivePlayerIndex = (this.currentActivePlayerIndex + 1) % this.players.length;
    }

    /**
     * Rolls on the board from one field to another depending on the given number
     * Prints out the current player position and its money margin
     *
     * @param number fields forward from the current position
     * @throws SemanticException when the player's money is less than 0
     */
    public void roll(int number) throws SemanticException {
        if (number < 1 || number > 6) {
            throw new SemanticException("the number is out of possible rolling numbers");
        }
        Terminal.printLine(players[getCurrentActivePlayer().getId()].rollIt(number) + ";"
                + players[getCurrentActivePlayer().getId()].getMargin().getGoldAmount());
    }

    /**
     * Changes an active player, prints out the current active player
     */
    public void turn() {
        changeCurrentActivePlayer();
        Terminal.printLine(getCurrentActivePlayer());
    }

    /**
     * Manufactures the raw material, where the player is staying and sells to the market,
     * which brings the player one gold profit
     *
     * @throws SemanticException when an incorrect material type is given
     */
    public void harvest() throws SemanticException {
        switch (getCurrentActivePlayer().fieldsString()) {
            case Market.EGG:
                market.sellMaterials(Market.EGG);
                getCurrentActivePlayer().getMargin()
                        .setGoldAmount(getCurrentActivePlayer().getMargin().getGoldAmount() + 1);
                Terminal.printLine(Market.EGG + ";" + getCurrentActivePlayer().getMargin().getGoldAmount());
                if (getCurrentActivePlayer().wins()) {
                    Terminal.printLine(getCurrentActivePlayer().toString() + " wins");
                }
                break;
            case Market.MILK:
                market.sellMaterials(Market.MILK);
                getCurrentActivePlayer().getMargin()
                        .setGoldAmount(getCurrentActivePlayer().getMargin().getGoldAmount() + 1);
                Terminal.printLine(Market.MILK + ";" + getCurrentActivePlayer().getMargin().getGoldAmount());
                if (getCurrentActivePlayer().wins()) {
                    Terminal.printLine(getCurrentActivePlayer().toString() + " wins");
                }
                break;
            case Market.FLOUR:
                market.sellMaterials(Market.FLOUR);
                getCurrentActivePlayer().getMargin()
                        .setGoldAmount(getCurrentActivePlayer().getMargin().getGoldAmount() + 1);
                Terminal.printLine(Market.FLOUR + ";" + getCurrentActivePlayer().getMargin().getGoldAmount());
                if (getCurrentActivePlayer().wins()) {
                    Terminal.printLine(getCurrentActivePlayer().toString() + " wins");
                }
                break;
            default:
                Terminal.printError("incorrect type of raw material is given");
        }
    }

    /**
     * Buys the given raw material from the market
     *
     * @param resource raw material to be bought
     * @throws SemanticException when player's money is not enough
     *                           or when the material is not in the market
     */
    public void buy(String resource) throws SemanticException {
        int playerCurrentMoney = market.playerBuys(getCurrentActivePlayer().getMargin().getGoldAmount(), resource);
        if (playerCurrentMoney < 0) {
            throw new SemanticException("player does not have enough money to buy " + resource);
        }
        if (getCurrentActivePlayer().getCurrentField() == 'S') {
            throw new SemanticException("cannot buy anything. Staying at the start field.");
        }
        if (market.getMaterialKey(resource) <= 0) {
            throw new SemanticException("the market does not contain " + resource + " anymore.");
        }
        Terminal.printLine(market.getMaterialKey(resource) + ";" + playerCurrentMoney);
        getCurrentActivePlayer().getMargin().setGoldAmount(playerCurrentMoney);
        market.buyMaterials(resource);
        getCurrentActivePlayer().addPlayerMaterials(resource);
    }

    /**
     * Prints out the money margin and raw materials of the given player
     *
     * @param player its money margin and raw materials are shown
     * @param index  of the given player
     *               * @throws SemanticException when given too high pr too low number of player
     */
    public void showPlayer(String player, int index) throws SemanticException {
        if (index > this.players.length) {
            throw new SemanticException("given too high number of player");
        }
        if (index - 1 < 0) {
            throw new SemanticException("given too low number of player");
        }
        if (this.players[index - 1].toString().equals(player)) {
            Terminal.printLine(this.players[index - 1].playerString());
        }
    }

    /**
     * Prepares the recipe from raw materials, which player has
     *
     * @param recipe is prepared
     * @throws SemanticException when the player does not have enough money
     */
    public void prepare(String recipe) throws SemanticException {
        getCurrentActivePlayer().prepareNew(recipe);
        getCurrentActivePlayer().masterLetter();
        Terminal.printLine(getCurrentActivePlayer().getMargin().getGoldAmount());
        if (getCurrentActivePlayer().wins()) {
            Terminal.printLine(getCurrentActivePlayer().toString() + " wins");
        }
    }

    /**
     * Prints out which recipes a player can prepare
     *
     * @throws SemanticException if incorrect type of raw material
     */
    public void canPrepare() throws SemanticException {
        getCurrentActivePlayer().canBePrepared();
    }

    /**
     * @return returns the market object
     */
    public Market getMarket() {
        return market;
    }
}
