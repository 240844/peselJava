
/**
 * Exception for errors related to the PESEL number.
 *
 * @author Kacper Zatoński
 * @version 1.0
 * @since JDK 23
 */
public class wrongPESELException extends Exception {
    public wrongPESELException(String message) {
        super(message);
    }
}
