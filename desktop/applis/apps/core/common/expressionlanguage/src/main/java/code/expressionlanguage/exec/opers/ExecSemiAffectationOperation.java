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
import code.expressionlanguage.fwd.opers.ExecStaticPostEltContent;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class ExecSemiAffectationOperation extends ExecAbstractUnaryOperation implements CallExecSimpleOperation {
    private final ExecTypeFunction pair;
    private ExecOperationNode settable;
    private ExecMethodOperation settableParent;
    private final ExecOperatorContent operatorContent;
    private final ExecStaticPostEltContent staticPostEltContent;
    private final ImplicitMethods converterFrom;
    private final ImplicitMethods converterTo;

    public ExecSemiAffectationOperation(ExecOperationContent _opCont, ExecStaticPostEltContent _staticPostEltContent, ExecOperatorContent _operatorContent, ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock, ImplicitMethods _converterFrom, ImplicitMethods _converterTo) {
        super(_opCont);
        staticPostEltContent = _staticPostEltContent;
        operatorContent = _operatorContent;
        pair = new ExecTypeFunction(_rootBlock,_named);
        converterFrom = _converterFrom;
        converterTo = _converterTo;
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
                ArgumentsPair pairBefore_ = ExecTemplates.getArgumentPair(_nodes,this);
                pairBefore_.setEndCalculate(true);
                pairBefore_.setIndexImplicitSemiFrom(-1);
                pairBefore_.setIndexImplicitSemiTo(-1);
                if (settable instanceof ExecCustArrOperation) {
                    pairBefore_.setCalledIndexer(true);
                }
                leftArg_ = new Argument(ExecClassArgumentMatching.convertFormatted(NullStruct.NULL_VALUE,_conf, getResultClass().getNames(), _stack));
                setQuickConvertSimpleArgument(leftArg_, _conf, _nodes, _stack);
                return;
            }
        }
        if (pair.getFct() != null) {
            ExecInvokingOperation.checkParametersOperators(_conf.getExiting(),_conf, pair, _nodes,this, staticPostEltContent.getClassName(), staticPostEltContent.getKind(), _stack);
            return;
        }
        ArgumentsPair pairBefore_ = ExecTemplates.getArgumentPair(_nodes,this);
        ImplicitMethods implicits_ = pairBefore_.getImplicitsSemiFrom();
        int indexImplicit_ = pairBefore_.getIndexImplicitSemiFrom();
        if (implicits_.isValidIndex(indexImplicit_)) {
            ExecOperationNode left_ = getFirstChild();
            Argument leftArg_ = getArgument(_nodes,left_);
            Struct store_ = leftArg_.getStruct();
            Argument l_ = new Argument(store_);
            pairBefore_.setIndexImplicitSemiFrom(ExecOperationNode.processConverter(_conf,l_, implicits_,indexImplicit_, _stack));
            return;
        }
        setRelOffsetPossibleLastPage(operatorContent.getOpOffset(), _stack);
        Argument arg_ = calculateSemiChSetting(_nodes, _conf, _stack);
        ArgumentsPair pair_ = ExecTemplates.getArgumentPair(_nodes,this);
        pair_.setEndCalculate(true);
        setSimpleArgument(arg_, _conf, _nodes, _stack);
    }

    private Argument calculateSemiChSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stackCall) {
        Argument arg_ = null;
        if (settable instanceof ExecStdVariableOperation) {
            arg_ = ((ExecStdVariableOperation)settable).calculateSemiSetting(_nodes, _conf, operatorContent.getOper(), staticPostEltContent.isPost(), getResultClass().getUnwrapObjectNb(), _stackCall);
        }
        if (settable instanceof ExecStdRefVariableOperation) {
            arg_ = ((ExecStdRefVariableOperation)settable).calculateSemiSetting(_nodes, _conf, operatorContent.getOper(), staticPostEltContent.isPost(), getResultClass().getUnwrapObjectNb(), _stackCall);
        }
        if (settable instanceof ExecRefParamOperation) {
            arg_ = ((ExecRefParamOperation)settable).calculateSemiSetting(_nodes, _conf, operatorContent.getOper(), staticPostEltContent.isPost(), getResultClass().getUnwrapObjectNb(), _stackCall);
        }
        if (settable instanceof ExecSettableFieldOperation) {
            arg_ = ((ExecSettableFieldOperation)settable).calculateSemiSetting(_nodes, _conf, operatorContent.getOper(), staticPostEltContent.isPost(), getResultClass().getUnwrapObjectNb(), _stackCall);
        }
        if (settable instanceof ExecCustArrOperation) {
            arg_ = ((ExecCustArrOperation)settable).calculateSemiSetting(_nodes, _conf, operatorContent.getOper(), staticPostEltContent.isPost(), getResultClass().getUnwrapObjectNb(), _stackCall);
        }
        if (settable instanceof ExecArrOperation) {
            arg_ = ((ExecArrOperation)settable).calculateSemiSetting(_nodes, _conf, operatorContent.getOper(), staticPostEltContent.isPost(), getResultClass().getUnwrapObjectNb(), _stackCall);
        }
        if (settable instanceof ExecSettableCallFctOperation) {
            arg_ = ((ExecSettableCallFctOperation)settable).calculateSemiSetting(_nodes, _conf, operatorContent.getOper(), staticPostEltContent.isPost(), getResultClass().getUnwrapObjectNb(), _stackCall);
        }
        if (settable instanceof ExecSwitchOperation) {
            arg_ = ((ExecSwitchOperation)settable).calculateSemiSetting(_nodes, _conf, operatorContent.getOper(), staticPostEltContent.isPost(), getResultClass().getUnwrapObjectNb(), _stackCall);
        }
        if (settable instanceof ExecRefTernaryOperation) {
            arg_ = ((ExecRefTernaryOperation)settable).calculateSemiSetting(_nodes, _conf, operatorContent.getOper(), staticPostEltContent.isPost(), getResultClass().getUnwrapObjectNb(), _stackCall);
        }
        return Argument.getNullableValue(arg_);
    }

    @Override
    public void endCalculate(ContextEl _conf,
                             IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right, StackCall _stack) {
        ArgumentsPair pair_ = ExecTemplates.getArgumentPair(_nodes,this);
        setRelOffsetPossibleLastPage(operatorContent.getOpOffset(), _stack);
        ImplicitMethods implicits_ = pair_.getImplicitsSemiFrom();
        int indexImplicit_ = pair_.getIndexImplicitSemiFrom();
        if (implicits_.isValidIndex(indexImplicit_)) {
            ExecOperationNode left_ = getFirstChild();
            Argument leftArg_ = getArgument(_nodes,left_);
            Struct store_ = leftArg_.getStruct();
            Argument l_ = new Argument(store_);
            pair_.setIndexImplicitSemiFrom(ExecOperationNode.processConverter(_conf,l_, implicits_,indexImplicit_, _stack));
            return;
        }
        implicits_ = pair_.getImplicitsSemiTo();
        indexImplicit_ = pair_.getIndexImplicitSemiTo();
        if (implicits_.isValidIndex(indexImplicit_)) {
            String tres_ = implicits_.get(indexImplicit_).getFct().getImportedParametersTypes().first();
            byte cast_ = ClassArgumentMatching.getPrimitiveCast(tres_, _conf.getStandards().getPrimTypes());
            Argument res_;
            res_ = ExecNumericOperation.calculateIncrDecr(_right, operatorContent.getOper(), cast_);
            pair_.setIndexImplicitSemiTo(ExecOperationNode.processConverter(_conf,res_, implicits_,indexImplicit_, _stack));
            return;
        }
        Argument stored_ = getNullArgument(_nodes, settable);
        if (!pair_.isEndCalculate()) {
            pair_.setEndCalculate(true);
            Argument arg_ = endCalculate(_conf, _nodes, _right, stored_, settable, staticPostEltContent, _stack);
            setSimpleArgument(arg_, _conf, _nodes, _stack);
            return;
        }
        if (settable instanceof ExecCustArrOperation) {
            Argument out_;
            if (!pair_.isCalledIndexer()) {
                pair_.setCalledIndexer(true);
                out_ = ExecSemiAffectationOperation.getPrePost(staticPostEltContent.isPost(), stored_, _right);
            } else {
                out_ = _right;
            }
            setSimpleArgument(out_, _conf, _nodes, _stack);
            return;
        }
        setSimpleArgument(_right, _conf, _nodes, _stack);
    }

    private static Argument getNullArgument(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ExecOperationNode _settable) {
        return getArgument(_nodes, _settable);
    }

    private static Argument endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right, Argument _stored, ExecOperationNode _settable, ExecStaticPostEltContent _staticPostEltContent, StackCall _stackCall) {
        Argument arg_ = null;
        if (_settable instanceof ExecStdVariableOperation) {
            arg_ = ((ExecStdVariableOperation)_settable).endCalculate(_conf, _nodes, _staticPostEltContent.isPost(), _stored, _right, _stackCall);
        }
        if (_settable instanceof ExecStdRefVariableOperation) {
            arg_ = ((ExecStdRefVariableOperation)_settable).endCalculate(_conf, _nodes, _staticPostEltContent.isPost(), _stored, _right, _stackCall);
        }
        if (_settable instanceof ExecRefParamOperation) {
            arg_ = ((ExecRefParamOperation)_settable).endCalculate(_conf, _nodes, _staticPostEltContent.isPost(), _stored, _right, _stackCall);
        }
        if (_settable instanceof ExecSettableFieldOperation) {
            arg_ = ((ExecSettableFieldOperation)_settable).endCalculate(_conf, _nodes, _staticPostEltContent.isPost(), _stored, _right, _stackCall);
        }
        if (_settable instanceof ExecCustArrOperation) {
            arg_ = ((ExecCustArrOperation)_settable).endCalculate(_conf, _nodes, _staticPostEltContent.isPost(), _stored, _right, _stackCall);
        }
        if (_settable instanceof ExecArrOperation) {
            arg_ = ((ExecArrOperation)_settable).endCalculate(_conf, _nodes, _staticPostEltContent.isPost(), _stored, _right, _stackCall);
        }
        if (_settable instanceof ExecSettableCallFctOperation) {
            arg_ = ((ExecSettableCallFctOperation)_settable).endCalculate(_conf, _nodes, _staticPostEltContent.isPost(), _stored, _right, _stackCall);
        }
        if (_settable instanceof ExecSwitchOperation) {
            arg_ = ((ExecSwitchOperation)_settable).endCalculate(_conf, _nodes, _staticPostEltContent.isPost(), _stored, _right, _stackCall);
        }
        if (_settable instanceof ExecRefTernaryOperation) {
            arg_ = ((ExecRefTernaryOperation)_settable).endCalculate(_conf, _nodes, _staticPostEltContent.isPost(), _stored, _right, _stackCall);
        }
        return Argument.getNullableValue(arg_);
    }

    static Argument getPrePost(boolean _post, Argument _stored,Argument _right) {
        Argument a_ = _right;
        if (_post) {
            a_ = _stored;
        }
        return a_;
    }

    public ImplicitMethods getConverterFrom() {
        return converterFrom;
    }

    public ImplicitMethods getConverterTo() {
        return converterTo;
    }
}
