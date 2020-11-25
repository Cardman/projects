package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
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
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        if (getFirstChild() instanceof ExecStdVariableOperation) {
            ExecStdVariableOperation ch_ = (ExecStdVariableOperation) getFirstChild();
            String variableName_ = ch_.getVariableContent().getVariableName();
            PageEl ip_ = _conf.getLastPage();
            LocalVariable val_ = ip_.getValueVars().getVal(variableName_);
            VariableWrapper v_ = new VariableWrapper();
            val_ = ExecTemplates.local(val_);
            v_.setLocal(val_);
            ArgumentsPair pair_ = ExecTemplates.getArgumentPair(_nodes, this);
            pair_.setWrapper(v_);
            setQuickNoConvertSimpleArgument(Argument.createVoid(),_conf,_nodes);
            return;
        }
        ExecOperationNode chFirst_ = getFirstChild();
        if (chFirst_ instanceof ExecDotOperation) {
            chFirst_ = ExecTemplates.getLastNode((ExecMethodOperation) chFirst_);
        }
        if (chFirst_ instanceof ExecSettableFieldOperation) {
            ExecSettableFieldOperation ch_ = (ExecSettableFieldOperation)chFirst_;
            ExecSettableOperationContent settableFieldContent_ = ch_.getSettableFieldContent();
            FieldWrapper f_ = new FieldWrapper();
            f_.setFieldType(settableFieldContent_.getRealType());
            f_.setFinalField(settableFieldContent_.isFinalField());
            f_.setStaticField(settableFieldContent_.isStaticField());
            f_.setId(settableFieldContent_.getClassField());
            f_.setRootBlock(ch_.getRootBlock());
            ArgumentsPair pairField_ = ExecTemplates.getArgumentPair(_nodes, getFirstChild());
            f_.setValue(pairField_.getArgument().getStruct());
            Argument previous_;
            if (!settableFieldContent_.isStaticField()) {
                Argument previousArgument_ = ch_.getPreviousArg(ch_,_nodes, _conf);
                String className_ = settableFieldContent_.getClassField().getClassName();
                previous_ = new Argument(ExecTemplates.getParent(settableFieldContent_.getAnc(), className_, previousArgument_.getStruct(), _conf));
            } else {
                previous_ = new Argument();
            }
            f_.setContainer(previous_.getStruct());
            ArgumentsPair pair_ = ExecTemplates.getArgumentPair(_nodes, this);
            pair_.setWrapper(f_);
            setQuickNoConvertSimpleArgument(Argument.createVoid(),_conf,_nodes);
            return;
        }
        if (chFirst_ instanceof ExecArrOperation) {
            ExecArrOperation ch_ = (ExecArrOperation)chFirst_;
            ArrayWrapper a_ = new ArrayWrapper();
            Argument previousArgument_ = ch_.getPreviousArg(ch_,_nodes, _conf);
            a_.setContainer(previousArgument_.getStruct());
            ArgumentsPair pairField_ = ExecTemplates.getArgumentPair(_nodes, getFirstChild());
            a_.setValue(pairField_.getArgument().getStruct());
            ArgumentsPair pairIndex_ = ExecTemplates.getArgumentPair(_nodes, ch_.getFirstChild());
            a_.setIndex(pairIndex_.getArgument().getStruct());
            ArgumentsPair pair_ = ExecTemplates.getArgumentPair(_nodes, this);
            pair_.setWrapper(a_);
            setQuickNoConvertSimpleArgument(Argument.createVoid(),_conf,_nodes);
            return;
        }
        ArgumentsPair pairCh_ = ExecTemplates.getArgumentPair(_nodes, getFirstChild());
        ArgumentsPair pair_ = ExecTemplates.getArgumentPair(_nodes, this);
        pair_.setWrapper(pairCh_.getWrapper());
        setQuickNoConvertSimpleArgument(Argument.createVoid(),_conf,_nodes);
    }
}
