import java.time.LocalDate;

/**
 * The Pesel class is responsible for handling and validating PESEL numbers,
 * extracting the associated date of birth and determining the gender.
 *
 * <p>This class validates the PESEL and ensures it is correctly formatted before storing it.</p>
 *
 * @author Kacper Zatoński
 * @version 1.0
 * @since JDK 23
 */
public class Pesel {
    private String PESEL;
    private LocalDate date;
    private String gender;


    /**
     * <p>The constructor validates the provided PESEL and initializes
     * the date of birth and gender based on its content.</p>
     *
     * @param PESEL the PESEL number to validate and process
     * @throws wrongPESELException if the PESEL is null, incorrectly formatted,
     *                             or fails validation checks
     */
    public Pesel(String PESEL) throws wrongPESELException {
        this.PESEL = PESEL;
        checkPesel();
        calculateGender();
    }

    /**
     * Determines the gender based on the ninth digit of the PESEL number.
     * <p>Even digits correspond to female ("K"), while odd digits correspond to male ("M").</p>
     */
    private void calculateGender(){
        int genderDigit = Character.getNumericValue(PESEL.charAt(9));
        gender = (genderDigit % 2 == 0) ? "K" : "M";
    }

    /**
     * Validates the PESEL number.
     *
     * <p>Checks if the PESEL is not null, is exactly 11 digits long,
     * contains only numbers, has a valid date encoded, and includes a valid control digit.</p>
     *
     * @throws wrongPESELException if any of the validation checks fail
     */
    private void checkPesel() throws wrongPESELException{
        if(PESEL == null) throw new wrongPESELException("PESEL is null");
        if(PESEL.length() > 11) throw new wrongPESELException("PESEL is too short");
        if(PESEL.length() < 11) throw new wrongPESELException("PESEL is too long");
        if(!PESELValidator.containsOnlyNumbers(PESEL)) throw new wrongPESELException("PESEL should contain only numbers");
        try{
            date = PESELValidator.validateDate(PESEL);
        }
        catch(wrongPESELException e){
            throw new wrongPESELException(e.getMessage());
        }
        if(!PESELValidator.validateDigit(PESEL)) throw new wrongPESELException("PESEL has an invalid control digit");
    }

    /**
     * Returns the PESEL number of this instance.
     *
     * @return the PESEL number
     */
    public String getPESEL() {
        return this.PESEL;
    }

    /**
     * Returns the date of birth.
     *
     * @return the date of birth
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns the gender.
     *
     * @return the gender ("M" for male, "K" for female)
     */
    public String getGender() {
        return gender;
    }

}
