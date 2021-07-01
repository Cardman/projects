package code.formathtml.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.opers.ExecFctOperation;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecInstFctContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendFctOperation extends RendSettableCallFctOperation implements RendCalculableOperation {

    private final ExecInstFctContent instFctContent;

    private final ExecTypeFunction pair;
    public RendFctOperation(ExecTypeFunction _pair, ExecOperationContent _content, ExecInstFctContent _instFctContent, boolean _intermediateDottedOperation, ExecArrContent _arrContent) {
        super(_content, _intermediateDottedOperation, _arrContent);
        instFctContent = _instFctContent;
        pair= _pair;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        Argument previous_ = getPreviousArg(this,_nodes, _rendStack);
        int off_ = instFctContent.getMethodName();
        setRelOffsetPossibleLastPage(off_, _rendStack);
        Argument result_ = ExecFctOperation.prep(_context,_rendStack.getStackCall(),previous_,buildInfos(_nodes),instFctContent,pair);
        ArgumentWrapper argres_ = RendDynOperationNode.processCall(result_, _context, _rendStack);
        setSimpleArgument(argres_, _nodes, _context, _rendStack);
    }

}
