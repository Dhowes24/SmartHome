import java.util.HashMap;

public class House {

    HashMap<String, Room> roomList = new HashMap<>();

    //HashMap<String, scheduleItem> scheduleList = new HashMap<>();

    public void addRoom(String Name){
        if(!roomList.containsKey(Name)){
        Room newRoom = new Room(Name);
        roomList.put(Name, newRoom);
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

    public void printScheduleList(){

    }

    public void changeHouseTemp(){

    }

    public void turnAppliances(){

    }

    public void turnOffLights(){

    }

}
