package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecNumericOperation;
import code.expressionlanguage.fwd.opers.ExecFieldOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecSettableOperationContent;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendSettableFieldOperation extends
        RendAbstractFieldOperation implements RendSettableElResult {

    private final ExecSettableOperationContent settableFieldContent;

    private final ExecRootBlock rootBlock;

    public RendSettableFieldOperation(ExecOperationContent _opCont, ExecFieldOperationContent _fieldCont, ExecSettableOperationContent _setFieldCont,ExecRootBlock _rootBlock) {
        super(_opCont,_fieldCont);
        settableFieldContent = _setFieldCont;
        rootBlock = _rootBlock;
    }
    @Override
    public boolean resultCanBeSet() {
        return settableFieldContent.isVariable();
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        Argument previous_ = getPreviousArg(this,_nodes, _rendStack);
        Argument arg_ = RendDynOperationNode.processCall(getCommonArgument(previous_, _conf, _advStandards, _context, _stack, _rendStack), _context, _stack).getValue();
        if (_context.callsOrException(_stack)) {
            return;
        }
        boolean simple_ = false;
        if (resultCanBeSet()) {
            simple_ = true;
        }
        if (simple_) {
            setQuickNoConvertSimpleArgument(arg_, _nodes, _context, _stack);
        } else {
            setSimpleArgument(arg_, _nodes, _context, _stack, _rendStack);
        }
    }

    Argument getCommonArgument(Argument _previous, Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, StackCall _stackCall, RendStackCall _rendStackCall) {
        if (resultCanBeSet()) {
            return Argument.createVoid();
        }
        return _advStandards.getCommonArgument(this,_previous,_conf, _ctx, _stackCall, _rendStackCall);
    }

    @Override
    public Argument calculateSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        Argument previous_ = getPreviousArg(this,_nodes, _rendStack);
        return RendDynOperationNode.processCall(getCommonSetting(previous_, _conf, _right, _advStandards, _context, _stack, _rendStack), _context, _stack).getValue();
    }

    @Override
    public Argument calculateCompoundSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, Argument _right, ExecClassArgumentMatching _cl, byte _cast, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        Argument previous_ = getPreviousArg(this,_nodes, _rendStack);
        Argument current_ = getArgument(_nodes,this);
        Struct store_ = current_.getStruct();
        return getCommonCompoundSetting(previous_, store_, _conf, _op, _right, _cl, _cast, _advStandards, _context, _stack, _rendStack);
    }

    @Override
    public Argument calculateSemiSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, boolean _post, Argument _store, byte _cast, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        Argument previous_ = getPreviousArg(this,_nodes, _rendStack);
        Struct store_ = _store.getStruct();
        return getCommonSemiSetting(previous_, store_, _conf, _op, _post, _cast, _advStandards, _context, _stack, _rendStack);
    }

    private Argument getCommonSetting(Argument _previous, Configuration _conf, Argument _right, BeanLgNames _advStandards, ContextEl _context, StackCall _stackCall, RendStackCall _rendStackCall) {
        return _advStandards.getCommonSetting(this,_previous,_conf,_right, _context, _stackCall, _rendStackCall);
    }
    private Argument getCommonCompoundSetting(Argument _previous, Struct _store, Configuration _conf, String _op, Argument _right, ExecClassArgumentMatching _cl, byte _cast, BeanLgNames _advStandards, ContextEl _context, StackCall _stackCall, RendStackCall _rendStackCall) {
        int off_ = getOff();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _rendStackCall);
        Argument left_ = new Argument(_store);
        Argument res_;

        res_ = RendNumericOperation.calculateAffect(left_, _right, _op, settableFieldContent.isCatString(), _cl.getNames(), _cast, _context, _stackCall);
        return getCommonSetting(_previous,_conf,res_, _advStandards, _context, _stackCall, _rendStackCall);
    }
    private Argument getCommonSemiSetting(Argument _previous, Struct _store, Configuration _conf, String _op, boolean _post, byte _cast, BeanLgNames _advStandards, ContextEl _context, StackCall _stackCall, RendStackCall _rendStackCall) {
        int off_ = getOff();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _rendStackCall);
        Argument left_ = new Argument(_store);
        Argument res_;

        res_ = ExecNumericOperation.calculateIncrDecr(left_, _op, _cast);
        getCommonSetting(_previous,_conf,res_, _advStandards, _context, _stackCall, _rendStackCall);
        return RendSemiAffectationOperation.getPrePost(_post, left_, res_);
    }

    @Override
    public Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        processField(_nodes, _conf, _right, _advStandards, _context, _stack, _rendStack);
        return _right;
    }

    @Override
    public Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, boolean _post, Argument _stored, Argument _right, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        processField(_nodes, _conf, _right, _advStandards, _context, _stack, _rendStack);
        return RendSemiAffectationOperation.getPrePost(_post, _stored, _right);
    }

    private void processField(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right, BeanLgNames _advStandards, ContextEl _context, StackCall _stackCall, RendStackCall _rendStackCall) {
        int off_ = getOff();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _rendStackCall);
        Argument prev_ = Argument.createVoid();
        if (!settableFieldContent.isStaticField()) {
            prev_ = getPreviousArg(this, _nodes, _rendStackCall);
        }
        getCommonSetting(prev_,_conf,_right, _advStandards, _context, _stackCall, _rendStackCall);
    }

    public ExecSettableOperationContent getSettableFieldContent() {
        return settableFieldContent;
    }

    public boolean isFinalField() {
        return settableFieldContent.isFinalField();
    }

    public boolean isStaticField() {
        return settableFieldContent.isStaticField();
    }

    public String getRealType() {
        return settableFieldContent.getRealType();
    }

    public ClassField getClassField() {
        return settableFieldContent.getClassField();
    }

    public int getAnc() {
        return settableFieldContent.getAnc();
    }

    public ExecRootBlock getRootBlock() {
        return rootBlock;
    }
}
