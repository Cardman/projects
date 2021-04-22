package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;

public final class StringBuilderStruct extends CharSequenceStruct {

    private final StringBuilder instance;

    public StringBuilderStruct(StringBuilder _instance) {
        instance = _instance;
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }

    @Override
    public long randCode() {
        return 1;
    }
    @Override
    public String getClassName(ContextEl _contextEl) {
        return _contextEl.getStandards().getContent().getCharSeq().getAliasStringBuilder();
    }

    public StringBuilder getInstance() {
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
        return new StringStruct(toStringInstance());
    }
    @Override
    public String toStringInstance() {
        return instance.toString();
    }

    @Override
    public String substring(int _i, int _j) {
        return instance.substring(_i,_j);
    }
}
