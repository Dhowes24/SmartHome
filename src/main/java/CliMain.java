import java.io.IOException;
import java.util.*;
import java.lang.String;

public class CliMain {

    private static boolean running = true;
    private static boolean signedIn = false;
    private static Scanner s = new Scanner(System.in);

    private static Room currentRoom;
    private static House currentHouse;
    private static User currentUser ;

    // TODO: add 'q' option at all times to quit out of an option you entered into

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
            Boolean successfulSignIn = false;
            while(!successfulSignIn){
                try{
                    successfulSignIn = signIn(username,pin);
                }
                catch(Exception e){
                    System.out.println("ERROR: "+e);
                }
                if(!successfulSignIn){
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
            }

            //TODO read in the house from the JSON
            House h = new House();
            currentUser = new User(h, username);
            signedIn = true;

            startMenu();

        }
    }

    public static void startMenu() {

        System.out.println("Welcome to the Smart Home Main Menu!");
        System.out.println("Feel free to navigate through the options!");
        System.out.println("At any point, enter 'q' to exit an option you have selected. Enter 'c' to continue, or any key to quit.");
        String selection = s.nextLine();
        if (selection.toLowerCase().equals("c")) {
            basicMenu();
        } else {
            System.exit(0);
        }
    }

    //Basic Menu CLI
    public static void basicMenu(){

        //Run until they sign in
        while(signedIn) {

            System.out.println("------------------------------------------");
            System.out.println("Would you like to 1) Create a new room, 2) Navigate to a room, or 3) Go back");
            System.out.println("1. Create a new room");
            System.out.println("2. Navigate to a room");
            System.out.println("3. User Options");
            System.out.println("4. Scheduling");
            System.out.println("5. Contact Emergency Services");
            System.out.println("6. Emergency Service");
            System.out.println("7. Sign Out");

            String sel = s.nextLine();

            while (!integerCheck(sel)) {
                System.out.println("Error: type in an integer value");
                sel = s.nextLine();
            }

            Integer selection = Integer.parseInt(sel);

            if (selection == 1) { // Create Room
                createRoomCLI();

            } else if (selection == 2) { // Navigate Room
                navigateRoomsCLI();

            } else if (selection == 3) { // User Options CLI
                userCLI();

            } else if (selection == 4) { // Scheduling CLI
                //TODO
            } else if (selection == 5) { // Emergency Services CLI
                //TODO
            } else if (selection == 6) { // Help CLI
                contactES();
            } else if (selection == 7) { // Sign Off
                System.out.println("Signing off...");
                System.out.println();
                signedIn = false;
            } else { // Error
                System.out.println("Error: Enter a value between 1 and 9.");
                basicMenu();
            }
        }

        run();
    }

    public static void createRoomCLI() {

        System.out.println("Please enter the name of the room you would like to create:");
        String selection = s.nextLine();
        System.out.println("Please confirm the name of the room you would like to create:");
        String selection2 = s.nextLine();

        if (selection.equals(selection2)) {
            currentHouse.addRoom(selection);
            System.out.println("Your room has been created!");
            basicMenu();
        } else {
            System.out.println("Your names do not match");
            createRoomCLI();
        }

        basicMenu();
    }

    public static void navigateRoomsCLI() {

        if (currentHouse.roomList.size() > 0) {
            System.out.println("Your Rooms:");
            currentHouse.printRooms();
            System.out.println("Which room would you like to go to? (Enter the corresponding number)");

            selectRoomCLI();
        }

        System.out.println("You have no rooms yet!");
        basicMenu();
    }

    public static void selectRoomCLI() {

        String selection = s.nextLine();

        while (!integerCheck(selection)) {
            System.out.println("Error: type in an integer value");
            System.out.println("Please enter a value that corresponds to a room");
            selection = s.nextLine();
        }

        Integer sInt = Integer.parseInt(selection);

        if (sInt-1 >= currentHouse.roomList.size() || sInt == 0) {
            System.out.println("Error: There is no room corresponding to number: " + sInt);
            System.out.println("Please enter a value that corresponds to a room");
            navigateRoomsCLI();
        }

        currentRoom = currentHouse.accessRoom(currentHouse.getKey(sInt-1)); // remove 1 because it was added for user interface earlier
        roomStatusCLI();
    }

    public static void roomStatusCLI() {

        System.out.println("\nCurrent Room: " + currentRoom.getRoomName());
        System.out.println("Temperature: " + currentRoom.checkTemp());

        if (currentRoom.lightMap.size() > 0) {
            System.out.println("Lights:\n");
            currentRoom.printLights();
        }
        if (currentRoom.applianceMap.size() > 0) {
            System.out.println("Appliances:\n");
            currentRoom.printAppliances();
        }

        System.out.println("Which of the following actions would you like to perform?");
        System.out.println("0. Go Back");
        System.out.println("1. Go To Appliance Menu");
        System.out.println("2. Go To Light Menu");
        System.out.println("3. Go To Temperature Menu");

        String selection = s.nextLine();

        while (!integerCheck(selection)) {
            System.out.println("Error: type in an integer value");
            System.out.println("Please enter a value that corresponds to an option");
            selection = s.nextLine();
        }

        Integer sInt = Integer.parseInt(selection);

        if (sInt == 0) { // Go Back

            basicMenu();

        } else if (sInt == 1) { // Appliance Menu

            applianceCLI();

        } else if (sInt == 2) { // Light Menu

            lightCLI();

        } else if (sInt == 3){ // Temp Menu

            tempCLI();

        } else {

            System.out.println("Error: Please select a valid option");
            roomStatusCLI();
        }
    }

    public static void applianceCLI() {

        System.out.println("Which of the following actions would you like to perform?");
        System.out.println("0. Go Back");
        System.out.println("1. Add Appliance");
        System.out.println("2. Remove Appliance");
        System.out.println("3. Change Appliance Status");
        System.out.println("4. Add Appliance Change to Schedule");

        String selection = s.nextLine();

        while (!integerCheck(selection)) {
            System.out.println("Error: type in an integer value");
            System.out.println("Please enter a value that corresponds to an option");
            selection = s.nextLine();
        }

        Integer sInt = Integer.parseInt(selection);

        if (sInt == 0) {

            roomStatusCLI();

        } else if (sInt == 1) { // Add Appliance

            createApplianceCLI();

        } else if (sInt == 2) { // Remove Appliance

            removeApplianceCLI();

        } else if (sInt == 3) { // Change Appliance Status

            adjustApplianceCLI();

        } else if (sInt == 4) { // Add Appliance Change to Schedule

            addApplianceToScheduleCLI();

        } else {

            System.out.println("Error: Please select a valid option");
            applianceCLI();
        }
    }

    public static void createApplianceCLI() {

        System.out.println("Please enter the name of the appliance you would like to create:");

        String selection = s.nextLine();
        System.out.println("Please confirm the name of the appliance you would like to create:");
        String selection2 = s.nextLine();

        if (selection.equals(selection2)) {

            System.out.println("Will this appliance require special permissions (Y/N)?"); // TODO
            String sel = s.nextLine();

            while (!sel.toLowerCase().equals("y") || !sel.toLowerCase().equals("n")) {
                System.out.println("Error: please enter 'y' or 'n'");
                sel = s.nextLine();
            }

            if (sel.toLowerCase().equals("y")) {
                currentRoom.addAppliance(selection, true);
            } else {
                currentRoom.addAppliance(selection, false);
            }
            System.out.println("Your appliance has been created!");
            roomStatusCLI();
        } else {
            System.out.println("Your names do not match");
            createApplianceCLI();
        }
    }

    public static void removeApplianceCLI() {

        if (currentRoom.applianceMap.size() > 0) {

            currentRoom.printAppliances();
            System.out.println("Which appliance would you like to remove?");
            System.out.println("Please enter a name below");

            String selection = s.nextLine();

            if (currentRoom.applianceMap.containsKey(selection)) {

                System.out.println("Would you like to delete the appliance: " + selection);
                System.out.println("Please enter 'Y' or 'N'");
                String sel = s.nextLine();

                while (!sel.toLowerCase().equals("y") || !sel.toLowerCase().equals("n")) {
                    System.out.println("Error: please enter 'y' or 'n'");
                    sel = s.nextLine();
                }

                if (sel.toLowerCase().equals("y")) {
                    currentRoom.removeAppliance(selection);
                } else {
                    roomStatusCLI();
                }
            } else {
                System.out.println("This appliance does not exist");
                removeApplianceCLI();
            }
        }

        System.out.println("You have no appliances yet!");
        roomStatusCLI();
    }

    public static void adjustApplianceCLI() {

        if (currentRoom.applianceMap.size() > 0) {

            currentRoom.printAppliances();
            System.out.println("Which appliance would you like to modify?");
            System.out.println("Please enter a name below");

            String selection = s.nextLine();
            if (currentRoom.applianceMap.containsKey(selection)) {

                System.out.println("Would you like to modify the appliance: " + selection);
                System.out.println("Please enter 'Y' or 'N'");
                String sel = s.nextLine();

                while (!sel.toLowerCase().equals("y") || !sel.toLowerCase().equals("n")) {
                    System.out.println("Error: please enter 'y' or 'n'");
                    sel = s.nextLine();
                }

                if (sel.toLowerCase().equals("y")) {
                    currentRoom.applianceMap.get(selection).changeStatus();
                } else {
                    roomStatusCLI();
                }
            } else {
                System.out.println("This appliance does not exist");
                adjustApplianceCLI();
            }
        }

        System.out.println("You have no appliances yet!");
        roomStatusCLI();
    }

    public static void addApplianceToScheduleCLI() {} // TODO: Michael

    public static void lightCLI() {

        System.out.println("Which of the following actions would you like to perform?");
        System.out.println("1. Add Light");
        System.out.println("2. Remove Light");
        System.out.println("3. Adjust Light");
        System.out.println("4. Add Light Change to Schedule");

        String selection = s.nextLine();

        while (!integerCheck(selection)) {
            System.out.println("Error: type in an integer value");
            System.out.println("Please enter a value that corresponds to an option");
            selection = s.nextLine();
        }

        Integer sInt = Integer.parseInt(selection);

        if (sInt == 0) {

            roomStatusCLI();

        } else if (sInt == 1) { // Add Light

            createLightCLI();

        } else if (sInt == 2) { // Remove Light

            removeLightCLI();

        } else if (sInt == 3) { // Adjust Light

            adjustLightCLI();

        } else if (sInt == 4) { // Add Light Change to Schedule

            addLightToScheduleCLI();

        } else {

            System.out.println("Error: Please select a valid option");
            lightCLI();
        }
    }

    public static void createLightCLI() {

        System.out.println("Please enter the name of the light you would like to create:");

        String selection = s.nextLine();
        System.out.println("Please confirm the name of the light you would like to create:");
        String selection2 = s.nextLine();

        if (selection.equals(selection2)) {

            System.out.println("Will this light require special permissions (Y/N)?"); // TODO
            String permSel = s.nextLine();

            while (!permSel.toLowerCase().equals("y") || !permSel.toLowerCase().equals("n")) {
                System.out.println("Error: please enter 'y' or 'n'");
                permSel = s.nextLine();
            }

            System.out.println("Will this light be dimmable (Y/N)?");
            String dimSel = s.nextLine();

            while (!dimSel.toLowerCase().equals("y") || !dimSel.toLowerCase().equals("n")) {
                System.out.println("Error: please enter 'y' or 'n'");
                dimSel = s.nextLine();
            }

            if (permSel.toLowerCase().equals("y")) {
                if (dimSel.toLowerCase().equals("y")) {
                    currentRoom.addLight(selection, true, true);
                } else {
                    currentRoom.addLight(selection, true, false);
                }
            } else {
                if (dimSel.toLowerCase().equals("y")) {
                    currentRoom.addLight(selection, false, true);
                } else {
                    currentRoom.addLight(selection, false, false);
                }
            }
            System.out.println("Your light has been created!");
            roomStatusCLI();
        } else {
            System.out.println("Your names do not match");
            createLightCLI();
        }
    }

    // TODO: Anthony
    public static void removeLightCLI() {

        System.out.println("Please enter the name of the light you wish to remove: ");
        String light = s.nextLine();
        for (Light lights : currentRoom.lightMap.values()) {
            if (light.equals(lights.getID())) {
                currentRoom.removeLight(light);
            }
            else {
                System.out.println("Light name you entered does not exist");
                removeLightCLI();
            }
        }
    }

    // TODO: Anthony
    public static void adjustLightCLI() {

        System.out.println("Please enter the name of the light you wish to adjust: ");
        String lightName = s.nextLine();
        double amount = 0.0;
        for (Light light : currentRoom.lightMap.values()) {
            if (lightName.equals(light.getID())) {
                System.out.println("Enter the intensity you would like to set it to: ");
                String num = s.nextLine();
                amount = Double.parseDouble(num);
                if (amount < 0.0) {
                    System.out.println("The value entered is invalid");
                }
                else {
                    currentRoom.adjustLight(amount, lightName);
                }
            }
            else {
                System.out.println("The name you entered does not exist");
            }
        }
        adjustLightCLI();
    }

    public static void addLightToScheduleCLI() {} // TODO: Michael

    public static void tempCLI() {

        System.out.println("Which of the following actions would you like to perform?");
        System.out.println("0. Go Back");
        System.out.println("1. Adjust Temperature");
        System.out.println("2. Add Temperature Change to Schedule");

        String selection = s.nextLine();

        while (!integerCheck(selection)) {
            System.out.println("Error: type in an integer value");
            System.out.println("Please enter a value that corresponds to an option");
            selection = s.nextLine();
        }

        Integer sInt = Integer.parseInt(selection);

        if (sInt == 0) {

            roomStatusCLI();

        } else if (sInt == 1) { // Adjust Temperature

            adjustTempCLI();

        } else if (sInt == 2) { // Add Temperature Change to Schedule

            addTempToScheduleCLI();

         } else {

            System.out.println("Error: Please select a valid option");
            tempCLI();
        }
    }

    // TODO: Anthony
    public static void adjustTempCLI() {

        System.out.println("Please type the temperature you would like to set for this room: ");
        String num = s.nextLine();
        boolean check = integerCheck(num);
        if (check) {
            int amount = Integer.parseInt(num);
            currentRoom.adjustTemp(amount);
        }
        else {
            System.out.println("The number you entered is invalid");
            adjustTempCLI();
        }
    }

    public static void addTempToScheduleCLI() {} // TODO: Michael

    public static void contactES(){
        boolean running = true;
        while(running){
            System.out.println();
            System.out.println("Contact Emergency Services");
            System.out.println("--------------------------");
            System.out.println("1. Police Department");
            System.out.println("2. Fire Department");
            System.out.println("3. Ambulance");
            System.out.println("4. Return");
            System.out.print("Who would you like to contact: ");

            String sel = s.nextLine();
            while (!integerCheck(sel, 4) ) {
                System.out.println("Error: type in an integer value 1-4");
                sel = s.nextLine();
            }
            Integer selI = Integer.parseInt(sel);

            if(selI==1){
                System.out.println("Contacting the Police Department right now.");
                System.out.println();
                running = false;
            } else if(selI==2){
                System.out.println("Contacting the Fire Department right now.");
                System.out.println();
                running = false;
            } else if(selI==3){
                System.out.println("Contacting Medical staff right now.");
                System.out.println();
                running = false;
            } else if (selI == 4) {
                running=false;
            }

        }
    }

    // User CLI
    public static void userCLI(){
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
                User newUser = new User(currentUser.House,userToAdd);
                System.out.println("You have entered "+ userToAdd+" as a new user in this house.");
                System.out.println();
            }else if(selection==2){
                System.out.print("Please enter the name for the user to delete: ");
                String userToDelete = s.nextLine();
                currentUser.deleteUser(userToDelete);
                System.out.println("You have removed " + userToDelete+ " as a user in this house.");
                System.out.println();
            }else if(selection==3){
                System.out.println(currentUser.printRoomList());
                System.out.println();
            }else if(selection==4){
                System.out.print("Please enter the room in the house you would like to get appliances for: ");
                String roomName = s.nextLine();
                System.out.println(currentUser.getRoomApplianceList(roomName));
                System.out.println();
            }else if(selection==5){
                System.out.print("Please enter the room in the house you would like to get lights for: ");
                String roomName = s.nextLine();
                System.out.println(currentUser.getRoomLightList(roomName));
                System.out.println();
            }else if(selection==6){
                run = false;
            }else{
                System.out.println("Error: Enter a value between 1 and 6.");
            }
        }
    }

    public static boolean signIn(String username, Integer pin)throws IOException {
        List<User> userListOut = new ArrayList<User>();
        try{
            userListOut = JsonUtil.listFromJsonFile("./src/main/files/usersList", User.class);
        } catch(Exception e){
            return false;
        }
        if(userListOut.size()==0){return false;}
        else{
            for (int i = 0; i < userListOut.size(); i++) {
                if ((userListOut.get(i).getName().equals(username)) && (userListOut.get(i).getPin().equals(pin))) {
                    return true;
                }
            }
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

    public static boolean integerCheck(String s, Integer bounds){
        try {
            Integer sInt = Integer.parseInt(s);
            if(sInt <= bounds && sInt >=1){
                return true;
            }else{
                return false;
            }
        }catch(NumberFormatException e){
            return false;
        }

    }



    public static void main(String[] args) {
        run();
    }


}
