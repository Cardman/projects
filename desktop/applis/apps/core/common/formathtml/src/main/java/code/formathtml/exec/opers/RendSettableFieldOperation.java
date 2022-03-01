package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.ExecTypeReturn;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecFieldOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecSettableOperationContent;
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
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        int off_ = getOff();
        setRelOffsetPossibleLastPage(off_, _rendStack);
        _rendStack.setOffset(off_);
        Argument previous_ = getPreviousArg(this,_nodes, _rendStack);
        Argument result_;
        if (resultCanBeSet()) {
            result_ = Argument.createVoid();
        } else {
            ClassField fieldId_ = getClassField();
            boolean staticField_ = isStaticField();
            String fieldType_ = getRealType();
            if (staticField_) {
                result_ = ExecTemplates.getStaticField(_context.getExiting(), getRootBlock(), fieldType_, _context, _rendStack.getStackCall(), fieldId_);
            } else {
                result_ = ExecTemplates.getSafeInstanceField(getAnc(), previous_, _context, _rendStack.getStackCall(), fieldId_);
            }
        }
        Argument arg_ = RendDynOperationNode.processCall(result_, _context, _rendStack).getValue();
        if (_context.callsOrException(_rendStack.getStackCall())) {
            return;
        }
        if (resultCanBeSet()) {
            setQuickNoConvertSimpleArgument(arg_, _nodes, _context, _rendStack);
        } else {
            setSimpleArgument(arg_, _nodes, _context, _rendStack);
        }
    }

    @Override
    public Argument calculateSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        return processField(_nodes, _right, _context, _rendStack);
    }

    private Argument processField(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, ContextEl _context, RendStackCall _rendStackCall) {
        if (_context.callsOrException(_rendStackCall.getStackCall())) {
            return _right;
        }
        Argument prev_ = getPreviousArg(this, _nodes, _rendStackCall);
        int off_ = getOff();
        _rendStackCall.setOffset(off_);
        String fieldType_ = getRealType();
        boolean isStatic_ = isStaticField();
        ClassField fieldId_ = getClassField();
        //Come from code directly so constant static fields can be initialized here
        Argument arg_;
        if (isStatic_) {
            arg_ = ExecTemplates.setStaticField(_context.getExiting(), getRootBlock(), fieldType_, _right, _context, _rendStackCall.getStackCall(), fieldId_);
        } else {
            arg_ = ExecTemplates.setSafeInstanceField(getAnc(), prev_, _right, _context, _rendStackCall.getStackCall(), fieldId_, new ExecTypeReturn(getRootBlock(), fieldType_));
        }
        return RendDynOperationNode.processCall(arg_,_context,_rendStackCall).getValue();
    }

    public ExecSettableOperationContent getSettableFieldContent() {
        return settableFieldContent;
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
