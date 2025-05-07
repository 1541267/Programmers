package programmers.highscorekit.bruteForce;

/*
코딩테스트 연습 / 완전 탐색 / 소수 찾기
문제 설명
	한자리 숫자가 적힌 종이 조각이 흩어져있습니다. 흩어진 종이 조각을 붙여 소수를 몇 개 만들 수 있는지 알아내려 합니다.

	각 종이 조각에 적힌 숫자가 적힌 문자열 numbers가 주어졌을 때, 종이 조각으로 만들 수 있는 소수가 몇 개인지 return 하도록 solution 함수를 완성해주세요.

	제한사항
	numbers는 길이 1 이상 7 이하인 문자열입니다.
	numbers는 0~9까지 숫자만으로 이루어져 있습니다.
	"013"은 0, 1, 3 숫자가 적힌 종이 조각이 흩어져있다는 의미입니다.
	입출력 예
	numbers	return
	"17"	3
	"011"	2
	입출력 예 설명
	예제 #1
	[1, 7]으로는 소수 [7, 17, 71]를 만들 수 있습니다.

	예제 #2
	[0, 1, 1]으로는 소수 [11, 101]를 만들 수 있습니다.

	11과 011은 같은 숫자로 취급합니다.
*/


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PrimeNumber {
	public static void main(String[] args) {

		String number = "011";

		System.out.println("solution(number) = " + solution(number));
	}

	
	// 평균 60ms? 더 느림
	static Set<Integer> set = new HashSet<>();
	static boolean[] usedNumber;
	static boolean[] isPrime = new boolean[10_000_000];
	static int count = 0;
	
	private static int solution(String n) {

		usedNumber = new boolean[n.length()];
		isPrime();
		combination(n, "");

		for (Integer i : set) {
			if (isPrime[i]) count++;
		}

		return count;
	}

	private static void combination(String n, String curStr) {
		if (!curStr.isEmpty()) {
			set.add(Integer.parseInt(curStr));
		}
		for (int i = 0; i < n.length(); i++) {
			if (!usedNumber[i]) {
				usedNumber[i] = true;
				combination(n, curStr + n.charAt(i));
				usedNumber[i] = false;
			}
		}

	}

	private static void isPrime() {
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;

		for (int i = 2; i * i < isPrime.length; i++) {
			if (isPrime[i]) {
				for (int j = i * i; j < isPrime.length; j += i) {
					isPrime[j] = false;
				}
			}
		}

	}
}

//  처음 푼 것 평균 15~20ms?
// 	static Set<Integer> set = new HashSet<>();
// 	static boolean[] usedNumber;
// 	static int count = 0;
//
// 	private static int solution(String n) {
//
// 		usedNumber = new boolean[n.length()];
//
// 		combination(n, "");
// 		for (Integer number : set) {
// 			isPrime(number);
// 		}
//
// 		return count;
// 	}
//
// 	private static void combination(String n, String curStr) {
//
// 		if (!curStr.isEmpty()) {
// 			set.add(Integer.parseInt(curStr));
// 		}
//
// 		for (int i = 0; i < n.length(); i++) {
// 			if (!usedNumber[i]) {
// 				usedNumber[i] = true;
// 				combination(n, curStr + n.charAt(i));
// 				usedNumber[i] = false;
// 			}
// 		}
//
// 	}
//
// 	private static void isPrime(Integer n) {
//
// 		if (n <= 1) return;
// 		if (n == 2) {
// 			count++;
// 			return;
// 		}
// 		if (n % 2 == 0) return;
// 		for (int i = 3; i * i <= n; i += 2) {
// 			if (n % i == 0) return;
// 		}
// 		count++;
// 	}
// }
