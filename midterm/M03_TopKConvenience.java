import java.util.*;

public class M03_TopKConvenience {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int K = sc.nextInt();

        // 讀入所有商品
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int qty = sc.nextInt();
            items.add(new Item(name, qty));
        }

        // 使用 Min-Heap 維護前 K 名
        PriorityQueue<Item> minHeap = new PriorityQueue<>(K, new Comparator<Item>() {
            @Override
            public int compare(Item a, Item b) {
                if (a.qty != b.qty) {
                    return a.qty - b.qty; // qty 小的優先出堆
                }
                return a.name.compareTo(b.name); // tie-break: 字典序 (也可改成輸入順序)
            }
        });

        for (Item it : items) {
            if (minHeap.size() < K) {
                minHeap.offer(it);
            } else if (compareForTop(it, minHeap.peek()) > 0) {
                // 如果 it 比堆頂更大 → 取代
                minHeap.poll();
                minHeap.offer(it);
            }
        }

        // 把堆中的元素取出，照 "大到小" 排序輸出
        List<Item> result = new ArrayList<>(minHeap);
        result.sort((a, b) -> {
            if (b.qty != a.qty) return b.qty - a.qty; // 高到低
            return a.name.compareTo(b.name); // tie-break
        });

        for (Item it : result) {
            System.out.println(it.name + " " + it.qty);
        }
    }

    // 幫助函式：判斷 a 是否比 b 更適合進入 Top-K
    private static int compareForTop(Item a, Item b) {
        if (a.qty != b.qty) return a.qty - b.qty;
        return -a.name.compareTo(b.name); 
    }

    static class Item {
        String name;
        int qty;
        Item(String n, int q) {
            this.name = n;
            this.qty = q;
        }
    }
}
