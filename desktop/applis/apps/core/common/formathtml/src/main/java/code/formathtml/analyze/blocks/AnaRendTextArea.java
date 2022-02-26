package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.analyze.ResultInput;
import code.formathtml.analyze.ResultText;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.util.InputInfo;
import code.sml.Element;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class AnaRendTextArea extends AnaRendParentBlock implements AnaRendBuildEl {
    private OperationNode rootRead;
    private OperationNode rootValue;
    private final ResultExpression resultExpressionConverter = new ResultExpression();
    private final ResultExpression resultExpressionConverterField = new ResultExpression();

    private OperationNode rootConverter;
    private OperationNode rootConverterField;
    private final StringMap<ResultText> attributesText = new StringMap<ResultText>();
    private final StringMap<ResultText> attributes = new StringMap<ResultText>();

    private String varNameConverter = EMPTY_STRING;
    private String varNameConverterField = EMPTY_STRING;
    private String varName = EMPTY_STRING;
    private InputInfo varNames = new InputInfo();
    private String id = EMPTY_STRING;
    private String idClass = EMPTY_STRING;
    private String idName = EMPTY_STRING;
    private String className = EMPTY_STRING;
    private final Element elt;
    private ResultInput resultInput;

    protected AnaRendTextArea(Element _elt, int _offset) {
        super(_offset);
        elt = _elt;
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        ResultInput r_ = new ResultInput();
        r_.build(this, elt, StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrVarValue()), _anaDoc, _page);
        varNames = r_.getVarNamesParams();
        rootRead = r_.getOpsReadRoot();
        rootValue = r_.getOpsValueRoot();
        varName = r_.getVarName();
        id = r_.getId();
        idClass = r_.getIdClass();
        idName = r_.getIdName();
        className = r_.getClassName();
        resultInput = r_;
        String converterValue_ = elt.getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrConvertValue()));
        if (rootRead != null){
            Mapping m_ = new Mapping();
            m_.setArg(r_.getOpsReadRoot().getResultClass());
            m_.setParam(_anaDoc.getAliasCharSequence());
            if (!AnaInherits.isCorrectOrNumbers(m_, _page)) {
                if (converterValue_.trim().isEmpty()) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFile(_page.getCurrentFile());
                    badEl_.setIndexFile(getOffset());
                    badEl_.buildError(_anaDoc.getRendAnalysisMessages().getEmptyAttr(),
                            StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrConvertValue()));
                    AnalyzingDoc.addError(badEl_, _page);
                }
                String string_ = _page.getAliasString();
                StringList varNames_ = new StringList();
                String varLoc_ = AnaRendBlock.lookForVar(varNames_, _page);
                varNames_.add(varLoc_);
                varNameConverter = varLoc_;
                AnaLocalVariable lv_ = new AnaLocalVariable();
                lv_.setClassName(string_);
                _page.getInfosVars().addEntry(varLoc_,lv_);
                String preRend_ = StringUtil.concat(converterValue_,AnaRendBlock.LEFT_PAR, varLoc_,AnaRendBlock.RIGHT_PAR);
                int attr_ = getAttributeDelimiter(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrConvertValue()));
                _page.setGlobalOffset(attr_);
                _page.zeroOffset();
                rootConverter = RenderAnalysis.getRootAnalyzedOperations(preRend_, 0, _anaDoc, _page,resultExpressionConverter);
                for (String v:varNames_) {
                    _page.getInfosVars().removeKey(v);
                }
                m_.setArg(rootConverter.getResultClass());
                m_.setParam(r_.getOpsReadRoot().getResultClass());
                if (!AnaInherits.isCorrectOrNumbers(m_, _page)) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFile(_page.getCurrentFile());
                    badEl_.setIndexFile(attr_);
                    badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringUtil.join(rootConverter.getResultClass().getNames(),AND_ERR),
                            StringUtil.join(rootRead.getResultClass().getNames(),AND_ERR));
                    AnalyzingDoc.addError(badEl_, _page);
                }
            }
        }
        String converterField_ = elt.getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrConvertField()));
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
            int attr_ = getAttributeDelimiter(StringUtil.concat(_anaDoc.getPrefix(), _anaDoc.getRendKeyWords().getAttrConvertField()));
            _page.setGlobalOffset(attr_);
            _page.zeroOffset();
            rootConverterField = RenderAnalysis.getRootAnalyzedOperations(preRend_, 0, _anaDoc, _page,resultExpressionConverterField);
            for (String v:varNames_) {
                _page.getInfosVars().removeKey(v);
            }
            Mapping m_ = new Mapping();
            m_.setArg(rootConverterField.getResultClass());
            m_.setParam(_anaDoc.getAliasCharSequence());
            if (!AnaInherits.isCorrectOrNumbers(m_, _page)) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFile(_page.getCurrentFile());
                badEl_.setIndexFile(attr_);
                badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringUtil.join(rootConverterField.getResultClass().getNames(),AND_ERR),
                        _anaDoc.getAliasCharSequence());
                AnalyzingDoc.addError(badEl_, _page);
            }
        }
        String id_ = elt.getAttribute(_anaDoc.getRendKeyWords().getAttrId());
        if (!id_.isEmpty()) {
            ResultText rId_ = new ResultText();
            int off_ = getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrId());
            rId_.buildIdAna(id_, off_, _anaDoc, _page);
            attributesText.put(_anaDoc.getRendKeyWords().getAttrId(),rId_);
        }
        String prefixWrite_ = _anaDoc.getPrefix();
        String prefGr_ = StringUtil.concat(prefixWrite_, _anaDoc.getRendKeyWords().getAttrGroupId());
        String groupId_ = elt.getAttribute(prefGr_);
        int offGrId_ = getAttributeDelimiter(prefGr_);
        if (!groupId_.isEmpty()) {
            ResultText rId_ = new ResultText();
            rId_.buildIdAna(groupId_, offGrId_, _anaDoc, _page);
            attributesText.put(prefGr_,rId_);
        }
        String rows_ = elt.getAttribute(_anaDoc.getRendKeyWords().getAttrRows());
        int rowsGrId_ = getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrRows());
        if (!rows_.isEmpty()) {
            ResultText rId_ = new ResultText();
            rId_.buildIdAna(rows_, rowsGrId_, _anaDoc, _page);
            attributes.addEntry(_anaDoc.getRendKeyWords().getAttrRows(),rId_);
        }
        String cols_ = elt.getAttribute(_anaDoc.getRendKeyWords().getAttrCols());
        int colsGrId_ = getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrCols());
        if (!cols_.isEmpty()) {
            ResultText rId_ = new ResultText();
            rId_.buildIdAna(cols_, colsGrId_, _anaDoc, _page);
            attributes.addEntry(_anaDoc.getRendKeyWords().getAttrCols(),rId_);
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

    public InputInfo getVarNames() {
        return varNames;
    }
}
