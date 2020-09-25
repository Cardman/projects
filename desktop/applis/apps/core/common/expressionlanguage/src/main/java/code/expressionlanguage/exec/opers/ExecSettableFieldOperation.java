package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.DefaultSetOffset;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.fwd.opers.ExecFieldOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecSettableOperationContent;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.util.IdMap;

public final class ExecSettableFieldOperation extends
        ExecAbstractFieldOperation implements ExecSettableElResult {

    private ExecSettableOperationContent settableFieldContent;

    private ExecRootBlock rootBlock;

    public ExecSettableFieldOperation(ExecRootBlock _rootBlock, ExecOperationContent _opCont, ExecFieldOperationContent _fieldCont, ExecSettableOperationContent _setFieldCont) {
        super(_opCont, _fieldCont);
        settableFieldContent = _setFieldCont;
        rootBlock = _rootBlock;
    }

    public boolean resultCanBeSet() {
        return settableFieldContent.isVariable();
    }

    @Override
    Argument getCommonArgument(Argument _previous, ContextEl _conf) {
        int off_ = getOff();
        ClassField fieldId_ = settableFieldContent.getClassField();
        String className_ = fieldId_.getClassName();
        String fieldName_ = fieldId_.getFieldName();
        boolean staticField_ = settableFieldContent.isStaticField();
        if (resultCanBeSet()) {
            return Argument.createVoid();
        }
        Argument previous_;
        if (!staticField_) {
            previous_ = new Argument(ExecTemplates.getParent(settableFieldContent.getAnc(), className_, _previous.getStruct(), _conf));
        } else {
            previous_ = new Argument();
        }
        if (_conf.callsOrException()) {
            return Argument.createVoid();
        }
        String fieldType_ = settableFieldContent.getRealType();
        return ExecTemplates.getField(new DefaultSetOffset(_conf),new DefaultExiting(_conf),className_, fieldName_, staticField_,fieldType_, previous_, _conf, off_);
    }
    
    public ClassField getFieldId() {
        return settableFieldContent.getClassField();
    }


    @Override
    public Argument calculateSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            Argument _right) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        return getCommonSetting(previous_, _conf, _right);
    }
    @Override
    public Argument calculateCompoundSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, Argument _right, ExecClassArgumentMatching _cl, byte _cast) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument current_ = getArgument(_nodes,this);
        Struct store_ = current_.getStruct();
        return getCommonCompoundSetting(previous_, store_, _conf, _op, _right, _cl, _cast);
    }
    @Override
    public Argument calculateSemiSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, boolean _post, byte _cast) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument current_ = getArgument(_nodes,this);
        Struct store_ = current_.getStruct();
        return getCommonSemiSetting(previous_, store_, _conf, _op, _post, _cast);
    }
    private Argument getCommonSetting(Argument _previous, ContextEl _conf, Argument _right) {
        int off_ = getOff();
        String fieldType_ = settableFieldContent.getRealType();
        boolean isStatic_ = settableFieldContent.isStaticField();
        boolean isFinal_ = settableFieldContent.isFinalField();
        ClassField fieldId_ = settableFieldContent.getClassField();
        String className_ = fieldId_.getClassName();
        String fieldName_ = fieldId_.getFieldName();
        Argument previous_;
        if (!isStatic_) {
            previous_ = new Argument(ExecTemplates.getParent(settableFieldContent.getAnc(), className_, _previous.getStruct(), _conf));
        } else {
            previous_ = new Argument();
        }
        if (_conf.callsOrException()) {
            return Argument.createVoid();
        }
        //Come from code directly so constant static fields can be initialized here
        return ExecTemplates.setField(new DefaultSetOffset(_conf),new DefaultExiting(_conf),rootBlock,className_, fieldName_, isStatic_, isFinal_, false, fieldType_, previous_, _right, _conf, off_);
    }
    private Argument getCommonCompoundSetting(Argument _previous, Struct _store, ContextEl _conf, String _op, Argument _right, ExecClassArgumentMatching _arg, byte _cast) {
        Argument left_ = new Argument(_store);
        Argument res_;

        res_ = ExecNumericOperation.calculateAffect(left_, _conf, _right, _op, settableFieldContent.isCatString(), _arg.getNames(), _cast);
        if (_conf.callsOrException()) {
            return res_;
        }
        return getCommonSetting(_previous,_conf,res_);
    }
    private Argument getCommonSemiSetting(Argument _previous, Struct _store, ContextEl _conf, String _op, boolean _post, byte _cast) {
        Argument left_ = new Argument(_store);
        Argument res_;

        res_ = ExecNumericOperation.calculateIncrDecr(left_, _op, _cast);
        getCommonSetting(_previous,_conf,res_);
        return ExecSemiAffectationOperation.getPrePost(_post, left_, res_);
    }

    @Override
    public Argument endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right) {
        endCalculateCommon(_conf, _nodes, _right);
        return _right;
    }
    @Override
    public Argument endCalculate(ContextEl _conf,
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, boolean _post,
            Argument _stored, Argument _right) {
        endCalculateCommon(_conf, _nodes, _right);
        return ExecSemiAffectationOperation.getPrePost(_post, _stored, _right);
    }

    private void endCalculateCommon(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right) {
        Argument prev_ = Argument.createVoid();
        if (!settableFieldContent.isStaticField()) {
            prev_ = getPreviousArg(this, _nodes, _conf);
        }
        getCommonSetting(prev_,_conf,_right);
    }

}
