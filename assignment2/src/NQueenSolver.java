import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Solves N-Queens problem using hill climbing algorithm.
 */
class NQueenSolver {

    // Maximum number of iterations.
    private final int MAX_ITERS = 1000;
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

        // While find global minimum:
        for (int i = 0; i < MAX_ITERS; i++) {
            // Generate a new random state.
            NQueenState state = new NQueenState(boardSize);
            float currentLoss = getLoss(state);
            // If global minimum is found, return it.
            if (currentLoss == 0.0) {
                return state;
            }

            NQueenState prevState;
            float prevLoss;
            // While find local minimum:
            do {
                prevState = state;
                prevLoss = currentLoss;
                state = getBestNextState(state);
                currentLoss = getLoss(state);
            } while (prevLoss > currentLoss);

            // If global minimum is found, return it.
            if (prevLoss == 0.0) {
                return prevState;
            }
        }

        // Maximum number of iterations exceeded.
        return null;
    }


    /**
     * Get best next state of given board.
     * @param currentState Current state.
     * @return Next state that maximizes the value of `getLoss`.
     */
    @NotNull
    private NQueenState getBestNextState(NQueenState currentState) {
        assert(currentState.boardState.size() == boardSize);

        NQueenState bestState = new NQueenState();
        float bestLoss = Float.MAX_VALUE;
        for (int i = 0; i < boardSize; i++) {
            for (int j = -1; j <= 1; j += 2) {
                int nextRow = currentState.boardState.get(i) + j;
                // If it is valid state:
                if (0 <= nextRow && nextRow < boardSize) {
                    NQueenState nextState = new NQueenState(currentState);
                    nextState.boardState.set(i, nextRow);
                    float nextLoss = getLoss(nextState);
                    if (bestLoss > nextLoss) {
                        bestLoss = nextLoss;
                        bestState = nextState;
                    }
                }
            }
        }
        return bestState;
    }

    // TODO
    private float getLoss(NQueenState state) {
        return (float)3.0;
    }
}
