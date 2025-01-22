package programmers.highscorekit.stackQueue;

// TODO 보류 잘 모르겠음

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BridgeTruck {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int bridge_length = Integer.parseInt(br.readLine());
		int weight = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int[] truck_weights = new int[st.countTokens()];
		int n = 0;

		while (st.hasMoreTokens()) {
			truck_weights[n] = Integer.parseInt(st.nextToken());
			n++;
		}
		System.out.println(solution(bridge_length, weight, truck_weights));
	}

	public static int solution(int bridge_length, int weight, int[] truck_weights) {

		Queue<Integer> truckList = new LinkedList<>();
		for (int truckWeight : truck_weights) {
			truckList.offer(truckWeight);
		}

		Queue<Integer> onTheBride = new LinkedList<>();
		Queue<Integer> finishedTrucks = new LinkedList<>();

		int time = 0;
		int truckListSize = truck_weights.length;

		int currentTruckWeight = 0; // 지나려는 트럭
		int nextTruckWeight = 0;
		int exitTruckWeight = 0;
		int currentBridgeWeight = 0;

			// System.out.print("finished truckList: " + finishedTrucks);
			// System.out.print(" | onTheBride: " + onTheBride);
			// System.out.print(" | truckList: " + truckList);
			// System.out.println(" | time: " + time);

		return time;
	}
}
