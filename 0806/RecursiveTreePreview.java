import java.util.*;

public class RecursiveTreePreview {
    static int countFiles(Map<String, Object> folder) {
        int count = 0;
        for (Object value : folder.values()) {
            if (value instanceof Map) {
                count += countFiles((Map<String, Object>) value);
            } else {
                count++;
            }
        }
        return count;
    }

    static void printMenu(Map<String, Object> menu, int level) {
        for (String key : menu.keySet()) {
            System.out.println("  ".repeat(level) + "- " + key);
            if (menu.get(key) instanceof Map) {
                printMenu((Map<String, Object>) menu.get(key), level + 1);
            }
        }
    }

    static void flatten(Object[] arr, List<Object> flat) {
        for (Object obj : arr) {
            if (obj instanceof Object[]) {
                flatten((Object[]) obj, flat);
            } else {
                flat.add(obj);
            }
        }
    }

    static int maxDepth(Object[] arr) {
        int depth = 1;
        for (Object obj : arr) {
            if (obj instanceof Object[]) {
                depth = Math.max(depth, 1 + maxDepth((Object[]) obj));
            }
        }
        return depth;
    }

    public static void main(String[] args) {
        Map<String, Object> fileSystem = new HashMap<>();
        fileSystem.put("file1.txt", "content");
        Map<String, Object> subFolder = new HashMap<>();
        subFolder.put("file2.txt", "content");
        fileSystem.put("folder1", subFolder);
        System.out.println("總檔案數: " + countFiles(fileSystem));

        Map<String, Object> menu = new HashMap<>();
        menu.put("主選單1", Map.of("子選單1", Map.of("子子選單1", "")));
        menu.put("主選單2", "");
        printMenu(menu, 0);

        Object[] nested = {1, new Object[]{2, 3}, new Object[]{new Object[]{4, 5}, 6}};
        List<Object> flatList = new ArrayList<>();
        flatten(nested, flatList);
        System.out.println("展平後: " + flatList);
        System.out.println("最大深度: " + maxDepth(nested));
    }
}
