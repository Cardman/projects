package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
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
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ExecCompoundAffectationOperation extends ExecMethodOperation implements AtomicExecCalculableOperation, CallExecSimpleOperation {

    private ExecOperationNode settable;
    private ExecMethodOperation settableParent;
    private ExecOperatorContent operatorContent;
    private ExecStaticEltContent staticEltContent;
    private final ExecTypeFunction pair;
    private ImplicitMethods converter;


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
                          ContextEl _conf) {
        if (settableParent instanceof ExecSafeDotOperation) {
            ExecOperationNode left_ = settableParent.getFirstChild();
            Argument leftArg_ = getArgument(_nodes,left_);
            if (leftArg_.isNull()) {
                ArgumentsPair pair_ = ExecTemplates.getArgumentPair(_nodes,this);
                pair_.setIndexImplicitCompound(-1);
                pair_.setEndCalculate(true);
                leftArg_ = new Argument(ExecClassArgumentMatching.convertFormatted(NullStruct.NULL_VALUE,_conf, getResultClass().getNames()));
                setQuickConvertSimpleArgument(leftArg_, _conf, _nodes);
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
                setSimpleArgument(leftArg_, _conf, _nodes);
                return;
            }
            setRelOffsetPossibleLastPage(operatorContent.getOpOffset(),_conf);
            Argument arg_ = ExecAffectationOperation.calculateChSetting(settable,_nodes, _conf, leftArg_);
            pair_.setEndCalculate(true);
            setSimpleArgument(arg_, _conf, _nodes);
            return;
        }
        if (pair.getFct() != null) {
            CustList<ExecOperationNode> chidren_ = new CustList<ExecOperationNode>();
            chidren_.add(getFirstChild());
            chidren_.add(ExecTemplates.getLastNode(this));
            CustList<Argument> arguments_ = new CustList<Argument>();
            arguments_.add(leftArg_);
            arguments_.add(rightArg_);
            CustList<Argument> firstArgs_ = ExecInvokingOperation.listArguments(chidren_, -1, EMPTY_STRING, arguments_);
            ExecInvokingOperation.checkParametersOperators(_conf.getExiting(),_conf, pair, firstArgs_, staticEltContent.getClassName(), staticEltContent.getKind());
            return;
        }
        if (StringUtil.quickEq(operatorContent.getOper(), "???=")) {
            if (!leftArg_.isNull()) {
                pair_.setIndexImplicitCompound(-1);
                pair_.setEndCalculate(true);
                setSimpleArgument(leftArg_, _conf, _nodes);
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
            Argument res_ = ExecNumericOperation.calculateAffect(leftArg_, _conf, rightArg_, operatorContent.getOper(), false, arg_, cast_);
            pairBefore_.setIndexImplicitCompound(processConverter(_conf,res_,implicits_,indexImplicit_));
            return;
        }
        setRelOffsetPossibleLastPage(operatorContent.getOpOffset(),_conf);
        Argument arg_ = calculateCompoundSetting(_nodes, _conf, rightArg_);
        pair_.setEndCalculate(true);
        setSimpleArgument(arg_, _conf, _nodes);
    }

    private Argument calculateCompoundSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument _rightArg) {
        Argument arg_ = null;
        if (settable instanceof ExecStdVariableOperation) {
            arg_ = ((ExecStdVariableOperation)settable).calculateCompoundSetting(_nodes, _conf, operatorContent.getOper(), _rightArg, getResultClass(), getResultClass().getUnwrapObjectNb());
        }
        if (settable instanceof ExecSettableFieldOperation) {
            arg_ = ((ExecSettableFieldOperation)settable).calculateCompoundSetting(_nodes, _conf, operatorContent.getOper(), _rightArg, getResultClass(), getResultClass().getUnwrapObjectNb());
        }
        if (settable instanceof ExecArrOperation) {
            arg_ = ((ExecArrOperation)settable).calculateCompoundSetting(_nodes, _conf, operatorContent.getOper(), _rightArg, getResultClass(), getResultClass().getUnwrapObjectNb());
        }
        if (settable instanceof ExecCustArrOperation) {
            arg_ = ((ExecCustArrOperation)settable).calculateCompoundSetting(_nodes, _conf, operatorContent.getOper(), _rightArg, getResultClass(), getResultClass().getUnwrapObjectNb());
        }
        return Argument.getNullableValue(arg_);
    }

    @Override
    public void endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right) {
        ArgumentsPair pair_ = ExecTemplates.getArgumentPair(_nodes,this);
        setRelOffsetPossibleLastPage(operatorContent.getOpOffset(),_conf);
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
