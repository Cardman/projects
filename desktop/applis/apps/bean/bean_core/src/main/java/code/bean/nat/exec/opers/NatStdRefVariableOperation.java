package code.bean.nat.exec.opers;

import code.bean.nat.exec.NatArgumentsPair;
import code.bean.nat.exec.NatImportingPage;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.variables.VariableWrapperNat;
import code.bean.nat.fwd.opers.NatExecVariableContent;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;
import code.util.StringMap;

public final class NatStdRefVariableOperation extends NatExecLeafOperation implements
        NatRendCalculableOperation {
    private final NatExecVariableContent variableContent;

    public NatStdRefVariableOperation(int _o, NatExecVariableContent _variableContent) {
        super(_o);
        variableContent = _variableContent;
    }

    public static Argument getIndexLoop(NatExecVariableContent _varCont, StringMap<LoopVariable> _vars) {
        return getIndexLoop(_varCont.getVariableName(), _vars);
    }

    public static Argument getIndexLoop(String _val, StringMap<LoopVariable> _vars) {
        LoopVariable locVar_ = _vars.getVal(_val);
        IntStruct str_ = new IntStruct((int) locVar_.getIndex());
        return new Argument(str_);
    }

    public static VariableWrapperNat getWrapper(NatExecVariableContent _varCont, StringMap<VariableWrapperNat> _refParams) {
        return getWrapper(_varCont.getVariableName(), _refParams);
    }

    public static VariableWrapperNat getWrapper(String _val, StringMap<VariableWrapperNat> _refParams) {
        return _refParams.getVal(_val);
    }

    public static Argument getArgValue(VariableWrapperNat _w) {
        return new Argument(getValue(_w));
    }

    public static Struct getValue(VariableWrapperNat _w) {
        if (_w == null) {
            return NullStruct.NULL_VALUE;
        }
        return Argument.getNull(_w.getValue());
    }

    @Override
    public void calculate(IdMap<NatExecOperationNode, NatArgumentsPair> _nodes, NatRendStackCall _rendStack) {
        NatImportingPage ip_ = _rendStack.getLastPage();
        VariableWrapperNat val_ = getWrapper(variableContent, ip_.getRefParams());
//        ArgumentsPair pair_ = getArgumentPair(_nodes, this);
//        pair_.setWrapper(val_);
        Argument arg_ = new ArgumentWrapper(getArgValue(val_), null).getValue();
        calcArg(_nodes, arg_);
    }


}
