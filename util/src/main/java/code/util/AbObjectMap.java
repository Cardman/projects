package code.util;
import code.util.ints.Equallable;
import code.util.ints.ListableEntries;

public abstract class AbObjectMap<K extends Equallable<K>, V> extends AbsMap<K, V> {

//    //list cannot be null, even by reflection
//    private final CustList<EntryCust<K, V>> list = new CustList<EntryCust<K, V>>();

    public AbObjectMap() {
    }

    public AbObjectMap(ListableEntries<K, V> _arg0) {
        putAllMap(_arg0);
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
