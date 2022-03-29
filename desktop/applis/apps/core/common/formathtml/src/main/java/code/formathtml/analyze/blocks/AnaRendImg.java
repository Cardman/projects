package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.formathtml.analyze.ResultText;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.Element;
import code.util.CustList;
import code.util.StringList;

public final class AnaRendImg extends AnaRendElement {

    private CustList<OperationNode> roots;

    private StringList texts = new StringList();
    private final ResultText res;
    public AnaRendImg(Element _elt, int _offset) {
        super(_elt, _offset);
        res = new ResultText();
    }

    @Override
    protected void processAttributes(AnaRendDocumentBlock _doc, Element _read, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        String pageName_ = _read.getAttribute(_anaDoc.getRendKeyWords().getAttrSrc());
        int rowsGrId_ = getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrSrc());
        res.buildIdAna(pageName_, rowsGrId_, _anaDoc, _page);
        roots = res.getOpExpRoot();
        texts = res.getTexts();
    }
    @Override
    public StringList processListAttributes(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        StringList list_ = attrList(_anaDoc);
        list_.removeAllString(_anaDoc.getRendKeyWords().getAttrSrc());
        return list_;
    }

    public ResultText getRes() {
        return res;
    }

    public CustList<OperationNode> getRoots() {
        return roots;
    }

    public StringList getTexts() {
        return texts;
    }
}
