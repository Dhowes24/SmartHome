import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class HouseTest {

    @Test
    public void addRoomTest(){
        House testHouse =  new House();
        testHouse.addRoom("Master Bath");
        assertTrue(testHouse.roomList.containsKey("Master Bath"));
        assertFalse(testHouse.roomList.containsKey("Peasant Bath"));
    }

    @Test
    public void removeRoomTest(){
        House testHouse =  new House();
        testHouse.addRoom("Master Bath");
        assertTrue(testHouse.roomList.containsKey("Master Bath"));
        testHouse.removeRoom("Master Bath");
        assertFalse(testHouse.roomList.containsKey("Master Bath"));
    }

    @Test
    public void cliTest(){
        House h = new House();
        h.addRoom("Kitchen");
        h.addRoom("Master Bedroom");
        h.runCLI();
    }

    @Test
    public void printScheduleListTest(){

    }

    @Test
    public void changeHouseTempTest(){

    }

    @Test
    public void turnApplicancesOffTest(){

    }

    @Test
    public void turnOffLightsTest(){

    }
}
