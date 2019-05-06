import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeekDay {

    private Pattern pattern;
    private Matcher matcher;
    private  static final String Time24Hour_Pattern =
            "([01]?[0-9]|2[0-3]):[0-5][0-9]";

    HashMap<String, ScheduleItem> daySchedule = new HashMap<>();
    //Value of object name, Key with time and Action

    public WeekDay(){

    }

    /**
     * Adds an appliance, light or thermostat the schedule
     * @param ID - the id of the object being observed
     * @param time - the time of day
     * @param statusTo - the new status of the object being activated or deactivated
     * @param type - the type of object
     */
    public void addObjectToSchedule(String ID, String time, boolean statusTo, Integer type){

        if(checkIfTimeValid(time) && type<4 && type>=0) {
            ScheduleItem newScheduleItem =  new ScheduleItem(time, statusTo, type);
            daySchedule.put(ID, newScheduleItem);
        }
    }

    /**
     * Adds an appliance, light or thermostat the schedule
     * @param ID - the id of the object being observed
     * @param time - the time of day
     * @param value - the value of the object being activated or deactivated
     * @param type - the type of objcet
     */
    public void addObjectToSchedule(String ID, String time, Double value, Integer type){

        if(checkIfTimeValid(time) && type<4 && type>=1) {
            ScheduleItem newScheduleItem =  new ScheduleItem(time,value, type);
            daySchedule.put(ID, newScheduleItem);
        }
    }

    /**
     * Removes an object from the schedule
     * @param ID - the id of the object being removed
     * @param time - the time of day its status is supposed to change
     */
    public void removeObjectFromSchedule(String ID, String time){
        if(checkIfTimeValid(time)){
            for (Map.Entry<String, ScheduleItem> e : daySchedule.entrySet()) {
                if(e.getKey() == ID && e.getValue().Time == time){
                    daySchedule.remove(e.getKey(),e.getValue());
                }
            }
            daySchedule.remove(ID,time);
        }
    }

    /**
     * Checks to see if the time for the schedule item is valid
     * @param time - the time being observed
     * @return boolean - true if the time is valid
     */
    public boolean checkIfTimeValid(String time){
        pattern = Pattern.compile(Time24Hour_Pattern);
        matcher = pattern.matcher(time);
        return matcher.matches();
    }


}
