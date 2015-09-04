package graph;

import graph.common.Edge;
import graph.common.FindUnion;
import graph.common.Graph;

import java.util.ArrayList;

/**
 * Created by Emin Guliyev on 19/07/2015.
 */
public class KruskalMinSpanningTree {
    public FindUnion findUnion;
    public ArrayList<Edge> sortedEdges;
    public Graph graph = new Graph();

    public void init(){
        graph.readFromConsole();
        findUnion = new FindUnion(graph.vertexCount);
        sortedEdges = graph.getSortedEdges();
    }

    public void printMinSpanningTree(){
        for (Edge edge: sortedEdges){
            if (findUnion.union(edge.from, edge.to)){
                System.out.println(edge.from + " -> " + edge.to);
            }
        }
    }
}
