package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.files.OffsetStringInfo;

public final class AnaRendWhileCondition extends AnaRendCondition implements AnaRendLocBreakableBlock {

    private final String label;
    private final int labelOffset;
    AnaRendWhileCondition(OffsetStringInfo _condition, OffsetStringInfo _label, int _offset) {
        super(_condition, _offset);
        label = _label.getInfo();
        labelOffset = _label.getOffset();
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public int getRealLabelOffset() {
        return labelOffset;
    }
}
