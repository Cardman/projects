package code.formathtml.classes;

import code.util.*;
import code.util.ints.ListableEntries;
import code.util.ints.SimpleIterable;

public final class NatTreeMapStringInteger extends AbsMap<String,Integer> {

    @Override
    protected int indexOfEntry(String _key) {
        int index_ = CustList.FIRST_INDEX;
        for (EntryCust<String, Integer> e:getList()) {
            String k_ = e.getKey();
            if (StringList.quickEq(_key, k_)) {
                return index_;
            }
            index_++;
        }
        return CustList.INDEX_NOT_FOUND_ELT;
    }
    void applyChanges() {
        for (int i = CustList.FIRST_INDEX; i < size(); i++) {
            for (int j = i + 1; j < size(); j++) {
                String c_ = getKey(i);
                int res_ = c_.compareTo(getKey(j));
                if (res_ > CustList.EQ_CMP) {
                    String firstKey_ = getKey(i);
                    String secondKey_ = getKey(j);
                    Integer firstValue_ = getValue(i);
                    Integer secondValue_ = getValue(j);
                    setKey(j,firstKey_);
                    setKey(i,secondKey_);
                    setValue(j, firstValue_);
                    setValue(i, secondValue_);
                }
            }
        }
    }

}
