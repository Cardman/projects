package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.util.ScopeFilter;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.ResultInput;
import code.maths.litteralcom.MathExpUtil;
import code.sml.Element;
import code.sml.NavigationCore;
import code.util.StringList;
import code.util.core.StringUtil;

public abstract class AnaRendInput extends AnaRendElement implements AnaRendInputInt {
    private OperationNode rootRead;
    private OperationNode rootValue;

    private ClassMethodIdReturn rootConverter;
    private ClassMethodIdReturn rootConverterField;
    private String idClass = NavigationCore.EMPTY_STRING;
    private String idName = NavigationCore.EMPTY_STRING;
    private String className = NavigationCore.EMPTY_STRING;
    private final ResultInput resultInput;
    protected AnaRendInput(Element _elt, int _offset) {
        super(_elt, _offset);
        resultInput = new ResultInput();
    }

    protected void processAnaInput(Element _read, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        resultInput.build(this, _read, StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrVarValue()), _anaDoc, _page);
        rootRead = resultInput.getOpsReadRoot();
        rootValue = resultInput.getOpsValueRoot();
        idClass = resultInput.getIdClass();
        idName = resultInput.getIdName();
        className = resultInput.getClassName();
        String converterValue_ = _read.getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrConvertValue()));
        if (MathExpUtil.isDollarWord(converterValue_.trim())) {
            Mapping m_ = new Mapping();
            m_.setArg(resultInput.getOpsReadRoot().getResultClass());
            m_.setParam(_anaDoc.getAliasCharSequence());
            if (!AnaInherits.isCorrectOrNumbers(m_, _page)) {
                String string_ = _page.getAliasString();
                int attr_ = getAttributeDelimiter(StringUtil.concat(_anaDoc.getPrefix(), _anaDoc.getRendKeyWords().getAttrConvertValue()));
                _page.setSumOffset(attr_);
                _page.zeroOffset();
                ClassMethodIdReturn classMethodIdReturn_ = OperationNode.tryGetDeclaredCustMethodSetIndexer(MethodAccessKind.INSTANCE, new StringList(_page.getGlobalClass()), converterValue_.trim(), new StringList(string_), _page, new ScopeFilter(null, true, true, false, _page.getGlobalType().getRootBlock()));
                rootConverter = classMethodIdReturn_;
                checkRead(_page,attr_,classMethodIdReturn_,resultInput);
            }
        } else {
            String clName_ = _read.getAttribute(StringUtil.concat(_anaDoc.getPrefix(), _anaDoc.getRendKeyWords().getAttrClassName()));
            if (!clName_.isEmpty()) {
                if (isNotConvertible(_page, clName_)) {
                    int attr_ = getAttributeDelimiter(StringUtil.concat(_anaDoc.getPrefix(), _anaDoc.getRendKeyWords().getAttrClassName()));
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFile(_page.getCurrentFile());
                    badEl_.setIndexFile(attr_);
                    badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            clName_,
                            clName_);
                    AnalyzingDoc.addError(badEl_, _page);
                }
            } else if (rootRead != null && isNotConvertible(_page, resultInput.getOpsReadRoot().getResultClass().getSingleNameOrEmpty())) {
                int attr_ = getAttributeDelimiter(StringUtil.concat(_anaDoc.getPrefix(), _anaDoc.getRendKeyWords().getAttrClassName()));
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFile(_page.getCurrentFile());
                badEl_.setIndexFile(attr_);
                badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringUtil.join(rootRead.getResultClass().getNames(), AND_ERR),
                        clName_);
                AnalyzingDoc.addError(badEl_, _page);
            }
        }
        String converterField_ = _read.getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrConvertField()));
        if (MathExpUtil.isDollarWord(converterField_.trim())) {
            String object_ = _page.getAliasObject();
            int attr_ = getAttributeDelimiter(StringUtil.concat(_anaDoc.getPrefix(), _anaDoc.getRendKeyWords().getAttrConvertField()));
            _page.setSumOffset(attr_);
            _page.zeroOffset();
            ClassMethodIdReturn classMethodIdReturn_ = OperationNode.tryGetDeclaredCustMethodSetIndexer(MethodAccessKind.INSTANCE, new StringList(_page.getGlobalClass()), converterField_.trim(), new StringList(object_), _page, new ScopeFilter(null, true, true, false, _page.getGlobalType().getRootBlock()));
            rootConverterField = classMethodIdReturn_;
            checkCharSeq(_anaDoc, _page, attr_, classMethodIdReturn_);
        }
    }

    @Override
    public Element getElt() {
        return getRead();
    }

    private static boolean isNotConvertible(AnalyzedPageEl _lgNames, String _className) {
        if (StringUtil.quickEq(_className, _lgNames.getAliasString())) {
            return false;
        }

        return !AnaTypeUtil.isPrimitiveOrWrapper(_className, _lgNames.getPrimitiveTypes());
    }
    public ClassMethodIdReturn getRootConverter() {
        return rootConverter;
    }

    public ClassMethodIdReturn getRootConverterField() {
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

    public ResultInput getResultInput() {
        return resultInput;
    }

}
