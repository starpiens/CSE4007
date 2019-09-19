import java.util.ArrayList;

/**
 * Represents a state of N-Queen problem.
 */
public class NQueenState {
    public ArrayList<Integer> boardState;

    public NQueenState() {
        this.boardState = new ArrayList<>();
    }

    // Deep copy constructor.
    public NQueenState(NQueenState another) {
        this.boardState = new ArrayList<>(another.boardState);
    }

    /**
     * Check if the state satisfies the rules of N-Queen problem.
     * @param boardSize The size of chess board.
     * @return          If the state is okay, return true.
     */
    public boolean isValidState(int boardSize) {
        // Is the number of elements right?
        if (boardState.size() != boardSize)
            return false;

        // Check all pairs of elements.
        for (int i = 0; i < boardSize; i++) {
            for (int j = i + 1; j < boardSize; j++) {
                int delta = Math.abs(boardState.get(j) - boardState.get(i));
                // Check horizontal and diagonal.
                if (delta == 0 || delta == j - i)
                    return false;
            }
        }

        return true;
    }
}
