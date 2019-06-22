package code.util;
import code.util.ints.Equallable;
import code.util.ints.ListableEntries;

public abstract class AbObjectMap<K extends Equallable<K>, V> extends AbsMap<K, V> {

    public AbObjectMap() {
    }

    protected AbObjectMap(CollCapacity _capacity) {
        super(_capacity);
    }

}
