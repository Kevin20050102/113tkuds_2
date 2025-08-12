import java.util.*;

public class StockMaximizer {

    // 主邏輯：從所有可賺錢的區間中選出 K 次最大利潤
    public static int maximizeProfit(int[] prices, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        int i = 0;
        while (i < prices.length - 1) {
            // 找買入點（當前價格比下一天低）
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            int buy = i;

            // 找賣出點（價格持續上升）
            while (i < prices.length - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            int sell = i;

            if (buy < sell) {
                int profit = prices[sell] - prices[buy];
                if (profit > 0) {
                    maxHeap.offer(profit);
                }
            }

            i++;
        }

        // 取出最多 K 個最大利潤加總
        int totalProfit = 0;
        while (k-- > 0 && !maxHeap.isEmpty()) {
            totalProfit += maxHeap.poll();
        }

        return totalProfit;
    }

    // 主程式：處理使用者輸入
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("請輸入股票價格的天數：");
        int n = scanner.nextInt();
        int[] prices = new int[n];

        System.out.println("請輸入每日價格：");
        for (int i = 0; i < n; i++) {
            prices[i] = scanner.nextInt();
        }

        System.out.println("請輸入最多交易次數 K：");
        int k = scanner.nextInt();

        int result = maximizeProfit(prices, k);
        System.out.println("最大利潤為：" + result);
    }
}
