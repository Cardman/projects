package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ErrorType;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.SwitchBlockStack;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class ExecStdSwitchBlock extends ExecAbstractSwitchBlock {

    public ExecStdSwitchBlock(String _instanceTest, String _label, int _valueOffset, CustList<ExecOperationNode> _opValue) {
        super(_instanceTest,_label,_valueOffset,_opValue);
    }

    static CustList<ExecBracedBlock> children(ExecBracedBlock _braced, SwitchBlockStack _if) {
        ExecBlock n_ = _braced.getFirstChild();
        CustList<ExecBracedBlock> children_;
        children_ = new CustList<ExecBracedBlock>();
        while (n_ instanceof ExecBracedBlock) {
            children_.add((ExecBracedBlock)n_);
            _if.setExecLastVisitedBlock((ExecBracedBlock) n_);
            n_ = n_.getNextSibling();
        }
        _if.setExecBlock(_braced);
        return children_;
    }

    @Override
    protected void processCase(ContextEl _cont, SwitchBlockStack _if, Argument _arg, StackCall _stack) {
        ExecResultCase found_ = innerProcess(getInstanceTest(),_cont,_stack,this, _if, _arg);
        addStack(_cont,_if,_arg,_stack, found_);
    }

    public static ExecResultCase innerProcess(String _instanceTest,ContextEl _cont, StackCall _stack, ExecBracedBlock _braced, SwitchBlockStack _if, Argument _arg) {
        CustList<ExecBracedBlock> children_ = children(_braced,_if);
        ExecResultCase res_ = innerProcess(_cont, _stack, children_, _arg);
        ExecBracedBlock out_ = ExecResultCase.block(res_);
        if (out_ instanceof ExecAbstractInstanceCaseCondition) {
            String type_;
            if (((ExecAbstractInstanceCaseCondition)out_).isSpecific()) {
                type_ = _stack.formatVarType(((ExecAbstractInstanceCaseCondition) out_ ).getImportedClassName());
            } else {
                type_ = _stack.formatVarType(_instanceTest);
            }
            Struct struct_ = _arg.getStruct();
            String var_ = ((ExecAbstractInstanceCaseCondition)out_).getVariableName();
            AbstractPageEl ip_ = _stack.getLastPage();
            ip_.putValueVar(var_, LocalVariable.newLocalVariable(struct_,type_));
        }
        return res_;
    }

    public static ExecResultCase innerProcess(ContextEl _cont, StackCall _stack, CustList<ExecBracedBlock> _children, Argument _arg) {
        ExecResultCase def_ = null;
        ExecResultCase found_ = null;
        for (ExecBracedBlock b: _children) {
            if (!(b instanceof ExecDefaultCondition) && !(b instanceof ExecAbstractInstanceCaseCondition && !((ExecAbstractInstanceCaseCondition)b).isSpecific())) {
                found_ = tryFind(_cont,_stack,found_,b, _arg);
            } else {
                def_ = new ExecResultCase(b,0);
            }
        }
        if (found_ != null) {
            return found_;
        }
        return def_;
    }
    private static ExecResultCase tryFind(ContextEl _cont, StackCall _stack,ExecResultCase _found,ExecBracedBlock _in, Argument _arg) {
        if (_found != null) {
            return _found;
        }
        if (_in instanceof ExecAbstractInstanceCaseCondition && !_arg.isNull()) {
            String type_ = _stack.formatVarType(((ExecAbstractInstanceCaseCondition)_in).getImportedClassName());
            Struct struct_ = _arg.getStruct();
            if (ExecInherits.safeObject(type_, struct_.getClassName(_cont), _cont) == ErrorType.NOTHING) {
                return new ExecResultCase(_in,0);
            }
        }
//        if (_in instanceof ExecEnumCaseCondition) {
//            String name_ = NumParsers.getNameOfEnum(_arg.getStruct());
//            ExecEnumCaseCondition c_ = (ExecEnumCaseCondition) _in;
//            if (StringUtil.quickEq(c_.getValue(), name_)) {
//                return new ExecResultCase(_in,0);
//            }
//        }
//        if (_in instanceof ExecStdCaseCondition) {
//            ExecStdCaseCondition c_ = (ExecStdCaseCondition) _in;
//            if (c_.getArg().getStruct().sameReference(_arg.getStruct())) {
//                return new ExecResultCase(_in,0);
//            }
//        }
        return processList(_cont, _in, _arg);
    }

    private static ExecResultCase processList(ContextEl _cont, ExecBracedBlock _in, Argument _arg) {
        if (_in instanceof ExecSwitchValuesCondition) {
            int match_ = ((ExecSwitchValuesCondition) _in).getList().match(_arg, _cont);
            if (match_ >= 0) {
                return new ExecResultCase(_in,match_);
            }
        }
        return null;
    }
}
