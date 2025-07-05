package programmers.highscorekit.DFSBFS;

import java.util.HashMap;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/43165?language=java
코딩테스트 고득점 Kit / 깊이 or 너비 우선 탐색(DFS or BFS) / 타겟 넘버
n개의 음이 아닌 정수들이 있습니다. 이 정수들을 순서를 바꾸지 않고 적절히 더하거나 빼서 타겟 넘버를 만들려고 합니다. 예를 들어 [1, 1, 1, 1, 1]로 숫자 3을 만들려면 다음 다섯 방법을 쓸 수 있습니다.

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3
사용할 수 있는 숫자가 담긴 배열 numbers, 타겟 넘버 target이 매개변수로 주어질 때 숫자를 적절히 더하고 빼서 타겟 넘버를 만드는 방법의 수를 return 하도록 solution 함수를 작성해주세요.

제한사항
주어지는 숫자의 개수는 2개 이상 20개 이하입니다.
각 숫자는 1 이상 50 이하인 자연수입니다.
타겟 넘버는 1 이상 1000 이하인 자연수입니다.
입출력 예
numbers	target	return
[1, 1, 1, 1, 1]	3	5
[4, 1, 2, 1]	4	2
입출력 예 설명
입출력 예 #1

문제 예시와 같습니다.

입출력 예 #2

+4+1-2+1 = 4
+4-1+2-1 = 4
총 2가지 방법이 있으므로, 2를 return 합니다.
*/

// dfs1 = 백트래킹 + dfs
// dfs2 = 메모이제이션 + dfs(재귀적 dp)
// 중복 되는 호출이 많을 경우 재귀적dp가 이득, 적을 경우 순수 dfs가 더 가벼움

public class TargetNumber {
	public static void main(String[] args) {

		int[][] n = {{1, 1, 1, 1, 1}, {4, 1, 2, 1}};

		int[] t = {3, 4};

		for (int i = 0; i < n.length; i++) {
			System.out.println("==========================================================");
			System.out.println(solution(n[i], t[i]));
		}
	}

	static int count;

	private static int solution(int[] n, int t) {
		count = 0;
		dfs1(n, t, n[0], 1);
		dfs1(n, t, -n[0], 1);
		System.out.println("dfs1 = " + count);

		HashMap<String, Integer> map = new HashMap<>();
		count = dfs2(n, t, map, 0, 0);
		System.out.println("dfs2 = " + count);

		return count;
	}

	private static void dfs1(int[] n, int t, int curNum, int depth) {
		if (depth == n.length) {
			if (curNum == t) count++;
			return;
		}

		dfs1(n, t, curNum + n[depth], depth + 1);
		dfs1(n, t, curNum - n[depth], depth + 1);
	}

	private static int dfs2(int[] n, int t, HashMap<String, Integer> memo, int sum, int idx) {
		String key = idx + "&" + sum;

		if (idx == n.length) {
			return sum == t ? 1 : 0;
		}

		if (memo.containsKey(key)) {
			return memo.get(key);
		}

		int result =
			dfs2(n, t, memo, sum + n[idx], idx + 1) + dfs2(n, t, memo, sum - n[idx], idx + 1);

		memo.put(key, result);

		return result;
	}

}