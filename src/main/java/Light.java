public class Light {

    private String ID;
    private Double amount;
    private Boolean isPinRequired;

    public Light(String name, Boolean pin){
        this.ID = name;
        this.amount = 0.0;
        this.isPinRequired = pin;
    }

    public void turnOn(){
        this.amount=1.0;
    }

    public void turnOff(){
        this.amount=0.0;
    }

    public void dim(Double newAmount){
        this.amount = newAmount;
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
