package code.expressionlanguage.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.exceptions.BadNumberValuesException;
import code.expressionlanguage.exceptions.DivideZeroException;
import code.expressionlanguage.exceptions.InvokeRedinedMethException;
import code.expressionlanguage.exceptions.NotNumberException;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.Struct;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.exceptions.NullObjectException;

public abstract class NumericOperation extends MethodOperation {

    private static final String FIRST = "first";
    private static final String SECOND = "second";

    public NumericOperation(int _index, ContextEl _importingPage,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _importingPage, _indexChild, _m, _op);
    }

    static Argument calculateAffect(Argument _left,ContextEl _conf, Argument _right, String _op) {
        Argument o_;
        boolean convert_ = true;
        if (StringList.quickEq(_op, Block.PLUS_EQ)) {
            o_ = NumericOperation.calculateSum(_left, _conf, _right);
        } else if (StringList.quickEq(_op, Block.EQ_PLUS)) {
            o_ = NumericOperation.calculateSum(_right, _conf, _left);
        } else if (StringList.quickEq(_op, Block.MINUS_EQ)) {
            o_ = NumericOperation.calculateDiff(_left, _conf, _right);
        } else if (StringList.quickEq(_op, Block.EQ_MINUS)) {
            o_ = NumericOperation.calculateDiff(_right, _conf, _left);
        } else if (StringList.quickEq(_op, Block.MULT_EQ)) {
            o_ = NumericOperation.calculateMult(_left, _conf, _right);
        } else if (StringList.quickEq(_op, Block.EQ_MULT)) {
            o_ = NumericOperation.calculateMult(_right, _conf, _left);
        } else if (StringList.quickEq(_op, Block.DIV_EQ)) {
            o_ = NumericOperation.calculateDiv(_left, _conf, _right);
        } else if (StringList.quickEq(_op, Block.EQ_DIV)) {
            o_ = NumericOperation.calculateDiv(_right, _conf, _left);
        } else if (StringList.quickEq(_op, Block.MOD_EQ)) {
            o_ = NumericOperation.calculateMod(_left, _conf, _right);
        } else if (StringList.quickEq(_op, Block.EQ_MOD)) {
            o_ = NumericOperation.calculateMod(_right, _conf, _left);
        } else if (StringList.quickEq(_op, Block.INCR)) {
            o_ = AddOperation.addOne(_left, _conf);
        } else if (StringList.quickEq(_op, Block.DECR)) {
            o_ = AddOperation.removeOne(_left, _conf);
        } else {
            o_ = _right;
            convert_ = false;
        }
        if (convert_) {
            ClassArgumentMatching cl_ = new ClassArgumentMatching(_left.getObjectClassName());
            Argument converted_ = new Argument();
            converted_.setStruct(PrimitiveTypeUtil.convertObject(cl_, o_.getObject()));
            o_ = converted_;
        }
        return o_;
    }
    /**@throws InvokeRedinedMethException
    @throws NullObjectException*/
    static Argument calculateSum(Argument _a, ContextEl _cont, Argument _b) {
        if (_a.getObject() instanceof String) {
            StringBuilder str_ = new StringBuilder();
            str_.append(_a.getObject());
            try {
                str_.append(_b.getObject());
            } catch (RuntimeException _0) {
                throw new InvokeRedinedMethException(SECOND+RETURN_LINE+_cont.joinPages(),new Struct(_0));
            }
            Argument a_ = new Argument();
            a_.setObject(str_.toString());
            return a_;
        }
        if (_b.getObject() instanceof String) {
            StringBuilder str_ = new StringBuilder();
            try {
                str_.append(_a.getObject());
            } catch (RuntimeException _0) {
                throw new InvokeRedinedMethException(FIRST+RETURN_LINE+_cont.joinPages(),new Struct(_0));
            }
            str_.append(_b.getObject());
            Argument a_ = new Argument();
            a_.setObject(str_.toString());
            return a_;
        }
        if (PrimitiveTypeUtil.toPrimitive(_a.getArgClass(), true) == char.class) {
            if (PrimitiveTypeUtil.toPrimitive(_b.getArgClass(), true) == char.class) {
                StringBuilder str_ = new StringBuilder();
                str_.append(_a.getObject());
                str_.append(_b.getObject());
                Argument a_ = new Argument();
                a_.setObject(str_.toString());
                return a_;
            }
        }
        if (_a.getObject() == null) {
            throw new NullObjectException(FIRST+RETURN_LINE+_cont.joinPages());
        }
        if (_b.getObject() == null) {
            throw new NullObjectException(SECOND+RETURN_LINE+_cont.joinPages());
        }
        Object o_ = _a.getObject();
        Double aOne_ = null;
        Float aTwo_ = null;
        Long aThree_ = null;
        Integer aFour_ = null;
        Short aFive_ = null;
        Byte aSix_ = null;
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
        }
        Object p_ = _b.getObject();
        Double bOne_ = null;
        Float bTwo_ = null;
        Long bThree_ = null;
        Integer bFour_ = null;
        Short bFive_ = null;
        Byte bSix_ = null;
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
        }
        int oa_ = PrimitiveTypeUtil.getOrderClass(_a.getArgClass());
        int ob_ = PrimitiveTypeUtil.getOrderClass(_b.getArgClass());
        Class<?> arg_;
        if (oa_ > ob_) {
            arg_ = _a.getArgClass();
        } else {
            arg_ = _b.getArgClass();
        }
        arg_ = PrimitiveTypeUtil.toPrimitive(arg_, true);
        Object nb_;
        if (aOne_ != null) {
            if (bOne_ != null) {
                nb_ = aOne_+ bOne_;
            } else if (bTwo_ != null) {
                nb_ = aOne_+ bTwo_;
            } else if (bThree_ != null) {
                nb_ = aOne_+ bThree_;
            } else if (bFour_ != null) {
                nb_ = aOne_+ bFour_;
            } else if (bFive_ != null) {
                nb_ = aOne_+ bFive_;
            } else {
                nb_ = aOne_+ bSix_;
            }
        } else if (aTwo_ != null) {
            if (bOne_ != null) {
                nb_ = aTwo_+ bOne_;
            } else if (bTwo_ != null) {
                nb_ = aTwo_+ bTwo_;
            } else if (bThree_ != null) {
                nb_ = aTwo_+ bThree_;
            } else if (bFour_ != null) {
                nb_ = aTwo_+ bFour_;
            } else if (bFive_ != null) {
                nb_ = aTwo_+ bFive_;
            } else {
                nb_ = aTwo_+ bSix_;
            }
        } else if (aThree_ != null) {
            if (bOne_ != null) {
                nb_ = aThree_+ bOne_;
            } else if (bTwo_ != null) {
                nb_ = aThree_+ bTwo_;
            } else if (bThree_ != null) {
                nb_ = aThree_+ bThree_;
            } else if (bFour_ != null) {
                nb_ = aThree_+ bFour_;
            } else if (bFive_ != null) {
                nb_ = aThree_+ bFive_;
            } else {
                nb_ = aThree_+ bSix_;
            }
        } else if (aFour_ != null) {
            if (bOne_ != null) {
                nb_ = aFour_+ bOne_;
            } else if (bTwo_ != null) {
                nb_ = aFour_+ bTwo_;
            } else if (bThree_ != null) {
                nb_ = aFour_+ bThree_;
            } else if (bFour_ != null) {
                nb_ = aFour_+ bFour_;
            } else if (bFive_ != null) {
                nb_ = aFour_+ bFive_;
            } else {
                nb_ = aFour_+ bSix_;
            }
        } else if (aFive_ != null) {
            if (bOne_ != null) {
                nb_ = aFive_+ bOne_;
            } else if (bTwo_ != null) {
                nb_ = aFive_+ bTwo_;
            } else if (bThree_ != null) {
                nb_ = aFive_+ bThree_;
            } else if (bFour_ != null) {
                nb_ = aFive_+ bFour_;
            } else if (bFive_ != null) {
                nb_ = aFive_+ bFive_;
            } else {
                nb_ = aFive_+ bSix_;
            }
        } else {
            if (bOne_ != null) {
                nb_ = aSix_+ bOne_;
            } else if (bTwo_ != null) {
                nb_ = aSix_+ bTwo_;
            } else if (bThree_ != null) {
                nb_ = aSix_+ bThree_;
            } else if (bFour_ != null) {
                nb_ = aSix_+ bFour_;
            } else if (bFive_ != null) {
                nb_ = aSix_+ bFive_;
            } else {
                nb_ = aSix_+ bSix_;
            }
        }
        Argument a_ = new Argument();
        a_.setObject(nb_);
        return a_;
    }
    /**@throws NullObjectException*/
    static Argument calculateDiff(Argument _a, ContextEl _cont, Argument _b) {
        if (_a.getObject() == null) {
            throw new NullObjectException(FIRST+RETURN_LINE+_cont.joinPages());
        }
        if (_b.getObject() == null) {
            throw new NullObjectException(SECOND+RETURN_LINE+_cont.joinPages());
        }
        Object o_ = _a.getObject();
        Double aOne_ = null;
        Float aTwo_ = null;
        Long aThree_ = null;
        Integer aFour_ = null;
        Short aFive_ = null;
        Byte aSix_ = null;
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
        }
        Object p_ = _b.getObject();
        Double bOne_ = null;
        Float bTwo_ = null;
        Long bThree_ = null;
        Integer bFour_ = null;
        Short bFive_ = null;
        Byte bSix_ = null;
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
        }
        int oa_ = PrimitiveTypeUtil.getOrderClass(_a.getArgClass());
        int ob_ = PrimitiveTypeUtil.getOrderClass(_b.getArgClass());
        Class<?> arg_;
        if (oa_ > ob_) {
            arg_ = _a.getArgClass();
        } else {
            arg_ = _b.getArgClass();
        }
        arg_ = PrimitiveTypeUtil.toPrimitive(arg_, true);
        Object nb_;
        if (aOne_ != null) {
            if (bOne_ != null) {
                nb_ = aOne_- bOne_;
            } else if (bTwo_ != null) {
                nb_ = aOne_- bTwo_;
            } else if (bThree_ != null) {
                nb_ = aOne_- bThree_;
            } else if (bFour_ != null) {
                nb_ = aOne_- bFour_;
            } else if (bFive_ != null) {
                nb_ = aOne_- bFive_;
            } else {
                nb_ = aOne_- bSix_;
            }
        } else if (aTwo_ != null) {
            if (bOne_ != null) {
                nb_ = aTwo_- bOne_;
            } else if (bTwo_ != null) {
                nb_ = aTwo_- bTwo_;
            } else if (bThree_ != null) {
                nb_ = aTwo_- bThree_;
            } else if (bFour_ != null) {
                nb_ = aTwo_- bFour_;
            } else if (bFive_ != null) {
                nb_ = aTwo_- bFive_;
            } else {
                nb_ = aTwo_- bSix_;
            }
        } else if (aThree_ != null) {
            if (bOne_ != null) {
                nb_ = aThree_- bOne_;
            } else if (bTwo_ != null) {
                nb_ = aThree_- bTwo_;
            } else if (bThree_ != null) {
                nb_ = aThree_- bThree_;
            } else if (bFour_ != null) {
                nb_ = aThree_- bFour_;
            } else if (bFive_ != null) {
                nb_ = aThree_- bFive_;
            } else {
                nb_ = aThree_- bSix_;
            }
        } else if (aFour_ != null) {
            if (bOne_ != null) {
                nb_ = aFour_- bOne_;
            } else if (bTwo_ != null) {
                nb_ = aFour_- bTwo_;
            } else if (bThree_ != null) {
                nb_ = aFour_- bThree_;
            } else if (bFour_ != null) {
                nb_ = aFour_- bFour_;
            } else if (bFive_ != null) {
                nb_ = aFour_- bFive_;
            } else {
                nb_ = aFour_- bSix_;
            }
        } else if (aFive_ != null) {
            if (bOne_ != null) {
                nb_ = aFive_- bOne_;
            } else if (bTwo_ != null) {
                nb_ = aFive_- bTwo_;
            } else if (bThree_ != null) {
                nb_ = aFive_- bThree_;
            } else if (bFour_ != null) {
                nb_ = aFive_- bFour_;
            } else if (bFive_ != null) {
                nb_ = aFive_- bFive_;
            } else {
                nb_ = aFive_- bSix_;
            }
        } else {
            if (bOne_ != null) {
                nb_ = aSix_- bOne_;
            } else if (bTwo_ != null) {
                nb_ = aSix_- bTwo_;
            } else if (bThree_ != null) {
                nb_ = aSix_- bThree_;
            } else if (bFour_ != null) {
                nb_ = aSix_- bFour_;
            } else if (bFive_ != null) {
                nb_ = aSix_- bFive_;
            } else {
                nb_ = aSix_- bSix_;
            }
        }
        Argument a_ = new Argument();
        a_.setObject(nb_);
        return a_;
    }
    /**@throws NullObjectException*/
    static Argument calculateMult(Argument _a, ContextEl _cont, Argument _b) {
        if (_a.getObject() == null) {
            throw new NullObjectException(FIRST+RETURN_LINE+_cont.joinPages());
        }
        if (_b.getObject() == null) {
            throw new NullObjectException(SECOND+RETURN_LINE+_cont.joinPages());
        }
        Object o_ = _a.getObject();
        Double aOne_ = null;
        Float aTwo_ = null;
        Long aThree_ = null;
        Integer aFour_ = null;
        Short aFive_ = null;
        Byte aSix_ = null;
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
        }
        Object p_ = _b.getObject();
        Double bOne_ = null;
        Float bTwo_ = null;
        Long bThree_ = null;
        Integer bFour_ = null;
        Short bFive_ = null;
        Byte bSix_ = null;
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
        }
        int oa_ = PrimitiveTypeUtil.getOrderClass(_a.getArgClass());
        int ob_ = PrimitiveTypeUtil.getOrderClass(_b.getArgClass());
        Class<?> arg_;
        if (oa_ > ob_) {
            arg_ = _a.getArgClass();
        } else {
            arg_ = _b.getArgClass();
        }
        arg_ = PrimitiveTypeUtil.toPrimitive(arg_, true);
        Object nb_;
        if (aOne_ != null) {
            if (bOne_ != null) {
                nb_ = aOne_* bOne_;
            } else if (bTwo_ != null) {
                nb_ = aOne_* bTwo_;
            } else if (bThree_ != null) {
                nb_ = aOne_* bThree_;
            } else if (bFour_ != null) {
                nb_ = aOne_* bFour_;
            } else if (bFive_ != null) {
                nb_ = aOne_* bFive_;
            } else {
                nb_ = aOne_* bSix_;
            }
        } else if (aTwo_ != null) {
            if (bOne_ != null) {
                nb_ = aTwo_* bOne_;
            } else if (bTwo_ != null) {
                nb_ = aTwo_* bTwo_;
            } else if (bThree_ != null) {
                nb_ = aTwo_* bThree_;
            } else if (bFour_ != null) {
                nb_ = aTwo_* bFour_;
            } else if (bFive_ != null) {
                nb_ = aTwo_* bFive_;
            } else {
                nb_ = aTwo_* bSix_;
            }
        } else if (aThree_ != null) {
            if (bOne_ != null) {
                nb_ = aThree_* bOne_;
            } else if (bTwo_ != null) {
                nb_ = aThree_* bTwo_;
            } else if (bThree_ != null) {
                nb_ = aThree_* bThree_;
            } else if (bFour_ != null) {
                nb_ = aThree_* bFour_;
            } else if (bFive_ != null) {
                nb_ = aThree_* bFive_;
            } else {
                nb_ = aThree_* bSix_;
            }
        } else if (aFour_ != null) {
            if (bOne_ != null) {
                nb_ = aFour_* bOne_;
            } else if (bTwo_ != null) {
                nb_ = aFour_* bTwo_;
            } else if (bThree_ != null) {
                nb_ = aFour_* bThree_;
            } else if (bFour_ != null) {
                nb_ = aFour_* bFour_;
            } else if (bFive_ != null) {
                nb_ = aFour_* bFive_;
            } else {
                nb_ = aFour_* bSix_;
            }
        } else if (aFive_ != null) {
            if (bOne_ != null) {
                nb_ = aFive_* bOne_;
            } else if (bTwo_ != null) {
                nb_ = aFive_* bTwo_;
            } else if (bThree_ != null) {
                nb_ = aFive_* bThree_;
            } else if (bFour_ != null) {
                nb_ = aFive_* bFour_;
            } else if (bFive_ != null) {
                nb_ = aFive_* bFive_;
            } else {
                nb_ = aFive_* bSix_;
            }
        } else {
            if (bOne_ != null) {
                nb_ = aSix_* bOne_;
            } else if (bTwo_ != null) {
                nb_ = aSix_* bTwo_;
            } else if (bThree_ != null) {
                nb_ = aSix_* bThree_;
            } else if (bFour_ != null) {
                nb_ = aSix_* bFour_;
            } else if (bFive_ != null) {
                nb_ = aSix_* bFive_;
            } else {
                nb_ = aSix_* bSix_;
            }
        }
        Argument a_ = new Argument();
        a_.setObject(nb_);
        return a_;
    }
    /**@throws DivideZeroException
    @throws NullObjectException*/
    static Argument calculateDiv(Argument _a, ContextEl _cont, Argument _b) {
        if (_a.getObject() == null) {
            throw new NullObjectException(FIRST+RETURN_LINE+_cont.joinPages());
        }
        if (_b.getObject() == null) {
            throw new NullObjectException(SECOND+RETURN_LINE+_cont.joinPages());
        }
        Object o_ = _a.getObject();
        Double aOne_ = null;
        Float aTwo_ = null;
        Long aThree_ = null;
        Integer aFour_ = null;
        Short aFive_ = null;
        Byte aSix_ = null;
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
        }
        Object p_ = _b.getObject();
        Double bOne_ = null;
        Float bTwo_ = null;
        Long bThree_ = null;
        Integer bFour_ = null;
        Short bFive_ = null;
        Byte bSix_ = null;
        if (p_ instanceof Double) {
            bOne_ = (Double) p_;
        } else if (p_ instanceof Float) {
            bTwo_ = (Float) p_;
        } else if (p_ instanceof Long) {
            bThree_ = (Long) p_;
            if (bThree_.longValue() == 0) {
                throw new DivideZeroException(_cont.joinPages());
            }
        } else if (p_ instanceof Integer) {
            bFour_ = (Integer) p_;
            if (bFour_.longValue() == 0) {
                throw new DivideZeroException(_cont.joinPages());
            }
        } else if (p_ instanceof Short) {
            bFive_ = (Short) p_;
            if (bFive_.longValue() == 0) {
                throw new DivideZeroException(_cont.joinPages());
            }
        } else if (p_ instanceof Byte) {
            bSix_ = (Byte) p_;
            if (bSix_.longValue() == 0) {
                throw new DivideZeroException(_cont.joinPages());
            }
        }
        int oa_ = PrimitiveTypeUtil.getOrderClass(_a.getArgClass());
        int ob_ = PrimitiveTypeUtil.getOrderClass(_b.getArgClass());
        Class<?> arg_;
        if (oa_ > ob_) {
            arg_ = _a.getArgClass();
        } else {
            arg_ = _b.getArgClass();
        }
        arg_ = PrimitiveTypeUtil.toPrimitive(arg_, true);
        Object nb_;
        if (aOne_ != null) {
            if (bOne_ != null) {
                nb_ = aOne_/ bOne_;
            } else if (bTwo_ != null) {
                nb_ = aOne_/ bTwo_;
            } else if (bThree_ != null) {
                nb_ = aOne_/ bThree_;
            } else if (bFour_ != null) {
                nb_ = aOne_/ bFour_;
            } else if (bFive_ != null) {
                nb_ = aOne_/ bFive_;
            } else {
                nb_ = aOne_/ bSix_;
            }
        } else if (aTwo_ != null) {
            if (bOne_ != null) {
                nb_ = aTwo_/ bOne_;
            } else if (bTwo_ != null) {
                nb_ = aTwo_/ bTwo_;
            } else if (bThree_ != null) {
                nb_ = aTwo_/ bThree_;
            } else if (bFour_ != null) {
                nb_ = aTwo_/ bFour_;
            } else if (bFive_ != null) {
                nb_ = aTwo_/ bFive_;
            } else {
                nb_ = aTwo_/ bSix_;
            }
        } else if (aThree_ != null) {
            if (bOne_ != null) {
                nb_ = aThree_/ bOne_;
            } else if (bTwo_ != null) {
                nb_ = aThree_/ bTwo_;
            } else if (bThree_ != null) {
                nb_ = aThree_/ bThree_;
            } else if (bFour_ != null) {
                nb_ = aThree_/ bFour_;
            } else if (bFive_ != null) {
                nb_ = aThree_/ bFive_;
            } else {
                nb_ = aThree_/ bSix_;
            }
        } else if (aFour_ != null) {
            if (bOne_ != null) {
                nb_ = aFour_/ bOne_;
            } else if (bTwo_ != null) {
                nb_ = aFour_/ bTwo_;
            } else if (bThree_ != null) {
                nb_ = aFour_/ bThree_;
            } else if (bFour_ != null) {
                nb_ = aFour_/ bFour_;
            } else if (bFive_ != null) {
                nb_ = aFour_/ bFive_;
            } else {
                nb_ = aFour_/ bSix_;
            }
        } else if (aFive_ != null) {
            if (bOne_ != null) {
                nb_ = aFive_/ bOne_;
            } else if (bTwo_ != null) {
                nb_ = aFive_/ bTwo_;
            } else if (bThree_ != null) {
                nb_ = aFive_/ bThree_;
            } else if (bFour_ != null) {
                nb_ = aFive_/ bFour_;
            } else if (bFive_ != null) {
                nb_ = aFive_/ bFive_;
            } else {
                nb_ = aFive_/ bSix_;
            }
        } else {
            if (bOne_ != null) {
                nb_ = aSix_/ bOne_;
            } else if (bTwo_ != null) {
                nb_ = aSix_/ bTwo_;
            } else if (bThree_ != null) {
                nb_ = aSix_/ bThree_;
            } else if (bFour_ != null) {
                nb_ = aSix_/ bFour_;
            } else if (bFive_ != null) {
                nb_ = aSix_/ bFive_;
            } else {
                nb_ = aSix_/ bSix_;
            }
        }
        Argument a_ = new Argument();
        a_.setObject(nb_);
        return a_;
    }
    /**@throws DivideZeroException
    @throws NullObjectException*/
    static Argument calculateMod(Argument _a, ContextEl _cont, Argument _b) {
        if (_a.getObject() == null) {
            throw new NullObjectException(FIRST+RETURN_LINE+_cont.joinPages());
        }
        if (_b.getObject() == null) {
            throw new NullObjectException(SECOND+RETURN_LINE+_cont.joinPages());
        }
        Object o_ = _a.getObject();
        Double aOne_ = null;
        Float aTwo_ = null;
        Long aThree_ = null;
        Integer aFour_ = null;
        Short aFive_ = null;
        Byte aSix_ = null;
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
        }
        Object p_ = _b.getObject();
        Double bOne_ = null;
        Float bTwo_ = null;
        Long bThree_ = null;
        Integer bFour_ = null;
        Short bFive_ = null;
        Byte bSix_ = null;
        if (p_ instanceof Double) {
            bOne_ = (Double) p_;
        } else if (p_ instanceof Float) {
            bTwo_ = (Float) p_;
        } else if (p_ instanceof Long) {
            bThree_ = (Long) p_;
            if (bThree_.longValue() == 0) {
                throw new DivideZeroException(_cont.joinPages());
            }
        } else if (p_ instanceof Integer) {
            bFour_ = (Integer) p_;
            if (bFour_.longValue() == 0) {
                throw new DivideZeroException(_cont.joinPages());
            }
        } else if (p_ instanceof Short) {
            bFive_ = (Short) p_;
            if (bFive_.longValue() == 0) {
                throw new DivideZeroException(_cont.joinPages());
            }
        } else if (p_ instanceof Byte) {
            bSix_ = (Byte) p_;
            if (bSix_.longValue() == 0) {
                throw new DivideZeroException(_cont.joinPages());
            }
        }
        int oa_ = PrimitiveTypeUtil.getOrderClass(_a.getArgClass());
        int ob_ = PrimitiveTypeUtil.getOrderClass(_b.getArgClass());
        Class<?> arg_;
        if (oa_ > ob_) {
            arg_ = _a.getArgClass();
        } else {
            arg_ = _b.getArgClass();
        }
        arg_ = PrimitiveTypeUtil.toPrimitive(arg_, true);
        Object nb_;
        if (aOne_ != null) {
            if (bOne_ != null) {
                nb_ = aOne_% bOne_;
            } else if (bTwo_ != null) {
                nb_ = aOne_% bTwo_;
            } else if (bThree_ != null) {
                nb_ = aOne_% bThree_;
            } else if (bFour_ != null) {
                nb_ = aOne_% bFour_;
            } else if (bFive_ != null) {
                nb_ = aOne_% bFive_;
            } else {
                nb_ = aOne_% bSix_;
            }
        } else if (aTwo_ != null) {
            if (bOne_ != null) {
                nb_ = aTwo_% bOne_;
            } else if (bTwo_ != null) {
                nb_ = aTwo_% bTwo_;
            } else if (bThree_ != null) {
                nb_ = aTwo_% bThree_;
            } else if (bFour_ != null) {
                nb_ = aTwo_% bFour_;
            } else if (bFive_ != null) {
                nb_ = aTwo_% bFive_;
            } else {
                nb_ = aTwo_% bSix_;
            }
        } else if (aThree_ != null) {
            if (bOne_ != null) {
                nb_ = aThree_% bOne_;
            } else if (bTwo_ != null) {
                nb_ = aThree_% bTwo_;
            } else if (bThree_ != null) {
                nb_ = aThree_% bThree_;
            } else if (bFour_ != null) {
                nb_ = aThree_% bFour_;
            } else if (bFive_ != null) {
                nb_ = aThree_% bFive_;
            } else {
                nb_ = aThree_% bSix_;
            }
        } else if (aFour_ != null) {
            if (bOne_ != null) {
                nb_ = aFour_% bOne_;
            } else if (bTwo_ != null) {
                nb_ = aFour_% bTwo_;
            } else if (bThree_ != null) {
                nb_ = aFour_% bThree_;
            } else if (bFour_ != null) {
                nb_ = aFour_% bFour_;
            } else if (bFive_ != null) {
                nb_ = aFour_% bFive_;
            } else {
                nb_ = aFour_% bSix_;
            }
        } else if (aFive_ != null) {
            if (bOne_ != null) {
                nb_ = aFive_% bOne_;
            } else if (bTwo_ != null) {
                nb_ = aFive_% bTwo_;
            } else if (bThree_ != null) {
                nb_ = aFive_% bThree_;
            } else if (bFour_ != null) {
                nb_ = aFive_% bFour_;
            } else if (bFive_ != null) {
                nb_ = aFive_% bFive_;
            } else {
                nb_ = aFive_% bSix_;
            }
        } else {
            if (bOne_ != null) {
                nb_ = aSix_% bOne_;
            } else if (bTwo_ != null) {
                nb_ = aSix_% bTwo_;
            } else if (bThree_ != null) {
                nb_ = aSix_% bThree_;
            } else if (bFour_ != null) {
                nb_ = aSix_% bFour_;
            } else if (bFive_ != null) {
                nb_ = aSix_% bFive_;
            } else {
                nb_ = aSix_% bSix_;
            }
        }
        Argument a_ = new Argument();
        a_.setObject(nb_);
        return a_;
    }

    static ClassArgumentMatching getResultClass(ClassArgumentMatching _a, ContextEl _cont, ClassArgumentMatching _b) {
        int oa_ = PrimitiveTypeUtil.getOrderClass(_a);
        String mess_ = _a+RETURN_LINE;
        if (oa_ == 0) {
            throw new NotNumberException(mess_+_cont.joinPages());
        }
        int ob_ = PrimitiveTypeUtil.getOrderClass(_b);
        if (ob_ == 0) {
            throw new NotNumberException(mess_+_b+RETURN_LINE+_cont.joinPages());
        }
        ClassArgumentMatching arg_;
        int max_ = Math.max(oa_, ob_);
        if (oa_ > ob_) {
            arg_ = _a;
        } else {
            arg_ = _b;
        }
        int intOrder_ = PrimitiveTypeUtil.getOrderClass(int.class);
        if (max_ < intOrder_) {
            arg_ = new ClassArgumentMatching(int.class.getName());
        }
        return PrimitiveTypeUtil.toPrimitive(arg_, true);
    }

    @Override
    public final void analyze(CustList<OperationNode> _nodes, ContextEl _conf,
            String _fieldName, String _op) {
        analyzeCommon(_nodes, _conf, _op);
    }

    final void analyzeCommon(CustList<OperationNode> _nodes, ContextEl _conf, String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        if (chidren_.size() < 2) {
            setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
            throw new BadNumberValuesException(_conf.joinPages());
        }
        ClassArgumentMatching a_ = chidren_.first().getResultClass();
        ClassArgumentMatching r_;
        int i_ = CustList.SECOND_INDEX;
        for (EntryCust<Integer, String> e: getOperations().getOperators().entryList()) {
            ClassArgumentMatching c_ = chidren_.get(i_).getResultClass();
            setRelativeOffsetPossibleLastPage(getIndexInEl()+e.getKey(), _conf);
            r_ = analyzeOper(a_, e.getValue(), c_, _conf);
            a_ = r_;
            i_++;
        }
        setResultClass(a_);
    }
    abstract ClassArgumentMatching analyzeOper(ClassArgumentMatching _a, String _op, ClassArgumentMatching _b, ContextEl _cont);
    abstract Argument calculateOper(Argument _a, String _op, Argument _b, ContextEl _cont);

    @Override
    public final Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf, String _op) {
        return calculateCommon(_nodes, _conf, _op);
    }

    final Argument calculateCommon(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode o_ = chidren_.first();
        Argument a_ = _nodes.getVal(o_).getArgument();
        Argument r_;
        int i_ = CustList.SECOND_INDEX;
        for (EntryCust<Integer, String> e: getOperations().getOperators().entryList()) {
            o_ = chidren_.get(i_);
            Argument c_ = _nodes.getVal(o_).getArgument();
            setRelativeOffsetPossibleLastPage(getIndexInEl()+e.getKey(), _conf);
            r_ = calculateOper(a_, e.getValue(), c_, _conf);
            a_ = r_;
            i_++;
        }
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }
    /**@throws InvokeRedinedMethException
    @throws DivideZeroException
    @throws NullObjectException*/
    @Override
    public final void calculate(CustList<OperationNode> _nodes, ContextEl _conf,
            String _op) {
        calculateCommon(_nodes, _conf, _op);
    }

    final void calculateCommon(CustList<OperationNode> _nodes, ContextEl _conf, String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument a_ = chidren_.first().getArgument();
        Argument r_;
        int i_ = CustList.SECOND_INDEX;
        for (EntryCust<Integer, String> e: getOperations().getOperators().entryList()) {
            Argument c_ = chidren_.get(i_).getArgument();
            setRelativeOffsetPossibleLastPage(getIndexInEl()+e.getKey(), _conf);
            r_ = calculateOper(a_, e.getValue(), c_, _conf);
            a_ = r_;
            i_++;
        }
        setSimpleArgument(a_, _conf);
    }

    @Override
    public boolean isOtherConstructorClass() {
        return false;
    }

    @Override
    public ConstructorId getConstId() {
        return null;
    }

    @Override
    public boolean isPossibleInitClass() {
        return false;
    }
    @Override
    public boolean isSuperConstructorCall() {
        return false;
    }

    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }
}
