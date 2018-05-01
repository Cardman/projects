package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.BadImplicitCast;
import code.expressionlanguage.methods.util.UnexpectedOperationAffect;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.StringList;

public final class SemiAffectationOperation extends PrimitiveBoolOperation {
    private SettableElResult settable;
    private boolean post;

    public SemiAffectationOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op, boolean _post) {
        super(_index, _indexChild, _m, _op);
        post = _post;
    }

    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }

    @Override
    public void analyze(Analyzable _conf, String _fieldName) {
        OperationNode leftEl_ = getChildrenNodes().last();
        setResultClass(leftEl_.getResultClass());
        settable = AffectationOperation.tryGetSettable(this);
        if (settable == null) {
            leftEl_.setRelativeOffsetPossibleLastPage(leftEl_.getIndexInEl(), _conf);
            UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
            un_.setFileName(_conf.getCurrentFileName());
            un_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(un_);
            return;
        }
        boolean var_ = true;
        if (settable instanceof ArrOperation) {
            var_ = false;
        }
        settable.setVariable(var_);
        LgNames stds_ = _conf.getStandards();
        String stringType_ = stds_.getAliasString();
        String res_ = settable.getResultClass().getName();
        if (settable.resultCanBeSet() && StringList.quickEq(res_, stringType_)) {
            settable.setCatenizeStrings();
        }
        ClassArgumentMatching clMatchLeft_ = leftEl_.getResultClass();
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        if (!PrimitiveTypeUtil.isPureNumberClass(clMatchLeft_, _conf)) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(clMatchLeft_.getName());
            mapping_.setParam(_conf.getStandards().getAliasLong());
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(_conf.getCurrentFileName());
            cast_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(cast_);
            return;
        }
        String oper_ = getOperations().getOperators().firstValue();
        if (!StringList.quickEq(oper_, Block.INCR)) {
            if (!StringList.quickEq(oper_, Block.DECR)) {
                Mapping mapping_ = new Mapping();
                mapping_.setArg(clMatchLeft_.getName());
                mapping_.setParam(_conf.getStandards().getAliasLong());
                BadImplicitCast cast_ = new BadImplicitCast();
                cast_.setMapping(mapping_);
                cast_.setFileName(_conf.getCurrentFileName());
                cast_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().getErrorsDet().add(cast_);
                return;
            }
        }
    }

    @Override
    public void calculate(CustList<OperationNode> _nodes, ContextEl _conf,
            String _op) {
        OperationNode right_ = getChildrenNodes().last();
        _conf.getLastPage().setRightArgument(right_.getArgument());
        String oper_ = getOperations().getOperators().firstValue();
        settable.calculateSetting(_nodes, _conf, oper_, post);
        OperationNode op_ = (OperationNode)settable;
        setSimpleArgument(op_.getArgument(), _conf);
        _conf.getLastPage().setRightArgument(null);
    }

    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf, String _op) {
        OperationNode right_ = getChildrenNodes().last();
        _conf.getLastPage().setRightArgument(_nodes.getVal(right_).getArgument());
        String oper_ = getOperations().getOperators().firstValue();
        Argument arg_ = settable.calculateSetting(_nodes, _conf, oper_, post);
        setSimpleArgument(arg_, _conf, _nodes);
        _conf.getLastPage().setRightArgument(null);
        return arg_;
    }

}
