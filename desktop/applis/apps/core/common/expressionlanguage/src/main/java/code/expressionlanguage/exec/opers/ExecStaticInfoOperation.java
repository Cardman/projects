package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.IdMap;

public final class ExecStaticInfoOperation extends ExecLeafOperation implements
        AtomicExecCalculableOperation {

    private String className;

    public ExecStaticInfoOperation(ExecOperationContent _opCont, String _className) {
        super(_opCont);
        className = _className;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        String classStr_ = _conf.getLastPage().formatVarType(className, _conf);
        Argument a_ = new Argument(ExecutingUtil.getClassMetaInfo(_conf,classStr_));
        setSimpleArgument(a_, _conf, _nodes);
    }

}
