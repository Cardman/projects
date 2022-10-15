package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;

public final class NullStruct extends AbNullStruct implements DisplayableStruct,AnaDisplayableStruct {

    public static final NullStruct NULL_VALUE = new NullStruct();

    private NullStruct() {
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
    public long randCode() {
        return 0;
    }


}
