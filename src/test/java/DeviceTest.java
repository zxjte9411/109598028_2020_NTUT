import org.junit.*;
import static org.junit.Assert.*;

public class DeviceTest
{
    @Before
    public void setUp()
    {

    }

    @After
    public void tearDown()
    {

    }

    @Test
    public void testDevicePolymorphism()
    {
        Device device = new IPin();
        assertEquals(IPin.class.getName(), device.getClass().getName());

        device = new OPin();
        assertEquals(OPin.class.getName(), device.getClass().getName());

        device = new GateNot();
        assertEquals(GateNot.class.getName(), device.getClass().getName());

        device = new GateAnd();
        assertEquals(GateAnd.class.getName(), device.getClass().getName());

        device = new GateOR();
        assertEquals(GateOR.class.getName(), device.getClass().getName());
    }

    @Test
    public void testIPinAndOPin()
    {
        IPin iPin = new IPin();
        iPin.setInput(false);

        OPin oPin = new OPin();
        oPin.addInputPin(iPin);

        assertFalse(oPin.getOutput());
        assertFalse(iPin.getOutput());

        iPin = new IPin();
        iPin.setInput(true);

        oPin = new OPin();
        oPin.addInputPin(iPin);

        assertTrue(oPin.getOutput());
        assertTrue(iPin.getOutput());
    }

    @Test
    public void testGateNot()
    {
        IPin iPin = new IPin();
        iPin.setInput(false);

        GateNot gateNot = new GateNot();
        gateNot.addInputPin(iPin);

        assertTrue(gateNot.getOutput());

        iPin = new IPin();
        iPin.setInput(true);

        gateNot = new GateNot();
        gateNot.addInputPin(iPin);

        assertFalse(gateNot.getOutput());
    }

    @Test
    public void testGateAnd()
    {
        // 0
        IPin iPin1 = new IPin();
        iPin1.setInput(false);
        IPin iPin2 = new IPin();
        iPin2.setInput(false);
        GateAnd gateAnd = new GateAnd();
        gateAnd.addInputPin(iPin1);
        gateAnd.addInputPin(iPin2);
        assertFalse(gateAnd.getOutput());

        // 0
        iPin1 = new IPin();
        iPin1.setInput(false);
        iPin2 = new IPin();
        iPin2.setInput(true);
        gateAnd = new GateAnd();
        gateAnd.addInputPin(iPin1);
        gateAnd.addInputPin(iPin2);
        assertFalse(gateAnd.getOutput());

        // 0
        iPin1 = new IPin();
        iPin1.setInput(true);
        iPin2 = new IPin();
        iPin2.setInput(false);
        gateAnd = new GateAnd();
        gateAnd.addInputPin(iPin1);
        gateAnd.addInputPin(iPin2);
        assertFalse(gateAnd.getOutput());

        // 1
        iPin1 = new IPin();
        iPin1.setInput(true);
        iPin2 = new IPin();
        iPin2.setInput(true);
        gateAnd = new GateAnd();
        gateAnd.addInputPin(iPin1);
        gateAnd.addInputPin(iPin2);
        assertTrue(gateAnd.getOutput());
    }

    @Test
    public void testGateOR()
    {
        // 0
        IPin iPin1 = new IPin();
        iPin1.setInput(false);
        IPin iPin2 = new IPin();
        iPin2.setInput(false);
        GateOR gateOR = new GateOR();
        gateOR.addInputPin(iPin1);
        gateOR.addInputPin(iPin2);
        assertFalse(gateOR.getOutput());

        // 1
        iPin1 = new IPin();
        iPin1.setInput(false);
        iPin2 = new IPin();
        iPin2.setInput(true);
        gateOR = new GateOR();
        gateOR.addInputPin(iPin1);
        gateOR.addInputPin(iPin2);
        assertTrue(gateOR.getOutput());

        // 1
        iPin1 = new IPin();
        iPin1.setInput(true);
        iPin2 = new IPin();
        iPin2.setInput(false);
        gateOR = new GateOR();
        gateOR.addInputPin(iPin1);
        gateOR.addInputPin(iPin2);
        assertTrue(gateOR.getOutput());

        // 1
        iPin1 = new IPin();
        iPin1.setInput(true);
        iPin2 = new IPin();
        iPin2.setInput(true);
        gateOR = new GateOR();
        gateOR.addInputPin(iPin1);
        gateOR.addInputPin(iPin2);
        assertTrue(gateOR.getOutput());
    }
}
