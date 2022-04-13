package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.opers.ExecChoiceFctOperation;
import code.expressionlanguage.exec.util.ExecOperationInfo;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.blocks.ExecTypeFunctionInst;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecInstFctContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.RendStackCall;
import code.util.CustList;
import code.util.IdMap;

public final class RendChoiceFctOperation extends RendSettableCallFctOperation implements RendCalculableOperation {

    private final ExecTypeFunctionInst inst;
    public RendChoiceFctOperation(ExecTypeFunction _pair, ExecOperationContent _content, boolean _intermediateDottedOperation, ExecInstFctContent _instFctContent, ExecArrContent _arrContent) {
        super(_content, _intermediateDottedOperation, _arrContent);
        inst = new ExecTypeFunctionInst(_instFctContent,_pair);
    }


    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        Argument previous_ = getPreviousArg(this,_nodes, _rendStack);
        int off_ = inst.getInst().getMethodName();
        setRelOffsetPossibleLastPage(off_, _rendStack);
        CustList<ExecOperationInfo> infos_ = buildInfos(_nodes);
        Argument result_ = ExecChoiceFctOperation.prep(_context,_rendStack.getStackCall(),previous_,infos_,inst);
        ArgumentWrapper argres_ = RendDynOperationNode.processCall(result_, _context, _rendStack);
        setSimpleArgument(argres_, _nodes, _context, _rendStack);
    }

}
