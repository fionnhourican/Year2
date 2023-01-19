// AlarmClock.java

// Example operation:

// input:
// 07 00
// 02 30
// 03 30
// 06 30
// 07 30

// output:
// false alarms: 3

// import
import java.util.Scanner;

public class AlarmClock {

    public static void main(String[] args) {

        // Scanner to take input and create hours and min objects.
        Scanner scanner = new Scanner(System.in);
        int alarm_hrs = scanner.nextInt();
        int alarm_min = scanner.nextInt();

        // Turn into min.
        int alarm = (alarm_hrs * 60) + alarm_min;

        // initilise current time
        int current_hour = 0;
        int current_minutes = 0;
        int current_normalised = current_hour*60 + current_minutes;

        // Initial value of false alarms is -1 
        // since we increment it every question in the loop
        // so that the first time the input is asked, it is set to 0
        int false_alarms = -1;

        // A do...while loop is better here since the condition to check
        // needs to happen after the input
        // i.e. first take input, and then check if its later than alarm time
        do {
            false_alarms = false_alarms + 1;
            current_hour = scanner.nextInt();
            current_minutes = scanner.nextInt();
            current_normalised = current_hour*60 + current_minutes;
        } while (current_normalised < alarm);

            System.out.println("false alarms: " + false_alarms);
    
        // Closed to free memory
        scanner.close();
    }
    
}
