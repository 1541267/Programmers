package programmers.skillchecktest.Level1;

// pass

public class attraction {
	public static void main(String[] args) {
		int price = 3;
		int money = 20;
		int count = 4;

		System.out.println(solution(price, money ,count));
	}

	public static long solution(int price, int money, int count) {


		long totalPrice = 0;

		for(int i = 1; i <= count; i++){
			totalPrice += (long)price * i;
		}

		long moneyResult = totalPrice - money;

		if (moneyResult < 0) {
			return 0;
		} else {
			return moneyResult;
		}
	}
}
