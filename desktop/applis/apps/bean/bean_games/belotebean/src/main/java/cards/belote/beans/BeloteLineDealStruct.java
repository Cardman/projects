package cards.belote.beans;

import code.bean.nat.CommNatStruct;

public final class BeloteLineDealStruct extends CommNatStruct {
    private final BeloteLineDeal lineDeal;

    public BeloteLineDealStruct(BeloteLineDeal _lineDeal, String _className) {
        super(_className);
        this.lineDeal = _lineDeal;
    }

    public BeloteLineDeal getLineDeal() {
        return lineDeal;
    }
}
