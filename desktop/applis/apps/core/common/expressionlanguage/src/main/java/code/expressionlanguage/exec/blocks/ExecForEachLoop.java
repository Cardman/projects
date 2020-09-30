package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.LoopBlockStack;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.StringMap;

public final class ExecForEachLoop extends ExecBracedBlock implements ExecLoop, WithNotEmptyEl {

    private String label;

    private String importedClassName;

    private String importedClassIndexName;

    private final String variableName;

    private int variableNameOffset;

    private int expressionOffset;

    private CustList<ExecOperationNode> opList;

    public ExecForEachLoop(String _label, String importedClassName, String importedClassIndexName, String variableName, int variableNameOffset, int expressionOffset, CustList<ExecOperationNode> _opList, int _offsetTrim) {
        super(_offsetTrim);
        label = _label;
        this.importedClassName = importedClassName;
        this.importedClassIndexName = importedClassIndexName;
        this.variableName = variableName;
        this.variableNameOffset = variableNameOffset;
        this.expressionOffset = expressionOffset;
        opList = _opList;
    }

    @Override
    public void processLastElementLoop(ContextEl _conf, LoopBlockStack _l) {
        _l.setEvaluatingKeepLoop(true);
        boolean hasNext_;
        if (_l.getStructIterator() != null) {
            ConditionReturn has_ = iteratorHasNext(_conf,_l);
            if (has_ == ConditionReturn.CALL_EX) {
                return;
            }
            hasNext_ = has_ == ConditionReturn.YES;
        } else {
            hasNext_ = _l.hasNext();
        }
        incrOrFinish(_conf, !hasNext_,_l);
    }

    private ConditionReturn iteratorHasNext(ContextEl _conf, LoopBlockStack _l) {
        Struct strIter_ = _l.getStructIterator();
        String locName_ = getHasNextVar(_conf);
        _conf.getLastPage().putInternVars(locName_, strIter_,_conf);
        ExpressionLanguage dyn_ = _conf.getLastPage().getCurrentEl(_conf,this, CustList.FIRST_INDEX, 2);
        Argument arg_ = ExpressionLanguage.tryToCalculate(_conf,dyn_,0);
        if (_conf.callsOrException()) {
            return ConditionReturn.CALL_EX;
        }
        if (BooleanStruct.isTrue(arg_.getStruct())) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }

    private void incrementLoop(ContextEl _conf, LoopBlockStack _l) {
        _l.setIndex(_l.getIndex() + 1);
        AbstractPageEl abs_ = _conf.getLastPage();

        abs_.setGlobalOffset(variableNameOffset);
        abs_.setOffset(0);
        Struct iterator_ = _l.getStructIterator();
        Struct element_ = NullStruct.NULL_VALUE;
        Argument arg_ = Argument.createVoid();
        ExecOperationNode el_ = opList.last();
        if (!el_.getResultClass().isArray()) {
            String locName_ = getNextVar(_conf);
            abs_.putInternVars(locName_, iterator_,_conf);
            ExpressionLanguage dyn_ = abs_.getCurrentEl(_conf,this, CustList.SECOND_INDEX, 3);
            arg_ = ExpressionLanguage.tryToCalculate(_conf,dyn_,0);
        } else {
            Struct container_ = _l.getContainer();
            LongStruct lg_ = new LongStruct(_l.getIndex());
            element_ = ExecTemplates.getElement(container_, lg_, _conf);
        }
        if (_conf.callsOrException()) {
            return;
        }
        abs_.clearCurrentEls();
        if (el_.getResultClass().isArray()) {
            arg_ = new Argument(element_);
        }
        ExecTemplates.setValue(_conf, variableName,abs_,arg_,-1);
        ExecTemplates.incrIndexLoop(_conf, variableName, abs_, -1);
        if (_conf.callsOrException()) {
            return;
        }
        _l.setEvaluatingKeepLoop(false);
        abs_.getReadWrite().setBlock(getFirstChild());
    }

    private Struct processLoop(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        ip_.setGlobalOffset(expressionOffset);
        ip_.setOffset(0);
        ExpressionLanguage el_ = ip_.getCurrentEl(_conf, this, CustList.FIRST_INDEX, CustList.FIRST_INDEX);
        Argument arg_ = ExpressionLanguage.tryToCalculate(_conf,el_,0);
        if (_conf.callsOrException()) {
            return NullStruct.NULL_VALUE;
        }
        return arg_.getStruct();

    }

    @Override
    public void removeAllVars(AbstractPageEl _ip) {
        super.removeAllVars(_ip);
        StringMap<LoopVariable> v_ = _ip.getVars();
        v_.removeKey(variableName);
        StringMap<LocalVariable> vInfo_ = _ip.getValueVars();
        vInfo_.removeKey(variableName);
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

    public ExpressionLanguage getEl() {
        return new ExpressionLanguage(opList);
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


    @Override
    public void reduce(ContextEl _context) {
        ExecOperationNode r_ = opList.last();
        opList = ExpressionLanguage.getReducedNodes(r_);
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        LoopBlockStack c_ = ip_.getLastLoopIfPossible(this);
        if (c_ != null) {
            ip_.processVisitedLoop(c_,this,this,_cont);
            return;
        }
        Struct its_ = processLoop(_cont);
        if (_cont.callsOrException()) {
            return;
        }
        Struct iterStr_ = null;
        long length_ = CustList.INDEX_NOT_FOUND_ELT;
        boolean finished_ = false;
        ExecOperationNode el_ = opList.last();
        if (el_.getResultClass().isArray()) {
            length_ = getLength(its_,_cont);
            if (length_ == CustList.SIZE_EMPTY) {
                finished_ = true;
            }
            if (_cont.callsOrException()) {
                return;
            }
        } else {
            if (its_ == NullStruct.NULL_VALUE) {
                String npe_ = _cont.getStandards().getContent().getCoreNames().getAliasNullPe();
                _cont.setException(new ErrorStruct(_cont, npe_));
                return;
            }
            String locName_ = getIteratorVar(_cont);
            ip_.putInternVars(locName_, its_,_cont);
            ExpressionLanguage dyn_ = ip_.getCurrentEl(_cont,this, CustList.SECOND_INDEX,CustList.SECOND_INDEX);
            Argument arg_ = ExpressionLanguage.tryToCalculate(_cont,dyn_,0);
            if (_cont.callsOrException()) {
                return;
            }
            iterStr_ = arg_.getStruct();
        }
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setLabel(label);
        l_.setIndex(-1);
        l_.setFinished(finished_);
        l_.setExecBlock(this);
        l_.setExecLoop(this);
        l_.setCurrentVisitedBlock(this);
        l_.setStructIterator(iterStr_);
        l_.setMaxIteration(length_);
        l_.setContainer(its_);
        ip_.addBlock(l_);
        ip_.clearCurrentEls();
        l_.setEvaluatingKeepLoop(true);
        String className_ = ip_.formatVarType(importedClassName, _cont);
        Struct struct_ = ExecClassArgumentMatching.defaultValue(className_, _cont);
        LoopVariable lv_ = new LoopVariable();
        lv_.setIndex(-1);
        lv_.setIndexClassName(importedClassIndexName);
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        varsLoop_.put(variableName, lv_);
        ip_.putValueVar(variableName, LocalVariable.newLocalVariable(struct_,className_));
        if (iterStr_ != null) {
            iteratorHasNext(_cont,l_);
            return;
        }
        incrOrFinish(_cont, finished_,l_);
    }

    private int getLength(Struct _str,ContextEl _cont) {
        if (_str instanceof ArrayStruct) {
            return ((ArrayStruct)_str).getInstance().length;
        }
        String npe_ = _cont.getStandards().getContent().getCoreNames().getAliasNullPe();
        _cont.setException(new ErrorStruct(_cont, npe_));
        return -1;
    }
    private void incrOrFinish(ContextEl _cont, boolean _finished, LoopBlockStack _l) {
        AbstractPageEl ip_ = _cont.getLastPage();
        if (_finished) {
            ip_.clearCurrentEls();
            _cont.getCoverage().passLoop(_cont, new Argument(BooleanStruct.of(false)));
            _l.setEvaluatingKeepLoop(false);
            _l.setFinished(true);
            return;
        }
        _cont.getCoverage().passLoop(_cont, new Argument(BooleanStruct.of(true)));
        incrementLoop(_cont, _l);
    }

}
