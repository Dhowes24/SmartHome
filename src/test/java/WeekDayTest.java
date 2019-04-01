import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class WeekDayTest {

    @Test
    public void addObjectToScheduleTest(){
        WeekDay testWeekDay = new WeekDay();

        testWeekDay.addObjectToSchedule("ToasterID", "12:45", true);
        assertTrue(testWeekDay.daySchedule.containsKey("ToasterID"));
        Assert.assertEquals(testWeekDay.daySchedule.get("ToasterID").Time, "12:45");
        Assert.assertEquals(testWeekDay.daySchedule.get("ToasterID").StatusTo, true);

        testWeekDay.addObjectToSchedule("WontWorKID", "132:45", true);
        assertFalse(testWeekDay.daySchedule.containsKey("WontWorkID"));

    }

    @Test
    public void removeObjectFromScheduleTest(){
        WeekDay testWeekDay = new WeekDay();
        testWeekDay.addObjectToSchedule("ToasterID", "12:45", true);
        Assert.assertEquals(testWeekDay.daySchedule.get("ToasterID").Time, "12:45");
        testWeekDay.removeObjectFromSchedule("ToasterID", "12:45");
        //TODO check how to see if that key value is now gone

    }

    @Test
    public void checkIfTimeValidTest(){
        WeekDay testWeekDay = new WeekDay();

        assertTrue(testWeekDay.checkIfTimeValid("00:00"));
        assertTrue(testWeekDay.checkIfTimeValid("23:59"));
        assertTrue(testWeekDay.checkIfTimeValid("1:00"));
        assertTrue(testWeekDay.checkIfTimeValid("01:00"));


        assertFalse(testWeekDay.checkIfTimeValid("00.00"));
        assertFalse(testWeekDay.checkIfTimeValid("24:00"));
        assertFalse(testWeekDay.checkIfTimeValid("0:0"));
        assertFalse(testWeekDay.checkIfTimeValid("200:00"));
        assertFalse(testWeekDay.checkIfTimeValid("10:0"));

    }
}
