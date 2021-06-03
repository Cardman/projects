package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.blocks.ExecRecordBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundRecordConstructor;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.inherits.InstanceParamChecker;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.*;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class RendStandardInstancingOperation extends RendInvokingOperation implements RendCalculableOperation {

    private final ExecInstancingCommonContent instancingCommonContent;
    private final ExecInstancingStdContent instancingStdContent;

    private final ExecTypeFunction pair;
    private final ExecFormattedRootBlock formattedType;

    public RendStandardInstancingOperation(ExecTypeFunction _pair, ExecOperationContent _content, boolean _intermediateDottedOperation, ExecInstancingCommonContent _instancingCommonContent, ExecInstancingStdContent _instancingStdContent) {
        super(_content, _intermediateDottedOperation);
        pair = _pair;
        instancingCommonContent = _instancingCommonContent;
        instancingStdContent = _instancingStdContent;
        formattedType = _instancingCommonContent.getFormattedType();
    }

    public RendStandardInstancingOperation(ExecOperationContent _content, ExecInstancingCommonContent _instancingCommonContent, ExecInstancingStdContent _instancingStdContent, ExecTypeFunction _pair) {
        super(_content,false);
        pair = _pair;
        instancingCommonContent = _instancingCommonContent;
        instancingStdContent = _instancingStdContent;
        formattedType = _instancingCommonContent.getFormattedType();
    }
    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        Argument previous_ = getPreviousArg(this,_nodes, _rendStack);
        int off_ = StringUtil.getFirstPrintableCharIndex(instancingCommonContent.getMethodName());
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _rendStack);
        String lastType_ = ExecInherits.quickFormat(formattedType, instancingCommonContent.getLastType());
        Argument result_;
        if (pair.getType() instanceof ExecRecordBlock) {
            CustList<Argument> arguments_ = getArguments(_nodes, this);
            _rendStack.getStackCall().setCallingState(new CustomFoundRecordConstructor(formattedType, pair,instancingStdContent.getInfos(), instancingStdContent.getFieldName(), instancingStdContent.getBlockIndex(), arguments_));
            result_ = Argument.createVoid();
        } else {
            result_ = new InstanceParamChecker(pair, ExecInvokingOperation.fectchArgs(lastType_, instancingCommonContent.getNaturalVararg(), null, _context, _rendStack.getStackCall(), buildInfos(_nodes)), instancingStdContent.getFieldName(), instancingStdContent.getBlockIndex()).checkParams(formattedType, previous_, null, _context, _rendStack.getStackCall());
        }
        ArgumentWrapper argres_ = RendDynOperationNode.processCall(result_, _context, _rendStack);
        setSimpleArgument(argres_, _nodes, _context, _rendStack);
    }

}
