import java.util.Stack;

public class NQueenSolverDFS implements NQueenSolverInterface {

    public String getAlgorithmName() {
        return "DFS";
    }

    public NQueenState solve(int N) {
        return runDFS(N, N);
    }

    protected NQueenState runDFS(int N, int depth) {
        Stack<NQueenState> states = new Stack<>();
        states.push(new NQueenState());

        while (!states.empty()) {
            NQueenState currentState = states.pop();
            if (currentState.isValidState(N)) {
                // If solution is found, return.
                return currentState;
            } else if (currentState.boardState.size() < depth) {
                // Expand.
                for (int i = 0; i < N; i++) {
                    NQueenState nextState = new NQueenState(currentState);
                    nextState.boardState.add(i);
                    states.push(nextState);
                }
            }
        }

        // Solution not found.
        return null;
    }
}
