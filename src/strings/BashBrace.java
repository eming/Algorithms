package strings;

import java.util.ArrayList;

/**
 * Created by Emin Guliyev on 24/08/2015.
 */
//http://www.careercup.com/question?id=5717301963784192
public class BashBrace {
    private int i=0;
    public String s;

    public BashBrace(String s) {
        this.s = "(" + s + ")";
    }

    public ArrayList<String> getStringArray() {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> temp;
        if (s.charAt(i) == '(') {
            i++;
            while (s.charAt(i-1) != ')') {
                temp = getStringArray();
                result.addAll(temp);
                i++;// skip "," or ")"
            }
            if (i == s.length()) {
                return result;
            }
            result = getCartesianProductStringArray(result);
        } else {
            String literal = "";
            while (i < s.length() && s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                literal += s.charAt(i);
                i++;
            }
            result.add(literal);
            result = getCartesianProductStringArray(result);
        }

        return result;
    }

    private ArrayList<String> getCartesianProductStringArray(ArrayList<String> result) {
        ArrayList<String> temp;
        while (i < s.length() && s.charAt(i) != ',' && s.charAt(i) != ')') {
            temp = getStringArray();
            result = cartesianProduct(result, temp);
        }
        return result;
    }

    private ArrayList<String> cartesianProduct(ArrayList<String> listA, ArrayList<String> listB) {
        ArrayList<String> result = new ArrayList<>();
        for (String a: listA) {
            for (String b : listB) {
                result.add(a+b);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        BashBrace bashBrace = new BashBrace("((a,b)o(m,n)p,b)");

        for (String item : bashBrace.getStringArray()){
            System.out.println(item);
        }
    }
}
