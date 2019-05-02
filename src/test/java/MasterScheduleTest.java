import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class MasterScheduleTest {

    @Test
    public void addScheduleItemTest(){

        MasterSchedule testMasterSchedule = new MasterSchedule();

        testMasterSchedule.addScheduleItem("Monday","Toaster","11:00",true,0);
        testMasterSchedule.addScheduleItem("Monday","ToasterLight","11:00",0.7,1);
        assertTrue(testMasterSchedule.Monday.daySchedule.containsKey("Toaster"));
        assertTrue(testMasterSchedule.Monday.daySchedule.containsKey("ToasterLight"));

        testMasterSchedule.addScheduleItem("Monday","shouldn't work","11:00",0.7,4);
        assertFalse(testMasterSchedule.Monday.daySchedule.containsKey("shouldn't work"));


    }
}
