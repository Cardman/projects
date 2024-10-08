package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractInitPageEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.ExecFieldTemplates;
import code.expressionlanguage.exec.inherits.ExecTypeReturn;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;
import code.util.core.BoolVal;

public final class ExecAnnotationMethodBlock extends ExecNamedFunctionBlock {

    private final int defaultValueOffset;

    private CustList<ExecOperationNode> opValue = new CustList<ExecOperationNode>();
    public ExecAnnotationMethodBlock(String _name, boolean _varargs, AccessEnum _access, StringList _parametersNames, int _defaultValueOffset) {
        super(_access, new ExecExecNamedFunctionContent(_name, new StringList(), new CustList<BoolVal>(), _parametersNames, false, _varargs));
        defaultValueOffset = _defaultValueOffset;
    }

    public Struct getDefaultArgument() {
        if (opValue.isEmpty()) {
            return null;
        }
        return opValue.last().getArgument();
    }

    public MethodId getId() {
        return new MethodId(MethodAccessKind.INSTANCE, getName(), new StringList(), false);
    }

    public void setOpValue(CustList<ExecOperationNode> _opValue) {
        this.opValue = _opValue;
    }

    public void processEl(ContextEl _cont, StackCall _stack, AbstractInitPageEl _last) {
        _last.globalOffset(defaultValueOffset);
        Struct arg_ = ExecHelperBlocks.tryToCalculate(_cont,0,_stack,getOpValue(),0, this, -1);
        if (_stack.stopAt(_cont)) {
            return;
        }
        if (_stack.getStopper().isStopAtRef(_cont,_stack)) {
            return;
        }
        setValue(_cont,arg_, _last.getBlockRootType(), _stack);
        _last.clearCurrentEls();
        ExecHelperBlocks.processMemberBlock(_stack,_last);
    }

    private void setValue(ContextEl _cont, Struct _arg, ExecRootBlock _type, StackCall _stackCall) {
        String name_ = getName();
        String idCl_ = _type.getFullName();
        String ret_ = getImportedReturnType();
        setValue(_type,idCl_,name_,ret_,_cont,_arg, _stackCall);
    }
    public static void setValue(ExecRootBlock _rootBlock, String _cl, String _name, String _returnType, ContextEl _cont, Struct _arg, StackCall _stackCall) {
        AbstractPageEl ip_ = _stackCall.getLastPage();
        Struct gl_ = ip_.getGlobalStruct();
        Struct arg_ = ExecInvokingOperation.swallowCopy(_arg);
        ExecFieldTemplates.setInstanceField(gl_, arg_, _cont, _stackCall, new ClassField(_cl, _name), new ExecTypeReturn(_rootBlock, _returnType));
    }

    public int getDefaultValueOffset() {
        return defaultValueOffset;
    }

    public CustList<ExecOperationNode> getOpValue() {
        return opValue;
    }

}
