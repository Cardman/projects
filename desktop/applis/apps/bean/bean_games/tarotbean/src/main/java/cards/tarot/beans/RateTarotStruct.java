package cards.tarot.beans;

import code.bean.nat.CommNatStruct;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.DisplayableStruct;
import code.expressionlanguage.structs.StringStruct;
import code.maths.Rate;

public final class RateTarotStruct extends CommNatStruct implements DisplayableStruct {

    private final Rate instance;

    public RateTarotStruct(Rate _instance, String _className) {
        super(_className);
        instance = _instance;
    }

    public Rate getRate() {
        return instance;
    }

    @Override
    public StringStruct getDisplayedString(ContextEl _an) {
        return new StringStruct(instance.toNumberString());
    }
}
