package code.expressionlanguage.structs;

import code.expressionlanguage.exec.blocks.ExecMemberCallingsBlock;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.stds.StandardMethod;

public final class CallersInfo {

    private final MethodModifier modifier;

    private final ExecMemberCallingsBlock callee;
    private final ExecTypeFunction pair;
    private final ExecFormattedRootBlock formatted;
    private final StandardMethod stdCallee;

    public CallersInfo(MethodModifier _modifier,ExecMemberCallingsBlock _callee, ExecTypeFunction _pair, ExecFormattedRootBlock _formatted, StandardMethod _stdCallee) {
        modifier = _modifier;
        this.callee = _callee;
        this.pair = _pair;
        this.formatted = _formatted;
        this.stdCallee = _stdCallee;
    }

    public MethodModifier getModifier() {
        return modifier;
    }

    public ExecFormattedRootBlock getFormatted() {
        return formatted;
    }

    public ExecTypeFunction getPair() {
        return pair;
    }

    public ExecMemberCallingsBlock getCallee() {
        return callee;
    }

    public StandardMethod getStdCallee() {
        return stdCallee;
    }
}
