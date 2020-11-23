package code.formathtml.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecUnaryOperation;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendUnaryOperation extends RendAbstractUnaryOperation {
    private String oper;

    public RendUnaryOperation(ExecOperationContent _content, String _oper) {
        super(_content);
        oper = _oper;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context) {
        Argument arg_ = getArgument(_nodes,getFirstNode(this));
        Argument a_ = getArgument(_conf, arg_);
        setSimpleArgument(a_, _conf,_nodes, _context);
    }

    Argument getArgument(Configuration _conf,
                         Argument _in) {
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        ExecClassArgumentMatching to_ = getResultClass();
        return ExecUnaryOperation.getArgument(_in,to_,oper);
    }
}
