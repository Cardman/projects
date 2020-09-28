package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultExiting;
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

    private ExecSettableElResult settable;
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
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        if (((ExecOperationNode) settable).getParent() instanceof ExecSafeDotOperation) {
            ExecOperationNode left_ = ((ExecOperationNode) settable).getParent().getFirstChild();
            Argument leftArg_ = getArgument(_nodes,left_);
            if (leftArg_.isNull()) {
                ArgumentsPair pair_ = getArgumentPair(_nodes,this);
                pair_.setIndexImplicitCompound(-1);
                pair_.setEndCalculate(true);
                leftArg_ = new Argument(ExecClassArgumentMatching.convert(_conf.getLastPage(), NullStruct.NULL_VALUE,_conf, getResultClass().getNames()));
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
            Argument arg_ = settable.calculateSetting(_nodes, _conf, leftArg_);
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
            ExecInvokingOperation.checkParametersOperators(new DefaultExiting(_conf),_conf, rootBlock, named, firstArgs_, staticEltContent.getClassName(), staticEltContent.getKind());
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
        Argument arg_ = settable.calculateCompoundSetting(_nodes, _conf, operatorContent.getOper(), rightArg_, getResultClass(), getResultClass().getUnwrapObjectNb());
        pair_.setEndCalculate(true);
        setSimpleArgument(arg_, _conf, _nodes);
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
            Argument arg_ = settable.endCalculate(_conf, _nodes, _right);
            setSimpleArgument(arg_, _conf, _nodes);
            return;
        }
        setSimpleArgument(_right,_conf,_nodes);
    }

    public ExecSettableElResult getSettable() {
        return settable;
    }

    public String getOper() {
        return operatorContent.getOper();
    }

    public ImplicitMethods getConverter() {
        return converter;
    }
}
