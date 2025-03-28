package programmers.highscorekit.greedy;

//문제 설명
// 어떤 숫자에서 k개의 수를 제거했을 때 얻을 수 있는 가장 큰 숫자를 구하려 합니다.
//
// 예를 들어, 숫자 1924에서 수 두 개를 제거하면 [19, 12, 14, 92, 94, 24] 를 만들 수 있습니다. 이 중 가장 큰 숫자는 94 입니다.
//
// 문자열 형식으로 숫자 number와 제거할 수의 개수 k가 solution 함수의 매개변수로 주어집니다. number에서 k 개의 수를 제거했을 때 만들 수 있는 수 중 가장 큰 숫자를 문자열 형태로 return 하도록 solution 함수를 완성하세요.
//
// 제한 조건
// number는 2자리 이상, 1,000,000자리 이하인 숫자입니다.
// k는 1 이상 number의 자릿수 미만인 자연수입니다.
// 입출력 예
// number	k	return
// "1924"	2	"94"
// "1231234"	3	"3234"
// "4177252841"	4	"775841"

// 풀긴 했는데 코드 조금 더 이해 해보기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MakingBigNumber {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		String number = st.nextToken();
		int k = Integer.parseInt(st.nextToken());

		System.out.println(solution(number, k));
	}

	public static StringBuilder solution(String number, int k) {
		StringBuilder stb = new StringBuilder();

		int deleteCount = 0;
		
		// 반복문 한번에 비교,삭제 해결되도록 생각만 하다가 
		// 검색 후 원리 이해, 코드 더 이해해보기
		
		for (char c : number.toCharArray()) {
			while (deleteCount < k && !stb.isEmpty() && stb.charAt(stb.length() -1) < c) {
				stb.deleteCharAt(stb.length() -1);
				deleteCount++;
			}
			stb.append(c);
		}

		if(deleteCount != k) {
			stb.delete(stb.length() - (k-deleteCount), stb.length());
		}
		return stb;
	}
}
