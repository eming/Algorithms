package graph.common;

/**
 * Created by Emin Guliyev on 19/07/2015.
 */
public class FindUnion {
    public int count;
    public int[] parents;
    public int[] size;

    public FindUnion(int count){
        this.count = count;
        parents = new int[count];
        size = new int[count];
        for (int i = 0; i < count; i++) {
            parents[i]=-1;
            size[i]=1;
        }
    }

    public int findAncestor(int current){
        while (parents[current]!=-1){
            current=parents[current];
        }
        return current;
    }

    public boolean isSiblings(int x, int y){
        return findAncestor(x) == findAncestor(y);
    }

    public boolean union(int x, int y){
        int xAncestor = findAncestor(x);
        int yAncestor = findAncestor(y);
        if (xAncestor==yAncestor){
            return false;
        }
        if (size[xAncestor] > size[yAncestor]){
            size[xAncestor] += size[yAncestor];
            parents[yAncestor] = xAncestor;
        }else {
            size[yAncestor] += size[xAncestor];
            parents[xAncestor] = yAncestor;
        }
        return true;
    }
}
