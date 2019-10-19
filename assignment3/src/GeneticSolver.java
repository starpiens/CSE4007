import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class GeneticSolver implements NQueensSolver {

    /**
     * State representation for `GeneticSolver`.
     */
    private class GeneticState extends NQueensState {

        private int[] elementLoss = null;

        // Default constructor.
        public GeneticState(int size) {
            super(size);
        }

        // Copy constructor.
        public GeneticState(@NotNull GeneticState other) {
            super(other);
        }

        @Override
        public int getLoss() {
            if (loss != -1)
                return loss;
            computeElementLoss();
            return loss;
        }

        public int[] getElementLoss() {
            if (elementLoss != null)
                return elementLoss.clone();
            return computeElementLoss();
        }

        private int[] computeElementLoss() {
            loss = 0;
            elementLoss = new int[size];
            // Check all pairs of elements.
            for (int i = 0; i < state.length - 1; i++) {
                for (int j = i + 1; j < state.length; j++) {
                    int delta = Math.abs(state[j] - state[i]);
                    if (delta == 0 || delta == j - i) {
                        ++elementLoss[i];
                        ++elementLoss[j];
                        loss++;
                    }
                }
            }
            return elementLoss.clone();
        }
    }

    // Hyperparameters
    private int maxGenerations = (int)1e9;
    private int population = 500;           // [1,
    private int mutationStrength = 1;       // [1,
    private double crossoverRate = 0.3;
    // private double selectionRate = 0.5;     // [0, 1]

    @Override
    public String name() {
        return "Genetic Algorithm";
    }

    @Nullable
    @Override
    public NQueensState solve(int N) {
        GeneticState[] currentStates, nextStates;
        nextStates = new GeneticState[population];
        for (int i = 0; i < population; i++) {
            nextStates[i] = new GeneticState(N);
            if (nextStates[i].getLoss() == 0)
                return nextStates[i];
        }

        for (int gen = 0; gen < maxGenerations; gen++) {
            currentStates = nextStates;
            nextStates = new GeneticState[population];
            double[] inverseLoss = new double[population];
            for (int i = 0; i < population; i++)
                inverseLoss[i] = (double)1 / currentStates[i].getLoss();
            randSelect(inverseLoss, true);

            // Make children using crossover.
            int numCrossover = (int)(population * crossoverRate);
            for (int i = 0; i < numCrossover; i++) {
                GeneticState p0 = currentStates[randSelect(inverseLoss, false)];
                GeneticState p1 = currentStates[randSelect(inverseLoss, false)];
                nextStates[i] = new GeneticState(crossOver(p0, p1));
                if (nextStates[i].getLoss() == 0)
                    return nextStates[i];
            }

            // Make children using mutation.
            for (int i = numCrossover; i < population; i++) {
                int idx = randSelect(inverseLoss, false);
                nextStates[i] = new GeneticState(currentStates[idx]);
                mutate(nextStates[i]);
                if (nextStates[i].getLoss() == 0)
                    return nextStates[i];
            }
        }

        return null;
    }

    private GeneticState crossOver(GeneticState p0, GeneticState p1) {
        GeneticState child = new GeneticState(p0.size);
        Random gen = new Random();
        int idx = gen.nextInt(p0.size);
        for (int i = 0; i < idx; i++)
            child.set(i, p0.get(i));
        for (int i = idx; i < p1.size; i++)
            child.set(i, p1.get(i));
        return child;
    }

    private void mutate(GeneticState state) {
        for (int i = 0; i < mutationStrength; i++) {
            // Randomly select gene using softmax function.
            int[] elementLoss = state.getElementLoss();
            double[] expLoss = new double[elementLoss.length];
            for (int j = 0; j < elementLoss.length; j++)
                expLoss[j] = Math.exp(elementLoss[j]);
            int idx = randSelect(expLoss, true);

            // Mutate.
            Random gen = new Random();
            state.set(idx, gen.nextInt(state.size));
        }
    }

    private int randSelect(@NotNull double[] p, boolean normalize) {
        if (normalize) {
            double sum = 0;
            for (double i : p)
                sum += i;
            for (int i = 0; i < p.length; i++)
                p[i] /= sum;
        }
        double rand = Math.random();
        for (int i = 0; i < p.length; i++) {
            if (rand < p[i])
                return i;
            rand -= p[i];
        }
        return p.length - 1;
    }
}