package code.util;

public final class StringMapObject extends AbsMap<String,Object> {

    @Override
    public StringList getKeys() {
        StringList s_ = new StringList();
        for (EntryCust<String, Object> e: getList()) {
            s_.add(e.getKey());
        }
        return s_;
    }

    @Override
    int indexOfEntry(String _key) {
        int index_ = CustList.FIRST_INDEX;
        for (EntryCust<String, Object> e:getList()) {
            String k_ = e.getKey();
            if (StringList.quickEq(_key, k_)) {
                return index_;
            }
            index_++;
        }
        return CustList.INDEX_NOT_FOUND_ELT;
    }
}
