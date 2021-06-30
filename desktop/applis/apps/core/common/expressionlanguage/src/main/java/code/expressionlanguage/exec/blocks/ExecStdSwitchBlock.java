package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ErrorType;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.SwitchBlockStack;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.core.StringUtil;

public final class ExecStdSwitchBlock extends ExecAbstractSwitchBlock {

    public ExecStdSwitchBlock(String _instanceTest, String _label, int _valueOffset, CustList<ExecOperationNode> _opValue) {
        super(_instanceTest,_label,_valueOffset,_opValue);
    }

    @Override
    protected void processCase(ContextEl _cont, SwitchBlockStack _if, Argument _arg, StackCall _stack) {
        ExecBracedBlock found_ = innerProcess(getInstanceTest(),_cont,_stack,this, _if, _arg);
        addStack(_cont,_if,_arg,_stack, found_);
    }

    public static ExecBracedBlock innerProcess(String _instanceTest,ContextEl _cont, StackCall _stack, ExecBracedBlock _braced, SwitchBlockStack _if, Argument _arg) {
        CustList<ExecBracedBlock> children_ = children(_braced,_if);
        ExecBracedBlock res_ = ExecStdSwitchBlock.innerProcess(_cont, _stack, children_, _arg);
        if (res_ instanceof ExecAbstractInstanceCaseCondition) {
            String type_;
            if (((ExecAbstractInstanceCaseCondition)res_).isSpecific()) {
                type_ = _stack.formatVarType(((ExecAbstractInstanceCaseCondition) res_).getImportedClassName());
            } else {
                type_ = _stack.formatVarType(_instanceTest);
            }
            Struct struct_ = _arg.getStruct();
            String var_ = ((ExecAbstractInstanceCaseCondition)res_).getVariableName();
            AbstractPageEl ip_ = _stack.getLastPage();
            ip_.putValueVar(var_, LocalVariable.newLocalVariable(struct_,type_));
        }
        return res_;
    }

    public static ExecBracedBlock innerProcess(ContextEl _cont, StackCall _stack, CustList<ExecBracedBlock> _children, Argument _arg) {
        ExecBracedBlock def_ = null;
        ExecBracedBlock found_ = null;
        for (ExecBracedBlock b: _children) {
            if (!(b instanceof ExecDefaultCondition) && !(b instanceof ExecAbstractInstanceCaseCondition && !((ExecAbstractInstanceCaseCondition)b).isSpecific())) {
                found_ = tryFind(_cont,_stack,found_,b, _arg);
            } else {
                def_ = b;
            }
        }
        if (found_ != null) {
            return found_;
        }
        return def_;
    }
    private static ExecBracedBlock tryFind(ContextEl _cont, StackCall _stack,ExecBracedBlock _found,ExecBracedBlock _in, Argument _arg) {
        if (_found != null) {
            return _found;
        }
//        if (_in instanceof ExecNullCaseCondition && _arg.isNull()) {
//            return _in;
//        }
        if (_in instanceof ExecAbstractInstanceCaseCondition && !_arg.isNull()) {
            String type_ = _stack.formatVarType(((ExecAbstractInstanceCaseCondition)_in).getImportedClassName());
            Struct struct_ = _arg.getStruct();
            if (ExecInherits.safeObject(type_, struct_.getClassName(_cont), _cont) == ErrorType.NOTHING) {
                return _in;
            }
        }
        if (_in instanceof ExecEnumCaseCondition) {
            String name_ = NumParsers.getNameOfEnum(_arg.getStruct());
            ExecEnumCaseCondition c_ = (ExecEnumCaseCondition) _in;
            if (StringUtil.quickEq(c_.getValue(), name_)) {
                return _in;
            }
        }
        if (_in instanceof ExecStdCaseCondition) {
            ExecStdCaseCondition c_ = (ExecStdCaseCondition) _in;
            if (c_.getArg().getStruct().sameReference(_arg.getStruct())) {
                return _in;
            }
        }
        return null;
    }
}
