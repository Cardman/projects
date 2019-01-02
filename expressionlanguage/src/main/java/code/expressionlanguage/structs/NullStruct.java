package code.expressionlanguage.structs;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ExecutableCode;

public final class NullStruct implements DisplayableStruct, ExportableStringStruct {

    public static final NullStruct NULL_VALUE = new NullStruct();

    private NullStruct() {
    }
    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public StringStruct getDisplayedString(Analyzable _an) {
        return new StringStruct(_an.getStandards().getNullString());
    }

    @Override
    public StringStruct exportValue() {
        return new StringStruct(";");
    }


    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return null;
    }

    @Override
    public boolean sameReference(Struct _other) {
        return _other == NULL_VALUE;
    }

}
