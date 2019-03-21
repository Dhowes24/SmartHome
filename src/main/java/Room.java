import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Room {

    HashMap<String, Light> lightMap;
    HashMap<String, Appliance> applianceMap;

    public Room(String name){
        String roomName = name;
        String ID = UUID.randomUUID().toString();

        //Arraylist<'ApplianceObject'> applianceList = new ArrayList<'ApplianceObject'>();
        //Arraylist<'lightObject'> applianceList = new ArrayList<'lightObject'>();
        //Thermostat nearestThermostat = nearestThermostat;
    }

    /**
     * Add appliance to list for this room by unique name
     * @param name
     */
    public void addAppliance(String name){
        if(checkApplianceList(name)){
            //add appliance
        }

    }

    /**
     * remove appliance from list for this room by unique name
     * @param name
     */
    public void removeAppliance(String name){

    }

    /**
     * change the boolean status of an appliance from the list by its name
     * @param name
     */
    public void changeApplianceStatus(String name){

    }

    /**
     * add a light to the light with a unique name
     * @param name
     */
    public void addLight(String name, Boolean pinRequired, Boolean isDimable){
        if (containedInLightList(name)){
            //add light
            lightMap.put(name, Light(name, pinRequired, isDimable));
        }
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
    public int checkTemp(){
        return 0;
    }

    /**
     * adjust thermostat nearest to room
     * @param amount
     */
    public void adjustTemp(int amount){

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

    }

    /**
     * checks appliance list for instances of a given name
     * @param name
     * @return
     */
    public boolean checkApplianceList(String name){

        return true;
    }

    /**
     * checks light list for instances of a given name
     * @param name
     * @return
     */
    public boolean containedInLightList(String name){

        if (lightMap.containsKey(name)) {
            return true;
        }
        return false;
    }

}
