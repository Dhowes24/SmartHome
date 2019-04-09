import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class House {

    public static HashMap<String, User> userList = new HashMap<>();
    public static HashMap<String, Room> roomList = new HashMap<>();
    public static Room currentRoom = new Room();

    //HashMap<String, scheduleItem> scheduleList = new HashMap<>();

    private static boolean running = true;
    private static Scanner s = new Scanner(System.in);

    public static void houseCLI() {

        System.out.println("Would you like to 1) Create a new room, 2) Navigate to a room, or 3) Go back");

        String selection = s.nextLine();

        while (!integerCheck(selection)) {
            System.out.println("Error: type in an integer value");
            System.out.println("Please enter 1, 2, or 3");
            selection = s.nextLine();
        }

        Integer sInt = Integer.parseInt(selection);

        if (sInt == 1) {
            createRoomCLI();
        } else if (sInt == 2){
            navigateRoomsCLI();
        } else {
            // TODO: GO BACK
        }
    }

    public static void createRoomCLI() {

        System.out.println("Please enter the name of the room you would like to create:");
        String selection = s.nextLine();
        System.out.println("Please confirm the name of the room you would like to create:");
        String selection2 = s.nextLine();

        if (selection.equals(selection2)) {
            addRoom(selection);
            System.out.println("Your room has been created!");
            houseCLI();
        } else {
            System.out.println("Your names do not match");
            createRoomCLI();
        }
    }

    public static void navigateRoomsCLI() {

        if (roomList.size() > 0) {
            System.out.println("Your Rooms:");
            printRooms();
            System.out.println("Which room would you like to go to? (Enter the corresponding number)");

            selectRoomCLI();
        }

        System.out.println("You have no rooms yet!");
        houseCLI();
    }

    public static void selectRoomCLI() {

        String selection = s.nextLine();

        while (!integerCheck(selection)) {
            System.out.println("Error: type in an integer value");
            System.out.println("Please enter a value that corresponds to a room");
            selection = s.nextLine();
        }

        Integer sInt = Integer.parseInt(selection);

        if (sInt-1 >= roomList.size() || sInt == 0) {
            System.out.println("Error: There is no room corresponding to number: " + sInt);
            System.out.println("Please enter a value that corresponds to a room");
            navigateRoomsCLI();
        }

        currentRoom = accessRoom(getKey(sInt-1)); // remove 1 because it was added for user interface earlier
        roomStatusCLI();
    }

    public static void roomStatusCLI() {
        System.out.println("\nCurrent Room: " + currentRoom.getRoomName());
        System.out.println("Temperature: " + currentRoom.checkTemp());
        System.out.println("Lights:\n");
        currentRoom.printLights();
        System.out.println("Appliances:\n");
        currentRoom.printAppliances();

        System.out.println("Which of the following actions would you like to perform?");
        System.out.println("0. Go Back");
        System.out.println("1. Add Appliance");
        System.out.println("2. Remove Appliance");
        System.out.println("3. Change Appliance Status");
        System.out.println("4. Add Light");
        System.out.println("5. Remove Light");
        System.out.println("6. Adjust Light");
        System.out.println("7. Adjust Temperature");

        String selection = s.nextLine();

        while (!integerCheck(selection)) {
            System.out.println("Error: type in an integer value");
            System.out.println("Please enter a value that corresponds to an option");
            selection = s.nextLine();
        }

        Integer sInt = Integer.parseInt(selection);

        if (sInt < 0 || sInt > 7) {
            System.out.println("Error: Please select a valid option");
            roomStatusCLI();
        }

        System.out.println(sInt);

        if (sInt == 0) { // Go Back

            houseCLI();

        } else if (sInt == 1) { // Add Appliance

        } else if (sInt == 2) { // Remove Appliance

        } else if (sInt == 3) { // Change Appliance Status

        } else if (sInt == 4) { // Add Light

        } else if (sInt == 5) { // Remove Light

        } else if (sInt == 6) { // Adjust Light

        } else { // Adjust Temperature

        }
    }

    public static void main(String[] args) {
        houseCLI();
    }

    public static void printRooms() {

        Integer count = 0;
        for (String key: roomList.keySet()) {
            System.out.println(count+1 + ": " + key);
            count++;
        }
    }

    public static String getKey(Integer index) {

        Integer count = 0;
        for (String key: roomList.keySet()) {
            if (count.equals(index)) {
                return key;
            }
            count++;
        }
        return "not found";
    }

    public static boolean integerCheck(String s){
        try {
            Integer.parseInt(s);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

    public static void addRoom(String name){
        if(!roomList.containsKey(name)){
            Room newRoom = new Room(name);
            roomList.put(name, newRoom);
            System.out.println(roomList.get(name).getRoomName());
        } else{
            //Return what ever we want to send back as an error
        }
    }

    public static void removeRoom(String Name){
        if(roomList.containsKey(Name)){
            roomList.remove(Name);
        } else{
            //Return what ever we want to send back as an error
        }
    }

    public static Room accessRoom(String roomName){
        if(roomList.containsKey(roomName)){
            return roomList.get(roomName);
        }
        return null;
    }

    public static void addUser(User user){
        if(!userList.containsKey(user.Name))
            userList.put(user.Name, user);
    }


    public void printScheduleList(){

    }

    public void changeHouseTemp(){

    }

    public void turnAppliances(){

    }

    public void turnOffLights(){

    }
}
