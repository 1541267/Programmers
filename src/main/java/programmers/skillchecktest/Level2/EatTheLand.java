package programmers.skillchecktest.Level2;

// 땅따먹기 게임을 하려고 합니다. 땅따먹기 게임의 땅(land)은 총 N행 4열로 이루어져 있고, 모든 칸에는 점수가 쓰여 있습니다. 1행부터 땅을 밟으며 한 행씩 내려올 때, 각 행의 4칸 중 한 칸만 밟으면서 내려와야 합니다. 단, 땅따먹기 게임에는 한 행씩 내려올 때, 같은 열을 연속해서 밟을 수 없는 특수 규칙이 있습니다.
//
// 예를 들면,
//
// | 1 | 2 | 3 | 5 |
//
// | 5 | 6 | 7 | 8 |
//
// | 4 | 3 | 2 | 1 |
//
// 로 땅이 주어졌다면, 1행에서 네번째 칸 (5)를 밟았으면, 2행의 네번째 칸 (8)은 밟을 수 없습니다.
//
// 마지막 행까지 모두 내려왔을 때, 얻을 수 있는 점수의 최대값을 return하는 solution 함수를 완성해 주세요. 위 예의 경우, 1행의 네번째 칸 (5), 2행의 세번째 칸 (7), 3행의 첫번째 칸 (4) 땅을 밟아 16점이 최고점이 되므로 16을 return 하면 됩니다.
//
// 제한사항
// 행의 개수 N : 100,000 이하의 자연수
// 열의 개수는 4개이고, 땅(land)은 2차원 배열로 주어집니다.
// 점수 : 100 이하의 자연수
// 입출력 예
// land	answer
// [[1,2,3,5],[5,6,7,8],[4,3,2,1]]	16
// TODO 아직 못함

import java.io.IOException;
import java.util.Arrays;

public class EatTheLand {
	public static void main(String[] args) throws IOException {

		int[][] a = {{1, 2, 3, 5}, {5, 6, 7, 8}, {4, 3, 2, 1}};

		System.out.println("a = " + Arrays.deepToString(a));

		System.out.println(solution(a));
	}

	private static int solution(int[][] inputArray) {

		int totalSum = 0;
		int n = inputArray.length;
		int[][] checkArray = new int[n][4];

		for (int i = 0; i < 4; i++) {
			 checkArray[0][i] = inputArray[0][i];
		}

		System.out.println("checkArray = " + Arrays.deepToString(checkArray));
		
		for (int i = 1; i < n; i++) {

			for (int j = 0; j < 4; j++) {
				int maxPrevNum = 0;
				for (int k = 0; k < 4; k++) {
					if(i != j) {
						maxPrevNum = Math.max(maxPrevNum, checkArray[i-1][j]);
					}
				}
				checkArray[i][j] = inputArray[i][j] + maxPrevNum;
				System.out.println("maxPrevNum = " + maxPrevNum);
			}

		}

		for (int j = 0; j < 4; j++) {
			totalSum = Math.max(totalSum, checkArray[n - 1][j]);
		}

		return totalSum;
	}
}
