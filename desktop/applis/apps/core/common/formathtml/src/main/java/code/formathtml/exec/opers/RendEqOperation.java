package code.formathtml.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.BooleanStruct;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class RendEqOperation extends RendMethodOperation implements RendCalculableOperation {

    private final String oper;
    public RendEqOperation(ExecOperationContent _content, String _oper) {
        super(_content);
        oper = _oper;
    }

    private static boolean calculateEq(Argument _a, Argument _b) {
        return _a.getStruct().sameReference(_b.getStruct());
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        Argument first_ = getArgument(_nodes,getFirstNode(this));
        Argument second_ = getArgument(_nodes,getLastNode(this));
        boolean complement_ = false;
        String op_ = oper.trim();
        if (StringUtil.quickEq(op_, DIFF)) {
            complement_ = true;
        }
        boolean b_ = calculateEq(first_, second_);
        if (complement_) {
            b_ = !b_;
        }
        Argument arg_ = new Argument(BooleanStruct.of(b_));
        setSimpleArgument(arg_, _nodes, _context, _stack, _rendStack);
    }
}
