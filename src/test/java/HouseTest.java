import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class HouseTest {

    public House testHouse = new House();

    @Test
    public void addRoomTest(){
        testHouse.addRoom("Master Bath");
        assertTrue(testHouse.roomList.containsKey("Master Bath"));
    }

    @Test
    public void removeRoomTest(){
        testHouse.removeRoom("Master Bath");
        assertFalse(testHouse.roomList.containsKey("Master Bath"));
    }

    @Test
    public void changeHouseTempTest(){
        testHouse.changeHouseTemp(45);
        assertEquals(45, testHouse.globaltemp);

    }

    @Test
    public void turnApplicancesOffTest(){




    }

    @Test
    public void turnOffLightsTest(){

    }
}
