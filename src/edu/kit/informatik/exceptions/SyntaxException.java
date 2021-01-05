package edu.kit.informatik.exceptions;

/**
 * This class represents the syntax exceptions arrangements
 *
 * @author Nodirjon Tadjiev
 * @version 1.0
 */
public class SyntaxException extends Exception {
    /**
     * Serial version uid for serialization purposes
     */
    private static final long serialVersionUID = -8293537853359190045L;

    /**
     * A new Syntax exception, with corresponding message.
     *
     * @param message some detailed error message (null is not allowed)
     */
    public SyntaxException(String message) {
        super(message);
    }
}
