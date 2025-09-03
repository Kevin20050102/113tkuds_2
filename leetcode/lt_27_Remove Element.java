class Solution {
    public int removeElement(int[] nums, int val) {
        // 使用一個指標 k，代表目前應該放置不等於 val 的元素的位置
        int k = 0;

        // 遍歷整個陣列
        for (int i = 0; i < nums.length; i++) {
            // 如果當前元素不是 val，就把它放到 nums[k] 位置
            if (nums[i] != val) {
                nums[k] = nums[i];
                k++; // 移動指標，表示多了一個有效元素
            }
        }

        // 此時陣列的前 k 個元素就是「不等於 val 的元素」
        // 後面的元素不需要理會，題目說不重要
        return k;
    }
}
