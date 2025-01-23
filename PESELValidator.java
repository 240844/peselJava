import java.time.LocalDate;


/**
 * The PESELValidator class provides utility methods to validate and process PESEL numbers.
 *
 * <p>This class includes methods for validating the PESEL's format, extracting and validating
 * the date of birth, and verifying the control digit.</p>
 *
 * @author Kacper Zatoński
 * @version 1.0
 * @since JDK 23
 */
public class PESELValidator {

    private static final String PESEL_PATTERN = "\\d{11}";

    /**
    * Checks whether the given PESEL contains only numeric characters.
    *
    * @param PESEL the PESEL number to validate
    * @return {@code true} if the PESEL contains only numeric characters, {@code false} otherwise
    */
    public static boolean containsOnlyNumbers(String PESEL) {
        return PESEL.matches(PESEL_PATTERN);
    }

    /**
     * Validates and extracts the date of birth encoded in the PESEL number.
     *
     * @param PESEL the PESEL number to validate and process
     * @return the date of birth as a {@link LocalDate} object
     * @throws wrongPESELException if the date is invalid or cannot be parsed
     */
    public static LocalDate validateDate(String PESEL) throws wrongPESELException{
        int year = Integer.parseInt(PESEL.substring(0, 2));
        int month = Integer.parseInt(PESEL.substring(2, 4));
        int day = Integer.parseInt(PESEL.substring(4, 6));

        if (month > 80) {
            year += 1800;
            month -= 80;
        } else if (month > 60) {
            year += 2200;
            month -= 60;
        } else if (month > 40) {
            year += 2100;
            month -= 40;
        } else if (month > 20) {
            year += 2000;
            month -= 20;
        } else {
            year += 1900;
        }
        System.out.println(year + "-" + month + "-" + day);
        try {
            LocalDate date = LocalDate.of(year, month, day);
            return date;
        }
        catch (Exception e) {
            throw new wrongPESELException("PESEL has an invalid date");
        }
    }

    /**
     * Validates the control digit of the PESEL number.
     *
     * @param PESEL the PESEL number to validate
     * @return {@code true} if the control digit is valid, {@code false} otherwise
     */
    public static boolean validateDigit(String PESEL) {
        int[] weights = {1, 3, 7, 9, 1, 3, 	7, 9, 1, 3};
        int sum = 0;

        for(int i = 0; i < weights.length; i++) {
            sum += weights[i] * Character.getNumericValue(PESEL.charAt(i));
        }
        int checksum = Character.getNumericValue(PESEL.charAt(10));
        int check2 = 10 - (sum % 10);
        if (check2 == 10) {
            check2 = 0;
        }
        return check2 == checksum;
    }

}
