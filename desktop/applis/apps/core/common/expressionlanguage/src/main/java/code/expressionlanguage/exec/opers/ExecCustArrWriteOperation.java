package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.ExecFieldTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecOperationInfo;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.blocks.ExecTypeFunctionInst;
import code.expressionlanguage.fwd.opers.ExecInstFctContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;

public final class ExecCustArrWriteOperation extends ExecInvokingOperation implements ExecSettableElResult {

    private final ExecTypeFunctionInst instWrite;

    public ExecCustArrWriteOperation(ExecTypeFunction _set, ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecInstFctContent _instFctContentSet) {
        super(_opCont, _intermediateDottedOperation);
        this.instWrite = new ExecTypeFunctionInst(_instFctContentSet, _set);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        Struct array_ = getPreviousArgument(_nodes,this).getStruct();
        Argument a_ = new Argument(array_);
        setQuickNoConvertSimpleArgument(a_, _conf, _nodes, _stack);
    }
    @Override
    public Argument calculateSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument _right, StackCall _stack) {
        CustList<ExecOperationInfo> infos_ = buildInfos(_nodes);
        Argument previous_ = getPreviousArg(this, _nodes, _stack.getLastPage());
        Struct parent_ = ExecFieldTemplates.getParent(instWrite.getInst().getAnc(), previous_.getStruct(), _conf, _stack);
        ArgumentListCall argumentListCall_ = fetchFormattedArgs(_conf, _stack, parent_,instWrite, infos_);
        return ExecCustArrOperation.getArgument(this,_conf, _stack,instWrite, ArgumentListCall.wrapCall(argumentListCall_.getArgumentWrappers(),_right), parent_);
    }
    public Struct instanceWrite(IdMap<ExecOperationNode, ArgumentsPair> _nodes, AbstractPageEl _last) {
        return ExecOperationNode.instance(this,instWrite.getInst().getAnc(), _nodes, _last);
    }
    public ArgumentListCall argsWrite(ContextEl _cont, Struct _pr, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Struct _right) {
        return ArgumentListCall.wrapCall(args(_cont,instWrite.getPair().getType(),instWrite.getInst().getLastType(),instWrite.getInst().getNaturalVararg(),_pr,_nodes).getArguments().getArgumentWrappers(),ArgumentListCall.toStr(_right));
    }
    public ExecOverrideInfo polyWrite(ContextEl _cont, Struct _pr) {
        return ExecCustArrOperation.poly(instWrite, _cont, _pr);
    }

    public ExecTypeFunctionInst getInstWrite() {
        return instWrite;
    }

    @Override
    public boolean resultCanBeSet() {
        return true;
    }

}
