package code.expressionlanguage.opers;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElResolver;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.exceptions.EmptyPartException;
import code.expressionlanguage.exceptions.ErrorCausingException;
import code.expressionlanguage.exceptions.FinalMemberException;
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
import code.expressionlanguage.opers.util.ClassName;
import code.expressionlanguage.opers.util.FieldMetaInfo;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.serialize.ConverterMethod;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;
import code.util.consts.ConstClasses;
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

    private boolean possibleInitClass;

    private String argClassName;

//    private boolean setNextSibling;
    
    private ClassField fieldId;
    private FieldMetaInfo fieldMetaInfo;

    private Field field;

    public ConstantOperation(String _el, int _index, ContextEl _importingPage, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_el, _index, _importingPage, _indexChild, _m, _op);
    }

//    @Override
//    boolean isFirstLeaf() {
////        String str_ = getOperations().getValues().getValue(CustList.FIRST_INDEX).trim();
////        return getIndexChild() == CustList.FIRST_INDEX && !str_.startsWith(String.valueOf(FIRST_VAR_ARG));
//        return getIndexChild() == CustList.FIRST_INDEX;
//    }
//
//    @Override
//    boolean isRealLeaf() {
//        return true;
//    }

    @Override
    boolean isFirstChild() {
        return getIndexChild() == CustList.FIRST_INDEX;
    }

//    @Override
//    public void analyze(CustList<OperationNode> _nodes, ContextEl _conf, Calculation _setting) {
//        analyzeCalculate(_setting, _conf);
//        if (getArgument() != null) {
//            String str_ = getOperations().getValues().getValue(CustList.FIRST_INDEX).trim();
//            boolean static_ = usingPureStaticAccess();
//            setResultClass(new ClassArgumentMatching(argClassName),static_);
//            getResultClass().setVariable(StringList.quickEq(str_, NULL_REF_STRING));
//            return;
//        }
//        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
//        String str_ = originalStr_.trim();
////        int off_ = StringList.getFirstPrintableCharIndex(originalStr_);
////        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_);
//        PageEl ip_ = _conf.getLastPage();
//        if (StringList.quickEq(str_, CURRENT_INTANCE)) {
//            Classes classes_ = _conf.getClasses();
//            if (classes_ != null) {
//                if (_setting.getStep()  == StepCalculation.LEFT && getParent() == null) {
//                    setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
//                    throw new SettingMemberException(_conf.joinPages());
//                }
//                if (isStaticAccess()) {
//                    throw new StaticAccessException(_conf.joinPages());
//                }
////                Argument arg_ = _conf.getLastPage().getGlobalArgument();
//                String arg_ = _conf.getLastPage().getGlobalClass();
//                setResultClass(new ClassArgumentMatching(arg_));
//                return;
//            }
//        }
//        if (str_.endsWith(GET_FIELD)) {
//            Classes classes_ = _conf.getClasses();
//            ClassMetaInfo custClass_ = null;
//            if (classes_ != null) {
//                setNeedPrevious(true);
//                setResetablePreviousArg(true);
//                ClassArgumentMatching cl_ = getPreviousResultClass();
//                if (cl_ == null) {
//                    throw new NullGlobalObjectException(_conf.joinPages());
//                }
//                variable = getParent() == null || ElUtil.getDirectChildren(getParent()).last() == this;
//                String clCurName_ = cl_.getName();
//                custClass_ = classes_.getClassMetaInfo(clCurName_);
//                if (custClass_ != null) {
//                    String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_FIELD.length());
//                    FieldMetaInfo e_;
//                    e_ = getDeclaredCustField(_conf, cl_, key_);
//                    fieldMetaInfo = e_;
//                    if (isStaticAccess() && !e_.isStaticField()) {
//                        throw new StaticAccessException(_conf.joinPages());
//                    }
//                    if (_setting.getStep() != StepCalculation.RIGHT) {
//                        if (resultCanBeSet()) {
//                            if (_setting.getStep() != StepCalculation.SETTING) {
//                                setCalculatedLater(true);
//                            }
//                        }
//                    }
//                    fieldId = new ClassField(e_.getDeclaringClass().getName(), e_.getName());
//                    ClassName c_ = fieldMetaInfo.getType();
//                    setResultClass(new ClassArgumentMatching(c_.getName()));
//                    return;
//                }
//            }
//        }
//        if (str_.endsWith(GET_PARAM)) {
//            if (_setting.getStep()  == StepCalculation.LEFT && getParent() == null) {
//                setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
//                throw new SettingMemberException(_conf.joinPages());
//            }
//            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_PARAM.length());
//            LocalVariable locVar_ = ip_.getParameters().getVal(key_);
//            if (locVar_ != null) {
////                setResultClass(new ClassArgumentMatching(ConstClasses.classForNameNotInit(locVar_.getClassName())));
//                setResultClass(new ClassArgumentMatching(locVar_.getClassName()));
//                return;
//            }
//            throw new UndefinedVariableException(_conf.joinPages(), key_, PARAMETER);
//        }
//        if (str_.endsWith(GET_CATCH_VAR)) {
//            if (_setting.getStep()  == StepCalculation.LEFT && getParent() == null) {
//                setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
//                throw new SettingMemberException(_conf.joinPages());
//            }
//            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_CATCH_VAR.length());
//            LocalVariable locVar_ = ip_.getCatchVars().getVal(key_);
//            if (locVar_ != null) {
////                setResultClass(new ClassArgumentMatching(ConstClasses.classForNameNotInit(locVar_.getClassName())));
//                setResultClass(new ClassArgumentMatching(locVar_.getClassName()));
//                return;
//            }
//            throw new UndefinedVariableException(_conf.joinPages(), key_, CATCH_VARIABLE);
//        }
//        if (str_.endsWith(GET_LOC_VAR)) {
//            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_LOC_VAR.length());
//            LocalVariable locVar_ = ip_.getLocalVars().getVal(key_);
//            if (locVar_ != null) {
//                variable = getParent() == null;
//                if (_setting.getStep() == StepCalculation.LEFT) {
//                    if (resultCanBeSet()) {
//                        setCalculatedLater(true);
//                    }
//                }
////                setResultClass(new ClassArgumentMatching(ConstClasses.classForNameNotInit(locVar_.getClassName())));
//                setResultClass(new ClassArgumentMatching(locVar_.getClassName()));
//                return;
//            }
//            throw new UndefinedVariableException(_conf.joinPages(), key_, LOCAL_VARIABLE);
//        }
//        if (str_.endsWith(GET_INDEX)) {
//            if (_setting.getStep()  == StepCalculation.LEFT && getParent() == null) {
//                setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
//                throw new SettingMemberException(_conf.joinPages());
//            }
//            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_INDEX.length());
//            LoopVariable locVar_ = ip_.getVars().getVal(key_);
//            if (locVar_ != null) {
////                setResultClass(new ClassArgumentMatching(ConstClasses.classForNameNotInit(locVar_.getIndexClassName())));
//                setResultClass(new ClassArgumentMatching(locVar_.getIndexClassName()));
//                return;
//            }
//            throw new UndefinedVariableException(_conf.joinPages(), key_, INDEX);
//        }
//        if (str_.endsWith(GET_ATTRIBUTE)) {
//            if (_setting.getStep()  == StepCalculation.LEFT && getParent() == null) {
//                setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
//                throw new SettingMemberException(_conf.joinPages());
//            }
//            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_ATTRIBUTE.length());
//            LoopVariable locVar_ = ip_.getVars().getVal(key_);
//            if (locVar_ != null) {
////                setResultClass(new ClassArgumentMatching(ConstClasses.classForNameNotInit(locVar_.getClassName())));
//                setResultClass(new ClassArgumentMatching(locVar_.getClassName()));
//                return;
//            }
//            throw new UndefinedVariableException(_conf.joinPages(), key_, ATTRIBUTE);
//        }
//        setNeedPrevious(true);
//        setResetablePreviousArg(true);
//        ClassArgumentMatching cl_ = getPreviousResultClass();
//        if (cl_ == null) {
//            throw new NullGlobalObjectException(_conf.joinPages());
//        }
//        if (cl_.isArray() && StringList.quickEq(str_, LENGTH)) {
//            setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.PRIM_INT));
//            return;
//        }
//        variable = getParent() == null || ElUtil.getDirectChildren(getParent()).last() == this;
//        Field f_ = getDeclaredField(_conf, cl_, str_);
//        if (_setting.getStep() == StepCalculation.LEFT && Modifier.isFinal(f_.getModifiers())) {
//            if (variable) {
//                setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
//                throw new FinalMemberException(_conf.joinPages());
//            }
//        }
//        if (isStaticAccess() && !Modifier.isStatic(f_.getModifiers())) {
//            throw new StaticAccessException(_conf.joinPages());
//        }
//        if (_setting.getStep() != StepCalculation.RIGHT) {
//            if (resultCanBeSet()) {
//                if (_setting.getStep() != StepCalculation.SETTING) {
//                    setCalculatedLater(true);
//                }
//            }
//        }
//        field = f_;
//        setAccess(field, _conf);
////        setRelativeOffsetPossibleLastPage(key_+str_.length());
//        setResultClass(new ClassArgumentMatching(f_.getType().getName()));
//    }

    @Override
    public void analyzeLeft(CustList<OperationNode> _nodes, ContextEl _conf,
            boolean _enumContext, String _op) {
        analyzeCalculate(true, _conf);
        if (getArgument() != null) {
            String str_ = getOperations().getValues().getValue(CustList.FIRST_INDEX).trim();
            boolean static_ = usingPureStaticAccess();
            setResultClass(new ClassArgumentMatching(argClassName),static_);
            getResultClass().setVariable(StringList.quickEq(str_, NULL_REF_STRING));
            return;
        }
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        PageEl ip_ = _conf.getLastPage();
        if (StringList.quickEq(str_, CURRENT_INTANCE)) {
            Classes classes_ = _conf.getClasses();
            if (classes_ != null) {
                if (getParent() == null) {
                    setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
                    throw new SettingMemberException(_conf.joinPages());
                }
                if (isStaticAccess()) {
                    throw new StaticAccessException(_conf.joinPages());
                }
//                Argument arg_ = _conf.getLastPage().getGlobalArgument();
                String arg_ = _conf.getLastPage().getGlobalClass();
                setResultClass(new ClassArgumentMatching(arg_));
                return;
            }
        }
        if (str_.endsWith(GET_FIELD)) {
            Classes classes_ = _conf.getClasses();
            ClassMetaInfo custClass_ = null;
            if (classes_ != null) {
                setNeedPrevious(true);
                setResetablePreviousArg(true);
                ClassArgumentMatching cl_ = getPreviousResultClass();
                if (cl_ == null) {
                    throw new NullGlobalObjectException(_conf.joinPages());
                }
                variable = getParent() == null || ElUtil.getDirectChildren(getParent()).last() == this;
                String clCurName_ = cl_.getName();
                custClass_ = classes_.getClassMetaInfo(clCurName_);
                if (custClass_ != null) {
                    String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_FIELD.length());
                    FieldMetaInfo e_;
                    e_ = getDeclaredCustField(_conf, cl_, key_);
                    fieldMetaInfo = e_;
                    if (isStaticAccess() && !e_.isStaticField()) {
                        throw new StaticAccessException(_conf.joinPages());
                    }
                    if (variable) {
                        if (custClass_.getCategory() == ClassCategory.ENUM) {
                            if (fieldMetaInfo.isEnumElement()) {
                                setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
                                throw new FinalMemberException(_conf.joinPages());
                            }
                        }
                    }
                    if (resultCanBeSet()) {
                        setCalculatedLater(true);
                    }
                    fieldId = new ClassField(e_.getDeclaringClass().getName(), e_.getName());
                    ClassName c_ = fieldMetaInfo.getType();
                    setResultClass(new ClassArgumentMatching(c_.getName()));
                    return;
                }
            }
        }
        if (str_.endsWith(GET_PARAM)) {
            if (getParent() == null) {
                setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
                throw new SettingMemberException(_conf.joinPages());
            }
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
                setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
                throw new SettingMemberException(_conf.joinPages());
            }
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_CATCH_VAR.length());
            LocalVariable locVar_ = ip_.getCatchVars().getVal(key_);
            if (locVar_ != null) {
                setResultClass(new ClassArgumentMatching(locVar_.getClassName()));
                return;
            }
            throw new UndefinedVariableException(_conf.joinPages(), key_, CATCH_VARIABLE);
        }
        if (str_.endsWith(GET_LOC_VAR)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_LOC_VAR.length());
            LocalVariable locVar_ = ip_.getLocalVars().getVal(key_);
            if (locVar_ != null) {
                variable = getParent() == null;
                if (resultCanBeSet()) {
                    setCalculatedLater(true);
                }
                setResultClass(new ClassArgumentMatching(locVar_.getClassName()));
                return;
            }
            throw new UndefinedVariableException(_conf.joinPages(), key_, LOCAL_VARIABLE);
        }
        if (str_.endsWith(GET_INDEX)) {
            if (getParent() == null) {
                setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
                throw new SettingMemberException(_conf.joinPages());
            }
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
                setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
                throw new SettingMemberException(_conf.joinPages());
            }
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_ATTRIBUTE.length());
            LoopVariable locVar_ = ip_.getVars().getVal(key_);
            if (locVar_ != null) {
                setResultClass(new ClassArgumentMatching(locVar_.getClassName()));
                return;
            }
            throw new UndefinedVariableException(_conf.joinPages(), key_, ATTRIBUTE);
        }
        setNeedPrevious(true);
        setResetablePreviousArg(true);
        ClassArgumentMatching cl_ = getPreviousResultClass();
        if (cl_ == null) {
            throw new NullGlobalObjectException(_conf.joinPages());
        }
        if (cl_.isArray() && StringList.quickEq(str_, LENGTH)) {
            setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.PRIM_INT));
            return;
        }
        variable = getParent() == null || ElUtil.getDirectChildren(getParent()).last() == this;
        Field f_ = getDeclaredField(_conf, cl_, str_);
        if (Modifier.isFinal(f_.getModifiers())) {
            if (variable) {
                setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
                throw new FinalMemberException(_conf.joinPages());
            }
        }
        if (isStaticAccess() && !Modifier.isStatic(f_.getModifiers())) {
            throw new StaticAccessException(_conf.joinPages());
        }
        if (resultCanBeSet()) {
            setCalculatedLater(true);
        }
        field = f_;
        setAccess(field, _conf);
        if (f_.getType().isPrimitive()) {
            setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.PRIM+f_.getType().getName()));
        } else {
//            setResultClass(new ClassArgumentMatching(f_.getType().getName()));
            setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getAliasArrayClass(f_.getType())));
        }
    }

    @Override
    public void analyzeRight(CustList<OperationNode> _nodes, ContextEl _conf,
            boolean _enumContext, String _op) {
        analyzeCalculate(false, _conf);
        analyzeNotLeft(_nodes, _conf, _op);
    }

    @Override
    public void analyzeSetting(CustList<OperationNode> _nodes, ContextEl _conf,
            boolean _enumContext, String _op) {
        analyzeCalculate(false, _conf);
        analyzeNotLeft(_nodes, _conf, _op);
    }

    void analyzeNotLeft(CustList<OperationNode> _nodes, ContextEl _conf, String _op) {
        if (getArgument() != null) {
            String str_ = getOperations().getValues().getValue(CustList.FIRST_INDEX).trim();
            boolean static_ = usingPureStaticAccess();
            setResultClass(new ClassArgumentMatching(argClassName),static_);
            getResultClass().setVariable(StringList.quickEq(str_, NULL_REF_STRING));
            return;
        }
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
//        int off_ = StringList.getFirstPrintableCharIndex(originalStr_);
//        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_);
        PageEl ip_ = _conf.getLastPage();
        if (StringList.quickEq(str_, CURRENT_INTANCE)) {
            Classes classes_ = _conf.getClasses();
            if (classes_ != null) {
                if (isStaticAccess()) {
                    throw new StaticAccessException(_conf.joinPages());
                }
//                Argument arg_ = _conf.getLastPage().getGlobalArgument();
                String arg_ = _conf.getLastPage().getGlobalClass();
                setResultClass(new ClassArgumentMatching(arg_));
                return;
            }
        }
        if (str_.endsWith(GET_FIELD)) {
            Classes classes_ = _conf.getClasses();
            ClassMetaInfo custClass_ = null;
            if (classes_ != null) {
                setNeedPrevious(true);
                setResetablePreviousArg(true);
                ClassArgumentMatching cl_ = getPreviousResultClass();
                if (cl_ == null) {
                    throw new NullGlobalObjectException(_conf.joinPages());
                }
                variable = getParent() == null || ElUtil.getDirectChildren(getParent()).last() == this;
                String clCurName_ = cl_.getName();
                custClass_ = classes_.getClassMetaInfo(clCurName_);
                if (custClass_ != null) {
                    String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_FIELD.length());
                    FieldMetaInfo e_;
                    e_ = getDeclaredCustField(_conf, cl_, key_);
                    fieldMetaInfo = e_;
                    if (isStaticAccess() && !e_.isStaticField()) {
                        throw new StaticAccessException(_conf.joinPages());
                    }
                    fieldId = new ClassField(e_.getDeclaringClass().getName(), e_.getName());
                    ClassName c_ = fieldMetaInfo.getType();
                    setResultClass(new ClassArgumentMatching(c_.getName()));
                    return;
                }
            }
        }
        if (str_.endsWith(GET_PARAM)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_PARAM.length());
            LocalVariable locVar_ = ip_.getParameters().getVal(key_);
            if (locVar_ != null) {
//                setResultClass(new ClassArgumentMatching(ConstClasses.classForNameNotInit(locVar_.getClassName())));
                setResultClass(new ClassArgumentMatching(locVar_.getClassName()));
                return;
            }
            throw new UndefinedVariableException(_conf.joinPages(), key_, PARAMETER);
        }
        if (str_.endsWith(GET_CATCH_VAR)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_CATCH_VAR.length());
            LocalVariable locVar_ = ip_.getCatchVars().getVal(key_);
            if (locVar_ != null) {
//                setResultClass(new ClassArgumentMatching(ConstClasses.classForNameNotInit(locVar_.getClassName())));
                setResultClass(new ClassArgumentMatching(locVar_.getClassName()));
                return;
            }
            throw new UndefinedVariableException(_conf.joinPages(), key_, CATCH_VARIABLE);
        }
        if (str_.endsWith(GET_LOC_VAR)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_LOC_VAR.length());
            LocalVariable locVar_ = ip_.getLocalVars().getVal(key_);
            if (locVar_ != null) {
                variable = getParent() == null;
                setResultClass(new ClassArgumentMatching(locVar_.getClassName()));
                return;
            }
            throw new UndefinedVariableException(_conf.joinPages(), key_, LOCAL_VARIABLE);
        }
        if (str_.endsWith(GET_INDEX)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_INDEX.length());
            LoopVariable locVar_ = ip_.getVars().getVal(key_);
            if (locVar_ != null) {
                setResultClass(new ClassArgumentMatching(locVar_.getIndexClassName()));
                return;
            }
            throw new UndefinedVariableException(_conf.joinPages(), key_, INDEX);
        }
        if (str_.endsWith(GET_ATTRIBUTE)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_ATTRIBUTE.length());
            LoopVariable locVar_ = ip_.getVars().getVal(key_);
            if (locVar_ != null) {
                setResultClass(new ClassArgumentMatching(locVar_.getClassName()));
                return;
            }
            throw new UndefinedVariableException(_conf.joinPages(), key_, ATTRIBUTE);
        }
        setNeedPrevious(true);
        setResetablePreviousArg(true);
        ClassArgumentMatching cl_ = getPreviousResultClass();
        if (cl_ == null) {
            throw new NullGlobalObjectException(_conf.joinPages());
        }
        if (cl_.isArray() && StringList.quickEq(str_, LENGTH)) {
            setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.PRIM_INT));
            return;
        }
        variable = getParent() == null || ElUtil.getDirectChildren(getParent()).last() == this;
        Field f_ = getDeclaredField(_conf, cl_, str_);
        if (isStaticAccess() && !Modifier.isStatic(f_.getModifiers())) {
            throw new StaticAccessException(_conf.joinPages());
        }
        field = f_;
        setAccess(field, _conf);
//        setRelativeOffsetPossibleLastPage(key_+str_.length());
        if (ConstClasses.getPrimitiveClass(f_.getType().getName()) != null) {
            setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.PRIM+f_.getType().getName()));
        } else {
            setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getAliasArrayClass(f_.getType())));
//            setResultClass(new ClassArgumentMatching(f_.getType().getName()));
        }
    }
//    @Override
//    public Argument calculate(
//            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf,
//            Calculation _setting) {
//        if (_setting.getStep() == StepCalculation.SETTING) {
//            PageEl ip_ = _conf.getLastPage();
//            if (resultCanBeSet()) {
//                String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
//                String str_ = originalStr_.trim();
//                int off_ = StringList.getFirstPrintableCharIndex(originalStr_);
//                setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
//                if (str_.endsWith(GET_LOC_VAR)) {
//                    String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_LOC_VAR.length());
//                    LocalVariable locVar_ = ip_.getLocalVars().getVal(key_);
//                    String op_ = _setting.getOper();
//                    Argument left_ = new Argument();
////                    left_.setArgClassName(locVar_.getClassName());
////                    left_.setObject(locVar_.getElement());
////                    left_.setStruct(locVar_.getStruct());
//                    left_.setStructArgClassName(locVar_.getStruct(), locVar_.getClassName());
//                    Argument right_ = ip_.getRightArgument();
//                    Argument res_;
//                    res_ = NumericOperation.calculateAffect(left_, _conf, right_, op_);
//                    locVar_.setStruct(res_.getStruct());
//                    return res_;
//                }
////                
//                String op_ = _setting.getOper();
//                Struct struct_ = _nodes.getVal(this).getArgument().getStruct();
//                Argument right_ = ip_.getRightArgument();
//                Argument res_;
//                Argument left_ = new Argument();
//                if (fieldId != null) {
//                    // TODO _nodes.getVal(this).getPreviousArgument().getStruct()
//                    Classes classes_ = _conf.getClasses();
//                    if (fieldMetaInfo.isStaticField()) {
//                        Struct structField_ = classes_.getStaticField(fieldId);
////                        left_.setArgClassName(fieldMetaInfo.getType().getName());
////                        left_.setStruct(structField_);
//                        left_.setStructArgClassName(structField_, fieldMetaInfo.getType().getName());
//                        res_ = NumericOperation.calculateAffect(left_, _conf, right_, op_);
//                        classes_.initializeStaticField(fieldId, res_.getStruct());
//                    } else {
//                        Argument arg_ = _nodes.getVal(this).getPreviousArgument();
//                        Struct structField_ = arg_.getStruct().getStruct(fieldId, field);
////                        left_.setArgClassName(fieldMetaInfo.getType().getName());
////                        left_.setStruct(structField_);
//                        left_.setStructArgClassName(structField_, fieldMetaInfo.getType().getName());
//                        res_ = NumericOperation.calculateAffect(left_, _conf, right_, op_);
//                        arg_.getStruct().setStruct(fieldId, res_.getStruct());
//                    }
//                    
////                    ConverterMethod.setField(field, obj_, res_.getObject());
//                } else {
//                    Object obj_ = struct_.getInstance();
//                    Object field_ = ConverterMethod.getField(field, obj_);
////                    left_.setObject(field_);
////                    left_.setArgClassName(field.getType().getName());
//                    if (field_ == null) {
//                        left_.setStructArgClassName(new Struct(),field.getType().getName());
//                    } else {
//                        left_.setStructArgClassName(new Struct(field_),field.getType().getName());
//                    }
//                    res_ = NumericOperation.calculateAffect(left_, _conf, right_, op_);
//                    ConverterMethod.setField(field, obj_, res_.getObject());
//                }
////                if (fieldId != null) {
//////                    field_ = 
////                } else {
////                    field_ = ConverterMethod.getField(field, obj_);
////                }
//                
////                Argument res_;
////                res_ = NumericOperation.calculateAffect(getArgument(), _conf, right_, op_);
//                Argument a_ = _nodes.getVal(this).getArgument();
//                setSimpleArgument(a_, _conf, _nodes);
//                return a_;
//            }
//        }
//        if (isPossibleInitClass()) {
////            Argument cur_ = _nodes.getVal(this).getArgument();
//            String className_ = getResultClass().getName();
//            if (!_conf.getClasses().isInitialized(className_)) {
//                _conf.getClasses().initialize(className_);
//                throw new NotInitializedClassException(className_);
//            }
//        }
//        Argument cur_ = _nodes.getVal(this).getArgument();
//        if (cur_ != null) {
//            return cur_;
//        }
//        Argument a_ = new Argument();
//        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
//        String str_ = originalStr_.trim();
//        int off_ = StringList.getFirstPrintableCharIndex(originalStr_);
//        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
//        PageEl ip_ = _conf.getLastPage();
//        if (StringList.quickEq(str_, CURRENT_INTANCE)) {
//            Struct struct_ = ip_.getGlobalArgument().getStruct();
//            a_ = new Argument();
////            a_.setArgClassName(getResultClass().getName());
////            a_.setStruct(struct_);
//            a_.setStructArgClassName(struct_, getResultClass().getName());
//            setSimpleArgument(a_, _conf, _nodes);
//            return a_;
//        }
//        if (fieldId != null) {
//            if (fieldMetaInfo.isStaticField()) {
//                Classes classes_ = _conf.getClasses();
//                Struct struct_ = classes_.getStaticField(fieldId);
//                a_ = new Argument();
////                a_.setArgClassName(fieldMetaInfo.getType().getName());
////                a_.setStruct(struct_);
//                a_.setStructArgClassName(struct_, fieldMetaInfo.getType().getName());
//                setSimpleArgument(a_, _conf, _nodes);
//                return a_;
//            }
//            Argument arg_ = _nodes.getVal(this).getPreviousArgument();
//            Struct struct_ = arg_.getStruct().getStruct(fieldId, field);
//            a_ = new Argument();
////            a_.setArgClassName(fieldMetaInfo.getType().getName());
////            a_.setStruct(struct_);
//            a_.setStructArgClassName(struct_, fieldMetaInfo.getType().getName());
//            setSimpleArgument(a_, _conf, _nodes);
//            return a_;
//        }
//        if (str_.endsWith(GET_PARAM)) {
//            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_PARAM.length());
//            LocalVariable locVar_ = ip_.getParameters().getVal(key_);
//            a_ = new Argument();
////            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
////            a_.setArgClassName(getResultClass().getName());
////            a_.setObject(locVar_.getElement());
////            a_.setStruct(locVar_.getStruct());
//            a_.setStructArgClassName(locVar_.getStruct(), getResultClass().getName());
//            setSimpleArgument(a_, _conf, _nodes);
//            return a_;
//        }
//        if (str_.endsWith(GET_CATCH_VAR)) {
//            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_CATCH_VAR.length());
//            LocalVariable locVar_ = ip_.getCatchVars().getVal(key_);
//            a_ = new Argument();
////            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
////            a_.setArgClassName(getResultClass().getName());
////            a_.setObject(locVar_.getElement());
////            a_.setStruct(locVar_.getStruct());
//            a_.setStructArgClassName(locVar_.getStruct(), getResultClass().getName());
//            setSimpleArgument(a_, _conf, _nodes);
//            return a_;
//        }
//        if (str_.endsWith(GET_LOC_VAR)) {
//            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_LOC_VAR.length());
//            LocalVariable locVar_ = ip_.getLocalVars().getVal(key_);
//            if (_setting.getStep() == StepCalculation.LEFT) {
//                if (resultCanBeSet()) {
//                    //TODO other op += -=...
//                    a_ = Argument.createVoid();
//                    setSimpleArgument(a_, _conf, _nodes);
//                    return a_;
//                }
//            }
//            a_ = new Argument();
////            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
////            a_.setArgClassName(getResultClass().getName());
////            a_.setObject(locVar_.getElement());
////            a_.setStruct(locVar_.getStruct());
//            a_.setStructArgClassName(locVar_.getStruct(), getResultClass().getName());
//            setSimpleArgument(a_, _conf, _nodes);
//            return a_;
//        }
//        if (str_.endsWith(GET_INDEX)) {
//            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_INDEX.length());
//            LoopVariable locVar_ = ip_.getVars().getVal(key_);
//            a_ = new Argument();
////            a_.setArgClass(ConstClasses.classForName(locVar_.getIndexClassName()));
////            a_.setArgClassName(getResultClass().getName());
////            a_.setObject(locVar_.getIndex());
//            a_.setStructArgClassName(new Struct(locVar_.getIndex()), getResultClass().getName());
//            setSimpleArgument(a_, _conf, _nodes);
//            return a_;
//        }
//        if (str_.endsWith(GET_ATTRIBUTE)) {
//            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_ATTRIBUTE.length());
//            LoopVariable locVar_ = ip_.getVars().getVal(key_);
//            a_ = new Argument();
////            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
////            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
////            a_.setArgClassName(getResultClass().getName());
////            a_.setObject(locVar_.getElement());
////            a_.setStruct(locVar_.getStruct());
//            a_.setStructArgClassName(locVar_.getStruct(), getResultClass().getName());
//            setSimpleArgument(a_, _conf, _nodes);
//            return a_;
//        }
//        Argument arg_ = _nodes.getVal(this).getPreviousArgument();
//        if (arg_.isArrayClass() && StringList.quickEq(str_, LENGTH)) {
//            if (arg_.getObject() == null) {
//                throw new NullObjectException(_conf.joinPages());
//            }
//            a_ = new Argument();
////            a_.setArgClassName(int.class.getName());
////            a_.setObject(Array.getLength(arg_.getObject()));
//            a_.setStructArgClassName(new Struct(Array.getLength(arg_.getObject())), PrimitiveTypeUtil.PRIM_INT);
//            setSimpleArgument(a_, _conf, _nodes);
//            return a_;
//        }
////        if (fieldId != null) {
////            Classes classes_ = _conf.getClasses();
////            Struct struct_ = classes_.getStaticField(fieldId);
////            a_ = new Argument();
////            a_.setArgClassName(fieldMetaInfo.getType().getName());
////            a_.setStruct(struct_);
////            setSimpleArgument(a_, _conf, _nodes);
////            return a_;
////        }
////        Class<?> cl_ = arg_.getArgClass();
//        Object obj_ = arg_.getObject();
////        Field f_ = SerializeXmlObject.getDeclaredField(cl_, str_);
//        if (!Modifier.isStatic(field.getModifiers()) && obj_ == null) {
//            throw new NullObjectException(_conf.joinPages());
//        }
//        if (_setting.getStep() != StepCalculation.RIGHT) {
//            if (resultCanBeSet()) {
//                if (_setting.getStep() != StepCalculation.SETTING) {
//                    a_ = Argument.createVoid();
//                    a_.setStruct(arg_.getStruct());
//                    setSimpleArgument(a_, _conf, _nodes);
//                    return a_;
//                }
//                obj_ = _nodes.getVal(this).getArgument().getObject();
//                String op_ = _setting.getOper();
//                Argument right_ = ip_.getRightArgument();
//                Argument res_;
//                Object field_ = ConverterMethod.getField(field, obj_);
//                Argument left_ = new Argument();
//                if (field_ == null) {
//                    left_.setStructArgClassName(new Struct(), field.getType().getName());
//                } else {
//                    left_.setStructArgClassName(new Struct(field_), field.getType().getName());
//                }
////                left_.setObject(field_);
////                left_.setArgClassName(field.getType().getName());
//                res_ = NumericOperation.calculateAffect(left_, _conf, right_, op_);
////                ConverterMethod.setField(field, obj_, ip_.getRightArgument().getObject());
//                ConverterMethod.setField(field, obj_, res_.getObject());
//                Argument curArg_ = _nodes.getVal(this).getArgument();
//                setSimpleArgument(curArg_, _conf, _nodes);
//                return curArg_;
//            }
//        }
//        Object res_;
//        try {
//            res_ = ConverterMethod.getField(field, obj_);
//        } catch (ExceptionInInitializerError _0) {
//            throw new ErrorCausingException(_conf.joinPages(), new Struct(_0));
//        }
//        a_ = new Argument();
////        a_.setArgClassName(field.getType().getName());
////        a_.setObject(res_);
//        if (res_ == null) {
//            a_.setStructArgClassName(new Struct(), field.getType().getName());
//        } else {
//            a_.setStructArgClassName(new Struct(res_), field.getType().getName());
//        }
//        setSimpleArgument(a_, _conf, _nodes);
//        return a_;
//    }
    @Override
    public Argument calculateLeft(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf, String _op) {
        if (isPossibleInitClass()) {
//            Argument cur_ = _nodes.getVal(this).getArgument();
            String className_ = getResultClass().getName();
            if (!_conf.getClasses().isInitialized(className_)) {
                _conf.getClasses().initialize(className_);
                throw new NotInitializedClassException(className_);
            }
        }
        Argument cur_ = _nodes.getVal(this).getArgument();
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
//            a_.setArgClassName(getResultClass().getName());
//            a_.setStruct(struct_);
            a_.setStructArgClassName(struct_, getResultClass().getName());
            setSimpleArgument(a_, _conf, _nodes);
            return a_;
        }
        if (fieldId != null) {
            if (fieldMetaInfo.isStaticField()) {
                Classes classes_ = _conf.getClasses();
                Struct struct_ = classes_.getStaticField(fieldId);
                a_ = new Argument();
//                a_.setArgClassName(fieldMetaInfo.getType().getName());
//                a_.setStruct(struct_);
                a_.setStructArgClassName(struct_, fieldMetaInfo.getType().getName());
                setSimpleArgument(a_, _conf, _nodes);
                return a_;
            }
            Argument arg_ = _nodes.getVal(this).getPreviousArgument();
            Struct struct_ = arg_.getStruct().getStruct(fieldId, field);
            a_ = new Argument();
//            a_.setArgClassName(fieldMetaInfo.getType().getName());
//            a_.setStruct(struct_);
            a_.setStructArgClassName(struct_, fieldMetaInfo.getType().getName());
            setSimpleArgument(a_, _conf, _nodes);
            return a_;
        }
        if (str_.endsWith(GET_PARAM)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_PARAM.length());
            LocalVariable locVar_ = ip_.getParameters().getVal(key_);
            a_ = new Argument();
//            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
//            a_.setArgClassName(getResultClass().getName());
//            a_.setObject(locVar_.getElement());
//            a_.setStruct(locVar_.getStruct());
            a_.setStructArgClassName(locVar_.getStruct(), getResultClass().getName());
            setSimpleArgument(a_, _conf, _nodes);
            return a_;
        }
        if (str_.endsWith(GET_CATCH_VAR)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_CATCH_VAR.length());
            LocalVariable locVar_ = ip_.getCatchVars().getVal(key_);
            a_ = new Argument();
//            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
//            a_.setArgClassName(getResultClass().getName());
//            a_.setObject(locVar_.getElement());
//            a_.setStruct(locVar_.getStruct());
            a_.setStructArgClassName(locVar_.getStruct(), getResultClass().getName());
            setSimpleArgument(a_, _conf, _nodes);
            return a_;
        }
        if (str_.endsWith(GET_LOC_VAR)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_LOC_VAR.length());
            LocalVariable locVar_ = ip_.getLocalVars().getVal(key_);
            if (resultCanBeSet()) {
                //TODO other op += -=...
                a_ = Argument.createVoid();
                setSimpleArgument(a_, _conf, _nodes);
                return a_;
            }
            a_ = new Argument();
//            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
//            a_.setArgClassName(getResultClass().getName());
//            a_.setObject(locVar_.getElement());
//            a_.setStruct(locVar_.getStruct());
            a_.setStructArgClassName(locVar_.getStruct(), getResultClass().getName());
            setSimpleArgument(a_, _conf, _nodes);
            return a_;
        }
        if (str_.endsWith(GET_INDEX)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_INDEX.length());
            LoopVariable locVar_ = ip_.getVars().getVal(key_);
            a_ = new Argument();
//            a_.setArgClass(ConstClasses.classForName(locVar_.getIndexClassName()));
//            a_.setArgClassName(getResultClass().getName());
//            a_.setObject(locVar_.getIndex());
            a_.setStructArgClassName(new Struct(locVar_.getIndex()), getResultClass().getName());
            setSimpleArgument(a_, _conf, _nodes);
            return a_;
        }
        if (str_.endsWith(GET_ATTRIBUTE)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_ATTRIBUTE.length());
            LoopVariable locVar_ = ip_.getVars().getVal(key_);
            a_ = new Argument();
//            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
//            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
//            a_.setArgClassName(getResultClass().getName());
//            a_.setObject(locVar_.getElement());
//            a_.setStruct(locVar_.getStruct());
            a_.setStructArgClassName(locVar_.getStruct(), getResultClass().getName());
            setSimpleArgument(a_, _conf, _nodes);
            return a_;
        }
        Argument arg_ = _nodes.getVal(this).getPreviousArgument();
        if (arg_.isArrayClass() && StringList.quickEq(str_, LENGTH)) {
            if (arg_.getObject() == null) {
                throw new NullObjectException(_conf.joinPages());
            }
            a_ = new Argument();
//            a_.setArgClassName(int.class.getName());
//            a_.setObject(Array.getLength(arg_.getObject()));
            a_.setStructArgClassName(new Struct(Array.getLength(arg_.getObject())), PrimitiveTypeUtil.PRIM_INT);
            setSimpleArgument(a_, _conf, _nodes);
            return a_;
        }
//        if (fieldId != null) {
//            Classes classes_ = _conf.getClasses();
//            Struct struct_ = classes_.getStaticField(fieldId);
//            a_ = new Argument();
//            a_.setArgClassName(fieldMetaInfo.getType().getName());
//            a_.setStruct(struct_);
//            setSimpleArgument(a_, _conf, _nodes);
//            return a_;
//        }
//        Class<?> cl_ = arg_.getArgClass();
        Object obj_ = arg_.getObject();
//        Field f_ = SerializeXmlObject.getDeclaredField(cl_, str_);
        if (!Modifier.isStatic(field.getModifiers()) && obj_ == null) {
            throw new NullObjectException(_conf.joinPages());
        }
        if (resultCanBeSet()) {
            a_ = Argument.createVoid();
            a_.setStruct(arg_.getStruct());
            setSimpleArgument(a_, _conf, _nodes);
            return a_;
        }
        Object res_;
        try {
            res_ = ConverterMethod.getField(field, obj_);
        } catch (ExceptionInInitializerError _0) {
            throw new ErrorCausingException(_conf.joinPages(), new Struct(_0));
        }
        a_ = new Argument();
//        a_.setArgClassName(field.getType().getName());
//        a_.setObject(res_);
        if (res_ == null) {
            a_.setStructArgClassName(new Struct(), field.getType().getName());
        } else {
            a_.setStructArgClassName(new Struct(res_), field.getType().getName());
        }
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }
    @Override
    public Argument calculateRight(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf, String _op) {
        if (isPossibleInitClass()) {
//            Argument cur_ = _nodes.getVal(this).getArgument();
//            String className_ = cur_.getArgClassName();
            String className_ = getResultClass().getName();
            if (!_conf.getClasses().isInitialized(className_)) {
                _conf.getClasses().initialize(className_);
                throw new NotInitializedClassException(className_);
            }
        }
        Argument cur_ = _nodes.getVal(this).getArgument();
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
//            a_.setArgClassName(getResultClass().getName());
//            a_.setStruct(struct_);
            a_.setStructArgClassName(struct_, getResultClass().getName());
            setSimpleArgument(a_, _conf, _nodes);
            return a_;
        }
        if (fieldId != null) {
            if (fieldMetaInfo.isStaticField()) {
                Classes classes_ = _conf.getClasses();
                Struct struct_ = classes_.getStaticField(fieldId);
                a_ = new Argument();
//                a_.setArgClassName(fieldMetaInfo.getType().getName());
//                a_.setStruct(struct_);
                a_.setStructArgClassName(struct_, fieldMetaInfo.getType().getName());
                setSimpleArgument(a_, _conf, _nodes);
                return a_;
            }
            Argument arg_ = _nodes.getVal(this).getPreviousArgument();
            Struct struct_ = arg_.getStruct().getStruct(fieldId, field);
            a_ = new Argument();
//            a_.setArgClassName(fieldMetaInfo.getType().getName());
//            a_.setStruct(struct_);
            a_.setStructArgClassName(struct_, fieldMetaInfo.getType().getName());
            setSimpleArgument(a_, _conf, _nodes);
            return a_;
        }
        if (str_.endsWith(GET_PARAM)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_PARAM.length());
            LocalVariable locVar_ = ip_.getParameters().getVal(key_);
            a_ = new Argument();
//            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
//            a_.setArgClassName(getResultClass().getName());
//            a_.setObject(locVar_.getElement());
//            a_.setStruct(locVar_.getStruct());
            a_.setStructArgClassName(locVar_.getStruct(), getResultClass().getName());
            setSimpleArgument(a_, _conf, _nodes);
            return a_;
        }
        if (str_.endsWith(GET_CATCH_VAR)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_CATCH_VAR.length());
            LocalVariable locVar_ = ip_.getCatchVars().getVal(key_);
            a_ = new Argument();
//            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
//            a_.setArgClassName(getResultClass().getName());
//            a_.setObject(locVar_.getElement());
//            a_.setStruct(locVar_.getStruct());
            a_.setStructArgClassName(locVar_.getStruct(), getResultClass().getName());
            setSimpleArgument(a_, _conf, _nodes);
            return a_;
        }
        if (str_.endsWith(GET_LOC_VAR)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_LOC_VAR.length());
            LocalVariable locVar_ = ip_.getLocalVars().getVal(key_);
            a_ = new Argument();
//            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
//            a_.setArgClassName(getResultClass().getName());
//            a_.setObject(locVar_.getElement());
//            a_.setStruct(locVar_.getStruct());
            a_.setStructArgClassName(locVar_.getStruct(), getResultClass().getName());
            setSimpleArgument(a_, _conf, _nodes);
            return a_;
        }
        if (str_.endsWith(GET_INDEX)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_INDEX.length());
            LoopVariable locVar_ = ip_.getVars().getVal(key_);
            a_ = new Argument();
//            a_.setArgClass(ConstClasses.classForName(locVar_.getIndexClassName()));
//            a_.setArgClassName(getResultClass().getName());
//            a_.setObject(locVar_.getIndex());
            a_.setStructArgClassName(new Struct(locVar_.getIndex()), getResultClass().getName());
            setSimpleArgument(a_, _conf, _nodes);
            return a_;
        }
        if (str_.endsWith(GET_ATTRIBUTE)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_ATTRIBUTE.length());
            LoopVariable locVar_ = ip_.getVars().getVal(key_);
            a_ = new Argument();
//            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
//            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
//            a_.setArgClassName(getResultClass().getName());
//            a_.setObject(locVar_.getElement());
//            a_.setStruct(locVar_.getStruct());
            a_.setStructArgClassName(locVar_.getStruct(), getResultClass().getName());
            setSimpleArgument(a_, _conf, _nodes);
            return a_;
        }
        Argument arg_ = _nodes.getVal(this).getPreviousArgument();
        if (arg_.isArrayClass() && StringList.quickEq(str_, LENGTH)) {
            if (arg_.getObject() == null) {
                throw new NullObjectException(_conf.joinPages());
            }
            a_ = new Argument();
//            a_.setArgClassName(int.class.getName());
//            a_.setObject(Array.getLength(arg_.getObject()));
            a_.setStructArgClassName(new Struct(Array.getLength(arg_.getObject())), PrimitiveTypeUtil.PRIM_INT);
            setSimpleArgument(a_, _conf, _nodes);
            return a_;
        }
        Object obj_ = arg_.getObject();
        if (!Modifier.isStatic(field.getModifiers()) && obj_ == null) {
            throw new NullObjectException(_conf.joinPages());
        }
        Object res_;
        try {
            res_ = ConverterMethod.getField(field, obj_);
        } catch (ExceptionInInitializerError _0) {
            throw new ErrorCausingException(_conf.joinPages(), new Struct(_0));
        }
        a_ = new Argument();
//        a_.setArgClassName(field.getType().getName());
//        a_.setObject(res_);
        if (res_ == null) {
            a_.setStructArgClassName(new Struct(), field.getType().getName());
        } else {
            a_.setStructArgClassName(new Struct(res_), field.getType().getName());
        }
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }
    @Override
    public Argument calculateSetting(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op) {
        PageEl ip_ = _conf.getLastPage();
        if (resultCanBeSet()) {
            String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
            String str_ = originalStr_.trim();
            int off_ = StringList.getFirstPrintableCharIndex(originalStr_);
            setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
            if (str_.endsWith(GET_LOC_VAR)) {
                String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_LOC_VAR.length());
                LocalVariable locVar_ = ip_.getLocalVars().getVal(key_);
                Argument left_ = new Argument();
//                left_.setArgClassName(locVar_.getClassName());
//                left_.setObject(locVar_.getElement());
//                left_.setStruct(locVar_.getStruct());
                left_.setStructArgClassName(locVar_.getStruct(), locVar_.getClassName());
                Argument right_ = ip_.getRightArgument();
                Argument res_;
                res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op);
                locVar_.setStruct(res_.getStruct());
                return res_;
            }
            Struct struct_ = _nodes.getVal(this).getArgument().getStruct();
            Argument right_ = ip_.getRightArgument();
            Argument res_;
            Argument left_ = new Argument();
            if (fieldId != null) {
                // TODO _nodes.getVal(this).getPreviousArgument().getStruct()
                Classes classes_ = _conf.getClasses();
                if (fieldMetaInfo.isStaticField()) {
                    Struct structField_ = classes_.getStaticField(fieldId);
//                    left_.setArgClassName(fieldMetaInfo.getType().getName());
//                    left_.setStruct(structField_);
                    left_.setStructArgClassName(structField_, fieldMetaInfo.getType().getName());
                    res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op);
                    classes_.initializeStaticField(fieldId, res_.getStruct());
                } else {
                    Argument arg_ = _nodes.getVal(this).getPreviousArgument();
                    Struct structField_ = arg_.getStruct().getStruct(fieldId, field);
//                    left_.setArgClassName(fieldMetaInfo.getType().getName());
//                    left_.setStruct(structField_);
                    left_.setStructArgClassName(structField_, fieldMetaInfo.getType().getName());
                    res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op);
                    arg_.getStruct().setStruct(fieldId, res_.getStruct());
                }
            } else {
                Object obj_ = struct_.getInstance();
                Object field_ = ConverterMethod.getField(field, obj_);
//                left_.setObject(field_);
//                left_.setArgClassName(field.getType().getName());
                if (field_ == null) {
                    left_.setStructArgClassName(new Struct(),field.getType().getName());
                } else {
                    left_.setStructArgClassName(new Struct(field_),field.getType().getName());
                }
                res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op);
                ConverterMethod.setField(field, obj_, res_.getObject());
            }
            Argument a_ = _nodes.getVal(this).getArgument();
            setSimpleArgument(a_, _conf, _nodes);
            return a_;
        }
        if (isPossibleInitClass()) {
//            Argument cur_ = _nodes.getVal(this).getArgument();
            String className_ = getResultClass().getName();
            if (!_conf.getClasses().isInitialized(className_)) {
                _conf.getClasses().initialize(className_);
                throw new NotInitializedClassException(className_);
            }
        }
        Argument cur_ = _nodes.getVal(this).getArgument();
        if (cur_ != null) {
            return cur_;
        }
        Argument a_ = new Argument();
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        if (StringList.quickEq(str_, CURRENT_INTANCE)) {
            Struct struct_ = ip_.getGlobalArgument().getStruct();
            a_ = new Argument();
//            a_.setArgClassName(getResultClass().getName());
//            a_.setStruct(struct_);
            a_.setStructArgClassName(struct_, getResultClass().getName());
            setSimpleArgument(a_, _conf, _nodes);
            return a_;
        }
        if (fieldId != null) {
            if (fieldMetaInfo.isStaticField()) {
                Classes classes_ = _conf.getClasses();
                Struct struct_ = classes_.getStaticField(fieldId);
                a_ = new Argument();
//                a_.setArgClassName(fieldMetaInfo.getType().getName());
//                a_.setStruct(struct_);
                a_.setStructArgClassName(struct_, fieldMetaInfo.getType().getName());
                setSimpleArgument(a_, _conf, _nodes);
                return a_;
            }
            Argument arg_ = _nodes.getVal(this).getPreviousArgument();
            Struct struct_ = arg_.getStruct().getStruct(fieldId, field);
            a_ = new Argument();
//            a_.setArgClassName(fieldMetaInfo.getType().getName());
//            a_.setStruct(struct_);
            a_.setStructArgClassName(struct_, fieldMetaInfo.getType().getName());
            setSimpleArgument(a_, _conf, _nodes);
            return a_;
        }
        if (str_.endsWith(GET_PARAM)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_PARAM.length());
            LocalVariable locVar_ = ip_.getParameters().getVal(key_);
            a_ = new Argument();
//            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
//            a_.setArgClassName(getResultClass().getName());
//            a_.setObject(locVar_.getElement());
//            a_.setStruct(locVar_.getStruct());
            a_.setStructArgClassName(locVar_.getStruct(), getResultClass().getName());
            setSimpleArgument(a_, _conf, _nodes);
            return a_;
        }
        if (str_.endsWith(GET_CATCH_VAR)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_CATCH_VAR.length());
            LocalVariable locVar_ = ip_.getCatchVars().getVal(key_);
            a_ = new Argument();
//            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
//            a_.setArgClassName(getResultClass().getName());
//            a_.setObject(locVar_.getElement());
//            a_.setStruct(locVar_.getStruct());
            a_.setStructArgClassName(locVar_.getStruct(), getResultClass().getName());
            setSimpleArgument(a_, _conf, _nodes);
            return a_;
        }
        if (str_.endsWith(GET_LOC_VAR)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_LOC_VAR.length());
            LocalVariable locVar_ = ip_.getLocalVars().getVal(key_);
            a_ = new Argument();
//            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
//            a_.setArgClassName(getResultClass().getName());
//            a_.setObject(locVar_.getElement());
//            a_.setStruct(locVar_.getStruct());
            a_.setStructArgClassName(locVar_.getStruct(), getResultClass().getName());
            setSimpleArgument(a_, _conf, _nodes);
            return a_;
        }
        if (str_.endsWith(GET_INDEX)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_INDEX.length());
            LoopVariable locVar_ = ip_.getVars().getVal(key_);
            a_ = new Argument();
//            a_.setArgClass(ConstClasses.classForName(locVar_.getIndexClassName()));
//            a_.setArgClassName(getResultClass().getName());
//            a_.setObject(locVar_.getIndex());
            a_.setStructArgClassName(new Struct(locVar_.getIndex()), getResultClass().getName());
            setSimpleArgument(a_, _conf, _nodes);
            return a_;
        }
        if (str_.endsWith(GET_ATTRIBUTE)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_ATTRIBUTE.length());
            LoopVariable locVar_ = ip_.getVars().getVal(key_);
            a_ = new Argument();
//            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
//            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
//            a_.setArgClassName(getResultClass().getName());
//            a_.setObject(locVar_.getElement());
//            a_.setStruct(locVar_.getStruct());
            a_.setStructArgClassName(locVar_.getStruct(), getResultClass().getName());
            setSimpleArgument(a_, _conf, _nodes);
            return a_;
        }
        Argument arg_ = _nodes.getVal(this).getPreviousArgument();
        if (arg_.isArrayClass() && StringList.quickEq(str_, LENGTH)) {
            if (arg_.getObject() == null) {
                throw new NullObjectException(_conf.joinPages());
            }
            a_ = new Argument();
//            a_.setArgClassName(int.class.getName());
//            a_.setObject(Array.getLength(arg_.getObject()));
            a_.setStructArgClassName(new Struct(Array.getLength(arg_.getObject())), PrimitiveTypeUtil.PRIM_INT);
            setSimpleArgument(a_, _conf, _nodes);
            return a_;
        }
        Object obj_ = arg_.getObject();
        if (!Modifier.isStatic(field.getModifiers()) && obj_ == null) {
            throw new NullObjectException(_conf.joinPages());
        }

        if (resultCanBeSet()) {
            obj_ = _nodes.getVal(this).getArgument().getObject();
            Argument right_ = ip_.getRightArgument();
            Argument res_;
            Object field_ = ConverterMethod.getField(field, obj_);
            Argument left_ = new Argument();
            if (field_ == null) {
                left_.setStructArgClassName(new Struct(), field.getType().getName());
            } else {
                left_.setStructArgClassName(new Struct(field_), field.getType().getName());
            }
//            left_.setObject(field_);
//            left_.setArgClassName(field.getType().getName());
            res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op);
            ConverterMethod.setField(field, obj_, res_.getObject());
            Argument curArg_ = _nodes.getVal(this).getArgument();
            setSimpleArgument(curArg_, _conf, _nodes);
            return curArg_;
        }
        Object res_;
        try {
            res_ = ConverterMethod.getField(field, obj_);
        } catch (ExceptionInInitializerError _0) {
            throw new ErrorCausingException(_conf.joinPages(), new Struct(_0));
        }
        a_ = new Argument();
//        a_.setArgClassName(field.getType().getName());
//        a_.setObject(res_);
        if (res_ == null) {
            a_.setStructArgClassName(new Struct(), field.getType().getName());
        } else {
            a_.setStructArgClassName(new Struct(res_), field.getType().getName());
        }
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }
    /**@throws ErrorCausingException
    @throws NullObjectException*/
//    @Override
//    public void calculate(CustList<OperationNode> _nodes, ContextEl _conf, Calculation _setting) {
//        if (_setting.getStep() == StepCalculation.SETTING) {
//            PageEl ip_ = _conf.getLastPage();
//            if (resultCanBeSet()) {
//                String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
//                String str_ = originalStr_.trim();
//                int off_ = StringList.getFirstPrintableCharIndex(originalStr_);
//                setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
//                if (str_.endsWith(GET_LOC_VAR)) {
//                    String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_LOC_VAR.length());
//                    LocalVariable locVar_ = ip_.getLocalVars().getVal(key_);
//                    String op_ = _setting.getOper();
//                    Argument left_ = new Argument();
////                    left_.setArgClassName(locVar_.getClassName());
////                    left_.setObject(locVar_.getElement());
//                    left_.setStructArgClassName(locVar_.getStruct(), locVar_.getClassName());
//                    Argument right_ = ip_.getRightArgument();
//                    Argument res_;
//                    res_ = NumericOperation.calculateAffect(left_, _conf, right_, op_);
//                    locVar_.setStruct(res_.getStruct());
//                    return;
//                }
//                if (fieldId != null) {
//                    Argument right_ = ip_.getRightArgument();
//                    Argument left_ = new Argument();
//                    Argument res_;
//                    String op_ = _setting.getOper();
//                    // TODO _nodes.getVal(this).getPreviousArgument().getStruct()
//                    Classes classes_ = _conf.getClasses();
//                    if (fieldMetaInfo.isStaticField()) {
//                        Struct structField_ = classes_.getStaticField(fieldId);
////                        left_.setArgClassName(fieldMetaInfo.getType().getName());
////                        left_.setStruct(structField_);
//                        left_.setStructArgClassName(structField_, fieldMetaInfo.getType().getName());
//                        res_ = NumericOperation.calculateAffect(left_, _conf, right_, op_);
//                        classes_.initializeStaticField(fieldId, res_.getStruct());
//                    } else {
////                        Struct structField_ = ip_.getGlobalArgument().getStruct().getStruct(fieldId, field);
//                        Struct structField_ = getPreviousArgument().getStruct().getStruct(fieldId, field);
////                        left_.setArgClassName(fieldMetaInfo.getType().getName());
////                        left_.setStruct(structField_);
//                        left_.setStructArgClassName(structField_, fieldMetaInfo.getType().getName());
//                        res_ = NumericOperation.calculateAffect(left_, _conf, right_, op_);
////                        ip_.getGlobalArgument().getStruct().setStruct(fieldId, res_.getStruct());
//                        getPreviousArgument().getStruct().setStruct(fieldId, res_.getStruct());
//                    }
//                    setSimpleArgument(getArgument(), _conf);
//                    return;
////                    ConverterMethod.setField(field, obj_, res_.getObject());
//                }
//                String op_ = _setting.getOper();
//                Object obj_ = getArgument().getObject();
//                Argument right_ = ip_.getRightArgument();
//                Argument res_;
//                Object field_ = ConverterMethod.getField(field, obj_);
//                Argument left_ = new Argument();
//                if (field_ == null) {
//                    left_.setStructArgClassName(new Struct(), field.getType().getName());
//                } else {
//                    left_.setStructArgClassName(new Struct(field_), field.getType().getName());
//                }
////                left_.setObject(field_);
////                left_.setArgClassName(field.getType().getName());
//                res_ = NumericOperation.calculateAffect(left_, _conf, right_, op_);
////                Argument res_;
////                res_ = NumericOperation.calculateAffect(getArgument(), _conf, right_, op_);
//                ConverterMethod.setField(field, obj_, res_.getObject());
//                setSimpleArgument(getArgument(), _conf);
//                return;
//            }
//        }
//        if (isPossibleInitClass()) {
////            Argument cur_ = getArgument();
//            String className_ = getResultClass().getName();
//            if (!_conf.getClasses().isInitialized(className_)) {
//                _conf.getClasses().initialize(className_);
//                ProcessXmlMethod.initializeClass(className_, _conf);
//                return;
//            }
//        }
//        if (getArgument() != null) {
//            return;
//        }
//        Argument a_ = new Argument();
//        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
//        String str_ = originalStr_.trim();
//        int off_ = StringList.getFirstPrintableCharIndex(originalStr_);
//        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
//        PageEl ip_ = _conf.getLastPage();
//        if (fieldId != null) {
//            if (fieldMetaInfo.isStaticField()) {
//                Classes classes_ = _conf.getClasses();
//                Struct struct_ = classes_.getStaticField(fieldId);
//                a_ = new Argument();
////                a_.setArgClassName(fieldMetaInfo.getType().getName());
////                a_.setStruct(struct_);
//                a_.setStructArgClassName(struct_, fieldMetaInfo.getType().getName());
//                setSimpleArgument(getArgument(), _conf);
//                return;
//            }
////            Struct struct_ = ip_.getGlobalArgument().getStruct().getStruct(fieldId, field);
//            Struct struct_ = getPreviousArgument().getStruct().getStruct(fieldId, field);
//            a_ = new Argument();
////            a_.setArgClassName(fieldMetaInfo.getType().getName());
////            a_.setStruct(struct_);
//            a_.setStructArgClassName(struct_, fieldMetaInfo.getType().getName());
//            setSimpleArgument(getArgument(), _conf);
//            return;
//        }
//        if (str_.endsWith(GET_PARAM)) {
//            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_PARAM.length());
//            LocalVariable locVar_ = ip_.getParameters().getVal(key_);
//            a_ = new Argument();
////            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
////            a_.setArgClassName(getResultClass().getName());
////            a_.setObject(locVar_.getElement());
//            a_.setStructArgClassName(locVar_.getStruct(), getResultClass().getName());
//            setSimpleArgument(a_, _conf);
//            return;
//        }
//        if (str_.endsWith(GET_CATCH_VAR)) {
//            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_CATCH_VAR.length());
//            LocalVariable locVar_ = ip_.getCatchVars().getVal(key_);
//            a_ = new Argument();
////            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
////            a_.setArgClassName(getResultClass().getName());
////            a_.setObject(locVar_.getElement());
////            a_.setStruct(locVar_.getStruct());
//            a_.setStructArgClassName(locVar_.getStruct(), getResultClass().getName());
//            setSimpleArgument(a_, _conf);
//            return;
//        }
//        if (str_.endsWith(GET_LOC_VAR)) {
//            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_LOC_VAR.length());
//            LocalVariable locVar_ = ip_.getLocalVars().getVal(key_);
//            if (_setting.getStep() == StepCalculation.LEFT) {
//                if (resultCanBeSet()) {
//                    //TODO other op += -=...
//                    a_ = Argument.createVoid();
//                    setSimpleArgument(a_, _conf);
//                    return;
//                }
//            }
//            a_ = new Argument();
////            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
////            a_.setArgClassName(getResultClass().getName());
////            a_.setObject(locVar_.getElement());
////            a_.setStruct(locVar_.getStruct());
//            a_.setStructArgClassName(locVar_.getStruct(), getResultClass().getName());
//            setSimpleArgument(a_, _conf);
//            return;
//        }
//        if (str_.endsWith(GET_INDEX)) {
//            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_INDEX.length());
//            LoopVariable locVar_ = ip_.getVars().getVal(key_);
//            a_ = new Argument();
////            a_.setArgClass(ConstClasses.classForName(locVar_.getIndexClassName()));
////            a_.setArgClassName(getResultClass().getName());
////            a_.setObject(locVar_.getIndex());
//            a_.setStructArgClassName(new Struct(locVar_.getIndex()), getResultClass().getName());
//            setSimpleArgument(a_, _conf);
//            return;
//        }
//        if (str_.endsWith(GET_ATTRIBUTE)) {
//            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_ATTRIBUTE.length());
//            LoopVariable locVar_ = ip_.getVars().getVal(key_);
//            a_ = new Argument();
////            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
////            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
////            a_.setArgClassName(getResultClass().getName());
////            a_.setObject(locVar_.getElement());
////            a_.setStruct(locVar_.getStruct());
//            a_.setStructArgClassName(locVar_.getStruct(), getResultClass().getName());
//            setSimpleArgument(a_, _conf);
//            return;
//        }
//        Argument arg_ = getPreviousArgument();
//        if (arg_.isArrayClass() && StringList.quickEq(str_, LENGTH)) {
//            if (arg_.getObject() == null) {
//                throw new NullObjectException(_conf.joinPages());
//            }
//            a_ = new Argument();
////            a_.setArgClassName(int.class.getName());
////            a_.setObject(Array.getLength(arg_.getObject()));
//            a_.setStructArgClassName(new Struct(Array.getLength(arg_.getObject())), PrimitiveTypeUtil.PRIM_INT);
//            setSimpleArgument(a_, _conf);
//            return;
//        }
////        if (fieldId != null) {
////            Classes classes_ = _conf.getClasses();
////            Struct struct_ = classes_.getStaticField(fieldId);
////            a_ = new Argument();
////            a_.setArgClassName(fieldMetaInfo.getType().getName());
////            a_.setStruct(struct_);
////            setSimpleArgument(getArgument(), _conf);
////            return;
////        }
////        Class<?> cl_ = arg_.getArgClass();
//        Object obj_ = arg_.getObject();
////        Field f_ = SerializeXmlObject.getDeclaredField(cl_, str_);
//        if (!Modifier.isStatic(field.getModifiers()) && obj_ == null) {
//            throw new NullObjectException(_conf.joinPages());
//        }
//        if (_setting.getStep() != StepCalculation.RIGHT) {
//            if (resultCanBeSet()) {
//                a_ = Argument.createVoid();
//                a_.setStruct(arg_.getStruct());
//                setSimpleArgument(a_, _conf);
//                return;
////                if (_setting.getStep() != StepCalculation.SETTING) {
////                    a_ = Argument.createVoid();
////                    a_.setObject(obj_);
////                    setSimpleArgument(a_, _conf);
////                    return;
////                }
////                String op_ = _setting.getOper();
////                Argument right_ = ip_.getRightArgument();
////                Argument res_;
////                Object field_ = ConverterMethod.getField(field, obj_);
////                Argument left_ = new Argument();
////                left_.setObject(field_);
////                left_.setArgClassName(field.getType().getName());
////                res_ = NumericOperation.calculateAffect(left_, _conf, right_, op_);
//////                ConverterMethod.setField(field, obj_, ip_.getRightArgument().getObject());
////                ConverterMethod.setField(field, obj_, res_.getObject());
////                setSimpleArgument(getArgument(), _conf);
////                return;
//            }
//        }
//        Object res_;
//        try {
//            res_ = ConverterMethod.getField(field, obj_);
//        } catch (ExceptionInInitializerError _0) {
//            throw new ErrorCausingException(_conf.joinPages(), new Struct(_0));
//        }
//        a_ = new Argument();
//        if (res_ == null) {
//            a_.setStructArgClassName(new Struct(), field.getType().getName());
//        } else {
//            a_.setStructArgClassName(new Struct(res_), field.getType().getName());
//        }
////        a_.setArgClassName(field.getType().getName());
////        a_.setObject(res_);
//        setSimpleArgument(a_, _conf);
//    }

    @Override
    public void calculateLeft(CustList<OperationNode> _nodes, ContextEl _conf,
            String _op) {
        if (isPossibleInitClass()) {
//            Argument cur_ = getArgument();
            String className_ = getResultClass().getName();
            if (!_conf.getClasses().isInitialized(className_)) {
                _conf.getClasses().initialize(className_);
                ProcessXmlMethod.initializeClass(className_, _conf);
                return;
            }
        }
        if (getArgument() != null) {
            return;
        }
        Argument a_ = new Argument();
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        PageEl ip_ = _conf.getLastPage();
        if (fieldId != null) {
            if (fieldMetaInfo.isStaticField()) {
                Classes classes_ = _conf.getClasses();
                Struct struct_ = classes_.getStaticField(fieldId);
                a_ = new Argument();
//                a_.setArgClassName(fieldMetaInfo.getType().getName());
//                a_.setStruct(struct_);
                a_.setStructArgClassName(struct_, fieldMetaInfo.getType().getName());
                setSimpleArgument(getArgument(), _conf);
                return;
            }
//            Struct struct_ = ip_.getGlobalArgument().getStruct().getStruct(fieldId, field);
            Struct struct_ = getPreviousArgument().getStruct().getStruct(fieldId, field);
            a_ = new Argument();
//            a_.setArgClassName(fieldMetaInfo.getType().getName());
//            a_.setStruct(struct_);
            a_.setStructArgClassName(struct_, fieldMetaInfo.getType().getName());
            setSimpleArgument(getArgument(), _conf);
            return;
        }
        if (str_.endsWith(GET_PARAM)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_PARAM.length());
            LocalVariable locVar_ = ip_.getParameters().getVal(key_);
            a_ = new Argument();
//            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
//            a_.setArgClassName(getResultClass().getName());
//            a_.setObject(locVar_.getElement());
            a_.setStructArgClassName(locVar_.getStruct(), getResultClass().getName());
            setSimpleArgument(a_, _conf);
            return;
        }
        if (str_.endsWith(GET_CATCH_VAR)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_CATCH_VAR.length());
            LocalVariable locVar_ = ip_.getCatchVars().getVal(key_);
            a_ = new Argument();
//            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
//            a_.setArgClassName(getResultClass().getName());
//            a_.setObject(locVar_.getElement());
//            a_.setStruct(locVar_.getStruct());
            a_.setStructArgClassName(locVar_.getStruct(), getResultClass().getName());
            setSimpleArgument(a_, _conf);
            return;
        }
        if (str_.endsWith(GET_LOC_VAR)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_LOC_VAR.length());
            LocalVariable locVar_ = ip_.getLocalVars().getVal(key_);
            if (resultCanBeSet()) {
                //TODO other op += -=...
                a_ = Argument.createVoid();
                setSimpleArgument(a_, _conf);
                return;
            }
            a_ = new Argument();
//            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
//            a_.setArgClassName(getResultClass().getName());
//            a_.setObject(locVar_.getElement());
//            a_.setStruct(locVar_.getStruct());
            a_.setStructArgClassName(locVar_.getStruct(), getResultClass().getName());
            setSimpleArgument(a_, _conf);
            return;
        }
        if (str_.endsWith(GET_INDEX)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_INDEX.length());
            LoopVariable locVar_ = ip_.getVars().getVal(key_);
            a_ = new Argument();
//            a_.setArgClass(ConstClasses.classForName(locVar_.getIndexClassName()));
//            a_.setArgClassName(getResultClass().getName());
//            a_.setObject(locVar_.getIndex());
            a_.setStructArgClassName(new Struct(locVar_.getIndex()), getResultClass().getName());
            setSimpleArgument(a_, _conf);
            return;
        }
        if (str_.endsWith(GET_ATTRIBUTE)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_ATTRIBUTE.length());
            LoopVariable locVar_ = ip_.getVars().getVal(key_);
            a_ = new Argument();
//            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
//            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
//            a_.setArgClassName(getResultClass().getName());
//            a_.setObject(locVar_.getElement());
//            a_.setStruct(locVar_.getStruct());
            a_.setStructArgClassName(locVar_.getStruct(), getResultClass().getName());
            setSimpleArgument(a_, _conf);
            return;
        }
        Argument arg_ = getPreviousArgument();
        if (arg_.isArrayClass() && StringList.quickEq(str_, LENGTH)) {
            if (arg_.getObject() == null) {
                throw new NullObjectException(_conf.joinPages());
            }
            a_ = new Argument();
//            a_.setArgClassName(int.class.getName());
//            a_.setObject(Array.getLength(arg_.getObject()));
            a_.setStructArgClassName(new Struct(Array.getLength(arg_.getObject())), PrimitiveTypeUtil.PRIM_INT);
            setSimpleArgument(a_, _conf);
            return;
        }
        Object obj_ = arg_.getObject();
        if (!Modifier.isStatic(field.getModifiers()) && obj_ == null) {
            throw new NullObjectException(_conf.joinPages());
        }
        if (resultCanBeSet()) {
            a_ = Argument.createVoid();
            a_.setStruct(arg_.getStruct());
            setSimpleArgument(a_, _conf);
            return;
        }
        Object res_;
        try {
            res_ = ConverterMethod.getField(field, obj_);
        } catch (ExceptionInInitializerError _0) {
            throw new ErrorCausingException(_conf.joinPages(), new Struct(_0));
        }
        a_ = new Argument();
        String fieldTypeName_ = field.getType().getName();
        if (ConstClasses.getPrimitiveClass(fieldTypeName_) != null) {
            fieldTypeName_ = PrimitiveTypeUtil.PRIM+fieldTypeName_;
        }
        if (res_ == null) {
            a_.setStructArgClassName(new Struct(), fieldTypeName_);
        } else {
            a_.setStructArgClassName(new Struct(res_), fieldTypeName_);
        }
//        a_.setArgClassName(field.getType().getName());
//        a_.setObject(res_);
        setSimpleArgument(a_, _conf);
    }

    @Override
    public void calculateRight(CustList<OperationNode> _nodes, ContextEl _conf,
            String _op) {
        if (isPossibleInitClass()) {
//            Argument cur_ = getArgument();
            String className_ = getResultClass().getName();
            if (!_conf.getClasses().isInitialized(className_)) {
                _conf.getClasses().initialize(className_);
                ProcessXmlMethod.initializeClass(className_, _conf);
                return;
            }
        }
        if (getArgument() != null) {
            return;
        }
        Argument a_ = new Argument();
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        PageEl ip_ = _conf.getLastPage();
        if (fieldId != null) {
            if (fieldMetaInfo.isStaticField()) {
                Classes classes_ = _conf.getClasses();
                Struct struct_ = classes_.getStaticField(fieldId);
                a_ = new Argument();
//                a_.setArgClassName(fieldMetaInfo.getType().getName());
//                a_.setStruct(struct_);
                a_.setStructArgClassName(struct_, fieldMetaInfo.getType().getName());
                setSimpleArgument(getArgument(), _conf);
                return;
            }
//            Struct struct_ = ip_.getGlobalArgument().getStruct().getStruct(fieldId, field);
            Struct struct_ = getPreviousArgument().getStruct().getStruct(fieldId, field);
            a_ = new Argument();
//            a_.setArgClassName(fieldMetaInfo.getType().getName());
//            a_.setStruct(struct_);
            a_.setStructArgClassName(struct_, fieldMetaInfo.getType().getName());
            setSimpleArgument(getArgument(), _conf);
            return;
        }
        if (str_.endsWith(GET_PARAM)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_PARAM.length());
            LocalVariable locVar_ = ip_.getParameters().getVal(key_);
            a_ = new Argument();
//            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
//            a_.setArgClassName(getResultClass().getName());
//            a_.setObject(locVar_.getElement());
            a_.setStructArgClassName(locVar_.getStruct(), getResultClass().getName());
            setSimpleArgument(a_, _conf);
            return;
        }
        if (str_.endsWith(GET_CATCH_VAR)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_CATCH_VAR.length());
            LocalVariable locVar_ = ip_.getCatchVars().getVal(key_);
            a_ = new Argument();
//            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
//            a_.setArgClassName(getResultClass().getName());
//            a_.setObject(locVar_.getElement());
//            a_.setStruct(locVar_.getStruct());
            a_.setStructArgClassName(locVar_.getStruct(), getResultClass().getName());
            setSimpleArgument(a_, _conf);
            return;
        }
        if (str_.endsWith(GET_LOC_VAR)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_LOC_VAR.length());
            LocalVariable locVar_ = ip_.getLocalVars().getVal(key_);
            a_ = new Argument();
//            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
//            a_.setArgClassName(getResultClass().getName());
//            a_.setObject(locVar_.getElement());
//            a_.setStruct(locVar_.getStruct());
            a_.setStructArgClassName(locVar_.getStruct(), getResultClass().getName());
            setSimpleArgument(a_, _conf);
            return;
        }
        if (str_.endsWith(GET_INDEX)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_INDEX.length());
            LoopVariable locVar_ = ip_.getVars().getVal(key_);
            a_ = new Argument();
//            a_.setArgClass(ConstClasses.classForName(locVar_.getIndexClassName()));
//            a_.setArgClassName(getResultClass().getName());
//            a_.setObject(locVar_.getIndex());
            a_.setStructArgClassName(new Struct(locVar_.getIndex()), getResultClass().getName());
            setSimpleArgument(a_, _conf);
            return;
        }
        if (str_.endsWith(GET_ATTRIBUTE)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_ATTRIBUTE.length());
            LoopVariable locVar_ = ip_.getVars().getVal(key_);
            a_ = new Argument();
//            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
//            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
//            a_.setArgClassName(getResultClass().getName());
//            a_.setObject(locVar_.getElement());
//            a_.setStruct(locVar_.getStruct());
            a_.setStructArgClassName(locVar_.getStruct(), getResultClass().getName());
            setSimpleArgument(a_, _conf);
            return;
        }
        Argument arg_ = getPreviousArgument();
        if (arg_.isArrayClass() && StringList.quickEq(str_, LENGTH)) {
            if (arg_.getObject() == null) {
                throw new NullObjectException(_conf.joinPages());
            }
            a_ = new Argument();
//            a_.setArgClassName(int.class.getName());
//            a_.setObject(Array.getLength(arg_.getObject()));
            a_.setStructArgClassName(new Struct(Array.getLength(arg_.getObject())), PrimitiveTypeUtil.PRIM_INT);
            setSimpleArgument(a_, _conf);
            return;
        }
        Object obj_ = arg_.getObject();
        if (!Modifier.isStatic(field.getModifiers()) && obj_ == null) {
            throw new NullObjectException(_conf.joinPages());
        }
        Object res_;
        try {
            res_ = ConverterMethod.getField(field, obj_);
        } catch (ExceptionInInitializerError _0) {
            throw new ErrorCausingException(_conf.joinPages(), new Struct(_0));
        }
        a_ = new Argument();
        String fieldTypeName_ = field.getType().getName();
        if (ConstClasses.getPrimitiveClass(fieldTypeName_) != null) {
            fieldTypeName_ = PrimitiveTypeUtil.PRIM+fieldTypeName_;
        }
        if (res_ == null) {
            a_.setStructArgClassName(new Struct(), fieldTypeName_);
        } else {
            a_.setStructArgClassName(new Struct(res_), fieldTypeName_);
        }
//        a_.setArgClassName(field.getType().getName());
//        a_.setObject(res_);
        setSimpleArgument(a_, _conf);
    }

    @Override
    public void calculateSetting(CustList<OperationNode> _nodes,
            ContextEl _conf, String _op) {
        PageEl ip_ = _conf.getLastPage();
        if (resultCanBeSet()) {
            String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
            String str_ = originalStr_.trim();
            int off_ = StringList.getFirstPrintableCharIndex(originalStr_);
            setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
            if (str_.endsWith(GET_LOC_VAR)) {
                String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_LOC_VAR.length());
                LocalVariable locVar_ = ip_.getLocalVars().getVal(key_);
//                String op_ = _setting.getOper();
                Argument left_ = new Argument();
//                left_.setArgClassName(locVar_.getClassName());
//                left_.setObject(locVar_.getElement());
                left_.setStructArgClassName(locVar_.getStruct(), locVar_.getClassName());
                Argument right_ = ip_.getRightArgument();
                Argument res_;
                res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op);
                locVar_.setStruct(res_.getStruct());
                return;
            }
            if (fieldId != null) {
                Argument right_ = ip_.getRightArgument();
                Argument left_ = new Argument();
                Argument res_;
//                String op_ = _setting.getOper();
                Classes classes_ = _conf.getClasses();
                if (fieldMetaInfo.isStaticField()) {
                    Struct structField_ = classes_.getStaticField(fieldId);
//                    left_.setArgClassName(fieldMetaInfo.getType().getName());
//                    left_.setStruct(structField_);
                    left_.setStructArgClassName(structField_, fieldMetaInfo.getType().getName());
                    res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op);
                    classes_.initializeStaticField(fieldId, res_.getStruct());
                } else {
//                    Struct structField_ = ip_.getGlobalArgument().getStruct().getStruct(fieldId, field);
                    Struct structField_ = getPreviousArgument().getStruct().getStruct(fieldId, field);
//                    left_.setArgClassName(fieldMetaInfo.getType().getName());
//                    left_.setStruct(structField_);
                    left_.setStructArgClassName(structField_, fieldMetaInfo.getType().getName());
                    res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op);
//                    ip_.getGlobalArgument().getStruct().setStruct(fieldId, res_.getStruct());
                    getPreviousArgument().getStruct().setStruct(fieldId, res_.getStruct());
                }
                setSimpleArgument(getArgument(), _conf);
                return;
            }
//            String op_ = _setting.getOper();
            Object obj_ = getArgument().getObject();
            Argument right_ = ip_.getRightArgument();
            Argument res_;
            Object field_ = ConverterMethod.getField(field, obj_);
            Argument left_ = new Argument();
            if (field_ == null) {
                left_.setStructArgClassName(new Struct(), field.getType().getName());
            } else {
                left_.setStructArgClassName(new Struct(field_), field.getType().getName());
            }
//            left_.setObject(field_);
//            left_.setArgClassName(field.getType().getName());
            res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op);
//            Argument res_;
//            res_ = NumericOperation.calculateAffect(getArgument(), _conf, right_, op_);
            ConverterMethod.setField(field, obj_, res_.getObject());
            setSimpleArgument(getArgument(), _conf);
            return;
        }
        if (isPossibleInitClass()) {
//            Argument cur_ = getArgument();
            String className_ = getResultClass().getName();
            if (!_conf.getClasses().isInitialized(className_)) {
                _conf.getClasses().initialize(className_);
                ProcessXmlMethod.initializeClass(className_, _conf);
                return;
            }
        }
        if (getArgument() != null) {
            return;
        }
        Argument a_ = new Argument();
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        if (fieldId != null) {
            if (fieldMetaInfo.isStaticField()) {
                Classes classes_ = _conf.getClasses();
                Struct struct_ = classes_.getStaticField(fieldId);
                a_ = new Argument();
//                a_.setArgClassName(fieldMetaInfo.getType().getName());
//                a_.setStruct(struct_);
                a_.setStructArgClassName(struct_, fieldMetaInfo.getType().getName());
                setSimpleArgument(getArgument(), _conf);
                return;
            }
//            Struct struct_ = ip_.getGlobalArgument().getStruct().getStruct(fieldId, field);
            Struct struct_ = getPreviousArgument().getStruct().getStruct(fieldId, field);
            a_ = new Argument();
//            a_.setArgClassName(fieldMetaInfo.getType().getName());
//            a_.setStruct(struct_);
            a_.setStructArgClassName(struct_, fieldMetaInfo.getType().getName());
            setSimpleArgument(getArgument(), _conf);
            return;
        }
        if (str_.endsWith(GET_PARAM)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_PARAM.length());
            LocalVariable locVar_ = ip_.getParameters().getVal(key_);
            a_ = new Argument();
//            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
//            a_.setArgClassName(getResultClass().getName());
//            a_.setObject(locVar_.getElement());
            a_.setStructArgClassName(locVar_.getStruct(), getResultClass().getName());
            setSimpleArgument(a_, _conf);
            return;
        }
        if (str_.endsWith(GET_CATCH_VAR)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_CATCH_VAR.length());
            LocalVariable locVar_ = ip_.getCatchVars().getVal(key_);
            a_ = new Argument();
//            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
//            a_.setArgClassName(getResultClass().getName());
//            a_.setObject(locVar_.getElement());
//            a_.setStruct(locVar_.getStruct());
            a_.setStructArgClassName(locVar_.getStruct(), getResultClass().getName());
            setSimpleArgument(a_, _conf);
            return;
        }
        if (str_.endsWith(GET_LOC_VAR)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_LOC_VAR.length());
            LocalVariable locVar_ = ip_.getLocalVars().getVal(key_);
            a_ = new Argument();
//            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
//            a_.setArgClassName(getResultClass().getName());
//            a_.setObject(locVar_.getElement());
//            a_.setStruct(locVar_.getStruct());
            a_.setStructArgClassName(locVar_.getStruct(), getResultClass().getName());
            setSimpleArgument(a_, _conf);
            return;
        }
        if (str_.endsWith(GET_INDEX)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_INDEX.length());
            LoopVariable locVar_ = ip_.getVars().getVal(key_);
            a_ = new Argument();
//            a_.setArgClass(ConstClasses.classForName(locVar_.getIndexClassName()));
//            a_.setArgClassName(getResultClass().getName());
//            a_.setObject(locVar_.getIndex());
            a_.setStructArgClassName(new Struct(locVar_.getIndex()), getResultClass().getName());
            setSimpleArgument(a_, _conf);
            return;
        }
        if (str_.endsWith(GET_ATTRIBUTE)) {
            String key_ = str_.substring(CustList.FIRST_INDEX, str_.length() - GET_ATTRIBUTE.length());
            LoopVariable locVar_ = ip_.getVars().getVal(key_);
            a_ = new Argument();
//            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
//            a_.setArgClass(ConstClasses.classForName(locVar_.getClassName()));
//            a_.setArgClassName(getResultClass().getName());
//            a_.setObject(locVar_.getElement());
//            a_.setStruct(locVar_.getStruct());
            a_.setStructArgClassName(locVar_.getStruct(), getResultClass().getName());
            setSimpleArgument(a_, _conf);
            return;
        }
        Argument arg_ = getPreviousArgument();
        if (arg_.isArrayClass() && StringList.quickEq(str_, LENGTH)) {
            if (arg_.getObject() == null) {
                throw new NullObjectException(_conf.joinPages());
            }
            a_ = new Argument();
//            a_.setArgClassName(int.class.getName());
//            a_.setObject(Array.getLength(arg_.getObject()));
            a_.setStructArgClassName(new Struct(Array.getLength(arg_.getObject())), PrimitiveTypeUtil.PRIM_INT);
            setSimpleArgument(a_, _conf);
            return;
        }
        Object obj_ = arg_.getObject();
//        Field f_ = SerializeXmlObject.getDeclaredField(cl_, str_);
        if (!Modifier.isStatic(field.getModifiers()) && obj_ == null) {
            throw new NullObjectException(_conf.joinPages());
        }
        if (resultCanBeSet()) {
            a_ = Argument.createVoid();
            a_.setStruct(arg_.getStruct());
            setSimpleArgument(a_, _conf);
            return;
        }
        Object res_;
        try {
            res_ = ConverterMethod.getField(field, obj_);
        } catch (ExceptionInInitializerError _0) {
            throw new ErrorCausingException(_conf.joinPages(), new Struct(_0));
        }
        a_ = new Argument();
        String fieldTypeName_ = field.getType().getName();
        if (ConstClasses.getPrimitiveClass(fieldTypeName_) != null) {
            fieldTypeName_ = PrimitiveTypeUtil.PRIM+fieldTypeName_;
        }
        if (res_ == null) {
            a_.setStructArgClassName(new Struct(), fieldTypeName_);
        } else {
            a_.setStructArgClassName(new Struct(res_), fieldTypeName_);
        }
//        a_.setArgClassName(field.getType().getName());
//        a_.setObject(res_);
        setSimpleArgument(a_, _conf);
    }

    private void analyzeCalculate(boolean _noParentCheck, ContextEl _cont) {
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _cont);
        if (str_.isEmpty()) {
            throw new EmptyPartException(_cont.joinPages());
        }
        if (_noParentCheck && !checkLater()) {
            if (getParent() == null) {
                setRelativeOffsetPossibleLastPage(getIndexInEl(), _cont);
                throw new SettingMemberException(_cont.joinPages());
            }
        }
        if (isVararg()) {
            str_ = str_.substring(CustList.SECOND_INDEX);
            str_ = StringList.removeAllSpaces(str_);
            Argument a_ = new Argument();
            checkExist(_cont, str_, true, false, 0);
            argClassName = str_;
            a_.setArgClassName(str_);
            setSimpleArgument(a_, false);
            return;
        }
        Argument a_ = new Argument();
        if (StringList.quickEq(str_, TRUE_STRING)) {
            argClassName = PrimitiveTypeUtil.PRIM_BOOLEAN;
            a_.setArgClassName(PrimitiveTypeUtil.PRIM_BOOLEAN);
            a_.setObject(true);
            setSimpleArgument(a_, false);
            return;
        }
        if (StringList.quickEq(str_, FALSE_STRING)) {
            argClassName = PrimitiveTypeUtil.PRIM_BOOLEAN;
            a_.setArgClassName(PrimitiveTypeUtil.PRIM_BOOLEAN);
            a_.setObject(false);
            setSimpleArgument(a_, false);
            return;
        }
        if (StringList.quickEq(str_, NULL_REF_STRING)) {
            argClassName = Object.class.getName();
            a_.setArgClassName(Object.class.getName());
            setSimpleArgument(a_, false);
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
            a_.setArgClassName(String.class.getName());
            a_.setObject(strBuilder_.toString());
            setSimpleArgument(a_, false);
            return;
        }
        if (str_.startsWith(String.valueOf(DELIMITER_CHAR))) {
            if (str_.charAt(str_.length()-1) == CHAR_UPP_SUFFIX) {
                argClassName = Character.class.getName();
                a_.setArgClassName(Character.class.getName());
            } else if (str_.charAt(str_.length()-1) == CHAR_LOW_SUFFIX) {
                argClassName = PrimitiveTypeUtil.PRIM_CHAR;
                a_.setArgClassName(PrimitiveTypeUtil.PRIM_CHAR);
            } else {
                argClassName = PrimitiveTypeUtil.PRIM_CHAR;
                a_.setArgClassName(PrimitiveTypeUtil.PRIM_CHAR);
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
            setSimpleArgument(a_, false);
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
            Classes classes_ = _cont.getClasses();
            ClassMetaInfo custClass_ = null;
            if (classes_ != null) {
                custClass_ = classes_.getClassMetaInfo(classStr_);
            }
            if (custClass_ == null) {
                checkExist(_cont, classStr_, false, false, 0);
            } else {
                //TODO exclude primitive
                possibleInitClass = true;
            }
            a_ = new Argument();
            argClassName = classStr_;
            a_.setArgClassName(classStr_);
            setSimpleArgument(a_, false);
            return;
        }
        try {
            str_ = StringList.removeAllSpaces(str_);
            argClassName = Argument.getArgClassNameOf(str_);
            Argument arg_ = Argument.numberToArgument(str_);
            setSimpleArgument(arg_, false);
        } catch (RuntimeException _0) {
        }
    }
    private boolean checkLater() {
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        if (isVararg()) {
            return false;
        }
        if (StringList.quickEq(str_, TRUE_STRING)) {
            return false;
        }
        if (StringList.quickEq(str_, FALSE_STRING)) {
            return false;
        }
        if (StringList.quickEq(str_, NULL_REF_STRING)) {
            return false;
        }
        if (str_.startsWith(String.valueOf(DELIMITER_STRING))) {
            return false;
        }
        if (str_.startsWith(String.valueOf(DELIMITER_CHAR))) {
            return false;
        }
        StringList sepWords_ = StringList.getWordsSeparators(str_);
        if (StringList.quickEq(sepWords_.get(CustList.SECOND_INDEX), STATIC_ACCESS)) {
            return false;
        }
        try {
            str_ = StringList.removeAllSpaces(str_);
            Argument.numberToArgument(str_);
            return false;
        } catch (RuntimeException _0) {
        }
        return true;
    }
//    private void analyzeCalculate(Calculation _setting, ContextEl _cont) {
//        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
//        String str_ = originalStr_.trim();
//        int off_ = StringList.getFirstPrintableCharIndex(originalStr_);
//        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _cont);
//        if (isVararg()) {
//            if (_setting.getStep()  == StepCalculation.LEFT && getParent() == null) {
//                setRelativeOffsetPossibleLastPage(getIndexInEl(), _cont);
//                throw new SettingMemberException(_cont.joinPages());
//            }
//            str_ = str_.substring(CustList.SECOND_INDEX);
//            str_ = StringList.removeAllSpaces(str_);
////            Classes classes_ = _cont.getClasses();
////            ClassMetaInfo custClass_ = null;
////            if (classes_ != null) {
////                custClass_ = classes_.getClassMetaInfo(str_);
////            }
//            Argument a_ = new Argument();
//            checkExist(_cont, str_, true, false, 0);
//            a_.setArgClassName(str_);
//            argClassName = str_;
//            setSimpleArgument(a_, false);
//            return;
//        }
////        if (isFirstOptArg()) {
////            str_ = str_.substring(CustList.FIRST_INDEX, str_.indexOf(FIRST_VAR_ARG)).trim();
////        }
//        if (str_.isEmpty()) {
//            throw new EmptyPartException(_cont.joinPages());
//        }
//        Argument a_ = new Argument();
//        if (StringList.quickEq(str_, TRUE_STRING)) {
//            if (_setting.getStep()  == StepCalculation.LEFT && getParent() == null) {
//                setRelativeOffsetPossibleLastPage(getIndexInEl(), _cont);
//                throw new SettingMemberException(_cont.joinPages());
//            }
//            argClassName = PrimitiveTypeUtil.PRIM_BOOLEAN;
//            a_.setArgClassName(PrimitiveTypeUtil.PRIM_BOOLEAN);
//            a_.setObject(true);
//            setSimpleArgument(a_, false);
//            return;
//        }
//        if (StringList.quickEq(str_, FALSE_STRING)) {
//            if (_setting.getStep()  == StepCalculation.LEFT && getParent() == null) {
//                setRelativeOffsetPossibleLastPage(getIndexInEl(), _cont);
//                throw new SettingMemberException(_cont.joinPages());
//            }
//            argClassName = PrimitiveTypeUtil.PRIM_BOOLEAN;
//            a_.setArgClassName(PrimitiveTypeUtil.PRIM_BOOLEAN);
//            a_.setObject(false);
//            setSimpleArgument(a_, false);
//            return;
//        }
//        if (StringList.quickEq(str_, NULL_REF_STRING)) {
//            if (_setting.getStep()  == StepCalculation.LEFT && getParent() == null) {
//                setRelativeOffsetPossibleLastPage(getIndexInEl(), _cont);
//                throw new SettingMemberException(_cont.joinPages());
//            }
//            argClassName = Object.class.getName();
//            a_.setArgClassName(Object.class.getName());
//            setSimpleArgument(a_, false);
//            return;
//        }
//        if (str_.startsWith(String.valueOf(DELIMITER_STRING))) {
//            if (_setting.getStep()  == StepCalculation.LEFT && getParent() == null) {
//                setRelativeOffsetPossibleLastPage(getIndexInEl(), _cont);
//                throw new SettingMemberException(_cont.joinPages());
//            }
//            str_ = str_.substring(CustList.SECOND_INDEX, str_.lastIndexOf(DELIMITER_STRING));
//            StringBuilder strBuilder_ = new StringBuilder();
//            StringBuilder unicodeString_ = new StringBuilder();
//            int unicode_ = 0;
//            boolean escaped_ = false;
//            for (char c: str_.toCharArray()) {
//                if (escaped_) {
//                    if (unicode_ > 0) {
//                        unicodeString_.append(c);
//                        if (unicode_ < ElResolver.UNICODE_SIZE) {
//                            unicode_++;
//                        } else {
//                            unicode_ = 0;
//                            escaped_ = false;
//                            int val_ = Integer.parseInt(unicodeString_.toString(), HEX_BASE);
//                            char i_ = (char)val_;
//                            strBuilder_.append(i_);
//                        }
//                        continue;
//                    }
//                    if (c == IND_BOUND) {
//                        strBuilder_.append(BOUND);
//                        escaped_ = false;
//                        continue;
//                    }
//                    if (c == IND_LINE) {
//                        strBuilder_.append(LINE_RETURN);
//                        escaped_ = false;
//                        continue;
//                    }
//                    if (c == IND_FORM) {
//                        strBuilder_.append(FORM);
//                        escaped_ = false;
//                        continue;
//                    }
//                    if (c == IND_LINE_FEED) {
//                        strBuilder_.append(LINE_FEED);
//                        escaped_ = false;
//                        continue;
//                    }
//                    if (c == IND_TAB) {
//                        strBuilder_.append(TAB);
//                        escaped_ = false;
//                        continue;
//                    }
//                    if (c == DELIMITER_STRING) {
//                        strBuilder_.append(DELIMITER_STRING);
//                        escaped_ = false;
//                        continue;
//                    }
//                    if (c == ESCAPE_META_CHAR) {
//                        strBuilder_.append(ESCAPE_META_CHAR);
//                        escaped_ = false;
//                        continue;
//                    }
//                    unicode_ = 1;
//                    unicodeString_ = new StringBuilder();
//                    continue;
//                }
//                if (c == ESCAPE_META_CHAR) {
//                    escaped_ = true;
//                    continue;
//                }
//                strBuilder_.append(c);
//            }
//            argClassName = String.class.getName();
//            a_.setArgClassName(String.class.getName());
//            a_.setObject(strBuilder_.toString());
//            setSimpleArgument(a_, false);
//            return;
//        }
//        if (str_.startsWith(String.valueOf(DELIMITER_CHAR))) {
//            if (_setting.getStep()  == StepCalculation.LEFT && getParent() == null) {
//                setRelativeOffsetPossibleLastPage(getIndexInEl(), _cont);
//                throw new SettingMemberException(_cont.joinPages());
//            }
//            if (str_.charAt(str_.length()-1) == CHAR_UPP_SUFFIX) {
//                argClassName = Character.class.getName();
//                a_.setArgClassName(Character.class.getName());
//            } else if (str_.charAt(str_.length()-1) == CHAR_LOW_SUFFIX) {
//                argClassName = PrimitiveTypeUtil.PRIM_CHAR;
//                a_.setArgClassName(PrimitiveTypeUtil.PRIM_CHAR);
//            } else {
//                argClassName = PrimitiveTypeUtil.PRIM_CHAR;
//                a_.setArgClassName(PrimitiveTypeUtil.PRIM_CHAR);
//            }
//            str_ = str_.substring(CustList.SECOND_INDEX, str_.lastIndexOf(DELIMITER_CHAR));
//            StringBuilder strBuilder_ = new StringBuilder();
//            StringBuilder unicodeString_ = new StringBuilder();
//            int unicode_ = 0;
//            boolean escaped_ = false;
//            for (char c: str_.toCharArray()) {
//                if (escaped_) {
//                    if (unicode_ > 0) {
//                        unicodeString_.append(c);
//                        if (unicode_ < ElResolver.UNICODE_SIZE) {
//                            unicode_++;
//                        } else {
//                            unicode_ = 0;
//                            escaped_ = false;
//                            int val_ = Integer.parseInt(unicodeString_.toString(), HEX_BASE);
//                            char i_ = (char)val_;
//                            strBuilder_.append(i_);
//                        }
//                        continue;
//                    }
//                    if (c == IND_BOUND) {
//                        strBuilder_.append(BOUND);
//                        escaped_ = false;
//                        continue;
//                    }
//                    if (c == IND_LINE) {
//                        strBuilder_.append(LINE_RETURN);
//                        escaped_ = false;
//                        continue;
//                    }
//                    if (c == IND_FORM) {
//                        strBuilder_.append(FORM);
//                        escaped_ = false;
//                        continue;
//                    }
//                    if (c == IND_LINE_FEED) {
//                        strBuilder_.append(LINE_FEED);
//                        escaped_ = false;
//                        continue;
//                    }
//                    if (c == IND_TAB) {
//                        strBuilder_.append(TAB);
//                        escaped_ = false;
//                        continue;
//                    }
//                    if (c == DELIMITER_CHAR) {
//                        strBuilder_.append(DELIMITER_CHAR);
//                        escaped_ = false;
//                        continue;
//                    }
//                    if (c == ESCAPE_META_CHAR) {
//                        strBuilder_.append(ESCAPE_META_CHAR);
//                        escaped_ = false;
//                        continue;
//                    }
//                    unicode_ = 1;
//                    unicodeString_ = new StringBuilder();
//                    continue;
//                }
//                if (c == ESCAPE_META_CHAR) {
//                    escaped_ = true;
//                    continue;
//                }
//                strBuilder_.append(c);
//            }
//            a_.setObject(strBuilder_.toString().charAt(0));
//            setSimpleArgument(a_, false);
//            return;
//        }
//        StringList sepWords_ = StringList.getWordsSeparators(str_);
//        if (StringList.quickEq(sepWords_.get(CustList.SECOND_INDEX), STATIC_ACCESS)) {
//            if (_setting.getStep() == StepCalculation.LEFT && getParent() == null) {
//                setRelativeOffsetPossibleLastPage(getIndexInEl(), _cont);
//                throw new SettingMemberException(_cont.joinPages());
//            }
//            StringList path_ = sepWords_.mid(CustList.SECOND_INDEX + 2);
//            StringBuilder class_ = new StringBuilder();
//            for (String p: path_) {
//                if (StringList.isWord(p)) {
//                    class_.append(p);
//                } else if (StringList.quickEq(p.trim(), String.valueOf(EXTERN_CLASS))){
//                    class_.append(DOT_VAR);
//                } else {
//                    class_.append(INTERN_CLASS);
//                }
//            }
//            String classStr_ = StringList.removeAllSpaces(class_.toString());
//            Classes classes_ = _cont.getClasses();
//            ClassMetaInfo custClass_ = null;
//            if (classes_ != null) {
//                custClass_ = classes_.getClassMetaInfo(classStr_);
//            }
//            if (custClass_ == null) {
//                checkExist(_cont, classStr_, false, false, 0);
////                try {
////                    ConstClasses.classForNameNotInit(classStr_);
////                } catch (RuntimeClassNotFoundException _0_) {
////                    throw new RuntimeClassNotFoundException(classStr_+RETURN_LINE+_cont.joinPages());
////                }
//            } else {
//                //TODO exclude primitive
//                possibleInitClass = true;
//            }
////            if (_cont.getClasses().getClasses().contains(new Class))
//            a_ = new Argument();
//            argClassName = classStr_;
//            a_.setArgClassName(classStr_);
//            setSimpleArgument(a_, false);
//            return;
////            str_.substring((STATIC_ACCESS+EXTERN_CLASS).length());
//        }
//        try {
//            str_ = StringList.removeAllSpaces(str_);
//            argClassName = Argument.getArgClassNameOf(str_);
//            Argument arg_ = Argument.numberToArgument(str_);
//            if (_setting.getStep() == StepCalculation.LEFT && getParent() == null) {
//                setRelativeOffsetPossibleLastPage(getIndexInEl(), _cont);
//                throw new SettingMemberException(_cont.joinPages());
//            }
//            setSimpleArgument(arg_, false);
//        } catch (SettingMemberException _0) {
//            throw _0;
//        } catch (RuntimeException _0) {
//        }
//    }

    private boolean usingPureStaticAccess() {
        if (isVararg()) {
            return false;
        }
        Argument a_ = getArgument();
        if (a_.getObject() != null) {
            return false;
        }
        String str_ = getOperations().getValues().getValue(CustList.FIRST_INDEX).trim();
        if (StringList.quickEq(str_, NULL_REF_STRING)) {
            return false;
        }
        return true;
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

}
