import org.junit.Assert;
import org.junit.Test;

public class TemperatureTest {

    public Temperature t = new Temperature(67, true);

    @Test
    public void TemperatureTest(){
        Assert.assertNotNull(t);
        Assert.assertEquals(67,t.getTemp(),0.00);
        Assert.assertEquals(true, t.getIsPinRequired());
    }

    //Getter Tests
    @Test
    public void getTempTest(){
        Assert.assertEquals(t.getTemp(),67,0.00);
    }
    @Test
    public void getIsPinRequiredTest(){
        Assert.assertEquals(t.getIsPinRequired(),true);
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
    public void changePinRequiredTest(){

        //Changes the value and checks to make sure that it has switched
        t.changePinRequired();
        Assert.assertEquals(t.getIsPinRequired(),false);

        //Switches the value back to its initial version to make sure of the change
        t.changePinRequired();
        Assert.assertEquals(t.getIsPinRequired(),true);
    }
}
