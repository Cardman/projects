package code.expressionlanguage.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.util.IdMap;

public interface CallSimpleOperation {

    void endCalulate(IdMap<OperationNode,ArgumentsPair> _id, Argument _arg, ContextEl _cont);
}
