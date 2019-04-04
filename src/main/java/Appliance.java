public class Appliance {

private String ID;
private boolean status;
private boolean userPref;

/**
 *Creates a new appliance.
 * @param name
 * @param pin
 */
public Appliance(String name, boolean pin ) {
    this.ID = name;
    this.status = false;
    if (pin) {
        this.userPref = true;
    }
    else {
        this.userPref = false;
    }
}

public Appliance() {
    this.ID = "default";
    this.status = false;
    this.userPref = true;
}

//returns the ID of the appliance
public String getID() {
    return this.ID;
}

//returns a boolean representing if that appliance has a user preference
public boolean getUserPref() {
    return this.userPref;
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
public void changeUserPref() {
    if (userPref) {
        this.userPref = false;
    }
    else {
        this.userPref = true;
    }
}

}