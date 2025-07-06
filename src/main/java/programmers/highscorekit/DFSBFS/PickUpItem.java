package programmers.highscorekit.DFSBFS;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/87694?language=java
코딩테스트 고득점 Kit / 깊이 or 너비 우선 탐색(DFS or BFS) / 아이템 줍기
* 다음과 같은 다각형 모양 지형에서 캐릭터가 아이템을 줍기 위해 이동하려 합니다.

지형은 각 변이 x축, y축과 평행한 직사각형이 겹쳐진 형태로 표현하며, 캐릭터는 이 다각형의 둘레(굵은 선)를 따라서 이동합니다.

만약 직사각형을 겹친 후 다음과 같이 중앙에 빈 공간이 생기는 경우, 다각형의 가장 바깥쪽 테두리가 캐릭터의 이동 경로가 됩니다.

단, 서로 다른 두 직사각형의 x축 좌표 또는 y축 좌표가 같은 경우는 없습니다.

즉, 위 그림처럼 서로 다른 두 직사각형이 꼭짓점에서 만나거나, 변이 겹치는 경우 등은 없습니다.

다음 그림과 같이 지형이 2개 이상으로 분리된 경우도 없습니다.

한 직사각형이 다른 직사각형 안에 완전히 포함되는 경우 또한 없습니다.

지형을 나타내는 직사각형이 담긴 2차원 배열 rectangle, 초기 캐릭터의 위치 characterX, characterY, 아이템의 위치 itemX, itemY가 solution 함수의 매개변수로 주어질 때, 캐릭터가 아이템을 줍기 위해 이동해야 하는 가장 짧은 거리를 return 하도록 solution 함수를 완성해주세요.

제한사항
rectangle의 세로(행) 길이는 1 이상 4 이하입니다.
rectangle의 원소는 각 직사각형의 [좌측 하단 x, 좌측 하단 y, 우측 상단 x, 우측 상단 y] 좌표 형태입니다.
직사각형을 나타내는 모든 좌표값은 1 이상 50 이하인 자연수입니다.
서로 다른 두 직사각형의 x축 좌표, 혹은 y축 좌표가 같은 경우는 없습니다.
문제에 주어진 조건에 맞는 직사각형만 입력으로 주어집니다.
charcterX, charcterY는 1 이상 50 이하인 자연수입니다.
지형을 나타내는 다각형 테두리 위의 한 점이 주어집니다.
itemX, itemY는 1 이상 50 이하인 자연수입니다.
지형을 나타내는 다각형 테두리 위의 한 점이 주어집니다.
캐릭터와 아이템의 처음 위치가 같은 경우는 없습니다.
전체 배점의 50%는 직사각형이 1개인 경우입니다.
전체 배점의 25%는 직사각형이 2개인 경우입니다.
전체 배점의 25%는 직사각형이 3개 또는 4개인 경우입니다.
입출력 예
rectangle	characterX	characterY	itemX	itemY	result
[[1,1,7,4],[3,2,5,5],[4,3,6,9],[2,6,8,8]]	1	3	7	8	17
[[1,1,8,4],[2,2,4,9],[3,6,9,8],[6,3,7,7]]	9	7	6	1	11
[[1,1,5,7]]	1	1	4	7	9
[[2,1,7,5],[6,4,10,10]]	3	1	7	10	15
[[2,2,5,5],[1,3,6,4],[3,1,4,6]]	1	4	6	3	10
*/

// 처음에 푼 방법의 문제 -> 모든 사각형의 모든 간선을 저장, bfs를 시작 해 간선을 따라 이동 하며 거리 최솟값 체크
//
// 모든 사각형의 모든 간선을 저장 했기 때문에 간선이 다른 사각형을 통과(1번 문제) 하거나 간선이 겹치는 경우(2번 문제)를 체크하지 못하고
// bfs에서 x + 1 or y +1 로 좌표를 이동하며 체크, 기존 코드에선 실제로는 이어진 간선이 아닌 좌표에도 이동 가능(3번 문제)
//
// 단위 간선을 적용 (좌표에서 0.5씩 더해 만들어진 좌표)
// 해당 단위 간선 마다 다른 사각형의 간선에 충돌하는지 체크해 해당 간선은 추가하지 않음 -> 1, 2번을 동시 해결
// 추가된 모든 간선은 전체적인 테두리만 남은 상태로(3번 문제 해결) bfs를 시작하면 최소값을 구할 수 있음

public class PickUpItem {
	public static void main(String[] args) {

		int[][][] r = {{{1, 1, 7, 4}, {3, 2, 5, 5}, {4, 3, 6, 9}, {2, 6, 8, 8}},
			{{1, 1, 8, 4}, {2, 2, 4, 9}, {3, 6, 9, 8}, {6, 3, 7, 7}},
			{{1, 1, 5, 7}},
			{{2, 1, 7, 5}, {6, 4, 10, 10}},
			{{2, 2, 5, 5}, {1, 3, 6, 4}, {3, 1, 4, 6}}
		};

		int[] cx = {1, 9, 1, 3, 1};
		int[] cy = {3, 7, 1, 1, 4};
		int[] iX = {7, 6, 4, 7, 6};
		int[] iY = {8, 1, 7, 10, 3};

		for (int i = 0; i < r.length; i++) {
			System.out.println(solution(r[i], cx[i], cy[i], iX[i], iY[i]));
		}
	}

	private static int solution(int[][] r, int cX, int cY, int iX, int iY) {
		HashSet<String> edgeSet = new HashSet<>();
		int xMax = 0;
		int yMax = 0;

		for (int i = 0; i < r.length; i++) {

			int x1 = r[i][0];
			int x2 = r[i][2];
			int y1 = r[i][1];
			int y2 = r[i][3];

			xMax = Math.max(xMax, Math.max(x1, x2));
			yMax = Math.max(yMax, Math.max(y1, y2));

			for (int x = x1; x < x2; x++) {

				double mx = x + 0.5;

				if (isInsideEdge(r, i, mx, y1)) {
					edgeSet.add(x + "," + y1 + "," + (x + 1) + "," + y1);
					edgeSet.add((x + 1) + "," + y1 + "," + x + "," + y1);
				}
				if (isInsideEdge(r, i, mx, y2)) {
					edgeSet.add(x + "," + y2 + "," + (x + 1) + "," + y2);
					edgeSet.add((x + 1) + "," + y2 + "," + x + "," + y2);
				}

			}

			for (int y = y1; y < y2; y++) {
				double my = y + 0.5;

				if (isInsideEdge(r, i, x1, my)) {
					edgeSet.add(x1 + "," + y + "," + x1 + "," + (y + 1));
					edgeSet.add(x1 + "," + (y + 1) + "," + x1 + "," + y);
				}
				if (isInsideEdge(r, i, x2, my)) {
					edgeSet.add(x2 + "," + y + "," + x2 + "," + (y + 1));
					edgeSet.add(x2 + "," + (y + 1) + "," + x2 + "," + y);
				}
			}
		}

		return bfs(cX, cY, xMax, yMax, iX, iY, edgeSet);
	}

	private static boolean isInsideEdge(int[][] r, int idx, double mx, double my) {

		for (int j = 0; j < r.length; j++) {
			if (j == idx) continue;
			if (r[j][0] < mx && mx < r[j][2] && r[j][1] < my && my < r[j][3]) return false;
		}
		return true;
	}

	private static int bfs(int cX, int cY, int xMax, int yMax, int iX, int iY, HashSet<String> edgeSet) {

		int[] dx = {1, 0, -1, 0};
		int[] dy = {0, 1, 0, -1};

		Deque<int[]> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[xMax + 1][yMax + 1];

		visited[cX][cY] = true;
		queue.add(new int[] {cX, cY, 0});

		while (!queue.isEmpty()) {

			int[] curCoordinate = queue.poll();

			int x = curCoordinate[0];
			int y = curCoordinate[1];
			int depth = curCoordinate[2];

			if (x == iX && y == iY) {
				return depth;
			}

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				String key = x + "," + y + "," + nx + "," + ny;

				if (nx >= 0 && nx <= xMax && ny >= 0 && ny <= yMax) {
					if (!visited[nx][ny] && edgeSet.contains(key)) {
						visited[nx][ny] = true;
						queue.add(new int[] {nx, ny, depth + 1});
					}
				}
			}
		}
		return -1;
	}
}

// 통과 전 코드
// private static int solution(int[][] r, int cX, int cY, int iX, int iY) {
// 	System.out.println("==========================================================");
// 	HashSet<String> edgeSet = new HashSet<>();
// 	int xMax = 0;
// 	int yMax = 0;
//
// 	System.out.println(
// 		"r = " + Arrays.deepToString(r) + ", cX = " + cX + ", cY = " + cY + ", iX = " + iX + ", iY = " + iY);
//
// 	for (int[] ints : r) {
//
// 		int x1 = ints[0];
// 		int x2 = ints[2];
// 		int y1 = ints[1];
// 		int y2 = ints[3];
//
// 		xMax = Math.max(xMax, Math.max(x1, x2));
// 		yMax = Math.max(yMax, Math.max(y1, y2));
//
// 		for (int x = x1; x < x2; x++) {
// 			edgeSet.add(x + "," + y1 + "," + (x + 1) + "," + y1);
// 			edgeSet.add(x + "," + y2 + "," + (x + 1) + "," + y2);
// 		}
//
// 		for (int y = y1; y < y2; y++) {
// 			edgeSet.add(x1 + "," + y + "," + x1 + "," + (y + 1));
// 			edgeSet.add(x2 + "," + y + "," + x2 + "," + (y + 1));
// 		}
//
// 	}
//
// 	return bfs(r, cX, cY, xMax, yMax, iX, iY, edgeSet);
// }
//
// private static int bfs(int[][] r, int cX, int cY, int xMax, int yMax, int iX, int iY, HashSet<String> edgeSet) {
//
// 	int[] dx = {1, 0, -1, 0};
// 	int[] dy = {0, 1, 0, -1};
// 	System.out.println("edgeSet = " + edgeSet);
// 	Deque<int[]> queue = new ArrayDeque<>();
// 	boolean[][] visited = new boolean[xMax + 1][yMax + 1];
//
// 	visited[cX][cY] = true;
// 	queue.add(new int[] {cX, cY, 0});
//
// 	while (!queue.isEmpty()) {
//
// 		int[] curEdge = queue.poll();
//
// 		int x = curEdge[0];
// 		int y = curEdge[1];
// 		int depth = curEdge[2];
//
// 		if (x == iX && y == iY) {
// 			return depth;
// 		}
//
// 		for (int i = 0; i < 4; i++) {
// 			int nx = x + dx[i];
// 			int ny = y + dy[i];
// 			String key = nx + "," + ny;
//
// 			if (nx >= 0 && nx <= xMax && ny >= 0 && ny <= yMax && !visited[nx][ny] && edgeSet.contains(key)) {
// 				visited[nx][ny] = true;
// 				queue.add(new int[] {nx, ny, depth + 1});
// 			}
// 		}
//
// 	}
// 	return -1;
// }
//
