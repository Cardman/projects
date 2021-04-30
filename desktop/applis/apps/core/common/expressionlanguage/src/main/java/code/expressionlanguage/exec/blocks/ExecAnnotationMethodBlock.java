package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractInitPageEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecAnnotationMethodOperation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.Struct;
import code.util.BooleanList;
import code.util.CustList;
import code.util.StringList;
import code.util.core.IndexConstants;

public final class ExecAnnotationMethodBlock extends ExecNamedFunctionBlock implements
        BuildingEl {

    private final int defaultValueOffset;

    private CustList<ExecOperationNode> opValue = new CustList<ExecOperationNode>();
    public ExecAnnotationMethodBlock(String _name, boolean _varargs, AccessEnum _access, StringList _parametersNames, int _defaultValueOffset, int _offsetTrim) {
        super(false, _name, _varargs, _access, _parametersNames, _offsetTrim, new StringList(), new BooleanList());
        defaultValueOffset = _defaultValueOffset;
    }

    public Struct getDefaultArgument() {
        if (opValue.isEmpty()) {
            return null;
        }
        Argument arg_ = opValue.last().getArgument();
        if (arg_ == null) {
            return null;
        }
        return arg_.getStruct();
    }

    public MethodId getId() {
        return new MethodId(MethodAccessKind.INSTANCE, getName(), new StringList(), false);
    }

    public void setOpValue(CustList<ExecOperationNode> _opValue) {
        this.opValue = _opValue;
    }

    @Override
    public CustList<ExecOperationNode> getEl(ContextEl _context, int _indexProcess) {
        return getOpValue();
    }

    public void processEl(ContextEl _cont, StackCall _stack, AbstractInitPageEl _last) {
        _last.setGlobalOffset(defaultValueOffset);
        _last.setOffset(0);
        ExpressionLanguage el_ = _last.getCurrentEl(_cont,this, IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX);
        Argument arg_ = ExpressionLanguage.tryToCalculate(_cont,el_,0, _stack);
        setValue(_cont,arg_, _last.getBlockRootType(), _stack);
        if (_cont.callsOrException(_stack)) {
            return;
        }
        _last.clearCurrentEls();
        processMemberBlock(_last);
    }

    private void setValue(ContextEl _cont, Argument _arg, ExecRootBlock _type, StackCall _stackCall) {
        String name_ = getName();
        String idCl_ = _type.getFullName();
        String ret_ = getImportedReturnType();
        setValue(_type,idCl_,name_,ret_,_cont,_arg, _stackCall);
    }
    public static void setValue(ExecRootBlock _rootBlock, String _cl, String _name, String _returnType, ContextEl _cont, Argument _arg, StackCall _stackCall) {
        if (_cont.callsOrException(_stackCall)) {
            return;
        }
        AbstractPageEl ip_ = _stackCall.getLastPage();
        Argument gl_ = ip_.getGlobalArgument();
        Argument arg_ = ExecAnnotationMethodOperation.swallowCopy(_arg.getStruct());
        ExecTemplates.setInstanceField(_rootBlock, _returnType, gl_, arg_, _cont, _stackCall, new ClassField(_cl, _name));
    }

    public CustList<ExecOperationNode> getOpValue() {
        return opValue;
    }

}
