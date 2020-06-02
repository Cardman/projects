package code.formathtml.classes;

import code.util.*;

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

}
