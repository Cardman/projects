package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.coverage.AbstractCoverageResult;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.LoopBlockStack;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.WithNotEmptyEl;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class ExecForEachTable extends ExecBracedBlock implements ExecLoop, WithNotEmptyEl {

    private String label;
    private int labelOffset;

    private final String classNameFirst;

    private String importedClassNameFirst;

    private int classNameOffsetFirst;

    private final String classNameSecond;

    private String importedClassNameSecond;

    private int classNameOffsetSecond;

    private String importedClassIndexName;

    private final String variableNameFirst;

    private int variableNameOffsetFirst;

    private final String variableNameSecond;

    private int variableNameOffsetSecond;

    private final String expression;

    private int expressionOffset;

    private CustList<ExecOperationNode> opList;

    private CustList<PartOffset> partOffsetsFirst;

    private CustList<PartOffset> partOffsetsSecond;

    public ExecForEachTable(OffsetsBlock _offset, String label, int labelOffset, String classNameFirst, String importedClassNameFirst, int classNameOffsetFirst, String classNameSecond, String importedClassNameSecond, int classNameOffsetSecond, String importedClassIndexName, String variableNameFirst, int variableNameOffsetFirst, String variableNameSecond, int variableNameOffsetSecond, String expression, int expressionOffset, CustList<ExecOperationNode> opList, CustList<PartOffset> partOffsetsFirst, CustList<PartOffset> partOffsetsSecond) {
        super(_offset);
        this.label = label;
        this.labelOffset = labelOffset;
        this.classNameFirst = classNameFirst;
        this.importedClassNameFirst = importedClassNameFirst;
        this.classNameOffsetFirst = classNameOffsetFirst;
        this.classNameSecond = classNameSecond;
        this.importedClassNameSecond = importedClassNameSecond;
        this.classNameOffsetSecond = classNameOffsetSecond;
        this.importedClassIndexName = importedClassIndexName;
        this.variableNameFirst = variableNameFirst;
        this.variableNameOffsetFirst = variableNameOffsetFirst;
        this.variableNameSecond = variableNameSecond;
        this.variableNameOffsetSecond = variableNameOffsetSecond;
        this.expression = expression;
        this.expressionOffset = expressionOffset;
        this.opList = opList;
        this.partOffsetsFirst = partOffsetsFirst;
        this.partOffsetsSecond = partOffsetsSecond;
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        AbstractCoverageResult result_ = _cont.getCoverage().getCoverLoops(this);
        String tag_;
        if (result_.isFullCovered()) {
            tag_ = "<span class=\"f\">";
        } else if (result_.isPartialCovered()) {
            tag_ = "<span class=\"p\">";
        } else {
            tag_ = "<span class=\"n\">";
        }
        int off_ = getOffset().getOffsetTrim();
        _parts.add(new PartOffset(tag_,off_));
        KeyWords keyWords_ = _cont.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (StringList.quickEq(classNameFirst.trim(), keyWordVar_)) {
            tag_ = "<b title=\""+ElUtil.transform(importedClassNameFirst)+"\">";
            _parts.add(new PartOffset(tag_,classNameOffsetFirst));
            tag_ = "</b>";
            _parts.add(new PartOffset(tag_,classNameOffsetFirst+ _cont.getKeyWords().getKeyWordFor().length()));
        } else {
            _parts.addAllElts(partOffsetsFirst);
        }
        tag_ = "<a name=\"m"+ variableNameOffsetFirst +"\">";
        _parts.add(new PartOffset(tag_,variableNameOffsetFirst));
        tag_ = "</a>";
        _parts.add(new PartOffset(tag_,variableNameOffsetFirst+variableNameFirst.length()));
        if (StringList.quickEq(classNameSecond.trim(), keyWordVar_)) {
            tag_ = "<b title=\""+ElUtil.transform(importedClassNameSecond)+"\">";
            _parts.add(new PartOffset(tag_,classNameOffsetSecond));
            tag_ = "</b>";
            _parts.add(new PartOffset(tag_,classNameOffsetSecond+ _cont.getKeyWords().getKeyWordFor().length()));
        } else {
            _parts.addAllElts(partOffsetsSecond);
        }
        tag_ = "<a name=\"m"+ variableNameOffsetSecond +"\">";
        _parts.add(new PartOffset(tag_,variableNameOffsetSecond));
        tag_ = "</a>";
        _parts.add(new PartOffset(tag_,variableNameOffsetSecond+variableNameSecond.length()));
        tag_ = "</span>";
        _parts.add(new PartOffset(tag_,variableNameOffsetSecond+ variableNameSecond.length()));
        off_ = expressionOffset;
        int offsetEndBlock_ = off_ + expression.length();
        ElUtil.buildCoverageReport(_cont,off_,this,opList,offsetEndBlock_,_parts);
        _cont.getCoverage().getLoopVars().put(variableNameFirst,variableNameOffsetFirst);
        _cont.getCoverage().getLoopVars().put(variableNameSecond,variableNameOffsetSecond);
        refLabel(_parts,label,labelOffset);
    }

    @Override
    public void processLastElementLoop(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        StringMap<LoopVariable> vars_ = ip_.getVars();
        LoopBlockStack l_ = (LoopBlockStack) ip_.getLastStack();
        l_.setEvaluatingKeepLoop(true);
        ConditionReturn has_ = iteratorHasNext(_conf);
        if (has_ == ConditionReturn.CALL_EX) {
            return;
        }
        boolean hasNext_ = has_ == ConditionReturn.YES;

        if (hasNext_) {
            _conf.getCoverage().passExecLoop(_conf, new Argument(BooleanStruct.of(true)));
            incrementLoop(_conf, l_, vars_);
        } else {
            _conf.getCoverage().passExecLoop(_conf, new Argument(BooleanStruct.of(false)));
            _conf.getLastPage().clearCurrentEls();
            l_.setFinished(true);
            l_.setEvaluatingKeepLoop(false);
        }
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, int _indexProcess) {
        if (_indexProcess == 0) {
            return getEl();
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
    public void reduce(ContextEl _context) {
        ExecOperationNode r_ = opList.last();
        opList = ElUtil.getReducedNodes(r_);
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
        Struct iterStr_;
        long length_ = CustList.INDEX_NOT_FOUND_ELT;
        Classes cls_ = _cont.getClasses();
        String locName_ = cls_.getIteratorTableVarCust();
        LocalVariable locVar_ = LocalVariable.newLocalVariable(its_,_cont);
        _cont.getLastPage().getInternVars().put(locName_, locVar_);
        ExpressionLanguage dyn_ = _cont.getLastPage().getCurrentEl(_cont,this, CustList.SECOND_INDEX,CustList.SECOND_INDEX);
        Argument arg_ = ElUtil.tryToCalculate(_cont,dyn_,0);
        if (_cont.callsOrException()) {
            return;
        }
        iterStr_ = arg_.getStruct();
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setLabel(label);
        l_.setIndex(-1);
        l_.setFinished(false);
        l_.setExecBlock(this);
        l_.setCurrentVisitedBlock(this);
        l_.setStructIterator(iterStr_);
        l_.setMaxIteration(length_);
        ip_.addBlock(l_);
        ip_.clearCurrentEls();
        l_.setEvaluatingKeepLoop(true);
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        String className_;
        className_ = ip_.formatVarType(importedClassNameFirst, _cont);
        LoopVariable lv_ = LoopVariable.newLoopVariable(PrimitiveTypeUtil.defaultValue(className_,_cont),className_);
        lv_.setIndex(-1);
        lv_.setIndexClassName(importedClassIndexName);
        lv_.setContainer(its_);
        varsLoop_.put(variableNameFirst, lv_);
        className_ = ip_.formatVarType(importedClassNameSecond, _cont);
        lv_ = LoopVariable.newLoopVariable(PrimitiveTypeUtil.defaultValue(className_,_cont),className_);
        lv_.setIndex(-1);
        lv_.setIndexClassName(importedClassIndexName);
        lv_.setContainer(its_);
        varsLoop_.put(variableNameSecond, lv_);
        iteratorHasNext(_cont);
    }
    private Struct processLoop(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        ip_.setGlobalOffset(expressionOffset);
        ip_.setOffset(0);
        ExpressionLanguage el_ = ip_.getCurrentEl(_conf, this, CustList.FIRST_INDEX, CustList.FIRST_INDEX);
        Argument arg_ = ElUtil.tryToCalculate(_conf,el_,0);
        if (_conf.callsOrException()) {
            return NullStruct.NULL_VALUE;
        }
        Struct ito_ = arg_.getStruct();
        if (ito_== NullStruct.NULL_VALUE) {
            String npe_ = _conf.getStandards().getAliasNullPe();
            _conf.setException(new ErrorStruct(_conf, npe_));
        }
        return ito_;

    }
    @Override
    public void exitStack(ContextEl _context) {
        processLastElementLoop(_context);
    }

    @Override
    public void removeAllVars(AbstractPageEl _ip) {
        super.removeAllVars(_ip);
        StringMap<LoopVariable> v_ = _ip.getVars();
        v_.removeKey(variableNameFirst);
        v_.removeKey(variableNameSecond);
    }

    private void incrementLoop(ContextEl _conf, LoopBlockStack _l,
                               StringMap<LoopVariable> _vars) {
        _l.setIndex(_l.getIndex() + 1);
        Classes cls_ = _conf.getClasses();
        Struct iterator_ = _l.getStructIterator();
        AbstractPageEl call_ = _conf.getLastPage();
        if (call_.sizeEl() < 2) {
            String locName_ = cls_.getNextPairVarCust();
            LocalVariable locVar_ = LocalVariable.newLocalVariable(iterator_,_conf);
            _conf.getLastPage().getInternVars().put(locName_, locVar_);
        }
        ExpressionLanguage nextEl_ = call_.getCurrentEl(_conf,this, CustList.SECOND_INDEX, 3);
        ElUtil.tryToCalculate(_conf,nextEl_,0);
        if (_conf.callsOrException()) {
            return;
        }
        String classNameFirst_ = _conf.getLastPage().formatVarType(importedClassNameFirst, _conf);
        if (call_.sizeEl() < 3) {
            String locName_ = cls_.getFirstVarCust();
            Struct value_ = call_.getValue(1).getStruct();
            LocalVariable locVar_ = LocalVariable.newLocalVariable(value_,_conf);
            _conf.getLastPage().getInternVars().put(locName_, locVar_);
        }
        ExpressionLanguage firstEl_ = call_.getCurrentEl(_conf,this, 2, 4);
        Argument arg_ = ElUtil.tryToCalculate(_conf,firstEl_,0);
        if (_conf.callsOrException()) {
            return;
        }
        if (!Templates.checkQuick(classNameFirst_, arg_, _conf)) {
            return;
        }
        String classNameSecond_ = _conf.getLastPage().formatVarType(importedClassNameSecond, _conf);
        if (call_.sizeEl() < 4) {
            LoopVariable lv_ = _vars.getVal(variableNameFirst);
            lv_.setStruct(arg_.getStruct());
            lv_.setIndex(lv_.getIndex() + 1);
            String locName_ = cls_.getSecondVarCust();
            Struct value_ = call_.getValue(1).getStruct();
            LocalVariable locVar_ = LocalVariable.newLocalVariable(value_,_conf);
            _conf.getLastPage().getInternVars().put(locName_, locVar_);
        }
        ExpressionLanguage secondEl_ = call_.getCurrentEl(_conf,this, 3, 5);
        arg_ = ElUtil.tryToCalculate(_conf,secondEl_,0);
        if (_conf.callsOrException()) {
            return;
        }
        if (!Templates.checkQuick(classNameSecond_, arg_, _conf)) {
            return;
        }
        LoopVariable lv_ = _vars.getVal(variableNameSecond);
        lv_.setStruct(arg_.getStruct());
        lv_.setIndex(lv_.getIndex() + 1);
        call_.clearCurrentEls();
        call_.getReadWrite().setBlock(getFirstChild());
        _l.setEvaluatingKeepLoop(false);
    }
    private ConditionReturn iteratorHasNext(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        LoopBlockStack l_ = (LoopBlockStack) ip_.getLastStack();
        Struct strIter_ = l_.getStructIterator();
        Classes cls_ = _conf.getClasses();
        String locName_ = cls_.getHasNextPairVarCust();
        LocalVariable locVar_ = LocalVariable.newLocalVariable(strIter_,_conf);
        _conf.getLastPage().getInternVars().put(locName_, locVar_);
        ExpressionLanguage dyn_ = _conf.getLastPage().getCurrentEl(_conf,this, CustList.FIRST_INDEX, 2);
        Argument arg_ = ElUtil.tryToCalculate(_conf,dyn_,0);
        if (_conf.callsOrException()) {
            return ConditionReturn.CALL_EX;
        }
        if (BooleanStruct.isTrue(arg_.getStruct())) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }

    public ExpressionLanguage getEl() {
        return new ExpressionLanguage(opList);
    }

    public CustList<ExecOperationNode> getOpList() {
        return opList;
    }
}
