import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class House {

    public static HashMap<String, User> userList = new HashMap<>();
    public static HashMap<String, Room> roomList = new HashMap<>();
    public static MasterSchedule schedule = new MasterSchedule();
    public static int globaltemp = 65;

    /**
     * Prints all the created rooms in the house
     */
    public static void printRooms() {

        Integer count = 0;
        for (String key: roomList.keySet()) {
            System.out.println(count+1 + ": " + key);
            count++;
        }
    }

    /**
     * Returns the current user list
     * @return HashMap - the user list
     */
    public static HashMap<String, User> getUserList(){return userList;}

    /**
     * Gets the key for an individual room
     * @param index - the index of the room being observed
     * @return String - the key of the room being observed
     */
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

    /**
     * Creates a new room object in the house
     * @param name - the name for the new room
     */
    public static void addRoom(String name){
        if(!roomList.containsKey(name)){
            Room newRoom = new Room(name,schedule, globaltemp);
            roomList.put(name, newRoom);
        } else{
            System.out.println("Room already exists!");
        }
    }

    /**
     * Removes a room from the hosue if it exists
     * @param name - the name of the room to be removed
     */
    public static void removeRoom(String name){
        if(roomList.containsKey(name)){
            roomList.remove(name);
        } else{
            System.out.println("Room does not exists!");
        }
    }

    /**
     * Returns the room if it exists
     * @param roomName - the name of the room trying being accessed
     * @return Room - the room being accessed
     */
    public static Room accessRoom(String roomName){
        if(roomList.containsKey(roomName)){
            return roomList.get(roomName);
        }
        return null;
    }

    /**
     * Adds a new user to the hosue if it does not already exist
     * @param user - the new user to be added
     */
    public static void addUser(User user){

        if(userList.containsKey(user.Name)){
            System.out.println("User already exists!");
        }else {
            System.out.println("Added user.");
            userList.put(user.Name, user);
        }


    }

    /**
     * Adds users to the house once the program stats
     * @param user - the user to add
     */
    public static void addUserFromLogin(User user){
        if(!userList.containsKey(user.Name)) {
            userList.put(user.Name, user);
        }
    }

    /**
     * Prints all appliances in the house
     */
    public static void printAppliances(){
        Room r;
        System.out.println("Printing Appliance's in House");
        System.out.println("-----------------------------");
        for (String key: roomList.keySet()){
            r = roomList.get(key);
            for(String appliance: r.applianceMap.keySet()){
                System.out.println("Room: "+ key + " ---- Appliance: "+appliance);
            }
        }

    }

    /**
     * Ptins all the lights in the house
     */
    public static void printLights(){
        Room r;
        System.out.println("Printing Lights's in House");
        System.out.println("-----------------------------");
        for (String key: roomList.keySet()){
            r = roomList.get(key);
            for(String light: r.lightMap.keySet()){
                System.out.println("Room: "+ key + " ---- Light: "+light);
            }
        }
    }




    /**
     * Iterates through every room and changes that rooms temperature
     * @param newTemp - the new temperature of the rooms
     */
    public void changeHouseTemp(Integer newTemp){
        Room r;
        for (String key: roomList.keySet()){
            r = roomList.get(key);
            r.adjustTemp(newTemp);
        }
        globaltemp = newTemp;
        System.out.println();
        System.out.println("The temperature in every room is now set at "+newTemp+" degrees.");
        System.out.println();
    }


    /**
     * Iterates through every room, iterates through every appliance in the room and turns them off
     */
    public void turnOffAppliances(){
        Room r;
        HashMap<String, Appliance> applianceMap;
        Appliance a;
        for (String key: roomList.keySet()) {
            r = roomList.get(key);
            applianceMap = r.applianceMap;
            for(String appliance: applianceMap.keySet()){
                a = applianceMap.get(appliance);
                if(a.getStatus()==true){
                    a.changeStatus();
                }
            }
        }
        System.out.println();
        System.out.println("All appliances have been turned off!");
        System.out.println();
    }

    /**
     * Iterates through every room, iterates through every light in the room and turns them on
     */
    public void turnOnLights(){
        Room r;
        HashMap<String, Light> lightMap;
        Light l;
        for (String key: roomList.keySet()) {
            r = roomList.get(key);
            lightMap = r.lightMap;
            for(String light: lightMap.keySet()){
                l = lightMap.get(light);
                l.turnOn();
            }
        }
        System.out.println();
        System.out.println("All lights have been turned on!");
        System.out.println();
    }

    /**
     * Iterates through every room, iterates through every light in the room and turns them off
     */
    public void turnOffLights(){
        Room r;
        HashMap<String, Light> lightMap;
        Light l;
        for (String key: roomList.keySet()) {
            r = roomList.get(key);
            lightMap = r.lightMap;
            for(String light: lightMap.keySet()){
                l = lightMap.get(light);
                l.turnOff();
            }
        }
        System.out.println();
        System.out.println("All lights have been turned off!");
        System.out.println();
    }




}
