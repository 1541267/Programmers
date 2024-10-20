package programmers.highscorekit.sort;

// 문제 설명
// 가장 큰 수
// 0 또는 양의 정수가 주어졌을 때, 정수를 이어 붙여 만들 수 있는 가장 큰 수를 알아내 주세요.
//
// 예를 들어, 주어진 정수가 [6, 10, 2]라면 [6102, 6210, 1062, 1026, 2610, 2106]를 만들 수 있고, 이중 가장 큰 수는 6210입니다.
//
// 0 또는 양의 정수가 담긴 배열 numbers가 매개변수로 주어질 때, 순서를 재배치하여 만들 수 있는 가장 큰 수를 문자열로 바꾸어 return 하도록 solution 함수를 작성해주세요.
//
// 제한 사항
// numbers의 길이는 1 이상 100,000 이하입니다.
// numbers의 원소는 0 이상 1,000 이하입니다.
// 정답이 너무 클 수 있으니 문자열로 바꾸어 return 합니다.
// 입출력 예
// numbers	return
// [6, 10, 2]	"6210"
// [3, 30, 34, 5, 9]	"9534330"


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class maxNum {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] numbers = new int[st.countTokens()];

		while(st.hasMoreTokens()) {
			for(int i = 0; st.hasMoreTokens(); i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(solution(numbers));
	}

	public static String solution(int[] numbers) {

		String[] numStr = new String[numbers.length];

		for (int i = 0; i < numbers.length; i++) {
			numStr[i] = String.valueOf(numbers[i]);
		}
		Arrays.sort(numStr, (a, b) -> (b + a).compareTo(a + b));

		StringBuilder answer = new StringBuilder();

		for (String s : numStr) {
			answer.append(s);
		}

		if (answer.toString().charAt(0) == 48) {
			return "0";
		}

		return answer.toString();
	}
}

