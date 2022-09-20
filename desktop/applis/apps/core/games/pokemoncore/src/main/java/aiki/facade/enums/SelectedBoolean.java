package aiki.facade.enums;

import code.util.CustList;

public enum SelectedBoolean {
    YES("YES"), NO("NO"), YES_AND_NO("YES_AND_NO");
    private final String boolName;
    SelectedBoolean(String _w){
        boolName= _w;
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
