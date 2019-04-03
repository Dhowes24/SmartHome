import java.util.Scanner;
import java.lang.String;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;



public class CliMain extends Room {

    public static boolean running = true;
    public static boolean signedIn = false;
    public static Scanner s = new Scanner(System.in);

    //Initial CLI
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

    //Basic Menu CLI
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
                userCLI(u);

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

    //User CLI
    public static void userCLI(User u){
        boolean run = true;
        while(run) {
            System.out.println("User Menu");
            System.out.println("---------");
            System.out.println("1. Create User");
            System.out.println("2. Delete User");
            System.out.println("3. Print Room List");
            System.out.println("4. Get Room Appliances");
            System.out.println("5. Get Room Lights");
            System.out.println("6. Return");
            System.out.print("Please enter a digit 1-6: ");
            String selectionS = s.nextLine();
            while(!integerCheck(selectionS )){
                System.out.println();
                System.out.println("Error: type in a integer value.");
                System.out.print("Please enter a value 1-6: ");
                selectionS = s.nextLine();
            }
            System.out.println();
            Integer selection = Integer.parseInt(selectionS);
            if(selection==1){
                System.out.print("Please enter the name for the new user: ");
                String userToAdd = s.nextLine();
                User newUser = new User(u.House,userToAdd);
                System.out.println("You have entered "+ userToAdd+" as a new user in this house.");
                System.out.println();
            }else if(selection==2){
                System.out.print("Please enter the name for the user to delete: ");
                String userToDelete = s.nextLine();
                u.deleteUser(userToDelete);
                System.out.println("You have removed " + userToDelete+ " as a user in this house.");
                System.out.println();
            }else if(selection==3){
                System.out.println(u.printRoomList());
                System.out.println();
            }else if(selection==4){
                System.out.print("Please enter the room in the house you would like to get appliances for: ");
                String roomName = s.nextLine();
                System.out.println(u.getRoomApplianceList(roomName));
                System.out.println();
            }else if(selection==5){
                System.out.print("Please enter the room in the house you would like to get lights for: ");
                String roomName = s.nextLine();
                System.out.println(u.getRoomLightList(roomName));
                System.out.println();
            }else if(selection==6){
                run = false;
            }else{
                System.out.println("Error: Enter a value between 1 and 6.");
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
