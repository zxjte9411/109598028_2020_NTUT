public class GateAnd extends  Device
{
    @Override
    public boolean getOutput() {
        boolean result = iPins.get(0).getOutput();
        for (Device iPin : iPins) {
            result = result & iPin.getOutput();
        }
        return result;
    }
}
