package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.fwd.blocks.ForwardInfos;

public final class ExecOperatorContent {
    private final String oper;
    private final int opOffset;

    public ExecOperatorContent(AnaOperatorContent _cont) {
        this(ForwardInfos.syntheticOperator(_cont.getOper()), _cont.getOpOffset());
    }
    public ExecOperatorContent(String _op, int _off) {
        oper = _op;
        opOffset = _off;
    }
    public int getOpOffset() {
        return opOffset;
    }

    public String getOper() {
        return oper;
    }
}
