package classtype;

public class T {

    public static void main(String[] args) {
        for(int i = 10; i< 50; i++)
            t(i);
    }

    public static void t(int n) {
        int x, y;
        System.out.print("Input["+n+"]\t Output[");
        for(x = (int)Math.sqrt(2*n) - 1; x > 1; x --) {

            if(x%2 != 0 && n%x == 0) {

                for(y = 1; y <= x; y++) {

                    System.out.print(n/x - x/2 - 1 + y + "\t");
                }
                System.out.print("]\n");
                break;

            }
            else if(x%2 ==0 && n%x == x/2) {

                for(y = 1; y <=x; y++) {

                    System.out.print(n/x - (x + 1)/2 + y + "\t");
                }
                System.out.print("]\n");
                break;
            }

        }
        if(x == 1)
            System.out.print(n+"\t]\n");
    }
}
