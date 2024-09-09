package code.expressionlanguage.exec.symbols;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.AbstractStackCall;
import code.expressionlanguage.exec.calls.IntAbstractPageEl;
import code.expressionlanguage.exec.opers.ExecCatOperation;
import code.expressionlanguage.structs.Struct;

public final class ExecOperCat implements ExecOperSymbol{
    @Override
    public Struct calculateOperator(Struct _first, Struct _second, ContextEl _cont, IntAbstractPageEl _stackCall) {
        return ExecCatOperation.localSumDiff(_first,_second,_cont);
    }

    @Override
    public Struct afterCalculateExc(Struct _str, ContextEl _cont, AbstractStackCall _stackCall) {
        return _str;
    }

    @Override
    public String getSgn() {
        return "+";
    }
}
