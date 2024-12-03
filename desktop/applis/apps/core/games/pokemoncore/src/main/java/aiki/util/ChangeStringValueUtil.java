package aiki.util;

import aiki.db.*;
import code.util.*;
import code.util.core.*;

public final class ChangeStringValueUtil<K> implements ChangeStringFieldMatch {
    private final AbsMap<K,String> collection;
    public ChangeStringValueUtil(AbsMap<K,String> _map) {
        collection = _map;
    }

    @Override
    public boolean match(String _v) {
        for (EntryCust<K,String> e: collection.entryList()) {
            if (StringUtil.quickEq(e.getValue(),_v)) {
                return true;
            }
        }
        return false;
    }

    public void replace(String _o, String _n) {
        for (EntryCust<K,String> e: collection.entryList()) {
            if (StringUtil.quickEq(e.getValue(), _o)) {
                e.setValue(_n);
            }
        }
    }
    public void replaceExp(DataBase _db, AbsRenamingDataBase _abs) {
        for (EntryCust<K,String> e: collection.entryList()) {
            e.setValue(_abs.rename(_db,e.getValue()));
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
