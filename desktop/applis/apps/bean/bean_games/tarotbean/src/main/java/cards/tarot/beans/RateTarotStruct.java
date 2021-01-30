package cards.tarot.beans;

import code.bean.RealInstanceStruct;
import code.bean.nat.CommNatStruct;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.WithoutParentIdStruct;
import code.maths.Rate;

public final class RateTarotStruct extends CommNatStruct implements RealInstanceStruct {

    private final Rate instance;

    public RateTarotStruct(Rate _instance, String _className) {
        super(_className);
        instance = _instance;
    }

    @Override
    public Object getInstance() {
        return getRate();
    }
    public Rate getRate() {
        return instance;
    }

}
