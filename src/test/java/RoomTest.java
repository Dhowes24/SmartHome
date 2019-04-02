import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class RoomTest {

   @Test
    public void addApplianceTest(){
        Room room = new Room("testRoom");
        room.addAppliance("TV", false);
        assertTrue(room.applianceMap.containsKey("TV"));
        assertFalse(room.applianceMap.containsKey("NotMyTV"));

    }


    @Test
    public void removeApplianceTest(){
        Room room = new Room("testRoom");
        room.addAppliance("TV", false);
        assertTrue(room.applianceMap.containsKey("TV"));
        room.removeAppliance("TV");
        assertFalse(room.applianceMap.containsKey("TV"));

    }

    @Test
    public void changeApplianceStatusTest(){
        Room room = new Room("testRoom");
        room.addAppliance("TV", false);
        room.changeApplianceStatus("TV");
        //TODO once you can get appliance status

    }

    @Test
    public void addLightTest(){
       // TODO
//        Room room = new Room("testRoom");
//        room.addLight("a", false, true);
//        assertEquals("a", room.lightMap.get("a").getID());
    }

    @Test
    public void adjustLightTest(){
        Room room = new Room("testRoom");

    }

    @Test
    public void adjustTempTest(){
        Room room = new Room("testRoom");

    }

    @Test
    public void addToScheduleTest(){
        Room room = new Room("testRoom");

    }


}
