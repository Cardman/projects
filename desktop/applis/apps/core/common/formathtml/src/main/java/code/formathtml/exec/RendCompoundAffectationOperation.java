package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.CompoundAffectationOperation;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.structs.NullStruct;
import code.formathtml.Configuration;
import code.formathtml.util.AdvancedExiting;
import code.util.CustList;
import code.util.IdMap;

public final class RendCompoundAffectationOperation extends RendMethodOperation implements RendCalculableOperation,RendCallable {

    private RendSettableElResult settable;
    private String oper;
    private ClassMethodId classMethodId;
    private ClassMethodId converter;

    public RendCompoundAffectationOperation(CompoundAffectationOperation _c) {
        super(_c);
        oper = _c.getOper();
        classMethodId = _c.getClassMethodId();
        converter = _c.getConverter();
    }

    public void setup() {
        settable = RendAffectationOperation.tryGetSettable(this);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        if (((RendDynOperationNode) settable).getParent() instanceof RendSafeDotOperation) {
            RendDynOperationNode left_ = ((RendDynOperationNode) settable).getParent().getFirstChild();
            Argument leftArg_ = getArgument(_nodes,left_);
            if (leftArg_.isNull()) {
                leftArg_ = new Argument(ClassArgumentMatching.convert(_conf.getPageEl(),getResultClass(),NullStruct.NULL_VALUE,_conf.getContext()));
                setQuickConvertSimpleArgument(leftArg_, _conf, _nodes);
                return;
            }
        }
        CustList<RendDynOperationNode> list_ = getChildrenNodes();
        RendDynOperationNode left_ = list_.first();
        RendDynOperationNode right_ = list_.last();
        Argument leftArg_ = getArgument(_nodes,left_);
        Argument rightArg_ = getArgument(_nodes,right_);
        ArgumentsPair argumentPair_ = getArgumentPair(_nodes, left_);
        if (argumentPair_.isArgumentTest()){
            Argument arg_ = settable.calculateSetting(_nodes, _conf, leftArg_);
            setSimpleArgument(arg_, _conf,_nodes);
            return;
        }
        if (classMethodId != null) {
            CustList<RendDynOperationNode> chidren_ = new CustList<RendDynOperationNode>();
            chidren_.add(left_);
            chidren_.add(right_);
            CustList<Argument> arguments_ = new CustList<Argument>();
            arguments_.add(leftArg_);
            arguments_.add(rightArg_);
            CustList<Argument> firstArgs_ = RendInvokingOperation.listArguments(chidren_, -1, EMPTY_STRING, arguments_);
            Argument res_;
            res_ =  processCall(this,this, Argument.createVoid(),firstArgs_,_conf, null);
            if (converter != null) {
                Argument conv_ = tryConvert(converter, res_, _conf);
                if (conv_ == null) {
                    return;
                }
                res_ = conv_;
            }
            Argument arg_ = settable.endCalculate(_nodes, _conf, res_);
            setSimpleArgument(arg_, _conf,_nodes);
            return;
        }
        if (converter != null) {
            String tres_ = converter.getConstraints().getParametersType(1);
            Argument res_;
            res_ = RendNumericOperation.calculateAffect(leftArg_, _conf, rightArg_, oper, false, new ClassArgumentMatching(tres_));
            Argument conv_ = tryConvert(converter, res_, _conf);
            if (conv_ == null) {
                return;
            }
            res_ = conv_;
            Argument arg_ = settable.endCalculate(_nodes, _conf, res_);
            setSimpleArgument(arg_, _conf,_nodes);
            return;
        }
        Argument arg_ = settable.calculateCompoundSetting(_nodes, _conf, oper, rightArg_);
        setSimpleArgument(arg_, _conf,_nodes);
    }

    public String getOper() {
        return oper;
    }

    @Override
    public Argument getArgument(Argument _previous, CustList<Argument> _arguments, Configuration _conf, Argument _right) {
        ExecInvokingOperation.checkParametersOperators(new AdvancedExiting(_conf),_conf.getContext(),classMethodId,_previous,_arguments);
        return Argument.createVoid();
    }

    public RendSettableElResult getSettable() {
        return settable;
    }
}
