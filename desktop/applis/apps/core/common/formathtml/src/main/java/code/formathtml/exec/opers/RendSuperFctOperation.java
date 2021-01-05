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
        int off_ = StringUtil.getFirstPrintableCharIndex(instFctContent.getMethodName());
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _rendStack);
        String lastType_ = instFctContent.getLastType();
        int naturalVararg_ = instFctContent.getNaturalVararg();
        String classNameFound_ = instFctContent.getClassName();
        Argument prev_ = new Argument(ExecTemplates.getParent(instFctContent.getAnc(), classNameFound_, previous_.getStruct(), _context, _stack));
        Argument result_;
        if (_context.callsOrException(_stack)) {
            result_ = new Argument();
        } else {
            String argClassName_ = prev_.getStruct().getClassName(_context);
            String base_ = StringExpUtil.getIdFromAllTypes(classNameFound_);
            String fullClassNameFound_ = ExecTemplates.getSuperGeneric(argClassName_, base_, _context);
            lastType_ = ExecTemplates.quickFormat(pair.getType(), fullClassNameFound_, lastType_);
            result_ = ExecInvokingOperation.callPrepare(_context.getExiting(), _context, classNameFound_, pair, prev_, null, fectchArgs(_nodes, lastType_, naturalVararg_, _rendStack), null, MethodAccessKind.INSTANCE, "", _stack);
        }
        ArgumentWrapper argres_ = RendDynOperationNode.processCall(result_, _context, _stack);
        setSimpleArgument(argres_, _nodes, _context, _stack, _rendStack);
    }

}
