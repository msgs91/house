package house.general;

class MissingInteger {
    public int firstMissingPositive(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            visited[i] = false;
        }

        for (int i = 0; i < nums.length; i++) {
//            print(nums);
//            print(visited);
            if (!visited[i] && nums[i] == 0) {
//                System.out.printf("Setting %d to -1 ", i);
                nums[i] = -1;
            } else if (!visited[i]) {
                int next = nums[i];
                while ((next - 1) >= 0 && next-1 < nums.length && !visited[next -1]) {
//                    print(nums);
//                    print(visited);
                    visited[next - 1] = true;
                    int prev = next - 1;
                    next = nums[next - 1];
//                    System.out.printf("prev = %d, next = %d", prev, next);
                    nums[prev] = 0;
                }
            }
        }
//        print(nums);
//        print(visited);
        int i;
        for (i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                break;
            }
        }

//        System.out.printf("ans = %d", i+1);
        return i+1;
    }

    void print(int[] a) {
        System.out.println();
        for (int i : a) {
            System.out.printf("%d ", i);
        }
        System.out.println();
    }

    void print(boolean[] a) {
        System.out.println();
        for (boolean i : a) {
            System.out.printf("%b ", i);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        new MissingInteger().firstMissingPositive(new int[]{2});
    }
}
