package code.formathtml.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.UnaryBooleanOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.structs.BooleanStruct;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;

public final class RendUnaryBooleanOperation extends RendAbstractUnaryOperation {

    public RendUnaryBooleanOperation(UnaryBooleanOperation _u) {
        super(_u);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        Argument arg_ = getArgument(_nodes,chidren_.first());
        BooleanStruct o_ = ClassArgumentMatching.convertToBoolean(arg_.getStruct());
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        Argument a_ = new Argument();
        a_.setStruct(o_.neg());
        setSimpleArgument(a_, _conf,_nodes);
    }
}
