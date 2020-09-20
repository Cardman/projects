package code.formathtml;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.files.OffsetsBlock;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.util.AnalyzingDoc;
import code.sml.Element;
import code.sml.MutableNode;
import code.util.CustList;
import code.util.StringList;

public final class RendAnchor extends RendElement {
    private CustList<CustList<RendDynOperationNode>> opExp;
    private CustList<RendDynOperationNode> opExpAnch;

    private StringList texts = new StringList();
    private StringList varNames = new StringList();
    RendAnchor(Element _elt, OffsetsBlock _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(Configuration _cont, RendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        opExp = new CustList<CustList<RendDynOperationNode>>();
        ResultText res_ = ResultText.buildAnchor(_cont,this, _doc, _read, _list, _anaDoc, _page);
        varNames = res_.getVarNames();
        opExp = res_.getOpExp();
        opExpAnch = res_.getOpExpAnchor();
        texts = res_.getTexts();
    }

    @Override
    protected void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read) {
        processLink(_cont, (Element) _nextWrite, _read, varNames, opExp, texts,opExpAnch);
    }

    @Override
    public void reduce(Configuration _context) {
        super.reduce(_context);
        ResultText.reduce(opExp);
    }
}
