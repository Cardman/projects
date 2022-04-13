package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.inherits.ExecFieldTemplates;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecOperationInfo;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.blocks.ExecTypeFunctionInst;
import code.expressionlanguage.fwd.opers.ExecInstFctContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.util.CustList;
import code.util.IdMap;

public final class RendCustArrReadOperation extends RendInvokingOperation implements RendCalculableOperation {

    private final ExecTypeFunctionInst instRead;

    public RendCustArrReadOperation(boolean _intermediate, ExecTypeFunction _get, ExecOperationContent _content, ExecInstFctContent _instFctContent) {
        super(_content,_intermediate);
        this.instRead = new ExecTypeFunctionInst(_instFctContent, _get);
    }
    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        CustList<ExecOperationInfo> infos_ = buildInfos(_nodes);
        Argument previous_ = getPreviousArg(this, _nodes, _rendStack);
        Struct parent_ = ExecFieldTemplates.getParent(instRead.getInst().getAnc(), previous_.getStruct(), _context,  _rendStack.getStackCall());
        ArgumentListCall argumentListCall_ = ExecInvokingOperation.fetchFormattedArgs(_context, _rendStack.getStackCall(), parent_, instRead, infos_);
        getArgumentPair(_nodes,this).setArgumentList(argumentListCall_.getArgumentWrappers());
        getArgumentPair(_nodes,this).setArgumentParent(new Argument(parent_));
        RendCustArrOperation.processCalling(this,_nodes,null, _context, _rendStack, instRead);
    }

}
