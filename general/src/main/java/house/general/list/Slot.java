package house.general.list;

import java.util.concurrent.atomic.AtomicBoolean;

class Slot {
    int key;
    int value;
    boolean dirty = false;
    AtomicBoolean locked = new AtomicBoolean(false);
    Slot next = null;
    Slot prev = null;
}