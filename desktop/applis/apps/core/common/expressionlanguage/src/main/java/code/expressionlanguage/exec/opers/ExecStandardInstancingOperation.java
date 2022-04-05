package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ParamCheckerUtil;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecOperationInfo;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecInstancingCustContent;
import code.expressionlanguage.fwd.opers.ExecInstancingStdContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ExecStandardInstancingOperation extends
        ExecAbstractInstancingOperation {

    private final ExecInstancingStdContent instancingStdContent;

    public ExecStandardInstancingOperation(ExecOperationContent _opCont, boolean _intermediateDottedOperation, boolean _initBefore, ExecInstancingCustContent _instancingCommonContent, ExecInstancingStdContent _instancingStdContent) {
        super(_opCont, _intermediateDottedOperation, _initBefore, _instancingCommonContent);
        instancingStdContent = _instancingStdContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        int off_ = StringUtil.getFirstPrintableCharIndex(getInstancingCommonContent().getMethodName());
        ExecFormattedRootBlock className_ = StackCall.formatVarType(_stack,getFormattedType());
        if (instancingStdContent.getFieldName().isEmpty()) {
            setRelOffsetPossibleLastPage(off_, _stack);
        } else {
            off_ -= _stack.getLastPage().getTranslatedOffset();
            off_ -= instancingStdContent.getFieldName().length();
            off_--;
            setRelOffsetPossibleLastPage(off_, _stack);
        }
        Argument res_ = null;
        if (instancingStdContent.getFieldName().isEmpty()) {
            res_ = ParamCheckerUtil.tryInit(_conf,_stack, className_.getRootBlock());
        }
        if (res_ == null) {
            CustList<ExecOperationInfo> infos_ = buildInfos(_nodes);
            Argument resNext_ = ParamCheckerUtil.prep(_conf, _stack, previous_, className_, infos_, getInstancingCommonContent(), instancingStdContent);
            setSimpleArgument(resNext_, _conf, _nodes, _stack);
        } else {
            setSimpleArgument(res_, _conf, _nodes, _stack);
        }
    }

}
