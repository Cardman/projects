package code.expressionlanguage.exec.symbols;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.ExecCatOperation;
import code.expressionlanguage.structs.Struct;

public final class ExecOperCat implements ExecOperSymbol{
    @Override
    public Struct calculateOperator(Struct _first, Struct _second, byte _cast, ContextEl _cont, StackCall _stackCall) {
        return ExecCatOperation.localSumDiff(new Argument(_first),new Argument(_second),_cont).getStruct();
    }

}
