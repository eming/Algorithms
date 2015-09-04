package dp;

import dp.common.Cell;
import dp.common.Decision;

/**
 * Created by Emin Guliyev on 20/07/2015.
 */
//first word is vertically second is horizontally
public class EditDistance {
    public Cell[][] cells;
    public String first;
    public String second;
    public int goalCellX;
    public int goalCellY;
    public String alignedFirst;
    public String alignedSecond;

    public void printStringsMatch(String first, String second){
        this.first=first;
        this.second=second;
        init();
        int[] decisions = new int[4];
        Decision[] values = Decision.values();

        for (int i = 1; i <= first.length(); i++) {
            for (int j = 1; j <= second.length(); j++) {
                //substitution
                decisions[0]=(first.charAt(i-1)!=second.charAt(j-1))
                        ?cells[i-1][j-1].cost+getPenalty(Decision.Substitution):Integer.MAX_VALUE;
                //match
                decisions[1]=(first.charAt(i-1)==second.charAt(j-1))
                        ?cells[i-1][j-1].cost:Integer.MAX_VALUE;
                //offsetInFirst
                decisions[2]=cells[i][j-1].cost+getPenalty(Decision.OffsetInFirst);
                //offsetInSecond
                decisions[3]=cells[i-1][j].cost+getPenalty(Decision.OffsetInSecond);
                Decision decision = Decision.Substitution;
                int min = decisions[0];
                for (int k = 1; k < 4; k++) {
                    if (decisions[k]<min){
                        decision = values[k];
                        min=decisions[k];
                    }
                }
                cells[i][j]=new Cell(min,decision);
            }
        }
        setGoalCellCoordinates();
        System.out.println("Distance is " + cells[goalCellX][goalCellY].cost);
        alignStrings();
        System.out.println(alignedFirst);
        System.out.println(alignedSecond);
    }

    private void setGoalCellCoordinates() {
        goalCellX = first.length();
        goalCellY = second.length();
    }

    private void alignStrings() {
        alignedFirst="";
        alignedSecond="";
        while (goalCellY>0||goalCellX>0){
            switch (cells[goalCellX][goalCellY].parent){
                case Substitution: {
                    alignedFirst=first.charAt(goalCellX-1) + alignedFirst;
                    alignedSecond='*' + alignedSecond;
                    goalCellX--;
                    goalCellY--;
                    break;
                }
                case Match:{
                    alignedFirst=first.charAt(goalCellX-1) + alignedFirst;
                    alignedSecond=second.charAt(goalCellY-1) + alignedSecond;
                    goalCellX--;
                    goalCellY--;
                    break;
                }
                case OffsetInFirst:{
                    alignedFirst = '-' + alignedFirst;
                    alignedSecond = second.charAt(goalCellY-1) + alignedSecond;
                    goalCellY--;
                    break;
                }
                case OffsetInSecond:{
                    alignedFirst = first.charAt(goalCellX-1) + alignedFirst;
                    alignedSecond = '-' + alignedSecond;
                    goalCellX--;
                    break;
                }
            }
        }
    }

    private void init() {
        cells = new Cell[first.length()+1][second.length()+1];
        for (int i = 0; i <= first.length(); i++) {
            cells[i][0]=new Cell(i, Decision.OffsetInSecond);
        }

        for (int j = 0; j <= second.length(); j++) {
            cells[0][j]=new Cell(j, Decision.OffsetInFirst);
        }
    }

    private int getPenalty(Decision decision) {
        return 1;
    }
}
