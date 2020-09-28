package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.formathtml.Configuration;
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.analyze.ResultInput;
import code.formathtml.analyze.ResultText;
import code.formathtml.util.AnalyzingDoc;
import code.sml.Element;
import code.util.StringList;
import code.util.StringMap;

public final class AnaRendTextArea extends AnaRendParentBlock {
    private OperationNode rootRead;
    private OperationNode rootValue;
    private OperationNode rootConverter;
    private OperationNode rootConverterField;
    private StringMap<ResultText> attributesText = new StringMap<ResultText>();
    private StringMap<ResultText> attributes = new StringMap<ResultText>();

    private String varNameConverter = EMPTY_STRING;
    private String varNameConverterField = EMPTY_STRING;
    private String varName = EMPTY_STRING;
    private String id = EMPTY_STRING;
    private String idClass = EMPTY_STRING;
    private String idName = EMPTY_STRING;
    private String className = EMPTY_STRING;
    private Element elt;
    private ResultInput resultInput;

    protected AnaRendTextArea(Element _elt, OffsetsBlock _offset) {
        super(_offset);
        elt = _elt;
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont, AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        ResultInput r_ = new ResultInput();
        r_.build(_cont, this, elt,StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrVarValue()), _anaDoc, _page);
        rootRead = r_.getOpsReadRoot();
        rootValue = r_.getOpsValueRoot();
        varName = r_.getVarName();
        id = r_.getId();
        idClass = r_.getIdClass();
        idName = r_.getIdName();
        className = r_.getClassName();
        resultInput = r_;
        String converterValue_ = elt.getAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertValue()));
        if (rootRead != null){
            Mapping m_ = new Mapping();
            m_.setArg(r_.getOpsReadRoot().getResultClass());
            m_.setParam(_page.getStandards().getAliasCharSequence());
            if (!AnaTemplates.isCorrectOrNumbers(m_, _page)) {
                if (converterValue_.trim().isEmpty()) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_anaDoc.getFileName());
                    badEl_.setIndexFile(getOffset().getOffsetTrim());
                    badEl_.buildError(_anaDoc.getRendAnalysisMessages().getEmptyAttr(),
                            StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertValue()));
                    Configuration.addError(badEl_, _anaDoc, _page);
                }
                String string_ = _page.getStandards().getAliasString();
                StringList varNames_ = new StringList();
                String varLoc_ = AnaRendBlock.lookForVar(varNames_, _page);
                varNames_.add(varLoc_);
                varNameConverter = varLoc_;
                AnaLocalVariable lv_ = new AnaLocalVariable();
                lv_.setClassName(string_);
                _page.getInfosVars().addEntry(varLoc_,lv_);
                String preRend_ = StringList.concat(converterValue_,AnaRendBlock.LEFT_PAR, varLoc_,AnaRendBlock.RIGHT_PAR);
                int attr_ = getAttributeDelimiter(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertValue()));
                rootConverter = RenderAnalysis.getRootAnalyzedOperations(preRend_, 0, _anaDoc, _page);
                for (String v:varNames_) {
                    _page.getInfosVars().removeKey(v);
                }
                m_.setArg(rootConverter.getResultClass());
                m_.setParam(r_.getOpsReadRoot().getResultClass());
                if (!AnaTemplates.isCorrectOrNumbers(m_, _page)) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_anaDoc.getFileName());
                    badEl_.setIndexFile(attr_);
                    badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(rootConverter.getResultClass().getNames(),AND_ERR),
                            StringList.join(rootRead.getResultClass().getNames(),AND_ERR));
                    Configuration.addError(badEl_, _anaDoc, _page);
                }
            }
        }
        String converterField_ = elt.getAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertField()));
        if (!converterField_.trim().isEmpty()) {
            String object_ = _page.getStandards().getAliasObject();
            StringList varNames_ = new StringList();
            String varLoc_ = AnaRendBlock.lookForVar(varNames_, _page);
            varNames_.add(varLoc_);
            varNameConverterField = varLoc_;
            AnaLocalVariable lv_ = new AnaLocalVariable();
            lv_.setClassName(object_);
            _page.getInfosVars().addEntry(varLoc_,lv_);
            String preRend_ = StringList.concat(converterField_,AnaRendBlock.LEFT_PAR, varLoc_,AnaRendBlock.RIGHT_PAR);
            int attr_ = getAttributeDelimiter(StringList.concat(_cont.getPrefix(), _cont.getRendKeyWords().getAttrConvertField()));
            rootConverterField = RenderAnalysis.getRootAnalyzedOperations(preRend_, 0, _anaDoc, _page);
            for (String v:varNames_) {
                _page.getInfosVars().removeKey(v);
            }
            Mapping m_ = new Mapping();
            m_.setArg(rootConverterField.getResultClass());
            m_.setParam(_page.getStandards().getAliasCharSequence());
            if (!AnaTemplates.isCorrectOrNumbers(m_, _page)) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFileName(_anaDoc.getFileName());
                badEl_.setIndexFile(attr_);
                badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringList.join(rootConverterField.getResultClass().getNames(),AND_ERR),
                        _page.getStandards().getAliasCharSequence());
                Configuration.addError(badEl_, _anaDoc, _page);
            }
        }
        String id_ = elt.getAttribute(_cont.getRendKeyWords().getAttrId());
        if (!id_.isEmpty()) {
            ResultText rId_ = new ResultText();
            int off_ = getAttributeDelimiter(_cont.getRendKeyWords().getAttrId());
            rId_.buildAna(id_, off_, _anaDoc, _page);
            attributesText.put(_cont.getRendKeyWords().getAttrId(),rId_);
        }
        String prefixWrite_ = _cont.getPrefix();
        String prefGr_ = StringList.concat(prefixWrite_, _cont.getRendKeyWords().getAttrGroupId());
        String groupId_ = elt.getAttribute(prefGr_);
        int offGrId_ = getAttributeDelimiter(prefGr_);
        if (!groupId_.isEmpty()) {
            ResultText rId_ = new ResultText();
            rId_.buildIdAna(groupId_, offGrId_, _anaDoc, _page);
            attributesText.put(prefGr_,rId_);
        }
        String rows_ = elt.getAttribute(_cont.getRendKeyWords().getAttrRows());
        int rowsGrId_ = getAttributeDelimiter(_cont.getRendKeyWords().getAttrRows());
        if (!rows_.isEmpty()) {
            ResultText rId_ = new ResultText();
            rId_.buildIdAna(rows_, rowsGrId_, _anaDoc, _page);
            attributes.addEntry(_cont.getRendKeyWords().getAttrRows(),rId_);
        }
        String cols_ = elt.getAttribute(_cont.getRendKeyWords().getAttrCols());
        int colsGrId_ = getAttributeDelimiter(_cont.getRendKeyWords().getAttrCols());
        if (!cols_.isEmpty()) {
            ResultText rId_ = new ResultText();
            rId_.buildAna(cols_, colsGrId_, _anaDoc, _page);
            attributes.addEntry(_cont.getRendKeyWords().getAttrCols(),rId_);
        }
    }

    public Element getElt() {
        return elt;
    }

    public String getClassName() {
        return className;
    }

    public String getIdClass() {
        return idClass;
    }

    public String getIdName() {
        return idName;
    }

    public String getId() {
        return id;
    }

    public String getVarNameConverter() {
        return varNameConverter;
    }

    public String getVarName() {
        return varName;
    }

    public OperationNode getRootConverter() {
        return rootConverter;
    }

    public OperationNode getRootConverterField() {
        return rootConverterField;
    }

    public OperationNode getRootRead() {
        return rootRead;
    }

    public OperationNode getRootValue() {
        return rootValue;
    }

    public String getVarNameConverterField() {
        return varNameConverterField;
    }

    public StringMap<ResultText> getAttributes() {
        return attributes;
    }

    public StringMap<ResultText> getAttributesText() {
        return attributesText;
    }

    public ResultInput getResultInput() {
        return resultInput;
    }
}
