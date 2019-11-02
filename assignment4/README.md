# Assignment4: SAT Solver
N-Queens 문제를 SAT Solver를 통해 해결한다.

## Development Environment  
- MacOS 10.15 Catalina
- Python 3.7.4
- z3-solver 4.8.6.0

## Run
```bash
~/assignment4$ pip install -r "requirements.txt"
~/assignment4$ python nQueensOpt.py
```

## Methodology

### Variables
`i`번째 열에서 퀸이 있는 행 번호를 `col_i`라고 둔다. 
```python
X = [Int("col_%s" % col) for col in range(N)]
```

### Constraint
**Domain.** 모든 변수는 `[1, N]`의 자연수 값을 가져야 한다.
```python
domain = [And(X[col] > 0, X[col] <= N) for col in range(N)]
```
**Position.** 모든 (i,j)변수 쌍에 대해서, 두 퀸이 같은 행에 놓여 있거나 같은 대각선에 놓일 수 없다. 
```python
posConst = [
    And(X[i] != X[j], X[j]-X[i] != j-i, X[j]-X[i] != i-j)
    for i in range(N-1)
    for j in range(i+1,N)
]
```

## Result
Intel i7-7567U(@3.50GHz), 16GB RAM이 장착된 2017 Macbook Pro 3번씩 실행한 뒤 평균값을 비교했다.  

|                          | N=4       | N=8        | N=12       | N=16        | N=20        |
| ------------------------:| --------- | ---------- | ---------- | ----------- | ----------- |
| **Naive**                | 0.08s     | 0.99s      | 5.16s      | 39.2s       | 141.2s      |
| **Optimized**            | 0.03s     | 0.04s      | 0.06s      | 0.12s       | 0.68s       |
| **Relative performance** | **2.66x** | **24.75x** | **86.00x** | **326.66x** | **207.64x** |

N값이 커질수록 속도 격차가 크게 벌어졌다.   
이런 차이가 발생하는 주된 이유는 변수를 다르게 설정했기 때문으로, 
naive한 버전에서는 각 변수가 체스판에서 하나의 격자를 나타냈지만 최적화된 버전에서는 한 열을 통째로 하나의 변수로 나타냈다. 
이런 방식을 도입하면 1) 변수의 수가 1/N으로 크게 줄어들고, 2) 동일 열에 대한 제약조건을 확인할 필요가 없기 때문에, 
훨씬 빠른 속도를 보이는 것이다. 