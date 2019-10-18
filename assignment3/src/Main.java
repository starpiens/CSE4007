import java.io.FileWriter;
import java.io.IOException;

public class Main {

    private static void runSolver(FileWriter outfile, NQueensSolver solver, int N) throws IOException {
        // Write the name of the algorithm.
        outfile.write(solver.name() + "\n");

        // Run solver and measure elapsed time.
        long start_time = System.currentTimeMillis();
        NQueensState result = solver.solve(N);
        long end_time = System.currentTimeMillis();

        // Write result.
        if (result != null) {
            outfile.write("Location:");
            for (int i : result.state)
                outfile.write(" " + i);
            outfile.write("\n");
        } else {
            outfile.write("No solution\n");
        }

        // Write elapsed time.
        outfile.write("Elapsed time: " + ((double)(end_time - start_time) / 1e3) + "s\n\n");
    }

    public static void main(String[] args) {
        // Parse command line arguments
        if (args.length < 2) {
            System.exit(1);
        }
        int N = Integer.parseInt(args[0]);
        try {
            FileWriter outfile = new FileWriter(args[1] + "/result" + args[0] + ".txt");
            runSolver(outfile, new GeneticSolver(), N);
            outfile.close();
        } catch(IOException e) {
            System.exit(1);
        }
    }
}
