/**
 * Interface for N-Queen problem solver classes.
 */
public interface NQueenSolverInterface {
    /**
     * Returns the name of algorithm it uses.
     * @return  name of algorithm
     */
    String getAlgorithmName();

    /**
     *
     * @param N
     * @return
     */
    NQueenState solve(int N);
}