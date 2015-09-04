package dp;

import java.awt.geom.Point2D;

/**
 * Created by Emin Guliyev on 20/07/2015.
 */
//page 312
public class MinimumWeightTriangulation {
    public Point2D[] polygon;
    public int length;
    public double[][] minTriangulation;
    public int[][] middleVertex;
    public double[][] chords;

    public void printChords(Point2D[] polygon){
        init(polygon);
        double current;
        for (int gap = 3; gap <= length-1; gap++) {
            for (int i = 0; i < length - gap; i++) {
                int j = i + gap;
                minTriangulation[i][j] = Double.MAX_VALUE;
                for (int k = i+1; k <= j-1; k++) {
                    current = minTriangulation[i][k]+minTriangulation[k][j]
                            + ((k-1==i)?0:chords[i][k]) + ((k+1==j)?0:chords[k][j]);
                    if (current < minTriangulation[i][j]){
                        middleVertex[i][j]=k;
                        minTriangulation[i][j] = current;
                    }
                }
            }
        }

        System.out.println("Min triangulation value is " + minTriangulation[0][length - 1]);
        constructAndPrintChords(0, length - 1);
    }

    private void constructAndPrintChords(int i, int j) {
        if (j-i<=2){
            return;
        }

        if (i + 1 != middleVertex[i][j]){
            System.out.println(i + " -> " + middleVertex[i][j]);
        }
        if (j - 1 != middleVertex[i][j]){
            System.out.println(j + " -> " + middleVertex[i][j]);
        }
        constructAndPrintChords(i, middleVertex[i][j]);
        constructAndPrintChords(middleVertex[i][j], j);
    }

    private void init(Point2D[] polygon) {
        this.polygon = polygon;
        this.length = polygon.length;
        minTriangulation = new double[length][length];
        middleVertex = new int[length][length];
        chords = new double[length][length];

        minTriangulation[length-1][length-1]=0;
        minTriangulation[length-2][length-2]=0;
        minTriangulation[length-2][length-1]=0;
        for (int i = 0; i < length - 2; i++) {
            minTriangulation[i][i]=0;
            minTriangulation[i][i+1]=0;
            minTriangulation[i][i+2]=0;
        }

        for (int i = 0; i < length-1; i++) {
            for (int j = i+1; j < length; j++) {
                chords[i][j] = Point2D.distance(polygon[i].getX(), polygon[i].getY()
                        , polygon[j].getX(), polygon[j].getY());
            }
        }
    }
}
