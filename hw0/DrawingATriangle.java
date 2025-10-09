public class DrawingATriangle {
    public static void DrawnTriangle(int n) {
        for (int i = 0; i < n; i += 1) {
            for (int j = 0; j < i + 1; j += 1) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        DrawnTriangle(10);
    }
}
