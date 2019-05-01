import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ApplianceTest {

    @Test
    void constructorTest() {
        User testUser = new User();
        Appliance washer = new Appliance("washer");
        assertEquals("washer", washer.getID());
        assertFalse(washer.getUserPref(testUser));
        assertFalse(washer.getStatus());


    }

    @Test
    void changeStatusTest() {
        Appliance washer = new Appliance("washer");
        washer.changeStatus();
        assertTrue(washer.getStatus());


    }

    @Test
    void getStatusTest() {
        Appliance washer = new Appliance("washer");
        assertFalse(washer.getStatus());



    }


    @Test
    void getUserPrefTest() {
        User testUser = new User();
        Appliance washer = new Appliance("washer");
        assertFalse(washer.getUserPref(testUser));

    }

    @Test
    void getIDTest() {
        Appliance washer = new Appliance("washer");
        assertEquals("washer", washer.getID());

    }

    @Test
    void changeUserPref() {
        User testUser = new User();
        Appliance washer = new Appliance("washer");

        washer.changeUserPref(testUser, true);
        assertTrue(washer.getUserPref(testUser));

        washer.changeUserPref(testUser, false);
        assertFalse(washer.getUserPref(testUser));

    }

    @Test
    void setToPreference(){
        User testUser = new User();
        Appliance washer = new Appliance("washer");

        washer.setToPreference(testUser);
        assertFalse(washer.getStatus());

        washer.changeUserPref(testUser, true);
        washer.setToPreference(testUser);
        assertTrue(washer.getStatus());

    }
}
