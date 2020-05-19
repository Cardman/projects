package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
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
    private StringMap<ResultText> attributes = new StringMap<ResultText>();
    private String varName = EMPTY_STRING;
    private String id = EMPTY_STRING;
    private String idClass = EMPTY_STRING;
    private String idName = EMPTY_STRING;
    private Element elt;
    private boolean multiple;
    private String varNameConverter = EMPTY_STRING;
    private String varNameConverterField = EMPTY_STRING;
    private String varNameConverterFieldValue = EMPTY_STRING;
    private boolean arrayConverter;
    RendSelect(Element _elt, OffsetsBlock _offset) {
        super(_offset);
        elt = _elt;
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc) {
        ResultInput r_ = new ResultInput();
        r_.build(_cont, this,_doc,elt,_cont.getRendKeyWords().getAttrVarValue());
        opsRead = r_.getOpsRead();
        opsValue = r_.getOpsValue();
        opsWrite = r_.getOpsWrite();
        varName = r_.getVarName();
        id = r_.getId();
        idClass = r_.getIdClass();
        idName = r_.getIdName();
        String id_ = elt.getAttribute(_cont.getRendKeyWords().getAttrId());
        if (!id_.isEmpty()) {
            ResultText rId_ = new ResultText();
            int off_ = getAttributeDelimiter(_cont.getRendKeyWords().getAttrId());
            rId_.buildId(id_,_cont,off_,_doc);
            attributesText.put(_cont.getRendKeyWords().getAttrId(),rId_);
        }
        String prefixWrite_ = _cont.getPrefix();
        String prefGr_ = StringList.concat(prefixWrite_, _cont.getRendKeyWords().getAttrGroupId());
        String groupId_ = elt.getAttribute(_cont.getRendKeyWords().getAttrGroupId());
        if (!groupId_.isEmpty()) {
            ResultText rId_ = new ResultText();
            int off_ = getAttributeDelimiter(_cont.getRendKeyWords().getAttrGroupId());
            rId_.buildId(groupId_,_cont,off_,_doc);
            attributesText.put(prefGr_,rId_);
        }
        multiple = elt.hasAttribute(_cont.getRendKeyWords().getAttrMultiple());
        String map_ = elt.getAttribute(_cont.getRendKeyWords().getAttrMap());
        int offMap_ = getAttributeDelimiter(_cont.getRendKeyWords().getAttrMap());
        opsMap = RenderExpUtil.getAnalyzedOperations(map_,offMap_, 0, _cont);
        String converterValue_ = elt.getAttribute(_cont.getRendKeyWords().getAttrConvertValue());
        if (multiple) {
            if (converterValue_.trim().isEmpty()) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFileName(_cont.getCurrentFileName());
                badEl_.setIndexFile(getOffset().getOffsetTrim());
                badEl_.buildError(_cont.getRendAnalysisMessages().getEmptyAttr(),
                        _cont.getRendKeyWords().getAttrConvertValue());
                _cont.addError(badEl_);
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
            String preRend_ = StringList.concat(converterValue_,RendBlock.LEFT_PAR, varLoc_,RendBlock.RIGHT_PAR);
            int offConvValue_ = getAttributeDelimiter(_cont.getRendKeyWords().getAttrConvertValue());
            opsConverter = RenderExpUtil.getAnalyzedOperations(preRend_,offConvValue_,0,_cont);
            for (String v:varNames_) {
                _cont.getLocalVarsAna().last().removeKey(v);
            }
            StringList names_ = opsValue.last().getResultClass().getNames();
            if (!opsValue.last().getResultClass().isVariable()) {
                IterableAnalysisResult it_ = _cont.getStandards().getCustomType(names_,"", _cont.getContext());
                StringList candidates_ = it_.getClassName();
                if (!candidates_.onlyOneElt()) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_cont.getCurrentFileName());
                    badEl_.setIndexFile(offConvValue_);
                    badEl_.buildError(_cont.getContext().getAnalysisMessages().getUnexpectedType(),
                            StringList.join(candidates_,AND_ERR));
                    _cont.addError(badEl_);
                }
                Mapping m_ = new Mapping();
                m_.setArg(opsConverter.last().getResultClass());
                m_.setParam(opsRead.last().getResultClass());
                if (!Templates.isCorrectOrNumbers(m_,_cont.getContext())) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_cont.getCurrentFileName());
                    badEl_.setIndexFile(getOffset().getOffsetTrim());
                    badEl_.buildError(_cont.getContext().getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(opsConverter.last().getResultClass().getNames(),AND_ERR),
                            StringList.join(opsRead.last().getResultClass().getNames(),AND_ERR));
                    _cont.addError(badEl_);
                }
            }
        } else if (!opsRead.isEmpty()){
            Mapping m_ = new Mapping();
            m_.setArg(opsRead.last().getResultClass());
            m_.setParam(_cont.getStandards().getAliasCharSequence());
            if (!Templates.isCorrectOrNumbers(m_,_cont.getContext())) {
                if (converterValue_.trim().isEmpty()) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_cont.getCurrentFileName());
                    badEl_.setIndexFile(getOffset().getOffsetTrim());
                    badEl_.buildError(_cont.getRendAnalysisMessages().getEmptyAttr(),
                            _cont.getRendKeyWords().getAttrConvertValue());
                    _cont.addError(badEl_);
                }
                String string_ = _cont.getStandards().getAliasString();
                StringList varNames_ = new StringList();
                String varLoc_ = RendBlock.lookForVar(_cont, varNames_);
                varNames_.add(varLoc_);
                varNameConverter = varLoc_;
                LocalVariable lv_ = new LocalVariable();
                lv_.setClassName(string_);
                _cont.getLocalVarsAna().last().addEntry(varLoc_,lv_);
                int offConvValue_ = getAttributeDelimiter(_cont.getRendKeyWords().getAttrConvertValue());
                String preRend_ = StringList.concat(converterValue_,RendBlock.LEFT_PAR, varLoc_,RendBlock.RIGHT_PAR);
                opsConverter = RenderExpUtil.getAnalyzedOperations(preRend_,offConvValue_,0,_cont);
                for (String v:varNames_) {
                    _cont.getLocalVarsAna().last().removeKey(v);
                }
                m_.setArg(opsConverter.last().getResultClass());
                m_.setParam(opsRead.last().getResultClass());
                if (!Templates.isCorrectOrNumbers(m_,_cont.getContext())) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_cont.getCurrentFileName());
                    badEl_.setIndexFile(offConvValue_);
                    badEl_.buildError(_cont.getContext().getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(opsConverter.last().getResultClass().getNames(),AND_ERR),
                            StringList.join(opsRead.last().getResultClass().getNames(),AND_ERR));
                    _cont.addError(badEl_);
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
                String preRend_ = StringList.concat(converterValue_,RendBlock.LEFT_PAR, varLoc_,RendBlock.RIGHT_PAR);
                int offConvValue_ = getAttributeDelimiter(_cont.getRendKeyWords().getAttrConvertValue());
                opsConverter = RenderExpUtil.getAnalyzedOperations(preRend_,offConvValue_,0,_cont);
                for (String v:varNames_) {
                    _cont.getLocalVarsAna().last().removeKey(v);
                }
                m_.setArg(opsConverter.last().getResultClass());
                m_.setParam(opsRead.last().getResultClass());
                if (!Templates.isCorrectOrNumbers(m_,_cont.getContext())) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_cont.getCurrentFileName());
                    badEl_.setIndexFile(offConvValue_);
                    badEl_.buildError(_cont.getContext().getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(opsConverter.last().getResultClass().getNames(),AND_ERR),
                            StringList.join(opsRead.last().getResultClass().getNames(),AND_ERR));
                    _cont.addError(badEl_);
                }
            }
        }
        String converterField_ = elt.getAttribute(_cont.getRendKeyWords().getAttrConvertField());
        if (!converterField_.trim().isEmpty()) {
            String object_ = _cont.getStandards().getAliasObject();
            StringList varNames_ = new StringList();
            String varLoc_ = RendBlock.lookForVar(_cont, varNames_);
            varNames_.add(varLoc_);
            varNameConverterField = varLoc_;
            LocalVariable lv_ = new LocalVariable();
            lv_.setClassName(object_);
            _cont.getLocalVarsAna().last().addEntry(varLoc_,lv_);
            String preRend_ = StringList.concat(converterField_,RendBlock.LEFT_PAR, varLoc_,RendBlock.RIGHT_PAR);
            int offConvValue_ = getAttributeDelimiter(_cont.getRendKeyWords().getAttrConvertField());
            opsConverterField = RenderExpUtil.getAnalyzedOperations(preRend_,offConvValue_,0,_cont);
            for (String v:varNames_) {
                _cont.getLocalVarsAna().last().removeKey(v);
            }
            Mapping m_ = new Mapping();
            m_.setArg(opsConverterField.last().getResultClass());
            m_.setParam(_cont.getStandards().getAliasCharSequence());
            if (!Templates.isCorrectOrNumbers(m_,_cont.getContext())) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFileName(_cont.getCurrentFileName());
                badEl_.setIndexFile(offConvValue_);
                badEl_.buildError(_cont.getContext().getAnalysisMessages().getBadImplicitCast(),
                        StringList.join(opsConverterField.last().getResultClass().getNames(),AND_ERR),
                        _cont.getStandards().getAliasCharSequence());
                _cont.addError(badEl_);
            }
        }
        String converterFieldValue_ = elt.getAttribute(_cont.getRendKeyWords().getAttrConvertFieldValue());
        if (!converterFieldValue_.trim().isEmpty()) {
            String object_ = _cont.getStandards().getAliasObject();
            StringList varNames_ = new StringList();
            String varLoc_ = RendBlock.lookForVar(_cont, varNames_);
            varNames_.add(varLoc_);
            varNameConverterFieldValue = varLoc_;
            LocalVariable lv_ = new LocalVariable();
            lv_.setClassName(object_);
            _cont.getLocalVarsAna().last().addEntry(varLoc_,lv_);
            String preRend_ = StringList.concat(converterFieldValue_,RendBlock.LEFT_PAR, varLoc_,RendBlock.RIGHT_PAR);
            int offConvValue_ = getAttributeDelimiter(_cont.getRendKeyWords().getAttrConvertFieldValue());
            opsConverterFieldValue = RenderExpUtil.getAnalyzedOperations(preRend_,offConvValue_,0,_cont);
            for (String v:varNames_) {
                _cont.getLocalVarsAna().last().removeKey(v);
            }
            Mapping m_ = new Mapping();
            m_.setArg(opsConverterFieldValue.last().getResultClass());
            m_.setParam(_cont.getStandards().getAliasCharSequence());
            if (!Templates.isCorrectOrNumbers(m_,_cont.getContext())) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFileName(_cont.getCurrentFileName());
                badEl_.setIndexFile(offConvValue_);
                badEl_.buildError(_cont.getContext().getAnalysisMessages().getBadImplicitCast(),
                        StringList.join(opsConverterFieldValue.last().getResultClass().getNames(),AND_ERR),
                        _cont.getStandards().getAliasCharSequence());
                _cont.addError(badEl_);
            }
        }
        String default_ = elt.getAttribute(_cont.getRendKeyWords().getAttrDefault());
        if (!default_.isEmpty()) {
            String mName_ = elt.getAttribute(_cont.getRendKeyWords().getAttrConvert());
            if (mName_.trim().isEmpty()) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFileName(_cont.getCurrentFileName());
                badEl_.setIndexFile(getOffset().getOffsetTrim());
                badEl_.buildError(_cont.getRendAnalysisMessages().getEmptyAttr(),
                        _cont.getRendKeyWords().getAttrConvert());
                _cont.addError(badEl_);
            }
            String concat_ = StringList.concat(mName_,LEFT_PAR,STR,default_,STR,RIGHT_PAR);
            int offConvValue_ = getAttributeDelimiter(_cont.getRendKeyWords().getAttrConvert());
            opsDefault = RenderExpUtil.getAnalyzedOperations(concat_,offConvValue_,0,_cont);
            Mapping m_ = new Mapping();
            m_.setArg(opsDefault.last().getResultClass());
            if (!multiple) {
                m_.setParam(_cont.getStandards().getAliasCharSequence());
                if (!Templates.isCorrectOrNumbers(m_,_cont.getContext())) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_cont.getCurrentFileName());
                    badEl_.setIndexFile(getAttributeDelimiter(_cont.getRendKeyWords().getAttrDefault()));
                    badEl_.buildError(_cont.getContext().getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(opsDefault.last().getResultClass().getNames(),AND_ERR),
                            _cont.getStandards().getAliasCharSequence());
                    _cont.addError(badEl_);
                }
            } else {
                IterableAnalysisResult it_ = _cont.getStandards().getCustomType(opsDefault.last().getResultClass().getNames(),"", _cont.getContext());
                StringList candidates_ = it_.getClassName();
                if (!candidates_.onlyOneElt()) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_cont.getCurrentFileName());
                    badEl_.setIndexFile(getAttributeDelimiter(_cont.getRendKeyWords().getAttrDefault()));
                    badEl_.buildError(_cont.getContext().getAnalysisMessages().getUnexpectedType(),
                            StringList.join(candidates_,AND_ERR));
                    _cont.addError(badEl_);
                }
            }
        }
        String rows_ = elt.getAttribute(_cont.getRendKeyWords().getAttrRows());
        int rowsGrId_ = getAttributeDelimiter(_cont.getRendKeyWords().getAttrRows());
        if (!rows_.isEmpty()) {
            ResultText rId_ = new ResultText();
            rId_.build(rows_,_cont,rowsGrId_,_doc);
            attributes.addEntry(_cont.getRendKeyWords().getAttrRows(),rId_);
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
        if (_cont.getContext().hasException()) {
            return;
        }
        Argument map_ = RenderExpUtil.calculateReuse(opsMap, _cont);
        if (_cont.getContext().hasException()) {
            return;
        }
        RendReadWrite rw_ = _cont.getLastPage().getRendReadWrite();
        Element write_ = (Element) rw_.getWrite();
        Document doc_ = write_.getOwnerDocument();
        Element docElementSelect_ = doc_.createElement(_cont.getRendKeyWords().getKeyWordSelect());
        if (multiple) {
            docElementSelect_.setAttribute(_cont.getRendKeyWords().getAttrMultiple(), _cont.getRendKeyWords().getAttrMultiple());
        }
        String name_ = elt.getAttribute(_cont.getRendKeyWords().getAttrName());
        String default_ = elt.getAttribute(_cont.getRendKeyWords().getAttrDefault());
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
            if (_cont.getContext().hasException()) {
                return;
            }
            id_ = true;
            docElementSelect_.setAttribute(e.getKey(),txt_);
        }
        if (id_ && !elt.getAttribute(_cont.getRendKeyWords().getAttrValidator()).trim().isEmpty()) {
            docElementSelect_.setAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrValidator()),
                    elt.getAttribute(_cont.getRendKeyWords().getAttrValidator()));
        }
        docElementSelect_.setAttribute(_cont.getRendKeyWords().getAttrName(), name_);
        write_.appendChild(docElementSelect_);
        if (!name_.isEmpty()) {
            processIndexes(_cont,elt,docElementSelect_);
            if (_cont.getContext().hasException()) {
                return;
            }
            Longs stack_ = _cont.getFormsNb();
            if (!stack_.isEmpty()) {
                FormInputCoords inputs_ = new FormInputCoords();
                inputs_.setForm(stack_.last());
                inputs_.setInput(_cont.getIndexes().getNb());
                StringList allOptions_ = new StringList();
                ElementList elts_ = docElementSelect_.getElementsByTagName(_cont.getRendKeyWords().getKeyWordOption());
                int nbElts_ = elts_.getLength();
                for (int i = 0; i < nbElts_; i++) {
                    Element opt_ = elts_.item(i);
                    allOptions_.add(opt_.getAttribute(_cont.getRendKeyWords().getAttrValue()));
                }
                _cont.getHtmlPage().getSelects().put(inputs_, allOptions_);
            }
        }
        for (EntryCust<String,ResultText> e: attributes.entryList()) {
            ResultText res_ = e.getValue();
            String txt_ = ResultText.render(res_.getOpExp(), res_.getTexts(), _cont);
            if (_cont.getContext().hasException()) {
                return;
            }
            docElementSelect_.setAttribute(e.getKey(),txt_);
        }
        processBlock(_cont);
    }

    private void processOptionsMapEnum(Configuration _conf, Struct _extractedMap,
                                       Document _docSelect, Element _docElementSelect) {
        Argument argDef_ = RenderExpUtil.calculateReuse(opsDefault, _conf);
        if (_conf.getContext().hasException()) {
            return;
        }
        processOptionsMapEnumName(_conf,_extractedMap,_docSelect,_docElementSelect,argDef_.getStruct());
    }

    private void processOptionsMapEnumName(Configuration _conf, Struct _extractedMap,
                                           Document _docSelect, Element _docElementSelect, Struct _returnedVarValue) {
        CustList<Struct> obj_ = values(_conf,_returnedVarValue);
        if (_conf.getContext().hasException()) {
            return;
        }
        Argument arg_ = iteratorMultTable(_extractedMap,_conf);
        if (_conf.getContext().hasException()) {
            return;
        }
        Struct l_;
        l_ = arg_.getStruct();
        processOptions(_conf, _docSelect, _docElementSelect, obj_, l_);
    }

    private void processOptions(Configuration _conf, Document _docSelect, Element _docElementSelect, CustList<Struct> _obj, Struct _l) {
        while (true) {
            Argument hasNext_ = hasNextPair(_l, _conf);
            if (_conf.getContext().hasException()) {
                return;
            }
            if (BooleanStruct.of(false).sameReference(hasNext_.getStruct())) {
                break;
            }
            Argument nextPair_ = nextPair(_l, _conf);
            if (_conf.getContext().hasException()) {
                return;
            }
            Struct entry_ = nextPair_.getStruct();
            Argument first_ = first(entry_, _conf);
            if (_conf.getContext().hasException()) {
                return;
            }
            Struct o_ = first_.getStruct();
            if (o_ == NullStruct.NULL_VALUE) {
                continue;
            }
            Element option_ = _docSelect.createElement(_conf.getRendKeyWords().getKeyWordOption());
            String value_ = processOptionValue(_conf, o_);
            if (_conf.getContext().hasException()) {
                return;
            }
            option_.setAttribute(_conf.getRendKeyWords().getAttrValue(),value_);
            for (Struct n: _obj) {
                if (n.sameReference(o_)) {
                    option_.setAttribute(_conf.getRendKeyWords().getAttrSelected(), _conf.getRendKeyWords().getAttrSelected());
                    break;
                }
            }
            Argument second_ = second(entry_, _conf);
            if (_conf.getContext().hasException()) {
                return;
            }
            String txt_ = processOptionText(_conf, second_);
            if (_conf.getContext().hasException()) {
                return;
            }
            option_.appendChild(_docSelect.createTextNode(txt_));
            _docElementSelect.appendChild(option_);
        }
    }
    private String processOptionValue(Configuration _conf, Struct _arg) {
        BeanLgNames stds_ = _conf.getAdvStandards();
        if (opsConverterField.isEmpty()) {
            return getStringKey(_conf, _arg);
        }
        LocalVariable locVar_ = LocalVariable.newLocalVariable(_arg,_conf.getStandards().getAliasObject());
        _conf.getLastPage().putLocalVar(varNameConverterField, locVar_);
        Argument arg_ = RenderExpUtil.calculateReuse(opsConverterField, _conf);
        _conf.getLastPage().removeLocalVar(varNameConverterField);
        if (_conf.getContext().hasException()) {
            return EMPTY_STRING;
        }
        return stds_.processString(arg_,_conf);
    }
    private String processOptionText(Configuration _conf, Argument _arg) {
        BeanLgNames stds_ = _conf.getAdvStandards();
        if (opsConverterFieldValue.isEmpty()) {
            return stds_.processString(_arg,_conf);
        }
        LocalVariable locVar_ = LocalVariable.newLocalVariable(_arg.getStruct(),_conf.getStandards().getAliasObject());
        _conf.getLastPage().putLocalVar(varNameConverterFieldValue, locVar_);
        Argument arg_ = RenderExpUtil.calculateReuse(opsConverterFieldValue, _conf);
        _conf.getLastPage().removeLocalVar(varNameConverterFieldValue);
        if (_conf.getContext().hasException()) {
            return EMPTY_STRING;
        }
        return stds_.processString(arg_,_conf);
    }

    CustList<Struct> values(Configuration _conf,Struct _returnedVarValue) {
        IdList<Struct> obj_ = new IdList<Struct>();
        if (multiple) {
            Argument arg_ = iterator(_returnedVarValue,_conf);
            if (_conf.getContext().hasException()) {
                return obj_;
            }
            Struct it_ = arg_.getStruct();
            while (true) {
                Argument hasNext_ = hasNext(it_, _conf);
                if (_conf.getContext().hasException()) {
                    return obj_;
                }
                if (BooleanStruct.of(false).sameReference(hasNext_.getStruct())) {
                    break;
                }
                Argument next_ = next(it_, _conf);
                if (_conf.getContext().hasException()) {
                    return obj_;
                }
                obj_.add(next_.getStruct());
            }
        } else {
            obj_.add(_returnedVarValue);
        }
        return obj_;
    }
    private void processIndexes(Configuration _cont, Element _read, Element _write) {
        FieldUpdates f_ = new FieldUpdates();
        f_.setId(id);
        f_.setIdClass(idClass);
        f_.setIdName(idName);
        f_.setOpsRead(opsRead);
        f_.setOpsWrite(opsWrite);
        f_.setVarName(varName);
        f_.setVarNameConverter(varNameConverter);
        f_.setOpsConverter(opsConverter);
        f_.setArrayConverter(arrayConverter);
        fetchName(_cont, _read, _write, f_);
        fetchValue(_cont,_read,_write,opsValue,varNameConverterField,opsConverterField);
    }
}
