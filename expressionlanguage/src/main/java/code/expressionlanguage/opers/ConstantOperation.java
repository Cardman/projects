package code.expressionlanguage.opers;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

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
import code.expressionlanguage.exceptions.BadFormatPathException;
import code.expressionlanguage.exceptions.DynamicNumberFormatException;
import code.expressionlanguage.exceptions.EmptyPartException;
import code.expressionlanguage.exceptions.ErrorCausingException;
import code.expressionlanguage.exceptions.InvokeException;
import code.expressionlanguage.exceptions.NotInitializedClassException;
import code.expressionlanguage.exceptions.NullGlobalObjectException;
import code.expressionlanguage.exceptions.SettingMemberException;
import code.expressionlanguage.exceptions.StaticAccessException;
import code.expressionlanguage.exceptions.UndefinedVariableException;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ProcessXmlMethod;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.UniqueRootedBlock;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.FieldInfo;
import code.expressionlanguage.opers.util.FieldResult;
import code.expressionlanguage.opers.util.FieldableStruct;
import code.expressionlanguage.opers.util.IntStruct;
import code.expressionlanguage.opers.util.LongStruct;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.SearchingMemberStatus;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardInterface;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.types.NativeTypeUtil;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.serialize.ConverterMethod;
import code.serialize.exceptions.BadAccessException;
import code.serialize.exceptions.NoSuchDeclaredFieldException;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;
import code.util.exceptions.NullObjectException;
import code.util.exceptions.RuntimeClassNotFoundException;

public final class ConstantOperation extends OperationNode implements SettableElResult {
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
    private static final byte HEX_BASE = 16;

    private boolean variable;
    
    private boolean finalField;

    private boolean immutablePart;

    private boolean staticAccess;

    private boolean possibleInitClass;

    private ClassField fieldId;
    private FieldInfo fieldMetaInfo;

    private Field field;

    private boolean staticChoiceFieldTemplate;
    private boolean staticChoiceField;

    private boolean catString;

    private String variableName = EMPTY_STRING;

    public ConstantOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
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
        if (isVararg()) {
            str_ = str_.substring(CustList.SECOND_INDEX);
            str_ = StringList.removeAllSpaces(str_);
            Argument a_ = new Argument();
            checkCorrect(_conf, str_, false, 0);
            argClName_ = str_;
            setSimpleArgument(a_);
            setResultClass(new ClassArgumentMatching(argClName_),staticAccess);
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
            setResultClass(new ClassArgumentMatching(argClName_),staticAccess);
            return;
        }
        if (op_.getConstType() == ConstType.FALSE_CST) {
            argClName_ = stds_.getAliasPrimBoolean();
            a_.setObject(false);
            setSimpleArgument(a_);
            setResultClass(new ClassArgumentMatching(argClName_),staticAccess);
            return;
        }
        if (op_.getConstType() == ConstType.NULL_CST) {
            argClName_ = EMPTY_STRING;
            setSimpleArgument(a_);
            setResultClass(new ClassArgumentMatching(argClName_),staticAccess);
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
                            int val_ = LgNames.parseLong(unicodeString_.toString(), HEX_BASE).intValue();
                            char i_ = (char)val_;
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
            setResultClass(new ClassArgumentMatching(stringType_),staticAccess);
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
                            int val_ = LgNames.parseLong(unicodeString_.toString(), HEX_BASE).intValue();
                            char i_ = (char)val_;
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
            setResultClass(new ClassArgumentMatching(argClName_),staticAccess);
            return;
        }
        if (op_.getConstType() == ConstType.STATIC_ACCESS) {
            String type_ = str_.substring(STATIC_ACCESS.length() + 2);
            StringBuilder class_ = new StringBuilder();
            if (type_.startsWith(String.valueOf(EXTERN_CLASS))) {
                class_.append(type_);
            } else {
                for (char p: type_.toCharArray()) {
                    if (Character.isWhitespace(p)) {
                        continue;
                    }
                    if (StringList.isWordChar(p)) {
                        class_.append(p);
                    } else if (p == EXTERN_CLASS){
                        class_.append(DOT_VAR);
                    } else {
                        class_.append(INTERN_CLASS);
                    }
                }
            }
            String classStr_ = StringList.removeAllSpaces(class_.toString());
            String base_ = StringList.getAllTypes(classStr_).first();
            String glClass_ = _conf.getLastPage().getGlobalClass();
            Classes classes_ = _conf.getClasses();
            checkExistBase(_conf, false, base_, false, 0);
            if (classes_ != null && classes_.isCustomType(classStr_)) {
                String curClassBase_ = null;
                if (glClass_ != null) {
                    curClassBase_ = StringList.getAllTypes(glClass_).first();
                }
                if (!classes_.canAccessClass(curClassBase_, classStr_, _conf)) {
                    throw new BadAccessException(StringList.concat(classStr_,RETURN_LINE,_conf.joinPages()));
                }
                possibleInitClass = true;
            }
            staticAccess = true;
            a_ = new Argument();
            argClName_ = classStr_;
            setArguments(a_);
            setResultClass(new ClassArgumentMatching(argClName_),staticAccess);
            return;
        }
        str_ = StringList.removeAllSpaces(str_);
        if (op_.getConstType() == ConstType.NUMBER) {
            ParsedArgument parsed_ = ParsedArgument.parse(str_, _conf);
            String argClassName_ = parsed_.getType();
            if (argClassName_.isEmpty()) {
                throw new DynamicNumberFormatException(StringList.concat(str_,RETURN_LINE,_conf.joinPages()));
            }
            Argument arg_ = Argument.createVoid();
            arg_.setStruct(parsed_.getStruct());
            setSimpleArgument(arg_);
            setResultClass(new ClassArgumentMatching(argClassName_),staticAccess);
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
            Classes classes_ = _conf.getClasses();
            needGlobalArgument();
            ClassArgumentMatching cl_ = getPreviousResultClass();
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
                staticChoiceField = true;
                if (className_.contains(Templates.TEMPLATE_BEGIN)) {
                    staticChoiceFieldTemplate = true;
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
            if (classes_ != null) {
                if (classes_.isCustomType(clCurName_)) {
                    String base_ = StringList.getAllTypes(clCurName_).first();
                    RootBlock root_ = classes_.getClassBody(base_);
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
                        if (!(root_ instanceof UniqueRootedBlock)) {
                            throw new NoSuchDeclaredFieldException(StringList.concat(key_,RETURN_LINE,_conf.joinPages()));
                        }
                        String superClass_ = ((UniqueRootedBlock)root_).getSuperClass(_conf);
                        superClass_ = StringList.getAllTypes(superClass_).first();
                        superClass_ = Templates.getFullTypeByBases(clCurName_, superClass_, _conf);
                        if (StringList.quickEq(superClass_, stds_.getAliasObject())) {
                            throw new NoSuchDeclaredFieldException(StringList.concat(key_,RETURN_LINE,_conf.joinPages()));
                        }
                        cl_ = new ClassArgumentMatching(superClass_);
                        r_ = getDeclaredCustField(_conf, isStaticAccess(), cl_, superClassAccess_, key_);
                    } else {
                        key_ = str_;
                        superClassAccess_ = root_ instanceof UniqueRootedBlock;
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
                    if (!_conf.getClasses().canAccessField(curClassBase_, e_.getDeclaringBaseClass(), key_, _conf)) {
                        throw new BadAccessException(StringList.concat(clCurName_,DOT,key_,RETURN_LINE,_conf.joinPages()));
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
            }
            if (cl_ == null || cl_.getName() == null) {
                throw new NullGlobalObjectException(_conf.joinPages());
            }
            String key_ = str_;
            analyzeNativeField(_conf, key_);
            return;
        }
        if (op_.getConstType().isVariable()) {
            if (isIntermediateDotted()) {
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
        needGlobalArgument();
        ClassArgumentMatching cl_ = getPreviousResultClass();
        if (cl_ == null || cl_.getName() == null) {
            throw new NullGlobalObjectException(_conf.joinPages());
        }
        analyzeNativeField(_conf, str_);
    }

    private void analyzeNativeField(ContextEl _conf, String _key) {
        ClassArgumentMatching cl_ = getPreviousResultClass();
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
        if (_conf.getClasses() != null) {
            String str_ = _key;
            String clCurName_ = getPreviousResultClass().getName();
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
                r_ = LgNames.getDeclaredCustField(_conf, isStaticAccess(), new ClassArgumentMatching(clCurName_), superClassAccess_, key_);
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
                r_ = LgNames.getDeclaredCustField(_conf, isStaticAccess(), cl_, superClassAccess_, key_);
            } else {
                key_ = str_;
                superClassAccess_ = root_ instanceof StandardInterface;
                r_ = LgNames.getDeclaredCustField(_conf, isStaticAccess(), cl_, superClassAccess_, key_);
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
            return;
        }
        if (cl_.getClassOrNull() == null) {
            throw new RuntimeClassNotFoundException(StringList.concat(cl_.getName(),RETURN_LINE,_conf.joinPages()));
        }
        Field f_ = getDeclaredField(_conf, cl_, _key);
        if (!canBeUsed(f_, _conf)) {
            throw new BadAccessException(StringList.concat(f_.getDeclaringClass().getName(),DOT,_key,RETURN_LINE,_conf.joinPages()));
        }
        if (Modifier.isFinal(f_.getModifiers())) {
            if (resultCanBeSet()) {
                finalField = true;
            }
        }
        if (isStaticAccess() && !Modifier.isStatic(f_.getModifiers())) {
            throw new StaticAccessException(_conf.joinPages());
        }
        field = f_;
        setAccess(field, _conf);
        Type type_ = f_.getGenericType();
        String pre_ = NativeTypeUtil.getFormattedType(f_.getType().getName(), type_.toString(), 0, type_);
        setResultClass(new ClassArgumentMatching(pre_));
    }
    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf, String _op) {
        return calculateArgument(_nodes, _conf, _op);
    }

    Argument calculateArgument(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf, String _op) {
        Argument previous_ = _nodes.getVal(this).getPreviousArgument();
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
        Argument previous_ = getPreviousArgument();
        ArgumentCall argres_ = getCommonArgument(getArgument(), previous_, _conf, _op);
        if (argres_.isInitClass()) {
            ProcessXmlMethod.initializeClass(argres_.getInitClass().getClassName(), _conf);
            if (isPossibleInitClass()) {
                return;
            }
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
        if (isPossibleInitClass()) {
            String className_ = getResultClass().getName();
            if (!_conf.getClasses().isInitialized(className_)) {
                _conf.getClasses().initialize(className_);
                InitializatingClass inv_ = new InitializatingClass(className_);
                return ArgumentCall.newCall(inv_);
            }
        }
        Argument cur_ = _argument;
        if (cur_ != null) {
            return ArgumentCall.newArgument(cur_);
        }
        Argument a_ = new Argument();
        int relativeOff_ = getOperations().getOffset();
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        PageEl ip_ = _conf.getLastPage();
        if (StringList.quickEq(str_, CURRENT_INTANCE)) {
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
            if (classes_.isCustomType(className_)) {
                if (fieldMetaInfo.isStaticField()) {
                    if (!_conf.getClasses().isInitialized(className_)) {
                        _conf.getClasses().initialize(className_);
                        InitializatingClass inv_ = new InitializatingClass(className_);
                        return ArgumentCall.newCall(inv_);
                    }
                    Struct struct_ = classes_.getStaticField(fieldId);
                    a_ = new Argument();
                    a_.setStruct(struct_);
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
                Struct struct_ = ((FieldableStruct) arg_.getStruct()).getStruct(fieldId);
                a_ = new Argument();
                a_.setStruct(struct_);
                return ArgumentCall.newArgument(a_);
            } else {
                if (!fieldMetaInfo.isStaticField() && arg_.isNull()) {
                    throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
                }
                Struct default_ = NullStruct.NULL_VALUE;
                if (!fieldMetaInfo.isStaticField()) {
                    String argClassName_ = arg_.getObjectClassName(_conf);
                    String classNameFound_ = fieldId.getClassName();
                    String base_ = argClassName_;
                    if (!PrimitiveTypeUtil.canBeUseAsArgument(classNameFound_, base_, _conf)) {
                        throw new InvokeException(new StdStruct(new CustomError(StringList.concat(base_,RETURN_LINE,classNameFound_,RETURN_LINE,_conf.joinPages())),cast_));
                    }
                    default_ = arg_.getStruct();
                }
                ResultErrorStd res_ = LgNames.getField(_conf, fieldId, default_);
                if (res_.getError() != null) {
                    throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),res_.getError()));
                }
                a_ = new Argument();
                a_.setStruct(res_.getResult());
                return ArgumentCall.newArgument(a_);
            }
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
            a_.setStruct(new LongStruct(locVar_.getIndex()));
            return ArgumentCall.newArgument(a_);
        }
        if (op_.getConstType() == ConstType.LOOP_VAR) {
            LoopVariable locVar_ = ip_.getVars().getVal(variableName);
            a_ = new Argument();
            a_.setStruct(locVar_.getStruct());
            return ArgumentCall.newArgument(a_);
        }
        ClassArgumentMatching cl_ = getPreviousResultClass();
        Argument arg_ = _previous;
        if (cl_.isArray()) {
            if (arg_.isNull()) {
                throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
            }
            a_ = new Argument();
            a_.setStruct(new IntStruct(LgNames.getLength(arg_.getObject())));
            return ArgumentCall.newArgument(a_);
        }
        if (resultCanBeSet()) {
            return ArgumentCall.newArgument(arg_);
        }
        Object obj_ = arg_.getObject();
        if (!Modifier.isStatic(field.getModifiers()) && obj_ == null) {
            throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
        }
        Object res_;
        try {
            res_ = ConverterMethod.getField(field, obj_);
        } catch (Throwable _0) {
            String err_ = _conf.getStandards().getAliasError();
            throw new ErrorCausingException(_conf.joinPages(), new StdStruct(new CustomError(_conf.joinPages()),err_));
        }
        a_ = new Argument();
        Type type_ = field.getGenericType();
        String pre_ = NativeTypeUtil.getFormattedType(field.getType().getName(), type_.toString(), 0, type_);
        a_.setStruct(StdStruct.wrapStd(res_, pre_));
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
            if (PrimitiveTypeUtil.primitiveTypeNullObject(locVar_.getClassName(), right_.getStruct(), _conf)) {
                throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
            }
            Argument res_;
            res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op, catString);
            locVar_.setStruct(res_.getStruct());
            return res_;
        }
        Argument argument_ = _argument;
        Argument right_ = ip_.getRightArgument();
        Argument left_ = new Argument();
        Argument res_;
        if (fieldId != null) {
            Classes classes_ = _conf.getClasses();
            if (PrimitiveTypeUtil.primitiveTypeNullObject(fieldMetaInfo.getType(), right_.getStruct(), _conf)) {
                throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
            }
            Struct structField_ = null;
            String className_ = fieldId.getClassName();
            if (classes_.isCustomType(className_)) {
                if (fieldMetaInfo.isStaticField()) {
                    structField_ = classes_.getStaticField(fieldId);
                } else {
                    if (argument_.isNull()) {
                        throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
                    }
                    String argClassName_ = argument_.getObjectClassName(_conf);
                    String classNameFound_ = fieldId.getClassName();
                    String base_ = StringList.getAllTypes(argClassName_).first();
                    if (!PrimitiveTypeUtil.canBeUseAsArgument(classNameFound_, base_, _conf)) {
                        throw new InvokeException(new StdStruct(new CustomError(StringList.concat(base_,RETURN_LINE,classNameFound_,RETURN_LINE,_conf.joinPages())),cast_));
                    }
                    structField_ = ((FieldableStruct) argument_.getStruct()).getStruct(fieldId);
                    if (staticChoiceField) {
                        if (!staticChoiceFieldTemplate) {
                            classNameFound_ = StringList.getAllTypes(classNameFound_).first();
                            classNameFound_ = Templates.getFullTypeByBases(argClassName_, classNameFound_, _conf);
                            String type_ = fieldMetaInfo.getRealType();
                            type_ = Templates.format(classNameFound_, type_, _conf);
                            Mapping map_ = new Mapping();
                            map_.setArg(right_.getObjectClassName(_conf));
                            map_.setParam(type_);
                            if (!Templates.isCorrect(map_, _conf)) {
                                throw new InvokeException(new StdStruct(new CustomError(StringList.concat(right_.getObjectClassName(_conf),RETURN_LINE,type_,RETURN_LINE,_conf.joinPages())),cast_));
                            }
                        }
                    }
                }
                left_.setStruct(structField_);
                res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op, catString);
                if (fieldMetaInfo.isStaticField()) {
                    classes_.initializeStaticField(fieldId, res_.getStruct());
                } else {
                    ((FieldableStruct) argument_.getStruct()).setStruct(fieldId, res_.getStruct());
                }
                Argument a_ = res_;
                return a_;
            }
            if (!fieldMetaInfo.isStaticField() && argument_.isNull()) {
                throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
            }
            String argClassName_ = argument_.getObjectClassName(_conf);
            String classNameFound_ = fieldId.getClassName();
            String base_ = StringList.getAllTypes(argClassName_).first();
            if (!PrimitiveTypeUtil.canBeUseAsArgument(classNameFound_, base_, _conf)) {
                throw new InvokeException(new StdStruct(new CustomError(StringList.concat(base_,RETURN_LINE,classNameFound_,RETURN_LINE,_conf.joinPages())),cast_));
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
        if (!Modifier.isStatic(field.getModifiers())) {
            if (argument_.isNull()) {
                throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
            }
        }
        Object obj_ = argument_.getStruct().getInstance();
        Object field_ = ConverterMethod.getField(field, obj_);
        Type type_ = field.getGenericType();
        String pre_ = NativeTypeUtil.getFormattedType(field.getType().getName(), type_.toString(), 0, type_);
        left_.setStruct(StdStruct.wrapStd(field_, pre_));
        if (right_.isNull() && field.getType().isPrimitive()) {
            throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
        }
        res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op, catString);
        ConverterMethod.setField(field, obj_, res_.getObject());
        Argument a_ = res_;
        return a_;
    }

    @Override
    public boolean isPossibleInitClass() {
        return possibleInitClass;
    }

    @Override
    public OperationNode getFirstChild() {
        return null;
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
}
