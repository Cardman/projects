package code.bean.nat.exec.opers;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.BeanNatLgNames;
import code.bean.nat.NatCaller;
import code.bean.nat.fwd.opers.NatExecFieldOperationContent;
import code.bean.nat.fwd.opers.NatExecSettableOperationContent;
import code.expressionlanguage.Argument;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
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
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, RendStackCall _rendStack) {
        int off_ = getOff();
        setRelOffsetPossibleLastPage(off_, _rendStack);
        _rendStack.setOffset(off_);
        Argument previous_ = getPreviousArg(this,_nodes, _rendStack);
        Argument result_;
        ClassField fieldId_ = getClassField();
        Struct default_ = previous_.getStruct();
        NatCaller callerSet_ = settableFieldContent.getField().getCallerGet();
        if (callerSet_ != null) {
            result_ = new Argument(callerSet_.re(default_, new Struct[]{}));
            Argument arg_ = new ArgumentWrapper(result_, null).getValue();
            calcArg(_nodes, arg_);
            return;
        }
        ResultErrorStd res_ = ((BeanNatCommonLgNames)_advStandards).getOtherResult(fieldId_, default_);
        result_ = new Argument(res_.getResult());
        Argument arg_ = new ArgumentWrapper(result_, null).getValue();
        calcArg(_nodes, arg_);
    }

    public Argument calculateSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, BeanLgNames _advStandards, RendStackCall _rendStack) {
        return processField(_nodes, _right,_advStandards, _rendStack);
    }

    private Argument processField(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, BeanLgNames _advStandards, RendStackCall _rendStackCall) {
        Argument prev_ = getPreviousArg(this, _nodes, _rendStackCall);
        int off_ = getOff();
        _rendStackCall.setOffset(off_);
        ClassField fieldId_ = getClassField();
        NatCaller callerSet_ = settableFieldContent.getField().getCallerSet();
        if (callerSet_ != null) {
            return new Argument(callerSet_.re(prev_.getStruct(), new Struct[]{_right.getStruct()}));
        }
        ((BeanNatLgNames)_advStandards).setOtherResult(fieldId_, prev_.getStruct(), _right.getStruct());
        return new ArgumentWrapper(_right, null).getValue();
    }

    public ClassField getClassField() {
        return settableFieldContent.getClassField();
    }

}
