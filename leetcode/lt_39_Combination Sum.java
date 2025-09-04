import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int target, int start, List<Integer> path, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(path)); // 加入一個合法解
            return;
        }
        if (target < 0) {
            return; // 剪枝
        }

        for (int i = start; i < candidates.length; i++) {
            path.add(candidates[i]); // 選擇
            backtrack(candidates, target - candidates[i], i, path, result); // 可重複選 i
            path.remove(path.size() - 1); // 回溯
        }
    }
}
