package programmers.highscorekit.DFSBFS;

// Level 3 DFS/BFS, 단어 변환
// 두 개의 단어 begin, target과 단어의 집합 words가 있습니다. 아래와 같은 규칙을 이용하여 begin에서 target으로 변환하는 가장 짧은 변환 과정을 찾으려고 합니다.
//
// 1. 한 번에 한 개의 알파벳만 바꿀 수 있습니다.
// 2. words에 있는 단어로만 변환할 수 있습니다.
// 예를 들어 begin이 "hit", target가 "cog", words가 ["hot","dot","dog","lot","log","cog"]라면 "hit" -> "hot" -> "dot" -> "dog" -> "cog"와 같이 4단계를 거쳐 변환할 수 있습니다.
//
// 두 개의 단어 begin, target과 단어의 집합 words가 매개변수로 주어질 때, 최소 몇 단계의 과정을 거쳐 begin을 target으로 변환할 수 있는지 return 하도록 solution 함수를 작성해주세요.
//
// 제한사항
// 각 단어는 알파벳 소문자로만 이루어져 있습니다.
// 각 단어의 길이는 3 이상 10 이하이며 모든 단어의 길이는 같습니다.
// words에는 3개 이상 50개 이하의 단어가 있으며 중복되는 단어는 없습니다.
// begin과 target은 같지 않습니다.
// 변환할 수 없는 경우에는 0를 return 합니다.
// 입출력 예
// begin	target	words	return
// "hit"	"cog"	["hot", "dot", "dog", "lot", "log", "cog"]	4
// "hit"	"cog"	["hot", "dot", "dog", "lot", "log"]	0
// 입출력 예 설명
// 예제 #1
// 문제에 나온 예와 같습니다.
//
// 예제 #2
// target인 "cog"는 words 안에 없기 때문에 변환할 수 없습니다.

import java.util.LinkedList;
import java.util.Queue;

public class WordConversion {
	public static void main(String[] args) {

		String[] words1 = {"hot", "dot", "dog", "lot", "log", "cog"};
		String[] words2 = {"hot", "dot", "dog", "lot", "log"};

		System.out.println(solution("hit", "cog", words1));
		System.out.println(solution("hit", "cog", words2));

	}

	static boolean[] isVisited;

	static public int solution(String b, String t, String[] w) {

		return bfs(w, b, t);
	}

	private static int bfs(String[] w, String b, String t) {

		int count = 0;
		isVisited = new boolean[w.length];

		Queue<Word> q = new LinkedList<>();

		q.offer(new Word(b, 0));

		while (!q.isEmpty()) {
			Word word = q.poll();
			String curWord = word.word;
			int dist = word.count;
			// System.out.println("dist = " + dist);
			// System.out.println("curWord = " + curWord);
			for (int i = 0; i < w.length; i++) {

				if (curWord.equals(t)) return dist;

				// System.out.println(" aaa : " + (canChange(w[i], curWord)));
				if (!isVisited[i] && canChange(w[i], curWord)) {
					isVisited[i] = true;
					q.offer(new Word(w[i], dist + 1));
				}

			}
		}
		return count;
	}

	private static boolean canChange(String s, String curWord) {

		int diffCount = 0;

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != curWord.charAt(i)) diffCount++;
		}

		return diffCount <= 1;

	}

	private static class Word {
		String word;
		int count;

		public Word(String word, int count) {
			this.word = word;
			this.count = count;
		}
	}
}