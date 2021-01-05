package edu.kit.informatik.elements;

import edu.kit.informatik.exceptions.SemanticException;

/**
 * This class represents fields, from which the game board consists of
 *
 * @author Nodirjon Tadjiev
 * @version 1.0
 */
public class Fields {
    // string representation of the circulating game fields
    private final String fields;

    /**
     * Initialization of the circulating game fields
     *
     * @param fields of the game board
     * @throws SemanticException when an incorrect field is typed
     */
    public Fields(final String fields) throws SemanticException {
        if (!expressionCheck(fields)) {
            throw new SemanticException("incorrect field name.");
        }
        this.fields = fields;
    }

    /**
     * @param possibleChar possible characters to be typed as fields
     * @return true if correct characters of fields are given
     */
    private static boolean expressionCheck(final String possibleChar) {
        return possibleChar.matches("[MHCS]*");
    }

    /**
     * @return the string representation of the circulating fields
     */
    public String getFields() {
        return fields;
    }
}
