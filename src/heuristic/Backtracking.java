package heuristic;

/**
 * Created by Emin Guliyev on 19/07/2015.
 */
//in the example of all possible subsets
public class Backtracking {
    public boolean isFinished;

    public void backtrack(boolean a[], int k, int input){
        if (isSolution(a,k,input)){
            processSolution(a,k,input);
        }else{
            k++;
            boolean[] candidates = constructCandidates(a,k,input);
            for (boolean candidate:candidates) {
                a[k] = candidate;
                makeMove(a, k, input);
                backtrack(a, k, input);
                unmakeMove(a, k, input);
                if (isFinished){
                    return;
                }
            }
        }
    }

    private void makeMove(boolean[] a, int k, int input) {

    }

    private void unmakeMove(boolean[] a, int k, int input) {

    }

    private boolean[] constructCandidates(boolean[] a, int k, int input) {
        return new boolean[]{true,false};
    }

    private void processSolution(boolean[] a, int k, int input) {
        System.out.print("{");
        for (int i = 0; i < a.length; i++) {
            if (a[i]){
                System.out.print((i+1) + " ");
            }
        }
        System.out.println("}");
    }

    private boolean isSolution(boolean[] a, int k, int input) {
        return k==input-1;
    }
}
