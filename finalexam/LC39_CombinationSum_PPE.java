import java.util.*;

public class LC39_CombinationSum_PPE {
    static void dfs(int[] nums, int target, int idx, List<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = idx; i < nums.length; i++) {
            if (nums[i] > target) continue;
            path.add(nums[i]);
            dfs(nums, target - nums[i], i, path, res);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), target = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, target, 0, new ArrayList<>(), res);
        for (List<Integer> list : res) {
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i));
                if (i != list.size() - 1) System.out.print(" ");
            }
            System.out.println();
        }
    }
}
