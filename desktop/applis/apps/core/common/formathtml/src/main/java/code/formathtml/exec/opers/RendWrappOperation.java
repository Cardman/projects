package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.*;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecSettableOperationContent;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendWrappOperation extends RendAbstractUnaryOperation {
    public RendWrappOperation(ExecOperationContent _content) {
        super(_content);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        if (getFirstChild() instanceof RendStdRefVariableOperation) {
            RendStdRefVariableOperation ch_ = (RendStdRefVariableOperation) getFirstChild();
            String variableName_ = ch_.getVariableContent().getVariableName();
            AbstractWrapper val_ = _rendStack.getLastPage().getRefParams().getVal(variableName_);
            ArgumentsPair pair_ = getArgumentPair(_nodes, this);
            pair_.setWrapper(val_);
            setQuickNoConvertSimpleArgument(Argument.createVoid(),_nodes,_context, _stack);
            return;
        }
        if (getFirstChild() instanceof RendStdVariableOperation) {
            RendStdVariableOperation ch_ = (RendStdVariableOperation) getFirstChild();
            String variableName_ = ch_.getVariableContent().getVariableName();
            AbstractWrapper val_ = _rendStack.getLastPage().getRefParams().getVal(variableName_);
            ArgumentsPair pair_ = getArgumentPair(_nodes, this);
            pair_.setWrapper(val_);
            setQuickNoConvertSimpleArgument(Argument.createVoid(),_nodes,_context, _stack);
            return;
        }
        RendDynOperationNode chFirst_ = getFirstChild();
        if (chFirst_ instanceof RendDotOperation) {
            chFirst_ = getLastNode((RendMethodOperation) chFirst_);
        }
        if (chFirst_ instanceof RendSettableFieldOperation) {
            RendSettableFieldOperation ch_ = (RendSettableFieldOperation)chFirst_;
            ExecSettableOperationContent settableFieldContent_ = ch_.getSettableFieldContent();
            Argument previous_;
            if (!settableFieldContent_.isStaticField()) {
                Argument previousArgument_ = getPreviousArg(ch_,_nodes, _rendStack);
                String className_ = settableFieldContent_.getClassField().getClassName();
                previous_ = new Argument(ExecTemplates.getParent(settableFieldContent_.getAnc(), className_, previousArgument_.getStruct(), _context, _stack));
            } else {
                previous_ = new Argument();
            }
            FieldWrapper f_ = new FieldWrapper(previous_.getStruct(),settableFieldContent_.getRealType(),ch_.getRootBlock(),
                    settableFieldContent_.isStaticField(),settableFieldContent_.isFinalField(),settableFieldContent_.getClassField());
            ArgumentsPair pair_ = getArgumentPair(_nodes, this);
            pair_.setWrapper(f_);
            setQuickNoConvertSimpleArgument(Argument.createVoid(),_nodes,_context, _stack);
            return;
        }
        if (chFirst_ instanceof RendArrOperation) {
            RendArrOperation ch_ = (RendArrOperation)chFirst_;
            Argument previousArgument_ = getPreviousArg(ch_,_nodes, _rendStack);
            ArgumentsPair pairIndex_ = getArgumentPair(_nodes, ch_.getFirstChild());
            ArgumentsPair pair_ = getArgumentPair(_nodes, this);
            ArrayWrapper a_ = new ArrayWrapper(previousArgument_.getStruct(),pairIndex_.getArgument().getStruct());
            pair_.setWrapper(a_);
            setQuickNoConvertSimpleArgument(Argument.createVoid(),_nodes,_context, _stack);
            return;
        }
        ArgumentsPair pairCh_ = getArgumentPair(_nodes, getFirstChild());
        ArgumentsPair pair_ = getArgumentPair(_nodes, this);
        ExecHelper.fwdWrapper(pair_,pairCh_);
        setQuickNoConvertSimpleArgument(Argument.createVoid(),_nodes,_context, _stack);
    }
}
