package dp;

/**
 * Created by Emin Guliyev on 20/07/2015.
 */
public class LinearPartition {
    public int[] sequence;
    public int k;
    public int[][] maxSum;
    public int[][] lastDivisor;
    public int[] sumUntil;

    public void printPartitionResults(int[] sequence, int k){
        init(sequence, k);

        for (int i = 1; i < sequence.length; i++) {
            for (int j = 2; j <= k; j++) {
                maxSum[i][j] = Integer.MAX_VALUE;
                for (int x = 0; x < i; x++) {
                    int cost = Integer.max(maxSum[x][j - 1], sumUntil[i] - sumUntil[x]);
                    if (cost < maxSum[i][j]){
                        maxSum[i][j] = cost;
                        lastDivisor[i][j]=x;
                    }
                }
            }
        }

        constructPartitionsAndPrint();
    }

    private void constructPartitionsAndPrint() {
        int lastIndex = sequence.length-1;
        int kk = k;
        while(kk>1){
            printFromTo(lastDivisor[lastIndex][kk] +1 , lastIndex);
            lastIndex = lastDivisor[lastIndex][kk];
            kk--;
        }
        printFromTo(0, lastIndex);
    }

    private void printFromTo(int from, int to) {
        for (int i = from; i <= to; i++) {
            System.out.print(sequence[i] + " ");
        }
        System.out.println();
    }

    private void init(int[] sequence, int k) {
        this.sequence = sequence;
        this.k = k;
        maxSum = new int[sequence.length][k+1];
        lastDivisor = new int[sequence.length][k+1];
        sumUntil = new int[sequence.length];

        sumUntil[0]=sequence[0];
        for (int i = 1; i < sumUntil.length; i++) {
            sumUntil[i]=sumUntil[i-1] + sequence[i];
        }

        for (int i = 0; i < sequence.length; i++) {
            maxSum[i][1] = sumUntil[i];
        }

        for (int i = 1; i < k; i++) {
            maxSum[0][i] = sequence[0];
        }
    }
}
