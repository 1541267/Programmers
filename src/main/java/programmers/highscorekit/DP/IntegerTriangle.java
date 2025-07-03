package programmers.highscorekit.DP;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/43105?language=java
코딩테스트 고득점 Kit  / 동적계획법(DP, Dynamic Programming) / 정수 삼각형
위와 같은 삼각형의 꼭대기에서 바닥까지 이어지는 경로 중, 거쳐간 숫자의 합이 가장 큰 경우를 찾아보려고 합니다. 아래 칸으로 이동할 때는 대각선 방향으로 한 칸 오른쪽 또는 왼쪽으로만 이동 가능합니다. 예를 들어 3에서는 그 아래칸의 8 또는 1로만 이동이 가능합니다.

삼각형의 정보가 담긴 배열 triangle이 매개변수로 주어질 때, 거쳐간 숫자의 최댓값을 return 하도록 solution 함수를 완성하세요.

제한사항
삼각형의 높이는 1 이상 500 이하입니다.
삼각형을 이루고 있는 숫자는 0 이상 9,999 이하의 정수입니다.
입출력 예
triangle	result
[[7], [3, 8], [8, 1, 0], [2, 7, 4, 4], [4, 5, 2, 6, 5]]	30
* */

public class IntegerTriangle {
	public static void main(String[] args) {

		int[][] t = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};

		System.out.println(solution(t));

	}

	// 바텀업, 점화식은 별로 차이가 없는데 시작의 위치 & 계산 방향 차
	private static int solution(int[][] t) {

		if (t.length == 1) return t[0][0];

		int n = t.length;

		for (int i = n - 2; i >= 0; i--) {

			for (int j = 0; j <= i; j++) {
				t[i][j] += Math.max(t[i + 1][j], t[i + 1][j + 1]);
			}
		}
		return t[0][0];
	}
}

// 바텀업 인줄 알았는데 탑다운 이유 : 메모이제이션 사용
// 	private static int solution(int[][] t) {
//
// 		if (t.length == 1) return t[0][0];
//
// 		int[][] dp = new int[t.length][t.length];
//
// 		dp[0][0] = t[0][0];
//
// 		for (int i = 1; i < t.length; i++) {
//
// 			dp[i][0] = dp[i - 1][0] + t[i][0];
//
// 			for (int j = 1; j < i; j++) {
// 				dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + t[i][j];
// 			}
//
// 			dp[i][i] = dp[i - 1][i - 1] + t[i][i];
// 		}
//
// 		int max = 0;
//
// 		for (int i : dp[t.length - 1]) {
// 			max = Math.max(max, i);
// 		}
//
// 		return max;
// 	}
// }
