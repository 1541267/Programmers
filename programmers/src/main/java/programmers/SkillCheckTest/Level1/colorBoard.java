package programmers.SkillCheckTest.Level1;

import java.util.Objects;

// 시간 부족

public class colorBoard {
	public static void main(String[] args) {

		String[][] board = new String[][] {{"blue", "red", "orange", "red"},
			{"red", "red", "blue", "orange"}, {"blue", "orange", "red", "red"},
			{"orange", "orange", "red", "blue"}};
		int n;

		int h = 1;
		int w = 1;

		solution(board, h, w);
	}

	public static int solution(String[][] board, int h, int w) {

		String selectedColor = board[h][w];

		boolean[][] checked = new boolean[board.length][board[0].length];
		return dfs(board, checked, h, w);


	}

	public static int dfs(String[][] board, boolean[][] checked, int h, int w) {
		int count = 0;

		int[] dh = {0, 1, -1, 0};
		int[] dw = {1, 0, 0, -1};

		// 체크 된 보드 지도
		int rows = board.length;
		int cols = board[0].length;

		for (int i = 0; i < rows; i++) {

			int nh = h * dh[i];
			int nw = w * dw[i];



			for (int j = 0; j < cols; j++) {
				if (nh >= 0 && nw >= 0 && Objects.equals(board[h][w], board[nh][nw])) {
					if(!checked[nh][nw]) {

						count++;
					}
					checked[nh][nw] = true;
				}
			}
		}
		return count;
	}
}

