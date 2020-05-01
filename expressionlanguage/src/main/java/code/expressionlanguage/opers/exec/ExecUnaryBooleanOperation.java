package code.expressionlanguage.opers.exec;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.UnaryBooleanOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.structs.BooleanStruct;
import code.util.CustList;
import code.util.IdMap;

public final class ExecUnaryBooleanOperation extends ExecAbstractUnaryOperation {

    public ExecUnaryBooleanOperation(UnaryBooleanOperation _u) {
        super(_u);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        Argument arg_ = getArgument(_nodes,chidren_.first());
        BooleanStruct o_ = ClassArgumentMatching.convertToBoolean(arg_.getStruct());
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        boolean b_ = o_.getInstance();
        b_ = !b_;
        Argument a_ = new Argument();
        a_.setObject(b_);
        setSimpleArgument(a_, _conf, _nodes);
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        UnaryBooleanOperation.tryGetArg(this,_conf);
    }

}
