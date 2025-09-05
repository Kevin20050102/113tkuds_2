import java.util.*;

public class LC04_Median_QuakeFeeds {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        double[] A = new double[n];
        double[] B = new double[m];
        for (int i = 0; i < n; i++) A[i] = sc.nextDouble();
        for (int j = 0; j < m; j++) B[j] = sc.nextDouble();
        if (n > m) {
            double[] tmp = A; A = B; B = tmp;
            int t = n; n = m; m = t;
        }
        int total = n + m;
        int half = (total + 1) / 2;
        int lo = 0, hi = n;
        while (lo <= hi) {
            int i = (lo + hi) / 2;
            int j = half - i;
            double Aleft = (i == 0) ? Double.NEGATIVE_INFINITY : A[i - 1];
            double Aright = (i == n) ? Double.POSITIVE_INFINITY : A[i];
            double Bleft = (j == 0) ? Double.NEGATIVE_INFINITY : B[j - 1];
            double Bright = (j == m) ? Double.POSITIVE_INFINITY : B[j];
            if (Aleft <= Bright && Bleft <= Aright) {
                if (total % 2 == 1) {
                    System.out.printf("%.1f\n", Math.max(Aleft, Bleft));
                } else {
                    double val = (Math.max(Aleft, Bleft) + Math.min(Aright, Bright)) / 2.0;
                    System.out.printf("%.1f\n", val);
                }
                return;
            } else if (Aleft > Bright) {
                hi = i - 1;
            } else {
                lo = i + 1;
            }
        }
    }
}
