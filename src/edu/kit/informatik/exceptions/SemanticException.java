package edu.kit.informatik.exceptions;

/**
 * This class represents the semantic exceptions arrangements
 *
 * @author Nodirjon Tadjiev
 * @version 1.0
 */
public class SemanticException extends Exception {
    /**
     * Serial version uid for serialization purposes
     */
    private static final long serialVersionUID = -6509769491390357651L;

    /**
     * A new Semantic exception, with corresponding message.
     *
     * @param message some detailed error message (null is not allowed)
     */
    public SemanticException(String message) {
        super(message);
    }
}
