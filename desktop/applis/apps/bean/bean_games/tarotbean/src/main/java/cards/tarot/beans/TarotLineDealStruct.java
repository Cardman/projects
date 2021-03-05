package cards.tarot.beans;

import code.bean.nat.CommNatStruct;

public final class TarotLineDealStruct extends CommNatStruct {
    private final TarotLineDeal lineDeal;

    public TarotLineDealStruct(TarotLineDeal _lineDeal, String _className) {
        super(_className);
        this.lineDeal = _lineDeal;
    }

    public TarotLineDeal getLineDeal() {
        return lineDeal;
    }
}
