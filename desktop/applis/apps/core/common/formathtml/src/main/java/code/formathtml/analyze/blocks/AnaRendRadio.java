package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.Element;
import code.util.StringList;
import code.util.core.StringUtil;

public final class AnaRendRadio extends AnaRendInput {
    private final ResultExpression resultExpression = new ResultExpression();

    private OperationNode rootConverterFieldValue;
    private String varNameConverterFieldValue = EMPTY_STRING;
    AnaRendRadio(Element _elt, int _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(AnaRendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        processAnaInput(_read, _anaDoc, _page);
        _list.removeAllString(_anaDoc.getRendKeyWords().getAttrChecked());
        _list.removeAllString(_anaDoc.getRendKeyWords().getAttrValue());
        _list.removeAllString(_anaDoc.getRendKeyWords().getAttrName());
        _list.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrClassName()));
        _list.removeAllString(_anaDoc.getRendKeyWords().getAttrNi());
        _list.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrConvertValue()));
        _list.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrConvertFieldValue()));
        _list.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrConvertField()));
        _list.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrVarValue()));
        _list.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrValidator()));
        _list.removeAllString(_anaDoc.getRendKeyWords().getAttrType());
        String converterFieldValue_ = _read.getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrConvertFieldValue()));
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
            int attr_ = getAttributeDelimiter(StringUtil.concat(_anaDoc.getPrefix(), _anaDoc.getRendKeyWords().getAttrConvertFieldValue()));
            _page.setGlobalOffset(attr_);
            _page.zeroOffset();
            rootConverterFieldValue = RenderAnalysis.getRootAnalyzedOperations(preRend_, 0, _anaDoc, _page,resultExpression);
            AnaRendBlock.checkVars(attr_,varNames_,rootConverterFieldValue,_page,_anaDoc);
            for (String v:varNames_) {
                _page.getInfosVars().removeKey(v);
            }
            Mapping m_ = new Mapping();
            m_.setArg(rootConverterFieldValue.getResultClass());
            m_.setParam(_anaDoc.getAliasCharSequence());
            if (!AnaInherits.isCorrectOrNumbers(m_, _page)) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFile(_page.getCurrentFile());
                badEl_.setIndexFile(attr_);
                badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringUtil.join(rootConverterFieldValue.getResultClass().getNames(),AND_ERR),
                        _anaDoc.getAliasCharSequence());
                AnalyzingDoc.addError(badEl_, _page);
            }
        }
    }

    public OperationNode getRootConverterFieldValue() {
        return rootConverterFieldValue;
    }

    public String getVarNameConverterFieldValue() {
        return varNameConverterFieldValue;
    }
}
