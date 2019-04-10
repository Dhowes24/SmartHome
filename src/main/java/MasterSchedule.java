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

    public void addScheduleItem(String Day, String itemID, String Time, Boolean StatusTo){
        switch (Day){
            case "Sunday":
                Sunday.addObjectToSchedule(itemID,Time,StatusTo, 0);
            case "Monday":
                Monday.addObjectToSchedule(itemID,Time,StatusTo, 0);

            case "Tuesday":
                Tuesday.addObjectToSchedule(itemID,Time,StatusTo, 0);

            case "Wednesday":
                Wednesday.addObjectToSchedule(itemID,Time,StatusTo, 0);

            case "Thursday":
                Thursday.addObjectToSchedule(itemID,Time,StatusTo, 0);

            case "Friday":
                Friday.addObjectToSchedule(itemID,Time,StatusTo, 0);

            case "Saturday":
                Saturday.addObjectToSchedule(itemID,Time,StatusTo, 0);

            case "Weekdays":
                Monday.addObjectToSchedule(itemID,Time,StatusTo, 0);
                Tuesday.addObjectToSchedule(itemID,Time,StatusTo, 0);
                Wednesday.addObjectToSchedule(itemID,Time,StatusTo, 0);
                Thursday.addObjectToSchedule(itemID,Time,StatusTo, 0);
                Friday.addObjectToSchedule(itemID,Time,StatusTo, 0);

            case "Weekends":
                Sunday.addObjectToSchedule(itemID,Time,StatusTo, 0);
                Saturday.addObjectToSchedule(itemID,Time,StatusTo, 0);

        }
    }

}
