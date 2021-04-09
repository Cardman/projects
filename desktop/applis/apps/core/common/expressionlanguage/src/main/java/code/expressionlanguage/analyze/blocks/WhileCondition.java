package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.files.OffsetStringInfo;

public final class WhileCondition extends ConditionBlock implements Loop {

    private final String label;
    private final int labelOffset;

    public WhileCondition(OffsetStringInfo _condition, OffsetStringInfo _label, int _offset) {
        super(_condition, _offset);
        label = _label.getInfo();
        labelOffset = _label.getOffset();
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public int getRealLabelOffset() {
        return getLabelOffset();
    }
    public int getLabelOffset() {
        return labelOffset;
    }


}
