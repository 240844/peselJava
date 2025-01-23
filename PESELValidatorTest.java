import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class PESELValidatorTest {

    @Test
    void containsOnlyNumbers() {
        // Valid
        assertTrue(PESELValidator.containsOnlyNumbers("12345678901"));

        // Invalid
        assertFalse(PESELValidator.containsOnlyNumbers("12345A78901"));
        assertFalse(PESELValidator.containsOnlyNumbers("12345 78901"));
        assertFalse(PESELValidator.containsOnlyNumbers(""));

        assertThrows(NullPointerException.class, () -> PESELValidator.containsOnlyNumbers(null));
    }

    @Test
    void validateDate() {
        // Valid PESELs
        try {
            assertEquals(LocalDate.of(2000, 1, 1), PESELValidator.validateDate("00210112345"));
            assertEquals(LocalDate.of(1999, 12, 31), PESELValidator.validateDate("99123112345"));
            assertEquals(LocalDate.of(2100, 3, 15), PESELValidator.validateDate("00431512345"));
        } catch (wrongPESELException e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }

        // Invalid
        assertThrows(wrongPESELException.class, () -> PESELValidator.validateDate("00229912345")); // Invalid date
        assertThrows(wrongPESELException.class, () -> PESELValidator.validateDate("123456")); // Too short
        assertThrows(wrongPESELException.class, () -> PESELValidator.validateDate("abcdefghijk")); // Non-numeric
    }

    @Test
    void validateDigit() {
        // Valid PESEL with correct checksum
        assertTrue(PESELValidator.validateDigit("03211607136")); // Valid control digit

        // Invalid PESEL with incorrect checksum
        assertFalse(PESELValidator.validateDigit("03211607137")); // Invalid control digit

        // Invalid PESEL with fewer digits
        assertThrows(StringIndexOutOfBoundsException.class, () -> PESELValidator.validateDigit("1234567"));
    }
}