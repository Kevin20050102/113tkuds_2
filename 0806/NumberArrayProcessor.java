import java.util.*;

public class NumberArrayProcessor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 輸入陣列1
        System.out.print("請輸入陣列1長度: ");
        int n1 = sc.nextInt();
        int[] arr1 = new int[n1];
        System.out.println("輸入陣列1元素：");
        for (int i = 0; i < n1; i++) {
            arr1[i] = sc.nextInt();
        }

        // 移除重複
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        for (int num : arr1) set.add(num);
        System.out.println("\n移除重複元素後: " + set);

        // 輸入陣列2
        System.out.print("請輸入陣列2長度: ");
        int n2 = sc.nextInt();
        int[] arr2 = new int[n2];
        System.out.println("輸入陣列2元素：");
        for (int i = 0; i < n2; i++) {
            arr2[i] = sc.nextInt();
        }

        // 合併已排序陣列
        int[] merged = new int[n1 + n2];
        System.arraycopy(arr1, 0, merged, 0, n1);
        System.arraycopy(arr2, 0, merged, n1, n2);
        Arrays.sort(merged);
        System.out.println("\n合併排序後陣列: " + Arrays.toString(merged));

        // 出現頻率最高的元素
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : merged) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        int maxFreq = 0, mostFreq = merged[0];
        for (var entry : freqMap.entrySet()) {
            if (entry.getValue() > maxFreq) {
                maxFreq = entry.getValue();
                mostFreq = entry.getKey();
            }
        }
        System.out.println("出現頻率最高的元素: " + mostFreq + " (次數: " + maxFreq + ")");

        // 分割陣列
        int mid = merged.length / 2;
        int[] left = Arrays.copyOfRange(merged, 0, mid);
        int[] right = Arrays.copyOfRange(merged, mid, merged.length);
        System.out.println("左半部: " + Arrays.toString(left));
        System.out.println("右半部: " + Arrays.toString(right));
    }
}
