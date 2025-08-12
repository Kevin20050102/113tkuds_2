import java.util.Scanner;

public class ValidMaxHeapChecker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("請輸入陣列大小: ");
        int n = sc.nextInt();

        int[] arr = new int[n];
        System.out.println("請輸入陣列元素(以空格分隔):");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        boolean isHeap = true;
        int violationIndex = -1;

        int lastNonLeaf = (n - 2) / 2;

        for (int i = 0; i <= lastNonLeaf; i++) {
            int left = 2 * i + 1;   
            int right = 2 * i + 2;  

            if (left < n && arr[i] < arr[left]) {
                isHeap = false;
                violationIndex = left;
                break;
            }

            if (right < n && arr[i] < arr[right]) {
                isHeap = false;
                violationIndex = right;
                break;
            }
        }

        if (isHeap) {
            System.out.println("true");
        } else {
            System.out.println("false (索引 " + violationIndex + " 的 " + arr[violationIndex]
                               + " 大於父節點 " + arr[(violationIndex - 1) / 2] + ")");
        }

        sc.close();
    }
}
