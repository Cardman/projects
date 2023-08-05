package code.expressionlanguage.exec.symbols;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.AbstractStackCall;
import code.expressionlanguage.exec.calls.IntAbstractPageEl;
import code.expressionlanguage.exec.opers.ExecCatOperation;
import code.expressionlanguage.structs.Struct;

public final class ExecOperCat implements ExecOperSymbol{
    @Override
    public Struct calculateOperator(Struct _first, Struct _second, byte _cast, ContextEl _cont, IntAbstractPageEl _stackCall) {
        return ExecCatOperation.localSumDiff(new Argument(_first),new Argument(_second),_cont).getStruct();
    }

    @Override
    public Struct afterCalculateExc(Struct _str, ContextEl _cont, AbstractStackCall _stackCall) {
        return _str;
    }

}
