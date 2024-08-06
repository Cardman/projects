package aiki.facade.enums;

import aiki.db.*;
import code.util.CustList;
import code.util.core.StringUtil;

public enum SelectedBoolean {
    YES(DataBase.DEF_SEL_BOOL_YES), NO(DataBase.DEF_SEL_BOOL_NO), YES_AND_NO(DataBase.DEF_SEL_BOOL_YES_AND_NO);
    private final String boolName;
    SelectedBoolean(String _w){
        boolName= _w;
    }

    public static SelectedBoolean getBoolByName(String _env) {
        for (SelectedBoolean e : SelectedBoolean.all()) {
            if (StringUtil.quickEq(e.boolName, _env)) {
                return e;
            }
        }
        return SelectedBoolean.YES_AND_NO;
    }
    public static CustList<SelectedBoolean> all() {
        CustList<SelectedBoolean> bools_ = new CustList<SelectedBoolean>();
        bools_.add(YES);
        bools_.add(NO);
        bools_.add(YES_AND_NO);
        return bools_;
    }

    public String getBoolName() {
        return boolName;
    }
}
