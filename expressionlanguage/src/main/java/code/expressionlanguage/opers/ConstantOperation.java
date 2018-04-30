package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ArgumentCall;
import code.expressionlanguage.ConstType;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.ElResolver;
import code.expressionlanguage.InitClassState;
import code.expressionlanguage.InitializatingClass;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.ParsedArgument;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.NotInitializedClass;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.BadFormatNumber;
import code.expressionlanguage.methods.util.BadFormatPathError;
import code.expressionlanguage.methods.util.EmptyPartError;
import code.expressionlanguage.methods.util.FinalPart;
import code.expressionlanguage.methods.util.StaticAccessError;
import code.expressionlanguage.methods.util.StaticAccessThisError;
import code.expressionlanguage.methods.util.UndefinedFieldError;
import code.expressionlanguage.methods.util.UndefinedVariableError;
import code.expressionlanguage.opers.util.CausingErrorStruct;
import code.expressionlanguage.opers.util.CharStruct;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.FieldInfo;
import code.expressionlanguage.opers.util.FieldResult;
import code.expressionlanguage.opers.util.FieldableStruct;
import code.expressionlanguage.opers.util.IntStruct;
import code.expressionlanguage.opers.util.LongStruct;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.NumberStruct;
import code.expressionlanguage.opers.util.SearchingMemberStatus;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class ConstantOperation extends LeafOperation implements SettableElResult, PossibleIntermediateDotted {
    private static final String LENGTH = "length";
    private static final String TAB = "\t";
    private static final String BOUND = "\b";
    private static final String LINE_FEED = "\r";
    private static final String LINE_RETURN = "\n";
    private static final String FORM = "\f";

    private boolean variable;
    
    private boolean finalField;

    private boolean immutablePart;

    private boolean possibleInitClass;

    private ClassField fieldId;
    private boolean excVar;
    private FieldInfo fieldMetaInfo;

    private boolean catString;

    private String variableName = EMPTY_STRING;
    private ClassArgumentMatching previousResultClass;
    private boolean staticAccess;
    private boolean intermediate;

    private Argument previousArgument;

    public ConstantOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }
    @Override
    public boolean isCalculated(IdMap<OperationNode, ArgumentsPair> _nodes) {
        OperationNode op_ = this;
        while (op_ != null) {
            if (_nodes.getVal(op_).getArgument() != null) {
                return true;
            }
            op_ = op_.getParent();
        }
        return false;
    }

    @Override
    public boolean isCalculated() {
        OperationNode op_ = this;
        while (op_ != null) {
            if (op_.getArgument() != null) {
                return true;
            }
            op_ = op_.getParent();
        }
        return false;
    }

    @Override
    public void analyze(Analyzable _conf,
            String _fieldName) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_) + relativeOff_;
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String argClName_;
        if (str_.isEmpty()) {
            EmptyPartError emptyPart_ = new EmptyPartError();
            emptyPart_.setFileName(_conf.getCurrentFileName());
            emptyPart_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(emptyPart_);
            argClName_ = _conf.getStandards().getAliasObject();
            setResultClass(new ClassArgumentMatching(argClName_));
            return;
        }
        if (!isIntermediateDottedOperation()) {
            staticAccess = _conf.isStaticContext();
        }
        if (isVararg()) {
            str_ = str_.substring(CustList.SECOND_INDEX);
            str_ = StringList.removeAllSpaces(str_);
            Argument a_ = new Argument();
            if (!checkCorrect(_conf, str_, false, 0)) {
                argClName_ = _conf.getStandards().getAliasObject();
            } else {
                argClName_ = str_;
            }
            setSimpleArgument(a_);
            setResultClass(new ClassArgumentMatching(argClName_));
            return;
        }
        Argument a_ = new Argument();
        LgNames stds_ = _conf.getStandards();
        String stringType_;
        stringType_ = stds_.getAliasString();
        if (op_.getConstType() == ConstType.TRUE_CST) {
            argClName_ = stds_.getAliasPrimBoolean();
            a_.setObject(true);
            setSimpleArgument(a_);
            setResultClass(new ClassArgumentMatching(argClName_));
            return;
        }
        if (op_.getConstType() == ConstType.FALSE_CST) {
            argClName_ = stds_.getAliasPrimBoolean();
            a_.setObject(false);
            setSimpleArgument(a_);
            setResultClass(new ClassArgumentMatching(argClName_));
            return;
        }
        if (op_.getConstType() == ConstType.NULL_CST) {
            argClName_ = EMPTY_STRING;
            setSimpleArgument(a_);
            setResultClass(new ClassArgumentMatching(argClName_));
            return;
        }
        if (op_.getConstType() == ConstType.STRING) {
            str_ = str_.substring(CustList.SECOND_INDEX, str_.lastIndexOf(DELIMITER_STRING));
            StringBuilder strBuilder_ = new StringBuilder();
            StringBuilder unicodeString_ = new StringBuilder();
            int unicode_ = 0;
            boolean escaped_ = false;
            for (char c: str_.toCharArray()) {
                if (escaped_) {
                    if (unicode_ > 0) {
                        unicodeString_.append(c);
                        if (unicode_ < ElResolver.UNICODE_SIZE) {
                            unicode_++;
                        } else {
                            unicode_ = 0;
                            escaped_ = false;
                            char i_ = LgNames.parseCharSixteen(unicodeString_.toString());
                            strBuilder_.append(i_);
                        }
                        continue;
                    }
                    if (c == IND_BOUND) {
                        strBuilder_.append(BOUND);
                        escaped_ = false;
                        continue;
                    }
                    if (c == IND_LINE) {
                        strBuilder_.append(LINE_RETURN);
                        escaped_ = false;
                        continue;
                    }
                    if (c == IND_FORM) {
                        strBuilder_.append(FORM);
                        escaped_ = false;
                        continue;
                    }
                    if (c == IND_LINE_FEED) {
                        strBuilder_.append(LINE_FEED);
                        escaped_ = false;
                        continue;
                    }
                    if (c == IND_TAB) {
                        strBuilder_.append(TAB);
                        escaped_ = false;
                        continue;
                    }
                    if (c == DELIMITER_STRING) {
                        strBuilder_.append(DELIMITER_STRING);
                        escaped_ = false;
                        continue;
                    }
                    if (c == ESCAPE_META_CHAR) {
                        strBuilder_.append(ESCAPE_META_CHAR);
                        escaped_ = false;
                        continue;
                    }
                    unicode_ = 1;
                    unicodeString_ = new StringBuilder();
                    continue;
                }
                if (c == ESCAPE_META_CHAR) {
                    escaped_ = true;
                    continue;
                }
                strBuilder_.append(c);
            }
            a_.setObject(strBuilder_.toString());
            setSimpleArgument(a_);
            setResultClass(new ClassArgumentMatching(stringType_));
            return;
        }
        if (op_.getConstType() == ConstType.CHARACTER) {
            argClName_ = stds_.getAliasPrimChar();
            str_ = str_.substring(CustList.SECOND_INDEX, str_.lastIndexOf(DELIMITER_CHAR));
            StringBuilder strBuilder_ = new StringBuilder();
            StringBuilder unicodeString_ = new StringBuilder();
            int unicode_ = 0;
            boolean escaped_ = false;
            for (char c: str_.toCharArray()) {
                if (escaped_) {
                    if (unicode_ > 0) {
                        unicodeString_.append(c);
                        if (unicode_ < ElResolver.UNICODE_SIZE) {
                            unicode_++;
                        } else {
                            unicode_ = 0;
                            escaped_ = false;
                            char i_ = LgNames.parseCharSixteen(unicodeString_.toString());
                            strBuilder_.append(i_);
                        }
                        continue;
                    }
                    if (c == IND_BOUND) {
                        strBuilder_.append(BOUND);
                        escaped_ = false;
                        continue;
                    }
                    if (c == IND_LINE) {
                        strBuilder_.append(LINE_RETURN);
                        escaped_ = false;
                        continue;
                    }
                    if (c == IND_FORM) {
                        strBuilder_.append(FORM);
                        escaped_ = false;
                        continue;
                    }
                    if (c == IND_LINE_FEED) {
                        strBuilder_.append(LINE_FEED);
                        escaped_ = false;
                        continue;
                    }
                    if (c == IND_TAB) {
                        strBuilder_.append(TAB);
                        escaped_ = false;
                        continue;
                    }
                    if (c == DELIMITER_CHAR) {
                        strBuilder_.append(DELIMITER_CHAR);
                        escaped_ = false;
                        continue;
                    }
                    if (c == ESCAPE_META_CHAR) {
                        strBuilder_.append(ESCAPE_META_CHAR);
                        escaped_ = false;
                        continue;
                    }
                    unicode_ = 1;
                    unicodeString_ = new StringBuilder();
                    continue;
                }
                if (c == ESCAPE_META_CHAR) {
                    escaped_ = true;
                    continue;
                }
                strBuilder_.append(c);
            }
            a_.setObject(strBuilder_.toString().charAt(0));
            setSimpleArgument(a_);
            setResultClass(new ClassArgumentMatching(argClName_));
            return;
        }
        str_ = StringList.removeAllSpaces(str_);
        if (op_.getConstType() == ConstType.NUMBER) {
            ParsedArgument parsed_ = ParsedArgument.parse(op_.getNbInfos(), _conf);
            String argClassName_ = parsed_.getType();
            if (argClassName_.isEmpty()) {
                BadFormatNumber badFormat_ = new BadFormatNumber();
                badFormat_.setNumber(str_);
                badFormat_.setFileName(_conf.getCurrentFileName());
                badFormat_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().getErrorsDet().add(badFormat_);
                argClassName_ = stds_.getAliasPrimDouble();
            }
            Argument arg_ = Argument.createVoid();
            arg_.setStruct(parsed_.getStruct());
            setSimpleArgument(arg_);
            setResultClass(new ClassArgumentMatching(argClassName_));
            return;
        }
        if (op_.getConstType() == ConstType.THIS_KEYWORD) {
            String arg_ = _conf.getGlobalClass();
            if (arg_ == null) {
                arg_ = stds_.getAliasObject();
            }
            if (isStaticAccess()) {
                StaticAccessThisError static_ = new StaticAccessThisError();
                static_.setClassName(arg_);
                static_.setFileName(_conf.getCurrentFileName());
                static_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().getErrorsDet().add(static_);
            }
            if (getParent() == null) {
                immutablePart = true;
            }
            setResultClass(new ClassArgumentMatching(arg_));
            return;
        }
        if (op_.getConstType() == ConstType.CUST_FIELD || op_.getConstType() == ConstType.CLASSCHOICE_KEYWORD || op_.getConstType() == ConstType.SUPER_KEYWORD) {
            ClassArgumentMatching cl_;
            if (isIntermediateDottedOperation()) {
                cl_ = getPreviousResultClass();
            } else {
                cl_ = new ClassArgumentMatching(_conf.getGlobalClass());
            }
            String clCurName_;
            if (op_.getConstType() == ConstType.CLASSCHOICE_KEYWORD) {
                StringList classMethod_ = StringList.splitStrings(str_, STATIC_CALL);
                if (classMethod_.size() != 2) {
                    BadFormatPathError badFormat_ = new BadFormatPathError();
                    badFormat_.setPath(str_);
                    badFormat_.setFileName(_conf.getCurrentFileName());
                    badFormat_.setRc(_conf.getCurrentLocation());
                    _conf.getClasses().getErrorsDet().add(badFormat_);
                    setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                    return;
                }
                String className_ = classMethod_.first();
                if (!className_.startsWith(CLASS_CHOICE_PREF)) {
                    BadFormatPathError badFormat_ = new BadFormatPathError();
                    badFormat_.setPath(str_);
                    badFormat_.setFileName(_conf.getCurrentFileName());
                    badFormat_.setRc(_conf.getCurrentLocation());
                    _conf.getClasses().getErrorsDet().add(badFormat_);
                    setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                    return;
                }
                int lenPref_ = CLASS_CHOICE_PREF.length();
                className_ = className_.substring(lenPref_);
                className_ = StringList.removeAllSpaces(className_);
                className_ = className_.replace(EXTERN_CLASS, DOT_VAR);
                if (className_.contains(Templates.TEMPLATE_BEGIN)) {
                    if (!checkCorrect(_conf, className_, true, lenPref_)) {
                        setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                        return;
                    }
                } else {
                    if (!checkExistBase(_conf, false, className_, true, lenPref_)) {
                        setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                        return;
                    }
                }
                clCurName_ = className_;
            } else {
                if (cl_ == null) {
                    StaticAccessError static_ = new StaticAccessError();
                    static_.setFileName(_conf.getCurrentFileName());
                    static_.setRc(_conf.getCurrentLocation());
                    _conf.getClasses().getErrorsDet().add(static_);
                    setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                    return;
                }
                clCurName_ = cl_.getName();
            }

            String base_ = StringList.getAllTypes(clCurName_).first();
            String key_;
            boolean superClassAccess_ = true;
            boolean baseClassAccess_ = true;
            FieldResult r_;
            FieldInfo e_;
            if (op_.getConstType() == ConstType.CLASSCHOICE_KEYWORD) {
                StringList classMethod_ = StringList.splitStrings(str_, STATIC_CALL);
                key_ = classMethod_.last();
                superClassAccess_ = false;
                r_ = getDeclaredCustField(_conf, isStaticAccess(), new ClassArgumentMatching(clCurName_), baseClassAccess_, superClassAccess_, key_);
            } else if (op_.getConstType() == ConstType.SUPER_KEYWORD) {
                key_ = str_;
                baseClassAccess_ = false;
                r_ = getDeclaredCustField(_conf, isStaticAccess(), new ClassArgumentMatching(clCurName_), baseClassAccess_, superClassAccess_, key_);
            } else {
                key_ = str_;
                r_ = getDeclaredCustField(_conf, isStaticAccess(), cl_, baseClassAccess_, superClassAccess_, key_);
            }
            if (r_.getStatus() == SearchingMemberStatus.ZERO) {
                UndefinedFieldError und_ = new UndefinedFieldError();
                und_.setClassName(base_);
                und_.setFileName(key_);
                und_.setFileName(_conf.getCurrentFileName());
                und_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().getErrorsDet().add(und_);
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
            e_ = r_.getId();
            fieldMetaInfo = e_;
            String c_ = fieldMetaInfo.getType();
            if (resultCanBeSet()) {
                if (fieldMetaInfo.isFinalField()) {
                    finalField = true;
                }
                if (StringList.quickEq(c_, stringType_)) {
                    catString = true;
                }
            }
            fieldId = new ClassField(e_.getDeclaringBaseClass(), e_.getName());
            setResultClass(new ClassArgumentMatching(c_));
            return;
        }
        if (getParent() instanceof AffectationOperation && getParent().getParent() == null && _conf.isMerged()) {
            excVar = true;
        }
        if (op_.getConstType().isVariable() || excVar) {
            if (isIntermediateDottedOperation()) {
                setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
                FinalPart final_ = new FinalPart();
                final_.setClassName(_conf.getGlobalClass());
                final_.setId(str_);
                final_.setFileName(_conf.getCurrentFileName());
                final_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().getErrorsDet().add(final_);
            }
            if (op_.getConstType() == ConstType.PARAM) {
                if (getParent() == null) {
                    immutablePart = true;
                }
                variableName = str_;
                LocalVariable locVar_ = _conf.getParameters().getVal(variableName);
                if (locVar_ != null) {
                    String paramType_ = locVar_.getClassName();
                    if (paramType_.endsWith(VARARG_SUFFIX)) {
                        paramType_ = StringList.replace(paramType_, VARARG_SUFFIX, EMPTY_STRING);
                        paramType_ = PrimitiveTypeUtil.getPrettyArrayType(paramType_);
                    }
                    setResultClass(new ClassArgumentMatching(paramType_));
                    return;
                }
                UndefinedVariableError und_ = new UndefinedVariableError();
                und_.setId(variableName);
                und_.setFileName(_conf.getCurrentFileName());
                und_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().getErrorsDet().add(und_);
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
            if (op_.getConstType() == ConstType.CATCH_VAR) {
                if (getParent() == null) {
                    immutablePart = true;
                }
                variableName = str_;
                LocalVariable locVar_ = _conf.getCatchVars().getVal(variableName);
                if (locVar_ != null) {
                    setResultClass(new ClassArgumentMatching(locVar_.getClassName()));
                    return;
                }
                UndefinedVariableError und_ = new UndefinedVariableError();
                und_.setId(variableName);
                und_.setFileName(_conf.getCurrentFileName());
                und_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().getErrorsDet().add(und_);
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
            if (op_.getConstType() == ConstType.LOC_VAR || excVar) {
                variableName = str_;
                if (excVar) {
                    setResultClass(new ClassArgumentMatching(_conf.getCurrentVarSetting()));
                    return;
                }
                LocalVariable locVar_ = _conf.getLocalVars().getVal(variableName);
                if (locVar_ != null) {
                    String c_ = locVar_.getClassName();
                    if (StringList.quickEq(c_, stringType_)) {
                        catString = true;
                    }
                    setResultClass(new ClassArgumentMatching(c_));
                    return;
                }
                UndefinedVariableError und_ = new UndefinedVariableError();
                und_.setId(variableName);
                und_.setFileName(_conf.getCurrentFileName());
                und_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().getErrorsDet().add(und_);
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
            if (op_.getConstType() == ConstType.LOOP_INDEX) {
                if (getParent() == null) {
                    immutablePart = true;
                }
                variableName = str_;
                LoopVariable locVar_ = _conf.getVars().getVal(variableName);
                if (locVar_ != null) {
                    setResultClass(new ClassArgumentMatching(locVar_.getIndexClassName()));
                    return;
                }
                UndefinedVariableError und_ = new UndefinedVariableError();
                und_.setId(variableName);
                und_.setFileName(_conf.getCurrentFileName());
                und_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().getErrorsDet().add(und_);
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
            if (op_.getConstType() == ConstType.LOOP_VAR) {
                if (getParent() == null) {
                    immutablePart = true;
                }
                variableName = str_;
                LoopVariable locVar_ = _conf.getVars().getVal(variableName);
                if (locVar_ != null) {
                    setResultClass(new ClassArgumentMatching(locVar_.getClassName()));
                    return;
                }
                UndefinedVariableError und_ = new UndefinedVariableError();
                und_.setId(variableName);
                und_.setFileName(_conf.getCurrentFileName());
                und_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().getErrorsDet().add(und_);
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
        }
        analyzeNativeField(_conf, str_);
    }

    private void analyzeNativeField(Analyzable _conf, String _key) {
        ClassArgumentMatching cl_;
        if (isIntermediateDottedOperation()) {
            cl_ = getPreviousResultClass();
        } else {
            cl_ = new ClassArgumentMatching(_conf.getGlobalClass());
        }
        LgNames stds_ = _conf.getStandards();
        if (cl_ == null || cl_.getName() == null) {
            StaticAccessError static_ = new StaticAccessError();
            static_.setFileName(_conf.getCurrentFileName());
            static_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(static_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        String stringType_;
        stringType_ = stds_.getAliasString();
        if (cl_.isArray()) {
            if (StringList.quickEq(_key, LENGTH)) {
                setResultClass(new ClassArgumentMatching(stds_.getAliasPrimInteger()));
                return;
            }
            UndefinedFieldError und_ = new UndefinedFieldError();
            und_.setClassName(cl_.getName());
            und_.setFileName(_key);
            und_.setFileName(_conf.getCurrentFileName());
            und_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(und_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasPrimInteger()));
            return;
        }
        String str_ = _key;
        String clCurName_ = cl_.getName();
        String base_ = StringList.getAllTypes(clCurName_).first();
        String key_;
        boolean superClassAccess_ = true;
        boolean baseClassAccess_ = true;
        FieldResult r_;
        FieldInfo e_;
        OperationsSequence op_ = getOperations();
        if (op_.getConstType() == ConstType.CLASSCHOICE_KEYWORD) {
            StringList classMethod_ = StringList.splitStrings(str_, STATIC_CALL);
            key_ = classMethod_.last();
            superClassAccess_ = false;
            r_ = getDeclaredCustField(_conf, isStaticAccess(), new ClassArgumentMatching(clCurName_), baseClassAccess_, superClassAccess_, key_);
        } else if (op_.getConstType() == ConstType.SUPER_KEYWORD) {
            key_ = str_;
            baseClassAccess_ = false;
            r_ = getDeclaredCustField(_conf, isStaticAccess(), new ClassArgumentMatching(clCurName_), baseClassAccess_, superClassAccess_, key_);
        } else {
            key_ = str_;
            r_ = getDeclaredCustField(_conf, isStaticAccess(), cl_, baseClassAccess_, superClassAccess_, key_);
        }
        if (r_.getStatus() == SearchingMemberStatus.ZERO) {
            UndefinedFieldError und_ = new UndefinedFieldError();
            und_.setClassName(base_);
            und_.setFileName(key_);
            und_.setFileName(_conf.getCurrentFileName());
            und_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(und_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        e_ = r_.getId();
        fieldMetaInfo = e_;
        String c_ = fieldMetaInfo.getType();
        if (resultCanBeSet()) {
            if (fieldMetaInfo.isFinalField()) {
                finalField = true;
            }
            if (StringList.quickEq(c_, stringType_)) {
                catString = true;
            }
        }
        fieldId = new ClassField(e_.getDeclaringBaseClass(), e_.getName());
        setResultClass(new ClassArgumentMatching(c_));
    }
    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf, String _op) {
        return calculateArgument(_nodes, _conf, _op);
    }

    Argument calculateArgument(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf, String _op) {
        Argument previous_;
        if (isIntermediateDottedOperation()) {
            previous_ = _nodes.getVal(this).getPreviousArgument();
        } else {
            previous_ = _conf.getLastPage().getGlobalArgument();
        }
        Argument current_ = _nodes.getVal(this).getArgument();
        ArgumentCall argres_ = getCommonArgument(current_, previous_, _conf, _op);
        Argument arg_ = argres_.getArgument();
        if (_conf.getException() != null) {
            return arg_;
        }
        if (argres_.isInitClass()) {
            _conf.setInitClass(new NotInitializedClass(argres_.getInitClass().getClassName()));
        } else {
            setSimpleArgument(arg_, _conf, _nodes);
        }
        return arg_;
    }
    @Override
    public Argument calculateSetting(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op) {
        Argument current_ = _nodes.getVal(this).getArgument();
        Argument arg_ = getCommonSetting(current_, _conf, _op);
        if (_conf.getException() == null) {
            setSimpleArgument(arg_, _conf, _nodes);
        }
        return arg_;
    }

    @Override
    public void calculate(CustList<OperationNode> _nodes, ContextEl _conf,
            String _op) {
        calculateArgument(_nodes, _conf, _op);
    }

    void calculateArgument(CustList<OperationNode> _nodes, ContextEl _conf,
            String _op) {
        Argument previous_;
        if (isIntermediateDottedOperation()) {
            previous_ = getPreviousArgument();
        } else {
            previous_ = _conf.getLastPage().getGlobalArgument();
        }
        ArgumentCall argres_ = getCommonArgument(getArgument(), previous_, _conf, _op);
        if (_conf.getException() != null) {
            return;
        }
        if (argres_.isInitClass()) {
            ProcessMethod.initializeClass(argres_.getInitClass().getClassName(), _conf);
            argres_ = getCommonArgument(getArgument(), previous_, _conf, _op);
        }
        Argument arg_ = argres_.getArgument();
        if (arg_ == null) {
            return;
        }
        setSimpleArgument(arg_, _conf);
    }

    @Override
    public void calculateSetting(CustList<OperationNode> _nodes,
            ContextEl _conf, String _op) {
        Argument current_ = getArgument();
        Argument arg_ = getCommonSetting(current_, _conf, _op);
        if (_conf.getException() != null) {
            return;
        }
        setSimpleArgument(arg_, _conf);
    }

    ArgumentCall getCommonArgument(Argument _argument, Argument _previous, ContextEl _conf,
            String _op) {
        LgNames stds_ = _conf.getStandards();
        String null_;
        String cast_;
        null_ = stds_.getAliasNullPe();
        cast_ = stds_.getAliasCast();
        Argument a_ = new Argument();
        int relativeOff_ = getOperations().getOffset();
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        PageEl ip_ = _conf.getLastPage();
        if (getOperations().getConstType() == ConstType.THIS_KEYWORD) {
            Struct struct_ = ip_.getGlobalArgument().getStruct();
            a_ = new Argument();
            a_.setStruct(struct_);
            return ArgumentCall.newArgument(a_);
        }
        if (fieldId != null) {
            Classes classes_ = _conf.getClasses();
            Argument arg_ = _previous;
            if (resultCanBeSet()) {
                return ArgumentCall.newArgument(arg_);
            }
            String className_ = fieldId.getClassName();
            if (fieldMetaInfo.isStaticField()) {
                if (classes_.isCustomType(className_)) {
                    InitClassState res_ = classes_.getLocks().getState(_conf, className_);
                    if (res_ == InitClassState.NOT_YET) {
                        InitializatingClass inv_ = new InitializatingClass(className_);
                        return ArgumentCall.newCall(inv_);
                    }
                    if (res_ == InitClassState.ERROR) {
                        CausingErrorStruct causing_ = new CausingErrorStruct(className_);
                        _conf.setException(causing_);
                        return ArgumentCall.newArgument(Argument.createVoid());
                    }
                    Struct struct_ = classes_.getStaticField(fieldId);
                    a_ = new Argument();
                    a_.setStruct(struct_);
                    return ArgumentCall.newArgument(a_);
                }
                ResultErrorStd res_ = LgNames.getField(_conf, fieldId, NullStruct.NULL_VALUE);
                a_ = new Argument();
                if (res_.getError() != null) {
                    _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),res_.getError()));
                } else {
                    a_.setStruct(res_.getResult());
                }
                return ArgumentCall.newArgument(a_);
            }
            if (arg_.isNull()) {
                _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),null_));
                return ArgumentCall.newArgument(arg_);
            }
            String argClassName_ = arg_.getObjectClassName(_conf);
            String classNameFound_ = fieldId.getClassName();
            String base_ = StringList.getAllTypes(argClassName_).first();
            if (!PrimitiveTypeUtil.canBeUseAsArgument(classNameFound_, base_, _conf)) {
                _conf.setException(new StdStruct(new CustomError(StringList.concat(base_,RETURN_LINE,classNameFound_,RETURN_LINE,_conf.joinPages())),cast_));
                return ArgumentCall.newArgument(arg_);
            }
            if (arg_.getStruct() instanceof FieldableStruct) {
                Struct struct_ = ((FieldableStruct) arg_.getStruct()).getStruct(fieldId);
                a_ = new Argument();
                a_.setStruct(struct_);
                return ArgumentCall.newArgument(a_);
            }
            Struct default_ = arg_.getStruct();
            ResultErrorStd res_ = LgNames.getField(_conf, fieldId, default_);
            a_ = new Argument();
            if (res_.getError() != null) {
                _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),res_.getError()));
            } else {
                a_.setStruct(res_.getResult());
            }
            return ArgumentCall.newArgument(a_);
        }
        OperationsSequence op_ = getOperations();
        if (op_.getConstType() == ConstType.PARAM) {
            LocalVariable locVar_ = ip_.getParameters().getVal(variableName);
            a_ = new Argument();
            a_.setStruct(locVar_.getStruct());
            return ArgumentCall.newArgument(a_);
        }
        if (op_.getConstType() == ConstType.CATCH_VAR) {
            LocalVariable locVar_ = ip_.getCatchVars().getVal(variableName);
            a_ = new Argument();
            a_.setStruct(locVar_.getStruct());
            return ArgumentCall.newArgument(a_);
        }
        if (op_.getConstType() == ConstType.LOC_VAR || excVar) {
            if (resultCanBeSet()) {
                return ArgumentCall.newArgument(Argument.createVoid());
            }
            LocalVariable locVar_ = ip_.getLocalVars().getVal(variableName);
            a_ = new Argument();
            a_.setStruct(locVar_.getStruct());
            return ArgumentCall.newArgument(a_);
        }
        if (op_.getConstType() == ConstType.LOOP_INDEX) {
            LoopVariable locVar_ = ip_.getVars().getVal(variableName);
            a_ = new Argument();
            ClassArgumentMatching clArg_ = new ClassArgumentMatching(locVar_.getIndexClassName());
            a_.setStruct(PrimitiveTypeUtil.convertObject(clArg_, new LongStruct(locVar_.getIndex()), _conf));
            return ArgumentCall.newArgument(a_);
        }
        if (op_.getConstType() == ConstType.LOOP_VAR) {
            LoopVariable locVar_ = ip_.getVars().getVal(variableName);
            a_ = new Argument();
            a_.setStruct(locVar_.getStruct());
            return ArgumentCall.newArgument(a_);
        }
        Argument arg_ = _previous;
        a_ = new Argument();
        if (arg_.isNull()) {
            _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),null_));
        } else {
            a_.setStruct(new IntStruct(LgNames.getLength(arg_.getObject())));
        }
        return ArgumentCall.newArgument(a_);
    }

    Argument getCommonSetting(Argument _argument, ContextEl _conf, String _op) {
        PageEl ip_ = _conf.getLastPage();
        LgNames stds_ = _conf.getStandards();
        String null_;
        String cast_;
        null_ = stds_.getAliasNullPe();
        cast_ = stds_.getAliasCast();
        int relativeOff_ = getOperations().getOffset();
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        OperationsSequence op_ = getOperations();
        if (op_.getConstType() == ConstType.LOC_VAR || excVar) {
            LocalVariable locVar_ = ip_.getLocalVars().getVal(variableName);
            Argument left_ = new Argument();
            left_.setStruct(locVar_.getStruct());
            Argument right_ = ip_.getRightArgument();
            Argument check_ = left_;
            if (getParent() instanceof AffectationOperation || _conf.isCheckAffectation()) {
                check_ = right_;
            }
            String formattedClassVar_ = locVar_.getClassName();
            formattedClassVar_ = _conf.getLastPage().formatVarType(formattedClassVar_, _conf);
            if (PrimitiveTypeUtil.primitiveTypeNullObject(formattedClassVar_, check_.getStruct(), _conf)) {
                _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),null_));
                return Argument.createVoid();
            }
            if (!check_.isNull() && !NumericOperation.convert(_op)) {
                Mapping mapping_ = new Mapping();
                String base_ = check_.getObjectClassName(_conf);
                mapping_.setArg(base_);
                mapping_.setParam(formattedClassVar_);
                if (!Templates.isCorrect(mapping_, _conf)) {
                    _conf.setException(new StdStruct(new CustomError(StringList.concat(base_,RETURN_LINE,formattedClassVar_,RETURN_LINE,_conf.joinPages())),cast_));
                    return Argument.createVoid();
                }
            }
            Argument res_;
            res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op, catString);
            if (_conf.getException() != null) {
                return res_;
            }
            if (res_.getStruct() instanceof NumberStruct || res_.getStruct() instanceof CharStruct) {
                ClassArgumentMatching cl_ = new ClassArgumentMatching(locVar_.getClassName());
                res_.setStruct(PrimitiveTypeUtil.convertObject(cl_, res_.getStruct(), _conf));
            }
            locVar_.setStruct(res_.getStruct());
            return res_;
        }
        Argument argument_ = _argument;
        Argument right_ = ip_.getRightArgument();
        Argument left_ = new Argument();
        Argument res_;

        String fieldType_;
        if (!fieldMetaInfo.isStaticField()) {
            String argClassName_ = argument_.getObjectClassName(_conf);
            String classNameFound_ = fieldId.getClassName();
            classNameFound_ = StringList.getAllTypes(classNameFound_).first();
            classNameFound_ = Templates.getFullTypeByBases(argClassName_, classNameFound_, _conf);
            fieldType_ = fieldMetaInfo.getRealType();
            fieldType_ = Templates.format(classNameFound_, fieldType_, _conf);
        } else {
            fieldType_ = fieldMetaInfo.getRealType();
        }
        Classes classes_ = _conf.getClasses();
        Struct structField_ = null;
        String className_ = fieldId.getClassName();
        if (fieldMetaInfo.isStaticField()) {
            structField_ = classes_.getStaticField(fieldId);
            Struct check_ = structField_;
            if (getParent() instanceof AffectationOperation || _conf.isCheckAffectation()) {
                check_ = right_.getStruct();
            }
            if (PrimitiveTypeUtil.primitiveTypeNullObject(fieldType_, check_, _conf)) {
                _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),null_));
                return Argument.createVoid();
            }
            if (!check_.isNull() && !NumericOperation.convert(_op)) {
                Mapping map_ = new Mapping();
                String rightClass_ = stds_.getStructClassName(check_, _conf);
                map_.setArg(rightClass_);
                map_.setParam(fieldType_);
                if (!Templates.isCorrect(map_, _conf)) {
                    _conf.setException(new StdStruct(new CustomError(StringList.concat(rightClass_,RETURN_LINE,fieldType_,RETURN_LINE,_conf.joinPages())),cast_));
                    return Argument.createVoid();
                }
            }
            left_.setStruct(structField_);
            res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op, catString);
            if (_conf.getException() != null) {
                return res_;
            }
            if (res_.getStruct() instanceof NumberStruct || res_.getStruct() instanceof CharStruct) {
                ClassArgumentMatching cl_ = new ClassArgumentMatching(fieldType_);
                res_.setStruct(PrimitiveTypeUtil.convertObject(cl_, res_.getStruct(), _conf));
            }
            if (classes_.isCustomType(className_)) {
                classes_.initializeStaticField(fieldId, res_.getStruct());
                Argument a_ = res_;
                return a_;
            }
            ResultErrorStd result_ = LgNames.getField(_conf, fieldId, NullStruct.NULL_VALUE);
            if (result_.getError() != null) {
                _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),result_.getError()));
                return Argument.createVoid();
            }
            structField_ = result_.getResult();
            left_.setStruct(structField_);
            res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op, catString);
            if (_conf.getException() != null) {
                return res_;
            }
            result_ = LgNames.setField(_conf, fieldId, NullStruct.NULL_VALUE, res_.getStruct());
            if (result_.getError() != null) {
                _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),result_.getError()));
                return res_;
            }
            Argument a_ = res_;
            return a_;
        }
        if (argument_.isNull()) {
            _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),null_));
            return Argument.createVoid();
        }
        String argClassName_ = argument_.getObjectClassName(_conf);
        String classNameFound_ = fieldId.getClassName();
        String base_ = StringList.getAllTypes(argClassName_).first();
        if (!PrimitiveTypeUtil.canBeUseAsArgument(classNameFound_, base_, _conf)) {
            _conf.setException(new StdStruct(new CustomError(StringList.concat(base_,RETURN_LINE,classNameFound_,RETURN_LINE,_conf.joinPages())),cast_));
            return Argument.createVoid();
        }
        if (argument_.getStruct() instanceof FieldableStruct) {
            structField_ = ((FieldableStruct) argument_.getStruct()).getStruct(fieldId);
            Struct check_ = structField_;
            if (getParent() instanceof AffectationOperation || _conf.isCheckAffectation()) {
                check_ = right_.getStruct();
            }
            if (PrimitiveTypeUtil.primitiveTypeNullObject(fieldType_, check_, _conf)) {
                _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),null_));
                return Argument.createVoid();
            }
            if (!check_.isNull() && !NumericOperation.convert(_op)) {
                Mapping map_ = new Mapping();
                String rightClass_ = stds_.getStructClassName(check_, _conf);
                map_.setArg(rightClass_);
                map_.setParam(fieldType_);
                if (!Templates.isCorrect(map_, _conf)) {
                    _conf.setException(new StdStruct(new CustomError(StringList.concat(rightClass_,RETURN_LINE,fieldType_,RETURN_LINE,_conf.joinPages())),cast_));
                    return Argument.createVoid();
                }
            }
            left_.setStruct(structField_);
            res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op, catString);
            if (_conf.getException() != null) {
                return res_;
            }
            if (res_.getStruct() instanceof NumberStruct || res_.getStruct() instanceof CharStruct) {
                ClassArgumentMatching cl_ = new ClassArgumentMatching(fieldType_);
                res_.setStruct(PrimitiveTypeUtil.convertObject(cl_, res_.getStruct(), _conf));
            }
            ((FieldableStruct) argument_.getStruct()).setStruct(fieldId, res_.getStruct());
            Argument a_ = res_;
            return a_;
        }
        ResultErrorStd result_ = LgNames.getField(_conf, fieldId, argument_.getStruct());
        if (result_.getError() != null) {
            _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),result_.getError()));
            return Argument.createVoid();
        }
        structField_ = result_.getResult();
        left_.setStruct(structField_);
        Argument check_ = left_;
        if (getParent() instanceof AffectationOperation || _conf.isCheckAffectation()) {
            check_ = right_;
        }
        if (PrimitiveTypeUtil.primitiveTypeNullObject(fieldType_, check_.getStruct(), _conf)) {
            _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),null_));
            return Argument.createVoid();
        }
        res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op, catString);
        if (_conf.getException() != null) {
            return res_;
        }
        result_ = LgNames.setField(_conf, fieldId, argument_.getStruct(), res_.getStruct());
        if (result_.getError() != null) {
            _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),result_.getError()));
            return res_;
        }
        Argument a_ = res_;
        return a_;
    }

    @Override
    public boolean isPossibleInitClass() {
        return possibleInitClass;
    }

    @Override
    public boolean resultCanBeSet() {
        return variable;
    }

    @Override
    public boolean isSuperConstructorCall() {
        return false;
    }

    @Override
    public boolean isOtherConstructorClass() {
        return false;
    }

    @Override
    public ConstructorId getConstId() {
        return null;
    }

    @Override
    public void setVariable(boolean _variable) {
        variable = _variable;
    }

    public boolean isImmutablePart() {
        return immutablePart;
    }

    public boolean isFinalField() {
        return finalField;
    }
    @Override
    public void setIntermediateDotted() {
        intermediate = true;
    }
    @Override
    public boolean isIntermediateDottedOperation() {
        return intermediate;
    }

    @Override
    public final ClassArgumentMatching getPreviousResultClass() {
        return previousResultClass;
    }

    @Override
    public final void setPreviousResultClass(ClassArgumentMatching _previousResultClass) {
        setPreviousResultClass(_previousResultClass, false);
    }

    @Override
    public final void setPreviousResultClass(ClassArgumentMatching _previousResultClass, boolean _staticAccess) {
        previousResultClass = _previousResultClass;
        staticAccess = _staticAccess;
    }

    @Override
    public final Argument getPreviousArgument() {
        return previousArgument;
    }

    @Override
    public final void setPreviousArgument(Argument _previousArgument) {
        previousArgument = _previousArgument;
    }

    public final boolean isStaticAccess() {
        return staticAccess;
    }

    @Override
    public void setCatenizeStrings() {
        catString = true;
    }
}
