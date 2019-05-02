import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class House {

    public static HashMap<String, User> userList = new HashMap<>();
    public static HashMap<String, Room> roomList = new HashMap<>();
    public static MasterSchedule schedule = new MasterSchedule();

    public static void printRooms() {

        Integer count = 0;
        for (String key: roomList.keySet()) {
            System.out.println(count+1 + ": " + key);
            count++;
        }
    }

    public static HashMap<String, User> getUserList(){return userList;}
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
            Room newRoom = new Room(name,schedule);
            roomList.put(name, newRoom);
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

        if(userList.containsKey(user.Name)){
            System.out.println("User already exists!");
        }else {
            System.out.println("Added user.");
            userList.put(user.Name, user);
        }


    }

    public static void addUserFromLogin(User user){

        if(!userList.containsKey(user.Name)) {
            userList.put(user.Name, user);
        }


    }

    //TODO
    public static void printAppliances(){
        Room r;
        System.out.println("Printing Appliance's in House");
        System.out.println("-----------------------------");
        for (String key: roomList.keySet()){
            r = roomList.get(key);
            for(String appliance: r.applianceMap.keySet()){
                System.out.println("Room: "+ key + "\tAppliance: "+appliance);
            }
        }

    }

    public static void printLights(){

    }



    public void printScheduleList(){

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
