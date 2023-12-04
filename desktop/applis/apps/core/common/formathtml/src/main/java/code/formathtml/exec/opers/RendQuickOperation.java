package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.opers.CompoundedOperator;
import code.expressionlanguage.exec.opers.ExecCompoundAffectationStringOperation;
import code.expressionlanguage.exec.symbols.ExecOperSymbol;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;


public final class RendQuickOperation extends RendMethodOperation implements RendCalculableOperation, CompoundedOperator {

    private final ExecOperatorContent operatorContent;
    private final ExecOperSymbol operSymbol;
    private final ImplicitMethods conv = new ImplicitMethods();

    public RendQuickOperation(ExecOperationContent _content, ExecOperatorContent _off, ExecOperSymbol _op) {
        super(_content);
        operatorContent = _off;
        operSymbol = _op;
    }

    static void endCalculate(RendDynOperationNode _current, IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack, Argument _argres, ImplicitMethods _converter) {
        Argument argres_ = _argres;
        if (!_converter.isEmpty()) {
            Argument res_ = tryConvert(_converter, argres_, _context, _rendStack);
            if (res_ == null) {
                return;
            }
            argres_ = res_;
        }
        _current.setSimpleArgument(argres_, _nodes, _context, _rendStack);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
//        setRelativeOffsetPossibleLastPage(opOffset, _rendStack);
        setRelOffsetPossibleLastPage(operatorContent.getOpOffset(), _rendStack);
        RendDynOperationNode first_ = getFirstNode(this);
        ArgumentsPair argumentPair_ = getArgumentPair(_nodes, first_);
        if (argumentPair_.isArgumentTest()){
            Argument f_ = getArgument(_nodes, first_);
            setQuickConvertSimpleArgument(f_, _nodes, _context, _rendStack);
            return;
        }
        Argument f_ = getArgument(_nodes, first_);
        Struct abs_ = f_.getStruct();
        Argument a_ = getArgument(_nodes,getLastNode(this));
        Argument arg_ = new Argument(ExecCompoundAffectationStringOperation.calculatedValue(operSymbol, abs_, a_.getStruct(), _context, _rendStack, _rendStack.getLastPage()));
        endCalculate(this,_nodes,_context,_rendStack,arg_,conv);
    }

    public ImplicitMethods getConv() {
        return conv;
    }

    @Override
    public String getOper() {
        return operatorContent.getOper();
    }
}
