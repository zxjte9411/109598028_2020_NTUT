public class IPin extends Device
{
    boolean inputValue;

    public void setInput(boolean value)
    {
        this.inputValue = value;
    }

    public boolean getOutput()
    {
        return this.inputValue;
    }
}
