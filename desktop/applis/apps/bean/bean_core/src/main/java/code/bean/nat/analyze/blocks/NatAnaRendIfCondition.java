package code.bean.nat.analyze.blocks;

import code.expressionlanguage.analyze.files.OffsetStringInfo;

public final class NatAnaRendIfCondition extends NatAnaRendCondition {

    private final String label;

    NatAnaRendIfCondition(OffsetStringInfo _condition, OffsetStringInfo _label, int _offset) {
        super(_condition, _offset);
        label = _label.getInfo();
    }

    public String getRealLabel() {
        return label;
    }

}
