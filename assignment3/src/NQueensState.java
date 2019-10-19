import org.jetbrains.annotations.NotNull;
import java.util.Random;

public class NQueensState {
    public final int size;

    protected int[] state;
    protected int loss = -1;

    // Default constructor.
    public NQueensState(int size) {
        this.size = size;
        this.state = new int[size];
        Random gen = new Random();
        for (int i = 0; i < size; i++)
            state[i] = gen.nextInt(size);
    }

    // Copy constructor.
    public NQueensState(@NotNull NQueensState other) {
        this.size = other.size;
        this.state = other.state.clone();
        this.loss = other.loss;
    }

    public int get(int idx) {
        return state[idx];
    }

    public void set(int idx, int val) {
        if (state[idx] != val) {
            state[idx] = val;
            loss = -1;
        }
    }

    public int getLoss() {
        if (loss != -1)
            return loss;
        return computeLoss();
    }

    /**
     * Compute loss of the state.
     * This version of implementation uses naive implementation,
     * which is counting the number of violations of N-queens rule.
     * @return Loss of `state`.
     */
    private int computeLoss() {
        loss = 0;
        for (int i = 0; i < state.length - 1; i++) {
            for (int j = i + 1; j < state.length; j++) {
                int delta = Math.abs(state[j] - state[i]);
                // Check horizontal and diagonal.
                if (delta == 0 || delta == j - i)
                    loss++;
            }
        }
        return loss;
    }
}