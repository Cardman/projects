package aiki.fight.util;

import code.maths.Rate;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.core.IndexConstants;

public final class TypesDuos {
    private final CustList<TypesDuoRate> list;
    public TypesDuos() {
        list = new CustList<TypesDuoRate>();
    }
    public TypesDuos(CollCapacity _cap) {
        list = new CustList<TypesDuoRate>(_cap);
    }

    public CustList<TypesDuoRate> entryList() {
        return getList();
    }

    public static boolean contains(CustList<TypesDuo> _l, TypesDuo _e) {
        for (TypesDuo t: _l) {
            if (_e.eq(t)) {
                return true;
            }
        }
        return false;
    }

    public CustList<TypesDuo> getKeys() {
        CustList<TypesDuo> l_ = new CustList<TypesDuo>();
        for (TypesDuoRate e: entryList()) {
            l_.add(e.getStat());
        }
        return l_;
    }

    public CustList<TypesDuoRate> getList() {
        return list;
    }


    public boolean isEmpty() {
        return getList().isEmpty();
    }


    public TypesDuoRate getEntryByKey(TypesDuo _key) {
        int index_ = indexOfEntry(_key);
        if (index_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return null;
        }
        return getList().get(index_);
    }


    public boolean contains(TypesDuo _key) {
        return getEntryByKey(_key) != null;
    }

    public Rate getVal(TypesDuo _key) {
        TypesDuoRate e_ = getEntryByKey(_key);
        if (e_ == null) {
            return Rate.zero();
        }
        return e_.getValue();
    }


    public int indexOfEntry(TypesDuo _key){
        int len_ = list.size();
        for (int i = 0; i < len_; i++) {
            if (_key.eq(list.get(i).getStat())){
                return i;
            }
        }
        return -1;
    }

    public void addEntry(TypesDuo _k, Rate _v) {
        list.add(new TypesDuoRate(_k, _v));
    }

}
