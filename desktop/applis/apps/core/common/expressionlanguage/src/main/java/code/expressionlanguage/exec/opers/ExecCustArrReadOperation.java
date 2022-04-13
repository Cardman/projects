package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecFieldTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecOperationInfo;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.blocks.ExecTypeFunctionInst;
import code.expressionlanguage.fwd.opers.ExecInstFctContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;

public final class ExecCustArrReadOperation extends ExecInvokingOperation {

    private final ExecTypeFunctionInst instRead;

    public ExecCustArrReadOperation(ExecTypeFunction _get, ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecInstFctContent _instFctContent) {
        super(_opCont, _intermediateDottedOperation);
        this.instRead = new ExecTypeFunctionInst(_instFctContent, _get);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        CustList<ExecOperationInfo> infos_ = buildInfos(_nodes);
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        Struct parent_ = ExecFieldTemplates.getParent(instRead.getInst().getAnc(), previous_.getStruct(), _conf, _stack);
        ArgumentListCall argumentListCall_ = fetchFormattedArgs(_conf, _stack, parent_, instRead, infos_);
        Argument res_ = ExecCustArrOperation.getArgument(this,_conf, _stack,instRead, ArgumentListCall.wrapCall(argumentListCall_.getArgumentWrappers(),null), parent_);
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

}
