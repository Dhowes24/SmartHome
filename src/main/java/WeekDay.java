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

    public void addObjectToSchedule(String ID, String time, boolean statusTo, Integer type){

        if(checkIfTimeValid(time) && type<4 && type>=0) {
            ScheduleItem newScheduleItem =  new ScheduleItem(time, statusTo, type);
            daySchedule.put(ID, newScheduleItem);
        }
    }
    public void addObjectToSchedule(String ID, String time, Double value, Integer type){

        if(checkIfTimeValid(time) && type<4 && type>=1) {
            ScheduleItem newScheduleItem =  new ScheduleItem(time,value, type);
            daySchedule.put(ID, newScheduleItem);
        }
    }

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

    public boolean checkIfTimeValid(String time){
        pattern = Pattern.compile(Time24Hour_Pattern);
        matcher = pattern.matcher(time);
        return matcher.matches();
    }


}
