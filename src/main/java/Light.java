import java.util.HashMap;

public class Light {

    private String ID;
    private double currIntensity;
    private double defaultAmount;
    private boolean isDimmable;
    private HashMap<User, Double> preferences;


    public Light(String name, double defaultAmountt, boolean isDimmable) {
        this.ID = name;
        this.currIntensity = 0.0;
        this.isDimmable = isDimmable;
        if (amountIsValid(defaultAmount) && this.isDimmable) {
            this.defaultAmount = defaultAmountt;
        } else {
            this.defaultAmount = 1.0;
        }
        this.preferences = new HashMap<>();

    }

    public Light(String name, boolean isDimmable) {
        this.ID = name;
        this.currIntensity = 0.0;
        this.isDimmable = isDimmable;
        this.defaultAmount = 1.0;
        this.preferences = new HashMap<>();

    }

    /**
     * @return String - the ID of the light
     */
    public String getID() {
        return this.ID;
    }

    /**
     * @return double - the current currIntensity the light is operating on
     */
    public double getCurrIntensity() {
        return this.currIntensity;
    }

    /**
     * @return boolean - the current user preferences for the light
     */
    public double getPreference(User user) {
        if (this.isDimmable) {
            if (!this.preferences.containsKey(user)) {
                this.preferences.put(user,1.0 );
            }
            return this.preferences.get(user);
        }
        return 1.0;
    }

    /**
     * @return double - the current preferred currIntensity of the light
     */
    public double getDefaultAmount() {
        return this.defaultAmount;
    }

    /**
     * @return boolean - whether the light is dimable or not
     */
    public boolean getDimmable() {
        return this.isDimmable;
    }

    /**
     * Sets a new value to the preferred currIntensity
     *
     * @param newAmount - the new currIntensity of the preferred currIntensity
     */
    public void setDefaultAmount(double newAmount) {
        if (amountIsValid(newAmount)) {
            this.defaultAmount = newAmount;
        }
    }

    /**
     * Turns the lights currIntensity to the current preferred currIntensity
     */
    public void turnOn() {
        this.currIntensity = this.defaultAmount;
    }

    /**
     * Turns the lights currIntensity to zero turning it off
     */
    public void turnOff() {
        this.currIntensity = 0.0;
    }

    /**
     * Dims the light up or down to the new currIntensity
     *
     * @param newAmount - the new currIntensity that the light should be at
     */
    public void dim(double newAmount) {
        if (amountIsValid(newAmount)) {
            this.currIntensity = newAmount;
        }
    }

    /**
     * Checks to see if an currIntensity passed in by the user is valid (Between 0 and 1 inclusive)
     *
     * @param amount double - the currIntensity being checked
     * @return boolean - whether it is a valid currIntensity or not
     */
    public static boolean amountIsValid(double amount) {
        if (amount >= 0.0 && amount <= 1.0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets current intensity to user preference
     * @param user
     */
    public void setToPreference(User user){
        if(!this.preferences.containsKey(user)){
            this.preferences.put(user, 1.0);
        }
        this.currIntensity = this.preferences.get(user);
    }

    /**
     * Changes user preference to input value
     * @param user
     * @param preference
     */
    public void changeUserPref(User user, double preference) {
        if(amountIsValid(preference)) {
            if (!this.preferences.containsKey(user)) {
                this.preferences.put(user, preference);
            } else {
                this.preferences.replace(user, preference);
            }
        }
    }


}
