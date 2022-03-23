package code.bean.nat;

import code.expressionlanguage.structs.StringStruct;
import code.maths.LgInt;

public final class LgIntStruct extends CommNatStruct implements NatDisplayableStruct {

    private final LgInt value;
    public LgIntStruct(LgInt _instance, String _className) {
        super(_className);
        value = _instance;
    }

    public LgInt getInstance() {
        return value;
    }

    @Override
    public StringStruct getDisplayedString() {
        return new StringStruct(getInstance().toNumberString());
    }
}
