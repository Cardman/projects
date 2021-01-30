package cards.president.beans;

import code.bean.nat.CommNatStruct;

public final class LineDealStruct extends CommNatStruct {
    private final LineDeal lineDeal;

    public LineDealStruct(LineDeal _lineDeal, String _className) {
        super(_className);
        this.lineDeal = _lineDeal;
    }

    public LineDeal getLineDeal() {
        return lineDeal;
    }
}
