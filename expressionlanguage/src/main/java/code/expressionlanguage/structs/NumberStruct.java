package code.expressionlanguage.structs;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.opers.exec.ExecCatOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.stds.*;
import code.util.CustList;
import code.util.*;
import code.util.StringList;

public abstract class NumberStruct implements DisplayableStruct {
    private static final int DEFAULT_RADIX = 10;
    @Override
    public final Struct getParent() {
        return NullStruct.NULL_VALUE;
    }
    public static void calculateOperator(ExecutableCode _cont, ResultErrorStd _res, ClassArgumentMatching _order,
                                         String _op, boolean _catString,
                                         Struct _first, Struct _second) {
        if (_catString) {
            catenize(_cont, _res, _first, _second);
            return;
        }
        String op_ = _op.substring(0, _op.length() - 1);
        if (StringList.quickEq(op_, "+")) {
            _res.setResult(calculateSum(ClassArgumentMatching.convertToNumber(_first), ClassArgumentMatching.convertToNumber(_second), _cont, _order));
            return;
        }
        if (StringList.quickEq(op_, "-")) {
            _res.setResult(calculateDiff(ClassArgumentMatching.convertToNumber(_first), ClassArgumentMatching.convertToNumber(_second), _cont, _order));
            return;
        }
        if (StringList.quickEq(op_, "*")) {
            _res.setResult(calculateMult(ClassArgumentMatching.convertToNumber(_first), ClassArgumentMatching.convertToNumber(_second), _cont, _order));
            return;
        }
        if (StringList.quickEq(op_, "/")) {
            _res.setResult(calculateDivEx(ClassArgumentMatching.convertToNumber(_first), ClassArgumentMatching.convertToNumber(_second), _cont, _order));
            return;
        }
        if (StringList.quickEq(op_, "%")) {
            _res.setResult(calculateModEx(ClassArgumentMatching.convertToNumber(_first), ClassArgumentMatching.convertToNumber(_second), _cont, _order));
            return;
        }
        if (StringList.quickEq(op_, "&")) {
            _res.setResult(calculateAnd(_first, _second, _cont, _order));
            return;
        }
        if (StringList.quickEq(op_, "|")) {
            _res.setResult(calculateOr(_first, _second, _cont, _order));
            return;
        }
        if (StringList.quickEq(op_, "^")) {
            _res.setResult(calculateXor(_first, _second, _cont, _order));
            return;
        }
        if (StringList.quickEq(op_, "<<")) {
            _res.setResult(calculateShiftLeft(ClassArgumentMatching.convertToNumber(_first), ClassArgumentMatching.convertToNumber(_second), _cont, _order));
            return;
        }
        if (StringList.quickEq(op_, ">>")) {
            _res.setResult(calculateShiftRight(ClassArgumentMatching.convertToNumber(_first), ClassArgumentMatching.convertToNumber(_second), _cont, _order));
            return;
        }
        if (StringList.quickEq(op_, "<<<")) {
            _res.setResult(calculateBitShiftLeft(ClassArgumentMatching.convertToNumber(_first), ClassArgumentMatching.convertToNumber(_second), _cont, _order));
            return;
        }
        if (StringList.quickEq(op_, ">>>")) {
            _res.setResult(calculateBitShiftRight(ClassArgumentMatching.convertToNumber(_first), ClassArgumentMatching.convertToNumber(_second), _cont, _order));
            return;
        }
        if (StringList.quickEq(op_, "<<<<")) {
            _res.setResult(calculateRotateLeft(ClassArgumentMatching.convertToNumber(_first), ClassArgumentMatching.convertToNumber(_second), _cont, _order));
            return;
        }
        if (StringList.quickEq(op_, ">>>>")) {
            _res.setResult(calculateRotateRight(ClassArgumentMatching.convertToNumber(_first), ClassArgumentMatching.convertToNumber(_second), _cont, _order));
            return;
        }
        if (StringList.quickEq(op_, "??")) {
            if (_first != NullStruct.NULL_VALUE) {
                _res.setResult(ClassArgumentMatching.convert(_order, _first,_cont));
                return;
            }
            _res.setResult(ClassArgumentMatching.convert(_order, _second,_cont));
            return;
        }
        if (StringList.quickEq(op_, "&&")) {
            BooleanStruct b_ = ClassArgumentMatching.convertToBoolean(_first);
            if (BooleanStruct.of(false).sameReference(b_)) {
                _res.setResult(b_);
                return;
            }
            _res.setResult(ClassArgumentMatching.convertToBoolean(_second));
            return;
        }
        BooleanStruct b_ = ClassArgumentMatching.convertToBoolean(_first);
        if (BooleanStruct.of(true).sameReference(b_)) {
            _res.setResult(b_);
            return;
        }
        _res.setResult(ClassArgumentMatching.convertToBoolean(_second));
    }

  private static void catenize(Analyzable _cont, ResultErrorStd _res, Struct _first, Struct _second) {
      Argument arg_ = ExecCatOperation.localSumDiff(new Argument(_first), new Argument(_second), _cont.getContextEl());
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
                String one_ = ApplyCoreMethodUtil.getCharSeq(_args[0]).toStringInstance();
                if (StringList.quickEq(one_, lgNames_.getDisplayedStrings().getTrueString())) {
                    _res.setResult(BooleanStruct.of(true));
                } else {
                    _res.setResult(BooleanStruct.of(false));
                }
            } else {
                _res.setResult(ClassArgumentMatching.convertToBoolean(_args[0]));
            }
        } else if (StringList.quickEq(type_, charType_)) {
            _res.setResult(_args[0]);
        } else if (StringList.quickEq(type_, byteType_)) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                parseByte(_res,list_,lgNames_,_args,true);
            } else {
                Byte one_ = (ClassArgumentMatching.convertToNumber(_args[0])).getByte();
                _res.setResult(new ByteStruct(one_));
            }
        } else if (StringList.quickEq(type_, shortType_)) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                parseShort(_res,list_,lgNames_,_args,true);
            } else {
                Short one_ = (ClassArgumentMatching.convertToNumber(_args[0])).getShort();
                _res.setResult(new ShortStruct(one_));
            }
        } else if (StringList.quickEq(type_, intType_)) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                parseInt(_res,list_,lgNames_,_args,true);
            } else {
                Integer one_ = (ClassArgumentMatching.convertToNumber(_args[0])).getInt();
                _res.setResult(new IntStruct(one_));
            }
        } else if (StringList.quickEq(type_, longType_)) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                parseLong(_res,list_,lgNames_,_args,true);
            } else {
                Long one_ = (ClassArgumentMatching.convertToNumber(_args[0])).getLong();
                _res.setResult(new LongStruct(one_));
            }
        } else if (StringList.quickEq(type_, floatType_)) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                parseFloat(_res,lgNames_,_args[0],true);
            } else {
                Float one_ = (ClassArgumentMatching.convertToNumber(_args[0])).getFloat();
                _res.setResult(new FloatStruct(one_));
            }
        } else {
            if (StringList.quickEq(list_.first(), stringType_)) {
                parseDouble(_res,lgNames_,_args[0],true);
            } else {
                Double one_ = (ClassArgumentMatching.convertToNumber(_args[0])).getDouble();
                _res.setResult(new DoubleStruct(one_));
            }
        }
    }

    public static Struct instantiate(Analyzable _cont, ConstructorId _method, Struct... _args) {
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
                String one_ = ApplyCoreMethodUtil.getCharSeq(_args[0]).toStringInstance();
                if (StringList.quickEq(one_, lgNames_.getDisplayedStrings().getTrueString())) {
                    return(BooleanStruct.of(true));
                } else {
                    return(BooleanStruct.of(false));
                }
            } else {
                return(ClassArgumentMatching.convertToBoolean(_args[0]));
            }
        } else if (StringList.quickEq(type_, charType_)) {
            return(_args[0]);
        } else if (StringList.quickEq(type_, byteType_)) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                return parseByte(list_,_args,true);
            } else {
                Byte one_ = (ClassArgumentMatching.convertToNumber(_args[0])).getByte();
                return(new ByteStruct(one_));
            }
        } else if (StringList.quickEq(type_, shortType_)) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                return parseShort(list_,_args,true);
            } else {
                Short one_ = (ClassArgumentMatching.convertToNumber(_args[0])).getShort();
                return(new ShortStruct(one_));
            }
        } else if (StringList.quickEq(type_, intType_)) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                return parseInt(list_,_args,true);
            } else {
                Integer one_ = (ClassArgumentMatching.convertToNumber(_args[0])).getInt();
                return(new IntStruct(one_));
            }
        } else if (StringList.quickEq(type_, longType_)) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                return parseLong(list_,_args,true);
            } else {
                Long one_ = (ClassArgumentMatching.convertToNumber(_args[0])).getLong();
                return(new LongStruct(one_));
            }
        } else if (StringList.quickEq(type_, floatType_)) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                return parseFloat(_args[0],true);
            } else {
                Float one_ = (ClassArgumentMatching.convertToNumber(_args[0])).getFloat();
                return (new FloatStruct(one_));
            }
        } else {
            if (StringList.quickEq(list_.first(), stringType_)) {
                return parseDouble(_args[0],true);
            } else {
                Double one_ = (ClassArgumentMatching.convertToNumber(_args[0])).getDouble();
                return(new DoubleStruct(one_));
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
                BooleanStruct instance_ = ClassArgumentMatching.convertToBoolean(_struct);
                _res.setResult(instance_);
            } else if (StringList.quickEq(name_, lgNames_.getAliasCompare())) {
                _res.setResult(cmpBool((ClassArgumentMatching.convertToBoolean(_args[0])),(ClassArgumentMatching.convertToBoolean(_args[1]))));
            } else if (StringList.quickEq(name_, lgNames_.getAliasCompareTo())) {
                BooleanStruct instance_ = ClassArgumentMatching.convertToBoolean(_struct);
                _res.setResult(cmpBool(instance_,(ClassArgumentMatching.convertToBoolean(_args[0]))));
            } else if (StringList.quickEq(name_, lgNames_.getAliasEquals())) {
                BooleanStruct instance_ = ClassArgumentMatching.convertToBoolean(_struct);
                _res.setResult(BooleanStruct.of(instance_.sameReference(_args[0])));
            } else if (StringList.quickEq(name_, lgNames_.getAliasParseBoolean())) {
                StringStruct disp_ = ExecCatOperation.getDisplayable(new Argument(_args[0]),_cont.getContextEl()).getDisplayedString(_cont);
                if (StringList.quickEq(disp_.getInstance(),lgNames_.getDisplayedStrings().getTrueString())) {
                    _res.setResult(BooleanStruct.of(true));
                } else {
                    _res.setResult(BooleanStruct.of(false));
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                if (!list_.isEmpty()) {
                    _res.setResult((ClassArgumentMatching.convertToBoolean(_args[0])).getDisplayedString(_cont));
                } else {
                    BooleanStruct instance_ = ClassArgumentMatching.convertToBoolean(_struct);
                    _res.setResult(instance_.getDisplayedString(_cont));
                }
            } else {
                if (StringList.quickEq(list_.first(), booleanPrimType_)) {
                    _res.setResult(_args[0]);
                } else {
                    StringStruct disp_ = ExecCatOperation.getDisplayable(new Argument(_args[0]),_cont.getContextEl()).getDisplayedString(_cont);
                    if (StringList.quickEq(disp_.getInstance(),lgNames_.getDisplayedStrings().getTrueString())) {
                        _res.setResult(BooleanStruct.of(true));
                    } else {
                        _res.setResult(BooleanStruct.of(false));
                    }
                }
            }
        } else if (StringList.quickEq(type_, charType_)) {
            if (_method.getConstraints().isStaticMethod()) {
                if (StringList.quickEq(name_, lgNames_.getAliasCompare())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    char two_ = ClassArgumentMatching.convertToChar(_args[1]).getChar();
                    _res.setResult(new IntStruct(Numbers.compareLg(one_,two_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasDigit())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    Integer two_ = (ClassArgumentMatching.convertToNumber(_args[1])).intStruct();
                    _res.setResult(new IntStruct(Character.digit(one_, two_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasForDigit())) {
                    Integer one_ = (ClassArgumentMatching.convertToNumber(_args[0])).intStruct();
                    Integer two_ = (ClassArgumentMatching.convertToNumber(_args[1])).intStruct();
                    _res.setResult(new CharStruct(Character.forDigit(one_, two_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasGetDirectionality())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    _res.setResult(new ByteStruct(Character.getDirectionality(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasGetType())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    _res.setResult(new IntStruct(Character.getType(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsDigit())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    _res.setResult(BooleanStruct.of(ContextEl.isDigit(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsLetter())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    _res.setResult(BooleanStruct.of(Character.isLetter(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsLetterOrDigit())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    _res.setResult(BooleanStruct.of(Character.isLetterOrDigit(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsLowerCase())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    _res.setResult(BooleanStruct.of(Character.isLowerCase(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsUpperCase())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    _res.setResult(BooleanStruct.of(Character.isUpperCase(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsSpace())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    _res.setResult(BooleanStruct.of(Character.isWhitespace(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsWhitespace())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    _res.setResult(BooleanStruct.of(Character.isWhitespace(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsWordChar())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    _res.setResult(BooleanStruct.of(StringList.isWordChar(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasToLowerCase())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    _res.setResult(new CharStruct(Character.toLowerCase(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasToUpperCase())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    _res.setResult(new CharStruct(Character.toUpperCase(one_)));
                    return;
                }
                char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                _res.setResult(new StringStruct(Character.toString(one_)));
                return;
            }
            CharStruct ch_ = ClassArgumentMatching.convertToChar(_struct);
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
                _res.setResult(new IntStruct(compare(ClassArgumentMatching.convertToNumber(_args[0]),ClassArgumentMatching.convertToNumber(_args[1]))));
            } else if (StringList.quickEq(name_, lgNames_.getAliasCompareTo())) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                _res.setResult(new IntStruct(compare(instance_,ClassArgumentMatching.convertToNumber(_args[0]))));
            } else {
                if (StringList.quickEq(type_, byteType_)) {
                    if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                        Byte one_ = (ClassArgumentMatching.convertToNumber(_args[0])).getByte();
                        _res.setResult(new StringStruct(Integer.toString(one_)));
                    } else{
                        boolean exc_ = StringList.quickEq(name_, lgNames_.getAliasParseByte());
                        parseByte(_res, list_, lgNames_, _args,exc_);
                    }
                } else if (StringList.quickEq(type_, shortType_)) {
                    if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                        Short one_ = (ClassArgumentMatching.convertToNumber(_args[0])).getShort();
                        _res.setResult(new StringStruct(Integer.toString(one_)));
                    } else {
                        boolean exc_ = StringList.quickEq(name_, lgNames_.getAliasParseShort());
                        parseShort(_res, list_, lgNames_, _args,exc_);
                    }
                } else if (StringList.quickEq(type_, intType_)) {
                    if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                        Integer one_ = (ClassArgumentMatching.convertToNumber(_args[0])).intStruct();
                        _res.setResult(new StringStruct(Integer.toString(one_)));
                    } else {
                        boolean exc_ = StringList.quickEq(name_, lgNames_.getAliasParseInt());
                        parseInt(_res, list_, lgNames_, _args,exc_);
                    }
                } else if (StringList.quickEq(type_, longType_)) {
                    if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                        Long one_ = (ClassArgumentMatching.convertToNumber(_args[0])).longStruct();
                        _res.setResult(new StringStruct(Long.toString(one_)));
                    } else {
                        boolean exc_ = StringList.quickEq(name_, lgNames_.getAliasParseLong());
                        parseLong(_res, list_, lgNames_, _args,exc_);
                    }
                } else if (StringList.quickEq(type_, floatType_)) {
                    if (StringList.quickEq(name_, lgNames_.getAliasIsNan())) {
                        Float one_;
                        one_ = (ClassArgumentMatching.convertToNumber(_args[0])).getFloat();
                        _res.setResult(BooleanStruct.of(Double.isNaN(one_)));
                    } else if (StringList.quickEq(name_, lgNames_.getAliasIsInfinite())) {
                        Float one_;
                        one_ = (ClassArgumentMatching.convertToNumber(_args[0])).getFloat();
                        _res.setResult(BooleanStruct.of(Double.isInfinite(one_)));
                    } else if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                        Float one_ = (ClassArgumentMatching.convertToNumber(_args[0])).getFloat();
                        _res.setResult(new StringStruct(Float.toString(one_)));
                    } else {
                        boolean exc_ = StringList.quickEq(name_, lgNames_.getAliasParseFloat());
                        parseFloat(_res, lgNames_, _args[0],exc_);
                    }
                } else {
                    if (StringList.quickEq(name_, lgNames_.getAliasIsNan())) {
                        Double one_ = getaDouble(_struct, list_, _args);
                        _res.setResult(BooleanStruct.of(Double.isNaN(one_)));
                    } else if (StringList.quickEq(name_, lgNames_.getAliasIsInfinite())) {
                        Double one_ = getaDouble(_struct, list_, _args);
                        _res.setResult(BooleanStruct.of(Double.isInfinite(one_)));
                    } else if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                        Double one_ = (ClassArgumentMatching.convertToNumber(_args[0])).getDouble();
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
                _res.setResult(new IntStruct(compareGene(ClassArgumentMatching.convertToNumber(_args[0]), ClassArgumentMatching.convertToNumber(_args[1]))));
            } else if (StringList.quickEq(name_, lgNames_.getAliasCompareTo())) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                if (!(_args[0] instanceof  NumberStruct)) {
                    _res.setError(lgNames_.getAliasNullPe());
                    return;
                }
                _res.setResult(new IntStruct(compareGene(instance_, ClassArgumentMatching.convertToNumber(_args[0]))));
            } else if (StringList.quickEq(name_, lgNames_.getAliasEquals())) {
                _res.setResult(BooleanStruct.of(_struct.sameReference(_args[0])));
            } else if (StringList.quickEq(name_, lgNames_.getAliasByteValue())) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                _res.setResult(new ByteStruct(instance_.byteStruct()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasShortValue())) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                _res.setResult(new ShortStruct(instance_.shortStruct()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasIntValue())) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                _res.setResult(new IntStruct(instance_.intStruct()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasLongValue())) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                _res.setResult(new LongStruct(instance_.longStruct()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasFloatValue())) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                _res.setResult(new FloatStruct(instance_.floatStruct()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasDoubleValue())) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                _res.setResult(new DoubleStruct(instance_.doubleStruct()));
            } else if (list_.isEmpty()) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                _res.setResult(instance_.getDisplayedString(_cont));
            } else {
                StringStruct disp_ = ExecCatOperation.getDisplayable(new Argument(_args[0]),_cont.getContextEl())
                        .getDisplayedString(_cont);
                _res.setResult(disp_);
            }
        }
    }
    public static Struct calculate(Analyzable _cont, ClassMethodId _method, Struct _struct, Struct... _args) {
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
                return(ClassArgumentMatching.convertToBoolean(_struct));
            }
            if (StringList.quickEq(name_, lgNames_.getAliasCompare())) {
                return(cmpBool((ClassArgumentMatching.convertToBoolean(_args[0])),(ClassArgumentMatching.convertToBoolean(_args[1]))));
            }
            if (StringList.quickEq(name_, lgNames_.getAliasCompareTo())) {
                BooleanStruct instance_ = ClassArgumentMatching.convertToBoolean(_struct);
                return(cmpBool(instance_,(ClassArgumentMatching.convertToBoolean(_args[0]))));
            }
            if (StringList.quickEq(name_, lgNames_.getAliasEquals())) {
                BooleanStruct instance_ = ClassArgumentMatching.convertToBoolean(_struct);
                return(BooleanStruct.of(instance_.sameReference(_args[0])));
            }
            if (StringList.quickEq(name_, lgNames_.getAliasParseBoolean())) {
                StringStruct disp_ = ExecCatOperation.getDisplayable(new Argument(_args[0]),_cont.getContextEl()).getDisplayedString(_cont);
                if (StringList.quickEq(disp_.getInstance(),lgNames_.getDisplayedStrings().getTrueString())) {
                    return(BooleanStruct.of(true));
                }
                return(BooleanStruct.of(false));
            }
            if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                if (!list_.isEmpty()) {
                    return ((ClassArgumentMatching.convertToBoolean(_args[0])).getDisplayedString(_cont));
                }
                BooleanStruct instance_ = ClassArgumentMatching.convertToBoolean(_struct);
                return (instance_.getDisplayedString(_cont));
            }
            if (StringList.quickEq(list_.first(), booleanPrimType_)) {
                return (_args[0]);
            }
            StringStruct disp_ = ExecCatOperation.getDisplayable(new Argument(_args[0]), _cont.getContextEl()).getDisplayedString(_cont);
            if (StringList.quickEq(disp_.getInstance(), lgNames_.getDisplayedStrings().getTrueString())) {
                return (BooleanStruct.of(true));
            }
            return (BooleanStruct.of(false));
        }
        if (StringList.quickEq(type_, charType_)) {
            if (_method.getConstraints().isStaticMethod()) {
                if (StringList.quickEq(name_, lgNames_.getAliasCompare())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    char two_ = ClassArgumentMatching.convertToChar(_args[1]).getChar();
                    return(new IntStruct(Numbers.compareLg(one_,two_)));
                }
                if (StringList.quickEq(name_, lgNames_.getAliasDigit())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    Integer two_ = (ClassArgumentMatching.convertToNumber(_args[1])).intStruct();
                    return(new IntStruct(Character.digit(one_, two_)));
                }
                if (StringList.quickEq(name_, lgNames_.getAliasForDigit())) {
                    Integer one_ = (ClassArgumentMatching.convertToNumber(_args[0])).intStruct();
                    Integer two_ = (ClassArgumentMatching.convertToNumber(_args[1])).intStruct();
                    return(new CharStruct(Character.forDigit(one_, two_)));
                }
                if (StringList.quickEq(name_, lgNames_.getAliasGetDirectionality())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    return(new ByteStruct(Character.getDirectionality(one_)));
                }
                if (StringList.quickEq(name_, lgNames_.getAliasGetType())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    return(new IntStruct(Character.getType(one_)));
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsDigit())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    return(BooleanStruct.of(ContextEl.isDigit(one_)));
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsLetter())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    return(BooleanStruct.of(Character.isLetter(one_)));
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsLetterOrDigit())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    return(BooleanStruct.of(Character.isLetterOrDigit(one_)));
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsLowerCase())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    return(BooleanStruct.of(Character.isLowerCase(one_)));
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsUpperCase())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    return(BooleanStruct.of(Character.isUpperCase(one_)));
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsSpace())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    return(BooleanStruct.of(Character.isWhitespace(one_)));
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsWhitespace())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    return(BooleanStruct.of(Character.isWhitespace(one_)));
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsWordChar())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    return(BooleanStruct.of(StringList.isWordChar(one_)));
                }
                if (StringList.quickEq(name_, lgNames_.getAliasToLowerCase())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    return(new CharStruct(Character.toLowerCase(one_)));
                }
                if (StringList.quickEq(name_, lgNames_.getAliasToUpperCase())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    return(new CharStruct(Character.toUpperCase(one_)));
                }
                char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                return(new StringStruct(Character.toString(one_)));
            }
            CharStruct ch_ = ClassArgumentMatching.convertToChar(_struct);
            if (StringList.quickEq(name_, lgNames_.getAliasCharValue())) {
                return(new CharStruct(ch_.getChar()));
            }
            if (!(_args[0] instanceof CharStruct)) {
                return null;
            }
            char one_ = ch_.getChar();
            char two_ = ((CharStruct) _args[0]).getChar();
            return(new IntStruct(Numbers.compareLg(one_,two_)));
        }
        if (StringList.quickEq(type_, byteType_)
                || StringList.quickEq(type_, shortType_)
                || StringList.quickEq(type_, intType_)
                || StringList.quickEq(type_, longType_)
                || StringList.quickEq(type_, floatType_)
                || StringList.quickEq(type_, doubleType_)) {
            if (StringList.quickEq(name_, lgNames_.getAliasCompare())) {
                return(new IntStruct(compare(ClassArgumentMatching.convertToNumber(_args[0]),ClassArgumentMatching.convertToNumber(_args[1]))));
            }
            if (StringList.quickEq(name_, lgNames_.getAliasCompareTo())) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                return(new IntStruct(compare(instance_,ClassArgumentMatching.convertToNumber(_args[0]))));
            }
            if (StringList.quickEq(type_, byteType_)) {
                if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                    Byte one_ = (ClassArgumentMatching.convertToNumber(_args[0])).getByte();
                    return (new StringStruct(Integer.toString(one_)));
                }
                boolean exc_ = StringList.quickEq(name_, lgNames_.getAliasParseByte());
                return parseByte(list_, _args, exc_);
            }
            if (StringList.quickEq(type_, shortType_)) {
                if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                    Short one_ = (ClassArgumentMatching.convertToNumber(_args[0])).getShort();
                    return (new StringStruct(Integer.toString(one_)));
                }
                boolean exc_ = StringList.quickEq(name_, lgNames_.getAliasParseShort());
                return parseShort(list_, _args, exc_);
            }
            if (StringList.quickEq(type_, intType_)) {
                if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                    Integer one_ = (ClassArgumentMatching.convertToNumber(_args[0])).intStruct();
                    return (new StringStruct(Integer.toString(one_)));
                }
                boolean exc_ = StringList.quickEq(name_, lgNames_.getAliasParseInt());
                return parseInt(list_, _args, exc_);
            }
            if (StringList.quickEq(type_, longType_)) {
                if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                    Long one_ = (ClassArgumentMatching.convertToNumber(_args[0])).longStruct();
                    return (new StringStruct(Long.toString(one_)));
                }
                boolean exc_ = StringList.quickEq(name_, lgNames_.getAliasParseLong());
                return parseLong(list_, _args, exc_);
            }
            if (StringList.quickEq(type_, floatType_)) {
                if (StringList.quickEq(name_, lgNames_.getAliasIsNan())) {
                    Float one_;
                    one_ = (ClassArgumentMatching.convertToNumber(_args[0])).getFloat();
                    return (BooleanStruct.of(Double.isNaN(one_)));
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsInfinite())) {
                    Float one_;
                    one_ = (ClassArgumentMatching.convertToNumber(_args[0])).getFloat();
                    return (BooleanStruct.of(Double.isInfinite(one_)));
                }
                if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                    Float one_ = (ClassArgumentMatching.convertToNumber(_args[0])).getFloat();
                    return (new StringStruct(Float.toString(one_)));
                }
                boolean exc_ = StringList.quickEq(name_, lgNames_.getAliasParseFloat());
                return parseFloat(_args[0], exc_);
            }
            if (StringList.quickEq(name_, lgNames_.getAliasIsNan())) {
                Double one_ = getaDouble(_struct, list_, _args);
                return (BooleanStruct.of(Double.isNaN(one_)));
            }
            if (StringList.quickEq(name_, lgNames_.getAliasIsInfinite())) {
                Double one_ = getaDouble(_struct, list_, _args);
                return (BooleanStruct.of(Double.isInfinite(one_)));
            }
            if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                Double one_ = (ClassArgumentMatching.convertToNumber(_args[0])).getDouble();
                return (new StringStruct(Double.toString(one_)));
            }
            boolean exc_ = StringList.quickEq(name_, lgNames_.getAliasParseDouble());
            return parseDouble(_args[0], exc_);
        }
        if (StringList.quickEq(type_, nbType_)) {
            if (StringList.quickEq(name_, lgNames_.getAliasCompare())) {
                if (!(_args[0] instanceof  NumberStruct)) {
                    return null;
                }
                if (!(_args[1] instanceof  NumberStruct)) {
                    return null;
                }
                return(new IntStruct(compareGene(ClassArgumentMatching.convertToNumber(_args[0]), ClassArgumentMatching.convertToNumber(_args[1]))));
            }
            if (StringList.quickEq(name_, lgNames_.getAliasCompareTo())) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                if (!(_args[0] instanceof  NumberStruct)) {
                    return null;
                }
                return(new IntStruct(compareGene(instance_, ClassArgumentMatching.convertToNumber(_args[0]))));
            }
            if (StringList.quickEq(name_, lgNames_.getAliasEquals())) {
                return(BooleanStruct.of(_struct.sameReference(_args[0])));
            }
            if (StringList.quickEq(name_, lgNames_.getAliasByteValue())) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                return(new ByteStruct(instance_.byteStruct()));
            }
            if (StringList.quickEq(name_, lgNames_.getAliasShortValue())) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                return(new ShortStruct(instance_.shortStruct()));
            }
            if (StringList.quickEq(name_, lgNames_.getAliasIntValue())) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                return(new IntStruct(instance_.intStruct()));
            }
            if (StringList.quickEq(name_, lgNames_.getAliasLongValue())) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                return(new LongStruct(instance_.longStruct()));
            }
            if (StringList.quickEq(name_, lgNames_.getAliasFloatValue())) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                return(new FloatStruct(instance_.floatStruct()));
            }
            if (StringList.quickEq(name_, lgNames_.getAliasDoubleValue())) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                return(new DoubleStruct(instance_.doubleStruct()));
            }
            if (list_.isEmpty()) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                return(instance_.getDisplayedString(_cont));
            }
            return(ExecCatOperation.getDisplayable(new Argument(_args[0]),_cont.getContextEl())
                    .getDisplayedString(_cont));
        }
        return null;
    }

    private static Double getaDouble(Struct _struct, StringList list_, Struct[] _args) {
        Double one_;
        if (list_.isEmpty()) {
            NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
            one_ = instance_.getDouble();
        } else {
            one_ = (ClassArgumentMatching.convertToNumber(_args[0])).getDouble();
        }
        return one_;
    }

    private static Struct parseDouble(Struct _arg, boolean _exception) {
        if (!(_arg instanceof CharSequenceStruct)) {
            if (!_exception) {
                return(NullStruct.NULL_VALUE);
            }
            return null;
        }
        String one_ = ApplyCoreMethodUtil.getCharSeq(_arg).toStringInstance();
        DoubleInfo v_ = NumParsers.splitDouble(one_);
        if (!v_.isValid()) {
            if (!_exception) {
                return(NullStruct.NULL_VALUE);
            }
            return null;
        } else {
            return(new DoubleStruct(v_.getValue()));
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
        String one_ = ApplyCoreMethodUtil.getCharSeq(_arg).toStringInstance();
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
        String one_ = ApplyCoreMethodUtil.getCharSeq(_arg).toStringInstance();
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
    private static Struct parseFloat(Struct _arg, boolean _exception) {
        if (!(_arg instanceof CharSequenceStruct)) {
            if (!_exception) {
                return(NullStruct.NULL_VALUE);
            }
            return null;
        }
        String one_ = ApplyCoreMethodUtil.getCharSeq(_arg).toStringInstance();
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
                return(NullStruct.NULL_VALUE);
            }
            return null;
        } else {
            return(new FloatStruct((float) d_));
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
        String one_ = ApplyCoreMethodUtil.getCharSeq(_args[0]).toStringInstance();
        LongInfo lg_;
        int radix_ = getRadix(_list, _args);
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

    private static Struct parseLong(StringList _list, Struct[] _args, boolean _exception) {
        if (!(_args[0] instanceof CharSequenceStruct)) {
            if (!_exception) {
                return(NullStruct.NULL_VALUE);
            }
            return null;
        }
        String one_ = ApplyCoreMethodUtil.getCharSeq(_args[0]).toStringInstance();
        LongInfo lg_;
        int radix_ = getRadix(_list, _args);
        lg_ = NumParsers.parseLong(one_, radix_);
        if (!lg_.isValid()) {
            if (!_exception) {
                return(NullStruct.NULL_VALUE);
            }
            return null;
        } else {
            return(new LongStruct(lg_.getValue()));
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
        String one_ = ApplyCoreMethodUtil.getCharSeq(_args[0]).toStringInstance();
        LongInfo lg_;
        int radix_ = getRadix(_list, _args);
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
    private static Struct parseInt(StringList _list, Struct[] _args, boolean _exception) {
        if (!(_args[0] instanceof CharSequenceStruct)) {
            if (!_exception) {
                return(NullStruct.NULL_VALUE);
            }
            return null;
        }
        String one_ = ApplyCoreMethodUtil.getCharSeq(_args[0]).toStringInstance();
        LongInfo lg_;
        int radix_ = getRadix(_list, _args);
        lg_ = NumParsers.parseLong(one_, radix_);
        if (lg_.outOfRange(Integer.MIN_VALUE,Integer.MAX_VALUE)) {
            if (!_exception) {
                return(NullStruct.NULL_VALUE);
            }
            return null;
        } else {
            return(new IntStruct((int) lg_.getValue()));
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
        String one_ = ApplyCoreMethodUtil.getCharSeq(_args[0]).toStringInstance();
        LongInfo lg_;
        int radix_ = getRadix(_list, _args);
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
    private static Struct parseShort(StringList _list, Struct[] _args, boolean _exception) {
        if (!(_args[0] instanceof CharSequenceStruct)) {
            if (!_exception) {
                return(NullStruct.NULL_VALUE);
            }
            return null;
        }
        String one_ = ApplyCoreMethodUtil.getCharSeq(_args[0]).toStringInstance();
        LongInfo lg_;
        int radix_ = getRadix(_list, _args);
        lg_ = NumParsers.parseLong(one_, radix_);
        if (lg_.outOfRange(Short.MIN_VALUE,Short.MAX_VALUE)) {
            if (!_exception) {
                return(NullStruct.NULL_VALUE);
            }
            return null;
        } else {
            return(new ShortStruct((short) lg_.getValue()));
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
        String one_ = ApplyCoreMethodUtil.getCharSeq(_args[0]).toStringInstance();
        LongInfo lg_;
        int radix_ = getRadix(_list, _args);
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
    private static Struct parseByte(StringList _list, Struct[] _args, boolean _exception) {
        if (!(_args[0] instanceof CharSequenceStruct)) {
            if (!_exception) {
                return(NullStruct.NULL_VALUE);
            }
            return null;
        }
        String one_ = ApplyCoreMethodUtil.getCharSeq(_args[0]).toStringInstance();
        LongInfo lg_;
        int radix_ = getRadix(_list, _args);
        lg_ = NumParsers.parseLong(one_, radix_);
        if (lg_.outOfRange(Byte.MIN_VALUE,Byte.MAX_VALUE)) {
            if (!_exception) {
                return(NullStruct.NULL_VALUE);
            }
            return null;
        } else {
            return(new ByteStruct((byte)lg_.getValue()));
        }
    }

    private static int getRadix(StringList _list, Struct[] _args) {
        int radix_ = DEFAULT_RADIX;
        if (_list.size() != 1) {
            radix_ = (ClassArgumentMatching.convertToNumber(_args[1])).intStruct();
        }
        return radix_;
    }

    public static BooleanStruct quickCalculateLowerNb(Struct _a, Struct _b) {
        if (_a instanceof DoubleStruct || _a instanceof FloatStruct || _b instanceof DoubleStruct || _b instanceof FloatStruct) {
            return BooleanStruct.of(ClassArgumentMatching.convertToNumber(_a).doubleStruct() < ClassArgumentMatching.convertToNumber(_b).doubleStruct());
        }
        return BooleanStruct.of(ClassArgumentMatching.convertToNumber(_a).longStruct() < ClassArgumentMatching.convertToNumber(_b).longStruct());
    }

    public static BooleanStruct quickCalculateGreaterNb(Struct _a, Struct _b) {
        if (_a instanceof DoubleStruct || _a instanceof FloatStruct || _b instanceof DoubleStruct || _b instanceof FloatStruct) {
            return BooleanStruct.of(ClassArgumentMatching.convertToNumber(_a).doubleStruct() > ClassArgumentMatching.convertToNumber(_b).doubleStruct());
        }
        return BooleanStruct.of(ClassArgumentMatching.convertToNumber(_a).longStruct() > ClassArgumentMatching.convertToNumber(_b).longStruct());
    }
    public static BooleanStruct quickCalculateLowerStr(Struct _a, Struct _b) {
        String first_ = ApplyCoreMethodUtil.getCharSeq(_a).toStringInstance();
        String second_ = ApplyCoreMethodUtil.getCharSeq(_b).toStringInstance();
        return BooleanStruct.of(first_.compareTo(second_) < 0);
    }

    public static BooleanStruct quickCalculateGreaterStr(Struct _a, Struct _b) {
        String first_ = ApplyCoreMethodUtil.getCharSeq(_a).toStringInstance();
        String second_ = ApplyCoreMethodUtil.getCharSeq(_b).toStringInstance();
        return BooleanStruct.of(first_.compareTo(second_) > 0);
    }
    public static NumberStruct idNumber(NumberStruct _a, Analyzable _an,ClassArgumentMatching _order) {
        LgNames stds_ = _an.getStandards();
        return PrimitiveTypeUtil.convertToNumber(_order, _a, stds_);
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
        return PrimitiveTypeUtil.convertToNumber(_order, tmp_, stds_);
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
            return ClassArgumentMatching.convertToBoolean(_a).and(ClassArgumentMatching.convertToBoolean(_b));
        }
        if (_order.matchClass(int_)) {
            int left_ = ClassArgumentMatching.convertToNumber(_a).getInt();
            int right_ = ClassArgumentMatching.convertToNumber(_b).getInt();
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
        long left_ = ClassArgumentMatching.convertToNumber(_a).getLong();
        long right_ = ClassArgumentMatching.convertToNumber(_b).getLong();
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
            return ClassArgumentMatching.convertToBoolean(_a).or(ClassArgumentMatching.convertToBoolean(_b));
        }
        if (_order.matchClass(int_)) {
            int left_ = ClassArgumentMatching.convertToNumber(_a).getInt();
            int right_ = ClassArgumentMatching.convertToNumber(_b).getInt();
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
        long left_ = ClassArgumentMatching.convertToNumber(_a).getLong();
        long right_ = ClassArgumentMatching.convertToNumber(_b).getLong();
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
            return BooleanStruct.of(!ClassArgumentMatching.convertToBoolean(_a).sameReference(ClassArgumentMatching.convertToBoolean(_b)));
        }
        if (_order.matchClass(int_)) {
            int left_ = ClassArgumentMatching.convertToNumber(_a).getInt();
            int right_ = ClassArgumentMatching.convertToNumber(_b).getInt();
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
        long left_ = ClassArgumentMatching.convertToNumber(_a).getLong();
        long right_ = ClassArgumentMatching.convertToNumber(_b).getLong();
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
    private static IntStruct cmpBool(BooleanStruct _one, BooleanStruct _two) {
        if (_one.sameReference(_two)) {
            return new IntStruct(CustList.EQ_CMP);
        }
        if (BooleanStruct.of(true).sameReference(_one)) {
            return new IntStruct(CustList.SWAP_SORT);
        }
        return new IntStruct(CustList.NO_SWAP_SORT);
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
        NumberStruct other_ = ClassArgumentMatching.convertToNumber(_other);
        if (this instanceof DoubleStruct || this instanceof FloatStruct || other_ instanceof DoubleStruct || other_ instanceof FloatStruct) {
            return doubleStruct() == other_.doubleStruct();
        }
        return longStruct() == other_.longStruct();
    }

    @Override
    public StringStruct getDisplayedString(Analyzable _an) {
        return getStringValue();
    }

    public StringStruct exportValue() {
        return getStringValue();
    }

    private StringStruct getStringValue() {
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
