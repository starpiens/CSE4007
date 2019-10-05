import java.util.ArrayList;
import java.util.Random;

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

    // Random fill constructor.
    public NQueenState(int boardSize) {
        this.boardState = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < boardSize; i++) {
            this.boardState.add(random.nextInt(boardSize));
        }
    }
}