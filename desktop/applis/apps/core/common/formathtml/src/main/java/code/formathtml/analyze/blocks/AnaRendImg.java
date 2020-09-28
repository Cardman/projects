package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.formathtml.Configuration;
import code.formathtml.analyze.ResultText;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.Element;
import code.util.CustList;
import code.util.StringList;

public final class AnaRendImg extends AnaRendElement {

    private CustList<OperationNode> roots;

    private StringList texts = new StringList();
    AnaRendImg(Element _elt, OffsetsBlock _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(Configuration _cont, AnaRendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        ResultText res_ = new ResultText();
        String pageName_ = _read.getAttribute(_cont.getRendKeyWords().getAttrSrc());
        int rowsGrId_ = getAttributeDelimiter(_cont.getRendKeyWords().getAttrSrc());
        res_.buildAna(pageName_, rowsGrId_, _anaDoc, _page);
        roots = res_.getOpExpRoot();
        texts = res_.getTexts();
        _list.removeAllString(_cont.getRendKeyWords().getAttrSrc());
    }

    public CustList<OperationNode> getRoots() {
        return roots;
    }

    public StringList getTexts() {
        return texts;
    }
}
