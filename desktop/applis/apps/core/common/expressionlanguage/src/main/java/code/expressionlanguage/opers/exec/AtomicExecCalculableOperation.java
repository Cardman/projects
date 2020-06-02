package code.expressionlanguage.opers.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.util.IdMap;

public interface AtomicExecCalculableOperation extends Operable {

    void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf);
}
