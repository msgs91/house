package house.general.list;

public class LinkedList {
    static Slot head = null;

    public static Slot insert(int key) {
        Slot slot = new Slot();
        slot.key = key;
        slot.next = head;
        head = slot;
        return head;
    }

    static Slot insert(int key, int pos) {
        Slot slot = new Slot();
        slot.key = key;

        Slot current = head;
        Slot prev = null;
        for (int i = 0; i < pos && current != null; i++) {
            prev = current;
            current = current.next;
        }
        slot.next = current;

        if (prev != null) {
            prev.next = slot;
        } else {
            head = slot;
        }
        return slot;
    }

    public static Slot delete() {
        Slot slot = head;
        if (slot != null) {
            head = slot.next;
        }
        return slot;
    }

    static Slot delete(int target) {
        Slot current = head;
        Slot prev = null;
        while (current != null && current.key != target) {
            prev = current;
            current = current.next;
        }

        if (current != null) {
            if (prev != null) {
                prev.next = current.next;
            } else {
                head = current.next;
            }
            current.next = null;
        }
        return current;
    }


    public static void traverse() {
        Slot pointer = head;
        while (pointer != null) {
            String next;
            if (pointer.next == null) {
                next = "NULL";
            } else {
                next = String.valueOf(pointer.next.key);
            }
            System.out.printf("key = %d, next = %s -> ", pointer.key, next);
            pointer = pointer.next;
        }
        System.out.print("NULL\n");
    }

    public static void main(String[] args) {
        traverse();
        insert(1);
        insert(2);
        insert(3);
        traverse();
        delete();
        traverse();
        delete();
        traverse();
        delete();
        traverse();
    }
}