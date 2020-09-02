package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.FinalVariableOperation;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.util.IdMap;

public final class RendFinalVariableOperation extends RendLeafOperation implements RendCalculableOperation {

    private String variableName;
    private int off;
    private ConstType type;
    private int deep;

    public RendFinalVariableOperation(FinalVariableOperation _f) {
        super(_f);
        off = _f.getOff();
        type = _f.getType();
        variableName = _f.getVariableName();
        deep = _f.getDeep();
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        Argument arg_ = getCommonArgument(_conf);
        setSimpleArgument(arg_, _conf,_nodes);
    }

    Argument getCommonArgument(Configuration _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off, _conf);
        ImportingPage ip_ = _conf.getLastPage();
        if (type == ConstType.LOOP_INDEX) {
            return ExecTemplates.getIndexLoop(_conf.getContext(),variableName, ip_.getPageEl(),deep);
        }
        return ExecTemplates.getValue(_conf.getContext(),variableName,ip_.getPageEl(),deep);
    }

}
