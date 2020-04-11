package code.expressionlanguage.structs;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.instr.NumberInfos;
import code.expressionlanguage.opers.exec.ExecCatOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.stds.*;
import code.util.CustList;
import code.util.*;
import code.util.StringList;
import code.util.comparators.ComparatorBoolean;

public abstract class NumberStruct implements DisplayableStruct, ExportableStringStruct {
    private static final int DEFAULT_RADIX = 10;
    @Override
    public final Struct getParent() {
        return NullStruct.NULL_VALUE;
    }
    public static void calculateOperator(Analyzable _cont, ResultErrorStd _res, ClassArgumentMatching _order, String _op, boolean _catString, Struct... _args) {
        if (_catString) {
            catenize(_cont, _res, _args);
            return;
        }
        String op_ = _op.substring(0, _op.length() - 1);
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
        if (StringList.quickEq(op_, ">>>>")) {
            _res.setResult(calculateRotateRight((NumberStruct)_args[0], (NumberStruct)_args[1], _cont, _order));
            return;
        }
        if (StringList.quickEq(op_, "&&")) {
            BooleanStruct b_ = (BooleanStruct) _args[0];
            if (!b_.getInstance()) {
                _res.setResult(b_);
                return;
            }
            _res.setResult(_args[1]);
            return;
        }
        BooleanStruct b_ = (BooleanStruct) _args[0];
        if (b_.getInstance()) {
            _res.setResult(b_);
            return;
        }
        _res.setResult(_args[1]);
    }

  private static void catenize(Analyzable _cont, ResultErrorStd _res, Struct... _args) {
      Argument arg_ = ExecCatOperation.localSumDiff(new Argument(_args[0]), new Argument(_args[1]), _cont.getContextEl());
      _res.setResult(arg_.getStruct());
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
        if (StringList.quickEq(type_, booleanType_)) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                String one_ = ((CharSequenceStruct) _args[0]).toStringInstance();
                if (StringList.quickEq(one_, lgNames_.getTrueString())) {
                    _res.setResult(BooleanStruct.of(true));
                } else {
                    _res.setResult(BooleanStruct.of(false));
                }
            } else {
                boolean one_ = ((BooleanStruct) _args[0]).getInstance();
                _res.setResult(BooleanStruct.of(one_));
            }
        } else if (StringList.quickEq(type_, charType_)) {
            _res.setResult(_args[0]);
        } else if (StringList.quickEq(type_, byteType_)) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                parseByte(_res,list_,lgNames_,_args,true);
            } else {
                Byte one_ = ((NumberStruct) _args[0]).getByte();
                _res.setResult(new ByteStruct(one_));
            }
        } else if (StringList.quickEq(type_, shortType_)) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                parseShort(_res,list_,lgNames_,_args,true);
            } else {
                Short one_ = ((NumberStruct) _args[0]).getShort();
                _res.setResult(new ShortStruct(one_));
            }
        } else if (StringList.quickEq(type_, intType_)) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                parseInt(_res,list_,lgNames_,_args,true);
            } else {
                Integer one_ = ((NumberStruct) _args[0]).getInt();
                _res.setResult(new IntStruct(one_));
            }
        } else if (StringList.quickEq(type_, longType_)) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                parseLong(_res,list_,lgNames_,_args,true);
            } else {
                Long one_ = ((NumberStruct) _args[0]).getLong();
                _res.setResult(new LongStruct(one_));
            }
        } else if (StringList.quickEq(type_, floatType_)) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                parseFloat(_res,lgNames_,_args[0],true);
            } else {
                Float one_ = ((NumberStruct) _args[0]).getFloat();
                _res.setResult(new FloatStruct(one_));
            }
        } else {
            if (StringList.quickEq(list_.first(), stringType_)) {
                parseDouble(_res,lgNames_,_args[0],true);
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
        String byteType_ = lgNames_.getAliasByte();
        String shortType_ = lgNames_.getAliasShort();
        String intType_ = lgNames_.getAliasInteger();
        String longType_ = lgNames_.getAliasLong();
        String floatType_ = lgNames_.getAliasFloat();
        String doubleType_ = lgNames_.getAliasDouble();
        String nbType_ = lgNames_.getAliasNumber();
        String booleanPrimType_ = lgNames_.getAliasPrimBoolean();
        if (StringList.quickEq(type_, booleanType_)) {
            if (StringList.quickEq(name_, lgNames_.getAliasBooleanValue())) {
                BooleanStruct instance_ = (BooleanStruct) _struct;
                _res.setResult(BooleanStruct.of(instance_.getInstance()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasCompare())) {
                _res.setResult(new IntStruct(ComparatorBoolean.cmp(((BooleanStruct) _args[0]).getInstance(),((BooleanStruct) _args[1]).getInstance())));
            } else if (StringList.quickEq(name_, lgNames_.getAliasCompareTo())) {
                BooleanStruct instance_ = (BooleanStruct) _struct;
                _res.setResult(new IntStruct(ComparatorBoolean.cmp(instance_.getInstance(),((BooleanStruct) _args[0]).getInstance())));
            } else if (StringList.quickEq(name_, lgNames_.getAliasEquals())) {
                BooleanStruct instance_ = (BooleanStruct) _struct;
                _res.setResult(BooleanStruct.of(instance_.sameReference(_args[0])));
            } else if (StringList.quickEq(name_, lgNames_.getAliasParseBoolean())) {
                if (StringList.quickEq(((DisplayableStruct) _args[0]).getDisplayedString(_cont).getInstance(),lgNames_.getTrueString())) {
                    _res.setResult(BooleanStruct.of(true));
                } else {
                    _res.setResult(BooleanStruct.of(false));
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                if (!list_.isEmpty()) {
                    _res.setResult(((BooleanStruct) _args[0]).getDisplayedString(_cont));
                } else {
                    BooleanStruct instance_ = (BooleanStruct) _struct;
                    _res.setResult(instance_.getDisplayedString(_cont));
                }
            } else {
                if (StringList.quickEq(list_.first(), booleanPrimType_)) {
                    _res.setResult(_args[0]);
                } else {
                    if (StringList.quickEq(((DisplayableStruct) _args[0]).getDisplayedString(_cont).getInstance(),lgNames_.getTrueString())) {
                        _res.setResult(BooleanStruct.of(true));
                    } else {
                        _res.setResult(BooleanStruct.of(false));
                    }
                }
            }
        } else if (StringList.quickEq(type_, charType_)) {
            if (_method.getConstraints().isStaticMethod()) {
                if (StringList.quickEq(name_, lgNames_.getAliasCompare())) {
                    char one_ = ((CharStruct) _args[0]).getChar();
                    char two_ = ((CharStruct) _args[1]).getChar();
                    _res.setResult(new IntStruct(Numbers.compareLg(one_,two_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasDigit())) {
                    char one_ = ((CharStruct) _args[0]).getChar();
                    Integer two_ = ((NumberStruct) _args[1]).intStruct();
                    _res.setResult(new IntStruct(Character.digit(one_, two_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasForDigit())) {
                    Integer one_ = ((NumberStruct) _args[0]).intStruct();
                    Integer two_ = ((NumberStruct) _args[1]).intStruct();
                    _res.setResult(new CharStruct(Character.forDigit(one_, two_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasGetDirectionality())) {
                    char one_ = ((CharStruct) _args[0]).getChar();
                    _res.setResult(new ByteStruct(Character.getDirectionality(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasGetType())) {
                    char one_ = ((CharStruct) _args[0]).getChar();
                    _res.setResult(new IntStruct(Character.getType(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsDigit())) {
                    char one_ = ((CharStruct) _args[0]).getChar();
                    _res.setResult(BooleanStruct.of(ContextEl.isDigit(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsLetter())) {
                    char one_ = ((CharStruct) _args[0]).getChar();
                    _res.setResult(BooleanStruct.of(Character.isLetter(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsLetterOrDigit())) {
                    char one_ = ((CharStruct) _args[0]).getChar();
                    _res.setResult(BooleanStruct.of(Character.isLetterOrDigit(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsLowerCase())) {
                    char one_ = ((CharStruct) _args[0]).getChar();
                    _res.setResult(BooleanStruct.of(Character.isLowerCase(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsUpperCase())) {
                    char one_ = ((CharStruct) _args[0]).getChar();
                    _res.setResult(BooleanStruct.of(Character.isUpperCase(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsSpace())) {
                    char one_ = ((CharStruct) _args[0]).getChar();
                    _res.setResult(BooleanStruct.of(Character.isWhitespace(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsWhitespace())) {
                    char one_ = ((CharStruct) _args[0]).getChar();
                    _res.setResult(BooleanStruct.of(Character.isWhitespace(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsWordChar())) {
                    char one_ = ((CharStruct) _args[0]).getChar();
                    _res.setResult(BooleanStruct.of(StringList.isWordChar(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasToLowerCase())) {
                    char one_ = ((CharStruct) _args[0]).getChar();
                    _res.setResult(new CharStruct(Character.toLowerCase(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasToUpperCase())) {
                    char one_ = ((CharStruct) _args[0]).getChar();
                    _res.setResult(new CharStruct(Character.toUpperCase(one_)));
                    return;
                }
                char one_ = ((CharStruct) _args[0]).getChar();
                _res.setResult(new StringStruct(Character.toString(one_)));
                return;
            }
            CharStruct ch_ = (CharStruct) _struct;
            if (StringList.quickEq(name_, lgNames_.getAliasCharValue())) {
                _res.setResult(new CharStruct(ch_.getChar()));
                return;
            }
            if (!(_args[0] instanceof CharStruct)) {
                _res.setError(lgNames_.getAliasNullPe());
                return;
            }
            char one_ = ch_.getChar();
            char two_ = ((CharStruct) _args[0]).getChar();
            _res.setResult(new IntStruct(Numbers.compareLg(one_,two_)));
            return;
        }
        if (StringList.quickEq(type_, byteType_)
                || StringList.quickEq(type_, shortType_)
                || StringList.quickEq(type_, intType_)
                || StringList.quickEq(type_, longType_)
                || StringList.quickEq(type_, floatType_)
                || StringList.quickEq(type_, doubleType_)) {
            if (StringList.quickEq(name_, lgNames_.getAliasCompare())) {
                _res.setResult(new IntStruct(compare((NumberStruct)_args[0],(NumberStruct)_args[1])));
            } else if (StringList.quickEq(name_, lgNames_.getAliasCompareTo())) {
                NumberStruct instance_ = (NumberStruct) _struct;
                _res.setResult(new IntStruct(compare(instance_,(NumberStruct) _args[0])));
            } else {
                if (StringList.quickEq(type_, byteType_)) {
                    if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                        Byte one_ = ((NumberStruct) _args[0]).getByte();
                        _res.setResult(new StringStruct(Integer.toString(one_)));
                    } else{
                        boolean exc_ = StringList.quickEq(name_, lgNames_.getAliasParseByte());
                        parseByte(_res, list_, lgNames_, _args,exc_);
                    }
                } else if (StringList.quickEq(type_, shortType_)) {
                    if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                        Short one_ = ((NumberStruct) _args[0]).getShort();
                        _res.setResult(new StringStruct(Integer.toString(one_)));
                    } else {
                        boolean exc_ = StringList.quickEq(name_, lgNames_.getAliasParseShort());
                        parseShort(_res, list_, lgNames_, _args,exc_);
                    }
                } else if (StringList.quickEq(type_, intType_)) {
                    if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                        Integer one_ = ((NumberStruct) _args[0]).intStruct();
                        _res.setResult(new StringStruct(Integer.toString(one_)));
                    } else {
                        boolean exc_ = StringList.quickEq(name_, lgNames_.getAliasParseInt());
                        parseInt(_res, list_, lgNames_, _args,exc_);
                    }
                } else if (StringList.quickEq(type_, longType_)) {
                    if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                        Long one_ = ((NumberStruct) _args[0]).longStruct();
                        _res.setResult(new StringStruct(Long.toString(one_)));
                    } else {
                        boolean exc_ = StringList.quickEq(name_, lgNames_.getAliasParseLong());
                        parseLong(_res, list_, lgNames_, _args,exc_);
                    }
                } else if (StringList.quickEq(type_, floatType_)) {
                    if (StringList.quickEq(name_, lgNames_.getAliasIsNan())) {
                        Float one_;
                        one_ = ((NumberStruct) _args[0]).getFloat();
                        _res.setResult(BooleanStruct.of(Double.isNaN(one_)));
                    } else if (StringList.quickEq(name_, lgNames_.getAliasIsInfinite())) {
                        Float one_;
                        one_ = ((NumberStruct) _args[0]).getFloat();
                        _res.setResult(BooleanStruct.of(Double.isInfinite(one_)));
                    } else if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                        Float one_ = ((NumberStruct) _args[0]).getFloat();
                        _res.setResult(new StringStruct(Float.toString(one_)));
                    } else {
                        boolean exc_ = StringList.quickEq(name_, lgNames_.getAliasParseFloat());
                        parseFloat(_res, lgNames_, _args[0],exc_);
                    }
                } else {
                    if (StringList.quickEq(name_, lgNames_.getAliasIsNan())) {
                        Double one_;
                        if (list_.isEmpty()) {
                            NumberStruct instance_ = (NumberStruct) _struct;
                            one_ = instance_.getDouble();
                        } else {
                            one_ = ((NumberStruct) _args[0]).getDouble();
                        }
                        _res.setResult(BooleanStruct.of(Double.isNaN(one_)));
                    } else if (StringList.quickEq(name_, lgNames_.getAliasIsInfinite())) {
                        Double one_;
                        if (list_.isEmpty()) {
                            NumberStruct instance_ = (NumberStruct) _struct;
                            one_ = instance_.getDouble();
                        } else {
                            one_ = ((NumberStruct) _args[0]).getDouble();
                        }
                        _res.setResult(BooleanStruct.of(Double.isInfinite(one_)));
                    } else if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                        Double one_ = ((NumberStruct) _args[0]).getDouble();
                        _res.setResult(new StringStruct(Double.toString(one_)));
                    } else {
                        boolean exc_ = StringList.quickEq(name_, lgNames_.getAliasParseDouble());
                        parseDouble(_res, lgNames_, _args[0],exc_);
                    }
                }
            }
            return;
        }
        if (StringList.quickEq(type_, nbType_)) {
            if (StringList.quickEq(name_, lgNames_.getAliasCompare())) {
                if (!(_args[0] instanceof  NumberStruct)) {
                    _res.setError(lgNames_.getAliasNullPe());
                    return;
                }
                if (!(_args[1] instanceof  NumberStruct)) {
                    _res.setError(lgNames_.getAliasNullPe());
                    return;
                }
                _res.setResult(new IntStruct(compareGene((NumberStruct) _args[0], (NumberStruct) _args[1])));
            } else if (StringList.quickEq(name_, lgNames_.getAliasCompareTo())) {
                NumberStruct instance_ = (NumberStruct) _struct;
                if (!(_args[0] instanceof  NumberStruct)) {
                    _res.setError(lgNames_.getAliasNullPe());
                    return;
                }
                _res.setResult(new IntStruct(compareGene(instance_, (NumberStruct) _args[0])));
            } else if (StringList.quickEq(name_, lgNames_.getAliasEquals())) {
                _res.setResult(BooleanStruct.of(_struct.sameReference(_args[0])));
            } else if (StringList.quickEq(name_, lgNames_.getAliasByteValue())) {
                NumberStruct instance_ = (NumberStruct) _struct;
                _res.setResult(new ByteStruct(instance_.byteStruct()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasShortValue())) {
                NumberStruct instance_ = (NumberStruct) _struct;
                _res.setResult(new ShortStruct(instance_.shortStruct()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasIntValue())) {
                NumberStruct instance_ = (NumberStruct) _struct;
                _res.setResult(new IntStruct(instance_.intStruct()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasLongValue())) {
                NumberStruct instance_ = (NumberStruct) _struct;
                _res.setResult(new LongStruct(instance_.longStruct()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasFloatValue())) {
                NumberStruct instance_ = (NumberStruct) _struct;
                _res.setResult(new FloatStruct(instance_.floatStruct()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasDoubleValue())) {
                NumberStruct instance_ = (NumberStruct) _struct;
                _res.setResult(new DoubleStruct(instance_.doubleStruct()));
            } else if (list_.isEmpty()) {
                NumberStruct instance_ = (NumberStruct) _struct;
                _res.setResult(instance_.getDisplayedString(_cont));
            } else {
                _res.setResult(((DisplayableStruct)_args[0]).getDisplayedString(_cont));
            }
        }
    }

    private static void parseDouble(ResultErrorStd _res, LgNames _stds, Struct _arg, boolean _exception) {
        if (!(_arg instanceof CharSequenceStruct)) {
            if (!_exception) {
                _res.setResult(NullStruct.NULL_VALUE);
                return;
            }
            _res.setError(_stds.getAliasNullPe());
            return;
        }
        String one_ = ((CharSequenceStruct) _arg).toStringInstance();
        DoubleInfo v_ = NumParsers.splitDouble(one_);
        if (!v_.isValid()) {
            if (!_exception) {
                _res.setResult(NullStruct.NULL_VALUE);
                return;
            }
            _res.setErrorMessage(one_);
            _res.setError(_stds.getAliasNbFormat());
        } else {
            _res.setResult(new DoubleStruct(v_.getValue()));
        }
    }

    private static void parseFloat(ResultErrorStd _res, LgNames _stds, Struct _arg, boolean _exception) {
        if (!(_arg instanceof CharSequenceStruct)) {
            if (!_exception) {
                _res.setResult(NullStruct.NULL_VALUE);
                return;
            }
            _res.setError(_stds.getAliasNullPe());
            return;
        }
        String one_ = ((CharSequenceStruct) _arg).toStringInstance();
        boolean valid_ = true;
        DoubleInfo v_ = NumParsers.splitDouble(one_);
        double d_ = 0;
        if (v_.outOfRange(Float.MIN_VALUE,Float.MAX_VALUE)) {
            valid_ = false;
        } else {
            d_ = v_.getValue();
        }
        if (!valid_) {
            if (!_exception) {
                _res.setResult(NullStruct.NULL_VALUE);
                return;
            }
            _res.setErrorMessage(one_);
            _res.setError(_stds.getAliasNbFormat());
        } else {
            _res.setResult(new FloatStruct((float) d_));
        }
    }

    private static void parseLong(ResultErrorStd _res, StringList _list, LgNames _stds, Struct[] _args, boolean _exception) {
        if (!(_args[0] instanceof CharSequenceStruct)) {
            if (!_exception) {
                _res.setResult(NullStruct.NULL_VALUE);
                return;
            }
            _res.setError(_stds.getAliasNullPe());
            return;
        }
        String one_ = ((CharSequenceStruct) _args[0]).toStringInstance();
        LongInfo lg_;
        int radix_ = DEFAULT_RADIX;
        if (_list.size() != 1) {
            radix_ = ((NumberStruct) _args[1]).intStruct();
        }
        lg_ = NumParsers.parseLong(one_, radix_);
        if (!lg_.isValid()) {
            if (!_exception) {
                _res.setResult(NullStruct.NULL_VALUE);
                return;
            }
            _res.setErrorMessage(StringList.concat(one_,",",Integer.toString(radix_)));
            _res.setError(_stds.getAliasNbFormat());
        } else {
            _res.setResult(new LongStruct(lg_.getValue()));
        }
    }

    private static void parseInt(ResultErrorStd _res, StringList _list, LgNames _stds, Struct[] _args, boolean _exception) {
        if (!(_args[0] instanceof CharSequenceStruct)) {
            if (!_exception) {
                _res.setResult(NullStruct.NULL_VALUE);
                return;
            }
            _res.setError(_stds.getAliasNullPe());
            return;
        }
        String one_ = ((CharSequenceStruct) _args[0]).toStringInstance();
        LongInfo lg_;
        int radix_ = DEFAULT_RADIX;
        if (_list.size() != 1) {
            radix_ = ((NumberStruct) _args[1]).intStruct();
        }
        lg_ = NumParsers.parseLong(one_, radix_);
        if (lg_.outOfRange(Integer.MIN_VALUE,Integer.MAX_VALUE)) {
            if (!_exception) {
                _res.setResult(NullStruct.NULL_VALUE);
                return;
            }
            _res.setErrorMessage(StringList.concat(one_,",",Integer.toString(radix_)));
            _res.setError(_stds.getAliasNbFormat());
        } else {
            _res.setResult(new IntStruct((int) lg_.getValue()));
        }
    }

    private static void parseShort(ResultErrorStd _res, StringList _list, LgNames _stds, Struct[] _args, boolean _exception) {
        if (!(_args[0] instanceof CharSequenceStruct)) {
            if (!_exception) {
                _res.setResult(NullStruct.NULL_VALUE);
                return;
            }
            _res.setError(_stds.getAliasNullPe());
            return;
        }
        String one_ = ((CharSequenceStruct) _args[0]).toStringInstance();
        LongInfo lg_;
        int radix_ = DEFAULT_RADIX;
        if (_list.size() != 1) {
            radix_ = ((NumberStruct) _args[1]).intStruct();
        }
        lg_ = NumParsers.parseLong(one_, radix_);
        if (lg_.outOfRange(Short.MIN_VALUE,Short.MAX_VALUE)) {
            if (!_exception) {
                _res.setResult(NullStruct.NULL_VALUE);
                return;
            }
            _res.setErrorMessage(StringList.concat(one_,",",Integer.toString(radix_)));
            _res.setError(_stds.getAliasNbFormat());
        } else {
            _res.setResult(new ShortStruct((short) lg_.getValue()));
        }
    }

    private static void parseByte(ResultErrorStd _res, StringList _list, LgNames _stds, Struct[] _args, boolean _exception) {
        if (!(_args[0] instanceof CharSequenceStruct)) {
            if (!_exception) {
                _res.setResult(NullStruct.NULL_VALUE);
                return;
            }
            _res.setError(_stds.getAliasNullPe());
            return;
        }
        String one_ = ((CharSequenceStruct) _args[0]).toStringInstance();
        LongInfo lg_;
        int radix_ = DEFAULT_RADIX;
        if (_list.size() != 1) {
            radix_ = ((NumberStruct) _args[1]).intStruct();
        }
        lg_ = NumParsers.parseLong(one_, radix_);
        if (lg_.outOfRange(Byte.MIN_VALUE,Byte.MAX_VALUE)) {
            if (!_exception) {
                _res.setResult(NullStruct.NULL_VALUE);
                return;
            }
            _res.setErrorMessage(StringList.concat(one_,",",Integer.toString(radix_)));
            _res.setError(_stds.getAliasNbFormat());
        } else {
            _res.setResult(new ByteStruct((byte)lg_.getValue()));
        }
    }

    public static BooleanStruct quickCalculateLowerNb(Struct _a, Struct _b) {
        if (_a instanceof DoubleStruct || _a instanceof FloatStruct || _b instanceof DoubleStruct || _b instanceof FloatStruct) {
            return BooleanStruct.of(((NumberStruct)_a).doubleStruct() < ((NumberStruct)_b).doubleStruct());
        }
        return BooleanStruct.of(((NumberStruct)_a).longStruct() < ((NumberStruct)_b).longStruct());
    }

    public static BooleanStruct quickCalculateGreaterNb(Struct _a, Struct _b) {
        if (_a instanceof DoubleStruct || _a instanceof FloatStruct || _b instanceof DoubleStruct || _b instanceof FloatStruct) {
            return BooleanStruct.of(((NumberStruct)_a).doubleStruct() > ((NumberStruct)_b).doubleStruct());
        }
        return BooleanStruct.of(((NumberStruct)_a).longStruct() > ((NumberStruct)_b).longStruct());
    }
    public static BooleanStruct quickCalculateLowerStr(Struct _a, Struct _b) {
        String first_ = ((CharSequenceStruct)_a).toStringInstance();
        String second_ = ((CharSequenceStruct)_b).toStringInstance();
        return BooleanStruct.of(first_.compareTo(second_) < 0);
    }

    public static BooleanStruct quickCalculateGreaterStr(Struct _a, Struct _b) {
        String first_ = ((CharSequenceStruct)_a).toStringInstance();
        String second_ = ((CharSequenceStruct)_b).toStringInstance();
        return BooleanStruct.of(first_.compareTo(second_) > 0);
    }
    public static NumberStruct idNumber(NumberStruct _a, Analyzable _an,ClassArgumentMatching _order) {
        LgNames stds_ = _an.getStandards();
        return (NumberStruct) PrimitiveTypeUtil.convertObject(_order, _a, stds_);
    }
    public static NumberStruct negBinNumber(NumberStruct _a, Analyzable _an,ClassArgumentMatching _order) {
        String intPrim_ = _an.getStandards().getAliasPrimInteger();
        if (_order.matchClass(intPrim_)) {
            int left_ = _a.getInt();
            boolean[] bits_ = NumParsers.toBits(left_);
            int len_ = bits_.length;
            for (int i = 0; i<len_; i++) {
                bits_[i] = !bits_[i];
            }
            return new IntStruct(NumParsers.toInt(bits_));
        }
        long left_ = _a.getLong();
        boolean[] bits_ = NumParsers.toBits(left_);
        int len_ = bits_.length;
        for (int i = 0; i<len_; i++) {
            bits_[i] = !bits_[i];
        }
        return new LongStruct(NumParsers.toLong(bits_));
    }
    public static NumberStruct calculateSum(NumberStruct _a, NumberStruct _b, Analyzable _an,ClassArgumentMatching _order) {
        int order_ = PrimitiveTypeUtil.getOrderClass(_order, _an);
        String longPrim_ = _an.getStandards().getAliasPrimLong();
        String intPrim_ = _an.getStandards().getAliasPrimInteger();
        String floatPrim_ = _an.getStandards().getAliasPrimFloat();
        if (order_ <= PrimitiveTypeUtil.getOrderClass(longPrim_, _an)) {
            long left_ = _a.getLong();
            long right_ = _b.getLong();
            long nb_ = left_ + right_;
            if (order_ == PrimitiveTypeUtil.getOrderClass(intPrim_, _an)) {
                return new IntStruct((int)nb_);
            }
            return new LongStruct(nb_);
        }
        double left_ = _a.getDouble();
        double right_ = _b.getDouble();
        double nb_ = left_ + right_;
        if (order_ == PrimitiveTypeUtil.getOrderClass(floatPrim_, _an)) {
            return new FloatStruct((float)nb_);
        }
        return new DoubleStruct(nb_);
    }

    public static NumberStruct opposite(NumberStruct _a, Analyzable _an,ClassArgumentMatching _order) {
        int order_ = PrimitiveTypeUtil.getOrderClass(_order, _an);
        String longPrim_ = _an.getStandards().getAliasPrimLong();
        Struct tmp_;
        if (order_ <= PrimitiveTypeUtil.getOrderClass(longPrim_, _an)) {
            tmp_ = new LongStruct(-_a.longStruct());
        } else {
            tmp_ = new DoubleStruct(-_a.doubleStruct());
        }
        LgNames stds_ = _an.getStandards();
        tmp_ = PrimitiveTypeUtil.convertObject(_order, tmp_, stds_);
        return (NumberStruct) tmp_;
    }
    public static NumberStruct calculateDiff(NumberStruct _a, NumberStruct _b, Analyzable _an,ClassArgumentMatching _order) {
        int order_ = PrimitiveTypeUtil.getOrderClass(_order, _an);
        String longPrim_ = _an.getStandards().getAliasPrimLong();
        String intPrim_ = _an.getStandards().getAliasPrimInteger();
        String floatPrim_ = _an.getStandards().getAliasPrimFloat();
        if (order_ <= PrimitiveTypeUtil.getOrderClass(longPrim_, _an)) {
            long left_ = _a.getLong();
            long right_ = _b.getLong();
            long nb_ = left_ - right_;
            if (order_ == PrimitiveTypeUtil.getOrderClass(intPrim_, _an)) {
                return new IntStruct((int)nb_);
            }
            return new LongStruct(nb_);
        }
        double left_ = _a.getDouble();
        double right_ = _b.getDouble();
        double nb_ = left_ - right_;
        if (order_ == PrimitiveTypeUtil.getOrderClass(floatPrim_, _an)) {
            return new FloatStruct((float)nb_);
        }
        return new DoubleStruct(nb_);
    }
    public static NumberStruct calculateMult(NumberStruct _a, NumberStruct _b, Analyzable _an,ClassArgumentMatching _order) {
        int order_ = PrimitiveTypeUtil.getOrderClass(_order, _an);
        String longPrim_ = _an.getStandards().getAliasPrimLong();
        String intPrim_ = _an.getStandards().getAliasPrimInteger();
        String floatPrim_ = _an.getStandards().getAliasPrimFloat();
        if (order_ <= PrimitiveTypeUtil.getOrderClass(longPrim_, _an)) {
            long left_ = _a.getLong();
            long right_ = _b.getLong();
            long nb_ = left_ * right_;
            if (order_ == PrimitiveTypeUtil.getOrderClass(intPrim_, _an)) {
                return new IntStruct((int)nb_);
            }
            return new LongStruct(nb_);
        }
        double left_ = _a.getDouble();
        double right_ = _b.getDouble();
        double nb_ = left_ * right_;
        if (order_ == PrimitiveTypeUtil.getOrderClass(floatPrim_, _an)) {
            return new FloatStruct((float)nb_);
        }
        return new DoubleStruct(nb_);
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
        String longPrim_ = _an.getStandards().getAliasPrimLong();
        String intPrim_ = _an.getStandards().getAliasPrimInteger();
        String floatPrim_ = _an.getStandards().getAliasPrimFloat();
        if (order_ <= PrimitiveTypeUtil.getOrderClass(longPrim_, _an)) {
            long left_ = _a.getLong();
            long right_ = _b.getLong();
            if (right_ == 0) {
                return NullStruct.NULL_VALUE;
            }
            long nb_ = left_ / right_;
            if (order_ == PrimitiveTypeUtil.getOrderClass(intPrim_, _an)) {
                return new IntStruct((int)nb_);
            }
            return new LongStruct(nb_);
        }
        double left_ = _a.getDouble();
        double right_ = _b.getDouble();
        double nb_ = left_ / right_;
        if (order_ == PrimitiveTypeUtil.getOrderClass(floatPrim_, _an)) {
            return new FloatStruct((float)nb_);
        }
        return new DoubleStruct(nb_);
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
        String longPrim_ = _an.getStandards().getAliasPrimLong();
        String intPrim_ = _an.getStandards().getAliasPrimInteger();
        String floatPrim_ = _an.getStandards().getAliasPrimFloat();
        if (order_ <= PrimitiveTypeUtil.getOrderClass(longPrim_, _an)) {
            long left_ = _a.getLong();
            long right_ = _b.getLong();
            if (right_ == 0) {
                return NullStruct.NULL_VALUE;
            }
            long nb_ = left_ % right_;
            if (order_ == PrimitiveTypeUtil.getOrderClass(intPrim_, _an)) {
                return new IntStruct((int)nb_);
            }
            return new LongStruct(nb_);
        }
        double left_ = _a.getDouble();
        double right_ = _b.getDouble();
        double nb_ = left_ % right_;
        if (order_ == PrimitiveTypeUtil.getOrderClass(floatPrim_, _an)) {
            return new FloatStruct((float)nb_);
        }
        return new DoubleStruct(nb_);
    }

    public static Struct calculateAnd(Struct _a, Struct _b, Analyzable _an,ClassArgumentMatching _order) {
        LgNames stds_ = _an.getStandards();
        String bool_ = stds_.getAliasPrimBoolean();
        String int_ = stds_.getAliasPrimInteger();
        if (_order.matchClass(bool_)) {
            boolean left_ = ((BooleanStruct) _a).getInstance();
            boolean right_ = ((BooleanStruct) _b).getInstance();
            return BooleanStruct.of(left_ && right_);
        }
        if (_order.matchClass(int_)) {
            int left_ = ((NumberStruct)_a).getInt();
            int right_ = ((NumberStruct)_b).getInt();
            boolean[] bitsLeft_ = NumParsers.toBits(left_);
            boolean[] bitsRight_ = NumParsers.toBits(right_);
            int len_ = bitsLeft_.length;
            boolean[] bits_ = new boolean[len_];
            for (int i = 0; i < len_; i++) {
                bits_[i] = bitsLeft_[i] && bitsRight_[i];
            }
            int value_ = NumParsers.toInt(bits_);
            return new IntStruct(value_);
        }
        long left_ = ((NumberStruct)_a).getLong();
        long right_ = ((NumberStruct)_b).getLong();
        boolean[] bitsLeft_ = NumParsers.toBits(left_);
        boolean[] bitsRight_ = NumParsers.toBits(right_);
        int len_ = bitsLeft_.length;
        boolean[] bits_ = new boolean[len_];
        for (int i = 0; i < len_; i++) {
            bits_[i] = bitsLeft_[i] && bitsRight_[i];
        }
        long value_ = NumParsers.toLong(bits_);
        return new LongStruct(value_);
    }

    public static Struct calculateOr(Struct _a, Struct _b, Analyzable _an,ClassArgumentMatching _order) {
        LgNames stds_ = _an.getStandards();
        String bool_ = stds_.getAliasPrimBoolean();
        String int_ = stds_.getAliasPrimInteger();
        if (_order.matchClass(bool_)) {
            boolean left_ = ((BooleanStruct) _a).getInstance();
            boolean right_ = ((BooleanStruct) _b).getInstance();
            return BooleanStruct.of(left_ || right_);
        }
        if (_order.matchClass(int_)) {
            int left_ = ((NumberStruct)_a).getInt();
            int right_ = ((NumberStruct)_b).getInt();
            boolean[] bitsLeft_ = NumParsers.toBits(left_);
            boolean[] bitsRight_ = NumParsers.toBits(right_);
            int len_ = bitsLeft_.length;
            boolean[] bits_ = new boolean[len_];
            for (int i = 0; i < len_; i++) {
                bits_[i] = bitsLeft_[i] || bitsRight_[i];
            }
            int value_ = NumParsers.toInt(bits_);
            return new IntStruct(value_);
        }
        long left_ = ((NumberStruct)_a).getLong();
        long right_ = ((NumberStruct)_b).getLong();
        boolean[] bitsLeft_ = NumParsers.toBits(left_);
        boolean[] bitsRight_ = NumParsers.toBits(right_);
        int len_ = bitsLeft_.length;
        boolean[] bits_ = new boolean[len_];
        for (int i = 0; i < len_; i++) {
            bits_[i] = bitsLeft_[i] || bitsRight_[i];
        }
        long value_ = NumParsers.toLong(bits_);
        return new LongStruct(value_);
    }

    public static Struct calculateXor(Struct _a, Struct _b, Analyzable _an,ClassArgumentMatching _order) {
        LgNames stds_ = _an.getStandards();
        String bool_ = stds_.getAliasPrimBoolean();
        String int_ = stds_.getAliasPrimInteger();
        if (_order.matchClass(bool_)) {
            boolean left_ = ((BooleanStruct) _a).getInstance();
            boolean right_ = ((BooleanStruct) _b).getInstance();
            return BooleanStruct.of(left_ != right_);
        }
        if (_order.matchClass(int_)) {
            int left_ = ((NumberStruct)_a).getInt();
            int right_ = ((NumberStruct)_b).getInt();
            boolean[] bitsLeft_ = NumParsers.toBits(left_);
            boolean[] bitsRight_ = NumParsers.toBits(right_);
            int len_ = bitsLeft_.length;
            boolean[] bits_ = new boolean[len_];
            for (int i = 0; i < len_; i++) {
                bits_[i] = bitsLeft_[i] != bitsRight_[i];
            }
            int value_ = NumParsers.toInt(bits_);
            return new IntStruct(value_);
        }
        long left_ = ((NumberStruct)_a).getLong();
        long right_ = ((NumberStruct)_b).getLong();
        boolean[] bitsLeft_ = NumParsers.toBits(left_);
        boolean[] bitsRight_ = NumParsers.toBits(right_);
        int len_ = bitsLeft_.length;
        boolean[] bits_ = new boolean[len_];
        for (int i = 0; i < len_; i++) {
            bits_[i] = bitsLeft_[i] != bitsRight_[i];
        }
        long value_ = NumParsers.toLong(bits_);
        return new LongStruct(value_);
    }
    public static NumberStruct calculateShiftLeft(NumberStruct _a, NumberStruct _b, Analyzable _an,ClassArgumentMatching _order) {
        LgNames stds_ = _an.getStandards();
        String int_ = stds_.getAliasPrimInteger();
        if (_order.matchClass(int_)) {
            int left_ = _a.getInt();
            int right_ = _b.getInt();
            boolean[] bitsRight_ = NumParsers.toBits(right_);
            int value_ = NumParsers.toUnsignedInt(bitsRight_,5);
            int power_ = 1;
            for (int i = 0; i< value_; i++) {
                power_ *= 2;
            }
            return new IntStruct(left_*power_);
        }
        long left_ = _a.getLong();
        long right_ = _b.getLong();
        boolean[] bitsRight_ = NumParsers.toBits(right_);
        long value_ = NumParsers.toUnsignedLong(bitsRight_,6);
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
            boolean[] bitsRight_ = NumParsers.toBits(right_);
            int value_ = NumParsers.toUnsignedInt(bitsRight_,5);
            int power_ = 1;
            for (int i = 0; i< value_; i++) {
                power_ *= 2;
            }
            return new IntStruct(Numbers.quot(left_, power_));
        }
        long left_ = _a.getLong();
        long right_ = _b.getLong();
        boolean[] bitsRight_ = NumParsers.toBits(right_);
        long value_ = NumParsers.toUnsignedLong(bitsRight_,6);
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
            boolean[] bitsRight_ = NumParsers.toBits(right_);
            int value_ = NumParsers.toUnsignedInt(bitsRight_,5);
            boolean[] bitsLeft_ = NumParsers.toBits(left_);
            int diff_ = 32 - value_;
            for (int i = 1; i < diff_; i++) {
                shift(value_, bitsLeft_, i);
            }
            for (int i = diff_; i < 32; i++) {
                bitsLeft_[i] = false;
            }
            return new IntStruct(NumParsers.toInt(bitsLeft_));
        }
        long left_ = _a.getLong();
        long right_ = _b.getLong();
        boolean[] bitsRight_ = NumParsers.toBits(right_);
        int value_ = (int) NumParsers.toUnsignedLong(bitsRight_,6);
        boolean[] bitsLeft_ = NumParsers.toBits(left_);
        int diff_ = 64 - value_;
        for (int i = 1; i < diff_; i++) {
            shift(value_, bitsLeft_, i);
        }
        for (int i = diff_; i < 64; i++) {
            bitsLeft_[i] = false;
        }
        return new LongStruct(NumParsers.toLong(bitsLeft_));
    }

    private static void shift(int _value, boolean[] _bits, int _i) {
        _bits[_i] = _bits[_i + _value];
    }

    public static NumberStruct calculateBitShiftRight(NumberStruct _a, NumberStruct _b, Analyzable _an,ClassArgumentMatching _order) {
        LgNames stds_ = _an.getStandards();
        String int_ = stds_.getAliasPrimInteger();
        if (_order.matchClass(int_)) {
            int left_ = _a.getInt();
            int right_ = _b.getInt();
            boolean[] bitsRight_ = NumParsers.toBits(right_);
            int value_ = NumParsers.toUnsignedInt(bitsRight_,5);
            boolean[] bitsLeft_ = NumParsers.toBits(left_);
            int diff_ = 32 - value_;
            for (int i = 0; i < diff_; i++) {
                int index_ = 31 - i;
                bitsLeft_[index_] = bitsLeft_[index_ - value_];
            }
            for (int i = diff_; i < 32; i++) {
                int index_ = 31 - i;
                bitsLeft_[index_] = false;
            }
            return new IntStruct(NumParsers.toInt(bitsLeft_));
        }
        long left_ = _a.getLong();
        long right_ = _b.getLong();
        boolean[] bitsRight_ = NumParsers.toBits(right_);
        long value_ = NumParsers.toUnsignedLong(bitsRight_,6);
        boolean[] bitsLeft_ = NumParsers.toBits(left_);
        int diff_ = 64 - (int)value_;
        for (int i = 0; i < diff_; i++) {
            int index_ = 63 - i;
            bitsLeft_[index_] = bitsLeft_[index_ - (int)value_];
        }
        for (int i = diff_; i < 64; i++) {
            int index_ = 63 - i;
            bitsLeft_[index_] = false;
        }
        return new LongStruct(NumParsers.toLong(bitsLeft_));
    }
    public static NumberStruct calculateRotateLeft(NumberStruct _a, NumberStruct _b, Analyzable _an,ClassArgumentMatching _order) {
        LgNames stds_ = _an.getStandards();
        String int_ = stds_.getAliasPrimInteger();
        if (_order.matchClass(int_)) {
            int left_ = _a.getInt();
            int right_ = _b.getInt();
            boolean[] bitsRight_ = NumParsers.toBits(right_);
            int value_ = NumParsers.toUnsignedInt(bitsRight_,5);
            boolean[] bitsLeft_ = NumParsers.toBits(left_);
            int max_ = bitsLeft_.length - 1;
            for (int i = 0; i < value_; i++) {
                boolean firstBit_ = bitsLeft_[0];
                for (int j = 0; j < max_; j++) {
                    shift(bitsLeft_, j);
                }
                bitsLeft_[max_] = firstBit_;
            }
            return new IntStruct(NumParsers.toInt(bitsLeft_));
        }
        long left_ = _a.getLong();
        long right_ = _b.getLong();
        boolean[] bitsRight_ = NumParsers.toBits(right_);
        long value_ = NumParsers.toUnsignedLong(bitsRight_,6);
        boolean[] bitsLeft_ = NumParsers.toBits(left_);
        int max_ = bitsLeft_.length - 1;
        for (int i = 0; i < value_; i++) {
            boolean firstBit_ = bitsLeft_[0];
            for (int j = 0; j < max_; j++) {
                shift(bitsLeft_, j);
            }
            bitsLeft_[max_] = firstBit_;
        }
        return new LongStruct(NumParsers.toLong(bitsLeft_));
    }

    private static void shift(boolean[] _bits, int _j) {
        shift(1, _bits, _j);
    }

    public static NumberStruct calculateRotateRight(NumberStruct _a, NumberStruct _b, Analyzable _an,ClassArgumentMatching _order) {
        LgNames stds_ = _an.getStandards();
        String int_ = stds_.getAliasPrimInteger();
        if (_order.matchClass(int_)) {
            int left_ = _a.getInt();
            int right_ = _b.getInt();
            boolean[] bitsRight_ = NumParsers.toBits(right_);
            int value_ = NumParsers.toUnsignedInt(bitsRight_,5);
            boolean[] bitsLeft_ = NumParsers.toBits(left_);
            int max_ = bitsLeft_.length - 1;
            for (int i = 0; i < value_; i++) {
                boolean firstBit_ = bitsLeft_[max_];
                for (int j = 0; j < max_; j++) {
                    int index_ = max_ - j;
                    bitsLeft_[index_] = bitsLeft_[index_ - 1];
                }
                bitsLeft_[0] = firstBit_;
            }
            return new IntStruct(NumParsers.toInt(bitsLeft_));
        }
        long left_ = _a.getLong();
        long right_ = _b.getLong();
        boolean[] bitsRight_ = NumParsers.toBits(right_);
        long value_ = NumParsers.toUnsignedLong(bitsRight_,6);
        boolean[] bitsLeft_ = NumParsers.toBits(left_);
        int max_ = bitsLeft_.length - 1;
        for (int i = 0; i < value_; i++) {
            boolean firstBit_ = bitsLeft_[max_];
            for (int j = 0; j < max_; j++) {
                int index_ = max_ - j;
                bitsLeft_[index_] = bitsLeft_[index_ - 1];
            }
            bitsLeft_[0] = firstBit_;
        }
        return new LongStruct(NumParsers.toLong(bitsLeft_));
    }
    private double getDouble() {
        return doubleStruct();
    }
    private float getFloat() {
        return floatStruct();
    }
    private long getLong() {
        return longStruct();
    }

    private int getInt() {
        return intStruct();
    }

    private short getShort() {
        return shortStruct();
    }
    private byte getByte() {
        return byteStruct();
    }

    public abstract double doubleStruct();
    public abstract float floatStruct();
    public abstract long longStruct();
    public abstract int intStruct();
    public abstract short shortStruct();
    public abstract byte byteStruct();

    @Override
    public final boolean sameReference(Struct _other) {
        if (!(_other instanceof NumberStruct)) {
            return false;
        }
        NumberStruct other_ = (NumberStruct) _other;
        if (this instanceof DoubleStruct || this instanceof FloatStruct || other_ instanceof DoubleStruct || other_ instanceof FloatStruct) {
            return doubleStruct() == other_.doubleStruct();
        }
        return longStruct() == other_.longStruct();
    }

    @Override
    public StringStruct getDisplayedString(Analyzable _an) {
        return exportValue();
    }
    @Override
    public StringStruct exportValue() {
        if (this instanceof DoubleStruct) {
            return new StringStruct(Double.toString(doubleStruct()));
        }
        if (this instanceof FloatStruct) {
            return new StringStruct(Float.toString(floatStruct()));
        }
        return new StringStruct(Long.toString(longStruct()));
    }

    static int compareGene(NumberStruct _nb1,NumberStruct _nb2) {
        if (_nb1 instanceof DoubleStruct || _nb1 instanceof FloatStruct || _nb2 instanceof DoubleStruct || _nb2 instanceof FloatStruct) {
            if (_nb1.doubleStruct() < _nb2.doubleStruct()) {
                return CustList.NO_SWAP_SORT;
            }
            if (_nb1.doubleStruct() > _nb2.doubleStruct()) {
                return CustList.SWAP_SORT;
            }
            return CustList.EQ_CMP;
        }
        return compare(_nb1,_nb2);
    }
    static int compare(NumberStruct _nb1,NumberStruct _nb2) {
        return Numbers.compareLg(_nb1.longStruct(),_nb2.longStruct());
    }
}
