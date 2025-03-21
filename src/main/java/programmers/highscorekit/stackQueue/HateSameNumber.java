package programmers.highscorekit.stackQueue;

// 배열 arr가 주어집니다. 배열 arr의 각 원소는 숫자 0부터 9까지로 이루어져 있습니다. 이때, 배열 arr에서 연속적으로 나타나는 숫자는 하나만 남기고 전부 제거하려고 합니다. 단, 제거된 후 남은 수들을 반환할 때는 배열 arr의 원소들의 순서를 유지해야 합니다. 예를 들면,
//
// arr = [1, 1, 3, 3, 0, 1, 1] 이면 [1, 3, 0, 1] 을 return 합니다.
// arr = [4, 4, 4, 3, 3] 이면 [4, 3] 을 return 합니다.
// 배열 arr에서 연속적으로 나타나는 숫자는 제거하고 남은 수들을 return 하는 solution 함수를 완성해 주세요.
//
// 제한사항
// 배열 arr의 크기 : 1,000,000 이하의 자연수
// 배열 arr의 원소의 크기 : 0보다 크거나 같고 9보다 작거나 같은 정수
// 입출력 예
// arr	answer
// [1,1,3,3,0,1,1]	[1,3,0,1]
// [4,4,4,3,3]	[4,3]
// 효율성  테스트
// 테스트 1 〉	통과 (21.09ms, 110MB)
// 테스트 2 〉	통과 (21.42ms, 111MB)
// 테스트 3 〉	통과 (21.28ms, 110MB)
// 테스트 4 〉	통과 (20.75ms, 110MB)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class HateSameNumber {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] numbers = new int[st.countTokens()];
		int i = 0;
		while(st.hasMoreTokens()) {
			numbers[i] = Integer.parseInt(st.nextToken());
			i++;
		}

		System.out.println(solution(numbers));
	}

	public static Stack<Integer> solution(int[] numbers) {

		Stack<Integer> stack = new Stack<>();
			stack.push(numbers[0]);

		for(int i = 1; i < numbers.length; i++) {
			if(numbers[i] != numbers[i-1]) {
				stack.push(numbers[i]);
			}
		}

		return stack;
	}
}
