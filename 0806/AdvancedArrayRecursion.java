import java.util.*;

public class AdvancedArrayRecursion {
    static void quickSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int pivot = arr[(left + right) / 2];
        int i = left, j = right;
        while (i <= j) {
            while (arr[i] < pivot) i++;
            while (arr[j] > pivot) j--;
            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        quickSort(arr, left, j);
        quickSort(arr, i, right);
    }

    static int[] mergeArrays(int[] a, int[] b, int i, int j, List<Integer> result) {
        if (i == a.length) {
            while (j < b.length) result.add(b[j++]);
            return result.stream().mapToInt(x -> x).toArray();
        }
        if (j == b.length) {
            while (i < a.length) result.add(a[i++]);
            return result.stream().mapToInt(x -> x).toArray();
        }
        if (a[i] <= b[j]) result.add(a[i++]);
        else result.add(b[j++]);
        return mergeArrays(a, b, i, j, result);
    }

    static int kthSmallest(int[] arr, int k, int index) {
        if (index == k - 1) return arr[index];
        return kthSmallest(arr, k, index + 1);
    }

    static boolean subsetSum(int[] arr, int target, int index) {
        if (target == 0) return true;
        if (index >= arr.length || target < 0) return false;
        return subsetSum(arr, target - arr[index], index + 1) || subsetSum(arr, target, index + 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr = {5, 3, 8, 4, 2};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("快速排序結果: " + Arrays.toString(arr));

        int[] a = {1, 3, 5}, b = {2, 4, 6};
        System.out.println("合併陣列: " + Arrays.toString(mergeArrays(a, b, 0, 0, new ArrayList<>())));

        System.out.print("輸入 k 尋找第 k 小元素: ");
        int k = sc.nextInt();
        System.out.println("第 " + k + " 小元素: " + kthSmallest(arr, k, 0));

        System.out.print("輸入目標值檢查子序列和: ");
        int target = sc.nextInt();
        System.out.println("是否存在子序列和等於目標值: " + subsetSum(arr, target, 0));
    }
}
