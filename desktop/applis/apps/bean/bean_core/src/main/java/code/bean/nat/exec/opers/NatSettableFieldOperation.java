package code.bean.nat.exec.opers;

import code.bean.nat.NatCaller;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.fwd.opers.NatExecFieldOperationContent;
import code.bean.nat.fwd.opers.NatExecSettableOperationContent;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.IdMap;

public final class NatSettableFieldOperation extends
        NatAbstractFieldOperation {

    private final NatExecSettableOperationContent settableFieldContent;

    public NatSettableFieldOperation(ExecOperationContent _opCont, NatExecFieldOperationContent _fieldCont, NatExecSettableOperationContent _setFieldCont) {
        super(_opCont,_fieldCont);
        settableFieldContent = _setFieldCont;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, NatRendStackCall _rendStack) {
        Argument previous_ = getPreviousArg(this,this,_nodes, _rendStack);
        Argument result_;
        Struct default_ = previous_.getStruct();
        NatCaller callerSet_ = settableFieldContent.getField().getCallerGet();
        result_ = new Argument(callerSet_.re(default_, new Struct[]{}));
        Argument arg_ = new ArgumentWrapper(result_, null).getValue();
        calcArg(_nodes, arg_);
    }

    public Argument calculateSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, NatRendStackCall _rendStack) {
        return processField(_nodes, _right, _rendStack);
    }

    private Argument processField(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, NatRendStackCall _rendStackCall) {
        Argument prev_ = getPreviousArg(this,this, _nodes, _rendStackCall);
        NatCaller callerSet_ = settableFieldContent.getField().getCallerSet();
        return new Argument(callerSet_.re(prev_.getStruct(), new Struct[]{_right.getStruct()}));
    }

}
