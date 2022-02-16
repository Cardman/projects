package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.analyze.ResultInput;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.util.InputInfo;
import code.sml.Element;
import code.util.StringList;
import code.util.core.StringUtil;

public abstract class AnaRendInput extends AnaRendElement {
    private OperationNode rootRead;
    private OperationNode rootValue;
    private final ResultExpression resultExpressionConverter = new ResultExpression();
    private final ResultExpression resultExpressionConverterField = new ResultExpression();

    private OperationNode rootConverter;
    private OperationNode rootConverterField;
    private String varName = EMPTY_STRING;
    private InputInfo varNames = new InputInfo();
    private String varNameConverter = EMPTY_STRING;
    private String varNameConverterField = EMPTY_STRING;
    private String id = EMPTY_STRING;
    private String idClass = EMPTY_STRING;
    private String idName = EMPTY_STRING;
    private String className = EMPTY_STRING;
    private ResultInput resultInput;
    AnaRendInput(Element _elt, int _offset) {
        super(_elt, _offset);
    }

    protected void processAnaInput(Element _read, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        ResultInput r_ = new ResultInput();
        r_.build(this, _read, StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrVarValue()), _anaDoc, _page);
        varNames = r_.getVarNamesParams();
        resultInput = r_;
        rootRead = r_.getOpsReadRoot();
        rootValue = r_.getOpsValueRoot();
        varName = r_.getVarName();
        id = r_.getId();
        idClass = r_.getIdClass();
        idName = r_.getIdName();
        className = r_.getClassName();
        String converterValue_ = _read.getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrConvertValue()));
        if (!converterValue_.trim().isEmpty()) {
            Mapping m_ = new Mapping();
            m_.setArg(r_.getOpsReadRoot().getResultClass());
            m_.setParam(_anaDoc.getAliasCharSequence());
            if (!AnaInherits.isCorrectOrNumbers(m_, _page)) {
                String string_ = _page.getAliasString();
                StringList varNames_ = new StringList();
                String varLoc_ = AnaRendBlock.lookForVar(varNames_, _page);
                varNames_.add(varLoc_);
                varNameConverter = varLoc_;
                AnaLocalVariable lv_ = new AnaLocalVariable();
                lv_.setClassName(string_);
                _page.getInfosVars().addEntry(varLoc_,lv_);
                String preRend_ = StringUtil.concat(converterValue_,AnaRendBlock.LEFT_PAR, varLoc_,AnaRendBlock.RIGHT_PAR);
                int attr_ = getAttributeDelimiter(StringUtil.concat(_anaDoc.getPrefix(), _anaDoc.getRendKeyWords().getAttrConvertValue()));
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
                    badEl_.setFileName(_anaDoc.getFileName());
                    badEl_.setIndexFile(attr_);
                    badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringUtil.join(rootConverter.getResultClass().getNames(),AND_ERR),
                            StringUtil.join(rootRead.getResultClass().getNames(),AND_ERR));
                    AnalyzingDoc.addError(badEl_, _anaDoc, _page);
                }
            }
        } else {
            String clName_ = _read.getAttribute(StringUtil.concat(_anaDoc.getPrefix(), _anaDoc.getRendKeyWords().getAttrClassName()));
            if (!clName_.isEmpty()) {
                if (isNotConvertible(_page, clName_)) {
                    int attr_ = getAttributeDelimiter(StringUtil.concat(_anaDoc.getPrefix(), _anaDoc.getRendKeyWords().getAttrClassName()));
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_anaDoc.getFileName());
                    badEl_.setIndexFile(attr_);
                    badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            clName_,
                            clName_);
                    AnalyzingDoc.addError(badEl_, _anaDoc, _page);
                }
            } else if (rootRead != null) {
                if (isNotConvertible(_page, r_.getOpsReadRoot().getResultClass().getSingleNameOrEmpty())) {
                    int attr_ = getAttributeDelimiter(StringUtil.concat(_anaDoc.getPrefix(), _anaDoc.getRendKeyWords().getAttrClassName()));
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_anaDoc.getFileName());
                    badEl_.setIndexFile(attr_);
                    badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringUtil.join(rootRead.getResultClass().getNames(),AND_ERR),
                            clName_);
                    AnalyzingDoc.addError(badEl_, _anaDoc, _page);
                }
            }
        }
        String converterField_ = _read.getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrConvertField()));
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
                badEl_.setFileName(_anaDoc.getFileName());
                badEl_.setIndexFile(attr_);
                badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringUtil.join(rootConverterField.getResultClass().getNames(),AND_ERR),
                        _anaDoc.getAliasCharSequence());
                AnalyzingDoc.addError(badEl_, _anaDoc, _page);
            }
        }
    }
    private static boolean isNotConvertible(AnalyzedPageEl _lgNames, String _className) {
        if (StringUtil.quickEq(_className, _lgNames.getAliasString())) {
            return false;
        }

        return !AnaTypeUtil.isPrimitiveOrWrapper(_className, _lgNames.getPrimitiveTypes());
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

    public InputInfo getVarNames() {
        return varNames;
    }
}
