import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // 排序以便去重
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int target, int start, List<Integer> path, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(path)); // 找到一組合法解
            return;
        }
        if (target < 0) return; // 超過，剪枝

        for (int i = start; i < candidates.length; i++) {
            // 跳過同層的重複數字
            if (i > start && candidates[i] == candidates[i - 1]) continue;

            path.add(candidates[i]); // 選擇
            backtrack(candidates, target - candidates[i], i + 1, path, result); // i+1 → 每個數字只能用一次
            path.remove(path.size() - 1); // 回溯
        }
    }
}
