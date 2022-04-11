package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.inherits.ExecFieldTemplates;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecOperationInfo;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.blocks.ExecTypeFunctionPair;
import code.expressionlanguage.fwd.opers.ExecInstFctContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.util.CustList;
import code.util.IdMap;

public final class RendCustArrWriteOperation extends RendInvokingOperation implements RendCalculableOperation,RendSettableElResult {

    private final ExecTypeFunctionPair readWrite;

    public RendCustArrWriteOperation(boolean _intermediate, ExecTypeFunction _get, ExecTypeFunction _set, ExecOperationContent _content, ExecInstFctContent _instFctContent, ExecInstFctContent _instFctContentSet) {
        super(_content,_intermediate);
        readWrite = new ExecTypeFunctionPair(_get,_instFctContent,_set,_instFctContentSet);
    }
    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        Struct array_ = getPreviousArgument(_nodes,this).getStruct();
        Argument a_ = new Argument(array_);
        setQuickNoConvertSimpleArgument(a_, _nodes, _context, _rendStack);
    }

    @Override
    public boolean resultCanBeSet() {
        return true;
    }

    @Override
    public Argument calculateSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, ContextEl _context, RendStackCall _rendStack) {
        CustList<ExecOperationInfo> infos_ = buildInfos(_nodes);
        Argument previous_ = getPreviousArg(this, _nodes, _rendStack);
        Struct parent_ = ExecFieldTemplates.getParent(readWrite.getInstWrite().getAnc(), previous_.getStruct(), _context,  _rendStack.getStackCall());
        ArgumentListCall argumentListCall_ = ExecInvokingOperation.fetchFormattedArgs(_context, _rendStack.getStackCall(), parent_, readWrite.getWrite().getType(), readWrite.getInstWrite(), infos_);
        getArgumentPair(_nodes,this).setArgumentList(argumentListCall_.getArgumentWrappers());
        getArgumentPair(_nodes,this).setArgumentParent(new Argument(parent_));
        return RendCustArrOperation.processCalling(this,_nodes, _right, _context, _rendStack,readWrite.getWrite(),readWrite.getInstWrite());
    }

}
