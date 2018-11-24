package code.expressionlanguage.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.util.IdMap;

public interface CallSimpleOperation {

    Argument endCalculate(ContextEl _conf, IdMap<OperationNode, ArgumentsPair> _nodes, Argument _right);
}
