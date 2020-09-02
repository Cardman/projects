package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.FinalVariableOperation;
import code.util.IdMap;

public final class ExecFinalVariableOperation extends ExecLeafOperation implements
        AtomicExecCalculableOperation {

    private String variableName;
    private int off;
    private ConstType type;
    private int deep;

    public ExecFinalVariableOperation(FinalVariableOperation _f) {
        super(_f);
        off = _f.getOff();
        type = _f.getType();
        variableName = _f.getVariableName();
        deep = _f.getDeep();
    }
    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        Argument arg_ = getCommonArgument(_conf);
        setSimpleArgument(arg_, _conf, _nodes);
    }
    Argument getCommonArgument(ContextEl _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off, _conf);
        PageEl ip_ = _conf.getLastPage();
        if (type == ConstType.LOOP_INDEX) {
            return ExecTemplates.getIndexLoop(_conf,variableName, ip_,deep);
        }
        return ExecTemplates.getValue(_conf,variableName,ip_,deep);
    }

}
