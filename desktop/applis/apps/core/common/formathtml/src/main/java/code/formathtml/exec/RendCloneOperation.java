package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.opers.FctOperation;
import code.expressionlanguage.exec.opers.ExecCloneOperation;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.formathtml.Configuration;
import code.util.IdMap;
import code.util.StringList;

public final class RendCloneOperation extends RendInvokingOperation implements RendCalculableOperation {

    private String methodName;

    protected RendCloneOperation(FctOperation _fct) {
        super(_fct);
        methodName = _fct.getCallFctContent().getMethodName();
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        int off_ = StringList.getFirstPrintableCharIndex(getMethodName());
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        Argument argres_ = ExecCloneOperation.cloneArray(previous_, _conf.getContext());
        setSimpleArgument(argres_,_conf,_nodes);
    }

    public String getMethodName() {
        return methodName;
    }
}
