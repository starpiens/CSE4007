# Run

## Development Environment
```
javac 12.0.2
java 12.0.2 2019-07-16
Java(TM) SE Runtime Environment (build 12.0.2+10)
MacOS Catalina 10.15 Beta (19A582a)
```

## Compile and Run
```bash
~/assignment1$ make
~/assignment1$ java -cp out Assignment2 7 .
~/assignment1$ cat result7.txt
```

# Implementation

## Assignment2
```java
public class Assignment2
```
Solves Assignment2

### main()
```java
public static void main(String[] args)
```
Main function. Find a solution of N-Queens problem and write result on file.  
***@param args*** `args[0]` is parameter N of N-queens problem, and `args[1]` is absolute path of output file.

## NQueenState
```java
class NQueenState
```
Represents a board state of N-Queens problem.

## NQueenSolver
```java
class NQueenSolver
```

### run
```java
@Nullable NQueenState run(int boardSize)
```
Solves N-Queens problem and returns a solution which is firstly found.  
***@param boardSize*** Size of the board.  
***@return*** If solution is found, return a NQueenState object describes the state of board. Otherwise, return null.

### getBestNextState
```java
@NotNull private NQueenState getBestNextState(NQueenState currentState)
```
Get best next state of given board.  
***@param currentState*** Current state.  
***@return*** Next state that minimizes the value of `getLoss`.

### getLoss
```java
private float getLoss(NQueenState state)
```
Get loss of the state.  
This version of implementation uses naive implementation,
which is counting the number of violations of N-queens rule.  
***@param state*** The board state.  
***@return*** Loss of `state`.  

# Result
```zsh
➜  assignment2 git:(master) ✗ java -cp out Assignment2 11 . && cat result11.txt
> Hill Climbing
Location: 10 4 6 3 0 7 1 8 5 2 9
Elapsed time: 0.215s

➜  assignment2 git:(master) ✗ java -cp out Assignment2 11 . && cat result11.txt
> Hill Climbing
Location: 2 7 1 3 0 8 10 5 9 6 4
Elapsed time: 0.121s

➜  assignment2 git:(master) ✗ java -cp out Assignment2 11 . && cat result11.txt
> Hill Climbing
Location: 6 9 1 4 0 8 3 7 10 2 5
Elapsed time: 0.189s
```
- Because of its random behavior, results were different for each run. 
- Hill climbing method can find solution much faster than BFS, DFS.
- As `N` increases, number of restarts increased exponentially. It means there are many local minima.
