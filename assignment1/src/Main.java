import java.io.FileWriter;
import java.io.IOException;

public class Main {

    private static FileWriter outfile;

    private static void runNQueenSolvers(NQueenSolver[] solvers, int N) throws IOException {
        for (NQueenSolver solver: solvers) {
            outfile.write("> " + solver.getAlgorithmName() + "\n");
            long start_time = System.currentTimeMillis();
            int[] result = solver.solve(N);
            long end_time = System.currentTimeMillis();

            if (result.length != 0) {
                outfile.write("Location:");
                for (int i : result)
                    outfile.write(i + " ");
                outfile.write("\n");
            } else {
                outfile.write("No solution\n");
            }

            outfile.write("Elapsed time: " + ((double)(end_time - start_time) / 1e3) + "s\n\n");
        }
    }

    public static void main(String[] args) {
        // Parse command line arguments
        if (args.length < 2) {
            System.exit(1);
        }
        int N = Integer.parseInt(args[1]);
        try {
            outfile = new FileWriter(args[0] + "/result" + args[1] + ".txt");

            NQueenSolver[] solvers = {
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
