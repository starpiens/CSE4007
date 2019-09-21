/**
 * Interface for N-Queens problem solver classes.
 */
interface NQueenSolverInterface {
    /**
     * Returns the name of algorithm it uses.
     * @return name of algorithm
     */
    String getAlgorithmName();

    /**
     * Solves N-Queens problem and returns a solution which is firstly found.
     * @param boardSize Size of the board.
     * @return If solution is found, return a NQueenState object describes the state of board.
     *         Otherwise, return null.
     */
    NQueenState solve(int boardSize);
}