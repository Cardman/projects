package code.formathtml.util;

import code.bean.BeanInfo;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.VariableSuffix;
import code.formathtml.Configuration;
import code.formathtml.ElRenderUtil;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendDynOperationNode;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

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
    private String beforeDisplayingVar;

    private String putVarCust;
    private String putVarCustKey;
    private String putVarCustValue;
    private String putAllVarCust;
    private String putAllVarCustArg;
    private String getValVar;
    private String getValVarArg;
    private String getFormsVar;
    private String setFormsVarArg;
    private String setFormsVar;
    private String getDataBaseVar;
    private String setDataBaseVarArg;
    private String setDataBaseVar;

    private CustList<RendDynOperationNode> expsIterator;
    private CustList<RendDynOperationNode> expsHasNext;
    private CustList<RendDynOperationNode> expsNext;
    private CustList<RendDynOperationNode> expsIteratorTableCust;
    private CustList<RendDynOperationNode> expsHasNextPairCust;
    private CustList<RendDynOperationNode> expsNextPairCust;
    private CustList<RendDynOperationNode> expsFirstCust;
    private CustList<RendDynOperationNode> expsSecondCust;
    private CustList<RendDynOperationNode> expsBeforeDisplaying;

    private CustList<RendDynOperationNode> expsPut;
    private CustList<RendDynOperationNode> expsPutAll;
    private CustList<RendDynOperationNode> expsGetVal;
    private CustList<RendDynOperationNode> expsGetForms;
    private CustList<RendDynOperationNode> expsSetForms;
    private CustList<RendDynOperationNode> expsGetDataBase;
    private CustList<RendDynOperationNode> expsSetDataBase;
    private CustList<RendDynOperationNode> opsMap;
    private String aliasMapKeys = "keys";
    private String aliasMapValues = "values";
    private String aliasMapIndexOfEntry = "indexOfEntry";
    private String aliasMapAddEntry = "addEntry";
    private String aliasMapGetValue = "getValue";
    private String aliasMapFirstValue = "firstValue";
    private String aliasMapLastValue = "lastValue";
    private String aliasMapSetValue = "setValue";
    private String aliasMapPut = "put";
    private String aliasMapContains = "contains";
    private String aliasMapPutAll = "putAll";
    private String aliasMapGetVal = "getVal";
    private String aliasMapRemoveKey = "removeKey";
    private String aliasMapGetKey = "getKey";
    private String aliasMapFirstKey = "firstKey";
    private String aliasMapLastKey = "lastKey";
    private String aliasMapSetKey = "setKey";
    private String aliasMapSize = "size";
    private String aliasMapIsEmpty = "isEmpty";
    private String aliasMapClear = "clear";
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

        locName_ = context_.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasBean()));
        _context.getInternVars().put(locName_, locVar_);
        beforeDisplayingVar = locName_;
        String beforeDisplaying_ = getAliasBeforeDisplaying();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(beforeDisplaying_,PARS));
        expsBeforeDisplaying= ElRenderUtil.getAnalyzedOperations(exp_, 0,_context, Calculation.staticCalculation(true));

        locName_ = context_.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasStringMapObject()));
        _context.getInternVars().put(locName_, locVar_);
        putVarCust = locName_;
        locName_ = context_.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasString()));
        _context.getInternVars().put(locName_, locVar_);
        putVarCustKey = locName_;
        locName_ = context_.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasObject()));
        _context.getInternVars().put(locName_, locVar_);
        putVarCustValue = locName_;
        String put_ = getAliasMapPut();
        exp_ = StringList.concat(putVarCust, LOC_VAR, StringList.concat(put_,"(",putVarCustKey,",",putVarCustValue,")"));
        expsPut= ElRenderUtil.getAnalyzedOperations(exp_, 0,_context, Calculation.staticCalculation(true));

        locName_ = context_.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasStringMapObject()));
        _context.getInternVars().put(locName_, locVar_);
        putAllVarCust = locName_;
        locName_ = context_.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasStringMapObject()));
        _context.getInternVars().put(locName_, locVar_);
        putAllVarCustArg = locName_;
        String putAll_ = getAliasMapPutAll();
        exp_ = StringList.concat(putAllVarCust, LOC_VAR, StringList.concat(putAll_,"(",putAllVarCustArg,")"));
        expsPutAll= ElRenderUtil.getAnalyzedOperations(exp_, 0,_context, Calculation.staticCalculation(true));

        locName_ = context_.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasStringMapObject()));
        _context.getInternVars().put(locName_, locVar_);
        getValVar = locName_;
        locName_ = context_.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasString()));
        _context.getInternVars().put(locName_, locVar_);
        getValVarArg = locName_;
        String getVal_ = getAliasMapGetVal();
        exp_ = StringList.concat(getValVar, LOC_VAR, StringList.concat(getVal_,"(",getValVarArg,")"));
        expsGetVal= ElRenderUtil.getAnalyzedOperations(exp_, 0,_context, Calculation.staticCalculation(true));

        locName_ = context_.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasBean()));
        _context.getInternVars().put(locName_, locVar_);
        setFormsVar = locName_;
        locName_ = context_.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasStringMapObject()));
        _context.getInternVars().put(locName_, locVar_);
        setFormsVarArg = locName_;
        String setForms_ = getAliasSetForms();
        exp_ = StringList.concat(setFormsVar, LOC_VAR, StringList.concat(setForms_,"(",setFormsVarArg,")"));
        expsSetForms= ElRenderUtil.getAnalyzedOperations(exp_, 0,_context, Calculation.staticCalculation(true));

        locName_ = context_.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasBean()));
        _context.getInternVars().put(locName_, locVar_);
        getFormsVar = locName_;
        String getForms_ = getAliasGetForms();
        exp_ = StringList.concat(getFormsVar, LOC_VAR, StringList.concat(getForms_,PARS));
        expsGetForms= ElRenderUtil.getAnalyzedOperations(exp_, 0,_context, Calculation.staticCalculation(true));

        locName_ = context_.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasBean()));
        _context.getInternVars().put(locName_, locVar_);
        setDataBaseVar = locName_;
        locName_ = context_.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasObject()));
        _context.getInternVars().put(locName_, locVar_);
        setDataBaseVarArg = locName_;
        String setDataBase_ = getAliasSetDataBase();
        exp_ = StringList.concat(setDataBaseVar, LOC_VAR, StringList.concat(setDataBase_,"(",setDataBaseVarArg,")"));
        expsSetDataBase= ElRenderUtil.getAnalyzedOperations(exp_, 0,_context, Calculation.staticCalculation(true));

        locName_ = context_.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasBean()));
        _context.getInternVars().put(locName_, locVar_);
        getDataBaseVar = locName_;
        String getDataBase_ = getAliasGetDataBase();
        exp_ = StringList.concat(getDataBaseVar, LOC_VAR, StringList.concat(getDataBase_,PARS));
        expsGetDataBase= ElRenderUtil.getAnalyzedOperations(exp_, 0,_context, Calculation.staticCalculation(true));

        String aliasStringMapObject_ = getAliasStringMapObject();
        String keyWordNew_ = _context.getKeyWords().getKeyWordNew();
        opsMap = ElRenderUtil.getAnalyzedOperations(StringList.concat(keyWordNew_, " ", aliasStringMapObject_, "()"), 0, _context, Calculation.staticCalculation(false));

        _context.clearPages();
    }

    @Override
    public StringMap<String> buildFiles(ContextEl _context) {
        StringMap<String> files_ = super.buildFiles(_context);
        KeyWords keyWords_ = _context.getKeyWords();
        String public_ = keyWords_.getKeyWordPublic();
        String private_ = keyWords_.getKeyWordPrivate();
        String interface_ = keyWords_.getKeyWordInterface();
        String class_ = keyWords_.getKeyWordClass();
        String return_ = keyWords_.getKeyWordReturn();
        String if_ = keyWords_.getKeyWordIf();
        String for_ = keyWords_.getKeyWordFor();
        String null_ = keyWords_.getKeyWordNull();
        String new_ = keyWords_.getKeyWordNew();
        String int_ = getAliasPrimInteger();
        String endLine_ = String.valueOf(_context.getOptions().getEndLine());
        String suffix_ = String.valueOf(_context.getOptions().getSuffix());
        String suffixParam_ = "";
        if (_context.getOptions().getSuffixVar() == VariableSuffix.DISTINCT) {
            suffixParam_ = StringList.concat(suffix_,".",suffix_);
        } else if (_context.getOptions().getSuffixVar() != VariableSuffix.NONE) {
            suffixParam_ = suffix_;
        }
        StringBuilder file_ = new StringBuilder();
        file_.append(public_).append(" ").append(class_).append(" ").append(getAliasBean()).append("{");
        String string_ = getAliasString();
        String language_ = getAliasLanguage();
        String scope_ = getAliasScope();
        String dataBase_ = getAliasDataBaseField();
        String this_ = keyWords_.getKeyWordThis();
        String object_ = getAliasObject();
        String forms_ = getAliasForms();
        String boolean_ = getAliasPrimBoolean();
        String length_ = getAliasLength();
        file_.append(" ").append(private_).append(" ").append(string_).append(" ").append(language_).append(endLine_);
        file_.append(" ").append(private_).append(" ").append(string_).append(" ").append(scope_).append(endLine_);
        file_.append(" ").append(private_).append(" ").append(object_).append(" ").append(dataBase_).append(endLine_);
        file_.append(" ").append(private_).append(" ").append(ALIAS_STRING_MAP_OBJECT).append(" ").append(forms_).append(endLine_);
        String void_ = getAliasVoid();
        file_.append(" ").append(public_).append(" ").append(void_).append(" ").append(getAliasBeforeDisplaying()).append("(){");
        file_.append(" ").append("}");
        file_.append(" ").append(public_).append(" ").append(string_).append(" ").append(getAliasGetLanguage()).append("(){");
        file_.append("  ").append(return_).append(" ").append(language_).append(endLine_);
        file_.append(" ").append("}");
        file_.append(" ").append(public_).append(" ").append(void_).append(" ").append(getAliasSetLanguage()).append("(").append(string_).append(" ").append(language_).append(")").append("{");
        file_.append("  ").append(this_).append(".").append(language_).append("=").append(language_).append(suffixParam_).append(endLine_);
        file_.append(" ").append("}");
        file_.append(" ").append(public_).append(" ").append(string_).append(" ").append(getAliasGetScope()).append("(){");
        file_.append("  ").append(return_).append(" ").append(scope_).append(endLine_);
        file_.append(" ").append("}");
        file_.append(" ").append(public_).append(" ").append(void_).append(" ").append(getAliasSetScope()).append("(").append(string_).append(" ").append(scope_).append(")").append("{");
        file_.append("  ").append(this_).append(".").append(scope_).append("=").append(scope_).append(suffixParam_).append(endLine_);
        file_.append(" ").append("}");
        file_.append(" ").append(public_).append(" ").append(object_).append(" ").append(getAliasGetDataBase()).append("(){");
        file_.append("  ").append(return_).append(" ").append(dataBase_).append(endLine_);
        file_.append(" ").append("}");
        file_.append(" ").append(public_).append(" ").append(void_).append(" ").append(getAliasSetDataBase()).append("(").append(object_).append(" ").append(dataBase_).append(")").append("{");
        file_.append("  ").append(this_).append(".").append(dataBase_).append("=").append(dataBase_).append(suffixParam_).append(endLine_);
        file_.append(" ").append("}");
        file_.append(" ").append(public_).append(" ").append(ALIAS_STRING_MAP_OBJECT).append(" ").append(getAliasGetForms()).append("(){");
        file_.append("  ").append(return_).append(" ").append(forms_).append(endLine_);
        file_.append(" ").append("}");
        file_.append(" ").append(public_).append(" ").append(void_).append(" ").append(getAliasSetForms()).append("(").append(ALIAS_STRING_MAP_OBJECT).append(" ").append(forms_).append(")").append("{");
        file_.append("  ").append(this_).append(".").append(forms_).append("=").append(forms_).append(suffixParam_).append(endLine_);
        file_.append(" ").append("}");
        file_.append("}");
        files_.put(getAliasBean(), file_.toString());
        getPredefinedInterfacesInitOrder().add(getAliasBean());
        file_ = new StringBuilder();
        String keys_ = getAliasMapKeys();
        String values_ = getAliasMapValues();
        String indexOfEntry_ = getAliasMapIndexOfEntry();
        String addEntry_ = getAliasMapAddEntry();
        String getValue_ = getAliasMapGetValue();
        String setValue_ = getAliasMapSetValue();
        String put_ = getAliasMapPut();
        String putAll_ = getAliasMapPutAll();
        String getVal_ = getAliasMapGetVal();
        String removeKey_ = getAliasMapRemoveKey();
        String getKey_ = getAliasMapGetKey();
        String setKey_ = getAliasMapSetKey();
        file_.append(public_).append(" ").append(class_).append(" ").append(ALIAS_STRING_MAP_OBJECT).append("{");
        file_.append(private_).append(" ").append(string_).append("[] ").append(keys_).append("=").append(new_).append(" ").append(string_).append("[0]").append(endLine_);
        file_.append(private_).append(" ").append(object_).append("[] ").append(values_).append("=").append(new_).append(" ").append(object_).append("[0]").append(endLine_);
        file_.append(public_).append(" ").append(string_).append("[] ").append(keys_).append("(){");
        file_.append(return_).append(" ").append(keys_).append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(string_).append(" ").append(aliasMapFirstKey).append("(){");
        file_.append(return_).append(" ").append(keys_).append("[0]").append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(string_).append(" ").append(aliasMapLastKey).append("(){");
        file_.append(return_).append(" ").append(keys_).append("[").append(keys_).append(".").append(length_).append("-1]").append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(object_).append("[] ").append(values_).append("(){");
        file_.append(return_).append(" ").append(values_).append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(object_).append(" ").append(aliasMapFirstValue).append("(){");
        file_.append(return_).append(" ").append(values_).append("[0]").append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(object_).append(" ").append(aliasMapLastValue).append("(){");
        file_.append(return_).append(" ").append(values_).append("[").append(values_).append(".").append(length_).append("-1]").append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(void_).append(" ").append(setKey_).append("(").append(int_).append(" ").append(tr("i",_context)).append(",").append(string_).append(" ").append(tr("k",_context)).append("){");
        file_.append(this_).append(".").append(keys_).append("[").append(trParam("i",_context)).append("]=").append(trParam("k",_context)).append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(string_).append(" ").append(getKey_).append("(").append(int_).append(" ").append(tr("i",_context)).append("){");
        file_.append(return_).append(" ").append(this_).append(".").append(keys_).append("[").append(trParam("i",_context)).append("]").append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(void_).append(" ").append(setValue_).append("(").append(int_).append(" ").append(tr("i",_context)).append(",").append(object_).append(" ").append(tr("v",_context)).append("){");
        file_.append(this_).append(".").append(values_).append("[").append(trParam("i",_context)).append("]=").append(trParam("v",_context)).append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(object_).append(" ").append(getValue_).append("(").append(int_).append(" ").append(tr("i",_context)).append("){");
        file_.append(return_).append(" ").append(this_).append(".").append(values_).append("[").append(trParam("i",_context)).append("]").append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(void_).append(" ").append(aliasMapClear).append("(){");
        file_.append(keys_).append("=").append(new_).append(" ").append(string_).append("[0]").append(endLine_);
        file_.append(values_).append("=").append(new_).append(" ").append(object_).append("[0]").append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(boolean_).append(" ").append(aliasMapIsEmpty).append("(){");
        file_.append(return_).append(" ").append(keys_).append(".").append(length_).append("==0").append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(int_).append(" ").append(aliasMapSize).append("(){");
        file_.append(return_).append(" ").append(keys_).append(".").append(length_).append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(void_).append(" ").append(put_).append("(").append(string_).append(" ").append(tr("k",_context)).append(",").append(object_).append(" ").append(tr("v",_context)).append("){");
        file_.append(int_).append(" ").append(tr("index",_context)).append("=").append(indexOfEntry_).append("(").append(trParam("k",_context)).append(")").append(endLine_);
        file_.append(if_).append("(").append(trLoc("index",_context)).append("==-1){");
        file_.append(addEntry_).append("(").append(trParam("k",_context)).append(", ").append(trParam("v",_context)).append(")").append(endLine_);
        file_.append(return_).append(endLine_);
        file_.append("}");
        file_.append(setValue_).append("(").append(trLoc("index",_context)).append(", ").append(trParam("v",_context)).append(")").append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(boolean_).append(" ").append(aliasMapContains).append("(").append(string_).append(" ").append(tr("k",_context)).append("){");
        file_.append(return_).append(" ").append(indexOfEntry_).append("(").append(trParam("k",_context)).append(") != -1").append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(object_).append(" ").append(getVal_).append("(").append(string_).append(" ").append(tr("k",_context)).append("){");
        file_.append(int_).append(" ").append(tr("index",_context)).append("=").append(indexOfEntry_).append("(").append(trParam("k",_context)).append(")").append(endLine_);
        file_.append(if_).append("(").append(trLoc("index",_context)).append("==-1){");
        file_.append(return_).append(" ").append(null_).append(endLine_);
        file_.append("}");
        file_.append(return_).append(" ").append(getValue_).append("(").append(trLoc("index",_context)).append(")").append(endLine_);
        file_.append("}");

        file_.append(public_).append(" ").append(void_).append(" ").append(addEntry_).append("(").append(string_).append(" ").append(tr("k",_context)).append(",").append(object_).append(" ").append(tr("v",_context)).append("){");
        file_.append(string_).append("[] ").append(keys_).append("=").append(new_).append(" ").append(string_).append("[").append(this_).append(".").append(keys_).append(".").append(length_).append("+1]").append(endLine_);
        file_.append(for_).append("(").append(int_).append(" ").append(tr("i",_context)).append("=0").append(endLine_).append(trLoop("i",_context)).append("<").append(this_).append(".").append(keys_).append(".").append(length_).append(endLine_).append(trLoop("i",_context)).append("++){");
        file_.append(trLoc(keys_,_context)).append("[").append(trLoop("i",_context)).append("]=").append(this_).append(".").append(keys_).append("[").append(trLoop("i",_context)).append("]").append(endLine_);
        file_.append("}");
        file_.append(trLoc(keys_,_context)).append("[").append(this_).append(".").append(keys_).append(".").append(length_).append("]=").append(trParam("k",_context)).append(endLine_);
        file_.append(this_).append(".").append(keys_).append("=").append(trLoc(keys_,_context)).append(endLine_);
        file_.append(object_).append("[] ").append(values_).append("=").append(new_).append(" ").append(object_).append("[").append(this_).append(".").append(values_).append(".").append(length_).append("+1]").append(endLine_);
        file_.append(for_).append("(").append(int_).append(" ").append(tr("i",_context)).append("=0").append(endLine_).append(trLoop("i",_context)).append("<").append(this_).append(".").append(values_).append(".").append(length_).append(endLine_).append(trLoop("i",_context)).append("++){");
        file_.append(trLoc(values_,_context)).append("[").append(trLoop("i",_context)).append("]=").append(this_).append(".").append(values_).append("[").append(trLoop("i",_context)).append("]").append(endLine_);
        file_.append("}");
        file_.append(trLoc(values_,_context)).append("[").append(this_).append(".").append(values_).append(".").append(length_).append("]=").append(trParam("v",_context)).append(endLine_);
        file_.append(this_).append(".").append(values_).append("=").append(trLoc(values_,_context)).append(endLine_);
        file_.append("}");

        file_.append(public_).append(" ").append(int_).append(" ").append(indexOfEntry_).append("(").append(string_).append(" ").append(tr("k",_context)).append("){");
        file_.append(for_).append("(").append(int_).append(" ").append(tr("i",_context)).append("=0").append(endLine_).append(trLoop("i",_context)).append("<").append(this_).append(".").append(keys_).append(".").append(length_).append(endLine_).append(trLoop("i",_context)).append("++){");
        file_.append(if_).append("(").append(this_).append(".").append(keys_).append("[").append(trLoop("i",_context)).append("]==").append(trParam("k",_context)).append("){");
        file_.append(return_).append(" ").append(trLoop("i",_context)).append(endLine_);
        file_.append("}");
        file_.append("}");
        file_.append(return_).append(" -1").append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(void_).append(" ").append(putAll_).append("(").append(ALIAS_STRING_MAP_OBJECT).append(" ").append(tr("m",_context)).append("){");
        file_.append(int_).append(" ").append(tr("len",_context)).append("=").append(trParam("(m",_context)).append(").").append(keys_).append(".").append(length_).append(endLine_);
        file_.append(for_).append("(").append(int_).append(" ").append(tr("i",_context)).append("=0").append(endLine_).append(trLoop("i",_context)).append("<").append(trLoc("len",_context)).append(endLine_).append(trLoop("i",_context)).append("++){");
        file_.append(" ").append(put_).append("(").append(trParam("(m",_context)).append(").").append(keys_).append("[").append(trLoop("i",_context)).append("], ").append(trParam("(m",_context)).append(").").append(values_).append("[").append(trLoop("i",_context)).append("])").append(endLine_);
        file_.append("}");
        file_.append("}");
        file_.append(public_).append(" ").append(void_).append(" ").append(removeKey_).append("(").append(string_).append(" ").append(tr("k",_context)).append("){");
        file_.append(int_).append(" ").append(tr("index",_context)).append("=").append(indexOfEntry_).append("(").append(trParam("k",_context)).append(")").append(endLine_);
        file_.append(if_).append("(").append(trLoc("index",_context)).append("==-1){");
        file_.append(return_).append(endLine_);
        file_.append("}");
        file_.append(string_).append("[] ").append(keys_).append("=").append(new_).append(" ").append(string_).append("[").append(this_).append(".").append(keys_).append(".").append(length_).append("-1]").append(endLine_);
        file_.append(for_).append("(").append(int_).append(" ").append(tr("i",_context)).append("=0").append(endLine_).append(trLoop("i",_context)).append("<").append(trLoc("index",_context)).append(endLine_).append(trLoop("i",_context)).append("++){");
        file_.append(keys_).append("[").append(trLoop("i",_context)).append("]=").append(this_).append(".").append(keys_).append("[").append(trLoop("i",_context)).append("]").append(endLine_);
        file_.append("}");
        file_.append(for_).append("(").append(int_).append(" ").append(tr("i",_context)).append("=").append(trLoc("index",_context)).append("+1").append(endLine_).append(trLoop("i",_context)).append("<").append(this_).append(".").append(keys_).append(".").append(length_).append(endLine_).append(trLoop("i",_context)).append("++){");
        file_.append(keys_).append("[").append(trLoop("i",_context)).append("-1]=").append(this_).append(".").append(keys_).append("[").append(trLoop("i",_context)).append("]").append(endLine_);
        file_.append("}");
        file_.append(this_).append(".").append(keys_).append("=").append(keys_).append(endLine_);

        file_.append(object_).append("[] ").append(values_).append("=").append(new_).append(" ").append(object_).append("[").append(this_).append(".").append(values_).append(".").append(length_).append("-1]").append(endLine_);
        file_.append(for_).append("(").append(int_).append(" ").append(tr("i",_context)).append("=0").append(endLine_).append(trLoop("i",_context)).append("<").append(trLoc("index",_context)).append(endLine_).append(trLoop("i",_context)).append("++){");
        file_.append(values_).append("[").append(trLoop("i",_context)).append("]=").append(this_).append(".").append(values_).append("[").append(trLoop("i",_context)).append("]").append(endLine_);
        file_.append("}");
        file_.append(for_).append("(").append(int_).append(" ").append(tr("i",_context)).append("=").append(trLoc("index",_context)).append("+1").append(endLine_).append(trLoop("i",_context)).append("<").append(this_).append(".").append(values_).append(".").append(length_).append(endLine_).append(trLoop("i",_context)).append("++){");
        file_.append(values_).append("[").append(trLoop("i",_context)).append("-1]=").append(this_).append(".").append(values_).append("[").append(trLoop("i",_context)).append("]").append(endLine_);
        file_.append("}");
        file_.append(this_).append(".").append(values_).append("=").append(values_).append(endLine_);
        file_.append("}");
        file_.append("}");
        files_.put(ALIAS_STRING_MAP_OBJECT, file_.toString());
        getPredefinedInterfacesInitOrder().add(ALIAS_STRING_MAP_OBJECT);
        return files_;
    }
    private static String tr(String _var, ContextEl _context) {
        StringList allKeysWords_ = _context.getKeyWords().allKeyWords();
        allKeysWords_.addAllElts(_context.getStandards().getPrimitiveTypes().getKeys());
        allKeysWords_.add(_context.getStandards().getAliasVoid());
        String candidate_ = _var;
        int index_ = 0;
        while (StringList.contains(allKeysWords_,candidate_)) {
            candidate_ = StringList.concatNbs(_var,index_);
            index_++;
        }
        return candidate_;
    }
    private static String trLoop(String _var, ContextEl _context) {
        StringList allKeysWords_ = _context.getKeyWords().allKeyWords();
        allKeysWords_.addAllElts(_context.getStandards().getPrimitiveTypes().getKeys());
        allKeysWords_.add(_context.getStandards().getAliasVoid());
        String candidate_ = _var;
        int index_ = 0;
        while (StringList.contains(allKeysWords_,candidate_)) {
            candidate_ = StringList.concatNbs(_var,index_);
            index_++;
        }
        String suffix_ = String.valueOf(_context.getOptions().getSuffix());
        String suffixLoop_ = "";
        if (_context.getOptions().getSuffixVar() == VariableSuffix.DISTINCT) {
            suffixLoop_ = suffix_;
        } else if (_context.getOptions().getSuffixVar() != VariableSuffix.NONE) {
            suffixLoop_ = suffix_;
        }
        return StringList.concat(candidate_,suffixLoop_);
    }
    private static String trParam(String _var, ContextEl _context) {
        StringList allKeysWords_ = _context.getKeyWords().allKeyWords();
        allKeysWords_.addAllElts(_context.getStandards().getPrimitiveTypes().getKeys());
        allKeysWords_.add(_context.getStandards().getAliasVoid());
        String candidate_ = _var;
        int index_ = 0;
        while (StringList.contains(allKeysWords_,candidate_)) {
            candidate_ = StringList.concatNbs(_var,index_);
            index_++;
        }
        String suffix_ = String.valueOf(_context.getOptions().getSuffix());
        String suffixParam_ = "";
        if (_context.getOptions().getSuffixVar() == VariableSuffix.DISTINCT) {
            suffixParam_ = StringList.concat(suffix_,".",suffix_);
        } else if (_context.getOptions().getSuffixVar() != VariableSuffix.NONE) {
            suffixParam_ = suffix_;
        }
        return StringList.concat(candidate_,suffixParam_);
    }
    private static String trLoc(String _var, ContextEl _context) {
        StringList allKeysWords_ = _context.getKeyWords().allKeyWords();
        allKeysWords_.addAllElts(_context.getStandards().getPrimitiveTypes().getKeys());
        allKeysWords_.add(_context.getStandards().getAliasVoid());
        String candidate_ = _var;
        int index_ = 0;
        while (StringList.contains(allKeysWords_,candidate_)) {
            candidate_ = StringList.concatNbs(_var,index_);
            index_++;
        }
        return sufficLocal(_context, candidate_);
    }

    public static String sufficLocal(ContextEl _context, String _candidate) {
        String suffix_ = String.valueOf(_context.getOptions().getSuffix());
        String suffixLocal_ = "";
        if (_context.getOptions().getSuffixVar() == VariableSuffix.DISTINCT) {
            suffixLocal_ = StringList.concat(suffix_,".");
        } else if (_context.getOptions().getSuffixVar() != VariableSuffix.NONE) {
            suffixLocal_ = suffix_;
        }
        return StringList.concat(_candidate,suffixLocal_);
    }


    @Override
    public void initBeans(Configuration _conf,String _language,Struct _db) {
        for (EntryCust<String, BeanInfo> e: _conf.getBeansInfos().entryList()) {
            BeanInfo info_ = e.getValue();
            _conf.addPage(new ImportingPage(false));
            Argument arg_ = ElRenderUtil.calculateReuse(info_.getExps(), _conf);
            if (_conf.getContext().getException() != null) {
                _conf.removeLastPage();
                return;
            }
            Struct strBean_ = arg_.getStruct();
            String clName_ = strBean_.getClassName(_conf);
            if (!Templates.isCorrectExecute(clName_,getAliasBean(),_conf)) {
                _conf.removeLastPage();
                _conf.getBuiltBeans().addEntry(e.getKey(),strBean_);
                continue;
            }
            Struct map_ = ElRenderUtil.calculateReuse(opsMap, _conf).getStruct();
            ((FieldableStruct)strBean_).setStruct(new ClassField(getAliasBean(),getAliasForms()),map_);
            ((FieldableStruct)strBean_).setStruct(new ClassField(getAliasBean(),getAliasDataBaseField()),_db);
            ((FieldableStruct)strBean_).setStruct(new ClassField(getAliasBean(),getAliasLanguage()),new StringStruct(_language));
            ((FieldableStruct)strBean_).setStruct(new ClassField(getAliasBean(),getAliasScope()),new StringStruct(info_.getScope()));
            _conf.removeLastPage();
            _conf.getBuiltBeans().addEntry(e.getKey(),strBean_);
        }
    }
    @Override
    public void forwardDataBase(Struct _bean, Struct _to, Configuration _conf) {
        LocalVariable locVar_ = new LocalVariable();
        locVar_.setClassName(getStructClassName(_bean, _conf.getContext()));
        locVar_.setStruct(_bean);
        _conf.getLastPage().getInternVars().put(getDataBaseVar, locVar_);
        Argument argument_ = ElRenderUtil.calculateReuse(expsGetDataBase, _conf);
        _conf.getLastPage().getInternVars().removeKey(getDataBaseVar);
        if (_conf.getContext().hasExceptionOrFailInit()) {
            return;
        }
        locVar_ = new LocalVariable();
        locVar_.setClassName(getStructClassName(_to, _conf.getContext()));
        locVar_.setStruct(_to);
        _conf.getLastPage().getInternVars().put(setDataBaseVar, locVar_);
        locVar_ = new LocalVariable();
        locVar_.setClassName(getStructClassName(argument_.getStruct(), _conf.getContext()));
        locVar_.setStruct(argument_.getStruct());
        _conf.getLastPage().getInternVars().put(setDataBaseVarArg, locVar_);
        ElRenderUtil.calculateReuse(expsSetDataBase, _conf);
        _conf.getLastPage().getInternVars().removeKey(setDataBaseVar);
        _conf.getLastPage().getInternVars().removeKey(setDataBaseVarArg);
    }
    @Override
    public Argument getForms(Struct _bean, Configuration _conf) {
        String clName_ = _bean.getClassName(_conf);
        if (!Templates.isCorrectExecute(clName_,getAliasBean(),_conf)) {
            return ElRenderUtil.calculateReuse(opsMap, _conf);
        }
        LocalVariable locVar_ = new LocalVariable();
        locVar_.setClassName(getStructClassName(_bean, _conf.getContext()));
        locVar_.setStruct(_bean);
        _conf.getLastPage().getInternVars().put(getFormsVar, locVar_);
        Argument argument_ = ElRenderUtil.calculateReuse(expsGetForms, _conf);
        _conf.getLastPage().getInternVars().removeKey(getFormsVar);
        if (_conf.getContext().hasExceptionOrFailInit()) {
            return argument_;
        }
        if (argument_.isNull()) {
            return ElRenderUtil.calculateReuse(opsMap, _conf);
        }
        return argument_;
    }
    @Override
    public void setForms(Struct _bean, Struct _map, Configuration _conf) {
        LocalVariable locVar_ = new LocalVariable();
        locVar_.setClassName(getStructClassName(_bean, _conf.getContext()));
        locVar_.setStruct(_bean);
        _conf.getLastPage().getInternVars().put(setFormsVar, locVar_);
        locVar_ = new LocalVariable();
        locVar_.setClassName(getStructClassName(_map, _conf.getContext()));
        locVar_.setStruct(_map);
        _conf.getLastPage().getInternVars().put(setFormsVarArg, locVar_);
        ElRenderUtil.calculateReuse(expsSetForms, _conf);
        _conf.getLastPage().getInternVars().removeKey(setFormsVar);
        _conf.getLastPage().getInternVars().removeKey(setFormsVarArg);
    }
    @Override
    public void forwardMap(Struct _map, Struct _to, Struct _key, Configuration _conf) {
        LocalVariable locVar_ = new LocalVariable();
        locVar_.setClassName(getStructClassName(_map, _conf.getContext()));
        locVar_.setStruct(_map);
        _conf.getLastPage().getInternVars().put(getValVar, locVar_);
        locVar_ = new LocalVariable();
        locVar_.setClassName(getStructClassName(_key, _conf.getContext()));
        locVar_.setStruct(_key);
        _conf.getLastPage().getInternVars().put(getValVarArg, locVar_);
        Argument argument_ = ElRenderUtil.calculateReuse(expsGetVal, _conf);
        _conf.getLastPage().getInternVars().removeKey(getValVar);
        _conf.getLastPage().getInternVars().removeKey(getValVarArg);
        if (_conf.getContext().hasExceptionOrFailInit()) {
            return;
        }
        locVar_ = new LocalVariable();
        locVar_.setClassName(getStructClassName(_to, _conf.getContext()));
        locVar_.setStruct(_to);
        _conf.getLastPage().getInternVars().put(putVarCust, locVar_);
        locVar_ = new LocalVariable();
        locVar_.setClassName(getStructClassName(_key, _conf.getContext()));
        locVar_.setStruct(_key);
        _conf.getLastPage().getInternVars().put(putVarCustKey, locVar_);
        locVar_ = new LocalVariable();
        locVar_.setClassName(getStructClassName(argument_.getStruct(), _conf.getContext()));
        locVar_.setStruct(argument_.getStruct());
        _conf.getLastPage().getInternVars().put(putVarCustValue, locVar_);
        ElRenderUtil.calculateReuse(expsPut, _conf);
        _conf.getLastPage().getInternVars().removeKey(putVarCust);
        _conf.getLastPage().getInternVars().removeKey(putVarCustKey);
        _conf.getLastPage().getInternVars().removeKey(putVarCustValue);
    }
    @Override
    public void putAllMap(Struct _map, Struct _other, Configuration _conf) {
        LocalVariable locVar_ = new LocalVariable();
        locVar_.setClassName(getStructClassName(_map, _conf.getContext()));
        locVar_.setStruct(_map);
        _conf.getLastPage().getInternVars().put(putAllVarCust, locVar_);
        locVar_ = new LocalVariable();
        locVar_.setClassName(getStructClassName(_other, _conf.getContext()));
        locVar_.setStruct(_other);
        _conf.getLastPage().getInternVars().put(putAllVarCustArg, locVar_);
        ElRenderUtil.calculateReuse(expsPutAll, _conf);
        _conf.getLastPage().getInternVars().removeKey(putAllVarCust);
        _conf.getLastPage().getInternVars().removeKey(putAllVarCustArg);
    }

    @Override
    public String getIteratorVar() {
        return iteratorVar;
    }

    @Override
    public String getHasNextVar() {
        return hasNextVar;
    }

    @Override
    public String getNextVar() {
        return nextVar;
    }

    @Override
    public CustList<RendDynOperationNode> getExpsIterator() {
        return expsIterator;
    }

    @Override
    public CustList<RendDynOperationNode> getExpsHasNext() {
        return expsHasNext;
    }

    @Override
    public CustList<RendDynOperationNode> getExpsNext() {
        return expsNext;
    }

    @Override
    public String getIteratorTableVarCust() {
        return iteratorTableVarCust;
    }

    @Override
    public String getHasNextPairVarCust() {
        return hasNextPairVarCust;
    }

    @Override
    public String getNextPairVarCust() {
        return nextPairVarCust;
    }

    @Override
    public String getFirstVarCust() {
        return firstVarCust;
    }

    @Override
    public String getSecondVarCust() {
        return secondVarCust;
    }

    private String getBeforeDisplayingVar() {
        return beforeDisplayingVar;
    }

    @Override
    public CustList<RendDynOperationNode> getExpsIteratorTableCust() {
        return expsIteratorTableCust;
    }

    @Override
    public CustList<RendDynOperationNode> getExpsHasNextPairCust() {
        return expsHasNextPairCust;
    }

    @Override
    public CustList<RendDynOperationNode> getExpsNextPairCust() {
        return expsNextPairCust;
    }

    @Override
    public CustList<RendDynOperationNode> getExpsFirstCust() {
        return expsFirstCust;
    }

    @Override
    public CustList<RendDynOperationNode> getExpsSecondCust() {
        return expsSecondCust;
    }

    private CustList<RendDynOperationNode> getExpsBeforeDisplaying() {
        return expsBeforeDisplaying;
    }

    @Override
    public String getStringKey(Configuration _conf, Struct _instance) {
        if (_instance instanceof EnumerableStruct) {
            return ((EnumerableStruct) _instance).getName();
        }
        return processString(new Argument(_instance),_conf);
    }

    @Override
    public void beforeDisplaying(Struct _arg, Configuration _cont) {
        String clName_ = getStructClassName(_arg, _cont.getContext());
        if (!Templates.isCorrectExecute(clName_,getAliasBean(),_cont)) {
            return;
        }
        String locName_ = getBeforeDisplayingVar();
        LocalVariable locVar_ = new LocalVariable();
        locVar_.setClassName(clName_);
        locVar_.setStruct(_arg);
        _cont.getLastPage().getInternVars().put(locName_, locVar_);
        ElRenderUtil.calculateReuse(getExpsBeforeDisplaying(),_cont);
    }

    @Override
    public String processString(Argument _arg, Configuration _cont) {
        Struct struct_ = _arg.getStruct();
        if (struct_ instanceof DisplayableStruct) {
            return ((DisplayableStruct)struct_).getDisplayedString(_cont).getInstance();
        }
        return struct_.getClassName(_cont);
    }

    public String getAliasMapKeys() {
        return aliasMapKeys;
    }

    public void setAliasMapKeys(String _aliasMapKeys) {
        aliasMapKeys = _aliasMapKeys;
    }

    public String getAliasMapValues() {
        return aliasMapValues;
    }

    public void setAliasMapValues(String _aliasMapValues) {
        aliasMapValues = _aliasMapValues;
    }

    public String getAliasMapIndexOfEntry() {
        return aliasMapIndexOfEntry;
    }

    public void setAliasMapIndexOfEntry(String _aliasMapIndexOfEntry) {
        aliasMapIndexOfEntry = _aliasMapIndexOfEntry;
    }

    public String getAliasMapAddEntry() {
        return aliasMapAddEntry;
    }

    public void setAliasMapAddEntry(String _aliasMapAddEntry) {
        aliasMapAddEntry = _aliasMapAddEntry;
    }

    public String getAliasMapGetValue() {
        return aliasMapGetValue;
    }

    public void setAliasMapGetValue(String _aliasMapGetValue) {
        aliasMapGetValue = _aliasMapGetValue;
    }

    public String getAliasMapFirstValue() {
        return aliasMapFirstValue;
    }

    public void setAliasMapFirstValue(String _aliasMapFirstValue) {
        aliasMapFirstValue = _aliasMapFirstValue;
    }

    public String getAliasMapLastValue() {
        return aliasMapLastValue;
    }

    public void setAliasMapLastValue(String _aliasMapLastValue) {
        aliasMapLastValue = _aliasMapLastValue;
    }

    public String getAliasMapSetValue() {
        return aliasMapSetValue;
    }

    public void setAliasMapSetValue(String _aliasMapSetValue) {
        aliasMapSetValue = _aliasMapSetValue;
    }

    public String getAliasMapPut() {
        return aliasMapPut;
    }

    public void setAliasMapPut(String _aliasMapPut) {
        aliasMapPut = _aliasMapPut;
    }

    public String getAliasMapContains() {
        return aliasMapContains;
    }

    public void setAliasMapContains(String _aliasMapContains) {
        aliasMapContains = _aliasMapContains;
    }

    public String getAliasMapPutAll() {
        return aliasMapPutAll;
    }

    public void setAliasMapPutAll(String _aliasMapPutAll) {
        aliasMapPutAll = _aliasMapPutAll;
    }

    public String getAliasMapGetVal() {
        return aliasMapGetVal;
    }

    public void setAliasMapGetVal(String _aliasMapGetVal) {
        aliasMapGetVal = _aliasMapGetVal;
    }

    public String getAliasMapRemoveKey() {
        return aliasMapRemoveKey;
    }

    public void setAliasMapRemoveKey(String _aliasMapRemoveKey) {
        aliasMapRemoveKey = _aliasMapRemoveKey;
    }

    public String getAliasMapGetKey() {
        return aliasMapGetKey;
    }

    public void setAliasMapGetKey(String _aliasMapGetKey) {
        aliasMapGetKey = _aliasMapGetKey;
    }

    public String getAliasMapFirstKey() {
        return aliasMapFirstKey;
    }

    public void setAliasMapFirstKey(String _aliasMapFirstKey) {
        aliasMapFirstKey = _aliasMapFirstKey;
    }

    public String getAliasMapLastKey() {
        return aliasMapLastKey;
    }

    public void setAliasMapLastKey(String _aliasMapLastKey) {
        aliasMapLastKey = _aliasMapLastKey;
    }

    public String getAliasMapSetKey() {
        return aliasMapSetKey;
    }

    public void setAliasMapSetKey(String _aliasMapSetKey) {
        aliasMapSetKey = _aliasMapSetKey;
    }

    public String getAliasMapSize() {
        return aliasMapSize;
    }

    public void setAliasMapSize(String _aliasMapSize) {
        aliasMapSize = _aliasMapSize;
    }

    public String getAliasMapIsEmpty() {
        return aliasMapIsEmpty;
    }

    public void setAliasMapIsEmpty(String _aliasMapIsEmpty) {
        aliasMapIsEmpty = _aliasMapIsEmpty;
    }

    public String getAliasMapClear() {
        return aliasMapClear;
    }

    public void setAliasMapClear(String _aliasMapClear) {
        aliasMapClear = _aliasMapClear;
    }
}
