package programmers.highscorekit.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/42579?language=java
코딩테스트 고득점 Kit / 해시 / 베스트앨범
문제 설명
스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시하려 합니다. 노래는 고유 번호로 구분하며, 노래를 수록하는 기준은 다음과 같습니다.

속한 노래가 많이 재생된 장르를 먼저 수록합니다.
장르 내에서 많이 재생된 노래를 먼저 수록합니다.
장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
노래의 장르를 나타내는 문자열 배열 genres와 노래별 재생 횟수를 나타내는 정수 배열 plays가 주어질 때, 베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return 하도록 solution 함수를 완성하세요.

제한사항
genres[i]는 고유번호가 i인 노래의 장르입니다.
plays[i]는 고유번호가 i인 노래가 재생된 횟수입니다.
genres와 plays의 길이는 같으며, 이는 1 이상 10,000 이하입니다.
장르 종류는 100개 미만입니다.
장르에 속한 곡이 하나라면, 하나의 곡만 선택합니다.
모든 장르는 재생된 횟수가 다릅니다.
입출력 예
genres	plays	return
["classic", "pop", "classic", "classic", "pop"]	[500, 600, 150, 800, 2500]	[4, 1, 3, 0]
입출력 예 설명
classic 장르는 1,450회 재생되었으며, classic 노래는 다음과 같습니다.

고유 번호 3: 800회 재생
고유 번호 0: 500회 재생
고유 번호 2: 150회 재생
pop 장르는 3,100회 재생되었으며, pop 노래는 다음과 같습니다.

고유 번호 4: 2,500회 재생
고유 번호 1: 600회 재생
따라서 pop 장르의 [4, 1]번 노래를 먼저, classic 장르의 [3, 0]번 노래를 그다음에 수록합니다.

장르 별로 가장 많이 재생된 노래를 최대 두 개까지 모아 베스트 앨범을 출시하므로 2번 노래는 수록되지 않습니다.
*/

public class BestAlbum {
	public static void main(String[] args) {

		String[] g = {"classic", "pop", "classic", "classic", "pop"};
		int[] p = {500, 600, 150, 800, 2500};

		System.out.println(solution(g, p));

	}

	// 한 번의 순회로 장르 별 총 재생 회수 map 저장 & 해당 장르의 값과 인덱스를 저장하는데
	// 길이 4의 배열로 저장(재생 값1, 인덱스, 재생 값2, 인덱스), 매 인덱스 마다 비교
	// 장르 별 총 재생 회수 map의 values 기준으로 key값을 내림차 정렬
	// 정렬 된 장르 순서대로 해당 장르에 저장해 둔 배열에서 값과 인덱스를 사용 해 result list에 index를 저장
	// -> 만약 해당 장르의 값이 1개 이면 1개만 사용
	// result 반환
	private static List<Integer> solution(String[] g, int[] p) {

		if (g.length == 1) {
			return List.of(0);
		}

		HashMap<String, Integer> playCountMap = new HashMap<>();
		HashMap<String, int[]> genrePlayArrMap = new HashMap<>();

		for (int i = 0; i < g.length; i++) {
			playCountMap.put(g[i], playCountMap.getOrDefault(g[i], 0) + p[i]);

			int[] arr = genrePlayArrMap.computeIfAbsent(g[i], k -> new int[] {0, -1, 0, -1});

			if (p[i] > arr[0]) {
				arr[2] = arr[0];
				arr[3] = arr[1];

				arr[0] = p[i];
				arr[1] = i;
			} else if (p[i] > arr[2]) {
				arr[2] = p[i];
				arr[3] = i;
			}
		}

		List<String> sortedGenres = new ArrayList<>(playCountMap.keySet());

		sortedGenres.sort((a, b) -> playCountMap.get(b) - playCountMap.get(a));

		List<Integer> result = new ArrayList<>();

		for (String genre : sortedGenres) {
			result.add(genrePlayArrMap.get(genre)[1]);
			if (genrePlayArrMap.get(genre)[3] != -1) {
				result.add(genrePlayArrMap.get(genre)[3]);
			}
		}
		return result;
	}
}
// 내가 푼 방법
// 한 번의 순회로 장르별 총 재생 수 & 각 장르의 모든 인덱스를 배열로 수집
// 장르를 총 재생 수 기준으로 list<mapentry>를 사용해 내림차 정렬
// 정렬 된 장르 순서대로 Map을 체크하면서 해당 장르의 인덱스로 play 배열에서 확인 후 내림차순 정렬, 값이 같을 시 인덱스 오름차순 정렬
// 그리고 정렬 된 배열에서 두 인덱스를 result 리스트에 저장
// 모든 장르 체크 후 result 반환
// 
// 	private static List<Integer> solution(String[] g, int[] p) {
//
// 		if (g.length == 1) {
// 			return List.of(0);
// 		}
//
// 		HashMap<String, List<Integer>> indexMap = new HashMap<>();
// 		LinkedHashMap<String, Integer> playCountMap = new LinkedHashMap<>();
//
// 		for (int i = 0; i < g.length; i++) {
// 			playCountMap.put(g[i], playCountMap.getOrDefault(g[i], 0) + p[i]);
//
// 			indexMap.computeIfAbsent(g[i], k -> new ArrayList<>()).add(i);
// 		}
//
// 		List<Map.Entry<String, Integer>> entryList = new ArrayList<>(playCountMap.entrySet());
//
// 		entryList.sort((o1, o2) -> o2.getValue() - o1.getValue());
//
// 		List<Integer> result = new ArrayList<>();
//
// 		for (Map.Entry<String, Integer> e : entryList) {
//
// 			String genre = e.getKey();
//
// 			List<Integer> idxs = indexMap.get(genre);
//
// 			idxs.sort((o1, o2) -> {
// 				if (p[o2] != p[o1]) {
// 					return p[o2] - p[o1];
// 				}
// 				return o1 - o2;
// 			});
// 			for (int i = 0; i < Math.min(2, idxs.size()); i++) {
// 				result.add(idxs.get(i));
// 			}
// 		}
// 		return result;
// 	}
// }
