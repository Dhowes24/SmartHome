import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;


public class Light {

    private String ID;
    private double amount;
    private boolean isPinRequired;
    private double preferredAmount;
    private boolean dimable;

    public Light(String name, boolean pin, double preference, boolean dimable){
        this.ID = name;
        this.amount = 0.0;
        this.isPinRequired = pin;
        this.dimable=dimable;
        if(amountIsValid(preference && this.dimable==true)){
            this.preferredAmount = preference;
        }
        else{
            this.preferredAmount = 1.0;
        }
    }

    public Light(String name, boolean pin, boolean dimable){
        this.ID = name;
        this.amount = 0.0;
        this.isPinRequired = pin;
        this.dimable = dimable;
    }

    /**
     * @return String - the ID of the light
     */
    public String getID(){return this.ID;}

    /**
     * @return double - the current amount the light is operating on
     */
    public double getAmount(){return this.amount;}

    /**
     * @return boolean - the current user preferences for the light
     */
    public boolean getPreference(){return this.isPinRequired;}

    /**
     * @return double - the current preferred amount of the light
     */
    public double getPreferredAmount(){return this.preferredAmount;}

    /**
     * @return boolean - whether the light is dimable or not
     */
    public boolean getDimable(){return this.dimable;}

    /**
     * Sets a new value to the preferred amount
     * @param newAmount - the new amount of the preferred amount
     */
    public void setPreferredAmount(double newAmount){
        if(amountIsValid(newAmount)){
            this.preferredAmount = newAmount;
        }
    }

    /**
     * Turns the lights amount to the current preferred amount
     */
    public void turnOn(){
        this.amount=this.preferredAmount;
    }

    /**
     * Turns the lights amount to zero turning it off
     */
    public void turnOff(){
        this.amount=0.0;
    }

    /**
     * Dims the light up or down to the new amount
     * @param newAmount - the new amount that the light should be at
     */
    public void dim(double newAmount){
        if(amountIsValid(newAmount)){
            this.amount = newAmount;
        }
    }

    /**
     * Checks to see if an amount passed in by the user is valid (Between 0 and 1 inclusive)
     * @param amount double - the amount being checked
     * @return boolean - whether it is a valid amount or not
     */
    public static boolean amountIsValid(double amount){
        if(amount>=0.0 && amount <=1.0){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Changes the current pin preferences for the light
     */
    public void changeIsPinRequired() {
        if (this.isPinRequired == true) {
            this.isPinRequired = false;
        } else {
            this.isPinRequired = true;
        }
    }
}
