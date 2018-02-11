package aiki.beans.facade.comparators;
import java.util.Comparator;

import code.util.CustList;
import aiki.fight.util.CategoryMult;

public final class ComparatorCategoryMult implements Comparator<CategoryMult> {

    @Override
    public int compare(CategoryMult _arg0, CategoryMult _arg1) {
        int cmp_ = _arg0.getCategory().compareTo(_arg1.getCategory());
        if (cmp_ != CustList.EQ_CMP) {
            return cmp_;
        }
        return _arg0.getMult() - _arg1.getMult();
    }

}
