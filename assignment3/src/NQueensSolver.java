public interface NQueensSolver {
    /**
     * Returns name of algorithm it uses.
     * @return Name of algorithm
     */
    public String name();

    /**
     * Solves N-Queens problem and returns a solution which is firstly found.
     * @param boardSize Size of the board.
     * @return If solution is found, return a `NQueensState` object describing the state of board.
     *         Otherwise, return null.
     */
    NQueensState solve(int boardSize);
}
