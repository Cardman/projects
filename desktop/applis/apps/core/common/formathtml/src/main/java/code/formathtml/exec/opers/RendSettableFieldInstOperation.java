package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecFieldTemplates;
import code.expressionlanguage.exec.inherits.ExecTypeReturn;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecFieldOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecSettableOperationContent;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;

public final class RendSettableFieldInstOperation extends
        RendSettableFieldOperation {

    private final ExecTypeReturn pair;

    public RendSettableFieldInstOperation(ExecOperationContent _opCont, ExecFieldOperationContent _fieldCont, ExecSettableOperationContent _setFieldCont, ExecRootBlock _rootBlock) {
        super(_opCont, _fieldCont, _setFieldCont);
        pair = new ExecTypeReturn(_rootBlock,_setFieldCont.getRealType());
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        offset(_rendStack);
        Argument result_;
        if (resultCanBeSet()) {
            result_ = Argument.createVoid();
        } else {
            Argument previous_ = getPreviousArg(this, _nodes, _rendStack);
            ClassField fieldId_ = getClassField();
            Argument parent_ = new Argument(ExecFieldTemplates.getParent(getAnc(), previous_.getStruct(), _context, _rendStack.getStackCall()));
            getArgumentPair(_nodes,this).setArgumentParent(parent_);
            result_ = ExecFieldTemplates.getSafeInstanceField(_context, _rendStack.getStackCall(), fieldId_, parent_);
        }
        postCalulate(_nodes, _context, _rendStack, result_);
    }

    @Override
    public Argument calculateSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, ContextEl _context, RendStackCall _rendStack) {
        if (_context.callsOrException(_rendStack.getStackCall())) {
            return _right;
        }
        offsetLoc(_rendStack);
        //Come from code directly so constant static fields can be initialized here
        ClassField fieldId_ = getClassField();
        Argument arg_;
        Argument parent_;
        if (resultCanBeSet()) {
            Argument prev_ = getPreviousArg(this, _nodes, _rendStack);
            parent_ = new Argument(ExecFieldTemplates.getParent(getAnc(), prev_.getStruct(), _context, _rendStack.getStackCall()));
        } else {
            parent_ = Argument.getNullableValue(getArgumentPair(_nodes, this).getArgumentParent());
        }
        arg_ = ExecFieldTemplates.setSafeInstanceField(_right, _context, _rendStack.getStackCall(), fieldId_, pair, parent_);
        return RendDynOperationNode.processCall(arg_, _context, _rendStack).getValue();
    }

    public ExecTypeReturn getPair() {
        return pair;
    }
}
