package graph;

import graph.common.Edge;
import graph.common.Graph;
import graph.common.SetUnion;

import java.util.ArrayList;

/**
 * Created by Emin Guliyev on 19/07/2015.
 */
public class KruskalMinSpanningTree {
    public SetUnion setUnion;
    public ArrayList<Edge> sortedEdges;
    public Graph graph = new Graph();

    public void init(){
        graph.readFromConsole();
        setUnion = new SetUnion(graph.vertexCount);
        sortedEdges = graph.getSortedEdges();
    }

    public void printMinSpanningTree(){
        for (Edge edge: sortedEdges){
            if (setUnion.union(edge.from, edge.to)){
                System.out.println(edge.from + " -> " + edge.to);
            }
        }
    }
}
