package programmers.leveltest;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 문제 설명
// 두 정수 X, Y의 임의의 자리에서 공통으로 나타나는 정수 k(0 ≤ k ≤ 9)들을 이용하여 만들 수 있는 가장 큰 정수를 두 수의 짝꿍이라 합니다(단, 공통으로 나타나는 정수 중 서로 짝지을 수 있는 숫자만 사용합니다). X, Y의 짝꿍이 존재하지 않으면, 짝꿍은 -1입니다. X, Y의 짝꿍이 0으로만 구성되어 있다면, 짝꿍은 0입니다.
//
// 예를 들어, X = 3403이고 Y = 13203이라면, X와 Y의 짝꿍은 X와 Y에서 공통으로 나타나는 3, 0, 3으로 만들 수 있는 가장 큰 정수인 330입니다. 다른 예시로 X = 5525이고 Y = 1255이면 X와 Y의 짝꿍은 X와 Y에서 공통으로 나타나는 2, 5, 5로 만들 수 있는 가장 큰 정수인 552입니다(X에는 5가 3개, Y에는 5가 2개 나타나므로 남는 5 한 개는 짝 지을 수 없습니다.)
// 두 정수 X, Y가 주어졌을 때, X, Y의 짝꿍을 return 하는 solution 함수를 완성해주세요.
//
// 제한사항
// 3 ≤ X, Y의 길이(자릿수) ≤ 3,000,000입니다.
// X, Y는 0으로 시작하지 않습니다.
// X, Y의 짝꿍은 상당히 큰 정수일 수 있으므로, 문자열로 반환합니다.
// 입출력 예
// X	Y	result
// "100"	"2345"	"-1"
// "100"	"203045"	"0"
// "100"	"123450"	"10"
// "12321"	"42531"	"321"
// "5525"	"1255"	"552"

class NumberMate {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println(solution(br.readLine(), br.readLine()));
	}

	public static String solution(String X, String Y) {

		int[] countX = new int[10];
		int[] countY = new int[10];

		for (char c : X.toCharArray()) {
			countX[c - '0']++;
		}

		for (char c : Y.toCharArray()) {
			countY[c - '0']++;
		}

		StringBuilder result = new StringBuilder();

		for (int i = 9; i >= 0; i--) {
			int commonCount = Math.min(countX[i], countY[i]);
			for (int j = 0; j < commonCount; j++) {
				result.append(i);
			}
		}

		if (result.isEmpty()) {
			return "-1";
		}

		if (result.toString().charAt(0) == '0') {
			return "0";
		}

		return result.toString();
	}
}

// 내가 적은것, 1~4번은통과, 5번 (5521, 1255 가 실패)
//
// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.ArrayList;
// import java.util.Comparator;
// import java.util.List;
//
// class Solution {
// 	public String solution(String X, String Y) {
//
// 		List<Integer> sum = new ArrayList<>();
//
// 		int arrayCount = 0;
//
// 		for (int i = 0; i < X.length(); i++) {
//
// 			int numX = Integer.parseInt(String.valueOf(X.charAt(i)));
// 			for (int j = 0; j < Y.length(); j++) {
//
// 				int numY = Integer.parseInt(String.valueOf(Y.charAt(i)));
// 				if (X.charAt(i) == Y.charAt(j)) {
// 					if (!sum.contains(numX)) {
// 						sum.add(numX);
//
// 					} else {
// 						break;
// 					}
// 				}
// 			}
//
// 		}
//
// 		if (sum.isEmpty()) {
// 			System.out.println("-1");
// 			return "-1";
// 		} else {
// 			sum.sort(Comparator.reverseOrder());
// 		}
//
// 		System.out.println("sum : " + sum);
//
// 		StringBuilder result = new StringBuilder();
// 		for (Integer i : sum) {
// 			result.append(i);
// 		}
//
// 		System.out.println("result : " + result);
// 		return result.toString();
//
// 	}
// }