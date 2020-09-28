package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.opers.ExecAnnotationMethodOperation;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecCallFctAnnotContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.Configuration;
import code.util.IdMap;
import code.util.StringList;

public final class RendAnnotationMethodOperation extends RendInvokingOperation  implements RendCalculableOperation {

    private ExecCallFctAnnotContent callFctAnnotContent;

    public RendAnnotationMethodOperation(ExecOperationContent _content, boolean _intermediateDottedOperation, ExecCallFctAnnotContent _callFctAnnotContent) {
        super(_content, _intermediateDottedOperation);
        callFctAnnotContent = _callFctAnnotContent;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument res_ = getArgument(previous_, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }

    Argument getArgument(Argument _previous, Configuration _conf) {
        int off_ = StringList.getFirstPrintableCharIndex(callFctAnnotContent.getMethodName());
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        return ExecAnnotationMethodOperation.getAnnotation(_previous, callFctAnnotContent.getClassMethodId(), _conf.getContext());
    }
}
