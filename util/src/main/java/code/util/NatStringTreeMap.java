package code.util;

/**
    @author Cardman
*/
public final class NatStringTreeMap<V> extends AbsMap<String, V> {

    @Override
    public StringList getKeys() {
        StringList s_ = new StringList();
        for (EntryCust<String, V> e: getList()) {
            s_.add(e.getKey());
        }
        return s_;
    }

    @Override
    public void put(String _key, V _value) {
        int index_ = 0;
        while (true) {
            if (index_ >= getList().size()) {
                getList().add(new EntryCust<String, V>(_key, _value));
                return;
            }
            EntryCust<String, V> c_ = getList().get(index_);
            int res_ = _key.compareTo(c_.getKey());
            if (res_ < 0) {
                getList().add(index_, new EntryCust<String, V>(_key, _value));
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
    int indexOfEntry(String _key) {
        int index_ = CustList.FIRST_INDEX;
        for (EntryCust<String, V> e:getList()) {
            String c_ = _key;
            int res_ = c_.compareTo(e.getKey());
            if (res_ == CustList.EQ_CMP) {
                return index_;
            }
            index_++;
        }
        return CustList.INDEX_NOT_FOUND_ELT;
    }

}
