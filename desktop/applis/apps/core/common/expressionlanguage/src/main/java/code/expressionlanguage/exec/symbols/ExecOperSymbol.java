package code.expressionlanguage.exec.symbols;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.AbstractStackCall;
import code.expressionlanguage.exec.calls.IntAbstractPageEl;
import code.expressionlanguage.structs.Struct;

public interface ExecOperSymbol {
    Struct calculateOperator(
            Struct _first, Struct _second, byte _cast, ContextEl _cont, IntAbstractPageEl _stackCall);
    Struct afterCalculateExc(Struct _str, ContextEl _cont, AbstractStackCall _stackCall);
}
