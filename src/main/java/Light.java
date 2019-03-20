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
        if(amountIsValid(preference)){
            this.preferredAmount = preference;
        }
        else{
            this.preferredAmount = 1.0;
        }


    }

    public String getID(){return this.ID;}
    public double getAmount(){return this.amount;}
    public boolean getPreference(){return this.isPinRequired;}
    public double getPreferredAmount(){return this.preferredAmount;}
    public boolean getDimable(){return this.dimable;}


    public void turnOn(){
        this.amount=this.preferredAmount;
    }

    public void turnOff(){
        this.amount=0.0;
    }

    public void dim(double newAmount){
        this.amount = newAmount;
    }

    public void changePreference(double newPref){
        if(amountIsValid(newPref)){
            this.preferredAmount=newPref;
        }
    }

    public static boolean amountIsValid(double amount){
        if(amount>=0.0 && amount <=1.0){
            return true;
        }
        else{
            return false;
        }
    }

    public void changeIsPinRequired(){
        if(this.isPinRequired == true){
            this.isPinRequired=false;
        }
        if(this.isPinRequired == false){
            this.isPinRequired=true;
        }
    }
}
