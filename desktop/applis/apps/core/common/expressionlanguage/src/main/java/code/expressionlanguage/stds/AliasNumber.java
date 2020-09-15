package code.expressionlanguage.stds;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.DoubleInfo;
import code.expressionlanguage.common.LongInfo;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.opers.ExecCatOperation;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.structs.*;
import code.util.*;

public final class AliasNumber {
    private String aliasCompareTo;
    private String aliasCompare;
    private String aliasEquals;
    private String aliasToStringMethod;
    private String aliasValueOfMethod;
    private String aliasMaxValueField;
    private String aliasMinValueField;
    private String aliasPlusInfinityField;
    private String aliasMinusInfinityField;
    private String aliasNanField;
    private String aliasBoolean;
    private String aliasByte;
    private String aliasShort;
    private String aliasCharacter;
    private String aliasInteger;
    private String aliasLong;
    private String aliasFloat;
    private String aliasDouble;
    private String aliasNumber;
    private String aliasParseBoolean;
    private String aliasParseByte;
    private String aliasParseShort;
    private String aliasParseInt;
    private String aliasParseLong;
    private String aliasParseFloat;
    private String aliasParseDouble;
    private String aliasParseByteOrNull;
    private String aliasParseShortOrNull;
    private String aliasParseIntOrNull;
    private String aliasParseLongOrNull;
    private String aliasParseFloatOrNull;
    private String aliasParseDoubleOrNull;
    private String aliasBooleanValue;
    private String aliasByteValue;
    private String aliasShortValue;
    private String aliasCharValue;
    private String aliasIntValue;
    private String aliasLongValue;
    private String aliasFloatValue;
    private String aliasDoubleValue;
    private String aliasDigit;
    private String aliasIsDigit;
    private String aliasIsLetter;
    private String aliasIsLetterOrDigit;
    private String aliasIsWordChar;
    private String aliasIsLowerCase;
    private String aliasIsUpperCase;
    private String aliasIsWhitespace;
    private String aliasIsSpace;
    private String aliasIsInfinite;
    private String aliasIsNan;
    private String aliasForDigit;
    private String aliasGetDirectionality;
    private String aliasGetCharType;
    private String aliasToLowerCaseChar;
    private String aliasToUpperCaseChar;
    private AliasParamNumber params = new AliasParamNumber();

    public static void instantiateNumber(ContextEl _cont, ResultErrorStd _res, ConstructorId _method, Struct... _args) {
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
                String one_ = NumParsers.getCharSeq(_args[0]).toStringInstance();
                if (StringList.quickEq(one_, lgNames_.getDisplayedStrings().getTrueString())) {
                    _res.setResult(BooleanStruct.of(true));
                    return;
                }
                _res.setResult(BooleanStruct.of(false));
                return;
            }
            _res.setResult(ClassArgumentMatching.convertToBoolean(_args[0]));
            return;
        }
      if (StringList.quickEq(type_, charType_)) {
          _res.setResult(_args[0]);
          return;
      }
      if (StringList.quickEq(type_, byteType_)) {
          if (StringList.quickEq(list_.first(), stringType_)) {
              parseByte(_res,list_,lgNames_,_args,true);
              return;
          }
          byte one_ = (ClassArgumentMatching.convertToNumber(_args[0])).byteStruct();
          _res.setResult(new ByteStruct(one_));
          return;
      }
      if (StringList.quickEq(type_, shortType_)) {
          if (StringList.quickEq(list_.first(), stringType_)) {
              parseShort(_res,list_,lgNames_,_args,true);
              return;
          }
          short one_ = (ClassArgumentMatching.convertToNumber(_args[0])).shortStruct();
          _res.setResult(new ShortStruct(one_));
          return;
      }
      if (StringList.quickEq(type_, intType_)) {
          if (StringList.quickEq(list_.first(), stringType_)) {
              parseInt(_res,list_,lgNames_,_args,true);
              return;
          }
          int one_ = (ClassArgumentMatching.convertToNumber(_args[0])).intStruct();
          _res.setResult(new IntStruct(one_));
          return;
      }
      if (StringList.quickEq(type_, longType_)) {
          if (StringList.quickEq(list_.first(), stringType_)) {
              parseLong(_res,list_,lgNames_,_args,true);
              return;
          }
          long one_ = (ClassArgumentMatching.convertToNumber(_args[0])).longStruct();
          _res.setResult(new LongStruct(one_));
          return;
      }
      if (StringList.quickEq(type_, floatType_)) {
          if (StringList.quickEq(list_.first(), stringType_)) {
              parseFloat(_res,lgNames_,_args[0],true);
              return;
          }
          float one_ = (ClassArgumentMatching.convertToNumber(_args[0])).floatStruct();
          _res.setResult(new FloatStruct(one_));
          return;
      }
      if (StringList.quickEq(list_.first(), stringType_)) {
          parseDouble(_res,lgNames_,_args[0],true);
          return;
      }
        double one_ = (ClassArgumentMatching.convertToNumber(_args[0])).doubleStruct();
      _res.setResult(new DoubleStruct(one_));
  }

    public static void parseDouble(ResultErrorStd _res, LgNames _stds, Struct _arg, boolean _exception) {
      if (!(_arg instanceof CharSequenceStruct)) {
          if (!_exception) {
              _res.setResult(NullStruct.NULL_VALUE);
              return;
          }
          _res.setError(_stds.getAliasNullPe());
          return;
      }
      String one_ = NumParsers.getCharSeq(_arg).toStringInstance();
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

    public static void parseFloat(ResultErrorStd _res, LgNames _stds, Struct _arg, boolean _exception) {
      if (!(_arg instanceof CharSequenceStruct)) {
          if (!_exception) {
              _res.setResult(NullStruct.NULL_VALUE);
              return;
          }
          _res.setError(_stds.getAliasNullPe());
          return;
      }
      String one_ = NumParsers.getCharSeq(_arg).toStringInstance();
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

    public static void parseLong(ResultErrorStd _res, StringList _list, LgNames _stds, Struct[] _args, boolean _exception) {
      if (!(_args[0] instanceof CharSequenceStruct)) {
          if (!_exception) {
              _res.setResult(NullStruct.NULL_VALUE);
              return;
          }
          _res.setError(_stds.getAliasNullPe());
          return;
      }
      String one_ = NumParsers.getCharSeq(_args[0]).toStringInstance();
      LongInfo lg_;
      int radix_ = NumParsers.getRadix(_list, _args);
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

    public static void parseInt(ResultErrorStd _res, StringList _list, LgNames _stds, Struct[] _args, boolean _exception) {
      if (!(_args[0] instanceof CharSequenceStruct)) {
          if (!_exception) {
              _res.setResult(NullStruct.NULL_VALUE);
              return;
          }
          _res.setError(_stds.getAliasNullPe());
          return;
      }
      String one_ = NumParsers.getCharSeq(_args[0]).toStringInstance();
      LongInfo lg_;
      int radix_ = NumParsers.getRadix(_list, _args);
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

    public static void parseShort(ResultErrorStd _res, StringList _list, LgNames _stds, Struct[] _args, boolean _exception) {
      if (!(_args[0] instanceof CharSequenceStruct)) {
          if (!_exception) {
              _res.setResult(NullStruct.NULL_VALUE);
              return;
          }
          _res.setError(_stds.getAliasNullPe());
          return;
      }
      String one_ = NumParsers.getCharSeq(_args[0]).toStringInstance();
      LongInfo lg_;
      int radix_ = NumParsers.getRadix(_list, _args);
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

    public static void parseByte(ResultErrorStd _res, StringList _list, LgNames _stds, Struct[] _args, boolean _exception) {
      if (!(_args[0] instanceof CharSequenceStruct)) {
          if (!_exception) {
              _res.setResult(NullStruct.NULL_VALUE);
              return;
          }
          _res.setError(_stds.getAliasNullPe());
          return;
      }
      String one_ = NumParsers.getCharSeq(_args[0]).toStringInstance();
      LongInfo lg_;
      int radix_ = NumParsers.getRadix(_list, _args);
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

    public static void calculateNumber(ContextEl _cont, ResultErrorStd _res, ClassMethodId _method, Struct _struct, Struct... _args) {
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
                _res.setResult(NumParsers.cmpBool((ClassArgumentMatching.convertToBoolean(_args[0])),(ClassArgumentMatching.convertToBoolean(_args[1]))));
                return;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasCompareTo())) {
                BooleanStruct instance_ = ClassArgumentMatching.convertToBoolean(_struct);
                _res.setResult(NumParsers.cmpBool(instance_,(ClassArgumentMatching.convertToBoolean(_args[0]))));
                return;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasEquals())) {
                BooleanStruct instance_ = ClassArgumentMatching.convertToBoolean(_struct);
                _res.setResult(BooleanStruct.of(instance_.sameReference(_args[0])));
                return;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasParseBoolean())) {
                StringStruct disp_ = ExecCatOperation.getDisplayable(new Argument(_args[0]),_cont);
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
            StringStruct disp_ = ExecCatOperation.getDisplayable(new Argument(_args[0]),_cont);
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
                if (StringList.quickEq(name_, lgNames_.getAliasToLowerCaseChar())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    _res.setResult(new CharStruct(Character.toLowerCase(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasToUpperCaseChar())) {
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
                _res.setResult(new IntStruct(NumParsers.compare(ClassArgumentMatching.convertToNumber(_args[0]),ClassArgumentMatching.convertToNumber(_args[1]))));
                return;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasCompareTo())) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                _res.setResult(new IntStruct(NumParsers.compare(instance_,ClassArgumentMatching.convertToNumber(_args[0]))));
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
                    short one_ = (ClassArgumentMatching.convertToNumber(_args[0])).shortStruct();
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
                    one_ = (ClassArgumentMatching.convertToNumber(_args[0])).floatStruct();
                    _res.setResult(BooleanStruct.of(Float.isNaN(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsInfinite())) {
                    float one_;
                    one_ = (ClassArgumentMatching.convertToNumber(_args[0])).floatStruct();
                    _res.setResult(BooleanStruct.of(Float.isInfinite(one_)));
                    return;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                    DisplayedStrings dis_ = _cont.getStandards().getDisplayedStrings();
                    NumberStruct nb_ = ClassArgumentMatching.convertToNumber(_args[0]);
                    StringStruct tmp = NumParsers.getFloatString(nb_,dis_.getInfinity(),
                            dis_.getNan(),
                            dis_.getExponent());
                    _res.setResult(tmp);
                    return;
                }
                boolean exc_ = StringList.quickEq(name_, lgNames_.getAliasParseFloat());
                parseFloat(_res, lgNames_, _args[0],exc_);
                return;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasIsNan())) {
                double one_ = NumParsers.asDouble(_struct, list_, _args);
                _res.setResult(BooleanStruct.of(Double.isNaN(one_)));
                return;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasIsInfinite())) {
                double one_ = NumParsers.asDouble(_struct, list_, _args);
                _res.setResult(BooleanStruct.of(Double.isInfinite(one_)));
                return;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                DisplayedStrings dis_ = _cont.getStandards().getDisplayedStrings();
                NumberStruct nb_ = ClassArgumentMatching.convertToNumber(_args[0]);
                StringStruct displayedString = NumParsers.getDoubleString(nb_,dis_.getInfinity(),
                        dis_.getNan(),
                        dis_.getExponent());
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
                _res.setResult(new IntStruct(NumParsers.compareGene(ClassArgumentMatching.convertToNumber(_args[0]), ClassArgumentMatching.convertToNumber(_args[1]))));
                return;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasCompareTo())) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                if (!(_args[0] instanceof  NumberStruct)) {
                    _res.setError(lgNames_.getAliasNullPe());
                    return;
                }
                _res.setResult(new IntStruct(NumParsers.compareGene(instance_, ClassArgumentMatching.convertToNumber(_args[0]))));
                return;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasEquals())) {
                if (list_.size() > 1) {
                    _res.setResult(BooleanStruct.of(NumParsers.sameValue(_args[0],_args[1])));
                    return;
                }
                _res.setResult(BooleanStruct.of(NumParsers.sameValue(_struct,_args[0])));
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
            StringStruct disp_ = ExecCatOperation.getDisplayable(new Argument(_args[0]),_cont);
            _res.setResult(disp_);
        }
    }

    public static BooleanStruct quickCalculateLowerNb(Struct _a, Struct _b) {
        if (NumParsers.isFloatType(_a,_b)) {
            return BooleanStruct.of(ClassArgumentMatching.convertToNumber(_a).doubleStruct() < ClassArgumentMatching.convertToNumber(_b).doubleStruct());
        }
        return BooleanStruct.of(ClassArgumentMatching.convertToNumber(_a).longStruct() < ClassArgumentMatching.convertToNumber(_b).longStruct());
    }

    public static BooleanStruct quickCalculateGreaterNb(Struct _a, Struct _b) {
        if (NumParsers.isFloatType(_a,_b)) {
            return BooleanStruct.of(ClassArgumentMatching.convertToNumber(_a).doubleStruct() > ClassArgumentMatching.convertToNumber(_b).doubleStruct());
        }
        return BooleanStruct.of(ClassArgumentMatching.convertToNumber(_a).longStruct() > ClassArgumentMatching.convertToNumber(_b).longStruct());
    }

    public static BooleanStruct quickCalculateLowerStr(Struct _a, Struct _b) {
        String first_ = NumParsers.getCharSeq(_a).toStringInstance();
        String second_ = NumParsers.getCharSeq(_b).toStringInstance();
        return BooleanStruct.of(first_.compareTo(second_) < 0);
    }

    public static BooleanStruct quickCalculateGreaterStr(Struct _a, Struct _b) {
        String first_ = NumParsers.getCharSeq(_a).toStringInstance();
        String second_ = NumParsers.getCharSeq(_b).toStringInstance();
        return BooleanStruct.of(first_.compareTo(second_) > 0);
    }

    public static NumberStruct idNumber(NumberStruct _a, ClassArgumentMatching _order, LgNames standards) {
        LgNames stds_ = standards;
        return PrimitiveTypeUtil.convertToNumber(_order, _a, stds_);
    }

    public static NumberStruct negBinNumber(NumberStruct _a, AnalyzedPageEl _an, ClassArgumentMatching _order) {
        LgNames stds_ = _an.getStandards();
        return negBinNumber(_a, _order, stds_);
    }

    public static NumberStruct negBinNumber(NumberStruct _a, ContextEl _an, ClassArgumentMatching _order) {
        LgNames stds_ = _an.getStandards();
        return negBinNumber(_a, _order, stds_);
    }

    private static NumberStruct negBinNumber(NumberStruct _a, ClassArgumentMatching _order, LgNames stds_) {
        String intPrim_ = stds_.getAliasPrimInteger();
        if (_order.matchClass(intPrim_)) {
            int left_ = _a.intStruct();
            boolean[] bits_ = NumParsers.toBits(left_);
            int len_ = bits_.length;
            for (int i = 0; i<len_; i++) {
                bits_[i] = !bits_[i];
            }
            return new IntStruct(NumParsers.toInt(bits_));
        }
        long left_ = _a.longStruct();
        boolean[] bits_ = NumParsers.toBits(left_);
        int len_ = bits_.length;
        for (int i = 0; i<len_; i++) {
            bits_[i] = !bits_[i];
        }
        return new LongStruct(NumParsers.toLong(bits_));
    }

    public static NumberStruct calculateIncr(NumberStruct _a, int _dir, ClassArgumentMatching _order, LgNames standards) {
        if (PrimitiveTypeUtil.isByte(_order, standards)) {
            byte left_ = _a.byteStruct();
            left_+=_dir;
            return new ByteStruct(left_);
        }
        if (PrimitiveTypeUtil.isShort(_order, standards)) {
            short left_ = _a.shortStruct();
            left_+=_dir;
            return new ShortStruct(left_);
        }
        if (PrimitiveTypeUtil.isChar(_order, standards)) {
            char left_ = (char)_a.intStruct();
            left_+=_dir;
            return new CharStruct(left_);
        }
        if (PrimitiveTypeUtil.isInt(_order, standards)) {
            int left_ = _a.intStruct();
            int nb_ = left_ + _dir;
            return new IntStruct(nb_);
        }
        if (PrimitiveTypeUtil.isLong(_order, standards)) {
            long left_ = _a.longStruct();
            long nb_ = left_ + _dir;
            return new LongStruct(nb_);
        }
        double left_ = _a.doubleStruct();
        double nb_ = left_ + (double)_dir;
        if (PrimitiveTypeUtil.isFloat(_order, standards)) {
            return new FloatStruct((float)nb_);
        }
        return new DoubleStruct(nb_);
    }

    public static NumberStruct calculateSum(NumberStruct _a, NumberStruct _b, ClassArgumentMatching _order, LgNames standards) {
        if (PrimitiveTypeUtil.isIntOrLess(_order, standards)) {
            int left_ = _a.intStruct();
            int right_ = _b.intStruct();
            int nb_ = left_ + right_;
            return new IntStruct(nb_);
        }
        if (PrimitiveTypeUtil.isLong(_order, standards)) {
            long left_ = _a.longStruct();
            long right_ = _b.longStruct();
            long nb_ = left_ + right_;
            return new LongStruct(nb_);
        }
        double left_ = _a.doubleStruct();
        double right_ = _b.doubleStruct();
        double nb_ = left_ + right_;
        if (PrimitiveTypeUtil.isFloat(_order, standards)) {
            return new FloatStruct((float)nb_);
        }
        return new DoubleStruct(nb_);
    }

    public static NumberStruct opposite(NumberStruct _a, ClassArgumentMatching _order, LgNames standards) {
        NumberStruct tmp_;
        if (PrimitiveTypeUtil.isInt(_order, standards)) {
            tmp_ = new IntStruct(-_a.intStruct());
        } else if (PrimitiveTypeUtil.isLong(_order, standards)) {
            tmp_ = new LongStruct(-_a.longStruct());
        } else if (PrimitiveTypeUtil.isFloat(_order, standards)){
            tmp_ = new FloatStruct(-_a.floatStruct());
        } else {
            tmp_ = new DoubleStruct(-_a.doubleStruct());
        }
        return tmp_;
    }

    public static NumberStruct calculateDiff(NumberStruct _a, NumberStruct _b, ClassArgumentMatching _order, LgNames standards) {
        if (PrimitiveTypeUtil.isIntOrLess(_order, standards)) {
            int left_ = _a.intStruct();
            int right_ = _b.intStruct();
            int nb_ = left_ - right_;
            return new IntStruct(nb_);
        }
        if (PrimitiveTypeUtil.isLong(_order, standards)) {
            long left_ = _a.longStruct();
            long right_ = _b.longStruct();
            long nb_ = left_ - right_;
            return new LongStruct(nb_);
        }
        double left_ = _a.doubleStruct();
        double right_ = _b.doubleStruct();
        double nb_ = left_ - right_;
        if (PrimitiveTypeUtil.isFloat(_order, standards)) {
            return new FloatStruct((float)nb_);
        }
        return new DoubleStruct(nb_);
    }

    public static NumberStruct calculateMult(NumberStruct _a, NumberStruct _b, ClassArgumentMatching _order, LgNames standards) {
        if (PrimitiveTypeUtil.isIntOrLess(_order, standards)) {
            int left_ = _a.intStruct();
            int right_ = _b.intStruct();
            int nb_ = left_ * right_;
            return new IntStruct(nb_);
        }
        if (PrimitiveTypeUtil.isLong(_order, standards)) {
            long left_ = _a.longStruct();
            long right_ = _b.longStruct();
            long nb_ = left_ * right_;
            return new LongStruct(nb_);
        }
        double left_ = _a.doubleStruct();
        double right_ = _b.doubleStruct();
        double nb_ = left_ * right_;
        if (PrimitiveTypeUtil.isFloat(_order, standards)) {
            return new FloatStruct((float)nb_);
        }
        return new DoubleStruct(nb_);
    }

    public static Struct calculateDiv(NumberStruct _a, NumberStruct _b, ClassArgumentMatching _order, LgNames standards) {
        if (PrimitiveTypeUtil.isIntOrLess(_order, standards)) {
            int left_ = _a.intStruct();
            int right_ = _b.intStruct();
            if (right_ == 0) {
                return NullStruct.NULL_VALUE;
            }
            int nb_ = left_ / right_;
            return new IntStruct(nb_);
        }
        if (PrimitiveTypeUtil.isLong(_order, standards)) {
            long left_ = _a.longStruct();
            long right_ = _b.longStruct();
            if (right_ == 0) {
                return NullStruct.NULL_VALUE;
            }
            long nb_ = left_ / right_;
            return new LongStruct(nb_);
        }
        double left_ = _a.doubleStruct();
        double right_ = _b.doubleStruct();
        double nb_ = left_ / right_;
        if (PrimitiveTypeUtil.isFloat(_order, standards)) {
            return new FloatStruct((float)nb_);
        }
        return new DoubleStruct(nb_);
    }

    public static Struct calculateMod(NumberStruct _a, NumberStruct _b, ClassArgumentMatching _order, LgNames standards) {
        if (PrimitiveTypeUtil.isIntOrLess(_order, standards)) {
            int left_ = _a.intStruct();
            int right_ = _b.intStruct();
            if (right_ == 0) {
                return NullStruct.NULL_VALUE;
            }
            int nb_ = left_ % right_;
            return new IntStruct(nb_);
        }
        if (PrimitiveTypeUtil.isLong(_order, standards)) {
            long left_ = _a.longStruct();
            long right_ = _b.longStruct();
            if (right_ == 0) {
                return NullStruct.NULL_VALUE;
            }
            long nb_ = left_ % right_;
            return new LongStruct(nb_);
        }
        double left_ = _a.doubleStruct();
        double right_ = _b.doubleStruct();
        double nb_ = left_ % right_;
        if (PrimitiveTypeUtil.isFloat(_order, standards)) {
            return new FloatStruct((float)nb_);
        }
        return new DoubleStruct(nb_);
    }

    public static Struct calculateAnd(Struct _a, Struct _b, ClassArgumentMatching _order, LgNames standards) {
        LgNames stds_ = standards;
        String bool_ = stds_.getAliasPrimBoolean();
        String int_ = stds_.getAliasPrimInteger();
        if (_order.matchClass(bool_)) {
            return ClassArgumentMatching.convertToBoolean(_a).and(ClassArgumentMatching.convertToBoolean(_b));
        }
        if (_order.matchClass(int_)) {
            int left_ = ClassArgumentMatching.convertToNumber(_a).intStruct();
            int right_ = ClassArgumentMatching.convertToNumber(_b).intStruct();
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
        long left_ = ClassArgumentMatching.convertToNumber(_a).longStruct();
        long right_ = ClassArgumentMatching.convertToNumber(_b).longStruct();
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

    public static Struct calculateOr(Struct _a, Struct _b, ClassArgumentMatching _order, LgNames standards) {
        LgNames stds_ = standards;
        String bool_ = stds_.getAliasPrimBoolean();
        String int_ = stds_.getAliasPrimInteger();
        if (_order.matchClass(bool_)) {
            return ClassArgumentMatching.convertToBoolean(_a).or(ClassArgumentMatching.convertToBoolean(_b));
        }
        if (_order.matchClass(int_)) {
            int left_ = ClassArgumentMatching.convertToNumber(_a).intStruct();
            int right_ = ClassArgumentMatching.convertToNumber(_b).intStruct();
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
        long left_ = ClassArgumentMatching.convertToNumber(_a).longStruct();
        long right_ = ClassArgumentMatching.convertToNumber(_b).longStruct();
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

    public static Struct calculateXor(Struct _a, Struct _b, ClassArgumentMatching _order, LgNames standards) {
        LgNames stds_ = standards;
        String bool_ = stds_.getAliasPrimBoolean();
        String int_ = stds_.getAliasPrimInteger();
        if (_order.matchClass(bool_)) {
            return BooleanStruct.of(!ClassArgumentMatching.convertToBoolean(_a).sameReference(ClassArgumentMatching.convertToBoolean(_b)));
        }
        if (_order.matchClass(int_)) {
            int left_ = ClassArgumentMatching.convertToNumber(_a).intStruct();
            int right_ = ClassArgumentMatching.convertToNumber(_b).intStruct();
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
        long left_ = ClassArgumentMatching.convertToNumber(_a).longStruct();
        long right_ = ClassArgumentMatching.convertToNumber(_b).longStruct();
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

    public static NumberStruct calculateShiftLeft(NumberStruct _a, NumberStruct _b, ClassArgumentMatching _order, LgNames standards) {
        LgNames stds_ = standards;
        String int_ = stds_.getAliasPrimInteger();
        if (_order.matchClass(int_)) {
            int left_ = _a.intStruct();
            int right_ = _b.intStruct();
            boolean[] bitsRight_ = NumParsers.toBits(right_);
            int value_ = NumParsers.toUnsignedInt(bitsRight_,5);
            int power_ = 1;
            for (int i = 0; i< value_; i++) {
                power_ *= 2;
            }
            return new IntStruct(left_*power_);
        }
        long left_ = _a.longStruct();
        long right_ = _b.longStruct();
        boolean[] bitsRight_ = NumParsers.toBits(right_);
        long value_ = NumParsers.toUnsignedLong(bitsRight_,6);
        long power_ = 1;
        for (int i = 0; i< value_; i++) {
            power_ *= 2;
        }
        return new LongStruct(left_*power_);
    }

    public static NumberStruct calculateShiftRight(NumberStruct _a, NumberStruct _b, ClassArgumentMatching _order, LgNames standards) {
        LgNames stds_ = standards;
        String int_ = stds_.getAliasPrimInteger();
        if (_order.matchClass(int_)) {
            int left_ = _a.intStruct();
            int right_ = _b.intStruct();
            boolean[] bitsRight_ = NumParsers.toBits(right_);
            int value_ = NumParsers.toUnsignedInt(bitsRight_,5);
            int power_ = 1;
            for (int i = 0; i< value_; i++) {
                power_ *= 2;
            }
            return new IntStruct(Numbers.quot(left_, power_));
        }
        long left_ = _a.longStruct();
        long right_ = _b.longStruct();
        boolean[] bitsRight_ = NumParsers.toBits(right_);
        long value_ = NumParsers.toUnsignedLong(bitsRight_,6);
        long power_ = 1;
        for (int i = 0; i< value_; i++) {
            power_ *= 2;
        }
        return new LongStruct(Numbers.quot(left_, power_));
    }

    public static NumberStruct calculateBitShiftLeft(NumberStruct _a, NumberStruct _b, ClassArgumentMatching _order, LgNames standards) {
        LgNames stds_ = standards;
        String int_ = stds_.getAliasPrimInteger();
        if (_order.matchClass(int_)) {
            int left_ = _a.intStruct();
            int right_ = _b.intStruct();
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
        long left_ = _a.longStruct();
        long right_ = _b.longStruct();
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

    public static NumberStruct calculateBitShiftRight(NumberStruct _a, NumberStruct _b, ClassArgumentMatching _order, LgNames standards) {
        LgNames stds_ = standards;
        String int_ = stds_.getAliasPrimInteger();
        if (_order.matchClass(int_)) {
            int left_ = _a.intStruct();
            int right_ = _b.intStruct();
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
        long left_ = _a.longStruct();
        long right_ = _b.longStruct();
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

    public static NumberStruct calculateRotateLeft(NumberStruct _a, NumberStruct _b, ClassArgumentMatching _order, LgNames standards) {
        LgNames stds_ = standards;
        String int_ = stds_.getAliasPrimInteger();
        if (_order.matchClass(int_)) {
            int left_ = _a.intStruct();
            int right_ = _b.intStruct();
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
        long left_ = _a.longStruct();
        long right_ = _b.longStruct();
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

    public static NumberStruct calculateRotateRight(NumberStruct _a, NumberStruct _b, ClassArgumentMatching _order, LgNames standards) {
        LgNames stds_ = standards;
        String int_ = stds_.getAliasPrimInteger();
        if (_order.matchClass(int_)) {
            int left_ = _a.intStruct();
            int right_ = _b.intStruct();
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
        long left_ = _a.longStruct();
        long right_ = _b.longStruct();
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

    public void build(LgNames _lgNames) {
        CustList<StandardField> fields_;
        StringList params_;
        StandardMethod method_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        StandardType std_;
        StandardClass stdcl_;
        methods_ = new CustList<StandardMethod>();
        fields_ = new CustList<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        String aliasObject_ = _lgNames.getAliasObject();
        String aliasPrimBoolean_ = _lgNames.getAliasPrimBoolean();
        String aliasPrimByte_ = _lgNames.getAliasPrimByte();
        String aliasPrimShort_ = _lgNames.getAliasPrimShort();
        String aliasPrimChar_ = _lgNames.getAliasPrimChar();
        String aliasPrimInteger_ = _lgNames.getAliasPrimInteger();
        String aliasPrimLong_ = _lgNames.getAliasPrimLong();
        String aliasPrimFloat_ = _lgNames.getAliasPrimFloat();
        String aliasPrimDouble_ = _lgNames.getAliasPrimDouble();
        StringMap<StandardType> standards_ = _lgNames.getStandards();
        std_ = new StandardClass(aliasBoolean, fields_, constructors_, methods_, aliasObject_ , MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasBooleanValue, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(aliasPrimBoolean_, aliasPrimBoolean_);
        method_ = new StandardMethod(aliasCompare, params_, aliasPrimInteger_, false, MethodModifier.STATIC,new StringList(params.getAliasBoolean0Compare0(),params.getAliasBoolean0Compare1()));
        methods_.add( method_);
        params_ = new StringList(aliasBoolean);
        method_ = new StandardMethod(aliasCompareTo, params_, aliasPrimInteger_, false, MethodModifier.NORMAL,new StringList(params.getAliasBoolean0CompareTo0()));
        methods_.add( method_);
        params_ = new StringList(aliasBoolean);
        method_ = new StandardMethod(aliasEquals, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL,new StringList(params.getAliasBoolean0Equals0()));
        methods_.add( method_);
        params_ = new StringList(_lgNames.getAliasString());
        method_ = new StandardMethod(aliasParseBoolean, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasBoolean0ParseBoolean0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToStringMethod, params_, _lgNames.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(aliasPrimBoolean_);
        method_ = new StandardMethod(aliasToStringMethod, params_, _lgNames.getAliasString(), false, MethodModifier.STATIC,new StringList(params.getAliasBoolean0ToStringMethod0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimBoolean_);
        method_ = new StandardMethod(aliasValueOfMethod, params_, aliasBoolean, false, MethodModifier.STATIC,new StringList(params.getAliasBoolean0ValueOfMethod0()));
        methods_.add( method_);
        params_ = new StringList(_lgNames.getAliasString());
        method_ = new StandardMethod(aliasValueOfMethod, params_, aliasBoolean, false, MethodModifier.STATIC,new StringList(params.getAliasBoolean1ValueOfMethod0()));
        methods_.add( method_);
        StandardConstructor ctor_;
        params_ = new StringList(_lgNames.getAliasString());
        ctor_ = new StandardConstructor(params_,false,new StringList(params.getAliasBoolean0Boolean0()));
        constructors_.add(ctor_);
        params_ = new StringList(aliasBoolean);
        ctor_ = new StandardConstructor(params_,false,new StringList(params.getAliasBoolean1Boolean0()));
        constructors_.add(ctor_);
        standards_.addEntry(aliasBoolean, std_);
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<StandardMethod>();
        fields_ = new CustList<StandardField>();
        std_ = new StandardClass(aliasByte, fields_, constructors_, methods_, aliasShort, MethodModifier.FINAL);
        numbersConstructors(_lgNames,constructors_, aliasPrimByte_,new StringList(params.getAliasByte0Byte0()),new StringList(params.getAliasByte1Byte0()));
        numbersValuesMethodsRadix(_lgNames, methods_, aliasPrimByte_, aliasByte, aliasParseByte, new StringList(params.getAliasByte0ToStringMethod0()), new StringList(params.getAliasByte0ParseByte0()), new StringList(params.getAliasByte1ParseByte0(),params.getAliasByte1ParseByte1()), new StringList(params.getAliasByte0CompareTo0()), new StringList(params.getAliasByte0Compare0(),params.getAliasByte0Compare1()));
        numbersSafeParsersMethodsRadix(_lgNames, methods_, aliasByte, aliasParseByteOrNull, new StringList(params.getAliasByte0ParseByteOrNull0()), new StringList(params.getAliasByte1ParseByteOrNull0(),params.getAliasByte1ParseByteOrNull1()));
        numbersValuesFields(fields_, aliasPrimByte_, std_);
        standards_.addEntry(aliasByte, std_);
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<StandardMethod>();
        fields_ = new CustList<StandardField>();
        std_ = new StandardClass(aliasShort, fields_, constructors_, methods_, aliasInteger, MethodModifier.FINAL);
        numbersConstructors(_lgNames,constructors_, aliasPrimShort_,new StringList(params.getAliasShort0Short0()),new StringList(params.getAliasShort1Short0()));
        numbersValuesMethodsRadix(_lgNames, methods_, aliasPrimShort_, aliasShort, aliasParseShort, new StringList(params.getAliasShort0ToStringMethod0()), new StringList(params.getAliasShort0ParseShort0()), new StringList(params.getAliasShort1ParseShort0(),params.getAliasShort1ParseShort1()), new StringList(params.getAliasShort0CompareTo0()), new StringList(params.getAliasShort0Compare0(),params.getAliasShort0Compare1()));
        numbersSafeParsersMethodsRadix(_lgNames, methods_, aliasShort, aliasParseShortOrNull, new StringList(params.getAliasShort0ParseShortOrNull0()), new StringList(params.getAliasShort1ParseShortOrNull0(),params.getAliasShort1ParseShortOrNull1()));
        numbersValuesFields(fields_, aliasPrimShort_, std_);
        standards_.addEntry(aliasShort, std_);
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<StandardMethod>();
        fields_ = new CustList<StandardField>();
        std_ = new StandardClass(aliasInteger, fields_, constructors_, methods_, aliasLong, MethodModifier.FINAL);
        numbersConstructors(_lgNames,constructors_, aliasPrimInteger_,new StringList(params.getAliasInteger0Integer0()),new StringList(params.getAliasInteger1Integer0()));
        numbersValuesMethodsRadix(_lgNames, methods_, aliasPrimInteger_, aliasInteger, aliasParseInt, new StringList(params.getAliasInteger0ToStringMethod0()), new StringList(params.getAliasInteger0ParseInt0()), new StringList(params.getAliasInteger1ParseInt0(),params.getAliasInteger1ParseInt1()), new StringList(params.getAliasInteger0CompareTo0()), new StringList(params.getAliasInteger0Compare0(),params.getAliasInteger0Compare1()));
        numbersSafeParsersMethodsRadix(_lgNames, methods_, aliasInteger, aliasParseIntOrNull, new StringList(params.getAliasInteger0ParseIntOrNull0()), new StringList(params.getAliasInteger1ParseIntOrNull0(),params.getAliasInteger1ParseIntOrNull1()));
        numbersValuesFields(fields_, aliasPrimInteger_, std_);
        standards_.addEntry(aliasInteger, std_);
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<StandardMethod>();
        fields_ = new CustList<StandardField>();
        std_ = new StandardClass(aliasLong, fields_, constructors_, methods_, aliasNumber, MethodModifier.FINAL);
        numbersConstructors(_lgNames,constructors_, aliasPrimLong_,new StringList(params.getAliasLong0Long0()),new StringList(params.getAliasLong1Long0()));
        numbersValuesMethodsRadix(_lgNames, methods_, aliasPrimLong_, aliasLong, aliasParseLong, new StringList(params.getAliasLong0ToStringMethod0()), new StringList(params.getAliasLong0ParseLong0()), new StringList(params.getAliasLong1ParseLong0(),params.getAliasLong1ParseLong1()), new StringList(params.getAliasLong0CompareTo0()), new StringList(params.getAliasLong0Compare0(),params.getAliasLong0Compare1()));
        numbersSafeParsersMethodsRadix(_lgNames, methods_, aliasLong, aliasParseLongOrNull, new StringList(params.getAliasLong0ParseLongOrNull0()), new StringList(params.getAliasLong1ParseLongOrNull0(),params.getAliasLong1ParseLongOrNull1()));
        numbersValuesFields(fields_, aliasPrimLong_, std_);
        standards_.addEntry(aliasLong, std_);
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<StandardMethod>();
        fields_ = new CustList<StandardField>();
        std_ = new StandardClass(aliasFloat, fields_, constructors_, methods_, aliasDouble, MethodModifier.FINAL);
        numbersConstructors(_lgNames,constructors_, aliasPrimFloat_,new StringList(params.getAliasFloat0Float0()),new StringList(params.getAliasFloat1Float0()));
        numbersValuesMethods(_lgNames,methods_, aliasFloat, aliasParseFloat, aliasPrimFloat_, new StringList(params.getAliasFloat0ToStringMethod0()), new StringList(params.getAliasFloat0ParseFloat0()), new StringList(params.getAliasFloat0CompareTo0()), new StringList(params.getAliasFloat0Compare0(),params.getAliasFloat0Compare1()));
        numbersSafeParsersMethods(_lgNames,methods_, aliasFloat, aliasParseFloatOrNull, new StringList(params.getAliasFloat0ParseFloatOrNull0()));
        params_ = new StringList(aliasPrimFloat_);
        method_ = new StandardMethod(aliasIsInfinite, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasFloat0IsInfinite0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimFloat_);
        method_ = new StandardMethod(aliasIsNan, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasFloat0IsNan0()));
        methods_.add( method_);
        numbersDotValuesFields(fields_, aliasPrimFloat_, std_);
        standards_.addEntry(aliasFloat, std_);
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<StandardMethod>();
        fields_ = new CustList<StandardField>();
        std_ = new StandardClass(aliasDouble, fields_, constructors_, methods_, aliasNumber, MethodModifier.FINAL);
        numbersConstructors(_lgNames,constructors_, aliasPrimDouble_,new StringList(params.getAliasDouble0Double0()),new StringList(params.getAliasDouble1Double0()));
        numbersValuesMethods(_lgNames,methods_, aliasDouble, aliasParseDouble, aliasPrimDouble_, new StringList(params.getAliasDouble0ToStringMethod0()), new StringList(params.getAliasDouble0ParseDouble0()), new StringList(params.getAliasDouble0CompareTo0()), new StringList(params.getAliasDouble0Compare0(),params.getAliasDouble0Compare1()));
        numbersSafeParsersMethods(_lgNames,methods_, aliasDouble, aliasParseDoubleOrNull,new StringList(params.getAliasDouble0ParseDoubleOrNull0()));
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsInfinite, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(aliasPrimDouble_);
        method_ = new StandardMethod(aliasIsInfinite, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasDouble0IsInfinite0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsNan, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(aliasPrimDouble_);
        method_ = new StandardMethod(aliasIsNan, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasDouble0IsNan0()));
        methods_.add( method_);
        numbersDotValuesFields(fields_, aliasPrimDouble_, std_);
        standards_.addEntry(aliasDouble, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        std_ = new StandardClass(aliasNumber, fields_, constructors_, methods_, aliasObject_, MethodModifier.ABSTRACT);
        numbersAbsMethods(_lgNames,methods_, aliasNumber);
        standards_.addEntry(aliasNumber, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        stdcl_ = new StandardClass(aliasCharacter, fields_, constructors_, methods_, aliasInteger, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCharValue, params_, aliasPrimChar_, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(aliasCharacter);
        method_ = new StandardMethod(aliasCompareTo, params_, aliasPrimInteger_, false, MethodModifier.NORMAL,new StringList(params.getAliasCharacter0CompareTo0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_, aliasPrimChar_);
        method_ = new StandardMethod(aliasCompare, params_, aliasPrimInteger_, false, MethodModifier.STATIC,new StringList(params.getAliasCharacter0Compare0(),params.getAliasCharacter0Compare1()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_, aliasPrimInteger_);
        method_ = new StandardMethod(aliasDigit, params_, aliasPrimInteger_, false, MethodModifier.STATIC,new StringList(params.getAliasCharacter0Digit0(),params.getAliasCharacter0Digit1()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimInteger_);
        method_ = new StandardMethod(aliasForDigit, params_, aliasPrimChar_, false, MethodModifier.STATIC,new StringList(params.getAliasCharacter0ForDigit0(),params.getAliasCharacter0ForDigit1()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasGetDirectionality, params_, aliasPrimByte_, false, MethodModifier.STATIC,new StringList(params.getAliasCharacter0GetDirectionality0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasGetCharType, params_, aliasPrimByte_, false, MethodModifier.STATIC,new StringList(params.getAliasCharacter0GetType0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasIsDigit, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasCharacter0IsDigit0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasIsLetter, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasCharacter0IsLetter0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasIsLetterOrDigit, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasCharacter0IsLetterOrDigit0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasIsWordChar, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasCharacter0IsWordChar0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasIsWhitespace, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasCharacter0IsWhitespace0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasIsLowerCase, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasCharacter0IsLowerCase0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasIsUpperCase, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasCharacter0IsUpperCase0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasIsSpace, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasCharacter0IsSpace0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasToLowerCaseChar, params_, aliasPrimChar_, false, MethodModifier.STATIC,new StringList(params.getAliasCharacter0ToLowerCaseChar0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasToUpperCaseChar, params_, aliasPrimChar_, false, MethodModifier.STATIC,new StringList(params.getAliasCharacter0ToUpperCaseChar0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasToStringMethod, params_, _lgNames.getAliasString(), false, MethodModifier.STATIC,new StringList(params.getAliasCharacter0ToStringMethod0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        ctor_ = new StandardConstructor(params_, false,new StringList(params.getAliasCharacter0Character0()));
        constructors_.add(ctor_);
        numbersValuesFields(fields_, aliasPrimChar_, stdcl_);
        std_ = stdcl_;
        standards_.addEntry(aliasCharacter, std_);
    }

    private static void numbersSafeParsersMethodsRadix(LgNames _lgNames, CustList<StandardMethod> methods_, String _aliasType, String _aliasParseOrNull, StringList _first, StringList _second) {
        String aliasPrimInteger_ = _lgNames.getAliasPrimInteger();
        StringList params_;
        StandardMethod method_;
        params_ = new StringList(_lgNames.getAliasString());
        method_ = new StandardMethod(_aliasParseOrNull, params_, _aliasType, false, MethodModifier.STATIC, _first);
        methods_.add(method_);
        params_ = new StringList(_lgNames.getAliasString(), aliasPrimInteger_);
        method_ = new StandardMethod(_aliasParseOrNull, params_, _aliasType, false, MethodModifier.STATIC, _second);
        methods_.add(method_);
    }

    private void numbersValuesMethodsRadix(LgNames _lgNames, CustList<StandardMethod> methods_, String _primitive, String _aliasLong, String _aliasParse, StringList _first, StringList _second, StringList _third, StringList _fourth, StringList _fifth) {
        String aliasPrimInteger_ = _lgNames.getAliasPrimInteger();
        StringList params_;
        StandardMethod method_;
        params_ = new StringList(_primitive);
        method_ = new StandardMethod(aliasToStringMethod, params_, _lgNames.getAliasString(), false, MethodModifier.STATIC, _first);
        methods_.add( method_);
        params_ = new StringList(_lgNames.getAliasString());
        method_ = new StandardMethod(_aliasParse, params_, _aliasLong, false, MethodModifier.STATIC, _second);
        methods_.add( method_);
        params_ = new StringList(_lgNames.getAliasString(), aliasPrimInteger_);
        method_ = new StandardMethod(_aliasParse, params_, _aliasLong, false, MethodModifier.STATIC, _third);
        methods_.add( method_);
        params_ = new StringList(_aliasLong);
        method_ = new StandardMethod(aliasCompareTo, params_, aliasPrimInteger_, false, MethodModifier.NORMAL, _fourth);
        methods_.add( method_);
        params_ = new StringList(_primitive, _primitive);
        method_ = new StandardMethod(aliasCompare, params_, aliasPrimInteger_, false, MethodModifier.STATIC, _fifth);
        methods_.add( method_);
    }

    private static void numbersConstructors(LgNames _lgNames, CustList<StandardConstructor> _ctors, String _primitive, StringList _first,StringList _second) {
        StringList params_;
        StandardConstructor ctor_;
        params_ = new StringList(_lgNames.getAliasString());
        ctor_ = new StandardConstructor(params_,false,_first);
        _ctors.add(ctor_);
        params_ = new StringList(_primitive);
        ctor_ = new StandardConstructor(params_,false,_second);
        _ctors.add(ctor_);
    }
    private void numbersDotValuesFields(CustList<StandardField> _fields, String _primitive, StandardType _type) {
        StandardField field_ = new StandardField(aliasMinValueField, _primitive, true, true, _type);
        _fields.add(field_);
        field_ = new StandardField(aliasMaxValueField, _primitive, true, true, _type);
        _fields.add(field_);
        field_ = new StandardField(aliasMinusInfinityField, _primitive, true, true, _type);
        _fields.add(field_);
        field_ = new StandardField(aliasPlusInfinityField, _primitive, true, true, _type);
        _fields.add(field_);
        field_ = new StandardField(aliasNanField, _primitive, true, true, _type);
        _fields.add(field_);
    }
    private void numbersValuesFields(CustList<StandardField> _fields, String _primitive, StandardType _type) {
        StandardField field_ = new StandardField(aliasMinValueField, _primitive, true, true, _type);
        _fields.add(field_);
        field_ = new StandardField(aliasMaxValueField, _primitive, true, true, _type);
        _fields.add(field_);
    }
    private void numbersValuesMethods(LgNames _lgNames, CustList<StandardMethod> _methods, String _owner, String _parserName, String _primitive, StringList _first, StringList _second, StringList _third, StringList _fourth) {
        String aliasPrimInteger_ = _lgNames.getAliasPrimInteger();
        StringList params_;
        StandardMethod method_;
        params_ = new StringList(_primitive);
        method_ = new StandardMethod(aliasToStringMethod, params_, _lgNames.getAliasString(), false, MethodModifier.STATIC, _first);
        _methods.add( method_);
        params_ = new StringList(_lgNames.getAliasString());
        method_ = new StandardMethod(_parserName, params_, _owner, false, MethodModifier.STATIC, _second);
        _methods.add( method_);
        params_ = new StringList(_owner);
        method_ = new StandardMethod(aliasCompareTo, params_, aliasPrimInteger_, false, MethodModifier.NORMAL, _third);
        _methods.add( method_);
        params_ = new StringList(_primitive, _primitive);
        method_ = new StandardMethod(aliasCompare, params_, aliasPrimInteger_, false, MethodModifier.STATIC, _fourth);
        _methods.add( method_);
    }
    private static void numbersSafeParsersMethods(LgNames _lgNames, CustList<StandardMethod> _methods, String _owner, String _parserName, StringList _first) {
        StringList params_;
        StandardMethod method_;
        params_ = new StringList(_lgNames.getAliasString());
        method_ = new StandardMethod(_parserName, params_, _owner, false, MethodModifier.STATIC, _first);
        _methods.add(method_);

    }
    private void numbersAbsMethods(LgNames _lgNames, CustList<StandardMethod> _methods, String _owner) {
        String aliasPrimBoolean_ = _lgNames.getAliasPrimBoolean();
        String aliasPrimByte_ = _lgNames.getAliasPrimByte();
        String aliasPrimShort_ = _lgNames.getAliasPrimShort();
        String aliasPrimInteger_ = _lgNames.getAliasPrimInteger();
        String aliasPrimLong_ = _lgNames.getAliasPrimLong();
        String aliasPrimFloat_ = _lgNames.getAliasPrimFloat();
        String aliasPrimDouble_ = _lgNames.getAliasPrimDouble();
        StringList params_;
        StandardMethod method_;
        params_ = new StringList();
        method_ = new StandardMethod(aliasByteValue, params_, aliasPrimByte_, false, MethodModifier.NORMAL);
        _methods.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasShortValue, params_, aliasPrimShort_, false, MethodModifier.NORMAL);
        _methods.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIntValue, params_, aliasPrimInteger_, false, MethodModifier.NORMAL);
        _methods.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasLongValue, params_, aliasPrimLong_, false, MethodModifier.NORMAL);
        _methods.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasFloatValue, params_, aliasPrimFloat_, false, MethodModifier.NORMAL);
        _methods.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDoubleValue, params_, aliasPrimDouble_, false, MethodModifier.NORMAL);
        _methods.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToStringMethod, params_, _lgNames.getAliasString(), false, MethodModifier.NORMAL);
        _methods.add( method_);
        params_ = new StringList(_owner);
        method_ = new StandardMethod(aliasToStringMethod, params_, _lgNames.getAliasString(), false, MethodModifier.STATIC,new StringList(params.getAliasNumber0ToStringMethod0()));
        _methods.add( method_);
        params_ = new StringList(aliasNumber);
        method_ = new StandardMethod(aliasEquals, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL,new StringList(params.getAliasNumber0Equals0()));
        _methods.add( method_);
        params_ = new StringList(aliasNumber,aliasNumber);
        method_ = new StandardMethod(aliasEquals, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasNumber1Equals0(),params.getAliasNumber1Equals1()));
        _methods.add( method_);
        params_ = new StringList(_owner);
        method_ = new StandardMethod(aliasCompareTo, params_, aliasPrimInteger_, false, MethodModifier.NORMAL,new StringList(params.getAliasNumber0CompareTo0()));
        _methods.add( method_);
        params_ = new StringList(_owner, _owner);
        method_ = new StandardMethod(aliasCompare, params_, aliasPrimInteger_, false, MethodModifier.STATIC,new StringList(params.getAliasNumber0Compare0(),params.getAliasNumber0Compare1()));
        _methods.add( method_);
    }
    public String getAliasCompareTo() {
        return aliasCompareTo;
    }
    public void setAliasCompareTo(String _aliasCompareTo) {
        aliasCompareTo = _aliasCompareTo;
    }
    public String getAliasCompare() {
        return aliasCompare;
    }
    public void setAliasCompare(String _aliasCompare) {
        aliasCompare = _aliasCompare;
    }
    public String getAliasEquals() {
        return aliasEquals;
    }
    public void setAliasEquals(String _aliasEquals) {
        aliasEquals = _aliasEquals;
    }
    public String getAliasToStringMethod() {
        return aliasToStringMethod;
    }
    public void setAliasToStringMethod(String _aliasToString) {
        aliasToStringMethod = _aliasToString;
    }
    public String getAliasValueOfMethod() {
        return aliasValueOfMethod;
    }
    public void setAliasValueOfMethod(String _aliasValueOf) {
        aliasValueOfMethod = _aliasValueOf;
    }
    public String getAliasMaxValueField() {
        return aliasMaxValueField;
    }
    public void setAliasMaxValueField(String _aliasMaxValueField) {
        aliasMaxValueField = _aliasMaxValueField;
    }
    public String getAliasMinValueField() {
        return aliasMinValueField;
    }
    public void setAliasMinValueField(String _aliasMinValueField) {
        aliasMinValueField = _aliasMinValueField;
    }

    public String getAliasPlusInfinityField() {
        return aliasPlusInfinityField;
    }

    public void setAliasPlusInfinityField(String aliasPlusInfinityField) {
        this.aliasPlusInfinityField = aliasPlusInfinityField;
    }

    public String getAliasMinusInfinityField() {
        return aliasMinusInfinityField;
    }

    public void setAliasMinusInfinityField(String aliasMinusInfinityField) {
        this.aliasMinusInfinityField = aliasMinusInfinityField;
    }

    public String getAliasNanField() {
        return aliasNanField;
    }

    public void setAliasNanField(String aliasNanField) {
        this.aliasNanField = aliasNanField;
    }

    public String getAliasBoolean() {
        return aliasBoolean;
    }
    public void setAliasBoolean(String _aliasBoolean) {
        aliasBoolean = _aliasBoolean;
    }
    public String getAliasByte() {
        return aliasByte;
    }
    public void setAliasByte(String _aliasByte) {
        aliasByte = _aliasByte;
    }
    public String getAliasShort() {
        return aliasShort;
    }
    public void setAliasShort(String _aliasShort) {
        aliasShort = _aliasShort;
    }
    public String getAliasCharacter() {
        return aliasCharacter;
    }
    public void setAliasCharacter(String _aliasCharacter) {
        aliasCharacter = _aliasCharacter;
    }
    public String getAliasInteger() {
        return aliasInteger;
    }
    public void setAliasInteger(String _aliasInteger) {
        aliasInteger = _aliasInteger;
    }
    public String getAliasLong() {
        return aliasLong;
    }
    public void setAliasLong(String _aliasLong) {
        aliasLong = _aliasLong;
    }
    public String getAliasFloat() {
        return aliasFloat;
    }
    public void setAliasFloat(String _aliasFloat) {
        aliasFloat = _aliasFloat;
    }
    public String getAliasDouble() {
        return aliasDouble;
    }
    public void setAliasDouble(String _aliasDouble) {
        aliasDouble = _aliasDouble;
    }
    public String getAliasNumber() {
        return aliasNumber;
    }
    public void setAliasNumber(String _aliasNumber) {
        aliasNumber = _aliasNumber;
    }
    public String getAliasParseBoolean() {
        return aliasParseBoolean;
    }
    public void setAliasParseBoolean(String _aliasParseBoolean) {
        aliasParseBoolean = _aliasParseBoolean;
    }
    public String getAliasParseByte() {
        return aliasParseByte;
    }
    public void setAliasParseByte(String _aliasParseByte) {
        aliasParseByte = _aliasParseByte;
    }
    public String getAliasParseShort() {
        return aliasParseShort;
    }
    public void setAliasParseShort(String _aliasParseShort) {
        aliasParseShort = _aliasParseShort;
    }
    public String getAliasParseInt() {
        return aliasParseInt;
    }
    public void setAliasParseInt(String _aliasParseInt) {
        aliasParseInt = _aliasParseInt;
    }
    public String getAliasParseLong() {
        return aliasParseLong;
    }
    public void setAliasParseLong(String _aliasParseLong) {
        aliasParseLong = _aliasParseLong;
    }
    public String getAliasParseFloat() {
        return aliasParseFloat;
    }
    public void setAliasParseFloat(String _aliasParseFloat) {
        aliasParseFloat = _aliasParseFloat;
    }
    public String getAliasParseDouble() {
        return aliasParseDouble;
    }
    public void setAliasParseDouble(String _aliasParseDouble) {
        aliasParseDouble = _aliasParseDouble;
    }
    public String getAliasParseByteOrNull() {
        return aliasParseByteOrNull;
    }
    public void setAliasParseByteOrNull(String _aliasParseByte) {
        aliasParseByteOrNull = _aliasParseByte;
    }
    public String getAliasParseShortOrNull() {
        return aliasParseShortOrNull;
    }
    public void setAliasParseShortOrNull(String _aliasParseShort) {
        aliasParseShortOrNull = _aliasParseShort;
    }
    public String getAliasParseIntOrNull() {
        return aliasParseIntOrNull;
    }
    public void setAliasParseIntOrNull(String _aliasParseInt) {
        aliasParseIntOrNull = _aliasParseInt;
    }
    public String getAliasParseLongOrNull() {
        return aliasParseLongOrNull;
    }
    public void setAliasParseLongOrNull(String _aliasParseLong) {
        aliasParseLongOrNull = _aliasParseLong;
    }
    public String getAliasParseFloatOrNull() {
        return aliasParseFloatOrNull;
    }
    public void setAliasParseFloatOrNull(String _aliasParseFloat) {
        aliasParseFloatOrNull = _aliasParseFloat;
    }
    public String getAliasParseDoubleOrNull() {
        return aliasParseDoubleOrNull;
    }
    public void setAliasParseDoubleOrNull(String _aliasParseDouble) {
        aliasParseDoubleOrNull = _aliasParseDouble;
    }
    public String getAliasBooleanValue() {
        return aliasBooleanValue;
    }
    public void setAliasBooleanValue(String _aliasBooleanValue) {
        aliasBooleanValue = _aliasBooleanValue;
    }
    public String getAliasByteValue() {
        return aliasByteValue;
    }
    public void setAliasByteValue(String _aliasByteValue) {
        aliasByteValue = _aliasByteValue;
    }
    public String getAliasShortValue() {
        return aliasShortValue;
    }
    public void setAliasShortValue(String _aliasShortValue) {
        aliasShortValue = _aliasShortValue;
    }
    public String getAliasCharValue() {
        return aliasCharValue;
    }
    public void setAliasCharValue(String _aliasCharValue) {
        aliasCharValue = _aliasCharValue;
    }
    public String getAliasIntValue() {
        return aliasIntValue;
    }
    public void setAliasIntValue(String _aliasIntValue) {
        aliasIntValue = _aliasIntValue;
    }
    public String getAliasLongValue() {
        return aliasLongValue;
    }
    public void setAliasLongValue(String _aliasLongValue) {
        aliasLongValue = _aliasLongValue;
    }
    public String getAliasFloatValue() {
        return aliasFloatValue;
    }
    public void setAliasFloatValue(String _aliasFloatValue) {
        aliasFloatValue = _aliasFloatValue;
    }
    public String getAliasDoubleValue() {
        return aliasDoubleValue;
    }
    public void setAliasDoubleValue(String _aliasDoubleValue) {
        aliasDoubleValue = _aliasDoubleValue;
    }
    public String getAliasDigit() {
        return aliasDigit;
    }
    public void setAliasDigit(String _aliasDigit) {
        aliasDigit = _aliasDigit;
    }
    public String getAliasIsDigit() {
        return aliasIsDigit;
    }
    public void setAliasIsDigit(String _aliasIsDigit) {
        aliasIsDigit = _aliasIsDigit;
    }
    public String getAliasIsLetter() {
        return aliasIsLetter;
    }
    public void setAliasIsLetter(String _aliasIsLetter) {
        aliasIsLetter = _aliasIsLetter;
    }
    public String getAliasIsLetterOrDigit() {
        return aliasIsLetterOrDigit;
    }
    public void setAliasIsLetterOrDigit(String _aliasIsLetterOrDigit) {
        aliasIsLetterOrDigit = _aliasIsLetterOrDigit;
    }
    public String getAliasIsWordChar() {
        return aliasIsWordChar;
    }
    public void setAliasIsWordChar(String _aliasIsWordChar) {
        aliasIsWordChar = _aliasIsWordChar;
    }
    public String getAliasIsLowerCase() {
        return aliasIsLowerCase;
    }
    public void setAliasIsLowerCase(String _aliasIsLowerCase) {
        aliasIsLowerCase = _aliasIsLowerCase;
    }
    public String getAliasIsUpperCase() {
        return aliasIsUpperCase;
    }
    public void setAliasIsUpperCase(String _aliasIsUpperCase) {
        aliasIsUpperCase = _aliasIsUpperCase;
    }
    public String getAliasIsWhitespace() {
        return aliasIsWhitespace;
    }
    public void setAliasIsWhitespace(String _aliasIsWhitespace) {
        aliasIsWhitespace = _aliasIsWhitespace;
    }
    public String getAliasIsSpace() {
        return aliasIsSpace;
    }
    public void setAliasIsSpace(String _aliasIsSpace) {
        aliasIsSpace = _aliasIsSpace;
    }
    public String getAliasIsInfinite() {
        return aliasIsInfinite;
    }
    public void setAliasIsInfinite(String _aliasIsInfinite) {
        aliasIsInfinite = _aliasIsInfinite;
    }
    public String getAliasIsNan() {
        return aliasIsNan;
    }
    public void setAliasIsNan(String _aliasIsNan) {
        aliasIsNan = _aliasIsNan;
    }
    public String getAliasForDigit() {
        return aliasForDigit;
    }
    public void setAliasForDigit(String _aliasForDigit) {
        aliasForDigit = _aliasForDigit;
    }
    public String getAliasGetDirectionality() {
        return aliasGetDirectionality;
    }
    public void setAliasGetDirectionality(String _aliasGetDirectionality) {
        aliasGetDirectionality = _aliasGetDirectionality;
    }
    public String getAliasGetCharType() {
        return aliasGetCharType;
    }
    public void setAliasGetCharType(String _aliasGetType) {
        aliasGetCharType = _aliasGetType;
    }

    public String getAliasToLowerCaseChar() {
        return aliasToLowerCaseChar;
    }

    public void setAliasToLowerCaseChar(String aliasToLowerCaseChar) {
        this.aliasToLowerCaseChar = aliasToLowerCaseChar;
    }

    public String getAliasToUpperCaseChar() {
        return aliasToUpperCaseChar;
    }

    public void setAliasToUpperCaseChar(String aliasToUpperCaseChar) {
        this.aliasToUpperCaseChar = aliasToUpperCaseChar;
    }

    public AliasParamNumber getParams() {
        return params;
    }
}
