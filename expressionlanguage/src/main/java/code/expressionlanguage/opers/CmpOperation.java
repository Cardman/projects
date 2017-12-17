package code.expressionlanguage.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.exceptions.BadNumberValuesException;
import code.expressionlanguage.exceptions.NotComparableException;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.serialize.exceptions.NullFieldException;
import code.util.CustList;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.exceptions.NullObjectException;

public final class CmpOperation extends PrimitiveBoolOperation {

    private static final int EQ_CMP = 0;

    public CmpOperation(int _index, ContextEl _importingPage,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _importingPage, _indexChild, _m, _op);
    }

    static Argument calculateLower(Argument _a, Argument _b, ContextEl _context) {
        if (StringList.quickEq(_a.getObjectClassName(_context), String.class.getName())) {
            if (StringList.quickEq(_b.getObjectClassName(_context), String.class.getName())) {
                Argument a_ = new Argument();
                String first_ = (String)_a.getObject();
                String second_ = (String)_b.getObject();
                a_.setObject(first_.compareTo(second_) < EQ_CMP);
                return a_;
            }

        }
        Object o_ = _a.getObject();
        Double aOne_ = null;
        Float aTwo_ = null;
        Long aThree_ = null;
        Integer aFour_ = null;
        Short aFive_ = null;
        Byte aSix_ = null;
        Character aSeven_ = null;
        if (o_ instanceof Double) {
            aOne_ = (Double) o_;
        } else if (o_ instanceof Float) {
            aTwo_ = (Float) o_;
        } else if (o_ instanceof Long) {
            aThree_ = (Long) o_;
        } else if (o_ instanceof Integer) {
            aFour_ = (Integer) o_;
        } else if (o_ instanceof Short) {
            aFive_ = (Short) o_;
        } else if (o_ instanceof Byte) {
            aSix_ = (Byte) o_;
        } else if (o_ instanceof Character) {
            aSeven_ = (Character) o_;
        }
        Object p_ = _b.getObject();
        Double bOne_ = null;
        Float bTwo_ = null;
        Long bThree_ = null;
        Integer bFour_ = null;
        Short bFive_ = null;
        Byte bSix_ = null;
        Character bSeven_ = null;
        if (p_ instanceof Double) {
            bOne_ = (Double) p_;
        } else if (p_ instanceof Float) {
            bTwo_ = (Float) p_;
        } else if (p_ instanceof Long) {
            bThree_ = (Long) p_;
        } else if (p_ instanceof Integer) {
            bFour_ = (Integer) p_;
        } else if (p_ instanceof Short) {
            bFive_ = (Short) p_;
        } else if (p_ instanceof Byte) {
            bSix_ = (Byte) p_;
        } else if (p_ instanceof Character) {
            bSeven_ = (Character) p_;
        }
        Object nb_;
        if (aOne_ != null) {
            if (bOne_ != null) {
                nb_ = aOne_< bOne_;
            } else if (bTwo_ != null) {
                nb_ = aOne_< bTwo_;
            } else if (bThree_ != null) {
                nb_ = aOne_< bThree_;
            } else if (bFour_ != null) {
                nb_ = aOne_< bFour_;
            } else if (bFive_ != null) {
                nb_ = aOne_< bFive_;
            } else if (bSix_ != null) {
                nb_ = aOne_< bSix_;
            } else {
                nb_ = aOne_ < bSeven_;
            }
        } else if (aTwo_ != null) {
            if (bOne_ != null) {
                nb_ = aTwo_< bOne_;
            } else if (bTwo_ != null) {
                nb_ = aTwo_< bTwo_;
            } else if (bThree_ != null) {
                nb_ = aTwo_< bThree_;
            } else if (bFour_ != null) {
                nb_ = aTwo_< bFour_;
            } else if (bFive_ != null) {
                nb_ = aTwo_< bFive_;
            } else if (bSix_ != null) {
                nb_ = aTwo_< bSix_;
            } else {
                nb_ = aTwo_ < bSeven_;
            }
        } else if (aThree_ != null) {
            if (bOne_ != null) {
                nb_ = aThree_< bOne_;
            } else if (bTwo_ != null) {
                nb_ = aThree_< bTwo_;
            } else if (bThree_ != null) {
                nb_ = aThree_< bThree_;
            } else if (bFour_ != null) {
                nb_ = aThree_< bFour_;
            } else if (bFive_ != null) {
                nb_ = aThree_< bFive_;
            } else if (bSix_ != null) {
                nb_ = aThree_< bSix_;
            } else {
                nb_ = aThree_ < bSeven_;
            }
        } else if (aFour_ != null) {
            if (bOne_ != null) {
                nb_ = aFour_< bOne_;
            } else if (bTwo_ != null) {
                nb_ = aFour_< bTwo_;
            } else if (bThree_ != null) {
                nb_ = aFour_< bThree_;
            } else if (bFour_ != null) {
                nb_ = aFour_< bFour_;
            } else if (bFive_ != null) {
                nb_ = aFour_< bFive_;
            } else if (bSix_ != null) {
                nb_ = aFour_< bSix_;
            } else {
                nb_ = aFour_ < bSeven_;
            }
        } else if (aFive_ != null) {
            if (bOne_ != null) {
                nb_ = aFive_< bOne_;
            } else if (bTwo_ != null) {
                nb_ = aFive_< bTwo_;
            } else if (bThree_ != null) {
                nb_ = aFive_< bThree_;
            } else if (bFour_ != null) {
                nb_ = aFive_< bFour_;
            } else if (bFive_ != null) {
                nb_ = aFive_< bFive_;
            } else if (bSix_ != null) {
                nb_ = aFive_< bSix_;
            } else {
                nb_ = aFive_ < bSeven_;
            }
        } else if (aSix_ != null) {
            if (bOne_ != null) {
                nb_ = aSix_< bOne_;
            } else if (bTwo_ != null) {
                nb_ = aSix_< bTwo_;
            } else if (bThree_ != null) {
                nb_ = aSix_< bThree_;
            } else if (bFour_ != null) {
                nb_ = aSix_< bFour_;
            } else if (bFive_ != null) {
                nb_ = aSix_< bFive_;
            } else if (bSix_ != null) {
                nb_ = aSix_< bSix_;
            } else {
                nb_ = aSix_ < bSeven_;
            }
        } else {
            if (bOne_ != null) {
                nb_ = aSeven_ < bOne_;
            } else if (bTwo_ != null) {
                nb_ = aSeven_< bTwo_;
            } else if (bThree_ != null) {
                nb_ = aSeven_< bThree_;
            } else if (bFour_ != null) {
                nb_ = aSeven_< bFour_;
            } else if (bFive_ != null) {
                nb_ = aSeven_ < bFive_;
            } else if (bSix_ != null) {
                nb_ = aSeven_ < bSix_;
            } else {
                nb_ = aSeven_ < bSeven_;
            }
        }
        Argument a_ = new Argument();
        a_.setObject(nb_);
        return a_;
    }

    static Argument calculateGreater(Argument _a, Argument _b, ContextEl _context) {
        if (StringList.quickEq(_a.getObjectClassName(_context), String.class.getName())) {
            if (StringList.quickEq(_b.getObjectClassName(_context), String.class.getName())) {
                Argument a_ = new Argument();
                String first_ = (String)_a.getObject();
                String second_ = (String)_b.getObject();
                a_.setObject(first_.compareTo(second_) > EQ_CMP);
                return a_;
            }

        }
        Object o_ = _a.getObject();
        Double aOne_ = null;
        Float aTwo_ = null;
        Long aThree_ = null;
        Integer aFour_ = null;
        Short aFive_ = null;
        Byte aSix_ = null;
        Character aSeven_ = null;
        if (o_ instanceof Double) {
            aOne_ = (Double) o_;
        } else if (o_ instanceof Float) {
            aTwo_ = (Float) o_;
        } else if (o_ instanceof Long) {
            aThree_ = (Long) o_;
        } else if (o_ instanceof Integer) {
            aFour_ = (Integer) o_;
        } else if (o_ instanceof Short) {
            aFive_ = (Short) o_;
        } else if (o_ instanceof Byte) {
            aSix_ = (Byte) o_;
        } else if (o_ instanceof Character) {
            aSeven_ = (Character) o_;
        }
        Object p_ = _b.getObject();
        Double bOne_ = null;
        Float bTwo_ = null;
        Long bThree_ = null;
        Integer bFour_ = null;
        Short bFive_ = null;
        Byte bSix_ = null;
        Character bSeven_ = null;
        if (p_ instanceof Double) {
            bOne_ = (Double) p_;
        } else if (p_ instanceof Float) {
            bTwo_ = (Float) p_;
        } else if (p_ instanceof Long) {
            bThree_ = (Long) p_;
        } else if (p_ instanceof Integer) {
            bFour_ = (Integer) p_;
        } else if (p_ instanceof Short) {
            bFive_ = (Short) p_;
        } else if (p_ instanceof Byte) {
            bSix_ = (Byte) p_;
        } else if (p_ instanceof Character) {
            bSeven_ = (Character) p_;
        }
        Object nb_;
        if (aOne_ != null) {
            if (bOne_ != null) {
                nb_ = aOne_> bOne_;
            } else if (bTwo_ != null) {
                nb_ = aOne_> bTwo_;
            } else if (bThree_ != null) {
                nb_ = aOne_> bThree_;
            } else if (bFour_ != null) {
                nb_ = aOne_> bFour_;
            } else if (bFive_ != null) {
                nb_ = aOne_> bFive_;
            } else if (bSix_ != null) {
                nb_ = aOne_> bSix_;
            } else {
                nb_ = aOne_ > bSeven_;
            }
        } else if (aTwo_ != null) {
            if (bOne_ != null) {
                nb_ = aTwo_> bOne_;
            } else if (bTwo_ != null) {
                nb_ = aTwo_> bTwo_;
            } else if (bThree_ != null) {
                nb_ = aTwo_> bThree_;
            } else if (bFour_ != null) {
                nb_ = aTwo_> bFour_;
            } else if (bFive_ != null) {
                nb_ = aTwo_> bFive_;
            } else if (bSix_ != null) {
                nb_ = aTwo_> bSix_;
            } else {
                nb_ = aTwo_ > bSeven_;
            }
        } else if (aThree_ != null) {
            if (bOne_ != null) {
                nb_ = aThree_> bOne_;
            } else if (bTwo_ != null) {
                nb_ = aThree_> bTwo_;
            } else if (bThree_ != null) {
                nb_ = aThree_> bThree_;
            } else if (bFour_ != null) {
                nb_ = aThree_> bFour_;
            } else if (bFive_ != null) {
                nb_ = aThree_> bFive_;
            } else if (bSix_ != null) {
                nb_ = aThree_> bSix_;
            } else {
                nb_ = aThree_ > bSeven_;
            }
        } else if (aFour_ != null) {
            if (bOne_ != null) {
                nb_ = aFour_> bOne_;
            } else if (bTwo_ != null) {
                nb_ = aFour_> bTwo_;
            } else if (bThree_ != null) {
                nb_ = aFour_> bThree_;
            } else if (bFour_ != null) {
                nb_ = aFour_> bFour_;
            } else if (bFive_ != null) {
                nb_ = aFour_> bFive_;
            } else if (bSix_ != null) {
                nb_ = aFour_> bSix_;
            } else {
                nb_ = aFour_ > bSeven_;
            }
        } else if (aFive_ != null) {
            if (bOne_ != null) {
                nb_ = aFive_> bOne_;
            } else if (bTwo_ != null) {
                nb_ = aFive_> bTwo_;
            } else if (bThree_ != null) {
                nb_ = aFive_> bThree_;
            } else if (bFour_ != null) {
                nb_ = aFive_> bFour_;
            } else if (bFive_ != null) {
                nb_ = aFive_> bFive_;
            } else if (bSix_ != null) {
                nb_ = aFive_> bSix_;
            } else {
                nb_ = aFive_ > bSeven_;
            }
        } else if (aSix_ != null) {
            if (bOne_ != null) {
                nb_ = aSix_> bOne_;
            } else if (bTwo_ != null) {
                nb_ = aSix_> bTwo_;
            } else if (bThree_ != null) {
                nb_ = aSix_> bThree_;
            } else if (bFour_ != null) {
                nb_ = aSix_> bFour_;
            } else if (bFive_ != null) {
                nb_ = aSix_> bFive_;
            } else if (bSix_ != null) {
                nb_ = aSix_> bSix_;
            } else {
                nb_ = aSix_ > bSeven_;
            }
        } else {
            if (bOne_ != null) {
                nb_ = aSeven_ > bOne_;
            } else if (bTwo_ != null) {
                nb_ = aSeven_> bTwo_;
            } else if (bThree_ != null) {
                nb_ = aSeven_> bThree_;
            } else if (bFour_ != null) {
                nb_ = aSeven_> bFour_;
            } else if (bFive_ != null) {
                nb_ = aSeven_ > bFive_;
            } else if (bSix_ != null) {
                nb_ = aSeven_ > bSix_;
            } else {
                nb_ = aSeven_ > bSeven_;
            }
        }
        Argument a_ = new Argument();
        a_.setObject(nb_);
        return a_;
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
        ClassArgumentMatching first_ = chidren_.first().getResultClass();
        ClassArgumentMatching second_ = chidren_.last().getResultClass();
        if (first_.matchClass(String.class) && second_.matchClass(String.class)) {
            setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.PRIM_BOOLEAN));
            return;
        }
        ClassArgumentMatching classFirst_ = PrimitiveTypeUtil.toPrimitive(first_, true);
        ClassArgumentMatching classSecond_ = PrimitiveTypeUtil.toPrimitive(second_, true);
        if (classFirst_.isPrimitive(_conf)) {
            if (classSecond_.isPrimitive(_conf)) {
                setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.PRIM_BOOLEAN));
                return;
            }
        }
        setRelativeOffsetPossibleLastPage(getIndexInEl()+getOperations().getOperators().getKey(0), _conf);
        throw new NotComparableException(classFirst_+RETURN_LINE+classSecond_+RETURN_LINE+_conf.joinPages());
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
        if (first_.isNull()) {
            setRelativeOffsetPossibleLastPage(opOne_.getIndexInEl(), _conf);
            throw new NullFieldException(_conf.joinPages());
        }
        Argument second_ = _nodes.getVal(opTwo_).getArgument();
        if (second_.isNull()) {
            setRelativeOffsetPossibleLastPage(opTwo_.getIndexInEl(), _conf);
            throw new NullFieldException(_conf.joinPages());
        }
        boolean complement_ = false;
        String op_ = getOperations().getOperators().values().first().trim();
        String useOp_ = op_;
        if (StringList.quickEq(op_, LOWER_EQ)) {
            complement_ = true;
            useOp_ = GREATER;
        } else if (StringList.quickEq(op_, GREATER_EQ)) {
            complement_ = true;
            useOp_ = LOWER;
        }
        Argument arg_;
        if (StringList.quickEq(useOp_, LOWER)) {
            arg_ = calculateLower(first_, second_, _conf);
        } else {
            arg_ = calculateGreater(first_, second_, _conf);
        }
        Boolean b_ = (Boolean) arg_.getObject();
        if (complement_) {
            b_ = !b_;
            arg_.setObject(b_);
        }
        setSimpleArgument(arg_, _conf, _nodes);
        return arg_;
    }
    /**@throws NullObjectException*/
    @Override
    public void calculate(CustList<OperationNode> _nodes, ContextEl _conf,
            String _op) {
        calculateCommon(_nodes, _conf, _op);
    }

    void calculateCommon(CustList<OperationNode> _nodes, ContextEl _conf, String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument first_ = chidren_.first().getArgument();
        if (first_.getObject() == null) {
            setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl(), _conf);
            throw new NullFieldException(_conf.joinPages());
        }
        Argument second_ = chidren_.last().getArgument();
        if (second_.getObject() == null) {
            setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
            throw new NullFieldException(_conf.joinPages());
        }
        boolean complement_ = false;
        String op_ = getOperations().getOperators().values().first().trim();
        String useOp_ = op_;
        if (StringList.quickEq(op_, LOWER_EQ)) {
            complement_ = true;
            useOp_ = GREATER;
        } else if (StringList.quickEq(op_, GREATER_EQ)) {
            complement_ = true;
            useOp_ = LOWER;
        }
        Argument arg_;
        if (StringList.quickEq(useOp_, LOWER)) {
            arg_ = calculateLower(first_, second_, _conf);
        } else {
            arg_ = calculateGreater(first_, second_, _conf);
        }
        Boolean b_ = (Boolean) arg_.getObject();
        if (complement_) {
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
