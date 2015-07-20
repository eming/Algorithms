package dp;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Emin Guliyev on 20/07/2015.
 */
public class LongestMonotoneSubSequence {
    public int[] sequence;
    public int[] parents;
    public int[] subSequenceEndingAt;

    public void printLongestMonotoneSubSequence(int[] sequence){
        init(sequence);

        for (int i = 1; i < subSequenceEndingAt.length; i++) {
            for (int j = 0; j < i; j++) {
                if ((sequence[j]<=sequence[i]) && (subSequenceEndingAt[j]+1>subSequenceEndingAt[i])){
                    subSequenceEndingAt[i]=subSequenceEndingAt[j]+1;
                    parents[i]=j;
                }
            }
        }

        System.out.println("Length of max sequence is "
                +subSequenceEndingAt[sequence.length - 1]);

        int endingOfMax = getEndingOfMaxMonoSubSequence();

        ArrayList<Integer> list = constructMaxMonoSubSequence(endingOfMax);

        printList(list);
    }

    private void printList(ArrayList<Integer> list) {
        System.out.print("Max length monotone sub sequence: ");
        for (int item : list){
            System.out.print(item + " ");
        }
        System.out.println();
    }

    private ArrayList<Integer> constructMaxMonoSubSequence(int endingOfMax) {
        ArrayList<Integer> list = new ArrayList<Integer>(subSequenceEndingAt[endingOfMax]);
        do{
            list.add(sequence[endingOfMax]);
            endingOfMax=parents[endingOfMax];
        }while (endingOfMax!=-1);

        Collections.reverse(list);
        return list;
    }

    private int getEndingOfMaxMonoSubSequence() {
        int maxLength = 0;
        int endingOfMax = -1;
        for (int i = 0; i < subSequenceEndingAt.length; i++) {
            if (maxLength<subSequenceEndingAt[i]){
                maxLength=subSequenceEndingAt[i];
                endingOfMax=i;
            }
        }
        return endingOfMax;
    }

    private void init(int[] sequence) {
        this.sequence = sequence;

        parents = new int[sequence.length];
        subSequenceEndingAt = new int[sequence.length];

        for (int i = 0; i < parents.length; i++) {
            parents[i] = -1;
            subSequenceEndingAt[i]=1;
        }
    }
}
