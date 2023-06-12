package code.expressionlanguage.adv;

import code.util.core.StringUtil;
import code.util.ints.Comparing;

public final class DbgFieldStructQuickCmp implements Comparing<DbgFieldStruct> {
    @Override
    public int compare(DbgFieldStruct _one, DbgFieldStruct _two) {
        return StringUtil.compareStrings(_one.getVariable().getFieldName(), _two.getVariable().getFieldName());
    }
}
