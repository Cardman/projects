package aiki.util;

import aiki.db.DataBase;
import code.util.*;
import code.util.core.*;

public final class ChangeStringValueUtil<K> {
    private final AbsMap<K,String> collection;
    public ChangeStringValueUtil(AbsMap<K,String> _map) {
        collection = _map;
    }
    public void replace(String _o, String _n) {
        for (EntryCust<K,String> e: collection.entryList()) {
            if (StringUtil.quickEq(e.getValue(), _o)) {
                e.setValue(_n);
            }
        }
    }
    public void replaceExp(DataBase _db, StringList _mids, String _o, String _n) {
        for (EntryCust<K,String> e: collection.entryList()) {
            e.setValue(_db.rename(e.getValue(),_mids,_o,_n));
        }
    }
    public boolean containsWord(DataBase _db, StringList _mids, String _id) {
        for (EntryCust<K,String> e: collection.entryList()) {
            if (_db.containsWord(e.getValue(),_mids,_id)) {
                return true;
            }
        }
        return false;
    }
}
