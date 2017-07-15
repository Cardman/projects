package code.maths.litteral;
import code.maths.Rate;
import code.maths.exceptions.BadDivisionException;
import code.util.CustList;
import code.util.EntryCust;
import code.util.NatTreeMap;
import code.util.StringMap;

public abstract class NumericOperation extends MethodOperation {

    public NumericOperation(String _el, int _index, StringMap<String> _importingPage,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_el, _index, _importingPage, _indexChild, _m, _op);
    }

    static Argument calculateSum(Argument _a, Argument _b) {
        Argument a_ = new Argument();
        a_.setArgClass(MathType.RATE);
        a_.setObject(Rate.plus((Rate) _a.getObject(), (Rate) _b.getObject()));
        return a_;
//        if (_a.getArgClass() == String.class) {
//            StringBuilder str_ = new StringBuilder();
//            str_.append(_a.getObject());
//            str_.append(_b.getObject());
//            Argument a_ = new Argument();
//            a_.setArgClass(String.class);
//            a_.setObject(str_.toString());
//            return a_;
//        }
//        if (_b.getArgClass() == String.class) {
//            StringBuilder str_ = new StringBuilder();
//            str_.append(_a.getObject());
//            str_.append(_b.getObject());
//            Argument a_ = new Argument();
//            a_.setArgClass(String.class);
//            a_.setObject(str_.toString());
//            return a_;
//        }
//        if (toPrimitive(_a.getArgClass(), true) == char.class) {
//            if (toPrimitive(_b.getArgClass(), true) == char.class) {
//                StringBuilder str_ = new StringBuilder();
//                str_.append(_a.getObject());
//                str_.append(_b.getObject());
//                Argument a_ = new Argument();
//                a_.setArgClass(String.class);
//                a_.setObject(str_.toString());
//                return a_;
//            }
//        }
////        boolean allPrimitives_ = true;
////        if (!_a.getArgClass().isPrimitive()) {
////            allPrimitives_ = false;
////        } else if (!_b.getArgClass().isPrimitive()) {
////            allPrimitives_ = false;
////        }
//        Object o_ = _a.getObject();
//        Double aOne_ = null;
//        Float aTwo_ = null;
//        Long aThree_ = null;
//        Integer aFour_ = null;
//        Short aFive_ = null;
//        Byte aSix_ = null;
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
//        }
//        Object p_ = _b.getObject();
//        Double bOne_ = null;
//        Float bTwo_ = null;
//        Long bThree_ = null;
//        Integer bFour_ = null;
//        Short bFive_ = null;
//        Byte bSix_ = null;
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
//        }
//        int oa_ = getOrderClass(_a.getArgClass());
//        int ob_ = getOrderClass(_b.getArgClass());
//        Class<?> arg_;
//        if (oa_ > ob_) {
//            arg_ = _a.getArgClass();
//        } else {
//            arg_ = _b.getArgClass();
//        }
////        if (allPrimitives_) {
////            arg_ = toPrimitive(arg_, true);
////        } else {
////            arg_ = toWrapper(arg_, true);
////        }
//        arg_ = toPrimitive(arg_, true);
//        Object nb_;
//        if (aOne_ != null) {
//            if (bOne_ != null) {
//                nb_ = aOne_+ bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aOne_+ bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aOne_+ bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aOne_+ bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aOne_+ bFive_;
//            } else {
//                nb_ = aOne_+ bSix_;
//            }
//        } else if (aTwo_ != null) {
//            if (bOne_ != null) {
//                nb_ = aTwo_+ bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aTwo_+ bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aTwo_+ bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aTwo_+ bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aTwo_+ bFive_;
//            } else {
//                nb_ = aTwo_+ bSix_;
//            }
//        } else if (aThree_ != null) {
//            if (bOne_ != null) {
//                nb_ = aThree_+ bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aThree_+ bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aThree_+ bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aThree_+ bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aThree_+ bFive_;
//            } else {
//                nb_ = aThree_+ bSix_;
//            }
//        } else if (aFour_ != null) {
//            if (bOne_ != null) {
//                nb_ = aFour_+ bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aFour_+ bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aFour_+ bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aFour_+ bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aFour_+ bFive_;
//            } else {
//                nb_ = aFour_+ bSix_;
//            }
//        } else if (aFive_ != null) {
//            if (bOne_ != null) {
//                nb_ = aFive_+ bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aFive_+ bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aFive_+ bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aFive_+ bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aFive_+ bFive_;
//            } else {
//                nb_ = aFive_+ bSix_;
//            }
//        } else {
//            if (bOne_ != null) {
//                nb_ = aSix_+ bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aSix_+ bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aSix_+ bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aSix_+ bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aSix_+ bFive_;
//            } else {
//                nb_ = aSix_+ bSix_;
//            }
//        }
//        Argument a_ = new Argument();
//        a_.setArgClass(arg_);
//        a_.setObject(nb_);
//        return a_;
    }
    static Argument calculateDiff(Argument _a, Argument _b) {
        Argument a_ = new Argument();
        a_.setArgClass(MathType.RATE);
        a_.setObject(Rate.minus((Rate) _a.getObject(), (Rate) _b.getObject()));
        return a_;
//        Object o_ = _a.getObject();
//        Double aOne_ = null;
//        Float aTwo_ = null;
//        Long aThree_ = null;
//        Integer aFour_ = null;
//        Short aFive_ = null;
//        Byte aSix_ = null;
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
//        }
//        Object p_ = _b.getObject();
//        Double bOne_ = null;
//        Float bTwo_ = null;
//        Long bThree_ = null;
//        Integer bFour_ = null;
//        Short bFive_ = null;
//        Byte bSix_ = null;
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
//        }
//        int oa_ = getOrderClass(_a.getArgClass());
//        int ob_ = getOrderClass(_b.getArgClass());
//        Class<?> arg_;
//        if (oa_ > ob_) {
//            arg_ = _a.getArgClass();
//        } else {
//            arg_ = _b.getArgClass();
//        }
//        arg_ = toPrimitive(arg_, true);
//        Object nb_;
//        if (aOne_ != null) {
//            if (bOne_ != null) {
//                nb_ = aOne_- bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aOne_- bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aOne_- bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aOne_- bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aOne_- bFive_;
//            } else {
//                nb_ = aOne_- bSix_;
//            }
//        } else if (aTwo_ != null) {
//            if (bOne_ != null) {
//                nb_ = aTwo_- bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aTwo_- bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aTwo_- bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aTwo_- bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aTwo_- bFive_;
//            } else {
//                nb_ = aTwo_- bSix_;
//            }
//        } else if (aThree_ != null) {
//            if (bOne_ != null) {
//                nb_ = aThree_- bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aThree_- bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aThree_- bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aThree_- bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aThree_- bFive_;
//            } else {
//                nb_ = aThree_- bSix_;
//            }
//        } else if (aFour_ != null) {
//            if (bOne_ != null) {
//                nb_ = aFour_- bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aFour_- bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aFour_- bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aFour_- bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aFour_- bFive_;
//            } else {
//                nb_ = aFour_- bSix_;
//            }
//        } else if (aFive_ != null) {
//            if (bOne_ != null) {
//                nb_ = aFive_- bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aFive_- bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aFive_- bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aFive_- bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aFive_- bFive_;
//            } else {
//                nb_ = aFive_- bSix_;
//            }
//        } else {
//            if (bOne_ != null) {
//                nb_ = aSix_- bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aSix_- bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aSix_- bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aSix_- bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aSix_- bFive_;
//            } else {
//                nb_ = aSix_- bSix_;
//            }
//        }
//        Argument a_ = new Argument();
//        a_.setArgClass(arg_);
//        a_.setObject(nb_);
//        return a_;
    }
    static Argument calculateMult(Argument _a, Argument _b) {
        Argument a_ = new Argument();
        a_.setArgClass(MathType.RATE);
        a_.setObject(Rate.multiply((Rate) _a.getObject(), (Rate) _b.getObject()));
        return a_;
//        Object o_ = _a.getObject();
//        Double aOne_ = null;
//        Float aTwo_ = null;
//        Long aThree_ = null;
//        Integer aFour_ = null;
//        Short aFive_ = null;
//        Byte aSix_ = null;
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
//        }
//        Object p_ = _b.getObject();
//        Double bOne_ = null;
//        Float bTwo_ = null;
//        Long bThree_ = null;
//        Integer bFour_ = null;
//        Short bFive_ = null;
//        Byte bSix_ = null;
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
//        }
//        int oa_ = getOrderClass(_a.getArgClass());
//        int ob_ = getOrderClass(_b.getArgClass());
//        Class<?> arg_;
//        if (oa_ > ob_) {
//            arg_ = _a.getArgClass();
//        } else {
//            arg_ = _b.getArgClass();
//        }
//        arg_ = toPrimitive(arg_, true);
//        Object nb_;
//        if (aOne_ != null) {
//            if (bOne_ != null) {
//                nb_ = aOne_* bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aOne_* bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aOne_* bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aOne_* bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aOne_* bFive_;
//            } else {
//                nb_ = aOne_* bSix_;
//            }
//        } else if (aTwo_ != null) {
//            if (bOne_ != null) {
//                nb_ = aTwo_* bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aTwo_* bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aTwo_* bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aTwo_* bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aTwo_* bFive_;
//            } else {
//                nb_ = aTwo_* bSix_;
//            }
//        } else if (aThree_ != null) {
//            if (bOne_ != null) {
//                nb_ = aThree_* bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aThree_* bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aThree_* bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aThree_* bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aThree_* bFive_;
//            } else {
//                nb_ = aThree_* bSix_;
//            }
//        } else if (aFour_ != null) {
//            if (bOne_ != null) {
//                nb_ = aFour_* bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aFour_* bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aFour_* bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aFour_* bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aFour_* bFive_;
//            } else {
//                nb_ = aFour_* bSix_;
//            }
//        } else if (aFive_ != null) {
//            if (bOne_ != null) {
//                nb_ = aFive_* bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aFive_* bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aFive_* bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aFive_* bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aFive_* bFive_;
//            } else {
//                nb_ = aFive_* bSix_;
//            }
//        } else {
//            if (bOne_ != null) {
//                nb_ = aSix_* bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aSix_* bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aSix_* bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aSix_* bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aSix_* bFive_;
//            } else {
//                nb_ = aSix_* bSix_;
//            }
//        }
//        Argument a_ = new Argument();
//        a_.setArgClass(arg_);
//        a_.setObject(nb_);
//        return a_;
    }
    static Argument calculateDiv(Argument _a, Argument _b, int _offset) {
        Argument a_ = new Argument();
        a_.setArgClass(MathType.RATE);
        Rate den_ = (Rate)_b.getObject();
        if (den_.isZero()) {
            throw new BadDivisionException(String.valueOf(_offset));
        }
        a_.setObject(Rate.divide((Rate) _a.getObject(), den_));
        return a_;
    }
//    static Argument calculateDiv(Argument _a, Argument _b) {
//        Object o_ = _a.getObject();
//        Double aOne_ = null;
//        Float aTwo_ = null;
//        Long aThree_ = null;
//        Integer aFour_ = null;
//        Short aFive_ = null;
//        Byte aSix_ = null;
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
//        }
//        Object p_ = _b.getObject();
//        Double bOne_ = null;
//        Float bTwo_ = null;
//        Long bThree_ = null;
//        Integer bFour_ = null;
//        Short bFive_ = null;
//        Byte bSix_ = null;
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
//        }
//        int oa_ = getOrderClass(_a.getArgClass());
//        int ob_ = getOrderClass(_b.getArgClass());
//        Class<?> arg_;
//        if (oa_ > ob_) {
//            arg_ = _a.getArgClass();
//        } else {
//            arg_ = _b.getArgClass();
//        }
//        arg_ = toPrimitive(arg_, true);
//        Object nb_;
//        if (aOne_ != null) {
//            if (bOne_ != null) {
//                nb_ = aOne_/ bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aOne_/ bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aOne_/ bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aOne_/ bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aOne_/ bFive_;
//            } else {
//                nb_ = aOne_/ bSix_;
//            }
//        } else if (aTwo_ != null) {
//            if (bOne_ != null) {
//                nb_ = aTwo_/ bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aTwo_/ bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aTwo_/ bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aTwo_/ bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aTwo_/ bFive_;
//            } else {
//                nb_ = aTwo_/ bSix_;
//            }
//        } else if (aThree_ != null) {
//            if (bOne_ != null) {
//                nb_ = aThree_/ bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aThree_/ bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aThree_/ bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aThree_/ bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aThree_/ bFive_;
//            } else {
//                nb_ = aThree_/ bSix_;
//            }
//        } else if (aFour_ != null) {
//            if (bOne_ != null) {
//                nb_ = aFour_/ bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aFour_/ bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aFour_/ bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aFour_/ bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aFour_/ bFive_;
//            } else {
//                nb_ = aFour_/ bSix_;
//            }
//        } else if (aFive_ != null) {
//            if (bOne_ != null) {
//                nb_ = aFive_/ bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aFive_/ bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aFive_/ bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aFive_/ bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aFive_/ bFive_;
//            } else {
//                nb_ = aFive_/ bSix_;
//            }
//        } else {
//            if (bOne_ != null) {
//                nb_ = aSix_/ bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aSix_/ bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aSix_/ bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aSix_/ bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aSix_/ bFive_;
//            } else {
//                nb_ = aSix_/ bSix_;
//            }
//        }
//        Argument a_ = new Argument();
//        a_.setArgClass(arg_);
//        a_.setObject(nb_);
//        return a_;
//    }
//    static Argument calculateMod(Argument _a, Argument _b) {
//        Object o_ = _a.getObject();
//        Double aOne_ = null;
//        Float aTwo_ = null;
//        Long aThree_ = null;
//        Integer aFour_ = null;
//        Short aFive_ = null;
//        Byte aSix_ = null;
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
//        }
//        Object p_ = _b.getObject();
//        Double bOne_ = null;
//        Float bTwo_ = null;
//        Long bThree_ = null;
//        Integer bFour_ = null;
//        Short bFive_ = null;
//        Byte bSix_ = null;
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
//        }
//        int oa_ = getOrderClass(_a.getArgClass());
//        int ob_ = getOrderClass(_b.getArgClass());
//        Class<?> arg_;
//        if (oa_ > ob_) {
//            arg_ = _a.getArgClass();
//        } else {
//            arg_ = _b.getArgClass();
//        }
//        arg_ = toPrimitive(arg_, true);
//        Object nb_;
//        if (aOne_ != null) {
//            if (bOne_ != null) {
//                nb_ = aOne_% bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aOne_% bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aOne_% bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aOne_% bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aOne_% bFive_;
//            } else {
//                nb_ = aOne_% bSix_;
//            }
//        } else if (aTwo_ != null) {
//            if (bOne_ != null) {
//                nb_ = aTwo_% bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aTwo_% bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aTwo_% bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aTwo_% bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aTwo_% bFive_;
//            } else {
//                nb_ = aTwo_% bSix_;
//            }
//        } else if (aThree_ != null) {
//            if (bOne_ != null) {
//                nb_ = aThree_% bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aThree_% bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aThree_% bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aThree_% bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aThree_% bFive_;
//            } else {
//                nb_ = aThree_% bSix_;
//            }
//        } else if (aFour_ != null) {
//            if (bOne_ != null) {
//                nb_ = aFour_% bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aFour_% bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aFour_% bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aFour_% bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aFour_% bFive_;
//            } else {
//                nb_ = aFour_% bSix_;
//            }
//        } else if (aFive_ != null) {
//            if (bOne_ != null) {
//                nb_ = aFive_% bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aFive_% bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aFive_% bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aFive_% bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aFive_% bFive_;
//            } else {
//                nb_ = aFive_% bSix_;
//            }
//        } else {
//            if (bOne_ != null) {
//                nb_ = aSix_% bOne_;
//            } else if (bTwo_ != null) {
//                nb_ = aSix_% bTwo_;
//            } else if (bThree_ != null) {
//                nb_ = aSix_% bThree_;
//            } else if (bFour_ != null) {
//                nb_ = aSix_% bFour_;
//            } else if (bFive_ != null) {
//                nb_ = aSix_% bFive_;
//            } else {
//                nb_ = aSix_% bSix_;
//            }
//        }
//        Argument a_ = new Argument();
//        a_.setArgClass(arg_);
//        a_.setObject(nb_);
//        return a_;
//    }
//    static CustList<Class<?>> getOrdersGreaterEqThan(Class<?> _class) {
//        CustList<Class<?>> primitives_ = new CustList<Class<?>>();
//        primitives_.add(double.class);
//        primitives_.add(float.class);
//        primitives_.add(long.class);
//        primitives_.add(int.class);
//        primitives_.add(char.class);
//        primitives_.add(short.class);
//        primitives_.add(byte.class);
//        CustList<Class<?>> gt_ = new CustList<Class<?>>();
//        for (Class<?> p: primitives_) {
//            if (getOrderClass(p) >= getOrderClass(_class)) {
//                gt_.add(p);
//            }
//        }
//        return gt_;
//    }
//    static int getOrderClass(ClassMatching _class) {
//        return getOrderClass(_class.getClazz());
//    }
//    static int getOrderClass(Class<?> _class) {
//        if (_class == double.class) {
//            return 7;
//        }
//        if (_class == Double.class) {
//            return 7;
//        }
//        if (_class == float.class) {
//            return 6;
//        }
//        if (_class == Float.class) {
//            return 6;
//        }
//        if (_class == long.class) {
//            return 5;
//        }
//        if (_class == Long.class) {
//            return 5;
//        }
//        if (_class == int.class) {
//            return 4;
//        }
//        if (_class == Integer.class) {
//            return 4;
//        }
//        if (_class == char.class) {
//            return 3;
//        }
//        if (_class == Character.class) {
//            return 3;
//        }
//        if (_class == short.class) {
//            return 2;
//        }
//        if (_class == Short.class) {
//            return 2;
//        }
//        if (_class == byte.class) {
//            return 1;
//        }
//        if (_class == Byte.class) {
//            return 1;
//        }
//        return 0;
//    }
//    static ClassMatching toPrimitive(ClassMatching _class, boolean _id) {
//        if (_class.getClazz() == Double.class) {
//            return new ClassMatching(double.class);
//        }
//        if (_class.getClazz() == Float.class) {
//            return new ClassMatching(float.class);
//        }
//        if (_class.getClazz() == Long.class) {
//            return new ClassMatching(long.class);
//        }
//        if (_class.getClazz() == Integer.class) {
//            return new ClassMatching(int.class);
//        }
//        if (_class.getClazz() == Short.class) {
//            return new ClassMatching(short.class);
//        }
//        if (_class.getClazz() == Byte.class) {
//            return new ClassMatching(byte.class);
//        }
//        if (_class.getClazz() == Character.class) {
//            return new ClassMatching(char.class);
//        }
//        if (_id) {
//            return _class;
//        }
//        return null;
//    }
//    static Class<?> toPrimitive(Class<?> _class, boolean _id) {
//        if (_class == Double.class) {
//            return double.class;
//        }
//        if (_class == Float.class) {
//            return float.class;
//        }
//        if (_class == Long.class) {
//            return long.class;
//        }
//        if (_class == Integer.class) {
//            return int.class;
//        }
//        if (_class == Short.class) {
//            return short.class;
//        }
//        if (_class == Byte.class) {
//            return byte.class;
//        }
//        if (_class == Character.class) {
//            return char.class;
//        }
//        if (_id) {
//            return _class;
//        }
//        return null;
//    }
//    static ClassMatching toWrapper(ClassMatching _class, boolean _id) {
//        if (_class.getClazz() == double.class) {
//            return new ClassMatching(Double.class);
//        }
//        if (_class.getClazz() == float.class) {
//            return new ClassMatching(Float.class);
//        }
//        if (_class.getClazz() == long.class) {
//            return new ClassMatching(Long.class);
//        }
//        if (_class.getClazz() == int.class) {
//            return new ClassMatching(Integer.class);
//        }
//        if (_class.getClazz() == char.class) {
//            return new ClassMatching(Character.class);
//        }
//        if (_class.getClazz() == short.class) {
//            return new ClassMatching(Short.class);
//        }
//        if (_class.getClazz() == byte.class) {
//            return new ClassMatching(Byte.class);
//        }
//        if (_id) {
//            return _class;
//        }
//        return null;
//    }
//    static Class<?> toWrapper(Class<?> _class, boolean _id) {
//        if (_class == double.class) {
//            return Double.class;
//        }
//        if (_class == float.class) {
//            return Float.class;
//        }
//        if (_class == long.class) {
//            return Long.class;
//        }
//        if (_class == int.class) {
//            return Integer.class;
//        }
//        if (_class == char.class) {
//            return Character.class;
//        }
//        if (_class == short.class) {
//            return Short.class;
//        }
//        if (_class == byte.class) {
//            return Byte.class;
//        }
//        if (_id) {
//            return _class;
//        }
//        return null;
//    }

    static MathType getResultClass(MathType _a, MathType _b) {
//        int oa_ = getOrderClass(_a);
//        if (oa_ == 0) {
//            throw new AnalyzingException();
//        }
//        int ob_ = getOrderClass(_b);
//        if (ob_ == 0) {
//            throw new AnalyzingException();
//        }
//        ClassMatching arg_;
//        if (oa_ > ob_) {
//            arg_ = _a;
//        } else {
//            arg_ = _b;
//        }
//        return toPrimitive(arg_, true);
        return MathType.RATE;
    }

    @Override
    void analyze(CustList<OperationNode> _nodes, StringMap<String> _conf) {
        CustList<OperationNode> chidren_ = getChildrenAmong(_nodes, true);
        MathType a_ = chidren_.first().getResultClass();
//        System.out.println(getOperations().getOperators());
        MathType r_;
        int i_ = CustList.SECOND_INDEX;
        for (EntryCust<Integer, String> e: getOperations().getOperators().entryList()) {
            MathType c_ = chidren_.get(i_).getResultClass();
            r_ = analyzeOper(a_, e.getValue(), c_, chidren_.get(i_).getIndexInEl());
            a_ = r_;
            i_++;
        }
        setResultClass(a_);
    }

    abstract MathType analyzeOper(MathType _a, String _op, MathType _b, int _offset);
    abstract Argument calculateOper(Argument _a, String _op, Argument _b, int _offset);

    @Override
    void calculate(CustList<OperationNode> _nodes, StringMap<String> _conf) {
        CustList<OperationNode> chidren_ = getChildrenAmong(_nodes, false);
//        for (OperationNode o: _nodes) {
//            if (o.getParent() == this) {
//                chidren_.add(o);
//            }
//        }
//        boolean ready_ = true;
//        for (OperationNode o: chidren_) {
//            if (o.getArgument() == null) {
//                ready_ = false;
//                break;
//            }
//        }
//        if (!ready_) {
//            return;
//        }
//        int key_ = getOperations().getOperators().firstKey();
        Argument a_ = chidren_.first().getArgument();
        Argument r_;
        int i_ = CustList.SECOND_INDEX;
        for (EntryCust<Integer, String> e: getOperations().getOperators().entryList()) {
            Argument c_ = chidren_.get(i_).getArgument();
            r_ = calculateOper(a_, e.getValue(), c_, chidren_.get(i_).getIndexInEl());
            a_ = r_;
            i_++;
        }
        setArgument(a_);
    }

    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }
}
