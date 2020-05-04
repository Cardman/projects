package code.expressionlanguage.structs;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ExecutableCode;

public final class NullStruct implements DisplayableStruct {

    public static final NullStruct NULL_VALUE = new NullStruct();

    private NullStruct() {
    }
    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public StringStruct getDisplayedString(Analyzable _an) {
        return new StringStruct(_an.getStandards().getDisplayedStrings().getNullString());
    }


    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return "";
    }

    @Override
    public boolean sameReference(Struct _other) {
        return _other == NULL_VALUE;
    }

}
