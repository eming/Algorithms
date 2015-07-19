package graph;

import graph.common.AdjacentNode;
import graph.common.Graph;
import graph.common.VertexStatus;

import java.util.ArrayList;
import java.util.Collections;
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
                if (vertexStatuses[adjacentNode.to] != VertexStatus.processed || graph.isDirected) {
                    processEdge(current, adjacentNode.to, adjacentNode.weight);
                }
                if (vertexStatuses[adjacentNode.to] == VertexStatus.undiscovered) {
                    parents[adjacentNode.to] = current;
                    queue.add(adjacentNode.to);
                    vertexStatuses[adjacentNode.to] = VertexStatus.discovered;
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
        Integer[] path = findShortUnweightedPath(from, to);
        if (path.length == 0){
            System.out.println("No such path");
        }else {
            for (Integer vertex: path){
                System.out.print(vertex + " ");
            }
            System.out.println();
        }
    }

    public Integer[] findShortUnweightedPath(int from, int to) {
        traverse(from);
        Integer[] result = null;
        if (from == to) {
            result = new Integer[]{from, to};
        } else {
            if (parents[to] == -1) {
                result = new Integer[0];
            } else {
                ArrayList<Integer> list = new ArrayList<Integer>();
                list.add(to);
                while (parents[to]!=-1) {
                    to=parents[to];
                    list.add(to);
                }
                Collections.reverse(list);
                result = list.toArray(new Integer[list.size()]);
            }
        }
        return result;
    }
}

