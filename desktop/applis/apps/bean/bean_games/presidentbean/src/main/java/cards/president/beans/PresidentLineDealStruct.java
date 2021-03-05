package cards.president.beans;

import code.bean.nat.CommNatStruct;

public final class PresidentLineDealStruct extends CommNatStruct {
    private final PresidentLineDeal lineDeal;

    public PresidentLineDealStruct(PresidentLineDeal _lineDeal, String _className) {
        super(_className);
        this.lineDeal = _lineDeal;
    }

    public PresidentLineDeal getLineDeal() {
        return lineDeal;
    }
}
