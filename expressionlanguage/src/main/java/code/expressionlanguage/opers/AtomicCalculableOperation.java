package code.expressionlanguage.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.util.IdMap;

public interface AtomicCalculableOperation {

    Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf);
}
