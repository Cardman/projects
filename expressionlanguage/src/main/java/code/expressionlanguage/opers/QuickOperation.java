package code.expressionlanguage.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.exceptions.BadNumberValuesException;
import code.expressionlanguage.exceptions.NotBooleanException;
import code.expressionlanguage.exceptions.SettingMemberException;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.util.CustList;
import code.util.IdMap;
import code.util.exceptions.NullObjectException;


public abstract class QuickOperation extends PrimitiveBoolOperation {

    public QuickOperation(String _el, int _index, ContextEl _importingPage,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_el, _index, _importingPage, _indexChild, _m, _op);
    }

//    @Override
//    public final void analyze(CustList<OperationNode> _nodes, ContextEl _conf, Calculation _setting) {
//        if (_setting.getStep()  == StepCalculation.LEFT && getParent() == null) {
//            setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
//            throw new SettingMemberException(_conf.joinPages());
//        }
//        CustList<OperationNode> chidren_ = getChildrenAmong(_nodes, true);
//        if (chidren_.size() < 2) {
//            setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
//            throw new BadNumberValuesException(_conf.joinPages());
//        }
//        for (OperationNode o: chidren_) {
//            ClassArgumentMatching clMatch_;
//            clMatch_ = o.getResultClass();
//            setRelativeOffsetPossibleLastPage(o.getIndexInEl(), _conf);
//            if (!clMatch_.matchClass(PrimitiveTypeUtil.PRIM_BOOLEAN)) {
//                if (!clMatch_.matchClass(Boolean.class)) {
//                    ClassArgumentMatching cl_ = o.getResultClass();
//                    throw new NotBooleanException(cl_+RETURN_LINE+_conf.joinPages());
//                }
//            }
//        }
//        setResultClass(chidren_.last().getResultClass());
//    }

    @Override
    public final void analyzeLeft(CustList<OperationNode> _nodes, ContextEl _conf,
            boolean _enumContext, String _op) {
        if (getParent() == null) {
            setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
            throw new SettingMemberException(_conf.joinPages());
        }
        analyzeCommon(_nodes, _conf, _op);
    }

    @Override
    public final void analyzeRight(CustList<OperationNode> _nodes, ContextEl _conf,
            boolean _enumContext, String _op) {
        analyzeCommon(_nodes, _conf, _op);
    }

    @Override
    public final void analyzeSetting(CustList<OperationNode> _nodes, ContextEl _conf,
            boolean _enumContext, String _op) {
        analyzeCommon(_nodes, _conf, _op);
    }

    final void analyzeCommon(CustList<OperationNode> _nodes, ContextEl _conf, String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        if (chidren_.size() < 2) {
            setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
            throw new BadNumberValuesException(_conf.joinPages());
        }
        for (OperationNode o: chidren_) {
            ClassArgumentMatching clMatch_;
            clMatch_ = o.getResultClass();
            setRelativeOffsetPossibleLastPage(o.getIndexInEl(), _conf);
            if (!clMatch_.matchClass(PrimitiveTypeUtil.PRIM_BOOLEAN)) {
                if (!clMatch_.matchClass(Boolean.class)) {
                    ClassArgumentMatching cl_ = o.getResultClass();
                    throw new NotBooleanException(cl_+RETURN_LINE+_conf.joinPages());
                }
            }
        }
        setResultClass(chidren_.last().getResultClass());
    }

//    @Override
//    public final Argument calculate(
//            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf,
//            Calculation _setting) {
//        CustList<OperationNode> chidren_ = getChildrenAmong();
//        OperationNode last_ = chidren_.last();
//        setRelativeOffsetPossibleLastPage(last_.getIndexInEl(), _conf);
//        Argument a_ = _nodes.getVal(last_).getArgument();
//        if (a_.getObject() == null) {
//            throw new NullObjectException(_conf.joinPages());
//        }
//        setSimpleArgument(a_, _conf, _nodes);
//        return a_;
//    }

    @Override
    public final Argument calculateLeft(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf, String _op) {
        return calculateCommon(_nodes, _conf, _op);
    }
    @Override
    public final Argument calculateRight(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf, String _op) {
        return calculateCommon(_nodes, _conf, _op);
    }
    @Override
    public final Argument calculateSetting(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op) {
        return calculateCommon(_nodes, _conf, _op);
    }
    final Argument calculateCommon(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode last_ = chidren_.last();
        setRelativeOffsetPossibleLastPage(last_.getIndexInEl(), _conf);
        Argument a_ = _nodes.getVal(last_).getArgument();
        if (a_.getObject() == null) {
            throw new NullObjectException(_conf.joinPages());
        }
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }
    /**@throws NullObjectException*/
//    @Override
//    public final void calculate(CustList<OperationNode> _nodes, ContextEl _conf, Calculation _setting) {
//        CustList<OperationNode> chidren_ = getChildrenAmong(_nodes, false);
//        setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
//        Argument a_ = chidren_.last().getArgument();
//        if (a_.getObject() == null) {
//            throw new NullObjectException(_conf.joinPages());
//        }
//        setSimpleArgument(a_, _conf);
//    }
    @Override
    public final void calculateLeft(CustList<OperationNode> _nodes, ContextEl _conf,
            String _op) {
        calculateCommon(_nodes, _conf, _op);
    }
    @Override
    public final void calculateRight(CustList<OperationNode> _nodes, ContextEl _conf,
            String _op) {
        calculateCommon(_nodes, _conf, _op);
    }
    @Override
    public final void calculateSetting(CustList<OperationNode> _nodes,
            ContextEl _conf, String _op) {
        calculateCommon(_nodes, _conf, _op);
    }
    final void calculateCommon(CustList<OperationNode> _nodes, ContextEl _conf, String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
        Argument a_ = chidren_.last().getArgument();
        if (a_.getObject() == null) {
            throw new NullObjectException(_conf.joinPages());
        }
        setSimpleArgument(a_, _conf);
    }
}
