import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PeselTest {

    @Test
    void getPESEL() {
        try {
            Pesel pesel = new Pesel("03211607136");
            assertEquals("03211607136", pesel.getPESEL());
        } catch (wrongPESELException e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }

        assertThrows(wrongPESELException.class, () -> new Pesel("1234567890"));
        assertThrows(wrongPESELException.class, () -> new Pesel("abcdefghijk"));
    }

    @Test
    void getDate() {
        try {
            Pesel pesel = new Pesel("03211607136");
            assertEquals(LocalDate.of(2003, 1, 16), pesel.getDate());
        } catch (wrongPESELException e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }

        assertThrows(wrongPESELException.class, () -> new Pesel("03218607136"));
    }

    @Test
    void getGender() {
        try {
            Pesel pesel = new Pesel("03211607136");
            assertEquals("M", pesel.getGender());
        } catch (wrongPESELException e) {
            throw new RuntimeException(e);
        }
    }
}