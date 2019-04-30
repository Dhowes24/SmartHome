import org.junit.Assert;
import org.junit.Test;

public class TemperatureTest {

    public Temperature t = new Temperature(67);

    @Test
    public void TemperatureTest(){
        Assert.assertNotNull(t);
        Assert.assertEquals(67,t.getTemp(),0.00);
    }

    //Getter Tests
    @Test
    public void getTempTest(){
        Assert.assertEquals(t.getTemp(),67,0.00);
    }

    //Setter Tests
    @Test
    public void setTempTest(){
        //Invalid Inputs
        t.setTemp(30);
        Assert.assertEquals(t.getTemp(),67,0.00);
        t.setTemp(100);
        Assert.assertEquals(t.getTemp(),67,0.00);

        //Valid Inputs
        t.setTemp(55);
        Assert.assertEquals(t.getTemp(),55,0.00);
        t.setTemp(76);
        Assert.assertEquals(t.getTemp(),76,0.00);
    }

    @Test
    public void setTempPreferenceTest(){
        User testUser = new User();
        Temperature testTemperature = new Temperature(60);

        Assert.assertEquals(60.0,testTemperature.getPreference(testUser),0.00);

        testTemperature.setTempPreference(testUser, 75.0);

        Assert.assertEquals(75.0,testTemperature.getPreference(testUser),0.00);

    }

    @Test
    public void setToPreferenceTest(){
        User testUser = new User();
        Temperature testTemperature = new Temperature(60);

        testTemperature.setTempPreference(testUser, 75.0);

        testTemperature.setToPreference(testUser);
        Assert.assertEquals(75.0,testTemperature.getTemp(),0.00);
    }

}
