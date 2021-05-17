package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.InstanceParamChecker;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecInstancingCommonContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ExecAnonymousInstancingOperation extends
        ExecAbstractInstancingOperation {

    public ExecAnonymousInstancingOperation(ExecOperationContent _opCont, boolean _intermediateDottedOperation, boolean _initBefore, ExecInstancingCommonContent _instancingCommonContent, ExecTypeFunction _pair) {
        super(_opCont, _intermediateDottedOperation, _initBefore, _pair,_instancingCommonContent);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        int off_ = StringUtil.getFirstPrintableCharIndex(getInstancingCommonContent().getMethodName());
        setRelOffsetPossibleLastPage(off_, _stack);
        ExecFormattedRootBlock className_ = _stack.formatVarType(getFormattedType());
        String base_ = StringExpUtil.getIdFromAllTypes(className_.getFormatted());
        Argument res_;
        if (_conf.getExiting().hasToExit(_stack, base_)) {
            res_ = Argument.createVoid();
        } else {
            res_ = new InstanceParamChecker(getPair(), fectchInstFormattedArgs(_nodes,className_, getInstancingCommonContent(),_conf,_stack), "", -1).checkParams(className_, previous_, null, _conf, _stack);
        }
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

}
