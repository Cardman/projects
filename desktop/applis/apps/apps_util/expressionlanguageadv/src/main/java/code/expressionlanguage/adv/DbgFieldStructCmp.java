package code.expressionlanguage.adv;

import code.util.core.SortConstants;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public final class DbgFieldStructCmp implements Comparing<DbgFieldStruct> {
    @Override
    public int compare(DbgFieldStruct _one, DbgFieldStruct _two) {
        int res_ = StringUtil.compareStrings(_one.getVariable().getClassName(), _two.getVariable().getClassName());
        if (res_ != SortConstants.EQ_CMP) {
            return res_;
        }
        return StringUtil.compareStrings(_one.getVariable().getFieldName(), _two.getVariable().getFieldName());
    }
}
