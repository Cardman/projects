package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassArgumentMatching;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.ParamCheckerUtil;
import code.expressionlanguage.exec.symbols.ExecOperSymbol;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;
import code.util.StringList;

public final class ExecCompoundAffectationStringOperation extends ExecCompoundAffectationOperation {

    private final ExecOperSymbol symbol;

    public ExecCompoundAffectationStringOperation(ExecOperationContent _opCont, ExecOperatorContent _operatorContent, StringList _names, ExecOperSymbol _symbol, ImplicitMethods _converter, boolean _staticPostEltContent) {
        super(_opCont, _operatorContent, _converter, _names, _staticPostEltContent);
        symbol = _symbol;
    }


    @Override
    protected void calculateSpec(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        ArgumentsPair pairBefore_ = ExecHelper.getArgumentPair(_nodes,this);
        ImplicitMethods implicits_ = getConverter();
        int indexImplicit_ = pairBefore_.getIndexImplicitConv();
        if (ImplicitMethods.isValidIndex(implicits_,indexImplicit_)) {
            String tres_ = implicits_.get(indexImplicit_).getFct().getImportedParametersTypes().first();
            byte cast_ = ClassArgumentMatching.getPrimitiveCast(tres_, _conf.getStandards().getPrimTypes());
            Argument leftArg_ = getFirstArgument(_nodes,this);
            Argument rightArg_ = getLastArgument(_nodes,this);
            Argument res_ = new Argument(symbol.afterCalculateExc(symbol.calculateOperator(leftArg_.getStruct(), rightArg_.getStruct(), cast_, _conf, _stack.getLastPage()), _conf,_stack));
            pairBefore_.setIndexImplicitConv(ParamCheckerUtil.processConverter(_conf,res_,implicits_,indexImplicit_, _stack));
            return;
        }
        Argument res_ = new Argument(calculated(_nodes,_conf, _stack));
        Argument before_ = firstArg(this,_nodes);
        Argument set_ = calculateChSetting(_nodes,_conf,res_,_stack);
        Argument arg_ = getPrePost(isStaticPostEltContent(), before_, set_);
        setSimpleArgument(arg_, _conf, _nodes, _stack);
    }

    public Struct calculated(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        Struct res_ = calculated(_nodes, _conf, _stack.getLastPage());
        return symbol.afterCalculateExc(res_,_conf,_stack);
    }

    public Struct calculated(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, AbstractPageEl _page) {
        Argument leftArg_ = getFirstArgument(_nodes,this);
        Argument rightArg_ = getLastArgument(_nodes,this);
        return symbol.calculateOperator(leftArg_.getStruct(), rightArg_.getStruct(), getResultClass().getUnwrapObjectNb(), _conf, _page);
    }

    public Struct leftArg(IdMap<ExecOperationNode, ArgumentsPair> _nodes) {
        return NumParsers.unwrapObject(getResultClass().getUnwrapObjectNb(),ArgumentListCall.toStr(getFirstArgument(_nodes,this)));
    }

}
