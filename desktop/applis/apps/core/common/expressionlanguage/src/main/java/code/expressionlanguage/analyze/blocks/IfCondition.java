package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.files.OffsetStringInfo;

public final class IfCondition extends LabelledConditionBlock implements BreakableBlock,BreakableLabelBlock {

    public IfCondition(OffsetStringInfo _condition, OffsetStringInfo _label, int _offset) {
        super(_condition, _offset,_label);
    }

}
