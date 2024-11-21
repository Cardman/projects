package aiki.beans.facade.comparators;
import aiki.fight.util.CategoryMult;
import code.util.core.SortConstants;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public final class ComparatorCategoryMult implements Comparing<CategoryMult> {

    @Override
    public int compare(CategoryMult _arg0, CategoryMult _arg1) {
        return cmp(_arg0, _arg1);
    }

    public static int cmp(CategoryMult _arg0, CategoryMult _arg1) {
        int cmp_ = StringUtil.compareStrings(_arg0.getCategory(),_arg1.getCategory());
        if (cmp_ != SortConstants.EQ_CMP) {
            return cmp_;
        }
        return _arg0.getMult() - _arg1.getMult();
    }

}