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
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public abstract class ExecAbstractSwitchBlock extends ExecBracedBlock implements StackableBlock, WithNotEmptyEl {
    private final String label;
    private final String instanceTest;

    private final ExecOperationNodeListOff value;

    ExecAbstractSwitchBlock(String _instanceTest, String _label, int _valueOffset, CustList<ExecOperationNode> _opValue) {
        instanceTest = _instanceTest;
        label = _label;
        value = new ExecOperationNodeListOff(_opValue,_valueOffset);
    }

    private static CustList<ExecBracedBlock> children(ExecBracedBlock _braced, SwitchBlockStack _if) {
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

    public static ExecResultCase innerProcess(String _instanceTest, ContextEl _cont, StackCall _stack, ExecBracedBlock _braced, SwitchBlockStack _if, Argument _arg, int _index) {
        CustList<ExecBracedBlock> children_ = children(_braced,_if);
        return innerProcess(_instanceTest,_cont, _stack, children_, _arg, _index);
    }

    private static ExecResultCase innerProcess(String _instanceTest, ContextEl _cont, StackCall _stack, CustList<ExecBracedBlock> _children, Argument _arg, int _index) {
        ExecResultCase def_ = null;
        CustList<ExecBracedBlock> filtered_ = new CustList<ExecBracedBlock>();
        for (ExecBracedBlock b: _children) {
            if (b instanceof ExecDefaultCondition || b instanceof ExecAbstractInstanceCaseCondition && !((ExecAbstractInstanceCaseCondition) b).isSpecific()) {
                def_ = new ExecResultCase(b,0);
            } else {
                filtered_.add(b);
            }
        }
        ExecResultCase found_ = null;
        for (ExecBracedBlock b: filtered_) {
            found_ = tryFind(_cont,_stack, found_, b, _arg, _index);
            if (_cont.callsOrException(_stack)){
                return null;
            }
        }
        return result(_instanceTest, _stack, _arg, def_, found_);
    }

    private static ExecResultCase result(String _instanceTest, StackCall _stack, Argument _arg, ExecResultCase _def, ExecResultCase _found) {
        if (_found != null) {
            return _found;
        }
        ExecBracedBlock block_ = ExecResultCase.block(_def);
        if (block_ instanceof ExecAbstractInstanceCaseCondition) {
            String type_ = _stack.formatVarType(_instanceTest);
            putVar(_stack, (ExecAbstractInstanceCaseCondition) block_, type_, _arg);
        }
        return _def;
    }

    private static ExecResultCase tryFind(ContextEl _cont, StackCall _stack, ExecResultCase _found, ExecBracedBlock _in, Argument _arg, int _index) {
        if (_found != null) {
            return _found;
        }
        if (_in instanceof ExecAbstractInstanceCaseCondition && !_arg.isNull()) {
            String type_ = _stack.formatVarType(((ExecAbstractInstanceCaseCondition)_in).getImportedClassName());
            int sum_ = ((ExecAbstractInstanceCaseCondition) _in).getIndex() + _index;
            int nbPrevious_ = _stack.getLastPage().sizeEl() - 1;
            return procTypeVar(_cont, _stack, (ExecAbstractInstanceCaseCondition) _in, _arg, type_, sum_, nbPrevious_);
        }
        return processList(_cont, _in, _arg);
    }

    private static ExecResultCase procTypeVar(ContextEl _cont, StackCall _stack, ExecAbstractInstanceCaseCondition _in, Argument _arg, String _type, int _sum, int _nbPrevious) {
        ExecOperationNodeListOff exp_ = _in.getExp();
        CustList<ExecOperationNode> list_ = exp_.getList();
        boolean safe_ = ExecInherits.safeObject(_type, _arg.getStruct().getClassName(_cont), _cont) == ErrorType.NOTHING;
        if (list_.isEmpty()) {
            if (safe_) {
                putVar(_stack, _in, _type, _arg);
                return new ExecResultCase(_in,0);
            }
            return null;
        }
        if (_sum < _nbPrevious) {
            return null;
        }
        if (_sum > _nbPrevious && safe_) {
            putVar(_stack, _in, _type, _arg);
        }
        int offset_ = exp_.getOffset();
        AbstractPageEl lastPage_ = _stack.getLastPage();
        lastPage_.globalOffset(offset_);
        Argument visit_;
        if (safe_) {
            visit_ = ExecHelperBlocks.tryToCalculate(_cont, _sum, _stack, list_, 0, _in);
        } else {
            visit_ = ExecHelperBlocks.tryToCalculate(_cont, _sum, _stack, _cont.getClasses().getExpsConstFalse(), 0, _in);
        }
        if (_cont.callsOrException(_stack)) {
            if (!_stack.calls()) {
                _stack.getLastPage().removeRefVar(_in.getVariableName());
            }
            return null;
        }
        if (BooleanStruct.isFalse(visit_.getStruct())) {
            if (safe_) {
                _stack.getLastPage().removeRefVar(_in.getVariableName());
            }
            return null;
        }
        return new ExecResultCase(_in,0);
    }

    private static void putVar(StackCall _stack, ExecAbstractInstanceCaseCondition _in, String _type, Argument _arg) {
        Struct struct_ = _arg.getStruct();
        String var_ = _in.getVariableName();
        AbstractPageEl ip_ = _stack.getLastPage();
        ip_.putValueVar(var_, LocalVariable.newLocalVariable(struct_, _type));
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

    public static ExecResultCase tryLastVisited(SwitchBlockStack _if, ExecResultCase _res) {
        if (_res != null) {
            _if.setExecLastVisitedBlock(_res.getBlock());
        }
        return _res;
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.processSwitch(_cont, _stack, label, value, this);
    }

    protected void processCase(ContextEl _cont, SwitchBlockStack _if, Argument _arg, StackCall _stack) {
        ExecResultCase res_ = innerProcess(getInstanceTest(), _cont, _stack, this, _if, _arg, 1);
        lastVis(_if, res_);
        addStack(_cont, _if, _arg, _stack, res_);
    }

    protected abstract ExecResultCase lastVis(SwitchBlockStack _if, ExecResultCase _res);
    protected void addStack(ContextEl _cont, SwitchBlockStack _if, Argument _arg, StackCall _stack, ExecResultCase _found) {
        if (_cont.callsOrException(_stack)) {
            return;
        }
        AbstractPageEl ip_ = _stack.getLastPage();
        _cont.getCoverage().passSwitch(this, _found, _arg, _stack);
        ip_.clearCurrentEls();
        visit(_if, _found, ip_, this);
    }

    static void visit(SwitchBlockStack _if, ExecResultCase _found, AbstractPageEl _ip, ExecBracedBlock _bl) {
        if (_found == null) {
            _if.setCurrentVisitedBlock(_bl);
        } else {
            _ip.setBlock(_found.getBlock());
            _if.setCurrentVisitedBlock(_found.getBlock());
        }
        _ip.addBlock(_if);
    }

    public String getInstanceTest() {
        return instanceTest;
    }
}
