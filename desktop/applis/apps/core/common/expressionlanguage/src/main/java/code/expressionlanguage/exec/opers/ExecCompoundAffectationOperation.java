package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.CompoundAffectationOperation;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.NullStruct;
import code.util.CustList;
import code.util.IdMap;

public final class ExecCompoundAffectationOperation extends ExecMethodOperation implements AtomicExecCalculableOperation, CallExecSimpleOperation {

    private ExecSettableElResult settable;
    private String oper;
    private ClassMethodId classMethodId;
    private ClassMethodId converter;

    private int opOffset;

    public ExecCompoundAffectationOperation(CompoundAffectationOperation _c) {
        super(_c);
        oper = _c.getOper();
        classMethodId = _c.getClassMethodId();
        converter = _c.getConverter();
        opOffset = _c.getOpOffset();
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
                leftArg_ = new Argument(ClassArgumentMatching.convert(_conf.getLastPage(),getResultClass(),NullStruct.NULL_VALUE,_conf));
                setQuickConvertSimpleArgument(leftArg_, _conf, _nodes);
                return;
            }
        }
        CustList<ExecOperationNode> list_ = getChildrenNodes();
        ExecOperationNode left_ = list_.first();
        ExecOperationNode right_ = list_.last();
        Argument leftArg_ = getArgument(_nodes,left_);
        Argument rightArg_ = getArgument(_nodes,right_);
        if (classMethodId != null) {
            CustList<ExecOperationNode> chidren_ = new CustList<ExecOperationNode>();
            chidren_.add(left_);
            chidren_.add(right_);
            CustList<Argument> arguments_ = new CustList<Argument>();
            arguments_.add(leftArg_);
            arguments_.add(rightArg_);
            CustList<Argument> firstArgs_ = ExecInvokingOperation.listArguments(chidren_, -1, EMPTY_STRING, arguments_);
            ExecInvokingOperation.checkParametersOperators(new DefaultExiting(_conf),_conf, classMethodId, Argument.createVoid(), firstArgs_);
            return;
        }
        setRelativeOffsetPossibleLastPage(getIndexInEl()+opOffset,_conf);
        Argument arg_ = settable.calculateCompoundSetting(_nodes, _conf, oper, rightArg_);
        ArgumentsPair pair_ = getArgumentPair(_nodes,this);
        pair_.setEndCalculate(true);
        setSimpleArgument(arg_, _conf, _nodes);
    }

    @Override
    public void endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right) {
        ArgumentsPair pair_ = getArgumentPair(_nodes,this);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+opOffset,_conf);
        CustList<ClassMethodId> implicits_ = pair_.getImplicitsCompound();
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

    static int processConverter(ContextEl _conf, Argument _right, CustList<ClassMethodId> implicits_, int indexImplicit_) {
        ClassMethodId c = implicits_.get(indexImplicit_);
        DefaultExiting ex_ = new DefaultExiting(_conf);
        CustList<Argument> args_ = new CustList<Argument>(_right);
        AbstractPageEl last_ = _conf.getLastPage();
        String cl_ = c.getClassName();
        MethodId id_ = c.getConstraints();
        if (ExecExplicitOperation.checkCustomOper(ex_, id_, args_, cl_, last_,_conf,_right)) {
            return indexImplicit_;
        }
        return indexImplicit_ +1;
    }

    @Override
    public ExecSettableElResult getSettable() {
        return settable;
    }

    public String getOper() {
        return oper;
    }

    public ClassMethodId getConverter() {
        return converter;
    }
}
