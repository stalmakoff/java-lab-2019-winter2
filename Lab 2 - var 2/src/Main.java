import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner nScan = new Scanner(System.in);
        System.out.print("n: ");
        int n = nScan.nextInt();
        Scanner mScan = new Scanner(System.in);
        System.out.print("m: ");
        int m = mScan.nextInt();
        Scanner aScan = new Scanner(System.in);
        Scanner bScan = new Scanner(System.in);

        int[] massA = new int[n];
        for (int i = 0; i < n; i++)
        {
            System.out.print("a" + (n - i - 1) + ": ");
            massA[i] = aScan.nextInt();
        }

        int[] massB = new int[m];
        for (int i = 0; i < m; i++)
        {
            System.out.print("b" + (m - i - 1) + ": ");
            massB[i] = bScan.nextInt();
        }

        System.out.print("P1 = " + massA[0] + "x^" + (n - 1));
        for (int i = 1; i < n; i++)
        {
            if (i != (n - 1))
            {
                if (massA[i] < 0)
                    System.out.print(" - " + Math.abs(massA[i]) + "x^" + (n - i - 1));
                else
                    System.out.print(" + " + massA[i] + "x^" + (n - i - 1));
            } else
            {
                if (massA[i] < 0)
                    System.out.println(" - " + Math.abs(massA[i]));
                else
                    System.out.println(" + " + massA[i]);
            }
        }

        System.out.print("P2 = " + massB[0] + "x^" + (n - 1));
        for (int i = 1; i < m; i++)
        {
            if (i != (m - 1))
            {
                if (massB[i] < 0)
                    System.out.print(" - " + Math.abs(massB[i]) + "x^" + (m - i - 1));
                else
                    System.out.print(" + " + massB[i] + "x^" + (m - i - 1));
            } else
            {
                if (massB[i] < 0)
                    System.out.println(" - " + Math.abs(massB[i]));
                else
                    System.out.println(" + " + massB[i]);
            }
        }

        int[][] column_values = new int[m][n + (m - 1)];
        int[] P1P2 = new int[n + (m - 1)];
        int localX;

        for (int i = 0; i < m; i++)
        {
            localX = m + 1;
            if(i == 0)
                System.out.print(massB[m - i - 1] + " P1:    ");
            else
                System.out.print(massB[m - i - 1] + "x^" + i + " P1:    ");
            for (int j = 0; j < n + (m - 1); j++)
            {

                if (j >= (m - i - 1) && j < ((n + (m - 1)) - i))
                {
                    column_values[i][j] = massB[m - i - 1] * massA[j - m + i + 1];
                    if(column_values[i][j] > 0)
                        System.out.print(" + " + column_values[i][j] + "x^" + (localX--));
                    else
                        System.out.print(" - " + Math.abs(column_values[i][j]) + "x^" + (localX--));
                }
                else
                    System.out.print("\t");
            }
            System.out.println();
        }
        localX = n + (m - 1) - 1;
        System.out.print("P1*P2 = \t");
        for (int i = 0; i < n + (m - 1); i++)
        {
            for (int j = 0; j < m; j++)
            {
                P1P2[i] += column_values[j][i];
            }
            if(i == 0)
            {
                System.out.print(P1P2[i] + "x^" + (localX--));
            }
            else
            {
                if (P1P2[i] > 0)
                    System.out.print(" + " + P1P2[i] + "x^" + (localX--));
                else
                    System.out.print(" - " + Math.abs(P1P2[i]) + "x^" + (localX--));
            }
        }
    }
}
