package code.formathtml;

import code.expressionlanguage.files.OffsetsBlock;
import code.formathtml.exec.RendDynOperationNode;
import code.sml.Element;
import code.sml.MutableNode;
import code.util.CustList;
import code.util.StringList;

public final class RendAnchor extends RendElement {
    private CustList<CustList<RendDynOperationNode>> opExp;

    private StringList texts = new StringList();
    private StringList varNames = new StringList();
    RendAnchor(Element _elt, OffsetsBlock _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(Configuration _cont, RendDocumentBlock _doc, Element _read, StringList _list) {
        opExp = new CustList<CustList<RendDynOperationNode>>();
        ResultText res_ = ResultText.buildAnchor(_cont, _doc, _read, _list);
        opExp = res_.getOpExp();
        texts = res_.getTexts();
    }

    @Override
    protected void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read) {
        processLink(_cont, (Element) _nextWrite, _read, varNames, opExp, texts);
    }

    @Override
    public void reduce(Configuration _context) {
        super.reduce(_context);
        ResultText.reduce(opExp);
    }
}
