package code.expressionlanguage.opers;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElResolver;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.exceptions.BadFormatPathException;
import code.expressionlanguage.exceptions.DynamicCastClassException;
import code.expressionlanguage.exceptions.EmptyPartException;
import code.expressionlanguage.exceptions.ErrorCausingException;
import code.expressionlanguage.exceptions.NotInitializedClassException;
import code.expressionlanguage.exceptions.NullGlobalObjectException;
import code.expressionlanguage.exceptions.SettingMemberException;
import code.expressionlanguage.exceptions.StaticAccessException;
import code.expressionlanguage.exceptions.UndefinedVariableException;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ProcessXmlMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassCategory;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.FieldMetaInfo;
import code.expressionlanguage.opers.util.FieldResult;
import code.expressionlanguage.opers.util.SearchingMemberStatus;
import code.expressionlanguage.opers.util.Struct;
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
    private static final char CHAR_UPP_SUFFIX = 'C';
    private static final char CHAR_LOW_SUFFIX = 'c';
    private static final byte HEX_BASE = 16;

    private boolean variable;
    
    private boolean finalField;

    private boolean immutablePart;

    private boolean staticAccess;

    private boolean possibleInitClass;

    private String argClassName;
    private ClassField fieldId;
    private FieldMetaInfo fieldMetaInfo;

    private Field field;

    private boolean dottedPrevious;

    public ConstantOperation(int _index, ContextEl _importingPage, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _importingPage, _indexChild, _m, _op);
    }

    @Override
    boolean isFirstChild() {
        return getIndexChild() == CustList.FIRST_INDEX;
    }

    @Override
    public void analyze(CustList<OperationNode> _nodes, ContextEl _conf,
            String _fieldName, String _op) {
        analyzeCalculate(_conf);
        if (getArgument() != null) {
            String str_ = getOperations().getValues().getValue(CustList.FIRST_INDEX).trim();
            setResultClass(new ClassArgumentMatching(argClassName),staticAccess);
            getResultClass().setVariable(StringList.quickEq(str_, NULL_REF_STRING));
            return;
        }
        analyzeCommon(_conf);
        if (dottedPrevious) {
            setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
            throw new SettingMemberException(_conf.joinPages());
        }
    }

    void analyzeCommon(ContextEl _conf) {
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        PageEl ip_ = _conf.getLastPage();
        if (StringList.quickEq(str_, CURRENT_INTANCE)) {
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
        if (str_.endsWith(GET_FIELD)) {
            Classes classes_ = _conf.getClasses();
            ClassMetaInfo custClass_ = null;
            needGlobalArgument();
            ClassArgumentMatching cl_ = getPreviousResultClass();
            String clCurName_;
            if (str_.contains(STATIC_CALL)) {
                StringList classMethod_ = StringList.splitStrings(str_, STATIC_CALL);
                if (classMethod_.size() != 2) {
                    throw new BadFormatPathException(str_+RETURN_LINE+_conf.joinPages());
                }
                String className_ = classMethod_.first();
                if (!className_.startsWith(CLASS_CHOICE_PREF)) {
                    throw new BadFormatPathException(str_+RETURN_LINE+_conf.joinPages());
                }
                int lenPref_ = CLASS_CHOICE_PREF.length();
                className_ = className_.substring(lenPref_);
                className_ = StringList.removeAllSpaces(className_);
                className_ = className_.replace(EXTERN_CLASS, DOT_VAR);
                checkExist(_conf, className_, true, lenPref_);
                clCurName_ = className_;
            } else {
                if (cl_ == null) {
                    throw new NullGlobalObjectException(_conf.joinPages());
                }
                clCurName_ = cl_.getName();
            }
            if (classes_ != null) {
                custClass_ = classes_.getClassMetaInfo(clCurName_);
                if (custClass_ != null) {
                    String key_;
                    boolean superClassAccess_ = true;
                    FieldResult r_;
                    FieldMetaInfo e_;
                    if (str_.contains(STATIC_CALL)) {
                        StringList classMethod_ = StringList.splitStrings(str_, STATIC_CALL);
                        key_ = classMethod_.last();
                        key_ = key_.substring(CustList.FIRST_INDEX, key_.length() - GET_FIELD.length());
                        superClassAccess_ = false;
                        r_ = getDeclaredCustField(_conf, isStaticAccess(), new ClassArgumentMatching(clCurName_), superClassAccess_, key_);
                    } else if (str_.startsWith(EXTERN_CLASS+SUPER_ACCESS+EXTERN_CLASS)) {
                        key_ = str_.substring((EXTERN_CLASS+SUPER_ACCESS+EXTERN_CLASS).length(), str_.length() - GET_FIELD.length());
                        if (custClass_.getCategory() != ClassCategory.CLASS) {
                            throw new NoSuchDeclaredFieldException(key_+RETURN_LINE+_conf.joinPages());
                        }
                        cl_ = new ClassArgumentMatching(custClass_.getSuperClass());
                        r_ = getDeclaredCustField(_conf, isStaticAccess(), cl_, superClassAccess_, key_);
                    } else {
                        key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_FIELD.length());
                        superClassAccess_ = custClass_.getCategory() == ClassCategory.CLASS;
                        r_ = getDeclaredCustField(_conf, isStaticAccess(), cl_, superClassAccess_, key_);
                    }
                    if (r_.getStatus() == SearchingMemberStatus.ZERO) {
                        throw new NoSuchDeclaredFieldException(key_+RETURN_LINE+_conf.joinPages());
                    }
                    e_ = r_.getId();
                    String glClass_ = _conf.getLastPage().getGlobalClass();
                    String curClassBase_ = null;
                    if (glClass_ != null) {
                        curClassBase_ = StringList.getAllTypes(glClass_).first();
                    }
                    if (!_conf.getClasses().canAccessField(curClassBase_, e_.getDeclaringClass(), key_)) {
                        throw new BadAccessException(clCurName_+DOT+key_+RETURN_LINE+_conf.joinPages());
                    }
                    fieldMetaInfo = e_;
                    if (resultCanBeSet()) {
                        if (fieldMetaInfo.isFinalField()) {
                            finalField = true;
                        }
                    }
                    fieldId = new ClassField(e_.getDeclaringClass(), e_.getName());
                    String c_ = fieldMetaInfo.getType();
                    setResultClass(new ClassArgumentMatching(c_));
                    return;
                }
            }
            if (cl_ == null) {
                throw new NullGlobalObjectException(_conf.joinPages());
            }
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_FIELD.length());
            analyzeNativeField(_conf, key_);
            return;
        }
        if (str_.endsWith(GET_PARAM)) {
            if (getParent() == null) {
                immutablePart = true;
            }
            dottedPrevious = hasDottedPreviousSibling();
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_PARAM.length());
            LocalVariable locVar_ = ip_.getParameters().getVal(key_);
            if (locVar_ != null) {
                setResultClass(new ClassArgumentMatching(locVar_.getClassName()));
                return;
            }
            throw new UndefinedVariableException(_conf.joinPages(), key_, PARAMETER);
        }
        if (str_.endsWith(GET_CATCH_VAR)) {
            if (getParent() == null) {
                immutablePart = true;
            }
            dottedPrevious = hasDottedPreviousSibling();
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_CATCH_VAR.length());
            LocalVariable locVar_ = ip_.getCatchVars().getVal(key_);
            if (locVar_ != null) {
                setResultClass(new ClassArgumentMatching(locVar_.getClassName()));
                return;
            }
            throw new UndefinedVariableException(_conf.joinPages(), key_, CATCH_VARIABLE);
        }
        if (str_.endsWith(GET_LOC_VAR)) {
            dottedPrevious = hasDottedPreviousSibling();
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_LOC_VAR.length());
            LocalVariable locVar_ = ip_.getLocalVars().getVal(key_);
            if (locVar_ != null) {
                setResultClass(new ClassArgumentMatching(locVar_.getClassName()));
                return;
            }
            throw new UndefinedVariableException(_conf.joinPages(), key_, LOCAL_VARIABLE);
        }
        if (str_.endsWith(GET_INDEX)) {
            if (getParent() == null) {
                immutablePart = true;
            }
            dottedPrevious = hasDottedPreviousSibling();
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_INDEX.length());
            LoopVariable locVar_ = ip_.getVars().getVal(key_);
            if (locVar_ != null) {
                setResultClass(new ClassArgumentMatching(locVar_.getIndexClassName()));
                return;
            }
            throw new UndefinedVariableException(_conf.joinPages(), key_, INDEX);
        }
        if (str_.endsWith(GET_ATTRIBUTE)) {
            if (getParent() == null) {
                immutablePart = true;
            }
            dottedPrevious = hasDottedPreviousSibling();
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_ATTRIBUTE.length());
            LoopVariable locVar_ = ip_.getVars().getVal(key_);
            if (locVar_ != null) {
                setResultClass(new ClassArgumentMatching(locVar_.getClassName()));
                return;
            }
            throw new UndefinedVariableException(_conf.joinPages(), key_, ATTRIBUTE);
        }
        needGlobalArgument();
        ClassArgumentMatching cl_ = getPreviousResultClass();
        if (cl_ == null) {
            throw new NullGlobalObjectException(_conf.joinPages());
        }
        analyzeNativeField(_conf, str_);
    }
    private void analyzeNativeField(ContextEl _conf, String _key) {
        ClassArgumentMatching cl_ = getPreviousResultClass();
        if (cl_.isArray()) {
            if (StringList.quickEq(_key, LENGTH)) {
                setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.PRIM_INT));
                return;
            }
            throw new NoSuchDeclaredFieldException(cl_.getName()+RETURN_LINE+_key+RETURN_LINE+_conf.joinPages());
        }
        Field f_ = getDeclaredField(_conf, cl_, _key);
        if (!canBeUsed(f_, _conf)) {
            throw new BadAccessException(f_.getDeclaringClass().getName()+DOT+_key+RETURN_LINE+_conf.joinPages());
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
        setResultClass(new ClassArgumentMatching(NativeTypeUtil.getPrettyType(f_.getGenericType())));
    }
    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf, String _op) {
        return calculateArgument(_nodes, _conf, _op);
    }

    Argument calculateArgument(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf, String _op) {
        Argument arg_ = getCommonArgument(false, _nodes.getVal(this).getArgument(), _nodes.getVal(this).getPreviousArgument(), _conf, _op);
        setSimpleArgument(arg_, _conf, _nodes);
        return arg_;
    }
    @Override
    public Argument calculateSetting(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op) {
        Argument arg_ = getCommonSetting(true,  _nodes.getVal(this).getArgument(), _nodes.getVal(this).getPreviousArgument(), _conf, _op);
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
        Argument arg_ = getCommonArgument(true, getArgument(), getPreviousArgument(), _conf, _op);
        if (arg_ == null) {
            return;
        }
        setSimpleArgument(arg_, _conf);
    }

    @Override
    public void calculateSetting(CustList<OperationNode> _nodes,
            ContextEl _conf, String _op) {
        Argument arg_ = getCommonSetting(true, getArgument(), getPreviousArgument(), _conf, _op);
        setSimpleArgument(arg_, _conf);
    }

    Argument getCommonArgument(boolean _processInit, Argument _argument, Argument _previous, ContextEl _conf,
            String _op) {
        if (isPossibleInitClass()) {
            String className_ = getResultClass().getName();
            if (!_conf.getClasses().isInitialized(className_)) {
                _conf.getClasses().initialize(className_);
                if (!_processInit) {
                    throw new NotInitializedClassException(className_);
                }
                ProcessXmlMethod.initializeClass(className_, _conf);
                return null;
            }
        }
        Argument cur_ = _argument;
        if (cur_ != null) {
            return cur_;
        }
        Argument a_ = new Argument();
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        PageEl ip_ = _conf.getLastPage();
        if (StringList.quickEq(str_, CURRENT_INTANCE)) {
            Struct struct_ = ip_.getGlobalArgument().getStruct();
            a_ = new Argument();
            a_.setStruct(struct_);
            return a_;
        }
        if (fieldId != null) {
            Classes classes_ = _conf.getClasses();
            if (fieldMetaInfo.isStaticField()) {
                String className_ = fieldId.getClassName();
                if (!_conf.getClasses().isInitialized(className_)) {
                    _conf.getClasses().initialize(className_);
                    if (!_processInit) {
                        throw new NotInitializedClassException(className_);
                    }
                    ProcessXmlMethod.initializeClass(className_, _conf);
                }
                Struct struct_ = classes_.getStaticField(fieldId);
                a_ = new Argument();
                a_.setStruct(struct_);
                return a_;
            }
            Argument arg_ = _previous;
            if (arg_.isNull()) {
                throw new NullObjectException(_conf.joinPages());
            }
            String argClassName_ = arg_.getObjectClassName();
            String classNameFound_ = fieldId.getClassName();
            if (!PrimitiveTypeUtil.canBeUseAsArgument(classNameFound_, argClassName_, classes_)) {
                throw new DynamicCastClassException(argClassName_+RETURN_LINE+classNameFound_+RETURN_LINE+_conf.joinPages());
            }
            Struct struct_ = arg_.getStruct().getStruct(fieldId, field);
            a_ = new Argument();
            a_.setStruct(struct_);
            return a_;
        }
        if (str_.endsWith(GET_PARAM)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_PARAM.length());
            LocalVariable locVar_ = ip_.getParameters().getVal(key_);
            a_ = new Argument();
            a_.setStruct(locVar_.getStruct());
            return a_;
        }
        if (str_.endsWith(GET_CATCH_VAR)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_CATCH_VAR.length());
            LocalVariable locVar_ = ip_.getCatchVars().getVal(key_);
            a_ = new Argument();
            a_.setStruct(locVar_.getStruct());
            return a_;
        }
        if (str_.endsWith(GET_LOC_VAR)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_LOC_VAR.length());
            LocalVariable locVar_ = ip_.getLocalVars().getVal(key_);
            a_ = new Argument();
            a_.setStruct(locVar_.getStruct());
            return a_;
        }
        if (str_.endsWith(GET_INDEX)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_INDEX.length());
            LoopVariable locVar_ = ip_.getVars().getVal(key_);
            a_ = new Argument();
            a_.setStruct(new Struct(locVar_.getIndex()));
            return a_;
        }
        if (str_.endsWith(GET_ATTRIBUTE)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_ATTRIBUTE.length());
            LoopVariable locVar_ = ip_.getVars().getVal(key_);
            a_ = new Argument();
            a_.setStruct(locVar_.getStruct());
            return a_;
        }
        ClassArgumentMatching cl_ = getPreviousResultClass();
        Argument arg_ = _previous;
        if (cl_.isArray()) {
            if (arg_.isNull()) {
                throw new NullObjectException(_conf.joinPages());
            }
            a_ = new Argument();
            a_.setStruct(new Struct(Array.getLength(arg_.getObject())));
            return a_;
        }
        Object obj_ = arg_.getObject();
        if (!Modifier.isStatic(field.getModifiers()) && obj_ == null) {
            throw new NullObjectException(_conf.joinPages());
        }
        Object res_;
        try {
            res_ = ConverterMethod.getField(field, obj_);
        } catch (Throwable _0) {
            throw new ErrorCausingException(_conf.joinPages(), new Struct(_0));
        }
        a_ = new Argument();
        if (res_ == null) {
            a_.setStruct(new Struct());
        } else {
            a_.setStruct(new Struct(res_));
        }
        return a_;
    }

    Argument getCommonSetting(boolean _processInit, Argument _argument, Argument _previous, ContextEl _conf, String _op) {
        PageEl ip_ = _conf.getLastPage();

        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        if (str_.endsWith(GET_LOC_VAR)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_LOC_VAR.length());
            LocalVariable locVar_ = ip_.getLocalVars().getVal(key_);
            Argument left_ = new Argument();
            left_.setStruct(locVar_.getStruct());
            Argument right_ = ip_.getRightArgument();
            if (PrimitiveTypeUtil.primitiveTypeNullObject(locVar_.getClassName(), right_.getStruct())) {
                throw new NullObjectException(_conf.joinPages());
            }
            Argument res_;
            res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op);
            locVar_.setStruct(res_.getStruct());
            return res_;
        }
        Argument right_ = ip_.getRightArgument();
        Argument left_ = new Argument();
        Argument res_;
        Argument previous_ = _previous;
        if (fieldId != null) {
            Classes classes_ = _conf.getClasses();
            if (PrimitiveTypeUtil.primitiveTypeNullObject(fieldMetaInfo.getType(), right_.getStruct())) {
                throw new NullObjectException(_conf.joinPages());
            }
            Struct structField_ = null;
            if (fieldMetaInfo.isStaticField()) {
                structField_ = classes_.getStaticField(fieldId);
            } else {
                if (previous_.isNull()) {
                    throw new NullObjectException(_conf.joinPages());
                }
                String argClassName_ = previous_.getObjectClassName();
                String classNameFound_ = fieldId.getClassName();
                if (!PrimitiveTypeUtil.canBeUseAsArgument(classNameFound_, argClassName_, classes_)) {
                    throw new DynamicCastClassException(argClassName_+RETURN_LINE+classNameFound_+RETURN_LINE+_conf.joinPages());
                }
                structField_ = previous_.getStruct().getStruct(fieldId, field);
            }
            left_.setStruct(structField_);
            res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op);
            if (fieldMetaInfo.isStaticField()) {
                classes_.initializeStaticField(fieldId, res_.getStruct());
            } else {
                previous_.getStruct().setStruct(fieldId, res_.getStruct());
            }
            Argument a_ = _argument;
            return a_;
        }
        if (!Modifier.isStatic(field.getModifiers())) {
            if (previous_.isNull()) {
                throw new NullObjectException(_conf.joinPages());
            }
        }
        Object obj_ = previous_.getStruct().getInstance();
        Object field_ = ConverterMethod.getField(field, obj_);
        if (field_ == null) {
            left_.setStruct(new Struct());
        } else {
            left_.setStruct(new Struct(field_));
        }
        if (right_.isNull() && field.getType().isPrimitive()) {
            throw new NullObjectException(_conf.joinPages());
        }
        res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op);
        ConverterMethod.setField(field, obj_, res_.getObject());
        Argument a_ = _argument;
        return a_;
    }
    private void analyzeCalculate(ContextEl _cont) {
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _cont);
        if (str_.isEmpty()) {
            throw new EmptyPartException(_cont.joinPages());
        }
        if (isVararg()) {
            str_ = str_.substring(CustList.SECOND_INDEX);
            str_ = StringList.removeAllSpaces(str_);
            Argument a_ = new Argument();
            checkExist(_cont, str_, false, 0);
            argClassName = str_;
            setSimpleArgument(a_);
            return;
        }
        Argument a_ = new Argument();
        if (StringList.quickEq(str_, TRUE_STRING)) {
            argClassName = PrimitiveTypeUtil.PRIM_BOOLEAN;
            a_.setObject(true);
            setSimpleArgument(a_);
            return;
        }
        if (StringList.quickEq(str_, FALSE_STRING)) {
            argClassName = PrimitiveTypeUtil.PRIM_BOOLEAN;
            a_.setObject(false);
            setSimpleArgument(a_);
            return;
        }
        if (StringList.quickEq(str_, NULL_REF_STRING)) {
            argClassName = Object.class.getName();
            setSimpleArgument(a_);
            return;
        }
        if (str_.startsWith(String.valueOf(DELIMITER_STRING))) {
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
                            int val_ = Integer.parseInt(unicodeString_.toString(), HEX_BASE);
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
            argClassName = String.class.getName();
            a_.setObject(strBuilder_.toString());
            setSimpleArgument(a_);
            return;
        }
        if (str_.startsWith(String.valueOf(DELIMITER_CHAR))) {
            if (str_.charAt(str_.length()-1) == CHAR_UPP_SUFFIX) {
                argClassName = Character.class.getName();
            } else if (str_.charAt(str_.length()-1) == CHAR_LOW_SUFFIX) {
                argClassName = PrimitiveTypeUtil.PRIM_CHAR;
            } else {
                argClassName = PrimitiveTypeUtil.PRIM_CHAR;
            }
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
                            int val_ = Integer.parseInt(unicodeString_.toString(), HEX_BASE);
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
            return;
        }
        StringList sepWords_ = StringList.getWordsSeparators(str_);
        if (StringList.quickEq(sepWords_.get(CustList.SECOND_INDEX), STATIC_ACCESS)) {
            StringList path_ = sepWords_.mid(CustList.SECOND_INDEX + 2);
            StringBuilder class_ = new StringBuilder();
            for (String p: path_) {
                if (StringList.isWord(p)) {
                    class_.append(p);
                } else if (StringList.quickEq(p.trim(), String.valueOf(EXTERN_CLASS))){
                    class_.append(DOT_VAR);
                } else {
                    class_.append(INTERN_CLASS);
                }
            }
            String classStr_ = StringList.removeAllSpaces(class_.toString());
            String glClass_ = _cont.getLastPage().getGlobalClass();
            Classes classes_ = _cont.getClasses();
            ClassMetaInfo custClass_ = null;
            if (classes_ != null) {
                custClass_ = classes_.getClassMetaInfo(classStr_);
            }
            if (custClass_ == null) {
                checkExist(_cont, classStr_, false, 0);
            } else {
                //TODO exclude primitive
                String curClassBase_ = null;
                if (glClass_ != null) {
                    curClassBase_ = StringList.getAllTypes(glClass_).first();
                }
                if (!classes_.canAccessClass(curClassBase_, classStr_)) {
                    throw new BadAccessException(classStr_+RETURN_LINE+_cont.joinPages());
                }
                possibleInitClass = true;
            }
            staticAccess = true;
            a_ = new Argument();
            argClassName = classStr_;
            setArguments(a_);
            return;
        }
        try {
            str_ = StringList.removeAllSpaces(str_);
            argClassName = Argument.getArgClassNameOf(str_);
            Argument arg_ = Argument.numberToArgument(str_);
            setSimpleArgument(arg_);
        } catch (RuntimeException _0) {
        }
    }

    private boolean hasDottedPreviousSibling() {
        if (!(getParent() instanceof DotOperation)) {
            return false;
        }
        return getPreviousSibling() != null;
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
