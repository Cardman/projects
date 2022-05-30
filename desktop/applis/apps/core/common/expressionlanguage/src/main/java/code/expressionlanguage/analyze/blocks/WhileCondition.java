package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.files.OffsetStringInfo;

public final class WhileCondition extends LabelledConditionBlock implements BreakableBlock {

    public WhileCondition(OffsetStringInfo _condition, OffsetStringInfo _label, int _offset) {
        super(_condition, _offset,_label);
    }



}
