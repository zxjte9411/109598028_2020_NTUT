import java.util.Vector;

public class Device
{
    public Vector<Device> iPins = new Vector<>();
    public boolean IsConnectToOtherGates = false;
    public void addInputPin(Device iPin)
    {
        iPin.IsConnectToOtherGates = true;
        iPins.add(iPin);
    }

    public void setInput(boolean value)
    {
        throw new RuntimeException("This Device is not allowed to call setInput.");
    }

    public boolean getOutput()
    {
        throw new RuntimeException("This Device is not allowed to call getOutput.");
    }
}
