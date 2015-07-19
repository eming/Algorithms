package graph.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by Emin Guliyev on 18/07/2015.
 */
//zero based naming for vertices
public class Graph {
    public int vertexCount;
    public int edgeCount;
    public ArrayList<AdjacentNode>[] vertices;
    public boolean isDirected = false;
    public void init(int vertexCount) {
        this.vertexCount = vertexCount;
        vertices = new ArrayList[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            vertices[i] = new ArrayList<AdjacentNode>(vertexCount);
        }
    }

    public void addEdge(int from, int to, double weight){
        vertices[from].add(new AdjacentNode(to, weight));
        if (!isDirected){
            vertices[to].add(new AdjacentNode(from, weight));
        }
    }

    public void readFromConsole() {
        Scanner scanIn = new Scanner(System.in);
        vertexCount = scanIn.nextInt();
        init(vertexCount);
        edgeCount = scanIn.nextInt();
        int realEdgeCount = edgeCount;
        for (int i = 0; i < edgeCount; i++) {
            int from = scanIn.nextInt();
            int to = scanIn.nextInt();
            double weight = scanIn.nextDouble();
            if (weight != 0){
                addEdge(from,to,weight);
            }else {
                realEdgeCount--;
            }
        }
        edgeCount = realEdgeCount;
        if (!isDirected){
            edgeCount *= 2;
        }
    }

    public ArrayList<Edge> getSortedEdges(){
        ArrayList<Edge> result = new ArrayList<Edge>(isDirected?edgeCount:edgeCount/2);
        for (int i = 0; i < vertexCount; i++) {
            for (AdjacentNode adjacentNode: vertices[i]){
                if (isDirected  || i<adjacentNode.to){
                    result.add(new Edge(i,adjacentNode.to,adjacentNode.weight));
                }
            }
        }
        Collections.sort(result);
        return result;
    }

    public double[][] getAdjacencyMatrix(){
        double[][] result = new double[vertexCount][vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                if (i == j) {
                    result[i][j] = 0;
                } else {
                    result[i][j] = Double.MAX_VALUE;
                }
            }
        }
        for (int i = 0; i < vertexCount; i++) {
            for (AdjacentNode adjacentNode:vertices[i]){
                result[i][adjacentNode.to]=adjacentNode.weight;
            }
        }
        return result;
    }

    public int getIndexOfAdjacencyNode(int x, int y) {
        for (int i = 0; i < vertices[x].size(); i++) {
            if (vertices[x].get(i).to==y){
                return i;
            }
        }
        //if not found then add
        vertices[x].add(new AdjacentNode(y,0));
        return vertices[x].size()-1;
    }
}
