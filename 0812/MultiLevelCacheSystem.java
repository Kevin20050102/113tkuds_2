import java.util.*;

public class MultiLevelCacheSystem {

    static class CacheEntry {
        int key;
        String value;
        int freq;
        long timestamp;

        public CacheEntry(int key, String value, int freq, long timestamp) {
            this.key = key;
            this.value = value;
            this.freq = freq;
            this.timestamp = timestamp;
        }
    }

    static class CacheLevel {
        int capacity;
        int cost;
        Map<Integer, CacheEntry> map;
        PriorityQueue<CacheEntry> heap;

        public CacheLevel(int capacity, int cost) {
            this.capacity = capacity;
            this.cost = cost;
            this.map = new HashMap<>();
            this.heap = new PriorityQueue<>(
                (a, b) -> {
                    if (a.freq == b.freq) return Long.compare(a.timestamp, b.timestamp);
                    return Integer.compare(a.freq, b.freq);
                }
            );
        }

        public boolean contains(int key) {
            return map.containsKey(key);
        }

        public CacheEntry get(int key) {
            return map.get(key);
        }

        public void put(CacheEntry entry) {
            if (map.containsKey(entry.key)) {
                heap.remove(map.get(entry.key));
            } else if (map.size() >= capacity) {
                evict();
            }
            map.put(entry.key, entry);
            heap.offer(entry);
        }

        public void evict() {
            CacheEntry toRemove = heap.poll();
            if (toRemove != null) {
                map.remove(toRemove.key);
            }
        }

        public void remove(int key) {
            CacheEntry entry = map.remove(key);
            if (entry != null) {
                heap.remove(entry);
            }
        }

        public void print(String levelName) {
            System.out.print(levelName + ": [");
            List<CacheEntry> list = new ArrayList<>(map.values());
            list.sort(Comparator.comparingInt(a -> a.key));
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i).key);
                if (i < list.size() - 1) System.out.print(",");
            }
            System.out.println("]");
        }
    }

    static class MultiCache {
        CacheLevel l1 = new CacheLevel(2, 1);
        CacheLevel l2 = new CacheLevel(5, 3);
        CacheLevel l3 = new CacheLevel(10, 10);
        long timestamp = 0;

        public void put(int key, String value) {
            removeIfExists(key); // 移除所有層中舊的
            CacheEntry entry = new CacheEntry(key, value, 1, timestamp++);
            insertToBestLevel(entry);
        }

        public String get(int key) {
            CacheEntry entry = null;
            if (l1.contains(key)) entry = l1.get(key);
            else if (l2.contains(key)) entry = l2.get(key);
            else if (l3.contains(key)) entry = l3.get(key);

            if (entry == null) return null;

            // 提升頻率與時間
            entry.freq++;
            entry.timestamp = timestamp++;

            // 移動資料到更高層（若值得）
            removeIfExists(key);
            insertToBestLevel(entry);

            return entry.value;
        }

        private void removeIfExists(int key) {
            l1.remove(key);
            l2.remove(key);
            l3.remove(key);
        }

        private void insertToBestLevel(CacheEntry entry) {
            // 根據存取頻率/成本選擇適合的層級
            if (entry.freq >= 3) {
                if (l1.map.size() < l1.capacity) {
                    l1.put(entry);
                } else {
                    l2.put(entry);
                }
            } else if (entry.freq == 2) {
                l2.put(entry);
            } else {
                l3.put(entry);
            }
        }

        public void printAll() {
            l1.print("L1");
            l2.print("L2");
            l3.print("L3");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MultiCache cache = new MultiCache();

        int n = sc.nextInt(); // 操作次數
        for (int i = 0; i < n; i++) {
            String op = sc.next();
            if (op.equals("put")) {
                int key = sc.nextInt();
                String value = sc.next();
                cache.put(key, value);
            } else if (op.equals("get")) {
                int key = sc.nextInt();
                String result = cache.get(key);
                System.out.println(result != null ? result : "null");
            } else if (op.equals("print")) {
                cache.printAll();
            }
        }
    }
}
