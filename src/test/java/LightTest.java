import org.junit.Assert;
import org.junit.Test;

public class LightTest {

    public Light l = new Light("lamp", true,0.75,true);

    @Test
    public void LightTest(){

        Assert.assertNotNull(l);
    }

    @Test
    public void getIDTest(){

        Assert.assertEquals(l.getID(),"lamp");
    }

    @Test
    public void getPinTest(){
        Assert.assertEquals(l.getPreference(),true);
    }

    @Test
    public void getDimableTest(){
        Assert.assertEquals(l.getDimable(),true);
    }


    @Test
    public void changePreferenceTest(){
        //Change
        l.changeIsPinRequired();
        Assert.assertEquals(l.getPreference(),false);

        //Change back
        l.changeIsPinRequired();
        Assert.assertEquals(l.getPreference(),true);
    }

    //TODO
//    @Test
//    public void isAmountValidTest(){
//
//    }

    //TODO
//    @Test
//    public void dimTest(){
//        l.dim(.96);
//        Assert.assertEquals(l.getAmount(),.96);
//    }


//    //TODO
//    @Test
//    public void turnLightsOffTest(){
//        l.turnOff();
//        Assert.assertEquals(l.getAmount(),0.0);
//
//    }

    //TODO
//    @Test
//    public void turnLightsOnTest(){
//        l.turnOn();
//        Assert.assertEquals(l.getAmount(),.75);
//
//    }

    //TODO
//    @Test
//    public void getAmount(){
//        Assert.assertEquals(l.getAmount(),.75);
//    }

    //TODO
//    @Test
//    public void setPreferenceTest(){
//        //INVALID AMOUNT
//        l.setPrefferedAmount(10);
//        Assert.assertEquals(l.getAmount(),.75);
//    }

    //TODO
//    @Test
//    public void getPreferreedAmountTest(){
//        Assert.assertEquals(l.getPreferredAmount(),0.75);
//    }



}
