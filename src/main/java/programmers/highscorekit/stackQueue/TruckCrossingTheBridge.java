package programmers.highscorekit.stackQueue;

/*

코딩테스트 고득점 Kit / 스택 | 큐 /다리를 지나는 트럭

트럭 여러 대가 강을 가로지르는 일차선 다리를 정해진 순으로 건너려 합니다. 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 알아내야 합니다. 다리에는 트럭이 최대 bridge_length대 올라갈 수 있으며, 다리는 weight 이하까지의 무게를 견딜 수 있습니다. 단, 다리에 완전히 오르지 않은 트럭의 무게는 무시합니다.

	예를 들어, 트럭 2대가 올라갈 수 있고 무게를 10kg까지 견디는 다리가 있습니다. 무게가 [7, 4, 5, 6]kg인 트럭이 순서대로 최단 시간 안에 다리를 건너려면 다음과 같이 건너야 합니다.

	경과 시간	다리를 지난 트럭	다리를 건너는 트럭	대기 트럭
	0	[]	[]	[7,4,5,6]
	1~2	[]	[7]	[4,5,6]
	3	[7]	[4]	[5,6]
	4	[7]	[4,5]	[6]
	5	[7,4]	[5]	[6]
	6~7	[7,4,5]	[6]	[]
	8	[7,4,5,6]	[]	[]
	따라서, 모든 트럭이 다리를 지나려면 최소 8초가 걸립니다.

	solution 함수의 매개변수로 다리에 올라갈 수 있는 트럭 수 bridge_length, 다리가 견딜 수 있는 무게 weight, 트럭 별 무게 truck_weights가 주어집니다. 이때 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 return 하도록 solution 함수를 완성하세요.

	제한 조건
	bridge_length는 1 이상 10,000 이하입니다.
	weight는 1 이상 10,000 이하입니다.
	truck_weights의 길이는 1 이상 10,000 이하입니다.
	모든 트럭의 무게는 1 이상 weight 이하입니다.
	입출력 예
	bridge_length	weight	truck_weights	return
	2	10	[7,4,5,6]	8
	100	100	[10]	101
	100	100	[10,10,10,10,10,10,10,10,10,10]	110
	*/

import java.util.LinkedList;
import java.util.Queue;

public class TruckCrossingTheBridge {
	public static void main(String[] args) {

		int bridgeLength1 = 2;
		int bridgeLength2 = 100;
		int bridgeLength3 = 100;

		int weight1 = 10;
		int weight2 = 100;
		int weight3 = 100;

		int[] truckWeights1 = {7, 4, 5, 6};
		int[] truckWeights2 = {10};
		int[] truckWeights3 = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10};

		System.out.println(solution(bridgeLength1, weight1, truckWeights1));
		System.out.println(solution(bridgeLength2, weight2, truckWeights2));
		System.out.println(solution(bridgeLength3, weight3, truckWeights3));
	}

	private static int solution(int bridgeLength, int weight, int[] truckWeights) {
		Queue<Truck> bridge = new LinkedList<>();
		int time = 0, curWeight = 0;
		int index = 0;

		while (index < truckWeights.length || !bridge.isEmpty()) {

			time++;

			if (!bridge.isEmpty() && bridge.peek().exitTime == time) {
				Truck truck = bridge.poll();
				curWeight -= truck.weight;
			}

			// 지나간 시간 + 다리 길이 = 트럭이 다리위를 벗어나는 시간
			if (index < truckWeights.length && curWeight + truckWeights[index] <= weight
				&& bridge.size() < bridgeLength) {
				int truckWeight = truckWeights[index++];
				curWeight += truckWeight;
				bridge.add(new Truck(truckWeight, time + bridgeLength));
			}
		}
		return time;
	}

	static class Truck {

		int weight, exitTime;

		public Truck(int weight, int exitTime) {
			this.weight = weight;
			this.exitTime = exitTime;
		}
	}
}

// 처음 시도 한 것, 첫 케이스만 통과 됨
// 	private static int solution(int bridgeLength, int weight, int[] truckWeights) {
// 		Queue<Integer> crossingQueue = new LinkedList<>();
// 		int time = 0, curWeight = 0;
// 		int index = 0;
// 		while (index < truckWeights.length) {
// 			System.out.println("==========================================================");
//
// 			boolean isboarding = false;
//
// 			if (curWeight + truckWeights[index] <= weight && crossingQueue.size() + 1 <= bridgeLength) {
// 				curWeight += truckWeights[index];
// 				crossingQueue.add(truckWeights[index++]);
// 				time++;
// 				isboarding = true;
// 			}
// 			System.out.println(
// 				"before curWeight = " + curWeight + ", before Queue = " + crossingQueue + ", time = " + time);
//
// 			if (!isboarding && !crossingQueue.isEmpty()) {
// 				time++;
// 				curWeight -= crossingQueue.poll();
// 			}
//
// 			System.out.println(
// 				"after curWeight = " + curWeight + ", after Queue = " + crossingQueue + ", time = " + time);
// 		}
//
// 		while(!crossingQueue.isEmpty()) {
// 			curWeight += crossingQueue.poll();
// 			time++;
// 		}
//
// 		return time;
// 	}
// }
