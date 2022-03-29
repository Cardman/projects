package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.InstanceParamChecker;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecOperationInfo;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecInstancingCustContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.CustList;
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
        Argument res_ = prep(_conf,_stack,previous_,className_,buildInfos(_nodes),getInstancingCommonContent());
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

    public static Argument prep(ContextEl _conf, StackCall _stack, Argument _previous, ExecFormattedRootBlock _className, CustList<ExecOperationInfo> _infos, ExecInstancingCustContent _instancingCommonContent) {
        Argument res_;
        if (_conf.getExiting().hasToExit(_stack, _className.getRootBlock())) {
            res_ = Argument.createVoid();
        } else {
            res_ = new InstanceParamChecker(_instancingCommonContent.getPair(), fectchInstFormattedArgs(_className, _instancingCommonContent,_conf,_stack, _infos), "", -1).checkParams(_className, _previous, null, _conf, _stack);
        }
        return res_;
    }
}
