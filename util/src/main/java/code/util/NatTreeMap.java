package code.util;

/**
    @author Cardman
*/
public abstract class NatTreeMap<K, V> extends AbsMap<K, V>  {

    public NatTreeMap() {
    }
    
    public NatTreeMap(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    public void put(K _key, V _value) {
        long convert_ = convert(_key);
        int index_ = 0;
        while (true) {
            if (index_ >= getList().size()) {
                getList().add(new EntryCust<K, V>(_key, _value));
                return;
            }
            EntryCust<K, V> c_ = getList().get(index_);
            int res_ = Numbers.compareLg(convert_,convert(c_.getKey()));
            if (res_ < 0) {
                getList().add(index_, new EntryCust<K, V>(_key, _value));
                return;
            }
            if (res_ == 0) {
                setValue(index_, _value);
                return;
            }
            index_++;
        }
    }

    @Override
    int indexOfEntry(K _key) {
        long convert_ = convert(_key);
        int s_ = size();
        for (int i = 0; i < s_; i++) {
            int res_ = Numbers.compareLg(convert_,convert(getKey(i)));
            if (res_ == CustList.EQ_CMP) {
                return i;
            }
        }
        return CustList.INDEX_NOT_FOUND_ELT;
    }

    abstract long convert(K _key);
}
