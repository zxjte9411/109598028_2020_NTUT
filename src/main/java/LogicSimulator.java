import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Vector;

public class LogicSimulator
{
    private Vector<Device> circuits = new Vector<>();
    private Vector<Device> iPins = new Vector<>();
    private Vector<Device> oPins = new Vector<>();
    public Vector<String> inputString = new Vector<>();
    private int pins;
    private int gates;

    private void createPins()
    {
        for (int i=0;i<pins;i++)
        {
            iPins.add(new IPin());
        }
    }

    private void createCircuits()
    {
        for (int i=2;i<inputString.size();i++)
        {
            if (inputString.get(i).toCharArray()[0] == '1')
            {
                circuits.add(new GateAnd());
            }
            else if (inputString.get(i).toCharArray()[0]== '2')
            {
                circuits.add(new GateOR());
            }
            else if (inputString.get(i).toCharArray()[0]== '3')
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
                inputString.add(br.readLine());
            }
            //System.out.println(inputString.toString());
            fr.close();
            pins = Integer.parseInt(inputString.get(0));
            gates = Integer.parseInt(inputString.get(1));
            createPins();
            createCircuits();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }

    }

    public String getSimulationResult(Vector<Boolean> inputValues)
    {
        String simulationResult = "";
        return simulationResult;
    }

    public String getTruthTable()
    {
        String truthTable = "";
        return truthTable;
    }
}
