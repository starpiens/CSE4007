import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.Arrays;

public class GeneticSolver implements NQueensSolver {

    /**
     * State representation for `GeneticSolver`.
     */
    private class GeneticState extends NQueensState {

        public int[] partialLoss;

        public GeneticState(int size) {
            super(size);
            partialLoss = new int[size];
        }

        /**
         * Get loss of the state.
         * This version of implementation uses naive implementation,
         * which is counting the number of violations of N-queens rule.
         * @return Loss of `state`.
         */
        int loss() {
            int loss = 0;
            // Check all pairs of elements.
            for (int i = 0; i < state.length - 1; i++) {
                for (int j = i + 1; j < state.length; j++) {
                    int delta = Math.abs(state[j] - state[i]);
                    if (delta == 0 || delta == j - i)
                        loss++;
                }
            }
            return loss;
        }

        // Who mostly caused loss?
        int maxLossIdx() {
            Arrays.fill(partialLoss, 0);
            int maxIdx = 0;
            // Check all pairs of elements.
            for (int i = 0; i < state.length - 1; i++) {
                for (int j = i + 1; j < state.length; j++) {
                    int delta = Math.abs(state[j] - state[i]);
                    if (delta == 0 || delta == j - i) {
                        if (++partialLoss[i] > partialLoss[maxIdx]) maxIdx = i;
                        if (++partialLoss[j] > partialLoss[maxIdx]) maxIdx = j;
                    }
                }
            }
            return maxIdx;
        }
    }

    private int maxGenerations = (int)1e9;
    private int population = 100;
    private int mutationStrength = 1;

    @Override
    public String name() {
        return "Genetic Algorithm";
    }

    @Nullable
    @Override
    public NQueensState solve(int N) {
        GeneticState[] states = new GeneticState[population];
        for (int i = 0; i < population; i++) {
            states[i] = new GeneticState(N);
            states[i].rand();
        }
        for (int gen = 1; gen < maxGenerations; gen++) {
            for (GeneticState i : states) {
                mutate(i);
                if (i.valid())
                    return i;
            }
        }

        return null;
    }

    /**
     * Get loss of the state.
     * This version of implementation uses naive implementation,
     * which is counting the number of violations of N-queens rule.
     * @param state The board state.
     * @return Loss of `state`.
     */
    private int getLoss(@NotNull GeneticState state) {
        int loss = 0;
        // Check all pairs of elements.
        for (int i = 0; i < state.state.length - 1; i++) {
            for (int j = i + 1; j < state.state.length; j++) {
                int delta = Math.abs(state.state[j] - state.state[i]);
                // Check horizontal and diagonal.
                if (delta == 0 || delta == j - i)
                    loss++;
            }
        }
        return loss;
    }

    private void mutate(GeneticState state) {
        Math.random();
    }

    private int softmaxRand(@NotNull double p[]) {
        int N = p.length;
        double soften[] = new double[N];
        double sum = 0;
        // Calculate Softmax of p.
        for (int i = 0; i < N; i++)
            sum += (soften[i] = Math.exp(p[i]));
        for (int i = 0; i < N; i++)
            soften[i] /= sum;
        // Pick value.
        double rand = Math.random();
        for (int i = 0; i < N; i++) {
            if (rand < soften[i])
                return i;
            rand -= soften[i];
        }
        return N - 1;
    }
}