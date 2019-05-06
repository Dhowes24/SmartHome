import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.lang.reflect.Executable;
import java.util.*;
import java.lang.String;

public class CliMain {

    private static boolean running = true;
    private static boolean signedIn = false;
    private static Scanner s = new Scanner(System.in);

    private static Room currentRoom;
    private static House currentHouse;
    private static User currentUser;

    // TODO: add 'q' option at all times to quit out of an option you entered into

    //Initial CLI
    public static void run() {
        while (running) {
            signedIn = false;
            System.out.println();
            System.out.println("Welcome to the Smart Home Application");
            System.out.println("-------------------------------------");

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

            while (!successfulSignIn) {
                try {
                    successfulSignIn = signIn(username, pin);
                } catch (Exception e) {
                    System.out.println("ERROR: " + e);
                }
                if (!successfulSignIn) {
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
            currentHouse = new House();
            currentUser = new User(username);
            signedIn = true;
            startMenu();

        }
    }

    public static void startMenu() {
        System.out.println();
        System.out.println("Welcome to Smart Home!\n");
        basicMenu();
//        System.out.println("Feel free to navigate through the options!");
//        System.out.println("At any point, enter 'q' to exit an option you have selected. Enter 'c' to continue, or any key to quit.");
//        String selection = s.nextLine();
//        if (selection.toLowerCase().equals("c")) {
//            basicMenu();
//        } else {
//            System.exit(0);
//        }
    }

    //Basic Menu CLI
    public static void basicMenu() {

        //Run until they sign in
        while (signedIn) {

            System.out.println();
            System.out.println("Basic Menu:");
            System.out.println("-----------");
            System.out.println("1. Create a new room");
            System.out.println("2. Navigate to a room");
            System.out.println("3. User Options");
            System.out.println("4. House Manipulation");
            System.out.println("5. Contact Emergency Services");
            System.out.println("6. Help");
            System.out.println("7. Sign Out");
            System.out.print("What would you like to do: " + "\n");
            String sel = s.nextLine();

            while (!integerCheck(sel, 7)) {
                System.out.println();
                System.out.print("Error: type in an integer value 1-7:");
                sel = s.nextLine();
            }
            Integer selection = Integer.parseInt(sel);

            if (selection == 1) { // Create Room
                createRoomCLI();
            } else if (selection == 2) { // Navigate Room
                navigateRoomsCLI();
            } else if (selection == 3) { // User Options CLI
                userCLI();
            } else if (selection == 4) {
                houseManipulationCLI();
            } else if (selection == 5) { // Emergency Services CLI
                contactESCLI();
            } else if (selection == 6) { // Help CLI
                helpCLI();
            } else if (selection == 7) { // Sign Off
                System.out.println("Signing off...");
                System.out.println();
                saveUsers();
                signedIn = false;
            }
        }

        run();
    }

    //DONE
    public static void createRoomCLI() {
        boolean running = true;
        String selection;
        String selection2;
        while (running) {

            System.out.println();
            System.out.print("Please enter the name of the room you would like to create: ");
            selection = s.nextLine();
            if (selection.equalsIgnoreCase("q")) {
                break;
            }

            System.out.print("Please confirm the name of the room you would like to create: ");
            selection2 = s.nextLine();
            if (selection2.equalsIgnoreCase("q")) {
                break;
            } else {

                if (selection.equalsIgnoreCase(selection2)) {
                    currentHouse.addRoom(selection);
                    System.out.println();
                    System.out.print("Your room " + selection + " has been created!");
                    System.out.println();
                    running = false;
                } else {
                    System.out.println("Your names do not match");
                }
            }
        }
    }

    //DONE
    public static void navigateRoomsCLI() {
        boolean running = true;
        String selection;
        Integer sInt;
        int currSize = currentHouse.roomList.size();
        int currSizeNeeded = currSize + 1;

        if (currSize <= 0) {
            System.out.println();
            System.out.println("You have no rooms yet!");
            running = false;
        }

        while (running) {
            System.out.println();
            System.out.println("Your Rooms:");
            currentHouse.printRooms();
            System.out.println("---------");
            System.out.println(currSizeNeeded + ". Return");
            System.out.print("Please enter a value that corresponds to a room: ");

            selection = s.nextLine();
            while (!integerCheck(selection, currentHouse.roomList.size()+1 )) {
                System.out.println("Error: type in an integer value 1-" + currSizeNeeded + ".");
                System.out.println();
                System.out.print("Please enter a value that corresponds to a room: ");
                selection = s.nextLine();
            }
            sInt = Integer.parseInt(selection);
            if (sInt == currSizeNeeded) {
                running = false;;
                break;
            } else {
                currentRoom = currentHouse.accessRoom(currentHouse.getKey(sInt - 1));
                roomStatusCLI();
            }
        }
    }

    //DONE
    public static void roomStatusCLI() {
        boolean running = true;
        String selection;
        Integer sInt;
        while (running) {
            System.out.println();
            System.out.println("Current Room: " + currentRoom.getRoomName());
            System.out.println("Temperature: " + currentRoom.checkTemp());

            if (currentRoom.lightMap.size() > 0) {
                System.out.println();
                System.out.println(currentRoom.getRoomName() + "'s Lights: ");
                currentRoom.printLights();
                System.out.println();
            }
            if (currentRoom.applianceMap.size() > 0) {
                System.out.println();
                System.out.println(currentRoom.getRoomName() + "'s Appliances: ");
                currentRoom.printAppliances();
                System.out.println();
            }

            System.out.println("1. Go To Appliance Menu");
            System.out.println("2. Go To Light Menu");
            System.out.println("3. Go To Temperature Menu");
            System.out.println("4. Add To Schedule");
            System.out.println("5. Return");
            System.out.print("What would you like to do: ");

            selection = s.nextLine();

            while (!integerCheck(selection, 5)) {
                System.out.println("Error: type in an integer value 1-5.");
                System.out.print("Please enter a value that corresponds to an option: ");
                selection = s.nextLine();
            }
            sInt = Integer.parseInt(selection);

            if (sInt == 1) {
                applianceCLI();
            } else if (sInt == 2) {
                lightCLI();
            } else if (sInt == 3) {
                tempCLI();
            } else if (sInt == 4) {
                scheduleCLI();
            } else if (sInt == 5) {
                running = false;
            }
        }
    }

    public static void scheduleCLI() {

        boolean running = true;
        String selection;


        while (running) {
            System.out.println();
            System.out.println("Schedule Adjust:");
            System.out.println("-----------------");
            System.out.println("1. Add to Schedule");
            System.out.println("2. Remove from Schedule");
            System.out.println("3. Return");
            System.out.print("What would you like to do: ");

            selection = s.nextLine();

            if (selection.equalsIgnoreCase("q")) {
                break;
            } else if (selection.equals("1")) {
                addToScheduleAPI();
            } else if (selection.equals("2")) {
                removeFromScheduleAPI();
            } else if (selection.equals("3")){
                break;
            }
            else {
                System.out.println("Error: type in an integer value 1-3.");
            }
        }
    }

    public static void addToScheduleAPI() {

        boolean running = true;
        String selection;

        System.out.println();

        while (running) {
            System.out.println("Which of the following items would you like to add to the schedule?");
            System.out.println("1. Lights");
            System.out.println("2. Appliances");
            System.out.println("3. Thermostat");
            System.out.println("4. Return");

            selection = s.nextLine();

            if (selection.equalsIgnoreCase("q")) {
                break;
            } else if (selection.equals("1")) {
                addLightToScheduleCLI();
            } else if (selection.equals("2")) {
                addApplianceToScheduleCLI();
            } else if (selection.equals("3")) {
                addTempToScheduleCLI();
            } else if(selection.equals("4")){
                break;
            } else {
                System.out.println("Error: type in an integer value 1-4");
            }
        }
    }

    public static void removeFromScheduleAPI() {
        System.out.println();
        System.out.println("Currently Unavailable Feature!");
        System.out.println();

    } // TODO: Michael

    //DONE
    public static void applianceCLI() {
        boolean running = true;
        String selection;
        Integer sInt;

        while (running) {
            System.out.println();
            System.out.println("Room: "+ currentRoom.getRoomName() + " --- Appliance Menu" );
            System.out.println("1. Add Appliance");
            System.out.println("2. Remove Appliance");
            System.out.println("3. Change Appliance Status");
            System.out.println("4. Add Appliance Change to Schedule");
            System.out.println("5. Return");
            System.out.print("What would you like to do: ");

            selection = s.nextLine();

            while (!integerCheck(selection, 5)) {
                System.out.println("Error: type in an integer value.");
                System.out.print("Please enter a value that corresponds to an option: ");
                selection = s.nextLine();
            }

            sInt = Integer.parseInt(selection);
            if (sInt == 1) {
                createApplianceCLI();
            } else if (sInt == 2) {
                removeApplianceCLI();
            } else if (sInt == 3) {
                adjustApplianceCLI();
            } else if (sInt == 4) {
                addApplianceToScheduleCLI();
            } else if (sInt == 5) {
                running = false;
            }
        }
    }

    //DONE
    public static void createApplianceCLI() {
        boolean running = true;
        String selection;
        String selection2;
        String sel;
        System.out.println();
        System.out.println("Create Appliance:");
        System.out.println("-----------------");

        while (running) {

            System.out.println();
            System.out.print("Please enter the name of the appliance you would like to create: ");
            selection = s.nextLine();
            if (selection.equalsIgnoreCase("q")) {
                break;
            }
            System.out.print("Please confirm the name of the appliance you would like to create: ");
            selection2 = s.nextLine();
            if (selection2.equalsIgnoreCase("q")) {
                break;
            } else {

                if (selection.equalsIgnoreCase(selection2)) {
                    System.out.print("Will this appliance require special permissions (Y/N): "); // TODO
                    sel = s.nextLine();
                    while (!sel.equalsIgnoreCase("y") && !sel.equalsIgnoreCase("n")) {
                        System.out.println("Error: please enter 'y' or 'n'");
                        sel = s.nextLine();
                    }

                    if (sel.equalsIgnoreCase("y")) {
                        currentRoom.addAppliance(selection, true);
                    } else {
                        currentRoom.addAppliance(selection, false);
                    }

                    System.out.println();
                    System.out.println("Your appliance " + selection + " has been created!");
                    running = false;
                } else {
                    System.out.println("Your names do not match");
                }
            }
        }
    }

    //DONE
    public static void removeApplianceCLI() {
        boolean running = true;
        String selection;

        System.out.println();
        System.out.println("Appliance Removal:");
        System.out.println("------------------");
        if (currentRoom.applianceMap.size() > 0) {
            while (running) {

                currentRoom.printAppliances();
                System.out.print("Please enter the name of the item you wish to remove: ");
                selection = s.nextLine();

                if (selection.equalsIgnoreCase("q")) {
                    break;
                } else if (currentRoom.applianceMap.containsKey(selection)) {
                    System.out.print("Would you like to delete the appliance - " + selection + " - (Y/N): ");
                    String sel = s.nextLine();

                    while (!sel.equalsIgnoreCase("y") && !sel.equalsIgnoreCase("n")) {
                        System.out.print("Error: please enter 'y' or 'n': ");
                        sel = s.nextLine();
                    }

                    if (sel.equalsIgnoreCase("y")) {
                        currentRoom.removeAppliance(selection);
                        running = false;
                    }
                } else {
                    System.out.println("This appliance does not exist!");
                    System.out.println();
                }
            }
        }else{
            System.out.println("You have no appliances yet!");
        }

    }

    //DONE
    public static void adjustApplianceCLI() {
        boolean running = true;
        String selection;

        System.out.println();
        System.out.println("Appliance Adjust:");
        System.out.println("-----------------");
        if (currentRoom.applianceMap.size() > 0) {
            while (running) {
                System.out.println();
                System.out.println("Choose an appliance to adjust.");
                currentRoom.printAppliances();
                System.out.print("Please enter the name of the item you wish to adjust: ");
                selection = s.nextLine();

                if (selection.equalsIgnoreCase("q")) {
                    break;
                } else if (currentRoom.applianceMap.containsKey(selection)) {
                    System.out.print("Would you like to adjust the appliance - " + selection + " - (Y/N): ");
                    String sel = s.nextLine();

                    while (!sel.equalsIgnoreCase("y") && !sel.equalsIgnoreCase("n")) {
                        System.out.print("Error: please enter 'y' or 'n': ");
                        sel = s.nextLine();
                    }

                    if (sel.equalsIgnoreCase("y")) {
                        currentRoom.applianceMap.get(selection).changeStatus();
                        running = false;
                    }
                } else {
                    System.out.println("This appliance does not exist!");
                    System.out.println();
                }
            }
        }else{
            System.out.println("You have no appliances yet!");
        }
    }

    public static void addApplianceToScheduleCLI() {

        boolean running = true;
        String objSel;
        String daySel;
        String hourSel;
        String minSel;
        String APMSel;
        String statusSel;

        String objSelected;
        String daySelected;
        String timeSelected;
        String APMSelected;
        Boolean statusSelected;

        System.out.println();
        System.out.println("Add Appliance To Schedule:");
        System.out.println("-----------------");

        if (currentRoom.applianceMap.size() > 0) {
            while (running) {
                currentRoom.printAppliances();
                System.out.println("Please enter the name of the item you wish to add to the schedule: ");
                objSel = s.nextLine();

                if (objSel.equalsIgnoreCase("q")) {
                    break;
                } else if (currentRoom.applianceMap.containsKey(objSel)) {

                    System.out.print(objSel + "Would you like to add this appliance to the schedule? (Y/N): ");
                    String sel = s.nextLine();

                    while (!sel.equalsIgnoreCase("y") && !sel.equalsIgnoreCase("n")) {
                        System.out.println("Error: please enter 'y' or 'n': ");
                        sel = s.nextLine();
                    }

                    if (sel.equalsIgnoreCase("y")) {

                        objSelected = objSel;

                        System.out.println("Please enter the following schedule option you would like:");
                        System.out.println("0. Return");
                        System.out.println("1. Monday");
                        System.out.println("2. Tuesday");
                        System.out.println("3. Wednesday");
                        System.out.println("4. Thursday");
                        System.out.println("5. Friday");
                        System.out.println("6. Saturday");
                        System.out.println("7. Sunday");
                        System.out.println("8. Weekdays");
                        System.out.println("9. Weekends");
                        daySel = s.nextLine();

                        while (!integerCheck(daySel, 0, 9)) {
                            System.out.println();
                            System.out.print("Error: type in an integer value 1-7:");
                            daySel = s.nextLine();
                        }

                        daySelected = daySel;

                        System.out.println("What hour would you like to schedule this for? Please enter a number between 1 and 12");
                        hourSel = s.nextLine();

                        while (!integerCheck(hourSel, 1, 12)) {
                            System.out.println();
                            System.out.print("Error: type in an integer value 1-12:");
                            hourSel = s.nextLine();
                        }

                        if (Integer.parseInt(hourSel) < 10) {
                            hourSel = "0" + hourSel;
                        }

                        System.out.println("What minute would you like to schedule this for? Please enter a number between 0 and 59");
                        minSel = s.nextLine();

                        while (!integerCheck(minSel, 0,59)) {
                            System.out.println();
                            System.out.print("Error: type in an integer value 0-59:");
                            minSel = s.nextLine();
                        }

                        if (Integer.parseInt(minSel) < 10) {
                            minSel = "0" + minSel;
                        }

                        System.out.println("Would you like this to go off at " + hourSel + ":" + minSel + " AM or PM?");
                        System.out.println("Please enter 'a' or 'p'");
                        APMSel = s.nextLine();

                        while (!APMSel.equalsIgnoreCase("a") && !APMSel.equalsIgnoreCase("p")) {
                            System.out.print("Error: please enter 'a' or 'p': ");
                            APMSel = s.nextLine();
                        }

                        if (APMSel.equals("a")) {
                            APMSelected = "AM";
                        } else {
                            APMSelected = "PM";
                        }

                        if (APMSelected.equals("AM")) {
                            timeSelected = hourSel + ":" + minSel;
                        } else {
                            Integer newHour = Integer.parseInt(hourSel) + 12;
                            timeSelected = newHour + ":" + minSel;
                        }

                        System.out.println("Would you like to schedule this object to be turned on or off?");
                        System.out.println("Please enter 'on' or 'off'");
                        statusSel = s.nextLine();

                        while (!statusSel.equalsIgnoreCase("on") && !statusSel.equalsIgnoreCase("off")) {
                            System.out.print("Error: please enter 'on' or 'off': ");
                            statusSel = s.nextLine();
                        }

                        if (statusSel.equals("on")) {
                            statusSelected = true;
                        } else {
                            statusSelected = false;
                        }

                        currentRoom.addApplianceToSchedule(objSelected, daySelected, timeSelected, statusSelected);

                        System.out.println("This appliance has been added to the schedule!");
                        System.out.println("Summary: " + objSelected + " - " + daySelected + " - " + timeSelected + " - " + statusSel);
                        running = false;
                    }
                    running = false;
                } else {
                    System.out.println("This appliance does not exist!");
                    System.out.println();
                }
            }
        } else{
            System.out.println("You have no appliances yet!");
        }
    }

    //DONE
    public static void lightCLI() {
        boolean running = true;
        String selection;
        Integer sInt;

        while (running) {
            System.out.println();
            System.out.println("Room: "+ currentRoom.getRoomName() + " --- Light Menu" );
            System.out.println("1. Add Light");
            System.out.println("2. Remove Light");
            System.out.println("3. Adjust Light");
            System.out.println("4. Add Light Change to Schedule");
            System.out.println("5. Return");
            System.out.println("What would you like to do: ");

            selection = s.nextLine();

            while (!integerCheck(selection, 5)) {
                System.out.println("Error: type in an integer value.");
                System.out.println("Please enter a value that corresponds to an option: ");
                selection = s.nextLine();
            }

            sInt = Integer.parseInt(selection);
            if (sInt == 1) {
                createLightCLI();
            } else if (sInt == 2) {
                removeLightCLI();
            } else if (sInt == 3) {
                adjustLightCLI();
            } else if (sInt == 4) {
                addLightToScheduleCLI();
            } else if (sInt == 5) {
                running = false;
            }
        }
    }

    //DONE
    public static void createLightCLI() {
        boolean running = true;
        String selection;
        String selection2;
        String sel;
        String sel2;
        System.out.println();
        System.out.println("Create Light:");
        System.out.println("-----------------");

        while (running) {

            System.out.println();
            System.out.println("Please enter the name of the light you would like to create: ");
            selection = s.nextLine();
            if (selection.equalsIgnoreCase("q")) {
                break;
            }
            System.out.print("Please confirm the name of the light you would like to create: ");
            selection2 = s.nextLine();
            if (selection2.equalsIgnoreCase("q")) {
                break;
            } else {

                if (selection.equalsIgnoreCase(selection2)) {

                    //Dimming
                    System.out.println("Is this light dimmable (Y/N): "); // TODO
                    sel2 = s.nextLine();
                    while (!sel2.equalsIgnoreCase("y") && !sel2.equalsIgnoreCase("n")) {
                        System.out.println("Error: please enter 'y' or 'n'");
                        sel2 = s.nextLine();
                        if (sel2.equalsIgnoreCase("q")) {
                            running = false;
                        }
                    }

                    //yy
                    if (sel2.equalsIgnoreCase("y")) {
                        currentRoom.addLight(selection, true);
                    }

                    //nn
                    else {
                        currentRoom.addLight(selection, false);
                    }

                    System.out.println();
                    System.out.println("Your light " + selection + " has been created!");
                    running = false;
                } else {
                    System.out.println("Your names do not match");
                }
            }
        }
    }

    public static void removeLightCLI() {
        boolean running = true;
        String selection;

        System.out.println();
        System.out.println("Light Removal:");
        System.out.println("------------------");
        if (currentRoom.lightMap.size() > 0) {
            while (running) {

                System.out.println("Choose an light to remove.");
                currentRoom.printLights();
                System.out.println("Please enter the name of the item you wish to remove: ");
                selection = s.nextLine();

                if (selection.equalsIgnoreCase("q")) {
                    break;
                } else if (currentRoom.lightMap.containsKey(selection)) {
                    System.out.println("Would you like to delete the light - " + selection + " - (Y/N): ");
                    String sel = s.nextLine();

                    while (!sel.equalsIgnoreCase("y") && !sel.equalsIgnoreCase("n")) {
                        System.out.println("Error: please enter 'y' or 'n': ");
                        sel = s.nextLine();
                    }

                    if (sel.equalsIgnoreCase("y")) {
                        currentRoom.removeLight(selection);
                        System.out.println("The light " + selection + " has been removed");
                        System.out.println(" ");
                        running = false;
                    }
                } else {
                    System.out.println("This light does not exist!");
                    System.out.println();
                }
            }
        }
        else{
            System.out.println("You have no lights yet!");
        }
    }

    public static void adjustLightCLI() {
        boolean running = true;
        String selection;
        String sel;
        String dimS;
        Double dim;

        System.out.println();
        System.out.println("Light Adjust:");
        System.out.println("-----------------");
        if (currentRoom.lightMap.size() > 0) {
            while (running) {
                System.out.println();
                System.out.println("Choose an light to adjust.");
                currentRoom.printLights();
                selection = s.nextLine();

                if (selection.equalsIgnoreCase("q")) {
                    break;
                } else if (currentRoom.lightMap.containsKey(selection)) {
                    System.out.println("Would you like to adjust the light - " + selection + " - (Y/N): ");
                    sel = s.nextLine();
                    while (!sel.equalsIgnoreCase("y") && !sel.equalsIgnoreCase("n")) {
                        System.out.println("Error: please enter 'y' or 'n': ");
                        sel = s.nextLine();
                    }
                    if (sel.equalsIgnoreCase("y")) {
                        System.out.println("What would you like to dim it to 0.0 - 1.0: ");
                        dimS = s.nextLine();
                        while (!doubleCheck(dimS, 1.0)) {
                            System.out.println();
                            System.out.println("Error: type in an  value between 0.0 and 1.0: ");
                            dimS = s.nextLine();
                        }
                        dim = Double.parseDouble(dimS);
                        currentRoom.lightMap.get(selection).dim(dim);
                        System.out.println("The light " + selection + " has been dimmed to" + dim);
                        System.out.println(" ");
                        running = false;
                    }
                } else {
                    System.out.println("This light does not exist!");
                    System.out.println();
                }
            }
        } else {
            System.out.println("You have no lights yet!");
        }
    }

    public static void addLightToScheduleCLI() {

        boolean running = true;
        String objSel;
        String daySel;
        String hourSel;
        String minSel;
        String APMSel;
        String statusSel;
        String dimSel;

        String objSelected;
        String daySelected;
        String timeSelected;
        String APMSelected;
        Integer statusSelected;
        Integer dimSelected;

        System.out.println();
        System.out.println("Add Light To Schedule:");
        System.out.println("-----------------");

        if (currentRoom.lightMap.size() > 0) {
            while (running) {
                currentRoom.printLights();
                System.out.println("Please enter the name of the item you wish to add to the schedule: ");
                objSel = s.nextLine();

                if (objSel.equalsIgnoreCase("q")) {
                    break;
                } else if (currentRoom.lightMap.containsKey(objSel)) {

                    System.out.print(objSel + "Would you like to add this light to the schedule? (Y/N): ");
                    String sel = s.nextLine();

                    while (!sel.equalsIgnoreCase("y") && !sel.equalsIgnoreCase("n")) {
                        System.out.println("Error: please enter 'y' or 'n': ");
                        sel = s.nextLine();
                    }

                    if (sel.equalsIgnoreCase("y")) {

                        objSelected = objSel;

                        System.out.println("Please enter the following schedule option you would like:");
                        System.out.println("0. Return");
                        System.out.println("1. Monday");
                        System.out.println("2. Tuesday");
                        System.out.println("3. Wednesday");
                        System.out.println("4. Thursday");
                        System.out.println("5. Friday");
                        System.out.println("6. Saturday");
                        System.out.println("7. Sunday");
                        System.out.println("8. Weekdays");
                        System.out.println("9. Weekends");
                        daySel = s.nextLine();

                        while (!integerCheck(daySel, 9)) {
                            System.out.println();
                            System.out.print("Error: type in an integer value 1-7:");
                            daySel = s.nextLine();
                        }

                        daySelected = daySel;

                        System.out.println("What hour would you like to schedule this for? Please enter a number between 1 and 12");
                        hourSel = s.nextLine();

                        while (!integerCheck(hourSel, 1, 12)) {
                            System.out.println();
                            System.out.print("Error: type in an integer value 1-12:");
                            hourSel = s.nextLine();
                        }

                        if (Integer.parseInt(hourSel) < 10) {
                            hourSel = "0" + hourSel;
                        }

                        System.out.println("What minute would you like to schedule this for? Please enter a number between 0 and 59");
                        minSel = s.nextLine();

                        while (!integerCheck(minSel, 0, 59)) {
                            System.out.println();
                            System.out.print("Error: type in an integer value 0-59:");
                            minSel = s.nextLine();
                        }

                        if (Integer.parseInt(minSel) < 10) {
                            minSel = "0" + minSel;
                        }

                        System.out.println("Would you like this to go off at " + hourSel + ":" + minSel + " AM or PM?");
                        System.out.println("Please enter 'a' or 'p'");
                        APMSel = s.nextLine();

                        while (!APMSel.equalsIgnoreCase("a") && !APMSel.equalsIgnoreCase("p")) {
                            System.out.print("Error: please enter 'a' or 'p': ");
                            APMSel = s.nextLine();
                        }

                        if (APMSel.equals("a")) {
                            APMSelected = "AM";
                        } else {
                            APMSelected = "PM";
                        }

                        if (APMSelected.equals("AM")) {
                            timeSelected = hourSel + ":" + minSel;
                        } else {
                            Integer newHour = Integer.parseInt(hourSel) + 12;
                            timeSelected = newHour + ":" + minSel;
                        }


                        System.out.println("What would you like to dim it to 0.0 - 1.0: ");
                        System.out.println("If the light cannot dim, anything above 0 will be equal to 1.");
                        System.out.print("New change: ");
                        dimSel = s.nextLine();

                        while (!doubleCheck(dimSel, 1.0)) {
                            System.out.println();
                            System.out.print("Error: type in an integer value 0.0 - 1.0 :");
                            dimSel = s.nextLine();
                        }

                        dimSelected = Integer.parseInt(dimSel);


                        currentRoom.addLightToSchedule(objSel, daySelected, timeSelected, dimSelected);

                        System.out.println("This light has been added to the schedule!");
                        System.out.println("Summary: " + objSelected + " - " + daySelected + " - " + timeSelected);
                    }
                    running = false;
                } else {
                    System.out.println("This light does not exist!");
                    System.out.println();
                }
            }
        }else {
            System.out.println("You have no lights yet!");
        }
    }

    public static void tempCLI() {
        boolean running = true;
        String selection;
        String sel;
        int sInt;


        while (running) {
            System.out.println();
            System.out.println("Temperature of " + currentRoom.getRoomName() + ": " + currentRoom.checkTemp() + ".");
            System.out.println("1. Adjust Temperature");
            System.out.println("2. Add Temperature Change to Schedule");
            System.out.println("3. Return");
            System.out.println("What would you like to do: ");


            selection = s.nextLine();

            while (!integerCheck(selection, 3)) {
                System.out.println("Error: type in an integer value.");
                System.out.println("Please enter a value that corresponds to an option: ");
                selection = s.nextLine();
            }

            sInt = Integer.parseInt(selection);
            if (sInt == 1) {
                adjustTempCLI();
            } else if (sInt == 2) {
                addTempToScheduleCLI();
            } else if (sInt == 3) {
                running = false;
            }

        }

    }
    public static void adjustTempCLI() {
        boolean running = true;
        boolean whileloopissue = true;
        String selection;
        int sInt;
        while (running) {
            System.out.println();
            System.out.print("Please enter the temperature you would like to set for this room (46-94): ");

            selection = s.nextLine();
            if (selection.equalsIgnoreCase("q")) {
                break;
            }
            else {
                while (!(integerCheck(selection, 46, 94))) {
                    System.out.println();
                    System.out.println("Error: type in an integer value (46-94).");
                    System.out.println("Please enter a correct value: ");
                    selection = s.nextLine();
                    if (selection.equalsIgnoreCase("q")) {
                        whileloopissue=false;
                    }
                }

                if(!whileloopissue){
                    break;
                }

                sInt = Integer.parseInt(selection);
                currentRoom.adjustTemp(sInt);
                running = false;
            }
        }
    }
    public static void addTempToScheduleCLI() {

        boolean running = true;
        String daySel;
        String hourSel;
        String minSel;
        String APMSel;
        String tempSel;

        String daySelected;
        String timeSelected;
        String APMSelected;
        Integer tempSelected;

        System.out.println();
        System.out.println("Add Thermostat To Schedule:");
        System.out.println("-----------------");

        while (running) {

            System.out.println("Please enter the following schedule option you would like:");
            System.out.println("0. Return");
            System.out.println("1. Monday");
            System.out.println("2. Tuesday");
            System.out.println("3. Wednesday");
            System.out.println("4. Thursday");
            System.out.println("5. Friday");
            System.out.println("6. Saturday");
            System.out.println("7. Sunday");
            System.out.println("8. Weekdays");
            System.out.println("9. Weekends");
            daySel = s.nextLine();

            while (!integerCheck(daySel, 9)) {
                System.out.println();
                System.out.print("Error: type in an integer value 1-9:");
                daySel = s.nextLine();
            }

            daySelected = daySel;

            System.out.println("What hour would you like to schedule this for? Please enter a number between 1 and 12");
            hourSel = s.nextLine();

            while (!integerCheck(hourSel, 1, 12)) {
                System.out.println();
                System.out.print("Error: type in an integer value 1-12:");
                hourSel = s.nextLine();
            }

            if (Integer.parseInt(hourSel) < 10) {
                hourSel = "0" + hourSel;
            }

            System.out.println("What minute would you like to schedule this for? Please enter a number between 0 and 59");
            minSel = s.nextLine();

            while (!integerCheck(minSel, 0, 59)) {
                System.out.println();
                System.out.print("Error: type in an integer value 0-59:");
                minSel = s.nextLine();
            }

            if (Integer.parseInt(minSel) < 10) {
                minSel = "0" + minSel;
            }

            System.out.println("Would you like this to go off at " + hourSel + ":" + minSel + " AM or PM?");
            System.out.println("Please enter 'a' or 'p'");
            APMSel = s.nextLine();

            while (!APMSel.equalsIgnoreCase("a") && !APMSel.equalsIgnoreCase("p")) {
                System.out.print("Error: please enter 'a' or 'p': ");
                APMSel = s.nextLine();
            }

            if (APMSel.equals("a")) {
                APMSelected = "AM";
            } else {
                APMSelected = "PM";
            }

            if (APMSelected.equals("AM")) {
                if (Integer.parseInt(hourSel) == 12) {
                    hourSel = "00";
                }
                timeSelected = hourSel + ":" + minSel;
            } else {
                Integer newHour = Integer.parseInt(hourSel) + 12;
                timeSelected = newHour + ":" + minSel;
            }

            System.out.println("What temperature would you like to set this item to?");
            System.out.println("Please enter an integer from 46-94");
            tempSel = s.nextLine();

            while (!integerCheck(tempSel, 46, 94)) {
                System.out.println();
                System.out.print("Error: type in an integer value 46-94:");
                tempSel = s.nextLine();
            }

            tempSelected = Integer.parseInt(tempSel);

            currentRoom.addThermoToSchedule(currentRoom.getThermostatName(),daySel, timeSelected, Double.valueOf(tempSelected));

            System.out.println("This thermostat has been added to the schedule!");
            System.out.println("Summary: " + daySelected + " - " + timeSelected + " - " + tempSelected + "degrees");
            running = false;
        }
    }

    public static void houseManipulationCLI() {
        boolean running = true;
        String sel;
        Integer selI;

        while (running) {
            System.out.println();
            System.out.println("House Manipulation");
            System.out.println("------------------");
            System.out.println("1. Turn ON all lights");
            System.out.println("2. Turn OFF all lights");
            System.out.println("3. Turn OFF all appliances");
            System.out.println("4. Set house temperature");
            System.out.println("5. Return");
            System.out.println("What would you like to do: ");

            sel = s.nextLine();
            while (!integerCheck(sel, 5)) {
                System.out.println("Error: type in an integer value 1-5: ");
                sel = s.nextLine();
            }
            selI = Integer.parseInt(sel);
            if (selI == 1) {
                currentHouse.turnOnLights();
            } else if (selI == 2) {
                currentHouse.turnOffLights();
            } else if (selI == 3) {
                currentHouse.turnOffAppliances();
            } else if (selI == 4) {
                changeHouseTempCLI();
            } else if (selI == 5) {
                running = false;
            }
        }
    }
    public static void changeHouseTempCLI() {
        boolean running = true;
        String selection;
        int sInt;
        while (running) {
            System.out.println();
            System.out.println("Please enter the temperature you would like to set for the house (46-94):");
            selection = s.nextLine();
            if (selection.equalsIgnoreCase("q")) {
                break;
            }
            while (!(integerCheck(selection, 46, 94))) {
                System.out.println();
                System.out.println("Error: type in an integer value (46-94).");
                System.out.println("Please enter a correct value: ");
                selection = s.nextLine();
                if (selection.equalsIgnoreCase("q")) {
                    break;
                }
            }
            sInt = Integer.parseInt(selection);
            currentHouse.changeHouseTemp(sInt);
            running = false;
        }

    }

    public static void contactESCLI() {
        boolean running = true;
        String sel;
        Integer selI;
        while (running) {
            System.out.println();
            System.out.println("Contact Emergency Services");
            System.out.println("--------------------------");
            System.out.println("1. Police Department");
            System.out.println("2. Fire Department");
            System.out.println("3. Ambulance");
            System.out.println("4. Return");
            System.out.println("Who would you like to contact: ");

            sel = s.nextLine();
            while (!integerCheck(sel, 4)) {
                System.out.println("Error: type in an integer value 1-4: ");
                sel = s.nextLine();
            }
            selI = Integer.parseInt(sel);

            if (selI == 1) {
                System.out.println("Contacting the Police Department right now.");
                System.out.println();
                running = false;
            } else if (selI == 2) {
                System.out.println("Contacting the Fire Department right now.");
                System.out.println();
                running = false;
            } else if (selI == 3) {
                System.out.println("Contacting Medical staff right now.");
                System.out.println();
                running = false;
            } else if (selI == 4) {
                running = false;
            }

        }
    }

    public static void helpCLI() {
        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("Help Menu:");
            System.out.println("----------");
            System.out.println("1. Learn about Creating a room");
            System.out.println("2. Learn about Navigate between rooms");
            System.out.println("3. Learn about User Options");
            System.out.println("4. Learn about House Manipulation");
            System.out.println("5. Learn about Contactingt Emergency Services");
            System.out.println("6. Return");
            System.out.println();
            System.out.print("With what would you like help with: ");
            String sel = s.nextLine();
            while (!integerCheck(sel, 6)) {
                System.out.print("Error: type in an integer value 1-6:");
                sel = s.nextLine();
            }
            Integer selI = Integer.parseInt(sel);
            if (selI == 1) {
                System.out.println();
                System.out.println("Creating a Room");
                System.out.println("---------------");
                System.out.println("When creating a room, you will be asked to provide a name for the room.");
                System.out.println("After that, you will be asked to type in the information again to verify the name.");
                System.out.println("If the names do not match, you will be prompted to provide the proper name.");
                System.out.println("After all the information is correctly inputted a new room will be added to the house.");
            } else if (selI == 2) {
                System.out.println();
                System.out.println("Navigating to a Room");
                System.out.println("--------------------");
                System.out.println("This option allows you to navigate to rooms that are in the house.");
                System.out.println("A list of rooms will appear and the user is prompted to enter the corresponding number.");
                System.out.println("When you navigate, it will output the current room you are in as well as its current temperature.");
                System.out.println("After this you can navigate to the appliance, light and temperature menu to manipulate those iteams in that room.");
            } else if (selI == 3) {
                System.out.println("User Options");
                System.out.println("------------");
                System.out.println("This option allows you to create new users as well as delete old ones.");
                System.out.println("If you choose to create or delete a user, you must provide a name.");
                System.out.println("You can print a list of current rooms within the house.");
                System.out.println("Finally, you can get a list of all appliances and lighting in a specific room.");
            } else if (selI == 4) {
                System.out.println();
                System.out.println("House Manipulation");
                System.out.println("----------");
                System.out.println("This option allows you to easily manipulate all aspects of your house from a single click.");
                System.out.println("One can turn ON or OFF all of their lights and appliances for the entire house.");
                System.out.println("On top of that they can manipulate the room temperature for every room.");
                System.out.println("This feature is ideally used when the user leaves the house and wants it to be energy efficient.");

            } else if (selI == 5) {
                System.out.println();
                System.out.println("");
                System.out.println("Contacting Emergency Services");
                System.out.println("-----------------------------");
                System.out.println("This menu allows for users to get into contact with emergency personnel.");
                System.out.println("You can contact the Police Department.");
                System.out.println("You can contact the Fire Department.");
                System.out.println("You can contact medical personnel for an ambulance.");
                System.out.println("The system will then notify the authorities and help will be on its way.");
            } else if (selI == 6) {
                running = false;
            }

        }

    }


    public static void userCLI() {
        boolean running = true;
        String sel;
        Integer selI;


        while (running) {
            System.out.println();
            System.out.println("User Menu");
            System.out.println("---------");
            System.out.println("1. Create User");
            System.out.println("2. Delete User");
            System.out.println("3. Print Room List");
            System.out.println("4. Get Room Appliances");
            System.out.println("5. Get Room Lights");
            System.out.println("6. Return");
            System.out.print("Please enter a digit 1-6: ");
            sel = s.nextLine();
            while (!integerCheck(sel, 6)) {
                System.out.print("Error: type in an integer value 1-6: ");
                sel = s.nextLine();
            }
            System.out.println();
            selI = Integer.parseInt(sel);
            if (selI == 1) {
                addUserCLI();
            } else if (selI == 2) {
                deleteUserCLI();
            } else if (selI == 3) {
                currentHouse.printRooms();
            } else if (selI == 4) {
                currentHouse.printAppliances();
            } else if (selI == 5) {
                currentHouse.printLights();
            } else if (selI == 6) {
                running = false;
            }
        }
    }
    public static void addUserCLI() {
        boolean running = true;
        String sel;
        String sel2;
        String pinS;
        String pin2S;
        Integer pin;
        Integer pin2;
        User userToAdd;

        while (running) {
            System.out.println();
            System.out.print("Please enter the name of the user you want to create: ");
            sel = s.nextLine();
            if (sel.equalsIgnoreCase("q")) {
                break;
            }

            System.out.print("Please re-enter the name of the user you want to create: ");
            sel2 = s.nextLine();
            if (sel2.equalsIgnoreCase("q")) {
                break;
            }

            while (!sel.equalsIgnoreCase(sel2)) {
                System.out.println();
                System.out.println("Error names do not match...");
                System.out.println();

                System.out.print("Please enter the name of the user you want to create: ");
                sel = s.nextLine();
                if (sel.equalsIgnoreCase("q")) {
                    break;
                }

                System.out.print("Please re-enter the name of the user you want to create: ");
                sel2 = s.nextLine();
                if (sel2.equalsIgnoreCase("q")) {
                    break;
                }
            }

            System.out.print("Please enter a numerical pin: ");
            pinS = s.nextLine();
            if (pinS.equalsIgnoreCase("q")) {
                break;
            }
            while (!integerCheck(pinS)) {
                System.out.print("Error: please enter a numerical pin: ");
                pinS = s.nextLine();
                if (pinS.equalsIgnoreCase("q")) {
                    break;
                }
            }
            System.out.print("Please confirm your pin entry: ");
            pin2S = s.nextLine();
            if (pin2S.equalsIgnoreCase("q")) {
                break;
            }
            while (!integerCheck(pinS)) {
                System.out.print("Error: please enter a numerical pin: ");
                pinS = s.nextLine();
                if (pin2S.equalsIgnoreCase("q")) {
                    break;
                }
            }

            while (!pinS.equalsIgnoreCase(pin2S)) {
                System.out.println();
                System.out.println("Pins do not match.");
                System.out.println();
                System.out.print("Please enter a numerical pin: ");
                pinS = s.nextLine();
                if (pinS.equalsIgnoreCase("q")) {
                    break;
                }
                while (!integerCheck(pinS)) {
                    System.out.print("Error: please enter a numerical pin: ");
                    pinS = s.nextLine();
                    if (pinS.equalsIgnoreCase("q")) {
                        break;
                    }
                }
                System.out.print("Please confirm your pin entry: ");
                pin2S = s.nextLine();
                if (pin2S.equalsIgnoreCase("q")) {
                    break;
                }
                while (!integerCheck(pinS)) {
                    System.out.print("Error: please enter a numerical pin: ");
                    pinS = s.nextLine();
                    if (pin2S.equalsIgnoreCase("q")) {
                        break;
                    }
                }

            }
            pin = Integer.parseInt(pinS);
            userToAdd = new User(sel, pin);
            currentHouse.addUser(userToAdd);
            running = false;
        }

    }
    public static void deleteUserCLI() {
        Boolean running = true;
        String username;
        String pinS;
        int pin;
        List<User> userListIn;
        List<User> userListOut = new ArrayList<>();
        boolean potentialBreak = false;

        while(running){
            System.out.println();
            System.out.println("Please enter the name of the user you wish to delete (Case Sensitive).");
            System.out.println("Or type 'q' to return.");
            System.out.print("User name to delete: ");
            username = s.nextLine();
            if(username.equalsIgnoreCase("q")){
                break;
            }
            if(username.equalsIgnoreCase(currentUser.getName())){
                System.out.println();
                System.out.println("Error: you cannot delete an account that is currently signed in!");
                System.out.println("Rerouting you back to the user options screen");
                break;
            }
            System.out.println();
            System.out.println("Please enter the pin of the user you wish to delete: ");
            System.out.println("Or type 'q' to return.");
            System.out.print("User's pin: ");
            pinS = s.nextLine();
            if(pinS.equalsIgnoreCase("q")){
                break;
            }
            while (!integerCheck(pinS)) {
                System.out.println();
                System.out.println("Error: type in an integer value.");
                System.out.print("Please enter the pin of the user you wish to delete: ");
                pinS = s.nextLine();
            }
            pin = Integer.parseInt(pinS);



            try{
                userListIn = JsonUtil.listFromJsonFile("./src/main/files/usersList", User.class);
                for (int i = 0; i < userListIn.size(); i++) {
                    if(userListIn.get(i).getName().equalsIgnoreCase(username) && userListIn.get(i).getPin().equals(pin)){
                        userListIn.remove(i);
                        userListOut = userListIn;
                    }
                }

                if(userListOut.size() == userListIn.size()){
                    try {
                        currentHouse.userList.remove(username);
                        JsonUtil.toJsonFile("./src/main/files/usersList", userListOut);
                        System.out.println();
                        System.out.println(username + " was successfully deleted from the user list!");

                    } catch (Exception e) {
                        System.out.println(e);
                    }
                } else{
                    System.out.println();
                    System.out.println("Error: the user you entered did not exist or the pin is incorrect!");
                }

            } catch (Exception e){
                System.out.println(e);
                running=false;
                potentialBreak = true;
                break;
            }
            if(potentialBreak){
                break;
            }


        }


    }

    public static boolean signIn(String username, Integer pin) throws IOException {
        List<User> userListOut;
        User u;
        boolean ifExists = false;
        try {
            userListOut = JsonUtil.listFromJsonFile("./src/main/files/usersList", User.class);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        if (userListOut.size() == 0) {
            return false;
        } else {
            for (int i = 0; i < userListOut.size(); i++) {
                if ((userListOut.get(i).getName().equalsIgnoreCase(username)) && (userListOut.get(i).getPin().equals(pin))) {
                    u = new User(userListOut.get(i).getName(), userListOut.get(i).getPin());
                    currentHouse.addUserFromLogin(u);
                    ifExists = true;
                } else {
                    u = new User(userListOut.get(i).getName(), userListOut.get(i).getPin());
                    currentHouse.addUserFromLogin(u);
                }
            }
            return ifExists;
        }
    }

    public static void saveUsers() {
        HashMap<String, User> userMap = currentHouse.getUserList();
        List<User> userList = new ArrayList<>(userMap.values());


        try {
            JsonUtil.toJsonFile("./src/main/files/usersList", userList);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static boolean integerCheck(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean integerCheck(String s, Integer bounds) {
        try {
            Integer sInt = Integer.parseInt(s);
            if (sInt <= bounds && sInt >= 1) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

    }

    public static boolean integerCheck(String s, Integer lower, Integer upper) {
        try {
            Integer sInt = Integer.parseInt(s);
            if (sInt <= upper && sInt >= lower) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

    }

    public static boolean doubleCheck(String s, Double bounds) {
        try {
            Double sDouble = Double.parseDouble(s);
            if (sDouble <= bounds && sDouble >= 0) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

    }

    public static void main(String[] args) {
        run();

    }


}
