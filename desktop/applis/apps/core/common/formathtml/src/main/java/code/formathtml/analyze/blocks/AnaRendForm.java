package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.util.ScopeFilter;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.analyze.ResultText;
import code.maths.litteralcom.MathExpUtil;
import code.sml.Element;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class AnaRendForm extends AnaRendElement implements AnaRendElementAttr{
    private String sgn = "";


    private final ResultText res = new ResultText();
    private StringMap<ResultExpression> results = new StringMap<ResultExpression>();
    public AnaRendForm(Element _elt, int _offset) {
        super(_elt, _offset);
    }

    @Override
    public void processAttributes(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        if (getRead().hasAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrCommand()))) {
            String lk_ = getRead().getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrCommand()));
            int rowsGrId_ = getAttributeDelimiter(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrCommand()));
            results = res.getResults();
            for (EntryCust<String, ResultExpression> e: res.getResults().entryList()) {
                _page.zeroOffset();
                _page.setSumOffset(e.getValue().getSumOffset());
                RenderAnalysis.getRootAnalyzedOperations(0, _anaDoc, _page, e.getValue());
            }
            if (MathExpUtil.isDollarWord(lk_)) {
                StringList argCla_ = ResultText.feedArgs(res,_page);
                _page.zeroOffset();
                _page.setSumOffset(rowsGrId_);
                ClassMethodIdReturn classMethodIdReturn_ = OperationNode.tryGetDeclaredCustMethodSetIndexer(MethodAccessKind.INSTANCE, new StringList(_page.getGlobalClass()), lk_, argCla_, _page, new ScopeFilter(null, true, true, false, _page.getGlobalType().getRootBlock()));
                res.setResultAnc(classMethodIdReturn_);
                ResultText.check(_page, rowsGrId_, classMethodIdReturn_);
                sgn = AnaRendBlock.toSgn(classMethodIdReturn_,_page);
                getRead().setAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrSgn()),getSgn());
                return;
            }
            FoundErrorInterpret badEl_ = new FoundErrorInterpret();
            badEl_.setFile(_page.getCurrentFile());
            badEl_.setIndexFile(rowsGrId_);
            badEl_.buildError("");
            AnalyzingDoc.addError(badEl_, _page);
        }
    }

    public ResultText getRes() {
        return res;
    }

    public String getSgn() {
        return sgn;
    }

    @Override
    public StringList processListAttributes(AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        StringList list_ = attrList(_anaDoc);
        list_.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrCommand()));
        list_.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrSgn()));
        list_.removeAllString(_anaDoc.getRendKeyWords().getAttrAction());
        int i_ = IndexConstants.FIRST_INDEX;
        while (getRead().hasAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrParam(),Long.toString(i_)))) {
            list_.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrParam(),Long.toString(i_)));
            i_++;
        }
        return list_;
    }

    public StringMap<ResultExpression> getResults() {
        return results;
    }

}
