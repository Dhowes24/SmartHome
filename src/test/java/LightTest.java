import org.junit.Assert;
import org.junit.Test;

public class LightTest {

    public Light l = new Light("lamp", true, 0.75, true);

    //Constructor Test
    @Test
    public void LightTest(){
        Assert.assertNotNull(l);
        Assert.assertEquals("lamp", l.getID());
        Assert.assertEquals(true, l.getPreference());
        Assert.assertEquals(0.75, l.getDefaultAmount(),0.00);
        Assert.assertEquals(true, l.getDimmable());
    }

    //Getter Tests
    @Test
    public void getIDTest(){ Assert.assertEquals(l.getID(),"lamp");}
    @Test
    public void getPinTest(){ Assert.assertEquals(l.getPreference(),true);}
    @Test
    public void getDimmableTest(){ Assert.assertEquals(l.getDimmable(),true);}
    @Test
    public void getAmount(){ Assert.assertEquals(l.getCurrIntensity(),0, 0.01);}
    @Test
    public void getDefaultAmountTest(){Assert.assertEquals(l.getDefaultAmount(),0.75,0.01);}


    //Setter Tests
    @Test
    public void setDefaultAmountTest(){
        //Invalid Amounts
        l.setDefaultAmount(10.0);
        Assert.assertEquals(l.getDefaultAmount(),.75, 0.01);
        l.setDefaultAmount(-1);
        Assert.assertEquals(l.getDefaultAmount(),.75, 0.01);

        //Valid Amounts
        l.setDefaultAmount(.00);
        Assert.assertEquals(l.getDefaultAmount(),.00, 0.01);
        l.setDefaultAmount(1.00);
        Assert.assertEquals(l.getDefaultAmount(),1.0, 0.01);
        l.setDefaultAmount(.55);
        Assert.assertEquals(l.getDefaultAmount(),.55, 0.01);
    }

    //Light Control Tests
    @Test
    public void turnLightsOnTest(){
        l.turnOn();
        Assert.assertEquals(l.getCurrIntensity(),.75,0.01);
    }
    @Test
    public void turnLightsOffTest(){
        l.turnOff();
        Assert.assertEquals(l.getCurrIntensity(),0.0,0.00);
    }
    @Test
    public void dimTest(){
        l.dim(.96);
        Assert.assertEquals(l.getCurrIntensity(),.96,.01);
    }


    //Permission Control Testing
    @Test
    public void changePreferenceTest(){
        //Change
        l.changeIsPinRequired();
        Assert.assertEquals(l.getPreference(),false);

        //Change back
        l.changeIsPinRequired();
        Assert.assertEquals(l.getPreference(),true);
    }

}
