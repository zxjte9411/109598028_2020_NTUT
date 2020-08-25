import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;

public class LogicSimulator
{
    private Vector<Device> circuits = new Vector<>();
    private Vector<Device> iPins = new Vector<>();
    private Vector<Device> oPins = new Vector<>();
    public Vector<String> inputStrings = new Vector<>();
    private int pins;
    private final String BLANK_SPACE = " ";

    private void FindOutputPin()
    {
        oPins.clear();
        for (Device device: circuits)
        {
            if (!device.IsConnectToOtherGates)
            {
                oPins.add(device);
            }
        }
    }

    private void connectPings()
    {
        String[] substring;
        for (int i=0;i<inputStrings.size();i++) {
            substring = inputStrings.get(i).split(BLANK_SPACE);
            for (String s : substring) {
                if (s.matches("-[1-9]\\d*"))
                {
                    circuits.get(i).addInputPin(this.iPins.get(Integer.parseInt(s.substring(1)) - 1));
                } else if (s.matches("[1-9]\\d*\\.1")) {
                    circuits.get(i).addInputPin(circuits.get(Integer.parseInt(s.split("\\.")[0]) - 1));
                }
            }
        }
        FindOutputPin();
    }

    private void createPins(int pins)
    {
        this.pins = pins;
        for (int i=0;i<pins;i++)
        {
            iPins.add(new IPin());
        }
    }

    private void createCircuits()
    {
        for (String inputString : inputStrings)
        {
            if (inputString.toCharArray()[0] == '1')
            {
                circuits.add(new GateAnd());
            } else if (inputString.toCharArray()[0] == '2')
            {
                circuits.add(new GateOR());
            } else if (inputString.toCharArray()[0] == '3')
            {
                circuits.add(new GateNot());
            }
        }
    }

    private void initialization()
    {
        inputStrings.clear();
        circuits.clear();
        iPins.clear();
        oPins.clear();
    }

    public boolean load(String filePath)
    {
        initialization();
        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            while(br.ready())
            {
                inputStrings.add(br.readLine());
            }
            fr.close();

            createPins(Integer.parseInt(inputStrings.remove(0)));
            inputStrings.remove(0);
            createCircuits();
            connectPings();
        }
        catch (Exception e){
            // System.out.println(e.toString());
            return false;
        }
        return true;
    }

    private void buildResultHead(StringBuilder stringBuilder)
    {
        for (int i=0;i<pins;i++)
        {
            stringBuilder.append("i ");
        }

        stringBuilder.append("|");

        for (int i=0;i<oPins.size();i++)
        {
            stringBuilder.append(" o");
        }

        stringBuilder.append("\n");

        for (int i=0; i<iPins.size(); i++)
        {
            stringBuilder.append(i + 1).append(BLANK_SPACE);
        }

        stringBuilder.append("|");

        for (int i=0; i<oPins.size(); i++)
        {
            stringBuilder.append(BLANK_SPACE).append(i+1);
        }
        stringBuilder.append("\n");
        String DASH = "--";
        for (int i=0; i<pins; i++)
        {
            stringBuilder.append(DASH);
        }
        stringBuilder.append("+");
        for (int i=0; i<oPins.size(); i++)
        {
            stringBuilder.append(DASH);
        }
        stringBuilder.append("\n");
    }

    public String getSimulationResult(Vector<Boolean> inputValues)
    {

        StringBuilder simulationResult = new StringBuilder("Simulation Result:\n");

        for (int i=0; i<iPins.size(); i++)
        {
            iPins.get(i).setInput(inputValues.get(i));
        }
        buildResultHead(simulationResult);

        for (Boolean inputValue : inputValues)
        {
            simulationResult.append(inputValue ? 1 : 0).append(BLANK_SPACE);
        }
        simulationResult.append("|");
        for (Device oPin: oPins)
        {
            simulationResult.append(BLANK_SPACE).append(oPin.getOutput()? 1 : 0);
        }
        simulationResult.append("\n");
        return simulationResult.toString();
    }

    private void buildTruthTableBody(StringBuilder stringBuilder)
    {
        int rows = (int) Math.pow(2, pins);

        for (int i=0; i<rows; i++)
        {
            for (int j=pins-1; j>=0; j--)
            {
                String value = String.valueOf((i/(int)Math.pow(2, j))%2);

                stringBuilder.append(value).append(BLANK_SPACE);
                // pins-j-1
                iPins.get(pins-j-1).setInput(!value.equals("0"));
            }
            stringBuilder.append("|");
            for (Device oPin: oPins)
            {
                stringBuilder.append(BLANK_SPACE).append(oPin.getOutput()?1:0);
            }
            stringBuilder.append("\n");
        }
    }

    public String getTruthTable()
    {
        StringBuilder truthTable = new StringBuilder("Truth table:\n");
        buildResultHead(truthTable);
        buildTruthTableBody(truthTable);


        return truthTable.toString();
    }

    public int getInputPinsSize()
    {
        return pins;
    }

    public int getOutputPinsSize()
    {
        return oPins.size();
    }

    public int getCircuitsSize()
    {
        return circuits.size();
    }
}
