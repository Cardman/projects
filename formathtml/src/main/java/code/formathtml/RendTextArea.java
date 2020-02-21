package code.formathtml;

import code.expressionlanguage.errors.custom.BadElError;
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

    private String varNameConverter = "";
    private String varNameConverterField = "";
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
        r_.build(_cont, _doc,elt,StringList.concat(_cont.getPrefix(),ATTRIBUTE_VAR_VALUE));
        opsRead = r_.getOpsRead();
        opsValue = r_.getOpsValue();
        opsWrite = r_.getOpsWrite();
        varName = r_.getVarName();
        idField = r_.getIdField();
        MethodAccessKind st_ = _doc.getStaticContext();
        String converterValue_ = elt.getAttribute(StringList.concat(_cont.getPrefix(),ATTRIBUTE_CONVERT_VALUE));
        if (!opsRead.isEmpty()){
            Mapping m_ = new Mapping();
            m_.setArg(opsRead.last().getResultClass());
            m_.setParam(_cont.getStandards().getAliasCharSequence());
            if (!Templates.isCorrectOrNumbers(m_,_cont)) {
                if (converterValue_.trim().isEmpty()) {
                    BadElError badEl_ = new BadElError();
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
                    BadElError badEl_ = new BadElError();
                    badEl_.setFileName(_cont.getCurrentFileName());
                    badEl_.setIndexFile(_cont.getCurrentLocationIndex());
                    _cont.getClasses().addError(badEl_);
                }
            }
        }
        String converterField_ = elt.getAttribute(StringList.concat(_cont.getPrefix(),ATTRIBUTE_CONVERT_FIELD));
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
                BadElError badEl_ = new BadElError();
                badEl_.setFileName(_cont.getCurrentFileName());
                badEl_.setIndexFile(_cont.getCurrentLocationIndex());
                _cont.getClasses().addError(badEl_);
            }
        }
        String id_ = elt.getAttribute(ATTRIBUTE_ID);
        if (!id_.isEmpty()) {
            ResultText rId_ = new ResultText();
            rId_.buildId(id_,_cont,_doc);
            attributesText.put(ATTRIBUTE_ID,rId_);
        }
        String prefixWrite_ = _cont.getPrefix();
        String prefGr_ = StringList.concat(prefixWrite_, ATTRIBUTE_GROUP_ID);
        String groupId_ = elt.getAttribute(prefGr_);
        if (!groupId_.isEmpty()) {
            ResultText rId_ = new ResultText();
            rId_.buildId(groupId_,_cont,_doc);
            attributesText.put(prefGr_,rId_);
        }
        String rows_ = elt.getAttribute(ATTRIBUTE_ROWS);
        if (!rows_.isEmpty()) {
            ResultText rId_ = new ResultText();
            rId_.build(rows_,_cont,_doc);
            attributes.addEntry(ATTRIBUTE_ROWS,rId_);
        }
        String cols_ = elt.getAttribute(ATTRIBUTE_COLS);
        if (!cols_.isEmpty()) {
            ResultText rId_ = new ResultText();
            rId_.build(cols_,_cont,_doc);
            attributes.addEntry(ATTRIBUTE_COLS,rId_);
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
        Element docElementSelect_ = doc_.createElement(TEXT_AREA);
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
        if (elt.hasAttribute(StringList.concat(_cont.getPrefix(),ATTRIBUTE_VALIDATOR))) {
            docElementSelect_.setAttribute(StringList.concat(_cont.getPrefix(),ATTRIBUTE_VALIDATOR),elt.getAttribute(StringList.concat(_cont.getPrefix(),ATTRIBUTE_VALIDATOR)));
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
