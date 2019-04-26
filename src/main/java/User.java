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

    public String getName(){return this.Name;}
    public Integer getPin(){return this.Pin;}

    public void setName(String name){this.Name=name;}
    public void setPin(Integer pin){this.Pin=pin;}



    /**
     * Creates a user and adds them to the house user list
     * Can set preferences permissions in user creation
     * @param Name
     * @return
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
     * @param Name
     * @return
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