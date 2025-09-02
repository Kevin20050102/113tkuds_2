import java.util.*;

class Solution {
    public int romanToInt(String s) {
        // 建立符號對應表
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int total = 0;
        
        // 從左到右掃描
        for (int i = 0; i < s.length(); i++) {
            int value = map.get(s.charAt(i));
            
            // 如果不是最後一個字元，且當前數值 < 下一個數值 → 減法情況
            if (i + 1 < s.length() && value < map.get(s.charAt(i + 1))) {
                total -= value;
            } else {
                total += value;
            }
        }

        return total;
    }
}