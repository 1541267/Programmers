package programmers.highscorekit.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/49191?language=java
코딩테스트 연습 / 그래프 / 순위

문제 설명
n명의 권투선수가 권투 대회에 참여했고 각각 1번부터 n번까지 번호를 받았습니다. 권투 경기는 1대1 방식으로 진행이 되고, 만약 A 선수가 B 선수보다 실력이 좋다면 A 선수는 B 선수를 항상 이깁니다. 심판은 주어진 경기 결과를 가지고 선수들의 순위를 매기려 합니다. 하지만 몇몇 경기 결과를 분실하여 정확하게 순위를 매길 수 없습니다.

선수의 수 n, 경기 결과를 담은 2차원 배열 results가 매개변수로 주어질 때 정확하게 순위를 매길 수 있는 선수의 수를 return 하도록 solution 함수를 작성해주세요.

제한사항
선수의 수는 1명 이상 100명 이하입니다.
경기 결과는 1개 이상 4,500개 이하입니다.
results 배열 각 행 [A, B]는 A 선수가 B 선수를 이겼다는 의미입니다.
모든 경기 결과에는 모순이 없습니다.
입출력 예
n	results	return
5	[[4, 3], [4, 2], [3, 2], [1, 2], [2, 5]]	2
입출력 예 설명
2번 선수는 [1, 3, 4] 선수에게 패배했고 5번 선수에게 승리했기 때문에 4위입니다.
5번 선수는 4위인 2번 선수에게 패배했기 때문에 5위입니다.
*/

// 자신을 제외 한 이긴 사람 + 진 사람 합이 전체 -1과 같아야 순위를 메길수있음
// 선수 별 이긴 & 진 그래프 생성 후 이긴 * 진 사람 카운트 후 n - 1로 비교
public class Rank {
	public static void main(String[] args) {

		int n = 5;
		int[][] r = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};

		System.out.print(solution(n, r));
	}

	private static int solution(int n, int[][] r) {

		List<HashSet<Integer>> loseGraph = new ArrayList<>(n + 1);
		List<HashSet<Integer>> winGraph = new ArrayList<>(n + 1);

		for (int i = 0; i <= n; i++) {
			loseGraph.add(new HashSet<>());
			winGraph.add(new HashSet<>());
		}

		for (int[] ints : r) {
			loseGraph.get(ints[1]).add(ints[0]);
			winGraph.get(ints[0]).add(ints[1]);
		}

		int count = 0;

		for (int i = 1; i <= n; i++) {
			if (bfs(loseGraph, i, n) + bfs(winGraph, i, n) == n - 1) count++;
		}
		return count;
	}

	private static int bfs(List<HashSet<Integer>> graph, int i, int n) {

		int count = 0;

		if (graph.get(i).isEmpty()) {
			return 0;
		}

		boolean[] visited = new boolean[n + 1];
		visited[i] = true;
		Deque<Integer> queue = new ArrayDeque<>();

		queue.offer(i);

		while (!queue.isEmpty()) {

			int curPlayer = queue.poll();

			for (Integer integer : graph.get(curPlayer)) {
				if (!visited[integer]) {
					visited[integer] = true;
					queue.offer(integer);
					count++;
				}
			}
		}
		return count;
	}
}
