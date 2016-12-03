import java.io.*;

/**
 * Created by zhengwu on 11/30/16.
 */
public class FileIOTest {

    public static void main(String args[]) throws IOException {
        BufferedReader in = null;
        BufferedWriter out = null;
        try {
// File reader
            in = new BufferedReader(new FileReader(new File("WordleDeck"))); // File writer
            out = new BufferedWriter(new FileWriter(new File("output.txt")));
// Copy line-by-line
            String line;
            System.out.println(in.readLine());
            while ((line = in.readLine()) != null) {
               // out.write(line + "\n");
                System.out.println(line);
            }
        } finally {
// Finalize
            if (in != null) {
                System.out.println("HELP");
                in.close();
            }
            if (out != null) {
                System.out.println("HELP2");
                out.close(); }
        } }
}
