package code.bean.nat.exec.opers;

import code.bean.nat.*;
import code.bean.nat.exec.NatArgumentsPair;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.fwd.opers.NatExecFieldOperationContent;
import code.bean.nat.fwd.opers.NatExecSettableOperationContent;
import code.bean.nat.*;
import code.bean.nat.*;
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
            calcArg(_nodes, NaNu.NULL_VALUE);
            return;
        }
        NaSt previous_ = getPreviousArg(this,_nodes, _rendStack);
        NatCaller callerSet_ = settableFieldContent.getField().getCallerGet();
        NaSt result_ = callerSet_.re(previous_, new NaSt[]{});
        calcArg(_nodes, result_);
    }

    public NaSt calculateSetting(IdMap<NatExecOperationNode, NatArgumentsPair> _nodes, NaSt _right, NatRendStackCall _rendStack) {
        return processField(_nodes, _right, _rendStack);
    }

    private NaSt processField(IdMap<NatExecOperationNode, NatArgumentsPair> _nodes, NaSt _right, NatRendStackCall _rendStackCall) {
        NaSt prev_ = getPreviousArg(this, _nodes, _rendStackCall);
        NatCaller callerSet_ = settableFieldContent.getField().getCallerSet();
        return callerSet_.re(prev_, new NaSt[]{_right});
    }

}
