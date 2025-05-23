package programmers.skillchecktest.Level2;

// 정수 n, left, right가 주어집니다. 다음 과정을 거쳐서 1차원 배열을 만들고자 합니다.
//
// n행 n열 크기의 비어있는 2차원 배열을 만듭니다.
// i = 1, 2, 3, ..., n에 대해서, 다음 과정을 반복합니다.
// 1행 1열부터 i행 i열까지의 영역 내의 모든 빈 칸을 숫자 i로 채웁니다.
// 1행, 2행, ..., n행을 잘라내어 모두 이어붙인 새로운 1차원 배열을 만듭니다.
// 새로운 1차원 배열을 arr이라 할 때, arr[left], arr[left+1], ..., arr[right]만 남기고 나머지는 지웁니다.
// 정수 n, left, right가 매개변수로 주어집니다. 주어진 과정대로 만들어진 1차원 배열을 return 하도록 solution 함수를 완성해주세요.
//
// 제한사항
// 1 ≤ n ≤ 107
// 0 ≤ left ≤ right < n2
// right - left < 105
// 입출력 예
// n	left	right	result
// 3	2	5	[3,2,2,3]
// 4	7	14	[4,3,3,3,4,4,4,4]
// 입출력 예 설명
// 입출력 예 #1
//
// 다음 애니메이션은 주어진 과정대로 1차원 배열을 만드는 과정을 나타낸 것입니다.
// TODO 시간 초과
// 직접 2차 배열 만드는데 시간 썼으나 직접 만들지 않고 계산으로 해당 좌표의 값 구해서 1차배열 생성
// 설명보고 이해함

import java.util.Arrays;

public class MakeOneDimensionArray {
	public static void main(String[] args) {
		int n = 4;
		long left = 7;
		long right = 14;

		System.out.println(Arrays.toString(solution(n, left, right)));
	}

	static int[] solution(int n, long left, long right) {

		int[] result = new int[(int)(right - left + 1)];

		for (int i = 0; i < result.length; i++) {
			int num1 = (int)(left % n + 1);
			int num2 = (int)(left / n + 1);

			left++;
			result[i] = Math.max(num1, num2);
		}
		return result;
	}
}

// 시간초과
// public class MakeOneDimensionArray {
// 	public static void main(String[] args) {
// 		int n = 4;
// 		long left = 7;
// 		long right = 14;
//
// 		System.out.println(Arrays.toString(solution(n, left, right)));
// 	}
//
// 	static int[] solution(int n, long left, long right) {
//
// 		int[][] secondDimension = new int[n][n];
//
//
// 		int resultArrSize = (int)(right - left + 1);
//
// 		int[] result = new int[resultArrSize];
//
// 		int startNum = 1;
// 		int maxNum = n;
//
// 		for (int i = 0; i < secondDimension.length; i++) {
// 			int inputNum = startNum;
// 			for (int j = 0; j < secondDimension.length; j++) {
//
// 				secondDimension[i][j] = inputNum;
// 				if (inputNum < maxNum) {
// 					inputNum++;
// 				}
// 			}
// 			startNum++;
// 		}
//		
// 		System.out.println("secondDimension = " + Arrays.deepToString(secondDimension));
//
// 		return null;
// 	}
// }
