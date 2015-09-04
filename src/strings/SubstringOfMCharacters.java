package strings;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Emin Guliyev on 23/08/2015.
 */
//http://www.careercup.com/question?id=5665888671891456
class Info{
    char c;
    int last;

    Info(char c, int last) {
        this.c = c;
        this.last = last;
    }
}
public class SubstringOfMCharacters {
    ArrayList<Info> mChars=new ArrayList<>();
    HashMap<Character, Info> infos = new HashMap<>();

    public String getSubstring(String s, int m){
        int maxLength=0, currentLength=0, currentBegin=0, maxBegin=0;
        for (int i=0; i< s.length(); i++){
            char c = s.charAt(i);
            Info info;
            if (infos.containsKey(c)){
                info = infos.get(c);
            }else{
                info = new Info(c, 0);
                infos.put(c, info);
            }
            if (!mChars.contains(info)){
                if (mChars.size() == m){
                    Info firstInfo = mChars.remove(0);
                    while(mChars.size()>0 && mChars.get(0).last < firstInfo.last){
                        mChars.remove(0);
                    }
                    currentBegin = firstInfo.last + 1;
                    currentLength = i - firstInfo.last - 1;
                }
                mChars.add(info);
            }
            info.last = i;
            currentLength++;
            if (maxLength < currentLength){
                maxBegin = currentBegin;
                maxLength = currentLength;
            }
        }

        String result = s.substring(maxBegin, maxBegin + maxLength);
        return result;
    }

    public static void main(String[] args) {
        SubstringOfMCharacters substringOfMCharacters = new SubstringOfMCharacters();
        System.out.println(substringOfMCharacters.getSubstring("abbaccccaiiiiaabba", 2));
        System.out.println(substringOfMCharacters.getSubstring("aabacbeabbed", 2));
    }
}
