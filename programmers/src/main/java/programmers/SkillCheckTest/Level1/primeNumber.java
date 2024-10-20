package programmers.SkillCheckTest.Level1;

// 주어진 숫자 중 3개의 수를 더했을 때 소수가 되는 경우의 개수를 구하려고 합니다. 숫자들이 들어있는 배열 nums가 매개변수로 주어질 때, nums에 있는 숫자들 중 서로 다른 3개를 골라 더했을 때 소수가 되는 경우의 개수를 return 하도록 solution 함수를 완성해주세요.
//
// 제한사항
// nums에 들어있는 숫자의 개수는 3개 이상 50개 이하입니다.
// nums의 각 원소는 1 이상 1,000 이하의 자연수이며, 중복된 숫자가 들어있지 않습니다.
// 입출력 예
// nums	result
// [1,2,3,4]	1
// [1,2,7,6,4]	4
// 입출력 예 설명
// 입출력 예 #1
// [1,2,4]를 이용해서 7을 만들 수 있습니다.
//
// 입출력 예 #2
// [1,2,4]를 이용해서 7을 만들 수 있습니다.
// [1,4,6]을 이용해서 11을 만들 수 있습니다.
// [2,4,7]을 이용해서 13을 만들 수 있습니다.
// [4,6,7]을 이용해서 17을 만들 수 있습니다.

public class primeNumber {
	public static void main(String[] args) {
		// int[] nums = new int[] {1, 2, 3, 4};
			int[] nums = new int[] {1, 2, 7, 6, 4};

		System.out.println(solution(nums));
	}

	public static int solution(int[] nums) {

		int count = 0;

		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				for (int k = j + 1; k < nums.length; k++) {
					int sum = nums[i] + nums[j] + nums[k];

					if (isPrime(sum)) {
						count++;
					}
				}
			}
		}

		return count;
	}

	public static boolean isPrime(int num) {
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

