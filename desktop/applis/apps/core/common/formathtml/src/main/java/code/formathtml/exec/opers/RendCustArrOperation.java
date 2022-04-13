package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.inherits.ExecFieldTemplates;
import code.expressionlanguage.exec.opers.ExecCustArrOperation;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecOperationInfo;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.blocks.ExecTypeFunctionInst;
import code.expressionlanguage.fwd.blocks.ExecTypeFunctionPair;
import code.expressionlanguage.fwd.opers.ExecInstFctContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.util.CustList;
import code.util.IdMap;

public final class RendCustArrOperation extends RendInvokingOperation implements RendCalculableOperation,RendSettableElResult {

    private final ExecTypeFunctionPair readWrite;

    public RendCustArrOperation(boolean _intermediate, ExecTypeFunction _get, ExecTypeFunction _set, ExecOperationContent _content, ExecInstFctContent _instFctContent, ExecInstFctContent _instFctContentSet) {
        super(_content,_intermediate);
        readWrite = new ExecTypeFunctionPair(new ExecTypeFunctionInst(_instFctContent, _get), new ExecTypeFunctionInst(_instFctContentSet, _set));
    }
    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        CustList<ExecOperationInfo> infos_ = buildInfos(_nodes);
        Argument previous_ = getPreviousArg(this, _nodes, _rendStack);
        Struct parent_ = ExecFieldTemplates.getParent(readWrite.getInstRead().getInst().getAnc(), previous_.getStruct(), _context,  _rendStack.getStackCall());
        ArgumentListCall argumentListCall_ = ExecInvokingOperation.fetchFormattedArgs(_context, _rendStack.getStackCall(), parent_,readWrite.getInstRead(), infos_);
        getArgumentPair(_nodes,this).setArgumentList(argumentListCall_.getArgumentWrappers());
        getArgumentPair(_nodes,this).setArgumentParent(new Argument(parent_));
        processCalling(this,_nodes,null, _context, _rendStack, readWrite.getInstRead());
    }

    @Override
    public boolean resultCanBeSet() {
        return false;
    }

    @Override
    public Argument calculateSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, ContextEl _context, RendStackCall _rendStack) {
        return processCalling(this,_nodes,_right, _context, _rendStack, readWrite.getInstWrite());
    }

    static Argument processCalling(RendMethodOperation _ren, IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, ContextEl _context, RendStackCall _rendStackCall, ExecTypeFunctionInst _in) {
        if (_context.callsOrException(_rendStackCall.getStackCall())) {
            return _right;
        }
        CustList<ArgumentWrapper> argumentList_ = getArgumentPair(_nodes, _ren).getArgumentList();
        Argument argumentParent_ = Argument.getNullableValue(getArgumentPair(_nodes, _ren).getArgumentParent());
        Argument result_ = ExecCustArrOperation.redirect(_context, _rendStackCall.getStackCall(), _in, argumentParent_.getStruct(), ArgumentListCall.wrapCall(argumentList_,_right));
        ArgumentWrapper argres_ = RendDynOperationNode.processCall(result_, _context, _rendStackCall);
        _ren.setSimpleArgument(argres_, _nodes, _context, _rendStackCall);
        return argres_.getValue();
    }

    public ExecTypeFunctionPair getReadWrite() {
        return readWrite;
    }
}
