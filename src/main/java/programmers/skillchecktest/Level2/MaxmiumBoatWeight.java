package programmers.skillchecktest.Level2;

import java.util.Arrays;

public class MaxmiumBoatWeight {
	public static void main(String[] args) {

		int[] people = new int[] {70, 50, 80, 50};
		int limit = 100;

		System.out.println(solution(people, limit));

	}

	private static int solution(int[] people, int limit) {

		Arrays.sort(people);

		int left = 0, right = people.length - 1, count = 0;

		while (left <= right) {
			if(people[left] + people[right] <= limit) {
				left++;
			}
			right--;
			count++;
		}

		return count;
	}
}
