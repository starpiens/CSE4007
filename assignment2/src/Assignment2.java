import java.io.FileWriter;
import java.io.IOException;

public class Assignment2 {

    // FileWriter object to write results.
    private static FileWriter outfile;

    /**
     * Main function. Find a solution of N-Queens problem and write result on file.
     * @param args args[0] is absolute path of output file, and args[1] is parameter N of N-queens problem.
     */
    public static void main(String[] args) {
        // Parse command line arguments
        if (args.length < 2) {
            System.exit(1);
        }
        int N = Integer.parseInt(args[1]);
        try {
            outfile = new FileWriter(args[0] + "/result" + args[1] + ".txt");

            runHillClimber();

            outfile.close();

        } catch(IOException e) {
            System.exit(1);
        }
    }
}