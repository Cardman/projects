package code.expressionlanguage.exec.symbols;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.structs.Struct;

public interface ExecOperSymbol {
    Struct calculateOperator(
                      Struct _first, Struct _second, byte _cast, ContextEl _cont,StackCall _stackCall);
}
