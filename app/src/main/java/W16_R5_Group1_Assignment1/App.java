package W16_R5_Group1_Assignment1;

import java.util.Scanner;
import java.io.File;

public class App {
    public static void main(String[] args) {
        ATMSystem system = new ATMSystem();
        String bank_cardlist = null;
        String atm_name = null;

        try {
            File f = new File("src/main/resources/configuration.txt");
            Scanner scan = new Scanner(f);
            String[] buffer;
            if (scan.hasNextLine()) {
                String line = scan.nextLine();
                buffer = line.split(" ");
                bank_cardlist = buffer[1];
            } else {
                System.out.println("Empty file");
            }

            if (scan.hasNextLine()) {
                String line = scan.nextLine();
                buffer = line.split(" ");
                atm_name = buffer[1];
            } else {
                System.out.println("Wrong attribute!");
            }
            scan.close();
        } catch (Exception e) {
            System.err.println("configuration file not found or attribute in file is wrong!");
            return;
        }
        system.run(bank_cardlist, atm_name);
    }
}
