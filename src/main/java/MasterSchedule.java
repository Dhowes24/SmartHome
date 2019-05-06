public class MasterSchedule {
    WeekDay Sunday = new WeekDay();
    WeekDay Monday = new WeekDay();
    WeekDay Tuesday = new WeekDay();
    WeekDay Wednesday = new WeekDay();
    WeekDay Thursday = new WeekDay();
    WeekDay Friday = new WeekDay();
    WeekDay Saturday = new WeekDay();

    public MasterSchedule(){

    }

    /**
     * Adds an appliance, light or thermostat to the current schedule
     * @param Day - the day of the week to be activated or deactivated
     * @param itemID - the id of the item being activated or deactivated
     * @param Time - the time of day it needs to activate or deactivated
     * @param type - the type of item being added
     */
    public void addScheduleItem(String Day, String itemID, String Time, Boolean StatusTo, Integer type){
        switch (Day){
            case "Sunday":
                Sunday.addObjectToSchedule(itemID,Time,StatusTo, type);
            case "Monday":
                Monday.addObjectToSchedule(itemID,Time,StatusTo, type);

            case "Tuesday":
                Tuesday.addObjectToSchedule(itemID,Time,StatusTo, type);

            case "Wednesday":
                Wednesday.addObjectToSchedule(itemID,Time,StatusTo, type);

            case "Thursday":
                Thursday.addObjectToSchedule(itemID,Time,StatusTo, type);

            case "Friday":
                Friday.addObjectToSchedule(itemID,Time,StatusTo, type);

            case "Saturday":
                Saturday.addObjectToSchedule(itemID,Time,StatusTo, type);

            case "Weekdays":
                Monday.addObjectToSchedule(itemID,Time,StatusTo, type);
                Tuesday.addObjectToSchedule(itemID,Time,StatusTo, type);
                Wednesday.addObjectToSchedule(itemID,Time,StatusTo, type);
                Thursday.addObjectToSchedule(itemID,Time,StatusTo, type);
                Friday.addObjectToSchedule(itemID,Time,StatusTo, type);

            case "Weekends":
                Sunday.addObjectToSchedule(itemID,Time,StatusTo, type);
                Saturday.addObjectToSchedule(itemID,Time,StatusTo, type);

        }
    }

    /**
     * Adds an appliance, light or thermostat to the current schedule
     * @param Day - the day of the week to be activated or deactivated
     * @param itemID - the id of the item being activated or deactivated
     * @param Time - the time of day it needs to activate or deactivated
     * @param type - the type of item being added
     */
    public void addScheduleItem(String Day, String itemID, String Time,Double value,Integer type){
        switch (Day){
            case "Sunday":
                Sunday.addObjectToSchedule(itemID,Time,value, type);
            case "Monday":
                Monday.addObjectToSchedule(itemID,Time,value, type);

            case "Tuesday":
                Tuesday.addObjectToSchedule(itemID,Time,value, type);

            case "Wednesday":
                Wednesday.addObjectToSchedule(itemID,Time,value, type);

            case "Thursday":
                Thursday.addObjectToSchedule(itemID,Time,value, type);

            case "Friday":
                Friday.addObjectToSchedule(itemID,Time,value, type);

            case "Saturday":
                Saturday.addObjectToSchedule(itemID,Time,value, type);

            case "Weekdays":
                Monday.addObjectToSchedule(itemID,Time,value, type);
                Tuesday.addObjectToSchedule(itemID,Time,value, type);
                Wednesday.addObjectToSchedule(itemID,Time,value, type);
                Thursday.addObjectToSchedule(itemID,Time,value, type);
                Friday.addObjectToSchedule(itemID,Time,value, type);

            case "Weekends":
                Sunday.addObjectToSchedule(itemID,Time,value, type);
                Saturday.addObjectToSchedule(itemID,Time,value, type);

        }
    }

}
