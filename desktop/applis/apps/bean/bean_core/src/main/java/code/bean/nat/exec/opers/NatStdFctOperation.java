package code.bean.nat.exec.opers;

import code.bean.nat.*;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.exec.NatArgumentsPair;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.fwd.opers.NatExecStdFctContent;
import code.bean.nat.*;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.IndexConstants;

public final class NatStdFctOperation extends NatSettableCallFctOperation implements NatRendCalculableOperation {

    private final SpecNatMethod standardMethod;

    public NatStdFctOperation(int _o, boolean _intermediateDottedOperation, NatExecStdFctContent _stdFctContent) {
        super(_o, _intermediateDottedOperation);
        standardMethod = _stdFctContent.getStandardMethod();
    }

    public static NaSt[] getObjects(CustList<NaSt> _args) {
        int len_ = _args.size();
        NaSt[] classes_ = new NaSt[len_];
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            classes_[i] = _args.get(i);
        }
        return classes_;
    }

    @Override
    public void calculate(IdMap<NatExecOperationNode, NatArgumentsPair> _nodes, NatRendStackCall _rendStack) {
        NaSt previous_ = getPreviousArg(this,_nodes, _rendStack);
        CustList<NaSt> firstArgs_ = getArguments(_nodes,this);
        NaSt[] args_ = getObjects(firstArgs_);
        NatCaller caller_ = standardMethod.getCaller();
        calcArg(_nodes, caller_.re(previous_, args_));
    }

}
