package programmers.highscorekit.bruteForce;

import java.util.Arrays;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/42842?language=java
코딩테스트 고득점 Kit  / 완전탐색 / 카펫
Leo는 카펫을 사러 갔다가 아래 그림과 같이 중앙에는 노란색으로 칠해져 있고 테두리 1줄은 갈색으로 칠해져 있는 격자 모양 카펫을 봤습니다.

Leo는 집으로 돌아와서 아까 본 카펫의 노란색과 갈색으로 색칠된 격자의 개수는 기억했지만, 전체 카펫의 크기는 기억하지 못했습니다.

Leo가 본 카펫에서 갈색 격자의 수 brown, 노란색 격자의 수 yellow가 매개변수로 주어질 때 카펫의 가로, 세로 크기를 순서대로 배열에 담아 return 하도록 solution 함수를 작성해주세요.

제한사항
갈색 격자의 수 brown은 8 이상 5,000 이하인 자연수입니다.
노란색 격자의 수 yellow는 1 이상 2,000,000 이하인 자연수입니다.
카펫의 가로 길이는 세로 길이와 같거나, 세로 길이보다 깁니다.
입출력 예
brown	yellow	return
10	2	[4, 3]
8	1	[3, 3]
24	24	[8, 6]
*/

// 갈색 = 테두리, 노란색 = 내부
// 노란색 = (가로 - 2) & (세로 - 2) -> 노란색 테두리를 한 칸씩 뺸 것
// 전체 테두리는 위와 반대로 가로 + 2, 세로 + 2
// 따라서 (가로 + 2)(세로 + 2) = 갈 + 노

public class Carpet {
	public static void main(String[] args) {

		int[] brown = {10, 8, 24};
		int[] yellow = {2, 1, 24};

		for (int i = 0; i < 3; i++) {
			System.out.println(Arrays.toString(solution(brown[i], yellow[i])));
		}
	}

	private static int[] solution(int b, int y) {
		for (int first = 1; first <= Math.sqrt(y); first++) {

			if (y % first != 0) {
				continue;
			}

			int divisor = y / first;

			int a = (first + 2) * (divisor + 2);

			if (a == b + y) {
				first += 2;
				divisor += 2;

				return first >= divisor ? new int[] {first, divisor} : new int[] {divisor, first};
			}
		}
		return null;
	}
}
