package code.maths.litteral;
import code.maths.Rate;
import code.maths.litteral.exceptions.NotComparableException;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;

public final class CmpOperation extends PrimitiveBoolOperation {

    public CmpOperation(String _el, int _index, StringMap<String> _importingPage,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_el, _index, _importingPage, _indexChild, _m, _op);
    }

    static Argument calculateLower(Argument _a, Argument _b) {
        Argument a_ = new Argument();
        a_.setArgClass(MathType.BOOLEAN);
        a_.setObject(Rate.strLower((Rate)_a.getObject(),(Rate) _b.getObject()));
        return a_;
//        Object o_ = _a.getObject();
//        Double aOne_ = null;
//        Float aTwo_ = null;
//        Long aThree_ = null;
//        Integer aFour_ = null;
//        Short aFive_ = null;
//        Byte aSix_ = null;
//        Character aSeven_ = null;
//        if (o_ instanceof Double) {
//            aOne_ = (Double) o_;
//        } else if (o_ instanceof Float) {
//            aTwo_ = (Float) o_;
//        } else if (o_ instanceof Long) {
//            aThree_ = (Long) o_;
//        } else if (o_ instanceof Integer) {
//            aFour_ = (Integer) o_;
//        } else if (o_ instanceof Short) {
//            aFive_ = (Short) o_;
//        } else if (o_ instanceof Byte) {
//            aSix_ = (Byte) o_;
//        } else if (o_ instanceof Character) {
//            aSeven_ = (Character) o_;
//        }
//        Object p_ = _b.getObject();
//        Double bOne_ = null;
//        Float bTwo_ = null;
//        Long bThree_ = null;
//        Integer bFour_ = null;
//        Short bFive_ = null;
//        Byte bSix_ = null;
//        Character bSeven_ = null;
//        if (p_ instanceof Double) {
//            bOne_ = (Double) p_;
//        } else if (p_ instanceof Float) {
//            bTwo_ = (Float) p_;
//        } else if (p_ instanceof Long) {
//            bThree_ = (Long) p_;
//        } else if (p_ instanceof Integer) {
//            bFour_ = (Integer) p_;
//        } else if (p_ instanceof Short) {
//            bFive_ = (Short) p_;
//        } else if (p_ instanceof Byte) {
//            bSix_ = (Byte) p_;
//        } else if (p_ instanceof Character) {
//            bSeven_ = (Character) p_;
//        }
//        Object nb_;
//        if (aOne_ != null) {
//            if (bOne_ != null) {
//                nb_ = aOne_< bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aOne_< bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aOne_< bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aOne_< bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aOne_< bFive_;
//            } else if (bSix_ != null) {
//                nb_ = aOne_< bSix_;
//            } else {
//                nb_ = aOne_ < bSeven_;
//            }
//        } else if (aTwo_ != null) {
//            if (bOne_ != null) {
//                nb_ = aTwo_< bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aTwo_< bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aTwo_< bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aTwo_< bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aTwo_< bFive_;
//            } else if (bSix_ != null) {
//                nb_ = aTwo_< bSix_;
//            } else {
//                nb_ = aTwo_ < bSeven_;
//            }
//        } else if (aThree_ != null) {
//            if (bOne_ != null) {
//                nb_ = aThree_< bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aThree_< bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aThree_< bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aThree_< bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aThree_< bFive_;
//            } else if (bSix_ != null) {
//                nb_ = aThree_< bSix_;
//            } else {
//                nb_ = aThree_ < bSeven_;
//            }
//        } else if (aFour_ != null) {
//            if (bOne_ != null) {
//                nb_ = aFour_< bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aFour_< bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aFour_< bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aFour_< bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aFour_< bFive_;
//            } else if (bSix_ != null) {
//                nb_ = aFour_< bSix_;
//            } else {
//                nb_ = aFour_ < bSeven_;
//            }
//        } else if (aFive_ != null) {
//            if (bOne_ != null) {
//                nb_ = aFive_< bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aFive_< bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aFive_< bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aFive_< bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aFive_< bFive_;
//            } else if (bSix_ != null) {
//                nb_ = aFive_< bSix_;
//            } else {
//                nb_ = aFive_ < bSeven_;
//            }
//        } else if (aSix_ != null) {
//            if (bOne_ != null) {
//                nb_ = aSix_< bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aSix_< bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aSix_< bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aSix_< bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aSix_< bFive_;
//            } else if (bSix_ != null) {
//                nb_ = aSix_< bSix_;
//            } else {
//                nb_ = aSix_ < bSeven_;
//            }
//        } else {
//            if (bOne_ != null) {
//                nb_ = aSeven_ < bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aSeven_< bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aSeven_< bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aSeven_< bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aSeven_ < bFive_;
//            } else if (bSix_ != null) {
//                nb_ = aSeven_ < bSix_;
//            } else {
//                nb_ = aSeven_ < bSeven_;
//            }
//        }
//        Argument a_ = new Argument();
//        a_.setArgClass(boolean.class);
//        a_.setObject(nb_);
//        return a_;
    }

    static Argument calculateGreater(Argument _a, Argument _b) {
        Argument a_ = new Argument();
        a_.setArgClass(MathType.BOOLEAN);
        a_.setObject(Rate.strGreater((Rate)_a.getObject(),(Rate) _b.getObject()));
        return a_;
//        if (_a.getArgClass() == String.class) {
//            if (_b.getArgClass() == String.class) {
//                Argument a_ = new Argument();
//                a_.setArgClass(boolean.class);
//                String first_ = (String)_a.getObject();
//                String second_ = (String)_b.getObject();
//                a_.setObject(first_.compareTo(second_) > EQ_CMP);
//                return a_;
//            }
//
//        }
////        if (NumericOperation.toPrimitive(_a.getArgClass(), true) == char.class) {
////            if (NumericOperation.toPrimitive(_b.getArgClass(), true) == char.class) {
////                Argument a_ = new Argument();
////                a_.setArgClass(boolean.class);
////                Character first_ = (Character)_a.getObject();
////                Character second_ = (Character)_b.getObject();
////                a_.setObject(first_ > second_);
////                return a_;
////            }
////        }
//        Object o_ = _a.getObject();
//        Double aOne_ = null;
//        Float aTwo_ = null;
//        Long aThree_ = null;
//        Integer aFour_ = null;
//        Short aFive_ = null;
//        Byte aSix_ = null;
//        Character aSeven_ = null;
//        if (o_ instanceof Double) {
//            aOne_ = (Double) o_;
//        } else if (o_ instanceof Float) {
//            aTwo_ = (Float) o_;
//        } else if (o_ instanceof Long) {
//            aThree_ = (Long) o_;
//        } else if (o_ instanceof Integer) {
//            aFour_ = (Integer) o_;
//        } else if (o_ instanceof Short) {
//            aFive_ = (Short) o_;
//        } else if (o_ instanceof Byte) {
//            aSix_ = (Byte) o_;
//        } else if (o_ instanceof Character) {
//            aSeven_ = (Character) o_;
//        }
//        Object p_ = _b.getObject();
//        Double bOne_ = null;
//        Float bTwo_ = null;
//        Long bThree_ = null;
//        Integer bFour_ = null;
//        Short bFive_ = null;
//        Byte bSix_ = null;
//        Character bSeven_ = null;
//        if (p_ instanceof Double) {
//            bOne_ = (Double) p_;
//        } else if (p_ instanceof Float) {
//            bTwo_ = (Float) p_;
//        } else if (p_ instanceof Long) {
//            bThree_ = (Long) p_;
//        } else if (p_ instanceof Integer) {
//            bFour_ = (Integer) p_;
//        } else if (p_ instanceof Short) {
//            bFive_ = (Short) p_;
//        } else if (p_ instanceof Byte) {
//            bSix_ = (Byte) p_;
//        } else if (p_ instanceof Character) {
//            bSeven_ = (Character) p_;
//        }
//        Object nb_;
//        if (aOne_ != null) {
//            if (bOne_ != null) {
//                nb_ = aOne_> bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aOne_> bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aOne_> bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aOne_> bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aOne_> bFive_;
//            } else if (bSix_ != null) {
//                nb_ = aOne_> bSix_;
//            } else {
//                nb_ = aOne_ > bSeven_;
//            }
//        } else if (aTwo_ != null) {
//            if (bOne_ != null) {
//                nb_ = aTwo_> bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aTwo_> bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aTwo_> bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aTwo_> bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aTwo_> bFive_;
//            } else if (bSix_ != null) {
//                nb_ = aTwo_> bSix_;
//            } else {
//                nb_ = aTwo_ > bSeven_;
//            }
//        } else if (aThree_ != null) {
//            if (bOne_ != null) {
//                nb_ = aThree_> bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aThree_> bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aThree_> bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aThree_> bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aThree_> bFive_;
//            } else if (bSix_ != null) {
//                nb_ = aThree_> bSix_;
//            } else {
//                nb_ = aThree_ > bSeven_;
//            }
//        } else if (aFour_ != null) {
//            if (bOne_ != null) {
//                nb_ = aFour_> bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aFour_> bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aFour_> bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aFour_> bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aFour_> bFive_;
//            } else if (bSix_ != null) {
//                nb_ = aFour_> bSix_;
//            } else {
//                nb_ = aFour_ > bSeven_;
//            }
//        } else if (aFive_ != null) {
//            if (bOne_ != null) {
//                nb_ = aFive_> bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aFive_> bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aFive_> bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aFive_> bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aFive_> bFive_;
//            } else if (bSix_ != null) {
//                nb_ = aFive_> bSix_;
//            } else {
//                nb_ = aFive_ > bSeven_;
//            }
//        } else if (aSix_ != null) {
//            if (bOne_ != null) {
//                nb_ = aSix_> bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aSix_> bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aSix_> bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aSix_> bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aSix_> bFive_;
//            } else if (bSix_ != null) {
//                nb_ = aSix_> bSix_;
//            } else {
//                nb_ = aSix_ > bSeven_;
//            }
//        } else {
//            if (bOne_ != null) {
//                nb_ = aSeven_ > bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aSeven_> bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aSeven_> bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aSeven_> bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aSeven_ > bFive_;
//            } else if (bSix_ != null) {
//                nb_ = aSeven_ > bSix_;
//            } else {
//                nb_ = aSeven_ > bSeven_;
//            }
//        }
//        Argument a_ = new Argument();
//        a_.setArgClass(boolean.class);
//        a_.setObject(nb_);
//        return a_;
    }
    @Override
    void analyze(CustList<OperationNode> _nodes, StringMap<String> _conf) {
        CustList<OperationNode> chidren_ = getChildrenAmong(_nodes, true);
        MathType first_ = chidren_.first().getResultClass();
        MathType second_ = chidren_.last().getResultClass();
        if (first_ == MathType.RATE && second_ == MathType.RATE) {
            setResultClass(MathType.BOOLEAN);
            return;
        }
//        ClassMatching classFirst_ = NumericOperation.toPrimitive(first_, true);
//        ClassMatching classSecond_ = NumericOperation.toPrimitive(second_, true);
//        if (classFirst_.isPrimitive()) {
//            if (classSecond_.isPrimitive()) {
//                setResultClass(new ClassMatching(boolean.class));
//                return;
//            }
//        }
        throw new NotComparableException(String.valueOf(getIndexInEl()));
    }
    @Override
    void calculate(CustList<OperationNode> _nodes, StringMap<String> _conf) {
        CustList<OperationNode> chidren_ = getChildrenAmong(_nodes, false);
        Argument first_ = chidren_.first().getArgument();
        Argument second_ = chidren_.last().getArgument();
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
            arg_ = calculateLower(first_, second_);
        } else {
            arg_ = calculateGreater(first_, second_);
        }
        Boolean b_ = (Boolean) arg_.getObject();
        if (complement_) {
            b_ = !b_;
            arg_.setObject(b_);
        }
        setArgument(arg_);
        setNextSiblingsArg(arg_);
//        MethodOperation par_ = getParent();
//        if (b_ && par_ instanceof OrOperation) {
//            List<OperationNode> opers_ = new List<OperationNode>();
//            for (SortedNode s: TreeRetrieving.getDirectChildren(par_)) {
//                opers_.add((OperationNode) s);
//            }
//            int len_ = opers_.size();
//            for (int i = getIndexChild() + 1; i < len_; i++) {
//                opers_.get(i).setArgument(arg_);
//            }
//        } else if (!b_ && par_ instanceof AndOperation) {
//            List<OperationNode> opers_ = new List<OperationNode>();
//            for (SortedNode s: TreeRetrieving.getDirectChildren(par_)) {
//                opers_.add((OperationNode) s);
//            }
//            int len_ = opers_.size();
//            for (int i = getIndexChild() + 1; i < len_; i++) {
//                opers_.get(i).setArgument(arg_);
//            }
//        }
    }

    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }

}
