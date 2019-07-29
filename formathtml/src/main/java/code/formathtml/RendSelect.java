package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.stds.IterableAnalysisResult;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.util.*;
import code.sml.*;
import code.util.*;

public final class RendSelect extends RendParentBlock implements RendWithEl, RendReducableOperations,RendBuildableElMethod {
    private CustList<RendDynOperationNode> opsRead = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsValue = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsWrite = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsMap = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsDefault = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsConverter = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsConverterField = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsConverterFieldValue = new CustList<RendDynOperationNode>();
    private StringMap<ResultText> attributesText = new StringMap<ResultText>();
    private String varName = EMPTY_STRING;
    private ClassField idField;
    private Element elt;
    private boolean multiple;
    private String varNameConverter = "";
    private String varNameConverterField = "";
    private String varNameConverterFieldValue = "";
    private boolean arrayConverter;
    RendSelect(Element _elt, OffsetsBlock _offset) {
        super(_offset);
        elt = _elt;
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc) {
        ResultInput r_ = new ResultInput();
        r_.build(_cont, _doc,elt,ATTRIBUTE_VAR_VALUE);
        opsRead = r_.getOpsRead();
        opsValue = r_.getOpsValue();
        opsWrite = r_.getOpsWrite();
        varName = r_.getVarName();
        idField = r_.getIdField();
        String id_ = elt.getAttribute(ATTRIBUTE_ID);
        if (!id_.isEmpty()) {
            ResultText rId_ = new ResultText();
            rId_.buildId(id_,_cont,_doc);
            attributesText.put(ATTRIBUTE_ID,rId_);
        }
        String prefixWrite_ = _cont.getPrefix();
        String prefGr_ = StringList.concat(prefixWrite_, ATTRIBUTE_GROUP_ID);
        String groupId_ = elt.getAttribute(ATTRIBUTE_GROUP_ID);
        if (!groupId_.isEmpty()) {
            ResultText rId_ = new ResultText();
            rId_.buildId(groupId_,_cont,_doc);
            attributesText.put(prefGr_,rId_);
        }
        boolean st_ = _doc.isStaticContext();
        multiple = elt.hasAttribute(ATTRIBUTE_MULTIPLE);
        String map_ = elt.getAttribute(ATTRIBUTE_MAP);
        opsMap = RenderExpUtil.getAnalyzedOperations(map_, 0, _cont, Calculation.staticCalculation(st_));
        String converterValue_ = elt.getAttribute(ATTRIBUTE_CONVERT_VALUE);
        if (_cont.getAdvStandards() instanceof BeanCustLgNames) {
            if (multiple) {
                if (converterValue_.trim().isEmpty()) {
                    BadElRender badEl_ = new BadElRender();
                    badEl_.setErrors(_cont.getClasses().getErrorsDet());
                    badEl_.setFileName(_cont.getCurrentFileName());
                    badEl_.setIndexFile(_cont.getCurrentLocationIndex());
                    _cont.getClasses().addError(badEl_);
                }
                String string_ = _cont.getStandards().getAliasString();
                StringList varNames_ = new StringList();
                String varLoc_ = RendBlock.lookForVar(_cont, varNames_);
                varNames_.add(varLoc_);
                varNameConverter = varLoc_;
                LocalVariable lv_ = new LocalVariable();
                arrayConverter = true;
                lv_.setClassName(PrimitiveTypeUtil.getPrettyArrayType(string_));
                _cont.getLocalVarsAna().last().addEntry(varLoc_,lv_);
                String preRend_ = StringList.concat(converterValue_,"(",BeanCustLgNames.sufficLocal(_cont.getContext(),varLoc_),")");
                opsConverter = RenderExpUtil.getAnalyzedOperations(preRend_,0,_cont,Calculation.staticCalculation(st_));
                for (String v:varNames_) {
                    _cont.getLocalVarsAna().last().removeKey(v);
                }
                StringList names_ = opsValue.last().getResultClass().getNames();
                if (!opsValue.last().getResultClass().isVariable()) {
                    IterableAnalysisResult it_ = _cont.getStandards().getCustomType(names_, _cont.getContext());
                    StringList candidates_ = it_.getClassName();
                    if (candidates_.size() != 1) {
                        BadElRender badEl_ = new BadElRender();
                        badEl_.setErrors(_cont.getClasses().getErrorsDet());
                        badEl_.setFileName(_cont.getCurrentFileName());
                        badEl_.setIndexFile(_cont.getCurrentLocationIndex());
                        _cont.getClasses().addError(badEl_);
                    }
                    Mapping m_ = new Mapping();
                    m_.setArg(opsConverter.last().getResultClass());
                    m_.setParam(opsRead.last().getResultClass());
                    if (!Templates.isCorrectOrNumbers(m_,_cont)) {
                        BadElRender badEl_ = new BadElRender();
                        badEl_.setErrors(_cont.getClasses().getErrorsDet());
                        badEl_.setFileName(_cont.getCurrentFileName());
                        badEl_.setIndexFile(_cont.getCurrentLocationIndex());
                        _cont.getClasses().addError(badEl_);
                    }
                }
            } else if (!opsRead.isEmpty()){
                Mapping m_ = new Mapping();
                m_.setArg(opsRead.last().getResultClass());
                m_.setParam(_cont.getStandards().getAliasCharSequence());
                if (!Templates.isCorrectOrNumbers(m_,_cont)) {
                    if (converterValue_.trim().isEmpty()) {
                        BadElRender badEl_ = new BadElRender();
                        badEl_.setErrors(_cont.getClasses().getErrorsDet());
                        badEl_.setFileName(_cont.getCurrentFileName());
                        badEl_.setIndexFile(_cont.getCurrentLocationIndex());
                        _cont.getClasses().addError(badEl_);
                    }
                    String string_ = _cont.getStandards().getAliasString();
                    StringList varNames_ = new StringList();
                    String varLoc_ = RendBlock.lookForVar(_cont, varNames_);
                    varNames_.add(varLoc_);
                    varNameConverter = varLoc_;
                    LocalVariable lv_ = new LocalVariable();
                    lv_.setClassName(string_);
                    _cont.getLocalVarsAna().last().addEntry(varLoc_,lv_);
                    String preRend_ = StringList.concat(converterValue_,"(",BeanCustLgNames.sufficLocal(_cont.getContext(),varLoc_),")");
                    opsConverter = RenderExpUtil.getAnalyzedOperations(preRend_,0,_cont,Calculation.staticCalculation(st_));
                    for (String v:varNames_) {
                        _cont.getLocalVarsAna().last().removeKey(v);
                    }
                    m_.setArg(opsConverter.last().getResultClass());
                    m_.setParam(opsRead.last().getResultClass());
                    if (!Templates.isCorrectOrNumbers(m_,_cont)) {
                        BadElRender badEl_ = new BadElRender();
                        badEl_.setErrors(_cont.getClasses().getErrorsDet());
                        badEl_.setFileName(_cont.getCurrentFileName());
                        badEl_.setIndexFile(_cont.getCurrentLocationIndex());
                        _cont.getClasses().addError(badEl_);
                    }
                } else if (!converterValue_.trim().isEmpty()) {
                    String string_ = _cont.getStandards().getAliasString();
                    StringList varNames_ = new StringList();
                    String varLoc_ = RendBlock.lookForVar(_cont, varNames_);
                    varNames_.add(varLoc_);
                    varNameConverter = varLoc_;
                    LocalVariable lv_ = new LocalVariable();
                    lv_.setClassName(string_);
                    _cont.getLocalVarsAna().last().addEntry(varLoc_,lv_);
                    String preRend_ = StringList.concat(converterValue_,"(",BeanCustLgNames.sufficLocal(_cont.getContext(),varLoc_),")");
                    opsConverter = RenderExpUtil.getAnalyzedOperations(preRend_,0,_cont,Calculation.staticCalculation(st_));
                    for (String v:varNames_) {
                        _cont.getLocalVarsAna().last().removeKey(v);
                    }
                    m_.setArg(opsConverter.last().getResultClass());
                    m_.setParam(opsRead.last().getResultClass());
                    if (!Templates.isCorrectOrNumbers(m_,_cont)) {
                        BadElRender badEl_ = new BadElRender();
                        badEl_.setErrors(_cont.getClasses().getErrorsDet());
                        badEl_.setFileName(_cont.getCurrentFileName());
                        badEl_.setIndexFile(_cont.getCurrentLocationIndex());
                        _cont.getClasses().addError(badEl_);
                    }
                }
            }
        }
        String converterField_ = elt.getAttribute(ATTRIBUTE_CONVERT_FIELD);
        if (!converterField_.trim().isEmpty()) {
            String object_ = _cont.getStandards().getAliasObject();
            StringList varNames_ = new StringList();
            String varLoc_ = RendBlock.lookForVar(_cont, varNames_);
            varNames_.add(varLoc_);
            varNameConverterField = varLoc_;
            LocalVariable lv_ = new LocalVariable();
            lv_.setClassName(object_);
            _cont.getLocalVarsAna().last().addEntry(varLoc_,lv_);
            String preRend_ = StringList.concat(converterField_,"(",BeanCustLgNames.sufficLocal(_cont.getContext(),varLoc_),")");
            opsConverterField = RenderExpUtil.getAnalyzedOperations(preRend_,0,_cont,Calculation.staticCalculation(st_));
            for (String v:varNames_) {
                _cont.getLocalVarsAna().last().removeKey(v);
            }
            Mapping m_ = new Mapping();
            m_.setArg(opsConverterField.last().getResultClass());
            m_.setParam(_cont.getStandards().getAliasCharSequence());
            if (!Templates.isCorrectOrNumbers(m_,_cont)) {
                BadElRender badEl_ = new BadElRender();
                badEl_.setErrors(_cont.getClasses().getErrorsDet());
                badEl_.setFileName(_cont.getCurrentFileName());
                badEl_.setIndexFile(_cont.getCurrentLocationIndex());
                _cont.getClasses().addError(badEl_);
            }
        }
        String converterFieldValue_ = elt.getAttribute(ATTRIBUTE_CONVERT_FIELD_VALUE);
        if (!converterFieldValue_.trim().isEmpty()) {
            String object_ = _cont.getStandards().getAliasObject();
            StringList varNames_ = new StringList();
            String varLoc_ = RendBlock.lookForVar(_cont, varNames_);
            varNames_.add(varLoc_);
            varNameConverterFieldValue = varLoc_;
            LocalVariable lv_ = new LocalVariable();
            lv_.setClassName(object_);
            _cont.getLocalVarsAna().last().addEntry(varLoc_,lv_);
            String preRend_ = StringList.concat(converterFieldValue_,"(",BeanCustLgNames.sufficLocal(_cont.getContext(),varLoc_),")");
            opsConverterFieldValue = RenderExpUtil.getAnalyzedOperations(preRend_,0,_cont,Calculation.staticCalculation(st_));
            for (String v:varNames_) {
                _cont.getLocalVarsAna().last().removeKey(v);
            }
            Mapping m_ = new Mapping();
            m_.setArg(opsConverterFieldValue.last().getResultClass());
            m_.setParam(_cont.getStandards().getAliasCharSequence());
            if (!Templates.isCorrectOrNumbers(m_,_cont)) {
                BadElRender badEl_ = new BadElRender();
                badEl_.setErrors(_cont.getClasses().getErrorsDet());
                badEl_.setFileName(_cont.getCurrentFileName());
                badEl_.setIndexFile(_cont.getCurrentLocationIndex());
                _cont.getClasses().addError(badEl_);
            }
        }
        String default_ = elt.getAttribute(DEFAULT_ATTRIBUTE);
        if (!default_.isEmpty()) {
            String mName_ = elt.getAttribute(ATTRIBUTE_CONVERT);
            if (mName_.trim().isEmpty()) {
                BadElRender badEl_ = new BadElRender();
                badEl_.setErrors(_cont.getClasses().getErrorsDet());
                badEl_.setFileName(_cont.getCurrentFileName());
                badEl_.setIndexFile(_cont.getCurrentLocationIndex());
                _cont.getClasses().addError(badEl_);
            }
            String concat_ = StringList.concat(mName_,"(\"",default_,"\")");
            opsDefault = RenderExpUtil.getAnalyzedOperations(concat_,0,_cont,Calculation.staticCalculation(st_));
            Mapping m_ = new Mapping();
            m_.setArg(opsDefault.last().getResultClass());
            if (!multiple) {
                m_.setParam(_cont.getStandards().getAliasCharSequence());
                if (!Templates.isCorrectOrNumbers(m_,_cont)) {
                    BadElRender badEl_ = new BadElRender();
                    badEl_.setErrors(_cont.getClasses().getErrorsDet());
                    badEl_.setFileName(_cont.getCurrentFileName());
                    badEl_.setIndexFile(_cont.getCurrentLocationIndex());
                    _cont.getClasses().addError(badEl_);
                }
            } else {
                IterableAnalysisResult it_ = _cont.getStandards().getCustomType(opsDefault.last().getResultClass().getNames(), _cont.getContext());
                StringList candidates_ = it_.getClassName();
                if (candidates_.size() != 1) {
                    BadElRender badEl_ = new BadElRender();
                    badEl_.setErrors(_cont.getClasses().getErrorsDet());
                    badEl_.setFileName(_cont.getCurrentFileName());
                    badEl_.setIndexFile(_cont.getCurrentLocationIndex());
                    _cont.getClasses().addError(badEl_);
                }
            }
        }
    }

    @Override
    public void reduce(Configuration _context) {
        opsRead = reduceList(opsRead);
        opsValue = reduceList(opsValue);
        opsWrite = reduceList(opsWrite);
        opsMap = reduceList(opsMap);
        opsDefault = reduceList(opsDefault);
        opsConverter = reduceList(opsConverter);
        opsConverterField = reduceList(opsConverterField);
        opsConverterFieldValue = reduceList(opsConverterFieldValue);
    }

    @Override
    public void processEl(Configuration _cont) {
        Argument value_ = RenderExpUtil.calculateReuse(opsValue, _cont);
        if (_cont.getContext().getException() != null) {
            return;
        }
        Argument map_ = RenderExpUtil.calculateReuse(opsMap, _cont);
        if (_cont.getContext().getException() != null) {
            return;
        }
        RendReadWrite rw_ = _cont.getLastPage().getRendReadWrite();
        Element write_ = (Element) rw_.getWrite();
        Document doc_ = write_.getOwnerDocument();
        Element docElementSelect_ = doc_.createElement(SELECT_TAG);
        if (multiple) {
            docElementSelect_.setAttribute(ATTRIBUTE_MULTIPLE, ATTRIBUTE_MULTIPLE);
        }
        String name_ = elt.getAttribute(ATTRIBUTE_NAME);
        String default_ = elt.getAttribute(DEFAULT_ATTRIBUTE);
        if (default_.isEmpty()) {
            processOptionsMapEnumName(_cont, map_.getStruct(),
                    doc_, docElementSelect_,
                    value_.getStruct());
        } else {
            processOptionsMapEnum(_cont, map_.getStruct(),
                    doc_, docElementSelect_);
        }
        boolean id_ = false;
        for (EntryCust<String,ResultText> e: attributesText.entryList()) {
            ResultText res_ = e.getValue();
            String txt_ = ResultText.render(res_.getOpExp(), res_.getTexts(), _cont);
            if (_cont.getContext().hasExceptionOrFailInit()) {
                return;
            }
            id_ = true;
            docElementSelect_.setAttribute(e.getKey(),txt_);
        }
        if (id_ && !elt.getAttribute(ATTRIBUTE_VALIDATOR).trim().isEmpty()) {
            docElementSelect_.setAttribute(StringList.concat(_cont.getPrefix(),ATTRIBUTE_VALIDATOR), elt.getAttribute(ATTRIBUTE_VALIDATOR));
        }
        docElementSelect_.setAttribute(ATTRIBUTE_NAME, name_);
        if (!(_cont.getAdvStandards() instanceof BeanCustLgNames)) {
            docElementSelect_.setAttribute(StringList.concat(_cont.getPrefix(),ATTRIBUTE_CLASS_NAME), elt.getAttribute(ATTRIBUTE_CLASS_NAME));
        }
        write_.appendChild(docElementSelect_);
        if (!name_.isEmpty()) {
            processIndexes(_cont,elt,docElementSelect_);
            if (_cont.getContext().getException() != null) {
                return;
            }
            FormInputCoords inputs_ = new FormInputCoords();
            inputs_.setForm(_cont.getCurrentForm() - 1);
            inputs_.setInput(_cont.getIndexes().getNb());
            StringList allOptions_ = new StringList();
            ElementList elts_ = docElementSelect_.getElementsByTagName(TAG_OPTION);
            int nbElts_ = elts_.getLength();
            for (int i = 0; i < nbElts_; i++) {
                Element opt_ = elts_.item(i);
                allOptions_.add(opt_.getAttribute(ATTRIBUTE_VALUE));
            }
            _cont.getHtmlPage().getSelects().put(inputs_, allOptions_);
        }
        if (!(_cont.getAdvStandards() instanceof BeanCustLgNames)) {
            docElementSelect_.removeAttribute(StringList.concat(_cont.getPrefix(), ATTRIBUTE_CLASS_NAME));
        }
        processBlock(_cont);
    }

    private void processOptionsMapEnum(Configuration _conf, Struct _extractedMap,
                                       Document _docSelect, Element _docElementSelect) {
        Argument argDef_ = RenderExpUtil.calculateReuse(opsDefault, _conf);
        if (_conf.getContext().hasExceptionOrFailInit()) {
            return;
        }
        processOptionsMapEnumName(_conf,_extractedMap,_docSelect,_docElementSelect,argDef_.getStruct());
    }

    private void processOptionsMapEnumName(Configuration _conf, Struct _extractedMap,
                                           Document _docSelect, Element _docElementSelect, Struct _returnedVarValue) {
        CustList<Struct> obj_ = values(_conf,_returnedVarValue);
        if (_conf.getContext().getException() != null) {
            return;
        }
        Argument arg_ = iteratorMultTable(_extractedMap,_conf);
        if (_conf.getContext().getException() != null) {
            return;
        }
        Struct l_;
        l_ = arg_.getStruct();
        processOptions(_conf, _docSelect, _docElementSelect, obj_, l_);
    }

    private void processOptions(Configuration _conf, Document _docSelect, Element _docElementSelect, CustList<Struct> _obj, Struct _l) {
        BeanLgNames stds_ = _conf.getAdvStandards();
        while (true) {
            Argument hasNext_ = hasNextPair(_l, _conf);
            if (_conf.getContext().getException() != null) {
                return;
            }
            if (!((BooleanStruct)hasNext_.getStruct()).getInstance()) {
                break;
            }
            Argument nextPair_ = nextPair(_l, _conf);
            if (_conf.getContext().getException() != null) {
                return;
            }
            Struct entry_ = nextPair_.getStruct();
            Argument first_ = first(entry_, _conf);
            if (_conf.getContext().getException() != null) {
                return;
            }
            Struct o_ = first_.getStruct();
            if (o_ == NullStruct.NULL_VALUE) {
                continue;
            }
            Element option_ = _docSelect.createElement(TAG_OPTION);
            if (opsConverterField.isEmpty()) {
                option_.setAttribute(ATTRIBUTE_VALUE, getStringKey(_conf, o_));
            } else {
                LocalVariable locVar_ = new LocalVariable();
                locVar_.setClassName(_conf.getStandards().getAliasObject());
                locVar_.setStruct(o_);
                _conf.getLastPage().putLocalVar(varNameConverterField, locVar_);
                Argument arg_ = RenderExpUtil.calculateReuse(opsConverterField, _conf);
                _conf.getLastPage().removeLocalVar(varNameConverterField);
                if (_conf.getContext().getException() != null) {
                    return;
                }
                option_.setAttribute(ATTRIBUTE_VALUE,stds_.processString(arg_,_conf));
            }
            for (Struct n: _obj) {
                if (n.sameReference(o_)) {
                    option_.setAttribute(SELECTED, SELECTED);
                    break;
                }
            }
            Argument second_ = second(entry_, _conf);
            if (_conf.getContext().getException() != null) {
                return;
            }
            if (opsConverterFieldValue.isEmpty()) {
                option_.appendChild(_docSelect.createTextNode(stds_.processString(second_,_conf)));
            } else {
                LocalVariable locVar_ = new LocalVariable();
                locVar_.setClassName(_conf.getStandards().getAliasObject());
                locVar_.setStruct(second_.getStruct());
                _conf.getLastPage().putLocalVar(varNameConverterFieldValue, locVar_);
                Argument arg_ = RenderExpUtil.calculateReuse(opsConverterFieldValue, _conf);
                _conf.getLastPage().removeLocalVar(varNameConverterFieldValue);
                if (_conf.getContext().getException() != null) {
                    return;
                }
                option_.appendChild(_docSelect.createTextNode(stds_.processString(arg_,_conf)));
            }
            _docElementSelect.appendChild(option_);
        }
    }

    CustList<Struct> values(Configuration _conf,Struct _returnedVarValue) {
        IdList<Struct> obj_ = new IdList<Struct>();
        if (multiple) {
            Argument arg_ = iterator(_returnedVarValue,_conf);
            if (_conf.getContext().getException() != null) {
                return obj_;
            }
            Struct it_ = arg_.getStruct();
            while (true) {
                Argument hasNext_ = hasNext(it_, _conf);
                if (_conf.getContext().getException() != null) {
                    return obj_;
                }
                if (!((BooleanStruct)hasNext_.getStruct()).getInstance()) {
                    break;
                }
                Argument next_ = next(it_, _conf);
                if (_conf.getContext().getException() != null) {
                    return obj_;
                }
                obj_.add(next_.getStruct());
            }
        } else {
            obj_.add(_returnedVarValue);
        }
        return obj_;
    }
    Argument processIndexes(Configuration _cont, Element _read, Element _write) {
        FieldUpdates f_ = new FieldUpdates();
        f_.setIdField(idField);
        f_.setOpsRead(opsRead);
        f_.setOpsWrite(opsWrite);
        f_.setOpsValue(opsValue);
        f_.setVarName(varName);
        f_.setVarNameConverter(varNameConverter);
        f_.setOpsConverter(opsConverter);
        f_.setArrayConverter(arrayConverter);
        Argument arg_ = fetchName(_cont, _read, _write, f_);
        fetchValue(_cont,_read,_write,opsValue,varNameConverterField,opsConverterField);
        return arg_;
    }
}
