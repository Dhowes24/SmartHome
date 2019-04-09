import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ApplianceTest {

    @Test
    void constructorTest() {
        Appliance washer = new Appliance("washer", false);
        assertEquals("washer", washer.getID());
        assertFalse(washer.getUserPref());
        assertFalse(washer.getStatus());


    }

    @Test
    void changeStatusTest() {
        Appliance washer = new Appliance("washer", false);
        washer.changeStatus();
        assertTrue(washer.getStatus());


    }

    @Test
    void getStatusTest() {
        Appliance washer = new Appliance("washer", false);
        assertFalse(washer.getStatus());



    }


    @Test
    void getUserPrefTest() {
        Appliance washer = new Appliance("washer", false);
        assertFalse(washer.getUserPref());



    }

    @Test
    void getIDTest() {
        Appliance washer = new Appliance("washer", false);
        assertEquals("washer", washer.getID());

    }

    @Test
    void changeUserPref() {
        Appliance washer = new Appliance("washer", false);
        washer.changeUserPref();
        assertTrue(washer.getUserPref());

    }
}
