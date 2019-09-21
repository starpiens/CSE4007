class NQueenSolverDFID extends NQueenSolverDFS {

    public String getAlgorithmName() {
        return "DFID";
    }

    public NQueenState solve(int N) {
        // Iterative deepening.
        for (int depth = 1; depth <= N; depth++) {
            NQueenState result = runDFS(N, depth);
            if (result != null && result.isValidState(N))
                return result;
        }

        // Solution not found.
        return null;
    }
}
