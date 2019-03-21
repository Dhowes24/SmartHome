public class Temperature {

    private double temperature;
    private boolean isPinRequired;


    public Temperature(double temp, boolean preferrences){
            this.temperature = temp;
            this.isPinRequired = preferrences;

    }

    public double getTemp(){return this.temperature;}
    public boolean getIsPringRequired(){return this.isPinRequired;}

    public void setTemp(double newTemp){
        if(isAmountValid(newTemp)){
            this.temperature=newTemp;
        }
    }

    public boolean isAmountValid(double amount){
        if(amount>45 && amount < 95){
            return true;
        }
        else{
            return false;
        }
    }
}
