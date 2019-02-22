package pa;

/**
 * Exception for invalid parts and products
 *
 * @author anthonyfreda
 */
public class ValidationException extends Exception {

    /**
     * Constructor. Takes a message
     *
     * @param message
     */
    public ValidationException(String message) {
        super(message);
    }
}
