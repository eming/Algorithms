package graph;

import graph.common.AdjacentNode;

/**
 * Created by Emin Guliyev on 19/07/2015.
 */
public class Dijkstra extends PrimOrDijkstra{
    protected void minimize(int current, AdjacentNode adjacentNode) {
        if (!inTree[adjacentNode.to]
                && distance[adjacentNode.to]>distance[current] + adjacentNode.weight){
            distance[adjacentNode.to]=distance[current] + adjacentNode.weight;
            parents[adjacentNode.to]=current;
        }
    }

    public void printMinPath(int from,int to){
        mainLogic(from);
        String result = "";
        while(parents[to]!=-1){
            result = " -> " + to + result;
            to = parents[to];
        }
        if (result.isEmpty()){
            if (from==to){
                System.out.println("We are at destination");
            }else {
                System.out.println("No any path!");
            }
        }else {
            System.out.println(from + result);
        }
    }
}
