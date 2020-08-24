import org.junit.*;

import java.util.Vector;

import static org.junit.Assert.*;

public class LogicSimulatorTest
{

    String file1Path;
    String file2Path;

    @Before
    public void setUp()
    {
        file1Path = "src/File1.lcf";
        file2Path = "src/File2.lcf";
    }

    @Test
    public void testGetSimulationResult()
    {
        LogicSimulator logicSimulator = new LogicSimulator();
        try {
            logicSimulator.load(file1Path);
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }

//        Vector<Boolean> inputValues = new Vector<>();

//        assertEquals("Simulation Result:\n" +
//                "i i i | o\n" +
//                "1 2 3 | 1\n" +
//                "------+--\n" +
//                "0 1 1 | 0\n", logicSimulator.getSimulationResult(inputValues));
    }

    @Test
    public void testGetTruthTable()
    {
        LogicSimulator logicSimulator = new LogicSimulator();

        logicSimulator.load(file1Path);
//        System.out.println(logicSimulator.inputString.toString());

//        assertEquals("Truth table:\n" +
//                "i i i | o\n" +
//                "1 2 3 | 1\n" +
//                "------+--\n" +
//                "0 0 0 | 0\n" +
//                "0 0 1 | 0\n" +
//                "0 1 0 | 0\n" +
//                "0 1 1 | 0\n" +
//                "1 0 0 | 1\n" +
//                "1 0 1 | 1\n" +
//                "1 1 0 | 0\n" +
//                "1 1 1 | 0\n", logicSimulator.getTruthTable());
    }
}
