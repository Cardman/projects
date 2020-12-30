package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.*;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecSettableOperationContent;
import code.util.IdMap;

public final class ExecWrappOperation extends ExecAbstractUnaryOperation {
    public ExecWrappOperation(ExecOperationContent _opCont) {
        super(_opCont);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        if (getFirstChild() instanceof ExecStdRefVariableOperation) {
            ExecStdRefVariableOperation ch_ = (ExecStdRefVariableOperation) getFirstChild();
            String variableName_ = ch_.getVariableContent().getVariableName();
            PageEl ip_ = _stack.getLastPage();
            AbstractWrapper val_ = ExecTemplates.getWrapper(variableName_,ch_.getVariableContent().getDeep(), ip_.getCache(), _stack.getLastPage().getRefParams());
            ArgumentsPair pair_ = ExecTemplates.getArgumentPair(_nodes, this);
            pair_.setWrapper(val_);
            setQuickNoConvertSimpleArgument(Argument.createVoid(),_conf,_nodes, _stack);
            return;
        }
        if (getFirstChild() instanceof ExecStdVariableOperation) {
            ExecStdVariableOperation ch_ = (ExecStdVariableOperation) getFirstChild();
            String variableName_ = ch_.getVariableContent().getVariableName();
            PageEl ip_ = _stack.getLastPage();
            AbstractWrapper val_ = ip_.getRefParams().getVal(variableName_);
            ArgumentsPair pair_ = ExecTemplates.getArgumentPair(_nodes, this);
            pair_.setWrapper(val_);
            setQuickNoConvertSimpleArgument(Argument.createVoid(),_conf,_nodes, _stack);
            return;
        }
        ExecOperationNode chFirst_ = getFirstChild();
        if (chFirst_ instanceof ExecDotOperation) {
            chFirst_ = ExecTemplates.getLastNode((ExecMethodOperation) chFirst_);
        }
        if (chFirst_ instanceof ExecSettableFieldOperation) {
            ExecSettableFieldOperation ch_ = (ExecSettableFieldOperation)chFirst_;
            ExecSettableOperationContent settableFieldContent_ = ch_.getSettableFieldContent();
            Argument previous_;
            if (!settableFieldContent_.isStaticField()) {
                Argument previousArgument_ = ch_.getPreviousArg(ch_,_nodes, _stack);
                String className_ = settableFieldContent_.getClassField().getClassName();
                previous_ = new Argument(ExecTemplates.getParent(settableFieldContent_.getAnc(), className_, previousArgument_.getStruct(), _conf, _stack));
            } else {
                previous_ = new Argument();
            }
            FieldWrapper f_ = new FieldWrapper(previous_.getStruct(),settableFieldContent_.getRealType(),ch_.getRootBlock(),
                    settableFieldContent_.isStaticField(),settableFieldContent_.isFinalField(),settableFieldContent_.getClassField());
            ArgumentsPair pair_ = ExecTemplates.getArgumentPair(_nodes, this);
            pair_.setWrapper(f_);
            setQuickNoConvertSimpleArgument(Argument.createVoid(),_conf,_nodes, _stack);
            return;
        }
        if (chFirst_ instanceof ExecArrOperation) {
            ExecArrOperation ch_ = (ExecArrOperation)chFirst_;
            Argument previousArgument_ = ch_.getPreviousArg(ch_,_nodes, _stack);
            ArgumentsPair pairIndex_ = ExecTemplates.getArgumentPair(_nodes, ch_.getFirstChild());
            ArgumentsPair pair_ = ExecTemplates.getArgumentPair(_nodes, this);
            ArrayWrapper a_ = new ArrayWrapper(previousArgument_.getStruct(),pairIndex_.getArgument().getStruct());
            pair_.setWrapper(a_);
            setQuickNoConvertSimpleArgument(Argument.createVoid(),_conf,_nodes, _stack);
            return;
        }
        ArgumentsPair pairCh_ = ExecTemplates.getArgumentPair(_nodes, getFirstChild());
        ArgumentsPair pair_ = ExecTemplates.getArgumentPair(_nodes, this);
        pair_.setWrapper(pairCh_.getWrapper());
        setQuickNoConvertSimpleArgument(Argument.createVoid(),_conf,_nodes, _stack);
    }
}
