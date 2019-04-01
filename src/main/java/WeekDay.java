import java.util.HashMap;
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

    public void addObjectToSchedule(String ID, String time, boolean statusTo){

        if(checkIfTimeValid(time)){
        ScheduleItem newScheduleItem =  new ScheduleItem(time, statusTo);
        daySchedule.put(ID, newScheduleItem);}
    }

    public void removeObjectFromSchedule(String ID, String time){
        if(checkIfTimeValid(time)){
            daySchedule.remove(ID,time);
        }
    }

    public boolean checkIfTimeValid(String time){
        pattern = Pattern.compile(Time24Hour_Pattern);
        matcher = pattern.matcher(time);
        return matcher.matches();
    }


}
