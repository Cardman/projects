package code.expressionlanguage.stds;

import code.expressionlanguage.*;
import code.expressionlanguage.errors.stds.ErrorCat;
import code.expressionlanguage.errors.stds.StdWordError;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.inherits.TypeUtil;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.methods.ForEachLoop;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.PredefinedClasses;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.variables.LocalVariable;
import code.maths.montecarlo.AbMonteCarlo;
import code.util.*;

public abstract class LgNames {
    protected static final String LOC_VAR = ".";

    protected static final String PARS = "()";

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

    public Struct getStringOfObject(ContextEl _cont, Struct _arg) {
        if (_arg instanceof EnumerableStruct) {
            return new StringStruct(((EnumerableStruct)_arg).getName());
        }
        return new StringStruct(_arg.getClassName(_cont));
    }
    public static StringList parseLineArg(String _line) {
        StringList args_ = new StringList();
        StringBuilder arg_ = new StringBuilder();
        int len_ = _line.length();
        int i_ = 0;
        boolean escaped_ = false;
        while (i_ < len_) {
            char cur_ = _line.charAt(i_);
            if (escaped_) {
                escaped_ = false;
                if (cur_ == 'n') {
                    arg_.append('\n');
                    i_++;
                    continue;
                }
                if (cur_ == 'e') {
                    arg_.append(' ');
                    i_++;
                    continue;
                }
                if (cur_ == 't') {
                    arg_.append('\t');
                    i_++;
                    continue;
                }
                if (cur_ == 'c') {
                    if (i_ + 2 < len_) {
                        String sub_ = _line.substring(i_ + 1,i_ + 3);
                        Long char_ = NumParsers.parseLong(sub_, 16);
                        if (char_ != null && char_ >= 0 && char_ < ' ') {
                            char ch_ = (char) char_.intValue();
                            arg_.append(ch_);
                            i_ += 3;
                            continue;
                        }
                    }
                }
                if (cur_ == 'u') {
                    if (i_ + 4 < len_) {
                        String sub_ = _line.substring(i_ + 1,i_ + 5);
                        Long char_ = NumParsers.parseLong(sub_, 16);
                        if (char_ != null && char_ >= 0) {
                            char ch_ = (char) char_.intValue();
                            arg_.append(ch_);
                            i_ += 5;
                            continue;
                        }
                    }
                }
                arg_.append(cur_);
                i_++;
                continue;
            }
            if (cur_ == '\\') {
                escaped_ = true;
                i_++;
                continue;
            }
            if (cur_ == ' ') {
                args_.add(arg_.toString());
                arg_.delete(0,arg_.length());
                i_++;
                continue;
            }
            arg_.append(cur_);
            i_++;
        }
        args_.add(arg_.toString());
        return args_;
    }
    public static String parseValue(String _line) {
        StringBuilder arg_ = new StringBuilder();
        int len_ = _line.length();
        int i_ = 0;
        boolean escaped_ = false;
        while (i_ < len_) {
            char cur_ = _line.charAt(i_);
            if (escaped_) {
                escaped_ = false;
                if (cur_ == 'n') {
                    arg_.append('\n');
                    i_++;
                    continue;
                }
                if (cur_ == 'e') {
                    arg_.append(' ');
                    i_++;
                    continue;
                }
                if (cur_ == 't') {
                    arg_.append('\t');
                    i_++;
                    continue;
                }
                if (cur_ == 'c') {
                    if (i_ + 2 < len_) {
                        String sub_ = _line.substring(i_ + 1,i_ + 3);
                        Long char_ = NumParsers.parseLong(sub_, 16);
                        if (char_ != null && char_ >= 0 && char_ < ' ') {
                            char ch_ = (char) char_.intValue();
                            arg_.append(ch_);
                            i_ += 3;
                            continue;
                        }
                    }
                }
                if (cur_ == 'u') {
                    if (i_ + 4 < len_) {
                        String sub_ = _line.substring(i_ + 1,i_ + 5);
                        Long char_ = NumParsers.parseLong(sub_, 16);
                        if (char_ != null && char_ >= 0) {
                            char ch_ = (char) char_.intValue();
                            arg_.append(ch_);
                            i_ += 5;
                            continue;
                        }
                    }
                }
                arg_.append(cur_);
                i_++;
                continue;
            }
            if (cur_ == '\\') {
                escaped_ = true;
                i_++;
                continue;
            }
            arg_.append(cur_);
            i_++;
        }
        return arg_.toString();
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
        list_.add(getAliasAnnotationType());
        list_.add(getAliasClassType());
        list_.add(getAliasConstructor());
        list_.add(getAliasFct());
        list_.add(getAliasField());
        list_.add(getAliasMethod());
        list_.add(getAliasObjectsUtil());
        list_.add(getAliasStringUtil());
        list_.add(getAliasResources());
        list_.add(getAliasClassNotFoundError());
        list_.add(getAliasErrorInitClass());
        list_.add(getAliasInvokeTarget());
        list_.add(getAliasEnumType());
        list_.add(getAliasIterable());
        list_.add(getAliasIteratorType());
        list_.add(getAliasEnumParam());
        list_.add(getAliasEnums());
        list_.add(getAliasIteratorTableType());
        list_.add(getAliasIterableTable());
        list_.add(getAliasPairType());
        list_.add(getAliasMath());
        list_.add(getAliasStackTraceElement());
        list_.add(getAliasBadEncode());
        list_.add(getAliasBadIndex());
        list_.add(getAliasIllegalArg());
        list_.add(getAliasDivisionZero());
        list_.add(getAliasStore());
        list_.add(getAliasCastType());
        list_.add(getAliasBadSize());
        list_.add(getAliasSof());
        list_.add(getAliasReplacement());
        list_.add(getAliasNullPe());
        list_.add(getAliasNbFormat());
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
                if (StringList.contains(_prims, p)) {
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
                    if (!StringList.isDollarWordChar(c)) {
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
    public CustList<StringList> allMergeTableTypeMethodNames() {
        CustList<StringList> list_ = new CustList<StringList>();
        list_.add(new StringList(
                getAliasIterator(),
                getAliasHasNext(),
                getAliasNext(),
                getAliasIteratorTable(),
                getAliasHasNextPair(),
                getAliasNextPair(),
                getAliasGetFirst(),
                getAliasGetSecond(),
                getAliasEnumOrdinal(),
                getAliasEnumName()
        ));
        return list_;
    }
    public StringMap<StringList> allTableTypeMethodNames() {
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put(getAliasError(), new StringList(
                getAliasCurrentStack(),
                getAliasToStringMethod(),
                getAliasGetMessage(),
                getAliasGetCause()));
        map_.put(getAliasAnnotated(), new StringList(
                getAliasGetFileName(),
                getAliasGetAnnotations(),
                getAliasGetAnnotationsParameters()));
        map_.put(getAliasAnnotationType(), new StringList(getAliasGetString()));
        map_.put(getAliasClassType(), new StringList(
                getAliasGetAnnotations(),
                getAliasGetAnnotationsParameters(),
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
                getAliasGetDeclaredStaticMethods(),
                getAliasGetEnclosingType(),
                getAliasGetEnumConstants(),
                getAliasGetGenericBounds(),
                getAliasGetGenericInterfaces(),
                getAliasGetGenericSuperClass(),
                getAliasGetGenericVariableOwner(),
                getAliasGetInterfaces(),
                getAliasArrayGetLength(),
                getAliasGetLowerBounds(),
                getAliasGetFileName(),
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
                getAliasIsTypeVariable(),
                getAliasIsVariable(),
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
                getAliasGetAnnotations(),
                getAliasGetAnnotationsParameters(),
                getAliasGetDeclaringClass(),
                getAliasGetGenericReturnType(),
                getAliasGetFileName(),
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
                getAliasGetAnnotations(),
                getAliasGetAnnotationsParameters(),
                getAliasArrayGet(),
                getAliasGetDeclaringClass(),
                getAliasGetGenericType(),
                getAliasGetFileName(),
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
                getAliasGetAnnotations(),
                getAliasGetAnnotationsParameters(),
                getAliasGetDeclaringClass(),
                getAliasGetDefaultValue(),
                getAliasGetGenericReturnType(),
                getAliasGetFileName(),
                getAliasGetName(),
                getAliasGetParameterNames(),
                getAliasGetParameterTypes(),
                getAliasGetReturnType(),
                getAliasInvoke(),
                getAliasInvokeDirect(),
                getAliasIsAbstract(),
                getAliasIsFinal(),
                getAliasIsNormal(),
                getAliasIsPackage(),
                getAliasIsPrivate(),
                getAliasIsProtected(),
                getAliasIsPublic(),
                getAliasIsStatic(),
                getAliasIsStaticCall(),
                getAliasIsInstanceMethod(),
                getAliasIsVarargs()));
        map_.put(getAliasObjectsUtil(), new StringList(
                getAliasSameRef(),
                getAliasGetParent(),
                getAliasSetParent()));
        map_.put(getAliasStringUtil(), new StringList(
                getAliasValueOfMethod()));
        map_.put(getAliasResources(), new StringList(
                getAliasReadResourcesNames(),
                getAliasReadResources()));
        map_.put(getAliasEnumType(), new StringList(
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
        map_.put(getAliasIterableTable(), new StringList(
                getAliasIteratorTable()));
        map_.put(getAliasIteratorTableType(), new StringList(
                getAliasHasNextPair(),
                getAliasNextPair()));
        map_.put(getAliasPairType(), new StringList(
                getAliasGetFirst(),
                getAliasGetSecond()));
        map_.put(getAliasStackTraceElement(), new StringList(
                getAliasCurrentStack(),
                getAliasCurrentFullStack(),
                getAliasToStringMethod()));
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
                getAliasRotateRight(),
                getAliasRandom()));
        map_.put(getAliasReplacement(), new StringList(
                getAliasGetNewString(),
                getAliasGetOldString()));
        map_.put(getAliasBoolean(), new StringList(
                getAliasBooleanValue(),
                getAliasCompare(),
                getAliasCompareTo(),
                getAliasEquals(),
                getAliasParseBoolean(),
                getAliasToStringMethod(),
                getAliasValueOfMethod()));
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
                getAliasParseByteOrNull(),
                getAliasShortValue(),
                getAliasToStringMethod()));
        map_.put(getAliasCharSequence(), new StringList(
                getAliasCharAt(),
                getAliasEquals(),
                getAliasCompareTo(),
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
                getAliasToStringMethod(),
                getAliasTrim()));
        map_.put(getAliasCharacter(), new StringList(
                getAliasByteValue(),
                getAliasDoubleValue(),
                getAliasEquals(),
                getAliasFloatValue(),
                getAliasIntValue(),
                getAliasLongValue(),
                getAliasParseInt(),
                getAliasParseIntOrNull(),
                getAliasShortValue(),
                getAliasCharAt(),
                getAliasCharValue(),
                getAliasCompare(),
                getAliasCompareTo(),
                getAliasDigit(),
                getAliasForDigit(),
                getAliasGetCharType(),
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
                getAliasToStringMethod(),
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
                getAliasParseDoubleOrNull(),
                getAliasShortValue(),
                getAliasToStringMethod()));
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
                getAliasParseFloatOrNull(),
                getAliasShortValue(),
                getAliasToStringMethod()));
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
                getAliasParseIntOrNull(),
                getAliasShortValue(),
                getAliasToStringMethod()));
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
                getAliasParseLongOrNull(),
                getAliasShortValue(),
                getAliasToStringMethod()));
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
                getAliasToStringMethod()));
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
                getAliasParseShortOrNull(),
                getAliasShortValue(),
                getAliasToStringMethod()));
        map_.put(getAliasString(), new StringList(
                getAliasEquals(),
                getAliasCompareTo(),
                getAliasCharAt(),
                getAliasCompare(),
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
                getAliasToStringMethod(),
                getAliasTrim(),
                getAliasValueOfMethod()));
        map_.put(getAliasStringBuilder(), new StringList(
                getAliasEquals(),
                getAliasCompareTo(),
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
                getAliasToStringMethod(),
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
                getAliasSame(),
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
                if (_cont.getKeyWords().isKeyWordNotVar(k)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.concat("key word ", k));
                    err_.setErrCat(ErrorCat.WRITE_METHOD_WORD);
                    _cont.getClasses().addStdError(err_);
                }
                if (StringList.contains(_prims, k)) {
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
                err_.setMessage(StringList.concat(e.getKey()," duplicate methods ",e.getValue().display()));
                err_.setErrCat(ErrorCat.DUPLICATE_METHOD_WORD);
                _cont.getClasses().addStdError(err_);
            }
        }
    }

    public void validateMergedDuplicates(ContextEl _cont, CustList<StringList> _methods){
        for (StringList e: _methods) {
            StringList keyWords_ = new StringList(e);
            int size_ = keyWords_.size();
            keyWords_.removeDuplicates();
            if (size_ != keyWords_.size()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.concat("duplicate methods ",e.display()));
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
    public StringMap<StringList> allTableTypeVarTypes() {
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put(getAliasEnumParam(), new StringList(
                getAliasEnumParamVar()));
        map_.put(getAliasIterable(), new StringList(
                getAliasIterableVar()));
        map_.put(getAliasIteratorType(), new StringList(
                getAliasIteratorTypeVar()));
        map_.put(getAliasIterableTable(), new StringList(
                getAliasIterableTableVarFirst(),
                getAliasIterableTableVarSecond()));
        map_.put(getAliasIteratorTableType(), new StringList(
                getAliasIteratorTableTypeVarFirst(),
                getAliasIteratorTableTypeVarSecond()));
        map_.put(getAliasPairType(), new StringList(
                getAliasPairTypeVarFirst(),
                getAliasPairTypeVarSecond()));
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
                if (StringList.contains(_prims, k)) {
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
    public void validateVarTypesContents(ContextEl _cont, StringMap<StringList> _methods, StringList _prims){
        for (EntryCust<String, StringList> e: _methods.entryList()) {
            for (String k: e.getValue()) {
                if (k.isEmpty()) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage("empty word");
                    err_.setErrCat(ErrorCat.WRITE_VAR_TYPE_WORD);
                    _cont.getClasses().addStdError(err_);
                    continue;
                }
                if (_cont.getKeyWords().isKeyWord(k)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.concat("key word ", k));
                    err_.setErrCat(ErrorCat.WRITE_VAR_TYPE_WORD);
                    _cont.getClasses().addStdError(err_);
                }
                if (StringList.contains(_prims, k)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.concat("primitive ", k));
                    err_.setErrCat(ErrorCat.WRITE_VAR_TYPE_WORD);
                    _cont.getClasses().addStdError(err_);
                }
                for (char c: k.toCharArray()) {
                    if (!StringList.isDollarWordChar(c)) {
                        StdWordError err_ = new StdWordError();
                        err_.setMessage(StringList.concat("not word char ", Character.toString(c)));
                        err_.setErrCat(ErrorCat.WRITE_VAR_TYPE_WORD);
                        _cont.getClasses().addStdError(err_);
                        break;
                    }
                }
                if (ContextEl.isDigit(k.charAt(0))) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.concat("digit ", Character.toString(k.charAt(0))));
                    err_.setErrCat(ErrorCat.WRITE_VAR_TYPE_WORD);
                    _cont.getClasses().addStdError(err_);
                }
            }
        }
    }

    public void validateVarTypesDuplicates(ContextEl _cont, StringMap<StringList> _methods){
        for (EntryCust<String, StringList> e: _methods.entryList()) {
            StringList keyWords_ = new StringList(e.getValue());
            int size_ = keyWords_.size();
            keyWords_.removeDuplicates();
            if (size_ != keyWords_.size()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.concat("duplicate types ",e.getValue().display()));
                err_.setErrCat(ErrorCat.DUPLICATE_VAR_TYPE_WORD);
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
            ObjectMap<MethodId, EqList<ClassMethodId>> allOv_ = TypeUtil.getAllInstanceSignatures(t, _cont);
            t.getAllOverridingMethods().putAllMap(allOv_);
        }
    }

    public abstract void buildOther();
    public static ResultErrorStd invokeMethod(ContextEl _cont, ClassMethodId _method, Struct _struct, Argument... _args) {
        LgNames lgNames_ = _cont.getStandards();
        return lgNames_.invoke(_cont,_method,_struct,_args);
    }

    protected ResultErrorStd invoke(ContextEl _cont, ClassMethodId _method, Struct _struct, Argument... _args) {
        ResultErrorStd result_;
        Struct[] args_ = getObjects(_args);
        String type_ = _method.getClassName();
        LgNames lgNames_ = _cont.getStandards();
        String stringBuilderType_ = lgNames_.getAliasStringBuilder();
        result_ = invokeStdMethod(_cont, _method, _struct, _args);
        if (result_.getResult() != null) {
            return result_;
        }
        if (result_.getError() != null) {
            processError(_cont,result_);
            return result_;
        }
        if (StringList.quickEq(type_, lgNames_.getAliasObjectsUtil())) {
            Struct inst_ = args_[0];
            if (!(inst_ instanceof WithParentStruct)) {
                result_.setResult(NullStruct.NULL_VALUE);
                return result_;
            }
            WithParentStruct i_ = (WithParentStruct) inst_;
            Struct par_ = args_[1];
            if (!StringList.quickEq(i_.getParentClassName(),par_.getClassName(_cont))) {
                result_.setResult(NullStruct.NULL_VALUE);
                return result_;
            }
            if (_cont.isContainedSensibleFields(i_)) {
                _cont.failInitEnums();
                return result_;
            }
            i_.setParent(par_);
            result_.setResult(NullStruct.NULL_VALUE);
            return result_;
        }
        String mathType_ = lgNames_.getAliasMath();
        String stringUtil_ = lgNames_.getAliasStringUtil();
        if (StringList.quickEq(type_, stringUtil_)) {
            Argument a_ = new Argument(args_[0]);
            a_ = ExecOperationNode.processString(a_,_cont);
            if (_cont.getCallingState() == null) {
                result_.setResult(a_.getStruct());
            }
            return result_;
        }
        if (StringList.quickEq(type_, mathType_)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                return result_;
            }
            /** mathematics "random" calls in order to facilitate uses,
             * despite of the difference between the JAVA names and the user choice names (parameterized in a text file)*/
            StringList paramList_ = _method.getConstraints().getParametersTypes();
            if (paramList_.isEmpty()) {
                result_.setResult(new DoubleStruct(Math.random()));
            } else {
                result_.setResult(new LongStruct(AbMonteCarlo.randomLong(((NumberStruct) args_[0]).longStruct())));
            }
            return result_;
        }
        if (StringList.quickEq(type_, stringBuilderType_)) {
            result_ = AliasCharSequence.invokeMethod(_cont, _method, _struct, _args);
            processError(_cont, result_);
            return result_;
        }
        result_ = AliasReflection.invokeMethod(_cont, _method, _struct, _args);
        if (result_.getResult() != null) {
            return result_;
        }
        if (result_.getError() != null) {
            processError(_cont,result_);
            return result_;
        }
        if (_cont.hasExceptionOrFailInit()) {
            return result_;
        }
        result_ = lgNames_.getOtherResult(_cont, _struct, _method, args_);
        processError(_cont,result_);
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
        String replType_ = lgNames_.getAliasReplacement();
        if (StringList.quickEq(type_, lgNames_.getAliasResources())) {
        	if (StringList.quickEq(name_, lgNames_.getAliasReadResourcesNames())) {
        		result_.setResult(ResourcesStruct.getResourceNames(_cont));
        	} else {
        		result_.setResult(ResourcesStruct.getResource(_cont, (StringStruct) args_[0]));
        	}
        	return result_;
        }
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
        if (StringList.quickEq(type_, replType_)) {
            ReplacementStruct.calculate(_cont, result_, _method, _struct);
            return result_;
        }
        if (StringList.quickEq(type_, stringType_)
                || StringList.quickEq(type_, lgNames_.getAliasCharSequence())) {
            result_ = AliasCharSequence.invokeStdMethod(_cont, _method, _struct, _args);
            return result_;
        }
        if (StringList.quickEq(type_, lgNames_.getAliasStackTraceElement())) {
            ContextEl c_ = _cont.getContextEl();
            return AliasStackTraceElement.invokeMethod(c_, _method, _struct);
        }
        if (StringList.quickEq(type_, lgNames_.getAliasError())) {
            if (StringList.quickEq(name_, lgNames_.getAliasCurrentStack())) {
                ErroneousStruct err_;
                if (args_.length == 0) {
                    err_ = (ErroneousStruct) _struct;
                    result_.setResult(err_.getStack());
                    return result_;
                }
                if (args_[0] instanceof ErroneousStruct){
                    err_ = (ErroneousStruct) args_[0];
                    result_.setResult(err_.getFullStack());
                    return result_;
                }
                Struct[] arr_ = new Struct[0];
                String cl_ = lgNames_.getAliasStackTraceElement();
                cl_ = PrimitiveTypeUtil.getPrettyArrayType(cl_);
                result_.setResult(new ArrayStruct(arr_, cl_));
                return result_;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasGetMessage())) {
                ErroneousStruct err_ = (ErroneousStruct) _struct;
                result_.setResult(err_.getMessage());
                return result_;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasGetCause())) {
                ErroneousStruct err_ = (ErroneousStruct) _struct;
                result_.setResult(err_.getCause());
                return result_;
            }
            ErroneousStruct err_;
            if (args_.length == 0) {
                err_ = (ErroneousStruct) _struct;
                result_.setResult(err_.getDisplayedString(_cont));
                return result_;
            }
            if (args_[0] instanceof ErroneousStruct){
                err_ = (ErroneousStruct) args_[0];
                result_.setResult(new StringStruct(err_.getStringRep(_cont,err_.getFullStack().getInstance())));
                return result_;
            }
            result_.setResult(new StringStruct(""));
            return result_;
        }
        if (StringList.quickEq(type_, mathType_)) {
            return AliasMath.invokeStdMethod(_cont, _method, _args);
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

    public ResultErrorStd getOtherResult(ContextEl _cont, Struct _instance, ClassMethodId _method, Struct... _args) {
        ResultErrorStd result_ = new ResultErrorStd();

        String name_ = _method.getConstraints().getName();
        LgNames lgNames_ = _cont.getStandards();
        if (StringList.quickEq(name_, lgNames_.getAliasName())) {
            Struct str_ = _args[0];
            if (!(str_ instanceof EnumerableStruct)) {
                result_.setError(lgNames_.getAliasNullPe());
            } else {
                EnumerableStruct en_ = (EnumerableStruct) str_;
                result_.setResult(new StringStruct(en_.getName()));
            }
        } else {
            Struct str_ = _args[0];
            if (!(str_ instanceof EnumerableStruct)) {
                result_.setError(lgNames_.getAliasNullPe());
            } else {
                EnumerableStruct en_ = (EnumerableStruct) str_;
                result_.setResult(new IntStruct(en_.getOrdinal()));
            }
        }
        return result_;
    }

    public Argument defaultInstance(ExecutableCode _cont, String _id) {
        return new Argument(new SimpleObjectStruct());
    }
    public static ResultErrorStd newInstance(ContextEl _cont, ConstructorId _method, Argument... _args) {
        LgNames lgNames_ = _cont.getStandards();
        return lgNames_.instance(_cont,_method,_args);
    }

    protected ResultErrorStd instance(ContextEl _cont, ConstructorId _method, Argument... _args) {
        ResultErrorStd result_;
        Struct[] args_ = getObjects(_args);
        String type_ = _method.getName();
        LgNames lgNames_ = _cont.getStandards();
        String stringBuilderType_ = lgNames_.getAliasStringBuilder();
        result_ = newInstanceStd(_cont, _method, _args);
        if (result_.getResult() != null) {
            return result_;
        }
        if (result_.getError() != null) {
            processError(_cont, result_);
            return result_;
        }
        if (StringList.quickEq(type_, stringBuilderType_)) {
            StringBuilderStruct.instantiate(_cont, result_, _method, args_);
            processError(_cont, result_);
            return result_;
        }
        result_ = lgNames_.getOtherResult(_cont, _method, args_);
        processError(_cont,result_);
        return result_;
    }
    protected static void processError(ContextEl _cont, ResultErrorStd _result) {
        if (_result.getError() != null) {
            String errMessage_ = _result.getErrorMessage();
            if (errMessage_ != null) {
                _cont.setException(new ErrorStruct(_cont,errMessage_,_result.getError()));
            } else {
                _cont.setException(new ErrorStruct(_cont,_result.getError()));
            }
        }
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
            ReplacementStruct.instantiate(result_, args_);
            return result_;
        }
        if (StringList.quickEq(type_, stringType_)) {
            StringStruct.instantiate(lgNames_, result_, _method, args_);
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
        ResultErrorStd res_ = new ResultErrorStd();
        res_.setResult(new SimpleObjectStruct());
        return res_;
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
        } else {
            if (StringList.quickEq(name_, lgNames_.getAliasMinValueField())) {
                result_.setResult(new DoubleStruct(Double.MIN_VALUE));
            } else {
                result_.setResult(new DoubleStruct(Double.MAX_VALUE));
            }
        }
        return result_;
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
        cl_.setExpsIteratorCust(ElUtil.getAnalyzedOperationsReadOnly(exp_, _context, Calculation.staticCalculation(MethodAccessKind.STATIC)));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasIteratorType(),"<?>"));
        _context.getInternVars().put(locName_, locVar_);
        cl_.setHasNextVarCust(locName_);
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(hasNext_,PARS));
        cl_.setExpsHasNextCust(ElUtil.getAnalyzedOperationsReadOnly(exp_, _context, Calculation.staticCalculation(MethodAccessKind.STATIC)));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasIteratorType(),"<?>"));
        _context.getInternVars().put(locName_, locVar_);
        cl_.setNextVarCust(locName_);
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(next_,PARS));
        cl_.setExpsNextCust(ElUtil.getAnalyzedOperationsReadOnly(exp_, _context, Calculation.staticCalculation(MethodAccessKind.STATIC)));
        
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasIterableTable(),"<?,?>"));
        _context.getInternVars().put(locName_, locVar_);
        cl_.setIteratorTableVarCust(locName_);
        String iteratorTable_ = getAliasIteratorTable();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(iteratorTable_,PARS));
        cl_.setExpsIteratorTableCust(ElUtil.getAnalyzedOperationsReadOnly(exp_, _context, Calculation.staticCalculation(MethodAccessKind.STATIC)));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasIteratorTableType(),"<?,?>"));
        _context.getInternVars().put(locName_, locVar_);
        cl_.setHasNextPairVarCust(locName_);
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(hasNextPair_,PARS));
        cl_.setExpsHasNextPairCust(ElUtil.getAnalyzedOperationsReadOnly(exp_, _context, Calculation.staticCalculation(MethodAccessKind.STATIC)));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasIteratorTableType(),"<?,?>"));
        _context.getInternVars().put(locName_, locVar_);
        cl_.setNextPairVarCust(locName_);
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(nextPair_,PARS));
        cl_.setExpsNextPairCust(ElUtil.getAnalyzedOperationsReadOnly(exp_, _context, Calculation.staticCalculation(MethodAccessKind.STATIC)));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasPairType(),"<?,?>"));
        _context.getInternVars().put(locName_, locVar_);
        cl_.setFirstVarCust(locName_);
        String first_ = getAliasGetFirst();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(first_,PARS));
        cl_.setExpsFirstCust(ElUtil.getAnalyzedOperationsReadOnly(exp_, _context, Calculation.staticCalculation(MethodAccessKind.STATIC)));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasPairType(),"<?,?>"));
        _context.getInternVars().put(locName_, locVar_);
        cl_.setSecondVarCust(locName_);
        String second_ = getAliasGetSecond();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(second_,PARS));
        cl_.setExpsSecondCust(ElUtil.getAnalyzedOperationsReadOnly(exp_, _context, Calculation.staticCalculation(MethodAccessKind.STATIC)));
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
        name_ = stds_.getAliasEnumType();
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
        predefinedInterfacesInitOrder.add(stds_.getAliasEnumType());
        return files_;
    }

    public String getStructClassName(Struct _struct, ContextEl _context) {
        return _struct.getClassName(_context);
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
    public String getAliasEnumType() {
        return predefTypes.getAliasEnumType();
    }
    public void setAliasEnumType(String _aliasEnum) {
        predefTypes.setAliasEnumType(_aliasEnum);
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

    public String getAliasGetCause() {
        return coreNames.getAliasGetCause();
    }

    public void setAliasGetCause(String _aliasGetCause) {
        coreNames.setAliasGetCause(_aliasGetCause);
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
    public String getAliasCastType() {
        return coreNames.getAliasCastType();
    }
    public void setAliasCastType(String _aliasCast) {
        coreNames.setAliasCastType(_aliasCast);
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

    public String getAliasIllegalArg() {
        return coreNames.getAliasIllegalArg();
    }

    public void setAliasIllegalArg(String _aliasIllegalArg) {
        coreNames.setAliasIllegalArg(_aliasIllegalArg);
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
    public String getAliasToStringMethod() {
        return nbAlias.getAliasToStringMethod();
    }
    public void setAliasToStringMethod(String _aliasToString) {
        nbAlias.setAliasToStringMethod(_aliasToString);
    }
    public String getAliasValueOfMethod() {
        return nbAlias.getAliasValueOfMethod();
    }
    public void setAliasValueOfMethod(String _aliasValueOf) {
        nbAlias.setAliasValueOfMethod(_aliasValueOf);
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
    public String getAliasParseByteOrNull() {
        return nbAlias.getAliasParseByteOrNull();
    }
    public void setAliasParseByteOrNull(String _aliasParseByte) {
        nbAlias.setAliasParseByteOrNull(_aliasParseByte);
    }
    public String getAliasParseShortOrNull() {
        return nbAlias.getAliasParseShortOrNull();
    }
    public void setAliasParseShortOrNull(String _aliasParseShort) {
        nbAlias.setAliasParseShortOrNull(_aliasParseShort);
    }
    public String getAliasParseIntOrNull() {
        return nbAlias.getAliasParseIntOrNull();
    }
    public void setAliasParseIntOrNull(String _aliasParseInt) {
        nbAlias.setAliasParseIntOrNull(_aliasParseInt);
    }
    public String getAliasParseLongOrNull() {
        return nbAlias.getAliasParseLongOrNull();
    }
    public void setAliasParseLongOrNull(String _aliasParseLong) {
        nbAlias.setAliasParseLongOrNull(_aliasParseLong);
    }
    public String getAliasParseFloatOrNull() {
        return nbAlias.getAliasParseFloatOrNull();
    }
    public void setAliasParseFloatOrNull(String _aliasParseFloat) {
        nbAlias.setAliasParseFloatOrNull(_aliasParseFloat);
    }
    public String getAliasParseDoubleOrNull() {
        return nbAlias.getAliasParseDoubleOrNull();
    }
    public void setAliasParseDoubleOrNull(String _aliasParseDouble) {
        nbAlias.setAliasParseDoubleOrNull(_aliasParseDouble);
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
        return reflect.getAliasGetType();
    }
    public void setAliasGetType(String _aliasGetType) {
        reflect.setAliasGetType(_aliasGetType);
    }
    public String getAliasGetCharType() {
        return nbAlias.getAliasGetCharType();
    }
    public void setAliasGetCharType(String _aliasGetType) {
        nbAlias.setAliasGetCharType(_aliasGetType);
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
    public String getAliasSame() {
        return charSeq.getAliasSame();
    }
    public void setAliasSame(String _aliasSetLength) {
        charSeq.setAliasSame(_aliasSetLength);
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
    public String getAliasReadResourcesNames() {
    	return coreNames.getAliasReadResourcesNames();
    }
    public void setAliasReadResourcesNames(String _aliasReadResourcesNames) {
    	coreNames.setAliasReadResourcesNames(_aliasReadResourcesNames);
    }
    public String getAliasReadResources() {
        return coreNames.getAliasReadResources();
    }
    public void setAliasReadResources(String _aliasReadResources) {
        coreNames.setAliasReadResources(_aliasReadResources);
    }
    public String getAliasResources() {
        return coreNames.getAliasResources();
    }
    public void setAliasResources(String _aliasResources) {
        coreNames.setAliasResources(_aliasResources);
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

    public String getAliasIterableVar() {
        return predefTypes.getAliasIterableVar();
    }

    public void setAliasIterableVar(String aliasIterableVar) {
        predefTypes.setAliasIterableVar(aliasIterableVar);
    }

    public String getAliasIteratorTypeVar() {
        return predefTypes.getAliasIteratorTypeVar();
    }

    public void setAliasIteratorTypeVar(String aliasIteratorTypeVar) {
        predefTypes.setAliasIteratorTypeVar(aliasIteratorTypeVar);
    }

    public String getAliasIterableTableVarFirst() {
        return predefTypes.getAliasIterableTableVarFirst();
    }

    public void setAliasIterableTableVarFirst(String aliasIterableTableVarFirst) {
        predefTypes.setAliasIterableTableVarFirst(aliasIterableTableVarFirst);
    }

    public String getAliasIterableTableVarSecond() {
        return predefTypes.getAliasIterableTableVarSecond();
    }

    public void setAliasIterableTableVarSecond(String aliasIterableTableVarSecond) {
        predefTypes.setAliasIterableTableVarSecond(aliasIterableTableVarSecond);
    }

    public String getAliasIteratorTableTypeVarFirst() {
        return predefTypes.getAliasIteratorTableTypeVarFirst();
    }

    public void setAliasIteratorTableTypeVarFirst(String aliasIteratorTableTypeVarFirst) {
        predefTypes.setAliasIteratorTableTypeVarFirst(aliasIteratorTableTypeVarFirst);
    }

    public String getAliasIteratorTableTypeVarSecond() {
        return predefTypes.getAliasIteratorTableTypeVarSecond();
    }

    public void setAliasIteratorTableTypeVarSecond(String aliasIteratorTableTypeVarSecond) {
        predefTypes.setAliasIteratorTableTypeVarSecond(aliasIteratorTableTypeVarSecond);
    }

    public String getAliasPairTypeVarFirst() {
        return predefTypes.getAliasPairTypeVarFirst();
    }

    public void setAliasPairTypeVarFirst(String aliasPairTypeVarFirst) {
        predefTypes.setAliasPairTypeVarFirst(aliasPairTypeVarFirst);
    }

    public String getAliasPairTypeVarSecond() {
        return predefTypes.getAliasPairTypeVarSecond();
    }

    public void setAliasPairTypeVarSecond(String aliasPairTypeVarSecond) {
        predefTypes.setAliasPairTypeVarSecond(aliasPairTypeVarSecond);
    }

    public String getAliasEnumParamVar() {
        return predefTypes.getAliasEnumParamVar();
    }

    public void setAliasEnumParamVar(String aliasEnumParamVar) {
        predefTypes.setAliasEnumParamVar(aliasEnumParamVar);
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

    public String getAliasClassType() {
        return reflect.getAliasClassType();
    }
    public void setAliasClassType(String _aliasClass) {
        reflect.setAliasClassType(_aliasClass);
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
    public String getAliasAnnotationType() {
        return reflect.getAliasAnnotationType();
    }
    public void setAliasAnnotationType(String _aliasAnnotation) {
        reflect.setAliasAnnotationType(_aliasAnnotation);
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

    public String getAliasGetDeclaredStaticMethods() {
        return reflect.getAliasGetDeclaredStaticMethods();
    }

    public void setAliasGetDeclaredStaticMethods(String _aliasGetDeclaredStaticMethods) {
        reflect.setAliasGetDeclaredStaticMethods(_aliasGetDeclaredStaticMethods);
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
    public String getAliasInvokeDirect() {
        return reflect.getAliasInvokeDirect();
    }
    public void setAliasInvokeDirect(String _aliasInvoke) {
        reflect.setAliasInvokeDirect(_aliasInvoke);
    }
    public String getAliasNewInstance() {
        return reflect.getAliasNewInstance();
    }
    public void setAliasNewInstance(String _aliasNewInstance) {
        reflect.setAliasNewInstance(_aliasNewInstance);
    }

    public String getAliasIsAbstract() {
        return reflect.getAliasIsAbstract();
    }
    public void setAliasIsAbstract(String _aliasIsAbstract) {
        reflect.setAliasIsAbstract(_aliasIsAbstract);
    }
    public String getAliasGetFileName() {
        return reflect.getAliasGetFileName();
    }
    public void setAliasGetFileName(String _aliasGetName) {
        reflect.setAliasGetFileName(_aliasGetName);
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
    public String getAliasStringUtil() {
        return coreNames.getAliasStringUtil();
    }
    public void setAliasStringUtil(String _aliasObjectsUtil) {
        coreNames.setAliasStringUtil(_aliasObjectsUtil);
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
    public String getAliasSetParent() {
        return coreNames.getAliasSetParent();
    }
    public void setAliasSetParent(String _aliasGetParent) {
        coreNames.setAliasSetParent(_aliasGetParent);
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
    public String getAliasIsTypeVariable() {
        return reflect.getAliasIsTypeVariable();
    }
    public void setAliasIsTypeVariable(String _aliasIsFinal) {
        reflect.setAliasIsTypeVariable(_aliasIsFinal);
    }
    public String getAliasIsVariable() {
        return reflect.getAliasIsVariable();
    }
    public void setAliasIsVariable(String _aliasIsFinal) {
        reflect.setAliasIsVariable(_aliasIsFinal);
    }
    public String getAliasIsStatic() {
        return reflect.getAliasIsStatic();
    }
    public void setAliasIsStatic(String _aliasIsStatic) {
        reflect.setAliasIsStatic(_aliasIsStatic);
    }
    public String getAliasIsStaticCall() {
        return reflect.getAliasIsStaticCall();
    }
    public void setAliasIsStaticCall(String _aliasIsStatic) {
        reflect.setAliasIsStaticCall(_aliasIsStatic);
    }

    public String getAliasIsInstanceMethod() {
        return reflect.getAliasIsInstanceMethod();
    }

    public void setAliasIsInstanceMethod(String _aliasIsInstanceMethod) {
        reflect.setAliasIsInstanceMethod(_aliasIsInstanceMethod);
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
    public String getAliasRandom() {
        return mathRef.getAliasRandom();
    }
    public void setAliasRandom(String _aliasRotateRight) {
        mathRef.setAliasRandom(_aliasRotateRight);
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
    public String getAliasCurrentFullStack() {
        return stackElt.getAliasCurrentFullStack();
    }
    public void setAliasCurrentFullStack(String _aliasCurrentStack) {
        stackElt.setAliasCurrentFullStack(_aliasCurrentStack);
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

    public StringList getPredefinedInterfacesInitOrder() {
        return predefinedInterfacesInitOrder;
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
