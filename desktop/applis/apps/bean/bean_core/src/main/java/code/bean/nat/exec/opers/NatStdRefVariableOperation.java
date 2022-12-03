package code.bean.nat.exec.opers;

import code.bean.nat.exec.*;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.variables.VariableWrapperNat;
import code.bean.nat.fwd.opers.NatExecVariableContent;
import code.expressionlanguage.structs.*;
import code.util.IdMap;
import code.util.StringMap;

public final class NatStdRefVariableOperation extends NatExecLeafOperation implements
        NatRendCalculableOperation {
    private final NatExecVariableContent variableContent;

    public NatStdRefVariableOperation(int _o, NatExecVariableContent _variableContent) {
        super(_o);
        variableContent = _variableContent;
    }

    public static Struct getIndexLoop(NatExecVariableContent _varCont, StringMap<Integer> _vars) {
        return getIndexLoop(_varCont.getVariableName(), _vars);
    }

    public static Struct getIndexLoop(String _val, StringMap<Integer> _vars) {
        int locVar_ = _vars.getVal(_val);
        return new IntStruct(locVar_);
    }

    public static VariableWrapperNat getWrapper(NatExecVariableContent _varCont, StringMap<VariableWrapperNat> _refParams) {
        return getWrapper(_varCont.getVariableName(), _refParams);
    }

    public static VariableWrapperNat getWrapper(String _val, StringMap<VariableWrapperNat> _refParams) {
        return _refParams.getVal(_val);
    }

    public static Struct getValue(VariableWrapperNat _w) {
        if (_w == null) {
            return NullStruct.NULL_VALUE;
        }
        return _w.getValue();
    }

    @Override
    public void calculate(IdMap<NatExecOperationNode, NatArgumentsPair> _nodes, NatRendStackCall _rendStack) {
        NatImportingPageAbs ip_ = _rendStack.getLastPage();
        VariableWrapperNat val_ = getWrapper(variableContent, ip_.getRefParams());
//        ArgumentsPair pair_ = getArgumentPair(_nodes, this);
//        pair_.setWrapper(val_);
        calcArg(_nodes, getValue(val_));
    }


}
