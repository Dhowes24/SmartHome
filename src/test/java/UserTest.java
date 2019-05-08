import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserTest {
    House testHouse  = new House();

    @Test
    public void createUserTest(){
        User masterUser = new User("TestMaster");
        masterUser.createUser("TestUser");

        assertTrue(testHouse.userList.containsKey("TestUser"));
        assertFalse(testHouse.userList.containsKey("falseTestUser"));

        String testString = masterUser.createUser("TestUser");
        assertTrue(testString.equals("User could not be created because name is already in use"));

    }

    @Test
    public void deleteUserTest(){
        User masterUser = new User("TestMaster");
        masterUser.createUser("TestUser");

        assertTrue(testHouse.userList.containsKey("TestUser"));
        masterUser.deleteUser("TestUser");
        assertFalse(testHouse.userList.containsKey("TestUser"));

        masterUser.deleteUser("TestUser");
        String testString = masterUser.deleteUser("TestUser");
        assertTrue(testString.equals("User could not be deleted because it does not exist"));
    }

    @Test
    public void printRoomListTest(){
        User masterUser = new User("TestMaster");

        testHouse.addRoom("testRoom1");
        testHouse.addRoom("testRoom2");
        testHouse.addRoom("testRoom3");

        masterUser.printRoomList();


    }

    @Test
    public void getRoomApplianceList(){
        User masterUser = new User("TestMaster");

        testHouse.addRoom("testRoom1");
        Room testRoom = testHouse.accessRoom("testRoom1");

//        testRoom.addAppliance("Test TV", false, false);
//        testRoom.addAppliance("Coffee Maker", false, false);
//        testRoom.addAppliance("Toilet", false, false);

        masterUser.getRoomApplianceList("testRoom1");



    }

    @Test
    public void getRoomLightList(){
        User masterUser = new User("TestMaster");

        testHouse.addRoom("testRoom1");
        Room testRoom = testHouse.accessRoom("testRoom1");

        testRoom.addLight("TV light", false);
        testRoom.addLight("Coffee Maker light", false);
        testRoom.addLight("Toilet light", false);

        masterUser.getRoomLightList("testRoom1");



    }
}
