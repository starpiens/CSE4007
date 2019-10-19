import org.jetbrains.annotations.NotNull;
import java.util.Random;

public class NQueensState {
    public int[] state;

    // Default constructor.
    public NQueensState(int size) {
        this.state = new int[size];
    }

    // Copy constructor.
    public NQueensState(@NotNull NQueensState other) {
        this.state = other.state.clone();
    }

    // Set state random.
    public void rand() {
        Random gen = new Random();
        for (int i = 0; i < state.length; i++) {
            state[i] = gen.nextInt(state.length);
        }
    }

    public boolean valid() {
        // Check all pairs of elements.
        for (int i = 0; i < state.length - 1; i++) {
            for (int j = i + 1; j < state.length; j++) {
                int delta = Math.abs(state[j] - state[i]);
                // Check horizontal and diagonal.
                if (delta == 0 || delta == j - i)
                    return false;
            }
        }
        return true;
    }
}