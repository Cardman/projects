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
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.Element;
import code.util.StringList;

public abstract class AnaRendInput extends AnaRendElement {
    private OperationNode rootRead;
    private OperationNode rootValue;
    private OperationNode rootConverter;
    private OperationNode rootConverterField;
    private String varName = EMPTY_STRING;
    private String varNameConverter = EMPTY_STRING;
    private String varNameConverterField = EMPTY_STRING;
    private String id = EMPTY_STRING;
    private String idClass = EMPTY_STRING;
    private String idName = EMPTY_STRING;
    private String className = EMPTY_STRING;
    private ResultInput resultInput;
    AnaRendInput(Element _elt, OffsetsBlock _offset) {
        super(_elt, _offset);
    }

    protected void processAnaInput(Configuration _cont, Element _read, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        ResultInput r_ = new ResultInput();
        r_.build(_cont, this, _read,StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrVarValue()), _anaDoc, _page);
        resultInput = r_;
        rootRead = r_.getOpsReadRoot();
        rootValue = r_.getOpsValueRoot();
        varName = r_.getVarName();
        id = r_.getId();
        idClass = r_.getIdClass();
        idName = r_.getIdName();
        className = r_.getClassName();
        String converterValue_ = _read.getAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertValue()));
        if (!converterValue_.trim().isEmpty()) {
            Mapping m_ = new Mapping();
            m_.setArg(r_.getOpsReadRoot().getResultClass());
            m_.setParam(_page.getStandards().getAliasCharSequence());
            if (!AnaTemplates.isCorrectOrNumbers(m_, _page)) {
                String string_ = _page.getStandards().getAliasString();
                StringList varNames_ = new StringList();
                String varLoc_ = AnaRendBlock.lookForVar(varNames_, _page);
                varNames_.add(varLoc_);
                varNameConverter = varLoc_;
                AnaLocalVariable lv_ = new AnaLocalVariable();
                lv_.setClassName(string_);
                _page.getInfosVars().addEntry(varLoc_,lv_);
                String preRend_ = StringList.concat(converterValue_,AnaRendBlock.LEFT_PAR, varLoc_,AnaRendBlock.RIGHT_PAR);
                int attr_ = getAttributeDelimiter(StringList.concat(_cont.getPrefix(), _cont.getRendKeyWords().getAttrConvertValue()));
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
                    AnalyzingDoc.addError(badEl_, _anaDoc, _page);
                }
            }
        } else {
            String clName_ = _read.getAttribute(StringList.concat(_cont.getPrefix(), _cont.getRendKeyWords().getAttrClassName()));
            if (!clName_.isEmpty()) {
                if (!_cont.getAdvStandards().isConveritble(clName_)) {
                    int attr_ = getAttributeDelimiter(StringList.concat(_cont.getPrefix(), _cont.getRendKeyWords().getAttrClassName()));
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_anaDoc.getFileName());
                    badEl_.setIndexFile(attr_);
                    badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            clName_,
                            clName_);
                    AnalyzingDoc.addError(badEl_, _anaDoc, _page);
                }
            } else if (rootRead != null) {
                if (!_cont.getAdvStandards().isConveritble(r_.getOpsReadRoot().getResultClass().getSingleNameOrEmpty())) {
                    int attr_ = getAttributeDelimiter(StringList.concat(_cont.getPrefix(), _cont.getRendKeyWords().getAttrClassName()));
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_anaDoc.getFileName());
                    badEl_.setIndexFile(attr_);
                    badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(rootRead.getResultClass().getNames(),AND_ERR),
                            clName_);
                    AnalyzingDoc.addError(badEl_, _anaDoc, _page);
                }
            }
        }
        String converterField_ = _read.getAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertField()));
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
                AnalyzingDoc.addError(badEl_, _anaDoc, _page);
            }
        }
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

    public String getVarNameConverterField() {
        return varNameConverterField;
    }

    public ResultInput getResultInput() {
        return resultInput;
    }
}
