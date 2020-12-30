package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecInstFctContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class RendSuperFctOperation extends RendSettableCallFctOperation implements RendCalculableOperation {

    private final ExecInstFctContent instFctContent;
    private final ExecTypeFunction pair;
    public RendSuperFctOperation(ExecTypeFunction _pair, ExecOperationContent _content, boolean _intermediateDottedOperation, ExecInstFctContent _instFctContent, ExecArrContent _arrContent) {
        super(_content, _intermediateDottedOperation, _arrContent);
        instFctContent = _instFctContent;
        pair = _pair;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        Argument previous_ = getPreviousArg(this,_nodes, _rendStack);
        ArgumentWrapper argres_ = RendDynOperationNode.processCall(getArgument(previous_, _nodes, _context, _stack, _rendStack), _context, _stack);
        setSimpleArgument(argres_, _nodes, _context, _stack, _rendStack);
    }

    public Argument getArgument(Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, ContextEl _context, StackCall _stackCall, RendStackCall _rendStackCall) {
        int off_ = StringUtil.getFirstPrintableCharIndex(instFctContent.getMethodName());
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _rendStackCall);
        String lastType_ = instFctContent.getLastType();
        int naturalVararg_ = instFctContent.getNaturalVararg();
        String classNameFound_ = instFctContent.getClassName();
        Argument prev_ = new Argument(ExecTemplates.getParent(instFctContent.getAnc(), classNameFound_, _previous.getStruct(),_context, _stackCall));
        if (_context.callsOrException(_stackCall)) {
            return new Argument();
        }
        String argClassName_ = prev_.getStruct().getClassName(_context);
        String base_ = StringExpUtil.getIdFromAllTypes(classNameFound_);
        String fullClassNameFound_ = ExecTemplates.getSuperGeneric(argClassName_, base_, _context);
        lastType_ = ExecTemplates.quickFormat(pair.getType(),fullClassNameFound_, lastType_);
        return ExecInvokingOperation.callPrepare(_context.getExiting(), _context, classNameFound_, pair, prev_,null, fectchArgs(_all,lastType_,naturalVararg_, _rendStackCall), null, MethodAccessKind.INSTANCE, "", _stackCall);
    }
}
