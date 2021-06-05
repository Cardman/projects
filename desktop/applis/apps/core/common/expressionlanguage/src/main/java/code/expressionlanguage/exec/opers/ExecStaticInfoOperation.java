package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.ClassMetaInfo;
import code.util.IdMap;

public final class ExecStaticInfoOperation extends ExecLeafOperation implements
        AtomicExecCalculableOperation {

    private final String className;

    public ExecStaticInfoOperation(ExecOperationContent _opCont, String _className) {
        super(_opCont);
        className = _className;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        String classStr_ = _stack.formatVarType(className);
        Argument a_ = new Argument(ClassMetaInfo.getClassMetaInfo(_conf,classStr_));
        setSimpleArgument(a_, _conf, _nodes, _stack);
    }

}
