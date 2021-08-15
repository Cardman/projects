package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.util.IterableAnalysisResult;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.StringExpUtil;
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.analyze.ResultInput;
import code.formathtml.analyze.ResultText;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.util.InputInfo;
import code.sml.Element;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class AnaRendSelect extends AnaRendParentBlock implements AnaRendBuildEl {
    private OperationNode rootRead;
    private OperationNode rootValue;
    private OperationNode rootMap;
    private OperationNode rootDefault;
    private OperationNode rootConverter;
    private OperationNode rootConverterField;
    private OperationNode rootConverterFieldValue;
    private final StringMap<ResultText> attributesText = new StringMap<ResultText>();
    private final StringMap<ResultText> attributes = new StringMap<ResultText>();
    private String varName = EMPTY_STRING;
    private InputInfo varNames = new InputInfo();
    private String id = EMPTY_STRING;
    private String idClass = EMPTY_STRING;
    private String idName = EMPTY_STRING;
    private final Element elt;
    private boolean multiple;
    private String varNameConverter = EMPTY_STRING;
    private String varNameConverterField = EMPTY_STRING;
    private String varNameConverterFieldValue = EMPTY_STRING;
    private String className = EMPTY_STRING;
    private boolean arrayConverter;
    private ResultInput resultInput;

    AnaRendSelect(Element _elt, int _offset) {
        super(_offset);
        elt = _elt;
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        ResultInput r_ = new ResultInput();
        r_.build(this, elt,_anaDoc.getRendKeyWords().getAttrVarValue(), _anaDoc, _page);
        varNames = r_.getVarNamesParams();
        rootRead = r_.getOpsReadRoot();
        rootValue = r_.getOpsValueRoot();
        varName = r_.getVarName();
        resultInput = r_;
        id = r_.getId();
        idClass = r_.getIdClass();
        idName = r_.getIdName();
        className = r_.getClassName();
        String id_ = elt.getAttribute(_anaDoc.getRendKeyWords().getAttrId());
        if (!id_.isEmpty()) {
            ResultText rId_ = new ResultText();
            int off_ = getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrId());
            rId_.buildIdAna(id_, off_, _anaDoc, _page);
            attributesText.put(_anaDoc.getRendKeyWords().getAttrId(),rId_);
        }
        String prefixWrite_ = _anaDoc.getPrefix();
        String prefGr_ = StringUtil.concat(prefixWrite_, _anaDoc.getRendKeyWords().getAttrGroupId());
        String groupId_ = elt.getAttribute(_anaDoc.getRendKeyWords().getAttrGroupId());
        if (!groupId_.isEmpty()) {
            ResultText rId_ = new ResultText();
            int off_ = getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrGroupId());
            rId_.buildIdAna(groupId_, off_, _anaDoc, _page);
            attributesText.put(prefGr_,rId_);
        }
        multiple = elt.hasAttribute(_anaDoc.getRendKeyWords().getAttrMultiple());
        String map_ = elt.getAttribute(_anaDoc.getRendKeyWords().getAttrMap());
        int offMap_ = getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrMap());
        rootMap = RenderAnalysis.getRootAnalyzedOperations(map_, 0, _anaDoc, _page);
        String converterValue_ = elt.getAttribute(_anaDoc.getRendKeyWords().getAttrConvertValue());
        if (multiple) {
            if (converterValue_.trim().isEmpty()) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFileName(_anaDoc.getFileName());
                badEl_.setIndexFile(getOffset());
                badEl_.buildError(_anaDoc.getRendAnalysisMessages().getEmptyAttr(),
                        _anaDoc.getRendKeyWords().getAttrConvertValue());
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
            String preRend_ = StringUtil.concat(converterValue_,AnaRendBlock.LEFT_PAR, varLoc_,AnaRendBlock.RIGHT_PAR);
            int offConvValue_ = getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrConvertValue());
            rootConverter = RenderAnalysis.getRootAnalyzedOperations(preRend_, 0, _anaDoc, _page);
            for (String v:varNames_) {
                _page.getInfosVars().removeKey(v);
            }
            StringList names_ = r_.getOpsValueRoot().getResultClass().getNames();
            if (!r_.getOpsValueRoot().getResultClass().isVariable()) {
                IterableAnalysisResult it_ = ContextUtil.getCustomTypeBase(names_,_page);
                StringList candidates_ = it_.getClassName();
                if (!candidates_.onlyOneElt()) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_anaDoc.getFileName());
                    badEl_.setIndexFile(offConvValue_);
                    badEl_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                            StringUtil.join(candidates_,AND_ERR));
                    AnalyzingDoc.addError(badEl_, _anaDoc, _page);
                }
                Mapping m_ = new Mapping();
                m_.setArg(rootConverter.getResultClass());
                m_.setParam(r_.getOpsReadRoot().getResultClass());
                if (!AnaInherits.isCorrectOrNumbers(m_, _page)) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_anaDoc.getFileName());
                    badEl_.setIndexFile(getOffset());
                    badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringUtil.join(rootConverter.getResultClass().getNames(),AND_ERR),
                            StringUtil.join(rootRead.getResultClass().getNames(),AND_ERR));
                    AnalyzingDoc.addError(badEl_, _anaDoc, _page);
                }
            }
        } else if (rootRead != null){
            Mapping m_ = new Mapping();
            m_.setArg(r_.getOpsReadRoot().getResultClass());
            m_.setParam(_anaDoc.getAliasCharSequence());
            if (!AnaInherits.isCorrectOrNumbers(m_, _page)) {
                if (converterValue_.trim().isEmpty()) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_anaDoc.getFileName());
                    badEl_.setIndexFile(getOffset());
                    badEl_.buildError(_anaDoc.getRendAnalysisMessages().getEmptyAttr(),
                            _anaDoc.getRendKeyWords().getAttrConvertValue());
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
                int offConvValue_ = getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrConvertValue());
                String preRend_ = StringUtil.concat(converterValue_,AnaRendBlock.LEFT_PAR, varLoc_,AnaRendBlock.RIGHT_PAR);
                rootConverter = RenderAnalysis.getRootAnalyzedOperations(preRend_, 0, _anaDoc, _page);
                for (String v:varNames_) {
                    _page.getInfosVars().removeKey(v);
                }
                m_.setArg(rootConverter.getResultClass());
                m_.setParam(r_.getOpsReadRoot().getResultClass());
                if (!AnaInherits.isCorrectOrNumbers(m_, _page)) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_anaDoc.getFileName());
                    badEl_.setIndexFile(offConvValue_);
                    badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringUtil.join(rootConverter.getResultClass().getNames(),AND_ERR),
                            StringUtil.join(rootRead.getResultClass().getNames(),AND_ERR));
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
                String preRend_ = StringUtil.concat(converterValue_,AnaRendBlock.LEFT_PAR, varLoc_,AnaRendBlock.RIGHT_PAR);
                int offConvValue_ = getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrConvertValue());
                rootConverter = RenderAnalysis.getRootAnalyzedOperations(preRend_, 0, _anaDoc, _page);
                for (String v:varNames_) {
                    _page.getInfosVars().removeKey(v);
                }
                m_.setArg(rootConverter.getResultClass());
                m_.setParam(r_.getOpsReadRoot().getResultClass());
                if (!AnaInherits.isCorrectOrNumbers(m_, _page)) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_anaDoc.getFileName());
                    badEl_.setIndexFile(offConvValue_);
                    badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringUtil.join(rootConverter.getResultClass().getNames(),AND_ERR),
                            StringUtil.join(rootRead.getResultClass().getNames(),AND_ERR));
                    AnalyzingDoc.addError(badEl_, _anaDoc, _page);
                }
            }
        }
        String converterField_ = elt.getAttribute(_anaDoc.getRendKeyWords().getAttrConvertField());
        if (!converterField_.trim().isEmpty()) {
            String object_ = _page.getAliasObject();
            StringList varNames_ = new StringList();
            String varLoc_ = AnaRendBlock.lookForVar(varNames_, _page);
            varNames_.add(varLoc_);
            varNameConverterField = varLoc_;
            AnaLocalVariable lv_ = new AnaLocalVariable();
            lv_.setClassName(object_);
            _page.getInfosVars().addEntry(varLoc_,lv_);
            String preRend_ = StringUtil.concat(converterField_,AnaRendBlock.LEFT_PAR, varLoc_,AnaRendBlock.RIGHT_PAR);
            int offConvValue_ = getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrConvertField());
            rootConverterField = RenderAnalysis.getRootAnalyzedOperations(preRend_, 0, _anaDoc, _page);
            for (String v:varNames_) {
                _page.getInfosVars().removeKey(v);
            }
            Mapping m_ = new Mapping();
            m_.setArg(rootConverterField.getResultClass());
            m_.setParam(_anaDoc.getAliasCharSequence());
            if (!AnaInherits.isCorrectOrNumbers(m_, _page)) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFileName(_anaDoc.getFileName());
                badEl_.setIndexFile(offConvValue_);
                badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringUtil.join(rootConverterField.getResultClass().getNames(),AND_ERR),
                        _anaDoc.getAliasCharSequence());
                AnalyzingDoc.addError(badEl_, _anaDoc, _page);
            }
        }
        String converterFieldValue_ = elt.getAttribute(_anaDoc.getRendKeyWords().getAttrConvertFieldValue());
        if (!converterFieldValue_.trim().isEmpty()) {
            String object_ = _page.getAliasObject();
            StringList varNames_ = new StringList();
            String varLoc_ = AnaRendBlock.lookForVar(varNames_, _page);
            varNames_.add(varLoc_);
            varNameConverterFieldValue = varLoc_;
            AnaLocalVariable lv_ = new AnaLocalVariable();
            lv_.setClassName(object_);
            _page.getInfosVars().addEntry(varLoc_,lv_);
            String preRend_ = StringUtil.concat(converterFieldValue_,AnaRendBlock.LEFT_PAR, varLoc_,AnaRendBlock.RIGHT_PAR);
            int offConvValue_ = getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrConvertFieldValue());
            rootConverterFieldValue = RenderAnalysis.getRootAnalyzedOperations(preRend_, 0, _anaDoc, _page);
            for (String v:varNames_) {
                _page.getInfosVars().removeKey(v);
            }
            Mapping m_ = new Mapping();
            m_.setArg(rootConverterFieldValue.getResultClass());
            m_.setParam(_anaDoc.getAliasCharSequence());
            if (!AnaInherits.isCorrectOrNumbers(m_, _page)) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFileName(_anaDoc.getFileName());
                badEl_.setIndexFile(offConvValue_);
                badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringUtil.join(rootConverterFieldValue.getResultClass().getNames(),AND_ERR),
                        _anaDoc.getAliasCharSequence());
                AnalyzingDoc.addError(badEl_, _anaDoc, _page);
            }
        }
        String default_ = elt.getAttribute(_anaDoc.getRendKeyWords().getAttrDefault());
        if (!default_.isEmpty()) {
            String mName_ = elt.getAttribute(_anaDoc.getRendKeyWords().getAttrConvert());
            if (mName_.trim().isEmpty()) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFileName(_anaDoc.getFileName());
                badEl_.setIndexFile(getOffset());
                badEl_.buildError(_anaDoc.getRendAnalysisMessages().getEmptyAttr(),
                        _anaDoc.getRendKeyWords().getAttrConvert());
                AnalyzingDoc.addError(badEl_, _anaDoc, _page);
            }
            String concat_ = StringUtil.concat(mName_,LEFT_PAR,STR,default_,STR,RIGHT_PAR);
            int offConvValue_ = getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrConvert());
            rootDefault = RenderAnalysis.getRootAnalyzedOperations(concat_, 0, _anaDoc, _page);
            Mapping m_ = new Mapping();
            m_.setArg(rootDefault.getResultClass());
            if (!multiple) {
                m_.setParam(_anaDoc.getAliasCharSequence());
                if (!AnaInherits.isCorrectOrNumbers(m_, _page)) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_anaDoc.getFileName());
                    badEl_.setIndexFile(getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrDefault()));
                    badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringUtil.join(rootDefault.getResultClass().getNames(),AND_ERR),
                            _anaDoc.getAliasCharSequence());
                    AnalyzingDoc.addError(badEl_, _anaDoc, _page);
                }
            } else {
                IterableAnalysisResult it_ = ContextUtil.getCustomTypeBase(rootDefault.getResultClass().getNames(),_page);
                StringList candidates_ = it_.getClassName();
                if (!candidates_.onlyOneElt()) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_anaDoc.getFileName());
                    badEl_.setIndexFile(getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrDefault()));
                    badEl_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                            StringUtil.join(candidates_,AND_ERR));
                    AnalyzingDoc.addError(badEl_, _anaDoc, _page);
                }
            }
        }
        String rows_ = elt.getAttribute(_anaDoc.getRendKeyWords().getAttrRows());
        int rowsGrId_ = getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrRows());
        if (!rows_.isEmpty()) {
            ResultText rId_ = new ResultText();
            rId_.buildAna(rows_, rowsGrId_, _anaDoc, _page);
            attributes.addEntry(_anaDoc.getRendKeyWords().getAttrRows(),rId_);
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

    public InputInfo getVarNames() {
        return varNames;
    }
}
