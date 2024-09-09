package code.expressionlanguage.exec.opers;

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
import code.expressionlanguage.structs.NullStruct;
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
        Struct array_ = getPreviousArgument(_nodes,this);
        setQuickNoConvertSimpleArgument(array_, _conf, _nodes, _stack);
    }
    @Override
    public Struct calculateSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Struct _right, StackCall _stack) {
        CustList<ExecOperationInfo> infos_ = buildInfos(_nodes);
        Struct previous_ = getPreviousArg(this, _nodes, _stack.getLastPage());
        Struct parent_ = ExecFieldTemplates.getParent(instWrite.getInst().getAnc(), previous_, _conf, _stack);
        ArgumentListCall argumentListCall_ = fetchFormattedArgs(_conf, _stack, parent_,instWrite, infos_);
        ExecCustArrOperation.getArgument(this,_conf, _stack,instWrite, ArgumentListCall.wrapCall(argumentListCall_.getArgumentWrappers(),_right), parent_);
        return NullStruct.NULL_VALUE;
    }
    public Struct instanceWrite(IdMap<ExecOperationNode, ArgumentsPair> _nodes, AbstractPageEl _last) {
        return ExecOperationNode.instance(this,instWrite.getInst().getAnc(), _nodes, _last);
    }
    public ArgumentListCall argsWrite(ContextEl _cont, Struct _pr, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Struct _right) {
        return ArgumentListCall.wrapCall(args(_cont,instWrite.getPair().getType(),instWrite.getInst().getLastType(),instWrite.getInst().getNaturalVararg(),_pr,_nodes).getArguments().getArgumentWrappers(),_right);
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
