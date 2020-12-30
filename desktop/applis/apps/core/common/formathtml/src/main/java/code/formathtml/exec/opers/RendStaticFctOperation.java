package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStaticFctContent;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class RendStaticFctOperation extends RendSettableCallFctOperation implements RendCalculableOperation {

    private final ExecStaticFctContent staticFctContent;

    private final ExecTypeFunction pair;
    public RendStaticFctOperation(ExecTypeFunction _pair, ExecOperationContent _content, boolean _intermediateDottedOperation, ExecStaticFctContent _staticFctContent, ExecArrContent _arrContent) {
        super(_content, _intermediateDottedOperation, _arrContent);
        staticFctContent = _staticFctContent;
        pair = _pair;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        ArgumentWrapper argres_ = RendDynOperationNode.processCall(getArgument(_nodes, _context, _stack, _rendStack), _context, _stack);
        setSimpleArgument(argres_, _nodes, _context, _stack, _rendStack);
    }

    Argument getArgument(IdMap<RendDynOperationNode, ArgumentsPair> _all, ContextEl _context, StackCall _stackCall, RendStackCall _rendStackCall) {
        int off_ = StringUtil.getFirstPrintableCharIndex(staticFctContent.getMethodName());
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _rendStackCall);
        String lastType_ = staticFctContent.getLastType();
        int naturalVararg_ = staticFctContent.getNaturalVararg();
        Argument prev_ = new Argument();
        String classNameFound_ = staticFctContent.getClassName();
        return ExecInvokingOperation.callPrepare(_context.getExiting(), _context, classNameFound_, pair, prev_,null, fectchArgs(_all,lastType_,naturalVararg_, _rendStackCall), null, staticFctContent.getKind(), "", _stackCall);
    }
}
