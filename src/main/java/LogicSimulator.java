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

    public void load(String filePath)
    {
        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            while(br.ready())
            {
                inputStrings.add(br.readLine());
            }
            fr.close();

            createPins(Integer.parseInt(inputStrings.remove(0)));

            createCircuits();
            connectPings();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }

    private void buildResultHead(StringBuilder stringBuilder)
    {
        for (int i=0;i<iPins.size();i++)
        {
            stringBuilder.append("i ");
        }
        stringBuilder.append("|");
        for (int i=0;i<oPins.size();i++)
        {
            stringBuilder.append(" o");
        }
        stringBuilder.append("\n");

    }
    public String getSimulationResult(Vector<Boolean> inputValues)
    {
        FindOutputPin();
        StringBuilder simulationResult = new StringBuilder("Simulation Result:\n");

        buildResultHead(simulationResult);
        // 1 2 3
        for (int i=0;i<inputValues.size();i++)
        {
            iPins.get(i).setInput(inputValues.get(i));
            simulationResult.append(i + 1).append(" ");
        }
        // 1 2 3 |
        simulationResult.append("|");
        // 1 2 3 | 1
        for (int i=0;i<oPins.size();i++)
        {
            simulationResult.append(" ").append(i+1);
        }
        simulationResult.append("\n");
        String dash = "--";
        for (int i=0; i<pins;i++)
        {
            simulationResult.append(dash);
        }
        simulationResult.append("+");
        for (int i=0; i<oPins.size();i++)
        {
            simulationResult.append(dash);
        }
        simulationResult.append("\n");
        // 0 1 1
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

    public String getTruthTable()
    {
        StringBuilder truthTable = new StringBuilder("Truth table:\ni i i | o\n");
        for (int i=0;i<pins;i++)
        {
            truthTable.append(i + 1);
            truthTable.append(" ");
        }
        truthTable.append("| 1\n");
        truthTable.append("------+--\n");
        int rows = (int) Math.pow(2, pins);
        for (int i=0; i<rows; i++)
        {
            for (int j=pins-1; j>=0; j--)
            {
                System.out.print((i/(int) Math.pow(2, j))%2 + " ");
            }
        }

        return truthTable.toString();
    }
}
