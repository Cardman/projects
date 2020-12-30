package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRecordBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundRecordConstructor;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.*;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class RendStandardInstancingOperation extends RendInvokingOperation implements RendCalculableOperation {

    private final ExecInstancingCommonContent instancingCommonContent;
    private final ExecInstancingStdContent instancingStdContent;

    private final ExecTypeFunction pair;
    public RendStandardInstancingOperation(ExecTypeFunction _pair, ExecOperationContent _content, boolean _intermediateDottedOperation, ExecInstancingCommonContent _instancingCommonContent, ExecInstancingStdContent _instancingStdContent) {
        super(_content, _intermediateDottedOperation);
        pair = _pair;
        instancingCommonContent = _instancingCommonContent;
        instancingStdContent = _instancingStdContent;
    }

    public RendStandardInstancingOperation(ExecRootBlock _rootBlock, ExecOperationContent _content, ExecInstancingCommonContent _instancingCommonContent, ExecInstancingStdContent _instancingStdContent) {
        super(_content,false);
        pair = new ExecTypeFunction(_rootBlock,null);
        instancingCommonContent = _instancingCommonContent;
        instancingStdContent = _instancingStdContent;
    }
    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        Argument previous_ = getPreviousArg(this,_nodes, _rendStack);
        Argument argres_ = RendDynOperationNode.processCall(getArgument(previous_, _nodes, _context, _stack, _rendStack), _context, _stack).getValue();
        setSimpleArgument(argres_, _nodes, _context, _stack, _rendStack);
    }

    public Argument getArgument(Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all,
                                ContextEl _context, StackCall _stackCall, RendStackCall _rendStackCall) {
        int off_ = StringUtil.getFirstPrintableCharIndex(instancingCommonContent.getMethodName());
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _rendStackCall);
        String className_ = instancingCommonContent.getClassName();
        String lastType_ = ExecTemplates.quickFormat(pair.getType(),className_, instancingCommonContent.getLastType());
        if (pair.getType() instanceof ExecRecordBlock) {
            CustList<Argument> arguments_ = getArguments(_all, this);
            _stackCall.setCallingState(new CustomFoundRecordConstructor(className_, pair,instancingStdContent.getInfos(), instancingStdContent.getFieldName(), instancingStdContent.getBlockIndex(), arguments_));
            return Argument.createVoid();
        }
        return ExecInvokingOperation.instancePrepareCust(_context, className_, pair, _previous, fectchArgs(_all,lastType_,instancingCommonContent.getNaturalVararg(), _rendStackCall), instancingStdContent.getFieldName(), instancingStdContent.getBlockIndex(), _stackCall);
    }

}
