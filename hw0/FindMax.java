public class FindMax {
        /** Returns the maximum value from m. */
    public static int max(int[] m) {
        int tempmax = m[0];
        int length = m.length;
        for (int i = 0; i < length; i += 1) {
            if (m[i] > tempmax) {
                tempmax = m[i];
            }
        }
        return tempmax;
    }
    public static void main(String[] args) {
        int[] numbers = new int[] { 9, 2, 15, 2, 22, 10, 6 };
        int maxx = max(numbers);
        System.out.println(maxx);
    }
}
