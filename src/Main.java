import graph.BreadthFirstTraversal;
import graph.DepthFirstTraversal;
import graph.common.DftTask;

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
    }
}