package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.InstanceParamChecker;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecInstancingCustContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ExecAnonymousInstancingOperation extends
        ExecAbstractInstancingOperation {

    public ExecAnonymousInstancingOperation(ExecOperationContent _opCont, boolean _intermediateDottedOperation, boolean _initBefore, ExecInstancingCustContent _instancingCommonContent) {
        super(_opCont, _intermediateDottedOperation, _initBefore, _instancingCommonContent);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        int off_ = StringUtil.getFirstPrintableCharIndex(getInstancingCommonContent().getMethodName());
        setRelOffsetPossibleLastPage(off_, _stack);
        ExecFormattedRootBlock className_ = StackCall.formatVarType(_stack,getFormattedType());
        Argument res_;
        if (_conf.getExiting().hasToExit(_stack, className_.getRootBlock())) {
            res_ = Argument.createVoid();
        } else {
            res_ = new InstanceParamChecker(getInstancingCommonContent().getPair(), fectchInstFormattedArgs(className_, getInstancingCommonContent(),_conf,_stack, buildInfos(_nodes)), "", -1).checkParams(className_, previous_, null, _conf, _stack);
        }
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

}
