import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class House {

    HashMap<String, User> userList = new HashMap<>();
    HashMap<String, Room> roomList = new HashMap<>();
    Room currentRoom = new Room();

    //HashMap<String, scheduleItem> scheduleList = new HashMap<>();

    private static boolean running = true;
    private static Scanner s = new Scanner(System.in);

    public void runCLI() {

        System.out.println("Would you like to 1) Create a new room or 2) Navigate to a room");

        String selection = s.nextLine();

        while (!integerCheck(selection)) {
            System.out.println("Error: type in an integer value");
            System.out.println("Please enter 1 or 2");
            selection = s.nextLine();
        }

        Integer sInt = Integer.parseInt(selection);

        if (sInt == 1) {

        } else {

        }

    }

    public void createRoomCLI() {

    }

    public void navigateRoomsCLI() {

        System.out.println("Your Rooms:");
        printRooms();
        System.out.println("Which room would you like to go to? (Enter the corresponding number)");
        String selection = s.nextLine();

        while (!integerCheck(selection)) {
            System.out.println("Error: type in an integer value");
            System.out.println("Please enter a value that corresponds to a room");
            selection = s.nextLine();
        }

        Integer sInt = Integer.parseInt(selection);

        currentRoom = roomList.get(getKey(sInt-1));
    }

    public void printRooms() {

        Integer count = 0;
        for (String key: roomList.keySet()) {
            System.out.println(count+1 + ": " + key);
            count++;
        }
    }

    public String getKey(Integer index) {

        Integer count = 0;
        for (String key: roomList.keySet()) {
            if (count == index) {
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

    public void addRoom(String name){
        if(!roomList.containsKey(name)){
            Room newRoom = new Room(name);
            roomList.put(name, newRoom);
            System.out.println(roomList.get(name).getRoomName());
        } else{
            //Return what ever we want to send back as an error
        }
    }

    public void removeRoom(String Name){
        if(roomList.containsKey(Name)){
            roomList.remove(Name);
        } else{
            //Return what ever we want to send back as an error
        }
    }

    public Room accessRoom(String roomName){
        if(roomList.containsKey(roomName)){
            return roomList.get(roomName);
        }
        return null;
    }

    public void addUser(User user){
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
