package graph.common;

import java.util.ArrayList;
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
        for (int i = 0; i < edgeCount; i++) {
            int from = scanIn.nextInt();
            int to = scanIn.nextInt();
            double weight = scanIn.nextDouble();
            addEdge(from,to,weight);
        }
        if (!isDirected){
            edgeCount *= 2;
        }
    }
}
