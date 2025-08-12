import java.util.*;

class Task {
    String name;
    int priority;
    long timestamp; 

    Task(String name, int priority, long timestamp) {
        this.name = name;
        this.priority = priority;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "[" + name + ", 優先級=" + priority + "]";
    }
}

public class PriorityQueueWithHeap {
    private List<Task> heap = new ArrayList<>();
    private long timeCounter = 0; // 模擬時間戳

    // 取得父、左、右節點
    private int parent(int i) { return (i - 1) / 2; }
    private int left(int i) { return 2 * i + 1; }
    private int right(int i) { return 2 * i + 2; }

    // 比較方法（優先級高者先，相同優先級依時間早者先）
    private boolean higherPriority(Task a, Task b) {
        if (a.priority != b.priority) {
            return a.priority > b.priority;
        }
        return a.timestamp < b.timestamp;
    }

    // 上浮
    private void heapifyUp(int i) {
        while (i > 0 && higherPriority(heap.get(i), heap.get(parent(i)))) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    // 下沉
    private void heapifyDown(int i) {
        int maxIndex = i;

        int l = left(i);
        if (l < heap.size() && higherPriority(heap.get(l), heap.get(maxIndex))) {
            maxIndex = l;
        }

        int r = right(i);
        if (r < heap.size() && higherPriority(heap.get(r), heap.get(maxIndex))) {
            maxIndex = r;
        }

        if (i != maxIndex) {
            swap(i, maxIndex);
            heapifyDown(maxIndex);
        }
    }

    // 交換
    private void swap(int i, int j) {
        Task temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // 添加任務
    public void addTask(String name, int priority) {
        Task newTask = new Task(name, priority, timeCounter++);
        heap.add(newTask);
        heapifyUp(heap.size() - 1);
    }

    // 執行最高優先級任務
    public Task executeNext() {
        if (heap.isEmpty()) return null;
        Task top = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        heapifyDown(0);
        return top;
    }

    // 查看下一個要執行的任務
    public Task peek() {
        return heap.isEmpty() ? null : heap.get(0);
    }

    // 修改任務優先級
    public boolean changePriority(String name, int newPriority) {
        for (int i = 0; i < heap.size(); i++) {
            if (heap.get(i).name.equals(name)) {
                heap.get(i).priority = newPriority;
                // 重新調整位置（上下浮都試一次）
                heapifyUp(i);
                heapifyDown(i);
                return true;
            }
        }
        return false;
    }

    // 是否為空
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    // 測試互動
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PriorityQueueWithHeap pq = new PriorityQueueWithHeap();

        while (true) {
            System.out.println("\n--- 優先級佇列功能選單 ---");
            System.out.println("1. 添加任務");
            System.out.println("2. 執行下一個任務");
            System.out.println("3. 查看下一個任務");
            System.out.println("4. 修改任務優先級");
            System.out.println("5. 離開");
            System.out.print("請選擇功能：");

            int choice = sc.nextInt();
            sc.nextLine(); // 清除換行

            switch (choice) {
                case 1:
                    System.out.print("請輸入任務名稱：");
                    String name = sc.nextLine();
                    System.out.print("請輸入任務優先級（數字越大越高）：");
                    int priority = sc.nextInt();
                    pq.addTask(name, priority);
                    System.out.println("已添加：" + name);
                    break;
                case 2:
                    Task executed = pq.executeNext();
                    if (executed == null) {
                        System.out.println("目前沒有任務可執行。");
                    } else {
                        System.out.println("正在執行任務：" + executed.name);
                    }
                    break;
                case 3:
                    Task next = pq.peek();
                    if (next == null) {
                        System.out.println("目前沒有任務。");
                    } else {
                        System.out.println("下一個要執行的任務：" + next.name + " (優先級=" + next.priority + ")");
                    }
                    break;
                case 4:
                    System.out.print("請輸入要修改的任務名稱：");
                    String target = sc.nextLine();
                    System.out.print("請輸入新的優先級：");
                    int newPriority = sc.nextInt();
                    if (pq.changePriority(target, newPriority)) {
                        System.out.println("已修改任務 " + target + " 的優先級為 " + newPriority);
                    } else {
                        System.out.println("找不到任務：" + target);
                    }
                    break;
                case 5:
                    System.out.println("程式結束。");
                    sc.close();
                    return;
                default:
                    System.out.println("選項無效，請重新輸入。");
            }
        }
    }
}
