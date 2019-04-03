import java.util.HashMap;

public class User {
    House House;
    String Name;
    //Preferences preferences;
    //Permissions permissions;


    public User(House house, String Name){
        //Preferences preferences = new Preferences();
        //Permissions permissions = new Permissions();
        this.House = house;
        this.Name = Name;

    }



    /**
     * Creates a user and adds them to the house user list
     * Can set preferences permissions in user creation
     * @param Name
     * @return
     */
    public String createUser(String Name){
        if(!this.House.userList.containsKey(Name)) {
            User newUser = new User(this.House, Name);
            this.House.addUser(newUser);
            return ("User successfully created");
        }
        else{
            return ("User could not be created because name is already in use");
        }

    }

    /**
     * deletes a user from the house user list
     * @param Name
     * @return
     */
    public String deleteUser(String Name){
        if(this.House.userList.containsKey(Name)){
            this.House.userList.remove(Name);
            return ("User successfully deleted");
        }
        else{
            return ("User could not be deleted because it does not exist");
        }
    }

    /**
     * prints the list of all rooms in the house
     * @return
     */
    public HashMap printRoomList(){
        return House.roomList;

    }

    /**
     * prints all appliances linked to a certain room
     * @param roomName
     * @return
     */
    public HashMap getRoomApplianceList(String roomName){
        if(House.roomList.containsKey(roomName)){
            return House.accessRoom(roomName).applianceMap;}
        else{
            return null;
        }
    }

    /**
     * prints all lights linked to a certain room
     * @return
     */
    public HashMap getRoomLightList(String roomName){
        if(House.roomList.containsKey(roomName)){
            return House.accessRoom(roomName).lightMap;
        }
        else{
            return null;
        }


    }
}
