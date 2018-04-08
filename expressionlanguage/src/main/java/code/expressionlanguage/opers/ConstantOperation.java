package code.expressionlanguage.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ArgumentCall;
import code.expressionlanguage.ConstType;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.ElResolver;
import code.expressionlanguage.InitializatingClass;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.ParsedArgument;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.common.GeneClass;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.exceptions.BadFormatPathException;
import code.expressionlanguage.exceptions.DynamicNumberFormatException;
import code.expressionlanguage.exceptions.EmptyPartException;
import code.expressionlanguage.exceptions.ErrorCausingException;
import code.expressionlanguage.exceptions.InvokeException;
import code.expressionlanguage.exceptions.NoSuchDeclaredFieldException;
import code.expressionlanguage.exceptions.NotInitializedClassException;
import code.expressionlanguage.exceptions.NullGlobalObjectException;
import code.expressionlanguage.exceptions.SettingMemberException;
import code.expressionlanguage.exceptions.StaticAccessException;
import code.expressionlanguage.exceptions.UndefinedVariableException;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.BadAccessField;
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
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;
import code.util.exceptions.NullObjectException;

public final class ConstantOperation extends LeafOperation implements SettableElResult, PossibleIntermediateDotted {
    private static final String ATTRIBUTE = "attribute";
    private static final String INDEX = "index";
    private static final String CATCH_VARIABLE = "catch variable";
    private static final String LOCAL_VARIABLE = "local variable";
    private static final String PARAMETER = "parameter";
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
    public void analyze(CustList<OperationNode> _nodes, ContextEl _conf,
            String _fieldName, String _op) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_) + relativeOff_;
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String argClName_;
        if (str_.isEmpty()) {
            throw new EmptyPartException(_conf.joinPages());
        }
        if (!isIntermediateDottedOperation()) {
            staticAccess = _conf.getLastPage().isStaticContext();
        }
        if (isVararg()) {
            str_ = str_.substring(CustList.SECOND_INDEX);
            str_ = StringList.removeAllSpaces(str_);
            Argument a_ = new Argument();
            checkCorrect(_conf, str_, false, 0);
            argClName_ = str_;
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
                throw new DynamicNumberFormatException(StringList.concat(str_,RETURN_LINE,_conf.joinPages()));
            }
            Argument arg_ = Argument.createVoid();
            arg_.setStruct(parsed_.getStruct());
            setSimpleArgument(arg_);
            setResultClass(new ClassArgumentMatching(argClassName_));
            return;
        }
        if (op_.getConstType() == ConstType.THIS_KEYWORD) {
            if (isStaticAccess()) {
                throw new StaticAccessException(_conf.joinPages());
            }
            if (getParent() == null) {
                immutablePart = true;
            }
            String arg_ = _conf.getLastPage().getGlobalClass();
            setResultClass(new ClassArgumentMatching(arg_));
            return;
        }
        PageEl ip_ = _conf.getLastPage();
        if (op_.getConstType() == ConstType.CUST_FIELD || op_.getConstType() == ConstType.CLASSCHOICE_KEYWORD || op_.getConstType() == ConstType.SUPER_KEYWORD) {
            ClassArgumentMatching cl_;
            if (isIntermediateDottedOperation()) {
                cl_ = getPreviousResultClass();
            } else {
                cl_ = new ClassArgumentMatching(_conf.getLastPage().getGlobalClass());
            }
            String clCurName_;
            if (op_.getConstType() == ConstType.CLASSCHOICE_KEYWORD) {
                StringList classMethod_ = StringList.splitStrings(str_, STATIC_CALL);
                if (classMethod_.size() != 2) {
                    throw new BadFormatPathException(StringList.concat(str_,RETURN_LINE,_conf.joinPages()));
                }
                String className_ = classMethod_.first();
                if (!className_.startsWith(CLASS_CHOICE_PREF)) {
                    throw new BadFormatPathException(StringList.concat(str_,RETURN_LINE,_conf.joinPages()));
                }
                int lenPref_ = CLASS_CHOICE_PREF.length();
                className_ = className_.substring(lenPref_);
                className_ = StringList.removeAllSpaces(className_);
                className_ = className_.replace(EXTERN_CLASS, DOT_VAR);
                if (className_.contains(Templates.TEMPLATE_BEGIN)) {
                    checkCorrect(_conf, className_, true, lenPref_);
                } else {
                    checkExistBase(_conf, false, className_, true, lenPref_);
                }
                clCurName_ = className_;
            } else {
                if (cl_ == null) {
                    throw new NullGlobalObjectException(_conf.joinPages());
                }
                clCurName_ = cl_.getName();
            }

            String base_ = StringList.getAllTypes(clCurName_).first();
            GeneType root_ = _conf.getClassBody(base_);
            String key_;
            boolean superClassAccess_ = true;
            FieldResult r_;
            FieldInfo e_;
            if (op_.getConstType() == ConstType.CLASSCHOICE_KEYWORD) {
                StringList classMethod_ = StringList.splitStrings(str_, STATIC_CALL);
                key_ = classMethod_.last();
                superClassAccess_ = false;
                r_ = getDeclaredCustField(_conf, isStaticAccess(), new ClassArgumentMatching(clCurName_), superClassAccess_, key_);
            } else if (op_.getConstType() == ConstType.SUPER_KEYWORD) {
                key_ = str_;
                if (!(root_ instanceof GeneClass)) {
                    throw new NoSuchDeclaredFieldException(StringList.concat(key_,RETURN_LINE,_conf.joinPages()));
                }
                String superClass_ = ((GeneClass)root_).getSuperClass(_conf);
                superClass_ = StringList.getAllTypes(superClass_).first();
                superClass_ = Templates.getFullTypeByBases(clCurName_, superClass_, _conf);
                if (StringList.quickEq(superClass_, stds_.getAliasObject())) {
                    throw new NoSuchDeclaredFieldException(StringList.concat(key_,RETURN_LINE,_conf.joinPages()));
                }
                cl_ = new ClassArgumentMatching(superClass_);
                r_ = getDeclaredCustField(_conf, isStaticAccess(), cl_, superClassAccess_, key_);
            } else {
                key_ = str_;
                superClassAccess_ = root_ instanceof GeneClass;
                r_ = getDeclaredCustField(_conf, isStaticAccess(), cl_, superClassAccess_, key_);
            }
            if (r_.getStatus() == SearchingMemberStatus.ZERO) {
                throw new NoSuchDeclaredFieldException(StringList.concat(key_,RETURN_LINE,_conf.joinPages()));
            }
            e_ = r_.getId();
            String glClass_ = _conf.getLastPage().getGlobalClass();
            String curClassBase_ = null;
            if (glClass_ != null) {
                curClassBase_ = StringList.getAllTypes(glClass_).first();
            }
            if (!Classes.canAccessField(curClassBase_, e_.getDeclaringBaseClass(), key_, _conf)) {
                BadAccessField access_ = new BadAccessField();
                access_.setFileName(_conf.getCurrentFileName());
                access_.setId(new ClassField(e_.getDeclaringBaseClass(), _fieldName));
                access_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().getErrorsDet().add(access_);
            }
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
        if (op_.getConstType().isVariable()) {
            if (isIntermediateDottedOperation()) {
                setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
                throw new SettingMemberException(_conf.joinPages());
            }
            if (op_.getConstType() == ConstType.PARAM) {
                if (getParent() == null) {
                    immutablePart = true;
                }
                variableName = str_;
                LocalVariable locVar_ = ip_.getParameters().getVal(variableName);
                if (locVar_ != null) {
                    String paramType_ = locVar_.getClassName();
                    if (paramType_.endsWith(VARARG_SUFFIX)) {
                        paramType_ = StringList.replace(paramType_, VARARG_SUFFIX, EMPTY_STRING);
                        paramType_ = PrimitiveTypeUtil.getPrettyArrayType(paramType_);
                    }
                    setResultClass(new ClassArgumentMatching(paramType_));
                    return;
                }
                throw new UndefinedVariableException(_conf.joinPages(), variableName, PARAMETER);
            }
            if (op_.getConstType() == ConstType.CATCH_VAR) {
                if (getParent() == null) {
                    immutablePart = true;
                }
                variableName = str_;
                LocalVariable locVar_ = ip_.getCatchVars().getVal(variableName);
                if (locVar_ != null) {
                    setResultClass(new ClassArgumentMatching(locVar_.getClassName()));
                    return;
                }
                throw new UndefinedVariableException(_conf.joinPages(), variableName, CATCH_VARIABLE);
            }
            if (op_.getConstType() == ConstType.LOC_VAR) {
                variableName = str_;
                LocalVariable locVar_ = ip_.getLocalVars().getVal(variableName);
                if (locVar_ != null) {
                    String c_ = locVar_.getClassName();
                    if (StringList.quickEq(c_, stringType_)) {
                        catString = true;
                    }
                    setResultClass(new ClassArgumentMatching(c_));
                    return;
                }
                throw new UndefinedVariableException(_conf.joinPages(), variableName, LOCAL_VARIABLE);
            }
            if (op_.getConstType() == ConstType.LOOP_INDEX) {
                if (getParent() == null) {
                    immutablePart = true;
                }
                variableName = str_;
                LoopVariable locVar_ = ip_.getVars().getVal(variableName);
                if (locVar_ != null) {
                    setResultClass(new ClassArgumentMatching(locVar_.getIndexClassName()));
                    return;
                }
                throw new UndefinedVariableException(_conf.joinPages(), variableName, INDEX);
            }
            if (op_.getConstType() == ConstType.LOOP_VAR) {
                if (getParent() == null) {
                    immutablePart = true;
                }
                variableName = str_;
                LoopVariable locVar_ = ip_.getVars().getVal(variableName);
                if (locVar_ != null) {
                    setResultClass(new ClassArgumentMatching(locVar_.getClassName()));
                    return;
                }
                throw new UndefinedVariableException(_conf.joinPages(), variableName, ATTRIBUTE);
            }
        }
        analyzeNativeField(_conf, str_);
    }

    private void analyzeNativeField(ContextEl _conf, String _key) {
        ClassArgumentMatching cl_;
        if (isIntermediateDottedOperation()) {
            cl_ = getPreviousResultClass();
        } else {
            cl_ = new ClassArgumentMatching(_conf.getLastPage().getGlobalClass());
        }
        if (cl_ == null || cl_.getName() == null) {
            throw new NullGlobalObjectException(_conf.joinPages());
        }
        LgNames stds_ = _conf.getStandards();
        String stringType_;
        stringType_ = stds_.getAliasString();
        if (cl_.isArray()) {
            if (StringList.quickEq(_key, LENGTH)) {
                setResultClass(new ClassArgumentMatching(stds_.getAliasPrimInteger()));
                return;
            }
            throw new NoSuchDeclaredFieldException(StringList.concat(cl_.getName(),RETURN_LINE,_key,RETURN_LINE,_conf.joinPages()));
        }
        String str_ = _key;
        String clCurName_ = cl_.getName();
        String base_ = StringList.getAllTypes(clCurName_).first();
        StandardType root_ = stds_.getStandards().getVal(base_);
        String key_;
        boolean superClassAccess_ = true;
        FieldResult r_;
        FieldInfo e_;
        OperationsSequence op_ = getOperations();
        if (op_.getConstType() == ConstType.CLASSCHOICE_KEYWORD) {
            StringList classMethod_ = StringList.splitStrings(str_, STATIC_CALL);
            key_ = classMethod_.last();
            superClassAccess_ = false;
            r_ = getDeclaredCustField(_conf, isStaticAccess(), new ClassArgumentMatching(clCurName_), superClassAccess_, key_);
        } else if (op_.getConstType() == ConstType.SUPER_KEYWORD) {
            key_ = str_;
            if (!(root_ instanceof StandardClass)) {
                throw new NoSuchDeclaredFieldException(StringList.concat(key_,RETURN_LINE,_conf.joinPages()));
            }
            String superClass_ = ((StandardClass)root_).getSuperClass();
            superClass_ = StringList.getAllTypes(superClass_).first();
            superClass_ = Templates.getFullTypeByBases(clCurName_, superClass_, _conf);
            if (StringList.quickEq(superClass_, stds_.getAliasObject())) {
                throw new NoSuchDeclaredFieldException(StringList.concat(key_,RETURN_LINE,_conf.joinPages()));
            }
            cl_ = new ClassArgumentMatching(superClass_);
            r_ = getDeclaredCustField(_conf, isStaticAccess(), cl_, superClassAccess_, key_);
        } else {
            key_ = str_;
            superClassAccess_ = root_ instanceof StandardClass;
            r_ = getDeclaredCustField(_conf, isStaticAccess(), cl_, superClassAccess_, key_);
        }
        if (r_.getStatus() == SearchingMemberStatus.ZERO) {
            throw new NoSuchDeclaredFieldException(StringList.concat(key_,RETURN_LINE,_conf.joinPages()));
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
        ArgumentCall argres_ = getCommonArgument(_nodes.getVal(this).getArgument(), previous_, _conf, _op);
        if (argres_.isInitClass()) {
            throw new NotInitializedClassException(argres_.getInitClass().getClassName());
        }
        Argument arg_ = argres_.getArgument();
        setSimpleArgument(arg_, _conf, _nodes);
        return arg_;
    }
    @Override
    public Argument calculateSetting(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op) {
        Argument previous_ = _nodes.getVal(this).getPreviousArgument();
        Argument arg_ = getCommonSetting(_nodes.getVal(this).getArgument(), previous_, _conf, _op);
        setSimpleArgument(arg_, _conf, _nodes);
        return arg_;
    }

    /**@throws ErrorCausingException
    @throws NullObjectException */

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
        Argument arg_ = getCommonSetting(getArgument(), getPreviousArgument(), _conf, _op);
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
                    if (!classes_.isInitialized(className_)) {
                        classes_.initialize(className_);
                        InitializatingClass inv_ = new InitializatingClass(className_);
                        return ArgumentCall.newCall(inv_);
                    }
                    Struct struct_ = classes_.getStaticField(fieldId);
                    a_ = new Argument();
                    a_.setStruct(struct_);
                    return ArgumentCall.newArgument(a_);
                }
                ResultErrorStd res_ = LgNames.getField(_conf, fieldId, NullStruct.NULL_VALUE);
                if (res_.getError() != null) {
                    throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),res_.getError()));
                }
                a_ = new Argument();
                a_.setStruct(res_.getResult());
                return ArgumentCall.newArgument(a_);
            }
            if (arg_.isNull()) {
                throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
            }
            String argClassName_ = arg_.getObjectClassName(_conf);
            String classNameFound_ = fieldId.getClassName();
            String base_ = StringList.getAllTypes(argClassName_).first();
            if (!PrimitiveTypeUtil.canBeUseAsArgument(classNameFound_, base_, _conf)) {
                throw new InvokeException(new StdStruct(new CustomError(StringList.concat(base_,RETURN_LINE,classNameFound_,RETURN_LINE,_conf.joinPages())),cast_));
            }
            if (arg_.getStruct() instanceof FieldableStruct) {
                Struct struct_ = ((FieldableStruct) arg_.getStruct()).getStruct(fieldId);
                a_ = new Argument();
                a_.setStruct(struct_);
                return ArgumentCall.newArgument(a_);
            }
            Struct default_ = arg_.getStruct();
            ResultErrorStd res_ = LgNames.getField(_conf, fieldId, default_);
            if (res_.getError() != null) {
                throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),res_.getError()));
            }
            a_ = new Argument();
            a_.setStruct(res_.getResult());
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
        if (op_.getConstType() == ConstType.LOC_VAR) {
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
        if (arg_.isNull()) {
            throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
        }
        a_ = new Argument();
        a_.setStruct(new IntStruct(LgNames.getLength(arg_.getObject())));
        return ArgumentCall.newArgument(a_);
    }

    Argument getCommonSetting(Argument _argument, Argument _previous, ContextEl _conf, String _op) {
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
        if (op_.getConstType() == ConstType.LOC_VAR) {
            LocalVariable locVar_ = ip_.getLocalVars().getVal(variableName);
            Argument left_ = new Argument();
            left_.setStruct(locVar_.getStruct());
            Argument right_ = ip_.getRightArgument();
            String formattedClassVar_ = locVar_.getClassName();
            formattedClassVar_ = _conf.getLastPage().formatVarType(formattedClassVar_, _conf);
            if (PrimitiveTypeUtil.primitiveTypeNullObject(formattedClassVar_, right_.getStruct(), _conf)) {
                throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
            }
            if (!right_.isNull() && !NumericOperation.convert(_op)) {
                Mapping mapping_ = new Mapping();
                String base_ = right_.getObjectClassName(_conf);
                mapping_.setArg(base_);
                mapping_.setParam(formattedClassVar_);
                if (!Templates.isCorrect(mapping_, _conf)) {
                    throw new InvokeException(new StdStruct(new CustomError(StringList.concat(base_,RETURN_LINE,formattedClassVar_,RETURN_LINE,_conf.joinPages())),cast_));
                }
            }
            Argument res_;
            res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op, catString);
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
        if (PrimitiveTypeUtil.primitiveTypeNullObject(fieldType_, right_.getStruct(), _conf)) {
            throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
        }
        Struct structField_ = null;
        String className_ = fieldId.getClassName();
        if (fieldMetaInfo.isStaticField()) {
            structField_ = classes_.getStaticField(fieldId);
            if (!right_.isNull() && !NumericOperation.convert(_op)) {
                Mapping map_ = new Mapping();
                String rightClass_ = right_.getObjectClassName(_conf);
                map_.setArg(rightClass_);
                map_.setParam(fieldType_);
                if (!Templates.isCorrect(map_, _conf)) {
                    throw new InvokeException(new StdStruct(new CustomError(StringList.concat(rightClass_,RETURN_LINE,fieldType_,RETURN_LINE,_conf.joinPages())),cast_));
                }
            }
            left_.setStruct(structField_);
            res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op, catString);
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
                throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),result_.getError()));
            }
            structField_ = result_.getResult();
            left_.setStruct(structField_);
            res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op, catString);
            result_ = LgNames.setField(_conf, fieldId, NullStruct.NULL_VALUE, res_.getStruct());
            if (result_.getError() != null) {
                throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),result_.getError()));
            }
            Argument a_ = res_;
            return a_;
        }
        if (argument_.isNull()) {
            throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
        }
        String argClassName_ = argument_.getObjectClassName(_conf);
        String classNameFound_ = fieldId.getClassName();
        String base_ = StringList.getAllTypes(argClassName_).first();
        if (!PrimitiveTypeUtil.canBeUseAsArgument(classNameFound_, base_, _conf)) {
            throw new InvokeException(new StdStruct(new CustomError(StringList.concat(base_,RETURN_LINE,classNameFound_,RETURN_LINE,_conf.joinPages())),cast_));
        }
        if (argument_.getStruct() instanceof FieldableStruct) {
            structField_ = ((FieldableStruct) argument_.getStruct()).getStruct(fieldId);
            if (!right_.isNull() && !NumericOperation.convert(_op)) {
                Mapping map_ = new Mapping();
                String rightClass_ = right_.getObjectClassName(_conf);
                map_.setArg(rightClass_);
                map_.setParam(fieldType_);
                if (!Templates.isCorrect(map_, _conf)) {
                    throw new InvokeException(new StdStruct(new CustomError(StringList.concat(rightClass_,RETURN_LINE,fieldType_,RETURN_LINE,_conf.joinPages())),cast_));
                }
            }
            left_.setStruct(structField_);
            res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op, catString);
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
            throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),result_.getError()));
        }
        structField_ = result_.getResult();
        left_.setStruct(structField_);
        res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op, catString);
        result_ = LgNames.setField(_conf, fieldId, argument_.getStruct(), res_.getStruct());
        if (result_.getError() != null) {
            throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),result_.getError()));
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
    public void setVariable() {
        variable = true;
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
}
