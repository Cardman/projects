package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;

public final class IfCondition extends Condition implements BlockCondition {

    private String label;
    private int labelOffset;

    public IfCondition(OffsetStringInfo _condition, OffsetStringInfo _label, OffsetsBlock _offset) {
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
