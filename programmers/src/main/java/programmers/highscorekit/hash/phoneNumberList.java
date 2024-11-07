package programmers.highscorekit.hash;

// 전화번호부에 적힌 전화번호 중, 한 번호가 다른 번호의 접두어인 경우가 있는지 확인하려 합니다.
// 전화번호가 다음과 같을 경우, 구조대 전화번호는 영석이의 전화번호의 접두사입니다.
//
// 구조대 : 119
// 박준영 : 97 674 223
// 지영석 : 11 9552 4421
// 전화번호부에 적힌 전화번호를 담은 배열 phone_book 이 solution 함수의 매개변수로 주어질 때, 어떤 번호가 다른 번호의 접두어인 경우가 있으면 false를 그렇지 않으면 true를 return 하도록 solution 함수를 작성해주세요.
//
// 제한 사항
// phone_book의 길이는 1 이상 1,000,000 이하입니다.
// 각 전화번호의 길이는 1 이상 20 이하입니다.
// 같은 전화번호가 중복해서 들어있지 않습니다.
// 입출력 예제
// phone_book	return
// ["119", "97674223", "1195524421"]	false
// ["123","456","789"]	true
// ["12","123","1235","567","88"]	false
// 입출력 예 설명
// 입출력 예 #1
// 앞에서 설명한 예와 같습니다.
//
// 입출력 예 #2
// 한 번호가 다른 번호의 접두사인 경우가 없으므로, 답은 true입니다.
//
// 입출력 예 #3
// 첫 번째 전화번호, “12”가 두 번째 전화번호 “123”의 접두사입니다. 따라서 답은 false입니다.

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class phoneNumberList {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		String[] phone_book = new String[st.countTokens()];
		int i = 0;

		while(st.hasMoreTokens()) {
			phone_book[i] = st.nextToken();
			i++;
		}
		System.out.println(solution(phone_book));
	}

	public static boolean solution(String[] phone_book) {


		Arrays.sort(phone_book);

		for(int i = 0; i < phone_book.length -1; i++) {
			if(phone_book[i+1].startsWith(phone_book[i])) {
				return false;
			}
		}

		return true;
	}
}
// 효율성  테스트
// 테스트 1 〉	통과 (22.97ms, 58.6MB)
// 테스트 2 〉	통과 (18.29ms, 56.3MB)
// 테스트 3 〉	통과 (328.21ms, 97MB)
// 테스트 4 〉	통과 (301.19ms, 112MB)
// 	public static boolean solution(String[] phone_book) {
//
//
// 		Arrays.sort(phone_book);
//
// 		for(int i = 0; i < phone_book.length -1; i++) {
// 			if(phone_book[i+1].startsWith(phone_book[i])) {
// 				return false;
// 			}
// 		}
//
// 		return true;
// 	}
// }

// 효율성  테스트
// 테스트 1 〉	통과 (16.45ms, 57.6MB)
// 테스트 2 〉	통과 (29.27ms, 72.1MB)
// 테스트 3 〉	실패 (시간 초과)
// 테스트 4 〉	실패 (시간 초과)
// 	public static boolean solution(String[] phone_book) {
//
// 		Arrays.sort(phone_book);
//
// 		for (String s : phone_book) {
// 			int stringSize = s.length();
//
// 			for (String currentString : phone_book) {
// 				if (currentString.length() < stringSize) {
// 					continue;
// 				}
//
// 				if (!s.equals(currentString)) {
// 					String aa = currentString.substring(0, stringSize);
//
// 					if (aa.equals(s)) {
// 						return false;
// 					}
// 				}
// 			}
// 		}
// 		return true;
// 	}
// }

// 효율성  테스트
// 테스트 1 〉	통과 (1.73ms, 57.4MB)
// 테스트 2 〉	통과 (1.85ms, 59.9MB)
// 테스트 3 〉	실패 (시간 초과)
// 테스트 4 〉	실패 (시간 초과)
// 	public static boolean solution(String[] phone_book) {
//
// 		for (String s : phone_book) {
//
// 			int stringSize = s.length();
//
// 			for (String currentString : phone_book) {
// 				if (currentString.length() < stringSize) {
// 					continue;
// 				}
//
// 				if (!s.equals(currentString)) {
// 					String aa = currentString.substring(0, stringSize);
//
// 					if (aa.equals(s)) {
// 						return false;
// 					}
// 				}
// 			}
// 		}
// 		return true;
// 	}
// }
