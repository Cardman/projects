package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.calls.util.CallingState;
import code.expressionlanguage.calls.util.NotInitializedClass;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.SettableAbstractFieldOperation;
import code.expressionlanguage.opers.exec.ExecInvokingOperation;
import code.expressionlanguage.opers.exec.ExecNumericOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.FieldInfo;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.util.BeanNatLgNames;
import code.util.IdMap;

public final class RendSettableFieldOperation extends
        RendAbstractFieldOperation implements RendSettableElResult {

    private boolean variable;
    private FieldInfo fieldMetaInfo;

    private boolean catString;

    private int anc;

    private ClassArgumentMatching previous;
    public RendSettableFieldOperation(SettableAbstractFieldOperation _s) {
        super(_s);
        variable = _s.isVariable();
        fieldMetaInfo = _s.getFieldMetaInfo();
        catString = _s.isCatString();
        anc = _s.getAnc();
        previous = _s.getPreviousResultClass();
    }
    public RendSettableFieldOperation(RendSettableFieldOperation _s,int _indexChild, ClassArgumentMatching _res, int _order, boolean _int) {
        super(_s.getPreviousArgument(),_indexChild,_res,_order,_int);
        variable = _s.variable;
        fieldMetaInfo = _s.getFieldMetaInfo();
        catString = _s.catString;
        anc = _s.anc;
        previous = _s.previous;
    }
    @Override
    public boolean resultCanBeSet() {
        return variable;
    }

    @Override
    Argument getCommonArgument(Argument _previous, Configuration _conf) {
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
            ResultErrorStd res_ = BeanNatLgNames.getField(_conf.getContextEl(), fieldId_, default_);
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
        String fieldType_ = fieldMetaInfo.getRealType();
        return ExecInvokingOperation.getField(className_, fieldName_, staticField_,fieldType_, previous_, _conf, off_);
    }

    @Override
    public Argument calculateSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right) {
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        Argument arg_ = getCommonSetting(previous_, _conf, _right);
        CallingState state_ = _conf.getContextEl().getCallingState();
        if (state_ instanceof NotInitializedClass) {
            NotInitializedClass statusInit_ = (NotInitializedClass) state_;
            ProcessMethod.initializeClass(statusInit_.getClassName(), _conf.getContextEl());
            if (_conf.getContextEl().hasException()) {
                return arg_;
            }
            arg_ = getCommonSetting(previous_, _conf, _right);
        }
        return arg_;
    }

    @Override
    public Argument calculateCompoundSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, Argument _right) {
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        Argument current_ = getArgument(_nodes,this);
        Struct store_ = current_.getStruct();
        return getCommonCompoundSetting(previous_, store_, _conf, _op, _right);
    }

    @Override
    public Argument calculateSemiSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, boolean _post) {
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        Argument current_ = getArgument(_nodes,this);
        Struct store_ = current_.getStruct();
        return getCommonSemiSetting(previous_, store_, _conf, _op, _post);
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
            BeanNatLgNames.setField(_conf.getContextEl(), fieldId_, _previous.getStruct(), _right.getStruct());
            return _right;
        }
        Argument previous_ = new Argument();
        if (!isStatic_) {
            previous_.setStruct(PrimitiveTypeUtil.getParent(anc, className_, _previous.getStruct(), _conf));
        }
        if (_conf.getContextEl().hasException()) {
            return Argument.createVoid();
        }
        //Come from code directly so constant static fields can be initialized here
        return ExecInvokingOperation.setField(className_, fieldName_, isStatic_, isFinal_, false, fieldType_, previous_, _right, _conf, off_);
    }
    Argument getCommonCompoundSetting(Argument _previous, Struct _store, Configuration _conf, String _op, Argument _right) {
        int off_ = getOff();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        Argument left_ = new Argument(_store);
        Argument res_;

        ClassArgumentMatching cl_ = getResultClass();
        res_ = RendNumericOperation.calculateAffect(left_, _conf, _right, _op, catString, cl_);
        return getCommonSetting(_previous,_conf,res_);
    }
    Argument getCommonSemiSetting(Argument _previous, Struct _store, Configuration _conf, String _op, boolean _post) {
        int off_ = getOff();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        Argument left_ = new Argument(_store);
        Argument res_;

        ClassArgumentMatching cl_ = getResultClass();
        res_ = ExecNumericOperation.calculateIncrDecr(left_, _conf, _op, cl_);
        getCommonSetting(_previous,_conf,res_);
        return RendSemiAffectationOperation.getPrePost(_post, left_, res_);
    }

    @Override
    public Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right) {
        return endCalculate(_nodes,_conf, false, null, _right);
    }

    @Override
    public Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, boolean _post, Argument _stored, Argument _right) {
        int off_ = getOff();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        Argument prev_ = Argument.createVoid();
        if (!fieldMetaInfo.isStaticField()) {
            prev_ = getPreviousArg(this, _nodes, _conf);
        }
        getCommonSetting(prev_,_conf,_right);
        Argument a_ = RendSemiAffectationOperation.getPrePost(_post, _stored, _right);
        return a_;
    }

    public FieldInfo getFieldMetaInfo() {
        return fieldMetaInfo;
    }

    public ClassArgumentMatching getPrevious() {
        return previous;
    }
}
