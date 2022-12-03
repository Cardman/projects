package code.bean.nat.exec.opers;

import code.bean.nat.NatCaller;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.exec.NatArgumentsPair;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.fwd.opers.NatExecStdFctContent;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.IndexConstants;

public final class NatStdFctOperation extends NatSettableCallFctOperation implements NatRendCalculableOperation {

    private final SpecNatMethod standardMethod;

    public NatStdFctOperation(int _o, boolean _intermediateDottedOperation, NatExecStdFctContent _stdFctContent) {
        super(_o, _intermediateDottedOperation);
        standardMethod = _stdFctContent.getStandardMethod();
    }

    public static Struct[] getObjects(CustList<Struct> _args) {
        int len_ = _args.size();
        Struct[] classes_ = new Struct[len_];
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            classes_[i] = _args.get(i);
        }
        return classes_;
    }

    @Override
    public void calculate(IdMap<NatExecOperationNode, NatArgumentsPair> _nodes, NatRendStackCall _rendStack) {
        Struct previous_ = getPreviousArg(this,_nodes, _rendStack);
        CustList<Struct> firstArgs_ = getArguments(_nodes,this);
        Struct[] args_ = getObjects(firstArgs_);
        NatCaller caller_ = standardMethod.getCaller();
        calcArg(_nodes, caller_.re(previous_, args_));
    }

}
