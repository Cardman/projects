package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.NumParsers;
import code.util.core.StringUtil;

public final class StringStruct extends CharSequenceStruct implements AnaDisplayableStruct {

    private final String instance;

    public StringStruct(String _instance) {
        instance = StringUtil.nullToEmpty(_instance);
    }

    public boolean sameReference(Struct _other) {
        if (!(_other instanceof StringStruct)) {
            return false;
        }
        return NumParsers.sameEq(this, _other);
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return _contextEl.getStandards().getContent().getCharSeq().getAliasString();
    }

    public String getInstance() {
        return instance;
    }

    @Override
    public int length() {
        return instance.length();
    }
    @Override
    public char charAt(int _i) {
        return instance.charAt(_i);
    }

    @Override
    public StringStruct getDisplayedString(ContextEl _an) {
        return this;
    }
    @Override
    public String toStringInstance() {
        return instance;
    }

    @Override
    public String substring(int _i, int _j) {
        return instance.substring(_i,_j);
    }

    @Override
    public StringStruct getDisplayedString(AnalyzedPageEl _an) {
        return this;
    }
}
