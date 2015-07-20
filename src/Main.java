import dp.EditDistance;
import dp.LinearPartition;
import dp.LongestMonotoneSubSequence;
import dp.MinimumWeightTriangulation;
import graph.Dijkstra;
import graph.Floyd;
import graph.MaxFlow;
import graph.PrimMinSpanningTree;
import heuristics.Backtracking;

import java.awt.geom.Point2D;

public class Main {
    public static void main(String[] args) {
        /*BreadthFirstTraversal breadthFirstTraversal =new BreadthFirstTraversal();
        breadthFirstTraversal.init();
        breadthFirstTraversal.printShortUnweightedPath(0, 6);*/
        /*11
        15
        0 1 1
        0 2 1
        0 6 1
        0 7 1
        1 2 1
        2 3 1
        3 4 1
        3 5 1
        4 5 1
        6 7 1
        7 8 1
        7 9 1
        8 9 1
        8 10 1
        9 10 1*/
        /*DepthFirstTraversal depthFirstTraversal = new DepthFirstTraversal();
        depthFirstTraversal.task = DftTask.articulation;
        depthFirstTraversal.init();
        depthFirstTraversal.traverse(0);*/
        /*7
        10
        0 1 1
        0 3 1
        1 2 1
        1 4 1
        2 3 1
        2 5 1
        3 5 1
        4 2 1
        4 6 1
        5 6 1*/
        /*DepthFirstTraversal depthFirstTraversal = new DepthFirstTraversal();
        depthFirstTraversal.graph.isDirected=true;
        depthFirstTraversal.init();
        depthFirstTraversal.printTopologicalOrder();*/
        /*8
        12
        0 1 1
        1 2 1
        2 0 1
        2 3 1
        3 1 1
        3 4 1
        4 5 1
        3 5 1
        2 6 1
        6 5 1
        5 7 1
        7 6 1*/
        /*DepthFirstTraversal depthFirstTraversal = new DepthFirstTraversal();
        depthFirstTraversal.graph.isDirected=true;
        depthFirstTraversal.init();
        depthFirstTraversal.printStronglyConnectedComponents();*/
        /*5
        8
        0 1 10
        0 2 10
        0 3 2
        0 4 10
        1 2 2
        2 3 1
        2 4 3
        3 4 10*/
        /*PrimMinSpanningTree primMinSpanningTree =new PrimMinSpanningTree();
        primMinSpanningTree.init();
        primMinSpanningTree.printMinSpanningTree();*/
        /*KruskalMinSpanningTree kruskalMinSpanningTree =new KruskalMinSpanningTree();
        kruskalMinSpanningTree.init();
        kruskalMinSpanningTree.printMinSpanningTree();*/
        /*5
        8
        0 1 10
        0 2 10
        0 3 2
        0 4 10
        1 2 2
        2 3 1
        2 4 8
        3 4 10*/
        /*Dijkstra dijkstra =new Dijkstra();
        dijkstra.init();
        dijkstra.printMinPath(0, 4);*/
        /*Floyd floyd = new Floyd();
        floyd.init();
        floyd.calculateAllShortestPathLengths();
        floyd.printAllShortestPathLengths();*/
        /*7
        10
        0 1 5
        0 3 12
        1 2 9
        1 4 7
        2 4 3
        2 3 4
        2 5 3
        3 5 7
        4 6 5
        5 6 2
        maxFlow.graph.isDirected = false;
        maxFlow.printMaxFlowPaths(0, 6);*/
        /*6
        9
        0 1 4
        0 4 2
        1 3 2
        1 2 4
        2 5 3
        3 2 1
        3 5 1
        4 3 1
        4 5 3
        maxFlow.graph.isDirected = true;
        maxFlow.printMaxFlowPaths(0, 5);*/
        /*MaxFlow maxFlow =new MaxFlow();
        maxFlow.init();*/
        /*Backtracking backtracking= new Backtracking();
        backtracking.backtrack(new boolean[3],-1,3);*/
        /*EditDistance editDistance= new EditDistance();
        editDistance.printStringsMatch("thou shalt not","you should not");*/
        /*LongestMonotoneSubSequence longestMonotoneSubSequence = new LongestMonotoneSubSequence();
        longestMonotoneSubSequence.printLongestMonotoneSubSequence(new int[]{2, 4, 3, 5, 1, 7, 6, 9, 8});*/
        /*LinearPartition linearPartition =new LinearPartition();
        linearPartition.printPartitionResults(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 3);*/
        /*output should be 4.23606797749979*/
        /*MinimumWeightTriangulation minimumWeightTriangulation =new MinimumWeightTriangulation();
        minimumWeightTriangulation.printChords(new Point2D[]{
                 new Point2D.Double(0,0)
                ,new Point2D.Double(1,0)
                ,new Point2D.Double(2,1)
                ,new Point2D.Double(1,2)
                ,new Point2D.Double(0,2)});*/
    }
}