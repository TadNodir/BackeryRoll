package edu.kit.informatik.elements;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.elements.backery.Cookie;
import edu.kit.informatik.elements.backery.Material;
import edu.kit.informatik.exceptions.SemanticException;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a player and its functionality on the game field
 *
 * @author Nodirjon Tadjiev
 * @version 1.0
 */
public class Players {
    /**
     * String embodying of the start field
     */
    private static final String START = "start";
    /**
     * Amount money, which players get after surpassing the start field
     */
    private final int surpassStart = 5;
    /**
     * One; needed amount to prepare certain cookies
     */
    private final int one = 1;
    /**
     * Two; needed amount to prepare certain cookies
     */
    private final int two = 2;
    /**
     * Three; needed amount to prepare certain cookies
     */
    private final int three = 3;
    /**
     * Amount of money, which the player should reach to win
     */
    private final int winningAmount = 100;
    /**
     * The amount of gold, which player gets, when it prepares all possible cookies
     */
    private final int masterLetterPrize = 25;
    // utility integer: index of the field, where the player stays
    private int fieldIndex = 0;
    // output of recipe, that can be prepared
    private String output = "";
    // utility integer: to count the amount of the raw materials player has
    private int materialAmount;
    // player id
    private final int id;
    // money margin of the player
    private MoneyMargin margin;
    // fields, where the player can roll
    private Fields fields;
    // field, where the player is now
    private char currentField;
    // embodying of the list of raw materials, the player contains
    private List<Material> playersMaterials = new ArrayList<>();
    // list of all cookies
    private List<Cookie> allCookiesList = new ArrayList<>();
    // embodying of the list of recipes/cookies, the player contains
    private List<Cookie> enumCookieList = new ArrayList<>();

    /**
     * Initializes a new player with given id.
     *
     * @param id     id of the player
     * @param margin player's left money
     * @param fields fields on the game board, where player plays
     */
    public Players(final int id, MoneyMargin margin, Fields fields) {
        this.id = id;
        this.margin = margin;
        this.fields = fields;
        this.currentField = 'S';
        this.allCookiesList.add(Cookie.YOGHURT);
        this.allCookiesList.add(Cookie.MERINGUE);
        this.allCookiesList.add(Cookie.BREAD);
        this.allCookiesList.add(Cookie.BUN);
        this.allCookiesList.add(Cookie.CREPE);
        this.allCookiesList.add(Cookie.PUDDING);
        this.allCookiesList.add(Cookie.CAKE);
    }

    /**
     * @return returns the player id
     */
    public int getId() {
        return this.id;
    }

    /**
     * @param number of fields the player moves forward
     * @return returns the current field, where the player is situated
     * @throws SemanticException when the player has less than 0 gold
     */
    public char rollIt(int number) throws SemanticException {
        this.fieldIndex = this.fieldIndex + number;
        this.currentField = fields.getFields().charAt(fieldIndex);
        if (this.currentField == 'S') {
            this.margin.setGoldAmount(this.margin.getGoldAmount() + surpassStart);
        }
        return this.currentField;
    }

    /**
     * @return returns string representation of the materials can be sold on each field
     */
    public String fieldsString() {
        switch (currentField) {
            case 'H':
                return Market.EGG;
            case 'M':
                return Market.FLOUR;
            case 'C':
                return Market.MILK;
            case 'S':
                return START;
            default:
                try {
                    throw new SemanticException("incorrect field type.");
                } catch (SemanticException e) {
                    e.printStackTrace();
                }
        }
        return null;
    }

    /**
     * Adds the given resource/raw materials to player larder,
     * which means adds the given material to the array list
     *
     * @param resource to be added
     * @throws SemanticException when incorrect resource type to add is typed
     */
    public void addPlayerMaterials(String resource) throws SemanticException {
        if (!(resource.equals(Market.EGG) || resource.equals(Market.MILK) || resource.equals(Market.FLOUR))) {
            throw new SemanticException("incorrect resource type to add");
        }
        if (resource.equals(Market.EGG)) {
            playersMaterials.add(new Material(null, null, Market.EGG));
        } else if (resource.equals(Market.MILK)) {
            playersMaterials.add(new Material(Market.MILK, null, null));
        } else {
            playersMaterials.add(new Material(null, Market.FLOUR, null));
        }
    }

    /**
     * Player gets a master letter and 25 Gold
     *
     * @throws SemanticException when money is less than 0
     */
    public void masterLetter() throws SemanticException {
        if (enumCookieList.containsAll(allCookiesList)) {
            getMargin().setGoldAmount(this.margin.getGoldAmount() + masterLetterPrize);
            enumCookieList.removeAll(allCookiesList);
        }
    }

    /**
     * @return returns the golf margin of the player
     */
    public MoneyMargin getMargin() {
        return margin;
    }

    /**
     * @return returns the output of the players gold amount and amount of raw materials it has
     */
    public String playerString() {
        try {
            return getMargin().getGoldAmount() + ";" + count(Market.FLOUR)
                    + ";" + count(Market.EGG) + ";" + count(Market.MILK);
        } catch (SemanticException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @return returns the player string with id
     */
    @Override
    public String toString() {
        return "P" + (this.id + 1);
    }

    /**
     * Counts the amount of raw materials the player has in its larder
     *
     * @param material to count in the player's larder
     * @return returns the amount of the materials in the player's larder
     * @throws SemanticException when the incorrect type of raw material was given
     */
    private int count(String material) throws SemanticException {
        switch (material) {
            case Market.EGG:
                materialAmount = 0;
                for (Material m : playersMaterials) {
                    if (m.getEgg() != null) {
                        materialAmount++;
                    }
                }
                return materialAmount;
            case Market.MILK:
                materialAmount = 0;
                for (Material m : playersMaterials) {
                    if (m.getMilk() != null) {
                        materialAmount++;
                    }
                }
                return materialAmount;
            case Market.FLOUR:
                materialAmount = 0;
                for (Material m : playersMaterials) {
                    if (m.getFlour() != null) {
                        materialAmount++;
                    }
                }
                return materialAmount;
            default:
                throw new SemanticException("incorrect type of raw material");
        }
    }

    /**
     * Prints out the recipes, that the player can prepare
     *
     * @throws SemanticException if the incorrect type of raw material was given
     */
    public void canBePrepared() throws SemanticException {
        if (count(Market.MILK) >= three) {
            output = output + Cookie.YOGHURT.getName() + "\n";
        }
        if (count(Market.EGG) >= three) {
            output = output + Cookie.MERINGUE.getName() + "\n";
        }
        if (count(Market.FLOUR) >= three) {
            output = output + Cookie.BREAD.getName() + "\n";
        }
        if (count(Market.FLOUR) >= two && count(Market.MILK) >= one) {
            output = output + Cookie.BUN.getName() + "\n";
        }
        if (count(Market.FLOUR) >= one && count(Market.EGG) >= two) {
            output = output + Cookie.CREPE.getName() + "\n";
        }
        if (count(Market.EGG) >= one && count(Market.MILK) >= two) {
            output = output + Cookie.PUDDING.getName() + "\n";
        }
        if (count(Market.EGG) >= two && count(Market.MILK) >= two && count(Market.FLOUR) >= two) {
            output = output + Cookie.CAKE.getName() + "\n";
        }
        if (output.length() > 0 && output.charAt(output.length() - 1) == '\n') {
            output = output.substring(0, output.length() - 1);
            Terminal.printLine(output);
        }
    }

    /**
     * Utility method: removes raw materials from the larder of player, which means, changing it to null
     *
     * @param material to be removed
     * @param times    amount of times the material should be removed
     */
    private void removeMaterials(String material, int times) {
        int k = 0;
        if (material.equals(Market.EGG)) {
            for (Material playersMaterial : playersMaterials) {
                if (playersMaterial.getEgg() != null) {
                    playersMaterial.setEgg(null);
                    k = k + 1;
                }
                if (k == times) {
                    break;
                }
            }
        } else if (material.equals(Market.MILK)) {
            for (Material playersMaterial : playersMaterials) {
                if (playersMaterial.getMilk() != null) {
                    playersMaterial.setMilk(null);
                    k = k + 1;
                }
                if (k == times) {
                    break;
                }
            }
        } else if (material.equals(Market.FLOUR)) {
            for (Material playersMaterial : playersMaterials) {
                if (playersMaterial.getFlour() != null) {
                    playersMaterial.setFlour(null);
                    k = k + 1;
                }
                if (k == times) {
                    break;
                }
            }
        }
    }

    /**
     * @return returns true, if player earns 100 golds
     * @throws SemanticException when the player has less than 0 golds
     */
    public boolean wins() throws SemanticException {
        return margin.getGoldAmount() >= winningAmount;
    }

    /**
     * Prepares the given recipe for the player, which means
     * removing needed materials from the player's array list, adding the cookie price
     * to player's gold margin and adding to player's cookies list
     *
     * @param cookie object representation of the recipe to be backed
     * @throws SemanticException when the player's gold amount is less than 0
     */
    public void prepareNew(String cookie) throws SemanticException {
        switch (cookie) {
            case Market.YOGHURT:
                if (count(Market.MILK) < three) {
                    throw new SemanticException("not enough milk to prepare yoghurt.");
                } else {
                    removeMaterials(Market.MILK, three);
                    getMargin().setGoldAmount(this.margin.getGoldAmount() + Cookie.YOGHURT.getPrice());
                    enumCookieList.add(Cookie.YOGHURT);
                }
                break;
            case Market.MERINGUE:
                if (count(Market.EGG) < three) {
                    throw new SemanticException("not enough milk to prepare meringue.");
                } else {
                    removeMaterials(Market.EGG, three);
                    getMargin().setGoldAmount(this.margin.getGoldAmount() + Cookie.MERINGUE.getPrice());
                    enumCookieList.add(Cookie.MERINGUE);
                }
                break;
            case Market.BREAD:
                if (count(Market.FLOUR) < three) {
                    throw new SemanticException("not enough milk to prepare bread.");
                } else {
                    removeMaterials(Market.FLOUR, three);
                    getMargin().setGoldAmount(this.margin.getGoldAmount() + Cookie.BREAD.getPrice());
                    enumCookieList.add(Cookie.BREAD);
                }
                break;
            case Market.BUN:
                if (count(Market.FLOUR) < two || count(Market.MILK) < one) {
                    throw new SemanticException("not enough milk to prepare bun.");
                } else {
                    removeMaterials(Market.FLOUR, two);
                    removeMaterials(Market.MILK, one);
                    getMargin().setGoldAmount(this.margin.getGoldAmount() + Cookie.BUN.getPrice());
                    enumCookieList.add(Cookie.BUN);
                }
                break;
            case Market.CREPE:
                if (count(Market.FLOUR) < one || count(Market.EGG) < two) {
                    throw new SemanticException("not enough milk to prepare crepe.");
                } else {
                    removeMaterials(Market.FLOUR, one);
                    removeMaterials(Market.EGG, two);
                    getMargin().setGoldAmount(this.margin.getGoldAmount() + Cookie.CREPE.getPrice());
                    enumCookieList.add(Cookie.CREPE);
                }
                break;
            case Market.PUDDING:
                if (count(Market.EGG) < one || count(Market.MILK) < two) {
                    throw new SemanticException("not enough milk to prepare pudding.");
                } else {
                    removeMaterials(Market.MILK, two);
                    removeMaterials(Market.EGG, one);
                    getMargin().setGoldAmount(this.margin.getGoldAmount() + Cookie.PUDDING.getPrice());
                    enumCookieList.add(Cookie.PUDDING);
                }
                break;
            case Market.CAKE:
                if (count(Market.EGG) < two || count(Market.MILK) < two || count(Market.FLOUR) < two) {
                    throw new SemanticException("not enough milk to prepare cake.");
                } else {
                    removeMaterials(Market.MILK, two);
                    removeMaterials(Market.EGG, two);
                    removeMaterials(Market.FLOUR, two);
                    getMargin().setGoldAmount(this.margin.getGoldAmount() + Cookie.CAKE.getPrice());
                    enumCookieList.add(Cookie.CAKE);
                }
                break;
            default:
                throw new SemanticException("incorrect input of recipe. Please, try again.");
        }
    }

    /**
     * @return returns the field, where the player currently stays
     */
    public char getCurrentField() {
        return currentField;
    }
}