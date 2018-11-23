package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.CustomFoundMethod;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.UnexpectedTypeOperationError;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassMethodIdReturn;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.ResultOperand;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.DoubleStruct;
import code.expressionlanguage.structs.FloatStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.StdStruct;
import code.util.CustList;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.Numbers;
import code.util.StringList;

public abstract class NumericOperation extends MethodOperation {
    private ClassMethodId classMethodId;

    public NumericOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    static Argument calculateAffect(Argument _left,ExecutableCode _conf, Argument _right, String _op, boolean _catString, ClassArgumentMatching _arg) {
        Argument o_;
        boolean convert_ = true;
        if (StringList.quickEq(_op, Block.PLUS_EQ)) {
            o_ = NumericOperation.calculateSum(_left, _right, _catString, _conf, _arg);
            convert_ = !_catString;
        } else if (StringList.quickEq(_op, Block.MINUS_EQ)) {
            o_ = NumericOperation.calculateDiff(_left, _right, _conf, _arg);
        } else if (StringList.quickEq(_op, Block.MULT_EQ)) {
            o_ = NumericOperation.calculateMult(_left, _right, _conf, _arg);
        } else if (StringList.quickEq(_op, Block.DIV_EQ)) {
            o_ = NumericOperation.calculateDivEx(_left, _conf, _right, _arg);
        } else if (StringList.quickEq(_op, Block.MOD_EQ)) {
            o_ = NumericOperation.calculateModEx(_left, _conf, _right, _arg);
        } else if (StringList.quickEq(_op, Block.INCR)) {
            o_ = AddOperation.addOne(_left, _conf, _arg);
        } else if (StringList.quickEq(_op, Block.DECR)) {
            o_ = AddOperation.removeOne(_left, _conf, _arg);
        } else if (StringList.quickEq(_op, Block.SHIFT_LEFT_EQ)) {
            o_ = calculateShiftLeft(_left, _right, _conf, _arg);
        } else if (StringList.quickEq(_op, Block.SHIFT_RIGHT_EQ)) {
            o_ = calculateShiftRight(_left, _right, _conf, _arg);
        } else if (StringList.quickEq(_op, Block.SHIFT_LOG_LEFT_EQ)) {
            o_ = calculateBitShiftLeft(_left, _right, _conf, _arg);
        } else if (StringList.quickEq(_op, Block.SHIFT_LOG_RIGHT_EQ)) {
            o_ = calculateBitShiftRight(_left, _right, _conf, _arg);
        } else if (StringList.quickEq(_op, Block.ROTATE_LEFT_EQ)) {
            o_ = calculateRotateLeft(_left, _right, _conf, _arg);
        } else if (StringList.quickEq(_op, Block.ROTATE_RIGHT_EQ)) {
            o_ = calculateRotateRight(_left, _right, _conf, _arg);
        } else if (StringList.quickEq(_op, Block.AND_EQ)) {
            o_ = calculateAnd(_left, _right, _conf, _arg);
            LgNames stds_ = _conf.getStandards();
            String bool_ = stds_.getAliasPrimBoolean();
            convert_ = !_arg.matchClass(bool_);
        } else if (StringList.quickEq(_op, Block.OR_EQ)) {
            o_ = calculateOr(_left, _right, _conf, _arg);
            LgNames stds_ = _conf.getStandards();
            String bool_ = stds_.getAliasPrimBoolean();
            convert_ = !_arg.matchClass(bool_);
        } else if (StringList.quickEq(_op, Block.XOR_EQ)) {
            o_ = calculateXor(_left, _right, _conf, _arg);
            LgNames stds_ = _conf.getStandards();
            String bool_ = stds_.getAliasPrimBoolean();
            convert_ = !_arg.matchClass(bool_);
        } else {
            o_ = _right;
            convert_ = false;
        }
        if (convert_) {
            if (_conf.getContextEl().hasExceptionOrFailInit()) {
                return o_;
            }
            ClassArgumentMatching cl_ = new ClassArgumentMatching(_left.getObjectClassName(_conf.getContextEl()));
            Argument converted_ = new Argument();
            converted_.setStruct(PrimitiveTypeUtil.convertObject(cl_, o_.getStruct(), _conf));
            o_ = converted_;
        }
        return o_;
    }
    static Argument calculateIncrDecr(Argument _left,ExecutableCode _conf, String _op, ClassArgumentMatching _arg) {
        Argument o_;
        if (StringList.quickEq(_op, Block.INCR)) {
            o_ = AddOperation.addOne(_left, _conf, _arg);
        } else {
            o_ = AddOperation.removeOne(_left, _conf, _arg);
        }
        ClassArgumentMatching cl_ = new ClassArgumentMatching(_left.getObjectClassName(_conf.getContextEl()));
        Argument converted_ = new Argument();
        converted_.setStruct(PrimitiveTypeUtil.convertObject(cl_, o_.getStruct(), _conf));
        o_ = converted_;
        return o_;
    }

    public static Argument calculateSum(Argument _a, Argument _b, boolean _catString, Analyzable _an,ClassArgumentMatching _order) {
        if (_catString) {
            StringBuilder str_ = new StringBuilder();
            str_.append(_a.getString(_an));
            str_.append(_b.getString(_an));
            Argument a_ = new Argument();
            a_.setObject(str_.toString());
            return a_;
        }
        int order_ = PrimitiveTypeUtil.getOrderClass(_order, _an);
        Number nb_;
        String longPrim_ = _an.getStandards().getAliasPrimLong();
        String intPrim_ = _an.getStandards().getAliasPrimInteger();
        String floatPrim_ = _an.getStandards().getAliasPrimFloat();
        Argument a_ = new Argument();
        if (order_ <= PrimitiveTypeUtil.getOrderClass(longPrim_, _an)) {
            long left_ = _a.getLong();
            long right_ = _b.getLong();
            nb_ = left_ + right_;
            if (order_ == PrimitiveTypeUtil.getOrderClass(intPrim_, _an)) {
                a_.setStruct(new IntStruct(nb_.intValue()));
            } else {
                a_.setStruct(new LongStruct(nb_.longValue()));
            }
        } else {
            double left_ = _a.getDouble();
            double right_ = _b.getDouble();
            nb_ = left_ + right_;
            if (order_ == PrimitiveTypeUtil.getOrderClass(floatPrim_, _an)) {
                a_.setStruct(new FloatStruct(nb_.floatValue()));
            } else {
                a_.setStruct(new DoubleStruct(nb_.doubleValue()));
            }
        }
        return a_;
    }

    public static Argument calculateDiff(Argument _a, Argument _b, Analyzable _an,ClassArgumentMatching _order) {
        int order_ = PrimitiveTypeUtil.getOrderClass(_order, _an);
        Number nb_;
        String longPrim_ = _an.getStandards().getAliasPrimLong();
        String intPrim_ = _an.getStandards().getAliasPrimInteger();
        String floatPrim_ = _an.getStandards().getAliasPrimFloat();
        Argument a_ = new Argument();
        if (order_ <= PrimitiveTypeUtil.getOrderClass(longPrim_, _an)) {
            long left_ = _a.getLong();
            long right_ = _b.getLong();
            nb_ = left_ - right_;
            if (order_ == PrimitiveTypeUtil.getOrderClass(intPrim_, _an)) {
                a_.setStruct(new IntStruct(nb_.intValue()));
            } else {
                a_.setStruct(new LongStruct(nb_.longValue()));
            }
        } else {
            double left_ = _a.getDouble();
            double right_ = _b.getDouble();
            nb_ = left_ - right_;
            if (order_ == PrimitiveTypeUtil.getOrderClass(floatPrim_, _an)) {
                a_.setStruct(new FloatStruct(nb_.floatValue()));
            } else {
                a_.setStruct(new DoubleStruct(nb_.doubleValue()));
            }
        }
        return a_;
    }
    public static Argument calculateMult(Argument _a, Argument _b, Analyzable _an,ClassArgumentMatching _order) {
        int order_ = PrimitiveTypeUtil.getOrderClass(_order, _an);
        Number nb_;
        String longPrim_ = _an.getStandards().getAliasPrimLong();
        String intPrim_ = _an.getStandards().getAliasPrimInteger();
        String floatPrim_ = _an.getStandards().getAliasPrimFloat();
        Argument a_ = new Argument();
        if (order_ <= PrimitiveTypeUtil.getOrderClass(longPrim_, _an)) {
            long left_ = _a.getLong();
            long right_ = _b.getLong();
            nb_ = left_ * right_;
            if (order_ == PrimitiveTypeUtil.getOrderClass(intPrim_, _an)) {
                a_.setStruct(new IntStruct(nb_.intValue()));
            } else {
                a_.setStruct(new LongStruct(nb_.longValue()));
            }
        } else {
            double left_ = _a.getDouble();
            double right_ = _b.getDouble();
            nb_ = left_ * right_;
            if (order_ == PrimitiveTypeUtil.getOrderClass(floatPrim_, _an)) {
                a_.setStruct(new FloatStruct(nb_.floatValue()));
            } else {
                a_.setStruct(new DoubleStruct(nb_.doubleValue()));
            }
        }
        return a_;
    }
    static Argument calculateDivEx(Argument _a, ExecutableCode _cont, Argument _b,ClassArgumentMatching _order) {
        LgNames stds_ = _cont.getStandards();
        String div_;
        div_ = stds_.getAliasDivisionZero();
        Argument res_ = calculateDiv(_a, _b, _cont, _order);
        if (res_.isNull()) {
            _cont.setException(new StdStruct(new CustomError(_cont.joinPages()),div_));
        }
        return res_;
    }
    public static Argument calculateDiv(Argument _a, Argument _b, Analyzable _an,ClassArgumentMatching _order) {
        int order_ = PrimitiveTypeUtil.getOrderClass(_order, _an);
        Number nb_;
        String longPrim_ = _an.getStandards().getAliasPrimLong();
        String intPrim_ = _an.getStandards().getAliasPrimInteger();
        String floatPrim_ = _an.getStandards().getAliasPrimFloat();
        Argument a_ = new Argument();
        if (order_ <= PrimitiveTypeUtil.getOrderClass(longPrim_, _an)) {
            long left_ = _a.getLong();
            long right_ = _b.getLong();
            if (right_ == 0) {
                return a_;
            }
            nb_ = left_ / right_;
            if (order_ == PrimitiveTypeUtil.getOrderClass(intPrim_, _an)) {
                a_.setStruct(new IntStruct(nb_.intValue()));
            } else {
                a_.setStruct(new LongStruct(nb_.longValue()));
            }
        } else {
            double left_ = _a.getDouble();
            double right_ = _b.getDouble();
            nb_ = left_ / right_;
            if (order_ == PrimitiveTypeUtil.getOrderClass(floatPrim_, _an)) {
                a_.setStruct(new FloatStruct(nb_.floatValue()));
            } else {
                a_.setStruct(new DoubleStruct(nb_.doubleValue()));
            }
        }
        return a_;
    }
    static Argument calculateModEx(Argument _a, ExecutableCode _cont, Argument _b,ClassArgumentMatching _order) {
        LgNames stds_ = _cont.getStandards();
        String div_;
        div_ = stds_.getAliasDivisionZero();
        Argument res_ = calculateMod(_a, _b, _cont, _order);
        if (res_.isNull()) {
            _cont.setException(new StdStruct(new CustomError(_cont.joinPages()),div_));
        }
        return res_;
    }
    public static Argument calculateMod(Argument _a, Argument _b, Analyzable _an,ClassArgumentMatching _order) {
        int order_ = PrimitiveTypeUtil.getOrderClass(_order, _an);
        Number nb_;
        String longPrim_ = _an.getStandards().getAliasPrimLong();
        String intPrim_ = _an.getStandards().getAliasPrimInteger();
        String floatPrim_ = _an.getStandards().getAliasPrimFloat();
        Argument a_ = new Argument();
        if (order_ <= PrimitiveTypeUtil.getOrderClass(longPrim_, _an)) {
            long left_ = _a.getLong();
            long right_ = _b.getLong();
            if (right_ == 0) {
                return a_;
            }
            nb_ = left_ % right_;
            if (order_ == PrimitiveTypeUtil.getOrderClass(intPrim_, _an)) {
                a_.setStruct(new IntStruct(nb_.intValue()));
            } else {
                a_.setStruct(new LongStruct(nb_.longValue()));
            }
        } else {
            double left_ = _a.getDouble();
            double right_ = _b.getDouble();
            nb_ = left_ % right_;
            if (order_ == PrimitiveTypeUtil.getOrderClass(floatPrim_, _an)) {
                a_.setStruct(new FloatStruct(nb_.floatValue()));
            } else {
                a_.setStruct(new DoubleStruct(nb_.doubleValue()));
            }
        }
        return a_;
    }

    public static Argument calculateAnd(Argument _a, Argument _b, Analyzable _an,ClassArgumentMatching _order) {
        LgNames stds_ = _an.getStandards();
        String bool_ = stds_.getAliasPrimBoolean();
        String int_ = stds_.getAliasPrimInteger();
        if (_order.matchClass(bool_)) {
            Argument o_ = new Argument();
            Boolean left_ = (Boolean) _a.getObject();
            Boolean right_ = (Boolean) _b.getObject();
            o_.setStruct(new BooleanStruct(left_ && right_));
            return o_;
        }
        if (_order.matchClass(int_)) {
            Argument o_ = new Argument();
            int left_ = _a.getInt();
            int right_ = _b.getInt();
            boolean[] bitsLeft_ = LgNames.toBits(left_);
            boolean[] bitsRight_ = LgNames.toBits(right_);
            int len_ = bitsLeft_.length;
            boolean[] bits_ = new boolean[len_];
            for (int i = 0; i < len_; i++) {
                bits_[i] = bitsLeft_[i] && bitsRight_[i];
            }
            int value_ = LgNames.toInt(bits_);
            o_.setStruct(new IntStruct(value_));
            return o_;
        }
        Argument o_ = new Argument();
        long left_ = _a.getLong();
        long right_ = _b.getLong();
        boolean[] bitsLeft_ = LgNames.toBits(left_);
        boolean[] bitsRight_ = LgNames.toBits(right_);
        int len_ = bitsLeft_.length;
        boolean[] bits_ = new boolean[len_];
        for (int i = 0; i < len_; i++) {
            bits_[i] = bitsLeft_[i] && bitsRight_[i];
        }
        long value_ = LgNames.toLong(bits_);
        o_.setStruct(new LongStruct(value_));
        return o_;
    }

    public static Argument calculateOr(Argument _a, Argument _b, Analyzable _an,ClassArgumentMatching _order) {
        LgNames stds_ = _an.getStandards();
        String bool_ = stds_.getAliasPrimBoolean();
        String int_ = stds_.getAliasPrimInteger();
        if (_order.matchClass(bool_)) {
            Argument o_ = new Argument();
            Boolean left_ = (Boolean) _a.getObject();
            Boolean right_ = (Boolean) _b.getObject();
            o_.setStruct(new BooleanStruct(left_ || right_));
            return o_;
        }
        if (_order.matchClass(int_)) {
            Argument o_ = new Argument();
            int left_ = _a.getInt();
            int right_ = _b.getInt();
            boolean[] bitsLeft_ = LgNames.toBits(left_);
            boolean[] bitsRight_ = LgNames.toBits(right_);
            int len_ = bitsLeft_.length;
            boolean[] bits_ = new boolean[len_];
            for (int i = 0; i < len_; i++) {
                bits_[i] = bitsLeft_[i] || bitsRight_[i];
            }
            int value_ = LgNames.toInt(bits_);
            o_.setStruct(new IntStruct(value_));
            return o_;
        }
        Argument o_ = new Argument();
        long left_ = _a.getLong();
        long right_ = _b.getLong();
        boolean[] bitsLeft_ = LgNames.toBits(left_);
        boolean[] bitsRight_ = LgNames.toBits(right_);
        int len_ = bitsLeft_.length;
        boolean[] bits_ = new boolean[len_];
        for (int i = 0; i < len_; i++) {
            bits_[i] = bitsLeft_[i] || bitsRight_[i];
        }
        long value_ = LgNames.toLong(bits_);
        o_.setStruct(new LongStruct(value_));
        return o_;
    }

    public static Argument calculateXor(Argument _a, Argument _b, Analyzable _an,ClassArgumentMatching _order) {
        LgNames stds_ = _an.getStandards();
        String bool_ = stds_.getAliasPrimBoolean();
        String int_ = stds_.getAliasPrimInteger();
        if (_order.matchClass(bool_)) {
            Argument o_ = new Argument();
            Boolean left_ = (Boolean) _a.getObject();
            Boolean right_ = (Boolean) _b.getObject();
            o_.setStruct(new BooleanStruct(left_ != right_));
            return o_;
        }
        if (_order.matchClass(int_)) {
            Argument o_ = new Argument();
            int left_ = _a.getInt();
            int right_ = _b.getInt();
            boolean[] bitsLeft_ = LgNames.toBits(left_);
            boolean[] bitsRight_ = LgNames.toBits(right_);
            int len_ = bitsLeft_.length;
            boolean[] bits_ = new boolean[len_];
            for (int i = 0; i < len_; i++) {
                bits_[i] = bitsLeft_[i] != bitsRight_[i];
            }
            int value_ = LgNames.toInt(bits_);
            o_.setStruct(new IntStruct(value_));
            return o_;
        }
        Argument o_ = new Argument();
        long left_ = _a.getLong();
        long right_ = _b.getLong();
        boolean[] bitsLeft_ = LgNames.toBits(left_);
        boolean[] bitsRight_ = LgNames.toBits(right_);
        int len_ = bitsLeft_.length;
        boolean[] bits_ = new boolean[len_];
        for (int i = 0; i < len_; i++) {
            bits_[i] = bitsLeft_[i] != bitsRight_[i];
        }
        long value_ = LgNames.toLong(bits_);
        o_.setStruct(new LongStruct(value_));
        return o_;
    }
    public static Argument calculateShiftLeft(Argument _a, Argument _b, Analyzable _an,ClassArgumentMatching _order) {
        LgNames stds_ = _an.getStandards();
        String int_ = stds_.getAliasPrimInteger();
        if (_order.matchClass(int_)) {
            Argument o_ = new Argument();
            int left_ = _a.getInt();
            int right_ = _b.getInt();
            boolean[] bitsRight_ = LgNames.toBits(right_);
            int value_ = LgNames.toUnsignedInt(bitsRight_,5);
            int power_ = 1;
            for (int i = 0; i< value_; i++) {
                power_ *= 2;
            }
            o_.setStruct(new IntStruct(left_*power_));
            return o_;
        }
        Argument o_ = new Argument();
        long left_ = _a.getLong();
        long right_ = _b.getLong();
        boolean[] bitsRight_ = LgNames.toBits(right_);
        long value_ = LgNames.toUnsignedLong(bitsRight_,6);
        long power_ = 1;
        for (int i = 0; i< value_; i++) {
            power_ *= 2;
        }
        o_.setStruct(new LongStruct(left_*power_));
        return o_;
    }
    public static Argument calculateShiftRight(Argument _a, Argument _b, Analyzable _an,ClassArgumentMatching _order) {
        LgNames stds_ = _an.getStandards();
        String int_ = stds_.getAliasPrimInteger();
        if (_order.matchClass(int_)) {
            Argument o_ = new Argument();
            int left_ = _a.getInt();
            int right_ = _b.getInt();
            boolean[] bitsRight_ = LgNames.toBits(right_);
            int value_ = LgNames.toUnsignedInt(bitsRight_,5);
            int power_ = 1;
            for (int i = 0; i< value_; i++) {
                power_ *= 2;
            }
            o_.setStruct(new IntStruct(Numbers.quot(left_, power_)));
            return o_;
        }
        Argument o_ = new Argument();
        long left_ = _a.getLong();
        long right_ = _b.getLong();
        boolean[] bitsRight_ = LgNames.toBits(right_);
        long value_ = LgNames.toUnsignedLong(bitsRight_,6);
        long power_ = 1;
        for (int i = 0; i< value_; i++) {
            power_ *= 2;
        }
        o_.setStruct(new LongStruct(Numbers.quot(left_, power_)));
        return o_;
    }
    public static Argument calculateBitShiftLeft(Argument _a, Argument _b, Analyzable _an,ClassArgumentMatching _order) {
        LgNames stds_ = _an.getStandards();
        String int_ = stds_.getAliasPrimInteger();
        if (_order.matchClass(int_)) {
            Argument o_ = new Argument();
            int left_ = _a.getInt();
            int right_ = _b.getInt();
            boolean[] bitsRight_ = LgNames.toBits(right_);
            int value_ = LgNames.toUnsignedInt(bitsRight_,5);
            boolean[] bitsLeft_ = LgNames.toBits(left_);
            int diff_ = 32 - value_;
            for (int i = 1; i < diff_; i++) {
                bitsLeft_[i] = bitsLeft_[i + value_];
            }
            for (int i = diff_; i < 32; i++) {
                bitsLeft_[i] = false;
            }
            o_.setStruct(new IntStruct(LgNames.toInt(bitsLeft_)));
            return o_;
        }
        Argument o_ = new Argument();
        long left_ = _a.getLong();
        long right_ = _b.getLong();
        boolean[] bitsRight_ = LgNames.toBits(right_);
        long value_ = LgNames.toUnsignedLong(bitsRight_,6);
        boolean[] bitsLeft_ = LgNames.toBits(left_);
        int diff_ = 64 - (int)value_;
        for (int i = 1; i < diff_; i++) {
            bitsLeft_[i] = bitsLeft_[i + (int)value_];
        }
        for (int i = diff_; i < 64; i++) {
            bitsLeft_[i] = false;
        }
        o_.setStruct(new LongStruct(LgNames.toLong(bitsLeft_)));
        return o_;
    }
    public static Argument calculateBitShiftRight(Argument _a, Argument _b, Analyzable _an,ClassArgumentMatching _order) {
        LgNames stds_ = _an.getStandards();
        String int_ = stds_.getAliasPrimInteger();
        if (_order.matchClass(int_)) {
            Argument o_ = new Argument();
            int left_ = _a.getInt();
            int right_ = _b.getInt();
            boolean[] bitsRight_ = LgNames.toBits(right_);
            int value_ = LgNames.toUnsignedInt(bitsRight_,5);
            boolean[] bitsLeft_ = LgNames.toBits(left_);
            int diff_ = 32 - value_;
            for (int i = 0; i < diff_; i++) {
                int index_ = 31 - i;
                bitsLeft_[index_] = bitsLeft_[index_ - value_];
            }
            for (int i = diff_; i < 32; i++) {
                int index_ = 31 - i;
                bitsLeft_[index_] = false;
            }
            o_.setStruct(new IntStruct(LgNames.toInt(bitsLeft_)));
            return o_;
        }
        Argument o_ = new Argument();
        long left_ = _a.getLong();
        long right_ = _b.getLong();
        boolean[] bitsRight_ = LgNames.toBits(right_);
        long value_ = LgNames.toUnsignedLong(bitsRight_,6);
        boolean[] bitsLeft_ = LgNames.toBits(left_);
        int diff_ = 64 - (int)value_;
        for (int i = 0; i < diff_; i++) {
            int index_ = 63 - i;
            bitsLeft_[index_] = bitsLeft_[index_ - (int)value_];
        }
        for (int i = diff_; i < 64; i++) {
            int index_ = 63 - i;
            bitsLeft_[index_] = false;
        }
        o_.setStruct(new LongStruct(LgNames.toLong(bitsLeft_)));
        return o_;
    }
    public static Argument calculateRotateLeft(Argument _a, Argument _b, Analyzable _an,ClassArgumentMatching _order) {
        LgNames stds_ = _an.getStandards();
        String int_ = stds_.getAliasPrimInteger();
        if (_order.matchClass(int_)) {
            Argument o_ = new Argument();
            int left_ = _a.getInt();
            int right_ = _b.getInt();
            boolean[] bitsRight_ = LgNames.toBits(right_);
            int value_ = LgNames.toUnsignedInt(bitsRight_,5);
            boolean[] bitsLeft_ = LgNames.toBits(left_);
            int max_ = bitsLeft_.length - 1;
            for (int i = 0; i < value_; i++) {
                boolean firstBit_ = bitsLeft_[0];
                for (int j = 0; j < max_; j++) {
                    bitsLeft_[j] = bitsLeft_[j + 1];
                }
                bitsLeft_[max_] = firstBit_;
            }
            o_.setStruct(new IntStruct(LgNames.toInt(bitsLeft_)));
            return o_;
        }
        Argument o_ = new Argument();
        long left_ = _a.getLong();
        long right_ = _b.getLong();
        boolean[] bitsRight_ = LgNames.toBits(right_);
        long value_ = LgNames.toUnsignedLong(bitsRight_,6);
        boolean[] bitsLeft_ = LgNames.toBits(left_);
        int max_ = bitsLeft_.length - 1;
        for (int i = 0; i < value_; i++) {
            boolean firstBit_ = bitsLeft_[0];
            for (int j = 0; j < max_; j++) {
                bitsLeft_[j] = bitsLeft_[j + 1];
            }
            bitsLeft_[max_] = firstBit_;
        }
        o_.setStruct(new LongStruct(LgNames.toLong(bitsLeft_)));
        return o_;
    }
    public static Argument calculateRotateRight(Argument _a, Argument _b, Analyzable _an,ClassArgumentMatching _order) {
        LgNames stds_ = _an.getStandards();
        String int_ = stds_.getAliasPrimInteger();
        if (_order.matchClass(int_)) {
            Argument o_ = new Argument();
            int left_ = _a.getInt();
            int right_ = _b.getInt();
            boolean[] bitsRight_ = LgNames.toBits(right_);
            int value_ = LgNames.toUnsignedInt(bitsRight_,5);
            boolean[] bitsLeft_ = LgNames.toBits(left_);
            int max_ = bitsLeft_.length - 1;
            for (int i = 0; i < value_; i++) {
                boolean firstBit_ = bitsLeft_[max_];
                for (int j = 0; j < max_; j++) {
                    int index_ = max_ - j;
                    bitsLeft_[index_] = bitsLeft_[index_ - 1];
                }
                bitsLeft_[0] = firstBit_;
            }
            o_.setStruct(new IntStruct(LgNames.toInt(bitsLeft_)));
            return o_;
        }
        Argument o_ = new Argument();
        long left_ = _a.getLong();
        long right_ = _b.getLong();
        boolean[] bitsRight_ = LgNames.toBits(right_);
        long value_ = LgNames.toUnsignedLong(bitsRight_,6);
        boolean[] bitsLeft_ = LgNames.toBits(left_);
        int max_ = bitsLeft_.length - 1;
        for (int i = 0; i < value_; i++) {
            boolean firstBit_ = bitsLeft_[max_];
            for (int j = 0; j < max_; j++) {
                int index_ = max_ - j;
                bitsLeft_[index_] = bitsLeft_[index_ - 1];
            }
            bitsLeft_[0] = firstBit_;
        }
        o_.setStruct(new LongStruct(LgNames.toLong(bitsLeft_)));
        return o_;
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
            un_.setOperands(_a);
            _cont.getClasses().addError(un_);
            _cont.setOkNumOp(false);
            ok_ = false;
        }
        int ob_ = PrimitiveTypeUtil.getOrderClass(_b, _cont);
        if (ob_ == 0) {
            UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
            un_.setRc(_cont.getCurrentLocation());
            un_.setFileName(_cont.getCurrentFileName());
            un_.setExpectedResult(exp_);
            un_.setOperands(_b);
            _cont.getClasses().addError(un_);
            _cont.setOkNumOp(false);
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
    public final void analyze(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        ClassArgumentMatching a_ = chidren_.first().getResultClass();
        ResultOperand r_;
        NatTreeMap<Integer, String> ops_ = getOperations().getOperators();
        ClassArgumentMatching c_ = chidren_.last().getResultClass();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ops_.firstKey(), _conf);
        ClassMethodIdReturn cust_ = getOperator(_conf, ops_.firstValue(), a_, c_);
        if (cust_.isFoundMethod()) {
            setResultClass(new ClassArgumentMatching(cust_.getReturnType()));
            String foundClass_ = cust_.getRealClass();
            foundClass_ = Templates.getIdFromAllTypes(foundClass_);
            MethodId id_ = cust_.getRealId();
            classMethodId = new ClassMethodId(foundClass_, id_);
            MethodId realId_ = cust_.getRealId();
            CustList<ClassArgumentMatching> firstArgs_ = new CustList<ClassArgumentMatching>();
            for (OperationNode o: chidren_) {
                firstArgs_.add(o.getResultClass());
            }
            InvokingOperation.unwrapArgsFct(chidren_, realId_, -1, EMPTY_STRING, firstArgs_, _conf);
            return;
        }
        r_ = analyzeOper(a_, ops_.firstValue(), c_, _conf);
        setCatenize(r_);
        a_ = r_.getResult();
        setResultClass(a_);
    }

    abstract ResultOperand analyzeOper(ClassArgumentMatching _a, String _op, ClassArgumentMatching _b, Analyzable _cont);
    @Override
    public final void analyzeAssignmentBeforeNextSibling(Analyzable _conf,
            OperationNode _nextSibling, OperationNode _previous) {
        analyzeStdAssignmentBeforeNextSibling(_conf, _nextSibling, _previous);
    }
    @Override
    public final void analyzeAssignmentAfter(Analyzable _conf) {
        analyzeStdAssignmentAfter(_conf);
    }
    abstract Argument calculateOper(Argument _a, String _op, Argument _b, ExecutableCode _cont);
    abstract Argument calculateOperAna(Argument _a, String _op, Argument _b, Analyzable _an);

    @Override
    public final Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode o_ = chidren_.first();
        Argument a_ = _nodes.getVal(o_).getArgument();
        NatTreeMap<Integer, String> ops_ = getOperations().getOperators();
        o_ = chidren_.last();
        Argument c_ = _nodes.getVal(o_).getArgument();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ops_.firstKey(), _conf);
        if (classMethodId != null) {
            CustList<Argument> arguments_ = new CustList<Argument>();
            for (OperationNode o: chidren_) {
                arguments_.add(_nodes.getVal(o).getArgument());
            }
            CustList<Argument> firstArgs_ = InvokingOperation.listArguments(chidren_, -1, EMPTY_STRING, arguments_, _conf);
            String classNameFound_ = classMethodId.getClassName();
            MethodId id_ = classMethodId.getConstraints();
            _conf.getContextEl().setCallMethod(new CustomFoundMethod(Argument.createVoid(), classNameFound_, id_, firstArgs_));
            return Argument.createVoid();
        }
        Argument r_;
        r_ = calculateOper(a_, ops_.firstValue(), c_, _conf);
        if (_conf.hasExceptionOrFailInit()) {
            return r_;
        }
        a_ = r_;
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }
    @Override
    public void quickCalculate(Analyzable _conf) {
        if (classMethodId != null || !_conf.isOkNumOp()) {
            return;
        }
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument a_ = chidren_.first().getArgument();
        NatTreeMap<Integer, String> ops_ = getOperations().getOperators();
        Argument c_ = chidren_.last().getArgument();
        Argument r_;
        r_ = calculateOperAna(a_, ops_.firstValue(), c_, _conf);
        if (r_.isNull()) {
            return;
        }
        a_ = r_;
        setSimpleArgumentAna(a_, _conf);
    }
    @Override
    public final void calculate(ExecutableCode _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument a_ = chidren_.first().getArgument();
        NatTreeMap<Integer, String> ops_ = getOperations().getOperators();
        Argument c_ = chidren_.last().getArgument();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ops_.firstKey(), _conf);
        if (classMethodId != null) {
            CustList<Argument> arguments_ = new CustList<Argument>();
            for (OperationNode o: chidren_) {
                arguments_.add(o.getArgument());
            }
            CustList<Argument> firstArgs_ = InvokingOperation.listArguments(chidren_, -1, EMPTY_STRING, arguments_, _conf);
            String classNameFound_ = classMethodId.getClassName();
            MethodId id_ = classMethodId.getConstraints();
            Argument res_;
            res_ = ProcessMethod.calculateArgument(Argument.createVoid(), classNameFound_, id_, firstArgs_, _conf.getContextEl());
            setSimpleArgument(res_, _conf);
            return;
        }
        Argument r_;
        r_ = calculateOper(a_, ops_.firstValue(), c_, _conf);
        if (_conf.getContextEl().hasException()) {
            return;
        }
        a_ = r_;
        setSimpleArgument(a_, _conf);
    }

    @Override
    public final ConstructorId getConstId() {
        return null;
    }

    @Override
    final void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }
    abstract void setCatenize(ResultOperand _res);
}
