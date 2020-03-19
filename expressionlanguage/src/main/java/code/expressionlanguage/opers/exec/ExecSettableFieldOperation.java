package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.SettableAbstractFieldOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.FieldInfo;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class ExecSettableFieldOperation extends
        ExecAbstractFieldOperation implements ExecSettableElResult, StandardFieldOperable, ReductibleOperable {

    private boolean variable;
    private FieldInfo fieldMetaInfo;

    private boolean catString;

    private int anc;

    private int delta;
    public ExecSettableFieldOperation(SettableAbstractFieldOperation _s) {
        super(_s);
        variable = _s.isVariable();
        fieldMetaInfo = _s.getFieldMetaInfo();
        catString = _s.isCatString();
        anc = _s.getAnc();
        delta = _s.getDelta();
    }

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
        Argument previous_ = new Argument();
        if (!staticField_) {
            previous_.setStruct(PrimitiveTypeUtil.getParent(anc, className_, _previous.getStruct(), _conf));
        }
        if (_conf.getContextEl().callsOrException()) {
            return Argument.createVoid();
        }
        String fieldType_ = fieldMetaInfo.getRealType();
        return ExecInvokingOperation.getField(className_, fieldName_, staticField_,fieldType_, previous_, _conf, off_);
    }
    
    public ClassField getFieldId() {
        return fieldMetaInfo.getClassField();
    }

    @Override
    public void tryCalculateNode(Analyzable _conf) {
        SettableAbstractFieldOperation.trySet(_conf,this,fieldMetaInfo);
    }
    @Override
    public Argument calculateSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            Argument _right) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument arg_ = getCommonSetting(previous_, _conf, _right);
        return arg_;
    }
    @Override
    public Argument calculateCompoundSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, Argument _right) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument current_ = getArgument(_nodes,this);
        Struct store_ = current_.getStruct();
        Argument arg_ = getCommonCompoundSetting(previous_, store_, _conf, _op, _right, getResultClass());
        return arg_;
    }
    @Override
    public Argument calculateSemiSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, boolean _post) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument current_ = getArgument(_nodes,this);
        Struct store_ = current_.getStruct();
        Argument arg_ = getCommonSemiSetting(previous_, store_, _conf, _op, _post);
        return arg_;
    }
    Argument getCommonSetting(Argument _previous, ExecutableCode _conf, Argument _right) {
        int off_ = getOff();
        String fieldType_ = fieldMetaInfo.getRealType();
        boolean isStatic_ = fieldMetaInfo.isStaticField();
        boolean isFinal_ = fieldMetaInfo.isFinalField();
        ClassField fieldId_ = fieldMetaInfo.getClassField();
        String className_ = fieldId_.getClassName();
        String fieldName_ = fieldId_.getFieldName();
        Argument previous_ = new Argument();
        if (!isStatic_) {
            previous_.setStruct(PrimitiveTypeUtil.getParent(anc, className_, _previous.getStruct(), _conf));
        }
        if (_conf.getContextEl().callsOrException()) {
            return Argument.createVoid();
        }
        //Come from code directly so constant static fields can be initialized here
        return ExecInvokingOperation.setField(className_, fieldName_, isStatic_, isFinal_, false, fieldType_, previous_, _right, _conf, off_);
    }
    Argument getCommonCompoundSetting(Argument _previous, Struct _store, ExecutableCode _conf, String _op, Argument _right, ClassArgumentMatching _arg) {
        int off_ = getOff();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        Argument left_ = new Argument();
        Argument res_;

        left_.setStruct(_store);
        res_ = ExecNumericOperation.calculateAffect(left_, _conf, _right, _op, catString, _arg);
        if (_conf.getContextEl().callsOrException()) {
            return res_;
        }
        return getCommonSetting(_previous,_conf,res_);
    }
    Argument getCommonSemiSetting(Argument _previous, Struct _store, ExecutableCode _conf, String _op, boolean _post) {
        int off_ = getOff();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        Argument left_ = new Argument();
        Argument res_;

        left_.setStruct(_store);
        ClassArgumentMatching cl_ = getResultClass();
        res_ = ExecNumericOperation.calculateIncrDecr(left_, _conf, _op, cl_);
        getCommonSetting(_previous,_conf,res_);
        return ExecSemiAffectationOperation.getPrePost(_post, left_, res_);
    }

    @Override
    public Argument endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right) {
        return endCalculate(_conf, _nodes, false, null, _right);
    }
    @Override
    public Argument endCalculate(ContextEl _conf,
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, boolean _post,
            Argument _stored, Argument _right) {
        int off_ = getOff();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        Argument prev_ = Argument.createVoid();
        if (!fieldMetaInfo.isStaticField()) {
            prev_ = getPreviousArg(this, _nodes, _conf);
        }
        getCommonSetting(prev_,_conf,_right);
        Argument a_ = ExecSemiAffectationOperation.getPrePost(_post, _stored, _right);
        return a_;
    }

    public int getDelta() {
        return delta;
    }
}
