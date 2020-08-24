import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Vector;

public class Main
{
    public static void main(String args[]) throws Exception
    {
        FileReader fr = new FileReader("src/File1.lcf");
        BufferedReader br = new BufferedReader(fr);
        Vector<String> test = new Vector<>();
        while(br.ready())
        {
            test.add(br.readLine());
        }
        System.out.println(test.toString());
        fr.close();
    }
}
