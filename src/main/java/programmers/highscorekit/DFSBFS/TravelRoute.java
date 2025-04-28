package programmers.highscorekit.DFSBFS;

// Level 3 DFS/BFS, 여행 경로
// 주어진 항공권을 모두 이용하여 여행경로를 짜려고 합니다. 항상 "ICN" 공항에서 출발합니다.
//
// 항공권 정보가 담긴 2차원 배열 tickets가 매개변수로 주어질 때, 방문하는 공항 경로를 배열에 담아 return 하도록 solution 함수를 작성해주세요.
//
// 제한사항
// 모든 공항은 알파벳 대문자 3글자로 이루어집니다.
// 주어진 공항 수는 3개 이상 10,000개 이하입니다.
// tickets의 각 행 [a, b]는 a 공항에서 b 공항으로 가는 항공권이 있다는 의미입니다.
// 주어진 항공권은 모두 사용해야 합니다.
// 만일 가능한 경로가 2개 이상일 경우 알파벳 순서가 앞서는 경로를 return 합니다.
// 모든 도시를 방문할 수 없는 경우는 주어지지 않습니다.
// 입출력 예
// tickets	return
// [["ICN", "JFK"], ["HND", "IAD"], ["JFK", "HND"]]	["ICN", "JFK", "HND", "IAD"]
// [["ICN", "SFO"], ["ICN", "ATL"], ["SFO", "ATL"], ["ATL", "ICN"], ["ATL","SFO"]]	["ICN", "ATL", "ICN", "SFO", "ATL", "SFO"]
// 입출력 예 설명
// 예제 #1
//
// ["ICN", "JFK", "HND", "IAD"] 순으로 방문할 수 있습니다.
//
// 예제 #2
//
// ["ICN", "SFO", "ATL", "ICN", "ATL", "SFO"] 순으로 방문할 수도 있지만 ["ICN", "ATL", "ICN", "SFO", "ATL", "SFO"] 가 알파벳 순으로 앞섭니다.

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TravelRoute {
	public static void main(String[] args) {
		// 전위순회로도 통과가 됨, 에러 가능
		String[][] arr1 = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
		String[][] arr2 = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};
		// 후위순회로 문제의 제한사항에 모두 충족, 정답
		String[][] arr3 = {{"ICN", "AAA"}, {"ICN", "BBB"}, {"BBB", "ICN"}};
		
		System.out.println("solution(arr1) = " + solution(arr1));
		route = new ArrayList<>();
		System.out.println("solution(arr2) = " + solution(arr2));
		route = new ArrayList<>();
		System.out.println("solution(arr3) = " + solution(arr3));
	}

	// 아래 실패 코드의 수정, 실패 코드는 전위순회로 인한 조기 종료 가능성
	// -> 알파벳 순으로 진행하긴 하나 문제에서 제시한 모든 티켓 사용이 불가
	// 수정된 코드는 후위순회, 방문 가능한 모든 경로를 사용 후 해당 사용 된 경로를 차례대로 route에 추가.
	// [["ICN", "BBB"], ["ICN", "CCC"], ["BBB", "ICN"]] = [ICN, BBB, ICN, AAA]

	static Map<String, PriorityQueue<String>> map = new HashMap<>();
	static List<String> route = new ArrayList<>();

	private static List<String> solution(String[][] t) {

		for (String[] s : t) {
			PriorityQueue<String> pq = map.getOrDefault(s[0], new PriorityQueue<>());
			pq.offer(s[1]);

			map.put(s[0], pq);
		}

		dfs("ICN");

		return route;
	}

	private static void dfs(String start) {

		while (map.containsKey(start) && !map.get(start).isEmpty()) {
			dfs(map.get(start).poll());
		}
		route.add(0, start);
	}
}

// 처음에 푼 것, 테스트케이스 처럼 맞게 나오는 경우도 있으나, 경로가 역순으로 저장되는 경우도 있다.
// 모든 티켓을 방문 못 할 수도 있음 (알파벳 순으로 꺼내서 조기 종료 가능하기 때문)
// ex [["ICN", "BBB"], ["ICN", "CCC"], ["BBB", "ICN"]]
// -> 전위순회 로 ICN -> AAA 종료
// 	static Map<String, PriorityQueue<String>> map = new HashMap<>();
// 	static List<String> route = new ArrayList<>();
//
// 	public static List<String> solution(String[][] t) {
//
// 		for (String[] s : t) {
// 			PriorityQueue<String> pq = map.getOrDefault(s[0], new PriorityQueue<>());
// 			pq.offer(s[1]);
//
// 			map.put(s[0], pq);
//
// 		}
//
// 		dfs("ICN");
//
// 		return route;
// 	}
//
// 	private static void dfs(String start) {
// 		// System.out.println("==========================================================");
// 		// System.out.println("start = " + start);
// 		// System.out.println("route = " + route);
//
// 		if (map.containsKey(start) && !map.get(start).isEmpty()) {
// 			route.add(start);
// 			dfs(map.get(start).poll());
// 		} else {
// 			route.add(start);
// 		}
// 	}
// }