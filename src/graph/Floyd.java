package graph;

import graph.common.Graph;

/**
 * Created by Emin Guliyev on 19/07/2015.
 */
public class Floyd {
    public double[][] adjacencyMatrix;
    public Graph graph = new Graph();

    public void init(){
        graph.readFromConsole();
    }

    public void calculateAllShortestPathLengths(){
        adjacencyMatrix = graph.getAdjacencyMatrix();
        for (int k = 0; k < graph.vertexCount; k++) {
            for (int i = 0; i < graph.vertexCount; i++) {
                for (int j = 0; j < graph.vertexCount; j++) {
                    double throughK = adjacencyMatrix[i][k] + adjacencyMatrix[k][j];
                    if (adjacencyMatrix[i][j] > throughK){
                        adjacencyMatrix[i][j]= throughK;
                    }
                }
            }
        }
    }

    public void printAllShortestPathLengths() {
        for (int i = 0; i < graph.vertexCount; i++) {
            for (int j = 0; j < graph.vertexCount; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
