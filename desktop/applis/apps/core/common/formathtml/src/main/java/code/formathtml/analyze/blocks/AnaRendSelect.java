package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.util.IterableAnalysisResult;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.StringExpUtil;
import code.formathtml.Configuration;
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.analyze.ResultInput;
import code.formathtml.analyze.ResultText;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.Element;
import code.util.StringList;
import code.util.StringMap;

public final class AnaRendSelect extends AnaRendParentBlock {
    private OperationNode rootRead ;
    private OperationNode rootValue ;
    private OperationNode rootMap ;
    private OperationNode rootDefault ;
    private OperationNode rootConverter ;
    private OperationNode rootConverterField ;
    private OperationNode rootConverterFieldValue ;
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
    private String className = EMPTY_STRING;
    private boolean arrayConverter;
    private ResultInput resultInput;

    AnaRendSelect(Element _elt, OffsetsBlock _offset) {
        super(_offset);
        elt = _elt;
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont, AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        ResultInput r_ = new ResultInput();
        r_.build(_cont, this, elt,_cont.getRendKeyWords().getAttrVarValue(), _anaDoc, _page);
        rootRead = r_.getOpsReadRoot();
        rootValue = r_.getOpsValueRoot();
        varName = r_.getVarName();
        resultInput = r_;
        id = r_.getId();
        idClass = r_.getIdClass();
        idName = r_.getIdName();
        className = r_.getClassName();
        String id_ = elt.getAttribute(_cont.getRendKeyWords().getAttrId());
        if (!id_.isEmpty()) {
            ResultText rId_ = new ResultText();
            int off_ = getAttributeDelimiter(_cont.getRendKeyWords().getAttrId());
            rId_.buildIdAna(id_, off_, _anaDoc, _page);
            attributesText.put(_cont.getRendKeyWords().getAttrId(),rId_);
        }
        String prefixWrite_ = _cont.getPrefix();
        String prefGr_ = StringList.concat(prefixWrite_, _cont.getRendKeyWords().getAttrGroupId());
        String groupId_ = elt.getAttribute(_cont.getRendKeyWords().getAttrGroupId());
        if (!groupId_.isEmpty()) {
            ResultText rId_ = new ResultText();
            int off_ = getAttributeDelimiter(_cont.getRendKeyWords().getAttrGroupId());
            rId_.buildIdAna(groupId_, off_, _anaDoc, _page);
            attributesText.put(prefGr_,rId_);
        }
        multiple = elt.hasAttribute(_cont.getRendKeyWords().getAttrMultiple());
        String map_ = elt.getAttribute(_cont.getRendKeyWords().getAttrMap());
        int offMap_ = getAttributeDelimiter(_cont.getRendKeyWords().getAttrMap());
        rootMap = RenderAnalysis.getRootAnalyzedOperations(map_, 0, _anaDoc, _page);
        String converterValue_ = elt.getAttribute(_cont.getRendKeyWords().getAttrConvertValue());
        if (multiple) {
            if (converterValue_.trim().isEmpty()) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFileName(_anaDoc.getFileName());
                badEl_.setIndexFile(getOffset().getOffsetTrim());
                badEl_.buildError(_anaDoc.getRendAnalysisMessages().getEmptyAttr(),
                        _cont.getRendKeyWords().getAttrConvertValue());
                AnalyzingDoc.addError(badEl_, _anaDoc, _page);
            }
            String string_ = _page.getAliasString();
            StringList varNames_ = new StringList();
            String varLoc_ = AnaRendBlock.lookForVar(varNames_, _page);
            varNames_.add(varLoc_);
            varNameConverter = varLoc_;
            AnaLocalVariable lv_ = new AnaLocalVariable();
            arrayConverter = true;
            lv_.setClassName(StringExpUtil.getPrettyArrayType(string_));
            _page.getInfosVars().addEntry(varLoc_,lv_);
            String preRend_ = StringList.concat(converterValue_,AnaRendBlock.LEFT_PAR, varLoc_,AnaRendBlock.RIGHT_PAR);
            int offConvValue_ = getAttributeDelimiter(_cont.getRendKeyWords().getAttrConvertValue());
            rootConverter = RenderAnalysis.getRootAnalyzedOperations(preRend_, 0, _anaDoc, _page);
            for (String v:varNames_) {
                _page.getInfosVars().removeKey(v);
            }
            StringList names_ = r_.getOpsValueRoot().getResultClass().getNames();
            if (!r_.getOpsValueRoot().getResultClass().isVariable()) {
                IterableAnalysisResult it_ = _page.getForEachFetch().getCustomType(names_,"");
                StringList candidates_ = it_.getClassName();
                if (!candidates_.onlyOneElt()) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_anaDoc.getFileName());
                    badEl_.setIndexFile(offConvValue_);
                    badEl_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                            StringList.join(candidates_,AND_ERR));
                    AnalyzingDoc.addError(badEl_, _anaDoc, _page);
                }
                Mapping m_ = new Mapping();
                m_.setArg(rootConverter.getResultClass());
                m_.setParam(r_.getOpsReadRoot().getResultClass());
                if (!AnaTemplates.isCorrectOrNumbers(m_, _page)) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_anaDoc.getFileName());
                    badEl_.setIndexFile(getOffset().getOffsetTrim());
                    badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(rootConverter.getResultClass().getNames(),AND_ERR),
                            StringList.join(rootRead.getResultClass().getNames(),AND_ERR));
                    AnalyzingDoc.addError(badEl_, _anaDoc, _page);
                }
            }
        } else if (rootRead != null){
            Mapping m_ = new Mapping();
            m_.setArg(r_.getOpsReadRoot().getResultClass());
            m_.setParam(_page.getAliasCharSequence());
            if (!AnaTemplates.isCorrectOrNumbers(m_, _page)) {
                if (converterValue_.trim().isEmpty()) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_anaDoc.getFileName());
                    badEl_.setIndexFile(getOffset().getOffsetTrim());
                    badEl_.buildError(_anaDoc.getRendAnalysisMessages().getEmptyAttr(),
                            _cont.getRendKeyWords().getAttrConvertValue());
                    AnalyzingDoc.addError(badEl_, _anaDoc, _page);
                }
                String string_ = _page.getAliasString();
                StringList varNames_ = new StringList();
                String varLoc_ = AnaRendBlock.lookForVar(varNames_, _page);
                varNames_.add(varLoc_);
                varNameConverter = varLoc_;
                AnaLocalVariable lv_ = new AnaLocalVariable();
                lv_.setClassName(string_);
                _page.getInfosVars().addEntry(varLoc_,lv_);
                int offConvValue_ = getAttributeDelimiter(_cont.getRendKeyWords().getAttrConvertValue());
                String preRend_ = StringList.concat(converterValue_,AnaRendBlock.LEFT_PAR, varLoc_,AnaRendBlock.RIGHT_PAR);
                rootConverter = RenderAnalysis.getRootAnalyzedOperations(preRend_, 0, _anaDoc, _page);
                for (String v:varNames_) {
                    _page.getInfosVars().removeKey(v);
                }
                m_.setArg(rootConverter.getResultClass());
                m_.setParam(r_.getOpsReadRoot().getResultClass());
                if (!AnaTemplates.isCorrectOrNumbers(m_, _page)) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_anaDoc.getFileName());
                    badEl_.setIndexFile(offConvValue_);
                    badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(rootConverter.getResultClass().getNames(),AND_ERR),
                            StringList.join(rootRead.getResultClass().getNames(),AND_ERR));
                    AnalyzingDoc.addError(badEl_, _anaDoc, _page);
                }
            } else if (!converterValue_.trim().isEmpty()) {
                String string_ = _page.getAliasString();
                StringList varNames_ = new StringList();
                String varLoc_ = AnaRendBlock.lookForVar(varNames_, _page);
                varNames_.add(varLoc_);
                varNameConverter = varLoc_;
                AnaLocalVariable lv_ = new AnaLocalVariable();
                lv_.setClassName(string_);
                _page.getInfosVars().addEntry(varLoc_,lv_);
                String preRend_ = StringList.concat(converterValue_,AnaRendBlock.LEFT_PAR, varLoc_,AnaRendBlock.RIGHT_PAR);
                int offConvValue_ = getAttributeDelimiter(_cont.getRendKeyWords().getAttrConvertValue());
                rootConverter = RenderAnalysis.getRootAnalyzedOperations(preRend_, 0, _anaDoc, _page);
                for (String v:varNames_) {
                    _page.getInfosVars().removeKey(v);
                }
                m_.setArg(rootConverter.getResultClass());
                m_.setParam(r_.getOpsReadRoot().getResultClass());
                if (!AnaTemplates.isCorrectOrNumbers(m_, _page)) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_anaDoc.getFileName());
                    badEl_.setIndexFile(offConvValue_);
                    badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(rootConverter.getResultClass().getNames(),AND_ERR),
                            StringList.join(rootRead.getResultClass().getNames(),AND_ERR));
                    AnalyzingDoc.addError(badEl_, _anaDoc, _page);
                }
            }
        }
        String converterField_ = elt.getAttribute(_cont.getRendKeyWords().getAttrConvertField());
        if (!converterField_.trim().isEmpty()) {
            String object_ = _page.getAliasObject();
            StringList varNames_ = new StringList();
            String varLoc_ = AnaRendBlock.lookForVar(varNames_, _page);
            varNames_.add(varLoc_);
            varNameConverterField = varLoc_;
            AnaLocalVariable lv_ = new AnaLocalVariable();
            lv_.setClassName(object_);
            _page.getInfosVars().addEntry(varLoc_,lv_);
            String preRend_ = StringList.concat(converterField_,AnaRendBlock.LEFT_PAR, varLoc_,AnaRendBlock.RIGHT_PAR);
            int offConvValue_ = getAttributeDelimiter(_cont.getRendKeyWords().getAttrConvertField());
            rootConverterField = RenderAnalysis.getRootAnalyzedOperations(preRend_, 0, _anaDoc, _page);
            for (String v:varNames_) {
                _page.getInfosVars().removeKey(v);
            }
            Mapping m_ = new Mapping();
            m_.setArg(rootConverterField.getResultClass());
            m_.setParam(_page.getAliasCharSequence());
            if (!AnaTemplates.isCorrectOrNumbers(m_, _page)) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFileName(_anaDoc.getFileName());
                badEl_.setIndexFile(offConvValue_);
                badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringList.join(rootConverterField.getResultClass().getNames(),AND_ERR),
                        _page.getAliasCharSequence());
                AnalyzingDoc.addError(badEl_, _anaDoc, _page);
            }
        }
        String converterFieldValue_ = elt.getAttribute(_cont.getRendKeyWords().getAttrConvertFieldValue());
        if (!converterFieldValue_.trim().isEmpty()) {
            String object_ = _page.getAliasObject();
            StringList varNames_ = new StringList();
            String varLoc_ = AnaRendBlock.lookForVar(varNames_, _page);
            varNames_.add(varLoc_);
            varNameConverterFieldValue = varLoc_;
            AnaLocalVariable lv_ = new AnaLocalVariable();
            lv_.setClassName(object_);
            _page.getInfosVars().addEntry(varLoc_,lv_);
            String preRend_ = StringList.concat(converterFieldValue_,AnaRendBlock.LEFT_PAR, varLoc_,AnaRendBlock.RIGHT_PAR);
            int offConvValue_ = getAttributeDelimiter(_cont.getRendKeyWords().getAttrConvertFieldValue());
            rootConverterFieldValue = RenderAnalysis.getRootAnalyzedOperations(preRend_, 0, _anaDoc, _page);
            for (String v:varNames_) {
                _page.getInfosVars().removeKey(v);
            }
            Mapping m_ = new Mapping();
            m_.setArg(rootConverterFieldValue.getResultClass());
            m_.setParam(_page.getAliasCharSequence());
            if (!AnaTemplates.isCorrectOrNumbers(m_, _page)) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFileName(_anaDoc.getFileName());
                badEl_.setIndexFile(offConvValue_);
                badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringList.join(rootConverterFieldValue.getResultClass().getNames(),AND_ERR),
                        _page.getAliasCharSequence());
                AnalyzingDoc.addError(badEl_, _anaDoc, _page);
            }
        }
        String default_ = elt.getAttribute(_cont.getRendKeyWords().getAttrDefault());
        if (!default_.isEmpty()) {
            String mName_ = elt.getAttribute(_cont.getRendKeyWords().getAttrConvert());
            if (mName_.trim().isEmpty()) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFileName(_anaDoc.getFileName());
                badEl_.setIndexFile(getOffset().getOffsetTrim());
                badEl_.buildError(_anaDoc.getRendAnalysisMessages().getEmptyAttr(),
                        _cont.getRendKeyWords().getAttrConvert());
                AnalyzingDoc.addError(badEl_, _anaDoc, _page);
            }
            String concat_ = StringList.concat(mName_,LEFT_PAR,STR,default_,STR,RIGHT_PAR);
            int offConvValue_ = getAttributeDelimiter(_cont.getRendKeyWords().getAttrConvert());
            rootDefault = RenderAnalysis.getRootAnalyzedOperations(concat_, 0, _anaDoc, _page);
            Mapping m_ = new Mapping();
            m_.setArg(rootDefault.getResultClass());
            if (!multiple) {
                m_.setParam(_page.getAliasCharSequence());
                if (!AnaTemplates.isCorrectOrNumbers(m_, _page)) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_anaDoc.getFileName());
                    badEl_.setIndexFile(getAttributeDelimiter(_cont.getRendKeyWords().getAttrDefault()));
                    badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(rootDefault.getResultClass().getNames(),AND_ERR),
                            _page.getAliasCharSequence());
                    AnalyzingDoc.addError(badEl_, _anaDoc, _page);
                }
            } else {
                IterableAnalysisResult it_ = _page.getForEachFetch().getCustomType(rootDefault.getResultClass().getNames(),"");
                StringList candidates_ = it_.getClassName();
                if (!candidates_.onlyOneElt()) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_anaDoc.getFileName());
                    badEl_.setIndexFile(getAttributeDelimiter(_cont.getRendKeyWords().getAttrDefault()));
                    badEl_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                            StringList.join(candidates_,AND_ERR));
                    AnalyzingDoc.addError(badEl_, _anaDoc, _page);
                }
            }
        }
        String rows_ = elt.getAttribute(_cont.getRendKeyWords().getAttrRows());
        int rowsGrId_ = getAttributeDelimiter(_cont.getRendKeyWords().getAttrRows());
        if (!rows_.isEmpty()) {
            ResultText rId_ = new ResultText();
            rId_.buildAna(rows_, rowsGrId_, _anaDoc, _page);
            attributes.addEntry(_cont.getRendKeyWords().getAttrRows(),rId_);
        }
    }

    public StringMap<ResultText> getAttributesText() {
        return attributesText;
    }

    public StringMap<ResultText> getAttributes() {
        return attributes;
    }

    public String getVarNameConverterField() {
        return varNameConverterField;
    }

    public OperationNode getRootValue() {
        return rootValue;
    }

    public OperationNode getRootRead() {
        return rootRead;
    }

    public OperationNode getRootConverterField() {
        return rootConverterField;
    }

    public OperationNode getRootConverter() {
        return rootConverter;
    }

    public OperationNode getRootConverterFieldValue() {
        return rootConverterFieldValue;
    }

    public String getVarName() {
        return varName;
    }

    public String getVarNameConverter() {
        return varNameConverter;
    }

    public String getId() {
        return id;
    }

    public String getIdName() {
        return idName;
    }

    public String getIdClass() {
        return idClass;
    }

    public String getClassName() {
        return className;
    }

    public Element getElt() {
        return elt;
    }

    public OperationNode getRootDefault() {
        return rootDefault;
    }

    public OperationNode getRootMap() {
        return rootMap;
    }

    public String getVarNameConverterFieldValue() {
        return varNameConverterFieldValue;
    }

    public boolean isArrayConverter() {
        return arrayConverter;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public ResultInput getResultInput() {
        return resultInput;
    }
}
