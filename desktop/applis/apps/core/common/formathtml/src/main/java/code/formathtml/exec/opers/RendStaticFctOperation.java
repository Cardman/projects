package code.formathtml.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.opers.ExecStaticFctOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStaticFctContent;
import code.expressionlanguage.structs.NullStruct;
import code.formathtml.exec.RendStackCall;
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
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        int off_ = StringUtil.getFirstPrintableCharIndex(staticFctContent.getMethodName());
        setRelOffsetPossibleLastPage(off_, _rendStack);
        ExecFormattedRootBlock classNameFound_ = ExecFormattedRootBlock.formatType(staticFctContent.getElts(), _rendStack);
        String lastType_ = ExecFormattedRootBlock.formatLastType(classNameFound_,staticFctContent);
//        int naturalVararg_ = staticFctContent.getNaturalVararg();
//        Argument prev_ = new Argument();
        ArgumentListCall args_ = ExecInvokingOperation.fectchArgs(lastType_, staticFctContent.getNaturalVararg(), _context, _rendStack.getStackCall(), buildInfos(_nodes));
        getArgumentPair(_nodes,this).setArgumentList(args_.getArgumentWrappers());
        ExecStaticFctOperation.prep(_context,_rendStack.getStackCall(),classNameFound_, staticFctContent,pair, args_);
        ArgumentWrapper argres_ = RendDynOperationNode.processCall(ArgumentListCall.toStr(NullStruct.NULL_VALUE), _context, _rendStack);
        setSimpleArgument(argres_, _nodes, _context, _rendStack);
    }

}
