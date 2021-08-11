package code.bean.nat.exec.opers;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.BeanNatLgNames;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.bean.nat.fwd.opers.NatExecFieldOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.bean.nat.fwd.opers.NatExecSettableOperationContent;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class NatSettableFieldOperation extends
        NatAbstractFieldOperation {

    private final NatExecSettableOperationContent settableFieldContent;

    public NatSettableFieldOperation(ExecOperationContent _opCont, NatExecFieldOperationContent _fieldCont, NatExecSettableOperationContent _setFieldCont) {
        super(_opCont,_fieldCont);
        settableFieldContent = _setFieldCont;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        int off_ = getOff();
        setRelOffsetPossibleLastPage(off_, _rendStack);
        _rendStack.setOffset(off_);
        Argument previous_ = getPreviousArg(this,_nodes, _rendStack);
        Argument result_;
        ClassField fieldId_ = getClassField();
        Struct default_ = previous_.getStruct();
        ResultErrorStd res_ = ((BeanNatCommonLgNames)_advStandards).getOtherResult(_context, fieldId_, default_);
        result_ = new Argument(res_.getResult());
        Argument arg_ = new ArgumentWrapper(result_, null).getValue();
        setSimpleArgument(arg_, _nodes, _context, _rendStack);
    }

    public Argument calculateSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        return processField(_nodes, _right,_advStandards,_context, _rendStack);
    }

    private Argument processField(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStackCall) {
        Argument prev_ = getPreviousArg(this, _nodes, _rendStackCall);
        int off_ = getOff();
        _rendStackCall.setOffset(off_);
        ClassField fieldId_ = getClassField();
        ((BeanNatLgNames)_advStandards).setOtherResult(_context, fieldId_, prev_.getStruct(), _right.getStruct());
        return new ArgumentWrapper(_right, null).getValue();
    }

    public ClassField getClassField() {
        return settableFieldContent.getClassField();
    }

}
