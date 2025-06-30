package programmers.highscorekit.DP;

import java.util.Arrays;
import java.util.HashSet;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/42895?language=java
코딩테스트 고득점 Kit  / 동적계획법(DP, Dynamic Programming) / N으로 표현
아래와 같이 5와 사칙연산만으로 12를 표현할 수 있습니다.

12 = 5 + 5 + (5 / 5) + (5 / 5)
12 = 55 / 5 + 5 / 5
12 = (55 + 5) / 5

5를 사용한 횟수는 각각 6,5,4 입니다. 그리고 이중 가장 작은 경우는 4입니다.
이처럼 숫자 N과 number가 주어질 때, N과 사칙연산만 사용해서 표현 할 수 있는 방법 중 N 사용횟수의 최솟값을 return 하도록 solution 함수를 작성하세요.

제한사항
N은 1 이상 9 이하입니다.
number는 1 이상 32,000 이하입니다.
수식에는 괄호와 사칙연산만 가능하며 나누기 연산에서 나머지는 무시합니다.
최솟값이 8보다 크면 -1을 return 합니다.
입출력 예
N	number	return
5	12	4
2	11	3
*/

// 이 문제는 Bottom-Up -> 작은 문제부터 큰 문제로, 값을 쌓아가며
// Top-Down -> 큰 문제를 재귀적으로 작은 문제로 나누며 계속 호출, 조건에 맞는 작은 문제 결과를 합쳐 상위 문제 해결
// Top-Down -> 중복 계산을 피하기 위해 멩모이제이션(방문 체크)가 필요, 경우에 따라 여러 분기 존재, 그 중 최적 해 선택

public class ExpressedUsingN {
	public static void main(String[] args) {

		int[] n = {5, 2};
		int[] number = {12, 11};

		for (int i = 0; i < n.length; i++) {
			System.out.println(solution(n[i], number[i]));
		}

	}

	private static int solution(int n, int number) {
		System.out.println("==========================================================");
		HashSet<Integer>[] dp = new HashSet[9];

		int num = 0;
		for (int i = 1; i <= 8; i++) {
			dp[i] = new HashSet<>();
			num = num * 10 + n;
			dp[i].add(num);

			for (int j = 1; j < i; j++) {
				for (Integer v : dp[j]) {
					for (Integer v2 : dp[i - j]) {
						dp[i].add(v + v2);
						dp[i].add(v - v2);
						dp[i].add(v * v2);
						if (v2 != 0) {
							dp[i].add(v / v2);
						}
					}
				}
			}
			if (dp[i].contains(number)) return i;
		}

		System.out.println("set = " + Arrays.toString(dp));

		return -1;
	}
}
