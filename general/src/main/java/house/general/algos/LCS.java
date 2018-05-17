package house.general.algos;

public class LCS {

    static int max(int a, int b) {
        if (a > b) return a;
        return b;
    }

    static void printTable(int[][] table) {
        System.out.println();
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                System.out.printf("%d ", table[i][j]);
            }
            System.out.println();
        }
    }

    static int lcs(String s1, String s2, int i, int j, int[][] table) {
        //means comparing empty string with another string
        if (i < 0 || j < 0) return 0;
        System.out.printf("Comparing %d %d \n", (i), (j));
//        printTable(table);
        if (s1.charAt(i) == s2.charAt(j)) {
            int size = 1;
            if (i > 0 && j > 0) {
                if (table[i-1][j-1] != -1){
                    System.out.printf("Found %d, %d in table \n", i-1, j-1);
                    size = 1 + table[i-1][j-1];
                } else {
                    size = 1 + lcs(s1, s2, i-1, j-1, table);
                }
            }
            return size;
        } else {
            int sizeLeft = 0;
            if (i > 0 && j >= 0) {
                if (table[i - 1][j] != -1) {
                    sizeLeft = table[i - 1][j];
                    System.out.printf("Found %d, %d in table \n", i-1, j);

                } else {
                    sizeLeft = lcs(s1, s2, i - 1, j, table);
                    table[i - 1][j] = sizeLeft;
                }
            }

            int sizeRight = 0;
            if (i >= 0 && j > 0) {
                if (table[i][j-1] != -1) {
                    sizeRight = table[i][j-1];
                    System.out.printf("Found %d, %d in table \n", i, j-1);
                } else {
                    sizeRight = lcs(s1, s2, i, j - 1, table);
                    table[i][j - 1] = sizeRight;
                }
            }
            return max(sizeLeft, sizeRight);
        }
    }

    static int lcs(String s1, String s2) {
        int[][] table = new int[s1.length()][s2.length()];
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                table[i][j] = -1;
            }
        }
        return lcs(s1, s2, s1.length()-1, s2.length()-1, table);
    }

    public static void main(String[] args) {
        String[][] inputs = new String[][]{{"ganapathy", "gGaAnNaApPaAtThHy"}, {"abcd", ""}, {"abcdef", "acf"}};

        for (int i = 0; i < inputs.length; i++) {
            int ans = lcs(inputs[i][0], inputs[i][1]);
            System.out.printf("s1= %s, s2= %s, ans= %d", inputs[i][0], inputs[i][1], ans);
        }

    }
}
