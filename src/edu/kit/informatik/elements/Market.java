package edu.kit.informatik.elements;

import edu.kit.informatik.elements.backery.Material;
import edu.kit.informatik.exceptions.SemanticException;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents the market of raw materials
 *
 * @author Nodirjon Tadjiev
 * @version 1.0
 */
public class Market {
    /**
     * unchangeable String representation of egg
     */
    public static final String EGG = "egg";
    /**
     * unchangeable String representation of milk
     */
    public static final String MILK = "milk";
    /**
     * unchangeable String representation of flour
     */
    public static final String FLOUR = "flour";
    /**
     * unchangeable String representation of yoghurt
     */
    public static final String YOGHURT = "yoghurt";
    /**
     * unchangeable String representation of meringue
     */
    public static final String MERINGUE = "meringue";
    /**
     * unchangeable String representation of bread
     */
    public static final String BREAD = "bread";
    /**
     * unchangeable String representation of bun
     */
    public static final String BUN = "bun";
    /**
     * unchangeable String representation of crepe
     */
    public static final String CREPE = "crepe";
    /**
     * unchangeable String representation of pudding
     */
    public static final String PUDDING = "pudding";
    /**
     * unchangeable String representation of cake
     */
    public static final String CAKE = "cake";


    // the utility integer to count keys of the map
    private int key;
    // the utility integer to count values of the map
    private int countMaterial;
    // representation of the raw materials stored in the market
    private Material aMaterial;
    // the map of the raw materials as values and their price as the map key
    private Map<Integer, Material> rawMaterial = new HashMap<>();

    /**
     * Initialization of the market, where there are two eggs, milk and flour each
     * Their price is 4 and 5 respectively
     */
    public Market() {
        for (int i = 5; i > 3; i--) {
            aMaterial = new Material(MILK, FLOUR, EGG);
            rawMaterial.put(i, aMaterial);
        }
        for (int i = 3; i > 0; i--) {
            aMaterial = new Material(null, null, null);
            rawMaterial.put(i, aMaterial);
        }
    }

    /**
     * Sells materials to the market, which means putting manufactured raw material to the map
     *
     * @param material to be sold
     * @throws SemanticException when the material is given incorrect
     */
    public void sellMaterials(String material) throws SemanticException {
        if (!(material.equals(EGG) || material.equals(FLOUR) || material.equals(MILK))) {
            throw new SemanticException("incorrect material type.");
        }

        if (material.equals(EGG)) {
            for (int i = rawMaterial.keySet().size(); i > 0; i--) {
                if (rawMaterial.get(i).getEgg() == null) {
                    aMaterial = new Material(rawMaterial.get(i).getMilk(), rawMaterial.get(i).getFlour(), EGG);
                    rawMaterial.put(i, aMaterial);
                    break;
                }
            }
        }
        if (material.equals(MILK)) {
            for (int i = rawMaterial.keySet().size(); i > 0; i--) {
                if (rawMaterial.get(i).getMilk() == null) {
                    aMaterial = new Material(MILK, rawMaterial.get(i).getFlour(), rawMaterial.get(i).getEgg());
                    rawMaterial.put(i, aMaterial);
                    break;
                }
            }
        } else if (material.equals(FLOUR)) {
            for (int i = rawMaterial.keySet().size(); i > 0; i--) {
                if (rawMaterial.get(i).getFlour() == null) {
                    aMaterial = new Material(rawMaterial.get(i).getMilk(), FLOUR, rawMaterial.get(i).getEgg());
                    rawMaterial.put(i, aMaterial);
                    break;
                }
            }
        }
    }

    /**
     * Buys raw material from the market, which means taking it from the map
     * and putting to the player's larder
     *
     * @param material to be bought
     * @throws SemanticException when the material is given incorrect
     */
    public void buyMaterials(String material) throws SemanticException {
        if (!(material.equals(EGG) || material.equals(FLOUR) || material.equals(MILK))) {
            throw new SemanticException("incorrect material type.");
        }

        if (material.equals(EGG)) {
            for (int i = 1; i <= rawMaterial.keySet().size(); i++) {
                if (rawMaterial.get(i).getEgg() != null) {
                    rawMaterial.get(i).setEgg(null);
                    break;
                }
            }
        } else if (material.equals(MILK)) {
            for (int i = 1; i <= rawMaterial.keySet().size(); i++) {
                if (rawMaterial.get(i).getMilk() != null) {
                    rawMaterial.get(i).setMilk(null);
                    break;
                }
            }
        } else {
            for (int i = 1; i <= rawMaterial.keySet().size(); i++) {
                if (rawMaterial.get(i).getFlour() != null) {
                    rawMaterial.get(i).setFlour(null);
                    break;
                }
            }
        }
    }

    /**
     * @param resource to get its key
     * @return returns the price of the resource(raw material), which means the least key of the raw material
     * @throws SemanticException when the material is given incorrect
     */
    public int getMaterialKey(String resource) throws SemanticException {
        if (!(resource.equals(EGG) || resource.equals(FLOUR) || resource.equals(MILK))) {
            throw new SemanticException("incorrect material type.");
        }
        if (resource.equals(EGG)) {
            key = 0;
            for (int i = 1; i <= rawMaterial.keySet().size(); i++) {
                if (rawMaterial.get(i).getEgg() != null) {
                    key = i;
                    break;
                }
            }
            return key;
        } else if (resource.equals(MILK)) {
            key = 0;
            for (int i = 1; i <= rawMaterial.keySet().size(); i++) {
                if (rawMaterial.get(i).getMilk() != null) {
                    key = i;
                    break;
                }
            }
            return key;
        } else {
            key = 0;
            for (int i = 1; i <= rawMaterial.keySet().size(); i++) {
                if (rawMaterial.get(i).getFlour() != null) {
                    key = i;
                    break;
                }
            }
            return key;
        }
    }

    /**
     * @param playerMoney money before buying the raw material
     * @param resource    bought raw material
     * @return returns the money margin of the player after buying the raw material
     * @throws SemanticException
     */
    public int playerBuys(int playerMoney, String resource) throws SemanticException {
        return playerMoney - getMaterialKey(resource);
    }

    /**
     * @param materialName which has to be counted
     * @param map          the map, which represents the market
     * @return returns the amount of the raw material in the market
     */
    private int countValues(String materialName, Map<Integer, Material> map) throws SemanticException {
        switch (materialName) {
            case FLOUR:
                countMaterial = 0;
                for (Material s : map.values()) {
                    if (s.getFlour() != null) {
                        countMaterial++;
                    }
                }
                return countMaterial;
            case EGG:
                countMaterial = 0;
                for (Material s : map.values()) {
                    if (s.getEgg() != null) {
                        countMaterial++;
                    }
                }
                return countMaterial;
            case MILK:
                countMaterial = 0;
                for (Material s : map.values()) {
                    if (s.getMilk() != null) {
                        countMaterial++;
                    }
                }
                return countMaterial;
            default:
                throw new SemanticException("incorrect raw material type is written.");
        }
    }

    /**
     * @return returns the string representation of the market: amount and raw material name
     */
    @Override
    public String toString() {
        try {
            return countValues(EGG, rawMaterial) + ";" + EGG
                    + "\n" + countValues(MILK, rawMaterial) + ";" + MILK
                    + "\n" + countValues(FLOUR, rawMaterial) + ";" + FLOUR;
        } catch (SemanticException e) {
            e.printStackTrace();
        }
        return null;
    }
}
