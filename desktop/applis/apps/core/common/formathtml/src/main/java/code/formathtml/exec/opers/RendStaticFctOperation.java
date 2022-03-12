package code.formathtml.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.opers.ExecStaticFctOperation;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStaticFctContent;
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
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        int off_ = StringUtil.getFirstPrintableCharIndex(staticFctContent.getMethodName());
        setRelOffsetPossibleLastPage(off_, _rendStack);
        ExecFormattedRootBlock classNameFound_ = ExecFormattedRootBlock.formatType(staticFctContent.getElts(), _rendStack);
        String lastType_ = ExecFormattedRootBlock.formatLastType(classNameFound_,staticFctContent);
//        int naturalVararg_ = staticFctContent.getNaturalVararg();
//        Argument prev_ = new Argument();
        ArgumentWrapper argres_ = RendDynOperationNode.processCall(ExecStaticFctOperation.prep(_context,_rendStack.getStackCall(),classNameFound_,lastType_,buildInfos(_nodes),staticFctContent,pair), _context, _rendStack);
        setSimpleArgument(argres_, _nodes, _context, _rendStack);
    }

}
