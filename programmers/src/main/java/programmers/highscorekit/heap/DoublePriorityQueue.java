package programmers.highscorekit.heap;

// 이중 우선순의 큐
//  이중 우선순위 큐는 다음 연산을 할 수 있는 자료구조를 말합니다.
// 
// 명령어	수신 탑(높이)
// I 숫자	큐에 주어진 숫자를 삽입합니다.
// D 1	큐에서 최댓값을 삭제합니다.
// D -1	큐에서 최솟값을 삭제합니다.
// 이중 우선순위 큐가 할 연산 operations가 매개변수로 주어질 때, 모든 연산을 처리한 후 큐가 비어있으면 [0,0] 비어있지 않으면 [최댓값, 최솟값]을 return 하도록 solution 함수를 구현해주세요.
// 
// 제한사항
// operations는 길이가 1 이상 1,000,000 이하인 문자열 배열입니다.
// operations의 원소는 큐가 수행할 연산을 나타냅니다.
// 원소는 “명령어 데이터” 형식으로 주어집니다.- 최댓값/최솟값을 삭제하는 연산에서 최댓값/최솟값이 둘 이상인 경우, 하나만 삭제합니다.
// 빈 큐에 데이터를 삭제하라는 연산이 주어질 경우, 해당 연산은 무시합니다.
// 입출력 예
// operations	return
// ["I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"]	[0,0]
// ["I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"]	[333, -45]
// 입출력 예 설명
// 입출력 예 #1
// 
// 16과 -5643을 삽입합니다.
// 최솟값을 삭제합니다. -5643이 삭제되고 16이 남아있습니다.
// 최댓값을 삭제합니다. 16이 삭제되고 이중 우선순위 큐는 비어있습니다.
// 우선순위 큐가 비어있으므로 최댓값 삭제 연산이 무시됩니다.
// 123을 삽입합니다.
// 최솟값을 삭제합니다. 123이 삭제되고 이중 우선순위 큐는 비어있습니다.
// 따라서 [0, 0]을 반환합니다.
// 
// 입출력 예 #2
// 
// -45와 653을 삽입후 최댓값(653)을 삭제합니다. -45가 남아있습니다.
// -642, 45, 97을 삽입 후 최댓값(97), 최솟값(-642)을 삭제합니다. -45와 45가 남아있습니다.
// 333을 삽입합니다.
// 이중 우선순위 큐에 -45, 45, 333이 남아있으므로, [333, -45]를 반환합니다.

import java.io.IOException;
import java.util.Arrays;
import java.util.TreeSet;

public class DoublePriorityQueue {
	public static void main(String[] args) throws IOException {

		System.out.println(
			Arrays.toString(solution(new String[] {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"})));

		System.out.println(
			Arrays.toString(
				solution(new String[] {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"})));

	}

	private static int[] solution(String[] operations) {

		TreeSet<Integer> setBox = new TreeSet<>();

		for (String s : operations) {
			String operation = s.split(" ")[0];
			int operationNumber = Integer.parseInt(s.split(" ")[1]);
			switch (operation) {
				case "I":
					insertNumber(setBox, operationNumber);
					break;
				case "D":
					if (operationNumber < 0) {
						deleteMinNumber(setBox);
					} else {
						deleteMaxNumber(setBox);
					}
					break;
			}
		}
		if (!setBox.isEmpty()) {
			return new int[] {setBox.last(), setBox.first()};
		} else {
			return new int[] {0, 0};
		}
	}

	private static void insertNumber(TreeSet<Integer> set, int number) {

		set.add(number);

	}

	private static void deleteMinNumber(TreeSet<Integer> set) {
		if (set.isEmpty())
			return;
		set.pollFirst();
	}

	private static void deleteMaxNumber(TreeSet<Integer> set) {
		if (set.isEmpty())
			return;
		set.pollLast();
	}
}

// 테스트 코드 5, 6, 7 통과 X -> 삽입 삭제에서 정렬문제
// -> Deque에서 TreeSet으로 변경
// 
// class Solution {
//     public int[] solution(String[] operations)	{
// 		int[] result = new int[2];
// 
// 		Deque<Integer> queueBox = new ArrayDeque<>();
// 
// 		for (String s : operations) {
// 			String operation = s.split(" ")[0];
// 			int operationNumber = Integer.parseInt(s.split(" ")[1]);
// 			switch (operation) {
// 				case "I":
// 					insertNumber(queueBox, operationNumber);
// 					break;
// 				case "D":
// 					if (operationNumber < 0) {
// 						deleteMinNumber(queueBox);
// 					} else {
// 						deleteMaxNumber(queueBox);
// 					}
// 					break;
// 			}
// 		}
// 		if (!queueBox.isEmpty()) {
// 			result[0] = queueBox.peekFirst();
// 			result[1] = queueBox.peekLast();
// 		} else {
// 			return new int[] {0, 0};
// 		}
// 		return result;
// 	}
// 
// 	private static void insertNumber(Deque<Integer> queue, int number) {
// 
// 		if (queue.isEmpty()) {
// 			queue.offerFirst(number);
// 		} else {
// 			if (queue.peekFirst() < number) {
// 				queue.addFirst(number);
// 			} else {
// 				queue.offerLast(number);
// 			}
// 		}
// 
// 	}
// 
// 	private static void deleteMinNumber(Deque<Integer> queue) {
// 		if (queue.isEmpty())
// 			return;
// 		queue.removeLast();
// 	}
// 
// 	private static void deleteMaxNumber(Deque<Integer> queue) {
// 		if (queue.isEmpty())
// 			return;
// 		queue.removeFirst();
// 	}
// }
