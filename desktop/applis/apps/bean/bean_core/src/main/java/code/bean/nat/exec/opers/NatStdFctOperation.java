package code.bean.nat.exec.opers;

import code.bean.nat.NatCaller;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.fwd.opers.NatExecStdFctContent;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.IndexConstants;

public final class NatStdFctOperation extends NatSettableCallFctOperation implements NatRendCalculableOperation {

    private final SpecNatMethod standardMethod;

    public NatStdFctOperation(ExecOperationContent _content, boolean _intermediateDottedOperation, NatExecStdFctContent _stdFctContent) {
        super(_content, _intermediateDottedOperation);
        standardMethod = _stdFctContent.getStandardMethod();
    }

    public static Struct[] getObjects(Argument... _args) {
        int len_ = _args.length;
        Struct[] classes_ = new Struct[len_];
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            classes_[i] = _args[i].getStruct();
        }
        return classes_;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, NatRendStackCall _rendStack) {
        Argument previous_ = NatAbstractFieldOperation.getPreviousArg(this, this,_nodes, _rendStack);
        CustList<Argument> firstArgs_ = RendDynOperationNode.getArguments(_nodes,this);
        Struct[] args_ = getObjects(Argument.toArgArray(firstArgs_));
        Struct instance_ = previous_.getStruct();
        NatCaller caller_ = standardMethod.getCaller();
        Argument argres_ = new Argument(caller_.re(instance_, args_));
        calcArg(_nodes, argres_);
    }

}
