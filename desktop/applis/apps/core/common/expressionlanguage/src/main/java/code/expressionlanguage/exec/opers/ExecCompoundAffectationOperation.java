package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.expressionlanguage.fwd.opers.ExecStaticEltContent;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.structs.NullStruct;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class ExecCompoundAffectationOperation extends ExecMethodOperation implements AtomicExecCalculableOperation, CallExecSimpleOperation {

    private ExecOperationNode settable;
    private ExecMethodOperation settableParent;
    private ExecOperatorContent operatorContent;
    private ExecStaticEltContent staticEltContent;
    private ExecNamedFunctionBlock named;
    private ExecRootBlock rootBlock;
    private ImplicitMethods converter;


    public ExecCompoundAffectationOperation(ExecOperationContent _opCont, ExecOperatorContent _operatorContent, ExecStaticEltContent _staticEltContent, ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock, ImplicitMethods _converter) {
        super(_opCont);
        operatorContent = _operatorContent;
        staticEltContent = _staticEltContent;
        named = _named;
        rootBlock = _rootBlock;
        converter = _converter;
    }

    public void setup() {
        settable = ExecAffectationOperation.tryGetSettable(this);
        settableParent = ExecAffectationOperation.tryGetSettableParent(this);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        if (settableParent instanceof ExecSafeDotOperation) {
            ExecOperationNode left_ = settableParent.getFirstChild();
            Argument leftArg_ = getArgument(_nodes,left_);
            if (leftArg_.isNull()) {
                ArgumentsPair pair_ = getArgumentPair(_nodes,this);
                pair_.setIndexImplicitCompound(-1);
                pair_.setEndCalculate(true);
                leftArg_ = new Argument(ExecClassArgumentMatching.convertFormatted(NullStruct.NULL_VALUE,_conf, getResultClass().getNames()));
                setQuickConvertSimpleArgument(leftArg_, _conf, _nodes);
                return;
            }
        }
        CustList<ExecOperationNode> list_ = getChildrenNodes();
        ExecOperationNode left_ = list_.first();
        ExecOperationNode right_ = list_.last();
        Argument leftArg_ = getArgument(_nodes,left_);
        Argument rightArg_ = getArgument(_nodes,right_);
        ArgumentsPair pair_ = getArgumentPair(_nodes,this);
        ArgumentsPair argumentPair_ = getArgumentPair(_nodes, left_);
        if (argumentPair_.isArgumentTest()){
            pair_.setIndexImplicitCompound(-1);
            setRelativeOffsetPossibleLastPage(getIndexInEl()+ operatorContent.getOpOffset(),_conf);
            Argument arg_ = ExecAffectationOperation.calculateChSetting(settable,_nodes, _conf, leftArg_);
            pair_.setEndCalculate(true);
            setSimpleArgument(arg_, _conf, _nodes);
            return;
        }
        if (named != null) {
            CustList<ExecOperationNode> chidren_ = new CustList<ExecOperationNode>();
            chidren_.add(left_);
            chidren_.add(right_);
            CustList<Argument> arguments_ = new CustList<Argument>();
            arguments_.add(leftArg_);
            arguments_.add(rightArg_);
            CustList<Argument> firstArgs_ = ExecInvokingOperation.listArguments(chidren_, -1, EMPTY_STRING, arguments_);
            ExecInvokingOperation.checkParametersOperators(_conf.getExiting(),_conf, rootBlock, named, firstArgs_, staticEltContent.getClassName(), staticEltContent.getKind());
            return;
        }
        ArgumentsPair pairBefore_ = getArgumentPair(_nodes,this);
        ImplicitMethods implicits_ = pairBefore_.getImplicitsCompound();
        int indexImplicit_ = pairBefore_.getIndexImplicitCompound();
        if (implicits_.isValidIndex(indexImplicit_)) {
            String tres_ = implicits_.get(indexImplicit_).getImportedParametersTypes().first();
            Argument res_;
            StringList arg = new StringList(tres_);
            byte cast_ = ClassArgumentMatching.getPrimitiveCast(tres_, _conf.getStandards().getPrimTypes());
            res_ = ExecNumericOperation.calculateAffect(leftArg_, _conf, rightArg_, operatorContent.getOper(), false, arg, cast_);
            pairBefore_.setIndexImplicitCompound(processConverter(_conf,res_,implicits_,indexImplicit_));
            return;
        }
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ operatorContent.getOpOffset(),_conf);
        Argument arg_ = calculateCompoundSetting(_nodes, _conf, rightArg_);
        pair_.setEndCalculate(true);
        setSimpleArgument(arg_, _conf, _nodes);
    }

    private Argument calculateCompoundSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument rightArg_) {
        Argument arg_ = null;
        if (settable instanceof ExecStdVariableOperation) {
            arg_ = ((ExecStdVariableOperation)settable).calculateCompoundSetting(_nodes, _conf, operatorContent.getOper(), rightArg_, getResultClass(), getResultClass().getUnwrapObjectNb());
        }
        if (settable instanceof ExecSettableFieldOperation) {
            arg_ = ((ExecSettableFieldOperation)settable).calculateCompoundSetting(_nodes, _conf, operatorContent.getOper(), rightArg_, getResultClass(), getResultClass().getUnwrapObjectNb());
        }
        if (settable instanceof ExecArrOperation) {
            arg_ = ((ExecArrOperation)settable).calculateCompoundSetting(_nodes, _conf, operatorContent.getOper(), rightArg_, getResultClass(), getResultClass().getUnwrapObjectNb());
        }
        if (settable instanceof ExecCustArrOperation) {
            arg_ = ((ExecCustArrOperation)settable).calculateCompoundSetting(_nodes, _conf, operatorContent.getOper(), rightArg_, getResultClass(), getResultClass().getUnwrapObjectNb());
        }
        return Argument.getNullableValue(arg_);
    }

    @Override
    public void endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right) {
        ArgumentsPair pair_ = getArgumentPair(_nodes,this);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ operatorContent.getOpOffset(),_conf);
        ImplicitMethods implicits_ = pair_.getImplicitsCompound();
        int indexImplicit_ = pair_.getIndexImplicitCompound();
        if (implicits_.isValidIndex(indexImplicit_)) {
            pair_.setIndexImplicitCompound(processConverter(_conf, _right, implicits_, indexImplicit_));
            return;
        }
        if (!pair_.isEndCalculate()) {
            pair_.setEndCalculate(true);
            Argument arg_ = endCalculateCh(settable, _nodes, _conf, _right);
            setSimpleArgument(arg_, _conf, _nodes);
            return;
        }
        setSimpleArgument(_right,_conf,_nodes);
    }
    private static Argument endCalculateCh(ExecOperationNode _set,
                                           IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument _right){
        Argument arg_ = null;
        if (_set instanceof ExecStdVariableOperation) {
            arg_ = ((ExecStdVariableOperation)_set).endCalculate(_conf, _nodes, _right);
        }
        if (_set instanceof ExecSettableFieldOperation) {
            arg_ = ((ExecSettableFieldOperation)_set).endCalculate(_conf, _nodes, _right);
        }
        if (_set instanceof ExecCustArrOperation) {
            arg_ = ((ExecCustArrOperation)_set).endCalculate(_conf, _nodes, _right);
        }
        if (_set instanceof ExecArrOperation) {
            arg_ = ((ExecArrOperation)_set).endCalculate(_conf, _nodes,_right);
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
