import java.util.HashMap;

public class User {
    String Name;
    Integer Pin=0;
    //Preferences preferences;
    //Permissions permissions;

    public User(){}
    public User(String name){
        this.Name = name;
    }
    public User(String Name, Integer pin){
        //Preferences preferences = new Preferences();
        //Permissions permissions = new Permissions();
        this.Name = Name;
        this.Pin = pin;

    }

    /**
     * Returns the name of a user
     * @return String - the name of the user
     */
    public String getName(){return this.Name;}

    /**
     * Returns the pin of a user
     * @return Integer - the pin of the user
     */
    public Integer getPin(){return this.Pin;}

    /**
     * Sets the name of the user
     * @param name - the name to set
     */
    public void setName(String name){this.Name=name;}



    /**
     * Creates a user and adds them to the house user list
     * Can set preferences permissions in user creation
     * @param Name
     * @return String - confirmation if the user was properly created
     */
    public String createUser(String Name){
        if(!House.userList.containsKey(Name)) {
            User newUser = new User(Name);
            House.addUser(newUser);
            return ("User successfully created");
        }
        else{
            return ("User could not be created because name is already in use");
        }

    }

    /**
     * deletes a user from the house user list
     * @param Name - the name of the user to be deleted
     * @return String - confirmation of whether the user was deleted or not
     */
    public String deleteUser(String Name){
        if(House.userList.containsKey(Name)){
            House.userList.remove(Name);
            return ("User successfully deleted");
        }
        else{
            return ("User could not be deleted because it does not exist");
        }
    }

    /**
     * prints the list of all rooms in the house
     * @return HashMap - the list of rooms
     */
    public HashMap printRoomList(){
        return House.roomList;

    }

    /**
     * prints all appliances linked to a certain room
     * @param roomName - the name of the room being observed
     * @return HashMap - a map of appliances
     */
    public HashMap getRoomApplianceList(String roomName){
        if(House.roomList.containsKey(roomName)){
            return House.accessRoom(roomName).applianceMap;}
        else{
            return null;
        }
    }

    /**
     * Prints all of the lights linked to a certain room
     * @param roomName - the name of the room being observed
     * @return HashMap - a map of lights
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