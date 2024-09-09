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
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecInstFctContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;

public final class ExecCustArrReadOperation extends ExecSettableCallFctOperation {

    private final ExecTypeFunctionInst instRead;

    public ExecCustArrReadOperation(ExecTypeFunction _get, ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecInstFctContent _instFctContent) {
        super(_opCont, _intermediateDottedOperation,new ExecArrContent(false));
        this.instRead = new ExecTypeFunctionInst(_instFctContent, _get);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        CustList<ExecOperationInfo> infos_ = buildInfos(_nodes);
        Struct previous_ = getPreviousArg(this, _nodes, _stack.getLastPage());
        Struct parent_ = ExecFieldTemplates.getParent(instRead.getInst().getAnc(), previous_, _conf, _stack);
        ArgumentListCall argumentListCall_ = fetchFormattedArgs(_conf, _stack, parent_, instRead, infos_);
        ExecCustArrOperation.getArgument(this,_conf, _stack,instRead, ArgumentListCall.wrapCall(argumentListCall_.getArgumentWrappers(),null), parent_);
        setCheckedResult(NullStruct.NULL_VALUE, _conf, _nodes, _stack);
    }
    public Struct instance(IdMap<ExecOperationNode, ArgumentsPair> _nodes, AbstractPageEl _last) {
        return ExecOperationNode.instance(this,instRead.getInst().getAnc(), _nodes, _last);
    }
    public ArgumentListCall args(ContextEl _cont, Struct _pr, IdMap<ExecOperationNode, ArgumentsPair> _nodes) {
        return args(_cont,instRead.getPair().getType(),instRead.getInst().getLastType(),instRead.getInst().getNaturalVararg(),_pr,_nodes).getArguments();
    }
    public ExecOverrideInfo poly(ContextEl _cont, Struct _pr) {
        return ExecCustArrOperation.poly(instRead, _cont, _pr);
    }

    public ExecTypeFunctionInst getInstRead() {
        return instRead;
    }
}
