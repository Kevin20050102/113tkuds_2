import java.util.*;

// 主類別
public class M11_HeapSortWithTie {
    // 定義一個類別來表示學生的分數和原始索引
    static class Student {
        int score;
        int index;

        Student(int score, int index) {
            this.score = score;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        Student[] students = new Student[n];

        // 讀取分數並儲存成 Student 物件陣列
        for (int i = 0; i < n; i++) {
            int score = sc.nextInt();
            students[i] = new Student(score, i);
        }

        // 使用 heap sort 排序
        heapSort(students);

        // 輸出排序後的分數
        for (int i = 0; i < n; i++) {
            System.out.print(students[i].score + " ");
        }

        sc.close();
    }

    // Heap Sort 主邏輯
    public static void heapSort(Student[] arr) {
        int n = arr.length;

        // Step 1: 建立 Max-Heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Step 2: 將最大值一一放到陣列尾端
        for (int i = n - 1; i > 0; i--) {
            // 將 root（最大）與當前尾端交換
            Student temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // 重新調整 heap（排除已排序的尾端）
            heapify(arr, i, 0);
        }
    }

    // 調整 Max-Heap
    public static void heapify(Student[] arr, int n, int i) {
        int largest = i;        // 假設當前節點最大
        int left = 2 * i + 1;   // 左子節點
        int right = 2 * i + 2;  // 右子節點

        // 比較左子節點
        if (left < n && compare(arr[left], arr[largest]) > 0) {
            largest = left;
        }

        // 比較右子節點
        if (right < n && compare(arr[right], arr[largest]) > 0) {
            largest = right;
        }

        // 若最大值不是本身，交換並繼續調整
        if (largest != i) {
            Student swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }

    // 比較函數：先比分數，再比 index（小的優先）
    public static int compare(Student a, Student b) {
        if (a.score != b.score) {
            return a.score - b.score;  // 分數小的優先
        } else {
            return b.index - a.index;  // 輸入順序早的優先（因為是 Max-Heap）
        }
    }
}
