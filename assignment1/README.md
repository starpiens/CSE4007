# Run

## Development Environment
```
java 12.0.2 2019-07-16  
Java(TM) SE Runtime Environment (build 12.0.2+10)
MacOS Catalina 10.15 Beta (19A558d)
```

## Compile and Run
```bash
~/assignment1$ make
~/assignment1$ java -cp out NQueenSolver . 5
~/assignment1$ cat result5.txt
~/assignment1$ make clean
```

# Implementation

## class NQueenSolver

### main()
Main function. Find a solution of N-Queens problem and write result on file.  
***@param args*** `args[0]` is absolute path of output file, and `args[1]` is parameter N of N-queens problem.

### runNQueenSolvers()
Run `NQueenSolverInterface` objects and write results on `outfile`.  
***@param solvers*** Array of `NQueenSolverInterface` objects.  
***@param N*** Size of the chess board.  
***@throws IOException*** On output error.  


## class NQueenState
Represents a board state of N-Queens problem.

### isValidState()
Check if the state satisfies the rules of N-Queens problem.  
***@param*** N parameter of N-Queens problem.  
***@return*** If the state is okay, return true. Otherwise, return false.

### getNextStates()
Get all possible next state of board.  
***@param boardSize*** Size of the board.  
***@return*** Array of `NQueenState` objects.


## Interface NQueenSolverInterface
Interface for N-Queen problem solver classes.

### getAlgorithmName()
Returns the name of algorithm it uses.
***@return*** name of algorithm

### solve()
Solves N-Queen problem and returns a solution which is firstly found.  
***@param boardSize*** Size of the board.  
***@return*** If solution is found, return a `NQueenState` object describes the state of board. Otherwise, return null.


# Result
### N = 3 (No result expected)
```
> DFS
No solution
Elapsed time: 0.0s

> BFS
Location:
Elapsed time: 0.0s

> DFID
No solution
Elapsed time: 0.0s
```

### N = 5
```
> DFS
Location: 4 2 0 3 1
Elapsed time: 0.002s

> BFS
Location: 0 2 4 1 3
Elapsed time: 0.003s

> DFID
Location: 4 2 0 3 1
Elapsed time: 0.001s
```

### N = 7
```
> DFS
Location: 6 4 2 0 5 3 1
Elapsed time: 0.033s

> BFS
Location: 0 2 4 6 1 3 5
Elapsed time: 0.139s

> DFID
Location: 6 4 2 0 5 3 1
Elapsed time: 0.047s
```
