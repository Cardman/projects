package code.formathtml.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.BooleanStruct;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;

public final class RendUnaryBooleanOperation extends RendAbstractUnaryOperation {

    public RendUnaryBooleanOperation(ExecOperationContent _content) {
        super(_content);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        Argument arg_ = getArgument(_nodes,chidren_.first());
        BooleanStruct o_ = NumParsers.convertToBoolean(arg_.getStruct());
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        Argument a_ = new Argument(o_.neg());
        setSimpleArgument(a_, _conf,_nodes);
    }
}
