package code.expressionlanguage.fwd.blocks;

import code.expressionlanguage.fwd.opers.ExecInstFctContent;

public final class ExecTypeFunctionInst {
    private final ExecInstFctContent inst;
    private final ExecTypeFunction pair;

    public ExecTypeFunctionInst(ExecInstFctContent _i, ExecTypeFunction _p) {
        this.inst = _i;
        this.pair = _p;
    }

    public ExecTypeFunction getPair() {
        return pair;
    }

    public ExecInstFctContent getInst() {
        return inst;
    }
}
