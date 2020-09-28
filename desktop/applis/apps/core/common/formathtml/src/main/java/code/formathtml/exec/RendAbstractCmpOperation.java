package code.formathtml.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;

public final class RendAbstractCmpOperation extends RendMethodOperation implements RendCalculableOperation {

    private boolean stringCompare;
    private ExecOperatorContent operatorContent;

    public RendAbstractCmpOperation(ExecOperationContent _content, ExecOperatorContent _operatorContent, boolean _stringCompare) {
        super(_content);
        operatorContent = _operatorContent;
        stringCompare = _stringCompare;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        RendDynOperationNode opOne_ = chidren_.first();
        RendDynOperationNode opTwo_ = chidren_.last();
        Argument first_ = getArgument(_nodes,opOne_);
        Argument second_ = getArgument(_nodes,opTwo_);
        Argument arg_ = calculateCommon(first_, second_);
        setSimpleArgument(arg_, _conf,_nodes);
    }

    private Argument calculateCommon(Argument _one, Argument _two) {
        String op_ = getOp().trim();
        if (stringCompare) {
            return new Argument(NumParsers.compareStr(op_, _one.getStruct(), _two.getStruct()));
        }
        return new Argument(NumParsers.compareNb(op_, _one.getStruct(), _two.getStruct()));
    }

    public String getOp() {
        return operatorContent.getOper();
    }

}
