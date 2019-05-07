import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.lang.String;
import java.lang.Boolean;

public class Room {

    HashMap<String, Light> lightMap;
    HashMap<String, Appliance> applianceMap;
    private Temperature thermostat;
    private String roomName;
    private String ID;
    MasterSchedule schedule;

    public Room(String name, MasterSchedule schedule, int globalTemp){
        this.roomName = name;
        this.ID = UUID.randomUUID().toString();
        this.schedule =schedule;

        lightMap = new HashMap<>();
        applianceMap = new HashMap<>();
        thermostat = new Temperature(globalTemp);
    }
    public Room() {
        this.ID = UUID.randomUUID().toString();
        lightMap = new HashMap<>();
        applianceMap = new HashMap<>();
        thermostat = new Temperature(65);

    }

    /**
     * @return string - the name of the thermostat object
     */
    public String getThermostatName() {return thermostat.getID();}

    /**
     * @return string - the name of the room
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * Add appliance to list for this room by unique name
     * @param name - the name of the appliance being added
     */

    public void addAppliance(String name, Boolean isRequired){
        if(!containedInApplianceList(name)){
            this.applianceMap.put(name, new Appliance(name));
        }
        // TODO: throw exception or somehow handle receiving a new name for the appliance
    }



    /**
     * remove appliance from list for this room by unique name
     * @param name - the name of the appliance being removed
     */
    public void removeAppliance(String name) {
        if(containedInApplianceList(name)){
            applianceMap.remove(name);
        }
    }

    /**
     * change the boolean status of an appliance from the list by its name
     * @param name - the name of the appliance that needs to be changed
     */
    public void changeApplianceStatus(String name){
        if(containedInApplianceList(name)){
            applianceMap.get(name).changeStatus();
        }
    }

    /**
     * add a light to the light with a unique name
     * @param name - the name of the light being added
     */
    public void addLight(String name, Boolean isDimable){
        if (!containedInLightList(name)){
            lightMap.put(name, new Light(name, isDimable));
        }
    }

    /**
     * remove light from list for this room by unique name
     * @param name - the name of the light being removed
     */
    public void removeLight(String name){
        lightMap.remove(name);
    }

    /**
     * change the light value from 0-100 for a specific light by name
     * @param amount - amount that the light should change to
     */
    public void adjustLight(double amount, String name){
        if (containedInLightList(name)){
            lightMap.get(name).dim(amount);
        }
    }

    /**
     * check temperature of thermostat nearest to room
     * @return double - the current temperature of a thermostat
     */
    public double checkTemp(){
        return thermostat.getTemp();
    }

    /**
     * adjust thermostat nearest to room
     * @param amount - the new temperature of the thermostat
     */
    public void adjustTemp(int amount){
        thermostat.setTemp(amount);
    }

    /**
     * add appliance to schedule by name,
     * enter a day, weekdays, or weekends,
     * a time of those days you want the action to go off in 24 hour HH:MM format,
     * and what status change you want to happen (False = off, True = on)
     * @param applianceID - the name of the appliance
     * @param day - the day of the week
     * @param time - the time of that day
     * @param status - the status it should change to
     */
    public void addApplianceToSchedule(String applianceID, String day, String time, boolean status){
        schedule.addScheduleItem(day,applianceID,time,status,0);
    }

    /**
     * add light to schedule by name,
     * enter a day, weekdays, or weekends,
     * a time of those days you want the action to go off in 24 hour HH:MM format,
     * and what status change you want to happen (0 = off, 1 = on, 2 = dimmed)
     * if dimmed, set to the dim double
     * @param lightID - the name of the light
     * @param day - the day of the week
     * @param time - the time of that day
     * @param dim - the new intensity it should change to
     */
    public void addLightToSchedule(String lightID, String day, String time, double dim){
        schedule.addScheduleItem(day,lightID,time,dim,1);
    }
    /**
     * add Thermostat to schedule by name,
     * enter a day, weekdays, or weekends,
     * a time of those days you want the action to go off in 24 hour HH:MM format,
     * and what status change you want to happen (False = off, True = on)
     * Set temp with double
     * @param day - the day of the week
     * @param time - the time fo that day
     * @param temp - the new temperature it should change to
     */
    public void addThermoToSchedule(String thermoID,String day, String time, double temp){
        schedule.addScheduleItem(day,thermoID,time,temp,2);

    }

    /**
     * checks appliance list for instances of a given name
     * @param name - the name of the appliance
     * @return boolean - true if the appliance exists
     */
    public boolean containedInApplianceList(String name){

        if (applianceMap == null) {
            return false;
        } else if (applianceMap.containsKey(name)) {
            return true;
        }
        return false;
    }

    /**
     * checks light list for instances of a given name
     * @param name - the name of the light
     * @return - true if the light exists
     */
    public boolean containedInLightList(String name){

        if (lightMap == null) {
            return false;
        }
        if (lightMap.containsKey(name)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Prints the lights of a current room
     */
    public void printLights() {
        for (Light light : lightMap.values()) {
            System.out.println(light.getID() + ": " + light.getCurrIntensity());
        }
    }

    /**
     * Prints the appliances of a current room
     */
    public void printAppliances() {
        for (Appliance app : applianceMap.values()) {
            if (app.getStatus()) {
                System.out.println(app.getID() + ": ON");
            } else {
                System.out.println(app.getID() + ": OFF");
            }
        }
    }
}
