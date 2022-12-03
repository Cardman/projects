package code.bean.nat.exec.opers;

import code.bean.nat.NatCaller;
import code.bean.nat.exec.NatArgumentsPair;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.fwd.opers.NatExecFieldOperationContent;
import code.bean.nat.fwd.opers.NatExecSettableOperationContent;
import code.expressionlanguage.structs.NullStruct;
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
            calcArg(_nodes, NullStruct.NULL_VALUE);
            return;
        }
        Struct previous_ = getPreviousArg(this,_nodes, _rendStack);
        NatCaller callerSet_ = settableFieldContent.getField().getCallerGet();
        Struct result_ = callerSet_.re(previous_, new Struct[]{});
        calcArg(_nodes, result_);
    }

    public Struct calculateSetting(IdMap<NatExecOperationNode, NatArgumentsPair> _nodes, Struct _right, NatRendStackCall _rendStack) {
        return processField(_nodes, _right, _rendStack);
    }

    private Struct processField(IdMap<NatExecOperationNode, NatArgumentsPair> _nodes, Struct _right, NatRendStackCall _rendStackCall) {
        Struct prev_ = getPreviousArg(this, _nodes, _rendStackCall);
        NatCaller callerSet_ = settableFieldContent.getField().getCallerSet();
        return callerSet_.re(prev_, new Struct[]{_right});
    }

}
