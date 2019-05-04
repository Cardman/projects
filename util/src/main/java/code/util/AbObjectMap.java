package code.util;
import code.util.ints.Equallable;
import code.util.ints.ListableEntries;

public abstract class AbObjectMap<K extends Equallable<K>, V> extends AbsMap<K, V> {

    public AbObjectMap() {
    }

    protected AbObjectMap(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    public EqList<K> getKeys() {
        EqList<K> s_ = new EqList<K>();
        for (EntryCust<K, V> e: getList()) {
            s_.add(e.getKey());
        }
        return s_;
    }


}
