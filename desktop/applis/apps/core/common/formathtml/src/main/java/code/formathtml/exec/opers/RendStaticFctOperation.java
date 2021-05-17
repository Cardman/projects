package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.MethodParamChecker;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
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
        int off_ = StringUtil.getFirstPrintableCharIndex(staticFctContent.getMethodName());
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _rendStack);
        String lastType_ = staticFctContent.getLastType();
        int naturalVararg_ = staticFctContent.getNaturalVararg();
        Argument prev_ = new Argument();
        ExecFormattedRootBlock classNameFound_ = staticFctContent.getFormattedType();
        ArgumentWrapper argres_ = RendDynOperationNode.processCall(new MethodParamChecker(pair, fectchArgs(_nodes, lastType_, naturalVararg_, _rendStack, null), staticFctContent.getKind()).checkParams(classNameFound_, prev_, null, _context, _stack), _context, _stack);
        setSimpleArgument(argres_, _nodes, _context, _stack, _rendStack);
    }

}
