package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRecordBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundRecordConstructor;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecInstancingCommonContent;
import code.expressionlanguage.fwd.opers.ExecInstancingStdContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ExecStandardInstancingOperation extends
        ExecAbstractInstancingOperation {

    private final ExecInstancingStdContent instancingStdContent;

    private final ExecRootBlock rootBlock;
    public ExecStandardInstancingOperation(ExecTypeFunction _pair, ExecOperationContent _opCont, boolean _intermediateDottedOperation, boolean _initBefore, ExecInstancingCommonContent _instancingCommonContent, ExecInstancingStdContent _instancingStdContent) {
        super(_opCont, _intermediateDottedOperation, _initBefore, _pair,_instancingCommonContent);
        instancingStdContent = _instancingStdContent;
        rootBlock = _pair.getType();
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        Argument res_ = getArgument(previous_,_nodes, _conf, _stack);
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }
    Argument getArgument(Argument _previous, IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                         ContextEl _conf, StackCall _stackCall) {
        int off_ = StringUtil.getFirstPrintableCharIndex(getInstancingCommonContent().getMethodName());
        if (!instancingStdContent.getFieldName().isEmpty()) {
            off_ -= _stackCall.getLastPage().getTranslatedOffset();
            off_ -= instancingStdContent.getFieldName().length();
            off_--;
        }
        setRelOffsetPossibleLastPage(off_, _stackCall);
        String className_ = _stackCall.formatVarType(getClassName());
        if (instancingStdContent.getFieldName().isEmpty()) {
            String base_ = StringExpUtil.getIdFromAllTypes(className_);
            if (_conf.getExiting().hasToExit(_stackCall, base_)) {
                return Argument.createVoid();
            }
        }
        if (rootBlock instanceof ExecRecordBlock) {
            CustList<Argument> arguments_ = getArguments(_nodes, this);
            _stackCall.setCallingState(new CustomFoundRecordConstructor(className_, getPair(),instancingStdContent.getInfos(), instancingStdContent.getFieldName(), instancingStdContent.getBlockIndex(), arguments_));
            return Argument.createVoid();
        }
        return instancePrepareCust(_conf, className_, getPair(), _previous, getArgs(_nodes, className_), instancingStdContent.getFieldName(), instancingStdContent.getBlockIndex(), _stackCall);
    }

    private ArgumentListCall getArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes, String _className) {
        return fectchInstFormattedArgs(_nodes,_className,rootBlock, getInstancingCommonContent().getLastType(), getInstancingCommonContent().getNaturalVararg());
    }

    public String getClassName() {
        return getInstancingCommonContent().getClassName();
    }

}
