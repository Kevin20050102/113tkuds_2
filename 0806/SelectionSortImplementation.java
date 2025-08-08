import java.util.*;

public class SelectionSortImplementation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("請輸入陣列長度: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.println("輸入陣列元素：");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int comparisons = 0, swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                swaps++;
            }
            System.out.println("第 " + (i + 1) + " 輪排序結果: " + Arrays.toString(arr));
        }

        System.out.println("排序完成: " + Arrays.toString(arr));
        System.out.println("比較次數: " + comparisons);
        System.out.println("交換次數: " + swaps);
    }
}
