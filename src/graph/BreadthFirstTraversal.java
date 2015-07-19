package graph;

import graph.common.AdjacentNode;
import graph.common.Graph;
import graph.common.VertexStatus;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Emin Guliyev on 18/07/2015.
 */
public class BreadthFirstTraversal {
    public int[] parents;
    public VertexStatus[] vertexStatuses;
    public Graph graph;
    private Queue<Integer> queue;

    public void init() {
        graph = new Graph();
        graph.readFromConsole();
    }

    private void eraseAll() {
        vertexStatuses = new VertexStatus[graph.vertexCount];
        parents = new int[graph.vertexCount];
        for (int i = 0; i < graph.vertexCount; i++) {
            vertexStatuses[i] = VertexStatus.undiscovered;
            parents[i] = -1;
        }
        queue = new LinkedList<Integer>();
    }

    public void traverse(int start) {
        eraseAll();
        vertexStatuses[start] = VertexStatus.discovered;
        queue.add(start);
        while (!queue.isEmpty()) {
            int current = queue.poll();
            vertexStatuses[current] = VertexStatus.processed;
            processVertex(current);
            for (AdjacentNode adjacentNode : graph.vertices[current]) {
                if (vertexStatuses[adjacentNode.to] != VertexStatus.processed) {
                    processEdge(current, adjacentNode.to, adjacentNode.weight);
                }else {
                    if (vertexStatuses[adjacentNode.to] == VertexStatus.undiscovered) {
                        parents[adjacentNode.to] = current;
                        queue.add(adjacentNode.to);
                        vertexStatuses[adjacentNode.to] = VertexStatus.discovered;
                    }
                }
            }
        }
    }

    private void processVertex(int vertex) {
        //System.out.println(vertex);
    }

    private void processEdge(int from, int to, double weight) {
        //System.out.println("" + (from) + "-" + (to));
    }

    public void printShortUnweightedPath(int from, int to) {
        traverse(from);
        if (from == to) {
            System.out.println((from+1) + " " + (to+1));
        } else {
            if (parents[to] == -1) {
                System.out.println("There is no path");
            } else {
                String s = " "+(to+1);
                do  {
                    s = " " + (parents[to]+1) + s;
                    to=parents[to];
                }while (parents[to]==from);
                System.out.println(s);
            }
        }
    }
}

