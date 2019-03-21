import org.junit.Assert;
import org.junit.Test;

public class LightTest {

    public Light l = new Light("lamp", true,0.75,true);

    //Constructor Test
    @Test
    public void LightTest(){
        Assert.assertNotNull(l);
    }

    //Getter Tests
    @Test
    public void getIDTest(){ Assert.assertEquals(l.getID(),"lamp");}
    @Test
    public void getPinTest(){ Assert.assertEquals(l.getPreference(),true);}
    @Test
    public void getDimableTest(){ Assert.assertEquals(l.getDimable(),true);}
    @Test
    public void getAmount(){ Assert.assertEquals(l.getAmount(),0, 0.01);}
    @Test
    public void getPreferreedAmountTest(){Assert.assertEquals(l.getPreferredAmount(),0.75,0.01);}


    //Setter Tests
    //TODO
    @Test
    public void setPreferredAmountTest(){

        //Invalid Amounts
        l.setPreferredAmount(10.0);
        Assert.assertEquals(l.getPreferredAmount(),.75, 0.01);
        l.setPreferredAmount(-1);
        Assert.assertEquals(l.getPreferredAmount(),.75, 0.01);

        //Valid Amounts
        l.setPreferredAmount(.00);
        Assert.assertEquals(l.getPreferredAmount(),.00, 0.01);
        l.setPreferredAmount(1.00);
        Assert.assertEquals(l.getPreferredAmount(),1.0, 0.01);
        l.setPreferredAmount(.55);
        Assert.assertEquals(l.getPreferredAmount(),.55, 0.01);
    }


    //Light Control Tests
    @Test
    public void turnLightsOnTest(){
        l.turnOn();
        Assert.assertEquals(l.getAmount(),.75,0.01);
    }
    @Test
    public void turnLightsOffTest(){
        l.turnOff();
        Assert.assertEquals(l.getAmount(),0.0,0.00);
    }
    @Test
    public void dimTest(){
        l.dim(.96);
        Assert.assertEquals(l.getAmount(),.96,.01);
    }


    //Permison's Control Testing
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
