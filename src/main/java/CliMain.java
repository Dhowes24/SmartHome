import java.util.Scanner;
import java.lang.String;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;



public class CliMain extends Room {

    public static boolean running = true;
    public static boolean signedIn = false;
    public static Scanner s = new Scanner(System.in);

    public static void run(){
        while(running) {
            signedIn=false;
            System.out.println("Welcome to the Smart Home Application");
            System.out.println("-------------------------------------");
            System.out.println();

            //Get the User Name
            System.out.print("Please enter your username: ");
            String username = s.nextLine();

            //Get the Pin
            System.out.print("Please enter your pin: ");
            String pinS = s.nextLine();
            while (!integerCheck(pinS)) {
                System.out.println();
                System.out.println("Error: type in a integer value.");
                System.out.print("Please enter your pin: ");
                pinS = s.nextLine();
            }
            Integer pin = Integer.parseInt(pinS);

            while (!signIn(username, pin)) {
                //Get the User Name
                System.out.println();
                System.out.println("Error: Failed log in.");
                System.out.println("Please enter the proper credentials");
                System.out.println();

                System.out.print("Please enter your username: ");
                username = s.nextLine();

                //Get the Pin
                System.out.print("Please enter your pin: ");
                pinS = s.nextLine();
                while (!integerCheck(pinS)) {
                    System.out.println("Error: type in a integer value.");
                    System.out.print("Please enter your pin: ");
                    pinS = s.nextLine();
                }
                pin = Integer.parseInt(pinS);
            }

            //TODO read in the house from the JSON
            House h = new House();
            User u = new User(h, username);
            signedIn = true;
            basicMenu(u);

        }
    }


    public static void basicMenu(User u){

        //Run until they sign in
        while(signedIn) {
            System.out.println();
            System.out.println("Welcome to the Smart Home Main Menu!");
            System.out.println("Feel free to navigate through the options!");
            System.out.println("------------------------------------------");
            System.out.println("1. Lighting");
            System.out.println("2. Appliances");
            System.out.println("3. Temperature");
            System.out.println("4. Rooms");
            System.out.println("5. Users");
            System.out.println("6. Scheduling");
            System.out.println("7. Contact Emergency Services");
            System.out.println("8. Help");
            System.out.println("9. Sign Out");
            System.out.println("------------------------------------------");
            System.out.println();
            System.out.print("What would you like to do: ");

            //Check for valid input
            String selectionS = s.nextLine();
            while (!integerCheck(selectionS)) {
                System.out.println();
                System.out.println("Error: type in a integer value.");
                System.out.print("Please enter a value 1-9: ");
                selectionS = s.nextLine();
            }
            //Parse input to integer
            Integer selection = Integer.parseInt(selectionS);


            //TODO send them to classes individual CLI's
            if (selection == 1) {
                //Lighting CLI

            }else if (selection == 2) {
                //Appliance CLI

            }else if (selection == 3) {
                //Temperature CLI

            }else if (selection == 4) {
                //Room CLI

            }else if (selection == 5) {
                //User CLI

            }else if (selection == 6) {
                //Scheduling CLI

            }else if (selection == 7) {
                //Emergency Services CLI
            }else if (selection == 8) {
                //Help CLI

            }else if (selection == 9) {
                System.out.println("Signing off...");
                System.out.println();
                signedIn=false;
            } else {
                System.out.println("Error: Enter a value between 1 and 9.");
            }
        }
    }


    //TODO read in the user from the JSON
    public static boolean signIn(String username, Integer pin){
        if(username.equals("brien") && pin == 69){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Checks to see if the input was an integer
     * @param s - the string being looked at
     * @return boolean - whether it is an integer or not
     */
    public static boolean integerCheck(String s){
        try {
            Integer.parseInt(s);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

    public static void main(String[] args) {
        run();
    }


}
