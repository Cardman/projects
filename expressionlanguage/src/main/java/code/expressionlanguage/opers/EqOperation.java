package code.expressionlanguage.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.exceptions.BadNumberValuesException;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.StringList;

public final class EqOperation extends PrimitiveBoolOperation {

    public EqOperation(int _index, ContextEl _importingPage,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _importingPage, _indexChild, _m, _op);
    }

    static Argument calculateEq(Argument _a, Argument _b, ContextEl _context) {
        Argument a_ = new Argument();
        Object first_ = _a.getObject();
        Object second_ = _b.getObject();
        if (first_ == second_) {
            a_.setObject(true);
            return a_;
        }
        if (first_ == null && second_ != null) {
            a_.setObject(false);
            return a_;
        }
        if (first_ != null && second_ == null) {
            a_.setObject(false);
            return a_;
        }
        // first_ != null && second_ != null
        if (first_ instanceof String && second_ instanceof String) {
            a_.setObject(StringList.quickEq((String)first_, (String) second_));
            return a_;
        }
        if (canUsePrimitiveFloattingPointEq(first_)) {
            if (canUsePrimitiveFloattingPointEq(second_)) {
                double vone_ = ((Number)first_).doubleValue();
                double vtwo_ = ((Number)second_).doubleValue();
                a_.setObject(vone_ == vtwo_);
                return a_;
            }
        }
        if (canUsePrimitiveIntegerEq(first_)) {
            if (canUsePrimitiveIntegerEq(second_)) {
                long vone_;
                if (first_ instanceof Number) {
                    vone_ = ((Number)first_).longValue();
                } else {
                    vone_ = (Character)first_;
                }
                long vtwo_;
                if (second_ instanceof Number) {
                    vtwo_ = ((Number)second_).longValue();
                } else {
                    vtwo_ = (Character)second_;
                }
                a_.setObject(vone_ == vtwo_);
                return a_;
            }
        }
        if (first_ instanceof Boolean) {
            if (second_ instanceof Boolean) {
                a_.setObject(((Boolean)first_).booleanValue() == ((Boolean)second_).booleanValue());
                return a_;
            }
        }
        if (canUsePrimitiveIntegerEq(first_)) {
            if (canUsePrimitiveFloattingPointEq(second_)) {
                double vone_;
                if (first_ instanceof Number) {
                    vone_ = ((Number)first_).doubleValue();
                } else {
                    vone_ = (Character)first_;
                }
                double vtwo_ = ((Number)second_).doubleValue();
                a_.setObject(vone_ == vtwo_);
                return a_;
            }
        }
        if (canUsePrimitiveFloattingPointEq(first_)) {
            if (canUsePrimitiveIntegerEq(second_)) {
                double vone_ = ((Number)first_).doubleValue();
                double vtwo_;
                if (second_ instanceof Number) {
                    vtwo_ = ((Number)second_).doubleValue();
                } else {
                    vtwo_ = (Character)second_;
                }
                a_.setObject(vone_ == vtwo_);
                return a_;
            }
        }
        a_.setObject(false);
        return a_;
    }

    /*static boolean canUsePrimitiveEq(Object _o) {
        if (_o instanceof Double) {
            return true;
        }
        if (_o instanceof Float) {
            return true;
        }
        if (_o instanceof Long) {
            return true;
        }
        if (_o instanceof Integer) {
            return true;
        }
        if (_o instanceof Short) {
            return true;
        }
        if (_o instanceof Byte) {
            return true;
        }
        if (_o instanceof Character) {
            return true;
        }
        if (_o instanceof Boolean) {
            return true;
        }
        return false;
    }*/

    static boolean canUsePrimitiveFloattingPointEq(Object _o) {
        if (_o instanceof Double) {
            return true;
        }
        if (_o instanceof Float) {
            return true;
        }
        return false;
    }

    static boolean canUsePrimitiveIntegerEq(Object _o) {
        if (_o instanceof Long) {
            return true;
        }
        if (_o instanceof Integer) {
            return true;
        }
        if (_o instanceof Short) {
            return true;
        }
        if (_o instanceof Byte) {
            return true;
        }
        if (_o instanceof Character) {
            return true;
        }
        return false;
    }

    @Override
    public void analyze(CustList<OperationNode> _nodes, ContextEl _conf,
            String _fieldName, String _op) {
        analyzeCommon(_nodes, _conf, _op);
    }

    void analyzeCommon(CustList<OperationNode> _nodes, ContextEl _conf, String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        if (chidren_.size() != 2) {
            setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
            throw new BadNumberValuesException(_conf.joinPages());
        }
        LgNames stds_ = _conf.getStandards();
        if (_conf.getClasses() != null) {
            
        }
        setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.PRIM_BOOLEAN));
    }

    @Override
    public Argument calculate(IdMap<OperationNode,ArgumentsPair> _nodes, ContextEl _conf, String _op) {
        return calculateCommon(_nodes, _conf, _op);
    }

    Argument calculateCommon(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op) {

        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode opOne_ = chidren_.first();
        OperationNode opTwo_ = chidren_.last();
        Argument first_ = _nodes.getVal(opOne_).getArgument();
        Argument second_ = _nodes.getVal(opTwo_).getArgument();
        boolean complement_ = false;
        String op_ = getOperations().getOperators().values().first().trim();
        if (StringList.quickEq(op_, DIFF)) {
            complement_ = true;
        }
        Argument arg_ = calculateEq(first_, second_, _conf);
        if (complement_) {
            Boolean b_ = (Boolean) arg_.getObject();
            b_ = !b_;
            arg_.setObject(b_);
        }
        setSimpleArgument(arg_, _conf, _nodes);
        return arg_;
    }

    @Override
    public void calculate(CustList<OperationNode> _nodes, ContextEl _conf,
            String _op) {
        calculateCommon(_nodes, _conf, _op);
    }

    void calculateCommon(CustList<OperationNode> _nodes, ContextEl _conf, String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument first_ = chidren_.first().getArgument();
        Argument second_ = chidren_.last().getArgument();
        boolean complement_ = false;
        String op_ = getOperations().getOperators().values().first().trim();
        if (StringList.quickEq(op_, DIFF)) {
            complement_ = true;
        }
        Argument arg_ = calculateEq(first_, second_, _conf);
        if (complement_) {
            Boolean b_ = (Boolean) arg_.getObject();
            b_ = !b_;
            arg_.setObject(b_);
        }
        setSimpleArgument(arg_, _conf);
    }
    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }

}
