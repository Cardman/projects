package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.LoopBlockStack;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.IndexConstants;

public final class ExecForEachTable extends ExecBracedBlock implements ExecLoop, WithNotEmptyEl {

    private String label;

    private String importedClassNameFirst;

    private String importedClassNameSecond;

    private String importedClassIndexName;

    private final String variableNameFirst;

    private final String variableNameSecond;

    private int expressionOffset;

    private CustList<ExecOperationNode> opList;

    public ExecForEachTable(String _label, String _importedClassNameFirst, String _importedClassNameSecond, String _importedClassIndexName,
                            String _variableNameFirst, String _variableNameSecond,
                            int _expressionOffset, CustList<ExecOperationNode> _opList, int _offsetTrim) {
        super(_offsetTrim);
        this.label = _label;
        this.importedClassNameFirst = _importedClassNameFirst;
        this.importedClassNameSecond = _importedClassNameSecond;
        this.importedClassIndexName = _importedClassIndexName;
        this.variableNameFirst = _variableNameFirst;
        this.variableNameSecond = _variableNameSecond;
        this.expressionOffset = _expressionOffset;
        this.opList = _opList;
    }

    @Override
    public void processLastElementLoop(ContextEl _conf, LoopBlockStack _l) {
        _l.setEvaluatingKeepLoop(true);
        ConditionReturn has_ = iteratorHasNext(_conf, _l);
        if (has_ == ConditionReturn.CALL_EX) {
            return;
        }
        boolean hasNext_ = has_ == ConditionReturn.YES;

        if (hasNext_) {
            _conf.getCoverage().passLoop(_conf, this, new Argument(BooleanStruct.of(true)));
            incrementLoop(_conf, _l);
        } else {
            _conf.getCoverage().passLoop(_conf, this, new Argument(BooleanStruct.of(false)));
            _conf.getLastPage().clearCurrentEls();
            _l.setFinished(true);
            _l.setEvaluatingKeepLoop(false);
        }
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, int _indexProcess) {
        if (_indexProcess == 0) {
            return new ExpressionLanguage(opList);
        }
        Classes cls_ = _context.getClasses();
        if (_indexProcess == 1) {
            return new ExpressionLanguage(cls_.getExpsIteratorTableCust());
        }
        if (_indexProcess == 2) {
            return new ExpressionLanguage(cls_.getExpsHasNextPairCust());
        }
        if (_indexProcess == 3) {
            return new ExpressionLanguage(cls_.getExpsNextPairCust());
        }
        if (_indexProcess == 4) {
            return new ExpressionLanguage(cls_.getExpsFirstCust());
        }
        return new ExpressionLanguage(cls_.getExpsSecondCust());
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
        long length_ = IndexConstants.INDEX_NOT_FOUND_ELT;
        Classes cls_ = _cont.getClasses();
        String locName_ = cls_.getIteratorTableVarCust();
        _cont.getLastPage().putInternVars(locName_, its_,_cont);
        ExpressionLanguage dyn_ = _cont.getLastPage().getCurrentEl(_cont,this, IndexConstants.SECOND_INDEX, IndexConstants.SECOND_INDEX);
        Argument arg_ = ExpressionLanguage.tryToCalculate(_cont,dyn_,0);
        if (_cont.callsOrException()) {
            return;
        }
        Struct iterStr_ = arg_.getStruct();
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setLabel(label);
        l_.setIndex(-1);
        l_.setFinished(false);
        l_.setExecBlock(this);
        l_.setExecLoop(this);
        l_.setCurrentVisitedBlock(this);
        l_.setStructIterator(iterStr_);
        l_.setMaxIteration(length_);
        ip_.addBlock(l_);
        ip_.clearCurrentEls();
        l_.setEvaluatingKeepLoop(true);
        l_.setContainer(its_);
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        String className_;
        className_ = _cont.formatVarType(importedClassNameFirst);
        Struct defFirst_ = ExecClassArgumentMatching.defaultValue(className_, _cont);
        LoopVariable lv_ = new LoopVariable();
        lv_.setIndex(-1);
        lv_.setIndexClassName(importedClassIndexName);
        varsLoop_.put(variableNameFirst, lv_);
        ip_.putValueVar(variableNameFirst, LocalVariable.newLocalVariable(defFirst_,className_));
        className_ = _cont.formatVarType(importedClassNameSecond);
        Struct defSecond_ = ExecClassArgumentMatching.defaultValue(className_, _cont);
        lv_ = new LoopVariable();
        lv_.setIndex(-1);
        lv_.setIndexClassName(importedClassIndexName);
        varsLoop_.put(variableNameSecond, lv_);
        ip_.putValueVar(variableNameSecond, LocalVariable.newLocalVariable(defSecond_,className_));
        iteratorHasNext(_cont, l_);
    }
    private Struct processLoop(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        ip_.setGlobalOffset(expressionOffset);
        ip_.setOffset(0);
        ExpressionLanguage el_ = ip_.getCurrentEl(_conf, this, IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX);
        Argument arg_ = ExpressionLanguage.tryToCalculate(_conf,el_,0);
        if (_conf.callsOrException()) {
            return NullStruct.NULL_VALUE;
        }
        Struct ito_ = arg_.getStruct();
        if (ito_== NullStruct.NULL_VALUE) {
            String npe_ = _conf.getStandards().getContent().getCoreNames().getAliasNullPe();
            _conf.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, npe_)));
        }
        return ito_;

    }

    @Override
    public void removeAllVars(AbstractPageEl _ip) {
        super.removeAllVars(_ip);
        StringMap<LoopVariable> v_ = _ip.getVars();
        v_.removeKey(variableNameFirst);
        v_.removeKey(variableNameSecond);
        StringMap<LocalVariable> vInfo_ = _ip.getValueVars();
        vInfo_.removeKey(variableNameFirst);
        vInfo_.removeKey(variableNameSecond);
    }

    private void incrementLoop(ContextEl _conf, LoopBlockStack _l) {
        _l.setIndex(_l.getIndex() + 1);
        Classes cls_ = _conf.getClasses();
        AbstractPageEl call_ = _conf.getLastPage();
        if (call_.sizeEl() < 2) {
            String locName_ = cls_.getNextPairVarCust();
            _conf.getLastPage().putInternVars(locName_, _l.getStructIterator(),_conf);
        }
        ExpressionLanguage nextEl_ = call_.getCurrentEl(_conf,this, IndexConstants.SECOND_INDEX, 3);
        ExpressionLanguage.tryToCalculate(_conf,nextEl_,0);
        if (_conf.callsOrException()) {
            return;
        }
        if (call_.sizeEl() < 3) {
            String locName_ = cls_.getFirstVarCust();
            Struct value_ = call_.getValue(1).getStruct();
            _conf.getLastPage().putInternVars(locName_, value_,_conf);
        }
        ExpressionLanguage firstEl_ = call_.getCurrentEl(_conf,this, 2, 4);
        Argument arg_ = ExpressionLanguage.tryToCalculate(_conf,firstEl_,0);
        if (_conf.callsOrException()) {
            return;
        }
        if (call_.sizeEl() < 4) {
            ExecTemplates.setValue(_conf,variableNameFirst, arg_,-1, _conf.getLastPage().getCache(), _conf.getLastPage().getValueVars());
            ExecTemplates.incrIndexLoop(_conf, variableNameFirst, -1, _conf.getLastPage().getCache(), _conf.getLastPage().getVars());
            if (_conf.callsOrException()) {
                return;
            }
            String locName_ = cls_.getSecondVarCust();
            Struct value_ = call_.getValue(1).getStruct();
            _conf.getLastPage().putInternVars(locName_, value_,_conf);
        }
        ExpressionLanguage secondEl_ = call_.getCurrentEl(_conf,this, 3, 5);
        arg_ = ExpressionLanguage.tryToCalculate(_conf,secondEl_,0);
        if (_conf.callsOrException()) {
            return;
        }
        ExecTemplates.setValue(_conf,variableNameSecond, arg_,-1, _conf.getLastPage().getCache(), _conf.getLastPage().getValueVars());
        ExecTemplates.incrIndexLoop(_conf, variableNameSecond, -1, _conf.getLastPage().getCache(), _conf.getLastPage().getVars());
        if (_conf.callsOrException()) {
            return;
        }
        call_.clearCurrentEls();
        call_.setBlock(getFirstChild());
        _l.setEvaluatingKeepLoop(false);
    }
    private ConditionReturn iteratorHasNext(ContextEl _conf, LoopBlockStack _l) {
        Classes cls_ = _conf.getClasses();
        String locName_ = cls_.getHasNextPairVarCust();
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

}
