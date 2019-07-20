package code.formathtml.util;

import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.structs.DisplayableStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.formathtml.Configuration;
import code.formathtml.ElRenderUtil;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendDynOperationNode;
import code.util.CustList;
import code.util.StringList;

public final class BeanCustLgNames extends BeanLgNames {

    private String iteratorVar;
    private String hasNextVar;
    private String nextVar;
    private String displayVar;


    private String iteratorTableVarCust;
    private String hasNextPairVarCust;
    private String nextPairVarCust;
    private String firstVarCust;
    private String secondVarCust;

    private CustList<RendDynOperationNode> expsIterator;
    private CustList<RendDynOperationNode> expsHasNext;
    private CustList<RendDynOperationNode> expsNext;
    private CustList<RendDynOperationNode> expsIteratorTableCust;
    private CustList<RendDynOperationNode> expsHasNextPairCust;
    private CustList<RendDynOperationNode> expsNextPairCust;
    private CustList<RendDynOperationNode> expsFirstCust;
    private CustList<RendDynOperationNode> expsSecondCust;
    @Override
    public void buildOther() {

    }

    public void buildIterables(Configuration _context) {
        ContextEl context_ = _context.getContext();
        _context.getImporting().add(new ImportingPage(false));
        context_.setAnalyzing(new AnalyzedPageEl());
        context_.getAnalyzing().setEnabledInternVars(true);
        String locName_ = context_.getNextTempVar();
        String exp_;
        LocalVariable locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasIterable(),"<?>"));
        _context.getInternVars().put(locName_, locVar_);
        iteratorVar = locName_;
        String simpleIterator_ = getAliasIterator();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(simpleIterator_,PARS));
        expsIterator = ElRenderUtil.getAnalyzedOperations(exp_,0, _context, Calculation.staticCalculation(true));
        locName_ = context_.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasIteratorType(),"<?>"));
        _context.getInternVars().put(locName_, locVar_);
        hasNextVar = locName_;
        String hasNext_ = getAliasHasNext();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(hasNext_,PARS));
        expsHasNext = ElRenderUtil.getAnalyzedOperations(exp_, 0,_context, Calculation.staticCalculation(true));
        locName_ = context_.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasIteratorType(),"<?>"));
        _context.getInternVars().put(locName_, locVar_);
        nextVar = locName_;
        String next_ = getAliasNext();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(next_,PARS));
        expsNext = ElRenderUtil.getAnalyzedOperations(exp_, 0,_context, Calculation.staticCalculation(true));

        String nextPair_ = getAliasNextPair();
        String hasNextPair_ = getAliasHasNextPair();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasIterableTable(),"<?,?>"));
        _context.getInternVars().put(locName_, locVar_);
        iteratorTableVarCust= locName_;
        String iteratorTable_ = getAliasIteratorTable();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(iteratorTable_,PARS));
        expsIteratorTableCust= ElRenderUtil.getAnalyzedOperations(exp_, 0,_context, Calculation.staticCalculation(true));
        locName_ = context_.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasIteratorTableType(),"<?,?>"));
        _context.getInternVars().put(locName_, locVar_);
        hasNextPairVarCust= locName_;
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(hasNextPair_,PARS));
        expsHasNextPairCust= ElRenderUtil.getAnalyzedOperations(exp_, 0,_context, Calculation.staticCalculation(true));
        locName_ = context_.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasIteratorTableType(),"<?,?>"));
        _context.getInternVars().put(locName_, locVar_);
        nextPairVarCust= locName_;
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(nextPair_,PARS));
        expsNextPairCust= ElRenderUtil.getAnalyzedOperations(exp_, 0,_context, Calculation.staticCalculation(true));
        locName_ = context_.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasPairType(),"<?,?>"));
        _context.getInternVars().put(locName_, locVar_);
        firstVarCust= locName_;
        String first_ = getAliasGetFirst();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(first_,PARS));
        expsFirstCust= ElRenderUtil.getAnalyzedOperations(exp_, 0,_context, Calculation.staticCalculation(true));
        locName_ = context_.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasPairType(),"<?,?>"));
        _context.getInternVars().put(locName_, locVar_);
        secondVarCust= locName_;
        String second_ = getAliasGetSecond();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(second_,PARS));
        expsSecondCust= ElRenderUtil.getAnalyzedOperations(exp_, 0,_context, Calculation.staticCalculation(true));
        
        _context.clearPages();
    }
    public String getIteratorVar() {
        return iteratorVar;
    }
    public String getHasNextVar() {
        return hasNextVar;
    }
    public String getNextVar() {
        return nextVar;
    }
    public CustList<RendDynOperationNode> getExpsIterator() {
        return expsIterator;
    }
    public CustList<RendDynOperationNode> getExpsHasNext() {
        return expsHasNext;
    }
    public CustList<RendDynOperationNode> getExpsNext() {
        return expsNext;
    }

    public String getIteratorTableVarCust() {
        return iteratorTableVarCust;
    }

    public String getHasNextPairVarCust() {
        return hasNextPairVarCust;
    }

    public String getNextPairVarCust() {
        return nextPairVarCust;
    }

    public String getFirstVarCust() {
        return firstVarCust;
    }

    public String getSecondVarCust() {
        return secondVarCust;
    }

    public CustList<RendDynOperationNode> getExpsIteratorTableCust() {
        return expsIteratorTableCust;
    }

    public CustList<RendDynOperationNode> getExpsHasNextPairCust() {
        return expsHasNextPairCust;
    }

    public CustList<RendDynOperationNode> getExpsNextPairCust() {
        return expsNextPairCust;
    }

    public CustList<RendDynOperationNode> getExpsFirstCust() {
        return expsFirstCust;
    }

    public CustList<RendDynOperationNode> getExpsSecondCust() {
        return expsSecondCust;
    }

    public String processString(Argument _arg, Configuration _cont) {
        Struct struct_ = _arg.getStruct();
        return ((DisplayableStruct)struct_).getDisplayedString(_cont).getInstance();
    }
}
