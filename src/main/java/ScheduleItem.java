public class ScheduleItem {
    String Time;
    Boolean StatusTo = null;
    Double value = null;
    Integer type;

    public ScheduleItem(String Time, Boolean StatusTo, Integer type){
        this.Time = Time;
        this.StatusTo = StatusTo;
        this.type = type;
    }

    public ScheduleItem(String Time, Double value, Integer type){
        this.Time = Time;
        this.StatusTo = StatusTo;
        this.value = value;
        this.type = type;
    }
}
