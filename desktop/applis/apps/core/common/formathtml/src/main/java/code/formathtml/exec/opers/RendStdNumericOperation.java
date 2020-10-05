package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;

public abstract class RendStdNumericOperation extends RendNumericOperation {
    private String op;

    public RendStdNumericOperation(ExecOperationContent _content, int _opOffset, String _op) {
        super(_content, _opOffset);
        op = _op;

    }

    abstract Argument calculateOper(Argument _a, String _op, Argument _b, ContextEl _cont);

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        Argument a_ = getArgument(_nodes,chidren_.first());
        Argument c_ = getArgument(_nodes,chidren_.last());
        setRelativeOffsetPossibleLastPage(getIndexInEl()+getOpOffset(), _conf);
        Argument r_;
        r_ = calculateOper(a_, op, c_, _context);
        a_ = r_;
        setSimpleArgument(a_, _conf,_nodes, _context);
    }
}
