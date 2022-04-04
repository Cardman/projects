package code.bean.nat.exec.opers;

import code.bean.nat.NatCaller;
import code.bean.nat.exec.NatArgumentsPair;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.fwd.opers.NatExecFieldOperationContent;
import code.bean.nat.fwd.opers.NatExecSettableOperationContent;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class NatSettableFieldOperation extends
        NatAbstractFieldOperation {

    private final NatExecSettableOperationContent settableFieldContent;
    private final boolean variable;

    public NatSettableFieldOperation(boolean _variable,int _o, NatExecFieldOperationContent _fieldCont, NatExecSettableOperationContent _setFieldCont) {
        super(_o,_fieldCont);
        variable = _variable;
        settableFieldContent = _setFieldCont;
    }

    @Override
    public void calculate(IdMap<NatExecOperationNode, NatArgumentsPair> _nodes, NatRendStackCall _rendStack) {
        if (variable) {
            calcArg(_nodes, Argument.createVoid());
            return;
        }
        Argument previous_ = getPreviousArg(this,_nodes, _rendStack);
        Argument result_;
        Struct default_ = previous_.getStruct();
        NatCaller callerSet_ = settableFieldContent.getField().getCallerGet();
        result_ = new Argument(callerSet_.re(default_, new Struct[]{}));
        Argument arg_ = new ArgumentWrapper(result_, null).getValue();
        calcArg(_nodes, arg_);
    }

    public Argument calculateSetting(IdMap<NatExecOperationNode, NatArgumentsPair> _nodes, Argument _right, NatRendStackCall _rendStack) {
        return processField(_nodes, _right, _rendStack);
    }

    private Argument processField(IdMap<NatExecOperationNode, NatArgumentsPair> _nodes, Argument _right, NatRendStackCall _rendStackCall) {
        Argument prev_ = getPreviousArg(this, _nodes, _rendStackCall);
        NatCaller callerSet_ = settableFieldContent.getField().getCallerSet();
        return new Argument(callerSet_.re(prev_.getStruct(), new Struct[]{_right.getStruct()}));
    }

}
