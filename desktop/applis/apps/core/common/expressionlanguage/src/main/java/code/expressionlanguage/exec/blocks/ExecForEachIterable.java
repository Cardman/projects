package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.LoopBlockStack;
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
    protected void checkIfNext(ContextEl _cont, LoopBlockStack _l) {
        iteratorHasNext(_cont, _l);
    }

    @Override
    protected LoopBlockStack newLoopBlockStack(ContextEl _cont, String _label, Struct _its) {
        if (_its == NullStruct.NULL_VALUE) {
            String npe_ = _cont.getStandards().getContent().getCoreNames().getAliasNullPe();
            _cont.setCallingState(new ErrorStruct(_cont, npe_));
            return null;
        }
        String locName_ = getIteratorVar(_cont);
        AbstractPageEl ip_ = _cont.getLastPage();
        ip_.putInternVars(locName_, _its,_cont);
        ExpressionLanguage dyn_ = ip_.getCurrentEl(_cont,this, IndexConstants.SECOND_INDEX, IndexConstants.SECOND_INDEX);
        Argument arg_ = ExpressionLanguage.tryToCalculate(_cont,dyn_,0);
        if (_cont.callsOrException()) {
            return null;
        }
        long length_ = IndexConstants.INDEX_NOT_FOUND_ELT;
        Struct iterStr_ = arg_.getStruct();
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setLabel(_label);
        l_.setIndex(-1);
        l_.setExecBlock(this);
        l_.setExecLoop(this);
        l_.setCurrentVisitedBlock(this);
        l_.setStructIterator(iterStr_);
        l_.setMaxIteration(length_);
        l_.setContainer(_its);
        return l_;
    }

    @Override
    protected Argument retrieveValue(ContextEl _conf, LoopBlockStack _l) {
        String locName_ = getNextVar(_conf);
        AbstractPageEl abs_ = _conf.getLastPage();
        abs_.putInternVars(locName_, _l.getStructIterator(),_conf);
        ExpressionLanguage dyn_ = abs_.getCurrentEl(_conf,this, IndexConstants.SECOND_INDEX, 3);
        return ExpressionLanguage.tryToCalculate(_conf,dyn_,0);
    }

    @Override
    protected ConditionReturn hasNext(ContextEl _conf, LoopBlockStack _l) {
        return iteratorHasNext(_conf, _l);
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, int _indexProcess) {
        if (_indexProcess == 0) {
            return getEl();
        }
        if (_indexProcess == 1) {
            return getEqIterator(_context);
        }
        if (_indexProcess == 2) {
            return getEqHasNext(_context);
        }
        return getEqNext(_context);
    }
    private ConditionReturn iteratorHasNext(ContextEl _conf, LoopBlockStack _l) {
        String locName_ = getHasNextVar(_conf);
        _conf.getLastPage().putInternVars(locName_, _l.getStructIterator(),_conf);
        ExpressionLanguage dyn_ = _conf.getLastPage().getCurrentEl(_conf,this, IndexConstants.FIRST_INDEX, 2);
        Argument arg_ = ExpressionLanguage.tryToCalculate(_conf,dyn_,0);
        if (_conf.callsOrException()) {
            return ConditionReturn.CALL_EX;
        }
        if (BooleanStruct.isTrue(arg_.getStruct())) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }

    public String getIteratorVar(ContextEl _an) {
        return _an.getClasses().getIteratorVarCust();
    }

    public String getHasNextVar(ContextEl _an) {
        return _an.getClasses().getHasNextVarCust();
    }

    public String getNextVar(ContextEl _an) {
        return _an.getClasses().getNextVarCust();
    }
    public ExpressionLanguage getEqIterator(ContextEl _an) {
        return new ExpressionLanguage(_an.getClasses().getExpsIteratorCust());
    }

    public ExpressionLanguage getEqHasNext(ContextEl _an) {
        return new ExpressionLanguage(_an.getClasses().getExpsHasNextCust());
    }
    public ExpressionLanguage getEqNext(ContextEl _an) {
        return new ExpressionLanguage(_an.getClasses().getExpsNextCust());
    }

}
