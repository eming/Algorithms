package karma;

/**
 * Created by Emin Guliyev on 23/08/2015.
 */
//http://www.careercup.com/question?id=5690323227377664
public class AABB {
    public static void main(String[] args) {
        int current = 14;
        for (int i = 1; i <= 15; i++) {
            doIt(i);
        }
    }

    private static void doIt(int current) {
        int i=1;
        while (current > Math.pow(2,i)){
            current-=Math.pow(2,i);
            i++;
        }
        while (i>0){
            if (current > Math.pow(2,i-1)){
                System.out.print("B");
                current-=Math.pow(2,i-1);
            }else {
                System.out.print("A");
            }
            i--;
        }
        System.out.println();
    }
}
