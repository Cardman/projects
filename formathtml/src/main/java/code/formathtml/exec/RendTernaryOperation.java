package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.AbstractTernaryOperation;
import code.expressionlanguage.structs.BooleanStruct;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;

public final class RendTernaryOperation extends RendMethodOperation implements RendCalculableOperation {

    private int offsetLocal;

    public RendTernaryOperation(AbstractTernaryOperation _t) {
        super(_t);
        offsetLocal = _t.getOffsetLocal();
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        CustList<Argument> arguments_ = getArguments(_nodes,this);
        Argument res_ = getArgument(arguments_, _conf);
        setSimpleArgument(res_, _conf,_nodes);
    }

    Argument  getArgument(CustList<Argument> _arguments, Configuration _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offsetLocal, _conf);
        Boolean obj_ = ((BooleanStruct) _arguments.first().getStruct()).getInstance();
        Argument arg_;
        if (obj_) {
            arg_ = _arguments.get(CustList.SECOND_INDEX);
        } else {
            arg_ = _arguments.last();
        }
        return arg_;
    }
}
