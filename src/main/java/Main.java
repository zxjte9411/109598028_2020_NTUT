import java.util.Scanner;
import java.util.Vector;

public class Main
{
    public static void main(String[] args)
    {
        LogicSimulator logicSimulator = new LogicSimulator();
        Scanner scanner = new Scanner(System.in);
        boolean isStop = false;

        while (!isStop)
        {
            System.out.println("1. Load logic circuit file");
            System.out.println("2. Simulation");
            System.out.println("3. Display truth table");
            System.out.println("4. Exit");
            System.out.print("Command:");
            String inputCommand = scanner.nextLine();
            if (inputCommand.matches("[1-4]"))
            {
                if (inputCommand.equals("1"))
                {
                    System.out.print("Please key in a file path:");
                    if(!logicSimulator.load(scanner.nextLine()))
                    {
                        System.out.println("File not found or file format error!!");
                    }
                    else
                    {
                        System.out.printf("Circuit: %d input pins, %d output pins and %d gates\n",
                                logicSimulator.getInputPinsSize(),
                                logicSimulator.getOutputPinsSize(),
                                logicSimulator.getCircuitsSize());
                    }
                }
                else if (inputCommand.equals("2"))
                {
                    if (logicSimulator.isFileLoadedAndSuccess)
                    {
                        Vector<Boolean> inputPinsValue = new Vector<>();
                        for (int i=0; i<logicSimulator.getInputPinsSize(); i++)
                        {
                            System.out.printf("Please key in the value of input pin %d:", i+1);
                            String value = scanner.nextLine();
                            if (value.matches("[0-1]"))
                            {
                                inputPinsValue.add(!value.equals("0"));
                            }
                            else
                            {
                                i-=1;
                                System.out.println("The value of input pin must be 0/1");
                            }
                        }
                        System.out.println(logicSimulator.getSimulationResult(inputPinsValue));
                    }
                    else
                    {
                        System.out.println("Please load an lcf file, before using this operation.");
                    }
                }
                else if (inputCommand.equals("3"))
                {
                    if (logicSimulator.isFileLoadedAndSuccess)
                        System.out.println(logicSimulator.getTruthTable());
                    else
                        System.out.println("Please load an lcf file, before using this operation.");
                }
                else if (inputCommand.equals("4"))
                {
                    isStop = true;
                    System.out.print("Goodbye, thanks for using LS.");
                }
            }
            else
            {
                System.out.println("Illegal input!");
            }
        }
    }
}