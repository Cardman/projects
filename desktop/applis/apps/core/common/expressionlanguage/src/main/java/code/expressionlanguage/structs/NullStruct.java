package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;

public final class NullStruct extends WithoutParentIdStruct implements DisplayableStruct {

    public static final NullStruct NULL_VALUE = new NullStruct();

    private NullStruct() {
    }

    @Override
    public StringStruct getDisplayedString(ContextEl _an) {
        return new StringStruct(_an.getStandards().getDisplayedStrings().getNullString());
    }


    @Override
    public String getClassName(ContextEl _contextEl) {
        return "";
    }


}
