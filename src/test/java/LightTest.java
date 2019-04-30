import org.junit.Assert;
import org.junit.Test;

public class LightTest {

    public Light l = new Light("lamp", 0.75, true);

    //Constructor Test
    @Test
    public void LightTest(){
        User testUser = new User();
        Assert.assertNotNull(l);
        Assert.assertEquals("lamp", l.getID());
        Assert.assertEquals(1.0, l.getPreference(testUser),0.00);
        Assert.assertEquals(0.75, l.getDefaultAmount(),0.00);
        Assert.assertEquals(true, l.getDimmable());
    }

    //Getter Tests
    @Test
    public void getIDTest(){ Assert.assertEquals(l.getID(),"lamp");}
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
        User testUser = new User();
        Light testLight = new Light("light", true);

        Assert.assertEquals(1.0,testLight.getPreference(testUser), 0.00);

        testLight.changeUserPref(testUser,0.5);


        Assert.assertEquals(0.5,testLight.getPreference(testUser), 0.00);

    }

    @Test
    public void setToPreferenceTest(){
        User testUser = new User();
        Light testLight = new Light("light", true);

        Assert.assertEquals(1.0,testLight.getPreference(testUser), 0.00);

        testLight.changeUserPref(testUser,0.5);
        testLight.setToPreference(testUser);

        Assert.assertEquals(0.5,testLight.getCurrIntensity(), 0.00);

    }

}
