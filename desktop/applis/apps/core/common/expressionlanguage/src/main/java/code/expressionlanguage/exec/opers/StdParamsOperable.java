package code.expressionlanguage.exec.opers;

import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.util.ArgumentList;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.stds.StandardNamedFunction;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public interface StdParamsOperable {
    ArgumentList args(IdMap<ExecOperationNode, ArgumentsPair> _nodes, String _alias);
    StandardNamedFunction fct();
    Struct instance(IdMap<ExecOperationNode, ArgumentsPair> _nodes, AbstractPageEl _stack);
}
