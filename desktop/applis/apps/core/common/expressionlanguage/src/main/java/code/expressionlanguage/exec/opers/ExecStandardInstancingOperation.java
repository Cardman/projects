package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRecordBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundRecordConstructor;
import code.expressionlanguage.exec.inherits.InstanceParamChecker;
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
        ExecFormattedRootBlock className_ = _stack.formatVarType(getFormattedType());
        Argument res_ = null;
        if (instancingStdContent.getFieldName().isEmpty()) {
            setRelOffsetPossibleLastPage(off_, _stack);
            if (_conf.getExiting().hasToExit(_stack, className_.getRootBlock())) {
                res_ = Argument.createVoid();
            }
        } else {
            off_ -= _stack.getLastPage().getTranslatedOffset();
            off_ -= instancingStdContent.getFieldName().length();
            off_--;
            setRelOffsetPossibleLastPage(off_, _stack);
        }
        if (res_ == null) {
            CustList<ExecOperationInfo> infos_ = buildInfos(_nodes);
            res_ = prep(_conf, _stack, previous_, className_, infos_, getInstancingCommonContent(), instancingStdContent);
        }
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

    public static Argument prep(ContextEl _conf, StackCall _stack, Argument _previous, ExecFormattedRootBlock _className, CustList<ExecOperationInfo> _infos, ExecInstancingCustContent _instancingCommonContent, ExecInstancingStdContent _instancingStdContent) {
        Argument res_;
        if (_instancingCommonContent.getPair().getType() instanceof ExecRecordBlock) {
            CustList<Argument> arguments_ = getArguments(_infos);
            _stack.setCallingState(new CustomFoundRecordConstructor(_className, _instancingCommonContent.getPair(), _instancingStdContent.getInfos(), _instancingStdContent.getFieldName(), _instancingStdContent.getBlockIndex(), arguments_));
            res_ = Argument.createVoid();
        } else {
            res_ = new InstanceParamChecker(_instancingCommonContent.getPair(), fectchInstFormattedArgs(_className, _instancingCommonContent, _conf, _stack, _infos), _instancingStdContent.getFieldName(), _instancingStdContent.getBlockIndex()).checkParams(_className, _previous, null, _conf, _stack);
        }
        return res_;
    }

}
