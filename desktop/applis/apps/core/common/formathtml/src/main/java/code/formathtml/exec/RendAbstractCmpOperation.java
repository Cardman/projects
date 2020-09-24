package code.formathtml.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.CmpOperation;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;

public final class RendAbstractCmpOperation extends RendMethodOperation implements RendCalculableOperation {

    private boolean stringCompare;
    private String op;

    public RendAbstractCmpOperation(CmpOperation _a) {
        super(_a);
        stringCompare = _a.isStringCompare();
        op = _a.getOp();
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
        return op;
    }

}
