package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.util.IdMap;

public interface AtomicExecCalculableOperation extends ExecOperable {

    Argument calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf);
}
