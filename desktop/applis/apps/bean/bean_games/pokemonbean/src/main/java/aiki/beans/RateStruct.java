package aiki.beans;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.DisplayableStruct;
import code.expressionlanguage.structs.StringStruct;
import code.maths.Rate;

public final class RateStruct extends ParamNatStruct<Rate> implements DisplayableStruct {

    public RateStruct(Rate _instance, String _className) {
        super(_instance,_className);
    }

    public Rate getRate() {
        return getInstance();
    }

    @Override
    public StringStruct getDisplayedString(ContextEl _an) {
        return new StringStruct(getInstance().toNumberString());
    }
}
