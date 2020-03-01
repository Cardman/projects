package code.formathtml.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.UnaryBooleanOperation;
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
        BooleanStruct o_ = (BooleanStruct) arg_.getStruct();
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        boolean b_ = o_.getInstance();
        b_ = !b_;
        Argument a_ = new Argument();
        a_.setObject(b_);
        setSimpleArgument(a_, _conf,_nodes);
    }
}
