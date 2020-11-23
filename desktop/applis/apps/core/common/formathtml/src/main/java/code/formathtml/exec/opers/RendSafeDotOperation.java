package code.formathtml.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.NullStruct;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendSafeDotOperation extends RendAbstractDotOperation {

    public RendSafeDotOperation(ExecOperationContent _d) {
        super(_d);
    }
    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context) {
        RendDynOperationNode o_ = getFirstNode(this);
        RendDynOperationNode l_ = getLastNode(this);
        Argument a_ = getArgument(_nodes,o_);
        if (a_.isNull()&&!(l_ instanceof RendLambdaOperation)) {
            a_ = new Argument(ExecClassArgumentMatching.convert(NullStruct.NULL_VALUE, _context, getResultClass().getNames()));
            setQuickConvertSimpleArgument(a_, _nodes, _context);
            return;
        }
        calculateDot(_nodes,_conf, _context);
    }
}
