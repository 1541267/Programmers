package programmers.highscorekit.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/49189?language=java
코딩테스트 고득점 Kit  / 그래프 / 가장 먼 노드
도움말
문제 설명
n개의 노드가 있는 그래프가 있습니다. 각 노드는 1부터 n까지 번호가 적혀있습니다. 1번 노드에서 가장 멀리 떨어진 노드의 갯수를 구하려고 합니다. 가장 멀리 떨어진 노드란 최단경로로 이동했을 때 간선의 개수가 가장 많은 노드들을 의미합니다.

노드의 개수 n, 간선에 대한 정보가 담긴 2차원 배열 vertex가 매개변수로 주어질 때, 1번 노드로부터 가장 멀리 떨어진 노드가 몇 개인지를 return 하도록 solution 함수를 작성해주세요.

제한사항
노드의 개수 n은 2 이상 20,000 이하입니다.
간선은 양방향이며 총 1개 이상 50,000개 이하의 간선이 있습니다.
vertex 배열 각 행 [a, b]는 a번 노드와 b번 노드 사이에 간선이 있다는 의미입니다.
입출력 예
n	vertex	return
6	[[3, 6], [4, 3], [3, 2], [1, 3], [1, 2], [2, 4], [5, 2]]	3
입출력 예 설명
*/

public class FarthestNode {
	public static void main(String[] args) {

		int n = 6;
		int[][] e = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};

		System.out.println(solution(n, e));
	}

	// 기존 raw 타입 배열 (list[])에서 제네릭 리스트(list<list<>>)로 변경
	// Node 클래스 사용 -> int[] 로 변경
	// 전역 변수를 내부 지역 변수로
	// 큐 사용 시 LinkedList 대신 ArrayDeque 사용 -> 내부 배열 기반이라 삽입&삭제가 빠르고 GC 부담도 줄어듦
	private static int solution(int n, int[][] e) {

		List<List<Integer>> graph = new ArrayList<>(n + 1);

		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}

		for (int[] arr : e) {
			graph.get(arr[0]).add(arr[1]);
			graph.get(arr[1]).add(arr[0]);
		}

		return bfs(graph, n);

	}

	private static int bfs(List<List<Integer>> graph, int n) {

		boolean[] visited = new boolean[n + 1];

		visited[1] = true;
		int maxDistance = 0;
		int count = 0;

		Deque<int[]> queue = new ArrayDeque<>();

		queue.offer(new int[] {1, 0});

		while (!queue.isEmpty()) {

			int[] node = queue.poll();

			int curNode = node[0];
			int curDepth = node[1];

			if (curDepth > maxDistance) {
				maxDistance = curDepth;
				count = 1;
			} else if (curDepth == maxDistance) {
				count++;
			}

			for (Integer i : graph.get(curNode)) {
				if (!visited[i]) {
					queue.offer(new int[] {i, curDepth + 1});
					visited[i] = true;
				}
			}
		}
		return count;
	}
}

// 처음 푼 것
// 	private static int solution(int n, int[][] e) {
//
// 		List<Integer>[] graph = new ArrayList[n + 1];
//
// 		for (int i = 0; i < graph.length; i++) {
// 			graph[i] = new ArrayList<>();
// 		}
//
// 		for (int[] arr : e) {
// 			graph[arr[0]].add(arr[1]);
// 			graph[arr[1]].add(arr[0]);
// 		}
//
// 		int depth = 0;
// 		visited = new boolean[n + 1];
// 		bfs(graph);
//
// 		return count;
// 	}
//
// 	static List<Integer> result = new ArrayList<>();
// 	static boolean[] visited;
// 	static Queue<Node> queue = new LinkedList<>();
// 	static int maxDistance = 0, count = 0;
//
// 	private static void bfs(List<Integer>[] graph) {
//
// 		visited[1] = true;
//
// 		queue.offer(new Node(1, 0));
//
// 		while (!queue.isEmpty()) {
//
// 			Node node = queue.poll();
//
// 			int curDepth = node.depth;
// 			int curNode = node.node;
//
// 			if (curDepth > maxDistance) {
// 				maxDistance = curDepth;
// 				count = 1;
// 			} else if (curDepth == maxDistance) {
// 				count++;
// 			}
//
// 			for (Integer i : graph[curNode]) {
// 				if (!visited[i]) {
// 					queue.offer(new Node(i, curDepth + 1));
// 					visited[i] = true;
// 				}
// 			}
// 		}
// 	}
//
// 	static class Node {
// 		int node, depth;
//
// 		public Node(int node, int depth) {
// 			this.node = node;
// 			this.depth = depth;
// 		}
// 	}
// }