package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.CallPrepareState;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.DefaultParamChecker;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.variables.ArgumentsPair;
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

public final class RendChoiceFctOperation extends RendSettableCallFctOperation implements RendCalculableOperation {

    private final ExecTypeFunction pair;
    private final ExecInstFctContent instFctContent;
    public RendChoiceFctOperation(ExecTypeFunction _pair, ExecOperationContent _content, boolean _intermediateDottedOperation, ExecInstFctContent _instFctContent, ExecArrContent _arrContent) {
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
            String fullClassNameFound_ = ExecInherits.getSuperGeneric(argClassName_, base_, _context);
            lastType_ = ExecInherits.quickFormat(pair.getType(), fullClassNameFound_, lastType_);
            result_ = new DefaultParamChecker(pair, fectchArgs(_nodes, lastType_, naturalVararg_, _rendStack, null), MethodAccessKind.INSTANCE, CallPrepareState.METHOD, null).checkParams(classNameFound_, prev_, null, _context, _stack);
        }
        ArgumentWrapper argres_ = RendDynOperationNode.processCall(result_, _context, _stack);
        setSimpleArgument(argres_, _nodes, _context, _stack, _rendStack);
    }

}
