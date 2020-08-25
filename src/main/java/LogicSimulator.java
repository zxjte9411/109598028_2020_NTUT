import sun.security.util.BitArray;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.Vector;

public class LogicSimulator
{
    private Vector<Device> circuits = new Vector<>();
    private Vector<Device> iPins = new Vector<>();
    private Vector<Device> oPins = new Vector<>();
    public Vector<String> inputStrings = new Vector<>();
    private int gates;
    private int pins;

    private void Print(Object something)
    {
        System.out.println(something);
    }

    private int FindOutputPin()
    {
        for (int i=0;i<circuits.size();i++)
        {
            for (int j=1;j<circuits.size();j++)
            {
                if (i != j)
                {
                    if(circuits.get(i) == circuits.get(j))
                    {
                        return i;
                    }
                }
            }
        }
        return 0;
    }

    private void connectPings()
    {
        String[] substring;
        for (int i=0;i<inputStrings.size();i++) {
            substring = inputStrings.get(i).split(" ");
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
            gates = Integer.parseInt(inputStrings.remove(0));

            createCircuits();
            connectPings();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public String getSimulationResult(Vector<Boolean> inputValues)
    {
        int oPinIndex = FindOutputPin();
        StringBuilder simulationResult = new StringBuilder("Simulation Result:\ni i i | o\n");
        for (int i=0;i<inputValues.size();i++)
        {
            iPins.get(i).setInput(inputValues.get(i));
            simulationResult.append(i + 1);
            simulationResult.append(" ");
        }
        simulationResult.append("| 1\n");
        simulationResult.append("------+--\n");
        for (Boolean inputValue : inputValues) {
            simulationResult.append(inputValue ? 1 : 0);
            simulationResult.append(" ");
        }
        simulationResult.append("| ");
        simulationResult.append(circuits.get(oPinIndex).getOutput()? 1 : 0);
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
//        int rows = (int) Math.pow(2, pins);


        return truthTable.toString();
    }
}
