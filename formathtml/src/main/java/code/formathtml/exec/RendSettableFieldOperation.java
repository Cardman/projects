package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.calls.util.NotInitializedClass;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.opers.SettableAbstractFieldOperation;
import code.expressionlanguage.opers.exec.ExecInvokingOperation;
import code.expressionlanguage.opers.exec.ExecNumericOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.FieldInfo;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.util.BeanLgNames;
import code.util.StringList;

public final class RendSettableFieldOperation extends
        RendAbstractFieldOperation implements RendSettableElResult {

    private boolean variable;
    private FieldInfo fieldMetaInfo;
    private boolean staticAccess;

    private boolean catString;

    private int anc;

    public RendSettableFieldOperation(SettableAbstractFieldOperation _s) {
        super(_s);
        variable = _s.isVariable();
        fieldMetaInfo = _s.getFieldMetaInfo();
        staticAccess = _s.isStaticAccess();
        catString = _s.isCatString();
        anc = _s.getAnc();
    }

    @Override
    public boolean resultCanBeSet() {
        return variable;
    }

    @Override
    Argument getCommonArgument(Argument _previous, ExecutableCode _conf) {
        int off_ = getOff();
        ClassField fieldId_ = fieldMetaInfo.getClassField();
        String className_ = fieldId_.getClassName();
        String fieldName_ = fieldId_.getFieldName();
        boolean staticField_ = fieldMetaInfo.isStaticField();
        if (resultCanBeSet()) {
            return Argument.createVoid();
        }
        Classes classes_ = _conf.getClasses();
        if (!classes_.isCustomType(className_)) {
            Struct default_ = _previous.getStruct();
            ResultErrorStd res_ = BeanLgNames.getField(_conf.getContextEl(), fieldId_, default_);
            Argument a_ = new Argument();
            a_.setStruct(res_.getResult());
            return a_;
        }
        Argument previous_ = new Argument();
        if (!staticField_) {
            previous_.setStruct(PrimitiveTypeUtil.getParent(anc, className_, _previous.getStruct(), _conf));
        }
        if (_conf.getContextEl().hasException()) {
            return Argument.createVoid();
        }
        return ExecInvokingOperation.getField(className_, fieldName_, staticField_, previous_, _conf, off_);
    }

    @Override
    public void calculateSetting(ExecutableCode _conf, Argument _right) {
        Argument previous_ = getPreviousArg(this,_conf);
        Argument arg_ = getCommonSetting(previous_, _conf, _right);
        NotInitializedClass statusInit_ = _conf.getContextEl().getInitClass();
        if (statusInit_ != null) {
            ProcessMethod.initializeClass(statusInit_.getClassName(), _conf.getContextEl());
            if (_conf.getContextEl().hasException()) {
                return;
            }
            arg_ = getCommonSetting(previous_, _conf, _right);
        }
        setSimpleArgument(arg_, _conf);
    }
    @Override
    public void calculateCompoundSetting(ExecutableCode _conf, String _op,
            Argument _right) {
        Argument previous_ = getPreviousArg(this,_conf);
        Argument current_ = getArgument();
        Struct store_ = current_.getStruct();
        Argument arg_ = getCommonCompoundSetting(previous_, store_, _conf, _op, _right);
        setSimpleArgument(arg_, _conf);
    }
    @Override
    public void calculateSemiSetting(ExecutableCode _conf, String _op,
            boolean _post) {
        Argument previous_ = getPreviousArg(this,_conf);
        Argument current_ = getArgument();
        Struct store_ = current_.getStruct();
        Argument arg_ = getCommonSemiSetting(previous_, store_, _conf, _op, _post);
        setSimpleArgument(arg_, _conf);
        
    }
    Argument getCommonSetting(Argument _previous, ExecutableCode _conf, Argument _right) {
        int off_ = getOff();
        String fieldType_ = fieldMetaInfo.getRealType();
        boolean isStatic_ = fieldMetaInfo.isStaticField();
        boolean isFinal_ = fieldMetaInfo.isFinalField();
        ClassField fieldId_ = fieldMetaInfo.getClassField();
        String className_ = fieldId_.getClassName();
        String fieldName_ = fieldId_.getFieldName();
        Classes classes_ = _conf.getClasses();
        if (!classes_.isCustomType(className_)) {
            BeanLgNames.setField(_conf.getContextEl(), fieldId_, _previous.getStruct(), _right.getStruct());
            return _right;
        }
        Argument previous_ = new Argument();
        if (!isStatic_) {
            previous_.setStruct(PrimitiveTypeUtil.getParent(anc, className_, _previous.getStruct(), _conf));
        }
        if (_conf.getContextEl().hasExceptionOrFailInit()) {
            return Argument.createVoid();
        }
        //Come from code directly so constant static fields can be initialized here
        return ExecInvokingOperation.setField(className_, fieldName_, isStatic_, isFinal_, false, fieldType_, previous_, _right, _conf, off_);
    }
    Argument getCommonCompoundSetting(Argument _previous, Struct _store, ExecutableCode _conf, String _op, Argument _right) {
        int off_ = getOff();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        Argument left_ = new Argument();
        Argument res_;

        String fieldType_;
        Classes classes_ = _conf.getClasses();
        ClassField fieldId_ = fieldMetaInfo.getClassField();
        String className_ = fieldId_.getClassName();
        if (fieldMetaInfo.isStaticField()) {
            fieldType_ = fieldMetaInfo.getRealType();
            left_.setStruct(_store);
            ClassArgumentMatching cl_ = new ClassArgumentMatching(fieldType_);
            res_ = RendNumericOperation.calculateAffect(left_, _conf, _right, _op, catString, cl_);
            if (_conf.getContextEl().hasExceptionOrFailInit()) {
                return res_;
            }
            classes_.initializeStaticField(fieldId_, res_.getStruct());
            Argument a_ = res_;
            return a_;
        }
        Argument previous_ = new Argument();
        previous_.setStruct(PrimitiveTypeUtil.getParent(anc, className_, _previous.getStruct(), _conf));
        left_.setStruct(_store);
        fieldType_ = _conf.getStandards().getStructClassName(_store, _conf.getContextEl());
        ClassArgumentMatching cl_ = new ClassArgumentMatching(fieldType_);
        res_ = RendNumericOperation.calculateAffect(left_, _conf, _right, _op, catString, cl_);
        if (_conf.getContextEl().hasExceptionOrFailInit()) {
            return res_;
        }
        ((FieldableStruct) previous_.getStruct()).setStruct(fieldId_, res_.getStruct());
        Argument a_ = res_;
        return a_;
    }
    Argument getCommonSemiSetting(Argument _previous, Struct _store, ExecutableCode _conf, String _op, boolean _post) {
        int off_ = getOff();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        Argument left_ = new Argument();
        Argument res_;

        String fieldType_;
        Classes classes_ = _conf.getClasses();
        ClassField fieldId_ = fieldMetaInfo.getClassField();
        String className_ = fieldId_.getClassName();
        if (fieldMetaInfo.isStaticField()) {
            fieldType_ = fieldMetaInfo.getRealType();
            left_.setStruct(_store);
            ClassArgumentMatching cl_ = new ClassArgumentMatching(fieldType_);
            res_ = ExecNumericOperation.calculateIncrDecr(left_, _conf, _op, cl_);
            classes_.initializeStaticField(fieldId_, res_.getStruct());
            return RendSemiAffectationOperation.getPrePost(_post, left_, res_);
        }
        Argument previous_ = new Argument();
        previous_.setStruct(PrimitiveTypeUtil.getParent(anc, className_, _previous.getStruct(), _conf));
        left_.setStruct(_store);
        fieldType_ = _conf.getStandards().getStructClassName(_store, _conf.getContextEl());
        ClassArgumentMatching cl_ = new ClassArgumentMatching(fieldType_);
        res_ = ExecNumericOperation.calculateIncrDecr(left_, _conf, _op, cl_);
        ((FieldableStruct) previous_.getStruct()).setStruct(fieldId_, res_.getStruct());
        return RendSemiAffectationOperation.getPrePost(_post, left_, res_);
    }

    @Override
    public Argument endCalculate(ExecutableCode _conf, Argument _right) {
        return endCalculate(_conf, false, null, _right);
    }
    @Override
    public Argument endCalculate(ExecutableCode _conf, boolean _post,
            Argument _stored, Argument _right) {
        int off_ = getOff();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        Classes classes_ = _conf.getClasses();
        ClassField fieldId_ = fieldMetaInfo.getClassField();
        String className_ = fieldId_.getClassName();
        if (fieldMetaInfo.isStaticField()) {
            classes_.initializeStaticField(fieldId_, _right.getStruct());
            Argument a_ = RendSemiAffectationOperation.getPrePost(_post, _stored, _right);
            setSimpleArgument(a_, _conf);
            return a_;
        }
        Argument previousNode_ = getPreviousArg(this,_conf);
        Argument previous_ = new Argument();
        previous_.setStruct(PrimitiveTypeUtil.getParent(anc, className_, previousNode_.getStruct(), _conf));
        ((FieldableStruct) previous_.getStruct()).setStruct(fieldId_, _right.getStruct());
        Argument a_ = RendSemiAffectationOperation.getPrePost(_post, _stored, _right);
        setSimpleArgument(a_, _conf);
        return a_;
    }
}
