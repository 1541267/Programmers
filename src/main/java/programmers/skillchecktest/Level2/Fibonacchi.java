package programmers.skillchecktest.Level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Fibonacchi {

	static int count = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		System.out.println(solution(n));
	}

	static int solution(int n) {
		int answer = 0;

		if (n == 0)
			return 0;
		if (n == 1)
			return 1;

		int prevNum = 0, currentNum = 1, next = 0;

		for (int i = 2; i <= n; i++) {
			next = (prevNum + currentNum) % 1234567;
			prevNum = currentNum;
			currentNum = next;
		}
		return next;
	}
}
