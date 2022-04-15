package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTypeReturn;
import code.expressionlanguage.exec.variables.*;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecSettableOperationContent;
import code.expressionlanguage.structs.RangeStruct;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class ExecWrappOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {
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
            FieldWrapper f_;
            if (ch_ instanceof ExecSettableFieldInstOperation) {
                ExecTypeReturn pair_ = ((ExecSettableFieldInstOperation) ch_).getPair();
                ArgumentsPair pairCh_ = ExecHelper.getArgumentPair(_nodes, ch_);
                Struct parent_ = pairCh_.getArgumentParent().getStruct();
                f_ = new InstanceFieldWrapper(parent_, parent_.getClassName(_conf),settableFieldContent_.getRealType(),
                        settableFieldContent_.getClassField(), pair_);
            } else {
                f_ = new StaticFieldWrapper(settableFieldContent_.getRealType(),((ExecSettableFieldStatOperation)ch_).getRootBlock(),
                        settableFieldContent_.getClassField());
            }
            ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes, this);
            pair_.setWrapper(f_);
            setQuickNoConvertSimpleArgument(ExecHelper.getArgumentPair(_nodes, ch_).getArgument(),_conf,_nodes, _stack);
            return;
        }
        if (chFirst_ instanceof ExecArrOperation) {
            ExecArrOperation ch_ = (ExecArrOperation)chFirst_;
            Argument previousArgument_ = ExecHelper.getArgumentPair(_nodes,ch_).getArgumentParent();
            ArgumentsPair pairIndex_ = ExecHelper.getArgumentPair(_nodes, ch_.getFirstChild());
            ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes, this);
            Struct struct_ = pairIndex_.getArgument().getStruct();
            AbstractWrapper a_ = buildArrWrapp(previousArgument_, struct_);
            pair_.setWrapper(a_);
            setQuickNoConvertSimpleArgument(ExecHelper.getArgumentPair(_nodes, ch_).getArgument(),_conf,_nodes, _stack);
            return;
        }
        if (chFirst_ instanceof ExecCustArrOperation) {
            ExecCustArrOperation ch_ = (ExecCustArrOperation)chFirst_;
            ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes, this);
            ArgumentsPair pairCh_ = ExecHelper.getArgumentPair(_nodes, ch_);
            Struct parent_ = pairCh_.getArgumentParent().getStruct();
            ArrayCustWrapper a_ = new ArrayCustWrapper(pairCh_.getArgumentList(),parent_, parent_.getClassName(_conf), ch_.getReadWrite());
            pair_.setWrapper(a_);
            setQuickNoConvertSimpleArgument(ExecHelper.getArgumentPair(_nodes, ch_).getArgument(),_conf,_nodes, _stack);
            return;
        }
        ArgumentsPair pairCh_ = ExecHelper.getArgumentPair(_nodes, getFirstChild());
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes, this);
        ExecHelper.fwdWrapper(pair_,pairCh_);
        setQuickNoConvertSimpleArgument(ExecHelper.getArgumentPair(_nodes, getFirstChild()).getArgument(),_conf,_nodes, _stack);
    }

    public static AbstractWrapper buildArrWrapp(Argument _previous, Struct _struct) {
        AbstractWrapper a_;
        if (_struct instanceof RangeStruct) {
            a_ = new ArrPartWrapper(_previous.getStruct(), (RangeStruct) _struct);
        } else {
            a_ = new ArrayWrapper(_previous.getStruct(), _struct);
        }
        return a_;
    }
}
