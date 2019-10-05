import java.io.FileWriter;
import java.io.IOException;

public class Assignment2 {

    // FileWriter object to write results.
    private static FileWriter outfile;

    /**
     * Main function. Find a solution of N-Queens problem and write result on file.
     * @param args `args[0]` is parameter N of N-queens problem, and `args[1]` is absolute path of output file.
     */
    public static void main(String[] args) {
        // Parse command line arguments
        if (args.length < 2) {
            System.exit(1);
        }
        int N = Integer.parseInt(args[0]);
        try {
            outfile = new FileWriter(args[1] + "/result" + args[0] + ".txt");
            NQueenSolver solver = new NQueenSolver();
            outfile.write("> Hill Climbing\n");

            // Run solver and measure elapsed time.
            long start_time = System.currentTimeMillis();
            NQueenState result = solver.run(N);
            long end_time = System.currentTimeMillis();

            // Write result.
            if (result != null) {
                outfile.write("Location:");
                for (int i : result.boardState)
                    outfile.write(" " + i);
                outfile.write("\n");
            } else {
                outfile.write("No solution\n");
            }
            // Write elapsed time.
            outfile.write("Elapsed time: " + ((double)(end_time - start_time) / 1e3) + "s\n\n");
            outfile.close();

        } catch(IOException e) {
            System.exit(1);
        }
    }
}