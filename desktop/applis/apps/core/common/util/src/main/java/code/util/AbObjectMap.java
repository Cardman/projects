package code.util;

public abstract class AbObjectMap<K, V> extends AbsMap<K, V> {

    public AbObjectMap() {
    }

    protected AbObjectMap(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    protected int indexOfEntry(K _key) {
        int index_ = CustList.FIRST_INDEX;
        for (EntryCust<K, V> e:getList()) {
            K key_ = e.getKey();
            if (eq(_key,key_)) {
                return index_;
            }
            index_++;
        }
        return CustList.INDEX_NOT_FOUND_ELT;
    }

    protected abstract boolean eq(K _one, K _two);
}
