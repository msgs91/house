package house.general;

import scala.Int;

public class CircularQueue<T> {

    Object[] arr;
    int head = 0;
    int tail = 0;
    int size = 0;

    public CircularQueue(int capacity) {
        this.arr =  new Object[capacity];
    }

    public void put(T element) {
        if (size == arr.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        arr[tail] = element;
        tail += 1;
        if (tail > arr.length - 1) {
            tail = 0;
        }
        size += 1;
    }

    public T get() {
        if (size > 0) {
            T result = (T) arr[head];
            head += 1;
            if (head > arr.length - 1) {
                head = 0;
            }
            size -= 1;
            if (size == 0) {
                head = tail = 0;
            }

            return result;
        } else {
            return null;
        }
    }

    public void print() {
        System.out.printf("head = %d, tail = %d \n", head, tail);
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d ", (T) arr[i]);
        }
        System.out.println();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        CircularQueue<Integer> queue = new CircularQueue<>(5);

        queue.put(1);
        queue.put(2);
        queue.put(3);
        queue.print();

        System.out.println(queue.get());
        System.out.println(queue.get());
        System.out.println(queue.get());
        queue.print();

        queue.put(4);
        queue.put(5);
        queue.put(6);
        queue.put(7);
        queue.print();

    }

}
