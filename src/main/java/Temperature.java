import java.util.HashMap;

public class Temperature {

    private double temperature;
    private HashMap<User, Double> preferences;



    /**
     * @param temp double - the temperature the room will be at
     */
    public Temperature(double temp){
            if(isAmountValid(temp)){
                this.temperature = temp;
            }
            else{
                this.temperature = 60;
            }
        this.preferences = new HashMap<>();
    }

    /**
     * @return double - the current temperature
     */
    public double getTemp(){return this.temperature;}


    /**
     * Changes the current temperature of the room if it is a valid input
     * @param newTemp double - the new desired temperature of the room
     */
    public void setTemp(double newTemp){
        if(isAmountValid(newTemp)){
            this.temperature=newTemp;
        }
    }

    /**
     * Checks to see if the amount entered is a valid amount (Between 45 and 95 not inclusive)
     * @param amount double - the amount in question
     * @return boolean - whether or not the value fulfills the requirements
     */
    public boolean isAmountValid(double amount){
        if(amount>45 && amount < 95){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Sets the preference of a user to another number
     * @param user
     * @param amount
     */
    public void setTempPreference(User user, double amount){
        if(this.preferences.containsKey(user) && isAmountValid(amount)){
            this.preferences.replace(user, amount);
        }
        else if (!this.preferences.containsKey(user)){
            this.preferences.put(user,amount);
        }
    }

    /**
     * Set current temperature to preference
     * @param user
     */
    public void setToPreference(User user){
        if(!this.preferences.containsKey(user)){
            this.preferences.put(user, 60.0);
        }
        setTemp(this.preferences.get(user));

    }

    /**
     * get preferences for specific user
     * @param user
     * @return
     */
    public double getPreference (User user){
        if(!this.preferences.containsKey(user)){
            this.preferences.put(user, 60.0);
        }
        return this.preferences.get(user);
    }
}
