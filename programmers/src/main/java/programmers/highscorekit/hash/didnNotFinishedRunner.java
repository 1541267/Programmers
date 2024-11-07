package programmers.highscorekit.hash;

// 수많은 마라톤 선수들이 마라톤에 참여하였습니다. 단 한 명의 선수를 제외하고는 모든 선수가 마라톤을 완주하였습니다.
//
// 마라톤에 참여한 선수들의 이름이 담긴 배열 participant와 완주한 선수들의 이름이 담긴 배열 completion이 주어질 때, 완주하지 못한 선수의 이름을 return 하도록 solution 함수를 작성해주세요.
//
// 제한사항
// 마라톤 경기에 참여한 선수의 수는 1명 이상 100,000명 이하입니다.
// completion의 길이는 participant의 길이보다 1 작습니다.
// 참가자의 이름은 1개 이상 20개 이하의 알파벳 소문자로 이루어져 있습니다.
// 참가자 중에는 동명이인이 있을 수 있습니다.
// 입출력 예
// participant	completion	return
// ["leo", "kiki", "eden"]	["eden", "kiki"]	"leo"
// ["marina", "josipa", "nikola", "vinko", "filipa"]	["josipa", "filipa", "marina", "nikola"]	"vinko"
// ["mislav", "stanko", "mislav", "ana"]	["stanko", "ana", "mislav"]	"mislav"
// 입출력 예 설명
// 예제 #1
// "leo"는 참여자 명단에는 있지만, 완주자 명단에는 없기 때문에 완주하지 못했습니다.
//
// 예제 #2
// "vinko"는 참여자 명단에는 있지만, 완주자 명단에는 없기 때문에 완주하지 못했습니다.
//
// 예제 #3
// "mislav"는 참여자 명단에는 두 명이 있지만, 완주자 명단에는 한 명밖에 없기 때문에 한명은 완주하지 못했습니다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// Arrays.sort(participant)와 Arrays.sort(completion)의 시간 복잡도는 각각 𝑂(𝑛logn)
// participant 배열을 순회하며 HashMap을 생성하는 데
// 𝑂(𝑛)
// completion 배열을 순회하며 HashMap 값을 감소시키는 데
// 𝑂(𝑚) m은 completion의 길이.

// 효율성  테스트
// 테스트 1 〉	통과 (36.92ms, 82.2MB)
// 테스트 2 〉	통과 (63.73ms, 88.9MB)
// 테스트 3 〉	통과 (95.92ms, 97.1MB)
// 테스트 4 〉	통과 (80.51ms, 95.7MB)
// 테스트 5 〉	통과 (73.59ms, 95.1MB)

// 참가자 등록:
// O(n).
// 완주자 처리:
// O(m).
// HashMap 탐색:
// O(k).
// 전체 복잡도:
// O(n+m+k).

public class didnNotFinishedRunner {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int size = st.countTokens();
		String[] participant = new String[size];

		for (int i = 0; i < size; i++) {
			participant[i] = st.nextToken();
		}

		st = new StringTokenizer(br.readLine(), " ");
		size = st.countTokens();
		String[] completion = new String[size];

		for (int i = 0; i < size; i++) {
			completion[i] = st.nextToken();
		}

		System.out.println(solution(participant, completion));
	}

	//  통과는 되는데 시간이 오래 걸림
	public static String solution(String[] participant, String[] completion) {

		Map<String, Integer> participants = new HashMap<>();

		for (String s : participant) {
			participants.put(s, participants.getOrDefault(s, 0) + 1);
		}

		for(String s : completion) {
			if(participants.containsKey(s)) {
				participants.put(s, participants.getOrDefault(s, 0) - 1);
			}
		}

		for(Map.Entry<String, Integer> entry : participants.entrySet()){
			if(entry.getValue() == 1) {
				return entry.getKey();
			}
		}

		return null;
	}
}
// 통과는 되는데 시간이 오래 걸림
// 효율성 테스트
// 테스트 1 〉	통과 (193.33ms, 85.7MB)
// 테스트 2 〉	통과 (282.82ms, 88.9MB)
// 테스트 3 〉	통과 (289.37ms, 94.5MB)
// 테스트 4 〉	통과 (398.06ms, 96.3MB)
// 테스트 5 〉	통과 (403.26ms, 110MB)

// 	public static String solution(String[] participant, String[] completion) {
//
// 		Arrays.sort(participant);
// 		Arrays.sort(completion);
// 		Map<String, Integer> participantList = new HashMap<>();
//
// 		for (String s : participant) {
// 			participantList.put(s, participantList.getOrDefault(s, 0) + 1);
// 		}
//
// 		for (String s : completion) {
// 			if (participantList.containsKey(s)) {
// 				participantList.put(s, participantList.getOrDefault(s, 0) - 1);
// 			}
// 		}
//
// 		for (Map.Entry<String, Integer> entry : participantList.entrySet()) {
// 			if (entry.getValue() == 2) {
// 				return entry.getKey();
// 			} else if (entry.getValue() == 1) {
// 				return entry.getKey();
// 			}
// 		}
// 		return null;
// 	}
// }

// 효율성 한개 시간 초과
// 효율성  테스트
// 테스트 1 〉	통과 (163.15ms, 84.6MB)
// 테스트 2 〉	통과 (256.66ms, 89.7MB)
// 테스트 3 〉	실패 (시간 초과)
// 테스트 4 〉	통과 (343.05ms, 95.1MB)
// 테스트 5 〉	통과 (337.86ms, 97MB)
// 	public static String solution(String[] participant, String[] completion) {
//
// 		Arrays.sort(participant);
// 		Arrays.sort(completion);
// 		Map<String, Integer> participantList = new HashMap<>();
//
// 		for (String s : participant) {
// 			participantList.put(s, participantList.getOrDefault(s, 0) + 1);
// 		}
//
// 		for (String s : completion) {
// 			if (participantList.containsKey(s)) {
// 				participantList.put(s, participantList.getOrDefault(s, 0) - 1);
// 			}
// 		}
//
// 		for (Map.Entry<String, Integer> entry : participantList.entrySet()) {
// 			if (entry.getValue() == 1) {
// 				return entry.getKey();
// 			}
// 		}
// 		return null;
// 	}
// }

//  통과
// 배열 정렬:
// O(nlogn+mlogm).
// 비교 루프:
// O(m).
// 전체 복잡도:
// O(nlogn).

// 	public static String solution(String[] participant, String[] completion) {
//
// 		Arrays.sort(participant);
// 		Arrays.sort(completion);
//
// 		for (int i = 0; i < completion.length; i++){
// 			if(!participant[i].equals(completion[i])){
// 				return participant[i];
// 			}
// 		}
// 		return participant[participant.length-1];
// 	}
// }
//