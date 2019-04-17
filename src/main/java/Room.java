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

    public Room(String name){
        this.roomName = name;
        this.ID = UUID.randomUUID().toString();

        lightMap = new HashMap<>();
        applianceMap = new HashMap<>();
        // TODO: must get user's preference for starting temp or ask them for a temp when creating a room
        // TODO: must also ask user if thermostat should require pin
        thermostat = new Temperature(65, true);
    }

    public Room() {

        this.ID = UUID.randomUUID().toString();

        lightMap = new HashMap<>();
        applianceMap = new HashMap<>();
        // TODO: must get user's preference for starting temp or ask them for a temp when creating a room
        // TODO: must also ask user if thermostat should require pin
        thermostat = new Temperature(65, true);

    }

    public String getRoomName() {
        return roomName;
    }

    /**
     * Add appliance to list for this room by unique name
     * @param name
     */

    public void addAppliance(String name, Boolean isRequired){
        if(!containedInApplianceList(name)){
            this.applianceMap.put(name, new Appliance(name, isRequired));
        }
        // TODO: throw exception or somehow handle receiving a new name for the appliance
    }



    /**
     * remove appliance from list for this room by unique name
     * @param name
     */
    public void removeAppliance(String name) {
        if(containedInApplianceList(name)){
            applianceMap.remove(name);}
    }

    /**
     * change the boolean status of an appliance from the list by its name
     * @param name
     */
    public void changeApplianceStatus(String name){
        if(containedInApplianceList(name)){
            applianceMap.get(name).changeStatus();
        }
    }

    /**
     * add a light to the light with a unique name
     * @param name
     */
    public void addLight(String name, Boolean pinRequired, Boolean isDimable){
        if (!containedInLightList(name)){
            lightMap.put(name, new Light(name, pinRequired, isDimable));
        }
        // TODO: throw exception or somehow handle receiving a new name for the light
    }

    /**
     * remove light from list for this room by unique name
     * @param name
     */
    public void removeLight(String name){
        lightMap.remove(name);
    }

    /**
     * change the light value from 0-100 for a specific light by name
     * @param amount
     */
    public void adjustLight(double amount, String name){
        if (containedInLightList(name)){
            lightMap.get(name).dim(amount);
        }
    }

    /**
     * check temperature of thermostat nearest to room
     * @return
     */
    public double checkTemp(){
        return thermostat.getTemp();
    }

    /**
     * adjust thermostat nearest to room
     * @param amount
     */
    public void adjustTemp(int amount){
        thermostat.setTemp(amount);
    }

    /**
     * add appliance to schedule by name,
     * enter a 7-long binary list to designate days you wish the action to go off,
     * a time of those days you want the action to go off,
     * and what status change you want to happen (False = off, True = on)
     * @param applianceID
     * @param daysOfWeek
     * @param time
     * @param status
     */
    public void addApplianceToSchedule(String applianceID, List daysOfWeek, int time, boolean status){
        // TODO
    }

    /**
     * add light to schedule by name,
     * enter a 7-long binary list to designate days you wish the action to go off,
     * a time of those days you want the action to go off,
     * and what status change you want to happen (False = off, True = on)
     * @param applianceID
     * @param daysOfWeek
     * @param time
     * @param status
     */
    public void addLightToSchedule(String applianceID, List daysOfWeek, int time, boolean status){
        // TODO
    }

    /**
     * checks appliance list for instances of a given name
     * @param name
     * @return
     */
    public boolean containedInApplianceList(String name){

        if (applianceMap == null) {
            return false;
        }
        if (applianceMap.containsKey(name)) {
            return true;
        }
        return false;
    }

    /**
     * checks light list for instances of a given name
     * @param name
     * @return
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

    public void printLights() {
        for (Light light : lightMap.values()) {
            System.out.println(light.getID() + ": " + light.getCurrIntensity());
        }
    }

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
