import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class RoomTest {

   @Test
    public void addApplianceTest(){
       MasterSchedule testSchedule = new MasterSchedule();
        Room room = new Room("testRoom",testSchedule, 65);
        room.addAppliance("TV", false);
        assertTrue(room.applianceMap.containsKey("TV"));
        assertFalse(room.applianceMap.containsKey("NotMyTV"));

    }


    @Test
    public void removeApplianceTest(){
        MasterSchedule testSchedule = new MasterSchedule();
        Room room = new Room("testRoom",testSchedule, 65);
        room.addAppliance("TV", false);
        assertTrue(room.applianceMap.containsKey("TV"));
        room.removeAppliance("TV");
        assertFalse(room.applianceMap.containsKey("TV"));

    }

    @Test
    public void changeApplianceStatusTest(){
        MasterSchedule testSchedule = new MasterSchedule();
        Room room = new Room("testRoom",testSchedule, 65);
        room.addAppliance("TV", false);
        room.changeApplianceStatus("TV");
        //TODO once you can get appliance status

    }

    @Test
    public void addLightTest(){

        MasterSchedule testSchedule = new MasterSchedule();
        Room room = new Room("testRoom",testSchedule, 65);
       room.addLight("a", false);
       assertEquals("a", room.lightMap.get("a").getID());
       room.printLights();
    }

    @Test
    public void adjustLightTest(){
        MasterSchedule testSchedule = new MasterSchedule();
        Room room = new Room("testRoom",testSchedule, 65);

    }

    @Test
    public void adjustTempTest(){
        MasterSchedule testSchedule = new MasterSchedule();
        Room room = new Room("testRoom",testSchedule, 65);

    }

    @Test
    public void addToScheduleTest(){
        MasterSchedule testSchedule = new MasterSchedule();
        Room room = new Room("testRoom",testSchedule, 65);

    }


}
