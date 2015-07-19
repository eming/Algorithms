package graph.common;

/**
 * Created by Emin Guliyev on 18/07/2015.
 */
public enum EdgeType {
    //duplicate edge is y-->x edge in graph if we have processed already x-->y
    treeEdge, backEdge, duplicateEdge, frontEdge, crossEdge, unknown
}
