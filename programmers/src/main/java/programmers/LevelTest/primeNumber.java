package programmers.LevelTest;
// 주어진 숫자 중 3개의 수를 더했을 때 소수가 되는 경우의 개수를 구하려고 합니다.
// 숫자들이 들어있는 배열 nums 가 매개변수로 주어질 때,
// nums 에 있는 숫자들 중 서로 다른 3개를 골라 더했을 때 소수가 되는 경우의 개수를 return 하도록 solution 함수를 완성해주세요.
//
// 제한사항
// nums 에 들어있는 숫자의 개수는 3개 이상 50개 이하입니다.
// nums 의 각 원소는 1 이상 1,000 이하의 자연수이며, 중복된 숫자가 들어있지 않습니다.

import java.util.ArrayList;
import java.util.List;

public class primeNumber {
	public static void main(String[] args) throws Exception {

		// int[] nums = new int[] {13, 100, 900};
		int[] nums = new int[] {1, 2, 8, 4, 5};
		System.out.println(solution(nums));

	}

	static int solution(int[] nums) {

		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				for (int k = j + 1; k < nums.length; k++) {
					int num = nums[i] + nums[j] + nums[k];

					if (isPrime(num)) {
						count++;
					}
				}
			}
		}
		return count;
	}

	// 불필요한 연산을 최적화
	static boolean isPrime(int num) {
		if (num <= 1)
			return false; // 1보다 작은 경우 소수가 아님
		if (num == 2)
			return true; // 2는 소수
		if (num % 2 == 0)
			return false; // 짝수는 소수가 아님

		// 2를 제외한 소수는 홀수
		for (int i = 3; i * i <= num; i += 2) {
			if (num % i == 0)
				return false; // 나눠지면 소수가 아님
		}
		return true; // 나눠지지 않으면 소수
	}
}
// 답은 맞으나 최적화가 안됨 -> 불필요한 연산이 많음
// 	static int solution(int[] nums) {
//
// 		int count = 0;
// 		for (int i = 0; i < nums.length; i++) {
// 			for (int j = i + 1; j < nums.length; j++) {
// 				for (int k = j + 1; k < nums.length; k++) {
// 					if(nums[i] + nums[j] + nums[k] % 2 != 0) {
// 						int num = nums[i] + nums[j] + nums[k];
// 						if(isPrime(num)){
// 							count++;
// 						}
// 					}
//
//
// 				}
// 			}
// 		}
// 		return count;
// 	}
//
// 	static boolean isPrime(int n) {
// 		return n % 2 != 0 && n % 3 != 1;
// 	}
// }