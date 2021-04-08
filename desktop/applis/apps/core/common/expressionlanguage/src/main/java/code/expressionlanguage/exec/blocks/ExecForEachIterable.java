package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.LoopBlockStack;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.core.IndexConstants;

public final class ExecForEachIterable extends ExecAbstractForEachLoop {
    public ExecForEachIterable(String _label, String _importedClassName, String _importedClassIndexName, String _variableName, int _variableNameOffset, int _expressionOffset, CustList<ExecOperationNode> _opList, int _offsetTrim) {
        super(_label, _importedClassName, _importedClassIndexName, _variableName, _variableNameOffset, _expressionOffset, _opList, _offsetTrim);
    }

    @Override
    protected void checkIfNext(ContextEl _cont, LoopBlockStack _l, StackCall _stack) {
        AbstractPageEl ip_ = _stack.getLastPage();
        String className_ = _stack.formatVarType(getImportedClassName());
        Struct struct_ = ExecClassArgumentMatching.defaultValue(className_, _cont);
        ip_.putValueVar(getVariableName(), LocalVariable.newLocalVariable(struct_,className_));
        iteratorHasNext(_cont, _l, _stack);
    }

    @Override
    protected LoopBlockStack newLoopBlockStack(ContextEl _cont, String _label, Struct _its, StackCall _stack) {
        if (_its == NullStruct.NULL_VALUE) {
            String npe_ = _cont.getStandards().getContent().getCoreNames().getAliasNullPe();
            _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, npe_, _stack)));
            return null;
        }
        String locName_ = _cont.getClasses().getIteratorVarCust();
        AbstractPageEl ip_ = _stack.getLastPage();
        ip_.putInternVars(locName_, _its,_cont);
        ExpressionLanguage dyn_ = ip_.getCurrentEl(_cont,this, IndexConstants.SECOND_INDEX, IndexConstants.SECOND_INDEX);
        Argument arg_ = ExpressionLanguage.tryToCalculate(_cont,dyn_,0, _stack);
        if (_cont.callsOrException(_stack)) {
            return null;
        }
        long length_ = IndexConstants.INDEX_NOT_FOUND_ELT;
        Struct iterStr_ = arg_.getStruct();
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setLabel(_label);
        l_.setIndex(-1);
        l_.setExecLoop(this);
        l_.setCurrentVisitedBlock(this);
        l_.setStructIterator(iterStr_);
        l_.setMaxIteration(length_);
        l_.setContainer(_its);
        return l_;
    }

    @Override
    protected Argument retrieveValue(ContextEl _conf, LoopBlockStack _l, StackCall _stack) {
        String locName_ = _conf.getClasses().getNextVarCust();
        AbstractPageEl abs_ = _stack.getLastPage();
        abs_.putInternVars(locName_, _l.getStructIterator(),_conf);
        ExpressionLanguage dyn_ = abs_.getCurrentEl(_conf,this, IndexConstants.SECOND_INDEX, 3);
        return ExpressionLanguage.tryToCalculate(_conf,dyn_,0, _stack);
    }

    @Override
    protected ConditionReturn hasNext(ContextEl _conf, LoopBlockStack _l, StackCall _stack) {
        return iteratorHasNext(_conf, _l, _stack);
    }

    @Override
    public CustList<ExecOperationNode> getEl(ContextEl _context, int _indexProcess) {
        if (_indexProcess == 0) {
            return getOpList();
        }
        if (_indexProcess == 1) {
            return _context.getClasses().getExpsIteratorCust();
        }
        if (_indexProcess == 2) {
            return _context.getClasses().getExpsHasNextCust();
        }
        return _context.getClasses().getExpsNextCust();
    }
    private ConditionReturn iteratorHasNext(ContextEl _conf, LoopBlockStack _l, StackCall _stackCall) {
        String locName_ = _conf.getClasses().getHasNextVarCust();
        _stackCall.getLastPage().putInternVars(locName_, _l.getStructIterator(),_conf);
        ExpressionLanguage dyn_ = _stackCall.getLastPage().getCurrentEl(_conf,this, IndexConstants.FIRST_INDEX, 2);
        Argument arg_ = ExpressionLanguage.tryToCalculate(_conf,dyn_,0, _stackCall);
        if (_conf.callsOrException(_stackCall)) {
            return ConditionReturn.CALL_EX;
        }
        if (BooleanStruct.isTrue(arg_.getStruct())) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }

}
