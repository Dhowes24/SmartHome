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

    /**
     * Gets the current if of the appliance
     * @return string - the id of the current appliance
     */
    public String getID() {
    return this.ID;
}

    /**
     * Returns whether the user has specific preferences
     * @param user - the user being observed
     * @return - the status fo the user preferences
     */
    public boolean getUserPref(User user) {
    if(!this.preferences.containsKey(user)){
        this.preferences.put(user, false);
    }
    return this.preferences.get(user);
}

    /**
     * Chancges the status of the appliance to the current users preference
     * @param user - the user being obeserved
     */
    public void setToPreference(User user){
    if(!this.preferences.containsKey(user)){
        this.preferences.put(user, false);
    }
    this.status = preferences.get(user);
}


    /**
     * Changes the status of the current appliance, if ON turn to OFF and vice versa
     */
    public void changeStatus() {
    if (this.status) {
        this.status = false;
    }
    else {
        this.status = true;
    }

}

    /**
     * Gets the current status of the appliance
     * @return boolean - true if the appliance is ON, false if otherwise
     */
    public boolean getStatus() {
    return this.status;
}

    /**
     * Changes the preference for the current appliance
     * @param user - the user being observed
     * @param preference - the new preference
     */
    public void changeUserPref(User user, boolean preference) {
    if (!this.preferences.containsKey(user)) {
        this.preferences.put(user, preference);
    } else {
        this.preferences.replace(user, preference);
    }
}




}
