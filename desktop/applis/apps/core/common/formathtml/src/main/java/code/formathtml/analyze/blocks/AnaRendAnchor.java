package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.formathtml.analyze.ResultText;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.Element;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class AnaRendAnchor extends AnaRendElement {
    private OperationNode root;

    private StringList texts = new StringList();
    private StringList varNames = new StringList();
    private CustList<OperationNode> roots;
    private final ResultText res = new ResultText();

    AnaRendAnchor(Element _elt, int _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(AnaRendDocumentBlock _doc, Element _read, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        ResultText.buildAnchor(this, _read, _anaDoc, _page,res);
        varNames = res.getVarNames();
        root = res.getOpExpAnchorRoot();
        roots = res.getOpExpRoot();
        texts = res.getTexts();
    }

    @Override
    public StringList processListAttributes(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        StringList list_ = attrList(_anaDoc);
        list_.removeAllString(_anaDoc.getRendKeyWords().getAttrHref());
        list_.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrCommand()));
        list_.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrSgn()));
        return list_;
    }

    public ResultText getRes() {
        return res;
    }

    public StringList getVarNames() {
        return varNames;
    }

    public OperationNode getRoot() {
        return root;
    }

    public CustList<OperationNode> getRoots() {
        return roots;
    }

    public StringList getTexts() {
        return texts;
    }
}
