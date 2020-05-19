package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.variables.LocalVariable;
import code.formathtml.exec.*;
import code.formathtml.util.FieldUpdates;
import code.sml.Element;
import code.util.CustList;
import code.util.StringList;

public abstract class RendInput extends RendElement {
    private CustList<RendDynOperationNode> opsRead = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsValue = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsWrite = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsConverter = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsConverterField = new CustList<RendDynOperationNode>();
    private String varName = EMPTY_STRING;
    private String varNameConverter = EMPTY_STRING;
    private String varNameConverterField = EMPTY_STRING;
    private String id = EMPTY_STRING;
    private String idClass = EMPTY_STRING;
    private String idName = EMPTY_STRING;
    RendInput(Element _elt, OffsetsBlock _offset) {
        super(_elt, _offset);
    }

    protected void processAnaInput(Configuration _cont, RendDocumentBlock _doc, Element _read) {
        ResultInput r_ = new ResultInput();
        r_.build(_cont, this,_doc,_read,StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrVarValue()));
        opsRead = r_.getOpsRead();
        opsValue = r_.getOpsValue();
        opsWrite = r_.getOpsWrite();
        varName = r_.getVarName();
        id = r_.getId();
        idClass = r_.getIdClass();
        idName = r_.getIdName();
        String converterValue_ = _read.getAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertValue()));
        if (!converterValue_.trim().isEmpty()) {
            Mapping m_ = new Mapping();
            m_.setArg(opsRead.last().getResultClass());
            m_.setParam(_cont.getStandards().getAliasCharSequence());
            if (!Templates.isCorrectOrNumbers(m_,_cont.getContext())) {
                String string_ = _cont.getStandards().getAliasString();
                StringList varNames_ = new StringList();
                String varLoc_ = RendBlock.lookForVar(_cont, varNames_);
                varNames_.add(varLoc_);
                varNameConverter = varLoc_;
                LocalVariable lv_ = new LocalVariable();
                lv_.setClassName(string_);
                _cont.getLocalVarsAna().last().addEntry(varLoc_,lv_);
                String preRend_ = StringList.concat(converterValue_,RendBlock.LEFT_PAR, varLoc_,RendBlock.RIGHT_PAR);
                int attr_ = getAttributeDelimiter(StringList.concat(_cont.getPrefix(), _cont.getRendKeyWords().getAttrConvertValue()));
                opsConverter = RenderExpUtil.getAnalyzedOperations(preRend_,attr_,0,_cont);
                for (String v:varNames_) {
                    _cont.getLocalVarsAna().last().removeKey(v);
                }
                m_.setArg(opsConverter.last().getResultClass());
                m_.setParam(opsRead.last().getResultClass());
                if (!Templates.isCorrectOrNumbers(m_,_cont.getContext())) {
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
        String converterField_ = _read.getAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertField()));
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
            int attr_ = getAttributeDelimiter(StringList.concat(_cont.getPrefix(), _cont.getRendKeyWords().getAttrConvertField()));
            opsConverterField = RenderExpUtil.getAnalyzedOperations(preRend_,attr_,0,_cont);
            for (String v:varNames_) {
                _cont.getLocalVarsAna().last().removeKey(v);
            }
            Mapping m_ = new Mapping();
            m_.setArg(opsConverterField.last().getResultClass());
            m_.setParam(_cont.getStandards().getAliasCharSequence());
            if (!Templates.isCorrectOrNumbers(m_,_cont.getContext())) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFileName(_cont.getCurrentFileName());
                badEl_.setIndexFile(attr_);
                badEl_.buildError(_cont.getContext().getAnalysisMessages().getBadImplicitCast(),
                        StringList.join(opsConverterField.last().getResultClass().getNames(),AND_ERR),
                        _cont.getStandards().getAliasCharSequence());
                _cont.addError(badEl_);
            }
        }
    }

    @Override
    public void reduce(Configuration _context) {
        super.reduce(_context);
        opsRead = reduceList(opsRead);
        opsValue = reduceList(opsValue);
        opsWrite = reduceList(opsWrite);
        opsConverter = reduceList(opsConverter);
        opsConverterField = reduceList(opsConverterField);
    }
    protected Argument processIndexes(Configuration _cont, Element _read, Element _write) {
        FieldUpdates f_ = new FieldUpdates();
        f_.setId(id);
        f_.setIdClass(idClass);
        f_.setIdName(idName);
        f_.setOpsRead(opsRead);
        f_.setOpsWrite(opsWrite);
        f_.setVarName(varName);
        f_.setVarNameConverter(varNameConverter);
        f_.setOpsConverter(opsConverter);
        Argument arg_ = fetchName(_cont, _read, _write, f_);
        fetchValue(_cont,_read,_write,opsValue,varNameConverterField,opsConverterField);
        _write.removeAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertValue()));
        _write.removeAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertField()));
        _write.removeAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertFieldValue()));
        return arg_;
    }

}
