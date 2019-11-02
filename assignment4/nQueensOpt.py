from z3 import *
import time

# Number of Queens
N = int(input("N: "))

start = time.time()
# Variables
X = [Int("col_%s" % col) for col in range(N)]

# Constraints
domain = [And(X[col] > 0, X[col] <= N) for col in range(N)]

posConst = [
    And(X[i] != X[j], X[j]-X[i] != j-i, X[j]-X[i] != i-j)
    for i in range(N-1)
    for j in range(i+1,N)
]

s = Solver()
s.add(domain + posConst)

if s.check() == sat:
    m = s.model()
    r = [m.evaluate(X[i]) for i in range(N)]
    print(r)

print("Elapsed time: ", time.time() - start, "s")