package programmers.highscorekit.bruteForce;

import java.util.ArrayList;
import java.util.List;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/86971?language=java
코딩테스트 고득점 Kit / 완전 탐색 / 전력망을 둘로 나누기
n개의 송전탑이 전선을 통해 하나의 트리 형태로 연결되어 있습니다. 당신은 이 전선들 중 하나를 끊어서 현재의 전력망 네트워크를 2개로 분할하려고 합니다. 이때, 두 전력망이 갖게 되는 송전탑의 개수를 최대한 비슷하게 맞추고자 합니다.

송전탑의 개수 n, 그리고 전선 정보 wires가 매개변수로 주어집니다. 전선들 중 하나를 끊어서 송전탑 개수가 가능한 비슷하도록 두 전력망으로 나누었을 때, 두 전력망이 가지고 있는 송전탑 개수의 차이(절대값)를 return 하도록 solution 함수를 완성해주세요.

제한사항
n은 2 이상 100 이하인 자연수입니다.
wires는 길이가 n-1인 정수형 2차원 배열입니다.
wires의 각 원소는 [v1, v2] 2개의 자연수로 이루어져 있으며, 이는 전력망의 v1번 송전탑과 v2번 송전탑이 전선으로 연결되어 있다는 것을 의미합니다.
1 ≤ v1 < v2 ≤ n 입니다.
전력망 네트워크가 하나의 트리 형태가 아닌 경우는 입력으로 주어지지 않습니다.
입출력 예
n	wires	result
9	[[1,3],[2,3],[3,4],[4,5],[4,6],[4,7],[7,8],[7,9]]	3
4	[[1,2],[2,3],[3,4]]	0
7	[[1,2],[2,7],[3,7],[3,4],[4,5],[6,7]]	1
입출력 예 설명
입출력 예 #1

다음 그림은 주어진 입력을 해결하는 방법 중 하나를 나타낸 것입니다.
ex1.png
4번과 7번을 연결하는 전선을 끊으면 두 전력망은 각 6개와 3개의 송전탑을 가지며, 이보다 더 비슷한 개수로 전력망을 나눌 수 없습니다.
또 다른 방법으로는 3번과 4번을 연결하는 전선을 끊어도 최선의 정답을 도출할 수 있습니다.
*/

/*
각 노드를 루트로 시작 하는 dfs, 해당 노드의 자식 개수(전력망, 자신 포함)를 체크 하고 dp[i] 에 저장
자르고 난 노드의 개수 = dp[i]
해당 노드를 잘랐을 때의 나머지 개수 = n - dp[i]
자르고 난 노드의 개수 = dp[i] - (n - dp[i]) => n - (dp[i] * 2)
이 차이의 절대값의 최소값 구하기
*/

public class SplittingTheElectricGridInTwo {
	public static void main(String[] args) {

		int[][][] wires =
			{{{1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}},
				{{1, 2}, {2, 3}, {3, 4}},
				{{1, 2}, {2, 7}, {3, 7}, {3, 4}, {4, 5}, {6, 7}}};

		int[] n = {9, 4, 7};

		for (int i = 0; i < wires.length; i++) {
			System.out.println("==========================================================");
			System.out.println(solution(n[i], wires[i]));
		}
	}

	private static int solution(int n, int[][] w) {

		List<List<Integer>> tree = new ArrayList<>(n + 1);

		for (int i = 0; i <= n; i++) {
			tree.add(i, new ArrayList<>());
		}

		for (int[] ints : w) {
			int first = ints[0], second = ints[1];
			tree.get(first).add(second);
			tree.get(second).add(first);
		}

		boolean[] visited = new boolean[n + 1];
		int[] dp = new int[n + 1];

		dfs(tree, visited, dp, 1);

		int diff = Integer.MAX_VALUE;

		for (int j : dp) {
			diff = Math.min(diff, Math.abs(n - 2 * j));
		}

		return diff;
	}

	private static int dfs(List<List<Integer>> tree, boolean[] visited, int[] dp, int curIdx) {
		dp[curIdx] = 1;
		visited[curIdx] = true;

		for (Integer next : tree.get(curIdx)) {
			if (!visited[next]) {
				visited[next] = true;
				dp[curIdx] += dfs(tree, visited, dp, next);
			}
		}

		return dp[curIdx];
	}
}