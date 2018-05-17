package house.general;

import java.util.ArrayList;
import java.util.Vector;

public class Heap {

    int[] tree = {-1, 3, 4, 1, 2, 5, 7, 8, 9, 3, 0, 6, 8};

    public Heap(){
        print();
    }

    void print(){
        for(int i=1; i < tree.length; i++){
            System.out.print(tree[i]);
            System.out.print(" ");
        }
        System.out.println();
    }

    void swap(int i, int j){
        int c = tree[i];
        tree[i] = tree[j];
        tree[j] = c;
    }

    int parent(int i){
        return i/2;
    }

    int left(int i){
        return 2*i;
    }

    int right(int i){
        return 2*i+1;
    }

    void heapify(int i){
        int left = 2 * i;
        int right = 2 * i + 1;
        int greater = tree[i];
        int greaterIndex = i;
        if (left < tree.length && tree[left] > greater) {
            greaterIndex = left;
            greater = tree[left];
        }
        if (right < tree.length && tree[right] > greater) {
            greaterIndex = right;
            greater = tree[right];
        }

        if (greaterIndex != i) {
            tree[greaterIndex] = tree[i];
            tree[i] = greater;
            heapify(greaterIndex);
        }

    }

    void increment(int index, int by) {
        tree[index] += by;
        for (int i = index; i != 0 && tree[i/2] < tree[i]; i=i/2) {
            swap(i, i/2);
        }
    }

    void buildHeap() {
        int mid = tree.length / 2;
        for (int i = mid; i >= 0; i--) {
            heapify(i);
        }
    }

    public static void main(String[] args) {
        Heap h = new Heap();
        h.buildHeap();
        h.print();
        h.increment(6, 3);
        h.print();
    }
}
