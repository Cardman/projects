package code.expressionlanguage.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.exceptions.BadNumberValuesException;
import code.expressionlanguage.exceptions.NotNumberException;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.util.CustList;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.exceptions.NullObjectException;

public final class UnaryOperation extends PrimitiveBoolOperation {

    public UnaryOperation(String _el, int _index, ContextEl _importingPage,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_el, _index, _importingPage, _indexChild, _m, _op);
    }

    @Override
    public void analyze(CustList<OperationNode> _nodes, ContextEl _conf,
            boolean _enumContext, String _op) {
        analyzeCommon(_nodes, _conf, _op);
    }

    void analyzeCommon(CustList<OperationNode> _nodes, ContextEl _conf, String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        if (chidren_.size() != 1) {
            setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
            throw new BadNumberValuesException(_conf.joinPages());
        }
        ClassArgumentMatching clMatch_ = chidren_.first().getResultClass();
        ClassArgumentMatching cl_ = PrimitiveTypeUtil.toPrimitive(clMatch_, false);
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        if (cl_ == null) {
            throw new NotNumberException(clMatch_+RETURN_LINE+_conf.joinPages());
        }
        setResultClass(cl_);
    }

    @Override
    public Argument calculateLeft(IdMap<OperationNode,ArgumentsPair> _nodes, ContextEl _conf, String _op) {
        return calculateCommon(_nodes, _conf, _op);
    }
    @Override
    public Argument calculateRight(IdMap<OperationNode,ArgumentsPair> _nodes, ContextEl _conf, String _op) {
        return calculateCommon(_nodes, _conf, _op);
    }

    Argument calculateCommon(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int key_ = getOperations().getOperators().firstKey();
        OperationNode op_ = chidren_.first();
        Argument arg_ = _nodes.getVal(op_).getArgument();
        Argument a_ = new Argument();
        Object o_ = arg_.getObject();
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        if (o_ == null) {
            throw new NullObjectException(_conf.joinPages());
        }
        if (StringList.quickEq(getOperations().getOperators().getVal(key_).trim(), UNARY_MINUS)) {
            if (o_ instanceof Character) {
                a_.setObject(-((Character)o_));
                setSimpleArgument(a_, _conf, _nodes);
                return a_;
            }
            Number b_ = (Number) o_;
            if (b_ instanceof Integer) {
                a_.setObject(-((Integer)b_));
            } else if (b_ instanceof Long) {
                a_.setObject(-((Long)b_));
            } else if (b_ instanceof Byte) {
                a_.setObject(-((Byte)b_));
            } else if (b_ instanceof Short) {
                a_.setObject(-((Short)b_));
            } else if (b_ instanceof Double) {
                a_.setObject(-((Double)b_));
            } else if (b_ instanceof Float) {
                a_.setObject(-((Float)b_));
            }
            setSimpleArgument(a_, _conf, _nodes);
            return a_;
        }
        a_.setStruct(arg_.getStruct());
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }
    /**@throws NullObjectException*/

    @Override
    public void calculateLeft(CustList<OperationNode> _nodes, ContextEl _conf,
            String _op) {
        calculateCommon(_nodes, _conf, _op);
    }

    @Override
    public void calculateRight(CustList<OperationNode> _nodes, ContextEl _conf,
            String _op) {
        calculateCommon(_nodes, _conf, _op);
    }

    void calculateCommon(CustList<OperationNode> _nodes, ContextEl _conf, String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int key_ = getOperations().getOperators().firstKey();
        Argument arg_ = chidren_.first().getArgument();
        Argument a_ = new Argument();
        Object o_ = arg_.getObject();
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        if (o_ == null) {
            throw new NullObjectException(_conf.joinPages());
        }
        if (StringList.quickEq(getOperations().getOperators().getVal(key_).trim(), UNARY_MINUS)) {
            if (o_ instanceof Character) {
                a_.setObject(-((Character)o_));
                setSimpleArgument(a_, _conf);
                return;
            }
            Number b_ = (Number) o_;
            if (b_ instanceof Integer) {
                a_.setObject(-((Integer)b_));
            } else if (b_ instanceof Long) {
                a_.setObject(-((Long)b_));
            } else if (b_ instanceof Byte) {
                a_.setObject(-((Byte)b_));
            } else if (b_ instanceof Short) {
                a_.setObject(-((Short)b_));
            } else if (b_ instanceof Double) {
                a_.setObject(-((Double)b_));
            } else if (b_ instanceof Float) {
                a_.setObject(-((Float)b_));
            }
            setSimpleArgument(a_, _conf);
            return;
        }
        a_.setStruct(arg_.getStruct());
        setSimpleArgument(a_, _conf);
    }

    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }
}
