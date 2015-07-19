package graph;

import graph.common.AdjacentNode;
import graph.common.Graph;

/**
 * Created by Emin Guliyev on 19/07/2015.
 */
public abstract class PrimOrDijkstra {
    public boolean[] inTree;
    public double[] distance;
    public int[] parents;
    public Graph graph = new Graph();

    public void init(){
        graph.readFromConsole();
    }

    public void mainLogic(int current){
        eraseAll();
        distance[current] = 0;
        while (!inTree[current]){
            inTree[current] = true;
            for (AdjacentNode adjacentNode:graph.vertices[current]){
                minimize(current, adjacentNode);
            }

            double min = Double.MAX_VALUE;
            for (int i = 0; i < graph.vertexCount; i++) {
                if (!inTree[i] && distance[i] < min){
                    min = distance[i];
                    current = i;
                }
            }
        }
    }

    protected abstract void minimize(int current, AdjacentNode adjacentNode);


    protected void eraseAll() {
        inTree = new boolean[graph.vertexCount];
        distance = new double[graph.vertexCount];
        parents = new int[graph.vertexCount];
        for (int i = 0; i < graph.vertexCount; i++) {
            inTree[i] = false;
            distance[i] = Double.MAX_VALUE;
            parents[i] = -1;
        }
    }
}
