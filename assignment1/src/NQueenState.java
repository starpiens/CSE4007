import java.util.ArrayList;

/**
 * Represents a board state of N-Queens problem.
 */
class NQueenState {
    public ArrayList<Integer> boardState;

    // Default constructor.
    public NQueenState() {
        this.boardState = new ArrayList<>();
    }

    // Deep copy constructor.
    public NQueenState(NQueenState another) {
        this.boardState = new ArrayList<>(another.boardState);
    }

    /**
     * Check if the state satisfies the rules of N-Queens problem.
     * @param N Number of N.
     * @return If the state is okay, return true. Otherwise, return false.
     */
    public boolean isValidState(int N) {
        // Is the number of elements right?
        if (boardState.size() != N)
            return false;

        // Check all pairs of elements.
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int delta = Math.abs(boardState.get(j) - boardState.get(i));
                // Check horizontal and diagonal.
                if (delta == 0 || delta == j - i)
                    return false;
            }
        }

        return true;
    }

    /**
     * Get all possible next state of board.
     * @param boardSize Size of the board.
     * @return Array of `NQueenState` objects.
     */
    public NQueenState[] getNextStates(int boardSize) {
        NQueenState[] nextStates = new NQueenState[boardSize];
        for (int i = 0; i < boardSize; i++) {
            nextStates[i] = new NQueenState(this);
            nextStates[i].boardState.add(i);
        }
        return nextStates;
    }
}
