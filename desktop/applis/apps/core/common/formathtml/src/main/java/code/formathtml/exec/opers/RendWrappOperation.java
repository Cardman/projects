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
        RendDynOperationNode chFirst_ = getFirstChild();
        if (chFirst_ instanceof RendDotOperation) {
            chFirst_ = getLastNode((RendMethodOperation) chFirst_);
        }
        if (chFirst_ instanceof RendSettableFieldOperation) {
            RendSettableFieldOperation ch_ = (RendSettableFieldOperation)chFirst_;
            ExecSettableOperationContent settableFieldContent_ = ch_.getSettableFieldContent();
            Argument previous_;
            FieldWrapper f_;
            if (!settableFieldContent_.isStaticField()) {
                Argument previousArgument_ = ch_.getPreviousArg(ch_,_nodes, _rendStack);
                previous_ = new Argument(ExecTemplates.getParent(settableFieldContent_.getAnc(), previousArgument_.getStruct(), _context, _stack));
                f_ = new InstanceFieldWrapper(previous_.getStruct(),settableFieldContent_.getRealType(),ch_.getRootBlock(),
                        settableFieldContent_.getClassField());
            } else {
                previous_ = new Argument();
                f_ = new StaticFieldWrapper(previous_.getStruct(),settableFieldContent_.getRealType(),ch_.getRootBlock(),
                        settableFieldContent_.getClassField());
            }
            ArgumentsPair pair_ = getArgumentPair(_nodes, this);
            pair_.setWrapper(f_);
            setQuickNoConvertSimpleArgument(getArgumentPair(_nodes,ch_).getArgument(),_nodes,_context, _stack);
            return;
        }
        if (chFirst_ instanceof RendArrOperation) {
            RendArrOperation ch_ = (RendArrOperation)chFirst_;
            Argument previousArgument_ = ch_.getPreviousArg(ch_,_nodes, _rendStack);
            ArgumentsPair pairIndex_ = getArgumentPair(_nodes, ch_.getFirstChild());
            ArgumentsPair pair_ = getArgumentPair(_nodes, this);
            ArrayWrapper a_ = new ArrayWrapper(previousArgument_.getStruct(),pairIndex_.getArgument().getStruct());
            pair_.setWrapper(a_);
            setQuickNoConvertSimpleArgument(getArgumentPair(_nodes,ch_).getArgument(),_nodes,_context, _stack);
            return;
        }
        ArgumentsPair pairCh_ = getArgumentPair(_nodes, getFirstChild());
        ArgumentsPair pair_ = getArgumentPair(_nodes, this);
        ExecHelper.fwdWrapper(pair_,pairCh_);
        setQuickNoConvertSimpleArgument(getArgumentPair(_nodes,getFirstChild()).getArgument(),_nodes,_context, _stack);
    }
}
