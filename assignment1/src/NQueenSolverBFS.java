import java.util.LinkedList;

class NQueenSolverBFS implements NQueenSolverInterface {

    public String getAlgorithmName() {
        return "BFS";
    }

    public NQueenState solve(int N) {
        LinkedList<NQueenState> states = new LinkedList<>();
        states.addFirst(new NQueenState());

        while (!states.isEmpty()) {
            NQueenState currentState = states.removeLast();
            if (currentState.isValidState(N)) {
                // If solution is found, return.
                return currentState;
            } else if (currentState.boardState.size() < N) {
                // Expand.
                for (int i = 0; i < N; i++) {
                    NQueenState nextState = new NQueenState(currentState);
                    nextState.boardState.add(i);
                    states.addFirst(nextState);
                }
            }
        }

        // Solution not found.
        return new NQueenState();
    }
}
