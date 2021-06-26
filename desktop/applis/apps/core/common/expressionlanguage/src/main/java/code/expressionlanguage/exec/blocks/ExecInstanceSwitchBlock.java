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

public final class ExecInstanceSwitchBlock extends ExecAbstractSwitchBlock {
    private final String instanceTest;
    public ExecInstanceSwitchBlock(String _instanceTest, String _label, int _valueOffset, CustList<ExecOperationNode> _opValue) {
        super(_label, _valueOffset, _opValue);
        instanceTest = _instanceTest;
    }

    @Override
    protected void processCase(ContextEl _cont, SwitchBlockStack _if, Argument _arg, StackCall _stack) {
        ExecBracedBlock found_ = innerProcess(this,instanceTest,_cont, _if, _arg, _stack);
        addStack(_cont, _if, _arg, _stack, found_);
    }

    public static ExecBracedBlock innerProcess(ExecBracedBlock _braced,String _instanceTest, ContextEl _cont, SwitchBlockStack _if, Argument _arg, StackCall _stack) {
        ExecBlock n_ = _braced.getFirstChild();
        CustList<ExecBracedBlock> children_;
        children_ = new CustList<ExecBracedBlock>();
        while (n_ instanceof ExecBracedBlock) {
            children_.add((ExecBracedBlock)n_);
            n_ = n_.getNextSibling();
        }
        _if.setExecBlock(_braced);
        ExecBracedBlock found_ = null;
        if (_arg.isNull()) {
            for (ExecBracedBlock b: children_) {
                if (b instanceof ExecNullInstanceCaseCondition) {
                    _if.setExecLastVisitedBlock(b);
                    found_ = b;
                    break;
                }
            }
        } else {
            for (ExecBracedBlock b: children_) {
                if (b instanceof ExecAbstractInstanceTypeCaseCondition && ((ExecAbstractInstanceTypeCaseCondition)b).isSpecific()) {
                    ExecAbstractInstanceTypeCaseCondition b_ = (ExecAbstractInstanceTypeCaseCondition) b;
                    found_ = fetch(_cont, _if, _arg,found_,b_, _stack);
                }
            }
        }
        return defCase(_instanceTest, _if, _arg, _stack, children_, found_);
    }

    private static ExecBracedBlock defCase(String _instanceTest, SwitchBlockStack _if, Argument _arg, StackCall _stack, CustList<ExecBracedBlock> _children, ExecBracedBlock _found) {
        ExecBracedBlock out_ = _found;
        for (ExecBracedBlock b: _children) {
            if (b instanceof ExecAbstractInstanceTypeCaseCondition && !((ExecAbstractInstanceTypeCaseCondition)b).isSpecific()) {
                ExecAbstractInstanceTypeCaseCondition b_ = (ExecAbstractInstanceTypeCaseCondition) b;
                out_ = fetchDef(_instanceTest, _if, _arg,out_,b_, _stack);
            }
        }
        return out_;
    }

    private static ExecBracedBlock fetch(ContextEl _cont, SwitchBlockStack _if, Argument _arg,
                                         ExecBracedBlock _found, ExecAbstractInstanceTypeCaseCondition _s, StackCall _stackCall) {
        if (_found != null) {
            return _found;
        }
        String type_ = _s.getImportedClassName();
        AbstractPageEl ip_ = _stackCall.getLastPage();
        type_ = _stackCall.formatVarType(type_);
        Struct struct_ = _arg.getStruct();
        if (ExecInherits.safeObject(type_, struct_.getClassName(_cont), _cont) == ErrorType.NOTHING) {
            String var_ = _s.getVariableName();
            ip_.putValueVar(var_,LocalVariable.newLocalVariable(struct_,type_));
            _if.setExecLastVisitedBlock(_s);
            return _s;
        }
        return null;
    }

    private static ExecBracedBlock fetchDef(String _instanceTest, SwitchBlockStack _if, Argument _arg,
                                            ExecBracedBlock _found, ExecAbstractInstanceTypeCaseCondition _s, StackCall _stackCall) {
        if (_found != null) {
            return _found;
        }
        AbstractPageEl ip_ = _stackCall.getLastPage();
        String type_ = _stackCall.formatVarType(_instanceTest);
        Struct struct_ = _arg.getStruct();
        String var_ = _s.getVariableName();
        ip_.putValueVar(var_,LocalVariable.newLocalVariable(struct_,type_));
        _if.setExecLastVisitedBlock(_s);
        return _s;
    }
}
