import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class RoomTest {

    @Test
    public void addApplianceTest(){
        Room room = new Room("testRoom");


    }

    @Test
    public void removeApplianceTest(){
        Room room = new Room("testRoom");

    }

    @Test
    public void changeApplianceStatusTest(){
        Room room = new Room("testRoom");

    }

    @Test
    public void addLightTest(){
        Room room = new Room("testRoom");
        room.addLight("a", false, true);
        assertEquals("a", room.lightMap.get("a").getID());
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
