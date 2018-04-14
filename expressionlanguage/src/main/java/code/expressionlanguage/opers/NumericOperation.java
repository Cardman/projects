package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.exceptions.InvokeException;
import code.expressionlanguage.exceptions.InvokeRedinedMethException;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.UnexpectedTypeOperationError;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.ResultOperand;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.exceptions.NullObjectException;

public abstract class NumericOperation extends MethodOperation {

    private static final String FIRST = "first";
    private static final String SECOND = "second";

    public NumericOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    static Argument calculateAffect(Argument _left,ContextEl _conf, Argument _right, String _op, boolean _catString) {
        Argument o_;
        boolean convert_ = true;
        if (StringList.quickEq(_op, Block.PLUS_EQ)) {
            o_ = NumericOperation.calculateSum(_left, _conf, _right, false, _catString);
        } else if (StringList.quickEq(_op, Block.EQ_PLUS)) {
            o_ = NumericOperation.calculateSum(_right, _conf, _left, false, _catString);
        } else if (StringList.quickEq(_op, Block.MINUS_EQ)) {
            o_ = NumericOperation.calculateDiff(_left, _conf, _right);
        } else if (StringList.quickEq(_op, Block.MULT_EQ)) {
            o_ = NumericOperation.calculateMult(_left, _conf, _right);
        } else if (StringList.quickEq(_op, Block.DIV_EQ)) {
            o_ = NumericOperation.calculateDiv(_left, _conf, _right);
        } else if (StringList.quickEq(_op, Block.MOD_EQ)) {
            o_ = NumericOperation.calculateMod(_left, _conf, _right);
        } else if (StringList.quickEq(_op, Block.INCR)) {
            o_ = AddOperation.addOne(_left, _conf);
        } else if (StringList.quickEq(_op, Block.DECR)) {
            o_ = AddOperation.removeOne(_left, _conf);
        } else {
            o_ = _right;
            convert_ = false;
        }
        if (convert_) {
            ClassArgumentMatching cl_ = new ClassArgumentMatching(_left.getObjectClassName(_conf));
            Argument converted_ = new Argument();
            converted_.setStruct(PrimitiveTypeUtil.convertObject(cl_, o_.getStruct(), _conf));
            o_ = converted_;
        }
        return o_;
    }
    static boolean convert(String _op) {
        if (StringList.quickEq(_op, Block.PLUS_EQ)) {
            return true;
        }
        if (StringList.quickEq(_op, Block.EQ_PLUS)) {
            return true;
        }
        if (StringList.quickEq(_op, Block.MINUS_EQ)) {
            return true;
        }
        if (StringList.quickEq(_op, Block.MULT_EQ)) {
            return true;
        }
        if (StringList.quickEq(_op, Block.DIV_EQ)) {
            return true;
        }
        if (StringList.quickEq(_op, Block.MOD_EQ)) {
            return true;
        }
        if (StringList.quickEq(_op, Block.INCR)) {
            return true;
        }
        if (StringList.quickEq(_op, Block.DECR)) {
            return true;
        }
        return false;
    }
    /**@throws InvokeRedinedMethException
    @throws NullObjectException*/
    static Argument calculateSum(Argument _a, ContextEl _cont, Argument _b, boolean _catChars, boolean _catString) {
        LgNames stds_ = _cont.getStandards();
        String null_;
        null_ = stds_.getAliasNullPe();
        if (_catString) {
            StringBuilder str_ = new StringBuilder();
            str_.append(_a.getObject());
            str_.append(_b.getObject());
            Argument a_ = new Argument();
            a_.setObject(str_.toString());
            return a_;
        }
        if (_catChars) {
            StringBuilder str_ = new StringBuilder();
            str_.append(_a.getObject());
            str_.append(_b.getObject());
            Argument a_ = new Argument();
            a_.setObject(str_.toString());
            return a_;
        }
        if (_a.isNull()) {
            throw new InvokeException(new StdStruct(new CustomError(StringList.concat(FIRST,RETURN_LINE,_cont.joinPages())),null_));
        }
        if (_b.isNull()) {
            throw new InvokeException(new StdStruct(new CustomError(StringList.concat(SECOND,RETURN_LINE,_cont.joinPages())),null_));
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
        Number nb_;
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
        LgNames stds_ = _cont.getStandards();
        String null_;
        null_ = stds_.getAliasNullPe();
        if (_a.isNull()) {
            throw new InvokeException(new StdStruct(new CustomError(StringList.concat(FIRST,RETURN_LINE,_cont.joinPages())),null_));
        }
        if (_b.isNull()) {
            throw new InvokeException(new StdStruct(new CustomError(StringList.concat(SECOND,RETURN_LINE,_cont.joinPages())),null_));
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
        Number nb_;
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
        LgNames stds_ = _cont.getStandards();
        String null_;
        null_ = stds_.getAliasNullPe();
        if (_a.isNull()) {
            throw new InvokeException(new StdStruct(new CustomError(StringList.concat(FIRST,RETURN_LINE,_cont.joinPages())),null_));
        }
        if (_b.isNull()) {
            throw new InvokeException(new StdStruct(new CustomError(StringList.concat(SECOND,RETURN_LINE,_cont.joinPages())),null_));
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
        Number nb_;
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
    static Argument calculateDiv(Argument _a, ContextEl _cont, Argument _b) {
        LgNames stds_ = _cont.getStandards();
        String div_;
        String null_;
        div_ = stds_.getAliasDivisionZero();
        null_ = stds_.getAliasNullPe();
        if (_a.isNull()) {
            throw new InvokeException(new StdStruct(new CustomError(StringList.concat(FIRST,RETURN_LINE,_cont.joinPages())),null_));
        }
        if (_b.isNull()) {
            throw new InvokeException(new StdStruct(new CustomError(StringList.concat(SECOND,RETURN_LINE,_cont.joinPages())),null_));
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
                throw new InvokeException(new StdStruct(new CustomError(_cont.joinPages()), div_));
            }
        } else if (p_ instanceof Integer) {
            bFour_ = (Integer) p_;
            if (bFour_.longValue() == 0) {
                throw new InvokeException(new StdStruct(new CustomError(_cont.joinPages()), div_));
            }
        } else if (p_ instanceof Short) {
            bFive_ = (Short) p_;
            if (bFive_.longValue() == 0) {
                throw new InvokeException(new StdStruct(new CustomError(_cont.joinPages()), div_));
            }
        } else if (p_ instanceof Byte) {
            bSix_ = (Byte) p_;
            if (bSix_.longValue() == 0) {
                throw new InvokeException(new StdStruct(new CustomError(_cont.joinPages()), div_));
            }
        }
        Number nb_;
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
    static Argument calculateMod(Argument _a, ContextEl _cont, Argument _b) {
        LgNames stds_ = _cont.getStandards();
        String div_;
        String null_;
        div_ = stds_.getAliasDivisionZero();
        null_ = stds_.getAliasNullPe();
        if (_a.isNull()) {
            throw new InvokeException(new StdStruct(new CustomError(StringList.concat(FIRST,RETURN_LINE,_cont.joinPages())),null_));
        }
        if (_b.isNull()) {
            throw new InvokeException(new StdStruct(new CustomError(StringList.concat(SECOND,RETURN_LINE,_cont.joinPages())),null_));
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
                throw new InvokeException(new StdStruct(new CustomError(_cont.joinPages()),div_));
            }
        } else if (p_ instanceof Integer) {
            bFour_ = (Integer) p_;
            if (bFour_.longValue() == 0) {
                throw new InvokeException(new StdStruct(new CustomError(_cont.joinPages()),div_));
            }
        } else if (p_ instanceof Short) {
            bFive_ = (Short) p_;
            if (bFive_.longValue() == 0) {
                throw new InvokeException(new StdStruct(new CustomError(_cont.joinPages()),div_));
            }
        } else if (p_ instanceof Byte) {
            bSix_ = (Byte) p_;
            if (bSix_.longValue() == 0) {
                throw new InvokeException(new StdStruct(new CustomError(_cont.joinPages()),div_));
            }
        }
        Number nb_;
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

    static ClassArgumentMatching getResultClass(ClassArgumentMatching _a, Analyzable _cont, ClassArgumentMatching _b) {
        int oa_ = PrimitiveTypeUtil.getOrderClass(_a, _cont);
        String exp_ = _cont.getStandards().getAliasNumber();
        boolean ok_ = true;
        if (oa_ == 0) {
            UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
            un_.setRc(_cont.getCurrentLocation());
            un_.setFileName(_cont.getCurrentFileName());
            un_.setExpectedResult(exp_);
            un_.setOperands(new StringList(_a.getName()));
            _cont.getClasses().getErrorsDet().add(un_);
            ok_ = false;
        }
        int ob_ = PrimitiveTypeUtil.getOrderClass(_b, _cont);
        if (ob_ == 0) {
            UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
            un_.setRc(_cont.getCurrentLocation());
            un_.setFileName(_cont.getCurrentFileName());
            un_.setExpectedResult(exp_);
            un_.setOperands(new StringList(_b.getName()));
            _cont.getClasses().getErrorsDet().add(un_);
            ok_ = false;
        }
        if (!ok_) {
            return new ClassArgumentMatching(exp_);
        }
        return getQuickResultClass(_a, oa_, _cont, _b, ob_);
    }

    static ClassArgumentMatching getQuickResultClass(ClassArgumentMatching _a, int _oa, Analyzable _cont, ClassArgumentMatching _b, int _ob) {
        ClassArgumentMatching arg_;
        int max_ = Math.max(_oa, _ob);
        if (_oa > _ob) {
            arg_ = _a;
        } else {
            arg_ = _b;
        }
        LgNames stds_ = _cont.getStandards();
        int intOrder_ = PrimitiveTypeUtil.getOrderClass(stds_.getAliasPrimInteger(), _cont);
        if (max_ < intOrder_) {
            arg_ = new ClassArgumentMatching(stds_.getAliasPrimInteger());
        }
        return PrimitiveTypeUtil.toPrimitive(arg_, true, _cont);
    }

    @Override
    public final void analyze(CustList<OperationNode> _nodes, Analyzable _conf,
            String _fieldName, String _op) {
        analyzeCommon(_nodes, _conf, _op);
    }

    final void analyzeCommon(CustList<OperationNode> _nodes, Analyzable _conf, String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        ClassArgumentMatching a_ = chidren_.first().getResultClass();
        ResultOperand r_;
        NatTreeMap<Integer, String> ops_ = getOperations().getOperators();
        ClassArgumentMatching c_ = chidren_.last().getResultClass();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ops_.firstKey(), _conf);
        r_ = analyzeOper(a_, ops_.firstValue(), c_, _conf);
        setCatenize(r_);
        a_ = r_.getResult();
        setResultClass(a_);
    }
    abstract ResultOperand analyzeOper(ClassArgumentMatching _a, String _op, ClassArgumentMatching _b, Analyzable _cont);
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
        NatTreeMap<Integer, String> ops_ = getOperations().getOperators();
        o_ = chidren_.last();
        Argument c_ = _nodes.getVal(o_).getArgument();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ops_.firstKey(), _conf);
        Argument r_;
        r_ = calculateOper(a_, ops_.firstValue(), c_, _conf);
        a_ = r_;
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }
    /**@throws InvokeRedinedMethException
    @throws NullObjectException*/
    @Override
    public final void calculate(CustList<OperationNode> _nodes, ContextEl _conf,
            String _op) {
        calculateCommon(_nodes, _conf, _op);
    }

    final void calculateCommon(CustList<OperationNode> _nodes, ContextEl _conf, String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument a_ = chidren_.first().getArgument();
        NatTreeMap<Integer, String> ops_ = getOperations().getOperators();
        Argument c_ = chidren_.last().getArgument();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ops_.firstKey(), _conf);
        Argument r_;
        r_ = calculateOper(a_, ops_.firstValue(), c_, _conf);
        a_ = r_;
        setSimpleArgument(a_, _conf);
    }

    @Override
    public final boolean isOtherConstructorClass() {
        return false;
    }

    @Override
    public final ConstructorId getConstId() {
        return null;
    }

    @Override
    public final boolean isPossibleInitClass() {
        return false;
    }
    @Override
    public final boolean isSuperConstructorCall() {
        return false;
    }

    @Override
    final void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }
    abstract void setCatenize(ResultOperand _res);
}
