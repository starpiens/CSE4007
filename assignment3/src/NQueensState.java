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
}