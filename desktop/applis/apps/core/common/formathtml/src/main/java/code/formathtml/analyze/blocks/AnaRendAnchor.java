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

public final class AnaRendAnchor extends AnaRendElement {
    private OperationNode root;

    private StringList texts = new StringList();
    private StringList varNames = new StringList();
    private CustList<OperationNode> roots;

    AnaRendAnchor(Element _elt, OffsetsBlock _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(Configuration _cont, AnaRendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        ResultText res_ = ResultText.buildAnchor(_cont,this, _read, _list, _anaDoc, _page);
        varNames = res_.getVarNames();
        root = res_.getOpExpAnchorRoot();
        roots = res_.getOpExpRoot();
        texts = res_.getTexts();
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
