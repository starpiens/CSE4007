JFLAGS = -g -sourcepath "./src" -d "./out" -cp "./lib/*"
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	src/GeneticSolver.java \
	src/Main.java \
	src/NQueensSolver.java \
	src/NQueensState.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	rm out/*.class