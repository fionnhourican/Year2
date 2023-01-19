// VowelAge.java

// Example operation:
// Name: Jane Doe
// Age: 18
// Hello, Jane Doe, you have 4 vowels, and you are an adult

// import
import java.util.Scanner;


public class VowelAge {

    public static void main(String[] args) {

        // Scanner to take input and create name object.
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        // Scanner to create age object.
        int age = scanner.nextInt();      

        // Loop to count vowels in name.
        int count = 0;

        for(int i=0; i < name.length(); i++) {

            if (name.charAt(i) == 'a' || name.charAt(i) == 'A' ||
                name.charAt(i) == 'e' || name.charAt(i) == 'E' ||
                name.charAt(i) == 'i' || name.charAt(i) == 'I' ||
                name.charAt(i) == 'o' || name.charAt(i) == 'O' ||
                name.charAt(i) == 'u' || name.charAt(i) == 'U') {

                    count++;
            }

        }

        // Statment to check if person is an adult or minor
        String minorAdult;
        if (age >= 18) {

            minorAdult = " an adult";
        }

        else {
            minorAdult = " a minor";
        }

        System.out.println("Hello " + name + ", you have " + count + " vowels, and you are" + minorAdult);

        // Closing scanner to reclaim memory
        scanner.close(); // Close scanner
    }
    
}
