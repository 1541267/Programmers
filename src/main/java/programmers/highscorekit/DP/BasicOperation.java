package programmers.highscorekit.DP;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/1843?language=java
코딩테스트 고득점 Kit  / 동적계획법(DP, Dynamic Programming) / 사칙연산
사칙연산에서 더하기(+)는 결합법칙이 성립하지만, 빼기(-)는 결합법칙이 성립하지 않습니다.
예를 들어 식 1 - 5 - 3은 연산 순서에 따라 다음과 같이 다른 결과를 가집니다.

((1 - 5) - 3) = -7
(1 - (5 - 3)) = -1
위 예시와 같이 뺄셈은 연산 순서에 따라 그 결과가 바뀔 수 있습니다.
또 다른 예로 식 1 - 3 + 5 - 8은 연산 순서에 따라 다음과 같이 5가지 결과가 나옵니다.

(((1 - 3) + 5) - 8) = -5
((1 - (3 + 5)) - 8) = -15
(1 - ((3 + 5) - 8)) = 1
(1 - (3 + (5 - 8))) = 1
((1 - 3) + (5 - 8)) = -5
위와 같이 서로 다른 연산 순서의 계산 결과는 [-15, -5, -5, 1, 1]이 되며, 이중 최댓값은 1입니다.
문자열 형태의 숫자와, 더하기 기호("+"), 뺄셈 기호("-")가 들어있는 배열 arr가 매개변수로 주어질 때, 서로 다른 연산순서의 계산 결과 중 최댓값을 return 하도록 solution 함수를 완성해 주세요.

제한 사항
arr는 두 연산자 "+", "-" 와 숫자가 들어있는 배열이며, 길이는 3 이상 201 이하 입니다.
arr의 길이는 항상 홀수입니다.
arr에 들어있는 숫자의 개수는 2개 이상 101개 이하이며, 연산자의 개수는 (숫자의 개수) -1 입니다.
숫자는 1 이상 1,000 이하의 자연수가 문자열 형태로 들어있습니다.. (ex : "456")
배열의 첫 번째 원소와 마지막 원소는 반드시 숫자이며, 숫자와 연산자가 항상 번갈아가며 들어있습니다.
입출력 예
arr	result
["1", "-", "3", "+", "5", "-", "8"]	1
["5", "-", "3", "+", "1", "+", "2", "-", "4"]	3
입출력 예시
입출력 예 #1
위의 예시와 같이 (1-(3+(5-8))) = 1 입니다.

입출력 예 #2
(5-(3+((1+2)-4))) = 3 입니다.
*/

// a + b + c + d + e 에서
// (a,b), (a,b,c) .. (c,d), (c,d,e) .. 으로 구간 계산
// 인덱스를 증가로 사용하려면 길이를 늘려가며 계산,
public class BasicOperation {
	public static void main(String[] args) {

		String[] arr1 = {"1", "-", "3", "+", "5", "-", "8"};
		String[] arr2 = {"5", "-", "3", "+", "1", "+", "2", "-", "4"};

		System.out.println(solution(arr1));
		System.out.println(solution(arr2));
		System.out.println("==========================================================");
		System.out.println(solution2(arr1));
		System.out.println(solution2(arr2));

	}

	private static int solution(String[] a) {

		int N = (a.length + 1) / 2;
		int[][] maxDp = new int[N][N];
		int[][] minDp = new int[N][N];

		for (int i = N - 1; i >= 0; i--) {

			int v = Integer.parseInt(a[2 * i]);
			maxDp[i][i] = v;
			minDp[i][i] = v;

			for (int j = i + 1; j < N; j++) {

				maxDp[i][j] = Integer.MIN_VALUE;
				minDp[i][j] = Integer.MAX_VALUE;

				for (int k = i; k < j; k++) {

					char op = a[2 * k + 1].charAt(0);
					int leftMax = maxDp[i][k];
					int leftMin = minDp[i][k];
					int rightMax = maxDp[k + 1][j];
					int rightMin = minDp[k + 1][j];

					if (op == '+') {
						maxDp[i][j] = Math.max(maxDp[i][j], leftMax + rightMax);
						minDp[i][j] = Math.min(minDp[i][j], leftMin + rightMin);
					} else {
						maxDp[i][j] = Math.max(maxDp[i][j], leftMax - rightMin);
						minDp[i][j] = Math.min(minDp[i][j], leftMin - rightMax);
					}
				}
			}
		}
		return maxDp[0][N - 1];
	}

	private static int solution2(String[] a) {
		int N = (a.length + 1) / 2;
		int[][] maxDp = new int[N][N];
		int[][] minDp = new int[N][N];

		for (int i = 0; i < N; i++) {
			int v = Integer.parseInt(a[2 * i]);
			maxDp[i][i] = v;
			minDp[i][i] = v;
		}

		for (int len = 1; len < N; len++) {
			for (int i = 0; i + len < N; i++) {
				int j = i + len;
				maxDp[i][j] = Integer.MIN_VALUE;
				minDp[i][j] = Integer.MAX_VALUE;

				for (int k = i; k < j; k++) {
					char op = a[2 * k + 1].charAt(0);
					int leftMax = maxDp[i][k];
					int leftMin = minDp[i][k];
					int rightMax = maxDp[k + 1][j];
					int rightMin = minDp[k + 1][j];

					if (op == '+') {
						maxDp[i][j] = Math.max(maxDp[i][j], leftMax + rightMax);
						minDp[i][j] = Math.min(minDp[i][j], leftMin + rightMin);
					} else {
						maxDp[i][j] = Math.max(maxDp[i][j], leftMax - rightMin);
						minDp[i][j] = Math.min(minDp[i][j], leftMin - rightMax);
					}
				}
			}
		}

		return maxDp[0][N - 1];
	}
}
