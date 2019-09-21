import java.io.FileWriter;
import java.io.IOException;

/**
 *
 */
public class NQueenSolver {

    // FileWriter object for writing results.
    private static FileWriter outfile;

    /**
     * Run NQueenSolverInterface objects and write results on `outfile`.
     * @param solvers Array of NQueenSolverInterface objects.
     * @param N Size of the chess board.
     * @throws IOException On output error.
     */
    private static void runNQueenSolvers(NQueenSolverInterface[] solvers, int N) throws IOException {
        for (NQueenSolverInterface solver : solvers) {
            // Run solver and measure elapsed time.
            outfile.write("> " + solver.getAlgorithmName() + "\n");
            long start_time = System.currentTimeMillis();
            NQueenState result = solver.solve(N);
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
        }
    }

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

            NQueenSolverInterface[] solvers = {
                    new NQueenSolverDFS(),
                    new NQueenSolverBFS(),
                    new NQueenSolverDFID()
            };
            runNQueenSolvers(solvers, N);

            outfile.close();

        } catch(IOException e) {
            System.exit(1);
        }
    }
}
