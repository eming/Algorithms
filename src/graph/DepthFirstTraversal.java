package graph;

import graph.common.*;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by Emin Guliyev on 18/07/2015.
 */
public class DepthFirstTraversal {
    public DftTask task;
    public Graph graph = new Graph();
    public int[] parents;
    public VertexStatus[] vertexStatuses;
    public int[] entryTimes;
    public int[] exitTimes;
    public int time;
    //for articulation identification
    public int[] treeOutDegree;
    public int[] reachableAncestor;
    //for topological order
    public Stack<Integer> topologicalOrder;
    //strongly connected components
    public Stack<Integer> stronglyConnComps;
    public int[] lowVertexInTheSCC;
    public int[] scc;
    public int componentsFound;
    public void init() {        
        graph.readFromConsole();
    }

    protected void eraseAll() {
        if (task==DftTask.topologicalOrder){
            topologicalOrder = new Stack<Integer>();
        }
        if (task==DftTask.stronglyConnectedComponents) {
            stronglyConnComps = new Stack<Integer>();
            componentsFound = 0;
            lowVertexInTheSCC = new int[graph.vertexCount];
            scc = new int[graph.vertexCount];
        }
        if (task==DftTask.articulation) {
            treeOutDegree = new int[graph.vertexCount];
            reachableAncestor = new int[graph.vertexCount];
        }
        time = 0;
        vertexStatuses = new VertexStatus[graph.vertexCount];
        parents = new int[graph.vertexCount];
        entryTimes = new int[graph.vertexCount];
        exitTimes = new int[graph.vertexCount];
        for (int i = 0; i < graph.vertexCount; i++) {
            vertexStatuses[i] = VertexStatus.undiscovered;
            entryTimes[i]=Integer.MAX_VALUE;
            exitTimes[i]=Integer.MAX_VALUE;
            parents[i] = -1;
            if (task==DftTask.articulation) {
                treeOutDegree[i] = 0;
                reachableAncestor[i] = i;
            }
            if (task==DftTask.stronglyConnectedComponents) {
                lowVertexInTheSCC[i] = i;
                scc[i] = -1;
            }
        }
    }

    protected void traverseRecursively(int current){
        vertexStatuses[current]=VertexStatus.discovered;
        entryTimes[current]=time++;
        processVertexEarly(current);
        for (AdjacentNode adjacentNode:graph.vertices[current]){
            EdgeType edgeType = processEdge(current, adjacentNode.to, adjacentNode.weight);
            if (edgeType == EdgeType.treeEdge){
                parents[adjacentNode.to]=current;
                traverseRecursively(adjacentNode.to);
            }
        }
        vertexStatuses[current]=VertexStatus.processed;
        exitTimes[current]=time++;
        processVertexLate(current);
    }

    protected void processVertexLate(int current) {
        //for topological order of DAG
        if (task==DftTask.topologicalOrder) {
            topologicalOrder.push(current);
        }
        //for articulation vertex identification
        if (task==DftTask.articulation) {
            if (parents[current] == -1) {
                if (treeOutDegree[current] > 1) {
                    System.out.println(current + " is root articulation vertex");
                }
                return;
            }

            boolean isParentRoot = parents[parents[current]] == -1;
            if (reachableAncestor[current] == parents[current] && !isParentRoot) {
                System.out.println(parents[current] + " is parent articulation vertex");
            } else {
                if (reachableAncestor[current] == current) {
                    System.out.println(parents[current] + " is bridge articulation vertex");
                    if (treeOutDegree[current] > 0) {
                        System.out.println(current + " is bridge articulation vertex");
                    }
                }
            }

            if (parents[current] != -1 && entryTimes[reachableAncestor[current]] < entryTimes[reachableAncestor[parents[current]]]) {
                reachableAncestor[parents[current]] = reachableAncestor[current];
            }
        }
        if (task==DftTask.stronglyConnectedComponents){
            if (lowVertexInTheSCC[current]==current){
                createScc(current);
            }
            if (parents[current] != -1 && entryTimes[lowVertexInTheSCC[current]] < entryTimes[lowVertexInTheSCC[parents[current]]]) {
                lowVertexInTheSCC[parents[current]] = lowVertexInTheSCC[current];
            }
        }
    }

    private void createScc(int current) {
        int vertex;
        componentsFound++;
        scc[current]=componentsFound;
        while((vertex= stronglyConnComps.pop()) != current){
            scc[vertex]=componentsFound;
        }
    }

    protected void processVertexEarly(int current) {
        //System.out.println(current);
        if (task == DftTask.stronglyConnectedComponents){
            stronglyConnComps.push(current);
        }
    }

    protected EdgeType processEdge(int from, int to, double weight) {
        EdgeType edgeType = getEdgeType(from, to);
        //for articulation vertex identification
        if (task==DftTask.articulation) {
            if (edgeType == EdgeType.treeEdge) {
                treeOutDegree[from]++;
            }
            if (edgeType == EdgeType.backEdge) {
                if (entryTimes[to] < entryTimes[reachableAncestor[from]]) {
                    reachableAncestor[from] = to;
                }
            }
        }
        if (task==DftTask.stronglyConnectedComponents){
            if (edgeType == EdgeType.backEdge){
                if (entryTimes[to] < entryTimes[lowVertexInTheSCC[from]]) {
                    lowVertexInTheSCC[from] = to;
                }
            }

            if (edgeType == EdgeType.crossEdge){
                //if not identified it means it has the path to pre ancestor,
                //so from this pre ancestor to last common ancestor, from there to "from".
                //there is circular path, so they should be in the same component
                if (scc[to]==-1){
                    if (entryTimes[to] < entryTimes[lowVertexInTheSCC[from]]) {
                        lowVertexInTheSCC[from] = to;
                    }
                }
            }
        }
        return edgeType;
    }

    protected EdgeType getEdgeType(int from, int to) {
        if (vertexStatuses[to] == VertexStatus.undiscovered){
            return EdgeType.treeEdge;
        }
        if (vertexStatuses[to] == VertexStatus.discovered){
            if (parents[from] != to) {
                //if DAF raise exception
                return EdgeType.backEdge;
            }else {
                return EdgeType.duplicateEdge;
            }
        }
        if (vertexStatuses[to] == VertexStatus.processed){
            if (entryTimes[from] < entryTimes[to]){
                return EdgeType.frontEdge;
            }else{
                return EdgeType.crossEdge;
            }
        }
        return EdgeType.unknown;
    }

    public void traverse(int start) {
        eraseAll();
        traverseRecursively(start);
    }

    public void printTopologicalOrder(){
        task = DftTask.topologicalOrder;
        eraseAll();
        for(int i=0; i<vertexStatuses.length; i++){
            if (vertexStatuses[i] == VertexStatus.undiscovered){
                traverse(i);
            }
        }
        while (!topologicalOrder.isEmpty()){
            System.out.println(topologicalOrder.pop());
        }
    }

    public void printStronglyConnectedComponents(){
        task = DftTask.stronglyConnectedComponents;
        eraseAll();
        for(int i=0; i<vertexStatuses.length; i++){
            if (vertexStatuses[i] == VertexStatus.undiscovered){
                traverse(i);
            }
        }

        for (int i = 0; i < scc.length; i++) {
            System.out.println(i + " is in component #" + scc[i]);
        }
    }
}
