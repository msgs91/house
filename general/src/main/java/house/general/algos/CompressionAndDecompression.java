package house.general.algos;

public class CompressionAndDecompression {

    static final char BLOCK_OPEN = '[';
    static final char BLOCK_CLOSE = ']';


    public String decompress(String input) {
        StringBuilder output = new StringBuilder(input.length());

        char[] cs = input.toCharArray();
        int repeat = 0;
        for (int i = 0; i < cs.length;) {
            repeat = 0;
            while (isNumber(cs[i])) {
                repeat = repeat * 10 + Integer.valueOf(new StringBuilder().append(cs[i]).toString());
                i += 1;
            }
            StringBuilder pattern = new StringBuilder();
            String current;
            if (repeat > 0) {
                i += 1;
                while (i < cs.length && cs[i] != BLOCK_CLOSE) {
                    pattern.append(cs[i]);
                    i += 1;
                }
                i += 1;
                current = decompress(pattern.toString());
            }
            else {
                while (i < cs.length && !isNumber(cs[i])) {
                    pattern.append(cs[i]);
                    i += 1;
                }
                repeat = 1;
                current = pattern.toString();
            }

            while (repeat > 0) {
                output.append(current);
                repeat -= 1;
            }

        }
        return output.toString();
    }

    boolean isNumber(char c) {
//        System.out.printf("%c %b \n", c, c >= '0' && c <= '9');
        return c >= '0' && c <= '9';
    }

    public static void main(String[] args) {
       String[] inputs = new String[]{"abcd", "", "1[ab]", "2[ab]b", "ab2[ac]", "3[a2[bc]]"};

       CompressionAndDecompression algo = new CompressionAndDecompression();
       for(String input: inputs) {
           String decompressed = algo.decompress(input);
           System.out.printf("input = %s, output = %s \n", input, decompressed);
       }

    }
}
