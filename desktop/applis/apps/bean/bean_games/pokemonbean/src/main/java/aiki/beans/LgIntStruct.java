package aiki.beans;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.DisplayableStruct;
import code.expressionlanguage.structs.StringStruct;
import code.maths.LgInt;

public final class LgIntStruct extends ParamNatStruct<LgInt> implements DisplayableStruct {

    public LgIntStruct(LgInt _instance, String _className) {
        super(_instance,_className);
    }

    @Override
    public StringStruct getDisplayedString(ContextEl _an) {
        return new StringStruct(getInstance().toNumberString());
    }
}
