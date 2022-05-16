package code.expressionlanguage.exec.symbols;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.symbol.CommonOperSymbol;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.ExecNumericOperation;
import code.expressionlanguage.structs.Struct;

public final class ExecOperDir implements ExecOperSymbol{
    private final CommonOperSymbol symbol;

    public ExecOperDir(CommonOperSymbol _s) {
        this.symbol = _s;
    }

    @Override
    public Struct calculateOperator(Struct _first, Struct _second, byte _cast, ContextEl _cont, StackCall _stackCall) {
        Struct res_ = symbol.calculateOperator(_first, _second, _cast);
        return ExecNumericOperation.exc(res_,_stackCall,_cont);
    }

}
