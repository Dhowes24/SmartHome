import java.util.HashMap;

public class Appliance {

private String ID;
private boolean status;

private HashMap<User, Boolean> preferences;

/**
 *Creates a new appliance.
 * @param name
 */
public Appliance(String name) {
    this.ID = name;
    this.status = false;
    this.preferences = new HashMap<>();
}

//returns the ID of the appliance
public String getID() {
    return this.ID;
}

//returns a boolean representing if that appliance has a user preference
public boolean getUserPref(User user) {
    if(!this.preferences.containsKey(user)){
        this.preferences.put(user, false);
    }
    return this.preferences.get(user);
}

public void setToPreference(User user){
    if(!this.preferences.containsKey(user)){
        this.preferences.put(user, false);
    }
    this.status = preferences.get(user);
}


//changes the status of the appliance from on to off and vice versa using a boolean representation
public void changeStatus() {
    if (this.status) {
        this.status = false;
    }
    else {
        this.status = true;
    }

}

//gets the status of the appliance
public boolean getStatus() {
    return this.status;
}


//changes the user preference of the appliance
public void changeUserPref(User user, boolean preference) {
    if(!this.preferences.containsKey(user)){
        this.preferences.put(user, preference);
    }
    else{
        this.preferences.replace(user, preference);
    }
}




}
