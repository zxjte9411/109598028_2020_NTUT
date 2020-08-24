import java.util.Vector;

public class Device
{
    public Vector<Device> iPins = new Vector<>();
    public void addInputPin(Device iPin)
    {
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
