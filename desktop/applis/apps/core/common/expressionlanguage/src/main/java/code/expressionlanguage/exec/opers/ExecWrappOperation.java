package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
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
        ExecOperationNode chFirst_ = getFirstChild();
        if (chFirst_ instanceof ExecDotOperation) {
            chFirst_ = ExecHelper.getLastNode((ExecMethodOperation) chFirst_);
        }
        if (chFirst_ instanceof ExecSettableFieldOperation) {
            ExecSettableFieldOperation ch_ = (ExecSettableFieldOperation)chFirst_;
            ExecSettableOperationContent settableFieldContent_ = ch_.getSettableFieldContent();
            Argument previous_;
            FieldWrapper f_;
            if (!settableFieldContent_.isStaticField()) {
                Argument previousArgument_ = ch_.getPreviousArg(ch_,_nodes, _stack);
                previous_ = new Argument(ExecTemplates.getParent(settableFieldContent_.getAnc(), previousArgument_.getStruct(), _conf, _stack));
                f_ = new InstanceFieldWrapper(previous_.getStruct(),settableFieldContent_.getRealType(),ch_.getRootBlock(),
                        settableFieldContent_.getClassField());
            } else {
                previous_ = new Argument();
                f_ = new StaticFieldWrapper(previous_.getStruct(),settableFieldContent_.getRealType(),ch_.getRootBlock(),
                        settableFieldContent_.getClassField());
            }
            ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes, this);
            pair_.setWrapper(f_);
            setQuickNoConvertSimpleArgument(ExecHelper.getArgumentPair(_nodes, ch_).getArgument(),_conf,_nodes, _stack);
            return;
        }
        if (chFirst_ instanceof ExecArrOperation) {
            ExecArrOperation ch_ = (ExecArrOperation)chFirst_;
            Argument previousArgument_ = ch_.getPreviousArg(ch_,_nodes, _stack);
            ArgumentsPair pairIndex_ = ExecHelper.getArgumentPair(_nodes, ch_.getFirstChild());
            ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes, this);
            ArrayWrapper a_ = new ArrayWrapper(previousArgument_.getStruct(),pairIndex_.getArgument().getStruct());
            pair_.setWrapper(a_);
            setQuickNoConvertSimpleArgument(ExecHelper.getArgumentPair(_nodes, ch_).getArgument(),_conf,_nodes, _stack);
            return;
        }
        if (chFirst_ instanceof ExecCustArrOperation) {
            ExecCustArrOperation ch_ = (ExecCustArrOperation)chFirst_;
            Argument previousArgument_ = ch_.getPreviousArg(ch_,_nodes, _stack);
            Argument previous_ = new Argument(ExecTemplates.getParent(ch_.getInstFctContent().getAnc(), previousArgument_.getStruct(), _conf, _stack));
            ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes, this);
            ArrayCustWrapper a_ = new ArrayCustWrapper(previous_,ch_.buildInfos(_nodes),ch_.getInstFctContent(),ch_.getReadWrite());
            pair_.setWrapper(a_);
            setQuickNoConvertSimpleArgument(ExecHelper.getArgumentPair(_nodes, ch_).getArgument(),_conf,_nodes, _stack);
            return;
        }
        ArgumentsPair pairCh_ = ExecHelper.getArgumentPair(_nodes, getFirstChild());
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes, this);
        ExecHelper.fwdWrapper(pair_,pairCh_);
        setQuickNoConvertSimpleArgument(ExecHelper.getArgumentPair(_nodes, getFirstChild()).getArgument(),_conf,_nodes, _stack);
    }
}
