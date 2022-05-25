package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.files.OffsetFinalInfo;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.util.StringList;

public final class ManyLoopExpressionsContent extends LoopExpressionsContent {

    private final StringList variableNames = new StringList();

    private final boolean finalVariable;
    private final int finalOffset;
    private final boolean refVariable;
    public ManyLoopExpressionsContent(OffsetFinalInfo _final, OffsetStringInfo _cl, OffsetStringInfo _from,
                                      OffsetStringInfo _exp, OffsetStringInfo _step, OffsetStringInfo _clName) {
        super(_cl, _from, _exp, _step, _clName);
        finalVariable = _final.getFinalInfo().isInfo();
        finalOffset = _final.getFinalInfo().getOffset();
        refVariable = _final.isRefVariable();
    }

    public StringList getVariableNames() {
        return variableNames;
    }

    public int getFinalOffset() {
        return finalOffset;
    }

    public boolean isRefVariable() {
        return refVariable;
    }

    public boolean isFinalVariable() {
        return finalVariable;
    }
}
