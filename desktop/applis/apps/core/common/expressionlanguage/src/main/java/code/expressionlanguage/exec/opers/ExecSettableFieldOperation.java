package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultSetOffset;
import code.expressionlanguage.exec.StackCall;
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

    private final ExecSettableOperationContent settableFieldContent;

    private final ExecRootBlock rootBlock;

    public ExecSettableFieldOperation(ExecRootBlock _rootBlock, ExecOperationContent _opCont, ExecFieldOperationContent _fieldCont, ExecSettableOperationContent _setFieldCont) {
        super(_opCont, _fieldCont);
        settableFieldContent = _setFieldCont;
        rootBlock = _rootBlock;
    }

    public boolean resultCanBeSet() {
        return settableFieldContent.isVariable();
    }
    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        Argument arg_ = getCommonArgument(previous_, _conf, _stack);
        if (resultCanBeSet()) {
            setQuickNoConvertSimpleArgument(arg_, _conf, _nodes, _stack);
        } else {
            setSimpleArgument(arg_, _conf, _nodes, _stack);
        }
    }

    Argument getCommonArgument(Argument _previous, ContextEl _conf, StackCall _stackCall) {
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
            previous_ = new Argument(ExecTemplates.getParent(settableFieldContent.getAnc(), className_, _previous.getStruct(), _conf, _stackCall));
        } else {
            previous_ = new Argument();
        }
        if (_conf.callsOrException(_stackCall)) {
            return Argument.createVoid();
        }
        String fieldType_ = settableFieldContent.getRealType();
        return ExecTemplates.getField(new DefaultSetOffset(),_conf.getExiting(),className_, fieldName_, staticField_,fieldType_, previous_, _conf, off_, _stackCall);
    }


    @Override
    public Argument calculateSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            Argument _right, StackCall _stack) {
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        return getCommonSetting(previous_, _conf, _right, _stack);
    }
    @Override
    public Argument calculateCompoundSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, Argument _right, ExecClassArgumentMatching _cl, byte _cast, StackCall _stack) {
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        Argument current_ = getArgument(_nodes,this);
        Struct store_ = current_.getStruct();
        return getCommonCompoundSetting(previous_, store_, _conf, _op, _right, _cl, _cast, _stack);
    }
    @Override
    public Argument calculateSemiSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, boolean _post, byte _cast, StackCall _stack) {
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        Argument current_ = getArgument(_nodes,this);
        Struct store_ = current_.getStruct();
        return getCommonSemiSetting(previous_, store_, _conf, _op, _post, _cast, _stack);
    }
    private Argument getCommonSetting(Argument _previous, ContextEl _conf, Argument _right, StackCall _stackCall) {
        int off_ = getOff();
        String fieldType_ = settableFieldContent.getRealType();
        boolean isStatic_ = settableFieldContent.isStaticField();
        boolean isFinal_ = settableFieldContent.isFinalField();
        ClassField fieldId_ = settableFieldContent.getClassField();
        String className_ = fieldId_.getClassName();
        String fieldName_ = fieldId_.getFieldName();
        Argument previous_;
        if (!isStatic_) {
            previous_ = new Argument(ExecTemplates.getParent(settableFieldContent.getAnc(), className_, _previous.getStruct(), _conf, _stackCall));
        } else {
            previous_ = new Argument();
        }
        if (_conf.callsOrException(_stackCall)) {
            return Argument.createVoid();
        }
        //Come from code directly so constant static fields can be initialized here
        return ExecTemplates.setField(new DefaultSetOffset(),_conf.getExiting(),rootBlock,className_, fieldName_, isStatic_, isFinal_, false, fieldType_, previous_, _right, _conf, off_, _stackCall);
    }
    private Argument getCommonCompoundSetting(Argument _previous, Struct _store, ContextEl _conf, String _op, Argument _right, ExecClassArgumentMatching _arg, byte _cast, StackCall _stackCall) {
        Argument left_ = new Argument(_store);
        Argument res_ = ExecNumericOperation.calculateAffect(left_, _conf, _right, _op, settableFieldContent.isCatString(), _arg.getNames(), _cast, _stackCall);

        if (_conf.callsOrException(_stackCall)) {
            return res_;
        }
        return getCommonSetting(_previous,_conf,res_, _stackCall);
    }
    private Argument getCommonSemiSetting(Argument _previous, Struct _store, ContextEl _conf, String _op, boolean _post, byte _cast, StackCall _stackCall) {
        Argument left_ = new Argument(_store);
        Argument res_ = ExecNumericOperation.calculateIncrDecr(left_, _op, _cast);

        getCommonSetting(_previous,_conf,res_, _stackCall);
        return ExecSemiAffectationOperation.getPrePost(_post, left_, res_);
    }

    @Override
    public Argument endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right, StackCall _stack) {
        endCalculateCommon(_conf, _nodes, _right, _stack);
        return _right;
    }
    @Override
    public Argument endCalculate(ContextEl _conf,
                                 IdMap<ExecOperationNode, ArgumentsPair> _nodes, boolean _post,
                                 Argument _stored, Argument _right, StackCall _stack) {
        endCalculateCommon(_conf, _nodes, _right, _stack);
        return ExecSemiAffectationOperation.getPrePost(_post, _stored, _right);
    }

    private void endCalculateCommon(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right, StackCall _stackCall) {
        Argument prev_ = Argument.createVoid();
        if (!settableFieldContent.isStaticField()) {
            prev_ = getPreviousArg(this, _nodes, _stackCall);
        }
        getCommonSetting(prev_,_conf,_right, _stackCall);
    }

    public ExecSettableOperationContent getSettableFieldContent() {
        return settableFieldContent;
    }

    public ExecRootBlock getRootBlock() {
        return rootBlock;
    }
}
