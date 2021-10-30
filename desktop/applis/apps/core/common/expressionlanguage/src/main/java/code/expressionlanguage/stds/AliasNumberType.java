package code.expressionlanguage.stds;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.opers.ExecCatOperation;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.functionid.StdClassModifier;
import code.expressionlanguage.structs.*;
import code.maths.litteralcom.MathExpUtil;
import code.util.*;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class AliasNumberType {
    private String aliasCompareTo;
    private String aliasCompare;
    private String aliasEquals;
    private String aliasToStringMethod;
    private String aliasSignum;
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
    private String aliasToBinString;
    private String aliasToOctString;
    private String aliasToHexString;
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
    private final AliasParamNumber params = new AliasParamNumber();

    public static void instantiateNumber(ContextEl _cont, ResultErrorStd _res, ConstructorId _method, StackCall _stackCall, Struct... _args) {
      String type_ = _method.getName();
        LgNames lgNames_ = _cont.getStandards();
        String booleanType_ = lgNames_.getContent().getNbAlias().getAliasBoolean();
        String charType_ = lgNames_.getContent().getNbAlias().getAliasCharacter();
        String stringType_ = lgNames_.getContent().getCharSeq().getAliasString();
        String byteType_ = lgNames_.getContent().getNbAlias().getAliasByte();
        String shortType_ = lgNames_.getContent().getNbAlias().getAliasShort();
        String intType_ = lgNames_.getContent().getNbAlias().getAliasInteger();
        String longType_ = lgNames_.getContent().getNbAlias().getAliasLong();
        String floatType_ = lgNames_.getContent().getNbAlias().getAliasFloat();
        if (StringUtil.quickEq(type_, booleanType_)) {
            if (StringUtil.quickEq(_method.getParametersType(0), stringType_)) {
                String one_ = NumParsers.getCharSeq(_args[0]).toStringInstance();
                if (StringUtil.quickEq(one_, lgNames_.getDisplayedStrings().getTrueString())) {
                    _res.setResult(BooleanStruct.of(true));
                    return;
                }
                _res.setResult(BooleanStruct.of(false));
                return;
            }
            _res.setResult(_args[0]);
            return;
        }
      if (StringUtil.quickEq(type_, charType_)) {
          _res.setResult(_args[0]);
          return;
      }
      if (StringUtil.quickEq(type_, byteType_)) {
          if (StringUtil.quickEq(_method.getParametersType(0), stringType_)) {
              parseByte(_res,_method.getParametersTypesLength(), _args,true, _cont, _stackCall);
              return;
          }
          _res.setResult(_args[0]);
          return;
      }
      if (StringUtil.quickEq(type_, shortType_)) {
          if (StringUtil.quickEq(_method.getParametersType(0), stringType_)) {
              parseShort(_res,_method.getParametersTypesLength(), _args,true, _cont, _stackCall);
              return;
          }
          _res.setResult(_args[0]);
          return;
      }
      if (StringUtil.quickEq(type_, intType_)) {
          if (StringUtil.quickEq(_method.getParametersType(0), stringType_)) {
              parseInt(_res,_method.getParametersTypesLength(), _args,true, _cont, _stackCall);
              return;
          }
          _res.setResult(_args[0]);
          return;
      }
      if (StringUtil.quickEq(type_, longType_)) {
          if (StringUtil.quickEq(_method.getParametersType(0), stringType_)) {
              parseLong(_res,_method.getParametersTypesLength(), _args,true, _cont, _stackCall);
              return;
          }
          _res.setResult(_args[0]);
          return;
      }
      if (StringUtil.quickEq(type_, floatType_)) {
          if (StringUtil.quickEq(_method.getParametersType(0), stringType_)) {
              parseFloat(_res, _args[0],true, _cont, _stackCall);
              return;
          }
          _res.setResult(_args[0]);
          return;
      }
      if (StringUtil.quickEq(_method.getParametersType(0), stringType_)) {
          parseDouble(_res, _args[0],true, _cont, _stackCall);
          return;
      }
      _res.setResult(_args[0]);
  }

    public static void processNumber(ContextEl _cont, ResultErrorStd _res, ClassMethodId _method, Struct _struct, Struct[] _args, StackCall _stackCall) {
        String name_ = _method.getConstraints().getName();
        LgNames lgNames_ = _cont.getStandards();
        if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasCompare())) {
            if (!(_args[0] instanceof NumberStruct)) {
                _stackCall.setCallingState(new CustomFoundExc(getNpe(_cont, _stackCall)));
                return;
            }
            if (!(_args[1] instanceof  NumberStruct)) {
                _stackCall.setCallingState(new CustomFoundExc(getNpe(_cont, _stackCall)));
                return;
            }
            _res.setResult(new IntStruct(NumParsers.compareGene(NumParsers.convertToNumber(_args[0]), NumParsers.convertToNumber(_args[1]))));
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasCompareTo())) {
            NumberStruct instance_ = NumParsers.convertToNumber(_struct);
            if (!(_args[0] instanceof  NumberStruct)) {
                _stackCall.setCallingState(new CustomFoundExc(getNpe(_cont, _stackCall)));
                return;
            }
            _res.setResult(new IntStruct(NumParsers.compareGene(instance_, NumParsers.convertToNumber(_args[0]))));
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasEquals())) {
            if (_method.getConstraints().getParametersTypesLength() > 1) {
                _res.setResult(BooleanStruct.of(NumParsers.sameValue(_args[0],_args[1])));
                return;
            }
            _res.setResult(BooleanStruct.of(NumParsers.sameValue(_struct,_args[0])));
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasByteValue())) {
            NumberStruct instance_ = NumParsers.convertToNumber(_struct);
            _res.setResult(new ByteStruct(instance_.byteStruct()));
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasShortValue())) {
            NumberStruct instance_ = NumParsers.convertToNumber(_struct);
            _res.setResult(new ShortStruct(instance_.shortStruct()));
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasIntValue())) {
            NumberStruct instance_ = NumParsers.convertToNumber(_struct);
            _res.setResult(new IntStruct(instance_.intStruct()));
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasLongValue())) {
            NumberStruct instance_ = NumParsers.convertToNumber(_struct);
            _res.setResult(new LongStruct(instance_.longStruct()));
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasFloatValue())) {
            NumberStruct instance_ = NumParsers.convertToNumber(_struct);
            _res.setResult(new FloatStruct(instance_.floatStruct()));
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasDoubleValue())) {
            NumberStruct instance_ = NumParsers.convertToNumber(_struct);
            _res.setResult(new DoubleStruct(instance_.doubleStruct()));
            return;
        }
        if (_method.getConstraints().getParametersTypesLength() == 0) {
            NumberStruct instance_ = NumParsers.convertToNumber(_struct);
            _res.setResult(instance_.getDisplayedString(_cont));
            return;
        }
        StringStruct disp_ = ExecCatOperation.getDisplayable(new Argument(_args[0]),_cont);
        _res.setResult(disp_);
    }

    public static void processNumbers(ContextEl _cont, ResultErrorStd _res, ClassMethodId _method, Struct _struct, String _type, Struct[] _args, StackCall _stackCall) {
        LgNames lgNames_ = _cont.getStandards();
        String byteType_ = lgNames_.getContent().getNbAlias().getAliasByte();
        String shortType_ = lgNames_.getContent().getNbAlias().getAliasShort();
        String intType_ = lgNames_.getContent().getNbAlias().getAliasInteger();
        String longType_ = lgNames_.getContent().getNbAlias().getAliasLong();
        String floatType_ = lgNames_.getContent().getNbAlias().getAliasFloat();
        String name_ = _method.getConstraints().getName();
        if (StringUtil.quickEq(_type, byteType_)) {
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasCompare())) {
                _res.setResult(new IntStruct(NumParsers.compare(NumParsers.convertToNumber(_args[0]), NumParsers.convertToNumber(_args[1]))));
                return;
            }
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasCompareTo())) {
                NumberStruct instance_ = NumParsers.convertToNumber(_struct);
                _res.setResult(new IntStruct(NumParsers.compare(instance_, NumParsers.convertToNumber(_args[0]))));
                return;
            }
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasToBinString())) {
                byte one_ = NumParsers.convertToNumber(_args[0]).byteStruct();
                _res.setResult(new StringStruct(StringExpUtil.toByteGeneBin(one_)));
                return;
            }
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasToOctString())) {
                byte one_ = NumParsers.convertToNumber(_args[0]).byteStruct();
                _res.setResult(new StringStruct(StringExpUtil.toByteGeneOct(one_)));
                return;
            }
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasToHexString())) {
                byte one_ = NumParsers.convertToNumber(_args[0]).byteStruct();
                _res.setResult(new StringStruct(StringExpUtil.toByteGeneHex(one_)));
                return;
            }
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasToStringMethod())) {
                byte one_ = NumParsers.convertToNumber(_args[0]).byteStruct();
                _res.setResult(new StringStruct(Long.toString(one_)));
                return;
            }
            boolean exc_ = StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasParseByte());
            parseByte(_res, _method.getConstraints().getParametersTypesLength(), _args,exc_, _cont, _stackCall);
            return;
        }
        if (StringUtil.quickEq(_type, shortType_)) {
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasCompare())) {
                _res.setResult(new IntStruct(NumParsers.compare(NumParsers.convertToNumber(_args[0]), NumParsers.convertToNumber(_args[1]))));
                return;
            }
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasCompareTo())) {
                NumberStruct instance_ = NumParsers.convertToNumber(_struct);
                _res.setResult(new IntStruct(NumParsers.compare(instance_, NumParsers.convertToNumber(_args[0]))));
                return;
            }
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasToBinString())) {
                short one_ = NumParsers.convertToNumber(_args[0]).shortStruct();
                _res.setResult(new StringStruct(StringExpUtil.toShortGeneBin(one_)));
                return;
            }
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasToOctString())) {
                short one_ = NumParsers.convertToNumber(_args[0]).shortStruct();
                _res.setResult(new StringStruct(StringExpUtil.toShortGeneOct(one_)));
                return;
            }
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasToHexString())) {
                short one_ = NumParsers.convertToNumber(_args[0]).shortStruct();
                _res.setResult(new StringStruct(StringExpUtil.toShortGeneHex(one_)));
                return;
            }
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasToStringMethod())) {
                short one_ = (NumParsers.convertToNumber(_args[0])).shortStruct();
                _res.setResult(new StringStruct(Long.toString(one_)));
                return;
            }
            boolean exc_ = StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasParseShort());
            parseShort(_res, _method.getConstraints().getParametersTypesLength(), _args,exc_, _cont, _stackCall);
            return;
        }
        if (StringUtil.quickEq(_type, intType_)) {
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasCompare())) {
                _res.setResult(new IntStruct(NumParsers.compare(NumParsers.convertToNumber(_args[0]), NumParsers.convertToNumber(_args[1]))));
                return;
            }
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasCompareTo())) {
                NumberStruct instance_ = NumParsers.convertToNumber(_struct);
                _res.setResult(new IntStruct(NumParsers.compare(instance_, NumParsers.convertToNumber(_args[0]))));
                return;
            }
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasToBinString())) {
                int one_ = NumParsers.convertToNumber(_args[0]).intStruct();
                _res.setResult(new StringStruct(StringExpUtil.toGeneBin(one_)));
                return;
            }
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasToOctString())) {
                int one_ = NumParsers.convertToNumber(_args[0]).intStruct();
                _res.setResult(new StringStruct(StringExpUtil.toGeneOct(one_)));
                return;
            }
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasToHexString())) {
                int one_ = NumParsers.convertToNumber(_args[0]).intStruct();
                _res.setResult(new StringStruct(StringExpUtil.toGeneHex(one_)));
                return;
            }
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasToStringMethod())) {
                int one_ = (NumParsers.convertToNumber(_args[0])).intStruct();
                _res.setResult(new StringStruct(Long.toString(one_)));
                return;
            }
            boolean exc_ = StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasParseInt());
            parseInt(_res, _method.getConstraints().getParametersTypesLength(), _args,exc_, _cont, _stackCall);
            return;
        }
        if (StringUtil.quickEq(_type, longType_)) {
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasCompare())) {
                _res.setResult(new IntStruct(NumParsers.compare(NumParsers.convertToNumber(_args[0]), NumParsers.convertToNumber(_args[1]))));
                return;
            }
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasCompareTo())) {
                NumberStruct instance_ = NumParsers.convertToNumber(_struct);
                _res.setResult(new IntStruct(NumParsers.compare(instance_, NumParsers.convertToNumber(_args[0]))));
                return;
            }
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasSignum())) {
                long nb_ = (NumParsers.convertToNumber(_args[0])).longStruct();
                _res.setResult(new ByteStruct(NumberUtil.signum(nb_)));
                return;
            }
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasToBinString())) {
                long one_ = NumParsers.convertToNumber(_args[0]).longStruct();
                _res.setResult(new StringStruct(StringExpUtil.toLongGeneBin(one_)));
                return;
            }
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasToOctString())) {
                long one_ = NumParsers.convertToNumber(_args[0]).longStruct();
                _res.setResult(new StringStruct(StringExpUtil.toLongGeneOct(one_)));
                return;
            }
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasToHexString())) {
                long one_ = NumParsers.convertToNumber(_args[0]).longStruct();
                _res.setResult(new StringStruct(StringExpUtil.toLongGeneHex(one_)));
                return;
            }
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasToStringMethod())) {
                if (_method.getConstraints().getParametersTypesLength() == 2) {
                    long nb_ = (NumParsers.convertToNumber(_args[0])).longStruct();
                    int radix_ = (NumParsers.convertToNumber(_args[1])).intStruct();
                    _res.setResult(new StringStruct(StringExpUtil.toLongRadix(nb_,radix_)));
                    return;
                }
                long one_ = (NumParsers.convertToNumber(_args[0])).longStruct();
                _res.setResult(new StringStruct(Long.toString(one_)));
                return;
            }
            boolean exc_ = StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasParseLong());
            parseLong(_res, _method.getConstraints().getParametersTypesLength(), _args,exc_, _cont, _stackCall);
            return;
        }
        if (StringUtil.quickEq(_type, floatType_)) {
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasCompare())) {
                _res.setResult(new IntStruct(NumParsers.compareGene(NumParsers.convertToNumber(_args[0]), NumParsers.convertToNumber(_args[1]))));
                return;
            }
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasCompareTo())) {
                NumberStruct instance_ = NumParsers.convertToNumber(_struct);
                _res.setResult(new IntStruct(NumParsers.compareGene(instance_, NumParsers.convertToNumber(_args[0]))));
                return;
            }
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasToStringMethod())) {
                DisplayedStrings dis_ = _cont.getStandards().getDisplayedStrings();
                NumberStruct nb_ = NumParsers.convertToNumber(_args[0]);
                _res.setResult(NumParsers.getFloatString(nb_,dis_.getInfinity(),
                        dis_.getNan(),
                        dis_.getExponent()));
                return;
            }
            boolean exc_ = StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasParseFloat());
            parseFloat(_res, _args[0],exc_, _cont, _stackCall);
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasCompare())) {
            _res.setResult(new IntStruct(NumParsers.compareGene(NumParsers.convertToNumber(_args[0]), NumParsers.convertToNumber(_args[1]))));
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasCompareTo())) {
            NumberStruct instance_ = NumParsers.convertToNumber(_struct);
            _res.setResult(new IntStruct(NumParsers.compareGene(instance_, NumParsers.convertToNumber(_args[0]))));
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasToStringMethod())) {
            DisplayedStrings dis_ = _cont.getStandards().getDisplayedStrings();
            NumberStruct nb_ = NumParsers.convertToNumber(_args[0]);
            _res.setResult(NumParsers.getDoubleString(nb_,dis_.getInfinity(),
                    dis_.getNan(),
                    dis_.getExponent()));
            return;
        }
        boolean exc_ = StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasParseDouble());
        parseDouble(_res, _args[0],exc_, _cont, _stackCall);
    }

    public static void processCharacter(ContextEl _cont, ResultErrorStd _res, ClassMethodId _method, Struct _struct, Struct[] _args, StackCall _stackCall) {
        LgNames lgNames_ = _cont.getStandards();
        String name_ = _method.getConstraints().getName();
        if (_method.getConstraints().isStaticMethod()) {
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasCompare())) {
                char one_ = NumParsers.convertToChar(_args[0]).getChar();
                char two_ = NumParsers.convertToChar(_args[1]).getChar();
                _res.setResult(new IntStruct(NumberUtil.compareLg(one_,two_)));
                return;
            }
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasDigit())) {
                char one_ = NumParsers.convertToChar(_args[0]).getChar();
                int two_ = (NumParsers.convertToNumber(_args[1])).intStruct();
                _res.setResult(new IntStruct(StringDataUtil.digit(one_, two_)));
                return;
            }
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasForDigit())) {
                int one_ = (NumParsers.convertToNumber(_args[0])).intStruct();
                int two_ = (NumParsers.convertToNumber(_args[1])).intStruct();
                _res.setResult(new CharStruct(StringDataUtil.forDigit(one_, two_)));
                return;
            }
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasGetDirectionality())) {
                char one_ = NumParsers.convertToChar(_args[0]).getChar();
                _res.setResult(new ByteStruct(StringDataUtil.getDirectionality(one_)));
                return;
            }
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasGetCharType())) {
                char one_ = NumParsers.convertToChar(_args[0]).getChar();
                _res.setResult(new IntStruct(StringDataUtil.getType(one_)));
                return;
            }
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasIsDigit())) {
                char one_ = NumParsers.convertToChar(_args[0]).getChar();
                _res.setResult(BooleanStruct.of(StringExpUtil.isDigit(one_)));
                return;
            }
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasIsLetter())) {
                char one_ = NumParsers.convertToChar(_args[0]).getChar();
                _res.setResult(BooleanStruct.of(StringDataLetterUtil.isLetter(one_)));
                return;
            }
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasIsLetterOrDigit())) {
                char one_ = NumParsers.convertToChar(_args[0]).getChar();
                _res.setResult(BooleanStruct.of(StringDataUtil.isLetterOrDigit(one_)));
                return;
            }
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasIsLowerCase())) {
                char one_ = NumParsers.convertToChar(_args[0]).getChar();
                _res.setResult(BooleanStruct.of(StringDataUtil.isLowerCase(one_)));
                return;
            }
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasIsUpperCase())) {
                char one_ = NumParsers.convertToChar(_args[0]).getChar();
                _res.setResult(BooleanStruct.of(StringDataUtil.isUpperCase(one_)));
                return;
            }
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasIsSpace())) {
                char one_ = NumParsers.convertToChar(_args[0]).getChar();
                _res.setResult(BooleanStruct.of(StringUtil.isWhitespace(one_)));
                return;
            }
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasIsWhitespace())) {
                char one_ = NumParsers.convertToChar(_args[0]).getChar();
                _res.setResult(BooleanStruct.of(StringUtil.isWhitespace(one_)));
                return;
            }
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasIsWordChar())) {
                char one_ = NumParsers.convertToChar(_args[0]).getChar();
                _res.setResult(BooleanStruct.of(MathExpUtil.isWordChar(one_)));
                return;
            }
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasToLowerCaseChar())) {
                char one_ = NumParsers.convertToChar(_args[0]).getChar();
                _res.setResult(new CharStruct(StringDataUtil.toLowerCase(one_)));
                return;
            }
            if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasToUpperCaseChar())) {
                char one_ = NumParsers.convertToChar(_args[0]).getChar();
                _res.setResult(new CharStruct(StringDataUtil.toUpperCase(one_)));
                return;
            }
            char one_ = NumParsers.convertToChar(_args[0]).getChar();
            _res.setResult(new StringStruct(Character.toString(one_)));
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasCharValue())) {
            _res.setResult(_struct);
            return;
        }
        if (!(_args[0] instanceof CharStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(getNpe(_cont, _stackCall)));
            return;
        }
        CharStruct ch_ = NumParsers.convertToChar(_struct);
        char one_ = ch_.getChar();
        char two_ = ((CharStruct) _args[0]).getChar();
        _res.setResult(new IntStruct(NumberUtil.compareLg(one_,two_)));
    }

    public static void processBoolean(ContextEl _cont, ResultErrorStd _res, ClassMethodId _method, Struct _struct, Struct[] _args) {
        LgNames lgNames_ = _cont.getStandards();
        String booleanPrimType_ = lgNames_.getContent().getPrimTypes().getAliasPrimBoolean();
        String name_ = _method.getConstraints().getName();
        if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasBooleanValue())) {
            _res.setResult(_struct);
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasCompare())) {
            _res.setResult(NumParsers.cmpBool(_args[0],_args[1]));
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasCompareTo())) {
            _res.setResult(NumParsers.cmpBool(_struct,_args[0]));
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasEquals())) {
            _res.setResult(BooleanStruct.of(_struct.sameReference(_args[0])));
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasParseBoolean())) {
            StringStruct disp_ = ExecCatOperation.getDisplayable(new Argument(_args[0]),_cont);
            if (StringUtil.quickEq(disp_.getInstance(),lgNames_.getDisplayedStrings().getTrueString())) {
                _res.setResult(BooleanStruct.of(true));
                return;
            }
            _res.setResult(BooleanStruct.of(false));
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getNbAlias().getAliasToStringMethod())) {
            if (_method.getConstraints().getParametersTypesLength() > 0) {
                _res.setResult((NumParsers.convertToBoolean(_args[0])).getDisplayedString(_cont));
                return;
            }
            BooleanStruct instance_ = NumParsers.convertToBoolean(_struct);
            _res.setResult(instance_.getDisplayedString(_cont));
            return;
        }
        if (StringUtil.quickEq(_method.getConstraints().getParametersType(0), booleanPrimType_)) {
            _res.setResult(_args[0]);
            return;
        }
        StringStruct disp_ = ExecCatOperation.getDisplayable(new Argument(_args[0]),_cont);
        if (StringUtil.quickEq(disp_.getInstance(),lgNames_.getDisplayedStrings().getTrueString())) {
            _res.setResult(BooleanStruct.of(true));
            return;
        }
        _res.setResult(BooleanStruct.of(false));
    }

    public static void parseDouble(ResultErrorStd _res, Struct _arg, boolean _exception, ContextEl _context, StackCall _stackCall) {
      if (!(_arg instanceof CharSequenceStruct)) {
          if (!_exception) {
              _res.setResult(NullStruct.NULL_VALUE);
              return;
          }
          _stackCall.setCallingState(new CustomFoundExc(getNpe(_context, _stackCall)));
          return;
      }
      String one_ = NumParsers.getCharSeq(_arg).toStringInstance();
      DoubleInfo v_ = NumParsers.splitDouble(one_);
      if (!v_.isValid()) {
          if (!_exception) {
              _res.setResult(NullStruct.NULL_VALUE);
              return;
          }
          _stackCall.setCallingState(new CustomFoundExc(getFormatError(_context, one_, _stackCall)));
      } else {
          _res.setResult(new DoubleStruct(v_.getValue()));
      }
  }

    public static void parseFloat(ResultErrorStd _res, Struct _arg, boolean _exception, ContextEl _context, StackCall _stackCall) {
      if (!(_arg instanceof CharSequenceStruct)) {
          if (!_exception) {
              _res.setResult(NullStruct.NULL_VALUE);
              return;
          }
          _stackCall.setCallingState(new CustomFoundExc(getNpe(_context, _stackCall)));
          return;
      }
      String one_ = NumParsers.getCharSeq(_arg).toStringInstance();
      DoubleInfo v_ = NumParsers.splitDouble(one_);
      if (v_.outOfRange(Float.MIN_VALUE,Float.MAX_VALUE)) {
          if (!_exception) {
              _res.setResult(NullStruct.NULL_VALUE);
              return;
          }
          _stackCall.setCallingState(new CustomFoundExc(getFormatError(_context, one_, _stackCall)));
      } else {
          _res.setResult(new FloatStruct((float) v_.getValue()));
      }
  }

    public static void parseLong(ResultErrorStd _res, int _list, Struct[] _args, boolean _exception, ContextEl _context, StackCall _stackCall) {
      if (!(_args[0] instanceof CharSequenceStruct)) {
          if (!_exception) {
              _res.setResult(NullStruct.NULL_VALUE);
              return;
          }
          _stackCall.setCallingState(new CustomFoundExc(getNpe(_context, _stackCall)));
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
          _stackCall.setCallingState(new CustomFoundExc(getFormatError(_context, getNumberRadixMessage(one_, radix_), _stackCall)));
      } else {
          _res.setResult(new LongStruct(lg_.getValue()));
      }
  }

    public static void parseInt(ResultErrorStd _res, int _list, Struct[] _args, boolean _exception, ContextEl _context, StackCall _stackCall) {
      if (!(_args[0] instanceof CharSequenceStruct)) {
          if (!_exception) {
              _res.setResult(NullStruct.NULL_VALUE);
              return;
          }
          _stackCall.setCallingState(new CustomFoundExc(getNpe(_context, _stackCall)));
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
          _stackCall.setCallingState(new CustomFoundExc(getFormatError(_context, getNumberRadixMessage(one_, radix_), _stackCall)));
      } else {
          _res.setResult(new IntStruct((int) lg_.getValue()));
      }
  }

    public static void parseShort(ResultErrorStd _res, int _list, Struct[] _args, boolean _exception, ContextEl _context, StackCall _stackCall) {
      if (!(_args[0] instanceof CharSequenceStruct)) {
          if (!_exception) {
              _res.setResult(NullStruct.NULL_VALUE);
              return;
          }
          _stackCall.setCallingState(new CustomFoundExc(getNpe(_context, _stackCall)));
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
          _stackCall.setCallingState(new CustomFoundExc(getFormatError(_context, getNumberRadixMessage(one_, radix_), _stackCall)));
      } else {
          _res.setResult(new ShortStruct((short) lg_.getValue()));
      }
  }

    public static void parseByte(ResultErrorStd _res, int _list, Struct[] _args, boolean _exception, ContextEl _context, StackCall _stackCall) {
      if (!(_args[0] instanceof CharSequenceStruct)) {
          if (!_exception) {
              _res.setResult(NullStruct.NULL_VALUE);
              return;
          }
          _stackCall.setCallingState(new CustomFoundExc(getNpe(_context, _stackCall)));
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
          _stackCall.setCallingState(new CustomFoundExc(getFormatError(_context, getNumberRadixMessage(one_, radix_), _stackCall)));
      } else {
          _res.setResult(new ByteStruct((byte)lg_.getValue()));
      }
  }

    private static ErrorStruct getNpe(ContextEl _context, StackCall _stackCall) {
        return new ErrorStruct(_context, _context.getStandards().getContent().getCoreNames().getAliasNullPe(), _stackCall);
    }

    private static ErrorStruct getFormatError(ContextEl _context, String _text, StackCall _stackCall) {
        return new ErrorStruct(_context, _text, _context.getStandards().getContent().getCoreNames().getAliasNbFormat(), _stackCall);
    }

    private static String getNumberRadixMessage(String _nb, int _radix) {
        return StringUtil.concat(_nb, ",", Long.toString(_radix));
    }

    public void build(LgNames _lgNames) {
        CustList<CstFieldInfo> fields_;
        StringList params_;
        StandardMethod method_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        StandardType std_;
        StandardClass stdcl_;
        methods_ = new CustList<StandardMethod>();
        fields_ = new CustList<CstFieldInfo>();
        constructors_ = new CustList<StandardConstructor>();
        String aliasObject_ = _lgNames.getContent().getCoreNames().getAliasObject();
        String aliasPrimBoolean_ = _lgNames.getContent().getPrimTypes().getAliasPrimBoolean();
        String aliasPrimByte_ = _lgNames.getContent().getPrimTypes().getAliasPrimByte();
        String aliasPrimShort_ = _lgNames.getContent().getPrimTypes().getAliasPrimShort();
        String aliasPrimChar_ = _lgNames.getContent().getPrimTypes().getAliasPrimChar();
        String aliasPrimInteger_ = _lgNames.getContent().getPrimTypes().getAliasPrimInteger();
        String aliasPrimLong_ = _lgNames.getContent().getPrimTypes().getAliasPrimLong();
        String aliasPrimFloat_ = _lgNames.getContent().getPrimTypes().getAliasPrimFloat();
        String aliasPrimDouble_ = _lgNames.getContent().getPrimTypes().getAliasPrimDouble();
        StringMap<StandardType> standards_ = _lgNames.getStandards();
        std_ = new StandardClass(aliasBoolean, fields_, constructors_, methods_, aliasObject_ , MethodModifier.FINAL, new DfBool());
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
        params_ = new StringList(_lgNames.getContent().getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasParseBoolean, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasBoolean0ParseBoolean0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToStringMethod, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(aliasPrimBoolean_);
        method_ = new StandardMethod(aliasToStringMethod, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList(params.getAliasBoolean0ToStringMethod0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimBoolean_);
        method_ = new StandardMethod(aliasValueOfMethod, params_, aliasBoolean, false, MethodModifier.STATIC,new StringList(params.getAliasBoolean0ValueOfMethod0()));
        methods_.add( method_);
        params_ = new StringList(_lgNames.getContent().getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasValueOfMethod, params_, aliasBoolean, false, MethodModifier.STATIC,new StringList(params.getAliasBoolean1ValueOfMethod0()));
        methods_.add( method_);
        StandardConstructor ctor_;
        params_ = new StringList(_lgNames.getContent().getCharSeq().getAliasString());
        ctor_ = new StandardConstructor(params_,false,new StringList(params.getAliasBoolean0Boolean0()));
        constructors_.add(ctor_);
        params_ = new StringList(aliasBoolean);
        ctor_ = new StandardConstructor(params_,false,new StringList(params.getAliasBoolean1Boolean0()));
        constructors_.add(ctor_);
        standards_.addEntry(aliasBoolean, std_);
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<StandardMethod>();
        fields_ = new CustList<CstFieldInfo>();
        std_ = new StandardClass(aliasByte, fields_, constructors_, methods_, aliasShort, MethodModifier.FINAL, new DfNb(aliasByte));
        numbersConstructors(_lgNames,constructors_, aliasPrimByte_,new StringList(params.getAliasByte0Byte0()),new StringList(params.getAliasByte1Byte0()));
        numbersValuesMethodsRadix(_lgNames, methods_, aliasPrimByte_, aliasByte, aliasParseByte,
                new StringList(params.getAliasByte0ToStringMethod0()), new StringList(params.getAliasByte0ParseByte0()),
                new StringList(params.getAliasByte1ParseByte0(),params.getAliasByte1ParseByte1()),
                new StringList(params.getAliasByte0CompareTo0()), new StringList(params.getAliasByte0Compare0(),params.getAliasByte0Compare1()),
                new StringList(params.getAliasByte0ToBinString0()),new StringList(params.getAliasByte0ToOctString0()),new StringList(params.getAliasByte0ToHexString0()));
        numbersSafeParsersMethodsRadix(_lgNames, methods_, aliasByte, aliasParseByteOrNull, new StringList(params.getAliasByte0ParseByteOrNull0()), new StringList(params.getAliasByte1ParseByteOrNull0(),params.getAliasByte1ParseByteOrNull1()));
        numbersValuesFields(fields_, aliasPrimByte_);
        standards_.addEntry(aliasByte, std_);
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<StandardMethod>();
        fields_ = new CustList<CstFieldInfo>();
        std_ = new StandardClass(aliasShort, fields_, constructors_, methods_, aliasInteger, MethodModifier.FINAL, new DfNb(aliasShort));
        numbersConstructors(_lgNames,constructors_, aliasPrimShort_,new StringList(params.getAliasShort0Short0()),new StringList(params.getAliasShort1Short0()));
        numbersValuesMethodsRadix(_lgNames, methods_, aliasPrimShort_, aliasShort, aliasParseShort,
                new StringList(params.getAliasShort0ToStringMethod0()), new StringList(params.getAliasShort0ParseShort0()),
                new StringList(params.getAliasShort1ParseShort0(),params.getAliasShort1ParseShort1()),
                new StringList(params.getAliasShort0CompareTo0()), new StringList(params.getAliasShort0Compare0(),params.getAliasShort0Compare1()),
                new StringList(params.getAliasShort0ToBinString0()),new StringList(params.getAliasShort0ToOctString0()),new StringList(params.getAliasShort0ToHexString0()));
        numbersSafeParsersMethodsRadix(_lgNames, methods_, aliasShort, aliasParseShortOrNull, new StringList(params.getAliasShort0ParseShortOrNull0()), new StringList(params.getAliasShort1ParseShortOrNull0(),params.getAliasShort1ParseShortOrNull1()));
        numbersValuesFields(fields_, aliasPrimShort_);
        standards_.addEntry(aliasShort, std_);
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<StandardMethod>();
        fields_ = new CustList<CstFieldInfo>();
        std_ = new StandardClass(aliasInteger, fields_, constructors_, methods_, aliasLong, MethodModifier.FINAL, new DfNb(aliasInteger));
        numbersConstructors(_lgNames,constructors_, aliasPrimInteger_,new StringList(params.getAliasInteger0Integer0()),new StringList(params.getAliasInteger1Integer0()));
        numbersValuesMethodsRadix(_lgNames, methods_, aliasPrimInteger_, aliasInteger, aliasParseInt,
                new StringList(params.getAliasInteger0ToStringMethod0()), new StringList(params.getAliasInteger0ParseInt0()),
                new StringList(params.getAliasInteger1ParseInt0(),params.getAliasInteger1ParseInt1()),
                new StringList(params.getAliasInteger0CompareTo0()), new StringList(params.getAliasInteger0Compare0(),params.getAliasInteger0Compare1()),
                new StringList(params.getAliasInteger0ToBinString0()),new StringList(params.getAliasInteger0ToOctString0()),new StringList(params.getAliasInteger0ToHexString0()));
        numbersSafeParsersMethodsRadix(_lgNames, methods_, aliasInteger, aliasParseIntOrNull, new StringList(params.getAliasInteger0ParseIntOrNull0()), new StringList(params.getAliasInteger1ParseIntOrNull0(),params.getAliasInteger1ParseIntOrNull1()));
        numbersValuesFields(fields_, aliasPrimInteger_);
        standards_.addEntry(aliasInteger, std_);
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<StandardMethod>();
        fields_ = new CustList<CstFieldInfo>();
        std_ = new StandardClass(aliasLong, fields_, constructors_, methods_, aliasNumber, MethodModifier.FINAL, new DfNb(aliasLong));
        numbersConstructors(_lgNames,constructors_, aliasPrimLong_,new StringList(params.getAliasLong0Long0()),new StringList(params.getAliasLong1Long0()));
        numbersValuesMethodsRadix(_lgNames, methods_, aliasPrimLong_, aliasLong, aliasParseLong,
                new StringList(params.getAliasLong0ToStringMethod0()), new StringList(params.getAliasLong0ParseLong0()),
                new StringList(params.getAliasLong1ParseLong0(),params.getAliasLong1ParseLong1()),
                new StringList(params.getAliasLong0CompareTo0()), new StringList(params.getAliasLong0Compare0(),params.getAliasLong0Compare1()),
                new StringList(params.getAliasLong0ToBinString0()),new StringList(params.getAliasLong0ToOctString0()),new StringList(params.getAliasLong0ToHexString0()));
        numbersSafeParsersMethodsRadix(_lgNames, methods_, aliasLong, aliasParseLongOrNull, new StringList(params.getAliasLong0ParseLongOrNull0()), new StringList(params.getAliasLong1ParseLongOrNull0(),params.getAliasLong1ParseLongOrNull1()));
        numbersValuesFields(fields_, aliasPrimLong_);
        params_ = new StringList(aliasPrimLong_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasToStringMethod, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList(params.getAliasLong1ToStringMethod0(),params.getAliasLong1ToStringMethod1()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimLong_);
        method_ = new StandardMethod(aliasSignum, params_, aliasPrimByte_, false, MethodModifier.STATIC,new StringList(params.getAliasLong0Signum0()));
        methods_.add( method_);
        standards_.addEntry(aliasLong, std_);
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<StandardMethod>();
        fields_ = new CustList<CstFieldInfo>();
        std_ = new StandardClass(aliasFloat, fields_, constructors_, methods_, aliasDouble, MethodModifier.FINAL, new DfNb(aliasFloat));
        numbersConstructors(_lgNames,constructors_, aliasPrimFloat_,new StringList(params.getAliasFloat0Float0()),new StringList(params.getAliasFloat1Float0()));
        numbersValuesMethods(_lgNames,methods_, aliasFloat, aliasParseFloat, aliasPrimFloat_, new StringList(params.getAliasFloat0ToStringMethod0()), new StringList(params.getAliasFloat0ParseFloat0()), new StringList(params.getAliasFloat0CompareTo0()), new StringList(params.getAliasFloat0Compare0(),params.getAliasFloat0Compare1()));
        numbersSafeParsersMethods(_lgNames,methods_, aliasFloat, aliasParseFloatOrNull, new StringList(params.getAliasFloat0ParseFloatOrNull0()));
        numbersDotValuesFields(fields_, aliasPrimFloat_);
        standards_.addEntry(aliasFloat, std_);
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<StandardMethod>();
        fields_ = new CustList<CstFieldInfo>();
        std_ = new StandardClass(aliasDouble, fields_, constructors_, methods_, aliasNumber, MethodModifier.FINAL, new DfNb(aliasDouble));
        numbersConstructors(_lgNames,constructors_, aliasPrimDouble_,new StringList(params.getAliasDouble0Double0()),new StringList(params.getAliasDouble1Double0()));
        numbersValuesMethods(_lgNames,methods_, aliasDouble, aliasParseDouble, aliasPrimDouble_, new StringList(params.getAliasDouble0ToStringMethod0()), new StringList(params.getAliasDouble0ParseDouble0()), new StringList(params.getAliasDouble0CompareTo0()), new StringList(params.getAliasDouble0Compare0(),params.getAliasDouble0Compare1()));
        numbersSafeParsersMethods(_lgNames,methods_, aliasDouble, aliasParseDoubleOrNull,new StringList(params.getAliasDouble0ParseDoubleOrNull0()));
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsInfinite, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL, new FctDoubleIsInfinite0());
        methods_.add( method_);
        params_ = new StringList(aliasPrimDouble_);
        method_ = new StandardMethod(aliasIsInfinite, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasDouble0IsInfinite0()), new FctDoubleIsInfinite1());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsNan, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL, new FctDoubleIsNan0());
        methods_.add( method_);
        params_ = new StringList(aliasPrimDouble_);
        method_ = new StandardMethod(aliasIsNan, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasDouble0IsNan0()), new FctDoubleIsNan1());
        methods_.add( method_);
        numbersDotValuesFields(fields_, aliasPrimDouble_);
        standards_.addEntry(aliasDouble, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        std_ = new StandardClass(aliasNumber, fields_, constructors_, methods_, aliasObject_, StdClassModifier.ABSTRACT);
        numbersAbsMethods(_lgNames,methods_, aliasNumber);
        standards_.addEntry(aliasNumber, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasCharacter, fields_, constructors_, methods_, aliasInteger, MethodModifier.FINAL, new DfNb(aliasCharacter));
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
        method_ = new StandardMethod(aliasGetCharType, params_, aliasPrimInteger_, false, MethodModifier.STATIC,new StringList(params.getAliasCharacter0GetType0()));
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
        method_ = new StandardMethod(aliasToStringMethod, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList(params.getAliasCharacter0ToStringMethod0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        ctor_ = new StandardConstructor(params_, false,new StringList(params.getAliasCharacter0Character0()));
        constructors_.add(ctor_);
        numbersValuesFields(fields_, aliasPrimChar_);
        std_ = stdcl_;
        standards_.addEntry(aliasCharacter, std_);
    }

    private static void numbersSafeParsersMethodsRadix(LgNames _lgNames, CustList<StandardMethod> _methods, String _aliasType, String _aliasParseOrNull, StringList _first, StringList _second) {
        String aliasPrimInteger_ = _lgNames.getContent().getPrimTypes().getAliasPrimInteger();
        StringList params_;
        StandardMethod method_;
        params_ = new StringList(_lgNames.getContent().getCharSeq().getAliasString());
        method_ = new StandardMethod(_aliasParseOrNull, params_, _aliasType, false, MethodModifier.STATIC, _first);
        _methods.add(method_);
        params_ = new StringList(_lgNames.getContent().getCharSeq().getAliasString(), aliasPrimInteger_);
        method_ = new StandardMethod(_aliasParseOrNull, params_, _aliasType, false, MethodModifier.STATIC, _second);
        _methods.add(method_);
    }

    private void numbersValuesMethodsRadix(LgNames _lgNames, CustList<StandardMethod> _methods, String _primitive, String _aliasLong, String _aliasParse, StringList _first, StringList _second, StringList _third, StringList _fourth, StringList _fifth, StringList _sixth, StringList _seventh, StringList _eighth) {
        String aliasPrimInteger_ = _lgNames.getContent().getPrimTypes().getAliasPrimInteger();
        StringList params_;
        StandardMethod method_;
        params_ = new StringList(_primitive);
        method_ = new StandardMethod(aliasToStringMethod, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.STATIC, _first);
        _methods.add( method_);
        params_ = new StringList(_lgNames.getContent().getCharSeq().getAliasString());
        method_ = new StandardMethod(_aliasParse, params_, _aliasLong, false, MethodModifier.STATIC, _second);
        _methods.add( method_);
        params_ = new StringList(_lgNames.getContent().getCharSeq().getAliasString(), aliasPrimInteger_);
        method_ = new StandardMethod(_aliasParse, params_, _aliasLong, false, MethodModifier.STATIC, _third);
        _methods.add( method_);
        params_ = new StringList(_aliasLong);
        method_ = new StandardMethod(aliasCompareTo, params_, aliasPrimInteger_, false, MethodModifier.NORMAL, _fourth);
        _methods.add( method_);
        params_ = new StringList(_primitive, _primitive);
        method_ = new StandardMethod(aliasCompare, params_, aliasPrimInteger_, false, MethodModifier.STATIC, _fifth);
        _methods.add( method_);
        params_ = new StringList(_primitive);
        method_ = new StandardMethod(aliasToBinString, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.STATIC, _sixth);
        _methods.add( method_);
        params_ = new StringList(_primitive);
        method_ = new StandardMethod(aliasToOctString, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.STATIC, _seventh);
        _methods.add( method_);
        params_ = new StringList(_primitive);
        method_ = new StandardMethod(aliasToHexString, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.STATIC, _eighth);
        _methods.add( method_);
    }

    private static void numbersConstructors(LgNames _lgNames, CustList<StandardConstructor> _ctors, String _primitive, StringList _first,StringList _second) {
        StringList params_;
        StandardConstructor ctor_;
        params_ = new StringList(_lgNames.getContent().getCharSeq().getAliasString());
        ctor_ = new StandardConstructor(params_,false,_first);
        _ctors.add(ctor_);
        params_ = new StringList(_primitive);
        ctor_ = new StandardConstructor(params_,false,_second);
        _ctors.add(ctor_);
    }
    private void numbersDotValuesFields(CustList<CstFieldInfo> _fields, String _primitive) {
        CstFieldInfo field_ = new CstFieldInfo(aliasMinValueField, _primitive);
        _fields.add(field_);
        field_ = new CstFieldInfo(aliasMaxValueField, _primitive);
        _fields.add(field_);
        field_ = new CstFieldInfo(aliasMinusInfinityField, _primitive);
        _fields.add(field_);
        field_ = new CstFieldInfo(aliasPlusInfinityField, _primitive);
        _fields.add(field_);
        field_ = new CstFieldInfo(aliasNanField, _primitive);
        _fields.add(field_);
    }
    private void numbersValuesFields(CustList<CstFieldInfo> _fields, String _primitive) {
        CstFieldInfo field_ = new CstFieldInfo(aliasMinValueField, _primitive);
        _fields.add(field_);
        field_ = new CstFieldInfo(aliasMaxValueField, _primitive);
        _fields.add(field_);
    }
    private void numbersValuesMethods(LgNames _lgNames, CustList<StandardMethod> _methods, String _owner, String _parserName, String _primitive, StringList _first, StringList _second, StringList _third, StringList _fourth) {
        String aliasPrimInteger_ = _lgNames.getContent().getPrimTypes().getAliasPrimInteger();
        StringList params_;
        StandardMethod method_;
        params_ = new StringList(_primitive);
        method_ = new StandardMethod(aliasToStringMethod, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.STATIC, _first);
        _methods.add( method_);
        params_ = new StringList(_lgNames.getContent().getCharSeq().getAliasString());
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
        params_ = new StringList(_lgNames.getContent().getCharSeq().getAliasString());
        method_ = new StandardMethod(_parserName, params_, _owner, false, MethodModifier.STATIC, _first);
        _methods.add(method_);

    }
    private void numbersAbsMethods(LgNames _lgNames, CustList<StandardMethod> _methods, String _owner) {
        String aliasPrimBoolean_ = _lgNames.getContent().getPrimTypes().getAliasPrimBoolean();
        String aliasPrimByte_ = _lgNames.getContent().getPrimTypes().getAliasPrimByte();
        String aliasPrimShort_ = _lgNames.getContent().getPrimTypes().getAliasPrimShort();
        String aliasPrimInteger_ = _lgNames.getContent().getPrimTypes().getAliasPrimInteger();
        String aliasPrimLong_ = _lgNames.getContent().getPrimTypes().getAliasPrimLong();
        String aliasPrimFloat_ = _lgNames.getContent().getPrimTypes().getAliasPrimFloat();
        String aliasPrimDouble_ = _lgNames.getContent().getPrimTypes().getAliasPrimDouble();
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
        method_ = new StandardMethod(aliasToStringMethod, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.NORMAL);
        _methods.add( method_);
        params_ = new StringList(_owner);
        method_ = new StandardMethod(aliasToStringMethod, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList(params.getAliasNumber0ToStringMethod0()));
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

    public String getAliasToBinString() {
        return aliasToBinString;
    }

    public void setAliasToBinString(String _aliasToBinString) {
        this.aliasToBinString = _aliasToBinString;
    }

    public String getAliasToOctString() {
        return aliasToOctString;
    }

    public void setAliasToOctString(String _aliasToOctString) {
        this.aliasToOctString = _aliasToOctString;
    }

    public String getAliasToHexString() {
        return aliasToHexString;
    }

    public void setAliasToHexString(String _aliasToHexString) {
        this.aliasToHexString = _aliasToHexString;
    }

    public String getAliasSignum() {
        return aliasSignum;
    }
    public void setAliasSignum(String _aliasToString) {
        aliasSignum = _aliasToString;
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

    public void setAliasPlusInfinityField(String _aliasPlusInfinityField) {
        this.aliasPlusInfinityField = _aliasPlusInfinityField;
    }

    public String getAliasMinusInfinityField() {
        return aliasMinusInfinityField;
    }

    public void setAliasMinusInfinityField(String _aliasMinusInfinityField) {
        this.aliasMinusInfinityField = _aliasMinusInfinityField;
    }

    public String getAliasNanField() {
        return aliasNanField;
    }

    public void setAliasNanField(String _aliasNanField) {
        this.aliasNanField = _aliasNanField;
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

    public void setAliasToLowerCaseChar(String _aliasToLowerCaseChar) {
        this.aliasToLowerCaseChar = _aliasToLowerCaseChar;
    }

    public String getAliasToUpperCaseChar() {
        return aliasToUpperCaseChar;
    }

    public void setAliasToUpperCaseChar(String _aliasToUpperCaseChar) {
        this.aliasToUpperCaseChar = _aliasToUpperCaseChar;
    }

    public AliasParamNumber getParams() {
        return params;
    }
}
