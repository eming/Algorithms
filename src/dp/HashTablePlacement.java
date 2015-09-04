package dp;

import java.util.ArrayList;

/**
 * Created by Emin Guliyev on 10/08/2015.
 */
//http://www.geeksforgeeks.org/google-placement-paper/
//#10
class KeyAndIndex {
    public int key;
    public int index;

    KeyAndIndex(int key, int index) {
        this.key = key;
        this.index = index;
    }
}
public class HashTablePlacement {
    //2 power 6 possible variants of placements will be coded as integer indexes 0..2 power 6
    public int[] store = new int[64];

    public HashTablePlacement() {
    }

    //soFar 0 - empty, 1 - used, 2 - unused
    public int calculateVariants(int[] soFar, ArrayList<KeyAndIndex> unusedOnes) {
        int result = 0;
        int index = convertToInt(soFar);
        if (store[index] != 0) {
            return store[index];
        }
        if (unusedOnes.isEmpty()){
            result = 1;
        }else {
            ArrayList<KeyAndIndex> others = (ArrayList<KeyAndIndex>) unusedOnes.clone();
            for (KeyAndIndex keyAndIndex : unusedOnes) {
                others.remove(keyAndIndex);
                if (canBeFirst(keyAndIndex, soFar)) {
                    soFar[keyAndIndex.index] = 1;
                    result += calculateVariants(soFar, others);
                    soFar[keyAndIndex.index] = 2;
                }
                others.add(keyAndIndex);
            }
        }
        store[index] = result;
        return result;
    }

    private boolean canBeFirst(KeyAndIndex keyAndIndex, int[] soFar) {
        int match = keyAndIndex.key % 10;
        while (soFar[match] == 1) match++;
        return match == keyAndIndex.index;
    }

    private int convertToInt(int[] soFar) {
        int result = 0;
        int powerOfTwo = 1;
        for (int i = 9; i >= 0; i--) {
            if (soFar[i] > 0) {
                result += powerOfTwo * (soFar[i] == 1 ? 1 : 0);
                powerOfTwo *= 2;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] hashTable = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, 42, 23, 34, 52, 46, 33, Integer.MAX_VALUE, Integer.MAX_VALUE};
        ArrayList<KeyAndIndex> unusedOnes = new ArrayList<KeyAndIndex>();
        int[] soFar = new int[10];
        for (int i = 0; i < 10; i++) {
            if (hashTable[i] != Integer.MAX_VALUE) {
                soFar[i] = 2;
                unusedOnes.add(new KeyAndIndex(hashTable[i], i));
            }
        }

        HashTablePlacement hashTablePlacement = new HashTablePlacement();
        int result = hashTablePlacement.calculateVariants(soFar, unusedOnes);
        System.out.println(result);
    }
}