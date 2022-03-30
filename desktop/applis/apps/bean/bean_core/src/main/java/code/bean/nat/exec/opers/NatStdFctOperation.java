package code.bean.nat.exec.opers;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.fwd.opers.NatExecStdFctContent;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.IndexConstants;

public final class NatStdFctOperation extends NatSettableCallFctOperation implements NatRendCalculableOperation {

    private final ClassMethodId classMethodId;
    private final SpecNatMethod standardMethod;

    public NatStdFctOperation(ExecOperationContent _content, boolean _intermediateDottedOperation, NatExecStdFctContent _stdFctContent) {
        super(_content, _intermediateDottedOperation);
        standardMethod = _stdFctContent.getStandardMethod();
        classMethodId = new ClassMethodId(_stdFctContent.getFoundClass(), standardMethod.getId());
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
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, NatRendStackCall _rendStack) {
        Argument previous_ = NatAbstractFieldOperation.getPreviousArg(this, this,_nodes, _rendStack);
        CustList<Argument> firstArgs_ = RendDynOperationNode.getArguments(_nodes,this);
        Struct[] args_ = getObjects(Argument.toArgArray(firstArgs_));
        Struct instance_ = previous_.getStruct();
        if (instance_ instanceof ArrayStruct) {
            Argument argres_ = new Argument(BooleanStruct.of(((ArrayStruct)instance_).getLength()==0));
            calcArg(_nodes, argres_);
            return;
        }
        NatCaller caller_ = standardMethod.getCaller();
        if (caller_ != null) {
            Argument argres_ = new Argument(caller_.re(instance_, args_));
            calcArg(_nodes, argres_);
            return;
        }
        Argument argres_ = new Argument(((BeanNatCommonLgNames)_advStandards).getOtherResultLoc(instance_,classMethodId, args_));
        calcArg(_nodes, argres_);
    }

}
