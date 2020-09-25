package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.GeneCustModifierMethod;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.FieldInitPageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.analyze.blocks.AnnotationMethodBlock;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;

public final class ExecAnnotationMethodBlock extends ExecNamedFunctionBlock implements
        GeneCustModifierMethod, WithNotEmptyEl {

    private int defaultValueOffset;

    private CustList<ExecOperationNode> opValue;
    public ExecAnnotationMethodBlock(OffsetsBlock _offset, String _name, boolean _varargs, AccessEnum _access, StringList _parametersNames, int _defaultValueOffset) {
        super(_offset, _name, _varargs, _access, _parametersNames);
        defaultValueOffset = _defaultValueOffset;
    }

    public MethodModifier getModifier() {
        return MethodModifier.ABSTRACT;
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

    @Override
    public MethodId getId() {
        return new MethodId(MethodAccessKind.INSTANCE, getName(), new StringList(), false);
    }

    @Override
    public void reduce(ContextEl _context) {
        super.reduce(_context);
        if (opValue.isEmpty()) {
            return;
        }
        ExecOperationNode r_ = opValue.last();
        opValue = ExpressionLanguage.getReducedNodes(r_);
    }

    public void setOpValue(CustList<ExecOperationNode> opValue) {
        this.opValue = opValue;
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, int _indexProcess) {
        return new ExpressionLanguage(opValue);
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        boolean in_ = false;
        if (ip_ instanceof FieldInitPageEl) {
            in_ = true;
        }
        if (in_ && !opValue.isEmpty()) {
            ip_.setGlobalOffset(defaultValueOffset);
            ip_.setOffset(0);
            ExpressionLanguage el_ = ip_.getCurrentEl(_cont,this, CustList.FIRST_INDEX, CustList.FIRST_INDEX);
            Argument arg_ = ExpressionLanguage.tryToCalculate(_cont,el_,0);
            setValue(_cont,arg_);
            if (_cont.callsOrException()) {
                return;
            }
            ip_.clearCurrentEls();
        }
        processBlock(_cont);
    }

    private void setValue(ContextEl _cont, Argument _arg) {
        String name_ = getName();
        ExecRootBlock r_ = (ExecRootBlock) getParent();
        String idCl_ = r_.getFullName();
        String ret_ = getImportedReturnType();
        setValue(r_,idCl_,name_,ret_,_cont,_arg);
    }
    public static void setValue(ExecRootBlock _rootBlock,String _cl, String _name, String _returnType,ContextEl _cont, Argument _arg) {
        if (_cont.callsOrException()) {
            return;
        }
        AbstractPageEl ip_ = _cont.getLastPage();
        Argument gl_ = ip_.getGlobalArgument();
        ExecTemplates.setInstanceField(_rootBlock,_cl, _name, _returnType, gl_, _arg, _cont);
    }

    public CustList<ExecOperationNode> getOpValue() {
        return opValue;
    }

    public void buildImportedTypes(AnnotationMethodBlock _key) {
        setImportedReturnType(_key.getImportedReturnType());
        getImportedParametersTypes().addAllElts(_key.getImportedParametersTypes());
    }

}
