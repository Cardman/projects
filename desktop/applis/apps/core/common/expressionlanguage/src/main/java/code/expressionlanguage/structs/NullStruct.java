package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;

public final class NullStruct extends WithoutParentIdStruct implements DisplayableStruct,AnaDisplayableStruct {

    public static final NullStruct NULL_VALUE = new NullStruct();
    public static final NullStruct DEF_VALUE = new NullStruct();

    private NullStruct() {
    }
    public static Struct defNullValue(Struct _str) {
        return nullValue(def(_str));
    }
    public static Struct def(Struct _str) {
        if (_str == null) {
            return DEF_VALUE;
        }
        return _str;
    }
    public static Struct nullValue(Struct _str) {
        if (_str == DEF_VALUE) {
            return NULL_VALUE;
        }
        return _str;
    }

    @Override
    public StringStruct getDisplayedString(ContextEl _an) {
        return new StringStruct(_an.getStandards().getDisplayedStrings().getNullString());
    }

    @Override
    public StringStruct getDisplayedString(AnalyzedPageEl _an) {
        return new StringStruct(_an.getDisplayedStrings().getNullString());
    }
    @Override
    public String getClassName(ContextEl _contextEl) {
        return "";
    }
    @Override
    public long randCode() {
        return 0;
    }


}
