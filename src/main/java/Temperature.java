public class Temperature {

    private double temperature;
    private boolean isPinRequired;


    /**
     * @param temp double - the temperature the room will be at
     * @param preferences boolean - whether or not it will require a key input
     */
    public Temperature(double temp, boolean preferences){
            if(isAmountValid(temp)){
                this.temperature = temp;
                this.isPinRequired = preferences;
            }
            else{
                this.temperature = 60;
                this.isPinRequired = preferences;
            }

    }

    /**
     * @return double - the current temperature
     */
    public double getTemp(){return this.temperature;}

    /**
     * @return boolean - whether or not there is a required pin on changing the temp
     */
    public boolean getIsPinRequired(){return this.isPinRequired;}

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
     * Changes the current status of the pin requirement to the opposite
     */
    public void changePinRequired(){
        this.isPinRequired = !this.isPinRequired;
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
}
