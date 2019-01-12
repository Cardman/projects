package code.expressionlanguage.structs;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.instr.NumberInfos;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.util.Numbers;
import code.util.StringList;
import code.util.comparators.ComparatorBoolean;

public abstract class NumberStruct implements DisplayableStruct, ExportableStringStruct,RealInstanceStruct {
    private static final int DEFAULT_RADIX = 10;
    @Override
    public final Struct getParent() {
        return NullStruct.NULL_VALUE;
    }
    public static void calculateOperator(Analyzable _cont, ResultErrorStd _res, ClassArgumentMatching _order, String _op, boolean _catString, Struct... _args) {
        if (_catString) {
            if (StringList.quickEq(_op, "+") || StringList.quickEq(_op, "+=")) {
              catenize(_cont, _res, _args);
              return;
            }
        }
        if (StringList.quickEq(_op, "++")) {
            IntStruct one_ = new IntStruct(1);
            _res.setResult(calculateSum((NumberStruct)_args[0], one_, _cont, _order));
            return;
        }
        if (StringList.quickEq(_op, "--")) {
            IntStruct one_ = new IntStruct(1);
            _res.setResult(calculateDiff((NumberStruct)_args[0], one_, _cont, _order));
            return;
        }
        if (StringList.quickEq(_op, "-")) {
            _res.setResult(opposite((NumberStruct)_args[0], _cont, _order));
            return;
        }
        if (StringList.quickEq(_op, "+")) {
            _res.setResult(idNumber((NumberStruct)_args[0], _cont, _order));
            return;
        }
        if (StringList.quickEq(_op, "~")) {
            _res.setResult(negBinNumber((NumberStruct)_args[0], _cont, _order));
            return;
        }
        if (StringList.quickEq(_op, "!")) {
            Boolean arg_ = ((BooleanStruct)_args[0]).getInstance();
            _res.setResult(new BooleanStruct(!arg_));
            return;
        }
        if (StringList.quickEq(_op, "!=")) {
            _res.setResult(new BooleanStruct(!_args[0].sameReference(_args[1])));
            return;
        }
        if (StringList.quickEq(_op, "==")) {
            _res.setResult(new BooleanStruct(_args[0].sameReference(_args[1])));
            return;
        }
        String op_ = _op;
        if (op_.endsWith("=")) {
            op_ = op_.substring(0, op_.length() - 1);
        }
        if (StringList.quickEq(op_, "+")) {
            _res.setResult(calculateSum((NumberStruct)_args[0], (NumberStruct)_args[1], _cont, _order));
            return;
        }
        if (StringList.quickEq(op_, "-")) {
            _res.setResult(calculateDiff((NumberStruct)_args[0], (NumberStruct)_args[1], _cont, _order));
            return;
        }
        if (StringList.quickEq(op_, "*")) {
            _res.setResult(calculateMult((NumberStruct)_args[0], (NumberStruct)_args[1], _cont, _order));
            return;
        }
        if (StringList.quickEq(op_, "/")) {
            _res.setResult(calculateDivEx((NumberStruct)_args[0], (NumberStruct)_args[1], _cont, _order));
            return;
        }
        if (StringList.quickEq(op_, "%")) {
            _res.setResult(calculateModEx((NumberStruct)_args[0], (NumberStruct)_args[1], _cont, _order));
            return;
        }
        if (StringList.quickEq(op_, "&")) {
            _res.setResult(calculateAnd(_args[0], _args[1], _cont, _order));
            return;
        }
        if (StringList.quickEq(op_, "|")) {
            _res.setResult(calculateOr(_args[0], _args[1], _cont, _order));
            return;
        }
        if (StringList.quickEq(op_, "^")) {
            _res.setResult(calculateXor(_args[0], _args[1], _cont, _order));
            return;
        }
        if (StringList.quickEq(op_, "<<")) {
            _res.setResult(calculateShiftLeft((NumberStruct)_args[0], (NumberStruct)_args[1], _cont, _order));
            return;
        }
        if (StringList.quickEq(op_, ">>")) {
            _res.setResult(calculateShiftRight((NumberStruct)_args[0], (NumberStruct)_args[1], _cont, _order));
            return;
        }
        if (StringList.quickEq(op_, "<<<")) {
            _res.setResult(calculateBitShiftLeft((NumberStruct)_args[0], (NumberStruct)_args[1], _cont, _order));
            return;
        }
        if (StringList.quickEq(op_, ">>>")) {
            _res.setResult(calculateBitShiftRight((NumberStruct)_args[0], (NumberStruct)_args[1], _cont, _order));
            return;
        }
        if (StringList.quickEq(op_, "<<<<")) {
            _res.setResult(calculateRotateLeft((NumberStruct)_args[0], (NumberStruct)_args[1], _cont, _order));
            return;
        }
        _res.setResult(calculateRotateRight((NumberStruct)_args[0], (NumberStruct)_args[1], _cont, _order));
    }

  private static void catenize(Analyzable _cont, ResultErrorStd _res, Struct... _args) {
    StringBuilder str_ = new StringBuilder();
    str_.append(((DisplayableStruct)_args[0]).getDisplayedString(_cont).getInstance());
    str_.append(((DisplayableStruct)_args[1]).getDisplayedString(_cont).getInstance());
    _res.setResult(new StringStruct(str_.toString()));
    return;
  }

  public static void instantiate(Analyzable _cont, ResultErrorStd _res, ConstructorId _method, Struct... _args) {
      String type_ = _method.getName();
        StringList list_ = _method.getParametersTypes();
        LgNames lgNames_ = _cont.getStandards();
        String booleanType_ = lgNames_.getAliasBoolean();
        String charType_ = lgNames_.getAliasCharacter();
        String stringType_ = lgNames_.getAliasString();
        String byteType_ = lgNames_.getAliasByte();
        String shortType_ = lgNames_.getAliasShort();
        String intType_ = lgNames_.getAliasInteger();
        String longType_ = lgNames_.getAliasLong();
        String floatType_ = lgNames_.getAliasFloat();
        String doubleType_ = lgNames_.getAliasDouble();
        if (StringList.quickEq(type_, booleanType_)) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                String one_ = ((CharSequenceStruct) _args[0]).getInstance().toString();
                _res.setResult(new BooleanStruct(Boolean.parseBoolean(one_)));
            } else {
                Boolean one_ = ((BooleanStruct) _args[0]).getInstance();
                _res.setResult(new BooleanStruct(one_));
            }
        } else if (StringList.quickEq(type_, charType_)) {
            Character one_ = ((CharStruct)_args[0]).getChar();
            _res.setResult(new CharStruct(one_));
        } else if (StringList.quickEq(type_, byteType_)) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                String one_ = ((CharSequenceStruct) _args[0]).getInstance().toString();
                Long lg_;
                int radix_ = DEFAULT_RADIX;
                if (list_.size() != 1) {
                    radix_ = ((NumberStruct) _args[1]).getInt();
                }
                lg_ = LgNames.parseLong(one_, radix_);
                if (lg_ == null || lg_ < Byte.MIN_VALUE || lg_ > Byte.MAX_VALUE) {
                    _res.setError(lgNames_.getAliasNbFormat());
                } else {
                    _res.setResult(new ByteStruct(lg_.byteValue()));
                }
            } else {
                Byte one_ = ((NumberStruct) _args[0]).getByte();
                _res.setResult(new ByteStruct(one_));
            }
        } else if (StringList.quickEq(type_, shortType_)) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                String one_ = ((CharSequenceStruct) _args[0]).getInstance().toString();
                Long lg_;
                int radix_ = DEFAULT_RADIX;
                if (list_.size() != 1) {
                    radix_ = ((NumberStruct) _args[1]).getInt();
                }
                lg_ = LgNames.parseLong(one_, radix_);
                if (lg_ == null || lg_ < Short.MIN_VALUE || lg_ > Short.MAX_VALUE) {
                    _res.setError(lgNames_.getAliasNbFormat());
                } else {
                    _res.setResult(new ShortStruct(lg_.shortValue()));
                }
            } else {
                Short one_ = ((NumberStruct) _args[0]).getShort();
                _res.setResult(new ShortStruct(one_));
            }
        } else if (StringList.quickEq(type_, intType_)) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                String one_ = ((CharSequenceStruct) _args[0]).getInstance().toString();
                Long lg_;
                int radix_ = DEFAULT_RADIX;
                if (list_.size() != 1) {
                    radix_ = ((NumberStruct) _args[1]).getInt();
                }
                lg_ = LgNames.parseLong(one_, radix_);
                if (lg_ == null || lg_ < Integer.MIN_VALUE || lg_ > Integer.MAX_VALUE) {
                    _res.setError(lgNames_.getAliasNbFormat());
                } else {
                    _res.setResult(new IntStruct(lg_.intValue()));
                }
            } else {
                Integer one_ = ((NumberStruct) _args[0]).getInt();
                _res.setResult(new IntStruct(one_));
            }
        } else if (StringList.quickEq(type_, longType_)) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                String one_ = ((CharSequenceStruct) _args[0]).getInstance().toString();
                Long lg_;
                int radix_ = DEFAULT_RADIX;
                if (list_.size() != 1) {
                    radix_ = ((NumberStruct) _args[1]).getInt();
                }
                lg_ = LgNames.parseLong(one_, radix_);
                if (lg_ == null) {
                    _res.setError(lgNames_.getAliasNbFormat());
                } else {
                    _res.setResult(new LongStruct(lg_));
                }
            } else {
                Long one_ = ((NumberStruct) _args[0]).getLong();
                _res.setResult(new LongStruct(one_));
            }
        } else if (StringList.quickEq(type_, floatType_)) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                String one_ = ((CharSequenceStruct) _args[0]).getInstance().toString();
                NumberInfos infos_ = LgNames.trySplitDouble(one_);
                boolean valid_ = true;
                if (infos_ == null) {
                    valid_ = false;
                }
                Double d_ = null;
                if (valid_) {
                    d_ = LgNames.parseDouble(infos_);
                    double abs_ = Math.abs(d_);
                    if (abs_ > Float.MAX_VALUE) {
                        valid_ = false;
                    }
                }
                if (!valid_) {
                    _res.setError(lgNames_.getAliasNbFormat());
                } else {
                    _res.setResult(new FloatStruct(d_.floatValue()));
                }
            } else {
                Float one_ = ((NumberStruct) _args[0]).getFloat();
                _res.setResult(new FloatStruct(one_));
            }
        } else if (StringList.quickEq(type_, doubleType_)) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                String one_ = ((CharSequenceStruct) _args[0]).getInstance().toString();
                boolean valid_ = true;
                NumberInfos infos_ = LgNames.trySplitDouble(one_);
                if (infos_ == null) {
                    valid_ = false;
                }
                Double d_ = null;
                if (valid_) {
                    d_ = LgNames.parseDouble(infos_);
                }
                if (!valid_) {
                    _res.setError(lgNames_.getAliasNbFormat());
                } else {
                    _res.setResult(new DoubleStruct(d_));
                }
            } else {
                Double one_ = ((NumberStruct) _args[0]).getDouble();
                _res.setResult(new DoubleStruct(one_));
            }
        }
    }
    public static void calculate(Analyzable _cont, ResultErrorStd _res, ClassMethodId _method, Struct _struct, Struct... _args) {
        String type_ = _method.getClassName();
        String name_ = _method.getConstraints().getName();
        StringList list_ = _method.getConstraints().getParametersTypes();
        LgNames lgNames_ = _cont.getStandards();
        String booleanType_ = lgNames_.getAliasBoolean();
        String charType_ = lgNames_.getAliasCharacter();
        String nbType_ = lgNames_.getAliasNumber();
        String stringType_ = lgNames_.getAliasString();
        String booleanPrimType_ = lgNames_.getAliasPrimBoolean();
        if (StringList.quickEq(type_, booleanType_)) {
            BooleanStruct instance_ = (BooleanStruct) _struct;
            if (StringList.quickEq(name_, lgNames_.getAliasBooleanValue())) {
                _res.setResult(new BooleanStruct(instance_.getInstance()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasCompare())) {
                _res.setResult(new IntStruct(ComparatorBoolean.cmp(((BooleanStruct) _args[0]).getInstance(),((BooleanStruct) _args[1]).getInstance())));
            } else if (StringList.quickEq(name_, lgNames_.getAliasCompareTo())) {
                _res.setResult(new IntStruct(ComparatorBoolean.cmp(instance_.getInstance(),((BooleanStruct) _args[0]).getInstance())));
            } else if (StringList.quickEq(name_, lgNames_.getAliasEquals())) {
                _res.setResult(new BooleanStruct(instance_.sameReference(_args[0])));
            } else if (StringList.quickEq(name_, lgNames_.getAliasParseBoolean())) {
                _res.setResult(new BooleanStruct(Boolean.parseBoolean(((CharSequenceStruct) _args[0]).getInstance().toString())));
            } else if (StringList.quickEq(name_, lgNames_.getAliasToString())) {
                if (!list_.isEmpty()) {
                    _res.setResult(new StringStruct(Boolean.toString(((BooleanStruct) _args[0]).getInstance())));
                } else {
                    _res.setResult(new StringStruct(Boolean.toString(instance_.getInstance())));
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasValueOf())) {
                if (StringList.quickEq(list_.first(), booleanPrimType_)) {
                    _res.setResult(new BooleanStruct(((BooleanStruct) _args[0]).getInstance()));
                } else {
                    _res.setResult(new BooleanStruct(Boolean.valueOf(((CharSequenceStruct) _args[0]).getInstance().toString())));
                }
            }
        } else if (StringList.quickEq(type_, charType_)) {
            if (_method.getConstraints().isStaticMethod()) {
                if (StringList.quickEq(name_, lgNames_.getAliasCompare())) {
                    Character one_ = ((CharStruct) _args[0]).getChar();
                    Character two_ = ((CharStruct) _args[1]).getChar();
                    _res.setResult(new IntStruct(one_.compareTo(two_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasDigit())) {
                    Character one_ = ((CharStruct) _args[0]).getChar();
                    Integer two_ = ((NumberStruct) _args[1]).getInstance().intValue();
                    _res.setResult(new IntStruct(Character.digit(one_, two_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasForDigit())) {
                    Integer one_ = ((NumberStruct) _args[0]).getInstance().intValue();
                    Integer two_ = ((NumberStruct) _args[1]).getInstance().intValue();
                    _res.setResult(new CharStruct(Character.forDigit(one_, two_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasGetDirectionality())) {
                    Character one_ = ((CharStruct) _args[0]).getChar();
                    _res.setResult(new ByteStruct(Character.getDirectionality(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasGetType())) {
                    Character one_ = ((CharStruct) _args[0]).getChar();
                    _res.setResult(new IntStruct(Character.getType(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsDigit())) {
                    Character one_ = ((CharStruct) _args[0]).getChar();
                    _res.setResult(new BooleanStruct(ContextEl.isDigit(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsLetter())) {
                    Character one_ = ((CharStruct) _args[0]).getChar();
                    _res.setResult(new BooleanStruct(Character.isLetter(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsLetterOrDigit())) {
                    Character one_ = ((CharStruct) _args[0]).getChar();
                    _res.setResult(new BooleanStruct(Character.isLetterOrDigit(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsLowerCase())) {
                    Character one_ = ((CharStruct) _args[0]).getChar();
                    _res.setResult(new BooleanStruct(Character.isLowerCase(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsUpperCase())) {
                    Character one_ = ((CharStruct) _args[0]).getChar();
                    _res.setResult(new BooleanStruct(Character.isUpperCase(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsSpace())) {
                    Character one_ = ((CharStruct) _args[0]).getChar();
                    _res.setResult(new BooleanStruct(Character.isWhitespace(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsWhitespace())) {
                    Character one_ = ((CharStruct) _args[0]).getChar();
                    _res.setResult(new BooleanStruct(Character.isWhitespace(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsWordChar())) {
                    Character one_ = ((CharStruct) _args[0]).getChar();
                    _res.setResult(new BooleanStruct(StringList.isWordChar(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasToLowerCase())) {
                    Character one_ = ((CharStruct) _args[0]).getChar();
                    _res.setResult(new CharStruct(Character.toLowerCase(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasToUpperCase())) {
                    Character one_ = ((CharStruct) _args[0]).getChar();
                    _res.setResult(new CharStruct(Character.toUpperCase(one_)));
                    return;
                }
            }
            CharStruct ch_ = (CharStruct) _struct;
            if (StringList.quickEq(name_, lgNames_.getAliasCharValue())) {
                _res.setResult(new CharStruct(ch_.getChar()));
                return;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasCompareTo())) {
                Character one_ = ch_.getChar();
                Character two_ = ((CharStruct) _args[0]).getChar();
                _res.setResult(new IntStruct(one_.compareTo(two_)));
                return;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasEquals())) {
                Character one_ = ch_.getChar();
                Character two_ = ((CharStruct) _args[0]).getChar();
                _res.setResult(new BooleanStruct(one_.charValue() == two_.charValue()));
                return;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasToString())) {
                if (list_.isEmpty()) {
                    Character one_ = ch_.getChar();
                    _res.setResult(new StringStruct(Character.toString(one_)));
                } else {
                    Character one_ = ((CharStruct) _args[0]).getChar();
                    _res.setResult(new StringStruct(Character.toString(one_)));
                }
                return;
            }
            return;
        }
        if (PrimitiveTypeUtil.isPureNumberClass(new ClassArgumentMatching(type_), lgNames_)) {
            NumberStruct instance_ = (NumberStruct) _struct;
            if (StringList.quickEq(name_, lgNames_.getAliasCompare())) {
                Number one_ = ((NumberStruct) _args[0]).getInstance();
                Number two_ = ((NumberStruct) _args[1]).getInstance();
                _res.setResult(new IntStruct(Numbers.compare(one_, two_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasCompareTo())) {
                Number one_ = instance_.getInstance();
                Number two_ = ((NumberStruct) _args[0]).getInstance();
                _res.setResult(new IntStruct(Numbers.compare(one_, two_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasEquals())) {
                Number one_ = instance_.getInstance();
                Number two_ = ((NumberStruct) _args[0]).getInstance();
                _res.setResult(new BooleanStruct(Numbers.eq(one_, two_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasByteValue())) {
                Number one_ = instance_.getInstance();
                _res.setResult(new ByteStruct(one_.byteValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasShortValue())) {
                Number one_ = instance_.getInstance();
                _res.setResult(new ShortStruct(one_.shortValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasIntValue())) {
                Number one_ = instance_.getInstance();
                _res.setResult(new IntStruct(one_.intValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasLongValue())) {
                Number one_ = instance_.getInstance();
                _res.setResult(new LongStruct(one_.longValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasFloatValue())) {
                Number one_ = instance_.getInstance();
                _res.setResult(new FloatStruct(one_.floatValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasDoubleValue())) {
                Number one_ = instance_.getInstance();
                _res.setResult(new DoubleStruct(one_.doubleValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasToString()) && list_.isEmpty()) {
                Number one_ = instance_.getInstance();
                _res.setResult(new StringStruct(Numbers.toString(one_)));
            } else {
                String byteType_ = lgNames_.getAliasByte();
                String shortType_ = lgNames_.getAliasShort();
                String intType_ = lgNames_.getAliasInteger();
                String longType_ = lgNames_.getAliasLong();
                String floatType_ = lgNames_.getAliasFloat();
                if (StringList.quickEq(type_, byteType_)) {
                    if (StringList.quickEq(name_, lgNames_.getAliasValueOf()) && !StringList.quickEq(list_.first(), stringType_)) {
                        Byte one_ = ((NumberStruct) _args[0]).getByte();
                        _res.setResult(new ByteStruct(one_));
                    } else if (StringList.quickEq(name_, lgNames_.getAliasToString())) {
                        Byte one_ = ((NumberStruct) _args[0]).getByte();
                        _res.setResult(new StringStruct(Integer.toString(one_)));
                    } else{
                        String one_ = ((CharSequenceStruct) _args[0]).getInstance().toString();
                        Long lg_;
                        int radix_ = DEFAULT_RADIX;
                        if (list_.size() != 1) {
                            radix_ = ((NumberStruct) _args[1]).getInstance().intValue();
                        }
                        lg_ = LgNames.parseLong(one_, radix_);
                        if (lg_ == null || lg_ < Byte.MIN_VALUE || lg_ > Byte.MAX_VALUE) {
                            _res.setError(lgNames_.getAliasNbFormat());
                        } else {
                            _res.setResult(new ByteStruct(lg_.byteValue()));
                        }
                    }
                } else if (StringList.quickEq(type_, shortType_)) {
                    if (StringList.quickEq(name_, lgNames_.getAliasValueOf()) && !StringList.quickEq(list_.first(), stringType_)) {
                        Short one_ = ((NumberStruct) _args[0]).getShort();
                        _res.setResult(new ShortStruct(one_));
                    } else if (StringList.quickEq(name_, lgNames_.getAliasToString())) {
                        Short one_ = ((NumberStruct) _args[0]).getShort();
                        _res.setResult(new StringStruct(Integer.toString(one_)));
                    } else {
                        String one_ = ((CharSequenceStruct) _args[0]).getInstance().toString();
                        Long lg_;
                        int radix_ = DEFAULT_RADIX;
                        if (list_.size() != 1) {
                            radix_ = ((NumberStruct) _args[1]).getInstance().intValue();
                        }
                        lg_ = LgNames.parseLong(one_, radix_);
                        if (lg_ == null || lg_ < Short.MIN_VALUE || lg_ > Short.MAX_VALUE) {
                            _res.setError(lgNames_.getAliasNbFormat());
                        } else {
                            _res.setResult(new ShortStruct(lg_.shortValue()));
                        }
                    }
                } else if (StringList.quickEq(type_, intType_)) {
                    if (StringList.quickEq(name_, lgNames_.getAliasValueOf()) && !StringList.quickEq(list_.first(), stringType_)) {
                        Integer one_ = ((NumberStruct) _args[0]).getInstance().intValue();
                        _res.setResult(new IntStruct(one_));
                    } else if (StringList.quickEq(name_, lgNames_.getAliasToString())) {
                        Integer one_ = ((NumberStruct) _args[0]).getInstance().intValue();
                        _res.setResult(new StringStruct(Integer.toString(one_)));
                    } else {
                        String one_ = ((CharSequenceStruct) _args[0]).getInstance().toString();
                        Long lg_;
                        int radix_ = DEFAULT_RADIX;
                        if (list_.size() != 1) {
                            radix_ = ((NumberStruct) _args[1]).getInstance().intValue();
                        }
                        lg_ = LgNames.parseLong(one_, radix_);
                        if (lg_ == null || lg_ < Integer.MIN_VALUE || lg_ > Integer.MAX_VALUE) {
                            _res.setError(lgNames_.getAliasNbFormat());
                        } else {
                            _res.setResult(new IntStruct(lg_.intValue()));
                        }
                    }
                } else if (StringList.quickEq(type_, longType_)) {
                    if (StringList.quickEq(name_, lgNames_.getAliasValueOf()) && !StringList.quickEq(list_.first(), stringType_)) {
                        Long one_ = ((NumberStruct) _args[0]).getInstance().longValue();
                        _res.setResult(new LongStruct(one_));
                    } else if (StringList.quickEq(name_, lgNames_.getAliasToString())) {
                        Long one_ = ((NumberStruct) _args[0]).getInstance().longValue();
                        _res.setResult(new StringStruct(Long.toString(one_)));
                    } else {
                        String one_ = ((CharSequenceStruct) _args[0]).getInstance().toString();
                        Long lg_;
                        int radix_ = DEFAULT_RADIX;
                        if (list_.size() != 1) {
                            radix_ = ((NumberStruct) _args[1]).getInstance().intValue();
                        }
                        lg_ = LgNames.parseLong(one_, radix_);
                        if (lg_ == null) {
                            _res.setError(lgNames_.getAliasNbFormat());
                        } else {
                            _res.setResult(new LongStruct(lg_));
                        }
                    }
                } else if (StringList.quickEq(type_, floatType_)) {
                    if (StringList.quickEq(name_, lgNames_.getAliasValueOf()) && !StringList.quickEq(list_.first(), stringType_)) {
                        Float one_ = ((NumberStruct) _args[0]).getFloat();
                        _res.setResult(new FloatStruct(one_));
                    } else if (StringList.quickEq(name_, lgNames_.getAliasIsNan())) {
                        Float one_;
                        if (list_.isEmpty()) {
                            one_ = instance_.getFloat();
                        } else {
                            one_ = ((NumberStruct) _args[0]).getFloat();
                        }
                        _res.setResult(new BooleanStruct(Double.isNaN(one_)));
                    } else if (StringList.quickEq(name_, lgNames_.getAliasIsInfinite())) {
                        Float one_;
                        if (list_.isEmpty()) {
                            one_ = instance_.getFloat();
                        } else {
                            one_ = ((NumberStruct) _args[0]).getFloat();
                        }
                        _res.setResult(new BooleanStruct(Double.isInfinite(one_)));
                    } else if (StringList.quickEq(name_, lgNames_.getAliasToString())) {
                        Float one_ = ((NumberStruct) _args[0]).getFloat();
                        _res.setResult(new StringStruct(Float.toString(one_)));
                    } else {
                        String one_ = ((CharSequenceStruct) _args[0]).getInstance().toString();
                        boolean valid_ = true;
                        NumberInfos infos_ = LgNames.trySplitDouble(one_);
                        if (infos_ == null) {
                            valid_ = false;
                        }
                        Double d_ = null;
                        if (valid_) {
                            d_ = LgNames.parseDouble(infos_);
                            double abs_ = Math.abs(d_);
                            if (abs_ > Float.MAX_VALUE) {
                                valid_ = false;
                            }
                        }
                        if (!valid_) {
                            _res.setError(lgNames_.getAliasNbFormat());
                        } else {
                            _res.setResult(new FloatStruct(d_.floatValue()));
                        }
                    }
                } else {
                    if (StringList.quickEq(name_, lgNames_.getAliasValueOf()) && !StringList.quickEq(list_.first(), stringType_)) {
                        Double one_ = ((NumberStruct) _args[0]).getDouble();
                        _res.setResult(new DoubleStruct(one_));
                    } else if (StringList.quickEq(name_, lgNames_.getAliasIsNan())) {
                        Double one_;
                        if (list_.isEmpty()) {
                            one_ = instance_.getDouble();
                        } else {
                            one_ = ((NumberStruct) _args[0]).getDouble();
                        }
                        _res.setResult(new BooleanStruct(Double.isNaN(one_)));
                    } else if (StringList.quickEq(name_, lgNames_.getAliasIsInfinite())) {
                        Double one_;
                        if (list_.isEmpty()) {
                            one_ = instance_.getDouble();
                        } else {
                            one_ = ((NumberStruct) _args[0]).getDouble();
                        }
                        _res.setResult(new BooleanStruct(Double.isInfinite(one_)));
                    } else if (StringList.quickEq(name_, lgNames_.getAliasToString())) {
                        Double one_ = ((NumberStruct) _args[0]).getDouble();
                        _res.setResult(new StringStruct(Double.toString(one_)));
                    } else {
                        String one_ = ((CharSequenceStruct) _args[0]).getInstance().toString();
                        boolean valid_ = true;
                        NumberInfos infos_ = LgNames.trySplitDouble(one_);
                        if (infos_ == null) {
                            valid_ = false;
                        }
                        Double d_ = null;
                        if (valid_) {
                            d_ = LgNames.parseDouble(infos_);
                        }
                        if (!valid_) {
                            _res.setError(lgNames_.getAliasNbFormat());
                        } else {
                            _res.setResult(new DoubleStruct(d_));
                        }
                    }
                }
            }
            return;
        }
        if (StringList.quickEq(type_, nbType_)) {
            NumberStruct instance_ = (NumberStruct) _struct;
            if (StringList.quickEq(name_, lgNames_.getAliasCompare())) {
                Number one_ = ((NumberStruct) _args[0]).getInstance();
                Number two_ = ((NumberStruct) _args[1]).getInstance();
                _res.setResult(new IntStruct(Numbers.compare(one_, two_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasCompareTo())) {
                Number one_ = instance_.getInstance();
                Number two_ = ((NumberStruct) _args[0]).getInstance();
                _res.setResult(new IntStruct(Numbers.compare(one_, two_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasEquals())) {
                Number one_ = instance_.getInstance();
                Number two_ = ((NumberStruct) _args[0]).getInstance();
                _res.setResult(new BooleanStruct(Numbers.eq(one_, two_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasByteValue())) {
                Number one_ = instance_.getInstance();
                _res.setResult(new ByteStruct(one_.byteValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasShortValue())) {
                Number one_ = instance_.getInstance();
                _res.setResult(new ShortStruct(one_.shortValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasIntValue())) {
                Number one_ = instance_.getInstance();
                _res.setResult(new IntStruct(one_.intValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasLongValue())) {
                Number one_ = instance_.getInstance();
                _res.setResult(new LongStruct(one_.longValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasFloatValue())) {
                Number one_ = instance_.getInstance();
                _res.setResult(new FloatStruct(one_.floatValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasDoubleValue())) {
                Number one_ = instance_.getInstance();
                _res.setResult(new DoubleStruct(one_.doubleValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasToString()) && list_.isEmpty()) {
                Number one_ = instance_.getInstance();
                _res.setResult(new StringStruct(Numbers.toString(one_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasToString())) {
                Number one_ = ((NumberStruct) _args[0]).getInstance();
                if (one_ == null) {
                    _res.setResult(new StringStruct(""));
                } else {
                    _res.setResult(new StringStruct(Numbers.toString(one_)));
                }
            }
        }
    }
    public static BooleanStruct quickCalculateLowerNb(Struct _a, Struct _b) {
        Number a_ = ((NumberStruct) _a).getInstance();
        Number b_ = ((NumberStruct) _b).getInstance();
        return new BooleanStruct(Numbers.lt(a_, b_));
    }

    public static BooleanStruct quickCalculateGreaterNb(Struct _a, Struct _b) {
        Number a_ = ((NumberStruct) _a).getInstance();
        Number b_ = ((NumberStruct) _b).getInstance();
        return new BooleanStruct(Numbers.gt(a_, b_));
    }
    public static BooleanStruct quickCalculateLowerStr(Struct _a, Struct _b) {
        String first_ = ((CharSequenceStruct)_a).getInstance().toString();
        String second_ = ((CharSequenceStruct)_b).getInstance().toString();
        return new BooleanStruct(first_.compareTo(second_) < 0);
    }

    public static BooleanStruct quickCalculateGreaterStr(Struct _a, Struct _b) {
        String first_ = ((CharSequenceStruct)_a).getInstance().toString();
        String second_ = ((CharSequenceStruct)_b).getInstance().toString();
        return new BooleanStruct(first_.compareTo(second_) > 0);
    }
    public static NumberStruct idNumber(NumberStruct _a, Analyzable _an,ClassArgumentMatching _order) {
        LgNames stds_ = _an.getStandards();
        return (NumberStruct) PrimitiveTypeUtil.convertObject(_order, _a, stds_);
    }
    public static NumberStruct negBinNumber(NumberStruct _a, Analyzable _an,ClassArgumentMatching _order) {
        String intPrim_ = _an.getStandards().getAliasPrimInteger();
        if (_order.matchClass(intPrim_)) {
            int left_ = _a.getInt();
            boolean[] bits_ = LgNames.toBits(left_);
            int len_ = bits_.length;
            for (int i = 0; i<len_; i++) {
                bits_[i] = !bits_[i];
            }
            return new IntStruct(LgNames.toInt(bits_));
        }
        long left_ = _a.getLong();
        boolean[] bits_ = LgNames.toBits(left_);
        int len_ = bits_.length;
        for (int i = 0; i<len_; i++) {
            bits_[i] = !bits_[i];
        }
        return new LongStruct(LgNames.toLong(bits_));
    }
    public static NumberStruct calculateSum(NumberStruct _a, NumberStruct _b, Analyzable _an,ClassArgumentMatching _order) {
        int order_ = PrimitiveTypeUtil.getOrderClass(_order, _an);
        Number nb_;
        String longPrim_ = _an.getStandards().getAliasPrimLong();
        String intPrim_ = _an.getStandards().getAliasPrimInteger();
        String floatPrim_ = _an.getStandards().getAliasPrimFloat();
        if (order_ <= PrimitiveTypeUtil.getOrderClass(longPrim_, _an)) {
            long left_ = _a.getLong();
            long right_ = _b.getLong();
            nb_ = left_ + right_;
            if (order_ == PrimitiveTypeUtil.getOrderClass(intPrim_, _an)) {
                return new IntStruct(nb_.intValue());
            }
            return new LongStruct(nb_.longValue());
        }
        double left_ = _a.getDouble();
        double right_ = _b.getDouble();
        nb_ = left_ + right_;
        if (order_ == PrimitiveTypeUtil.getOrderClass(floatPrim_, _an)) {
            return new FloatStruct(nb_.floatValue());
        }
        return new DoubleStruct(nb_.doubleValue());
    }

    public static NumberStruct opposite(NumberStruct _a, Analyzable _an,ClassArgumentMatching _order) {
        Number b_ = _a.getInstance();
        int order_ = PrimitiveTypeUtil.getOrderClass(_order, _an);
        String longPrim_ = _an.getStandards().getAliasPrimLong();
        Struct tmp_;
        if (order_ <= PrimitiveTypeUtil.getOrderClass(longPrim_, _an)) {
            tmp_ = new LongStruct(-b_.longValue());
        } else {
            tmp_ = new DoubleStruct(-b_.doubleValue());
        }
        LgNames stds_ = _an.getStandards();
        tmp_ = PrimitiveTypeUtil.convertObject(_order, tmp_, stds_);
        return (NumberStruct) tmp_;
    }
    public static NumberStruct calculateDiff(NumberStruct _a, NumberStruct _b, Analyzable _an,ClassArgumentMatching _order) {
        int order_ = PrimitiveTypeUtil.getOrderClass(_order, _an);
        Number nb_;
        String longPrim_ = _an.getStandards().getAliasPrimLong();
        String intPrim_ = _an.getStandards().getAliasPrimInteger();
        String floatPrim_ = _an.getStandards().getAliasPrimFloat();
        if (order_ <= PrimitiveTypeUtil.getOrderClass(longPrim_, _an)) {
            long left_ = _a.getLong();
            long right_ = _b.getLong();
            nb_ = left_ - right_;
            if (order_ == PrimitiveTypeUtil.getOrderClass(intPrim_, _an)) {
                return new IntStruct(nb_.intValue());
            }
            return new LongStruct(nb_.longValue());
        }
        double left_ = _a.getDouble();
        double right_ = _b.getDouble();
        nb_ = left_ - right_;
        if (order_ == PrimitiveTypeUtil.getOrderClass(floatPrim_, _an)) {
            return new FloatStruct(nb_.floatValue());
        }
        return new DoubleStruct(nb_.doubleValue());
    }
    public static NumberStruct calculateMult(NumberStruct _a, NumberStruct _b, Analyzable _an,ClassArgumentMatching _order) {
        int order_ = PrimitiveTypeUtil.getOrderClass(_order, _an);
        Number nb_;
        String longPrim_ = _an.getStandards().getAliasPrimLong();
        String intPrim_ = _an.getStandards().getAliasPrimInteger();
        String floatPrim_ = _an.getStandards().getAliasPrimFloat();
        if (order_ <= PrimitiveTypeUtil.getOrderClass(longPrim_, _an)) {
            long left_ = _a.getLong();
            long right_ = _b.getLong();
            nb_ = left_ * right_;
            if (order_ == PrimitiveTypeUtil.getOrderClass(intPrim_, _an)) {
                return new IntStruct(nb_.intValue());
            }
            return new LongStruct(nb_.longValue());
        }
        double left_ = _a.getDouble();
        double right_ = _b.getDouble();
        nb_ = left_ * right_;
        if (order_ == PrimitiveTypeUtil.getOrderClass(floatPrim_, _an)) {
            return new FloatStruct(nb_.floatValue());
        }
        return new DoubleStruct(nb_.doubleValue());
    }
    private static Struct calculateDivEx(NumberStruct _a, NumberStruct _b, Analyzable _an,ClassArgumentMatching _order) {
        LgNames stds_ = _an.getStandards();
        String div_;
        div_ = stds_.getAliasDivisionZero();
        ContextEl c_ = _an.getContextEl();
        Struct res_ = calculateDiv(_a,_b, _an, _order);
        if (res_ == NullStruct.NULL_VALUE) {
            c_.setException(new ErrorStruct(c_,div_));
        }
        return res_;
    }
    public static Struct calculateDiv(NumberStruct _a, NumberStruct _b, Analyzable _an,ClassArgumentMatching _order) {
        int order_ = PrimitiveTypeUtil.getOrderClass(_order, _an);
        Number nb_;
        String longPrim_ = _an.getStandards().getAliasPrimLong();
        String intPrim_ = _an.getStandards().getAliasPrimInteger();
        String floatPrim_ = _an.getStandards().getAliasPrimFloat();
        if (order_ <= PrimitiveTypeUtil.getOrderClass(longPrim_, _an)) {
            long left_ = _a.getLong();
            long right_ = _b.getLong();
            if (right_ == 0) {
                return NullStruct.NULL_VALUE;
            }
            nb_ = left_ / right_;
            if (order_ == PrimitiveTypeUtil.getOrderClass(intPrim_, _an)) {
                return new IntStruct(nb_.intValue());
            }
            return new LongStruct(nb_.longValue());
        }
        double left_ = _a.getDouble();
        double right_ = _b.getDouble();
        nb_ = left_ / right_;
        if (order_ == PrimitiveTypeUtil.getOrderClass(floatPrim_, _an)) {
            return new FloatStruct(nb_.floatValue());
        }
        return new DoubleStruct(nb_.doubleValue());
    }
    private static Struct calculateModEx(NumberStruct _a, NumberStruct _b, Analyzable _an,ClassArgumentMatching _order) {
        LgNames stds_ = _an.getStandards();
        String div_;
        div_ = stds_.getAliasDivisionZero();
        ContextEl c_ = _an.getContextEl();
        Struct res_ = calculateMod(_a,_b, _an, _order);
        if (res_ == NullStruct.NULL_VALUE) {
            c_.setException(new ErrorStruct(c_,div_));
        }
        return res_;
    }
    public static Struct calculateMod(NumberStruct _a, NumberStruct _b, Analyzable _an,ClassArgumentMatching _order) {
        int order_ = PrimitiveTypeUtil.getOrderClass(_order, _an);
        Number nb_;
        String longPrim_ = _an.getStandards().getAliasPrimLong();
        String intPrim_ = _an.getStandards().getAliasPrimInteger();
        String floatPrim_ = _an.getStandards().getAliasPrimFloat();
        if (order_ <= PrimitiveTypeUtil.getOrderClass(longPrim_, _an)) {
            long left_ = _a.getLong();
            long right_ = _b.getLong();
            if (right_ == 0) {
                return NullStruct.NULL_VALUE;
            }
            nb_ = left_ % right_;
            if (order_ == PrimitiveTypeUtil.getOrderClass(intPrim_, _an)) {
                return new IntStruct(nb_.intValue());
            }
            return new LongStruct(nb_.longValue());
        }
        double left_ = _a.getDouble();
        double right_ = _b.getDouble();
        nb_ = left_ % right_;
        if (order_ == PrimitiveTypeUtil.getOrderClass(floatPrim_, _an)) {
            return new FloatStruct(nb_.floatValue());
        }
        return new DoubleStruct(nb_.doubleValue());
    }

    public static Struct calculateAnd(Struct _a, Struct _b, Analyzable _an,ClassArgumentMatching _order) {
        LgNames stds_ = _an.getStandards();
        String bool_ = stds_.getAliasPrimBoolean();
        String int_ = stds_.getAliasPrimInteger();
        if (_order.matchClass(bool_)) {
            Boolean left_ = ((BooleanStruct) _a).getInstance();
            Boolean right_ = ((BooleanStruct) _b).getInstance();
            return new BooleanStruct(left_ && right_);
        }
        if (_order.matchClass(int_)) {
            int left_ = ((NumberStruct)_a).getInt();
            int right_ = ((NumberStruct)_b).getInt();
            boolean[] bitsLeft_ = LgNames.toBits(left_);
            boolean[] bitsRight_ = LgNames.toBits(right_);
            int len_ = bitsLeft_.length;
            boolean[] bits_ = new boolean[len_];
            for (int i = 0; i < len_; i++) {
                bits_[i] = bitsLeft_[i] && bitsRight_[i];
            }
            int value_ = LgNames.toInt(bits_);
            return new IntStruct(value_);
        }
        long left_ = ((NumberStruct)_a).getLong();
        long right_ = ((NumberStruct)_b).getLong();
        boolean[] bitsLeft_ = LgNames.toBits(left_);
        boolean[] bitsRight_ = LgNames.toBits(right_);
        int len_ = bitsLeft_.length;
        boolean[] bits_ = new boolean[len_];
        for (int i = 0; i < len_; i++) {
            bits_[i] = bitsLeft_[i] && bitsRight_[i];
        }
        long value_ = LgNames.toLong(bits_);
        return new LongStruct(value_);
    }

    public static Struct calculateOr(Struct _a, Struct _b, Analyzable _an,ClassArgumentMatching _order) {
        LgNames stds_ = _an.getStandards();
        String bool_ = stds_.getAliasPrimBoolean();
        String int_ = stds_.getAliasPrimInteger();
        if (_order.matchClass(bool_)) {
            Boolean left_ = ((BooleanStruct) _a).getInstance();
            Boolean right_ = ((BooleanStruct) _b).getInstance();
            return new BooleanStruct(left_ || right_);
        }
        if (_order.matchClass(int_)) {
            int left_ = ((NumberStruct)_a).getInt();
            int right_ = ((NumberStruct)_b).getInt();
            boolean[] bitsLeft_ = LgNames.toBits(left_);
            boolean[] bitsRight_ = LgNames.toBits(right_);
            int len_ = bitsLeft_.length;
            boolean[] bits_ = new boolean[len_];
            for (int i = 0; i < len_; i++) {
                bits_[i] = bitsLeft_[i] || bitsRight_[i];
            }
            int value_ = LgNames.toInt(bits_);
            return new IntStruct(value_);
        }
        long left_ = ((NumberStruct)_a).getLong();
        long right_ = ((NumberStruct)_b).getLong();
        boolean[] bitsLeft_ = LgNames.toBits(left_);
        boolean[] bitsRight_ = LgNames.toBits(right_);
        int len_ = bitsLeft_.length;
        boolean[] bits_ = new boolean[len_];
        for (int i = 0; i < len_; i++) {
            bits_[i] = bitsLeft_[i] || bitsRight_[i];
        }
        long value_ = LgNames.toLong(bits_);
        return new LongStruct(value_);
    }

    public static Struct calculateXor(Struct _a, Struct _b, Analyzable _an,ClassArgumentMatching _order) {
        LgNames stds_ = _an.getStandards();
        String bool_ = stds_.getAliasPrimBoolean();
        String int_ = stds_.getAliasPrimInteger();
        if (_order.matchClass(bool_)) {
            Boolean left_ = ((BooleanStruct) _a).getInstance();
            Boolean right_ = ((BooleanStruct) _b).getInstance();
            return new BooleanStruct(left_ != right_);
        }
        if (_order.matchClass(int_)) {
            int left_ = ((NumberStruct)_a).getInt();
            int right_ = ((NumberStruct)_b).getInt();
            boolean[] bitsLeft_ = LgNames.toBits(left_);
            boolean[] bitsRight_ = LgNames.toBits(right_);
            int len_ = bitsLeft_.length;
            boolean[] bits_ = new boolean[len_];
            for (int i = 0; i < len_; i++) {
                bits_[i] = bitsLeft_[i] != bitsRight_[i];
            }
            int value_ = LgNames.toInt(bits_);
            return new IntStruct(value_);
        }
        long left_ = ((NumberStruct)_a).getLong();
        long right_ = ((NumberStruct)_b).getLong();
        boolean[] bitsLeft_ = LgNames.toBits(left_);
        boolean[] bitsRight_ = LgNames.toBits(right_);
        int len_ = bitsLeft_.length;
        boolean[] bits_ = new boolean[len_];
        for (int i = 0; i < len_; i++) {
            bits_[i] = bitsLeft_[i] != bitsRight_[i];
        }
        long value_ = LgNames.toLong(bits_);
        return new LongStruct(value_);
    }
    public static NumberStruct calculateShiftLeft(NumberStruct _a, NumberStruct _b, Analyzable _an,ClassArgumentMatching _order) {
        LgNames stds_ = _an.getStandards();
        String int_ = stds_.getAliasPrimInteger();
        if (_order.matchClass(int_)) {
            int left_ = _a.getInt();
            int right_ = _b.getInt();
            boolean[] bitsRight_ = LgNames.toBits(right_);
            int value_ = LgNames.toUnsignedInt(bitsRight_,5);
            int power_ = 1;
            for (int i = 0; i< value_; i++) {
                power_ *= 2;
            }
            return new IntStruct(left_*power_);
        }
        long left_ = _a.getLong();
        long right_ = _b.getLong();
        boolean[] bitsRight_ = LgNames.toBits(right_);
        long value_ = LgNames.toUnsignedLong(bitsRight_,6);
        long power_ = 1;
        for (int i = 0; i< value_; i++) {
            power_ *= 2;
        }
        return new LongStruct(left_*power_);
    }
    public static NumberStruct calculateShiftRight(NumberStruct _a, NumberStruct _b, Analyzable _an,ClassArgumentMatching _order) {
        LgNames stds_ = _an.getStandards();
        String int_ = stds_.getAliasPrimInteger();
        if (_order.matchClass(int_)) {
            int left_ = _a.getInt();
            int right_ = _b.getInt();
            boolean[] bitsRight_ = LgNames.toBits(right_);
            int value_ = LgNames.toUnsignedInt(bitsRight_,5);
            int power_ = 1;
            for (int i = 0; i< value_; i++) {
                power_ *= 2;
            }
            return new IntStruct(Numbers.quot(left_, power_));
        }
        long left_ = _a.getLong();
        long right_ = _b.getLong();
        boolean[] bitsRight_ = LgNames.toBits(right_);
        long value_ = LgNames.toUnsignedLong(bitsRight_,6);
        long power_ = 1;
        for (int i = 0; i< value_; i++) {
            power_ *= 2;
        }
        return new LongStruct(Numbers.quot(left_, power_));
    }
    public static NumberStruct calculateBitShiftLeft(NumberStruct _a, NumberStruct _b, Analyzable _an,ClassArgumentMatching _order) {
        LgNames stds_ = _an.getStandards();
        String int_ = stds_.getAliasPrimInteger();
        if (_order.matchClass(int_)) {
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
            return new IntStruct(LgNames.toInt(bitsLeft_));
        }
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
        return new LongStruct(LgNames.toLong(bitsLeft_));
    }
    public static NumberStruct calculateBitShiftRight(NumberStruct _a, NumberStruct _b, Analyzable _an,ClassArgumentMatching _order) {
        LgNames stds_ = _an.getStandards();
        String int_ = stds_.getAliasPrimInteger();
        if (_order.matchClass(int_)) {
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
            return new IntStruct(LgNames.toInt(bitsLeft_));
        }
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
        return new LongStruct(LgNames.toLong(bitsLeft_));
    }
    public static NumberStruct calculateRotateLeft(NumberStruct _a, NumberStruct _b, Analyzable _an,ClassArgumentMatching _order) {
        LgNames stds_ = _an.getStandards();
        String int_ = stds_.getAliasPrimInteger();
        if (_order.matchClass(int_)) {
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
            return new IntStruct(LgNames.toInt(bitsLeft_));
        }
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
        return new LongStruct(LgNames.toLong(bitsLeft_));
    }
    public static NumberStruct calculateRotateRight(NumberStruct _a, NumberStruct _b, Analyzable _an,ClassArgumentMatching _order) {
        LgNames stds_ = _an.getStandards();
        String int_ = stds_.getAliasPrimInteger();
        if (_order.matchClass(int_)) {
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
            return new IntStruct(LgNames.toInt(bitsLeft_));
        }
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
        return new LongStruct(LgNames.toLong(bitsLeft_));
    }
    private double getDouble() {
        return getInstance().doubleValue();
    }
    private float getFloat() {
        return getInstance().floatValue();
    }
    private long getLong() {
        return getInstance().longValue();
    }

    private int getInt() {
        return getInstance().intValue();
    }

    private short getShort() {
        return getInstance().shortValue();
    }
    private byte getByte() {
        return getInstance().byteValue();
    }

    @Override
    public final boolean sameReference(Struct _other) {
        if (!(_other instanceof NumberStruct)) {
            return false;
        }
        NumberStruct other_ = (NumberStruct) _other;
        return Numbers.eq(getInstance(), other_.getInstance());
    }

    @Override
    public StringStruct getDisplayedString(Analyzable _an) {
        return new StringStruct(Numbers.toString(getInstance()));
    }
    @Override
    public StringStruct exportValue() {
        return new StringStruct(Numbers.toString(getInstance()));
    }
    @Override
    public abstract Number getInstance();

}
