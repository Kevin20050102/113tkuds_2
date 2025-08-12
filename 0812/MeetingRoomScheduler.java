import java.util.*;

public class MeetingRoomScheduler {

    // 功能一：計算最少需要的會議室數量
    public static int minMeetingRooms(int[][] meetings) {
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int[] meeting : meetings) {
            if (!minHeap.isEmpty() && meeting[0] >= minHeap.peek()) {
                minHeap.poll(); // 重用會議室
            }
            minHeap.offer(meeting[1]); // 安排新會議
        }

        return minHeap.size();
    }

    // 功能二：安排會議最大化總會議時間（只有 N 個會議室）
    public static int maximizeTotalTime(int[][] meetings, int roomCount) {
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[1])); // 根據結束時間排序

        // 用 PriorityQueue 模擬每個會議室的排程狀況（每個會議室最後的結束時間）
        PriorityQueue<int[]>[] rooms = new PriorityQueue[roomCount];
        for (int i = 0; i < roomCount; i++) {
            rooms[i] = new PriorityQueue<>(Comparator.comparingInt(a -> a[1])); // 按結束時間排序
        }

        int totalTime = 0;

        for (int[] meeting : meetings) {
            boolean scheduled = false;

            for (PriorityQueue<int[]> room : rooms) {
                if (room.isEmpty() || room.peek()[1] <= meeting[0]) {
                    room.offer(meeting);
                    totalTime += (meeting[1] - meeting[0]);
                    scheduled = true;
                    break;
                }
            }
        }

        return totalTime;
    }

    // 主程式：只使用標準輸入，不額外提示
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt(); // 輸入會議數量
        int[][] meetings = new int[n][2];
        for (int i = 0; i < n; i++) {
            meetings[i][0] = scanner.nextInt(); // 開始時間
            meetings[i][1] = scanner.nextInt(); // 結束時間
        }

        int requiredRooms = minMeetingRooms(meetings);
        System.out.println(requiredRooms);

        int availableRooms = scanner.nextInt(); // 輸入可用會議室數
        int maxTime = maximizeTotalTime(meetings, availableRooms);
        System.out.println(maxTime);
    }
}
