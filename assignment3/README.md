# Assignment3: Genetic Algorithm
N-Queens 문제를 Genetic Algorithm(GA)을 통해 해결한다. 


## Development Environment
```
javac 12.0.2
java 12.0.2 2019-07-16
Java(TM) SE Runtime Environment (build 12.0.2+10)
MacOS 10.15 Catalina
```


## Compile and Run
```bash
~/assignment3$ make
~/assignment3$ java -cp out Main 11 .
~/assignment3$ cat result11.txt
```

## Methodology

**Loss.**
앞선 과제에서 사용했던 것과 동일하게, N-Queens 문제의 제약을 위반한 횟수를 loss라는 값으로 나타냈다.  
알고리즘의 목표는 loss를 0으로 만드는 것이다.

### Genetic Algorithm

**Gene.** 
여기서 사용한 gene은 다른 과제들에서 사용했던 board state와 같다. 
각 열에 대한 queen의 행의 위치를 나타낸 것이다.  

**Selection.**
각 state들의 loss를 계산한 뒤, loss의 역수를 normalize한다. 
이 값들을 각각의 state들이 선택될 확률로 사용했다.
즉, loss값이 적을 수록 다음 세대에 전달될 확률이 높아진다.  

**Crossover.**
`crossoverRate`와 `population` 값을 곱한 수만큼의 state들이 crossover를 통해 다음 세대로 전달된다.  
Crossover의 수행은 [1, N-1]의 랜덤한 값 i를 뽑은 뒤 [0, i-1]은 왼쪽 부모로부터 가져오고 나머지 [i, N-1]은 오른쪽 부모로부터 가져오도록 했다.
어떤 부모 state A가 [0, N-1]에서 적은 loss를 보인다면 이의 sub-state인 [0, i-1]도 좋은 loss를 보일 것으로 생각함이 합리적이기 때문이다.  
여기에 유전적 다양성을 위해 mutation을 적용한다.  

**Mutation.**
각 gene의 어떤 부분이 loss에 기여했는지를 `elementLoss`라는 값으로 나타낸다.
`elementLoss[i]`는 `i`번째 값이 다른 모든 값들과 비교했을 때 N-Queens 문제의 제약을 몇 번 위반했는지를 나타낸다.
`elementLoss`에 softmax 함수를 적용한 값을 확률로 하여 gene을 선택한다. 
즉, 이 값이 높을수록 gene의 해당 부분이 바뀔 확률이 높아진다.  
이 과정을 `mutationStrength` 만큼 반복한다.

## Experiment & Result

### Hyperparameter Tuning
각 hyperparameter에 대해 100번 실행한 뒤 평균값을 이용했다.  
- `N == 8`
- `mutationStrength == 1`
- `population == 500`

**Crossover rate.**
  
| Crossover rate | 0 | 0.2 | 0.4 | 0.6 | 0.8 | 1 |
|----------------|---|-----|-----|-----|-----|---|
| Average time(s)| 0.00475 | 0.00648 | 0.00768 | 0.00943 | 0.01264 | 0.0126 |

Crossover보다 mutation의 성능이 월등한 것으로 보인다. 
유전자를 자르는 위치 `i`를 랜덤하게 정하지 않고 더 최적화했으면 어땠을까 하는 아쉬움이 있다.

**Mutation strength.**

- `N == 8`
- `population == 500`
- `crossoverRate == 0.1`

| Mutation strength | 1 | 2 | 3 | 4 |
|-------------------|---|---|---|---|
| Average time(s)   | 0.00496 | 0.00607 | 0.00775 | 0.01104 |

Mutation도 과하면 오히려 성능이 감소했다. 

**Population.**

- `N == 8`
- `mutationStrength == 1`
- `crossoverRate == 0.1`

| Population      |   1   |   10    |   30    |   50    |   100   |
| --------------- | ----- | ------- | ------- | ------- | ------- |
| Average time(s) | 0.004 | 0.00096 | 0.001062 | 0.00119 | 0.00203 | 

의외로 `population == 10` 정도에서 가장 좋은 성능을 냈다.

### Result
각각의 N에 대한 최종 결과는 다음과 같다.

- `population == 10`
- `mutationStrength == 1`
- `crossoverRate == 0`

|N (= Board size) |   8    |   10   |   12   |   14   |   16   |
| --------------- | ------ | ------ | ------ | ------ | ------ |
| Average time(s) |0.00122 |0.00536 |0.01474 |0.05001 |0.20001 |

매우 빠른 속도로 정답을 찾음을 확인할 수 있었다. 

<img src="./rsrc/run.png" width="70%">