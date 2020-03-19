package code.formathtml;

import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.MethodAccessKind;
import code.expressionlanguage.variables.LocalVariable;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.util.BeanCustLgNames;
import code.formathtml.util.FieldUpdates;
import code.sml.Document;
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class RendTextArea extends RendParentBlock implements RendWithEl, RendReducableOperations,RendBuildableElMethod {
    private CustList<RendDynOperationNode> opsRead = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsValue = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsWrite = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsConverter = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsConverterField = new CustList<RendDynOperationNode>();
    private StringMap<ResultText> attributesText = new StringMap<ResultText>();
    private StringMap<ResultText> attributes = new StringMap<ResultText>();

    private String varNameConverter = EMPTY_STRING;
    private String varNameConverterField = EMPTY_STRING;
    private String varName = EMPTY_STRING;
    private ClassField idField;
    private Element elt;
    RendTextArea(Element _elt, OffsetsBlock _offset) {
        super(_offset);
        elt = _elt;
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc) {
        ResultInput r_ = new ResultInput();
        r_.build(_cont, this,_doc,elt,StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrVarValue()));
        opsRead = r_.getOpsRead();
        opsValue = r_.getOpsValue();
        opsWrite = r_.getOpsWrite();
        varName = r_.getVarName();
        idField = r_.getIdField();
        String converterValue_ = elt.getAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertValue()));
        if (!opsRead.isEmpty()){
            Mapping m_ = new Mapping();
            m_.setArg(opsRead.last().getResultClass());
            m_.setParam(_cont.getStandards().getAliasCharSequence());
            if (!Templates.isCorrectOrNumbers(m_,_cont)) {
                if (converterValue_.trim().isEmpty()) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_cont.getCurrentFileName());
                    badEl_.setIndexFile(getOffset().getOffsetTrim());
                    badEl_.buildError(_cont.getRendAnalysisMessages().getEmptyAttr(),
                            StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertValue()));
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
                String preRend_ = StringList.concat(converterValue_,RendBlock.LEFT_PAR,BeanCustLgNames.sufficLocal(_cont.getContext(),varLoc_),RendBlock.RIGHT_PAR);
                int attr_ = getAttributeDelimiter(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertValue()));
                opsConverter = RenderExpUtil.getAnalyzedOperations(preRend_,attr_,0,_cont);
                for (String v:varNames_) {
                    _cont.getLocalVarsAna().last().removeKey(v);
                }
                m_.setArg(opsConverter.last().getResultClass());
                m_.setParam(opsRead.last().getResultClass());
                if (!Templates.isCorrectOrNumbers(m_,_cont)) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_cont.getCurrentFileName());
                    badEl_.setIndexFile(attr_);
                    badEl_.buildError(_cont.getContext().getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(opsConverter.last().getResultClass().getNames(),AND_ERR),
                            StringList.join(opsRead.last().getResultClass().getNames(),AND_ERR));
                    _cont.addError(badEl_);
                }
            }
        }
        String converterField_ = elt.getAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertField()));
        if (!converterField_.trim().isEmpty()) {
            String object_ = _cont.getStandards().getAliasObject();
            StringList varNames_ = new StringList();
            String varLoc_ = RendBlock.lookForVar(_cont, varNames_);
            varNames_.add(varLoc_);
            varNameConverterField = varLoc_;
            LocalVariable lv_ = new LocalVariable();
            lv_.setClassName(object_);
            _cont.getLocalVarsAna().last().addEntry(varLoc_,lv_);
            String preRend_ = StringList.concat(converterField_,RendBlock.LEFT_PAR,BeanCustLgNames.sufficLocal(_cont.getContext(),varLoc_),RendBlock.RIGHT_PAR);
            int attr_ = getAttributeDelimiter(StringList.concat(_cont.getPrefix(), _cont.getRendKeyWords().getAttrConvertField()));
            opsConverterField = RenderExpUtil.getAnalyzedOperations(preRend_,attr_,0,_cont);
            for (String v:varNames_) {
                _cont.getLocalVarsAna().last().removeKey(v);
            }
            Mapping m_ = new Mapping();
            m_.setArg(opsConverterField.last().getResultClass());
            m_.setParam(_cont.getStandards().getAliasCharSequence());
            if (!Templates.isCorrectOrNumbers(m_,_cont)) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFileName(_cont.getCurrentFileName());
                badEl_.setIndexFile(attr_);
                badEl_.buildError(_cont.getContext().getAnalysisMessages().getBadImplicitCast(),
                        StringList.join(opsConverterField.last().getResultClass().getNames(),AND_ERR),
                        _cont.getStandards().getAliasCharSequence());
                _cont.addError(badEl_);
            }
        }
        String id_ = elt.getAttribute(_cont.getRendKeyWords().getAttrId());
        if (!id_.isEmpty()) {
            ResultText rId_ = new ResultText();
            int off_ = getAttributeDelimiter(_cont.getRendKeyWords().getAttrId());
            rId_.buildId(id_,_cont,off_,_doc);
            attributesText.put(_cont.getRendKeyWords().getAttrId(),rId_);
        }
        String prefixWrite_ = _cont.getPrefix();
        String prefGr_ = StringList.concat(prefixWrite_, _cont.getRendKeyWords().getAttrGroupId());
        String groupId_ = elt.getAttribute(prefGr_);
        int offGrId_ = getAttributeDelimiter(prefGr_);
        if (!groupId_.isEmpty()) {
            ResultText rId_ = new ResultText();
            rId_.buildId(groupId_,_cont,offGrId_,_doc);
            attributesText.put(prefGr_,rId_);
        }
        String rows_ = elt.getAttribute(_cont.getRendKeyWords().getAttrRows());
        int rowsGrId_ = getAttributeDelimiter(_cont.getRendKeyWords().getAttrRows());
        if (!rows_.isEmpty()) {
            ResultText rId_ = new ResultText();
            rId_.build(rows_,_cont,rowsGrId_,_doc);
            attributes.addEntry(_cont.getRendKeyWords().getAttrRows(),rId_);
        }
        String cols_ = elt.getAttribute(_cont.getRendKeyWords().getAttrCols());
        int colsGrId_ = getAttributeDelimiter(_cont.getRendKeyWords().getAttrCols());
        if (!cols_.isEmpty()) {
            ResultText rId_ = new ResultText();
            rId_.build(cols_,_cont,colsGrId_,_doc);
            attributes.addEntry(_cont.getRendKeyWords().getAttrCols(),rId_);
        }
    }

    @Override
    public void reduce(Configuration _context) {
        opsRead = reduceList(opsRead);
        opsValue = reduceList(opsValue);
        opsWrite = reduceList(opsWrite);
        opsConverter = reduceList(opsConverter);
        opsConverterField = reduceList(opsConverterField);
        for (EntryCust<String,ResultText> e: attributesText.entryList()) {
            ResultText.reduce(e.getValue().getOpExp());
        }
    }

    @Override
    public void processEl(Configuration _cont) {
        RendReadWrite rw_ = _cont.getLastPage().getRendReadWrite();
        Element write_ = (Element) rw_.getWrite();
        Document doc_ = write_.getOwnerDocument();
        Element docElementSelect_ = doc_.createElement(_cont.getRendKeyWords().getKeyWordTextarea());
        write_.appendChild(docElementSelect_);
        FieldUpdates f_ = new FieldUpdates();
        f_.setIdField(idField);
        f_.setOpsRead(opsRead);
        f_.setOpsWrite(opsWrite);
        f_.setVarName(varName);
        f_.setVarNameConverter(varNameConverter);
        f_.setOpsConverter(opsConverter);
        for (EntryCust<String,ResultText> e: attributesText.entryList()) {
            ResultText res_ = e.getValue();
            String txt_ = ResultText.render(res_.getOpExp(), res_.getTexts(), _cont);
            if (_cont.getContext().hasException()) {
                return;
            }
            docElementSelect_.setAttribute(e.getKey(),txt_);
        }
        if (elt.hasAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrValidator()))) {
            docElementSelect_.setAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrValidator()),
                    elt.getAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrValidator())));
        }
        fetchName(_cont, elt, docElementSelect_, f_);
        fetchValue(_cont,elt,docElementSelect_,opsValue,varNameConverterField,opsConverterField);
        if (_cont.getContext().hasException()) {
            return;
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
}
