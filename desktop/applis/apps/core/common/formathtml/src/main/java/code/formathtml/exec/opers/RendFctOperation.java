package code.formathtml.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.inherits.ExecFieldTemplates;
import code.expressionlanguage.exec.opers.ExecFctOperation;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.blocks.ExecTypeFunctionInst;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecInstFctContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;

public final class RendFctOperation extends RendSettableCallFctOperation implements RendCalculableOperation {

    private final ExecTypeFunctionInst inst;
    public RendFctOperation(ExecTypeFunction _pair, ExecOperationContent _content, ExecInstFctContent _instFctContent, boolean _intermediateDottedOperation, ExecArrContent _arrContent) {
        super(_content, _intermediateDottedOperation, _arrContent);
        inst = new ExecTypeFunctionInst(_instFctContent,_pair);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        Argument previous_ = getPreviousArg(this,_nodes, _rendStack);
        int off_ = inst.getInst().getMethodName();
        setRelOffsetPossibleLastPage(off_, _rendStack);
        Argument parent_ = new Argument(ExecFieldTemplates.getParent(inst.getInst().getAnc(), previous_.getStruct(), _context, _rendStack.getStackCall()));
        ArgumentListCall args_ = ExecInvokingOperation.fetchFormattedArgs(_context, _rendStack.getStackCall(), parent_.getStruct(), inst, buildInfos(_nodes));
        getArgumentPair(_nodes,this).setArgumentParent(parent_);
        getArgumentPair(_nodes,this).setArgumentList(args_.getArgumentWrappers());
        Argument result_ = ExecFctOperation.prep(_context,_rendStack.getStackCall(), inst, parent_, args_);
        ArgumentWrapper argres_ = RendDynOperationNode.processCall(result_, _context, _rendStack);
        setSimpleArgument(argres_, _nodes, _context, _rendStack);
    }

}
