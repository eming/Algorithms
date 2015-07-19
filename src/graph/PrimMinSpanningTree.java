package graph;

import graph.common.AdjacentNode;

/**
 * Created by Emin Guliyev on 19/07/2015.
 */
public class PrimMinSpanningTree extends PrimOrDijkstra{
    protected void minimize(int current, AdjacentNode adjacentNode) {
        if (!inTree[adjacentNode.to] && distance[adjacentNode.to]>adjacentNode.weight){
            distance[adjacentNode.to]=adjacentNode.weight;
            parents[adjacentNode.to]=current;
        }
    }

    public void printMinSpanningTree(){
        int current = 0;
        mainLogic(current);

        for (int i = 0; i < graph.vertexCount; i++) {
            if (parents[i] != -1){
                System.out.println(parents[i] + " -> " + i);
            }
        }
    }
}
