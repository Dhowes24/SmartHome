public class Light {

    private String ID;
    private double currIntensity;
    private boolean isPinRequired;
    private double defaultAmount;
    private boolean isDimmable;

    public Light(String name, boolean pin, double defaultAmountt, boolean isDimmable){
        this.ID = name;
        this.currIntensity = 0.0;
        this.isPinRequired = pin;
        this.isDimmable=isDimmable;
        if(amountIsValid(defaultAmount) && this.isDimmable){
            this.defaultAmount = defaultAmountt;
        }
        else{
            this.defaultAmount = 1.0;
        }
    }

    public Light(String name, boolean pin, boolean isDimmable){
        this.ID = name;
        this.currIntensity = 0.0;
        this.isPinRequired = pin;
        this.isDimmable = isDimmable;
        this.defaultAmount = 1.0;
    }

    /**
     * @return String - the ID of the light
     */
    public String getID(){return this.ID;}

    /**
     * @return double - the current currIntensity the light is operating on
     */
    public double getCurrIntensity(){return this.currIntensity;}

    /**
     * @return boolean - the current user preferences for the light
     */
    public boolean getPreference(){return this.isPinRequired;}

    /**
     * @return double - the current preferred currIntensity of the light
     */
    public double getDefaultAmount(){return this.defaultAmount;}

    /**
     * @return boolean - whether the light is dimable or not
     */
    public boolean getDimmable(){return this.isDimmable;}

    /**
     * Sets a new value to the preferred currIntensity
     * @param newAmount - the new currIntensity of the preferred currIntensity
     */
    public void setDefaultAmount(double newAmount){
        if(amountIsValid(newAmount)){
            this.defaultAmount = newAmount;
        }
    }

    /**
     * Turns the lights currIntensity to the current preferred currIntensity
     */
    public void turnOn(){
        this.currIntensity =this.defaultAmount;
    }

    /**
     * Turns the lights currIntensity to zero turning it off
     */
    public void turnOff(){
        this.currIntensity=0.0;
    }

    /**
     * Dims the light up or down to the new currIntensity
     * @param newAmount - the new currIntensity that the light should be at
     */
    public void dim(double newAmount){
        if(amountIsValid(newAmount)){
            this.currIntensity = newAmount;
        }
    }

    /**
     * Checks to see if an currIntensity passed in by the user is valid (Between 0 and 1 inclusive)
     * @param amount double - the currIntensity being checked
     * @return boolean - whether it is a valid currIntensity or not
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
