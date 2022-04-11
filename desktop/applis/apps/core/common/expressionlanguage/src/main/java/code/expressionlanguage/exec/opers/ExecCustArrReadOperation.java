package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecFieldTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecOperationInfo;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.blocks.ExecTypeFunctionPair;
import code.expressionlanguage.fwd.opers.ExecInstFctContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;

public final class ExecCustArrReadOperation extends ExecInvokingOperation {

    private final ExecTypeFunctionPair readWrite;

    public ExecCustArrReadOperation(ExecTypeFunction _get, ExecTypeFunction _set, ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecInstFctContent _instFctContent, ExecInstFctContent _instFctContentSet) {
        super(_opCont, _intermediateDottedOperation);
        readWrite = new ExecTypeFunctionPair(_get,_instFctContent,_set,_instFctContentSet);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        CustList<ExecOperationInfo> infos_ = buildInfos(_nodes);
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        Struct parent_ = ExecFieldTemplates.getParent(readWrite.getInstRead().getAnc(), previous_.getStruct(), _conf, _stack);
        ArgumentListCall argumentListCall_ = fetchFormattedArgs(_conf, _stack, parent_, readWrite.getRead().getType(), readWrite.getInstRead(), infos_);
        Argument res_ = ExecCustArrOperation.getArgument(this,_conf, _stack,readWrite.getRead(),readWrite.getInstRead(),ArgumentListCall.wrapCall(argumentListCall_.getArgumentWrappers(),null), parent_);
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

}
