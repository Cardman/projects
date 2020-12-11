package code.expressionlanguage.gui.unit;

import code.expressionlanguage.utilcompo.AbstractIssuer;
import code.gui.TextArea;

public final class UnitIssuer implements AbstractIssuer {
    private final TextArea area;

    public UnitIssuer(TextArea _area) {
        area = _area;
    }

    @Override
    public void log(String _info) {
        area.append(_info);
    }
}
