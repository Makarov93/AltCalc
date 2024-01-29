import java.awt.desktop.SystemEventListener;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        System.out.println(sumDig(n));
    }
    public static int sumDig(int n){
        int sum = 0;
        while (n !=0) {
            sum += n % 10;
            n /= 10;
        } return sum;
}
}
