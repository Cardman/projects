package code.expressionlanguage.gui.unit;

import code.expressionlanguage.utilcompo.AbstractIssuer;
import code.gui.AbsTextArea;


public final class UnitIssuer implements AbstractIssuer {
    private final AbsTextArea area;

    public UnitIssuer(AbsTextArea _area) {
        area = _area;
    }

    @Override
    public void log(String _info) {
        area.append(_info);
    }
}
