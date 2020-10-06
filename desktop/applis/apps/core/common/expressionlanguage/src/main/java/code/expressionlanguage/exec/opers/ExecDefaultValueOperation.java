package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.IdMap;

public final class ExecDefaultValueOperation extends ExecLeafOperation implements
        AtomicExecCalculableOperation {

    private String className;

    public ExecDefaultValueOperation(ExecOperationContent _opCont, String _className) {
        super(_opCont);
        className = _className;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        String classStr_ = _conf.formatVarType(className);
        Argument a_ = new Argument(ExecClassArgumentMatching.defaultValue(classStr_,_conf));
        setSimpleArgument(a_, _conf, _nodes);
    }
}
