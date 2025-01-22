package programmers.leveltest;

import java.util.Stack;

// 괄호가 바르게 짝지어져 있는지 체크 확인 문제
// => ( 문자로 열렸으면 반드시 ) 닫혀야 하는 첸크
// stack 구조를 사용한 체크

public class StackTest {

	public static void main(String[] args) {
		System.out.println(solution("()("));
	}

	public static boolean solution(String s) {
		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(') {
				stack.push(c);
			} else if (c == ')') {
				if (stack.isEmpty()) {
					return false;
				}
				stack.pop();
			}
		}
		return stack.isEmpty();
	}
}
