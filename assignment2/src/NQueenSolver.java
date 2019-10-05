import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Solves N-Queens problem using hill climbing algorithm.
 */
class NQueenSolver {

    // Size of the board.
    private int boardSize;

    /**
     * Solves N-Queens problem and returns a solution which is firstly found.
     * @param boardSize Size of the board.
     * @return If solution is found, return a NQueenState object describes the state of board. Otherwise, return null.
     */
    @Nullable
    NQueenState run(int boardSize) {
        this.boardSize = boardSize;

        // While find global minimum:
        while (true) {
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
    }


    /**
     * Get best next state of given board.
     * @param currentState Current state.
     * @return Next state that minimizes the value of `getLoss`.
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

    /**
     * Get loss of the state.
     * This version of implementation uses naive implementation,
     * which is counting the number of violations of N-queens rule.
     * @param state The board state.
     * @return Loss of `state`.
     */
    private float getLoss(NQueenState state) {
        float loss = 0;
        // Check all pairs of elements.
        for (int i = 0; i < boardSize - 1; i++) {
            for (int j = i + 1; j < boardSize; j++) {
                int delta = Math.abs(state.boardState.get(j) - state.boardState.get(i));
                // Check horizontal and diagonal.
                if (delta == 0 || delta == j - i)
                    loss++;
            }
        }
        return loss;
    }
}
