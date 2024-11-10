import java.io.BufferedReader;
import java.io.InputStreamReader;

public class calculator {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int count = 0;
		double sum = 0.0;
		while (count < 10) {

			sum += Double.parseDouble(br.readLine());

			count++;
		}

		System.out.println("total : " + sum);

	}
}
