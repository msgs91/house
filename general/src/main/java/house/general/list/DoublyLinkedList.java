package house.general.list;

class DoublyLinkedList {
    Slot head = null;
    Slot tail = null;

    public Slot insert(int key, int pos) {
        Slot current = head;
        Slot prev = null;
        for (int i = 0; i < pos && current != null; i++) {
            prev = current;
            current = current.next;
        }

        Slot slot = new Slot();
        slot.key = key;
        slot.next = current;
        slot.prev = prev;

        if (prev != null) {
            prev.next = slot;
        } else {
            head = slot;
        }

        if (current != null) {
            current.prev = slot;
        } else {
            tail = slot;
        }

        return head;
    }

    public Slot insert(int key) {
        return insert(key, 0);
    }

    public Slot append(int key) {
        return insert(key, 100000);
    }

    public Slot append() {
        Slot slot = new Slot();
        if (tail != null) {
            tail.next = slot;
        } else {
            head = slot;
        }
        slot.prev = tail;
        tail = slot;
        if (head == null) {
            head = tail;
        }
        return head;
    }

    public Slot delete() {
        Slot slot = head;
        if (head != null) {
            Slot next = head.next;
            head = next;
            if (next != null) {
                next.prev = null;
            } else {
                tail = null;
            }
        }
        return head;
    }

    public Slot pop() {
        Slot slot = tail;
        if (tail != null) {
            Slot prev = tail.prev;
            tail = prev;
            if (prev != null) {
                prev.next = null;
            } else {
                head = null;
            }
        }
        return head;
    }

    public void traverse() {
        Slot pointer = head;
        while (pointer != null) {
            System.out.println(pointer.key);
            pointer = pointer.next;
        }
    }

    public void reverseTraverse() {
        Slot pointer = tail;
        while (pointer != null) {
            System.out.println(pointer.key);
            pointer = pointer.prev;
        }
    }
}