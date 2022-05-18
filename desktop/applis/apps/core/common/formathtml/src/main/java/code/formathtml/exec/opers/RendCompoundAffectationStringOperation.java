package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassArgumentMatching;
import code.expressionlanguage.exec.symbols.ExecOperSymbol;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;
import code.util.StringList;

public final class RendCompoundAffectationStringOperation extends RendCompoundAffectationOperation {

    private final ExecOperSymbol symbol;

    public RendCompoundAffectationStringOperation(ExecOperationContent _opCont, ExecOperatorContent _operatorContent, StringList _names, ExecOperSymbol _symbol, ImplicitMethods _converter, boolean _staticPostEltContent) {
        super(_opCont, _operatorContent,_converter, _names, _staticPostEltContent);
        symbol = _symbol;
    }


    @Override
    protected void calculateSpec(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        RendDynOperationNode left_ = getFirstNode(this);
        RendDynOperationNode right_ = getLastNode(this);
        Argument leftArg_ = getArgument(_nodes,left_);
        Argument rightArg_ = getArgument(_nodes,right_);
        ImplicitMethods converter_ = getConverter();
        byte cast_;
        if (converter_ != null) {
            String tres_ = converter_.get(0).getFct().getImportedParametersTypes().get(0);
            cast_ = ClassArgumentMatching.getPrimitiveCast(tres_, _context.getStandards().getPrimTypes());
        } else {
            cast_ = getResultClass().getUnwrapObjectNb();
        }
        Argument res_ = new Argument(symbol.calculateOperator(leftArg_.getStruct(), rightArg_.getStruct(), cast_, _context, _rendStack));
        process(this,_nodes, _context, _rendStack,res_);
    }

}
