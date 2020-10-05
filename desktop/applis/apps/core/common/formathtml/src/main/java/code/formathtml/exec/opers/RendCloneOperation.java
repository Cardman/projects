package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.opers.ExecCloneOperation;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;
import code.util.StringList;

public final class RendCloneOperation extends RendInvokingOperation implements RendCalculableOperation {

    private String methodName;

    public RendCloneOperation(ExecOperationContent _content, boolean _intermediateDottedOperation, String _methodName) {
        super(_content, _intermediateDottedOperation);
        methodName = _methodName;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        int off_ = StringList.getFirstPrintableCharIndex(getMethodName());
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        Argument argres_ = ExecCloneOperation.cloneArray(previous_, _context);
        setSimpleArgument(argres_,_conf,_nodes, _context);
    }

    public String getMethodName() {
        return methodName;
    }
}
