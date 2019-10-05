import org.jetbrains.annotations.Nullable;

/**
 * Solves N-Queens problem using hill climbing algorithm.
 */
public class NQueenSolver {

    // Maximum number of iterations.
    final int MAX_ITERS = 1000;
    // Size of the board.
    private int boardSize;

    /**
     * Solves N-Queens problem and returns a solution which is firstly found.
     * @param boardSize Size of the board.
     * @return If solution is found, return a NQueenState object describes the state of board. Otherwise, return null.
     */
    @Nullable
    public NQueenState run(int boardSize) {
        this.boardSize = boardSize;
        NQueenState currentState;

        // While find global minimum:
        for (int i = 0; i < MAX_ITERS; i++) {
            // Generate a new random state.
            currentState = new NQueenState(boardSize);
            // If global minimum is found, return it.
            if (getLoss(currentState) == 0.0) {
                return currentState;
            }

            // While find local minimum:
            while (true) {
                NQueenState bestState = getBestNextState(currentState);
                if (getLoss(currentState) > getLoss(bestState)) {
                    currentState = bestState;
                } else {
                    break;
                }
            }

            // If global minimum is found, return it.
            if (getLoss(currentState) == 0.0) {
                return currentState;
            }
        }

        // Maximum number of iterations exceeded.
        return null;
    }


    /**
     * Get best next state of given board.
     * @param state Current state.
     * @return Next state that maximizes the value of `getLoss`.
     */
    // TODO
    @Nullable
    private NQueenState getBestNextState(NQueenState state) {
        return null;
    }

    // TODO
    float getLoss(NQueenState state) {
        return (float)3.0;
    }
}
