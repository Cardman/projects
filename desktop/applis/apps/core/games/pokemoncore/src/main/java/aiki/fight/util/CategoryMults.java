package aiki.fight.util;

import code.maths.Rate;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.core.IndexConstants;

public final class CategoryMults {
    private final CustList<CategoryMultRate> list;
    public CategoryMults() {
        list = new CustList<CategoryMultRate>();
    }
    public CategoryMults(CollCapacity _cap) {
        list = new CustList<CategoryMultRate>(_cap);
    }

    public CustList<CategoryMultRate> entryList() {
        return getList();
    }

    
    public CustList<CategoryMult> getKeys() {
        CustList<CategoryMult> l_ = new CustList<CategoryMult>();
        for (CategoryMultRate e: entryList()) {
            l_.add(e.getCategory());
        }
        return l_;
    }

    public CustList<Rate> values() {
        CustList<Rate> l_ = new CustList<Rate>();
        for (CategoryMultRate e: entryList()) {
            l_.add(e.getRate());
        }
        return l_;
    }
    public CustList<CategoryMultRate> getList() {
        return list;
    }


    public boolean isEmpty() {
        return getList().isEmpty();
    }


    public CategoryMultRate getEntryByKey(CategoryMult _key) {
        int index_ = indexOfEntry(_key);
        if (index_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return null;
        }
        return getList().get(index_);
    }


    public boolean contains(CategoryMult _key) {
        return getEntryByKey(_key) != null;
    }
    
    public Rate getVal(CategoryMult _key) {
        CategoryMultRate e_ = getEntryByKey(_key);
        if (e_ == null) {
            return Rate.zero();
        }
        return e_.getRate();
    }


    public int indexOfEntry(CategoryMult _key){
        int len_ = list.size();
        for (int i = 0; i < len_; i++) {
            if (_key.eq(list.get(i).getCategory())){
                return i;
            }
        }
        return -1;
    }

    public void addEntry(CategoryMult _k, Rate _v) {
        list.add(new CategoryMultRate(_k, _v));
    }

}
