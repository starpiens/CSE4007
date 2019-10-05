JFLAGS = -g -sourcepath "./src" -d "./out" -cp "./lib/*"
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	src/Assignment2.java \
	src/NQueenSolver.java \
	src/NQueenState.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	rm out/*.class