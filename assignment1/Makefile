JFLAGS = -g -sourcepath "./src" -d "./out"
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	src/NQueenState.java \
	src/NQueenSolverInterface.java \
	src/NQueenSolverDFS.java \
	src/NQueenSolverBFS.java \
	src/NQueenSolverDFID.java \
	src/NQueenSolver.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	rm out/*.class