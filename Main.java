import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String pesel = (args.length > 0) ? args[0] : null;

        if (pesel == null) {
            System.out.print("Podaj numer PESEL: ");
            pesel = scanner.nextLine();
        }

        try {
            Pesel p = new Pesel(pesel);
            System.out.println("Numer PESEL jest poprawny.");
            System.out.println("Data urodzenia: " + p.getDate());
            System.out.println("Płeć: " + p.getGender());
        } catch (wrongPESELException e) {
            System.err.println("Błąd: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}