package programmers.highscorekit.DP;

import java.util.Arrays;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/42897?language=java
코딩테스트 고득점 Kit / 동적계획법(DP, Dynamic Programming) / 도둑질
도둑이 어느 마을을 털 계획을 하고 있습니다. 이 마을의 모든 집들은 아래 그림과 같이 동그랗게 배치되어 있습니다.

image.png

각 집들은 서로 인접한 집들과 방범장치가 연결되어 있기 때문에 인접한 두 집을 털면 경보가 울립니다.

각 집에 있는 돈이 담긴 배열 money가 주어질 때, 도둑이 훔칠 수 있는 돈의 최댓값을 return 하도록 solution 함수를 작성하세요.

제한사항
이 마을에 있는 집은 3개 이상 1,000,000개 이하입니다.
money 배열의 각 원소는 0 이상 1,000 이하인 정수입니다.
입출력 예
money	return
[1, 2, 3, 1]	4
*/

public class Thievery {
	public static void main(String[] args) {
		int[] m = {1, 2, 3, 1};
		System.out.println(solution(m));
	}

	private static int solution(int[] m) {

		int n = m.length - 1;

		int aPrev2 = m[0];
		int aPrev1 = Math.max(m[0], m[1]);
		int bPrev2 = m[1];
		int bPrev1 = Math.max(m[1], m[2]);

		for (int i = 2; i < n; i++) {
			int aCurr = Math.max(aPrev1, aPrev2 + m[i]);
			aPrev2 = aPrev1;
			aPrev1 = aCurr;
			int bCurr = Math.max(bPrev1, bPrev2 + m[i + 1]);
			bPrev2 = bPrev1;
			bPrev1 = bCurr;
		}
		return Math.max(aPrev1, bPrev1);
	}
}

// 	private static int solution(int[] m) {
//
// 		int n = m.length - 1;
//
// 		int[] dpA = new int[n];
// 		int[] dpB = new int[n];
//
// 		dpA[0] = m[0];
// 		dpA[1] = Math.max(m[1], m[0]);
// 		dpB[0] = m[1];
// 		dpB[1] = Math.max(m[1], m[2]);
//
// 		for (int i = 2; i < n; i++) {
// 			dpA[i] = Math.max(dpA[i - 1], dpA[i - 2] + m[i]);
// 			dpB[i] = Math.max(dpB[i - 1], dpB[i - 2] + m[i + 1]);
// 		}
// 		System.out.println("m = " + Arrays.toString(m));
// 		System.out.println("dpA = " + Arrays.toString(dpA));
// 		System.out.println("dpB = " + Arrays.toString(dpB));
// 		return Math.max(dpA[n - 1], dpB[n - 1]);
// 	}
// }
