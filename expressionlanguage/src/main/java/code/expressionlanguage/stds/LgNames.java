package code.expressionlanguage.stds;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.stds.ErrorCat;
import code.expressionlanguage.errors.stds.StdWordError;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.inherits.TypeUtil;
import code.expressionlanguage.methods.AbstractForEachLoop;
import code.expressionlanguage.methods.BracedBlock;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ForEachLoop;
import code.expressionlanguage.methods.PredefinedClasses;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.util.AssignableFrom;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.DimComp;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.ByteStruct;
import code.expressionlanguage.structs.CharStruct;
import code.expressionlanguage.structs.DoubleStruct;
import code.expressionlanguage.structs.EnumerableStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.FloatStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.ReplacementStruct;
import code.expressionlanguage.structs.ShortStruct;
import code.expressionlanguage.structs.SimpleObjectStruct;
import code.expressionlanguage.structs.StringBuilderStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.text.ElUtil;
import code.expressionlanguage.text.NumberInfos;
import code.expressionlanguage.variables.LocalVariable;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public abstract class LgNames {
    protected static final int DEFAULT_RADIX = 10;
    protected static final String EMPTY_STRING = "";
    protected static final String RETURN_LINE = "\n";
    protected static final String SPACE = " ";

    protected static final char PAR_LEFT = '(';
    protected static final char SEP_ARG = ',';
    protected static final char PAR_RIGHT = ')';
    protected static final String DOT = ".";
    protected static final String VARARG_SUFFIX = "...";
    protected static final String LOC_VAR = ".";

    protected static final String PARS = "()";
    private static final byte HEX_BASE = 16;
    private static final char DOT_VAR = '.';
    private static final char EXP_UPP = 'E';
    private static final char EXP = 'e';
    private static final char MINUS_CHAR = '-';

    private static final long MULTMIN_RADIX_TEN = Long.MIN_VALUE / DEFAULT_RADIX;
    private static final long N_MULTMAX_RADIX_TEN = -Long.MAX_VALUE / DEFAULT_RADIX;
    private static final byte MAX_DIGITS_DOUBLE = 18;

    private StringMap<StandardType> standards = new StringMap<StandardType>();

    private StringList predefinedClasses = new StringList();
    private StringList predefinedInterfacesInitOrder = new StringList();

    private AliasCore coreNames = new AliasCore();

    private AliasCharSequence charSeq = new AliasCharSequence();
    private AliasReflection reflect = new AliasReflection();
    private AliasStackTraceElement stackElt = new AliasStackTraceElement();
    private AliasNumber nbAlias = new AliasNumber();
    private AliasMath mathRef = new AliasMath();
    private PrimitiveTypes primTypes = new PrimitiveTypes();
    private AliasPredefinedTypes predefTypes = new AliasPredefinedTypes();
    private String trueString;
    private String falseString;
    private String nullString;
    private String defaultPkg = "";
    /**Called after setters*/
    public void build() {
        coreNames.build(this);
        nbAlias.build(this);
        charSeq.build(this);
        reflect.build(this);
        mathRef.build(this);
        stackElt.build(this);
        primTypes.buildPrimitiveTypes(this);
        buildOther();
    }

    public String toWrapper(String _type) {
        return primTypes.toWrapper(_type);
    }
    public String toPrimitive(String _type) {
        return primTypes.toPrimitive(_type);
    }

    public StringList allPrimitives() {
        StringList list_ = new StringList();
        list_.add(primTypes.getAliasPrimBoolean());
        list_.add(primTypes.getAliasPrimByte());
        list_.add(primTypes.getAliasPrimShort());
        list_.add(primTypes.getAliasPrimChar());
        list_.add(primTypes.getAliasPrimInteger());
        list_.add(primTypes.getAliasPrimLong());
        list_.add(primTypes.getAliasPrimFloat());
        list_.add(primTypes.getAliasPrimDouble());
        list_.add(coreNames.getAliasVoid());
        return list_;
    }
    public void validatePrimitiveContents(ContextEl _cont, StringList _list) {
        for (String k: _list) {
            if (k.isEmpty()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage("empty word");
                err_.setErrCat(ErrorCat.WRITE_PRIMITIVE_WORD);
                _cont.getClasses().addStdError(err_);
                continue;
            }
            if (_cont.getKeyWords().isKeyWord(k)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.concat("key word ", k));
                err_.setErrCat(ErrorCat.WRITE_PRIMITIVE_WORD);
                _cont.getClasses().addStdError(err_);
            }
            for (char c: k.toCharArray()) {
                if (!StringList.isDollarWordChar(c)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.concat("not word char ", Character.toString(c)));
                    err_.setErrCat(ErrorCat.WRITE_PRIMITIVE_WORD);
                    _cont.getClasses().addStdError(err_);
                    break;
                }
            }
            if (ContextEl.isDigit(k.charAt(0))) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.concat("digit ", Character.toString(k.charAt(0))));
                err_.setErrCat(ErrorCat.WRITE_PRIMITIVE_WORD);
                _cont.getClasses().addStdError(err_);
            }
        }
    }
    public void validatePrimitiveDuplicates(ContextEl _cont, StringList _list) {
        StringList keyWords_ = new StringList(_list);
        int size_ = keyWords_.size();
        keyWords_.removeDuplicates();
        if (size_ != keyWords_.size()) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringList.concat("duplicate key words ",_list.display()));
            err_.setErrCat(ErrorCat.DUPLICATE_PRIMITIVE_WORD);
            _cont.getClasses().addStdError(err_);
        }
    }
    public StringList allRefTypes() {
        StringList list_ = new StringList();
        list_.add(getAliasAnnotated());
        list_.add(getAliasAnnotation());
        list_.add(getAliasClass());
        list_.add(getAliasConstructor());
        list_.add(getAliasFct());
        list_.add(getAliasField());
        list_.add(getAliasMethod());
        list_.add(getAliasObjectsUtil());
        list_.add(getAliasClassNotFoundError());
        list_.add(getAliasCustomError());
        list_.add(getAliasErrorInitClass());
        list_.add(getAliasInvokeTarget());
        list_.add(getAliasMath());
        list_.add(getAliasStackTraceElement());
        list_.add(getAliasBadEncode());
        list_.add(getAliasBadIndex());
        list_.add(getAliasDivisionZero());
        list_.add(getAliasStore());
        list_.add(getAliasCast());
        list_.add(getAliasBadSize());
        list_.add(getAliasSof());
        list_.add(getAliasReplacement());
        list_.add(getAliasNullPe());
        list_.add(getAliasBoolean());
        list_.add(getAliasByte());
        list_.add(getAliasCharSequence());
        list_.add(getAliasCharacter());
        list_.add(getAliasDouble());
        list_.add(getAliasError());
        list_.add(getAliasFloat());
        list_.add(getAliasInteger());
        list_.add(getAliasLong());
        list_.add(getAliasNumber());
        list_.add(getAliasObject());
        list_.add(getAliasShort());
        list_.add(getAliasString());
        list_.add(getAliasStringBuilder());
        return list_;
    }
    public void validateRefTypeContents(ContextEl _cont, StringList _list, StringList _prims) {
        StringList allPkgs_ = new StringList();
        for (String k: _list) {
            if (k.isEmpty()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage("empty word");
                err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                _cont.getClasses().addStdError(err_);
                continue;
            }
            for (String p : StringList.splitChars(k, '.')) {
                if (p.isEmpty()) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage("empty word");
                    err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                    _cont.getClasses().addStdError(err_);
                    continue;
                }
                if (_prims.containsStr(p)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.concat("primitive ", p));
                    err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                    _cont.getClasses().addStdError(err_);
                }
                if (_cont.getKeyWords().isKeyWord(p)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.concat("key word ", p));
                    err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                    _cont.getClasses().addStdError(err_);
                }
                for (char c: p.toCharArray()) {
                    if (!StringList.isDollarWordChar(c) && c != '.') {
                        StdWordError err_ = new StdWordError();
                        err_.setMessage(StringList.concat("not word char ", Character.toString(c)));
                        err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                        _cont.getClasses().addStdError(err_);
                        break;
                    }
                }
                if (ContextEl.isDigit(p.charAt(0))) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.concat("digit ", Character.toString(k.charAt(0))));
                    err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                    _cont.getClasses().addStdError(err_);
                }
            }
            String pkg_ = StandardType.getPackagePart(k);
            if (pkg_.isEmpty()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage("empty package");
                err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                _cont.getClasses().addStdError(err_);
            }
            allPkgs_.add(pkg_);
        }
        boolean exNonEmpty_ = false;
        for (String p: allPkgs_) {
            if (StringList.quickEq(defaultPkg, p)) {
                exNonEmpty_ = true;
            }
        }
        if (!exNonEmpty_) {
            //ERROR
            StdWordError err_ = new StdWordError();
            err_.setMessage("empty word");
            err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
            _cont.getClasses().addStdError(err_);
        }
        for (String k: _list) {
            if (defaultPkg.contains(k)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage("containing package");
                err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                _cont.getClasses().addStdError(err_);
            }
        }
    }
    public void validateRefTypeDuplicates(ContextEl _cont, StringList _list) {
        StringList keyWords_ = new StringList(_list);
        int size_ = keyWords_.size();
        keyWords_.removeDuplicates();
        if (size_ != keyWords_.size()) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringList.concat("duplicate key words ",_list.display()));
            err_.setErrCat(ErrorCat.DUPLICATE_TYPE_WORD);
            _cont.getClasses().addStdError(err_);
        }
    }
    public StringMap<StringList> allTableTypeMethodNames() {
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put(getAliasError(), new StringList(
                getAliasCurrentStack(),
                getAliasToString(),
                getAliasGetMessage()));
        map_.put(getAliasAnnotated(), new StringList(
                getAliasGetAnnotations(),
                getAliasGetAnnotationsParameters()));
        map_.put(getAliasAnnotation(), new StringList(getAliasGetString()));
        map_.put(getAliasClass(), new StringList(
                getAliasDefaultInstance(),
                getAliasEnumValueOf(),
                getAliasForName(),
                getAliasArrayGet(),
                getAliasGetActualTypeArguments(),
                getAliasGetAllClasses(),
                getAliasGetBounds(),
                getAliasGetClass(),
                getAliasGetComponentType(),
                getAliasGetDeclaredClasses(),
                getAliasGetDeclaredConstructors(),
                getAliasGetDeclaredFields(),
                getAliasGetDeclaredMethods(),
                getAliasGetEnclosingType(),
                getAliasGetEnumConstants(),
                getAliasGetGenericBounds(),
                getAliasGetGenericInterfaces(),
                getAliasGetGenericSuperClass(),
                getAliasGetGenericTypeArguments(),
                getAliasGetGenericVariableOwner(),
                getAliasGetInterfaces(),
                getAliasArrayGetLength(),
                getAliasGetLowerBounds(),
                getAliasGetName(),
                getAliasGetOperators(),
                getAliasGetPrettyName(),
                getAliasGetSuperClass(),
                getAliasGetTypeParameters(),
                getAliasGetUpperBounds(),
                getAliasGetVariableOwner(),
                getAliasInit(),
                getAliasIsAbstract(),
                getAliasIsAnnotation(),
                getAliasIsArray(),
                getAliasIsAssignableFrom(),
                getAliasIsClass(),
                getAliasIsEnum(),
                getAliasIsFinal(),
                getAliasIsInstance(),
                getAliasIsInterface(),
                getAliasIsPackage(),
                getAliasIsPrimitive(),
                getAliasIsPrivate(),
                getAliasIsProtected(),
                getAliasIsPublic(),
                getAliasIsStatic(),
                getAliasIsWildCard(),
                getAliasMakeArray(),
                getAliasMakeGeneric(),
                getAliasMakeWildCard(),
                getAliasArrayNewInstance(),
                getAliasArraySet()));
        map_.put(getAliasConstructor(), new StringList(
                getAliasGetDeclaringClass(),
                getAliasGetGenericReturnType(),
                getAliasGetName(),
                getAliasGetParameterNames(),
                getAliasGetParameterTypes(),
                getAliasGetReturnType(),
                getAliasIsPackage(),
                getAliasIsPrivate(),
                getAliasIsProtected(),
                getAliasIsPublic(),
                getAliasIsVarargs(),
                getAliasNewInstance()));
        map_.put(getAliasFct(), new StringList(getAliasCall()));
        map_.put(getAliasField(), new StringList(
                getAliasArrayGet(),
                getAliasGetDeclaringClass(),
                getAliasGetGenericType(),
                getAliasGetName(),
                getAliasGetType(),
                getAliasIsFinal(),
                getAliasIsPackage(),
                getAliasIsPrivate(),
                getAliasIsProtected(),
                getAliasIsPublic(),
                getAliasIsStatic(),
                getAliasSetField()));
        map_.put(getAliasMethod(), new StringList(
                getAliasGetDeclaringClass(),
                getAliasGetDefaultValue(),
                getAliasGetGenericReturnType(),
                getAliasGetName(),
                getAliasGetParameterNames(),
                getAliasGetParameterTypes(),
                getAliasGetReturnType(),
                getAliasInvoke(),
                getAliasIsAbstract(),
                getAliasIsFinal(),
                getAliasIsNormal(),
                getAliasIsPackage(),
                getAliasIsPolymorph(),
                getAliasIsPrivate(),
                getAliasIsProtected(),
                getAliasIsPublic(),
                getAliasIsStatic(),
                getAliasIsVarargs(),
                getAliasSetPolymorph()));
        map_.put(getAliasObjectsUtil(), new StringList(
                getAliasSameRef(),
                getAliasGetParent()));
        map_.put(getAliasEnum(), new StringList(
                getAliasEnumName(),
                getAliasEnumOrdinal(),
                getAliasEnumPredValueOf(),
                getAliasEnumValues()));
        map_.put(getAliasEnums(), new StringList(
                getAliasName(),
                getAliasOrdinal()));
        map_.put(getAliasIterable(), new StringList(
                getAliasIterator()));
        map_.put(getAliasIteratorType(), new StringList(
                getAliasHasNext(),
                getAliasNext()));
        map_.put(getAliasStackTraceElement(), new StringList(
                getAliasCurrentStack(),
                getAliasToString()));
        map_.put(getAliasMath(), new StringList(
                getAliasAbs(),
                getAliasMod(),
                getAliasQuot(),
                getAliasBinMod(),
                getAliasBinQuot(),
                getAliasPlus(),
                getAliasMinus(),
                getAliasMult(),
                getAliasNegBin(),
                getAliasNeg(),
                getAliasAnd(),
                getAliasOr(),
                getAliasXor(),
                getAliasLe(),
                getAliasGe(),
                getAliasLt(),
                getAliasGt(),
                getAliasShiftLeft(),
                getAliasShiftRight(),
                getAliasBitShiftLeft(),
                getAliasBitShiftRight(),
                getAliasRotateLeft(),
                getAliasRotateRight()));
        map_.put(getAliasReplacement(), new StringList(
                getAliasGetNewString(),
                getAliasGetOldString(),
                getAliasSetNewString(),
                getAliasSetOldString()));
        map_.put(getAliasBoolean(), new StringList(
                getAliasBooleanValue(),
                getAliasCompare(),
                getAliasCompareTo(),
                getAliasEquals(),
                getAliasParseBoolean(),
                getAliasToString(),
                getAliasValueOf()));
        map_.put(getAliasByte(), new StringList(
                getAliasByteValue(),
                getAliasCompare(),
                getAliasCompareTo(),
                getAliasDoubleValue(),
                getAliasEquals(),
                getAliasFloatValue(),
                getAliasIntValue(),
                getAliasLongValue(),
                getAliasParseByte(),
                getAliasShortValue(),
                getAliasToString()));
        map_.put(getAliasCharSequence(), new StringList(
                getAliasCharAt(),
                getAliasContains(),
                getAliasEndsWith(),
                getAliasFormat(),
                getAliasGetBytes(),
                getAliasIndexOf(),
                getAliasIsEmpty(),
                getAliasLastIndexOf(),
                getAliasLength(),
                getAliasRegionMatches(),
                getAliasReplace(),
                getAliasSplit(),
                getAliasSplitChars(),
                getAliasSplitStrings(),
                getAliasStartsWith(),
                getAliasSubSequence(),
                getAliasSubstring(),
                getAliasToCharArray(),
                getAliasToString(),
                getAliasTrim()));
        map_.put(getAliasCharacter(), new StringList(
                getAliasCharAt(),
                getAliasCharValue(),
                getAliasCompare(),
                getAliasCompareTo(),
                getAliasDigit(),
                getAliasForDigit(),
                getAliasGetType(),
                getAliasIsDigit(),
                getAliasGetDirectionality(),
                getAliasIsLetter(),
                getAliasIsLetterOrDigit(),
                getAliasIsLowerCase(),
                getAliasIsSpace(),
                getAliasIsUpperCase(),
                getAliasIsWhitespace(),
                getAliasIsWordChar(),
                getAliasLength(),
                getAliasSubSequence(),
                getAliasToLowerCase(),
                getAliasToString(),
                getAliasToUpperCase()));
        map_.put(getAliasDouble(), new StringList(
                getAliasByteValue(),
                getAliasCompare(),
                getAliasCompareTo(),
                getAliasDoubleValue(),
                getAliasEquals(),
                getAliasFloatValue(),
                getAliasIntValue(),
                getAliasIsInfinite(),
                getAliasIsNan(),
                getAliasLongValue(),
                getAliasParseDouble(),
                getAliasShortValue(),
                getAliasToString()));
        map_.put(getAliasFloat(), new StringList(
                getAliasByteValue(),
                getAliasCompare(),
                getAliasCompareTo(),
                getAliasDoubleValue(),
                getAliasEquals(),
                getAliasFloatValue(),
                getAliasIntValue(),
                getAliasIsInfinite(),
                getAliasIsNan(),
                getAliasLongValue(),
                getAliasParseFloat(),
                getAliasShortValue(),
                getAliasToString()));
        map_.put(getAliasInteger(), new StringList(
                getAliasByteValue(),
                getAliasCompare(),
                getAliasCompareTo(),
                getAliasDoubleValue(),
                getAliasEquals(),
                getAliasFloatValue(),
                getAliasIntValue(),
                getAliasLongValue(),
                getAliasParseInt(),
                getAliasShortValue(),
                getAliasToString()));
        map_.put(getAliasLong(), new StringList(
                getAliasByteValue(),
                getAliasCompare(),
                getAliasCompareTo(),
                getAliasDoubleValue(),
                getAliasEquals(),
                getAliasFloatValue(),
                getAliasIntValue(),
                getAliasLongValue(),
                getAliasParseLong(),
                getAliasShortValue(),
                getAliasToString()));
        map_.put(getAliasNumber(), new StringList(
                getAliasByteValue(),
                getAliasCompare(),
                getAliasCompareTo(),
                getAliasDoubleValue(),
                getAliasEquals(),
                getAliasFloatValue(),
                getAliasIntValue(),
                getAliasLongValue(),
                getAliasShortValue(),
                getAliasToString()));
        map_.put(getAliasShort(), new StringList(
                getAliasByteValue(),
                getAliasCompare(),
                getAliasCompareTo(),
                getAliasDoubleValue(),
                getAliasEquals(),
                getAliasFloatValue(),
                getAliasIntValue(),
                getAliasLongValue(),
                getAliasParseShort(),
                getAliasShortValue(),
                getAliasToString()));
        map_.put(getAliasString(), new StringList(
                getAliasCharAt(),
                getAliasCompareTo(),
                getAliasCompareToIgnoreCase(),
                getAliasContains(),
                getAliasEndsWith(),
                getAliasEqualsIgnoreCase(),
                getAliasFormat(),
                getAliasGetBytes(),
                getAliasIndexOf(),
                getAliasIsEmpty(),
                getAliasLastIndexOf(),
                getAliasLength(),
                getAliasRegionMatches(),
                getAliasReplace(),
                getAliasReplaceMultiple(),
                getAliasSplit(),
                getAliasSplitChars(),
                getAliasSplitStrings(),
                getAliasStartsWith(),
                getAliasSubSequence(),
                getAliasSubstring(),
                getAliasToCharArray(),
                getAliasToLowerCase(),
                getAliasToUpperCase(),
                getAliasToString(),
                getAliasTrim(),
                getAliasValueOf()));
        map_.put(getAliasStringBuilder(), new StringList(

                getAliasCharAt(),
                getAliasContains(),
                getAliasEndsWith(),
                getAliasFormat(),
                getAliasGetBytes(),
                getAliasIndexOf(),
                getAliasIsEmpty(),
                getAliasLastIndexOf(),
                getAliasLength(),
                getAliasRegionMatches(),
                getAliasReplace(),
                getAliasSplit(),
                getAliasSplitChars(),
                getAliasSplitStrings(),
                getAliasStartsWith(),
                getAliasSubSequence(),
                getAliasSubstring(),
                getAliasToCharArray(),
                getAliasToString(),
                getAliasTrim(),
                getAliasAppend(),
                getAliasCapacity(),
                getAliasClear(),
                getAliasDelete(),
                getAliasDeleteCharAt(),
                getAliasEnsureCapacity(),
                getAliasInsert(),
                getAliasReverse(),
                getAliasSetCharAt(),
                getAliasSetLength(),
                getAliasTrimToSize()));
        return map_;
    }
    public void validateMethodsContents(ContextEl _cont, StringMap<StringList> _methods, StringList _prims){
        for (EntryCust<String, StringList> e: _methods.entryList()) {
            for (String k: e.getValue()) {
                if (k.isEmpty()) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage("empty word");
                    err_.setErrCat(ErrorCat.WRITE_METHOD_WORD);
                    _cont.getClasses().addStdError(err_);
                    continue;
                }
                if (_cont.getKeyWords().isKeyWord(k)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.concat("key word ", k));
                    err_.setErrCat(ErrorCat.WRITE_METHOD_WORD);
                    _cont.getClasses().addStdError(err_);
                }
                if (_prims.containsStr(k)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.concat("primitive ", k));
                    err_.setErrCat(ErrorCat.WRITE_METHOD_WORD);
                    _cont.getClasses().addStdError(err_);
                }
                for (char c: k.toCharArray()) {
                    if (!StringList.isDollarWordChar(c)) {
                        StdWordError err_ = new StdWordError();
                        err_.setMessage(StringList.concat("not word char ", Character.toString(c)));
                        err_.setErrCat(ErrorCat.WRITE_METHOD_WORD);
                        _cont.getClasses().addStdError(err_);
                        break;
                    }
                }
                if (ContextEl.isDigit(k.charAt(0))) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.concat("digit ", Character.toString(k.charAt(0))));
                    err_.setErrCat(ErrorCat.WRITE_METHOD_WORD);
                    _cont.getClasses().addStdError(err_);
                }
            }
        }
    }
    public void validateMethodsDuplicates(ContextEl _cont, StringMap<StringList> _methods){
        for (EntryCust<String, StringList> e: _methods.entryList()) {
            StringList keyWords_ = new StringList(e.getValue());
            int size_ = keyWords_.size();
            keyWords_.removeDuplicates();
            if (size_ != keyWords_.size()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.concat("duplicate methods ",e.getValue().display()));
                err_.setErrCat(ErrorCat.DUPLICATE_METHOD_WORD);
                _cont.getClasses().addStdError(err_);
            }
        }
    }
    public StringMap<StringList> allTableTypeFieldNames() {
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put(nbAlias.getAliasDouble(), new StringList(
                nbAlias.getAliasMinValueField(),
                nbAlias.getAliasMaxValueField()));
        map_.put(nbAlias.getAliasFloat(), new StringList(
                nbAlias.getAliasMinValueField(),
                nbAlias.getAliasMaxValueField()));
        map_.put(nbAlias.getAliasLong(), new StringList(
                nbAlias.getAliasMinValueField(),
                nbAlias.getAliasMaxValueField()));
        map_.put(nbAlias.getAliasInteger(), new StringList(
                nbAlias.getAliasMinValueField(),
                nbAlias.getAliasMaxValueField()));
        map_.put(nbAlias.getAliasCharacter(), new StringList(
                nbAlias.getAliasMinValueField(),
                nbAlias.getAliasMaxValueField()));
        map_.put(nbAlias.getAliasShort(), new StringList(
                nbAlias.getAliasMinValueField(),
                nbAlias.getAliasMaxValueField()));
        map_.put(nbAlias.getAliasByte(), new StringList(
                nbAlias.getAliasMinValueField(),
                nbAlias.getAliasMaxValueField()));
        return map_;
    }
    public void validateFieldsContents(ContextEl _cont, StringMap<StringList> _methods, StringList _prims){
        for (EntryCust<String, StringList> e: _methods.entryList()) {
            for (String k: e.getValue()) {
                if (k.isEmpty()) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage("empty word");
                    err_.setErrCat(ErrorCat.WRITE_FIELD_WORD);
                    _cont.getClasses().addStdError(err_);
                    continue;
                }
                if (_cont.getKeyWords().isKeyWord(k)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.concat("key word ", k));
                    err_.setErrCat(ErrorCat.WRITE_FIELD_WORD);
                    _cont.getClasses().addStdError(err_);
                }
                if (_prims.containsStr(k)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.concat("primitive ", k));
                    err_.setErrCat(ErrorCat.WRITE_FIELD_WORD);
                    _cont.getClasses().addStdError(err_);
                }
                for (char c: k.toCharArray()) {
                    if (!StringList.isDollarWordChar(c)) {
                        StdWordError err_ = new StdWordError();
                        err_.setMessage(StringList.concat("not word char ", Character.toString(c)));
                        err_.setErrCat(ErrorCat.WRITE_FIELD_WORD);
                        _cont.getClasses().addStdError(err_);
                        break;
                    }
                }
                if (ContextEl.isDigit(k.charAt(0))) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.concat("digit ", Character.toString(k.charAt(0))));
                    err_.setErrCat(ErrorCat.WRITE_FIELD_WORD);
                    _cont.getClasses().addStdError(err_);
                }
            }
        }
    }
    public void validateFieldsDuplicates(ContextEl _cont, StringMap<StringList> _methods){
        for (EntryCust<String, StringList> e: _methods.entryList()) {
            StringList keyWords_ = new StringList(e.getValue());
            int size_ = keyWords_.size();
            keyWords_.removeDuplicates();
            if (size_ != keyWords_.size()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.concat("duplicate methods ",e.getValue().display()));
                err_.setErrCat(ErrorCat.DUPLICATE_FIELD_WORD);
                _cont.getClasses().addStdError(err_);
            }
        }
    }
    public void setupOverrides(ContextEl _cont) {
        StringList pkgs_ = new StringList();
        for (StandardType r: standards.values()) {
            String pkg_ = r.getPackageName();
            StringBuilder id_ = new StringBuilder();
            for (String p: StringList.splitChars(pkg_, '.')) {
                id_.append(p);
                pkgs_.add(id_.toString());
                id_.append('.');
            }
        }
        pkgs_.removeDuplicates();
        _cont.getClasses().getPackagesFound().addAllElts(pkgs_);
        _cont.setAnalyzing(new AnalyzedPageEl());
        TypeUtil.buildInherits(_cont);
        for (StandardType t: standards.values()) {
            TypeUtil.buildOverrides(t, _cont);
        }
        _cont.setAnalyzing(null);
    }

    public static boolean canBeUseAsArgument(boolean _exec, String _param, String _arg, Analyzable _context) {
        LgNames stds_ = _context.getStandards();
        String aliasVoid_ = stds_.getAliasVoid();
        if (StringList.quickEq(_param, aliasVoid_)) {
            return false;
        }
        ClassArgumentMatching param_ = new ClassArgumentMatching(_param);
        if (_arg == null || _arg.isEmpty()) {
            if (param_.isPrimitive(_context)) {
                return false;
            }
            return true;
        }
        if (StringList.quickEq(_arg, aliasVoid_)) {
            return false;
        }
        AssignableFrom a_ = isAssignableFromCust(_param, _arg, stds_);
        if (a_ == AssignableFrom.YES) {
            return true;
        }
        if (a_ == AssignableFrom.NO) {
            return false;
        }

        ClassArgumentMatching arg_ = new ClassArgumentMatching(_arg);
        DimComp paramComp_ = PrimitiveTypeUtil.getQuickComponentBaseType(_param);
        DimComp argComp_ = PrimitiveTypeUtil.getQuickComponentBaseType(_arg);
        String objAlias_ = stds_.getAliasObject();
        if (StringList.quickEq(paramComp_.getComponent(), objAlias_)) {
            if (paramComp_.getDim() > argComp_.getDim()) {
                return false;
            }
            return true;
        }
        if (paramComp_.getDim() != argComp_.getDim()) {
            return false;
        }
        boolean array_ = paramComp_.getDim() > 0;
        String paramName_ = paramComp_.getComponent();
        String argName_ = argComp_.getComponent();
        param_ = new ClassArgumentMatching(paramComp_.getComponent());
        arg_ = new ClassArgumentMatching(argComp_.getComponent());
        if (StringList.quickEq(paramComp_.getComponent(),argComp_.getComponent())) {
            return true;
        }
        String aliasPrimBool_ = stds_.getAliasPrimBoolean();
        if (!PrimitiveTypeUtil.isPrimitiveOrWrapper(arg_, _context)) {
            return false;
        }
        if (arg_.isPrimitive(_context)) {
            String pName_ = paramComp_.getComponent();
            String name_ = argComp_.getComponent();
            PrimitiveType pr_ = stds_.getPrimitiveTypes().getVal(name_);
            if (pr_.getAllSuperType(_context).containsStr(pName_)) {
                return true;
            }
            return false;
        }
        if (_exec || !param_.isPrimitive(_context)) {
            return false;
        }
        if (!array_) {
            if (StringList.quickEq(argName_, aliasPrimBool_)) {
                String typeNameParam_ = PrimitiveTypeUtil.toPrimitive(paramName_, true, stds_);
                if (!StringList.quickEq(typeNameParam_, aliasPrimBool_)) {
                    return false;
                }
                return true;
            }
            String pName_ = paramComp_.getComponent();
            String name_ = argComp_.getComponent();
            name_ = PrimitiveTypeUtil.toPrimitive(name_, true, stds_);
            PrimitiveType pr_ = stds_.getPrimitiveTypes().getVal(name_);
            if (pr_.getAllSuperType(_context).containsStr(pName_)) {
                return true;
            }
            return false;
        }
        return false;
    }
    static AssignableFrom isAssignableFromCust(String _param,String _arg, LgNames _context) {
        String aliasObject_ = _context.getAliasObject();
        if (StringList.quickEq(_param, aliasObject_)) {
            return AssignableFrom.YES;
        }
        DimComp dPar_ = PrimitiveTypeUtil.getQuickComponentBaseType(_param);
        String p_ = dPar_.getComponent();
        StandardType clParBl_ = _context.getStandards().getVal(p_);
        DimComp dArg_ = PrimitiveTypeUtil.getQuickComponentBaseType(_arg);
        String a_ = dArg_.getComponent();
        StandardType clArgBl_ = _context.getStandards().getVal(a_);
        if (clArgBl_ != null) {
            if (clParBl_ != null) {
                if (dArg_.getDim() > 0 && dPar_.getDim() > 0) {
                    if (isArrayAssignable(_arg, _param,_context)) {
                        return AssignableFrom.YES;
                    }
                    return AssignableFrom.NO;
                }
                if (dArg_.getDim() != dPar_.getDim()) {
                    return AssignableFrom.NO;
                }
                String className_ = dPar_.getComponent();
                if (StringList.quickEq(className_, a_)) {
                    return AssignableFrom.YES;
                }
                if (clArgBl_.getAllSuperTypes(_context).containsObj(className_)) {
                    return AssignableFrom.YES;
                }
                return AssignableFrom.NO;
            }
        }
        return AssignableFrom.MAYBE;
    }
    static boolean isArrayAssignable(String _arrArg, String _arrParam, LgNames _context) {
        String aliasObject_ = _context.getAliasObject();
        DimComp dArg_ = PrimitiveTypeUtil.getQuickComponentBaseType(_arrArg);
        String a_ = dArg_.getComponent();
        DimComp dPar_ = PrimitiveTypeUtil.getQuickComponentBaseType(_arrParam);
        String className_ = dPar_.getComponent();
        if (StringList.quickEq(className_, aliasObject_)) {
            if (dPar_.getDim() > dArg_.getDim()) {
                return false;
            }
            return true;
        }
        if (dPar_.getDim() != dArg_.getDim()) {
            return false;
        }
        StandardType clArgBl_ = _context.getStandards().getVal(a_);
        if (clArgBl_.getAllSuperTypes(_context).containsObj(className_)) {
            return true;
        }
        if (StringList.quickEq(className_, a_)) {
            return true;
        }
        return false;
    }
    public void buildOther() {
    }
    public static ResultErrorStd invokeMethod(ContextEl _cont, ClassMethodId _method, Struct _struct, Argument... _args) {
        ResultErrorStd result_ = new ResultErrorStd();
        Struct[] args_ = getObjects(_args);
        String type_ = _method.getClassName();
        String name_ = _method.getConstraints().getName();
        LgNames lgNames_ = _cont.getStandards();
        String stringBuilderType_ = lgNames_.getAliasStringBuilder();
        String replType_ = lgNames_.getAliasReplacement();
        result_ = invokeStdMethod(_cont, _method, _struct, _args);
        if (result_.getResult() != null) {
            return result_;
        }
        if (result_.getError() != null) {
            _cont.setException(new ErrorStruct(_cont,result_.getError()));
            return result_;
        }
        if (StringList.quickEq(type_, replType_)
                || StringList.quickEq(type_, stringBuilderType_)) {
            AliasCharSequence.invokeMethod(_cont, _method, _struct, _args);
            return result_;
        }
        if (StringList.quickEq(type_, lgNames_.getAliasEnums())) {
            if (StringList.quickEq(name_, lgNames_.getAliasName())) {
                Struct str_ = args_[0];
                if (!(str_ instanceof EnumerableStruct)) {
                    result_.setError(lgNames_.getAliasNullPe());
                } else {
                    EnumerableStruct en_ = (EnumerableStruct) str_;
                    result_.setResult(new StringStruct(en_.getName()));
                }
            } else {
                Struct str_ = args_[0];
                if (!(str_ instanceof EnumerableStruct)) {
                    result_.setError(lgNames_.getAliasNullPe());
                } else {
                    EnumerableStruct en_ = (EnumerableStruct) str_;
                    result_.setResult(new IntStruct(en_.getOrdinal()));
                }
            }
        }
        if (result_.getResult() != null) {
            return result_;
        }
        if (result_.getError() != null) {
            _cont.setException(new ErrorStruct(_cont,result_.getError()));
            return result_;
        }
        result_ = AliasReflection.invokeMethod(_cont, _method, _struct, _args);
        if (result_.getResult() != null) {
            return result_;
        }
        if (result_.getError() != null) {
            _cont.setException(new ErrorStruct(_cont,result_.getError()));
            return result_;
        }
        result_ = lgNames_.getOtherResult(_cont, _struct, _method, args_);
        if (result_.getError() != null) {
            _cont.setException(new ErrorStruct(_cont,result_.getError()));
            return result_;
        }
        return result_;
    }

    public static ResultErrorStd invokeStdMethod(Analyzable _cont, ClassMethodId _method, Struct _struct, Argument... _args) {
        ResultErrorStd result_ = new ResultErrorStd();
        Struct[] args_ = getObjects(_args);
        String type_ = _method.getClassName();
        String name_ = _method.getConstraints().getName();
        LgNames lgNames_ = _cont.getStandards();
        String mathType_ = lgNames_.getAliasMath();
        String booleanType_ = lgNames_.getAliasBoolean();
        String charType_ = lgNames_.getAliasCharacter();
        String nbType_ = lgNames_.getAliasNumber();
        String stringType_ = lgNames_.getAliasString();
        if (StringList.quickEq(type_, lgNames_.getAliasObjectsUtil())) {
            if (StringList.quickEq(name_, lgNames_.getAliasSameRef())) {
                result_.setResult(new BooleanStruct(args_[0].sameReference(args_[1])));
                return result_;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasGetParent())) {
                Struct arg_ = args_[0];
                Struct par_ = arg_.getParent();
                _cont.getContextEl().addSensibleField(arg_, par_);
                result_.setResult(par_);
                return result_;
            }
        }
        if (StringList.quickEq(type_, stringType_)
                || StringList.quickEq(type_, lgNames_.getAliasCharSequence())) {
            result_ = AliasCharSequence.invokeStdMethod(_cont, _method, _struct, _args);
            return result_;
        }
        if (StringList.quickEq(type_, lgNames_.getAliasStackTraceElement())) {
            ContextEl c_ = _cont.getContextEl();
            return AliasStackTraceElement.invokeMethod(c_, _method, _struct, _args);
        }
        if (StringList.quickEq(type_, lgNames_.getAliasError())) {
            ErrorStruct err_ = (ErrorStruct) _struct;
            if (StringList.quickEq(name_, lgNames_.getAliasCurrentStack())) {
                result_.setResult(err_.getStack());
                return result_;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasGetMessage())) {
                result_.setResult(err_.getMessage());
                return result_;
            }
            result_.setResult(err_.getDisplayedString(_cont));
            return result_;
        }
        if (StringList.quickEq(type_, mathType_)) {
            return AliasMath.invokeStdMethod(_cont, _method, _struct, _args);
        }
        if (StringList.quickEq(type_, booleanType_)
                || StringList.quickEq(type_, charType_)
                || PrimitiveTypeUtil.isPureNumberClass(new ClassArgumentMatching(type_), lgNames_)
                || StringList.quickEq(type_, nbType_)) {
            NumberStruct.calculate(_cont, result_, _method, _struct, args_);
            return result_;
        }
        return result_;
    }
    public static Double parseDouble(NumberInfos _nb) {
        StringBuilder int_ = new StringBuilder(_nb.getIntPart());
        while(int_.indexOf("_") >= 0) {
            int_.deleteCharAt(int_.indexOf("_"));
        }
        while(int_.indexOf(" ") >= 0) {
            int_.deleteCharAt(int_.indexOf(" "));
        }
        StringBuilder dec_ = new StringBuilder(_nb.getDecimalPart());
        while(dec_.indexOf("_") >= 0) {
            dec_.deleteCharAt(dec_.indexOf("_"));
        }
        while(dec_.indexOf(" ") >= 0) {
            dec_.deleteCharAt(dec_.indexOf(" "));
        }
        StringBuilder exp_ = new StringBuilder(_nb.getExponentialPart());
        while(exp_.indexOf("_") >= 0) {
            exp_.deleteCharAt(exp_.indexOf("_"));
        }
        while(exp_.indexOf(" ") >= 0) {
            exp_.deleteCharAt(exp_.indexOf(" "));
        }
        boolean positive_ = _nb.isPositive();
        Long expNb_;
        if (exp_.length() == 0) {
            expNb_ = 0l;
        } else {
            expNb_ = parseLongTen(exp_.toString());
        }
        if (expNb_ == null) {
            if (positive_) {
                if (exp_.charAt(0) == '-') {
                    return 0.0;
                }
                return Double.POSITIVE_INFINITY;
            }
            if (exp_.charAt(0) == '-') {
                return -0.0;
            }
            return Double.NEGATIVE_INFINITY;
        }
        long expNbLong_ = expNb_.longValue();
        if (_nb.getBase() == 16) {
            StringBuilder merged_ = new StringBuilder(int_.length()+dec_.length());
            merged_.append(int_);
            merged_.append(dec_);
            double parsed_ = parseDoubleSixteen(merged_.toString());
            long delta_ = expNbLong_;
            delta_ -= 4 * dec_.length();
            double p_ = 1.0;
            long absExpNbLong_ = Math.abs(delta_);
            for (int i = 0; i < absExpNbLong_; i++) {
                p_ *= 2;
            }
            if (delta_ > 0) {
                return parsed_ * p_;
            }
            return parsed_ / p_;
        }
        if (_nb.getBase() == 2) {
            StringBuilder merged_ = new StringBuilder(int_.length()+dec_.length());
            merged_.append(int_);
            merged_.append(dec_);
            double parsed_ = parseDoubleBinary(merged_.toString());
            long delta_ = expNbLong_;
            delta_ -= dec_.length();
            double p_ = 1.0;
            long absExpNbLong_ = Math.abs(delta_);
            for (int i = 0; i < absExpNbLong_; i++) {
                p_ *= 2;
            }
            if (delta_ > 0) {
                return parsed_ * p_;
            }
            return parsed_ / p_;
        }
        if (_nb.getBase() == 8) {
            StringBuilder merged_ = new StringBuilder(int_.length()+dec_.length());
            merged_.append(int_);
            merged_.append(dec_);
            double parsed_ = parseDoubleOctal(merged_.toString());
            long delta_ = expNbLong_;
            delta_ -= 3*dec_.length();
            double p_ = 1.0;
            long absExpNbLong_ = Math.abs(delta_);
            for (int i = 0; i < absExpNbLong_; i++) {
                p_ *= 2;
            }
            if (delta_ > 0) {
                return parsed_ * p_;
            }
            return parsed_ / p_;
        }
        if (dec_.length() == 0) {
            if (expNbLong_ == 0) {
                double long_;
                if (int_.length() > MAX_DIGITS_DOUBLE) {
                    return processBigNumbers(int_, positive_, MAX_DIGITS_DOUBLE);
                }
                long_ = parseQuickLongTen(int_.toString());
                if (!positive_) {
                    return -long_;
                }
                return long_;
            }
            double long_;
            if (int_.length() > MAX_DIGITS_DOUBLE) {
                long_ = parseQuickLongTen(int_.substring(0, MAX_DIGITS_DOUBLE + 1));
                expNbLong_ += int_.length() - MAX_DIGITS_DOUBLE - 1;
            } else {
                long_ = parseQuickLongTen(int_.toString());
            }
            double power_ = 1;
            long absExp_ = Math.abs(expNbLong_);
            for (long i = 0; i < absExp_; i++) {
                power_ *= 10d;
            }
            if (!positive_) {
                if (expNbLong_ > 0) {
                    return -long_ * power_;
                }
                return -long_ / power_;
            }
            if (expNbLong_ > 0) {
                return long_ * power_;
            }
            return long_ / power_;
        }
        if (expNbLong_ >= dec_.length()) {
            //try to get "double" as int
            StringBuilder number_ = new StringBuilder(int_.length()+dec_.length());
                    number_.append(int_);
                    number_.append(dec_);
                    int diff_ = (int)expNbLong_-dec_.length();
                    for (long i = 0; i < diff_; i++) {
                        number_.append("0");
                    }
                    if (number_.length() > MAX_DIGITS_DOUBLE) {
                        return processBigNumbers(number_, positive_, MAX_DIGITS_DOUBLE);
                    }
                    double long_ = parseQuickLongTen(number_.toString());
                    if (!positive_) {
                        return -long_;
                    }
                    return long_;
        }
        if (-expNbLong_ >= int_.length()) {
            StringBuilder number_ = new StringBuilder(int_);
            number_.append(dec_);
            int nbLeadingZeros_ = 0;
            StringBuilder decCopy_ = new StringBuilder();
            int index_ = 0;
            while (index_ < number_.length()) {
                if (number_.charAt(index_) != '0') {
                    break;
                }
                index_++;
            }
            nbLeadingZeros_ = index_;
            decCopy_.append(number_.substring(nbLeadingZeros_));
            if (decCopy_.length() == 0) {
                if (!positive_) {
                    return -0.0;
                }
                return 0.0;
            }
            double value_;
            int diff_;
            if (decCopy_.length() > MAX_DIGITS_DOUBLE) {
                value_ = parseQuickLongTen(decCopy_.substring(0, MAX_DIGITS_DOUBLE + 1));
                diff_ = (int) (-expNbLong_ - int_.length() + MAX_DIGITS_DOUBLE + 1 + nbLeadingZeros_);
            } else {
                value_ = parseQuickLongTen(decCopy_.toString());
                diff_ = (int) (-expNbLong_ + dec_.length());
            }
            double power_ = 1;
            for (int i = 0; i < diff_; i++) {
                power_ *= 10d;
            }
            if (!positive_) {
                return -value_ / power_;
            }
            return value_ / power_;
        }
        StringBuilder numberInt_ = new StringBuilder();
        StringBuilder numberDec_ = new StringBuilder();
        if (expNbLong_ > 0) {
            //expNbLong_ < dec_.length() => dec_.length() > 0 => numberInt_.length() > 0
                    //-expNbLong_ < int_.length()
                    numberInt_.append(int_);
            numberInt_.append(dec_.substring(0, (int) expNbLong_));
            numberDec_.append(dec_.substring((int)expNbLong_));
        } else if (expNbLong_ == 0) {
            //expNbLong_ < dec_.length() => 0 < dec_.length()
                    //-expNbLong_ < int_.length() => 0 < int_.length() => numberInt_.length() > 0
                    numberInt_.append(int_);
                    numberDec_.append(dec_);
        } else {
            //expNbLong_ < 0
            int del_ = int_.length() +(int)expNbLong_;
            //-expNbLong_ < int_.length() => 0 < -expNbLong_ < int_.length() => 0 < int_.length()
                    //-expNbLong_ < int_.length() => 0 < expNbLong_ + int_.length() => numberInt_.length() > 0
                    numberInt_.append(int_.substring(0, del_));
                    numberDec_.append(int_.substring(del_));
                    numberDec_.append(dec_);
        }
        if (numberInt_.length() > MAX_DIGITS_DOUBLE) {
            return processBigNumbers(numberInt_, positive_, MAX_DIGITS_DOUBLE);
        }
        double longValue_ = parseQuickLongTen(numberInt_.toString());
        StringBuilder decCopy_ = new StringBuilder();
        int nbLeadingZeros_ = 0;
        int index_ = 0;
        while (index_ < numberDec_.length()) {
            if (numberDec_.charAt(index_) != '0') {
                break;
            }
            index_++;
        }
        nbLeadingZeros_ = index_;
        decCopy_.append(numberDec_.substring(nbLeadingZeros_));
        decCopy_.delete(Math.min(MAX_DIGITS_DOUBLE + 1, decCopy_.length()), decCopy_.length());
        if (decCopy_.length() == 0) {
            if (!positive_) {
                return -longValue_;
            }
            return longValue_;
        }
        double decValue_ = parseQuickLongTen(decCopy_.toString());
        double power_ = 1;
        int logDec_ = numberDec_.length();
        for (int i = 0; i < logDec_; i++) {
            power_ *= 10d;
        }
        if (!positive_) {
            return -longValue_ - decValue_ / power_;
        }
        return longValue_ + decValue_ / power_;
    }
    private static Double processBigNumbers(StringBuilder _nb, boolean _positive, int _max) {
        Long long_ = parseQuickLongTen(_nb.substring(0, _max + 1));
        double power_ = 1;
        int logDec_ = _nb.length() - _max - 1;
        for (int i = 0; i < logDec_; i++) {
            power_ *= 10d;
        }
        double out_ = long_.doubleValue() * power_;
        if (_positive) {
            return out_;
        }
        return -out_;
    }
    //this long parser is very naive
    public static char parseCharSixteen(String _string) {
        int result_ = 0;
        int i_ = 0;
        int max_ = _string.length();
        while (i_ < max_) {
            int ch_ = _string.charAt(i_);
            if (ch_ >= 'A' && ch_ <= 'F') {
                ch_ = ch_ - 'A' + 'a';
            }
            i_++;
            int digit_ = Math.min(ch_ - '0', 10) + Math.max(ch_ - 'a', 0);
            result_ *= HEX_BASE;
            result_ += digit_;
        }
        return (char)result_;
    }
    public static boolean[] toBits(long _l) {
        boolean[] bits_ = new boolean[64];
        long t_ = _l;
        if (_l < 0) {
            bits_[0] = true;
            t_ = Long.MAX_VALUE + _l + 1;
        }
        int k_ = 63;
        for (int i = 0; i < 63; i++) {
            if (t_ % 2 == 1) {
                bits_[k_] = true;
            }
            k_--;
            t_ /= 2;
        }
        return bits_;
    }
    public static boolean[] toBits(int _l) {
        boolean[] bits_ = new boolean[32];
        long t_ = _l;
        if (_l < 0) {
            bits_[0] = true;
            t_ = Integer.MAX_VALUE + _l + 1;
        }
        int k_ = 31;
        for (int i = 0; i < 31; i++) {
            if (t_ % 2 == 1) {
                bits_[k_] = true;
            }
            k_--;
            t_ /= 2;
        }
        return bits_;
    }
    public static long toLong(boolean[] _bits) {
        long s_ = 0;
        for (int i = 1; i < 64; i++) {
            s_ *= 2;
            if (_bits[i]) {
                s_++;
            }
        }
        if (_bits[0]) {
            return s_ - Long.MAX_VALUE - 1;
        }
        return s_;
    }
    public static int toInt(boolean[] _bits) {
        int s_ = 0;
        for (int i = 1; i < 32; i++) {
            s_ *= 2;
            if (_bits[i]) {
                s_++;
            }
        }
        if (_bits[0]) {
            return s_ - Integer.MAX_VALUE - 1;
        }
        return s_;
    }
    public static int extractInt(boolean[] _bits) {
        int s_ = 0;
        for (int i = 33; i < 64; i++) {
            s_ *= 2;
            if (_bits[i]) {
                s_++;
            }
        }
        if (_bits[32]) {
            return s_ - Integer.MAX_VALUE - 1;
        }
        return s_;
    }
    public static short extractShort(boolean[] _bits) {
        int s_ = 0;
        for (int i = 49; i < 64; i++) {
            s_ *= 2;
            if (_bits[i]) {
                s_++;
            }
        }
        if (_bits[48]) {
            return (short) (s_ - Short.MAX_VALUE - 1);
        }
        return (short) s_;
    }
    public static byte extractByte(boolean[] _bits) {
        int s_ = 0;
        for (int i = 57; i < 64; i++) {
            s_ *= 2;
            if (_bits[i]) {
                s_++;
            }
        }
        if (_bits[56]) {
            return (byte) (s_ - Byte.MAX_VALUE - 1);
        }
        return (byte) s_;
    }
    public static int toUnsignedInt(boolean[] _bits, int _max) {
        int s_ = 0;
        for (int i = 32 - _max+1; i < 32; i++) {
            s_ *= 2;
            if (_bits[i]) {
                s_++;
            }
        }
        return s_;
    }
    public static long toUnsignedLong(boolean[] _bits, int _max) {
        int s_ = 0;
        for (int i = 64 - _max +1; i < 64; i++) {
            s_ *= 2;
            if (_bits[i]) {
                s_++;
            }
        }
        return s_;
    }
    public static long toLong(boolean[] _bits, long _max, boolean _strNeg) {
        long s_ = 0;
        for (int i = 1; i < 64; i++) {
            s_ *= 2;
            if (_bits[i]) {
                s_++;
            }
        }
        if (_strNeg) {
            return s_ - _max - 1;
        }
        return s_;
    }

    public static boolean[] parseLongSixteenToBits(String _string) {
        StringBuilder str_;
        if (_string.length() < 16) {
            str_ = new StringBuilder();
            int add_ = 16 - _string.length();
            for (int i = 0; i < add_; i++) {
                str_.append("0");
            }
            for (int i = add_; i < 16; i++) {
                str_.append(_string.charAt(i - add_));
            }
        } else {
            str_ = new StringBuilder(_string);
        }
        boolean[] out_ = new boolean[str_.length() * 4];
        int i_ = 0;
        int j_ = 0;
        int max_ = str_.length();
        while (i_ < max_) {
            // Accumulating negatively avoids surprises near MAX_VALUE
            int ch_ = str_.charAt(i_);
            if (ch_ >= 'A' && ch_ <= 'F') {
                ch_ = ch_ - 'A' + 'a';
            }
            i_++;
            int digit_ = Math.min(ch_ - '0', 10) + Math.max(ch_ - 'a', 0);
            int t_ = digit_;
            int k_ = 3;
            for (int j = 0; j < 4; j++) {
                if (t_ % 2 == 1) {
                    out_[j_ + k_] = true;
                }
                k_--;
                t_ /= 2;
            }
            j_ += 4;
        }
        return out_;
    }
    public static boolean[] parseLongOctalToBits(String _string) {
        StringBuilder str_;
        if (_string.length() < 21) {
            str_ = new StringBuilder();
            int add_ = 21 - _string.length();
            for (int i = 0; i < add_; i++) {
                str_.append("0");
            }
            for (int i = add_; i < 21; i++) {
                str_.append(_string.charAt(i - add_));
            }
        } else {
            str_ = new StringBuilder(_string);
        }
        int j_ = 0;
        boolean[] out_ = new boolean[str_.length()*3];
        int i_ = 0;
        int max_ = str_.length();
        while (i_ < max_) {
            int ch_ = str_.charAt(i_);
            i_++;
            int digit_ = ch_ - '0';
            int t_ = digit_;
            int k_ = 2;
            for (int j = 0; j < 3; j++) {
                if (t_ % 2 == 1) {
                    out_[j_ + k_] = true;
                }
                k_--;
                t_ /= 2;
            }
            j_ += 3;
        }
        return out_;
    }
    public static boolean[] parseLongBinaryToBits(String _string) {
        StringBuilder str_;
        if (_string.length() < 64) {
            str_ = new StringBuilder();
            int add_ = 64 - _string.length();
            for (int i = 0; i < add_; i++) {
                str_.append("0");
            }
            for (int i = add_; i < 64; i++) {
                str_.append(_string.charAt(i - add_));
            }
        } else {
            str_ = new StringBuilder(_string);
        }
        boolean[] out_ = new boolean[str_.length()];
        int i_ = 0;
        int max_ = str_.length();
        while (i_ < max_) {
            // Accumulating negatively avoids surprises near MAX_VALUE
            int ch_ = str_.charAt(i_);
            if (ch_ == '1') {
                out_[i_] = true;
            }
            i_++;
        }
        return out_;
    }
    public static double parseDoubleSixteen(String _string) {
        double result_ = 0;
        int i_ = 0;
        int max_ = _string.length();
        while (i_ < max_) {
            int ch_ = _string.charAt(i_);
            if (ch_ >= 'A' && ch_ <= 'F') {
                ch_ = ch_ - 'A' + 'a';
            }
            i_++;
            int digit_ = Math.min(ch_ - '0', 10) + Math.max(ch_ - 'a', 0);
            result_ *= HEX_BASE;
            result_ += digit_;
        }
        return result_;
    }
    public static double parseDoubleOctal(String _string) {
        double result_ = 0;
        int i_ = 0;
        int max_ = _string.length();
        while (i_ < max_) {
            int ch_ = _string.charAt(i_);
            i_++;
            int digit_ = ch_ - '0';
            result_ *= 8;
            result_ += digit_;
        }
        return result_;
    }
    public static double parseDoubleBinary(String _string) {
        double result_ = 0;
        int i_ = 0;
        int max_ = _string.length();
        while (i_ < max_) {
            int ch_ = _string.charAt(i_);
            i_++;
            int digit_ = ch_ - '0';
            result_ *= 2;
            result_ += digit_;
        }
        return result_;
    }
    //this long parser is very naive
    public static Long parseLongTen(String _string) {
        long result_ = 0;
        boolean negative_ = false;
        int i_ = 0;
        int max_ = _string.length();
        long limit_;
        long multmin_;
        int digit_;

        if (_string.charAt(0) == '-') {
            negative_ = true;
            limit_ = Long.MIN_VALUE;
            i_++;
        } else {
            limit_ = -Long.MAX_VALUE;
        }
        if (negative_) {
            multmin_ = MULTMIN_RADIX_TEN;
        } else {
            multmin_ = N_MULTMAX_RADIX_TEN;
        }
        int ch_ = _string.charAt(i_);
        i_++;
        digit_ = ch_ - '0';
        result_ = -digit_;
        while (i_ < max_) {
            // Accumulating negatively avoids surprises near MAX_VALUE
            ch_ = _string.charAt(i_);
            i_++;
            digit_ = ch_ - '0';
            if (result_ < multmin_) {
                return null;
            }
            result_ *= DEFAULT_RADIX;
            if (result_ < limit_ + digit_) {
                return null;
            }
            result_ -= digit_;
        }
        if (negative_) {
            return result_;
        }
        return -result_;
    }
    public static long parseQuickLongTen(String _string) {
        long result_ = 0;
        boolean negative_ = false;
        int i_ = 0;
        int max_ = _string.length();
        int digit_;

        if (_string.charAt(0) == '-') {
            negative_ = true;
            i_++;
        }
        int ch_ = _string.charAt(i_);
        i_++;
        digit_ = ch_ - '0';
        result_ = -digit_;
        while (i_ < max_) {
            // Accumulating negatively avoids surprises near MAX_VALUE
            ch_ = _string.charAt(i_);
            i_++;
            digit_ = ch_ - '0';
            result_ *= DEFAULT_RADIX;
            result_ -= digit_;
        }
        if (negative_) {
            return result_;
        }
        return -result_;
    }
    public static Long parseLong(String _string, int _radix) {
        if (_string == null) {
            return null;
        }
        if (_radix < Character.MIN_RADIX) {
            return null;
        }
        if (_radix > Character.MAX_RADIX) {
            return null;
        }

        long result_ = 0;
        boolean negative_ = false;
        int i_ = 0;
        int max_ = _string.length();
        long limit_;
        long multmin_;
        int digit_;

        if (max_ > 0) {
            if (_string.charAt(0) == '-') {
                negative_ = true;
                limit_ = Long.MIN_VALUE;
                i_++;
            } else {
                limit_ = -Long.MAX_VALUE;
            }
            if (_radix == DEFAULT_RADIX) {
                if (negative_) {
                    multmin_ = MULTMIN_RADIX_TEN;
                } else {
                    multmin_ = N_MULTMAX_RADIX_TEN;
                }
            } else {
                multmin_ = limit_ / _radix;
            }
            if (i_ < max_) {
                int ch_ = _string.charAt(i_);
                if (ch_ >= 'A' && ch_ <= 'Z') {
                    ch_ = ch_ - 'A' + 'a';
                }
                i_++;
                int dig_ = Math.min(ch_ - '0', 10) + Math.max(ch_ - 'a', 0);
                if (dig_ < 0 || dig_ >= _radix) {
                    return null;
                }
                digit_ = dig_;
                result_ = -digit_;
            }
            while (i_ < max_) {
                // Accumulating negatively avoids surprises near MAX_VALUE
                int ch_ = _string.charAt(i_);
                if (ch_ >= 'A' && ch_ <= 'Z') {
                    ch_ = ch_ - 'A' + 'a';
                }
                i_++;
                int dig_ = Math.min(ch_ - '0', 10) + Math.max(ch_ - 'a', 0);
                if (dig_ < 0 || dig_ >= _radix) {
                    return null;
                }
                digit_ = dig_;
                if (result_ < multmin_) {
                    return null;
                }
                result_ *= _radix;
                if (result_ < limit_ + digit_) {
                    return null;
                }
                result_ -= digit_;
            }
        } else {
            return null;
        }
        if (negative_) {
            if (i_ > 1) {
                return result_;
            }
            return null;
        }
        return -result_;
    }
    public static boolean isValidDouble(String _nb) {
        int to_ = _nb.length() - 1;
        int i_ = 0;
        if (!ContextEl.isDigit(_nb.charAt(i_))) {
            if (_nb.charAt(i_) != MINUS_CHAR) {
                if (_nb.charAt(i_) != DOT_VAR) {
                    return false;
                }
            }
            i_++;
            if (_nb.charAt(i_) == DOT_VAR) {
                i_++;
            }
        }
        if (i_ <= to_) {
            if (!ContextEl.isDigit(_nb.charAt(i_))) {
                return false;
            }
        } else {
            return false;
        }
        int nbDots_ = 0;
        boolean exp_ = false;
        while (i_ <= to_) {
            char cur_ = _nb.charAt(i_);
            if (!ContextEl.isDigit(cur_)) {
                if (Character.isLetter(cur_)) {
                    if (cur_ == EXP || cur_ == EXP_UPP) {
                        exp_ = true;
                        i_++;
                        continue;
                    }
                    return false;
                }
                if (cur_ == MINUS_CHAR && exp_) {
                    i_++;
                    continue;
                }
                if (cur_ != DOT_VAR || nbDots_ > 0) {
                    return false;
                }
                nbDots_++;
                i_++;
                continue;
            }
            i_++;
        }
        return true;
    }
    public static Double parseDouble(String _nb) {
        NumberInfos infos_ = trySplitDouble(_nb);
        if (infos_ == null) {
            return null;
        }
        return parseDouble(infos_);
    }
    public static Float parseFloat(String _nb) {
        NumberInfos infos_ = trySplitDouble(_nb);
        if (infos_ == null) {
            return null;
        }
        double double_ = parseDouble(infos_);
        double abs_ = Math.abs(double_);
        if (abs_ > Float.MAX_VALUE) {
            return null;
        }
        return (float)double_;
    }
    public static NumberInfos trySplitDouble(String _nb) {
        if (_nb == null) {
            return null;
        }
        if (_nb.isEmpty()) {
            return null;
        }
        NumberInfos infos_ = new NumberInfos();
        int i_ = 0;
        if (!ContextEl.isDigit(_nb.charAt(i_))) {
            if (_nb.charAt(i_) != MINUS_CHAR) {
                if (_nb.charAt(i_) != DOT_VAR) {
                    return null;
                }
                infos_.setPositive(true);
            } else {
                infos_.setPositive(false);
                i_++;
            }
        } else {
            infos_.setPositive(true);
        }
        int len_ = _nb.length();
        StringBuilder intPart_ = new StringBuilder();
        infos_.setIntPart(intPart_);
        StringBuilder decimalPart_ = new StringBuilder();
        infos_.setDecimalPart(decimalPart_);
        StringBuilder exponentialPart_ = new StringBuilder();
        infos_.setExponentialPart(exponentialPart_);
        while (i_ < len_) {
            char cur_ = _nb.charAt(i_);
            if (!ContextEl.isDigit(cur_)) {
                if (cur_ != DOT_VAR) {
                    if (cur_ != EXP && cur_ != EXP_UPP) {
                        return null;
                    }
                }
                break;
            }
            intPart_.append(cur_);
            i_++;
        }
        if (i_ >= len_) {
            return infos_;
        }
        if (_nb.charAt(i_) == DOT_VAR) {
            i_++;
            while (i_ < len_) {
                char cur_ = _nb.charAt(i_);
                if (!ContextEl.isDigit(cur_)) {
                    if (cur_ != EXP && cur_ != EXP_UPP) {
                        return null;
                    }
                    break;
                }
                decimalPart_.append(cur_);
                i_++;
            }
        }
        if (i_ >= len_) {
            return infos_;
        }
        char n_ = _nb.charAt(i_);
        if (n_ == EXP || n_ == EXP_UPP) {
            i_++;
            if (i_ >= len_) {
                return null;
            }
            char cur_ = _nb.charAt(i_);
            if (!ContextEl.isDigit(cur_) && cur_ != MINUS_CHAR) {
                return null;
            }
            i_++;
            exponentialPart_.append(cur_);
            while (i_ < len_) {
                cur_ = _nb.charAt(i_);
                if (!ContextEl.isDigit(cur_)) {
                    return null;
                }
                exponentialPart_.append(cur_);
                i_++;
            }
        }
        return infos_;
    }
    public static Byte parseByte(String _string) {
        Long int_ = parseLong(_string);
        if (int_ == null) {
            return null;
        }
        if (int_.longValue() < Byte.MIN_VALUE) {
            return null;
        }
        if (int_.longValue() > Byte.MAX_VALUE) {
            return null;
        }
        return int_.byteValue();
    }
    public static Short parseShort(String _string) {
        Long int_ = parseLong(_string);
        if (int_ == null) {
            return null;
        }
        if (int_.longValue() < Short.MIN_VALUE) {
            return null;
        }
        if (int_.longValue() > Short.MAX_VALUE) {
            return null;
        }
        return int_.shortValue();
    }
    public static Integer parseInt(String _string) {
        Long int_ = parseLong(_string);
        if (int_ == null) {
            return null;
        }
        if (int_.longValue() < Integer.MIN_VALUE) {
            return null;
        }
        if (int_.longValue() > Integer.MAX_VALUE) {
            return null;
        }
        return int_.intValue();
    }
    public static Long parseLong(String _string) {
        if (_string == null) {
            return null;
        }
        long result_ = 0;
        boolean negative_ = false;
        int i_ = 0;
        int max_ = _string.length();
        long limit_;
        long multmin_;
        int digit_;

        if (max_ > 0) {
            if (_string.charAt(0) == '-') {
                negative_ = true;
                limit_ = Long.MIN_VALUE;
                i_++;
            } else {
                limit_ = -Long.MAX_VALUE;
            }
            if (negative_) {
                multmin_ = MULTMIN_RADIX_TEN;
            } else {
                multmin_ = N_MULTMAX_RADIX_TEN;
            }
            if (i_ < max_) {
                char ch_ = _string.charAt(i_);
                i_++;
                if (ch_ < '0' || ch_ > '9') {
                    return null;
                }
                digit_ = ch_ - '0';
                result_ = -digit_;
            }
            while (i_ < max_) {
                // Accumulating negatively avoids surprises near MAX_VALUE
                char ch_ = _string.charAt(i_);
                i_++;
                if (ch_ < '0' || ch_ > '9') {
                    return null;
                }
                digit_ = ch_ - '0';
                if (result_ < multmin_) {
                    return null;
                }
                result_ *= 10;
                if (result_ < limit_ + digit_) {
                    return null;
                }
                result_ -= digit_;
            }
        } else {
            return null;
        }
        if (negative_) {
            if (i_ > 1) {
                return result_;
            }
            return null;
        }
        return -result_;
    }

    public ResultErrorStd getOtherResult(ContextEl _cont, Struct _instance, ClassMethodId _method, Struct... _args) {
        return new ResultErrorStd();
    }
    public static ResultErrorStd newInstance(ContextEl _cont, ConstructorId _method, Argument... _args) {
        ResultErrorStd result_ = new ResultErrorStd();
        Struct[] args_ = getObjects(_args);
        String type_ = _method.getName();
        LgNames lgNames_ = _cont.getStandards();
        String stringBuilderType_ = lgNames_.getAliasStringBuilder();
        String objectType_ = lgNames_.getAliasObject();
        result_ = newInstanceStd(_cont, _method, _args);
        if (result_.getResult() != null) {
            return result_;
        }
        if (result_.getError() != null) {
            _cont.setException(new ErrorStruct(_cont,result_.getError()));
            return result_;
        }
        if (StringList.quickEq(type_, stringBuilderType_)) {
            StringBuilderStruct.instantiate(_cont, result_, _method, args_);
            return result_;
        } else if (StringList.quickEq(type_, objectType_)) {
            result_.setResult(new SimpleObjectStruct());
        } else {
            result_ = lgNames_.getOtherResult(_cont, _method, args_);
        }
        if (result_.getError() != null) {
            _cont.setException(new ErrorStruct(_cont,result_.getError()));
        }
        return result_;
    }
    public static ResultErrorStd newInstanceStd(Analyzable _cont, ConstructorId _method, Argument... _args) {
        ResultErrorStd result_ = new ResultErrorStd();
        Struct[] args_ = getObjects(_args);
        String type_ = _method.getName();
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
        String replType_ = lgNames_.getAliasReplacement();
        if (StringList.quickEq(type_, replType_)) {
            ReplacementStruct.instantiate(result_, _method, args_);
            return result_;
        }
        if (StringList.quickEq(type_, stringType_)) {
            StringStruct.instantiate(_cont, result_, _method, args_);
            return result_;
        }
        if (StringList.quickEq(type_, booleanType_)
                || StringList.quickEq(type_, charType_)
                || StringList.quickEq(type_, byteType_)
                || StringList.quickEq(type_, shortType_)
                || StringList.quickEq(type_, intType_)
                || StringList.quickEq(type_, longType_)
                || StringList.quickEq(type_, floatType_)
                || StringList.quickEq(type_, doubleType_)) {
            NumberStruct.instantiate(_cont, result_, _method, args_);
            return result_;
        }
        return result_;
    }
    public ResultErrorStd getOtherResult(ContextEl _cont, ConstructorId _method, Struct... _args) {
        return new ResultErrorStd();
    }
    public static ResultErrorStd getField(ContextEl _cont, ClassField _classField, Struct _instance) {
        LgNames lgNames_ = _cont.getStandards();
        ResultErrorStd result_ = lgNames_.getSimpleResult(_cont, _classField);
        if (result_.getResult() != null) {
            return result_;
        }
        result_ = lgNames_.getOtherResult(_cont, _classField, _instance);
        return result_;
    }
    public ResultErrorStd getOtherResult(ContextEl _cont, ClassField _classField, Struct _instance) {
        return new ResultErrorStd();
    }
    public ResultErrorStd getSimpleResult(Analyzable _conf, ClassField _classField) {
        ResultErrorStd result_ = new ResultErrorStd();
        String type_ = _classField.getClassName();
        String name_ = _classField.getFieldName();
        LgNames lgNames_ = _conf.getStandards();
        String charType_ = lgNames_.getAliasCharacter();
        String byteType_ = lgNames_.getAliasByte();
        String shortType_ = lgNames_.getAliasShort();
        String intType_ = lgNames_.getAliasInteger();
        String longType_ = lgNames_.getAliasLong();
        String floatType_ = lgNames_.getAliasFloat();
        String doubleType_ = lgNames_.getAliasDouble();
        if (StringList.quickEq(type_, charType_)) {
            if (StringList.quickEq(name_, lgNames_.getAliasMinValueField())) {
                result_.setResult(new CharStruct(Character.MIN_VALUE));
            } else {
                result_.setResult(new CharStruct(Character.MAX_VALUE));
            }
        } else if (StringList.quickEq(type_, byteType_)) {
            if (StringList.quickEq(name_, lgNames_.getAliasMinValueField())) {
                result_.setResult(new ByteStruct(Byte.MIN_VALUE));
            } else {
                result_.setResult(new ByteStruct(Byte.MAX_VALUE));
            }
        } else if (StringList.quickEq(type_, shortType_)) {
            if (StringList.quickEq(name_, lgNames_.getAliasMinValueField())) {
                result_.setResult(new ShortStruct(Short.MIN_VALUE));
            } else {
                result_.setResult(new ShortStruct(Short.MAX_VALUE));
            }
        } else if (StringList.quickEq(type_, intType_)) {
            if (StringList.quickEq(name_, lgNames_.getAliasMinValueField())) {
                result_.setResult(new IntStruct(Integer.MIN_VALUE));
            } else {
                result_.setResult(new IntStruct(Integer.MAX_VALUE));
            }
        } else if (StringList.quickEq(type_, longType_)) {
            if (StringList.quickEq(name_, lgNames_.getAliasMinValueField())) {
                result_.setResult(new LongStruct(Long.MIN_VALUE));
            } else {
                result_.setResult(new LongStruct(Long.MAX_VALUE));
            }
        } else if (StringList.quickEq(type_, floatType_)) {
            if (StringList.quickEq(name_, lgNames_.getAliasMinValueField())) {
                result_.setResult(new FloatStruct(Float.MIN_VALUE));
            } else {
                result_.setResult(new FloatStruct(Float.MAX_VALUE));
            }
        } else if (StringList.quickEq(type_, doubleType_)) {
            if (StringList.quickEq(name_, lgNames_.getAliasMinValueField())) {
                result_.setResult(new DoubleStruct(Double.MIN_VALUE));
            } else {
                result_.setResult(new DoubleStruct(Double.MAX_VALUE));
            }
        }
        return result_;
    }
    public static ResultErrorStd setField(ContextEl _cont, ClassField _classField, Struct _instance, Struct _value) {
        LgNames lgNames_ = _cont.getStandards();
        return lgNames_.setOtherResult(_cont, _classField, _instance, _value);
    }
    public ResultErrorStd setOtherResult(ContextEl _cont, ClassField _classField, Struct _instance, Struct _value) {
        return new ResultErrorStd();
    }
    static Struct[] getObjects(Argument... _args) {
        int len_ = _args.length;
        Struct[] classes_ = new Struct[len_];
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            classes_[i] = _args[i].getStruct();
        }
        return classes_;
    }
    public void buildIterable(ContextEl _context) {
        //local names
        _context.getAnalyzing().setCurrentBlock(null);
        _context.getAnalyzing().setEnabledInternVars(true);
        Classes cl_ = _context.getClasses();
        String next_ = getAliasNext();
        String hasNext_ = getAliasHasNext();
        String nextPair_ = getAliasNextPair();
        String hasNextPair_ = getAliasHasNextPair();
        String locName_ = _context.getNextTempVar();
        String exp_;
        LocalVariable locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasIterable(),"<?>"));
        _context.getInternVars().put(locName_, locVar_);
        cl_.setIteratorVarCust(locName_);
        String iterator_ = getAliasIterator();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(iterator_,PARS));
        cl_.setExpsIteratorCust(ElUtil.getAnalyzedOperations(exp_, _context, Calculation.staticCalculation(true)));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasIteratorType(),"<?>"));
        _context.getInternVars().put(locName_, locVar_);
        cl_.setHasNextVarCust(locName_);
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(hasNext_,PARS));
        cl_.setExpsHasNextCust(ElUtil.getAnalyzedOperations(exp_, _context, Calculation.staticCalculation(true)));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasIteratorType(),"<?>"));
        _context.getInternVars().put(locName_, locVar_);
        cl_.setNextVarCust(locName_);
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(next_,PARS));
        cl_.setExpsNextCust(ElUtil.getAnalyzedOperations(exp_, _context, Calculation.staticCalculation(true)));
        
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasIterableTable(),"<?,?>"));
        _context.getInternVars().put(locName_, locVar_);
        cl_.setIteratorTableVarCust(locName_);
        String iteratorTable_ = getAliasIteratorTable();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(iteratorTable_,PARS));
        cl_.setExpsIteratorTableCust(ElUtil.getAnalyzedOperations(exp_, _context, Calculation.staticCalculation(true)));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasIteratorTableType(),"<?,?>"));
        _context.getInternVars().put(locName_, locVar_);
        cl_.setHasNextPairVarCust(locName_);
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(hasNextPair_,PARS));
        cl_.setExpsHasNextPairCust(ElUtil.getAnalyzedOperations(exp_, _context, Calculation.staticCalculation(true)));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasIteratorTableType(),"<?,?>"));
        _context.getInternVars().put(locName_, locVar_);
        cl_.setNextPairVarCust(locName_);
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(nextPair_,PARS));
        cl_.setExpsNextPairCust(ElUtil.getAnalyzedOperations(exp_, _context, Calculation.staticCalculation(true)));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasPairType(),"<?,?>"));
        _context.getInternVars().put(locName_, locVar_);
        cl_.setFirstVarCust(locName_);
        String first_ = getAliasGetFirst();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(first_,PARS));
        cl_.setExpsFirstCust(ElUtil.getAnalyzedOperations(exp_, _context, Calculation.staticCalculation(true)));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasPairType(),"<?,?>"));
        _context.getInternVars().put(locName_, locVar_);
        cl_.setSecondVarCust(locName_);
        String second_ = getAliasGetSecond();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(second_,PARS));
        cl_.setExpsSecondCust(ElUtil.getAnalyzedOperations(exp_, _context, Calculation.staticCalculation(true)));
    }
    public IterableAnalysisResult getCustomType(StringList _names, ContextEl _context) {
        StringList out_ = new StringList();
        for (String f: _names) {
            String iterable_ = getAliasIterable();
            String type_ = Templates.getFullTypeByBases(f, iterable_, _context);
            if (type_ != null) {
                out_.add(type_);
            }
        }
        out_.removeDuplicates();
        return new IterableAnalysisResult(out_);
    }
    public AbstractForEachLoop newForeachLoop(ContextEl _importingPage,
            BracedBlock _m,
            OffsetStringInfo _className, OffsetStringInfo _variable,
            OffsetStringInfo _expression, OffsetStringInfo _classIndex,
            OffsetStringInfo _label, OffsetsBlock _offset) {
        return new ForEachLoop(_importingPage, _m, _className, _variable, _expression, _classIndex, _label, _offset);
    }

    public StringMap<String> buildFiles(ContextEl _context) {
        StringMap<String> files_ = new StringMap<String>();
        LgNames stds_ = _context.getStandards();
        String content_ = PredefinedClasses.getBracedIterableType(_context);
        String name_;
        name_ = stds_.getAliasIterable();
        predefinedClasses.add(name_);
        files_.put(name_, content_);
        content_ = PredefinedClasses.getBracedIteratorType(_context);
        name_ = stds_.getAliasIteratorType();
        predefinedClasses.add(name_);
        files_.put(name_, content_);
        content_ = PredefinedClasses.getBracedIterableTableType(_context);
        name_ = stds_.getAliasIterableTable();
        predefinedClasses.add(name_);
        files_.put(name_, content_);
        content_ = PredefinedClasses.getBracedIteratorTableType(_context);
        name_ = stds_.getAliasIteratorTableType();
        predefinedClasses.add(name_);
        files_.put(name_, content_);
        content_ = PredefinedClasses.getBracedPairType(_context);
        name_ = stds_.getAliasPairType();
        predefinedClasses.add(name_);
        files_.put(name_, content_);
        content_ = PredefinedClasses.getBracedEnumType(_context);
        name_ = stds_.getAliasEnum();
        predefinedClasses.add(name_);
        files_.put(name_, content_);
        content_ = PredefinedClasses.getBracedEnumParamType(_context);
        name_ = stds_.getAliasEnumParam();
        predefinedClasses.add(name_);
        files_.put(name_, content_);
        predefinedInterfacesInitOrder.add(stds_.getAliasIterable());
        predefinedInterfacesInitOrder.add(stds_.getAliasIteratorType());
        predefinedInterfacesInitOrder.add(stds_.getAliasIterableTable());
        predefinedInterfacesInitOrder.add(stds_.getAliasIteratorTableType());
        predefinedInterfacesInitOrder.add(stds_.getAliasPairType());
        predefinedInterfacesInitOrder.add(stds_.getAliasEnumParam());
        predefinedInterfacesInitOrder.add(stds_.getAliasEnum());
        return files_;
    }

    public String getStructClassName(Struct _struct, ContextEl _context) {
        String w_ = _struct.getClassName(_context);
        w_ = _context.getStandards().toWrapper(w_);
        return w_;
    }
    
    public StringMap<StandardType> getStandards() {
        return standards;
    }
    public StringMap<PrimitiveType> getPrimitiveTypes() {
        return primTypes.getPrimitiveTypes();
    }
    public String getAliasObject() {
        return coreNames.getAliasObject();
    }
    public void setAliasObject(String _aliasObject) {
        coreNames.setAliasObject(_aliasObject);
    }
    public String getAliasVoid() {
        return coreNames.getAliasVoid();
    }
    public void setAliasVoid(String _aliasVoid) {
        coreNames.setAliasVoid(_aliasVoid);
    }
    public String getAliasCharSequence() {
        return charSeq.getAliasCharSequence();
    }
    public void setAliasCharSequence(String _aliasCharSequence) {
        charSeq.setAliasCharSequence(_aliasCharSequence);
    }

    public String getAliasIterator() {
        return predefTypes.getAliasIterator();
    }
    public void setAliasIterator(String _aliasIterator) {
        predefTypes.setAliasIterator(_aliasIterator);
    }
    public String getAliasIteratorType() {
        return predefTypes.getAliasIteratorType();
    }
    public void setAliasIteratorType(String _aliasIteratorType) {
        predefTypes.setAliasIteratorType(_aliasIteratorType);
    }
    public String getAliasIterable() {
        return predefTypes.getAliasIterable();
    }
    public void setAliasIterable(String _aliasIterable) {
        predefTypes.setAliasIterable(_aliasIterable);
    }
    public String getAliasEnumParam() {
        return predefTypes.getAliasEnumParam();
    }
    public void setAliasEnumParam(String _aliasEnumParam) {
        predefTypes.setAliasEnumParam(_aliasEnumParam);
    }
    public String getAliasEnum() {
        return predefTypes.getAliasEnum();
    }
    public void setAliasEnum(String _aliasEnum) {
        predefTypes.setAliasEnum(_aliasEnum);
    }
    public String getAliasEnums() {
        return coreNames.getAliasEnums();
    }
    public void setAliasEnums(String _aliasEnums) {
        coreNames.setAliasEnums(_aliasEnums);
    }
    public String getAliasError() {
        return coreNames.getAliasError();
    }
    public void setAliasError(String _aliasError) {
        coreNames.setAliasError(_aliasError);
    }
    public String getAliasGetMessage() {
        return coreNames.getAliasGetMessage();
    }
    public void setAliasGetMessage(String _aliasGetMessage) {
        coreNames.setAliasGetMessage(_aliasGetMessage);
    }
    public String getAliasCustomError() {
        return coreNames.getAliasCustomError();
    }
    public void setAliasCustomError(String _aliasCustomError) {
        coreNames.setAliasCustomError(_aliasCustomError);
    }
    public String getAliasBadSize() {
        return coreNames.getAliasBadSize();
    }
    public void setAliasBadSize(String _aliasBadSize) {
        coreNames.setAliasBadSize(_aliasBadSize);
    }
    public String getAliasDivisionZero() {
        return coreNames.getAliasDivisionZero();
    }
    public void setAliasDivisionZero(String _aliasDivisionZero) {
        coreNames.setAliasDivisionZero(_aliasDivisionZero);
    }
    public String getAliasCast() {
        return coreNames.getAliasCast();
    }
    public void setAliasCast(String _aliasCast) {
        coreNames.setAliasCast(_aliasCast);
    }
    public String getAliasStore() {
        return coreNames.getAliasStore();
    }
    public void setAliasStore(String _aliasStore) {
        coreNames.setAliasStore(_aliasStore);
    }
    public String getAliasNullPe() {
        return coreNames.getAliasNullPe();
    }
    public void setAliasNullPe(String _aliasNullPe) {
        coreNames.setAliasNullPe(_aliasNullPe);
    }
    public String getAliasNbFormat() {
        return coreNames.getAliasNbFormat();
    }
    public void setAliasNbFormat(String _aliasNbFormat) {
        coreNames.setAliasNbFormat(_aliasNbFormat);
    }
    public String getAliasBadEncode() {
        return coreNames.getAliasBadEncode();
    }
    public void setAliasBadEncode(String _aliasBadEncode) {
        coreNames.setAliasBadEncode(_aliasBadEncode);
    }
    public String getAliasBadIndex() {
        return coreNames.getAliasBadIndex();
    }
    public void setAliasBadIndex(String _aliasBadIndex) {
        coreNames.setAliasBadIndex(_aliasBadIndex);
    }
    public String getAliasSof() {
        return coreNames.getAliasSof();
    }
    public void setAliasSof(String _aliasSof) {
        coreNames.setAliasSof(_aliasSof);
    }
    public String getAliasPrimBoolean() {
        return primTypes.getAliasPrimBoolean();
    }
    public void setAliasPrimBoolean(String _aliasPrimBoolean) {
        primTypes.setAliasPrimBoolean(_aliasPrimBoolean);
    }
    public String getAliasMath() {
        return mathRef.getAliasMath();
    }
    public void setAliasMath(String _aliasMath) {
        mathRef.setAliasMath(_aliasMath);
    }
    public String getAliasPrimByte() {
        return primTypes.getAliasPrimByte();
    }
    public void setAliasPrimByte(String _aliasPrimByte) {
        primTypes.setAliasPrimByte(_aliasPrimByte);
    }
    public String getAliasPrimShort() {
        return primTypes.getAliasPrimShort();
    }
    public void setAliasPrimShort(String _aliasPrimShort) {
        primTypes.setAliasPrimShort(_aliasPrimShort);
    }
    public String getAliasPrimChar() {
        return primTypes.getAliasPrimChar();
    }
    public void setAliasPrimChar(String _aliasPrimChar) {
        primTypes.setAliasPrimChar(_aliasPrimChar);
    }
    public String getAliasPrimInteger() {
        return primTypes.getAliasPrimInteger();
    }
    public void setAliasPrimInteger(String _aliasPrimInteger) {
        primTypes.setAliasPrimInteger(_aliasPrimInteger);
    }
    public String getAliasPrimLong() {
        return primTypes.getAliasPrimLong();
    }
    public void setAliasPrimLong(String _aliasPrimLong) {
        primTypes.setAliasPrimLong(_aliasPrimLong);
    }
    public String getAliasPrimFloat() {
        return primTypes.getAliasPrimFloat();
    }
    public void setAliasPrimFloat(String _aliasPrimFloat) {
        primTypes.setAliasPrimFloat(_aliasPrimFloat);
    }
    public String getAliasPrimDouble() {
        return primTypes.getAliasPrimDouble();
    }
    public void setAliasPrimDouble(String _aliasPrimDouble) {
        primTypes.setAliasPrimDouble(_aliasPrimDouble);
    }
   
    public String getAliasCompareTo() {
        return nbAlias.getAliasCompareTo();
    }
    public void setAliasCompareTo(String _aliasCompareTo) {
        nbAlias.setAliasCompareTo(_aliasCompareTo);
    }
    public String getAliasCompare() {
        return nbAlias.getAliasCompare();
    }
    public void setAliasCompare(String _aliasCompare) {
        nbAlias.setAliasCompare(_aliasCompare);
    }
    public String getAliasEquals() {
        return nbAlias.getAliasEquals();
    }
    public void setAliasEquals(String _aliasEquals) {
        nbAlias.setAliasEquals(_aliasEquals);
    }
    public String getAliasToString() {
        return nbAlias.getAliasToString();
    }
    public void setAliasToString(String _aliasToString) {
        nbAlias.setAliasToString(_aliasToString);
    }
    public String getAliasValueOf() {
        return nbAlias.getAliasValueOf();
    }
    public void setAliasValueOf(String _aliasValueOf) {
        nbAlias.setAliasValueOf(_aliasValueOf);
    }
    public String getAliasMaxValueField() {
        return nbAlias.getAliasMaxValueField();
    }
    public void setAliasMaxValueField(String _aliasMaxValueField) {
        nbAlias.setAliasMaxValueField(_aliasMaxValueField);
    }
    public String getAliasMinValueField() {
        return nbAlias.getAliasMinValueField();
    }
    public void setAliasMinValueField(String _aliasMinValueField) {
        nbAlias.setAliasMinValueField(_aliasMinValueField);
    }
    public String getAliasBoolean() {
        return nbAlias.getAliasBoolean();
    }
    public void setAliasBoolean(String _aliasBoolean) {
        nbAlias.setAliasBoolean(_aliasBoolean);
    }
    public String getAliasByte() {
        return nbAlias.getAliasByte();
    }
    public void setAliasByte(String _aliasByte) {
        nbAlias.setAliasByte(_aliasByte);
    }
    public String getAliasShort() {
        return nbAlias.getAliasShort();
    }
    public void setAliasShort(String _aliasShort) {
        nbAlias.setAliasShort(_aliasShort);
    }
    public String getAliasCharacter() {
        return nbAlias.getAliasCharacter();
    }
    public void setAliasCharacter(String _aliasCharacter) {
        nbAlias.setAliasCharacter(_aliasCharacter);
    }
    public String getAliasInteger() {
        return nbAlias.getAliasInteger();
    }
    public void setAliasInteger(String _aliasInteger) {
        nbAlias.setAliasInteger(_aliasInteger);
    }
    public String getAliasLong() {
        return nbAlias.getAliasLong();
    }
    public void setAliasLong(String _aliasLong) {
        nbAlias.setAliasLong(_aliasLong);
    }
    public String getAliasFloat() {
        return nbAlias.getAliasFloat();
    }
    public void setAliasFloat(String _aliasFloat) {
        nbAlias.setAliasFloat(_aliasFloat);
    }
    public String getAliasDouble() {
        return nbAlias.getAliasDouble();
    }
    public void setAliasDouble(String _aliasDouble) {
        nbAlias.setAliasDouble(_aliasDouble);
    }
    public String getAliasNumber() {
        return nbAlias.getAliasNumber();
    }
    public void setAliasNumber(String _aliasNumber) {
        nbAlias.setAliasNumber(_aliasNumber);
    }
    public String getAliasParseBoolean() {
        return nbAlias.getAliasParseBoolean();
    }
    public void setAliasParseBoolean(String _aliasParseBoolean) {
        nbAlias.setAliasParseBoolean(_aliasParseBoolean);
    }
    public String getAliasParseByte() {
        return nbAlias.getAliasParseByte();
    }
    public void setAliasParseByte(String _aliasParseByte) {
        nbAlias.setAliasParseByte(_aliasParseByte);
    }
    public String getAliasParseShort() {
        return nbAlias.getAliasParseShort();
    }
    public void setAliasParseShort(String _aliasParseShort) {
        nbAlias.setAliasParseShort(_aliasParseShort);
    }
    public String getAliasParseInt() {
        return nbAlias.getAliasParseInt();
    }
    public void setAliasParseInt(String _aliasParseInt) {
        nbAlias.setAliasParseInt(_aliasParseInt);
    }
    public String getAliasParseLong() {
        return nbAlias.getAliasParseLong();
    }
    public void setAliasParseLong(String _aliasParseLong) {
        nbAlias.setAliasParseLong(_aliasParseLong);
    }
    public String getAliasParseFloat() {
        return nbAlias.getAliasParseFloat();
    }
    public void setAliasParseFloat(String _aliasParseFloat) {
        nbAlias.setAliasParseFloat(_aliasParseFloat);
    }
    public String getAliasParseDouble() {
        return nbAlias.getAliasParseDouble();
    }
    public void setAliasParseDouble(String _aliasParseDouble) {
        nbAlias.setAliasParseDouble(_aliasParseDouble);
    }
    public String getAliasBooleanValue() {
        return nbAlias.getAliasBooleanValue();
    }
    public void setAliasBooleanValue(String _aliasBooleanValue) {
        nbAlias.setAliasBooleanValue(_aliasBooleanValue);
    }
    public String getAliasByteValue() {
        return nbAlias.getAliasByteValue();
    }
    public void setAliasByteValue(String _aliasByteValue) {
        nbAlias.setAliasByteValue(_aliasByteValue);
    }
    public String getAliasShortValue() {
        return nbAlias.getAliasShortValue();
    }
    public void setAliasShortValue(String _aliasShortValue) {
        nbAlias.setAliasShortValue(_aliasShortValue);
    }
    public String getAliasCharValue() {
        return nbAlias.getAliasCharValue();
    }
    public void setAliasCharValue(String _aliasCharValue) {
        nbAlias.setAliasCharValue(_aliasCharValue);
    }
    public String getAliasIntValue() {
        return nbAlias.getAliasIntValue();
    }
    public void setAliasIntValue(String _aliasIntValue) {
        nbAlias.setAliasIntValue(_aliasIntValue);
    }
    public String getAliasLongValue() {
        return nbAlias.getAliasLongValue();
    }
    public void setAliasLongValue(String _aliasLongValue) {
        nbAlias.setAliasLongValue(_aliasLongValue);
    }
    public String getAliasFloatValue() {
        return nbAlias.getAliasFloatValue();
    }
    public void setAliasFloatValue(String _aliasFloatValue) {
        nbAlias.setAliasFloatValue(_aliasFloatValue);
    }
    public String getAliasDoubleValue() {
        return nbAlias.getAliasDoubleValue();
    }
    public void setAliasDoubleValue(String _aliasDoubleValue) {
        nbAlias.setAliasDoubleValue(_aliasDoubleValue);
    }
    public String getAliasDigit() {
        return nbAlias.getAliasDigit();
    }
    public void setAliasDigit(String _aliasDigit) {
        nbAlias.setAliasDigit(_aliasDigit);
    }
    public String getAliasIsDigit() {
        return nbAlias.getAliasIsDigit();
    }
    public void setAliasIsDigit(String _aliasIsDigit) {
        nbAlias.setAliasIsDigit(_aliasIsDigit);
    }
    public String getAliasIsLetter() {
        return nbAlias.getAliasIsLetter();
    }
    public void setAliasIsLetter(String _aliasIsLetter) {
        nbAlias.setAliasIsLetter(_aliasIsLetter);
    }
    public String getAliasIsLetterOrDigit() {
        return nbAlias.getAliasIsLetterOrDigit();
    }
    public void setAliasIsLetterOrDigit(String _aliasIsLetterOrDigit) {
        nbAlias.setAliasIsLetterOrDigit(_aliasIsLetterOrDigit);
    }
    public String getAliasIsWordChar() {
        return nbAlias.getAliasIsWordChar();
    }
    public void setAliasIsWordChar(String _aliasIsWordChar) {
        nbAlias.setAliasIsWordChar(_aliasIsWordChar);
    }
    public String getAliasIsLowerCase() {
        return nbAlias.getAliasIsLowerCase();
    }
    public void setAliasIsLowerCase(String _aliasIsLowerCase) {
        nbAlias.setAliasIsLowerCase(_aliasIsLowerCase);
    }
    public String getAliasIsUpperCase() {
        return nbAlias.getAliasIsUpperCase();
    }
    public void setAliasIsUpperCase(String _aliasIsUpperCase) {
        nbAlias.setAliasIsUpperCase(_aliasIsUpperCase);
    }
    public String getAliasIsWhitespace() {
        return nbAlias.getAliasIsWhitespace();
    }
    public void setAliasIsWhitespace(String _aliasIsWhitespace) {
        nbAlias.setAliasIsWhitespace(_aliasIsWhitespace);
    }
    public String getAliasIsSpace() {
        return nbAlias.getAliasIsSpace();
    }
    public void setAliasIsSpace(String _aliasIsSpace) {
        nbAlias.setAliasIsSpace(_aliasIsSpace);
    }
    public String getAliasIsInfinite() {
        return nbAlias.getAliasIsInfinite();
    }
    public void setAliasIsInfinite(String _aliasIsInfinite) {
        nbAlias.setAliasIsInfinite(_aliasIsInfinite);
    }
    public String getAliasIsNan() {
        return nbAlias.getAliasIsNan();
    }
    public void setAliasIsNan(String _aliasIsNan) {
        nbAlias.setAliasIsNan(_aliasIsNan);
    }
    public String getAliasForDigit() {
        return nbAlias.getAliasForDigit();
    }
    public void setAliasForDigit(String _aliasForDigit) {
        nbAlias.setAliasForDigit(_aliasForDigit);
    }
    public String getAliasGetDirectionality() {
        return nbAlias.getAliasGetDirectionality();
    }
    public void setAliasGetDirectionality(String _aliasGetDirectionality) {
        nbAlias.setAliasGetDirectionality(_aliasGetDirectionality);
    }
    public String getAliasGetType() {
        return nbAlias.getAliasGetType();
    }
    public void setAliasGetType(String _aliasGetType) {
        nbAlias.setAliasGetType(_aliasGetType);
    }
    public String getAliasString() {
        return charSeq.getAliasString();
    }
    public void setAliasString(String _aliasString) {
        charSeq.setAliasString(_aliasString);
    }
    public String getAliasLength() {
        return charSeq.getAliasLength();
    }
    public void setAliasLength(String _aliasLength) {
        charSeq.setAliasLength(_aliasLength);
    }
    public String getAliasCharAt() {
        return charSeq.getAliasCharAt();
    }
    public void setAliasCharAt(String _aliasCharAt) {
        charSeq.setAliasCharAt(_aliasCharAt);
    }
    public String getAliasToCharArray() {
        return charSeq.getAliasToCharArray();
    }
    public void setAliasToCharArray(String _aliasToCharArray) {
        charSeq.setAliasToCharArray(_aliasToCharArray);
    }
    public String getAliasSplit() {
        return charSeq.getAliasSplit();
    }
    public void setAliasSplit(String _aliasSplit) {
        charSeq.setAliasSplit(_aliasSplit);
    }
    public String getAliasSplitStrings() {
        return charSeq.getAliasSplitStrings();
    }
    public void setAliasSplitStrings(String _aliasSplitStrings) {
        charSeq.setAliasSplitStrings(_aliasSplitStrings);
    }
    public String getAliasSplitChars() {
        return charSeq.getAliasSplitChars();
    }
    public void setAliasSplitChars(String _aliasSplitChars) {
        charSeq.setAliasSplitChars(_aliasSplitChars);
    }
    public String getAliasReplace() {
        return charSeq.getAliasReplace();
    }
    public void setAliasReplace(String _aliasReplace) {
        charSeq.setAliasReplace(_aliasReplace);
    }
    public String getAliasReplaceMultiple() {
        return charSeq.getAliasReplaceMultiple();
    }
    public void setAliasReplaceMultiple(String _aliasReplaceMultiple) {
        charSeq.setAliasReplaceMultiple(_aliasReplaceMultiple);
    }
    public String getAliasEqualsIgnoreCase() {
        return charSeq.getAliasEqualsIgnoreCase();
    }
    public void setAliasEqualsIgnoreCase(String _aliasEqualsIgnoreCase) {
        charSeq.setAliasEqualsIgnoreCase(_aliasEqualsIgnoreCase);
    }
    public String getAliasCompareToIgnoreCase() {
        return charSeq.getAliasCompareToIgnoreCase();
    }
    public void setAliasCompareToIgnoreCase(String _aliasCompareToIgnoreCase) {
        charSeq.setAliasCompareToIgnoreCase(_aliasCompareToIgnoreCase);
    }
    public String getAliasContains() {
        return charSeq.getAliasContains();
    }
    public void setAliasContains(String _aliasContains) {
        charSeq.setAliasContains(_aliasContains);
    }
    public String getAliasEndsWith() {
        return charSeq.getAliasEndsWith();
    }
    public void setAliasEndsWith(String _aliasEndsWith) {
        charSeq.setAliasEndsWith(_aliasEndsWith);
    }
    public String getAliasStartsWith() {
        return charSeq.getAliasStartsWith();
    }
    public void setAliasStartsWith(String _aliasStartsWith) {
        charSeq.setAliasStartsWith(_aliasStartsWith);
    }
    public String getAliasIndexOf() {
        return charSeq.getAliasIndexOf();
    }
    public void setAliasIndexOf(String _aliasIndexOf) {
        charSeq.setAliasIndexOf(_aliasIndexOf);
    }
    public String getAliasFormat() {
        return charSeq.getAliasFormat();
    }
    public void setAliasFormat(String _aliasFormat) {
        charSeq.setAliasFormat(_aliasFormat);
    }
    public String getAliasGetBytes() {
        return charSeq.getAliasGetBytes();
    }
    public void setAliasGetBytes(String _aliasGetBytes) {
        charSeq.setAliasGetBytes(_aliasGetBytes);
    }
    public String getAliasIsEmpty() {
        return charSeq.getAliasIsEmpty();
    }
    public void setAliasIsEmpty(String _aliasIsEmpty) {
        charSeq.setAliasIsEmpty(_aliasIsEmpty);
    }
    public String getAliasLastIndexOf() {
        return charSeq.getAliasLastIndexOf();
    }
    public void setAliasLastIndexOf(String _aliasLastIndexOf) {
        charSeq.setAliasLastIndexOf(_aliasLastIndexOf);
    }
    public String getAliasRegionMatches() {
        return charSeq.getAliasRegionMatches();
    }
    public void setAliasRegionMatches(String _aliasRegionMatches) {
        charSeq.setAliasRegionMatches(_aliasRegionMatches);
    }
    public String getAliasSubstring() {
        return charSeq.getAliasSubstring();
    }
    public void setAliasSubstring(String _aliasSubstring) {
        charSeq.setAliasSubstring(_aliasSubstring);
    }
    public String getAliasSubSequence() {
        return charSeq.getAliasSubSequence();
    }
    public void setAliasSubSequence(String _aliasSubSequence) {
        charSeq.setAliasSubSequence(_aliasSubSequence);
    }
    public String getAliasToLowerCase() {
        return charSeq.getAliasToLowerCase();
    }
    public void setAliasToLowerCase(String _aliasToLowerCase) {
        charSeq.setAliasToLowerCase(_aliasToLowerCase);
    }
    public String getAliasToUpperCase() {
        return charSeq.getAliasToUpperCase();
    }
    public void setAliasToUpperCase(String _aliasToUpperCase) {
        charSeq.setAliasToUpperCase(_aliasToUpperCase);
    }
    public String getAliasTrim() {
        return charSeq.getAliasTrim();
    }
    public void setAliasTrim(String _aliasTrim) {
        charSeq.setAliasTrim(_aliasTrim);
    }
    public String getAliasStringBuilder() {
        return charSeq.getAliasStringBuilder();
    }
    public void setAliasStringBuilder(String _aliasStringBuilder) {
        charSeq.setAliasStringBuilder(_aliasStringBuilder);
    }
    public String getAliasAppend() {
        return charSeq.getAliasAppend();
    }
    public void setAliasAppend(String _aliasAppend) {
        charSeq.setAliasAppend(_aliasAppend);
    }
    public String getAliasCapacity() {
        return charSeq.getAliasCapacity();
    }
    public void setAliasCapacity(String _aliasCapacity) {
        charSeq.setAliasCapacity(_aliasCapacity);
    }
    public String getAliasClear() {
        return charSeq.getAliasClear();
    }
    public void setAliasClear(String _aliasClear) {
        charSeq.setAliasClear(_aliasClear);
    }
    public String getAliasDelete() {
        return charSeq.getAliasDelete();
    }
    public void setAliasDelete(String _aliasDelete) {
        charSeq.setAliasDelete(_aliasDelete);
    }
    public String getAliasDeleteCharAt() {
        return charSeq.getAliasDeleteCharAt();
    }
    public void setAliasDeleteCharAt(String _aliasDeleteCharAt) {
        charSeq.setAliasDeleteCharAt(_aliasDeleteCharAt);
    }
    public String getAliasEnsureCapacity() {
        return charSeq.getAliasEnsureCapacity();
    }
    public void setAliasEnsureCapacity(String _aliasEnsureCapacity) {
        charSeq.setAliasEnsureCapacity(_aliasEnsureCapacity);
    }
    public String getAliasInsert() {
        return charSeq.getAliasInsert();
    }
    public void setAliasInsert(String _aliasInsert) {
        charSeq.setAliasInsert(_aliasInsert);
    }
    public String getAliasReverse() {
        return charSeq.getAliasReverse();
    }
    public void setAliasReverse(String _aliasReverse) {
        charSeq.setAliasReverse(_aliasReverse);
    }
    public String getAliasSetCharAt() {
        return charSeq.getAliasSetCharAt();
    }
    public void setAliasSetCharAt(String _aliasSetCharAt) {
        charSeq.setAliasSetCharAt(_aliasSetCharAt);
    }
    public String getAliasSetLength() {
        return charSeq.getAliasSetLength();
    }
    public void setAliasSetLength(String _aliasSetLength) {
        charSeq.setAliasSetLength(_aliasSetLength);
    }
    public String getAliasTrimToSize() {
        return charSeq.getAliasTrimToSize();
    }
    public void setAliasTrimToSize(String _aliasTrimToSize) {
        charSeq.setAliasTrimToSize(_aliasTrimToSize);
    }

    public String getAliasNext() {
        return predefTypes.getAliasNext();
    }
    public void setAliasNext(String _aliasNext) {
        predefTypes.setAliasNext(_aliasNext);
    }
    public String getAliasHasNext() {
        return predefTypes.getAliasHasNext();
    }
    public void setAliasHasNext(String _aliasHasNext) {
        predefTypes.setAliasHasNext(_aliasHasNext);
    }
    
    public String getAliasIterableTable() {
        return predefTypes.getAliasIterableTable();
    }
    public void setAliasIterableTable(String _aliasIterableTable) {
        predefTypes.setAliasIterableTable(_aliasIterableTable);
    }
    public String getAliasIteratorTable() {
        return predefTypes.getAliasIteratorTable();
    }
    public void setAliasIteratorTable(String _aliasIteratorTable) {
        predefTypes.setAliasIteratorTable(_aliasIteratorTable);
    }
    public String getAliasIteratorTableType() {
        return predefTypes.getAliasIteratorTableType();
    }
    public void setAliasIteratorTableType(String _aliasIteratorTableType) {
        predefTypes.setAliasIteratorTableType(_aliasIteratorTableType);
    }
    public String getAliasHasNextPair() {
        return predefTypes.getAliasHasNextPair();
    }
    public void setAliasHasNextPair(String _aliasHasNextPair) {
        predefTypes.setAliasHasNextPair(_aliasHasNextPair);
    }
    public String getAliasNextPair() {
        return predefTypes.getAliasNextPair();
    }
    public void setAliasNextPair(String _aliasHasNextPair) {
        predefTypes.setAliasNextPair(_aliasHasNextPair);
    }
    public String getAliasPairType() {
        return predefTypes.getAliasPairType();
    }
    public void setAliasPairType(String _aliasPairType) {
        predefTypes.setAliasPairType(_aliasPairType);
    }
    public String getAliasGetFirst() {
        return predefTypes.getAliasGetFirst();
    }
    public void setAliasGetFirst(String _aliasGetFirst) {
        predefTypes.setAliasGetFirst(_aliasGetFirst);
    }
    public String getAliasGetSecond() {
        return predefTypes.getAliasGetSecond();
    }
    public void setAliasGetSecond(String _aliasGetSecond) {
        predefTypes.setAliasGetSecond(_aliasGetSecond);
    }
    public String getAliasName() {
        return coreNames.getAliasName();
    }
    public void setAliasName(String _aliasName) {
        coreNames.setAliasName(_aliasName);
    }
    public String getAliasOrdinal() {
        return coreNames.getAliasOrdinal();
    }
    public void setAliasOrdinal(String _aliasOrdinal) {
        coreNames.setAliasOrdinal(_aliasOrdinal);
    }
    public String getAliasReplacement() {
        return charSeq.getAliasReplacement();
    }
    public void setAliasReplacement(String _aliasReplacement) {
        charSeq.setAliasReplacement(_aliasReplacement);
    }
    public String getAliasGetOldString() {
        return charSeq.getAliasGetOldString();
    }
    public void setAliasGetOldString(String _aliasGetOldString) {
        charSeq.setAliasGetOldString(_aliasGetOldString);
    }
    public String getAliasGetNewString() {
        return charSeq.getAliasGetNewString();
    }
    public void setAliasGetNewString(String _aliasGetNewString) {
        charSeq.setAliasGetNewString(_aliasGetNewString);
    }
    public String getAliasSetOldString() {
        return charSeq.getAliasSetOldString();
    }
    public void setAliasSetOldString(String _aliasSetOldString) {
        charSeq.setAliasSetOldString(_aliasSetOldString);
    }
    public String getAliasSetNewString() {
        return charSeq.getAliasSetNewString();
    }
    public void setAliasSetNewString(String _aliasSetNewString) {
        charSeq.setAliasSetNewString(_aliasSetNewString);
    }
    public String getAliasAbs() {
        return mathRef.getAliasAbs();
    }
    public void setAliasAbs(String _aliasAbs) {
        mathRef.setAliasAbs(_aliasAbs);
    }
    public String getAliasQuot() {
        return mathRef.getAliasQuot();
    }
    public void setAliasQuot(String _aliasQuot) {
        mathRef.setAliasQuot(_aliasQuot);
    }
    public String getAliasMod() {
        return mathRef.getAliasMod();
    }
    public void setAliasMod(String _aliasMod) {
        mathRef.setAliasMod(_aliasMod);
    }
    public String getAliasErrorInitClass() {
        return coreNames.getAliasErrorInitClass();
    }
    public void setAliasErrorInitClass(String _aliasErrorInitClass) {
        coreNames.setAliasErrorInitClass(_aliasErrorInitClass);
    }
    public String getAliasClone() {
        return coreNames.getAliasClone();
    }
    public void setAliasClone(String _aliasClone) {
        coreNames.setAliasClone(_aliasClone);
    }
    public String getAliasEnumValues() {
        return predefTypes.getAliasEnumValues();
    }
    public void setAliasEnumValues(String _aliasValues) {
        predefTypes.setAliasEnumValues(_aliasValues);
    }
    public String getAliasEnumPredValueOf() {
        return predefTypes.getAliasEnumPredValueOf();
    }
    public void setAliasEnumPredValueOf(String _aliasValues) {
        predefTypes.setAliasEnumPredValueOf(_aliasValues);
    }
    public String getAliasInvokeTarget() {
        return reflect.getAliasInvokeTarget();
    }
    public void setAliasInvokeTarget(String _aliasInvokeTarget) {
        reflect.setAliasInvokeTarget(_aliasInvokeTarget);
    }
    public AliasReflection getReflect() {
        return reflect;
    }
    public String getAliasClassNotFoundError() {
        return reflect.getAliasClassNotFoundError();
    }
    public void setAliasClassNotFoundError(String _aliasClassNotFoundError) {
        reflect.setAliasClassNotFoundError(_aliasClassNotFoundError);
    }

    public String getAliasGetVariableOwner() {
        return reflect.getAliasGetVariableOwner();
    }
    public void setAliasGetVariableOwner(String _aliasTypeVariable) {
        reflect.setAliasGetVariableOwner(_aliasTypeVariable);
    }

    public String getAliasGetGenericVariableOwner() {
        return reflect.getAliasGetGenericVariableOwner();
    }
    public void setAliasGetGenericVariableOwner(String _aliasTypeVariable) {
        reflect.setAliasGetGenericVariableOwner(_aliasTypeVariable);
    }

    public String getAliasGetString() {
        return reflect.getAliasGetString();
    }
    public void setAliasGetString(String _aliasTypeVariable) {
        reflect.setAliasGetString(_aliasTypeVariable);
    }

    public String getAliasClass() {
        return reflect.getAliasClass();
    }
    public void setAliasClass(String _aliasClass) {
        reflect.setAliasClass(_aliasClass);
    }
    public String getAliasFct() {
        return reflect.getAliasFct();
    }
    public void setAliasFct(String _aliasFct) {
        reflect.setAliasFct(_aliasFct);
    }
    public String getAliasCall() {
        return reflect.getAliasCall();
    }
    public void setAliasCall(String _aliasCall) {
        reflect.setAliasCall(_aliasCall);
    }
    public String getAliasAnnotation() {
        return reflect.getAliasAnnotation();
    }
    public void setAliasAnnotation(String _aliasAnnotation) {
        reflect.setAliasAnnotation(_aliasAnnotation);
    }
    public String getAliasAnnotated() {
        return reflect.getAliasAnnotated();
    }
    public void setAliasAnnotated(String _aliasAnnotated) {
        reflect.setAliasAnnotated(_aliasAnnotated);
    }
    public String getAliasGetAnnotations() {
        return reflect.getAliasGetAnnotations();
    }
    public void setAliasGetAnnotations(String _aliasGetAnnotations) {
        reflect.setAliasGetAnnotations(_aliasGetAnnotations);
    }
    public String getAliasGetDefaultValue() {
        return reflect.getAliasGetDefaultValue();
    }
    public void setAliasGetDefaultValue(String _aliasGetDefaultValue) {
        reflect.setAliasGetDefaultValue(_aliasGetDefaultValue);
    }
    public String getAliasGetAnnotationsParameters() {
        return reflect.getAliasGetAnnotationsParameters();
    }
    public void setAliasGetAnnotationsParameters(String _aliasGetAnnotationsParameters) {
        reflect.setAliasGetAnnotationsParameters(_aliasGetAnnotationsParameters);
    }
    public String getAliasGetDeclaredMethods() {
        return reflect.getAliasGetDeclaredMethods();
    }
    public void setAliasGetDeclaredMethods(String _aliasGetDeclaredMethods) {
        reflect.setAliasGetDeclaredMethods(_aliasGetDeclaredMethods);
    }
    public String getAliasGetDeclaredConstructors() {
        return reflect.getAliasGetDeclaredConstructors();
    }
    public void setAliasGetDeclaredConstructors(String _aliasGetDeclaredConstructors) {
        reflect.setAliasGetDeclaredConstructors(_aliasGetDeclaredConstructors);
    }
    public String getAliasGetDeclaredFields() {
        return reflect.getAliasGetDeclaredFields();
    }
    public void setAliasGetDeclaredFields(String _aliasGetDeclaredFields) {
        reflect.setAliasGetDeclaredFields(_aliasGetDeclaredFields);
    }
    public String getAliasMakeGeneric() {
        return reflect.getAliasMakeGeneric();
    }
    public void setAliasMakeGeneric(String _aliasMakeGeneric) {
        reflect.setAliasMakeGeneric(_aliasMakeGeneric);
    }
    public String getAliasGetAllClasses() {
        return reflect.getAliasGetAllClasses();
    }
    public void setAliasGetAllClasses(String _aliasGetAllClasses) {
        reflect.setAliasGetAllClasses(_aliasGetAllClasses);
    }
    public String getAliasGetOperators() {
        return reflect.getAliasGetOperators();
    }
    public void setAliasGetOperators(String _aliasGetOperators) {
        reflect.setAliasGetOperators(_aliasGetOperators);
    }
    public String getAliasConstructor() {
        return reflect.getAliasConstructor();
    }
    public void setAliasConstructor(String _aliasConstructor) {
        reflect.setAliasConstructor(_aliasConstructor);
    }
    public String getAliasField() {
        return reflect.getAliasField();
    }
    public void setAliasField(String _aliasField) {
        reflect.setAliasField(_aliasField);
    }
    public String getAliasMethod() {
        return reflect.getAliasMethod();
    }
    public void setAliasMethod(String _aliasMethod) {
        reflect.setAliasMethod(_aliasMethod);
    }
    public String getAliasInvoke() {
        return reflect.getAliasInvoke();
    }
    public void setAliasInvoke(String _aliasInvoke) {
        reflect.setAliasInvoke(_aliasInvoke);
    }
    public String getAliasNewInstance() {
        return reflect.getAliasNewInstance();
    }
    public void setAliasNewInstance(String _aliasNewInstance) {
        reflect.setAliasNewInstance(_aliasNewInstance);
    }
    public String getAliasIsPolymorph() {
        return reflect.getAliasIsPolymorph();
    }
    public void setAliasIsPolymorph(String _aliasIsPolymorph) {
        reflect.setAliasIsPolymorph(_aliasIsPolymorph);
    }
    public String getAliasSetPolymorph() {
        return reflect.getAliasSetPolymorph();
    }
    public void setAliasSetPolymorph(String _aliasSetPolymorph) {
        reflect.setAliasSetPolymorph(_aliasSetPolymorph);
    }
    public String getAliasIsAbstract() {
        return reflect.getAliasIsAbstract();
    }
    public void setAliasIsAbstract(String _aliasIsAbstract) {
        reflect.setAliasIsAbstract(_aliasIsAbstract);
    }
    public String getAliasGetName() {
        return reflect.getAliasGetName();
    }
    public void setAliasGetName(String _aliasGetName) {
        reflect.setAliasGetName(_aliasGetName);
    }
    public String getAliasGetPrettyName() {
        return reflect.getAliasGetPrettyName();
    }
    public void setAliasGetPrettyName(String _aliasGetName) {
        reflect.setAliasGetPrettyName(_aliasGetName);
    }
    public String getAliasGetField() {
        return reflect.getAliasGetField();
    }
    public void setAliasGetField(String _aliasGetField) {
        reflect.setAliasGetField(_aliasGetField);
    }
    public String getAliasSetField() {
        return reflect.getAliasSetField();
    }
    public void setAliasSetField(String _aliasSetField) {
        reflect.setAliasSetField(_aliasSetField);
    }
    public String getAliasGetClass() {
        return reflect.getAliasGetClass();
    }
    public void setAliasGetClass(String _aliasGetClass) {
        reflect.setAliasGetClass(_aliasGetClass);
    }
    public String getAliasGetEnclosingType() {
        return reflect.getAliasGetEnclosingType();
    }
    public void setAliasGetEnclosingType(String _aliasGetEnclosingType) {
        reflect.setAliasGetEnclosingType(_aliasGetEnclosingType);
    }
    public String getAliasGetDeclaredClasses() {
        return reflect.getAliasGetDeclaredClasses();
    }
    public void setAliasGetDeclaredClasses(String _aliasGetDeclaredClasses) {
        reflect.setAliasGetDeclaredClasses(_aliasGetDeclaredClasses);
    }
    public String getAliasForName() {
        return reflect.getAliasForName();
    }
    public void setAliasForName(String _aliasForName) {
        reflect.setAliasForName(_aliasForName);
    }
    public String getAliasObjectsUtil() {
        return coreNames.getAliasObjectsUtil();
    }
    public void setAliasObjectsUtil(String _aliasObjectsUtil) {
        coreNames.setAliasObjectsUtil(_aliasObjectsUtil);
    }
    public String getAliasSameRef() {
        return coreNames.getAliasSameRef();
    }
    public void setAliasSameRef(String _aliasSameRef) {
        coreNames.setAliasSameRef(_aliasSameRef);
    }
    public String getAliasGetParent() {
        return coreNames.getAliasGetParent();
    }
    public void setAliasGetParent(String _aliasGetParent) {
        coreNames.setAliasGetParent(_aliasGetParent);
    }

    public String getAliasGetSuperClass() {
        return reflect.getAliasGetSuperClass();
    }
    public void setAliasGetSuperClass(String _aliasGetSuperClass) {
        reflect.setAliasGetSuperClass(_aliasGetSuperClass);
    }
    public String getAliasGetGenericSuperClass() {
        return reflect.getAliasGetGenericSuperClass();
    }
    public void setAliasGetGenericSuperClass(String _aliasGetGenericSuperClass) {
        reflect.setAliasGetGenericSuperClass(_aliasGetGenericSuperClass);
    }
    public String getAliasGetInterfaces() {
        return reflect.getAliasGetInterfaces();
    }
    public void setAliasGetInterfaces(String _aliasGetInterfaces) {
        reflect.setAliasGetInterfaces(_aliasGetInterfaces);
    }
    public String getAliasGetGenericInterfaces() {
        return reflect.getAliasGetGenericInterfaces();
    }
    public void setAliasGetGenericInterfaces(String _aliasGetGenericInterfaces) {
        reflect.setAliasGetGenericInterfaces(_aliasGetGenericInterfaces);
    }

    public String getAliasGetLowerBounds() {
        return reflect.getAliasGetLowerBounds();
    }
    public void setAliasGetLowerBounds(String _aliasGetLowerBounds) {
        reflect.setAliasGetLowerBounds(_aliasGetLowerBounds);
    }
    public String getAliasGetUpperBounds() {
        return reflect.getAliasGetUpperBounds();
    }
    public void setAliasGetUpperBounds(String _aliasGetUpperBounds) {
        reflect.setAliasGetUpperBounds(_aliasGetUpperBounds);
    }
    public String getAliasGetComponentType() {
        return reflect.getAliasGetComponentType();
    }
    public void setAliasGetComponentType(String _aliasGetComponentType) {
        reflect.setAliasGetComponentType(_aliasGetComponentType);
    }

    public String getAliasMakeArray() {
        return reflect.getAliasMakeArray();
    }
    public void setAliasMakeArray(String _aliasMakeArray) {
        reflect.setAliasMakeArray(_aliasMakeArray);
    }
    public String getAliasGetParameterTypes() {
        return reflect.getAliasGetParameterTypes();
    }
    public void setAliasGetParameterTypes(String _aliasGetParameterTypes) {
        reflect.setAliasGetParameterTypes(_aliasGetParameterTypes);
    }
    public String getAliasGetTypeParameters() {
        return reflect.getAliasGetTypeParameters();
    }
    public void setAliasGetTypeParameters(String _aliasGetTypeParameters) {
        reflect.setAliasGetTypeParameters(_aliasGetTypeParameters);
    }
    public String getAliasGetParameterNames() {
        return reflect.getAliasGetParameterNames();
    }
    public void setAliasGetParameterNames(String _aliasGetNameParameters) {
        reflect.setAliasGetParameterNames(_aliasGetNameParameters);
    }
    public String getAliasGetGenericReturnType() {
        return reflect.getAliasGetGenericReturnType();
    }
    public void setAliasGetGenericReturnType(String _aliasGetGenericReturnType) {
        reflect.setAliasGetGenericReturnType(_aliasGetGenericReturnType);
    }
    public String getAliasGetReturnType() {
        return reflect.getAliasGetReturnType();
    }
    public void setAliasGetReturnType(String _aliasGetReturnType) {
        reflect.setAliasGetReturnType(_aliasGetReturnType);
    }

    public String getAliasGetActualTypeArguments() {
        return reflect.getAliasGetActualTypeArguments();
    }
    public void setAliasGetActualTypeArguments(
            String _aliasGetActualTypeArguments) {
        reflect.setAliasGetActualTypeArguments(_aliasGetActualTypeArguments);
    }
    public String getAliasGetGenericTypeArguments() {
        return reflect.getAliasGetGenericTypeArguments();
    }
    public void setAliasGetGenericTypeArguments(
            String _aliasGetGenericTypeArguments) {
        reflect.setAliasGetGenericTypeArguments(_aliasGetGenericTypeArguments);
    }
    public String getAliasGetFieldType() {
        return reflect.getAliasGetType();
    }
    public void setAliasGetFieldType(String _aliasGetGenericType) {
        reflect.setAliasGetType(_aliasGetGenericType);
    }
    public String getAliasGetGenericType() {
        return reflect.getAliasGetGenericType();
    }
    public void setAliasGetGenericType(String _aliasGetGenericType) {
        reflect.setAliasGetGenericType(_aliasGetGenericType);
    }
    public String getAliasIsFinal() {
        return reflect.getAliasIsFinal();
    }
    public void setAliasIsFinal(String _aliasIsFinal) {
        reflect.setAliasIsFinal(_aliasIsFinal);
    }
    public String getAliasIsStatic() {
        return reflect.getAliasIsStatic();
    }
    public void setAliasIsStatic(String _aliasIsStatic) {
        reflect.setAliasIsStatic(_aliasIsStatic);
    }
    public String getAliasIsVarargs() {
        return reflect.getAliasIsVarargs();
    }
    public void setAliasIsVarargs(String _aliasIsVarargs) {
        reflect.setAliasIsVarargs(_aliasIsVarargs);
    }
    public String getAliasIsNormal() {
        return reflect.getAliasIsNormal();
    }
    public void setAliasIsNormal(String _aliasIsNormal) {
        reflect.setAliasIsNormal(_aliasIsNormal);
    }
    public String getAliasIsPublic() {
        return reflect.getAliasIsPublic();
    }
    public void setAliasIsPublic(String _aliasIsPublic) {
        reflect.setAliasIsPublic(_aliasIsPublic);
    }
    public String getAliasIsProtected() {
        return reflect.getAliasIsProtected();
    }
    public void setAliasIsProtected(String _aliasIsProtected) {
        reflect.setAliasIsProtected(_aliasIsProtected);
    }
    public String getAliasIsPackage() {
        return reflect.getAliasIsPackage();
    }
    public void setAliasIsPackage(String _aliasIsPackage) {
        reflect.setAliasIsPackage(_aliasIsPackage);
    }
    public String getAliasIsPrivate() {
        return reflect.getAliasIsPrivate();
    }
    public void setAliasIsPrivate(String _aliasIsPrivate) {
        reflect.setAliasIsPrivate(_aliasIsPrivate);
    }
    public String getAliasIsClass() {
        return reflect.getAliasIsClass();
    }
    public void setAliasIsClass(String _aliasIsClass) {
        reflect.setAliasIsClass(_aliasIsClass);
    }

    public String getAliasIsWildCard() {
        return reflect.getAliasIsWildCard();
    }
    public void setAliasIsWildCard(String _aliasIsWildCard) {
        reflect.setAliasIsWildCard(_aliasIsWildCard);
    }

    public String getAliasIsInterface() {
        return reflect.getAliasIsInterface();
    }
    public void setAliasIsInterface(String _aliasIsInterface) {
        reflect.setAliasIsInterface(_aliasIsInterface);
    }
    public String getAliasIsEnum() {
        return reflect.getAliasIsEnum();
    }
    public void setAliasIsEnum(String _aliasIsEnum) {
        reflect.setAliasIsEnum(_aliasIsEnum);
    }
    public String getAliasIsPrimitive() {
        return reflect.getAliasIsPrimitive();
    }
    public void setAliasIsPrimitive(String _aliasIsPrimitive) {
        reflect.setAliasIsPrimitive(_aliasIsPrimitive);
    }
    public String getAliasIsArray() {
        return reflect.getAliasIsArray();
    }
    public void setAliasIsArray(String _aliasIsArray) {
        reflect.setAliasIsArray(_aliasIsArray);
    }
    public String getAliasIsAnnotation() {
        return reflect.getAliasIsAnnotation();
    }
    public void setAliasIsAnnotation(String _aliasIsAnnotation) {
        reflect.setAliasIsAnnotation(_aliasIsAnnotation);
    }
    public String getAliasMakeWildCard() {
        return reflect.getAliasMakeWildCard();
    }
    public void setAliasMakeWildCard(String _aliasMakeWildCard) {
        reflect.setAliasMakeWildCard(_aliasMakeWildCard);
    }
    public String getAliasIsInstance() {
        return reflect.getAliasIsInstance();
    }
    public void setAliasIsInstance(String _aliasIsInstance) {
        reflect.setAliasIsInstance(_aliasIsInstance);
    }
    public String getAliasIsAssignableFrom() {
        return reflect.getAliasIsAssignableFrom();
    }
    public void setAliasIsAssignableFrom(String _aliasIsAssignableFrom) {
        reflect.setAliasIsAssignableFrom(_aliasIsAssignableFrom);
    }
    public String getAliasInit() {
        return reflect.getAliasInit();
    }
    public void setAliasInit(String _aliasInit) {
        reflect.setAliasInit(_aliasInit);
    }
    public String getAliasDefaultInstance() {
        return reflect.getAliasDefaultInstance();
    }
    public void setAliasDefaultInstance(String _aliasDefaultInstance) {
        reflect.setAliasDefaultInstance(_aliasDefaultInstance);
    }
    public String getAliasEnumValueOf() {
        return reflect.getAliasEnumValueOf();
    }
    public void setAliasEnumValueOf(String _aliasEnumValueOf) {
        reflect.setAliasEnumValueOf(_aliasEnumValueOf);
    }
    public String getAliasGetEnumConstants() {
        return reflect.getAliasGetEnumConstants();
    }
    public void setAliasGetEnumConstants(String _aliasGetEnumConstants) {
        reflect.setAliasGetEnumConstants(_aliasGetEnumConstants);
    }
    public String getAliasGetGenericBounds() {
        return reflect.getAliasGetGenericBounds();
    }
    public void setAliasGetGenericBounds(String _aliasGetGenericBounds) {
        reflect.setAliasGetGenericBounds(_aliasGetGenericBounds);
    }
    public String getAliasGetBounds() {
        return reflect.getAliasGetBounds();
    }
    public void setAliasGetBounds(String _aliasGetBounds) {
        reflect.setAliasGetBounds(_aliasGetBounds);
    }
    public String getAliasArrayNewInstance() {
        return reflect.getAliasArrayNewInstance();
    }
    public void setAliasArrayNewInstance(String _aliasArrayNewInstance) {
        reflect.setAliasArrayNewInstance(_aliasArrayNewInstance);
    }
    public String getAliasArrayGet() {
        return reflect.getAliasArrayGet();
    }
    public void setAliasArrayGet(String _aliasArrayGet) {
        reflect.setAliasArrayGet(_aliasArrayGet);
    }
    public String getAliasArraySet() {
        return reflect.getAliasArraySet();
    }
    public void setAliasArraySet(String _aliasArraySet) {
        reflect.setAliasArraySet(_aliasArraySet);
    }
    public String getAliasArrayGetLength() {
        return reflect.getAliasArrayGetLength();
    }
    public void setAliasArrayGetLength(String _aliasArrayGetLength) {
        reflect.setAliasArrayGetLength(_aliasArrayGetLength);
    }
    public String getAliasGetDeclaringClass() {
        return reflect.getAliasGetDeclaringClass();
    }
    public void setAliasGetDeclaringClass(String _aliasGetDeclaringClass) {
        reflect.setAliasGetDeclaringClass(_aliasGetDeclaringClass);
    }
    public AliasMath getMathRef() {
        return mathRef;
    }

    public String getAliasBinQuot() {
        return mathRef.getAliasBinQuot();
    }
    public void setAliasBinQuot(String _aliasBinQuot) {
        mathRef.setAliasBinQuot(_aliasBinQuot);
    }
    public String getAliasBinMod() {
        return mathRef.getAliasBinMod();
    }
    public void setAliasBinMod(String _aliasBinMod) {
        mathRef.setAliasBinMod(_aliasBinMod);
    }
    public String getAliasPlus() {
        return mathRef.getAliasPlus();
    }
    public void setAliasPlus(String _aliasPlus) {
        mathRef.setAliasPlus(_aliasPlus);
    }
    public String getAliasMinus() {
        return mathRef.getAliasMinus();
    }
    public void setAliasMinus(String _aliasMinus) {
        mathRef.setAliasMinus(_aliasMinus);
    }
    public String getAliasMult() {
        return mathRef.getAliasMult();
    }
    public void setAliasMult(String _aliasMult) {
        mathRef.setAliasMult(_aliasMult);
    }
    public String getAliasAnd() {
        return mathRef.getAliasAnd();
    }
    public void setAliasAnd(String _aliasAnd) {
        mathRef.setAliasAnd(_aliasAnd);
    }
    public String getAliasOr() {
        return mathRef.getAliasOr();
    }
    public void setAliasOr(String _aliasOr) {
        mathRef.setAliasOr(_aliasOr);
    }
    public String getAliasXor() {
        return mathRef.getAliasXor();
    }
    public void setAliasXor(String _aliasXor) {
        mathRef.setAliasXor(_aliasXor);
    }
    public String getAliasNegBin() {
        return mathRef.getAliasNegBin();
    }
    public void setAliasNegBin(String _aliasNegBin) {
        mathRef.setAliasNegBin(_aliasNegBin);
    }
    
    public String getAliasNeg() {
        return mathRef.getAliasNeg();
    }
    public void setAliasNeg(String _aliasNeg) {
        mathRef.setAliasNeg(_aliasNeg);
    }
    public String getAliasLt() {
        return mathRef.getAliasLt();
    }
    public void setAliasLt(String _aliasLt) {
        mathRef.setAliasLt(_aliasLt);
    }
    public String getAliasGt() {
        return mathRef.getAliasGt();
    }
    public void setAliasGt(String _aliasGt) {
        mathRef.setAliasGt(_aliasGt);
    }
    public String getAliasLe() {
        return mathRef.getAliasLe();
    }
    public void setAliasLe(String _aliasLe) {
        mathRef.setAliasLe(_aliasLe);
    }
    public String getAliasGe() {
        return mathRef.getAliasGe();
    }
    public void setAliasGe(String _aliasGe) {
        mathRef.setAliasGe(_aliasGe);
    }
    public String getAliasShiftLeft() {
        return mathRef.getAliasShiftLeft();
    }
    public void setAliasShiftLeft(String _aliasShiftLeft) {
        mathRef.setAliasShiftLeft(_aliasShiftLeft);
    }
    public String getAliasShiftRight() {
        return mathRef.getAliasShiftRight();
    }
    public void setAliasShiftRight(String _aliasShiftRight) {
        mathRef.setAliasShiftRight(_aliasShiftRight);
    }
    public String getAliasBitShiftLeft() {
        return mathRef.getAliasBitShiftLeft();
    }
    public void setAliasBitShiftLeft(String _aliasBitShiftLeft) {
        mathRef.setAliasBitShiftLeft(_aliasBitShiftLeft);
    }
    public String getAliasBitShiftRight() {
        return mathRef.getAliasBitShiftRight();
    }
    public void setAliasBitShiftRight(String _aliasBitShiftRight) {
        mathRef.setAliasBitShiftRight(_aliasBitShiftRight);
    }
    public String getAliasRotateLeft() {
        return mathRef.getAliasRotateLeft();
    }
    public void setAliasRotateLeft(String _aliasRotateLeft) {
        mathRef.setAliasRotateLeft(_aliasRotateLeft);
    }
    public String getAliasRotateRight() {
        return mathRef.getAliasRotateRight();
    }
    public void setAliasRotateRight(String _aliasRotateRight) {
        mathRef.setAliasRotateRight(_aliasRotateRight);
    }
    
    public String getAliasStackTraceElement() {
        return stackElt.getAliasStackTraceElement();
    }
    public void setAliasStackTraceElement(String _aliasStackTraceElement) {
        stackElt.setAliasStackTraceElement(_aliasStackTraceElement);
    }
    public String getAliasCurrentStack() {
        return stackElt.getAliasCurrentStack();
    }
    public void setAliasCurrentStack(String _aliasCurrentStack) {
        stackElt.setAliasCurrentStack(_aliasCurrentStack);
    }

    public String getAliasEnumName() {
        return predefTypes.getAliasEnumName();
    }

    public void setAliasEnumName(String _aliasEnumName) {
        predefTypes.setAliasEnumName(_aliasEnumName);
    }

    public String getAliasEnumOrdinal() {
        return predefTypes.getAliasEnumOrdinal();
    }

    public void setAliasEnumOrdinal(String _aliasEnumOrdinal) {
        predefTypes.setAliasEnumOrdinal(_aliasEnumOrdinal);
    }

    public AliasStackTraceElement getStackElt() {
        return stackElt;
    }
    public StringList getPredefinedClasses() {
        return predefinedClasses;
    }
    public void setPredefinedClasses(StringList _predefinedClasses) {
        predefinedClasses = _predefinedClasses;
    }
    public StringList getPredefinedInterfacesInitOrder() {
        return predefinedInterfacesInitOrder;
    }
    public void setPredefinedInterfacesInitOrder(
            StringList _predefinedInterfacesInitOrder) {
        predefinedInterfacesInitOrder = _predefinedInterfacesInitOrder;
    }
    
    public String getTrueString() {
        return trueString;
    }
    public void setTrueString(String _trueString) {
        trueString = _trueString;
    }
    public String getFalseString() {
        return falseString;
    }
    public void setFalseString(String _falseString) {
        falseString = _falseString;
    }
    public String getNullString() {
        return nullString;
    }
    public void setNullString(String _nullString) {
        nullString = _nullString;
    }
    public String getDefaultPkg() {
        return defaultPkg;
    }
    public void setDefaultPkg(String _defaultPkg) {
        defaultPkg = _defaultPkg;
    }

}
