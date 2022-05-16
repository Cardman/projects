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
        Argument before_ = firstArg(this,_nodes);
        RendDynOperationNode left_ = getFirstNode(this);
        RendDynOperationNode right_ = getLastNode(this);
        Argument leftArg_ = getArgument(_nodes,left_);
        Argument rightArg_ = getArgument(_nodes,right_);
        ImplicitMethods converter_ = getConverter();
        if (converter_ != null) {
            String tres_ = converter_.get(0).getFct().getImportedParametersTypes().get(0);
            byte cast_ = ClassArgumentMatching.getPrimitiveCast(tres_, _context.getStandards().getPrimTypes());
            Argument res_ = new Argument(symbol.calculateOperator(leftArg_.getStruct(), rightArg_.getStruct(), cast_, _context, _rendStack.getStackCall()));
            Argument conv_ = tryConvert(converter_, res_, _context, _rendStack);
            if (conv_ == null) {
                return;
            }
            res_ = conv_;
            Argument aff_ = calculateChSetting(_nodes, res_, _context, _rendStack);
            Argument arg_ = RendCompoundAffectationOperation.getPrePost(isStaticPostEltContent(),before_,aff_);
            setSimpleArgument(arg_, _nodes, _context, _rendStack);
            return;
        }
        Argument res_ = new Argument(symbol.calculateOperator(leftArg_.getStruct(), rightArg_.getStruct(), getResultClass().getUnwrapObjectNb(), _context, _rendStack.getStackCall()));
        Argument aff_ = calculateChSetting(_nodes, res_, _context, _rendStack);
        Argument arg_ = RendCompoundAffectationOperation.getPrePost(isStaticPostEltContent(),before_,aff_);
        setSimpleArgument(arg_, _nodes, _context, _rendStack);

//        Argument leftArg_ = getArgument(_nodes,getFirstNode(this));
//        Argument rightArg_ = getArgument(_nodes,getLastNode(this));
//        Argument res_ = ExecCatOperation.localSumDiff(leftArg_, rightArg_, _context);
//        Argument arg_ = calculateChSetting(_nodes,res_, _context,_rendStack);
//        setSimpleArgument(arg_, _nodes, _context, _rendStack);
    }

}
