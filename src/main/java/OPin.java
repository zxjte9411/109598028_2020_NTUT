import java.util.Vector;

public class OPin extends Device
{
    @Override
    public boolean getOutput()
    {
        return iPins.get(0).getOutput();
    }
}
