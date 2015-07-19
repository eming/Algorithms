package graph;

import graph.common.AdjacentNode;
import graph.common.Graph;
import graph.common.Tuple;

import java.util.ArrayList;

/**
 * Created by Emin Guliyev on 19/07/2015.
 */
public class MaxFlow {
    public Graph graph = new Graph();

    public void init(){
        graph.readFromConsole();
    }

    public void printMaxFlowPaths(int from, int to){
        BreadthFirstTraversal breadthFirstTraversal = new BreadthFirstTraversal();
        breadthFirstTraversal.graph = graph;
        //considered that there is not <=0 cycles
        Integer[] path = breadthFirstTraversal.findShortUnweightedPath(from, to);
        ArrayList<Tuple> pathTuple = new ArrayList<Tuple>(Integer.max(path.length-1,0));
        double volume = getVolumeAndConvertPathToTuples(path, pathTuple);
        double volumeSum = 0;
        while (volume>0) {
            volumeSum+=volume;
            printFlow(path, volume);
            augmentPath(pathTuple, volume);
            //considered that there is not <=0 cycles
            path = breadthFirstTraversal.findShortUnweightedPath(from, to);
            pathTuple = new ArrayList<Tuple>(Integer.max(path.length-1,0));
            volume = getVolumeAndConvertPathToTuples(path, pathTuple);
        }

        System.out.println("Max flow is " + volumeSum);
    }

    private void printFlow(Integer[] path, double volume) {
        System.out.print("Flow path: ");
        for (int vertex: path){
            System.out.print(vertex + " -> ");
        }
        System.out.println(" Flow amount is "+ volume);
    }

    private void augmentPath(ArrayList<Tuple> pathTuple, double volume) {
        for (Tuple tuple: pathTuple){
            AdjacentNode adjacentNode = graph.vertices[tuple.fromVertex].get(tuple.toIndex);
            if (adjacentNode.weight == volume){
                graph.vertices[tuple.fromVertex].remove(tuple.toIndex);
            }else {
                adjacentNode.weight-=volume;
            }

            int xIndex = graph.getIndexOfAdjacencyNode(adjacentNode.to, tuple.fromVertex);
            graph.vertices[adjacentNode.to].get(xIndex).weight+=volume;
        }
    }

    private double getVolumeAndConvertPathToTuples(Integer[] path, ArrayList<Tuple> pathTuple) {
        double volume = Double.MAX_VALUE;
        for (int i = 0; i < path.length - 1; i++) {
            int x = path[i];
            int y = path[i+1];
            int yIndex = graph.getIndexOfAdjacencyNode(x, y);
            if (graph.vertices[x].get(yIndex).weight < volume){
                volume = graph.vertices[x].get(yIndex).weight;
            }
            pathTuple.add(new Tuple(x, yIndex));
        }
        return volume==Double.MAX_VALUE?0:volume;
    }
}
