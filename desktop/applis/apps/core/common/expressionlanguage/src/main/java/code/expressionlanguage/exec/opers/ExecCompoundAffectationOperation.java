package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.expressionlanguage.fwd.opers.ExecStaticEltContent;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.structs.NullStruct;
import code.util.IdMap;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ExecCompoundAffectationOperation extends ExecMethodOperation implements AtomicExecCalculableOperation, CallExecSimpleOperation {

    private ExecOperationNode settable;
    private ExecMethodOperation settableParent;
    private final ExecOperatorContent operatorContent;
    private final ExecStaticEltContent staticEltContent;
    private final ExecTypeFunction pair;
    private final ImplicitMethods converter;


    public ExecCompoundAffectationOperation(ExecOperationContent _opCont, ExecOperatorContent _operatorContent, ExecStaticEltContent _staticEltContent, ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock, ImplicitMethods _converter) {
        super(_opCont);
        operatorContent = _operatorContent;
        staticEltContent = _staticEltContent;
        pair = new ExecTypeFunction(_rootBlock,_named);
        converter = _converter;
    }

    public void setup() {
        settable = ExecAffectationOperation.tryGetSettable(this);
        settableParent = ExecAffectationOperation.tryGetSettableParent(this);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        if (settableParent instanceof ExecSafeDotOperation) {
            ExecOperationNode left_ = settableParent.getFirstChild();
            Argument leftArg_ = getArgument(_nodes,left_);
            if (leftArg_.isNull()) {
                ArgumentsPair pair_ = ExecTemplates.getArgumentPair(_nodes,this);
                pair_.setIndexImplicitCompound(-1);
                pair_.setEndCalculate(true);
                leftArg_ = new Argument(ExecClassArgumentMatching.convertFormatted(NullStruct.NULL_VALUE,_conf, getResultClass().getNames(), _stack));
                setQuickConvertSimpleArgument(leftArg_, _conf, _nodes, _stack);
                return;
            }
        }
        Argument leftArg_ = getFirstArgument(_nodes,this);
        Argument rightArg_ = getLastArgument(_nodes,this);
        ArgumentsPair pair_ = ExecTemplates.getArgumentPair(_nodes,this);
        ArgumentsPair argumentPair_ = ExecTemplates.getArgumentPair(_nodes, getFirstChild());
        if (argumentPair_.isArgumentTest()){
            pair_.setIndexImplicitCompound(-1);
            if (StringUtil.quickEq(operatorContent.getOper(), "&&&=") || StringUtil.quickEq(operatorContent.getOper(), "|||=")) {
                pair_.setEndCalculate(true);
                setSimpleArgument(leftArg_, _conf, _nodes, _stack);
                return;
            }
            setRelOffsetPossibleLastPage(operatorContent.getOpOffset(), _stack);
            Argument arg_ = ExecAffectationOperation.calculateChSetting(settable,_nodes, _conf, leftArg_, _stack);
            pair_.setEndCalculate(true);
            setSimpleArgument(arg_, _conf, _nodes, _stack);
            return;
        }
        if (pair.getFct() != null) {
            ExecInvokingOperation.checkParametersOperators(_conf.getExiting(),_conf, pair, _nodes,this, staticEltContent.getClassName(), staticEltContent.getKind(), _stack);
            return;
        }
        if (StringUtil.quickEq(operatorContent.getOper(), "???=")) {
            if (!leftArg_.isNull()) {
                pair_.setIndexImplicitCompound(-1);
                pair_.setEndCalculate(true);
                setSimpleArgument(leftArg_, _conf, _nodes, _stack);
                return;
            }
        }
        ArgumentsPair pairBefore_ = ExecTemplates.getArgumentPair(_nodes,this);
        ImplicitMethods implicits_ = pairBefore_.getImplicitsCompound();
        int indexImplicit_ = pairBefore_.getIndexImplicitCompound();
        if (implicits_.isValidIndex(indexImplicit_)) {
            String tres_ = implicits_.get(indexImplicit_).getFct().getImportedParametersTypes().first();
            StringList arg_ = new StringList(tres_);
            byte cast_ = ClassArgumentMatching.getPrimitiveCast(tres_, _conf.getStandards().getPrimTypes());
            Argument res_ = ExecNumericOperation.calculateAffect(leftArg_, _conf, rightArg_, operatorContent.getOper(), false, arg_, cast_, _stack);
            pairBefore_.setIndexImplicitCompound(processConverter(_conf,res_,implicits_,indexImplicit_, _stack));
            return;
        }
        setRelOffsetPossibleLastPage(operatorContent.getOpOffset(), _stack);
        Argument arg_ = calculateCompoundSetting(_nodes, _conf, rightArg_, _stack);
        pair_.setEndCalculate(true);
        setSimpleArgument(arg_, _conf, _nodes, _stack);
    }

    private Argument calculateCompoundSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument _rightArg, StackCall _stackCall) {
        Argument arg_ = null;
        if (settable instanceof ExecStdVariableOperation) {
            arg_ = ((ExecStdVariableOperation)settable).calculateCompoundSetting(_nodes, _conf, operatorContent.getOper(), _rightArg, getResultClass(), getResultClass().getUnwrapObjectNb(), _stackCall);
        }
        if (settable instanceof ExecStdRefVariableOperation) {
            arg_ = ((ExecStdRefVariableOperation)settable).calculateCompoundSetting(_nodes, _conf, operatorContent.getOper(), _rightArg, getResultClass(), getResultClass().getUnwrapObjectNb(), _stackCall);
        }
        if (settable instanceof ExecRefParamOperation) {
            arg_ = ((ExecRefParamOperation)settable).calculateCompoundSetting(_nodes, _conf, operatorContent.getOper(), _rightArg, getResultClass(), getResultClass().getUnwrapObjectNb(), _stackCall);
        }
        if (settable instanceof ExecSettableFieldOperation) {
            arg_ = ((ExecSettableFieldOperation)settable).calculateCompoundSetting(_nodes, _conf, operatorContent.getOper(), _rightArg, getResultClass(), getResultClass().getUnwrapObjectNb(), _stackCall);
        }
        if (settable instanceof ExecArrOperation) {
            arg_ = ((ExecArrOperation)settable).calculateCompoundSetting(_nodes, _conf, operatorContent.getOper(), _rightArg, getResultClass(), getResultClass().getUnwrapObjectNb(), _stackCall);
        }
        if (settable instanceof ExecCustArrOperation) {
            arg_ = ((ExecCustArrOperation)settable).calculateCompoundSetting(_nodes, _conf, operatorContent.getOper(), _rightArg, getResultClass(), getResultClass().getUnwrapObjectNb(), _stackCall);
        }
        if (settable instanceof ExecSettableCallFctOperation) {
            arg_ = ((ExecSettableCallFctOperation)settable).calculateCompoundSetting(_nodes, _conf, operatorContent.getOper(), _rightArg, getResultClass(), getResultClass().getUnwrapObjectNb(), _stackCall);
        }
        if (settable instanceof ExecRefTernaryOperation) {
            arg_ = ((ExecRefTernaryOperation)settable).calculateCompoundSetting(_nodes, _conf, operatorContent.getOper(), _rightArg, getResultClass(), getResultClass().getUnwrapObjectNb(), _stackCall);
        }
        return Argument.getNullableValue(arg_);
    }

    @Override
    public void endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right, StackCall _stack) {
        ArgumentsPair pair_ = ExecTemplates.getArgumentPair(_nodes,this);
        setRelOffsetPossibleLastPage(operatorContent.getOpOffset(), _stack);
        ImplicitMethods implicits_ = pair_.getImplicitsCompound();
        int indexImplicit_ = pair_.getIndexImplicitCompound();
        if (implicits_.isValidIndex(indexImplicit_)) {
            pair_.setIndexImplicitCompound(processConverter(_conf, _right, implicits_, indexImplicit_, _stack));
            return;
        }
        if (!pair_.isEndCalculate()) {
            pair_.setEndCalculate(true);
            Argument arg_ = endCalculateCh(settable, _nodes, _conf, _right, _stack);
            setSimpleArgument(arg_, _conf, _nodes, _stack);
            return;
        }
        setSimpleArgument(_right,_conf,_nodes, _stack);
    }
    private static Argument endCalculateCh(ExecOperationNode _set,
                                           IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument _right, StackCall _stackCall){
        Argument arg_ = null;
        if (_set instanceof ExecStdVariableOperation) {
            arg_ = ((ExecStdVariableOperation)_set).endCalculate(_conf, _nodes, _right, _stackCall);
        }
        if (_set instanceof ExecStdRefVariableOperation) {
            arg_ = ((ExecStdRefVariableOperation)_set).endCalculate(_conf, _nodes, _right, _stackCall);
        }
        if (_set instanceof ExecRefParamOperation) {
            arg_ = ((ExecRefParamOperation)_set).endCalculate(_conf, _nodes, _right, _stackCall);
        }
        if (_set instanceof ExecSettableFieldOperation) {
            arg_ = ((ExecSettableFieldOperation)_set).endCalculate(_conf, _nodes, _right, _stackCall);
        }
        if (_set instanceof ExecCustArrOperation) {
            arg_ = ((ExecCustArrOperation)_set).endCalculate(_conf, _nodes, _right, _stackCall);
        }
        if (_set instanceof ExecArrOperation) {
            arg_ = ((ExecArrOperation)_set).endCalculate(_conf, _nodes,_right, _stackCall);
        }
        if (_set instanceof ExecSettableCallFctOperation) {
            arg_ = ((ExecSettableCallFctOperation)_set).endCalculate(_conf, _nodes,_right, _stackCall);
        }
        if (_set instanceof ExecRefTernaryOperation) {
            arg_ = ((ExecRefTernaryOperation)_set).endCalculate(_conf, _nodes,_right, _stackCall);
        }
        return Argument.getNullableValue(arg_);
    }
    public ExecOperationNode getSettable() {
        return settable;
    }

    public String getOper() {
        return operatorContent.getOper();
    }

    public ImplicitMethods getConverter() {
        return converter;
    }
}
