package code.expressionlanguage.structs;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.PageEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.opers.exec.ExecCatOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.stds.*;
import code.util.CustList;
import code.util.*;
import code.util.StringList;

public abstract class NumberStruct extends WithoutParentStruct implements DisplayableStruct {
    private static final int DEFAULT_RADIX = 10;

    public static void calculateOperator(PageEl _page, ContextEl _cont, ResultErrorStd _res, ClassArgumentMatching _order,
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
                _res.setResult(ClassArgumentMatching.convert(_page,_order, _first,_cont));
                return;
            }
            _res.setResult(ClassArgumentMatching.convert(_page,_order, _second,_cont));
            return;
        }
        if (StringList.quickEq(op_, "&&")) {
            BooleanStruct b_ = ClassArgumentMatching.convertToBoolean(_first);
            if (BooleanStruct.isFalse(b_)) {
                _res.setResult(b_);
                return;
            }
            _res.setResult(ClassArgumentMatching.convertToBoolean(_second));
            return;
        }
        BooleanStruct b_ = ClassArgumentMatching.convertToBoolean(_first);
        if (BooleanStruct.isTrue(b_)) {
            _res.setResult(b_);
            return;
        }
        _res.setResult(ClassArgumentMatching.convertToBoolean(_second));
    }

  private static void catenize(ContextEl _cont, ResultErrorStd _res, Struct _first, Struct _second) {
      Argument arg_ = ExecCatOperation.localSumDiff(new Argument(_first), new Argument(_second), _cont);
      _res.setResult(arg_.getStruct());
  }

  public static void instantiate(ContextEl _cont, ResultErrorStd _res, ConstructorId _method, Struct... _args) {
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
                float one_ = (ClassArgumentMatching.convertToNumber(_args[0])).getFloat();
                _res.setResult(new FloatStruct(one_));
            }
        } else {
            if (StringList.quickEq(list_.first(), stringType_)) {
                parseDouble(_res,lgNames_,_args[0],true);
            } else {
                double one_ = (ClassArgumentMatching.convertToNumber(_args[0])).getDouble();
                _res.setResult(new DoubleStruct(one_));
            }
        }
    }

    public static Struct instantiate(ContextEl _cont, ConstructorId _method, Struct... _args) {
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
                float one_ = (ClassArgumentMatching.convertToNumber(_args[0])).getFloat();
                return (new FloatStruct(one_));
            }
        } else {
            if (StringList.quickEq(list_.first(), stringType_)) {
                return parseDouble(_args[0],true);
            } else {
                double one_ = (ClassArgumentMatching.convertToNumber(_args[0])).getDouble();
                return(new DoubleStruct(one_));
            }
        }
    }
    public static void calculate(ContextEl _cont, ResultErrorStd _res, ClassMethodId _method, Struct _struct, Struct... _args) {
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
                return;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasCompare())) {
                _res.setResult(cmpBool((ClassArgumentMatching.convertToBoolean(_args[0])),(ClassArgumentMatching.convertToBoolean(_args[1]))));
                return;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasCompareTo())) {
                BooleanStruct instance_ = ClassArgumentMatching.convertToBoolean(_struct);
                _res.setResult(cmpBool(instance_,(ClassArgumentMatching.convertToBoolean(_args[0]))));
                return;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasEquals())) {
                BooleanStruct instance_ = ClassArgumentMatching.convertToBoolean(_struct);
                _res.setResult(BooleanStruct.of(instance_.sameReference(_args[0])));
                return;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasParseBoolean())) {
                StringStruct disp_ = ExecCatOperation.getDisplayable(new Argument(_args[0]),_cont).getDisplayedString(_cont);
                if (StringList.quickEq(disp_.getInstance(),lgNames_.getDisplayedStrings().getTrueString())) {
                    _res.setResult(BooleanStruct.of(true));
                    return;
                }
                _res.setResult(BooleanStruct.of(false));
                return;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                if (!list_.isEmpty()) {
                    _res.setResult((ClassArgumentMatching.convertToBoolean(_args[0])).getDisplayedString(_cont));
                    return;
                }
                BooleanStruct instance_ = ClassArgumentMatching.convertToBoolean(_struct);
                _res.setResult(instance_.getDisplayedString(_cont));
                return;
            }
            if (StringList.quickEq(list_.first(), booleanPrimType_)) {
                _res.setResult(_args[0]);
                return;
            }
            StringStruct disp_ = ExecCatOperation.getDisplayable(new Argument(_args[0]),_cont).getDisplayedString(_cont);
            if (StringList.quickEq(disp_.getInstance(),lgNames_.getDisplayedStrings().getTrueString())) {
                _res.setResult(BooleanStruct.of(true));
                return;
            }
            _res.setResult(BooleanStruct.of(false));
            return;
        }
        if (StringList.quickEq(type_, charType_)) {
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
                    _res.setResult(BooleanStruct.of(StringExpUtil.isDigit(one_)));
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
                return;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasCompareTo())) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                _res.setResult(new IntStruct(compare(instance_,ClassArgumentMatching.convertToNumber(_args[0]))));
                return;
            }
            if (StringList.quickEq(type_, byteType_)) {
                if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                    byte one_ = ClassArgumentMatching.convertToNumber(_args[0]).byteStruct();
                    _res.setResult(new StringStruct(Integer.toString(one_)));
                    return;
                }
                boolean exc_ = StringList.quickEq(name_, lgNames_.getAliasParseByte());
                parseByte(_res, list_, lgNames_, _args,exc_);
                return;
            }
            if (StringList.quickEq(type_, shortType_)) {
                if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                    short one_ = (ClassArgumentMatching.convertToNumber(_args[0])).getShort();
                    _res.setResult(new StringStruct(Integer.toString(one_)));
                    return;
                }
                boolean exc_ = StringList.quickEq(name_, lgNames_.getAliasParseShort());
                parseShort(_res, list_, lgNames_, _args,exc_);
                return;
            }
            if (StringList.quickEq(type_, intType_)) {
                if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                    int one_ = (ClassArgumentMatching.convertToNumber(_args[0])).intStruct();
                    _res.setResult(new StringStruct(Integer.toString(one_)));
                    return;
                }
                boolean exc_ = StringList.quickEq(name_, lgNames_.getAliasParseInt());
                parseInt(_res, list_, lgNames_, _args,exc_);
                return;
            }
            if (StringList.quickEq(type_, longType_)) {
                if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                    long one_ = (ClassArgumentMatching.convertToNumber(_args[0])).longStruct();
                    _res.setResult(new StringStruct(Long.toString(one_)));
                    return;
                }
                boolean exc_ = StringList.quickEq(name_, lgNames_.getAliasParseLong());
                parseLong(_res, list_, lgNames_, _args,exc_);
                return;
            }
            if (StringList.quickEq(type_, floatType_)) {
                if (StringList.quickEq(name_, lgNames_.getAliasIsNan())) {
                    float one_;
                    one_ = (ClassArgumentMatching.convertToNumber(_args[0])).getFloat();
                    _res.setResult(BooleanStruct.of(Float.isNaN(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsInfinite())) {
                    float one_;
                    one_ = (ClassArgumentMatching.convertToNumber(_args[0])).getFloat();
                    _res.setResult(BooleanStruct.of(Float.isInfinite(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                    StringStruct tmp = getLow(_cont, _args[0]);
                    _res.setResult(tmp);
                    return;
                }
                boolean exc_ = StringList.quickEq(name_, lgNames_.getAliasParseFloat());
                parseFloat(_res, lgNames_, _args[0],exc_);
                return;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasIsNan())) {
                double one_ = getaDouble(_struct, list_, _args);
                _res.setResult(BooleanStruct.of(Double.isNaN(one_)));
                return;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasIsInfinite())) {
                double one_ = getaDouble(_struct, list_, _args);
                _res.setResult(BooleanStruct.of(Double.isInfinite(one_)));
                return;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                StringStruct displayedString = getGreat(_cont, _args[0]);
                _res.setResult(displayedString);
                return;
            }
            boolean exc_ = StringList.quickEq(name_, lgNames_.getAliasParseDouble());
            parseDouble(_res, lgNames_, _args[0],exc_);
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
                return;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasCompareTo())) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                if (!(_args[0] instanceof  NumberStruct)) {
                    _res.setError(lgNames_.getAliasNullPe());
                    return;
                }
                _res.setResult(new IntStruct(compareGene(instance_, ClassArgumentMatching.convertToNumber(_args[0]))));
                return;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasEquals())) {
                if (list_.size() > 1) {
                    _res.setResult(BooleanStruct.of(NumberStruct.sameValue(_args[0],_args[1])));
                    return;
                }
                _res.setResult(BooleanStruct.of(NumberStruct.sameValue(_struct,_args[0])));
                return;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasByteValue())) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                _res.setResult(new ByteStruct(instance_.byteStruct()));
                return;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasShortValue())) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                _res.setResult(new ShortStruct(instance_.shortStruct()));
                return;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasIntValue())) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                _res.setResult(new IntStruct(instance_.intStruct()));
                return;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasLongValue())) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                _res.setResult(new LongStruct(instance_.longStruct()));
                return;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasFloatValue())) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                _res.setResult(new FloatStruct(instance_.floatStruct()));
                return;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasDoubleValue())) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                _res.setResult(new DoubleStruct(instance_.doubleStruct()));
                return;
            }
            if (list_.isEmpty()) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                _res.setResult(instance_.getDisplayedString(_cont));
                return;
            }
            StringStruct disp_ = ExecCatOperation.getDisplayable(new Argument(_args[0]),_cont)
                    .getDisplayedString(_cont);
            _res.setResult(disp_);
        }
    }

    private static StringStruct getGreat(ContextEl _cont, Struct arg) {
        DisplayedStrings dis_ = _cont.getStandards().getDisplayedStrings();
        return ClassArgumentMatching.convertToNumber(arg).getDoubleString(dis_.getInfinity(),
                dis_.getNan(),
                dis_.getExponent());
    }

    public static Struct calculate(ContextEl _cont, ClassMethodId _method, Struct _struct, Struct... _args) {
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
                StringStruct disp_ = ExecCatOperation.getDisplayable(new Argument(_args[0]),_cont).getDisplayedString(_cont);
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
            StringStruct disp_ = ExecCatOperation.getDisplayable(new Argument(_args[0]), _cont).getDisplayedString(_cont);
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
                    return(BooleanStruct.of(StringExpUtil.isDigit(one_)));
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
                    byte one_ = (ClassArgumentMatching.convertToNumber(_args[0])).byteStruct();
                    return (new StringStruct(Integer.toString(one_)));
                }
                boolean exc_ = StringList.quickEq(name_, lgNames_.getAliasParseByte());
                return parseByte(list_, _args, exc_);
            }
            if (StringList.quickEq(type_, shortType_)) {
                if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                    short one_ = (ClassArgumentMatching.convertToNumber(_args[0])).getShort();
                    return (new StringStruct(Integer.toString(one_)));
                }
                boolean exc_ = StringList.quickEq(name_, lgNames_.getAliasParseShort());
                return parseShort(list_, _args, exc_);
            }
            if (StringList.quickEq(type_, intType_)) {
                if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                    int one_ = (ClassArgumentMatching.convertToNumber(_args[0])).intStruct();
                    return (new StringStruct(Integer.toString(one_)));
                }
                boolean exc_ = StringList.quickEq(name_, lgNames_.getAliasParseInt());
                return parseInt(list_, _args, exc_);
            }
            if (StringList.quickEq(type_, longType_)) {
                if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                    long one_ = (ClassArgumentMatching.convertToNumber(_args[0])).longStruct();
                    return (new StringStruct(Long.toString(one_)));
                }
                boolean exc_ = StringList.quickEq(name_, lgNames_.getAliasParseLong());
                return parseLong(list_, _args, exc_);
            }
            if (StringList.quickEq(type_, floatType_)) {
                if (StringList.quickEq(name_, lgNames_.getAliasIsNan())) {
                    float one_;
                    one_ = (ClassArgumentMatching.convertToNumber(_args[0])).getFloat();
                    return (BooleanStruct.of(Float.isNaN(one_)));
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsInfinite())) {
                    float one_;
                    one_ = (ClassArgumentMatching.convertToNumber(_args[0])).getFloat();
                    return (BooleanStruct.of(Float.isInfinite(one_)));
                }
                if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                    return getLow(_cont, _args[0]);
                }
                boolean exc_ = StringList.quickEq(name_, lgNames_.getAliasParseFloat());
                return parseFloat(_args[0], exc_);
            }
            if (StringList.quickEq(name_, lgNames_.getAliasIsNan())) {
                double one_ = getaDouble(_struct, list_, _args);
                return (BooleanStruct.of(Double.isNaN(one_)));
            }
            if (StringList.quickEq(name_, lgNames_.getAliasIsInfinite())) {
                double one_ = getaDouble(_struct, list_, _args);
                return (BooleanStruct.of(Double.isInfinite(one_)));
            }
            if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                return getGreat(_cont, _args[0]);
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
                if (list_.size() > 1) {
                    return(BooleanStruct.of(NumberStruct.sameValue(_args[0],_args[1])));
                }
                return(BooleanStruct.of(NumberStruct.sameValue(_struct,_args[0])));
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
            return(ExecCatOperation.getDisplayable(new Argument(_args[0]),_cont)
                    .getDisplayedString(_cont));
        }
        return null;
    }

    private static StringStruct getLow(ContextEl _cont, Struct arg) {
        DisplayedStrings dis_ = _cont.getStandards().getDisplayedStrings();
        return ClassArgumentMatching.convertToNumber(arg).getFloatString(dis_.getInfinity(),
                dis_.getNan(),
                dis_.getExponent());
    }

    private static double getaDouble(Struct _struct, StringList list_, Struct[] _args) {
        double one_;
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
        double d_ = 0.0;
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
        double d_ = 0.0;
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
        if (isFloatType(_a,_b)) {
            return BooleanStruct.of(ClassArgumentMatching.convertToNumber(_a).doubleStruct() < ClassArgumentMatching.convertToNumber(_b).doubleStruct());
        }
        return BooleanStruct.of(ClassArgumentMatching.convertToNumber(_a).longStruct() < ClassArgumentMatching.convertToNumber(_b).longStruct());
    }

    public static BooleanStruct quickCalculateGreaterNb(Struct _a, Struct _b) {
        if (isFloatType(_a,_b)) {
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
    public static NumberStruct idNumber(NumberStruct _a, ContextEl _an,ClassArgumentMatching _order) {
        LgNames stds_ = _an.getStandards();
        return PrimitiveTypeUtil.convertToNumber(_order, _a, stds_);
    }
    public static NumberStruct negBinNumber(NumberStruct _a, ContextEl _an,ClassArgumentMatching _order) {
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
    public static NumberStruct calculateIncr(NumberStruct _a, int _dir, ContextEl _an, ClassArgumentMatching _order) {
        if (PrimitiveTypeUtil.isByte(_order,_an)) {
            byte left_ = _a.getByte();
            left_+=_dir;
            return new ByteStruct(left_);
        }
        if (PrimitiveTypeUtil.isShort(_order,_an)) {
            short left_ = _a.getShort();
            left_+=_dir;
            return new ShortStruct(left_);
        }
        if (PrimitiveTypeUtil.isChar(_order,_an)) {
            char left_ = (char)_a.intStruct();
            left_+=_dir;
            return new CharStruct(left_);
        }
        if (PrimitiveTypeUtil.isInt(_order,_an)) {
            int left_ = _a.getInt();
            int nb_ = left_ + _dir;
            return new IntStruct(nb_);
        }
        if (PrimitiveTypeUtil.isLong(_order,_an)) {
            long left_ = _a.getLong();
            long nb_ = left_ + _dir;
            return new LongStruct(nb_);
        }
        double left_ = _a.getDouble();
        double nb_ = left_ + (double)_dir;
        if (PrimitiveTypeUtil.isFloat(_order,_an)) {
            return new FloatStruct((float)nb_);
        }
        return new DoubleStruct(nb_);
    }

    public static NumberStruct calculateSum(NumberStruct _a, NumberStruct _b, ContextEl _an,ClassArgumentMatching _order) {
        if (PrimitiveTypeUtil.isIntOrLess(_order,_an)) {
            int left_ = _a.getInt();
            int right_ = _b.getInt();
            int nb_ = left_ + right_;
            return new IntStruct(nb_);
        }
        if (PrimitiveTypeUtil.isLong(_order,_an)) {
            long left_ = _a.getLong();
            long right_ = _b.getLong();
            long nb_ = left_ + right_;
            return new LongStruct(nb_);
        }
        double left_ = _a.getDouble();
        double right_ = _b.getDouble();
        double nb_ = left_ + right_;
        if (PrimitiveTypeUtil.isFloat(_order,_an)) {
            return new FloatStruct((float)nb_);
        }
        return new DoubleStruct(nb_);
    }

    public static NumberStruct opposite(NumberStruct _a, ContextEl _an,ClassArgumentMatching _order) {
        NumberStruct tmp_;
        if (PrimitiveTypeUtil.isInt(_order, _an)) {
            tmp_ = new IntStruct(-_a.intStruct());
        } else if (PrimitiveTypeUtil.isLong(_order, _an)) {
            tmp_ = new LongStruct(-_a.longStruct());
        } else if (PrimitiveTypeUtil.isFloat(_order,_an)){
            tmp_ = new FloatStruct(-_a.floatStruct());
        } else {
            tmp_ = new DoubleStruct(-_a.doubleStruct());
        }
        return tmp_;
    }
    public static NumberStruct calculateDiff(NumberStruct _a, NumberStruct _b, ContextEl _an,ClassArgumentMatching _order) {
        if (PrimitiveTypeUtil.isIntOrLess(_order,_an)) {
            int left_ = _a.getInt();
            int right_ = _b.getInt();
            int nb_ = left_ - right_;
            return new IntStruct(nb_);
        }
        if (PrimitiveTypeUtil.isLong(_order,_an)) {
            long left_ = _a.getLong();
            long right_ = _b.getLong();
            long nb_ = left_ - right_;
            return new LongStruct(nb_);
        }
        double left_ = _a.getDouble();
        double right_ = _b.getDouble();
        double nb_ = left_ - right_;
        if (PrimitiveTypeUtil.isFloat(_order,_an)) {
            return new FloatStruct((float)nb_);
        }
        return new DoubleStruct(nb_);
    }
    public static NumberStruct calculateMult(NumberStruct _a, NumberStruct _b, ContextEl _an,ClassArgumentMatching _order) {
        if (PrimitiveTypeUtil.isIntOrLess(_order,_an)) {
            int left_ = _a.getInt();
            int right_ = _b.getInt();
            int nb_ = left_ * right_;
            return new IntStruct(nb_);
        }
        if (PrimitiveTypeUtil.isLong(_order,_an)) {
            long left_ = _a.getLong();
            long right_ = _b.getLong();
            long nb_ = left_ * right_;
            return new LongStruct(nb_);
        }
        double left_ = _a.getDouble();
        double right_ = _b.getDouble();
        double nb_ = left_ * right_;
        if (PrimitiveTypeUtil.isFloat(_order,_an)) {
            return new FloatStruct((float)nb_);
        }
        return new DoubleStruct(nb_);
    }
    private static Struct calculateDivEx(NumberStruct _a, NumberStruct _b, ContextEl _an,ClassArgumentMatching _order) {
        LgNames stds_ = _an.getStandards();
        String div_;
        div_ = stds_.getAliasDivisionZero();
        Struct res_ = calculateDiv(_a,_b, _an, _order);
        if (res_ == NullStruct.NULL_VALUE) {
            _an.setException(new ErrorStruct(_an,div_));
        }
        return res_;
    }
    public static Struct calculateDiv(NumberStruct _a, NumberStruct _b, ContextEl _an,ClassArgumentMatching _order) {
        if (PrimitiveTypeUtil.isIntOrLess(_order,_an)) {
            int left_ = _a.getInt();
            int right_ = _b.getInt();
            if (right_ == 0) {
                return NullStruct.NULL_VALUE;
            }
            int nb_ = left_ / right_;
            return new IntStruct(nb_);
        }
        if (PrimitiveTypeUtil.isLong(_order,_an)) {
            long left_ = _a.getLong();
            long right_ = _b.getLong();
            if (right_ == 0) {
                return NullStruct.NULL_VALUE;
            }
            long nb_ = left_ / right_;
            return new LongStruct(nb_);
        }
        double left_ = _a.getDouble();
        double right_ = _b.getDouble();
        double nb_ = left_ / right_;
        if (PrimitiveTypeUtil.isFloat(_order,_an)) {
            return new FloatStruct((float)nb_);
        }
        return new DoubleStruct(nb_);
    }
    private static Struct calculateModEx(NumberStruct _a, NumberStruct _b, ContextEl _an,ClassArgumentMatching _order) {
        LgNames stds_ = _an.getStandards();
        String div_;
        div_ = stds_.getAliasDivisionZero();
        Struct res_ = calculateMod(_a,_b, _an, _order);
        if (res_ == NullStruct.NULL_VALUE) {
            _an.setException(new ErrorStruct(_an,div_));
        }
        return res_;
    }
    public static Struct calculateMod(NumberStruct _a, NumberStruct _b, ContextEl _an,ClassArgumentMatching _order) {
        if (PrimitiveTypeUtil.isIntOrLess(_order,_an)) {
            int left_ = _a.getInt();
            int right_ = _b.getInt();
            if (right_ == 0) {
                return NullStruct.NULL_VALUE;
            }
            int nb_ = left_ % right_;
            return new IntStruct(nb_);
        }
        if (PrimitiveTypeUtil.isLong(_order,_an)) {
            long left_ = _a.getLong();
            long right_ = _b.getLong();
            if (right_ == 0) {
                return NullStruct.NULL_VALUE;
            }
            long nb_ = left_ % right_;
            return new LongStruct(nb_);
        }
        double left_ = _a.getDouble();
        double right_ = _b.getDouble();
        double nb_ = left_ % right_;
        if (PrimitiveTypeUtil.isFloat(_order,_an)) {
            return new FloatStruct((float)nb_);
        }
        return new DoubleStruct(nb_);
    }

    public static Struct calculateAnd(Struct _a, Struct _b, ContextEl _an,ClassArgumentMatching _order) {
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

    public static Struct calculateOr(Struct _a, Struct _b, ContextEl _an,ClassArgumentMatching _order) {
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

    public static Struct calculateXor(Struct _a, Struct _b, ContextEl _an,ClassArgumentMatching _order) {
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
    public static NumberStruct calculateShiftLeft(NumberStruct _a, NumberStruct _b, ContextEl _an,ClassArgumentMatching _order) {
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
    public static NumberStruct calculateShiftRight(NumberStruct _a, NumberStruct _b, ContextEl _an,ClassArgumentMatching _order) {
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
    public static NumberStruct calculateBitShiftLeft(NumberStruct _a, NumberStruct _b, ContextEl _an,ClassArgumentMatching _order) {
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

    public static NumberStruct calculateBitShiftRight(NumberStruct _a, NumberStruct _b, ContextEl _an,ClassArgumentMatching _order) {
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
    public static NumberStruct calculateRotateLeft(NumberStruct _a, NumberStruct _b, ContextEl _an,ClassArgumentMatching _order) {
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

    public static NumberStruct calculateRotateRight(NumberStruct _a, NumberStruct _b, ContextEl _an,ClassArgumentMatching _order) {
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
        if (BooleanStruct.isTrue(_one)) {
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
        return sameReference(this, (NumberStruct) _other);
    }
    private static boolean sameReference(NumberStruct _first, NumberStruct _other) {
        if (isFloatType(_first) && !isFloatType(_other)) {
            return false;
        }
        if (!isFloatType(_first) && isFloatType(_other)) {
            return false;
        }
        return cmpWide(_first, _other);
    }

    public static boolean sameValue(Struct _first,Struct _other) {
        NumberStruct first_ = ClassArgumentMatching.convertToNumber(_first);
        NumberStruct other_ = ClassArgumentMatching.convertToNumber(_other);
        return cmpWide(first_, other_);
    }

    private static boolean cmpWide(NumberStruct _first, NumberStruct _other) {
        if (isFloatType(_first, _other)) {
            return compareFloat(_first, _other);
        }
        return _first.longStruct() == _other.longStruct();
    }

    private static boolean isFloatType(Struct _first, Struct _other) {
        return isFloatType(_first) || isFloatType(_other);
    }

    private static boolean compareFloat(NumberStruct _first, NumberStruct _other) {
        double f_ = _first.doubleStruct();
        double d_ = _other.doubleStruct();
        return Double.compare(f_,d_) == 0;
    }

    private static boolean isFloatType(Struct _value) {
        return _value instanceof DoubleStruct || _value instanceof FloatStruct;
    }

    @Override
    public StringStruct getDisplayedString(ContextEl _an) {
        DisplayedStrings dis_ = _an.getStandards().getDisplayedStrings();
        return getStringValue(dis_.getInfinity(),
                dis_.getNan(),
                dis_.getExponent());
    }

    public StringStruct exportValue(String _infinity, String _nan,String _exp) {
        return getStringValue(_infinity,_nan,_exp);
    }

    private StringStruct getStringValue(String _infinity, String _nan,String _exp) {
        if (this instanceof DoubleStruct) {
            return getDoubleString(_infinity, _nan, _exp);
        }
        if (this instanceof FloatStruct) {
            return getFloatString(_infinity, _nan, _exp);
        }
        return new StringStruct(Long.toString(longStruct()));
    }

    private StringStruct getFloatString(String _infinity, String _nan, String _exp) {
        float f_ = floatStruct();
        if (Float.isInfinite(f_)) {
            if (f_ > 0.0) {
                return new StringStruct(_infinity);
            }
            return new StringStruct(StringList.concat("-",_infinity));
        }
        if (Float.isNaN(f_)) {
            return new StringStruct(_nan);
        }
        return new StringStruct(StringList.replace(Float.toString(f_),"E",_exp));
    }

    private StringStruct getDoubleString(String _infinity, String _nan, String _exp) {
        double d_ = doubleStruct();
        if (Double.isInfinite(d_)) {
            if (d_ > 0.0) {
                return new StringStruct(_infinity);
            }
            return new StringStruct(StringList.concat("-",_infinity));
        }
        if (Double.isNaN(d_)) {
            return new StringStruct(_nan);
        }
        return new StringStruct(StringList.replace(Double.toString(d_),"E",_exp));
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
