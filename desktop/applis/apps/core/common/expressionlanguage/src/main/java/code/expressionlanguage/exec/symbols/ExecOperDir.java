package code.expressionlanguage.exec.symbols;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.symbol.CommonOperSymbol;
import code.expressionlanguage.exec.AbstractStackCall;
import code.expressionlanguage.exec.calls.IntAbstractPageEl;
import code.expressionlanguage.exec.opers.ExecNumericOperation;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class ExecOperDir implements ExecOperSymbol{
    private final CommonOperSymbol symbol;

    public ExecOperDir(CommonOperSymbol _s) {
        this.symbol = _s;
    }

    @Override
    public Struct calculateOperator(Struct _first, Struct _second, byte _cast, ContextEl _cont, IntAbstractPageEl _stackCall) {
        Struct res_ = symbol.calculateOperator(_first, _second, _cast);
        if (res_ == NullStruct.NULL_VALUE) {
            return null;
        }
        return res_;
    }

    @Override
    public Struct afterCalculateExc(Struct _str, ContextEl _cont, AbstractStackCall _stackCall) {
        return ExecNumericOperation.exc(_str,_stackCall.stack(),_cont);
    }

}
