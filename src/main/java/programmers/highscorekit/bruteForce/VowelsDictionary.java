package programmers.highscorekit.bruteForce;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/84512?language=java
코딩테스트 고득점 Kit / 완전 탐색 / 모음사전
사전에 알파벳 모음 'A', 'E', 'I', 'O', 'U'만을 사용하여 만들 수 있는, 길이 5 이하의 모든 단어가 수록되어 있습니다. 사전에서 첫 번째 단어는 "A"이고, 그다음은 "AA"이며, 마지막 단어는 "UUUUU"입니다.

단어 하나 word가 매개변수로 주어질 때, 이 단어가 사전에서 몇 번째 단어인지 return 하도록 solution 함수를 완성해주세요.

제한사항
word의 길이는 1 이상 5 이하입니다.
word는 알파벳 대문자 'A', 'E', 'I', 'O', 'U'로만 이루어져 있습니다.
입출력 예
word	result
"AAAAE"	6
"AAAE"	10
"I"	1563
"EIO"	1189
입출력 예 설명
입출력 예 #1

사전에서 첫 번째 단어는 "A"이고, 그다음은 "AA", "AAA", "AAAA", "AAAAA", "AAAAE", ... 와 같습니다. "AAAAE"는 사전에서 6번째 단어입니다.

입출력 예 #2

"AAAE"는 "A", "AA", "AAA", "AAAA", "AAAAA", "AAAAE", "AAAAI", "AAAAO", "AAAAU"의 다음인 10번째 단어입니다.

입출력 예 #3

"I"는 1563번째 단어입니다.

입출력 예 #4

"EIO"는 1189번째 단어입니다.
*/

/*
5진법으로 계산, 자리수에 따라 추가로 가지 수 + -> ex) I 면 Ixxxx -> 뒤 번호의 개수 * I 번호
처음 푼 방법은 HashMap(조회 오버헤드 발생) + Math.pow() 사용, 개선은 배열로 사용해 해시 조회 오버헤드를 없앰
해시 조회: hashCode() 계산 -> 버킷 인덱스 계산 -> equals() 비교 -> 값 반환
-> 평균적으로 O(1)이지만 해시 충돌이 많거나 리사이징 발생하면 최악은 O(n)
-> 배열을 사용 해 바로 인덱스로 접근, 추가 연산 없이 바로 값 비교
-> pow()는 double 연산, 지수연산을 처리하는 복잡한 알고리즘으로 인해, 메서드 호출 비용 + 연산 비용이 합쳐서 무거움
*/
public class VowelsDictionary {
	public static void main(String[] args) {

		String[] words = {"AAAAE", "AAAE", "I", "EIO"};

		for (String word : words) {
			System.out.println("==========================================================");
			System.out.println("word = " + word);
			System.out.println(solution(word));
		}

	}

	private static int solution(String w) {

		char[] V = {'A', 'E', 'I', 'O', 'U'};

		int[] pow = new int[5];

		pow[4] = 1;

		for (int i = 3; i >= 0; i--) {
			pow[i] += pow[i + 1] * 5 + 1;
		}

		int result = 0;

		for (int i = 0; i < w.length(); i++) {
			result += pow[i] * new String(V).indexOf(w.charAt(i)) + 1;
		}
		return result;
	}
}

// 처음 푼 방법
// 	private static int solution(String w) {
//
// 		HashMap<Character, Integer> map = new HashMap<>();
//
// 		map.put('A', 0);
// 		map.put('E', 1);
// 		map.put('I', 2);
// 		map.put('O', 3);
// 		map.put('U', 4);
//
// 		int[] pow = new int[5];
//
// 		for (int i = 0; i < 5; i++) {
// 			for (int j = 0; j < 5 - i; j++) {
// 				pow[i] += (int)Math.pow(5, j);
// 			}
// 		}
//
// 		int result = 0;
//
// 		for (int i = 0; i < w.length(); i++) {
//
// 			result += pow[i] * map.get(w.charAt(i)) + 1;
//
// 		}
//
// 		return result;
// 	}
// }
