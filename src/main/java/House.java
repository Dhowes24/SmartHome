import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class House {

    public static HashMap<String, User> userList = new HashMap<>();
    public static HashMap<String, Room> roomList = new HashMap<>();

    //HashMap<String, scheduleItem> scheduleList = new HashMap<>();

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

    public void changeHouseTemp(Integer newTemp){
        Integer count = 0;
        for (String key: roomList.keySet()) {
            roomList.get(key).adjustTemp(newTemp);
            System.out.println(count+1 + ": " + key +", current temperature: " + roomList.get(key).checkTemp());
            count++;
        }
    }

    public void turnAppliances(){

    }

    public void turnOnLights(){

    }

    public void turnOffLights(){

    }

    public void turnOffAppliances(){

    }
}
