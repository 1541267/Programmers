package programmers.leveltest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 문제 설명
// 메리는 여름을 맞아 무인도로 여행을 가기 위해 지도를 보고 있습니다. 지도에는 바다와 무인도들에 대한 정보가 표시돼 있습니다. 지도는 1 x 1크기의 사각형들로 이루어진 직사각형 격자 형태이며, 격자의 각 칸에는 'X' 또는 1에서 9 사이의 자연수가 적혀있습니다. 지도의 'X'는 바다를 나타내며, 숫자는 무인도를 나타냅니다. 이때, 상, 하, 좌, 우로 연결되는 땅들은 하나의 무인도를 이룹니다. 지도의 각 칸에 적힌 숫자는 식량을 나타내는데, 상, 하, 좌, 우로 연결되는 칸에 적힌 숫자를 모두 합한 값은 해당 무인도에서 최대 며칠동안 머물 수 있는지를 나타냅니다. 어떤 섬으로 놀러 갈지 못 정한 메리는 우선 각 섬에서 최대 며칠씩 머물 수 있는지 알아본 후 놀러갈 섬을 결정하려 합니다.
//
// 지도를 나타내는 문자열 배열 maps가 매개변수로 주어질 때, 각 섬에서 최대 며칠씩 머무를 수 있는지 배열에 오름차순으로 담아 return 하는 solution 함수를 완성해주세요. 만약 지낼 수 있는 무인도가 없다면 -1을 배열에 담아 return 해주세요.
//
// 제한사항
// 3 ≤ maps 의 길이 ≤ 100
// 3 ≤ maps[i]의 길이 ≤ 100
// maps[i]는 'X' 또는 1 과 9 사이의 자연수로 이루어진 문자열입니다.
// 지도는 직사각형 형태입니다.
// 입출력 예
// maps	result
// ["X591X","X1X5X","X231X", "1XXX1"]	[1, 1, 27]
// ["XXX","XXX","XXX"]	[-1]
// DFS or BFS 사용 문제, DFS 검색 후 풀어봄

public class desertIsland {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		List<String> map = new ArrayList<>();
		String line;

		while ((line = br.readLine()) != null && !line.isEmpty()) {
			map.add(line);
		}
		String[] maps = map.toArray(new String[0]);
		System.out.println("result: " + Arrays.toString(solution(maps)));

	}

	public static int[] solution(String[] maps) {

		// 배열로 지도 생성
		int rows = maps.length;
		int cols = maps[0].length();
		boolean[][] visitedIsland = new boolean[rows][cols];

		// 머무는 날짜 담기
		List<Integer> totalDays = new ArrayList<>();
		int sums = 0;

		// 모든 칸 순회
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (!visitedIsland[i][j] && maps[i].charAt(j) != 'X' && maps[i].charAt(j) != 'x') {
					int sum = dfs(maps, visitedIsland, i, j);
					totalDays.add(sum);
				}
			}
		}

		System.out.println("sums: " + sums);
		if (totalDays.isEmpty()) {
			return new int[] {-1};
		}

		Collections.sort(totalDays);
		return totalDays.stream().mapToInt(i -> i).toArray();
	}

	public static int dfs(String[] maps, boolean[][] visitedIsland, int x, int y) {

		// 상하 좌우 이동
		int[] dx = {0, 0, -1, 1}; // 좌우
		int[] dy = {-1, 1, 0, 0}; // 상하

		visitedIsland[x][y] = true; // 칸 방문 처리

		int rows = maps.length;
		int cols = maps[0].length();
		int sum = maps[x].charAt(y) - '0';

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];


			if (nx >= 0 && nx < rows && ny >= 0 && ny < cols && !visitedIsland[nx][ny] && maps[nx].charAt(ny) != 'X' && maps[nx].charAt(ny) != 'x') {

				sum += dfs(maps, visitedIsland, nx, ny);
				System.out.println("nx, xy: " + "(" + nx + "," + ny + ")");

			}
		}

		return sum;
	}
}

//  내가 처음에 접근 한 것
// 	public static int[] solution(String[] maps) {
// 	boolean exist = false;
//
// 		int sum = 0;
// 		int sum1 = 0;
// 		int[] total;
//
// 		for (int i = 0; i < maps.length; i++) {
//
// 			System.out.println(maps[i]);
//
// 			if (i == 0) {
// 				for (int j = 0; j < maps[i].length(); j++) {
// 					if (maps[i].charAt(j) != 'x' && maps[i].charAt(j) != 'X') {
//
// 						sum += (maps[i].charAt(j) - '0');
// 					}
// 				}
// 			} else {
// 				for (int j = 0; j < maps[i].length(); j++) {
// 					if (maps[i].charAt(j) != 'x' && maps[i].charAt(j) != 'X') {
// 						System.out.println("maps i : " + i + " j : " + j + " char: " + maps[i].toCharArray()[j]);
//
// 						if (maps[i - 1].charAt(j) -'0' > 0 || maps[i].charAt(j + 1) -'0' > 0 || maps[i+1].charAt(j) -'0' > 0) {
// 							// sum.add(maps[i].charAt(j) - '0');
// 							sum += (maps[i].charAt(j) - '0');
// 						} else {
// 							sum1 += (maps[i].charAt(j) - '0');
// 						}
// 					}
// 				}
// 			}
// 		}
//
// 		System.out.println("sum: " + sum);
// 		System.out.println("sum1: " + sum1);
// 		if (sum == 0) {
// 			return new int[] {-1};
// 		}
// 		return null;
// 	}
// }