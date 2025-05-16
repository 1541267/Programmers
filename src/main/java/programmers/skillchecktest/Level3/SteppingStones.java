package programmers.skillchecktest.Level3;

public class SteppingStones {
	public static void main(String[] args) {
		int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
		int n = 3;

		System.out.println(solution(stones, n));
	}

	private static int solution(int[] stones, int n) {

		int left = 1;
		int right = stones.length;

		while (left <= right) {
			int mid = (left + right) / 2;

			int zeroCount = 0;
			int maxZeroCount = 0;
			for (int stone : stones) {
				if (stone - mid < 0) {
					zeroCount++;
					maxZeroCount = Math.max(maxZeroCount, zeroCount);
				} else {
					zeroCount = 0;
				}
			}

			if (maxZeroCount < n) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		return right;
	}
}
