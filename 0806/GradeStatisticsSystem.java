import java.util.*;

public class GradeStatisticsSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("請輸入學生人數: ");
        int n = sc.nextInt();
        int[] scores = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("請輸入第 " + (i + 1) + " 位學生的成績: ");
            scores[i] = sc.nextInt();
        }

        int sum = 0, max = scores[0], min = scores[0];
        for (int score : scores) {
            sum += score;
            if (score > max) max = score;
            if (score < min) min = score;
        }
        double avg = sum / (double) n;

        int a = 0, b = 0, c = 0, d = 0, f = 0;
        for (int score : scores) {
            if (score >= 90) a++;
            else if (score >= 80) b++;
            else if (score >= 70) c++;
            else if (score >= 60) d++;
            else f++;
        }

        int aboveAvg = 0;
        for (int score : scores) {
            if (score > avg) aboveAvg++;
        }

        System.out.println("\n=== 成績統計報表 ===");
        System.out.println("平均分: " + avg);
        System.out.println("最高分: " + max);
        System.out.println("最低分: " + min);
        System.out.println("A等第人數: " + a);
        System.out.println("B等第人數: " + b);
        System.out.println("C等第人數: " + c);
        System.out.println("D等第人數: " + d);
        System.out.println("F等第人數: " + f);
        System.out.println("高於平均的人數: " + aboveAvg);
    }
}
